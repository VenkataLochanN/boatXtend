package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.os.Build;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothDeviceImpl {
    public static final int BATTERY_LEVEL_UNKNOWN = -1;
    public static final int PAIRING_VARIANT_CONSENT = 3;
    public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    public static final int PAIRING_VARIANT_OOB_CONSENT = 6;
    public static final int PAIRING_VARIANT_PASSKEY = 1;

    public static boolean cancelBondProcess(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("cancelBondProcess", null);
            if (method == null) {
                return false;
            }
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e2) {
            ZLogger.w("An exception occurred while cancelBondProcess : " + e2.getMessage());
            return false;
        }
    }

    public static boolean cancelPairingUserInput(BluetoothDevice bluetoothDevice) {
        return ((Boolean) bluetoothDevice.getClass().getMethod("cancelPairingUserInput", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
    }

    public static boolean createBond(BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 19) {
            return bluetoothDevice.createBond();
        }
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", null);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
            }
        } catch (Exception e2) {
            ZLogger.w("An exception occurred while creating bond: " + e2.toString());
        }
        return false;
    }

    public static boolean createBond(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            bluetoothDevice.getClass().getMethod("createBond", Integer.TYPE).invoke(bluetoothDevice, Integer.valueOf(i));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static boolean isConnected(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static String pairingVariantToString(int i) {
        switch (i) {
            case 0:
                return "PAIRING_VARIANT_PIN";
            case 1:
                return "PAIRING_VARIANT_PASSKEY";
            case 2:
                return "PAIRING_VARIANT_PASSKEY_CONFIRMATION";
            case 3:
                return "PAIRING_VARIANT_CONSENT";
            case 4:
                return "PAIRING_VARIANT_DISPLAY_PASSKEY";
            case 5:
                return "PAIRING_VARIANT_DISPLAY_PIN";
            case 6:
                return "PAIRING_VARIANT_OOB_CONSENT";
            default:
                return "UNKNOWN";
        }
    }

    public static String parseDeviceType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "Unknown" : "DUAL(BR/EDR/LE)" : "LE" : "BR/EDR";
    }

    public static boolean removeBond(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", null);
            if (method == null) {
                return false;
            }
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e2) {
            ZLogger.w("An exception occurred while removing bond information: " + e2.getMessage());
            return false;
        }
    }

    public static boolean setPassKey(BluetoothDevice bluetoothDevice, int i) {
        try {
            Boolean bool = (Boolean) bluetoothDevice.getClass().getDeclaredMethod("setPasskey", Integer.TYPE).invoke(bluetoothDevice, Integer.valueOf(i));
            StringBuilder sb = new StringBuilder();
            sb.append("returnValue=");
            sb.append(bool);
            ZLogger.e(sb.toString());
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return true;
    }
}