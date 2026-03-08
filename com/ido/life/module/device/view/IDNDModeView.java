package com.ido.life.module.device.view;

import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface IDNDModeView extends IBaseView {
    void onGetDNDMode(NotDisturbPara notDisturbPara);

    void onSetDNDModeFailed();

    void onSetDNDModeSuccess();
}