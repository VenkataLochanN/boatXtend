package com.ido.ble.h;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static double a(String str, String str2) {
        int iIndexOf;
        if (str.equalsIgnoreCase("0.0E") || str.equalsIgnoreCase("0.0N") || str.indexOf(".") - 2 <= 0) {
            return 0.0d;
        }
        double d2 = ((double) Integer.parseInt(str.substring(0, iIndexOf))) + (Double.parseDouble(str.substring(iIndexOf, str.length() - 1)) / 60.0d);
        return !str.substring(str.length() + (-1)).equalsIgnoreCase(str2) ? -d2 : d2;
    }
}