package com.ido.ble.callback;

import com.ido.ble.protocol.model.HornVoice;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceVoiceChangedCallBack {

    public interface ICallBack {
        void onHornVoiceChanged(HornVoice hornVoice);
    }

    public static void onHornVoiceChanged(final HornVoice hornVoice) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceVoiceChangedCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().p().iterator();
                while (it.hasNext()) {
                    it.next().onHornVoiceChanged(hornVoice);
                }
            }
        });
    }
}