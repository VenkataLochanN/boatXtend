package com.ido.life.module.sport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SportDistanceView extends FrameLayout {

    @BindView(R.id.sport_status_progress_view)
    SportStatusProgressView mSportStatusProgressView;

    @BindView(R.id.tv_distance_count)
    TextView mTvDistanceCount;

    @BindView(R.id.tv_speed_fastest)
    TextView mTvSpeedFastest;

    public SportDistanceView(Context context) {
        this(context, null);
    }

    public SportDistanceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SportDistanceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.sport_distance_status_layout, this);
        ButterKnife.bind(this);
        this.mTvSpeedFastest.setText(LanguageUtil.getLanguageText(R.string.sport_fastest));
    }

    public void setSportDistanceCount(String str) {
        this.mTvDistanceCount.setText(str);
    }

    public void setSportStatusProgress(int i, String str, String str2) {
        this.mSportStatusProgressView.setMax(100);
        this.mSportStatusProgressView.setProgress(i, str, str2, "");
    }

    public void setSportStatusEndText(int i) {
        this.mSportStatusProgressView.setEndTextColor(i);
    }

    public void setSportStatusProgressRechColor(int i, int i2) {
        this.mSportStatusProgressView.setReachedBarColor(i, i2);
    }
}