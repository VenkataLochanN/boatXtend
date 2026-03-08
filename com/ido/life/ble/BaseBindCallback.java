package com.ido.life.ble;

import com.ido.ble.callback.BindCallBack;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BaseBindCallback implements BindCallBack.ICallBack {
    @Override // com.ido.ble.callback.BindCallBack.ICallBack
    public void onCancel() {
    }

    @Override // com.ido.ble.callback.BindCallBack.ICallBack
    public void onFailed(BindCallBack.BindFailedError bindFailedError) {
    }

    @Override // com.ido.ble.callback.BindCallBack.ICallBack
    public void onNeedAuth(int i) {
    }

    @Override // com.ido.ble.callback.BindCallBack.ICallBack
    public void onReject() {
    }

    @Override // com.ido.ble.callback.BindCallBack.ICallBack
    public void onSuccess() {
        SPHelper.setConfigSynced(false);
        SPHelper.saveLastRemindLanguageInfo(null);
        SPHelper.saveQuickMsgList(null);
    }
}