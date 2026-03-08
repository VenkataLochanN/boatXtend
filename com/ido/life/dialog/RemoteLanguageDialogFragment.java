package com.ido.life.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteLanguageDialogFragment extends BaseDialogFragment {
    private static final String CONTENT = "content";
    private static final String LANGUAGE_NAME = "language";
    private View.OnClickListener mCancelListener;
    private View.OnClickListener mConfirmListener;

    @BindView(R.id.layout_failed)
    LinearLayout mLayoutFailed;

    @BindView(R.id.layout_progress)
    LinearLayout mLayoutProgress;

    @BindView(R.id.layout_remote_language)
    LinearLayout mLayoutRemoteLanguage;

    @BindView(R.id.layout_success)
    LinearLayout mLayoutSuccess;

    @BindView(R.id.mtv_content)
    TextView mMtvContent;

    @BindView(R.id.mtv_language)
    TextView mMtvLanguage;

    @BindView(R.id.mtv_upgrade)
    TextView mMtvUpgrade;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    private View.OnClickListener mRetryListener;

    @BindView(R.id.rtv_failed)
    TextView mRtvFailed;

    @BindView(R.id.rtv_progress_tip)
    TextView mRtvProgressTip;

    @BindView(R.id.rtv_progress_value)
    TextView mRtvProgressValue;

    @BindView(R.id.rtv_success)
    TextView mRtvSuccess;

    @BindView(R.id.rtv_title)
    TextView mRtvTitle;

    @BindView(R.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_remote_language;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public static RemoteLanguageDialogFragment getInstance(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("content", i);
        bundle.putString(LANGUAGE_NAME, str);
        RemoteLanguageDialogFragment remoteLanguageDialogFragment = new RemoteLanguageDialogFragment();
        remoteLanguageDialogFragment.setArguments(bundle);
        remoteLanguageDialogFragment.setStyle(1, 2131886083);
        remoteLanguageDialogFragment.setCancelable(false);
        return remoteLanguageDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            int i = arguments.getInt("content");
            if (i != 0) {
                this.mMtvContent.setText(i);
            }
            this.mMtvLanguage.setText(arguments.getString(LANGUAGE_NAME));
            this.mRtvTitle.setText(LanguageUtil.getLanguageText(R.string.device_language));
            this.mMtvUpgrade.setText(LanguageUtil.getLanguageText(R.string.device_to_upgrade));
            this.mTvCancel.setText(LanguageUtil.getLanguageText(R.string.public_tip_cancel));
            this.mTvConfirm.setText(LanguageUtil.getLanguageText(R.string.device_retry));
        }
    }

    public RemoteLanguageDialogFragment setOnConfirmListener(View.OnClickListener onClickListener) {
        this.mConfirmListener = onClickListener;
        return this;
    }

    public RemoteLanguageDialogFragment setOnCancelListener(View.OnClickListener onClickListener) {
        this.mCancelListener = onClickListener;
        return this;
    }

    public RemoteLanguageDialogFragment setOnRetryListener(View.OnClickListener onClickListener) {
        this.mRetryListener = onClickListener;
        return this;
    }

    @OnClick({R.id.mtv_upgrade, R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        View.OnClickListener onClickListener;
        int id = view.getId();
        if (id == R.id.mtv_upgrade) {
            View.OnClickListener onClickListener2 = this.mConfirmListener;
            if (onClickListener2 != null) {
                onClickListener2.onClick(view);
                return;
            }
            return;
        }
        if (id != R.id.tv_cancel) {
            if (id == R.id.tv_confirm && (onClickListener = this.mRetryListener) != null) {
                onClickListener.onClick(view);
                return;
            }
            return;
        }
        dismissAllowingStateLoss();
        View.OnClickListener onClickListener3 = this.mCancelListener;
        if (onClickListener3 != null) {
            onClickListener3.onClick(view);
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    private void hideAllLayout() {
        this.mRtvTitle.setVisibility(8);
        this.mLayoutRemoteLanguage.setVisibility(8);
        this.mLayoutProgress.setVisibility(8);
        this.mLayoutFailed.setVisibility(8);
        this.mLayoutSuccess.setVisibility(8);
    }

    public void setProgress(int i, int i2) {
        hideAllLayout();
        this.mRtvTitle.setVisibility(0);
        this.mLayoutProgress.setVisibility(0);
        if (i == 1) {
            this.mRtvTitle.setText(LanguageUtil.getLanguageText(R.string.device_downloading));
            this.mRtvProgressTip.setText(LanguageUtil.getLanguageText(R.string.device_language_downloading_tip));
        } else {
            this.mRtvTitle.setText(LanguageUtil.getLanguageText(R.string.device_installing));
            this.mRtvProgressTip.setText(LanguageUtil.getLanguageText(R.string.device_language_installing_tip));
        }
        this.mProgressBar.setProgress(i2);
        this.mRtvProgressValue.setText(String.format(LanguageUtil.getLanguageText(R.string.device_x_percent), Integer.valueOf(i2)));
    }

    public void setSuccess(int i) {
        hideAllLayout();
        this.mLayoutSuccess.setVisibility(0);
        if (i == 1) {
            this.mRtvSuccess.setText(LanguageUtil.getLanguageText(R.string.device_download_success));
        } else {
            this.mRtvSuccess.setText(LanguageUtil.getLanguageText(R.string.device_install_success));
            this.mRtvSuccess.postDelayed(new Runnable() { // from class: com.ido.life.dialog.-$$Lambda$jPqx4z2oPS2G98YHV_LBm-9q0Ec
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.dismissAllowingStateLoss();
                }
            }, 500L);
        }
    }

    public void setFailed(int i) {
        hideAllLayout();
        this.mLayoutFailed.setVisibility(0);
        if (i == 1) {
            this.mRtvFailed.setText(LanguageUtil.getLanguageText(R.string.device_download_failed_tip));
        } else {
            this.mRtvFailed.setText(LanguageUtil.getLanguageText(R.string.device_install_failed_tip));
        }
    }
}