package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: RectPacker.java */
/* JADX INFO: loaded from: classes.dex */
public class fc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    b f813a;

    /* JADX INFO: compiled from: RectPacker.java */
    enum a {
        FAIL,
        PERFECT,
        FIT
    }

    public fc(int i, int i2) {
        this.f813a = new b(new c(0, 0, i, i2));
    }

    public c a(int i, int i2, String str) {
        b bVarA = this.f813a.a(i, i2, str);
        if (bVarA != null) {
            return new c(bVarA.f821b.f825a, bVarA.f821b.f826b, bVarA.f821b.f827c, bVarA.f821b.f828d);
        }
        return null;
    }

    public boolean a(String str) {
        return this.f813a.a(str);
    }

    public int a() {
        return this.f813a.f821b.f827c;
    }

    public int b() {
        return this.f813a.f821b.f828d;
    }

    /* JADX INFO: compiled from: RectPacker.java */
    class b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        static final /* synthetic */ boolean f819e = !fc.class.desiredAssertionStatus();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f820a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c f821b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        b f822c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        b f823d = null;

        b(c cVar) {
            this.f821b = cVar;
        }

        b a(int i, int i2, String str) {
            if (!a()) {
                b bVarA = this.f822c.a(i, i2, str);
                return bVarA == null ? this.f823d.a(i, i2, str) : bVarA;
            }
            if (this.f820a != null) {
                return null;
            }
            int i3 = AnonymousClass1.f814a[b(i, i2).ordinal()];
            if (i3 == 1) {
                return null;
            }
            if (i3 == 2) {
                this.f820a = str;
                return this;
            }
            if (i3 == 3) {
                a(i, i2);
            }
            return this.f822c.a(i, i2, str);
        }

        boolean a() {
            return this.f822c == null;
        }

        boolean b() {
            return (this.f820a == null && a()) ? false : true;
        }

        boolean a(String str) {
            if (a()) {
                if (!str.equals(this.f820a)) {
                    return false;
                }
                this.f820a = null;
                return true;
            }
            boolean zA = this.f822c.a(str);
            if (!zA) {
                zA = this.f823d.a(str);
            }
            if (zA && !this.f822c.b() && !this.f823d.b()) {
                this.f822c = null;
                this.f823d = null;
            }
            return zA;
        }

        void a(int i, int i2) {
            c cVar;
            c cVar2;
            int i3 = this.f821b.f827c - i;
            int i4 = this.f821b.f828d - i2;
            if (!f819e && i3 < 0) {
                throw new AssertionError();
            }
            if (!f819e && i4 < 0) {
                throw new AssertionError();
            }
            if (i3 > i4) {
                c cVar3 = new c(this.f821b.f825a, this.f821b.f826b, i, this.f821b.f828d);
                cVar2 = new c(cVar3.f825a + i, this.f821b.f826b, this.f821b.f827c - i, this.f821b.f828d);
                cVar = cVar3;
            } else {
                cVar = new c(this.f821b.f825a, this.f821b.f826b, this.f821b.f827c, i2);
                cVar2 = new c(this.f821b.f825a, cVar.f826b + i2, this.f821b.f827c, this.f821b.f828d - i2);
            }
            this.f822c = fc.this.new b(cVar);
            this.f823d = fc.this.new b(cVar2);
        }

        a b(int i, int i2) {
            if (i <= this.f821b.f827c && i2 <= this.f821b.f828d) {
                if (i == this.f821b.f827c && i2 == this.f821b.f828d) {
                    return a.PERFECT;
                }
                return a.FIT;
            }
            return a.FAIL;
        }
    }

    /* JADX INFO: renamed from: com.amap.api.mapcore.util.fc$1, reason: invalid class name */
    /* JADX INFO: compiled from: RectPacker.java */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f814a = new int[a.values().length];

        static {
            try {
                f814a[a.FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f814a[a.PERFECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f814a[a.FIT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: RectPacker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f825a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f826b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f827c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f828d;

        c(int i, int i2, int i3, int i4) {
            this.f825a = i;
            this.f826b = i2;
            this.f827c = i3;
            this.f828d = i4;
        }

        public String toString() {
            return "[ x: " + this.f825a + ", y: " + this.f826b + ", w: " + this.f827c + ", h: " + this.f828d + " ]";
        }
    }
}