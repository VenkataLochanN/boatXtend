package com.ido.life.module.user.sportrecord;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class SportRecordActivity_ViewBinding implements Unbinder {
    private SportRecordActivity target;
    private View view7f0a033a;
    private View view7f0a06bc;
    private View view7f0a0776;
    private View view7f0a0962;

    public SportRecordActivity_ViewBinding(SportRecordActivity sportRecordActivity) {
        this(sportRecordActivity, sportRecordActivity.getWindow().getDecorView());
    }

    public SportRecordActivity_ViewBinding(final SportRecordActivity sportRecordActivity, View view) {
        this.target = sportRecordActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'toBack'");
        sportRecordActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRecordActivity.toBack(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.rv_sport_type, "field 'rvSportType' and method 'toChooseSportType'");
        sportRecordActivity.rvSportType = (RelativeLayout) Utils.castView(viewFindRequiredView2, R.id.rv_sport_type, "field 'rvSportType'", RelativeLayout.class);
        this.view7f0a06bc = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRecordActivity.toChooseSportType(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_sport_type, "field 'mTvSportType' and method 'toChooseSportType'");
        sportRecordActivity.mTvSportType = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_sport_type, "field 'mTvSportType'", TextView.class);
        this.view7f0a0962 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRecordActivity.toChooseSportType(view2);
            }
        });
        sportRecordActivity.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTvTitle'", TextView.class);
        sportRecordActivity.mRvExpandList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.expand_list, "field 'mRvExpandList'", RecyclerView.class);
        sportRecordActivity.mEmptyTv = (TextView) Utils.findRequiredViewAsType(view, R.id.empty_tv_text, "field 'mEmptyTv'", TextView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.iv_sport_type, "method 'toChooseSportType'");
        this.view7f0a033a = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.sportrecord.SportRecordActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRecordActivity.toChooseSportType(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportRecordActivity sportRecordActivity = this.target;
        if (sportRecordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportRecordActivity.mTitleLeftBtn = null;
        sportRecordActivity.rvSportType = null;
        sportRecordActivity.mTvSportType = null;
        sportRecordActivity.mTvTitle = null;
        sportRecordActivity.mRvExpandList = null;
        sportRecordActivity.mEmptyTv = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a06bc.setOnClickListener(null);
        this.view7f0a06bc = null;
        this.view7f0a0962.setOnClickListener(null);
        this.view7f0a0962 = null;
        this.view7f0a033a.setOnClickListener(null);
        this.view7f0a033a = null;
    }
}