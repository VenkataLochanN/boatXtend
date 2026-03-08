package com.ido.life.dialog;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteLanguageDialogFragment_ViewBinding implements Unbinder {
    private RemoteLanguageDialogFragment target;
    private View view7f0a0547;
    private View view7f0a07f9;
    private View view7f0a080e;

    public RemoteLanguageDialogFragment_ViewBinding(final RemoteLanguageDialogFragment remoteLanguageDialogFragment, View view) {
        this.target = remoteLanguageDialogFragment;
        remoteLanguageDialogFragment.mRtvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_title, "field 'mRtvTitle'", TextView.class);
        remoteLanguageDialogFragment.mMtvContent = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_content, "field 'mMtvContent'", TextView.class);
        remoteLanguageDialogFragment.mMtvLanguage = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_language, "field 'mMtvLanguage'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.mtv_upgrade, "field 'mMtvUpgrade' and method 'onViewClicked'");
        remoteLanguageDialogFragment.mMtvUpgrade = (TextView) Utils.castView(viewFindRequiredView, R.id.mtv_upgrade, "field 'mMtvUpgrade'", TextView.class);
        this.view7f0a0547 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.RemoteLanguageDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                remoteLanguageDialogFragment.onViewClicked(view2);
            }
        });
        remoteLanguageDialogFragment.mLayoutRemoteLanguage = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_remote_language, "field 'mLayoutRemoteLanguage'", LinearLayout.class);
        remoteLanguageDialogFragment.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'mProgressBar'", ProgressBar.class);
        remoteLanguageDialogFragment.mRtvProgressValue = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_progress_value, "field 'mRtvProgressValue'", TextView.class);
        remoteLanguageDialogFragment.mRtvProgressTip = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_progress_tip, "field 'mRtvProgressTip'", TextView.class);
        remoteLanguageDialogFragment.mLayoutProgress = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_progress, "field 'mLayoutProgress'", LinearLayout.class);
        remoteLanguageDialogFragment.mRtvSuccess = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_success, "field 'mRtvSuccess'", TextView.class);
        remoteLanguageDialogFragment.mLayoutSuccess = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_success, "field 'mLayoutSuccess'", LinearLayout.class);
        remoteLanguageDialogFragment.mRtvFailed = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_failed, "field 'mRtvFailed'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'onViewClicked'");
        remoteLanguageDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0a07f9 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.RemoteLanguageDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                remoteLanguageDialogFragment.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'onViewClicked'");
        remoteLanguageDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0a080e = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.RemoteLanguageDialogFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                remoteLanguageDialogFragment.onViewClicked(view2);
            }
        });
        remoteLanguageDialogFragment.mLayoutFailed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_failed, "field 'mLayoutFailed'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RemoteLanguageDialogFragment remoteLanguageDialogFragment = this.target;
        if (remoteLanguageDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        remoteLanguageDialogFragment.mRtvTitle = null;
        remoteLanguageDialogFragment.mMtvContent = null;
        remoteLanguageDialogFragment.mMtvLanguage = null;
        remoteLanguageDialogFragment.mMtvUpgrade = null;
        remoteLanguageDialogFragment.mLayoutRemoteLanguage = null;
        remoteLanguageDialogFragment.mProgressBar = null;
        remoteLanguageDialogFragment.mRtvProgressValue = null;
        remoteLanguageDialogFragment.mRtvProgressTip = null;
        remoteLanguageDialogFragment.mLayoutProgress = null;
        remoteLanguageDialogFragment.mRtvSuccess = null;
        remoteLanguageDialogFragment.mLayoutSuccess = null;
        remoteLanguageDialogFragment.mRtvFailed = null;
        remoteLanguageDialogFragment.mTvCancel = null;
        remoteLanguageDialogFragment.mTvConfirm = null;
        remoteLanguageDialogFragment.mLayoutFailed = null;
        this.view7f0a0547.setOnClickListener(null);
        this.view7f0a0547 = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a080e.setOnClickListener(null);
        this.view7f0a080e = null;
    }
}