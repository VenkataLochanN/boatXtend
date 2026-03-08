package com.ido.ble.callback;

import com.ido.ble.protocol.model.DeviceChangedPara;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceParaChangedCallBack {

    public interface ICallBack {
        void onChanged(DeviceChangedPara deviceChangedPara);
    }

    public static void onChanged(final DeviceChangedPara deviceChangedPara) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceParaChangedCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().m().iterator();
                while (it.hasNext()) {
                    it.next().onChanged(deviceChangedPara);
                }
            }
        });
    }
}