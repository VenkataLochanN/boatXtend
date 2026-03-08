package com.ido.common.utils.statusbar;

import android.app.Activity;
import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidMHelper implements IHelper {
    @Override // com.ido.common.utils.statusbar.IHelper
    public boolean setStatusBarLightMode(Activity activity, boolean z) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        if (z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(8192);
            return true;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(0);
        return true;
    }
}