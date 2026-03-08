package com.ido.common.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ido.common.R;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class CustomPickerView extends LinearLayout implements View.OnClickListener, OnItemSelectedListener {
    private static final String HOUR_COMPANY = "";
    private static final String MINUTE_COMPANY = "";
    private int mData;
    private WheelView mDataView;
    private int mMin;
    private OnTimeSelectedListener mOnTimeSelectedListener;
    private boolean mShowRefresh;
    private TextView mTvUnit;

    public interface OnTimeSelectedListener {
        void onTimeSelected(int i);
    }

    public CustomPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttrs(context, attributeSet);
        initView(context);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTimePickerView);
        this.mShowRefresh = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CustomTimePickerView_shwRefresh, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView(Context context) {
        setGravity(16);
        inflate(context, R.layout.widget_custom_time_picker, this);
        this.mDataView = (WheelView) findViewById(R.id.wheel_data);
        this.mTvUnit = (TextView) findViewById(R.id.unit);
    }

    public void setUnitTextColor(int i) {
        TextView textView = this.mTvUnit;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mDataView.setCyclic(false);
        this.mDataView.setItemsVisibleCount(5);
        this.mDataView.setPadding(0, 8, 0, 8);
        setDefaultTimes();
    }

    private List<String> generateWheelData(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        while (i <= i2) {
            arrayList.add(i + str);
            i++;
        }
        return arrayList;
    }

    private List<String> generateWheelData(int i, String str) {
        return generateWheelData(0, i, str);
    }

    private void setDefaultTimes() {
        this.mDataView.setCurrentItem(this.mData);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        setDefaultTimes();
        onItemSelected(0);
    }

    public void setOnTimeSelectedListener(OnTimeSelectedListener onTimeSelectedListener) {
        this.mOnTimeSelectedListener = onTimeSelectedListener;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            onItemSelected(0);
        }
    }

    public int getmData() {
        return this.mData + this.mMin;
    }

    public void setData(int i, int i2, int i3, String str) {
        this.mDataView.setAdapter(new ArrayWheelAdapter(generateWheelData(i, i2, "")));
        this.mMin = i;
        this.mData = i3 - i;
        this.mDataView.setOnItemSelectedListener(this);
        if (TextUtils.isEmpty(str)) {
            this.mTvUnit.setVisibility(8);
        } else {
            this.mTvUnit.setVisibility(0);
            this.mTvUnit.setText(str);
        }
    }

    public void setWeelCenterTextColor(int i) {
        WheelView wheelView = this.mDataView;
        if (wheelView != null) {
            wheelView.setTextColorCenter(i);
        }
    }

    public void setWeelOutTextColor(int i) {
        WheelView wheelView = this.mDataView;
        if (wheelView != null) {
            wheelView.setTextColorOut(i);
        }
    }

    public void setCurrentValue(int i) {
        this.mDataView.setCurrentItem(i - this.mMin);
    }

    public String getStringTime() {
        return String.format(Locale.getDefault(), "%02d%02d", Integer.valueOf(this.mDataView.getCurrentItem()));
    }

    private int tranformIndex(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
    public void onItemSelected(int i) {
        post(new Runnable() { // from class: com.ido.common.widget.view.CustomPickerView.1
            @Override // java.lang.Runnable
            public void run() {
                CustomPickerView customPickerView = CustomPickerView.this;
                customPickerView.mData = customPickerView.mDataView.getCurrentItem();
                if (CustomPickerView.this.mOnTimeSelectedListener != null) {
                    CustomPickerView.this.mOnTimeSelectedListener.onTimeSelected(CustomPickerView.this.mData);
                }
            }
        });
    }
}