package com.ido.life.module.user.set.data.strava;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class StravaAccreditActivity_ViewBinding implements Unbinder {
    private StravaAccreditActivity target;
    private View view7f0a07d6;
    private View view7f0a0838;
    private View view7f0a0977;

    public StravaAccreditActivity_ViewBinding(StravaAccreditActivity stravaAccreditActivity) {
        this(stravaAccreditActivity, stravaAccreditActivity.getWindow().getDecorView());
    }

    public StravaAccreditActivity_ViewBinding(final StravaAccreditActivity stravaAccreditActivity, View view) {
        this.target = stravaAccreditActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_strava_privacy_policy, "field 'mTvStravaPrivacyPolicy' and method 'onViewClicked'");
        stravaAccreditActivity.mTvStravaPrivacyPolicy = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_strava_privacy_policy, "field 'mTvStravaPrivacyPolicy'", TextView.class);
        this.view7f0a0977 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                stravaAccreditActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_disagree, "field 'mTvDisagree' and method 'onViewClicked'");
        stravaAccreditActivity.mTvDisagree = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_disagree, "field 'mTvDisagree'", TextView.class);
        this.view7f0a0838 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                stravaAccreditActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_agree, "field 'mTvAgree' and method 'onViewClicked'");
        stravaAccreditActivity.mTvAgree = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_agree, "field 'mTvAgree'", TextView.class);
        this.view7f0a07d6 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                stravaAccreditActivity.onViewClicked(view2);
            }
        });
        stravaAccreditActivity.mTvStravaTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_strava_title, "field 'mTvStravaTitle'", TextView.class);
        stravaAccreditActivity.mTvStravaTips = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_strava_tips, "field 'mTvStravaTips'", TextView.class);
        stravaAccreditActivity.mProgressbar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progressbar, "field 'mProgressbar'", ProgressBar.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        StravaAccreditActivity stravaAccreditActivity = this.target;
        if (stravaAccreditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        stravaAccreditActivity.mTvStravaPrivacyPolicy = null;
        stravaAccreditActivity.mTvDisagree = null;
        stravaAccreditActivity.mTvAgree = null;
        stravaAccreditActivity.mTvStravaTitle = null;
        stravaAccreditActivity.mTvStravaTips = null;
        stravaAccreditActivity.mProgressbar = null;
        this.view7f0a0977.setOnClickListener(null);
        this.view7f0a0977 = null;
        this.view7f0a0838.setOnClickListener(null);
        this.view7f0a0838 = null;
        this.view7f0a07d6.setOnClickListener(null);
        this.view7f0a07d6 = null;
    }
}