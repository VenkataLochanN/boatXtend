package com.loc;

import java.util.List;

/* JADX INFO: compiled from: UploadBufferBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bw extends bt {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static bw f4885b = new bw();

    private bw() {
        super(5120);
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    public static bw b() {
        return f4885b;
    }

    public final byte[] a(byte[] bArr, byte[] bArr2, List<? extends ca> list) {
        if (list == null) {
            return null;
        }
        try {
            int size = list.size();
            if (size <= 0 || bArr == null) {
                return null;
            }
            a();
            int iA = cd.a((er) this.f4883a, bArr);
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                ca caVar = list.get(i);
                iArr[i] = ci.a(this.f4883a, (byte) caVar.a(), ci.a(this.f4883a, caVar.b()));
            }
            this.f4883a.c(cd.a(this.f4883a, iA, bArr2 != null ? cd.b(this.f4883a, bArr2) : 0, cd.a(this.f4883a, iArr)));
            return this.f4883a.c();
        } catch (Throwable th) {
            dg.a(th);
            return null;
        }
    }

    public final byte[] c() {
        super.a();
        try {
            this.f4883a.c(df.a(this.f4883a, de.a(), this.f4883a.a(de.f()), this.f4883a.a(de.c()), (byte) de.m(), this.f4883a.a(de.i()), this.f4883a.a(de.h()), this.f4883a.a(a(de.g())), this.f4883a.a(a(de.j())), dd.a(de.n()), this.f4883a.a(de.l()), this.f4883a.a(de.k()), this.f4883a.a(de.d()), this.f4883a.a(de.e())));
            return this.f4883a.c();
        } catch (Exception e2) {
            dg.a(e2);
            return null;
        }
    }
}