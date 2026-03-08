package com.ido.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.HookClickListenerHelper;
import com.ido.common.utils.PermissionUtil;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseCoreFragment extends Fragment {
    private PermissionUtil mPermissionUtil;
    protected View mRootView;
    protected boolean mIsHidden = false;
    protected boolean mCanDoubleClick = false;

    protected abstract int getLayoutResId();

    protected void initData() {
    }

    protected void initEvent() {
    }

    protected void initView() {
    }

    protected void onVisible() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(getLayoutResId(), viewGroup, false);
        }
        ButterKnife.bind(this, this.mRootView);
        return this.mRootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView();
        initData();
        initEvent();
        if (canDoubleClick()) {
            return;
        }
        View view2 = this.mRootView;
        if (view2 instanceof ViewGroup) {
            HookClickListenerHelper.hookViewGroup((ViewGroup) view2);
        } else {
            HookClickListenerHelper.hookOnClick(view2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mIsHidden) {
            return;
        }
        try {
            onVisible();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.mIsHidden = z;
        CommonLogUtil.d("onHiddenChanged " + z + ":" + toString());
        if (!z) {
            try {
                onVisible();
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        onInVisible();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            try {
                onVisible();
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        onInVisible();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mIsHidden) {
            onInVisible();
        }
    }

    public boolean checkSelfPermission(String... strArr) {
        return PermissionUtil.checkSelfPermission(getContext(), strArr);
    }

    public void requestPermissions(int i, PermissionUtil.RequestResult requestResult, String... strArr) {
        if (requestResult == null) {
            requestPermissions(strArr, i);
            return;
        }
        if (this.mPermissionUtil == null) {
            this.mPermissionUtil = new PermissionUtil();
        }
        this.mPermissionUtil.setRequestResult(requestResult);
        PermissionUtil.requestPermissions(this, i, strArr);
    }

    public void onInVisible() {
        CommonLogUtil.d("isHidden:" + this.mIsHidden + ",onInVisible:" + toString());
    }

    protected boolean canDoubleClick() {
        return this.mCanDoubleClick;
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionUtil permissionUtil = this.mPermissionUtil;
        if (permissionUtil != null) {
            permissionUtil.onRequestPermissionsResult(i, strArr, iArr);
        }
    }
}