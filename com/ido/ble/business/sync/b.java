package com.ido.ble.business.sync;

import com.ido.ble.BLEManager;
import com.ido.ble.business.sync.a;
import com.ido.ble.business.sync.e;
import com.ido.ble.business.sync.f.f;
import com.ido.ble.business.sync.f.g;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    private static final long j = 300000;
    private static b k = null;
    private static final int l = 10;
    private static final int m = 35;
    private static final int n = 45;
    private static final int o = 5;
    private static final int p = 5;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ActivityDataCount f4165a;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private SyncPara f4172h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LinkedList<f> f4166b = new LinkedList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LinkedList<f> f4167c = new LinkedList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4168d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4169e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4170f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4171g = 0;
    private int i = -1;

    class a implements e.b {
        a() {
        }

        @Override // com.ido.ble.business.sync.e.b
        public void onTimeOut() {
            b.this.m();
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.business.sync.b$b, reason: collision with other inner class name */
    class C0062b implements a.c {
        C0062b() {
        }

        @Override // com.ido.ble.business.sync.a.c
        public void a(ActivityDataCount activityDataCount) {
            if (!b.this.f4168d) {
                LogTool.b(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] get activity count ok,but task is quit.");
                return;
            }
            b.this.f4165a = activityDataCount;
            if (d.a()) {
                b.this.j();
            } else {
                b.this.i();
            }
        }

        @Override // com.ido.ble.business.sync.a.c
        public void onFailed() {
            b.this.k();
        }
    }

    private class c implements com.ido.ble.business.sync.f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private f f4175a;

        public c(f fVar) {
            this.f4175a = fVar;
        }

        @Override // com.ido.ble.business.sync.f.a
        public void onFailed() {
            b.c(b.this);
            b bVar = b.this;
            bVar.f4169e = bVar.f4170f;
            b.this.c();
        }

        @Override // com.ido.ble.business.sync.f.a
        public void onProgress(int i) {
            int iA = (int) (((double) (i * this.f4175a.a())) / 100.0d);
            if (b.this.f4172h.iSyncProgressListener != null) {
                b.this.f4172h.iSyncProgressListener.onProgress(b.this.f4169e + iA);
            }
            b bVar = b.this;
            bVar.f4170f = bVar.f4169e + iA;
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] totalProgress = " + (b.this.f4169e + iA));
        }

        @Override // com.ido.ble.business.sync.f.a
        public void onSuccess() {
            b.this.f4169e += this.f4175a.a();
            b bVar = b.this;
            bVar.f4170f = bVar.f4169e;
            b.this.c();
        }
    }

    static /* synthetic */ int c(b bVar) {
        int i = bVar.f4171g;
        bVar.f4171g = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.f4166b.size() == 0) {
            if (this.f4171g == 0) {
                l();
                return;
            } else {
                k();
                return;
            }
        }
        if (!BLEManager.isConnected()) {
            LogTool.b(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] connect is break, stop sync");
            k();
            return;
        }
        f fVarPoll = this.f4166b.poll();
        if (fVarPoll != null) {
            fVarPoll.a(new c(fVarPoll));
            fVarPoll.a(this.f4172h.iSyncDataListener);
            fVarPoll.c();
        }
    }

    public static b d() {
        if (k == null) {
            k = new b();
        }
        return k;
    }

    private void e() {
        int i;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] initOnlyV3Task...");
        if (this.f4172h.isNeedSyncConfigData) {
            com.ido.ble.business.sync.f.c cVar = new com.ido.ble.business.sync.f.c();
            cVar.a(10);
            i = 90;
            this.f4166b.add(cVar);
        } else {
            i = 100;
        }
        g gVar = new g();
        gVar.a(70);
        int i2 = i - 70;
        this.f4166b.add(gVar);
        ActivityDataCount activityDataCount = this.f4165a;
        if (activityDataCount != null && activityDataCount.gps_count > 0) {
            com.ido.ble.business.sync.f.d dVar = new com.ido.ble.business.sync.f.d();
            dVar.a(20);
            i2 -= 20;
            this.f4166b.add(dVar);
        }
        if (i2 > 0) {
            gVar.a(gVar.a() + i2);
        }
        this.f4167c.addAll(this.f4166b);
    }

    private void f() {
        int i;
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] initSyncV2AndV3Task...");
        if (this.f4172h.isNeedSyncConfigData) {
            com.ido.ble.business.sync.f.c cVar = new com.ido.ble.business.sync.f.c();
            cVar.a(10);
            i = 90;
            this.f4166b.add(cVar);
        } else {
            i = 100;
        }
        com.ido.ble.business.sync.f.e eVar = new com.ido.ble.business.sync.f.e();
        eVar.a(35);
        int i2 = i - 35;
        this.f4166b.add(eVar);
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        if (supportFunctionInfoV != null && (supportFunctionInfoV.ex_main3_v3_spo2_data || supportFunctionInfoV.ex_main4_v3_swim || supportFunctionInfoV.ex_main4_v3_hr_data || supportFunctionInfoV.ex_main3_v3_pressure || supportFunctionInfoV.ex_table_main8_v3_sync_activity)) {
            g gVar = new g();
            gVar.a(45);
            i2 -= 45;
            this.f4166b.add(gVar);
        }
        ActivityDataCount activityDataCount = this.f4165a;
        if (activityDataCount != null) {
            if (activityDataCount.count > 0 && (supportFunctionInfoV == null || !supportFunctionInfoV.ex_table_main8_v3_sync_activity)) {
                com.ido.ble.business.sync.f.b bVar = new com.ido.ble.business.sync.f.b();
                bVar.a(5);
                i2 -= 5;
                this.f4166b.add(bVar);
            }
            if (this.f4165a.gps_count > 0) {
                com.ido.ble.business.sync.f.d dVar = new com.ido.ble.business.sync.f.d();
                dVar.a(5);
                i2 -= 5;
                this.f4166b.add(dVar);
            }
        }
        if (i2 > 0) {
            eVar.a(eVar.a() + i2);
        }
        this.f4167c.addAll(this.f4166b);
    }

    private void g() {
        e.a(this.i);
        this.f4166b.clear();
        this.f4167c.clear();
        this.f4168d = false;
        this.f4169e = 0;
        this.f4170f = 0;
        this.f4171g = 0;
        this.f4172h.iSyncDataListener = null;
        this.f4165a = null;
    }

    private void h() {
        new com.ido.ble.business.sync.a().a(new C0062b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        e();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        f();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        g();
        LogTool.b(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] synced failed.");
        ISyncProgressListener iSyncProgressListener = this.f4172h.iSyncProgressListener;
        if (iSyncProgressListener != null) {
            iSyncProgressListener.onFailed();
            this.f4172h.iSyncProgressListener = null;
        }
    }

    private void l() {
        g();
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] synced success.");
        ISyncProgressListener iSyncProgressListener = this.f4172h.iSyncProgressListener;
        if (iSyncProgressListener != null) {
            iSyncProgressListener.onSuccess();
            this.f4172h.iSyncProgressListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogTool.b(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] synced time out.");
        b();
        ISyncProgressListener iSyncProgressListener = this.f4172h.iSyncProgressListener;
        if (iSyncProgressListener != null) {
            iSyncProgressListener.onFailed();
            this.f4172h.iSyncProgressListener = null;
        }
    }

    public int a(ISyncProgressListener iSyncProgressListener) {
        if (iSyncProgressListener != null) {
            this.f4172h.iSyncProgressListener = iSyncProgressListener;
        }
        return this.f4170f;
    }

    public boolean a() {
        return this.f4168d;
    }

    public synchronized boolean a(SyncPara syncPara) {
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] start...");
        if (syncPara == null) {
            LogTool.b(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] syncConfig is null!");
            return false;
        }
        if (this.f4168d) {
            LogTool.b(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] is in doing state, ignore this action!");
            return false;
        }
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] syncPara is " + syncPara.toString());
        this.f4172h = syncPara;
        long j2 = this.f4172h.timeoutMillisecond;
        if (j2 < 120000) {
            j2 = j;
        }
        this.f4168d = true;
        ISyncProgressListener iSyncProgressListener = this.f4172h.iSyncProgressListener;
        if (iSyncProgressListener != null) {
            iSyncProgressListener.onStart();
        }
        if (d.b()) {
            h();
        } else {
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] is not need to get v2 activity and gps count.");
            if (d.a()) {
                j();
            } else {
                i();
            }
        }
        this.i = e.a(new a(), j2);
        return true;
    }

    public synchronized void b() {
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[SyncAllDataManager] stop");
        if (this.f4168d) {
            Iterator<f> it = this.f4167c.iterator();
            while (it.hasNext()) {
                it.next().d();
            }
            g();
        }
    }
}