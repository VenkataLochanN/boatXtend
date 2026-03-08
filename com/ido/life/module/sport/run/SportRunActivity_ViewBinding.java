package com.ido.life.module.sport.run;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.SportControlView;
import com.ido.life.module.sport.view.CustomGPSView;
import com.ido.life.module.sport.view.RunBottomView;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunActivity_ViewBinding implements Unbinder {
    private SportRunActivity target;
    private View view7f0a031a;
    private View view7f0a0334;
    private View view7f0a0335;

    public SportRunActivity_ViewBinding(SportRunActivity sportRunActivity) {
        this(sportRunActivity, sportRunActivity.getWindow().getDecorView());
    }

    public SportRunActivity_ViewBinding(final SportRunActivity sportRunActivity, View view) {
        this.target = sportRunActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_show_more, "field 'mIvShowMore' and method 'toHideMap'");
        sportRunActivity.mIvShowMore = (ImageView) Utils.castView(viewFindRequiredView, R.id.iv_show_more, "field 'mIvShowMore'", ImageView.class);
        this.view7f0a0335 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.run.SportRunActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRunActivity.toHideMap(view2);
            }
        });
        sportRunActivity.mLlRunBottom = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.ll_run_bottom, "field 'mLlRunBottom'", RelativeLayout.class);
        sportRunActivity.mTvDeviceStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_status, "field 'mTvDeviceStatus'", TextView.class);
        sportRunActivity.mGpsSportView = (CustomGPSView) Utils.findRequiredViewAsType(view, R.id.gps_sport_view, "field 'mGpsSportView'", CustomGPSView.class);
        sportRunActivity.mTvSportRunDistance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_run_distance, "field 'mTvSportRunDistance'", TextView.class);
        sportRunActivity.mTvSportRunDistanceUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_run_distance_unit, "field 'mTvSportRunDistanceUnit'", TextView.class);
        sportRunActivity.mTvSportSpeed = (RunBottomView) Utils.findRequiredViewAsType(view, R.id.tv_sport_speed, "field 'mTvSportSpeed'", RunBottomView.class);
        sportRunActivity.mTvSportTime = (RunBottomView) Utils.findRequiredViewAsType(view, R.id.tv_sport_time, "field 'mTvSportTime'", RunBottomView.class);
        sportRunActivity.mTvSportCalorie = (RunBottomView) Utils.findRequiredViewAsType(view, R.id.tv_sport_calorie, "field 'mTvSportCalorie'", RunBottomView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_show_map, "field 'mIvShowMap' and method 'toShowMap'");
        sportRunActivity.mIvShowMap = (ImageView) Utils.castView(viewFindRequiredView2, R.id.iv_show_map, "field 'mIvShowMap'", ImageView.class);
        this.view7f0a0334 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.run.SportRunActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRunActivity.toShowMap(view2);
            }
        });
        sportRunActivity.mSportDistanceMap = (RunBottomView) Utils.findRequiredViewAsType(view, R.id.tv_sport_distance_map, "field 'mSportDistanceMap'", RunBottomView.class);
        sportRunActivity.mSportTimeMap = (RunBottomView) Utils.findRequiredViewAsType(view, R.id.tv_sport_time_map, "field 'mSportTimeMap'", RunBottomView.class);
        sportRunActivity.mSportSpeedMap = (RunBottomView) Utils.findRequiredViewAsType(view, R.id.tv_sport_speed_map, "field 'mSportSpeedMap'", RunBottomView.class);
        sportRunActivity.mTvDeviceStatusMap = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_status_map, "field 'mTvDeviceStatusMap'", TextView.class);
        sportRunActivity.mGpsViewMap = (CustomGPSView) Utils.findRequiredViewAsType(view, R.id.gps_view_map, "field 'mGpsViewMap'", CustomGPSView.class);
        sportRunActivity.mIvSportSound = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_sport_sound, "field 'mIvSportSound'", ImageView.class);
        sportRunActivity.mTvSportTypeName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_type_name, "field 'mTvSportTypeName'", TextView.class);
        sportRunActivity.mTvSportStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_status, "field 'mTvSportStatus'", TextView.class);
        sportRunActivity.mTvGpsStatusDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_gps_status_desc, "field 'mTvGpsStatusDesc'", TextView.class);
        sportRunActivity.mLlDistance = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_distance, "field 'mLlDistance'", LinearLayout.class);
        sportRunActivity.mRvDistance = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rv_distance, "field 'mRvDistance'", RelativeLayout.class);
        sportRunActivity.sportControlView = (SportControlView) Utils.findRequiredViewAsType(view, R.id.sportControlView, "field 'sportControlView'", SportControlView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.iv_location, "method 'toLocation'");
        this.view7f0a031a = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.run.SportRunActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRunActivity.toLocation(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportRunActivity sportRunActivity = this.target;
        if (sportRunActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportRunActivity.mIvShowMore = null;
        sportRunActivity.mLlRunBottom = null;
        sportRunActivity.mTvDeviceStatus = null;
        sportRunActivity.mGpsSportView = null;
        sportRunActivity.mTvSportRunDistance = null;
        sportRunActivity.mTvSportRunDistanceUnit = null;
        sportRunActivity.mTvSportSpeed = null;
        sportRunActivity.mTvSportTime = null;
        sportRunActivity.mTvSportCalorie = null;
        sportRunActivity.mIvShowMap = null;
        sportRunActivity.mSportDistanceMap = null;
        sportRunActivity.mSportTimeMap = null;
        sportRunActivity.mSportSpeedMap = null;
        sportRunActivity.mTvDeviceStatusMap = null;
        sportRunActivity.mGpsViewMap = null;
        sportRunActivity.mIvSportSound = null;
        sportRunActivity.mTvSportTypeName = null;
        sportRunActivity.mTvSportStatus = null;
        sportRunActivity.mTvGpsStatusDesc = null;
        sportRunActivity.mLlDistance = null;
        sportRunActivity.mRvDistance = null;
        sportRunActivity.sportControlView = null;
        this.view7f0a0335.setOnClickListener(null);
        this.view7f0a0335 = null;
        this.view7f0a0334.setOnClickListener(null);
        this.view7f0a0334 = null;
        this.view7f0a031a.setOnClickListener(null);
        this.view7f0a031a = null;
    }
}