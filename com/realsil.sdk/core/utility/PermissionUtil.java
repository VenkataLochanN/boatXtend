package com.realsil.sdk.core.utility;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PermissionUtil {
    public static boolean checkSelfPermissions(Context context, String[] strArr) {
        if (strArr.length < 1) {
            return false;
        }
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String[] strArr) {
        if (strArr.length < 1) {
            return false;
        }
        for (String str : strArr) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyPermissions(int[] iArr) {
        if (iArr.length < 1) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}