package com.amazon.identity.auth.device.interactive;

import android.os.Bundle;
import com.amazon.identity.auth.device.ResponseManager;
import com.amazon.identity.auth.device.api.workflow.RequestContext;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
final class GenericInteractiveState implements InteractiveState {
    static final String INTERACTIVE_STATE_ID_KEY = "interactiveStateId";
    static final String REQUEST_LIST_KEY = "requestRecords";
    private WeakReference<InteractiveStateFragment> backingFragment;
    private final InteractiveRequestMap interactiveRequestMap;
    private final Set<InteractiveRequestRecord> requests;
    private final ResponseManager responseManager;
    private UUID stateId;
    private static final String LOG_TAG = GenericInteractiveState.class.getName();
    static final String SAVED_INSTANCE_STATE_KEY = InteractiveState.class.getName() + ".instanceState";

    GenericInteractiveState(InteractiveStateFragment interactiveStateFragment) {
        this(interactiveStateFragment, ResponseManager.getInstance(), InteractiveRequestMap.getInstance());
    }

    GenericInteractiveState(InteractiveStateFragment interactiveStateFragment, ResponseManager responseManager, InteractiveRequestMap interactiveRequestMap) {
        this.backingFragment = new WeakReference<>(interactiveStateFragment);
        this.responseManager = responseManager;
        this.interactiveRequestMap = interactiveRequestMap;
        this.requests = new HashSet();
        this.stateId = UUID.randomUUID();
    }

    String getId() {
        return this.stateId.toString();
    }

    Set<InteractiveRequestRecord> getRequests() {
        return this.requests;
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveState
    public synchronized void onRequestStart(InteractiveRequestRecord interactiveRequestRecord) {
        String str = interactiveRequestRecord.getFragmentWrapper() == null ? "activity" : "fragment";
        MAPLog.d(LOG_TAG, "InteractiveState " + this.stateId + ": Recording " + str + " request " + interactiveRequestRecord.getRequestId());
        this.requests.add(interactiveRequestRecord);
    }

    public boolean shouldAttemptProcessingResponses() {
        return (this.requests.size() > 0) && (this.responseManager.size() > 0);
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveState
    public synchronized void processPendingResponses(RequestContext requestContext) {
        if (shouldAttemptProcessingResponses()) {
            doProcessPendingResponses(requestContext);
        } else {
            MAPLog.d(LOG_TAG, "InteractiveState " + this.stateId + ": No responses to process");
        }
    }

    void doProcessPendingResponses(RequestContext requestContext) {
        RequestContext requestContextForRequest;
        LinkedList linkedList = new LinkedList();
        for (InteractiveRequestRecord interactiveRequestRecord : this.requests) {
            String requestId = interactiveRequestRecord.getRequestId();
            if (this.responseManager.hasPendingResponseForRequest(requestId) && (requestContextForRequest = getRequestContextForRequest(interactiveRequestRecord)) == requestContext) {
                MAPLog.d(LOG_TAG, "InteractiveState " + this.stateId + ": Processing request " + requestId);
                requestContextForRequest.processResponse(interactiveRequestRecord, this.responseManager.removePendingResponse(requestId));
                linkedList.add(interactiveRequestRecord);
            }
        }
        this.requests.removeAll(linkedList);
    }

    RequestContext getRequestContextForRequest(InteractiveRequestRecord interactiveRequestRecord) {
        return this.interactiveRequestMap.getRequestContextForSource(getRequestSourceForRequest(interactiveRequestRecord));
    }

    Object getRequestSourceForRequest(InteractiveRequestRecord interactiveRequestRecord) {
        Bundle fragmentWrapper = interactiveRequestRecord.getFragmentWrapper();
        Object fragment = fragmentWrapper != null ? this.backingFragment.get().getFragment(fragmentWrapper) : null;
        if (fragment == null) {
            fragment = this.backingFragment.get().getParentActivity();
        }
        return fragment == null ? this.backingFragment.get().getApplicationContext() : fragment;
    }

    public void readFromBundle(Bundle bundle) {
        Bundle bundle2;
        if (bundle == null || (bundle2 = bundle.getBundle(SAVED_INSTANCE_STATE_KEY)) == null) {
            return;
        }
        MAPLog.d(LOG_TAG, "Restoring interactive state from saved instance state");
        String string = bundle2.getString(INTERACTIVE_STATE_ID_KEY);
        if (string == null) {
            MAPLog.w(LOG_TAG, "Restoring interactive state from instance state but no state ID found");
        } else {
            MAPLog.d(LOG_TAG, "Reassigning interactive state " + this.stateId + " to " + string);
            this.stateId = UUID.fromString(string);
        }
        ArrayList parcelableArrayList = bundle2.getParcelableArrayList(REQUEST_LIST_KEY);
        if (parcelableArrayList != null) {
            this.requests.addAll(parcelableArrayList);
        }
    }

    public void writeToBundle(Bundle bundle) {
        if (this.requests.size() > 0) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(INTERACTIVE_STATE_ID_KEY, this.stateId.toString());
            bundle2.putParcelableArrayList(REQUEST_LIST_KEY, new ArrayList<>(this.requests));
            bundle.putBundle(SAVED_INSTANCE_STATE_KEY, bundle2);
            MAPLog.d(LOG_TAG, "InteractiveState " + this.stateId + ": writing to save instance state");
        }
    }
}