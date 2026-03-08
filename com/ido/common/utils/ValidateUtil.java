package com.ido.common.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class ValidateUtil {
    public static String getCorrectName(String str, String str2) {
        String strTrim = str2.trim();
        if (!checkMobileNO(strTrim)) {
            return strTrim;
        }
        return str + strTrim;
    }

    public static boolean checkName(String str) {
        return checkMobileNO(str) || checkEmail(str);
    }

    public static boolean checkCode(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static boolean checkMobileNO(String str) {
        return Pattern.compile("^[0-9]{1,}$").matcher(str).matches();
    }

    public static boolean checkEmail(String str) {
        return Pattern.compile("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$").matcher(str).matches();
    }

    public static boolean checkPassword(String str) {
        return Pattern.compile("^[0-9a-zA-Z]{6,}$").matcher(str).matches();
    }
}