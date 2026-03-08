package com.ido.ble.gps.callback;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.gps.callback.GpsCallBack;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static a i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<GpsCallBack.IGetGpsInfoCallBack> f4595a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<GpsCallBack.IDeviceReplySetGpsCallBack> f4596b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<GpsCallBack.ISyncGpsDataCallBack> f4597c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<GpsCallBack.ITranAgpsFileCallBack> f4598d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<GpsCallBack.ISPPTranFileCallBack> f4599e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<GpsCallBack.IMp3ConvertCallBack> f4600f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<GpsCallBack.ITranAgpsWatchErrorCallBack> f4601g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Handler f4602h = new Handler(Looper.getMainLooper());

    public static a h() {
        if (i == null) {
            i = new a();
        }
        return i;
    }

    List<GpsCallBack.IDeviceReplySetGpsCallBack> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4596b);
        return arrayList;
    }

    public void a(GpsCallBack.IDeviceReplySetGpsCallBack iDeviceReplySetGpsCallBack) {
        this.f4596b.add(iDeviceReplySetGpsCallBack);
    }

    public void a(GpsCallBack.IGetGpsInfoCallBack iGetGpsInfoCallBack) {
        this.f4595a.add(iGetGpsInfoCallBack);
    }

    public void a(GpsCallBack.IMp3ConvertCallBack iMp3ConvertCallBack) {
        this.f4600f.add(iMp3ConvertCallBack);
    }

    public void a(GpsCallBack.ISPPTranFileCallBack iSPPTranFileCallBack) {
        this.f4599e.add(iSPPTranFileCallBack);
    }

    public void a(GpsCallBack.ISyncGpsDataCallBack iSyncGpsDataCallBack) {
        this.f4597c.add(iSyncGpsDataCallBack);
    }

    public void a(GpsCallBack.ITranAgpsFileCallBack iTranAgpsFileCallBack) {
        this.f4598d.add(iTranAgpsFileCallBack);
    }

    public void a(GpsCallBack.ITranAgpsWatchErrorCallBack iTranAgpsWatchErrorCallBack) {
        this.f4601g.add(iTranAgpsWatchErrorCallBack);
    }

    void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.f4602h.post(runnable);
        }
    }

    List<GpsCallBack.IGetGpsInfoCallBack> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4595a);
        return arrayList;
    }

    public void b(GpsCallBack.IDeviceReplySetGpsCallBack iDeviceReplySetGpsCallBack) {
        this.f4596b.remove(iDeviceReplySetGpsCallBack);
    }

    public void b(GpsCallBack.IGetGpsInfoCallBack iGetGpsInfoCallBack) {
        this.f4595a.remove(iGetGpsInfoCallBack);
    }

    public void b(GpsCallBack.IMp3ConvertCallBack iMp3ConvertCallBack) {
        this.f4600f.remove(iMp3ConvertCallBack);
    }

    public void b(GpsCallBack.ISPPTranFileCallBack iSPPTranFileCallBack) {
        this.f4599e.remove(iSPPTranFileCallBack);
    }

    public void b(GpsCallBack.ISyncGpsDataCallBack iSyncGpsDataCallBack) {
        this.f4597c.remove(iSyncGpsDataCallBack);
    }

    public void b(GpsCallBack.ITranAgpsFileCallBack iTranAgpsFileCallBack) {
        this.f4598d.remove(iTranAgpsFileCallBack);
    }

    public void b(GpsCallBack.ITranAgpsWatchErrorCallBack iTranAgpsWatchErrorCallBack) {
        this.f4601g.remove(iTranAgpsWatchErrorCallBack);
    }

    List<GpsCallBack.IMp3ConvertCallBack> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4600f);
        return arrayList;
    }

    List<GpsCallBack.ISPPTranFileCallBack> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4599e);
        return arrayList;
    }

    List<GpsCallBack.ISyncGpsDataCallBack> e() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4597c);
        return arrayList;
    }

    List<GpsCallBack.ITranAgpsFileCallBack> f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4598d);
        return arrayList;
    }

    List<GpsCallBack.ITranAgpsWatchErrorCallBack> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4601g);
        return arrayList;
    }
}