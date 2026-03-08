package com.baidu.mapapi;

/* JADX INFO: loaded from: classes.dex */
public class OpenLogUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ModuleName f2707a;

    public static void setModuleLogEnable(ModuleName moduleName, boolean z) {
        f2707a = moduleName;
        com.baidu.mapsdkplatform.comjni.tools.a.a(z, f2707a.ordinal());
    }
}