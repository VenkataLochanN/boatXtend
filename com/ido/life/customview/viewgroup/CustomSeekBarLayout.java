package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class CustomSeekBarLayout extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    private int currentValue;
    private Context mContext;
    private RelativeLayout mLayoutBottom;
    private int mMinProgress;
    private String mPreStr;
    private RegularTextView mRtvMax;
    private RegularTextView mRtvMin;
    private RegularTextView mRtvTitle;
    private RegularTextView mRtvValue;
    private SeekBar mSeekBar;
    private boolean mShowLabelUnit;
    private String mUnit;
    private int maxValue;
    private int minValue;
    private boolean showBottomLabel;
    private String title;

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public CustomSeekBarLayout(Context context) {
        this(context, null);
    }

    public CustomSeekBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomSeekBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initAttrs(context, attributeSet);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_seek_bar, (ViewGroup) this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.CustomSeekBarLayout);
        this.title = typedArrayObtainStyledAttributes.getString(0);
        this.minValue = typedArrayObtainStyledAttributes.getInteger(3, 0);
        this.maxValue = typedArrayObtainStyledAttributes.getInteger(2, 0);
        this.currentValue = typedArrayObtainStyledAttributes.getInteger(1, 0);
        this.showBottomLabel = typedArrayObtainStyledAttributes.getBoolean(6, true);
        this.mUnit = typedArrayObtainStyledAttributes.getString(8);
        this.mPreStr = typedArrayObtainStyledAttributes.getString(5);
        this.mShowLabelUnit = typedArrayObtainStyledAttributes.getBoolean(7, false);
        this.mMinProgress = typedArrayObtainStyledAttributes.getInteger(4, 1);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView() {
        this.mRtvTitle = (RegularTextView) findViewById(R.id.rtv_title);
        this.mRtvValue = (RegularTextView) findViewById(R.id.rtv_value);
        this.mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        this.mLayoutBottom = (RelativeLayout) findViewById(R.id.layout_bottom_label);
        this.mRtvMin = (RegularTextView) findViewById(R.id.rtv_value_min);
        this.mRtvMax = (RegularTextView) findViewById(R.id.rtv_value_max);
        if (this.mShowLabelUnit) {
            if (TextUtils.isEmpty(this.mUnit)) {
                this.mUnit = "";
            }
            this.mRtvMin.setText(String.format(Locale.getDefault(), "%1$d%2$s", Integer.valueOf(this.minValue), this.mUnit));
            this.mRtvMax.setText(String.format(Locale.getDefault(), "%1$d%2$s", Integer.valueOf(this.maxValue), this.mUnit));
        } else {
            this.mRtvMin.setText(String.valueOf(this.minValue));
            this.mRtvMax.setText(String.valueOf(this.maxValue));
        }
        this.mLayoutBottom.setVisibility(this.showBottomLabel ? 0 : 8);
        setTitle(this.title);
        setMaxValue(this.maxValue);
        setCurrentValue(this.currentValue);
        this.mSeekBar.setOnSeekBarChangeListener(this);
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(int i) {
        this.maxValue = i;
        this.mSeekBar.setMax((i - this.minValue) / this.mMinProgress);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
        this.mRtvTitle.setText(str);
    }

    private void setValue(int i) {
        this.currentValue = i;
        if (TextUtils.isEmpty(this.mPreStr)) {
            this.mPreStr = "";
        }
        this.mRtvValue.setText(String.format(Locale.getDefault(), this.mContext.getString(R.string.pre_x_unit), this.mPreStr, Integer.valueOf(i), this.mUnit));
    }

    public void setUnit(String str) {
        this.mUnit = str;
        setValue(this.currentValue);
    }

    public void setPreStr(String str) {
        this.mPreStr = str;
        setValue(this.currentValue);
    }

    public int getMinValue() {
        return this.minValue;
    }

    public void setMinValue(int i) {
        this.minValue = i;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public void setCurrentValue(int i) {
        int i2 = this.minValue;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.maxValue;
        if (i > i3) {
            i = i3;
        }
        this.mSeekBar.setProgress((i - this.minValue) / this.mMinProgress);
        setValue(i);
    }

    public RegularTextView getRtvMin() {
        return this.mRtvMin;
    }

    public RegularTextView getRtvMax() {
        return this.mRtvMax;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        setValue((i * this.mMinProgress) + this.minValue);
    }
}