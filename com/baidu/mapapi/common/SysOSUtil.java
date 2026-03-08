package com.baidu.mapapi.common;

import com.baidu.mapsdkplatform.comapi.util.i;

/* JADX INFO: loaded from: classes.dex */
public class SysOSUtil {
    public static float getDensity() {
        return i.x;
    }

    public static int getDensityDpi() {
        return i.l();
    }

    public static String getDeviceID() {
        return i.p();
    }

    public static String getModuleFileName() {
        return i.o();
    }

    public static String getPhoneType() {
        return i.g();
    }

    public static int getScreenSizeX() {
        return i.h();
    }

    public static int getScreenSizeY() {
        return i.j();
    }
}