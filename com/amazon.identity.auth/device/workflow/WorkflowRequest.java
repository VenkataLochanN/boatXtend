package com.amazon.identity.auth.device.workflow;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.identity.auth.device.AbstractRequest;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier;
import com.amazon.identity.auth.device.authorization.TokenHelper;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.endpoint.OneTimeCodeRequest;
import com.amazon.identity.auth.device.endpoint.OneTimeCodeResponse;
import com.amazon.identity.auth.device.endpoint.ServerCommunication;
import com.amazon.identity.auth.device.interactive.InteractiveRequest;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class WorkflowRequest extends AbstractRequest {
    private static final String LOG_TAG = WorkflowRequest.class.getName();
    private static final String RELYING_PARTY_CONTEXT_PARAMETER_NAME = "rpContext";
    private static final String RP_CONTEXT_ONE_TIME_CODE_FIELD = "code";
    private static final String RP_CONTEXT_REDIRECT_URL_FIELD = "redirect_uri";
    private final ThirdPartyAppIdentifier appIdentifier;
    private final int minTokenLifetime;
    private final ServerCommunication serverCommunication;
    private final WorkflowToken workflowToken;
    private final String workflowUrl;

    @Override // com.amazon.identity.auth.device.AbstractRequest
    public int getMaxAttemptCount() {
        return 2;
    }

    public WorkflowRequest(InteractiveRequest<?, ?, ?, ?> interactiveRequest, String str, WorkflowToken workflowToken, int i, ServerCommunication serverCommunication) throws AuthError {
        super(interactiveRequest);
        this.appIdentifier = new ThirdPartyAppIdentifier();
        workflowToken.assertWorkflowUrlIsAllowed(str);
        this.workflowUrl = str;
        this.workflowToken = workflowToken;
        this.minTokenLifetime = i;
        this.serverCommunication = serverCommunication;
    }

    @Override // com.amazon.identity.auth.device.AbstractRequest
    public String getUrl(Context context) throws AuthError {
        try {
            return Uri.parse(this.workflowUrl).buildUpon().appendQueryParameter(RELYING_PARTY_CONTEXT_PARAMETER_NAME, getRelyingPartyContextParameter(context)).build().toString();
        } catch (IOException e2) {
            throw new AuthError("Error communicating with server", e2, AuthError.ERROR_TYPE.ERROR_IO);
        } catch (JSONException e3) {
            throw new AuthError("Error while generating workflow URL", e3, AuthError.ERROR_TYPE.ERROR_UNKNOWN);
        }
    }

    @Override // com.amazon.identity.auth.device.AbstractRequest
    public boolean handleResponse(Uri uri, Context context) {
        MAPLog.pii(LOG_TAG, "Received response from workflow", "response=" + uri.toString());
        WorkflowResponse workflowResponse = new WorkflowResponse(uri);
        if (workflowResponse.isError() && workflowResponse.isRecoverable()) {
            MAPLog.d(LOG_TAG, "Workflow response is a recoverable error. Retrying.");
            return false;
        }
        this.originalRequest.onRequestCompletion(context, getInteractiveRequestRecord(), uri);
        return true;
    }

    private String getRelyingPartyContextParameter(Context context) throws JSONException, AuthError, IOException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", getOneTimeCode(context));
        jSONObject.put(RP_CONTEXT_REDIRECT_URL_FIELD, getRedirectUrl(context));
        jSONObject.put("state", getStateField());
        return jSONObject.toString();
    }

    private String getStateField() throws JSONException {
        return String.format("%s=%s&%s=%s", "clientRequestId", this.requestId, LWAConstants.INTERACTIVE_REQUEST_TYPE_KEY, this.originalRequest.getRequestType());
    }

    private String getOneTimeCode(Context context) throws AuthError, IOException {
        Bundle bundle = new Bundle();
        bundle.putInt(AuthzConstants.BUNDLE_KEY.MINIMUM_TOKEN_LIFETIME.val, this.minTokenLifetime);
        String tokenInternal = TokenHelper.getTokenInternal(context, context.getPackageName(), this.workflowToken.getScopes(), this.appIdentifier.getAppInfo(context.getPackageName(), context), bundle);
        if (tokenInternal == null) {
            throw new AuthError("Could not find token for scopes required to open workflow", AuthError.ERROR_TYPE.ERROR_MISSING_TOKEN_FOR_REQUIRED_SCOPES);
        }
        return ((OneTimeCodeResponse) this.serverCommunication.executeRequest(new OneTimeCodeRequest(context, this.workflowToken.getClientId(), tokenInternal, getAppInfo(context)), context)).getOneTimeCode();
    }

    private String getRedirectUrl(Context context) {
        return this.appIdentifier.getRedirectUrl(context);
    }

    private AppInfo getAppInfo(Context context) {
        return this.appIdentifier.getAppInfo(context.getPackageName(), context);
    }
}