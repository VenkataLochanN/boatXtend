package com.baidu.mapapi;

import com.baidu.mapsdkplatform.comapi.util.d;

/* JADX INFO: loaded from: classes.dex */
public class PermissionUtils {

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final PermissionUtils f2708a = new PermissionUtils();
    }

    private PermissionUtils() {
    }

    public static PermissionUtils getInstance() {
        return a.f2708a;
    }

    public boolean isIndoorNaviAuthorized() {
        return d.a().b();
    }
}