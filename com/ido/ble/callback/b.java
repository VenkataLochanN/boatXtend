package com.ido.ble.callback;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.callback.AppControlDeviceCallBack;
import com.ido.ble.callback.AppExchangeDataCallBack;
import com.ido.ble.callback.AppSendAllPhoneContactsCallBack;
import com.ido.ble.callback.AppSendDataCallBack;
import com.ido.ble.callback.AutoConnectErrorHappenListener;
import com.ido.ble.callback.BindCallBack;
import com.ido.ble.callback.BloodPressureMeasureCallBack;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.callback.DeviceExchangeDataCallBack;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.callback.DeviceLogCallBack;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.callback.DeviceVoiceChangedCallBack;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.PhoneMsgNoticeCallBack;
import com.ido.ble.callback.QueryStatusCallBack;
import com.ido.ble.callback.RebootCallback;
import com.ido.ble.callback.ScanCallBack;
import com.ido.ble.callback.SetPressCalibrationCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.callback.UnbindCallBack;
import com.ido.ble.callback.V3AppExchangeDataCallBack;
import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.callback.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class b {
    private static b L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<ScanCallBack.ICallBack> f4206a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<ConnectCallBack.ICallBack> f4207b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<SettingCallBack.ICallBack> f4208c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<BindCallBack.ICallBack> f4209d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<UnbindCallBack.ICallBack> f4210e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<AppExchangeDataCallBack.ICallBack> f4211f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<DeviceExchangeDataCallBack.ICallBack> f4212g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<AppSendDataCallBack.ICallBack> f4213h = new ArrayList();
    private List<AppSendAllPhoneContactsCallBack.ICallBack> i = new ArrayList();
    private List<BloodPressureMeasureCallBack.ICallBack> j = new ArrayList();
    private List<AppControlDeviceCallBack.ICallBack> k = new ArrayList();
    private List<DeviceControlAppCallBack.ICallBack> l = new ArrayList();
    private List<GetDeviceInfoCallBack.ICallBack> m = new ArrayList();
    private List<PhoneMsgNoticeCallBack.ICallBack> n = new ArrayList();
    private List<SyncCallBack.IHealthCallBack> o = new ArrayList();
    private List<SyncCallBack.IActivityCallBack> p = new ArrayList();
    private List<SyncCallBack.IConfigCallBack> q = new ArrayList();
    private List<RebootCallback.ICallBack> r = new ArrayList();
    private List<EnterDfuModeCallback.ICallBack> s = new ArrayList();
    private List<QueryStatusCallBack.ICallBack> t = new ArrayList();
    private List<DeviceGattCallBack.ICallBack> u = new ArrayList();
    private List<OtherProtocolCallBack.ICallBack> v = new ArrayList();
    private List<SyncV3CallBack.ICallBack> w = new ArrayList();
    private List<GetDeviceParaCallBack.ICallBack> x = new ArrayList();
    private List<DeviceParaChangedCallBack.ICallBack> y = new ArrayList();
    private List<DeviceResponseCommonCallBack.ICallBack> z = new ArrayList();
    private List<VoiceCallBack.ICallBack> A = new ArrayList();
    private List<DeviceLogCallBack.ICallBack> B = new ArrayList();
    private List<V3AppExchangeDataCallBack.ICallBack> C = new ArrayList();
    private List<AutoConnectErrorHappenListener.IListener> D = new ArrayList();
    private List<DeviceUpgradeEventListener.IListener> E = new ArrayList();
    private List<OperateCallBack.ICallBack> F = new ArrayList();
    private List<SetPressCalibrationCallBack.ICallBack> G = new ArrayList();
    private List<OperateCallBack.IMusicCallBack> H = new ArrayList();
    private List<DeviceVoiceChangedCallBack.ICallBack> I = new ArrayList();
    private List<d.b> J = new ArrayList();
    private Handler K = new Handler(Looper.getMainLooper());

    private b() {
    }

    public static b K() {
        if (L == null) {
            L = new b();
        }
        return L;
    }

    List<SetPressCalibrationCallBack.ICallBack> A() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.G);
        return arrayList;
    }

    List<SettingCallBack.ICallBack> B() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4208c);
        return arrayList;
    }

    List<SyncCallBack.IActivityCallBack> C() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.p);
        return arrayList;
    }

    List<SyncCallBack.IConfigCallBack> D() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.q);
        return arrayList;
    }

    List<SyncCallBack.IHealthCallBack> E() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.o);
        return arrayList;
    }

    List<SyncV3CallBack.ICallBack> F() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.w);
        return arrayList;
    }

    List<UnbindCallBack.ICallBack> G() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4210e);
        return arrayList;
    }

    List<d.b> H() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.J);
        return arrayList;
    }

    List<V3AppExchangeDataCallBack.ICallBack> I() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.C);
        return arrayList;
    }

    List<VoiceCallBack.ICallBack> J() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.A);
        return arrayList;
    }

    List<AppControlDeviceCallBack.ICallBack> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.k);
        return arrayList;
    }

    public void a(AppControlDeviceCallBack.ICallBack iCallBack) {
        this.k.add(iCallBack);
    }

    public void a(AppExchangeDataCallBack.ICallBack iCallBack) {
        this.f4211f.add(iCallBack);
    }

    public void a(AppSendAllPhoneContactsCallBack.ICallBack iCallBack) {
        this.i.add(iCallBack);
    }

    public void a(AppSendDataCallBack.ICallBack iCallBack) {
        this.f4213h.add(iCallBack);
    }

    public void a(AutoConnectErrorHappenListener.IListener iListener) {
        this.D.add(iListener);
    }

    public void a(BindCallBack.ICallBack iCallBack) {
        this.f4209d.add(iCallBack);
    }

    public void a(BloodPressureMeasureCallBack.ICallBack iCallBack) {
        this.j.add(iCallBack);
    }

    public void a(ConnectCallBack.ICallBack iCallBack) {
        this.f4207b.add(iCallBack);
    }

    public void a(DeviceControlAppCallBack.ICallBack iCallBack) {
        this.l.add(iCallBack);
    }

    public void a(DeviceExchangeDataCallBack.ICallBack iCallBack) {
        this.f4212g.add(iCallBack);
    }

    public void a(DeviceGattCallBack.ICallBack iCallBack) {
        this.u.add(iCallBack);
    }

    public void a(DeviceLogCallBack.ICallBack iCallBack) {
        this.B.add(iCallBack);
    }

    public void a(DeviceParaChangedCallBack.ICallBack iCallBack) {
        this.y.add(iCallBack);
    }

    public void a(DeviceResponseCommonCallBack.ICallBack iCallBack) {
        this.z.add(iCallBack);
    }

    public void a(DeviceUpgradeEventListener.IListener iListener) {
        this.E.add(iListener);
    }

    public void a(DeviceVoiceChangedCallBack.ICallBack iCallBack) {
        this.I.add(iCallBack);
    }

    public void a(EnterDfuModeCallback.ICallBack iCallBack) {
        this.s.add(iCallBack);
    }

    public void a(GetDeviceInfoCallBack.ICallBack iCallBack) {
        this.m.add(iCallBack);
    }

    public void a(GetDeviceParaCallBack.ICallBack iCallBack) {
        this.x.add(iCallBack);
    }

    public void a(OperateCallBack.ICallBack iCallBack) {
        this.F.add(iCallBack);
    }

    public void a(OperateCallBack.IMusicCallBack iMusicCallBack) {
        this.H.add(iMusicCallBack);
    }

    public void a(OtherProtocolCallBack.ICallBack iCallBack) {
        this.v.add(iCallBack);
    }

    public void a(PhoneMsgNoticeCallBack.ICallBack iCallBack) {
        this.n.add(iCallBack);
    }

    public void a(QueryStatusCallBack.ICallBack iCallBack) {
        this.t.add(iCallBack);
    }

    public void a(RebootCallback.ICallBack iCallBack) {
        this.r.add(iCallBack);
    }

    public void a(ScanCallBack.ICallBack iCallBack) {
        this.f4206a.add(iCallBack);
    }

    public void a(SetPressCalibrationCallBack.ICallBack iCallBack) {
        this.G.add(iCallBack);
    }

    public void a(SettingCallBack.ICallBack iCallBack) {
        this.f4208c.add(iCallBack);
    }

    public void a(SyncCallBack.IActivityCallBack iActivityCallBack) {
        this.p.add(iActivityCallBack);
    }

    public void a(SyncCallBack.IConfigCallBack iConfigCallBack) {
        this.q.add(iConfigCallBack);
    }

    public void a(SyncCallBack.IHealthCallBack iHealthCallBack) {
        this.o.add(iHealthCallBack);
    }

    public void a(SyncV3CallBack.ICallBack iCallBack) {
        this.w.add(iCallBack);
    }

    public void a(UnbindCallBack.ICallBack iCallBack) {
        this.f4210e.add(iCallBack);
    }

    public void a(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        this.C.add(iCallBack);
    }

    public void a(VoiceCallBack.ICallBack iCallBack) {
        this.A.add(iCallBack);
    }

    public void a(d.b bVar) {
        this.J.add(bVar);
    }

    void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.K.post(runnable);
        }
    }

    List<AppExchangeDataCallBack.ICallBack> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4211f);
        return arrayList;
    }

    public void b(AppControlDeviceCallBack.ICallBack iCallBack) {
        this.k.remove(iCallBack);
    }

    public void b(AppExchangeDataCallBack.ICallBack iCallBack) {
        this.f4211f.remove(iCallBack);
    }

    public void b(AppSendAllPhoneContactsCallBack.ICallBack iCallBack) {
        this.i.remove(iCallBack);
    }

    public void b(AppSendDataCallBack.ICallBack iCallBack) {
        this.f4213h.remove(iCallBack);
    }

    public void b(AutoConnectErrorHappenListener.IListener iListener) {
        this.D.remove(iListener);
    }

    public void b(BindCallBack.ICallBack iCallBack) {
        this.f4209d.remove(iCallBack);
    }

    public void b(BloodPressureMeasureCallBack.ICallBack iCallBack) {
        this.j.remove(iCallBack);
    }

    public void b(ConnectCallBack.ICallBack iCallBack) {
        this.f4207b.remove(iCallBack);
    }

    public void b(DeviceControlAppCallBack.ICallBack iCallBack) {
        this.l.remove(iCallBack);
    }

    public void b(DeviceExchangeDataCallBack.ICallBack iCallBack) {
        this.f4212g.remove(iCallBack);
    }

    public void b(DeviceGattCallBack.ICallBack iCallBack) {
        this.u.remove(iCallBack);
    }

    public void b(DeviceLogCallBack.ICallBack iCallBack) {
        this.B.remove(iCallBack);
    }

    public void b(DeviceParaChangedCallBack.ICallBack iCallBack) {
        this.y.remove(iCallBack);
    }

    public void b(DeviceResponseCommonCallBack.ICallBack iCallBack) {
        this.z.remove(iCallBack);
    }

    public void b(DeviceUpgradeEventListener.IListener iListener) {
        this.E.remove(iListener);
    }

    public void b(DeviceVoiceChangedCallBack.ICallBack iCallBack) {
        this.I.remove(iCallBack);
    }

    public void b(EnterDfuModeCallback.ICallBack iCallBack) {
        this.s.remove(iCallBack);
    }

    public void b(GetDeviceInfoCallBack.ICallBack iCallBack) {
        this.m.remove(iCallBack);
    }

    public void b(GetDeviceParaCallBack.ICallBack iCallBack) {
        this.x.remove(iCallBack);
    }

    public void b(OperateCallBack.ICallBack iCallBack) {
        this.F.remove(iCallBack);
    }

    public void b(OperateCallBack.IMusicCallBack iMusicCallBack) {
        this.H.remove(iMusicCallBack);
    }

    public void b(OtherProtocolCallBack.ICallBack iCallBack) {
        this.v.remove(iCallBack);
    }

    public void b(PhoneMsgNoticeCallBack.ICallBack iCallBack) {
        this.n.remove(iCallBack);
    }

    public void b(QueryStatusCallBack.ICallBack iCallBack) {
        this.t.remove(iCallBack);
    }

    public void b(RebootCallback.ICallBack iCallBack) {
        this.r.remove(iCallBack);
    }

    public void b(ScanCallBack.ICallBack iCallBack) {
        this.f4206a.remove(iCallBack);
    }

    public void b(SetPressCalibrationCallBack.ICallBack iCallBack) {
        this.G.remove(iCallBack);
    }

    public void b(SettingCallBack.ICallBack iCallBack) {
        this.f4208c.remove(iCallBack);
    }

    public void b(SyncCallBack.IActivityCallBack iActivityCallBack) {
        this.p.remove(iActivityCallBack);
    }

    public void b(SyncCallBack.IConfigCallBack iConfigCallBack) {
        this.q.remove(iConfigCallBack);
    }

    public void b(SyncCallBack.IHealthCallBack iHealthCallBack) {
        this.o.remove(iHealthCallBack);
    }

    public void b(SyncV3CallBack.ICallBack iCallBack) {
        this.w.remove(iCallBack);
    }

    public void b(UnbindCallBack.ICallBack iCallBack) {
        this.f4210e.remove(iCallBack);
    }

    public void b(V3AppExchangeDataCallBack.ICallBack iCallBack) {
        this.C.remove(iCallBack);
    }

    public void b(VoiceCallBack.ICallBack iCallBack) {
        this.A.remove(iCallBack);
    }

    public void b(d.b bVar) {
        this.J.remove(bVar);
    }

    List<AppSendAllPhoneContactsCallBack.ICallBack> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.i);
        return arrayList;
    }

    List<AppSendDataCallBack.ICallBack> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4213h);
        return arrayList;
    }

    List<AutoConnectErrorHappenListener.IListener> e() {
        return new ArrayList(this.D);
    }

    List<BindCallBack.ICallBack> f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4209d);
        return arrayList;
    }

    List<BloodPressureMeasureCallBack.ICallBack> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.j);
        return arrayList;
    }

    List<ConnectCallBack.ICallBack> h() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4207b);
        return arrayList;
    }

    List<DeviceControlAppCallBack.ICallBack> i() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.l);
        return arrayList;
    }

    List<DeviceExchangeDataCallBack.ICallBack> j() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4212g);
        return arrayList;
    }

    List<DeviceGattCallBack.ICallBack> k() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.u);
        return arrayList;
    }

    List<DeviceLogCallBack.ICallBack> l() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.B);
        return arrayList;
    }

    List<DeviceParaChangedCallBack.ICallBack> m() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.y);
        return arrayList;
    }

    List<DeviceResponseCommonCallBack.ICallBack> n() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.z);
        return arrayList;
    }

    List<DeviceUpgradeEventListener.IListener> o() {
        return new ArrayList(this.E);
    }

    List<DeviceVoiceChangedCallBack.ICallBack> p() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.I);
        return arrayList;
    }

    List<EnterDfuModeCallback.ICallBack> q() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.s);
        return arrayList;
    }

    List<GetDeviceInfoCallBack.ICallBack> r() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.m);
        return arrayList;
    }

    List<GetDeviceParaCallBack.ICallBack> s() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.x);
        return arrayList;
    }

    List<OperateCallBack.ICallBack> t() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.F);
        return arrayList;
    }

    List<OperateCallBack.IMusicCallBack> u() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.H);
        return arrayList;
    }

    List<OtherProtocolCallBack.ICallBack> v() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.v);
        return arrayList;
    }

    List<PhoneMsgNoticeCallBack.ICallBack> w() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.n);
        return arrayList;
    }

    List<QueryStatusCallBack.ICallBack> x() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.t);
        return arrayList;
    }

    List<RebootCallback.ICallBack> y() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.r);
        return arrayList;
    }

    List<ScanCallBack.ICallBack> z() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4206a);
        return arrayList;
    }
}