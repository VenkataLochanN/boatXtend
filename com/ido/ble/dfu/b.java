package com.ido.ble.dfu;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.dfu.BleDFUState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b f4248c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<BleDFUState.IListener> f4249a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f4250b = new Handler(Looper.getMainLooper());

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onPrepare();
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.dfu.b$b, reason: collision with other inner class name */
    class RunnableC0063b implements Runnable {
        RunnableC0063b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onDeviceInDFUMode();
            }
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4253a;

        c(int i) {
            this.f4253a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onProgress(this.f4253a);
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onSuccess();
            }
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onSuccessAndNeedToPromptUser();
            }
        }
    }

    class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BleDFUState.FailReason f4257a;

        f(BleDFUState.FailReason failReason) {
            this.f4257a = failReason;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onFailed(this.f4257a);
            }
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onCanceled();
            }
        }
    }

    class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4260a;

        h(int i) {
            this.f4260a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                ((BleDFUState.IListener) it.next()).onRetry(this.f4260a);
            }
        }
    }

    private b() {
    }

    public static b f() {
        if (f4248c == null) {
            f4248c = new b();
        }
        return f4248c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<BleDFUState.IListener> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4249a);
        return arrayList;
    }

    public void a() {
        a(new g());
    }

    public void a(int i) {
        a(new c(i));
    }

    public void a(BleDFUState.FailReason failReason) {
        a(new f(failReason));
    }

    public void a(BleDFUState.IListener iListener) {
        this.f4249a.add(iListener);
    }

    void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.f4250b.post(runnable);
        }
    }

    public void b() {
        a(new RunnableC0063b());
    }

    public void b(int i) {
        a(new h(i));
    }

    public void b(BleDFUState.IListener iListener) {
        this.f4249a.remove(iListener);
    }

    public void c() {
        a(new a());
    }

    public void d() {
        a(new d());
    }

    public void e() {
        a(new e());
    }
}