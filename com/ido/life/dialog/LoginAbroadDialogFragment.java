package com.ido.life.dialog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class LoginAbroadDialogFragment extends BaseDialogFragment {

    @BindView(R.id.login_other)
    ImageButton mIbLoginOther;

    @BindView(R.id.ll_share_layout)
    LinearLayout mLlShareLayout;
    private OnShareChooseListener mOnShareChooseListener;

    public interface OnShareChooseListener {
        void onSharePlatChoose(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_login_abroad;
    }

    public static LoginAbroadDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        LoginAbroadDialogFragment loginAbroadDialogFragment = new LoginAbroadDialogFragment();
        loginAbroadDialogFragment.setStyle(1, 2131886083);
        loginAbroadDialogFragment.setArguments(bundle);
        return loginAbroadDialogFragment;
    }

    public void setOnShareResultListener(OnShareChooseListener onShareChooseListener) {
        this.mOnShareChooseListener = onShareChooseListener;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mLlShareLayout.measure(0, 0);
        int measuredHeight = this.mLlShareLayout.getMeasuredHeight() + DipPixelUtil.dip2px(30.0f);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(-1, measuredHeight);
        }
        this.mIbLoginOther.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    @OnClick({R.id.login_facebook, R.id.login_twitter, R.id.login_google})
    public void doClickPlat(View view) {
        share(view.getId());
    }

    protected void share(int i) {
        OnShareChooseListener onShareChooseListener = this.mOnShareChooseListener;
        if (onShareChooseListener != null) {
            onShareChooseListener.onSharePlatChoose(i);
            dismissAllowingStateLoss();
        }
    }
}