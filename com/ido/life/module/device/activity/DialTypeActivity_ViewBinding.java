package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.smartrefresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DialTypeActivity_ViewBinding implements Unbinder {
    private DialTypeActivity target;

    public DialTypeActivity_ViewBinding(DialTypeActivity dialTypeActivity) {
        this(dialTypeActivity, dialTypeActivity.getWindow().getDecorView());
    }

    public DialTypeActivity_ViewBinding(DialTypeActivity dialTypeActivity, View view) {
        this.target = dialTypeActivity;
        dialTypeActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        dialTypeActivity.mRefreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, R.id.refreshLayout, "field 'mRefreshLayout'", SmartRefreshLayout.class);
        dialTypeActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DialTypeActivity dialTypeActivity = this.target;
        if (dialTypeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dialTypeActivity.mRecyclerView = null;
        dialTypeActivity.mRefreshLayout = null;
        dialTypeActivity.mCommLoadingView = null;
    }
}