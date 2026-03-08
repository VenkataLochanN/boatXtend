package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothA2dp;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothA2dpImpl {
    public static void setAvrcpAbsoluteVolume(BluetoothA2dp bluetoothA2dp, int i) {
        String str;
        if (bluetoothA2dp == null) {
            return;
        }
        try {
            bluetoothA2dp.getClass().getMethod("setAvrcpAbsoluteVolume", Integer.TYPE).invoke(bluetoothA2dp, Integer.valueOf(i));
        } catch (IllegalAccessException e2) {
            e = e2;
            str = "Could not execute method 'connect' in profile " + bluetoothA2dp.getClass().getName() + ", ignoring request." + e.toString();
            ZLogger.w(str);
        } catch (NoSuchMethodException unused) {
            str = "No connect method in the " + bluetoothA2dp.getClass().getName() + " class, ignoring request.";
            ZLogger.w(str);
        } catch (InvocationTargetException e3) {
            e = e3;
            str = "Could not execute method 'connect' in profile " + bluetoothA2dp.getClass().getName() + ", ignoring request." + e.toString();
            ZLogger.w(str);
        }
    }
}