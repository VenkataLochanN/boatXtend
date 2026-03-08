package com.ido.ble.gps.callback;

import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.gps.model.GPSInfo;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.gps.model.GpsStatus;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GpsCallBack {

    public interface IDeviceReplySetGpsCallBack {
        void onControlGps(ControlGpsReply controlGpsReply);

        void onSetConfigGps(boolean z);

        void onSetConnParam(ConnParamReply connParamReply);

        void onSetHotStartGpsPara(boolean z);
    }

    public interface IGetGpsInfoCallBack {
        void onGetGpsInfo(GPSInfo gPSInfo);

        void onGetGpsStatus(GpsStatus gpsStatus);

        void onGetHotStartGpsPara(GpsHotStartParam gpsHotStartParam);
    }

    public interface IMp3ConvertCallBack {
        void onConvertFailed();

        void onConvertSuccess();

        void onNoNeedConvert();
    }

    public interface ISPPTranFileCallBack {
        void onFailed(int i);

        void onFinish();

        void onProgress(int i);
    }

    public interface ISyncGpsDataCallBack {
        void onFailed();

        void onFinish();

        void onGetGpsData(HealthGps healthGps, List<HealthGpsItem> list, boolean z);

        void onProgress(int i);

        void onStart();
    }

    public interface ITranAgpsFileCallBack {
        void onFailed(int i);

        void onFailed(int i, Object obj);

        void onFinish();

        void onProgress(int i);
    }

    public interface ITranAgpsWatchErrorCallBack {
        void onFailed(int i, int i2);
    }

    public static void a() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IMp3ConvertCallBack> it = a.h().c().iterator();
                while (it.hasNext()) {
                    it.next().onConvertFailed();
                }
            }
        });
    }

    public static void a(final int i) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.12
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISPPTranFileCallBack> it = a.h().d().iterator();
                while (it.hasNext()) {
                    it.next().onFailed(i);
                }
            }
        });
    }

    public static void a(final int i, final int i2) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ITranAgpsWatchErrorCallBack> it = a.h().g().iterator();
                while (it.hasNext()) {
                    it.next().onFailed(i, i2);
                }
            }
        });
    }

    public static void a(final int i, final Object obj) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ITranAgpsFileCallBack> it = a.h().f().iterator();
                while (it.hasNext()) {
                    it.next().onFailed(i, obj);
                }
            }
        });
    }

    public static void a(final HealthGps healthGps, final List<HealthGpsItem> list, final boolean z) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISyncGpsDataCallBack> it = a.h().e().iterator();
                while (it.hasNext()) {
                    it.next().onGetGpsData(healthGps, list, z);
                }
            }
        });
    }

    public static void a(final ConnParamReply connParamReply) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.15
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IDeviceReplySetGpsCallBack> it = a.h().a().iterator();
                while (it.hasNext()) {
                    it.next().onSetConnParam(connParamReply);
                }
            }
        });
    }

    public static void a(final ControlGpsReply controlGpsReply) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.16
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IDeviceReplySetGpsCallBack> it = a.h().a().iterator();
                while (it.hasNext()) {
                    it.next().onControlGps(controlGpsReply);
                }
            }
        });
    }

    public static void a(final GPSInfo gPSInfo) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.17
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IGetGpsInfoCallBack> it = a.h().b().iterator();
                while (it.hasNext()) {
                    it.next().onGetGpsInfo(gPSInfo);
                }
            }
        });
    }

    public static void a(final GpsHotStartParam gpsHotStartParam) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.18
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IGetGpsInfoCallBack> it = a.h().b().iterator();
                while (it.hasNext()) {
                    it.next().onGetHotStartGpsPara(gpsHotStartParam);
                }
            }
        });
    }

    public static void a(final GpsStatus gpsStatus) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.19
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IGetGpsInfoCallBack> it = a.h().b().iterator();
                while (it.hasNext()) {
                    it.next().onGetGpsStatus(gpsStatus);
                }
            }
        });
    }

    public static void a(final boolean z) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.13
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IDeviceReplySetGpsCallBack> it = a.h().a().iterator();
                while (it.hasNext()) {
                    it.next().onSetConfigGps(z);
                }
            }
        });
    }

    public static void b() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IMp3ConvertCallBack> it = a.h().c().iterator();
                while (it.hasNext()) {
                    it.next().onConvertSuccess();
                }
            }
        });
    }

    public static void b(final int i) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.10
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISPPTranFileCallBack> it = a.h().d().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(i);
                }
            }
        });
    }

    public static void b(final boolean z) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.14
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IDeviceReplySetGpsCallBack> it = a.h().a().iterator();
                while (it.hasNext()) {
                    it.next().onSetHotStartGpsPara(z);
                }
            }
        });
    }

    public static void c() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IMp3ConvertCallBack> it = a.h().c().iterator();
                while (it.hasNext()) {
                    it.next().onNoNeedConvert();
                }
            }
        });
    }

    public static void c(final int i) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.21
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISyncGpsDataCallBack> it = a.h().e().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(i);
                }
            }
        });
    }

    public static void d() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.11
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISPPTranFileCallBack> it = a.h().d().iterator();
                while (it.hasNext()) {
                    it.next().onFinish();
                }
            }
        });
    }

    public static void d(final int i) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ITranAgpsFileCallBack> it = a.h().f().iterator();
                while (it.hasNext()) {
                    it.next().onFailed(i);
                }
            }
        });
    }

    public static void e() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.23
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISyncGpsDataCallBack> it = a.h().e().iterator();
                while (it.hasNext()) {
                    it.next().onFailed();
                }
            }
        });
    }

    public static void e(final int i) {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ITranAgpsFileCallBack> it = a.h().f().iterator();
                while (it.hasNext()) {
                    it.next().onProgress(i);
                }
            }
        });
    }

    public static void f() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.22
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISyncGpsDataCallBack> it = a.h().e().iterator();
                while (it.hasNext()) {
                    it.next().onFinish();
                }
            }
        });
    }

    public static void g() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.20
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ISyncGpsDataCallBack> it = a.h().e().iterator();
                while (it.hasNext()) {
                    it.next().onStart();
                }
            }
        });
    }

    public static void h() {
        a.h().a(new Runnable() { // from class: com.ido.ble.gps.callback.GpsCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ITranAgpsFileCallBack> it = a.h().f().iterator();
                while (it.hasNext()) {
                    it.next().onFinish();
                }
            }
        });
    }
}