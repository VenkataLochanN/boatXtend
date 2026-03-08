package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class CommLoadingView extends RelativeLayout {
    private String mLoadingText;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.waiting_tv)
    TextView mWaitingTv;

    public CommLoadingView(Context context) {
        this(context, null);
    }

    public CommLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(context, attributeSet);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.view_comm_loading, (ViewGroup) this, true);
        ButterKnife.bind(this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.CommLoadingView);
        this.mLoadingText = typedArrayObtainStyledAttributes.getString(0);
        typedArrayObtainStyledAttributes.recycle();
        setLoadingText(this.mLoadingText);
    }

    public void setLoadingText(int i) {
        if (i != 0) {
            this.mWaitingTv.setText(i);
        }
        this.mWaitingTv.setVisibility(i == 0 ? 8 : 0);
    }

    public void setLoadingText(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mWaitingTv.setVisibility(8);
        } else {
            this.mWaitingTv.setVisibility(0);
            this.mWaitingTv.setText(str);
        }
    }
}