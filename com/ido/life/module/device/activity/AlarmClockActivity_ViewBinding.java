package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockActivity_ViewBinding implements Unbinder {
    private AlarmClockActivity target;
    private View view7f0a0642;

    public AlarmClockActivity_ViewBinding(AlarmClockActivity alarmClockActivity) {
        this(alarmClockActivity, alarmClockActivity.getWindow().getDecorView());
    }

    public AlarmClockActivity_ViewBinding(final AlarmClockActivity alarmClockActivity, View view) {
        this.target = alarmClockActivity;
        alarmClockActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_center_add, "field 'mRtvCenterAdd' and method 'onViewClicked'");
        alarmClockActivity.mRtvCenterAdd = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_center_add, "field 'mRtvCenterAdd'", RegularTextView.class);
        this.view7f0a0642 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmClockActivity alarmClockActivity = this.target;
        if (alarmClockActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmClockActivity.mRecyclerView = null;
        alarmClockActivity.mRtvCenterAdd = null;
        this.view7f0a0642.setOnClickListener(null);
        this.view7f0a0642 = null;
    }
}