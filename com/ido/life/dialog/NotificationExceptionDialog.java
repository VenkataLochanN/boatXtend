package com.ido.life.dialog;

import android.view.View;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationExceptionDialog extends BaseDialogFragment {
    private OnClickListener mOnClickListener;

    public interface OnClickListener {
        void onCancelClicked();

        void onConfirmClicked();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_notification_exception_tip;
    }

    public static NotificationExceptionDialog getInstance() {
        NotificationExceptionDialog notificationExceptionDialog = new NotificationExceptionDialog();
        notificationExceptionDialog.setStyle(1, 2131886083);
        notificationExceptionDialog.setCancelable(false);
        return notificationExceptionDialog;
    }

    public NotificationExceptionDialog setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        return this;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_cancel) {
            dismissAllowingStateLoss();
            OnClickListener onClickListener = this.mOnClickListener;
            if (onClickListener != null) {
                onClickListener.onCancelClicked();
                return;
            }
            return;
        }
        if (id != R.id.tv_confirm) {
            return;
        }
        dismissAllowingStateLoss();
        OnClickListener onClickListener2 = this.mOnClickListener;
        if (onClickListener2 != null) {
            onClickListener2.onConfirmClicked();
        }
    }
}