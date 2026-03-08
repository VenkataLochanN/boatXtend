package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.os.Build;
import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothHeadsetImpl {
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV = "+IPHONEACCEV";
    public static final int VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV_BATTERY_LEVEL = 1;
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT = "+XEVENT";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL = "BATTERY";

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getBatteryLevel(java.lang.String r4, java.lang.Object[] r5) {
        /*
            int r0 = r4.hashCode()
            r1 = 1
            r2 = -1
            r3 = 1884621890(0x70550c42, float:2.637405E29)
            if (r0 == r3) goto L1b
            r3 = 2093671693(0x7ccae50d, float:8.4279173E36)
            if (r0 == r3) goto L11
            goto L25
        L11:
            java.lang.String r0 = "+XEVENT"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L25
            r4 = 0
            goto L26
        L1b:
            java.lang.String r0 = "+IPHONEACCEV"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L25
            r4 = r1
            goto L26
        L25:
            r4 = r2
        L26:
            if (r4 == 0) goto L30
            if (r4 == r1) goto L2b
            goto L34
        L2b:
            int r2 = getBatteryLevelFromAppleBatteryVsc(r5)
            goto L34
        L30:
            int r2 = getBatteryLevelFromXEventVsc(r5)
        L34:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.impl.BluetoothHeadsetImpl.getBatteryLevel(java.lang.String, java.lang.Object[]):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
    
        if (r7 < 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
    
        if (r7 <= 9) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005a, code lost:
    
        return (r7 + 1) * 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getBatteryLevelFromAppleBatteryVsc(java.lang.Object[] r7) {
        /*
            int r0 = r7.length
            r1 = -1
            if (r0 != 0) goto La
            java.lang.String r7 = "getBatteryLevelFromAppleBatteryVsc() empty arguments"
        L6:
            com.realsil.sdk.core.logger.ZLogger.w(r7)
            return r1
        La:
            r0 = 0
            r2 = r7[r0]
            boolean r2 = r2 instanceof java.lang.Integer
            if (r2 == 0) goto L5b
            r2 = r7[r0]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r3 = r7.length
            int r4 = r2 * 2
            r5 = 1
            int r4 = r4 + r5
            if (r3 == r4) goto L23
            java.lang.String r7 = "getBatteryLevelFromAppleBatteryVsc() number of arguments does not match"
            goto L6
        L23:
            if (r0 >= r2) goto L4f
            int r3 = r0 * 2
            int r4 = r3 + 1
            r4 = r7[r4]
            boolean r6 = r4 instanceof java.lang.Integer
            if (r6 == 0) goto L4c
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            if (r4 == r5) goto L3a
            int r0 = r0 + 1
            goto L23
        L3a:
            int r3 = r3 + 2
            r7 = r7[r3]
            boolean r0 = r7 instanceof java.lang.Integer
            if (r0 == 0) goto L49
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            goto L50
        L49:
            java.lang.String r7 = "getBatteryLevelFromAppleBatteryVsc() error parsing indicator value"
            goto L6
        L4c:
            java.lang.String r7 = "getBatteryLevelFromAppleBatteryVsc() error parsing indicator type"
            goto L6
        L4f:
            r7 = r1
        L50:
            if (r7 < 0) goto L5a
            r0 = 9
            if (r7 <= r0) goto L57
            goto L5a
        L57:
            int r7 = r7 + r5
            int r1 = r7 * 10
        L5a:
            return r1
        L5b:
            java.lang.String r7 = "getBatteryLevelFromAppleBatteryVsc() error parsing number of arguments"
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.impl.BluetoothHeadsetImpl.getBatteryLevelFromAppleBatteryVsc(java.lang.Object[]):int");
    }

    public static int getBatteryLevelFromXEventVsc(Object[] objArr) {
        String string;
        int iIntValue;
        StringBuilder sb;
        if (objArr.length == 0) {
            string = "getBatteryLevelFromXEventVsc() empty arguments";
        } else {
            Object obj = objArr[0];
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL)) {
                    if (objArr.length != 5) {
                        sb = new StringBuilder();
                        sb.append("getBatteryLevelFromXEventVsc() wrong battery level event length: ");
                        iIntValue = objArr.length;
                    } else if ((objArr[1] instanceof Integer) && (objArr[2] instanceof Integer)) {
                        int iIntValue2 = ((Integer) objArr[1]).intValue();
                        iIntValue = ((Integer) objArr[2]).intValue();
                        if (iIntValue2 >= 0 && iIntValue > 1 && iIntValue2 <= iIntValue) {
                            return (iIntValue2 * 100) / (iIntValue - 1);
                        }
                        sb = new StringBuilder();
                        sb.append("getBatteryLevelFromXEventVsc() wrong event value, batteryLevel=");
                        sb.append(String.valueOf(iIntValue2));
                        sb.append(", numberOfLevels=");
                    } else {
                        string = "getBatteryLevelFromXEventVsc() error parsing event values";
                    }
                    sb.append(String.valueOf(iIntValue));
                    string = sb.toString();
                } else {
                    string = "getBatteryLevelFromXEventVsc() skip none BATTERY event: " + str;
                }
            } else {
                string = "getBatteryLevelFromXEventVsc() error parsing event name";
            }
        }
        ZLogger.w(string);
        return -1;
    }

    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("startScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return startScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("startScoUsingVirtualVoiceCall", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("stopScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return stopScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("stopScoUsingVirtualVoiceCall", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}