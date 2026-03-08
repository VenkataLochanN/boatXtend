package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class MultiMotionModeActivity_ViewBinding implements Unbinder {
    private MultiMotionModeActivity target;

    public MultiMotionModeActivity_ViewBinding(MultiMotionModeActivity multiMotionModeActivity) {
        this(multiMotionModeActivity, multiMotionModeActivity.getWindow().getDecorView());
    }

    public MultiMotionModeActivity_ViewBinding(MultiMotionModeActivity multiMotionModeActivity, View view) {
        this.target = multiMotionModeActivity;
        multiMotionModeActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        multiMotionModeActivity.mRtvMotionTypeTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_motion_mode_tip, "field 'mRtvMotionTypeTip'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MultiMotionModeActivity multiMotionModeActivity = this.target;
        if (multiMotionModeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        multiMotionModeActivity.mRecyclerView = null;
        multiMotionModeActivity.mRtvMotionTypeTip = null;
    }
}