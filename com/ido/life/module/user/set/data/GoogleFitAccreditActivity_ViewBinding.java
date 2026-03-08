package com.ido.life.module.user.set.data;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class GoogleFitAccreditActivity_ViewBinding implements Unbinder {
    private GoogleFitAccreditActivity target;
    private View view7f0a0776;
    private View view7f0a07fb;
    private View view7f0a0860;

    public GoogleFitAccreditActivity_ViewBinding(GoogleFitAccreditActivity googleFitAccreditActivity) {
        this(googleFitAccreditActivity, googleFitAccreditActivity.getWindow().getDecorView());
    }

    public GoogleFitAccreditActivity_ViewBinding(final GoogleFitAccreditActivity googleFitAccreditActivity, View view) {
        this.target = googleFitAccreditActivity;
        googleFitAccreditActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_google_login, "field 'mTvGoogleLogin' and method 'onViewClicked'");
        googleFitAccreditActivity.mTvGoogleLogin = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_google_login, "field 'mTvGoogleLogin'", TextView.class);
        this.view7f0a0860 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.data.GoogleFitAccreditActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                googleFitAccreditActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_cancel_google_accredit, "field 'tv_cancel_google_accredit' and method 'onViewClicked'");
        googleFitAccreditActivity.tv_cancel_google_accredit = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_cancel_google_accredit, "field 'tv_cancel_google_accredit'", TextView.class);
        this.view7f0a07fb = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.data.GoogleFitAccreditActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                googleFitAccreditActivity.onViewClicked(view2);
            }
        });
        googleFitAccreditActivity.tvGoogleLoginState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_google_login_state, "field 'tvGoogleLoginState'", TextView.class);
        googleFitAccreditActivity.mTvStravaTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_strava_title, "field 'mTvStravaTitle'", TextView.class);
        googleFitAccreditActivity.mTvStravaTips = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_strava_tips, "field 'mTvStravaTips'", TextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.data.GoogleFitAccreditActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                googleFitAccreditActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        GoogleFitAccreditActivity googleFitAccreditActivity = this.target;
        if (googleFitAccreditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        googleFitAccreditActivity.mTitleText = null;
        googleFitAccreditActivity.mTvGoogleLogin = null;
        googleFitAccreditActivity.tv_cancel_google_accredit = null;
        googleFitAccreditActivity.tvGoogleLoginState = null;
        googleFitAccreditActivity.mTvStravaTitle = null;
        googleFitAccreditActivity.mTvStravaTips = null;
        this.view7f0a0860.setOnClickListener(null);
        this.view7f0a0860 = null;
        this.view7f0a07fb.setOnClickListener(null);
        this.view7f0a07fb = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}