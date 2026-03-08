package com.ido.ble.callback;

import com.ido.ble.protocol.model.DeviceExchangeDataIngPara;
import com.ido.ble.protocol.model.DeviceExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceExchangeDataStartPara;
import com.ido.ble.protocol.model.DeviceExchangeDataStopPara;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceExchangeDataCallBack {

    public interface ICallBack {
        void onExchangeDataStart(DeviceExchangeDataStartPara deviceExchangeDataStartPara);

        void onExchangeDateIng(DeviceExchangeDataIngPara deviceExchangeDataIngPara);

        void onExchangeDatePause(DeviceExchangeDataPausePara deviceExchangeDataPausePara);

        void onExchangeDateResume(DeviceExchangeDataResumePara deviceExchangeDataResumePara);

        void onExchangeDateStop(DeviceExchangeDataStopPara deviceExchangeDataStopPara);
    }

    public static void a(final DeviceExchangeDataIngPara deviceExchangeDataIngPara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().j().iterator();
                while (it.hasNext()) {
                    it.next().onExchangeDateIng(deviceExchangeDataIngPara);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataPausePara deviceExchangeDataPausePara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().j().iterator();
                while (it.hasNext()) {
                    it.next().onExchangeDatePause(deviceExchangeDataPausePara);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataResumePara deviceExchangeDataResumePara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().j().iterator();
                while (it.hasNext()) {
                    it.next().onExchangeDateResume(deviceExchangeDataResumePara);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataStartPara deviceExchangeDataStartPara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().j().iterator();
                while (it.hasNext()) {
                    it.next().onExchangeDataStart(deviceExchangeDataStartPara);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataStopPara deviceExchangeDataStopPara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().j().iterator();
                while (it.hasNext()) {
                    it.next().onExchangeDateStop(deviceExchangeDataStopPara);
                }
            }
        });
    }
}