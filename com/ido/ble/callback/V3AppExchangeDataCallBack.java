package com.ido.ble.callback;

import com.ido.ble.protocol.model.V3AppExchangeDataDeviceReplayEndData;
import com.ido.ble.protocol.model.V3AppExchangeDataHeartRate;
import com.ido.ble.protocol.model.V3AppExchangeDataIngDeviceReplyData;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class V3AppExchangeDataCallBack {

    public interface ICallBack {
        void onReplyExchangeDataEndData(V3AppExchangeDataDeviceReplayEndData v3AppExchangeDataDeviceReplayEndData);

        void onReplyExchangeDateIng(V3AppExchangeDataIngDeviceReplyData v3AppExchangeDataIngDeviceReplyData);

        void onReplyExchangeHeartRateData(V3AppExchangeDataHeartRate v3AppExchangeDataHeartRate);
    }

    public static void a(final V3AppExchangeDataDeviceReplayEndData v3AppExchangeDataDeviceReplayEndData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.V3AppExchangeDataCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().I().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeDataEndData(v3AppExchangeDataDeviceReplayEndData);
                }
            }
        });
    }

    public static void a(final V3AppExchangeDataHeartRate v3AppExchangeDataHeartRate) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.V3AppExchangeDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().I().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeHeartRateData(v3AppExchangeDataHeartRate);
                }
            }
        });
    }

    public static void a(final V3AppExchangeDataIngDeviceReplyData v3AppExchangeDataIngDeviceReplyData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.V3AppExchangeDataCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().I().iterator();
                while (it.hasNext()) {
                    it.next().onReplyExchangeDateIng(v3AppExchangeDataIngDeviceReplyData);
                }
            }
        });
    }
}