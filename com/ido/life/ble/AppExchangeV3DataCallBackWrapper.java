package com.ido.life.ble;

import com.ido.ble.callback.V3AppExchangeDataCallBack;
import com.ido.ble.protocol.model.V3AppExchangeDataDeviceReplayEndData;
import com.ido.ble.protocol.model.V3AppExchangeDataHeartRate;
import com.ido.ble.protocol.model.V3AppExchangeDataIngDeviceReplyData;

/* JADX INFO: loaded from: classes2.dex */
public class AppExchangeV3DataCallBackWrapper implements V3AppExchangeDataCallBack.ICallBack {
    @Override // com.ido.ble.callback.V3AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeDataEndData(V3AppExchangeDataDeviceReplayEndData v3AppExchangeDataDeviceReplayEndData) {
    }

    @Override // com.ido.ble.callback.V3AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeDateIng(V3AppExchangeDataIngDeviceReplyData v3AppExchangeDataIngDeviceReplyData) {
    }

    @Override // com.ido.ble.callback.V3AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeHeartRateData(V3AppExchangeDataHeartRate v3AppExchangeDataHeartRate) {
    }
}