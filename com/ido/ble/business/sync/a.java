package com.ido.ble.business.sync;

import com.ido.ble.BLEManager;
import com.ido.ble.business.sync.e;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;

/* JADX INFO: loaded from: classes2.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f4159a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4160b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private GetDeviceInfoCallBack.ICallBack f4161c = new C0061a();

    /* JADX INFO: renamed from: com.ido.ble.business.sync.a$a, reason: collision with other inner class name */
    class C0061a extends com.ido.ble.callback.a {
        C0061a() {
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetActivityCount(ActivityDataCount activityDataCount) {
            a.this.a();
            if (activityDataCount == null) {
                LogTool.d(com.ido.ble.business.sync.c.f4177a, "[GetActivityCountTask] get activity count failed!");
                a.this.f4159a.onFailed();
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f4177a, "[GetActivityCountTask] get activity count ok," + activityDataCount.toString());
            a.this.f4159a.a(activityDataCount);
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetDeviceSummarySoftVersionInfo(DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo) {
        }
    }

    class b implements e.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c f4163a;

        b(c cVar) {
            this.f4163a = cVar;
        }

        @Override // com.ido.ble.business.sync.e.b
        public void onTimeOut() {
            LogTool.b(com.ido.ble.business.sync.c.f4177a, "[GetActivityCountTask] get activity count failed, timeout.");
            a.this.a();
            this.f4163a.onFailed();
        }
    }

    public interface c {
        void a(ActivityDataCount activityDataCount);

        void onFailed();
    }

    a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        e.a(this.f4160b);
        BLEManager.unregisterGetDeviceInfoCallBack(this.f4161c);
    }

    public void a(c cVar) {
        LogTool.d(com.ido.ble.business.sync.c.f4177a, "[GetActivityCountTask] start to get activity count");
        this.f4159a = cVar;
        BLEManager.registerGetDeviceInfoCallBack(this.f4161c);
        BLEManager.getActivityCount();
        this.f4160b = e.a(new b(cVar), 15000L);
    }
}