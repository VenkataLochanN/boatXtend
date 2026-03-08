package com.ido.life.ble;

import android.os.Handler;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.UnbindCallBack;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseUnbindCallback implements UnbindCallBack.ICallBack {
    private Handler mHandler;

    @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
    public void onFailed() {
    }

    @Override // com.ido.ble.callback.UnbindCallBack.ICallBack
    public void onSuccess() {
        unbindSuccess();
    }

    private void unbindSuccess() {
        SPHelper.saveLastRemindFirmwareVersion(0);
        SPHelper.saveLastOtaReminderDate("");
        SPHelper.saveLastFlashReminderDate("");
        SPHelper.saveAgpsOfflineUpgradeTime(0L);
        SPHelper.saveAgpsOnlineUpgradeTime(0L);
        connectHistoryDevice();
        EventBusHelper.post(Constants.EventConstants.EVENT_TYPE_UNBIND_SUCCESS);
    }

    private void connectHistoryDevice() {
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable() { // from class: com.ido.life.ble.-$$Lambda$BaseUnbindCallback$7pl4nBYUolvXpBaB6PYJMbRJo3c
            @Override // java.lang.Runnable
            public final void run() {
                BaseUnbindCallback.lambda$connectHistoryDevice$0();
            }
        }, 500L);
    }

    static /* synthetic */ void lambda$connectHistoryDevice$0() {
        if (BLEManager.isBind()) {
            return;
        }
        List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
        if (deviceList.isEmpty()) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "BaseUnbindCallback BLEManager.autoConnect");
        String mac = deviceList.get(0).getMac();
        BLEManager.autoConnect(mac);
        ConnectLogHelper.saveLog("BaseUnbindCallback", "autoConnect(".concat(mac).concat(")"));
    }
}