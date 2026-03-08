package com.amazon.identity.auth.device.interactive;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class RequestSourceFragmentActivityWrapper implements RequestSource {
    private static final String LOG_TAG = RequestSourceFragmentActivityWrapper.class.getName();
    private final WeakReference<FragmentActivity> activityReference;

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public boolean isHookNeededOnUIResume() {
        return true;
    }

    public RequestSourceFragmentActivityWrapper(FragmentActivity fragmentActivity) {
        if (fragmentActivity == null) {
            throw new IllegalArgumentException("activity must be non-null");
        }
        this.activityReference = new WeakReference<>(fragmentActivity);
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
        FragmentActivity fragmentActivity = this.activityReference.get();
        if (fragmentActivity != null) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            try {
                InteractiveStateFragment interactiveStateFragment = (InteractiveStateFragment) supportFragmentManager.findFragmentByTag(InteractiveStateFragment.TAG_ID);
                InteractiveStateFragment interactiveStateFragment2 = interactiveStateFragment;
                if (interactiveStateFragment == null) {
                    WorkflowSupportFragment workflowSupportFragment = new WorkflowSupportFragment();
                    supportFragmentManager.beginTransaction().add(workflowSupportFragment, InteractiveStateFragment.TAG_ID).commit();
                    interactiveStateFragment2 = workflowSupportFragment;
                }
                return interactiveStateFragment2.getState();
            } catch (ClassCastException e2) {
                MAPLog.e(LOG_TAG, "Found an invalid fragment looking for fragment with tag " + InteractiveStateFragment.TAG_ID + ". Please use a different fragment tag.", e2);
                return null;
            }
        }
        MAPLog.e(LOG_TAG, "Failed to get InteractiveState on a garbage-collected FragmentActivity");
        return null;
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Object getBackingObject() {
        return this.activityReference.get();
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Context getContext() {
        return this.activityReference.get();
    }

    public int hashCode() {
        WeakReference<FragmentActivity> weakReference = this.activityReference;
        return 31 + ((weakReference == null || weakReference.get() == null) ? 0 : this.activityReference.get().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequestSourceFragmentActivityWrapper requestSourceFragmentActivityWrapper = (RequestSourceFragmentActivityWrapper) obj;
        WeakReference<FragmentActivity> weakReference = this.activityReference;
        if (weakReference == null) {
            if (requestSourceFragmentActivityWrapper.activityReference != null) {
                return false;
            }
        } else {
            if (requestSourceFragmentActivityWrapper.activityReference == null) {
                return false;
            }
            if (weakReference.get() == null) {
                if (requestSourceFragmentActivityWrapper.activityReference.get() != null) {
                    return false;
                }
            } else if (!this.activityReference.get().equals(requestSourceFragmentActivityWrapper.activityReference.get())) {
                return false;
            }
        }
        return true;
    }
}