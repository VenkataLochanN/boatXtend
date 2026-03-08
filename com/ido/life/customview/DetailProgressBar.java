package com.ido.life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DetailProgressBar extends RelativeLayout {
    private MediumTextView mMtvUnit;
    private MediumTextView mMtvValue;
    private ProgressBar mProgressBar;
    private RegularTextView mRtvDate;
    private int maxValue;

    public DetailProgressBar(Context context) {
        this(context, null);
    }

    public DetailProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DetailProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxValue = 100;
        LayoutInflater.from(context).inflate(R.layout.view_detail_progress, this);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mMtvValue = (MediumTextView) findViewById(R.id.mtv_value);
        this.mMtvUnit = (MediumTextView) findViewById(R.id.mtv_unit);
        this.mRtvDate = (RegularTextView) findViewById(R.id.rtv_date);
    }

    public void setMaxValue(int i) {
        this.maxValue = i;
        if (this.maxValue == 0) {
            this.maxValue = 100;
        }
        this.mProgressBar.setMax(this.maxValue);
    }

    public void setProgress(int i, int i2) {
        setMaxValue(i2);
        setCurrentValue(i);
    }

    public void setCurrentValue(int i) {
        this.mProgressBar.setProgress(i);
        this.mMtvValue.setText(i == 0 ? "--" : String.valueOf(i));
    }

    public void setUnit(int i) {
        this.mMtvUnit.setText(i);
    }

    public void setUnit(String str) {
        this.mMtvUnit.setText(str);
    }

    public void setDate(int i) {
        this.mRtvDate.setText(i);
    }

    public void setDate(String str) {
        this.mRtvDate.setText(str);
    }
}