package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.fe;

/* JADX INFO: compiled from: AbstractPool.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class fd<T extends fe<?>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected T f829a;

    protected boolean a(T t) {
        return true;
    }

    public T b(T t) {
        if (t == null) {
            return null;
        }
        while (t != null) {
            Object obj = t.f830f;
            a(t);
            t.f830f = this.f829a;
            this.f829a = t;
            t = (T) obj;
        }
        return null;
    }
}