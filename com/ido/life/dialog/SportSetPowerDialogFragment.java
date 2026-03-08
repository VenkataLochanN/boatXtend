package com.ido.life.dialog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.LanguageUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SportSetPowerDialogFragment extends BaseDialogFragment {
    private View.OnClickListener mCancelListener;
    private View.OnClickListener mConfirmListener;

    @BindView(R.id.ib_close)
    ImageButton mIbClose;

    @BindView(R.id.iv_setting_remind)
    ImageView mIvSettingRemind;
    private boolean mToSetting = false;

    @BindView(R.id.tv_next)
    TextView mTvNext;

    @BindView(R.id.tv_setting_remind)
    TextView mTvSettingRemind;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_sport_set_power;
    }

    public static SportSetPowerDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        SportSetPowerDialogFragment sportSetPowerDialogFragment = new SportSetPowerDialogFragment();
        sportSetPowerDialogFragment.setStyle(1, 2131886083);
        sportSetPowerDialogFragment.setArguments(bundle);
        return sportSetPowerDialogFragment;
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
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(-1, -2);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    public SportSetPowerDialogFragment setCancelListener(View.OnClickListener onClickListener) {
        this.mCancelListener = onClickListener;
        return this;
    }

    public SportSetPowerDialogFragment setOnConfirmListener(View.OnClickListener onClickListener) {
        this.mConfirmListener = onClickListener;
        return this;
    }

    @OnClick({R.id.tv_next})
    public void doClickConfirm(View view) {
        if (!this.mToSetting) {
            this.mTvSettingRemind.setText(LanguageUtil.getLanguageText(R.string.sport_setting_manager_second));
            this.mTvNext.setText(LanguageUtil.getLanguageText(R.string.sport_setting_manager_to_set));
            this.mIvSettingRemind.setImageDrawable(getResources().getDrawable(R.mipmap.ic_sport_power_setting));
            this.mToSetting = true;
            return;
        }
        View.OnClickListener onClickListener = this.mConfirmListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @OnClick({R.id.ib_close})
    public void doClickCancel(View view) {
        dismissAllowingStateLoss();
        View.OnClickListener onClickListener = this.mCancelListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}