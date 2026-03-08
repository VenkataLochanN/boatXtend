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
public class ChoosePhotoDialogFragment extends BaseDialogFragment {
    private static final String TEXT_CANCEL = "textCancel";
    private static final String TEXT_CONFIRM = "textConfirm";
    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    @BindView(R.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_choose_avatar;
    }

    public static ChoosePhotoDialogFragment newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_CONFIRM, str);
        bundle.putString(TEXT_CANCEL, str2);
        ChoosePhotoDialogFragment choosePhotoDialogFragment = new ChoosePhotoDialogFragment();
        choosePhotoDialogFragment.setArguments(bundle);
        choosePhotoDialogFragment.setStyle(1, 2131886083);
        return choosePhotoDialogFragment;
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

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTvConfirm.setText(arguments.getString(TEXT_CONFIRM));
            this.mTvCancel.setText(arguments.getString(TEXT_CANCEL));
        }
    }

    public ChoosePhotoDialogFragment setRightClickListener(View.OnClickListener onClickListener) {
        this.mRightClickListener = onClickListener;
        return this;
    }

    public ChoosePhotoDialogFragment setLeftClickListener(View.OnClickListener onClickListener) {
        this.mLeftClickListener = onClickListener;
        return this;
    }

    @OnClick({R.id.tv_cancel})
    public void doClickCancel(View view) {
        dismissAllowingStateLoss();
        View.OnClickListener onClickListener = this.mLeftClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @OnClick({R.id.tv_confirm})
    public void doClickConfirm(View view) {
        dismissAllowingStateLoss();
        View.OnClickListener onClickListener = this.mRightClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }
}