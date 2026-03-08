package com.ido.ble.callback;

import com.ido.ble.protocol.model.PressCalibrationReplyInfo;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class SetPressCalibrationCallBack {

    public interface ICallBack {
        void onSetPressCalibrationResult(PressCalibrationReplyInfo.PressCalibrationResult pressCalibrationResult);
    }

    public static final void onSetPressCalibrationResult(final PressCalibrationReplyInfo.PressCalibrationResult pressCalibrationResult) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.SetPressCalibrationCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().A().iterator();
                while (it.hasNext()) {
                    it.next().onSetPressCalibrationResult(pressCalibrationResult);
                }
            }
        });
    }
}