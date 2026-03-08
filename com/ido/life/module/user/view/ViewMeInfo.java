package com.ido.life.module.user.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class ViewMeInfo extends LinearLayout {
    private int colorList;
    private int endTextPadding;
    private boolean mArrowVisible;
    private boolean mEndImgVisible;
    private int mEndResourceId;
    private String mEndText;
    private int mEndTextColor;
    private int mEndTextPadding;
    private Space mEndTextSpace;
    private boolean mEndTextVisible;
    private ImageView mOptionEndArrowImg;
    private ImageView mOptionEndImg;
    private TextView mOptionEndText;
    private ImageView mOptionStartImg;
    private TextView mOptionStartText;
    private boolean mStartImageVisible;
    private int mStartResourceId;
    private String mStartText;
    private int mStartTextColor;
    private int mStartTextPadding;
    private Space mStartTextSpace;
    private int mTextSize;
    private int startTextPadding;

    public ViewMeInfo(Context context) {
        super(context);
        initView(context);
    }

    public ViewMeInfo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttribute(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_option, (ViewGroup) this, true);
        this.mOptionStartImg = (ImageView) findViewById(R.id.option_start_img);
        this.mOptionStartText = (TextView) findViewById(R.id.option_start_text);
        this.mOptionEndText = (TextView) findViewById(R.id.option_end_text);
        this.mOptionEndImg = (ImageView) findViewById(R.id.option_end_img);
        this.mOptionEndArrowImg = (ImageView) findViewById(R.id.option_end_arrow_img);
        this.mStartTextSpace = (Space) findViewById(R.id.start_text_space);
        this.mEndTextSpace = (Space) findViewById(R.id.end_text_space);
        setTextSize(this.mTextSize);
        setStartImageResource(this.mStartResourceId);
        String str = this.mStartText;
        if (str == null) {
            str = "";
        }
        setStartText(str);
        String str2 = this.mEndText;
        if (str2 == null) {
            str2 = "";
        }
        setEndText(str2);
        setEndImageResource(this.mEndResourceId);
        setStartImageVisible(this.mStartImageVisible);
        setEndTextVisible(this.mEndTextVisible);
        setEndImgVisible(this.mEndImgVisible);
        setArrowVisible(this.mArrowVisible);
        setStartTextColor(this.mStartTextColor);
        setEndTextColor(this.mEndTextColor);
        setStartTextPadding(this.mStartTextPadding);
        setEndTextPadding(this.mEndTextPadding);
    }

    private void initAttribute(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.OptionView);
        this.mTextSize = (int) typedArrayObtainStyledAttributes.getDimension(17, 12.0f);
        this.mStartText = typedArrayObtainStyledAttributes.getString(13);
        this.mStartResourceId = typedArrayObtainStyledAttributes.getResourceId(12, 0);
        this.mStartImageVisible = typedArrayObtainStyledAttributes.getBoolean(11, true);
        this.mEndText = typedArrayObtainStyledAttributes.getString(5);
        this.mEndResourceId = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        this.mEndTextVisible = typedArrayObtainStyledAttributes.getBoolean(9, false);
        this.mEndImgVisible = typedArrayObtainStyledAttributes.getBoolean(3, false);
        this.mArrowVisible = typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.mStartTextColor = typedArrayObtainStyledAttributes.getColor(15, -1);
        this.mEndTextColor = typedArrayObtainStyledAttributes.getColor(6, -1);
        this.mStartTextPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, 8);
        this.mEndTextPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 8);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z) {
            setStartTextColor(-12303292);
            setEndTextColor(-12303292);
            setArrowVisible(false);
        } else {
            setStartTextColor(this.mStartTextColor);
            setEndTextColor(this.mEndTextColor);
            setArrowVisible(true);
        }
    }

    public void setTextSize(float f2) {
        this.mOptionStartText.setTextSize(0, f2);
        this.mOptionEndText.setTextSize(0, f2);
    }

    public void setStartImageDrawable(Drawable drawable) {
        this.mOptionStartImg.setImageDrawable(drawable);
    }

    public String getStartText() {
        return this.mStartText;
    }

    public void setStartImageResource(int i) {
        this.mOptionStartImg.setImageResource(i);
    }

    public void setStartText(String str) {
        this.mOptionStartText.setText(str);
    }

    public void setStartText(int i) {
        this.mOptionStartText.setText(i);
    }

    public void setEndImageDrawable(Drawable drawable) {
        this.mOptionEndImg.setImageDrawable(drawable);
    }

    public void setEndImageResource(int i) {
        this.mOptionEndImg.setImageResource(i);
    }

    public void setEndText(String str) {
        this.mOptionEndText.setText(str);
    }

    public String getEndText() {
        return this.mOptionEndText.getText().toString();
    }

    public void setEndText(int i) {
        this.mOptionEndText.setText(i);
    }

    public void setArrowVisible(boolean z) {
        this.mOptionEndArrowImg.setVisibility(z ? 0 : 8);
    }

    public void setEndTextVisible(boolean z) {
        this.mOptionEndText.setVisibility(z ? 0 : 8);
    }

    public void setEndImgVisible(boolean z) {
        this.mOptionEndImg.setVisibility(z ? 0 : 4);
    }

    public void setStartImageVisible(boolean z) {
        this.mOptionStartImg.setVisibility(z ? 0 : 8);
    }

    public void setStartTextColor(int i) {
        this.mOptionStartText.setTextColor(i);
    }

    public void setEndTextColor(int i) {
        this.mOptionEndText.setTextColor(i);
    }

    protected void setStartTextPadding(int i) {
        this.mStartTextSpace.setLayoutParams(new LinearLayout.LayoutParams(i, -1));
    }

    protected void setEndTextPadding(int i) {
        this.mEndTextSpace.setLayoutParams(new LinearLayout.LayoutParams(i, -1));
    }
}