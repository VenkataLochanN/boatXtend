package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.DeviceProperties;

/* JADX INFO: loaded from: classes.dex */
public final class zzi {
    private static int zzek = -1;

    public static int zza(Context context) {
        int i;
        if (zzek == -1) {
            if (DeviceProperties.isWearable(context)) {
                i = 3;
            } else {
                boolean z = false;
                if (DeviceProperties.isTv(context) || DeviceProperties.isAuto(context)) {
                    zzek = 0;
                } else {
                    if (DeviceProperties.isTablet(context.getResources()) && !zzb(context)) {
                        i = 2;
                    } else {
                        if (!TextUtils.isEmpty(Build.PRODUCT) && Build.PRODUCT.startsWith("glass_")) {
                            z = true;
                        }
                        if (z) {
                            i = 6;
                        } else {
                            zzek = 1;
                        }
                    }
                }
            }
            zzek = i;
        }
        return zzek;
    }

    private static boolean zzb(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getPhoneType() != 0;
        } catch (Resources.NotFoundException e2) {
            Log.wtf("Fitness", "Unable to determine type of device, assuming phone.", e2);
            return true;
        }
    }
}