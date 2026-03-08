package com.ido.ble.common;

import android.bluetooth.BluetoothAdapter;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes2.dex */
public class l {
    public static String a() {
        return com.ido.ble.b.b().getResources().getConfiguration().locale.getLanguage();
    }

    public static String b() {
        return !h() ? "[bluetooth switch: off]" : !e() ? "[bluetooth permission: no]" : !i() ? "[gps switch: off]" : !g() ? "[gps permission: no]" : "";
    }

    public static int c() {
        return Build.VERSION.SDK_INT;
    }

    public static String d() {
        return Build.VERSION.RELEASE;
    }

    public static boolean e() {
        return ActivityCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.BLUETOOTH_ADMIN") == 0;
    }

    public static boolean f() {
        return ActivityCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.BLUETOOTH") == 0;
    }

    public static boolean g() {
        return ActivityCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public static boolean h() {
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    public static boolean i() {
        LocationManager locationManager = (LocationManager) com.ido.ble.b.b().getSystemService(FirebaseAnalytics.Param.LOCATION);
        return locationManager != null && locationManager.isProviderEnabled("gps");
    }

    public static boolean j() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}