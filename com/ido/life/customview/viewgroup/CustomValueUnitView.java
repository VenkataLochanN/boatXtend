package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class CustomValueUnitView extends LinearLayout {
    private Context mContext;
    private MediumTextView mMtvUnit;
    private MediumTextView mMtvValue;
    private String mUnit;
    private int mUnitColor;
    private float mUnitSize;
    private String mValue;
    private int mValueColor;
    private float mValueSize;

    public CustomValueUnitView(Context context) {
        this(context, null);
    }

    public CustomValueUnitView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomValueUnitView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initAttrs(context, attributeSet);
        initViews();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomValueUnitView);
        this.mValueSize = typedArrayObtainStyledAttributes.getDimension(5, context.getResources().getDimension(com.boat.Xtend.two.R.dimen.size24sp));
        this.mUnitSize = typedArrayObtainStyledAttributes.getDimension(2, getResources().getDimension(com.boat.Xtend.two.R.dimen.size12sp));
        this.mValueColor = typedArrayObtainStyledAttributes.getColor(4, context.getColor(com.boat.Xtend.two.R.color.white));
        this.mUnitColor = typedArrayObtainStyledAttributes.getColor(1, context.getColor(com.boat.Xtend.two.R.color.white));
        this.mValue = typedArrayObtainStyledAttributes.getString(3);
        this.mUnit = typedArrayObtainStyledAttributes.getString(0);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initViews() {
        LayoutInflater.from(this.mContext).inflate(com.boat.Xtend.two.R.layout.view_custom_value_unit, (ViewGroup) this, true);
        this.mMtvValue = (MediumTextView) findViewById(com.boat.Xtend.two.R.id.mtv_value);
        this.mMtvUnit = (MediumTextView) findViewById(com.boat.Xtend.two.R.id.mtv_unit);
        this.mMtvValue.setTextSize(0, this.mValueSize);
        this.mMtvValue.setTextColor(this.mValueColor);
        this.mMtvUnit.setTextSize(0, this.mUnitSize);
        this.mMtvUnit.setTextColor(this.mUnitColor);
        this.mMtvValue.setText(this.mValue);
        this.mMtvUnit.setText(this.mUnit);
    }

    public float getValueSize() {
        return this.mValueSize;
    }

    public void setValueSize(float f2) {
        this.mValueSize = f2;
        this.mMtvValue.setTextSize(this.mValueSize);
    }

    public float getUnitSize() {
        return this.mUnitSize;
    }

    public void setUnitSize(float f2) {
        this.mUnitSize = f2;
        this.mMtvValue.setTextSize(this.mUnitSize);
    }

    public int getValueColor() {
        return this.mValueColor;
    }

    public void setValueColor(int i) {
        this.mValueColor = i;
        this.mMtvValue.setTextColor(this.mValueColor);
    }

    public int getUnitColor() {
        return this.mUnitColor;
    }

    public void setUnitColor(int i) {
        this.mUnitColor = i;
        this.mMtvUnit.setTextColor(this.mUnitColor);
    }

    public String getValue() {
        return this.mValue;
    }

    public void setValue(int i) {
        if (i == 0) {
            return;
        }
        setValue(this.mContext.getString(i));
    }

    public void setValue(String str) {
        this.mValue = str;
        this.mMtvValue.setText(str);
    }

    public String getUnit() {
        return this.mUnit;
    }

    public void setUnit(int i) {
        this.mMtvUnit.setVisibility(i == 0 ? 8 : 0);
        if (i == 0) {
            return;
        }
        setUnit(this.mContext.getString(i));
    }

    public void setUnit(String str) {
        this.mUnit = str;
        this.mMtvUnit.setText(str);
        this.mMtvUnit.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
    }
}