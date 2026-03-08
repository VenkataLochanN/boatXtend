package com.loc;

import android.os.SystemClock;
import com.loc.bx;
import java.util.List;

/* JADX INFO: compiled from: FpsCollector.java */
/* JADX INFO: loaded from: classes3.dex */
public final class by {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile by f4898g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static Object f4899h = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f4902c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private dc f4903d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private dc f4905f = new dc();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private bx f4900a = new bx();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private bz f4901b = new bz();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private bu f4904e = new bu();

    /* JADX INFO: compiled from: FpsCollector.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public dc f4906a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<dd> f4907b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f4908c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f4909d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4910e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f4911f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public byte f4912g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f4913h;
        public List<cw> i;
        public boolean j;
    }

    private by() {
    }

    public static by a() {
        if (f4898g == null) {
            synchronized (f4899h) {
                if (f4898g == null) {
                    f4898g = new by();
                }
            }
        }
        return f4898g;
    }

    public final ca a(a aVar) {
        ca caVar = null;
        if (aVar == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f4903d == null || aVar.f4906a.a(this.f4903d) >= 10.0d) {
            bx.a aVarA = this.f4900a.a(aVar.f4906a, aVar.j, aVar.f4912g, aVar.f4913h, aVar.i);
            List<dd> listA = this.f4901b.a(aVar.f4906a, aVar.f4907b, aVar.f4910e, aVar.f4909d, jCurrentTimeMillis);
            if (aVarA != null || listA != null) {
                dc dcVar = this.f4905f;
                dc dcVar2 = aVar.f4906a;
                long j = aVar.f4911f;
                dcVar.k = j;
                dcVar.f4961b = j;
                dcVar.f4962c = jCurrentTimeMillis;
                dcVar.f4964e = dcVar2.f4964e;
                dcVar.f4963d = dcVar2.f4963d;
                dcVar.f4965f = dcVar2.f4965f;
                dcVar.i = dcVar2.i;
                dcVar.f4966g = dcVar2.f4966g;
                dcVar.f4967h = dcVar2.f4967h;
                caVar = new ca(0, this.f4904e.a(this.f4905f, aVarA, aVar.f4908c, listA));
            }
            this.f4903d = aVar.f4906a;
            this.f4902c = jElapsedRealtime;
        }
        return caVar;
    }
}