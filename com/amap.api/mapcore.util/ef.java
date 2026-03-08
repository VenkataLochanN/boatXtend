package com.amap.api.mapcore.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/* JADX INFO: compiled from: NativeBufferAbstractPool.java */
/* JADX INFO: loaded from: classes.dex */
public class ef extends fd<a> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f728b;

    /* JADX INFO: compiled from: NativeBufferAbstractPool.java */
    static final class a extends fe<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ByteBuffer f729a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ShortBuffer f730b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        FloatBuffer f731c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        IntBuffer f732d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f733e;

        a() {
        }

        void a(int i) {
            if (i < 32768) {
                i = 32768;
            }
            this.f729a = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            this.f733e = i;
            this.f730b = null;
            this.f732d = null;
            this.f731c = null;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T extends com.amap.api.mapcore.util.fe<?>, T extends com.amap.api.mapcore.util.fe<T>] */
    public a a(int i) {
        a aVar = (a) this.f829a;
        if (aVar == null) {
            aVar = new a();
        } else {
            this.f829a = aVar.f830f;
            aVar.f830f = null;
        }
        if (aVar.f733e < i) {
            aVar.a(i);
        }
        this.f728b = (a) fe.a(this.f728b, aVar);
        return aVar;
    }

    public void a() {
        this.f728b = b(this.f728b);
    }

    public ShortBuffer b(int i) {
        a aVarA = a(i * 2);
        if (aVarA.f730b == null) {
            aVarA.f729a.clear();
            aVarA.f730b = aVarA.f729a.asShortBuffer();
        } else {
            aVarA.f730b.clear();
        }
        return aVarA.f730b;
    }

    public FloatBuffer c(int i) {
        a aVarA = a(i * 4);
        if (aVarA.f731c == null) {
            aVarA.f729a.clear();
            aVarA.f731c = aVarA.f729a.asFloatBuffer();
        } else {
            aVarA.f731c.clear();
        }
        aVarA.f731c.clear();
        return aVarA.f731c;
    }
}