package com.ido.ble.callback;

import com.ido.ble.protocol.model.AppExchangeDataIngDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataPauseDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataResumeDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStartDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStopDeviceReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopPara;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AppExchangeDataCallBack {

    public interface ICallBack {
        void onDeviceNoticeAppPause(DeviceNoticeAppExchangeDataPausePara deviceNoticeAppExchangeDataPausePara);

        void onDeviceNoticeAppResume(DeviceNoticeAppExchangeDataResumePara deviceNoticeAppExchangeDataResumePara);

        void onDeviceNoticeAppStop(DeviceNoticeAppExchangeDataStopPara deviceNoticeAppExchangeDataStopPara);

        void onReplyExchangeDataStart(AppExchangeDataStartDeviceReplyData appExchangeDataStartDeviceReplyData);

        void onReplyExchangeDateIng(AppExchangeDataIngDeviceReplyData appExchangeDataIngDeviceReplyData);

        void onReplyExchangeDatePause(AppExchangeDataPauseDeviceReplyData appExchangeDataPauseDeviceReplyData);

        void onReplyExchangeDateResume(AppExchangeDataResumeDeviceReplyData appExchangeDataResumeDeviceReplyData);

        void onReplyExchangeDateStop(AppExchangeDataStopDeviceReplyData appExchangeDataStopDeviceReplyData);
    }

    public static void a(final AppExchangeDataIngDeviceReplyData appExchangeDataIngDeviceReplyData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeDateIng(appExchangeDataIngDeviceReplyData);
                }
            }
        });
    }

    public static void a(final AppExchangeDataPauseDeviceReplyData appExchangeDataPauseDeviceReplyData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeDatePause(appExchangeDataPauseDeviceReplyData);
                }
            }
        });
    }

    public static void a(final AppExchangeDataResumeDeviceReplyData appExchangeDataResumeDeviceReplyData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeDateResume(appExchangeDataResumeDeviceReplyData);
                }
            }
        });
    }

    public static void a(final AppExchangeDataStartDeviceReplyData appExchangeDataStartDeviceReplyData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeDataStart(appExchangeDataStartDeviceReplyData);
                }
            }
        });
    }

    public static void a(final AppExchangeDataStopDeviceReplyData appExchangeDataStopDeviceReplyData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeDateStop(appExchangeDataStopDeviceReplyData);
                }
            }
        });
    }

    public static void a(final DeviceNoticeAppExchangeDataPausePara deviceNoticeAppExchangeDataPausePara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onDeviceNoticeAppPause(deviceNoticeAppExchangeDataPausePara);
                }
            }
        });
    }

    public static void a(final DeviceNoticeAppExchangeDataResumePara deviceNoticeAppExchangeDataResumePara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onDeviceNoticeAppResume(deviceNoticeAppExchangeDataResumePara);
                }
            }
        });
    }

    public static void a(final DeviceNoticeAppExchangeDataStopPara deviceNoticeAppExchangeDataStopPara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().b().iterator();
                while (it.hasNext()) {
                    it.next().onDeviceNoticeAppStop(deviceNoticeAppExchangeDataStopPara);
                }
            }
        });
    }
}