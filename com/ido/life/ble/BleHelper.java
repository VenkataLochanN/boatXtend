package com.ido.life.ble;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import androidx.fragment.app.Fragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.IdoApp;

/* JADX INFO: loaded from: classes2.dex */
public class BleHelper {
    public static final int REQUEST_CODE_BLUETOOTH_ON = 101;

    public static boolean isBluetoothOpen() {
        BluetoothAdapter adapter;
        BluetoothManager bluetoothManager = (BluetoothManager) IdoApp.getAppContext().getSystemService("bluetooth");
        return (bluetoothManager == null || (adapter = bluetoothManager.getAdapter()) == null || !adapter.isEnabled()) ? false : true;
    }

    public static boolean isOpenGPS(Context context) {
        LocationManager locationManager;
        return (context == null || (locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION)) == null || !locationManager.isProviderEnabled("gps")) ? false : true;
    }

    public static boolean openBLE() {
        BluetoothAdapter adapter;
        BluetoothManager bluetoothManager = (BluetoothManager) IdoApp.getAppContext().getSystemService("bluetooth");
        if (bluetoothManager == null || (adapter = bluetoothManager.getAdapter()) == null) {
            return false;
        }
        return adapter.enable();
    }

    public static void openBLE(Activity activity) {
        Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
        intent.setAction("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE");
        activity.startActivityForResult(intent, 101);
    }

    public static void openBLE(Fragment fragment) {
        Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
        intent.setAction("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE");
        fragment.startActivityForResult(intent, 101);
    }
}