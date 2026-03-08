package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.boat.Xtend.two.R;
import com.ido.common.utils.NumUtil;
import com.ido.common.widget.textview.RegularTextView;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class SleepStatusDetailView extends LinearLayout {
    public static final int TYPE_AWAKE = 1;
    public static final int TYPE_DEEP = 3;
    public static final int TYPE_EYE_MOVEMENT = 4;
    public static final int TYPE_LIGHT = 2;
    private RegularTextView mRtvHour;
    private RegularTextView mRtvHourUnit;
    private RegularTextView mRtvMin;
    private RegularTextView mRtvMinUnit;
    private RegularTextView mRtvRatio;
    private RegularTextView mRtvType;

    public SleepStatusDetailView(Context context) {
        this(context, null);
    }

    public SleepStatusDetailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SleepStatusDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.layout_sleep_status_detail_view, (ViewGroup) this, true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.SleepStatusDetailView);
        int i2 = typedArrayObtainStyledAttributes.getInt(0, 1);
        typedArrayObtainStyledAttributes.recycle();
        this.mRtvType = (RegularTextView) findViewById(R.id.rtv_type);
        this.mRtvHour = (RegularTextView) findViewById(R.id.rtv_hour);
        this.mRtvMin = (RegularTextView) findViewById(R.id.rtv_min);
        this.mRtvRatio = (RegularTextView) findViewById(R.id.rtv_ratio);
        this.mRtvHourUnit = (RegularTextView) findViewById(R.id.rtv_hour_unit);
        this.mRtvMinUnit = (RegularTextView) findViewById(R.id.rtv_min_unit);
        if (i2 == 1) {
            this.mRtvType.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.sleep_dot_awake), (Drawable) null, (Drawable) null, (Drawable) null);
            this.mRtvType.setText(R.string.home_sleep_sober);
            return;
        }
        if (i2 == 2) {
            this.mRtvType.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.sleep_dot_light), (Drawable) null, (Drawable) null, (Drawable) null);
            this.mRtvType.setText(R.string.home_sleep_shallow);
        } else if (i2 == 3) {
            this.mRtvType.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.sleep_dot_deep), (Drawable) null, (Drawable) null, (Drawable) null);
            this.mRtvType.setText(R.string.home_sleep_deep);
        } else {
            if (i2 != 4) {
                return;
            }
            this.mRtvType.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.sleep_dot_eye_movement), (Drawable) null, (Drawable) null, (Drawable) null);
            this.mRtvType.setText(R.string.home_sleep_eye_movement);
        }
    }

    public void setData(int i, int i2, int i3) {
        if (i <= 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        this.mRtvHour.setVisibility(i == 0 ? 8 : 0);
        this.mRtvHourUnit.setVisibility(i == 0 ? 8 : 0);
        this.mRtvMin.setVisibility(i2 == 0 ? 8 : 0);
        this.mRtvMinUnit.setVisibility(i2 != 0 ? 0 : 8);
        this.mRtvHour.setText(String.valueOf(i));
        this.mRtvMin.setText(NumUtil.numberFormat(i2));
        this.mRtvRatio.setText(String.format(Locale.CHINA, "%d%%", Integer.valueOf(i3)));
    }

    public void setData(String str, String str2, String str3) {
        this.mRtvHour.setText(str);
        this.mRtvMin.setText(str2);
        this.mRtvRatio.setText(String.format(Locale.CHINA, "%s%%", str3));
    }
}