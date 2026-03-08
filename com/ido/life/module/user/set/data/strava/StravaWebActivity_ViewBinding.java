package com.ido.life.module.user.set.data.strava;

import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class StravaWebActivity_ViewBinding implements Unbinder {
    private StravaWebActivity target;

    public StravaWebActivity_ViewBinding(StravaWebActivity stravaWebActivity) {
        this(stravaWebActivity, stravaWebActivity.getWindow().getDecorView());
    }

    public StravaWebActivity_ViewBinding(StravaWebActivity stravaWebActivity, View view) {
        this.target = stravaWebActivity;
        stravaWebActivity.mWvStrava = (WebView) Utils.findRequiredViewAsType(view, R.id.wv_strava, "field 'mWvStrava'", WebView.class);
        stravaWebActivity.mProgressbar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progressbar, "field 'mProgressbar'", ProgressBar.class);
        stravaWebActivity.mLayoutReload = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_reload, "field 'mLayoutReload'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        StravaWebActivity stravaWebActivity = this.target;
        if (stravaWebActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        stravaWebActivity.mWvStrava = null;
        stravaWebActivity.mProgressbar = null;
        stravaWebActivity.mLayoutReload = null;
    }
}