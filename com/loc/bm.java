package com.loc;

import java.io.File;

/* JADX INFO: compiled from: FileNumUpdateStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bm extends bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4865a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4866b;

    public bm(String str, bq bqVar) {
        super(bqVar);
        this.f4865a = 30;
        this.f4866b = str;
    }

    private static int a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.list().length;
            }
            return 0;
        } catch (Throwable th) {
            ab.b(th, "fus", "gfn");
            return 0;
        }
    }

    @Override // com.loc.bq
    protected final boolean a() {
        return a(this.f4866b) >= this.f4865a;
    }
}