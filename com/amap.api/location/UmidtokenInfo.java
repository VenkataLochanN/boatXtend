package com.amap.api.location;

import android.content.Context;
import android.os.Handler;
import com.loc.ej;
import com.loc.n;

/* JADX INFO: loaded from: classes.dex */
public class UmidtokenInfo {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static AMapLocationClient f101d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static Handler f98a = new Handler();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String f99b = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static long f102e = 30000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static boolean f100c = true;

    static class a implements AMapLocationListener {
        a() {
        }

        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            try {
                if (UmidtokenInfo.f101d != null) {
                    UmidtokenInfo.f98a.removeCallbacksAndMessages(null);
                    UmidtokenInfo.f101d.onDestroy();
                }
            } catch (Throwable th) {
                ej.a(th, "UmidListener", "onLocationChanged");
            }
        }
    }

    public static String getUmidtoken() {
        return f99b;
    }

    public static void setLocAble(boolean z) {
        f100c = z;
    }

    public static synchronized void setUmidtoken(Context context, String str) {
        try {
            f99b = str;
            n.a(str);
            if (f101d == null && f100c) {
                a aVar = new a();
                f101d = new AMapLocationClient(context);
                AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
                aMapLocationClientOption.setOnceLocation(true);
                aMapLocationClientOption.setNeedAddress(false);
                f101d.setLocationOption(aMapLocationClientOption);
                f101d.setLocationListener(aVar);
                f101d.startLocation();
                f98a.postDelayed(new Runnable() { // from class: com.amap.api.location.UmidtokenInfo.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (UmidtokenInfo.f101d != null) {
                                UmidtokenInfo.f101d.onDestroy();
                            }
                        } catch (Throwable th) {
                            ej.a(th, "UmidListener", "postDelayed");
                        }
                    }
                }, 30000L);
            }
        } catch (Throwable th) {
            ej.a(th, "UmidListener", "setUmidtoken");
        }
    }
}