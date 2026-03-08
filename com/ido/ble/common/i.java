package com.ido.ble.common;

import android.text.TextUtils;
import com.ido.ble.protocol.model.SupportFunctionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class i {
    private static String a(int i, String str) {
        byte[] bArrD;
        if (i <= 0) {
            return str;
        }
        if (TextUtils.isEmpty(str) || (bArrD = c.d(str)) == null) {
            return "";
        }
        if (bArrD.length <= i) {
            return str;
        }
        String str2 = "";
        int i2 = 0;
        while (i2 < str.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            int i3 = i2 + 1;
            sb.append(str.substring(i2, i3));
            String string = sb.toString();
            byte[] bArrD2 = c.d(string);
            if (bArrD2 == null) {
                return string;
            }
            if (bArrD2.length > i) {
                return string.substring(0, i2);
            }
            i2 = i3;
            str2 = string;
        }
        return str2;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a(b() ? 250 : a() ? 128 : 64, str);
    }

    private static boolean a() {
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        return supportFunctionInfoV != null && supportFunctionInfoV.ex_noitice_128byte;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a((a() || b()) ? 32 : 16, str);
    }

    private static boolean b() {
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        return supportFunctionInfoV != null && supportFunctionInfoV.ex_notice_250byte;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a((a() || b()) ? 64 : 32, str);
    }
}