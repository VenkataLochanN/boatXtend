package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.customview.recyclerview.CommRefreshHeader;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.smartrefresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteLanguageActivity_ViewBinding implements Unbinder {
    private RemoteLanguageActivity target;
    private View view7f0a0535;

    public RemoteLanguageActivity_ViewBinding(RemoteLanguageActivity remoteLanguageActivity) {
        this(remoteLanguageActivity, remoteLanguageActivity.getWindow().getDecorView());
    }

    public RemoteLanguageActivity_ViewBinding(final RemoteLanguageActivity remoteLanguageActivity, View view) {
        this.target = remoteLanguageActivity;
        remoteLanguageActivity.mRefreshHeader = (CommRefreshHeader) Utils.findRequiredViewAsType(view, R.id.refresh_header, "field 'mRefreshHeader'", CommRefreshHeader.class);
        remoteLanguageActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        remoteLanguageActivity.mRefreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, R.id.refreshLayout, "field 'mRefreshLayout'", SmartRefreshLayout.class);
        remoteLanguageActivity.mMtvNoLanguage = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_no_language, "field 'mMtvNoLanguage'", MediumTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.mtv_retry, "field 'mMtvRetry' and method 'onViewClicked'");
        remoteLanguageActivity.mMtvRetry = (MediumTextView) Utils.castView(viewFindRequiredView, R.id.mtv_retry, "field 'mMtvRetry'", MediumTextView.class);
        this.view7f0a0535 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.RemoteLanguageActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                remoteLanguageActivity.onViewClicked();
            }
        });
        remoteLanguageActivity.mLayoutFailed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_failed, "field 'mLayoutFailed'", LinearLayout.class);
        remoteLanguageActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RemoteLanguageActivity remoteLanguageActivity = this.target;
        if (remoteLanguageActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        remoteLanguageActivity.mRefreshHeader = null;
        remoteLanguageActivity.mRecyclerView = null;
        remoteLanguageActivity.mRefreshLayout = null;
        remoteLanguageActivity.mMtvNoLanguage = null;
        remoteLanguageActivity.mMtvRetry = null;
        remoteLanguageActivity.mLayoutFailed = null;
        remoteLanguageActivity.mCommLoadingView = null;
        this.view7f0a0535.setOnClickListener(null);
        this.view7f0a0535 = null;
    }
}