package com.ido.ble.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile long f4223a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4224b = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4225c = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4226d = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static synchronized String a() {
        return c(new Date(c()));
    }

    public static String a(Date date) {
        return b().format(date);
    }

    public static Date a(String str) {
        try {
            return d().parse(str);
        } catch (ParseException unused) {
            return b().parse(str);
        }
    }

    public static synchronized void a(long j) {
        f4223a = j - System.currentTimeMillis();
    }

    public static boolean a(int i) {
        return Calendar.getInstance().get(5) == i;
    }

    public static String b(Date date) {
        return d().format(date);
    }

    private static DateFormat b() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(f4226d, Locale.US);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat;
    }

    public static Date b(String str) {
        return e().parse(str);
    }

    public static long c() {
        return System.currentTimeMillis() + f4223a;
    }

    public static String c(Date date) {
        return e().format(date);
    }

    private static DateFormat d() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(f4225c, Locale.US);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat;
    }

    private static DateFormat e() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(f4224b, Locale.US);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return simpleDateFormat;
    }
}