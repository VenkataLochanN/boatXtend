package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmTypeEditActivity_ViewBinding implements Unbinder {
    private AlarmTypeEditActivity target;

    public AlarmTypeEditActivity_ViewBinding(AlarmTypeEditActivity alarmTypeEditActivity) {
        this(alarmTypeEditActivity, alarmTypeEditActivity.getWindow().getDecorView());
    }

    public AlarmTypeEditActivity_ViewBinding(AlarmTypeEditActivity alarmTypeEditActivity, View view) {
        this.target = alarmTypeEditActivity;
        alarmTypeEditActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmTypeEditActivity alarmTypeEditActivity = this.target;
        if (alarmTypeEditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmTypeEditActivity.mRecyclerView = null;
    }
}