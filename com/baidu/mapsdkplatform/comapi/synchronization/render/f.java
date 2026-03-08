package com.baidu.mapsdkplatform.comapi.synchronization.render;

import android.os.HandlerThread;
import android.os.Message;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.synchronization.DisplayOptions;
import com.baidu.mapapi.synchronization.RoleOptions;
import com.baidu.mapsdkplatform.comapi.synchronization.data.SyncResponseResult;
import com.baidu.mapsdkplatform.comapi.synchronization.data.i;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3792a = f.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static HandlerThread f3793d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3794b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3795c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.render.b f3796e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.data.g f3797f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private i f3798g;

    private class a implements i {
        private a() {
        }

        @Override // com.baidu.mapsdkplatform.comapi.synchronization.data.i
        public void a() {
            f.this.j();
            f fVar = f.this;
            fVar.e(fVar.f3794b);
        }

        @Override // com.baidu.mapsdkplatform.comapi.synchronization.data.i
        public void b() {
            f.this.i();
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final f f3800a = new f();
    }

    private f() {
        this.f3794b = 0;
        this.f3795c = 5;
    }

    static f a() {
        return b.f3800a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(f3792a, "The order state is: " + i);
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            f(i);
            return;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(f3792a, "Undefined order state: " + i);
    }

    private void f(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3792a, "SyncRenderHandler is null");
            return;
        }
        Message messageObtainMessage = bVar.obtainMessage();
        messageObtainMessage.what = i;
        this.f3796e.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        RoleOptions roleOptionsE = this.f3797f.e();
        DisplayOptions displayOptionsF = this.f3797f.f();
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3792a, "SyncRenderHandler is null");
        } else {
            bVar.a(roleOptionsE, displayOptionsF, (SyncResponseResult) null, this.f3795c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        SyncResponseResult syncResponseResultTake;
        RoleOptions roleOptionsE = this.f3797f.e();
        DisplayOptions displayOptionsF = this.f3797f.f();
        try {
            syncResponseResultTake = this.f3797f.g().take();
        } catch (InterruptedException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3792a, "Get result when InterruptedException happened.", e2);
            syncResponseResultTake = null;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3792a, "SyncRenderHandler is null");
        } else {
            bVar.a(roleOptionsE, displayOptionsF, syncResponseResultTake, this.f3795c);
        }
    }

    void a(int i) {
        this.f3794b = i;
        e(this.f3794b);
    }

    public void a(int i, int i2, int i3, int i4) {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.a(i, i2, i3, i4);
        }
    }

    public void a(BaiduMap baiduMap) {
        this.f3797f = com.baidu.mapsdkplatform.comapi.synchronization.data.g.a();
        this.f3798g = new a();
        this.f3797f.a(this.f3798g);
        f3793d = new HandlerThread("SynchronizationRenderStrategy");
        f3793d.start();
        this.f3796e = new com.baidu.mapsdkplatform.comapi.synchronization.render.b(f3793d.getLooper());
        this.f3796e.a(baiduMap, this.f3797f.e(), this.f3797f.f());
    }

    void a(d dVar) {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.a(dVar);
        }
    }

    Marker b() {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            return bVar.a();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3792a, "SyncRenderHandler created failed");
        return null;
    }

    void b(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.a(i);
        }
    }

    Marker c() {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            return bVar.b();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3792a, "SyncRenderHandler created failed");
        return null;
    }

    void c(int i) {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.b(i);
        }
    }

    Marker d() {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            return bVar.c();
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3792a, "SyncRenderHandler created failed");
        return null;
    }

    void d(int i) {
        this.f3795c = i;
    }

    public void e() {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.d();
        }
    }

    public void f() {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.e();
        }
    }

    public void g() {
        com.baidu.mapsdkplatform.comapi.synchronization.data.g gVar = this.f3797f;
        if (gVar != null) {
            gVar.c();
        }
        if (this.f3798g != null) {
            this.f3798g = null;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.data.g gVar2 = this.f3797f;
        if (gVar2 != null) {
            gVar2.h();
            this.f3797f = null;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.f();
            this.f3796e.removeCallbacksAndMessages(null);
            this.f3796e = null;
        }
        HandlerThread handlerThread = f3793d;
        if (handlerThread != null) {
            handlerThread.quit();
            f3793d = null;
        }
    }

    public void h() {
        com.baidu.mapsdkplatform.comapi.synchronization.render.b bVar = this.f3796e;
        if (bVar != null) {
            bVar.g();
        }
    }
}