package com.ido.life.module.device.view;

import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface INightLightView extends IBaseView {
    void onGetScreenBrightness(ScreenBrightness screenBrightness);

    void onSetNightLightFailed();

    void onSetNightLightSuccess();
}