package com.ido.life.module.user.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
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
import android.widget.TextView;
import com.ido.life.R;
import com.ido.life.module.user.country.CountryChooseActivity;

/* JADX INFO: loaded from: classes3.dex */
public class ViewMePhone extends FrameLayout {
    private View container;
    private boolean mAutoShowCountryCode;
    private Drawable mBackground;
    private int mDeleteSourceId;
    private EditText mEtName;
    private ImageButton mIbDelete;
    private boolean mIsShowDrop;
    private OnTextChangedListener mOnTextChangedListener;
    private ColorStateList mTextColor;
    private ColorStateList mTextColorHint;
    private String mTextHint;
    private int mTextSizeHint;
    private TextWatcher mTextWatcher;
    private TextView mTvCountryChoose;
    private View mViewLine;

    public String getPhone() {
        return this.mEtName.getText().toString();
    }

    public String getCode() {
        return this.mTvCountryChoose.getText().toString();
    }

    public void setPhoneHint(String str) {
        this.mEtName.setHint(str);
    }

    public void setText(String str) {
        this.mEtName.setText(str);
        validText(str);
        OnTextChangedListener onTextChangedListener = this.mOnTextChangedListener;
        if (onTextChangedListener != null) {
            onTextChangedListener.changed(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mIbDelete.setVisibility(4);
            if (str.contains("@")) {
                this.mTvCountryChoose.setVisibility(8);
                this.mViewLine.setVisibility(8);
                return;
            } else {
                if (str.length() > 6) {
                    return;
                }
                this.mTvCountryChoose.setVisibility(8);
                this.mViewLine.setVisibility(8);
                return;
            }
        }
        this.mIbDelete.setVisibility(4);
        this.mTvCountryChoose.setVisibility(8);
        this.mViewLine.setVisibility(8);
    }

    public void setEditTextEnable(boolean z) {
        this.mEtName.setEnabled(z);
        this.mIbDelete.setVisibility(z ? 0 : 8);
    }

    public void setmBackground(Drawable drawable) {
        this.container.setBackground(drawable);
    }

    public void setCountryCode(String str) {
        this.mTvCountryChoose.setText("+" + str);
        this.mTvCountryChoose.setTag(str);
    }

    public void setShowCountryCode(boolean z) {
        this.mAutoShowCountryCode = false;
        this.mTvCountryChoose.setVisibility(z ? 0 : 8);
    }

    public void setNameHint(CharSequence charSequence) {
        this.mTextHint = charSequence.toString();
        if (this.mTextSizeHint > 0) {
            SpannableString spannableString = new SpannableString(this.mTextHint);
            spannableString.setSpan(new AbsoluteSizeSpan(this.mTextSizeHint, true), 0, spannableString.length(), 33);
            this.mEtName.setHint(new SpannedString(spannableString));
            return;
        }
        this.mEtName.setHint(this.mTextHint);
    }

    public void setOnTextChangedListener(OnTextChangedListener onTextChangedListener) {
        this.mOnTextChangedListener = onTextChangedListener;
    }

    public ViewMePhone(Context context) {
        this(context, null);
    }

    public ViewMePhone(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewMePhone(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAutoShowCountryCode = true;
        initType(context, attributeSet);
        initView();
        initListener();
    }

    private void initType(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ViewPhone);
        this.mDeleteSourceId = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        this.mBackground = typedArrayObtainStyledAttributes.getDrawable(0);
        this.mTextColor = typedArrayObtainStyledAttributes.getColorStateList(9);
        this.mTextColorHint = typedArrayObtainStyledAttributes.getColorStateList(10);
        this.mTextHint = typedArrayObtainStyledAttributes.getString(11);
        this.mTextSizeHint = typedArrayObtainStyledAttributes.getInteger(12, 16);
        this.mIsShowDrop = typedArrayObtainStyledAttributes.getBoolean(7, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initListener() {
        this.mTvCountryChoose.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.view.-$$Lambda$ViewMePhone$SCzqGTrTRFZDpOgjvm1jT2y01hg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$0$ViewMePhone(view);
            }
        });
        this.mIbDelete.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.view.-$$Lambda$ViewMePhone$j7UZNnTyySdGlk_dkxYojoKkrBY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initListener$1$ViewMePhone(view);
            }
        });
        this.mIbDelete.setVisibility(4);
    }

    public /* synthetic */ void lambda$initListener$0$ViewMePhone(View view) {
        if (getContext() instanceof Activity) {
            chooseCountry(this.mTvCountryChoose.getTag().toString());
        }
    }

    public /* synthetic */ void lambda$initListener$1$ViewMePhone(View view) {
        this.mEtName.setText("");
    }

    protected void chooseCountry(String str) {
        CountryChooseActivity.startActivity((Activity) getContext(), str);
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(com.boat.Xtend.two.R.layout.widget_me_phone, (ViewGroup) this, true);
        this.container = viewInflate.findViewById(com.boat.Xtend.two.R.id.container);
        this.mEtName = (EditText) viewInflate.findViewById(com.boat.Xtend.two.R.id.me_name_et);
        this.mIbDelete = (ImageButton) viewInflate.findViewById(com.boat.Xtend.two.R.id.me_ib_delete_name);
        this.mTvCountryChoose = (TextView) viewInflate.findViewById(com.boat.Xtend.two.R.id.tv_country_choose);
        this.mViewLine = viewInflate.findViewById(com.boat.Xtend.two.R.id.view_line);
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            this.container.setBackground(drawable);
        }
        ColorStateList colorStateList = this.mTextColor;
        if (colorStateList != null) {
            this.mEtName.setTextColor(colorStateList);
        }
        if (TextUtils.isEmpty(this.mTextHint) && this.mEtName.getHint() != null) {
            this.mTextHint = this.mEtName.getHint().toString();
        }
        ColorStateList colorStateList2 = this.mTextColorHint;
        if (colorStateList2 != null) {
            this.mEtName.setHintTextColor(colorStateList2);
        }
        setNameHint(this.mTextHint);
        int i = this.mDeleteSourceId;
        if (i != 0) {
            setDeleteImageResource(i);
        }
    }

    public void setDeleteImageResource(int i) {
        this.mIbDelete.setImageResource(i);
    }

    private void setNameHint(String str) {
        this.mTextHint = str;
        if (this.mTextSizeHint > 0) {
            SpannableString spannableString = new SpannableString(this.mTextHint);
            spannableString.setSpan(new AbsoluteSizeSpan(this.mTextSizeHint, true), 0, spannableString.length(), 33);
            this.mEtName.setHint(new SpannedString(spannableString));
            return;
        }
        this.mEtName.setHint(this.mTextHint);
    }

    public String onActivityResultToGetCountrydode(int i, int i2, Intent intent) {
        String str = (String) this.mTvCountryChoose.getTag();
        if (i2 != 200) {
            return str;
        }
        if (i == 100 && intent != null) {
            String stringExtra = intent.getStringExtra("country_code");
            if (!TextUtils.isEmpty(intent.getStringExtra(CountryChooseActivity.COUNTRY)) && !TextUtils.isEmpty(stringExtra)) {
                this.mTvCountryChoose.setText("+" + stringExtra);
                this.mTvCountryChoose.setTag(stringExtra);
                return stringExtra;
            }
        }
        return str;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTextWatcher == null) {
            this.mTextWatcher = new TextWatcher() { // from class: com.ido.life.module.user.view.ViewMePhone.1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    if (ViewMePhone.this.mOnTextChangedListener != null) {
                        ViewMePhone.this.mOnTextChangedListener.changed(editable.toString());
                    }
                    ViewMePhone.this.validText(editable.toString());
                }
            };
        }
        this.mEtName.addTextChangedListener(this.mTextWatcher);
    }

    public void addFilter(InputFilter... inputFilterArr) {
        if (inputFilterArr == null || inputFilterArr.length == 0) {
            return;
        }
        this.mEtName.setFilters(inputFilterArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mEtName.removeTextChangedListener(this.mTextWatcher);
        super.onDetachedFromWindow();
    }
}