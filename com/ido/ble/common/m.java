package com.ido.ble.common;

import android.util.Log;
import com.ido.ble.logs.LogTool;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4232a = "TimeOutTaskManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f4233b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Map<Integer, c> f4234c = new HashMap();

    public interface b {
        void onTimeOut();
    }

    private static class c extends Timer {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static int f4235f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static int f4236g = 1;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private TimerTask f4237a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private b f4238b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f4239c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f4240d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f4241e;

        class a extends TimerTask {
            a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Log.d(m.f4232a, "task is fire, id = " + c.this.f4239c);
                int i = c.this.f4241e;
                int i2 = c.f4236g;
                if (i == i2) {
                    return;
                }
                c.this.f4241e = i2;
                if (c.this.f4238b != null) {
                    c.this.f4238b.onTimeOut();
                }
                m.b();
            }
        }

        private c(b bVar, long j, int i) {
            this.f4241e = f4235f;
            this.f4238b = bVar;
            this.f4240d = j;
            this.f4239c = i;
        }

        public int a() {
            return this.f4241e;
        }

        public int b() {
            return this.f4239c;
        }

        public void c() {
            Log.d(m.f4232a, "task start, id = " + this.f4239c);
            this.f4237a = new a();
            schedule(this.f4237a, this.f4240d);
        }

        public void d() {
            this.f4241e = f4236g;
            TimerTask timerTask = this.f4237a;
            if (timerTask != null) {
                timerTask.cancel();
                this.f4237a = null;
            }
            purge();
            cancel();
            Log.d(m.f4232a, "task stop, id = " + this.f4239c);
        }
    }

    public static int a(b bVar, long j) {
        b();
        f4233b++;
        c cVar = new c(bVar, j, f4233b);
        f4234c.put(Integer.valueOf(f4233b), cVar);
        cVar.c();
        return f4233b;
    }

    public static boolean a(int i) {
        c cVar;
        if (!f4234c.containsKey(Integer.valueOf(i)) || (cVar = f4234c.get(Integer.valueOf(i))) == null) {
            return false;
        }
        cVar.d();
        f4234c.remove(Integer.valueOf(i));
        Log.d(f4232a, "task queue size is " + f4234c.size());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b() {
        HashMap map = new HashMap();
        try {
            map.putAll(f4234c);
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                c cVar = (c) ((Map.Entry) it.next()).getValue();
                if (cVar != null && cVar.a() == c.f4236g) {
                    f4234c.remove(Integer.valueOf(cVar.b()));
                }
            }
            Log.d(f4232a, "after purge, task queue size is " + f4234c.size());
        } catch (ConcurrentModificationException unused) {
            LogTool.b(f4232a, "purgeTask error, ignore, handle next.");
        }
    }
}