package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.module.sport.history.KmProgress;
import com.ido.life.module.sport.view.SportStatusProgressView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportKmSpace extends FrameLayout {
    private LinearLayout mLlSportDistance;

    public SportKmSpace(Context context) {
        this(context, null);
    }

    public SportKmSpace(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SportKmSpace(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        this.mLlSportDistance = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.widget_sport_km, (ViewGroup) this, true).findViewById(R.id.ll_km);
    }

    public void setSportData(List<KmProgress> list, String str, boolean z) {
        if (list == null || list.size() != 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, getResources().getDimensionPixelSize(R.dimen.sw_dp_22));
            layoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.sw_dp_3), 0, getResources().getDimensionPixelSize(R.dimen.sw_dp_3));
            for (int i = 0; i < list.size(); i++) {
                KmProgress kmProgress = list.get(i);
                SportStatusProgressView sportStatusProgressView = new SportStatusProgressView(getContext());
                sportStatusProgressView.setLayoutParams(layoutParams);
                sportStatusProgressView.setMax(100);
                if (kmProgress.getIsFaster()) {
                    sportStatusProgressView.setStartTextColor(ResourceUtil.getColor(R.color.color_131825));
                    sportStatusProgressView.setProgressTextColor(ResourceUtil.getColor(R.color.color_131825));
                    sportStatusProgressView.setEndTextColor(ResourceUtil.getColor(R.color.color_82868F));
                    sportStatusProgressView.setProgressColor(ResourceUtil.getColor(R.color.color_00ECEF));
                    sportStatusProgressView.setProgress(kmProgress.getProgress(), (i + 1) + "", kmProgress.getPace(), LanguageUtil.getLanguageText(R.string.sport_fastest));
                } else if (kmProgress.getIsReach()) {
                    sportStatusProgressView.setStartTextColor(ResourceUtil.getColor(R.color.color_131825));
                    sportStatusProgressView.setProgressTextColor(ResourceUtil.getColor(R.color.color_131825));
                    sportStatusProgressView.setEndTextColor(ResourceUtil.getColor(R.color.color_82868F));
                    sportStatusProgressView.setProgressColor(ResourceUtil.getColor(R.color.color_00ECEF));
                    sportStatusProgressView.setProgress(kmProgress.getProgress(), (i + 1) + "", kmProgress.getPace(), "");
                } else {
                    sportStatusProgressView.setStartTextColor(ResourceUtil.getColor(R.color.color_898989));
                    sportStatusProgressView.setProgressTextColor(ResourceUtil.getColor(R.color.color_898989));
                    sportStatusProgressView.setEndTextColor(ResourceUtil.getColor(R.color.color_898989));
                    sportStatusProgressView.setProgressColor(ResourceUtil.getColor(R.color.color_EAEBED));
                    sportStatusProgressView.setProgress(kmProgress.getProgress(), String.format(LanguageUtil.getLanguageText(R.string.sport_not_reach_one), str), kmProgress.getPace(), "");
                }
                this.mLlSportDistance.addView(sportStatusProgressView);
            }
        }
    }
}