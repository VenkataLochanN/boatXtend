package com.ido.ble.callback;

import com.ido.ble.protocol.model.BloodPressureAdjustDeviceReplyInfo;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class QueryStatusCallBack {

    public interface ICallBack {
        void onQueryBloodAdjustResult(BloodPressureAdjustDeviceReplyInfo.BloodAdjustResult bloodAdjustResult);
    }

    public static final void onQueryBloodAdjustResult(final BloodPressureAdjustDeviceReplyInfo.BloodAdjustResult bloodAdjustResult) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.QueryStatusCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().x().iterator();
                while (it.hasNext()) {
                    it.next().onQueryBloodAdjustResult(bloodAdjustResult);
                }
            }
        });
    }
}