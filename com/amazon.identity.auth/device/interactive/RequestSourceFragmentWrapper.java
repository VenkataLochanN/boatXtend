package com.amazon.identity.auth.device.interactive;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class RequestSourceFragmentWrapper implements RequestSource {
    private static final String LOG_TAG = RequestSourceFragmentWrapper.class.getName();
    private final WeakReference<Fragment> fragmentReference;

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public boolean isHookNeededOnUIResume() {
        return true;
    }

    public RequestSourceFragmentWrapper(Fragment fragment) {
        if (fragment == null) {
            throw new IllegalArgumentException("fragment must be non-null");
        }
        this.fragmentReference = new WeakReference<>(fragment);
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public void onStartRequest(InteractiveRequestRecord interactiveRequestRecord) {
        getInteractiveStateAddingRequest(interactiveRequestRecord);
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public InteractiveState getInteractiveState() {
        return getInteractiveStateAddingRequest(null);
    }

    private InteractiveState getInteractiveStateAddingRequest(InteractiveRequestRecord interactiveRequestRecord) {
        Fragment fragment = this.fragmentReference.get();
        if (fragment != null) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            try {
                InteractiveStateFragment interactiveStateFragment = (InteractiveStateFragment) fragmentManager.findFragmentByTag(InteractiveStateFragment.TAG_ID);
                InteractiveStateFragment interactiveStateFragment2 = interactiveStateFragment;
                if (interactiveStateFragment == null) {
                    WorkflowFragment workflowFragment = new WorkflowFragment();
                    fragmentManager.beginTransaction().add(workflowFragment, InteractiveStateFragment.TAG_ID).commit();
                    interactiveStateFragment2 = workflowFragment;
                }
                if (interactiveRequestRecord != null) {
                    Bundle bundle = new Bundle();
                    fragmentManager.putFragment(bundle, InteractiveState.FRAGMENT_WRAPPER_KEY, fragment);
                    interactiveRequestRecord.setFragmentWrapper(bundle);
                    interactiveStateFragment2.getState().onRequestStart(interactiveRequestRecord);
                }
                return interactiveStateFragment2.getState();
            } catch (ClassCastException e2) {
                MAPLog.e(LOG_TAG, "Found an invalid fragment looking for fragment with tag " + InteractiveStateFragment.TAG_ID + ". Please use a different fragment tag.", e2);
                return null;
            }
        }
        MAPLog.e(LOG_TAG, "Failed to get InteractiveState for a garbage-collected Fragment");
        return null;
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Object getBackingObject() {
        return this.fragmentReference.get();
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Context getContext() {
        return this.fragmentReference.get().getActivity();
    }

    public int hashCode() {
        WeakReference<Fragment> weakReference = this.fragmentReference;
        return 31 + ((weakReference == null || weakReference.get() == null) ? 0 : this.fragmentReference.get().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequestSourceFragmentWrapper requestSourceFragmentWrapper = (RequestSourceFragmentWrapper) obj;
        WeakReference<Fragment> weakReference = this.fragmentReference;
        if (weakReference == null) {
            if (requestSourceFragmentWrapper.fragmentReference != null) {
                return false;
            }
        } else {
            if (requestSourceFragmentWrapper.fragmentReference == null) {
                return false;
            }
            if (weakReference.get() == null) {
                if (requestSourceFragmentWrapper.fragmentReference.get() != null) {
                    return false;
                }
            } else if (!this.fragmentReference.get().equals(requestSourceFragmentWrapper.fragmentReference.get())) {
                return false;
            }
        }
        return true;
    }
}