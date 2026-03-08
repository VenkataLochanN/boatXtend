package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.module.device.view.IDNDModeView;
import com.ido.life.util.AppLogUploadManager;

/* JADX INFO: loaded from: classes2.dex */
public class DNDModePresenter extends BaseCmdPresenter<IDNDModeView> {
    private BaseDeviceParaCallBack mParaCallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.DNDModePresenter.1
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetDoNotDisturbPara(NotDisturbPara notDisturbPara) {
            super.onGetDoNotDisturbPara(notDisturbPara);
            if (DNDModePresenter.this.isAttachView()) {
                if (notDisturbPara == null) {
                    notDisturbPara = new NotDisturbPara();
                    notDisturbPara.onOFf = 85;
                    notDisturbPara.startHour = 9;
                    notDisturbPara.startMinute = 0;
                    notDisturbPara.endHour = 18;
                    notDisturbPara.endMinute = 0;
                    notDisturbPara.noontimeRestOnOff = 85;
                    notDisturbPara.noontimeRestStartHour = 22;
                    notDisturbPara.noontimeRestStartMinute = 0;
                    notDisturbPara.noontimeRestEndHour = 7;
                    notDisturbPara.noontimeRestEndMinute = 0;
                    notDisturbPara.setWeeks(new boolean[]{true, true, true, true, true, true, true});
                }
                ((IDNDModeView) DNDModePresenter.this.getView()).onGetDNDMode(notDisturbPara);
            }
        }
    };

    public boolean isSupportGetDndMode() {
        return getSupportFunctionInfo().ex_main3_get_do_not_disturb;
    }

    public NotDisturbPara getDNDModeState() {
        NotDisturbPara notDisturbPara = LocalDataManager.getNotDisturbPara();
        if (notDisturbPara != null) {
            return notDisturbPara;
        }
        NotDisturbPara notDisturbPara2 = new NotDisturbPara();
        notDisturbPara2.onOFf = 85;
        notDisturbPara2.startHour = 9;
        notDisturbPara2.startMinute = 0;
        notDisturbPara2.endHour = 18;
        notDisturbPara2.endMinute = 0;
        notDisturbPara2.noontimeRestOnOff = 85;
        notDisturbPara2.noontimeRestStartHour = 22;
        notDisturbPara2.noontimeRestStartMinute = 0;
        notDisturbPara2.noontimeRestEndHour = 7;
        notDisturbPara2.noontimeRestEndMinute = 0;
        notDisturbPara2.setWeeks(new boolean[]{true, true, true, true, true, true, true});
        return notDisturbPara2;
    }

    public void getDNDModeFromDevice() {
        BLEManager.registerGetDeviceParaCallBack(this.mParaCallBack);
        BLEManager.getDoNotDisturbPara();
    }

    public void sendDNDMode2Device(NotDisturbPara notDisturbPara) {
        BLEManager.setNotDisturbPara(notDisturbPara);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.NOT_DISTURB) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_DO_NOT_DISTURB_FAILURE, "", null);
            if (isAttachView()) {
                ((IDNDModeView) getView()).onSetDNDModeFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.NOT_DISTURB) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_DO_NOT_DISTURB_SUCCESS, "", null);
            if (isAttachView()) {
                ((IDNDModeView) getView()).onSetDNDModeSuccess();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceParaCallBack(this.mParaCallBack);
    }
}