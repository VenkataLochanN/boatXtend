package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothClass;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.image.BinIndicator;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothClassImpl {

    public static class Device {
        public static final int PERIPHERAL_KEYBOARD = 1344;
        public static final int PERIPHERAL_KEYBOARD_POINTING = 1472;
        public static final int PERIPHERAL_NON_KEYBOARD_NON_POINTING = 1280;
        public static final int PERIPHERAL_POINTING = 1408;
    }

    public static boolean isAudioDevice(BluetoothClass bluetoothClass) {
        if (bluetoothClass == null) {
            return false;
        }
        ZLogger.v(String.format("getDeviceClass: 0x%04X", Integer.valueOf(bluetoothClass.getDeviceClass())));
        switch (bluetoothClass.getDeviceClass()) {
            case 1024:
            case 1028:
            case 1032:
            case BinIndicator.SubBinId.Bbpro.DSP_UI_PARAMETER_FILE /* 1040 */:
            case 1044:
            case 1048:
            case 1052:
            case 1056:
            case 1060:
            case 1064:
            case 1068:
            case 1072:
            case 1076:
            case 1080:
            case 1084:
            case 1088:
            case 1096:
                break;
        }
        return false;
    }

    public static boolean isHidDevice(BluetoothClass bluetoothClass) {
        if (bluetoothClass == null) {
            return false;
        }
        int deviceClass = bluetoothClass.getDeviceClass();
        boolean z = deviceClass == 1344 || deviceClass == 1408 || deviceClass == 1472;
        ZLogger.v(String.format("getDeviceClass: 0x%04X, isHid=%b", Integer.valueOf(bluetoothClass.getDeviceClass()), Boolean.valueOf(z)));
        return z;
    }
}