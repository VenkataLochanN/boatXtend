package com.amazon.identity.auth.device.interactive;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class WorkflowSupportFragment extends Fragment implements InteractiveStateFragment {
    private WeakReference<Context> contextWeakReference;
    private GenericInteractiveState state = new GenericInteractiveState(this);

    void setState(GenericInteractiveState genericInteractiveState) {
        this.state = genericInteractiveState;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.state.readFromBundle(bundle);
    }

    @Override // androidx.fragment.app.Fragment
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

    public void setApplicationContext(Context context) {
        this.contextWeakReference = new WeakReference<>(context);
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public Object getApplicationContext() {
        return this.contextWeakReference.get();
    }
}