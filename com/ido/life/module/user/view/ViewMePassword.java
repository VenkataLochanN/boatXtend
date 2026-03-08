package com.ido.life.module.user.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import com.ido.life.R;

/* JADX INFO: loaded from: classes3.dex */
public class ViewMePassword extends FrameLayout {
    private Drawable mBackground;
    private EditText mEtPassword;
    private ImageButton mIbDelete;
    private boolean mIsPassShow;
    private ImageView mMeSeepassIv;
    private OnTextChangedListener mOnTextChangedListener;
    private ColorStateList mTextColor;
    private ColorStateList mTextColorHint;
    private String mTextHint;
    private int mTextSizeHint;
    private TextWatcher mTextWatcher;

    public ViewMePassword(Context context) {
        this(context, null);
    }

    public ViewMePassword(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewMePassword(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsPassShow = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ViewMe);
        this.mBackground = typedArrayObtainStyledAttributes.getDrawable(0);
        this.mTextColor = typedArrayObtainStyledAttributes.getColorStateList(7);
        this.mTextColorHint = typedArrayObtainStyledAttributes.getColorStateList(8);
        this.mTextHint = typedArrayObtainStyledAttributes.getString(9);
        this.mTextSizeHint = typedArrayObtainStyledAttributes.getInteger(10, 16);
        typedArrayObtainStyledAttributes.recycle();
        initView();
        initListener();
    }

    public void setOnTextChangedListener(OnTextChangedListener onTextChangedListener) {
        this.mOnTextChangedListener = onTextChangedListener;
    }

    public void setPassword(CharSequence charSequence) {
        this.mEtPassword.setText(charSequence);
    }

    public void addFilter(InputFilter... inputFilterArr) {
        if (inputFilterArr == null || inputFilterArr.length == 0) {
            return;
        }
        this.mEtPassword.setFilters(inputFilterArr);
    }

    public void clearFilter() {
        this.mEtPassword.setFilters(new InputFilter[0]);
    }

    public String getPassword() {
        return this.mEtPassword.getText().toString();
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(com.boat.Xtend.two.R.layout.widget_me_password, (ViewGroup) this, true);
        View viewFindViewById = viewInflate.findViewById(com.boat.Xtend.two.R.id.container);
        this.mEtPassword = (EditText) viewInflate.findViewById(com.boat.Xtend.two.R.id.me_password_et);
        this.mMeSeepassIv = (ImageView) viewInflate.findViewById(com.boat.Xtend.two.R.id.me_seepassword_iv);
        this.mIbDelete = (ImageButton) viewInflate.findViewById(com.boat.Xtend.two.R.id.me_ib_delete_pass);
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            viewFindViewById.setBackground(drawable);
        }
        ColorStateList colorStateList = this.mTextColor;
        if (colorStateList != null) {
            this.mEtPassword.setTextColor(colorStateList);
        }
        ColorStateList colorStateList2 = this.mTextColorHint;
        if (colorStateList2 != null) {
            this.mEtPassword.setHintTextColor(colorStateList2);
        }
        if (TextUtils.isEmpty(this.mTextHint) && this.mEtPassword.getHint() != null) {
            this.mTextHint = this.mEtPassword.getHint().toString();
        }
        if (this.mTextSizeHint > 0) {
            SpannableString spannableString = new SpannableString(this.mTextHint);
            spannableString.setSpan(new AbsoluteSizeSpan(this.mTextSizeHint, true), 0, spannableString.length(), 33);
            this.mEtPassword.setHint(new SpannedString(spannableString));
            return;
        }
        this.mEtPassword.setHint(this.mTextHint);
    }

    private void initListener() {
        this.mIbDelete.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.view.-$$Lambda$ViewMePassword$jQ4XrhBVW-YM_8EGsR3QuqzD_Kk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$0$ViewMePassword(view);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$ViewMePassword(View view) {
        this.mEtPassword.setText("");
    }

    private void showOrHindePass(boolean z, EditText editText, ImageView imageView) {
        if (z) {
            editText.setInputType(144);
            imageView.setImageResource(com.boat.Xtend.two.R.mipmap.ic_login_see_selected);
        } else {
            editText.setInputType(129);
            imageView.setImageResource(com.boat.Xtend.two.R.mipmap.ic_login_see_normal);
        }
        editText.postInvalidate();
        Editable text = editText.getText();
        if (text != null) {
            Selection.setSelection(text, text.length());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTextWatcher == null) {
            this.mTextWatcher = new TextWatcher() { // from class: com.ido.life.module.user.view.ViewMePassword.1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    if (ViewMePassword.this.mOnTextChangedListener != null) {
                        ViewMePassword.this.mOnTextChangedListener.changed(editable.toString());
                    }
                    ViewMePassword.this.mIbDelete.setVisibility(8);
                }
            };
        }
        this.mEtPassword.addTextChangedListener(this.mTextWatcher);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mEtPassword.removeTextChangedListener(this.mTextWatcher);
        super.onDetachedFromWindow();
    }

    public void setNameHint(CharSequence charSequence) {
        this.mTextHint = charSequence.toString();
        if (this.mTextSizeHint > 0) {
            SpannableString spannableString = new SpannableString(this.mTextHint);
            spannableString.setSpan(new AbsoluteSizeSpan(this.mTextSizeHint, true), 0, spannableString.length(), 33);
            this.mEtPassword.setHint(new SpannedString(spannableString));
            return;
        }
        this.mEtPassword.setHint(this.mTextHint);
    }
}