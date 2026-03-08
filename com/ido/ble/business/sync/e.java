package com.ido.ble.business.sync;

import com.ido.ble.logs.LogTool;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4178a = "TimeOutTaskManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f4179b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Map<Integer, c> f4180c = new HashMap();

    public interface b {
        void onTimeOut();
    }

    private static class c extends Timer {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static int f4181f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static int f4182g = 1;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private TimerTask f4183a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private b f4184b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f4185c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f4186d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f4187e;

        class a extends TimerTask {
            a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LogTool.a(e.f4178a, "task is fire, id = " + c.this.f4185c);
                int i = c.this.f4187e;
                int i2 = c.f4182g;
                if (i == i2) {
                    return;
                }
                c.this.f4187e = i2;
                if (c.this.f4184b != null) {
                    c.this.f4184b.onTimeOut();
                }
                e.b();
            }
        }

        private c(b bVar, long j, int i) {
            this.f4187e = f4181f;
            this.f4184b = bVar;
            this.f4186d = j;
            this.f4185c = i;
        }

        public int a() {
            return this.f4187e;
        }

        public int b() {
            return this.f4185c;
        }

        public void c() {
            LogTool.a(e.f4178a, "task start, id = " + this.f4185c);
            this.f4183a = new a();
            schedule(this.f4183a, this.f4186d);
        }

        public void d() {
            this.f4187e = f4182g;
            TimerTask timerTask = this.f4183a;
            if (timerTask != null) {
                timerTask.cancel();
                this.f4183a = null;
            }
            purge();
            cancel();
            LogTool.a(e.f4178a, "task stop, id = " + this.f4185c);
        }
    }

    e() {
    }

    public static int a(b bVar, long j) {
        b();
        f4179b++;
        c cVar = new c(bVar, j, f4179b);
        f4180c.put(Integer.valueOf(f4179b), cVar);
        cVar.c();
        return f4179b;
    }

    public static boolean a(int i) {
        c cVar;
        if (!f4180c.containsKey(Integer.valueOf(i)) || (cVar = f4180c.get(Integer.valueOf(i))) == null) {
            return false;
        }
        cVar.d();
        f4180c.remove(Integer.valueOf(i));
        LogTool.a(f4178a, "task queue size is " + f4180c.size());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b() {
        HashMap map = new HashMap();
        try {
            map.putAll(f4180c);
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                c cVar = (c) ((Map.Entry) it.next()).getValue();
                if (cVar != null && cVar.a() == c.f4182g) {
                    f4180c.remove(Integer.valueOf(cVar.b()));
                }
            }
            LogTool.a(f4178a, "after purge, task queue size is " + f4180c.size());
        } catch (ConcurrentModificationException unused) {
            LogTool.b(f4178a, "purgeTask error, ignore, handle next.");
        }
    }
}