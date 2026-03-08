package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.workflow.RequestContext;
import com.amazon.identity.auth.device.endpoint.ResponseUri;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class RequestManager {
    private static final int MAX_ACTIVE_REQUESTS = 10;
    public static final String REQUEST_ID_FIELD = "clientRequestId";
    public static final String STATE_PARAMETER_NAME = "state";
    private final Map<String, AbstractRequest> activeRequests;
    private final ExternalBrowserManager externalBrowserManager;
    private static final String LOG_TAG = RequestManager.class.getName();
    private static RequestManager INSTANCE = null;

    public RequestManager(ExternalBrowserManager externalBrowserManager) {
        this.activeRequests = Collections.synchronizedMap(new LinkedHashMap(10));
        this.externalBrowserManager = externalBrowserManager;
    }

    private RequestManager() {
        this(new ExternalBrowserManager());
    }

    public static synchronized RequestManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestManager();
        }
        return INSTANCE;
    }

    public static void setInstance(RequestManager requestManager) {
        INSTANCE = requestManager;
    }

    public void executeRequest(AbstractRequest abstractRequest, Context context) throws AuthError {
        MAPLog.d(LOG_TAG, "Executing request " + abstractRequest.getRequestId());
        if (!abstractRequest.canAttempt()) {
            throw new AuthError(String.format("Reached maximum attempts for the request: %s", abstractRequest.getRequestId()), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        abstractRequest.incrementAttemptCount();
        cleanupOldActiveRequests();
        this.activeRequests.put(abstractRequest.getRequestId(), abstractRequest);
        this.externalBrowserManager.openUrl(abstractRequest, abstractRequest.getUrl(context), context);
    }

    public boolean handleResponse(Uri uri, Context context) throws AuthError {
        return handleResponse(uri, context, null);
    }

    public boolean handleResponse(Uri uri, Context context, RequestContext requestContext) throws AuthError {
        String requestIdFromResponseUri = getRequestIdFromResponseUri(uri);
        MAPLog.pii(LOG_TAG, "Handling response for request " + requestIdFromResponseUri, "uri=" + uri.toString());
        AbstractRequest abstractRequestRemove = this.activeRequests.remove(requestIdFromResponseUri);
        if (abstractRequestRemove == null) {
            return false;
        }
        if (requestContext != null) {
            abstractRequestRemove.getOriginalRequest().setRequestContext(requestContext);
        }
        if (abstractRequestRemove.handleResponse(uri, context)) {
            return true;
        }
        MAPLog.d(LOG_TAG, "Retrying request " + requestIdFromResponseUri);
        executeRequest(abstractRequestRemove, context);
        return true;
    }

    public RequestContext getRequestContextForRequest(String str) {
        AbstractRequest abstractRequest = this.activeRequests.get(str);
        if (abstractRequest == null || abstractRequest.getOriginalRequest() == null) {
            return null;
        }
        return abstractRequest.getOriginalRequest().getRequestContext();
    }

    private void cleanupOldActiveRequests() {
        while (this.activeRequests.size() >= 10) {
            synchronized (this.activeRequests) {
                String next = this.activeRequests.keySet().iterator().next();
                MAPLog.d(LOG_TAG, "Purging active request " + next);
                this.activeRequests.remove(next);
                ResponseManager.getInstance().removePendingResponse(next);
            }
        }
    }

    public static String getRequestIdFromResponseUri(Uri uri) throws AuthError {
        String str = new ResponseUri(uri).getStateValues().get("clientRequestId");
        if (str != null) {
            return str;
        }
        throw new AuthError(String.format("Response does not have a requestId: %s", uri.toString()), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
    }

    public static boolean isInteractiveRequest(Uri uri) throws AuthError {
        return new ResponseUri(uri).getStateValues().get(LWAConstants.INTERACTIVE_REQUEST_TYPE_KEY) != null;
    }
}