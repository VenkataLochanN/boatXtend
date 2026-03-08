package com.baidu.location.indoor.mapversion.c;

import android.content.Context;
import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f2676a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2681f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f2677b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2678c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2679d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2680e = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f2682g = "";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private double f2683h = 7.0d;
    private Map<String, b> i = Collections.synchronizedMap(new HashMap());

    public interface a {
    }

    public static class b implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2684a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2685b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public double f2686c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double f2687d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public double f2688e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public double f2689f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f2690g;
    }

    private c(Context context) {
        this.f2681f = "slr";
        this.f2681f = new File(context.getCacheDir(), this.f2681f).getAbsolutePath();
    }

    public static synchronized c a() {
        return f2676a;
    }

    public static c a(Context context) {
        if (f2676a == null) {
            f2676a = new c(context);
        }
        return f2676a;
    }

    public boolean b() {
        return this.f2680e;
    }

    public boolean c() {
        return this.f2682g.equals(DebugKt.DEBUG_PROPERTY_VALUE_ON);
    }

    public Map<String, b> d() {
        return this.i;
    }
}