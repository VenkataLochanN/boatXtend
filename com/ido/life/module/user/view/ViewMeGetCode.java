package com.ido.life.module.user.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.R;
import com.ido.life.module.user.CountDownTask;

/* JADX INFO: loaded from: classes3.dex */
public class ViewMeGetCode extends FrameLayout {
    private TextView mBtnGetCode;
    private CountDownTask mCountDownTask;
    private EditText mEtCode;
    private Drawable mGetCodeBg;
    private ColorStateList mGetCodeTextColor;
    private OnGetCodeCallback mOnClickGetCodeListener;
    private OnTextChangedListener mOnTextChangedListener;
    private ColorStateList mTextColor;
    private ColorStateList mTextColorHint;
    private String mTextHint;
    private int mTextSizeHint;
    private TextWatcher mTextWatcher;

    public interface OnGetCodeCallback {
        void onClick(View view);
    }

    public ViewMeGetCode(Context context) {
        this(context, null);
    }

    public ViewMeGetCode(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewMeGetCode(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ViewMe);
        this.mTextColor = typedArrayObtainStyledAttributes.getColorStateList(7);
        this.mTextColorHint = typedArrayObtainStyledAttributes.getColorStateList(8);
        this.mTextHint = typedArrayObtainStyledAttributes.getString(9);
        this.mTextSizeHint = typedArrayObtainStyledAttributes.getInteger(10, 16);
        this.mGetCodeTextColor = typedArrayObtainStyledAttributes.getColorStateList(6);
        this.mGetCodeBg = typedArrayObtainStyledAttributes.getDrawable(5);
        typedArrayObtainStyledAttributes.recycle();
        initView();
        initListener();
    }

    public void setOnTextChangedListener(OnTextChangedListener onTextChangedListener) {
        this.mOnTextChangedListener = onTextChangedListener;
    }

    public void setOnClickGetCodeListener(OnGetCodeCallback onGetCodeCallback) {
        this.mOnClickGetCodeListener = onGetCodeCallback;
    }

    public void setCode(CharSequence charSequence) {
        this.mEtCode.setText(charSequence);
    }

    public String getCode() {
        return this.mEtCode.getText().toString();
    }

    public void startCountDown() {
        if (this.mCountDownTask == null) {
            this.mCountDownTask = new CountDownTask(this.mBtnGetCode, 60);
        }
        this.mCountDownTask.start();
    }

    public void stopCountDown() {
        if (this.mCountDownTask != null) {
            CommonLogUtil.d("Test", "CountDownTask--stopCountDown--cancel");
            this.mCountDownTask.stop();
        }
    }

    public void setGetCodeEnable(boolean z) {
        stopCountDown();
        this.mBtnGetCode.setText(LanguageUtil.getLanguageText(com.boat.Xtend.two.R.string.mine_get_verification_code));
        this.mBtnGetCode.setEnabled(z);
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(com.boat.Xtend.two.R.layout.widget_me_code, (ViewGroup) this, true);
        this.mEtCode = (EditText) viewInflate.findViewById(com.boat.Xtend.two.R.id.me_code_et);
        this.mBtnGetCode = (TextView) viewInflate.findViewById(com.boat.Xtend.two.R.id.me_get_code_btn);
        ColorStateList colorStateList = this.mTextColor;
        if (colorStateList != null) {
            this.mEtCode.setTextColor(colorStateList);
        }
        ColorStateList colorStateList2 = this.mGetCodeTextColor;
        if (colorStateList2 != null) {
            this.mBtnGetCode.setTextColor(colorStateList2);
        }
        if (TextUtils.isEmpty(this.mTextHint) && this.mEtCode.getHint() != null) {
            this.mTextHint = this.mEtCode.getHint().toString();
        }
        if (this.mTextSizeHint > 0) {
            SpannableString spannableString = new SpannableString(this.mTextHint);
            spannableString.setSpan(new AbsoluteSizeSpan(this.mTextSizeHint, true), 0, spannableString.length(), 33);
            this.mEtCode.setHint(new SpannedString(spannableString));
        } else {
            this.mEtCode.setHint(this.mTextHint);
        }
        ColorStateList colorStateList3 = this.mTextColorHint;
        if (colorStateList3 != null) {
            this.mEtCode.setHintTextColor(colorStateList3);
        }
        Drawable drawable = this.mGetCodeBg;
        if (drawable != null) {
            this.mBtnGetCode.setBackground(drawable);
        }
    }

    private void initListener() {
        this.mBtnGetCode.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.view.ViewMeGetCode.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ViewMeGetCode.this.mOnClickGetCodeListener != null) {
                    ViewMeGetCode.this.mOnClickGetCodeListener.onClick(view);
                }
            }
        });
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTextWatcher == null) {
            this.mTextWatcher = new TextWatcher() { // from class: com.ido.life.module.user.view.ViewMeGetCode.2
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    if (ViewMeGetCode.this.mOnTextChangedListener != null) {
                        ViewMeGetCode.this.mOnTextChangedListener.changed(editable.toString());
                    }
                }
            };
        }
        this.mEtCode.addTextChangedListener(this.mTextWatcher);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mEtCode.removeTextChangedListener(this.mTextWatcher);
        if (this.mCountDownTask != null) {
            CommonLogUtil.d("Test", "CountDownTask--onDetachedFromWindow--cancel");
            this.mCountDownTask.stop();
            this.mCountDownTask = null;
        }
        super.onDetachedFromWindow();
    }

    public void setNameHint(CharSequence charSequence) {
        this.mTextHint = charSequence.toString();
        if (this.mTextSizeHint > 0) {
            SpannableString spannableString = new SpannableString(this.mTextHint);
            spannableString.setSpan(new AbsoluteSizeSpan(this.mTextSizeHint, true), 0, spannableString.length(), 33);
            this.mEtCode.setHint(new SpannedString(spannableString));
            return;
        }
        this.mEtCode.setHint(this.mTextHint);
    }
}