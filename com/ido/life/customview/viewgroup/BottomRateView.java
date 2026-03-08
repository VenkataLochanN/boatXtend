package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class BottomRateView extends FrameLayout {
    private String mBottomText;
    private int mBottomTextColor;
    private TextView mOptionBottomText;
    private TextView mOptionTopText;
    private int mTextBottomSize;
    private int mTextTopSize;
    private String mTopText;
    private int mTopTextColor;

    public BottomRateView(Context context) {
        this(context, null);
    }

    public BottomRateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomRateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttribute(context, attributeSet);
        initView(context);
    }

    private void initAttribute(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomRateView);
        this.mTextTopSize = (int) typedArrayObtainStyledAttributes.getDimension(5, 12.0f);
        this.mTextBottomSize = (int) typedArrayObtainStyledAttributes.getDimension(2, 14.0f);
        this.mTopText = typedArrayObtainStyledAttributes.getString(3);
        this.mBottomText = typedArrayObtainStyledAttributes.getString(0);
        this.mTopTextColor = typedArrayObtainStyledAttributes.getColor(4, -1);
        this.mBottomTextColor = typedArrayObtainStyledAttributes.getColor(1, -1);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(com.boat.Xtend.two.R.layout.widget_bottom_rate_view, (ViewGroup) this, true);
        this.mOptionTopText = (TextView) findViewById(com.boat.Xtend.two.R.id.option_top_text);
        this.mOptionBottomText = (TextView) findViewById(com.boat.Xtend.two.R.id.option_bottom_text);
        setTextSize(this.mTextTopSize);
        setTextEndSize(this.mTextBottomSize);
        String str = this.mTopText;
        if (str == null) {
            str = "";
        }
        setTopText(str);
        String str2 = this.mBottomText;
        if (str2 == null) {
            str2 = "";
        }
        setBottomText(str2);
    }

    public void setTextSize(float f2) {
        this.mOptionTopText.setTextSize(0, f2);
    }

    public void setTextEndSize(float f2) {
        this.mOptionBottomText.setTextSize(0, f2);
    }

    public void setTopText(String str) {
        this.mOptionTopText.setText(str);
    }

    public void setBottomText(String str) {
        this.mOptionBottomText.setText(str);
    }
}