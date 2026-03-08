package com.ido.life.module.sport;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.sport.view.CustomGPSView;

/* JADX INFO: loaded from: classes2.dex */
public class SportBgFragment_ViewBinding implements Unbinder {
    private SportBgFragment target;
    private View view7f0a0331;
    private View view7f0a0966;

    public SportBgFragment_ViewBinding(final SportBgFragment sportBgFragment, View view) {
        this.target = sportBgFragment;
        sportBgFragment.mTvSportTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_total, "field 'mTvSportTotal'", TextView.class);
        sportBgFragment.mCustomGPSView = (CustomGPSView) Utils.findRequiredViewAsType(view, R.id.gps_sport_view, "field 'mCustomGPSView'", CustomGPSView.class);
        sportBgFragment.mReSportRun = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.re_sport_run, "field 'mReSportRun'", RelativeLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_start, "field 'startView' and method 'toStart'");
        sportBgFragment.startView = viewFindRequiredView;
        this.view7f0a0966 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportBgFragment.toStart(view2);
            }
        });
        sportBgFragment.mLaySportExplain = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_sport_explain, "field 'mLaySportExplain'", LinearLayout.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_setting, "method 'toSportSetting'");
        this.view7f0a0331 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.SportBgFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportBgFragment.toSportSetting(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportBgFragment sportBgFragment = this.target;
        if (sportBgFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportBgFragment.mTvSportTotal = null;
        sportBgFragment.mCustomGPSView = null;
        sportBgFragment.mReSportRun = null;
        sportBgFragment.startView = null;
        sportBgFragment.mLaySportExplain = null;
        this.view7f0a0966.setOnClickListener(null);
        this.view7f0a0966 = null;
        this.view7f0a0331.setOnClickListener(null);
        this.view7f0a0331 = null;
    }
}