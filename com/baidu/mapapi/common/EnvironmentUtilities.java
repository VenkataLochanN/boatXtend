package com.baidu.mapapi.common;

import android.content.Context;
import com.baidu.mapsdkplatform.comapi.util.h;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class EnvironmentUtilities {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f2727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String f2728b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static String f2729c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static int f2730d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static int f2731e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static int f2732f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static h f2733g;

    public static String getAppCachePath() {
        return f2728b;
    }

    public static String getAppSDCardPath() {
        String str = f2727a + "/BaiduMapSDKNew";
        if (str.length() != 0) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return str;
    }

    public static String getAppSecondCachePath() {
        return f2729c;
    }

    public static int getDomTmpStgMax() {
        return f2731e;
    }

    public static int getItsTmpStgMax() {
        return f2732f;
    }

    public static int getMapTmpStgMax() {
        return f2730d;
    }

    public static String getSDCardPath() {
        return f2727a;
    }

    public static void initAppDirectory(Context context) throws Throwable {
        String strC;
        if (f2733g == null) {
            f2733g = h.a();
            f2733g.a(context);
        }
        String str = f2727a;
        if (str == null || str.length() <= 0) {
            f2727a = f2733g.b().a();
            strC = f2733g.b().c();
        } else {
            strC = f2727a + File.separator + "BaiduMapSDKNew" + File.separator + "cache";
        }
        f2728b = strC;
        f2729c = f2733g.b().d();
        f2730d = 20971520;
        f2731e = 52428800;
        f2732f = 5242880;
    }

    public static void setSDCardPath(String str) {
        f2727a = str;
    }
}