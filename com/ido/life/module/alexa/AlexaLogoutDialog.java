package com.ido.life.module.alexa;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.R2;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.LanguageUtil;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLogoutDialog extends BaseDialogFragment {
    private View.OnClickListener mConfirmListener;

    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R2.id.tv_confirm)
    TextView mTvConfirm;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_alexa_logout;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public static AlexaLogoutDialog newInstance() {
        AlexaLogoutDialog alexaLogoutDialog = new AlexaLogoutDialog();
        alexaLogoutDialog.setStyle(1, 2131886083);
        return alexaLogoutDialog;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        this.mTvCancel.setText(LanguageUtil.getLanguageText(R.string.alexa_logout_cancel));
        this.mTvConfirm.setText(LanguageUtil.getLanguageText(R.string.alexa_logout_ok));
    }

    public AlexaLogoutDialog setOnConfirmListener(View.OnClickListener onClickListener) {
        this.mConfirmListener = onClickListener;
        return this;
    }

    @OnClick({R2.id.tv_cancel})
    public void doClickCancel(View view) {
        dismissAllowingStateLoss();
    }

    @OnClick({R2.id.tv_confirm})
    public void doClickConfirm(View view) {
        View.OnClickListener onClickListener = this.mConfirmListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
            dismissAllowingStateLoss();
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setSoftInputMode(18);
        window.setAttributes(attributes);
    }
}