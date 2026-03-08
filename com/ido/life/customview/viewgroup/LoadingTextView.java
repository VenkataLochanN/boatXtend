package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;

/* JADX INFO: loaded from: classes2.dex */
public class LoadingTextView extends LinearLayout {
    private int mDisabledTextColor;
    private ImageView mIv;
    private int mTextColor;
    private TextView mTextView;

    public LoadingTextView(Context context) {
        this(context, null);
    }

    public LoadingTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.view_dial_set_label, (ViewGroup) this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.LoadingTextView);
        String string = typedArrayObtainStyledAttributes.getString(0);
        this.mTextColor = typedArrayObtainStyledAttributes.getColor(1, getResources().getColor(R.color.color_FF4A00));
        this.mDisabledTextColor = typedArrayObtainStyledAttributes.getColor(2, getResources().getColor(R.color.white));
        float dimension = typedArrayObtainStyledAttributes.getDimension(3, getResources().getDimension(R.dimen.size16sp));
        typedArrayObtainStyledAttributes.recycle();
        this.mIv = (ImageView) findViewById(R.id.iv_loading);
        this.mTextView = (TextView) findViewById(R.id.mtv_text);
        setText(string);
        this.mTextView.setTextColor(this.mTextColor);
        this.mTextView.setTextSize(0, dimension);
    }

    public void setText(int i) {
        this.mTextView.setText(i);
        changeTextColor(this.mTextView.getText().toString());
    }

    public void setText(String str) {
        this.mTextView.setText(str);
        changeTextColor(str);
    }

    private void changeTextColor(String str) {
        if (TextUtils.equals(str, LanguageUtil.getLanguageText(R.string.device_using))) {
            this.mTextView.setTextColor(this.mDisabledTextColor);
        } else {
            this.mTextView.setTextColor(this.mTextColor);
        }
    }

    public void setImageResource(int i) {
        this.mIv.clearAnimation();
        this.mIv.setImageResource(i);
    }

    public void setImageDrawable(Drawable drawable) {
        this.mIv.clearAnimation();
        this.mIv.setImageDrawable(drawable);
    }

    public void startAnimation() {
        this.mIv.clearAnimation();
        this.mIv.setVisibility(0);
        this.mIv.setImageResource(R.mipmap.icon_loading);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1800.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(4000L);
        rotateAnimation.setRepeatCount(-1);
        this.mIv.startAnimation(rotateAnimation);
    }

    public void stopAnimation() {
        this.mIv.clearAnimation();
        this.mIv.setVisibility(8);
    }
}