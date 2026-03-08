package com.amazon.identity.auth.device.interactive;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class WorkflowFragment extends Fragment implements InteractiveStateFragment {
    private GenericInteractiveState state = new GenericInteractiveState(this);

    void setState(GenericInteractiveState genericInteractiveState) {
        this.state = genericInteractiveState;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.state.readFromBundle(bundle);
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.state.writeToBundle(bundle);
        super.onSaveInstanceState(bundle);
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public InteractiveState getState() {
        return this.state;
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public Object getParentActivity() {
        return getActivity();
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public Object getFragment(Bundle bundle) {
        return getFragmentManager().getFragment(bundle, InteractiveState.FRAGMENT_WRAPPER_KEY);
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public Object getApplicationContext() {
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return activity.getApplicationContext();
    }
}