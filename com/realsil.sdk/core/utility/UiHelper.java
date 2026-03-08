package com.realsil.sdk.core.utility;

import android.content.Context;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes3.dex */
public class UiHelper {
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }
}