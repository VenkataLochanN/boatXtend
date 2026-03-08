package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class TopSportView extends FrameLayout {
    private String data;
    private ColorStateList mDataColor;
    private String mDescribe;
    private float mTataTextSize;
    private TextView mTvData;
    private TextView mTvDescribe;
    private TextView mTvUnit;
    private ColorStateList mUnitColor;
    private float mUnitMarginTop;
    private float mUnitTextSize;
    private String unit;

    public TopSportView(Context context) {
        this(context, null);
    }

    public TopSportView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TopSportView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initType(context, attributeSet);
        initView();
    }

    private void initType(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomRunItemView);
        this.mTataTextSize = typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        this.mUnitTextSize = typedArrayObtainStyledAttributes.getDimension(8, 0.0f);
        this.mUnitMarginTop = typedArrayObtainStyledAttributes.getDimension(6, 0.0f);
        this.mDataColor = typedArrayObtainStyledAttributes.getColorStateList(2);
        this.mUnitColor = typedArrayObtainStyledAttributes.getColorStateList(9);
        this.data = typedArrayObtainStyledAttributes.getString(0);
        this.unit = typedArrayObtainStyledAttributes.getString(7);
        this.mDescribe = typedArrayObtainStyledAttributes.getString(3);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(com.boat.Xtend.two.R.layout.widget_item_sport_month, (ViewGroup) this, true);
        this.mTvData = (TextView) findViewById(com.boat.Xtend.two.R.id.tv_date);
        this.mTvUnit = (TextView) findViewById(com.boat.Xtend.two.R.id.tv_unit);
        this.mTvDescribe = (TextView) findViewById(com.boat.Xtend.two.R.id.tv_describe);
        String str = this.data;
        if (str == null) {
            str = "";
        }
        setDataText(str);
        String str2 = this.unit;
        if (str2 == null) {
            str2 = "";
        }
        setUnitText(str2);
        String str3 = this.mDescribe;
        if (str3 == null) {
            str3 = "";
        }
        setDescribeText(str3);
        if (this.mUnitMarginTop != 0.0f) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTvUnit.getLayoutParams();
            layoutParams.topMargin = DipPixelUtil.dip2px(this.mUnitMarginTop);
            this.mTvUnit.setLayoutParams(layoutParams);
        }
        ColorStateList colorStateList = this.mDataColor;
        if (colorStateList != null) {
            this.mTvData.setTextColor(colorStateList);
        }
        ColorStateList colorStateList2 = this.mUnitColor;
        if (colorStateList2 != null) {
            this.mTvUnit.setTextColor(colorStateList2);
        }
        float f2 = this.mTataTextSize;
        if (f2 != 0.0f) {
            this.mTvData.setTextSize(0, f2);
        }
        float f3 = this.mUnitTextSize;
        if (f3 != 0.0f) {
            this.mTvUnit.setTextSize(0, f3);
        }
    }

    public void setUnitText(String str) {
        this.mTvUnit.setText(str);
    }

    public void setDescribeText(String str) {
        this.mTvDescribe.setText(str);
    }

    public void setDataText(String str) {
        this.mTvData.setText(str);
    }
}