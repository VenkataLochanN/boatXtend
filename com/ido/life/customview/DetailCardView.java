package com.ido.life.customview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DetailCardView extends LinearLayout {
    private MediumTextView mMtvTitle;
    private MediumTextView mMtvUnit1;
    private MediumTextView mMtvUnit2;
    private MediumTextView mMtvValue1;
    private MediumTextView mMtvValue2;

    public DetailCardView(Context context) {
        this(context, null);
    }

    public DetailCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DetailCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.view_detail_card, this);
        this.mMtvTitle = (MediumTextView) findViewById(R.id.mtv_title);
        this.mMtvValue1 = (MediumTextView) findViewById(R.id.mtv_value_1);
        this.mMtvUnit1 = (MediumTextView) findViewById(R.id.mtv_unit_1);
        this.mMtvValue2 = (MediumTextView) findViewById(R.id.mtv_value_2);
        this.mMtvUnit2 = (MediumTextView) findViewById(R.id.mtv_unit_2);
    }

    public void setTitle(int i) {
        this.mMtvTitle.setText(i);
    }

    public void setTitle(String str) {
        this.mMtvTitle.setText(str);
    }

    public void setValue1(String str) {
        MediumTextView mediumTextView = this.mMtvValue1;
        if (TextUtils.isEmpty(str)) {
            str = "--";
        }
        mediumTextView.setText(str);
        this.mMtvValue1.setVisibility(0);
    }

    public void setValue1(int i) {
        this.mMtvValue1.setText(i == 0 ? "--" : String.valueOf(i));
        this.mMtvValue1.setVisibility(0);
    }

    public void setUnit1(int i) {
        if (i == 0) {
            this.mMtvUnit1.setVisibility(8);
        } else {
            this.mMtvUnit1.setText(i);
            this.mMtvUnit1.setVisibility(0);
        }
    }

    public void setUnit1(String str) {
        this.mMtvUnit1.setText(str);
        this.mMtvUnit1.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
    }

    public void setValue2(String str) {
        MediumTextView mediumTextView = this.mMtvValue2;
        if (TextUtils.isEmpty(str)) {
            str = "--";
        }
        mediumTextView.setText(str);
        this.mMtvValue2.setVisibility(0);
    }

    public void setValue2(int i) {
        this.mMtvValue2.setText(i == 0 ? "--" : String.valueOf(i));
        this.mMtvValue2.setVisibility(0);
    }

    public void setUnit2(int i) {
        if (i == 0) {
            this.mMtvUnit2.setVisibility(8);
        } else {
            this.mMtvUnit2.setText(i);
            this.mMtvUnit2.setVisibility(0);
        }
    }

    public void setUnit2(String str) {
        this.mMtvUnit2.setText(str);
        this.mMtvUnit2.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
    }

    public void setData2Visible(int i) {
        this.mMtvValue2.setVisibility(0);
        this.mMtvUnit2.setVisibility(0);
    }
}