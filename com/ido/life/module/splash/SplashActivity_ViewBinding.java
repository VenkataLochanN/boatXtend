package com.ido.life.module.splash;

import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SplashActivity_ViewBinding implements Unbinder {
    private SplashActivity target;

    public SplashActivity_ViewBinding(SplashActivity splashActivity) {
        this(splashActivity, splashActivity.getWindow().getDecorView());
    }

    public SplashActivity_ViewBinding(SplashActivity splashActivity, View view) {
        this.target = splashActivity;
        splashActivity.mLayoutRoot = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_splash_root, "field 'mLayoutRoot'", RelativeLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SplashActivity splashActivity = this.target;
        if (splashActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        splashActivity.mLayoutRoot = null;
    }
}