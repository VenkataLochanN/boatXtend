package com.ido.life.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;

/* JADX INFO: loaded from: classes2.dex */
public class CloudSyncConfirmDialogFragment extends BaseDialogFragment {
    private static final String MESSAGE = "message";
    private static final String SHOW_CANCEL = "showCancel";
    private static final String TEXT_CANCEL = "textCancel";
    private static final String TEXT_CONFIRM = "textConfirm";
    private static final String TEXT_TITLE = "textTitle";
    private View.OnClickListener mCancelListener;
    private View.OnClickListener mConfirmListener;

    @BindView(R.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tv_context)
    TextView tvContext;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_clound_sync_confirm;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public static CloudSyncConfirmDialogFragment newInstance(String str, String str2, String str3, String str4, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, str2);
        bundle.putString(TEXT_TITLE, str);
        bundle.putString(TEXT_CONFIRM, str3);
        bundle.putString(TEXT_CANCEL, str4);
        bundle.putBoolean(SHOW_CANCEL, z);
        CloudSyncConfirmDialogFragment cloudSyncConfirmDialogFragment = new CloudSyncConfirmDialogFragment();
        cloudSyncConfirmDialogFragment.setArguments(bundle);
        cloudSyncConfirmDialogFragment.setCancelable(false);
        cloudSyncConfirmDialogFragment.setStyle(1, 2131886083);
        return cloudSyncConfirmDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTvConfirm.setText(arguments.getString(TEXT_CONFIRM));
            this.mTvCancel.setText(arguments.getString(TEXT_CANCEL));
            this.mTvTitle.setText(arguments.getString(TEXT_TITLE));
            this.tvContext.setText(arguments.getString(MESSAGE));
            this.mTvCancel.setVisibility(arguments.getBoolean(SHOW_CANCEL) ? 0 : 8);
        }
    }

    public CloudSyncConfirmDialogFragment setOnConfirmListener(View.OnClickListener onClickListener) {
        this.mConfirmListener = onClickListener;
        return this;
    }

    public CloudSyncConfirmDialogFragment setCancelListener(View.OnClickListener onClickListener) {
        this.mCancelListener = onClickListener;
        return this;
    }

    @OnClick({R.id.tv_cancel})
    public void doClickCancel(View view) {
        dismiss();
        View.OnClickListener onClickListener = this.mCancelListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @OnClick({R.id.tv_confirm})
    public void doClickConfirm(View view) {
        View.OnClickListener onClickListener = this.mConfirmListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
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
    }
}