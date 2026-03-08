package com.amap.api.mapcore.util;

import com.google.android.material.timepicker.TimeModel;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: compiled from: RandomUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class ej {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f740a = "0123456789";

    public static String a() {
        Random random = new Random();
        String str = String.format(Locale.US, "%05d", Integer.valueOf(random.nextInt(10)));
        int iNextInt = random.nextInt(10);
        int iNextInt2 = random.nextInt(100);
        return new a(f740a, iNextInt2).a(iNextInt, str) + String.format(Locale.US, "%01d", Integer.valueOf(iNextInt)) + String.format(Locale.US, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(iNextInt2));
    }

    /* JADX INFO: compiled from: RandomUtil.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f741a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f742b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f743c;

        public a(String str, int i) {
            this.f742b = 1103515245;
            this.f743c = 12345;
            this.f741a = a(str, i, str.length());
        }

        public a() {
            this(11);
        }

        public a(int i) {
            this("ABCDEFGHIJKLMNOPQRSTUVWXYZ", i);
        }

        public char a(int i, boolean z) {
            int length = this.f741a.length();
            if (z) {
                i = length - i;
            }
            return this.f741a.charAt(i);
        }

        public int a(char c2, boolean z) {
            int length = this.f741a.length();
            int iIndexOf = this.f741a.indexOf(c2);
            return z ? length - iIndexOf : iIndexOf;
        }

        public int a(int i) {
            return (int) (((((long) i) * ((long) this.f742b)) + ((long) this.f743c)) & 2147483647L);
        }

        public String a(String str, int i, int i2) {
            StringBuffer stringBuffer = new StringBuffer(str);
            int length = str.length();
            for (int i3 = 0; i3 < i2; i3++) {
                int iA = a(i);
                int i4 = iA % length;
                i = a(iA);
                int i5 = i % length;
                char cCharAt = stringBuffer.charAt(i4);
                stringBuffer.setCharAt(i4, stringBuffer.charAt(i5));
                stringBuffer.setCharAt(i5, cCharAt);
            }
            return stringBuffer.toString();
        }

        public String a(boolean z, int i, String str) {
            StringBuilder sb = new StringBuilder();
            int length = this.f741a.length();
            int length2 = str.length();
            for (int i2 = 0; i2 < length2; i2++) {
                int iA = a(str.charAt(i2), z);
                if (iA < 0) {
                    break;
                }
                sb.append(a(((iA + i) + i2) % length, z));
            }
            if (sb.length() == length2) {
                return sb.toString();
            }
            return null;
        }

        public String a(int i, String str) {
            return a(false, i, str);
        }
    }
}