package com.loc;

/* JADX INFO: compiled from: Base64.java */
/* JADX INFO: loaded from: classes3.dex */
public class dl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f4993a = !dl.class.desiredAssertionStatus();

    /* JADX INFO: compiled from: Base64.java */
    static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f4994a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4995b;

        a() {
        }
    }

    /* JADX INFO: compiled from: Base64.java */
    static class b extends a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final int[] f4996c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final int[] f4997d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f4998e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f4999f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final int[] f5000g;

        public b(byte[] bArr) {
            this.f4994a = bArr;
            this.f5000g = f4996c;
            this.f4998e = 0;
            this.f4999f = 0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:66:0x0101, code lost:
        
            if (r5 != 4) goto L70;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean a(byte[] r18, int r19) {
            /*
                Method dump skipped, instruction units count: 288
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.dl.b.a(byte[], int):boolean");
        }
    }

    private dl() {
    }

    public static byte[] a(byte[] bArr) {
        int length = bArr.length;
        b bVar = new b(new byte[(length * 3) / 4]);
        if (!bVar.a(bArr, length)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (bVar.f4995b == bVar.f4994a.length) {
            return bVar.f4994a;
        }
        byte[] bArr2 = new byte[bVar.f4995b];
        System.arraycopy(bVar.f4994a, 0, bArr2, 0, bVar.f4995b);
        return bArr2;
    }
}