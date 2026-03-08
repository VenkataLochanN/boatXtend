package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CommLoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockV3Activity_ViewBinding implements Unbinder {
    private AlarmClockV3Activity target;
    private View view7f0a0642;

    public AlarmClockV3Activity_ViewBinding(AlarmClockV3Activity alarmClockV3Activity) {
        this(alarmClockV3Activity, alarmClockV3Activity.getWindow().getDecorView());
    }

    public AlarmClockV3Activity_ViewBinding(final AlarmClockV3Activity alarmClockV3Activity, View view) {
        this.target = alarmClockV3Activity;
        alarmClockV3Activity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_center_add, "field 'mRtvCenterAdd' and method 'onViewClicked'");
        alarmClockV3Activity.mRtvCenterAdd = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_center_add, "field 'mRtvCenterAdd'", RegularTextView.class);
        this.view7f0a0642 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockV3Activity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockV3Activity.onViewClicked(view2);
            }
        });
        alarmClockV3Activity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmClockV3Activity alarmClockV3Activity = this.target;
        if (alarmClockV3Activity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmClockV3Activity.mRecyclerView = null;
        alarmClockV3Activity.mRtvCenterAdd = null;
        alarmClockV3Activity.mCommLoadingView = null;
        this.view7f0a0642.setOnClickListener(null);
        this.view7f0a0642 = null;
    }
}