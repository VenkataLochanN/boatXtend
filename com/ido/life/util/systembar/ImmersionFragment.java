package com.ido.life.util.systembar;

import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class ImmersionFragment extends Fragment {
    @Deprecated
    protected boolean immersionEnabled() {
        return true;
    }

    @Deprecated
    protected abstract void immersionInit();

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && isResumed()) {
            onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && immersionEnabled()) {
            immersionInit();
        }
    }
}