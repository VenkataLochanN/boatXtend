package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.gs;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.ido.alexa.AlexaCustomSkillConstant;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public final class kv {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f1612b = {"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore", "com.amap.api.3dmap.admic", "com.amap.api.trace", "com.amap.api.trace.core"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f1613c = {"com.amap.api.mapcore2d", "com.amap.api.maps2d"};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f1614d = {"com.amap.trace"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static gs f1611a = null;

    public static gs a() throws gh {
        Class<?> cls;
        Class<?> cls2;
        gs.a aVarA;
        gs gsVar = f1611a;
        if (gsVar != null) {
            return gsVar;
        }
        try {
            cls = Class.forName("com.amap.api.maps.MapsInitializer");
        } catch (Throwable unused) {
            cls = null;
        }
        try {
            if (cls != null) {
                String str = (String) ki.a(cls, "getVersion", (Object[]) null, (Class<?>[]) null);
                aVarA = new gs.a("3dmap", str, "AMAP_SDK_Android_Map_" + str).a(f1612b);
            } else {
                cls = Class.forName("com.amap.api.maps2d.MapsInitializer");
                String str2 = (String) ki.a(cls, "getVersion", (Object[]) null, (Class<?>[]) null);
                aVarA = new gs.a("2dmap", str2, "AMAP_SDK_Android_2DMap_" + str2).a(f1613c);
            }
            f1611a = aVarA.a();
        } catch (Throwable unused2) {
        }
        if (cls == null) {
            try {
                cls2 = Class.forName("com.amap.trace.AMapTraceClient");
            } catch (Throwable unused3) {
                cls2 = null;
            }
            if (cls2 != null) {
                try {
                    String str3 = (String) ki.a(cls2, "getVersion", (Object[]) null, (Class<?>[]) null);
                    f1611a = new gs.a("trace", str3, "AMAP_TRACE_Android_" + str3).a(f1614d).a();
                } catch (Throwable unused4) {
                }
            }
        }
        return f1611a;
    }

    public static boolean a(kr krVar) {
        if (krVar == null || krVar.d().equals(AlexaCustomSkillConstant.EVENT_BRIGHTNESS) || krVar.d().equals("5") || krVar.d().equals("6")) {
            return false;
        }
        return a((Inner_3dMap_location) krVar);
    }

    public static boolean a(Inner_3dMap_location inner_3dMap_location) {
        double longitude = inner_3dMap_location.getLongitude();
        double latitude = inner_3dMap_location.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }
}