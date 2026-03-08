package com.ido.life.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.module.device.activity.NativeWebViewActivity;

/* JADX INFO: loaded from: classes2.dex */
public class NewSplashConfirmDialogFragment extends BaseDialogFragment {
    private static final String MESSAGE = "message";
    private static final String SHOW_CANCEL = "showCancel";
    private static final String TEXT_CANCEL = "textCancel";
    private static final String TEXT_CONFIRM = "textConfirm";
    private static final String TEXT_TITLE = "textTitle";
    private View.OnClickListener mCancelListener;
    private View.OnClickListener mConfirmListener;

    @BindView(R.id.rb_protocol_policy)
    CheckBox mRadioButton;

    @BindView(R.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    @BindView(R.id.tv_context)
    TextView mTvContext;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_splash_title_confirm_new;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public static NewSplashConfirmDialogFragment newInstance(String str, String str2, String str3, String str4, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, str2);
        bundle.putString(TEXT_TITLE, str);
        bundle.putString(TEXT_CONFIRM, str3);
        bundle.putString(TEXT_CANCEL, str4);
        bundle.putBoolean(SHOW_CANCEL, z);
        NewSplashConfirmDialogFragment newSplashConfirmDialogFragment = new NewSplashConfirmDialogFragment();
        newSplashConfirmDialogFragment.setArguments(bundle);
        newSplashConfirmDialogFragment.setCancelable(false);
        newSplashConfirmDialogFragment.setStyle(1, 2131886083);
        return newSplashConfirmDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTvConfirm.setText(arguments.getString(TEXT_CONFIRM));
            this.mTvCancel.setText(arguments.getString(TEXT_CANCEL));
            this.mTvTitle.setText(arguments.getString(TEXT_TITLE));
            this.mTvContext.setText(arguments.getString(MESSAGE));
            this.mTvCancel.setVisibility(arguments.getBoolean(SHOW_CANCEL) ? 0 : 8);
        }
    }

    public NewSplashConfirmDialogFragment setOnConfirmListener(View.OnClickListener onClickListener) {
        this.mConfirmListener = onClickListener;
        return this;
    }

    public NewSplashConfirmDialogFragment setCancelListener(View.OnClickListener onClickListener) {
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
        View.OnClickListener onClickListener;
        if (!getCheckBoxState() || (onClickListener = this.mConfirmListener) == null) {
            return;
        }
        onClickListener.onClick(view);
    }

    public boolean getCheckBoxState() {
        return this.mRadioButton.isChecked();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            window.setAttributes(attributes);
        }
        String str = (String) this.mTvContext.getText();
        String string = getString(R.string.mine_user_agreement);
        String string2 = getString(R.string.logn_detail_policy_ios);
        SpannableString spannableString = new SpannableString(str);
        if (str.contains(string)) {
            int iIndexOf = str.indexOf(string);
            if (iIndexOf == -1) {
                iIndexOf = 0;
            }
            spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.dialog.NewSplashConfirmDialogFragment.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view2) {
                    Intent intent = new Intent(NewSplashConfirmDialogFragment.this.getActivity(), (Class<?>) NativeWebViewActivity.class);
                    intent.putExtra("type", 2);
                    NewSplashConfirmDialogFragment.this.startActivity(intent);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(true);
                    textPaint.setColor(NewSplashConfirmDialogFragment.this.getResources().getColor(R.color.color_027AFF));
                }
            }, iIndexOf, string.length() + iIndexOf, 33);
        }
        if (str.contains(string2)) {
            int iIndexOf2 = str.indexOf(string2);
            if (iIndexOf2 == -1) {
                iIndexOf2 = 0;
            }
            spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.dialog.NewSplashConfirmDialogFragment.2
                @Override // android.text.style.ClickableSpan
                public void onClick(View view2) {
                    Intent intent = new Intent(NewSplashConfirmDialogFragment.this.getActivity(), (Class<?>) NativeWebViewActivity.class);
                    intent.putExtra("type", 3);
                    NewSplashConfirmDialogFragment.this.startActivity(intent);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(NewSplashConfirmDialogFragment.this.getResources().getColor(R.color.color_027AFF));
                }
            }, iIndexOf2, string2.length() + iIndexOf2, 33);
        }
        this.mTvContext.setText(spannableString);
        this.mTvContext.setHighlightColor(getResources().getColor(android.R.color.transparent));
        this.mTvContext.setMovementMethod(LinkMovementMethod.getInstance());
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