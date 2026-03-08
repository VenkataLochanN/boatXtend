package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.Vector;

/* JADX INFO: compiled from: LogMemCacher.java */
/* JADX INFO: loaded from: classes.dex */
public class hh {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f1220b = 100;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f1221d = 10000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Vector<he> f1222a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1223c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1224e;

    public hh(int i) {
        this.f1223c = f1220b;
        this.f1224e = 0;
        this.f1223c = i;
        this.f1222a = new Vector<>();
    }

    public hh() {
        this.f1223c = f1220b;
        this.f1224e = 0;
        this.f1222a = new Vector<>();
    }

    public synchronized boolean a(String str) {
        if (str == null) {
            return false;
        }
        if (this.f1222a.size() >= this.f1223c) {
            return true;
        }
        return this.f1224e + str.getBytes().length > f1221d;
    }

    public Vector<he> a() {
        return this.f1222a;
    }

    public synchronized void b() {
        this.f1222a.clear();
        this.f1224e = 0;
    }

    public synchronized void a(he heVar) {
        if (heVar != null) {
            if (!TextUtils.isEmpty(heVar.b())) {
                this.f1222a.add(heVar);
                this.f1224e += heVar.b().getBytes().length;
            }
        }
    }
}