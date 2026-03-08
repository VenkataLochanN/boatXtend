package com.ido.ble.callback;

import com.ido.ble.protocol.model.BloodPressureMeasureDeviceReplyData;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class BloodPressureMeasureCallBack {

    public interface ICallBack {
        void onReply(BloodPressureMeasureDeviceReplyData bloodPressureMeasureDeviceReplyData);
    }

    public static void a(final BloodPressureMeasureDeviceReplyData bloodPressureMeasureDeviceReplyData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.BloodPressureMeasureCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().g().iterator();
                while (it.hasNext()) {
                    it.next().onReply(bloodPressureMeasureDeviceReplyData);
                }
            }
        });
    }
}