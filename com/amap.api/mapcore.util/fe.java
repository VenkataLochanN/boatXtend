package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.fe;

/* JADX INFO: compiled from: Inlist.java */
/* JADX INFO: loaded from: classes.dex */
public class fe<T extends fe<T>> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public T f830f;

    public static <T extends fe<?>> T a(T t, T t2) {
        if (t2.f830f != null) {
            throw new IllegalArgumentException("'item' is a list");
        }
        t2.f830f = t;
        return t2;
    }
}