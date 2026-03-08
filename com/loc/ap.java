package com.loc;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: StrictLineReader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ap implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f4764a = Charset.forName("US-ASCII");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InputStream f4765b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Charset f4766c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private byte[] f4767d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4768e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4769f;

    public ap(InputStream inputStream, Charset charset) {
        this(inputStream, charset, (byte) 0);
    }

    private ap(InputStream inputStream, Charset charset, byte b2) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        }
        if (!charset.equals(f4764a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f4765b = inputStream;
        this.f4766c = charset;
        this.f4767d = new byte[8192];
    }

    private void b() throws IOException {
        InputStream inputStream = this.f4765b;
        byte[] bArr = this.f4767d;
        int i = inputStream.read(bArr, 0, bArr.length);
        if (i == -1) {
            throw new EOFException();
        }
        this.f4768e = 0;
        this.f4769f = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a() throws java.io.IOException {
        /*
            r7 = this;
            java.io.InputStream r0 = r7.f4765b
            monitor-enter(r0)
            byte[] r1 = r7.f4767d     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L8f
            int r1 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r2 = r7.f4769f     // Catch: java.lang.Throwable -> L97
            if (r1 < r2) goto L10
            r7.b()     // Catch: java.lang.Throwable -> L97
        L10:
            int r1 = r7.f4768e     // Catch: java.lang.Throwable -> L97
        L12:
            int r2 = r7.f4769f     // Catch: java.lang.Throwable -> L97
            r3 = 10
            if (r1 == r2) goto L49
            byte[] r2 = r7.f4767d     // Catch: java.lang.Throwable -> L97
            r2 = r2[r1]     // Catch: java.lang.Throwable -> L97
            if (r2 != r3) goto L46
            int r2 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            if (r1 == r2) goto L2d
            byte[] r2 = r7.f4767d     // Catch: java.lang.Throwable -> L97
            int r3 = r1 + (-1)
            r2 = r2[r3]     // Catch: java.lang.Throwable -> L97
            r4 = 13
            if (r2 != r4) goto L2d
            goto L2e
        L2d:
            r3 = r1
        L2e:
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Throwable -> L97
            byte[] r4 = r7.f4767d     // Catch: java.lang.Throwable -> L97
            int r5 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r6 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r3 = r3 - r6
            java.nio.charset.Charset r6 = r7.f4766c     // Catch: java.lang.Throwable -> L97
            java.lang.String r6 = r6.name()     // Catch: java.lang.Throwable -> L97
            r2.<init>(r4, r5, r3, r6)     // Catch: java.lang.Throwable -> L97
            int r1 = r1 + 1
            r7.f4768e = r1     // Catch: java.lang.Throwable -> L97
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L97
            return r2
        L46:
            int r1 = r1 + 1
            goto L12
        L49:
            com.loc.ap$1 r1 = new com.loc.ap$1     // Catch: java.lang.Throwable -> L97
            int r2 = r7.f4769f     // Catch: java.lang.Throwable -> L97
            int r4 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r2 = r2 - r4
            int r2 = r2 + 80
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L97
        L55:
            byte[] r2 = r7.f4767d     // Catch: java.lang.Throwable -> L97
            int r4 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r5 = r7.f4769f     // Catch: java.lang.Throwable -> L97
            int r6 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r5 = r5 - r6
            r1.write(r2, r4, r5)     // Catch: java.lang.Throwable -> L97
            r2 = -1
            r7.f4769f = r2     // Catch: java.lang.Throwable -> L97
            r7.b()     // Catch: java.lang.Throwable -> L97
            int r2 = r7.f4768e     // Catch: java.lang.Throwable -> L97
        L69:
            int r4 = r7.f4769f     // Catch: java.lang.Throwable -> L97
            if (r2 == r4) goto L55
            byte[] r4 = r7.f4767d     // Catch: java.lang.Throwable -> L97
            r4 = r4[r2]     // Catch: java.lang.Throwable -> L97
            if (r4 != r3) goto L8c
            int r3 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            if (r2 == r3) goto L82
            byte[] r3 = r7.f4767d     // Catch: java.lang.Throwable -> L97
            int r4 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r5 = r7.f4768e     // Catch: java.lang.Throwable -> L97
            int r5 = r2 - r5
            r1.write(r3, r4, r5)     // Catch: java.lang.Throwable -> L97
        L82:
            int r2 = r2 + 1
            r7.f4768e = r2     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L97
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L97
            return r1
        L8c:
            int r2 = r2 + 1
            goto L69
        L8f:
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L97
            java.lang.String r2 = "LineReader is closed"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L97
            throw r1     // Catch: java.lang.Throwable -> L97
        L97:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L97
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ap.a():java.lang.String");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this.f4765b) {
            if (this.f4767d != null) {
                this.f4767d = null;
                this.f4765b.close();
            }
        }
    }
}