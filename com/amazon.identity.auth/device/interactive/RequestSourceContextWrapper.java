package com.amazon.identity.auth.device.interactive;

import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class RequestSourceContextWrapper implements RequestSource {
    private static final String LOG_TAG = RequestSourceContextWrapper.class.getName();
    private final WeakReference<Context> contextWeakReference;
    private WorkflowSupportFragment workflowSupportFragment;

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public boolean isHookNeededOnUIResume() {
        return false;
    }

    public RequestSourceContextWrapper(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must be non-null");
        }
        this.contextWeakReference = new WeakReference<>(context);
        this.workflowSupportFragment = null;
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public void onStartRequest(InteractiveRequestRecord interactiveRequestRecord) {
        InteractiveState interactiveState = getInteractiveState();
        if (interactiveState != null) {
            interactiveState.onRequestStart(interactiveRequestRecord);
        }
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public InteractiveState getInteractiveState() {
        if (this.workflowSupportFragment == null) {
            this.workflowSupportFragment = new WorkflowSupportFragment();
            this.workflowSupportFragment.setApplicationContext(this.contextWeakReference.get());
        }
        return this.workflowSupportFragment.getState();
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Object getBackingObject() {
        return this.contextWeakReference.get();
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Context getContext() {
        return this.contextWeakReference.get();
    }

    public int hashCode() {
        WeakReference<Context> weakReference = this.contextWeakReference;
        return 31 + ((weakReference == null || weakReference.get() == null) ? 0 : this.contextWeakReference.get().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequestSourceContextWrapper requestSourceContextWrapper = (RequestSourceContextWrapper) obj;
        WeakReference<Context> weakReference = this.contextWeakReference;
        if (weakReference == null) {
            if (requestSourceContextWrapper.contextWeakReference != null) {
                return false;
            }
        } else {
            if (requestSourceContextWrapper.contextWeakReference == null) {
                return false;
            }
            if (weakReference.get() == null) {
                if (requestSourceContextWrapper.contextWeakReference.get() != null) {
                    return false;
                }
            } else if (!this.contextWeakReference.get().equals(requestSourceContextWrapper.contextWeakReference.get())) {
                return false;
            }
        }
        return true;
    }
}