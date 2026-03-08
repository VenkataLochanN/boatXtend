package com.ido.life.customview.viewgroup;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class CommLoadingView_ViewBinding implements Unbinder {
    private CommLoadingView target;

    public CommLoadingView_ViewBinding(CommLoadingView commLoadingView) {
        this(commLoadingView, commLoadingView);
    }

    public CommLoadingView_ViewBinding(CommLoadingView commLoadingView, View view) {
        this.target = commLoadingView;
        commLoadingView.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'mProgressBar'", ProgressBar.class);
        commLoadingView.mWaitingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.waiting_tv, "field 'mWaitingTv'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CommLoadingView commLoadingView = this.target;
        if (commLoadingView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commLoadingView.mProgressBar = null;
        commLoadingView.mWaitingTv = null;
    }
}