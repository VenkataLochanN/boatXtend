package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothProfileImpl {
    public static final int A2DP_SINK = 11;
    public static final int AVRCP = 13;
    public static final int AVRCP_CONTROLLER = 12;
    public static final int HEADSET_CLIENT = 16;
    public static final int HID_HOST = 4;
    public static final int MAP = 9;
    public static final int MAP_CLIENT = 18;
    public static final int PAN = 5;
    public static final int PBAP = 6;
    public static final int PBAP_CLIENT = 17;

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        StringBuilder sb;
        String string;
        String string2;
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("connectProfile :");
            sb2.append(bluetoothDevice.getAddress());
            ZLogger.v(sb2.toString());
            Method method = bluetoothProfile.getClass().getMethod("connect", BluetoothDevice.class);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
        } catch (IllegalAccessException e2) {
            sb = new StringBuilder();
            sb.append("Could not execute method 'connect' in profile ");
            sb.append(bluetoothProfile.getClass().getName());
            sb.append(", ignoring request.");
            string = e2.toString();
            sb.append(string);
            string2 = sb.toString();
            ZLogger.w(string2);
            return false;
        } catch (NoSuchMethodException unused) {
            string2 = "No connect method in the " + bluetoothProfile.getClass().getName() + " class, ignoring request.";
            ZLogger.w(string2);
            return false;
        } catch (InvocationTargetException e3) {
            sb = new StringBuilder();
            sb.append("Could not execute method 'connect' in profile ");
            sb.append(bluetoothProfile.getClass().getName());
            sb.append(", ignoring request.");
            string = e3.toString();
            sb.append(string);
            string2 = sb.toString();
            ZLogger.w(string2);
            return false;
        }
    }

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) {
        StringBuilder sb;
        String string;
        String string2;
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("connectProfile :");
            sb2.append(bluetoothDevice.getAddress());
            ZLogger.v(sb2.toString());
            Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("connect", BluetoothDevice.class);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
        } catch (ClassNotFoundException e2) {
            sb = new StringBuilder();
            sb.append("Could not find clas: ");
            sb.append(str);
            sb.append(", ignoring request.");
            string = e2.toString();
            sb.append(string);
            string2 = sb.toString();
            ZLogger.w(string2);
            return false;
        } catch (IllegalAccessException e3) {
            sb = new StringBuilder();
            sb.append("Could not execute method 'connect' in profile ");
            sb.append(str);
            sb.append(", ignoring request.");
            string = e3.toString();
            sb.append(string);
            string2 = sb.toString();
            ZLogger.w(string2);
            return false;
        } catch (NoSuchMethodException unused) {
            string2 = "No connect method in the " + str + " class, ignoring request.";
            ZLogger.w(string2);
            return false;
        } catch (InvocationTargetException e4) {
            sb = new StringBuilder();
            sb.append("Could not execute method 'connect' in profile ");
            sb.append(str);
            sb.append(", ignoring request.");
            string = e4.toString();
            sb.append(string);
            string2 = sb.toString();
            ZLogger.w(string2);
            return false;
        }
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        StringBuilder sb;
        String string;
        String string2;
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return false;
        }
        try {
            ZLogger.v(String.format("disconnect : %s : %s", bluetoothProfile.getClass().getName(), bluetoothDevice.getAddress()));
            Method method = bluetoothProfile.getClass().getMethod("disconnect", BluetoothDevice.class);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
        } catch (IllegalAccessException e2) {
            sb = new StringBuilder();
            sb.append("Could not execute method 'disconnect' in profile ");
            sb.append("");
            sb.append(", ignoring request.");
            string = e2.toString();
            sb.append(string);
            string2 = sb.toString();
            ZLogger.e(string2);
            return false;
        } catch (NoSuchMethodException unused) {
            string2 = "No disconnect method in the  class, ignoring request.";
            ZLogger.e(string2);
            return false;
        } catch (InvocationTargetException e3) {
            sb = new StringBuilder();
            sb.append("Could not execute method 'disconnect' in profile ");
            sb.append("");
            sb.append(", ignoring request.");
            string = e3.toString();
            sb.append(string);
            string2 = sb.toString();
            ZLogger.e(string2);
            return false;
        }
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                ZLogger.v(String.format("disconnect : %s : %s", str, bluetoothDevice.getAddress()));
                Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("disconnect", BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (ClassNotFoundException e2) {
                ZLogger.w("Could not find clas: " + str + ", ignoring request." + e2.toString());
                return false;
            } catch (IllegalAccessException e3) {
                ZLogger.e("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e3.toString());
                return false;
            } catch (NoSuchMethodException unused) {
                ZLogger.e("No disconnect method in the " + str + " class, ignoring request.");
            } catch (InvocationTargetException e4) {
                ZLogger.e("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e4.toString());
                return false;
            }
        }
        return false;
    }

    public static int getConnectionState(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) {
        String str2;
        if (bluetoothProfile == null || bluetoothDevice == null) {
            return 0;
        }
        try {
            Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("getConnectionState", BluetoothDevice.class);
            method.setAccessible(true);
            return ((Integer) method.invoke(bluetoothProfile, bluetoothDevice)).intValue();
        } catch (ClassNotFoundException e2) {
            str2 = "Could not find clas: " + str + ", ignoring request." + e2.toString();
            ZLogger.w(str2);
            return 0;
        } catch (IllegalAccessException e3) {
            e = e3;
            str2 = "Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString();
            ZLogger.w(str2);
            return 0;
        } catch (NoSuchMethodException unused) {
            str2 = "No connect method in the CLASS_NAME class, ignoring request.";
            ZLogger.w(str2);
            return 0;
        } catch (InvocationTargetException e4) {
            e = e4;
            str2 = "Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString();
            ZLogger.w(str2);
            return 0;
        }
    }

    public static void setPriority(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothProfile == null) {
            return;
        }
        try {
            bluetoothProfile.getClass().getMethod("setPriority", BluetoothDevice.class, Integer.TYPE).invoke(bluetoothProfile, bluetoothDevice, Integer.valueOf(i));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}