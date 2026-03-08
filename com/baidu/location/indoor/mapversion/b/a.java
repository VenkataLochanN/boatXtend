package com.baidu.location.indoor.mapversion.b;

import com.baidu.location.BDLocation;
import com.baidu.location.indoor.mapversion.IndoorJni;
import com.baidu.location.indoor.mapversion.c.a;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Lock f2646a = new ReentrantLock();

    public static boolean a() {
        return IndoorJni.f2603a;
    }

    public static synchronized boolean a(String str) {
        Lock lock;
        if (!a()) {
            return false;
        }
        a.d dVarB = com.baidu.location.indoor.mapversion.c.a.a().b(str);
        double[][] dArrC = com.baidu.location.indoor.mapversion.c.a.a().c(str);
        if (dVarB == null) {
            return false;
        }
        dVarB.a(CoordinateType.GCJ02);
        short[][] sArr = dVarB.f2672g;
        double d2 = dVarB.a().f2655a;
        double d3 = dVarB.a().f2656b;
        a.d dVarC = com.baidu.location.indoor.mapversion.c.a.a().c();
        if (dVarC == null) {
            return false;
        }
        double dA = dVarC.a(-dVarB.a().f2658d);
        double dB = dVarC.b(-dVarB.a().f2660f);
        f2646a.lock();
        try {
            try {
                IndoorJni.setPfRdnt(str, sArr, d2, d3, (int) dVarB.f2671f.f2661g, (int) dVarB.f2671f.f2662h, dA, dB);
                IndoorJni.setPfGeoMap(dArrC, str, (int) dVarB.f2671f.f2661g, (int) dVarB.f2671f.f2662h);
                lock = f2646a;
            } catch (Exception e2) {
                e2.printStackTrace();
                lock = f2646a;
            }
            lock.unlock();
            return true;
        } finally {
        }
    }

    public static synchronized double[] a(double d2, double d3, double d4, double d5, double d6) {
        Lock lock;
        if (!a()) {
            return null;
        }
        com.baidu.location.indoor.mapversion.c.a.a().a(d2, d3);
        a.d dVarC = com.baidu.location.indoor.mapversion.c.a.a().c();
        double dA = dVarC.a(d2);
        double dB = dVarC.b(d3);
        double[] pfGps = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
        f2646a.lock();
        try {
            try {
                pfGps = IndoorJni.setPfGps(dA, dB, d4, d5, d6, System.currentTimeMillis());
                lock = f2646a;
            } catch (Throwable th) {
                f2646a.unlock();
                throw th;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            lock = f2646a;
        }
        lock.unlock();
        if (pfGps[0] == 0.0d) {
            double dC = dVarC.c(pfGps[1]);
            double d7 = dVarC.d(pfGps[2]);
            pfGps[1] = dC;
            pfGps[2] = d7;
        }
        return pfGps;
    }

    public static synchronized double[] a(BDLocation bDLocation) {
        Lock lock;
        if (!a()) {
            return null;
        }
        a.d dVarC = com.baidu.location.indoor.mapversion.c.a.a().c();
        double[] pfWf = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
        if (dVarC != null) {
            double dA = dVarC.a(bDLocation.getLongitude());
            double dB = dVarC.b(bDLocation.getLatitude());
            f2646a.lock();
            try {
                try {
                    pfWf = IndoorJni.setPfWf(dA, dB, 8.0d, System.currentTimeMillis());
                    lock = f2646a;
                } catch (Throwable th) {
                    f2646a.unlock();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                lock = f2646a;
            }
            lock.unlock();
            if (pfWf[0] == 0.0d) {
                double dC = dVarC.c(pfWf[1]);
                double d2 = dVarC.d(pfWf[2]);
                pfWf[1] = dC;
                pfWf[2] = d2;
            }
        }
        return pfWf;
    }

    public static synchronized double[] a(String str, double d2, double d3, double d4) {
        Lock lock;
        if (!a()) {
            return null;
        }
        a.d dVarC = com.baidu.location.indoor.mapversion.c.a.a().c();
        double[] pfDr = {-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d};
        if (dVarC != null) {
            f2646a.lock();
            try {
                try {
                    pfDr = IndoorJni.setPfDr(d3, d4, System.currentTimeMillis());
                    lock = f2646a;
                } catch (Throwable th) {
                    f2646a.unlock();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                lock = f2646a;
            }
            lock.unlock();
            if (pfDr[0] == 0.0d) {
                double dC = dVarC.c(pfDr[1]);
                double d5 = dVarC.d(pfDr[2]);
                pfDr[1] = dC;
                pfDr[2] = d5;
            }
        }
        return pfDr;
    }

    public static void b() {
        if (a()) {
            f2646a.lock();
            try {
                try {
                    IndoorJni.initPf();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                f2646a.unlock();
            }
        }
    }

    public static void c() {
        if (a()) {
            f2646a.lock();
            try {
                try {
                    IndoorJni.resetPf();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                f2646a.unlock();
            }
        }
    }
}