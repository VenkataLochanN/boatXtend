package com.baidu.mapapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import com.baidu.mapsdkplatform.comapi.util.SysUpdateObservable;

/* JADX INFO: loaded from: classes.dex */
public class NetworkUtil {
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCurrentNetMode(android.content.Context r2) {
        /*
            android.net.NetworkInfo r0 = getActiveNetworkInfo(r2)
            r1 = 1
            if (r0 == 0) goto L31
            int r0 = r0.getType()
            if (r0 != r1) goto Le
            goto L32
        Le:
            java.lang.String r0 = "phone"
            java.lang.Object r2 = r2.getSystemService(r0)
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2
            int r2 = r2.getNetworkType()
            switch(r2) {
                case 1: goto L2f;
                case 2: goto L2f;
                case 3: goto L2c;
                case 4: goto L2a;
                case 5: goto L28;
                case 6: goto L28;
                case 7: goto L28;
                case 8: goto L25;
                case 9: goto L2c;
                case 10: goto L2c;
                case 11: goto L23;
                case 12: goto L28;
                case 13: goto L21;
                case 14: goto L1e;
                case 15: goto L2c;
                default: goto L1d;
            }
        L1d:
            goto L31
        L1e:
            r1 = 10
            goto L32
        L21:
            r1 = 4
            goto L32
        L23:
            r1 = 2
            goto L32
        L25:
            r1 = 8
            goto L32
        L28:
            r1 = 7
            goto L32
        L2a:
            r1 = 5
            goto L32
        L2c:
            r1 = 9
            goto L32
        L2f:
            r1 = 6
            goto L32
        L31:
            r1 = 0
        L32:
            java.lang.String r2 = java.lang.Integer.toString(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.NetworkUtil.getCurrentNetMode(android.content.Context):java.lang.String");
    }

    public static boolean initConnectState() {
        return true;
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            if (isWifiConnected(context)) {
                return true;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || 1 != activeNetworkInfo.getType()) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isWifiConnected(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return false;
        }
        try {
            if (1 == networkInfo.getType()) {
                return networkInfo.isConnected();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isWifiState(Context context) {
        if (context == null) {
            return false;
        }
        int wifiState = -1;
        try {
            wifiState = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getWifiState();
        } catch (Exception unused) {
        }
        return wifiState == 3;
    }

    public static void updateNetworkProxy(Context context) {
        SysUpdateObservable.getInstance().updateNetworkProxy(context);
    }
}