package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothGatt;
import android.text.TextUtils;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothGattImpl {
    public static HashMap<Integer, String> Ea = new HashMap<>();
    public static HashMap<Integer, String> Fa;
    public static HashMap<Integer, String> Ga;

    static {
        Ea.put(0, "UNKNOW");
        Ea.put(1, "READ");
        Ea.put(2, "READ_ENCRYPTED");
        Ea.put(4, "READ_ENCRYPTED_MITM");
        Ea.put(16, "WRITE");
        Ea.put(32, "WRITE_ENCRYPTED");
        Ea.put(64, "WRITE_ENCRYPTED_MITM");
        Ea.put(128, "WRITE_SIGNED");
        Ea.put(256, "WRITE_SIGNED_MITM");
        Fa = new HashMap<>();
        Fa.put(1, "BROADCAST");
        Fa.put(128, "EXTENDED_PROPS");
        Fa.put(32, "INDICATE");
        Fa.put(16, "NOTIFY");
        Fa.put(2, "READ");
        Fa.put(64, "SIGNED_WRITE");
        Fa.put(8, "WRITE");
        Fa.put(4, "WRITE_NO_RESPONSE");
        Ga = new HashMap<>();
        Ga.put(0, "UNKNOW");
        Ga.put(1, "READ");
        Ga.put(2, "READ_ENCRYPTED");
        Ga.put(4, "READ_ENCRYPTED_MITM");
        Ga.put(16, "WRITE");
        Ga.put(32, "WRITE_ENCRYPTED");
        Ga.put(64, "WRITE_ENCRYPTED_MITM");
        Ga.put(128, "WRITE_SIGNED");
        Ga.put(256, "WRITE_SIGNED_MITM");
    }

    public static String a(HashMap<Integer, String> map, int i) {
        String str = map.get(Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        List<Integer> listC = c(i);
        String str2 = "";
        for (int i2 = 0; i2 < listC.size(); i2++) {
            str2 = str2 + map.get(listC.get(i2)) + "|";
        }
        return str2;
    }

    public static List<Integer> c(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 32; i2++) {
            int i3 = 1 << i2;
            if ((i & i3) > 0) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        return arrayList;
    }

    public static String getCharPermission(int i) {
        return a(Ea, i);
    }

    public static String getCharPropertie(int i) {
        return a(Fa, i);
    }

    public static String getDescPermission(int i) {
        return a(Ga, i);
    }

    public static List<String> parseProperty(int i) {
        ArrayList arrayList = new ArrayList();
        if ((i & 1) == 1) {
            arrayList.add("BROADCAST");
        }
        if ((i & 2) == 2) {
            arrayList.add("READ");
        }
        if ((i & 4) == 4) {
            arrayList.add("WRITE_NO_RESPONSE");
        }
        if ((i & 8) == 8) {
            arrayList.add("WRITE");
        }
        return arrayList;
    }

    public static String parseProperty2(int i) {
        StringBuilder sb = new StringBuilder();
        List<String> property = parseProperty(i);
        if (property != null && property.size() > 0) {
            Iterator<String> it = property.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
        }
        return sb.toString();
    }

    public static void refresh(BluetoothGatt bluetoothGatt) {
        try {
            Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
            if (method != null) {
                ZLogger.v("clears the internal cache");
                boolean zBooleanValue = ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                StringBuilder sb = new StringBuilder();
                sb.append("refreshDevice: ");
                sb.append(zBooleanValue);
                ZLogger.d(sb.toString());
            }
        } catch (Exception e2) {
            ZLogger.e("An exception occured while refreshing device" + e2.toString());
        }
    }

    public static void refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            refresh(bluetoothGatt);
        }
    }
}