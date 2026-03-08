package com.amazon.identity.auth.device.api.workflow;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.RequestManager;
import com.amazon.identity.auth.device.endpoint.ResponseUri;
import com.amazon.identity.auth.device.interactive.AggregateInteractiveListener;
import com.amazon.identity.auth.device.interactive.InteractiveAPI;
import com.amazon.identity.auth.device.interactive.InteractiveListener;
import com.amazon.identity.auth.device.interactive.InteractiveRequest;
import com.amazon.identity.auth.device.interactive.InteractiveRequestMap;
import com.amazon.identity.auth.device.interactive.InteractiveRequestRecord;
import com.amazon.identity.auth.device.interactive.InteractiveState;
import com.amazon.identity.auth.device.interactive.InternalInteractiveListener;
import com.amazon.identity.auth.device.interactive.RequestSource;
import com.amazon.identity.auth.device.interactive.RequestSourceActivityWrapper;
import com.amazon.identity.auth.device.interactive.RequestSourceContextWrapper;
import com.amazon.identity.auth.device.interactive.RequestSourceFragmentActivityWrapper;
import com.amazon.identity.auth.device.interactive.RequestSourceFragmentWrapper;
import com.amazon.identity.auth.device.interactive.RequestSourceSupportFragmentWrapper;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class RequestContext {
    private static final String LOG_TAG = RequestContext.class.getName();
    private final Map<String, Set<InteractiveListener<?, ?, ?>>> listeners;
    private final UUID requestContextId;
    private final RequestSource requestSource;

    public static RequestContext create(Context context) {
        if (context instanceof FragmentActivity) {
            return create((FragmentActivity) context);
        }
        if (context instanceof Activity) {
            return create((Activity) context);
        }
        return create(new RequestSourceContextWrapper(context));
    }

    @Deprecated
    public static RequestContext create(Activity activity) {
        return create(new RequestSourceActivityWrapper(activity));
    }

    public static RequestContext create(Fragment fragment) {
        return create(new RequestSourceFragmentWrapper(fragment));
    }

    @Deprecated
    public static RequestContext create(FragmentActivity fragmentActivity) {
        return create(new RequestSourceFragmentActivityWrapper(fragmentActivity));
    }

    public static RequestContext create(androidx.fragment.app.Fragment fragment) {
        return create(new RequestSourceSupportFragmentWrapper(fragment));
    }

    private static RequestContext create(RequestSource requestSource) {
        Object backingObject = requestSource.getBackingObject();
        RequestContext requestContextForSource = InteractiveRequestMap.getInstance().getRequestContextForSource(backingObject);
        if (requestContextForSource == null) {
            RequestContext requestContext = new RequestContext(requestSource);
            InteractiveRequestMap.getInstance().putRequestContextForSource(backingObject, requestContext);
            MAPLog.pii(LOG_TAG, "Created RequestContext " + requestContext.requestContextId, "requestSource=" + requestSource.getBackingObject());
            return requestContext;
        }
        MAPLog.pii(LOG_TAG, "Reusing RequestContext " + requestContextForSource.requestContextId, "requestSource=" + requestSource.getBackingObject());
        return requestContextForSource;
    }

    RequestContext(RequestSource requestSource) {
        if (requestSource == null) {
            throw new IllegalArgumentException("requestSource must be non-null");
        }
        this.requestSource = requestSource;
        this.requestContextId = UUID.randomUUID();
        this.listeners = new HashMap();
    }

    String getId() {
        return this.requestContextId.toString();
    }

    public Context getContext() {
        return this.requestSource.getContext();
    }

    public void registerListener(InteractiveListener<?, ?, ?> interactiveListener) {
        if (interactiveListener == null) {
            throw new IllegalArgumentException("listener must be non-null");
        }
        String requestType = interactiveListener.getRequestType();
        MAPLog.pii(LOG_TAG, "RequestContext " + this.requestContextId + ": registerListener for of request type " + requestType, "listener=" + interactiveListener);
        synchronized (this.listeners) {
            Set<InteractiveListener<?, ?, ?>> hashSet = this.listeners.get(requestType);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                this.listeners.put(requestType, hashSet);
            }
            hashSet.add(interactiveListener);
        }
    }

    public boolean unregisterListener(InteractiveListener<?, ?, ?> interactiveListener) {
        if (interactiveListener == null) {
            throw new IllegalArgumentException("listener must be non-null");
        }
        String requestType = interactiveListener.getRequestType();
        MAPLog.pii(LOG_TAG, "RequestContext " + this.requestContextId + ": unregisterListener for listener of request type " + requestType, "listener=" + interactiveListener);
        synchronized (this.listeners) {
            Set<InteractiveListener<?, ?, ?>> set = this.listeners.get(requestType);
            if (set == null) {
                return false;
            }
            return set.remove(interactiveListener);
        }
    }

    public void onResume() {
        MAPLog.d(LOG_TAG, "RequestContext " + this.requestContextId + ": onResume");
        InteractiveState interactiveState = this.requestSource.getInteractiveState();
        if (interactiveState != null) {
            interactiveState.processPendingResponses(this);
            return;
        }
        MAPLog.e(LOG_TAG, "RequestContext " + this.requestContextId + ": could not retrieve interactive state to process pending responses");
    }

    public void onStartRequest(InteractiveRequestRecord interactiveRequestRecord) {
        if (interactiveRequestRecord == null) {
            throw new IllegalArgumentException("request must be non-null");
        }
        MAPLog.d(LOG_TAG, "RequestContext " + this.requestContextId + ": onStartRequest for request ID " + interactiveRequestRecord.getRequestId());
        this.requestSource.onStartRequest(interactiveRequestRecord);
    }

    public void processResponse(final InteractiveRequestRecord interactiveRequestRecord, final Uri uri) {
        if (interactiveRequestRecord == null) {
            throw new IllegalArgumentException("request must be non-null");
        }
        if (uri == null) {
            throw new IllegalArgumentException("uri must be non-null");
        }
        MAPLog.pii(LOG_TAG, "RequestContext " + this.requestContextId + ": processing response", "uri=" + uri.toString());
        final Context context = this.requestSource.getContext();
        ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.api.workflow.RequestContext.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (RequestManager.getInstance().handleResponse(uri, context, RequestContext.this)) {
                        return;
                    }
                    Iterator it = RequestContext.this.getListenersInternal(new ResponseUri(uri).getStateValues().get(LWAConstants.INTERACTIVE_REQUEST_TYPE_KEY), InternalInteractiveListener.class).iterator();
                    while (it.hasNext()) {
                        ((InternalInteractiveListener) it.next()).onRequestCompletion(context, interactiveRequestRecord, uri);
                    }
                } catch (Exception e2) {
                    MAPLog.e(RequestContext.LOG_TAG, "RequestContext " + RequestContext.this.requestContextId + ": Unable to handle activity result", e2);
                }
            }
        });
    }

    public void assertListenerPresent(InteractiveAPI interactiveAPI) throws ListenerNotFoundException {
        if (interactiveAPI == null) {
            throw new IllegalArgumentException("api must be non-null");
        }
        getListenersInternal(interactiveAPI.getRequestType(), null);
    }

    public <T extends InteractiveListener<S, U, V>, S, U, V> InteractiveListener<S, U, V> getAggregateListener(InteractiveRequest<T, S, U, V> interactiveRequest) throws ListenerNotFoundException {
        return new AggregateInteractiveListener(interactiveRequest.getRequestType(), getListeners(interactiveRequest, interactiveRequest.getListenerClass()));
    }

    public <T> Set<T> getListeners(InteractiveAPI interactiveAPI, Class<T> cls) {
        if (interactiveAPI == null) {
            throw new IllegalArgumentException("api must be non-null");
        }
        if (cls == null) {
            throw new IllegalArgumentException("listenerClass must be non-null");
        }
        return getListenersInternal(interactiveAPI.getRequestType(), cls);
    }

    public boolean isHookNeededOnUIResume() {
        return this.requestSource.isHookNeededOnUIResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Set<T> getListenersInternal(String str, Class<T> cls) throws ListenerNotFoundException {
        Set<InteractiveListener<?, ?, ?>> set;
        if (str == null) {
            throw new IllegalArgumentException("requestType must be non-null");
        }
        synchronized (this.listeners) {
            set = this.listeners.get(str);
        }
        if (set == null || set.isEmpty()) {
            throw new ListenerNotFoundException("No listeners were registered with type \"" + str + "\" for RequestContext " + this.requestContextId + ". Listener types present: " + this.listeners.keySet());
        }
        if (cls == null) {
            return null;
        }
        HashSet hashSet = new HashSet(set.size());
        Iterator<InteractiveListener<?, ?, ?>> it = set.iterator();
        while (it.hasNext()) {
            try {
                hashSet.add(cls.cast(it.next()));
            } catch (ClassCastException e2) {
                throw new ListenerNotFoundException("Failed to retrieve listener of class type \"" + cls.toString() + "\" for request type \"" + str + "\"", e2);
            }
        }
        return hashSet;
    }
}