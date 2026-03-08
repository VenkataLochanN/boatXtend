package com.loc;

import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.UByte;

/* JADX INFO: compiled from: ConstConfig.java */
/* JADX INFO: loaded from: classes3.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5328a = "9aj&#k81";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f5329b = "IaHR0cDovL2xvZ3MuYW1hcC5jb20vd3MvbG9nL3VwbG9hZD9wcm9kdWN0PSVzJnR5cGU9JXMmcGxhdGZvcm09JXMmY2hhbm5lbD0lcyZzaWduPSVz";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static byte[] f5330c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5331d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5332e = false;
    private static String k = "ADgAIwBbAA8AagAIAHIAEwCFAD8AxABDAQcAIQEoADgBYAA8AZwAnwI7APADKwAHAzIADAM+AA9LWVc1a2NtOXBaQzV2Y3k1VFpYSjJhV05sVFdGdVlXZGxjZ1FaMlYwVTJWeWRtbGpaUUljR2h2Ym1VVWFYQm9iMjVsYzNWaWFXNW1id01ZMjl0TG1GdVpISnZhV1F1YVc1MFpYSnVZV3d1ZEdWc1pYQm9iMjU1TGtsVVpXeGxjR2h2Ym5ra1UzUjFZZ1FZMjl0TG1GdVpISnZhV1F1YVc1MFpYSnVZV3d1ZEdWc1pYQm9iMjU1TGtsUWFHOXVaVk4xWWtsdVptOGtVM1IxWWdHVkZKQlRsTkJRMVJKVDA1ZloyVjBSR1YyYVdObFNXUT1FWTI5dExtRnVaSEp2YVdRdWFXNTBaWEp1WVd3dWRHVnNaWEJvYjI1NUxrbFVaV3hsY0dodmJua0lZMjl0TG1GdVpISnZhV1F1YVc1MFpYSnVZV3d1ZEdWc1pYQm9iMjU1TGtsUWFHOXVaVk4xWWtsdVptOEVJbXRsZVNJNklpVnpJaXdpY0d4aGRHWnZjbTBpT2lKaGJtUnliMmxrSWl3aVpHbDFJam9pSlhNaUxDSndhMmNpT2lJbGN5SXNJbTF2WkdWc0lqb2lKWE1pTENKaGNIQnVZVzFsSWpvaUpYTWlMQ0poY0hCMlpYSnphVzl1SWpvaUpYTWlMQ0p6ZVhOMlpYSnphVzl1SWpvaUpYTWlMQUdJbXRsZVNJNklpVnpJaXdpY0d4aGRHWnZjbTBpT2lKaGJtUnliMmxrSWl3aVpHbDFJam9pSlhNaUxDSnRZV01pT2lJbGN5SXNJblJwWkNJNklpVnpJaXdpZFcxcFpIUWlPaUlsY3lJc0ltMWhiblZtWVdOMGRYSmxJam9pSlhNaUxDSmtaWFpwWTJVaU9pSWxjeUlzSW5OcGJTSTZJaVZ6SWl3aWNHdG5Jam9pSlhNaUxDSnRiMlJsYkNJNklpVnpJaXdpWVhCd2RtVnljMmx2YmlJNklpVnpJaXdpWVhCd2JtRnRaU0k2SWlWeklnPUlZV2xrUFFNZkhObGNtbGhiRDBRWVc1a2NtOXBaRjlwWkE=";
    private static volatile ConcurrentHashMap<String, String> l = new ConcurrentHashMap<>(8);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Integer f5333f = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Integer f5334g = 2;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Integer f5335h = 3;
    public static final Integer i = 4;
    public static final Integer j = 5;

    public static String a(String str) {
        String str2 = "";
        try {
            synchronized (l) {
                try {
                    String str3 = (l == null || !l.containsKey(str)) ? "" : l.get(str);
                    try {
                        return str3;
                    } catch (Throwable th) {
                        str2 = str3;
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            throw th;
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static void a(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                synchronized (l) {
                    if (l == null) {
                        l = new ConcurrentHashMap<>(8);
                    }
                    if (!l.containsKey(str)) {
                        l.put(str, str2);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static byte[] a(int i2) {
        if (f5330c == null) {
            f5330c = o.b(k);
        }
        byte[] bArr = new byte[4];
        System.arraycopy(f5330c, i2 * 4, bArr, 0, 4);
        int i3 = ((bArr[0] & UByte.MAX_VALUE) * 256) + (bArr[1] & UByte.MAX_VALUE);
        int i4 = ((bArr[2] & 255) * 256) + (bArr[3] & 255);
        byte[] bArr2 = new byte[i4];
        System.arraycopy(f5330c, i3, bArr2, 0, i4);
        return bArr2;
    }
}