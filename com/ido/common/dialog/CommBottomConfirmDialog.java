package com.ido.common.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.ido.common.R;
import com.ido.common.R2;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class CommBottomConfirmDialog extends BaseDialogFragment {
    private static final String DISMISS_WHIT_CONFIRM = "dismiss_whit_confirm";
    private static final String MESSAGE = "message";
    private static final String SHOW_CANCEL = "showCancel";
    private static final String TEXT_CANCEL = "textCancel";
    private static final String TEXT_CONFIRM = "textConfirm";
    private static final String TITLE = "title";
    private View.OnClickListener mCancelListener;
    private View.OnClickListener mConfirmListener;
    private boolean mDismissWithConfirm;

    @BindView(R2.id.rtv_title)
    TextView mRtvTitle;

    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R2.id.tv_confirm)
    TextView mTvConfirm;

    @BindView(R2.id.tv_message)
    TextView mTvMessage;

    public static CommBottomConfirmDialog newInstance(String str, String str2, String str3, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, str);
        bundle.putString(TEXT_CONFIRM, str2);
        bundle.putString(TEXT_CANCEL, str3);
        bundle.putBoolean(SHOW_CANCEL, z);
        CommBottomConfirmDialog commBottomConfirmDialog = new CommBottomConfirmDialog();
        commBottomConfirmDialog.setArguments(bundle);
        commBottomConfirmDialog.setStyle(1, R.style.AlertDialog_Dark);
        return commBottomConfirmDialog;
    }

    public static CommBottomConfirmDialog newInstance(String str, String str2, String str3, String str4, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString(MESSAGE, str2);
        bundle.putString(TEXT_CONFIRM, str3);
        bundle.putString(TEXT_CANCEL, str4);
        bundle.putBoolean(SHOW_CANCEL, z);
        CommBottomConfirmDialog commBottomConfirmDialog = new CommBottomConfirmDialog();
        commBottomConfirmDialog.setArguments(bundle);
        commBottomConfirmDialog.setStyle(1, R.style.AlertDialog_Dark);
        return commBottomConfirmDialog;
    }

    public static CommBottomConfirmDialog newInstance(String str, String str2, String str3, String str4, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString(MESSAGE, str2);
        bundle.putString(TEXT_CONFIRM, str3);
        bundle.putString(TEXT_CANCEL, str4);
        bundle.putBoolean(SHOW_CANCEL, z);
        CommBottomConfirmDialog commBottomConfirmDialog = new CommBottomConfirmDialog();
        commBottomConfirmDialog.setArguments(bundle);
        commBottomConfirmDialog.setCancelable(z2);
        commBottomConfirmDialog.setStyle(1, R.style.AlertDialog_Dark);
        return commBottomConfirmDialog;
    }

    public static CommBottomConfirmDialog newInstance(String str, String str2, String str3, String str4, boolean z, boolean z2, boolean z3) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString(MESSAGE, str2);
        bundle.putString(TEXT_CONFIRM, str3);
        bundle.putString(TEXT_CANCEL, str4);
        bundle.putBoolean(SHOW_CANCEL, z);
        bundle.putBoolean(DISMISS_WHIT_CONFIRM, z3);
        CommBottomConfirmDialog commBottomConfirmDialog = new CommBottomConfirmDialog();
        commBottomConfirmDialog.setArguments(bundle);
        commBottomConfirmDialog.setCancelable(z2);
        commBottomConfirmDialog.setStyle(1, R.style.AlertDialog_Dark);
        return commBottomConfirmDialog;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_white_confirm;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("title");
            if (TextUtils.isEmpty(string)) {
                this.mRtvTitle.setVisibility(8);
            } else {
                this.mRtvTitle.setText(string);
            }
            this.mTvMessage.setText(arguments.getString(MESSAGE));
            this.mTvConfirm.setText(arguments.getString(TEXT_CONFIRM));
            this.mTvCancel.setText(arguments.getString(TEXT_CANCEL));
            this.mTvCancel.setVisibility(arguments.getBoolean(SHOW_CANCEL) ? 0 : 8);
            this.mDismissWithConfirm = arguments.getBoolean(DISMISS_WHIT_CONFIRM, true);
        }
    }

    public CommBottomConfirmDialog setOnConfirmListener(View.OnClickListener onClickListener) {
        this.mConfirmListener = onClickListener;
        return this;
    }

    public CommBottomConfirmDialog setCancelListener(View.OnClickListener onClickListener) {
        this.mCancelListener = onClickListener;
        return this;
    }

    @OnClick({R2.id.tv_cancel})
    public void doClickCancel(View view) {
        dismissAllowingStateLoss();
        View.OnClickListener onClickListener = this.mCancelListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @OnClick({R2.id.tv_confirm})
    public void doClickConfirm(View view) {
        if (this.mDismissWithConfirm) {
            dismissAllowingStateLoss();
        }
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
        window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    private void setWindowSize() {
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(DipPixelUtil.dip2px(296.0f), DipPixelUtil.dip2px(210.0f));
        }
    }
}