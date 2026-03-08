package com.ido.life.ble;

import com.ido.ble.callback.AppExchangeDataCallBack;
import com.ido.ble.protocol.model.AppExchangeDataIngDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataPauseDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataResumeDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStartDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStopDeviceReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopPara;

/* JADX INFO: loaded from: classes2.dex */
public class AppExchangeDataCallBackWrapper implements AppExchangeDataCallBack.ICallBack {
    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onDeviceNoticeAppPause(DeviceNoticeAppExchangeDataPausePara deviceNoticeAppExchangeDataPausePara) {
    }

    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onDeviceNoticeAppResume(DeviceNoticeAppExchangeDataResumePara deviceNoticeAppExchangeDataResumePara) {
    }

    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onDeviceNoticeAppStop(DeviceNoticeAppExchangeDataStopPara deviceNoticeAppExchangeDataStopPara) {
    }

    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeDataStart(AppExchangeDataStartDeviceReplyData appExchangeDataStartDeviceReplyData) {
    }

    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeDateIng(AppExchangeDataIngDeviceReplyData appExchangeDataIngDeviceReplyData) {
    }

    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeDatePause(AppExchangeDataPauseDeviceReplyData appExchangeDataPauseDeviceReplyData) {
    }

    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeDateResume(AppExchangeDataResumeDeviceReplyData appExchangeDataResumeDeviceReplyData) {
    }

    @Override // com.ido.ble.callback.AppExchangeDataCallBack.ICallBack
    public void onReplyExchangeDateStop(AppExchangeDataStopDeviceReplyData appExchangeDataStopDeviceReplyData) {
    }
}