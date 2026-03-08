package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CommLoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class QuickAppActivity_ViewBinding implements Unbinder {
    private QuickAppActivity target;

    public QuickAppActivity_ViewBinding(QuickAppActivity quickAppActivity) {
        this(quickAppActivity, quickAppActivity.getWindow().getDecorView());
    }

    public QuickAppActivity_ViewBinding(QuickAppActivity quickAppActivity, View view) {
        this.target = quickAppActivity;
        quickAppActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        quickAppActivity.mLayoutContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", LinearLayout.class);
        quickAppActivity.mLayoutFailed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_load_failed, "field 'mLayoutFailed'", LinearLayout.class);
        quickAppActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        quickAppActivity.mAppTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_quick_app_tip, "field 'mAppTip'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        QuickAppActivity quickAppActivity = this.target;
        if (quickAppActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        quickAppActivity.mRecyclerView = null;
        quickAppActivity.mLayoutContent = null;
        quickAppActivity.mLayoutFailed = null;
        quickAppActivity.mCommLoadingView = null;
        quickAppActivity.mAppTip = null;
    }
}