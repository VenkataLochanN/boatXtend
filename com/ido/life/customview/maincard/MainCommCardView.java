package com.ido.life.customview.maincard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class MainCommCardView extends LinearLayout {
    public MainCommCardView(Context context) {
        this(context, null);
    }

    public MainCommCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MainCommCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        LayoutInflater.from(context).inflate(R.layout.item_comm_card, (ViewGroup) null);
    }
}