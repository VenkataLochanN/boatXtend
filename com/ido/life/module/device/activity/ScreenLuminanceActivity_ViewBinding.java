package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.customview.viewgroup.CommLoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenLuminanceActivity_ViewBinding implements Unbinder {
    private ScreenLuminanceActivity target;

    public ScreenLuminanceActivity_ViewBinding(ScreenLuminanceActivity screenLuminanceActivity) {
        this(screenLuminanceActivity, screenLuminanceActivity.getWindow().getDecorView());
    }

    public ScreenLuminanceActivity_ViewBinding(ScreenLuminanceActivity screenLuminanceActivity, View view) {
        this.target = screenLuminanceActivity;
        screenLuminanceActivity.mTvTip = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.tv_tip, "field 'mTvTip'", MediumTextView.class);
        screenLuminanceActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        screenLuminanceActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ScreenLuminanceActivity screenLuminanceActivity = this.target;
        if (screenLuminanceActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        screenLuminanceActivity.mTvTip = null;
        screenLuminanceActivity.mRecyclerView = null;
        screenLuminanceActivity.mCommLoadingView = null;
    }
}