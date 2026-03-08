package com.baidu.location.indoor.mapversion;

import android.os.Build;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Lock f2604a = new ReentrantLock();

    public static void a() {
        f2604a.lock();
        try {
            try {
                IndoorJni.stopPdr();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            f2604a.unlock();
        }
    }

    public static synchronized void a(int i, float[] fArr, long j) {
        Lock lock;
        f2604a.lock();
        try {
            try {
                if (b() && fArr != null && fArr.length >= 3) {
                    IndoorJni.phs(i, fArr[0], fArr[1], fArr[2], j);
                }
                lock = f2604a;
            } catch (Exception e2) {
                e2.printStackTrace();
                lock = f2604a;
            }
            lock.unlock();
        } catch (Throwable th) {
            f2604a.unlock();
            throw th;
        }
    }

    public static boolean b() {
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        return IndoorJni.f2603a;
    }

    public static float[] c() {
        f2604a.lock();
        try {
            try {
                return IndoorJni.pgo();
            } catch (Exception e2) {
                e2.printStackTrace();
                f2604a.unlock();
                return null;
            }
        } finally {
            f2604a.unlock();
        }
    }
}