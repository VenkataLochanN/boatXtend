package com.amap.api.mapcore.util;

import java.io.File;

/* JADX INFO: compiled from: FileNumUpdateStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jl extends jp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1468b;

    public jl(int i, String str, jp jpVar) {
        super(jpVar);
        this.f1467a = i;
        this.f1468b = str;
    }

    @Override // com.amap.api.mapcore.util.jp
    protected boolean c() {
        return a(this.f1468b) >= this.f1467a;
    }

    public int a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.list().length;
            }
            return 0;
        } catch (Throwable th) {
            hn.c(th, "fus", "gfn");
            return 0;
        }
    }
}