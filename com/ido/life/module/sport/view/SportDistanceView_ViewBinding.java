package com.ido.life.module.sport.view;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportDistanceView_ViewBinding implements Unbinder {
    private SportDistanceView target;

    public SportDistanceView_ViewBinding(SportDistanceView sportDistanceView) {
        this(sportDistanceView, sportDistanceView);
    }

    public SportDistanceView_ViewBinding(SportDistanceView sportDistanceView, View view) {
        this.target = sportDistanceView;
        sportDistanceView.mTvDistanceCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_distance_count, "field 'mTvDistanceCount'", TextView.class);
        sportDistanceView.mSportStatusProgressView = (SportStatusProgressView) Utils.findRequiredViewAsType(view, R.id.sport_status_progress_view, "field 'mSportStatusProgressView'", SportStatusProgressView.class);
        sportDistanceView.mTvSpeedFastest = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_speed_fastest, "field 'mTvSpeedFastest'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportDistanceView sportDistanceView = this.target;
        if (sportDistanceView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportDistanceView.mTvDistanceCount = null;
        sportDistanceView.mSportStatusProgressView = null;
        sportDistanceView.mTvSpeedFastest = null;
    }
}