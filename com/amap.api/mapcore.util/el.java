package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;

/* JADX INFO: compiled from: ResourcesUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class el {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f747a = new File("/system/framework/amap.jar").exists();

    public static AssetManager a(Context context) {
        if (context == null) {
            return null;
        }
        AssetManager assets = context.getAssets();
        if (f747a) {
            try {
                assets.getClass().getDeclaredMethod("addAssetPath", String.class).invoke(assets, "/system/framework/amap.jar");
            } catch (Throwable th) {
                hn.c(th, "ResourcesUtil", "getSelfAssets");
            }
        }
        return assets;
    }

    public static int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}