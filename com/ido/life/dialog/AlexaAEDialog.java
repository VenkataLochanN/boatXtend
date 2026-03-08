package com.ido.life.dialog;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaAEDialog extends BaseDialogFragment {
    private OnClickListener mOnClickListener;

    public interface OnClickListener {
        void onConfirmClicked();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.activity_alexa_ae;
    }

    public static AlexaAEDialog getInstance() {
        AlexaAEDialog alexaAEDialog = new AlexaAEDialog();
        alexaAEDialog.setStyle(1, 2131886083);
        alexaAEDialog.setCancelable(false);
        return alexaAEDialog;
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
        attributes.gravity = 17;
        attributes.width = -1;
        attributes.height = ScreenUtil.getScreenHeight(IdoApp.getAppContext()) - DipPixelUtil.dip2px(40.0f);
        window.setAttributes(attributes);
    }

    public AlexaAEDialog setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        return this;
    }

    @OnClick({R.id.rtv_btn})
    public void onClick(View view) {
        if (view.getId() != R.id.rtv_btn) {
            return;
        }
        dismissAllowingStateLoss();
        OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onConfirmClicked();
        }
    }
}