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
public class LeftMiddleRightView extends FrameLayout {
    private String mEndText;
    private int mEndTextColor;
    private int mMiddleColor;
    private String mMiddleText;
    private TextView mOptionEndText;
    private TextView mOptionMiddleText;
    private TextView mOptionStartText;
    private String mStartText;
    private int mStartTextColor;
    private int mTextEndSize;
    private int mTextMiddleSize;
    private int mTextSize;

    public LeftMiddleRightView(Context context) {
        this(context, null);
    }

    public LeftMiddleRightView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LeftMiddleRightView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttribute(context, attributeSet);
        initView(context);
    }

    private void initAttribute(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LeftMiddleRightView);
        this.mTextSize = (int) typedArrayObtainStyledAttributes.getDimension(8, 12.0f);
        this.mTextEndSize = (int) typedArrayObtainStyledAttributes.getDimension(2, 14.0f);
        this.mTextMiddleSize = (int) typedArrayObtainStyledAttributes.getDimension(5, 14.0f);
        this.mStartText = typedArrayObtainStyledAttributes.getString(6);
        this.mEndText = typedArrayObtainStyledAttributes.getString(0);
        this.mMiddleText = typedArrayObtainStyledAttributes.getString(3);
        this.mStartTextColor = typedArrayObtainStyledAttributes.getColor(7, -1);
        this.mEndTextColor = typedArrayObtainStyledAttributes.getColor(1, -1);
        this.mMiddleColor = typedArrayObtainStyledAttributes.getColor(1, -1);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(com.boat.Xtend.two.R.layout.widget_left_middle_right, (ViewGroup) this, true);
        this.mOptionStartText = (TextView) findViewById(com.boat.Xtend.two.R.id.option_start_text);
        this.mOptionEndText = (TextView) findViewById(com.boat.Xtend.two.R.id.option_end_text);
        this.mOptionMiddleText = (TextView) findViewById(com.boat.Xtend.two.R.id.option_middle_text);
        setTextSize(this.mTextSize);
        setTextEndSize(this.mTextEndSize);
        setTextMiddleSize(this.mTextMiddleSize);
        String str = this.mStartText;
        if (str == null) {
            str = "";
        }
        setStartText(str);
        String str2 = this.mMiddleText;
        if (str2 == null) {
            str2 = "";
        }
        setMiddleText(str2);
        String str3 = this.mEndText;
        if (str3 == null) {
            str3 = "";
        }
        setEndText(str3);
    }

    private void setTextMiddleSize(int i) {
        this.mOptionMiddleText.setTextSize(0, i);
    }

    public void setTextSize(float f2) {
        this.mOptionStartText.setTextSize(0, f2);
    }

    public void setTextEndSize(float f2) {
        this.mOptionEndText.setTextSize(0, f2);
    }

    public void setStartText(String str) {
        this.mOptionStartText.setText(str);
    }

    public void setEndText(String str) {
        this.mOptionEndText.setText(str);
    }

    public void setMiddleText(String str) {
        this.mOptionMiddleText.setText(str);
    }
}