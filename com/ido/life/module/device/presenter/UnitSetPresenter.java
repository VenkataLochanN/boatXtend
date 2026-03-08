package com.ido.life.module.device.presenter;

import android.text.format.DateFormat;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.Units;
import com.ido.common.IdoApp;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.Language;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.module.device.view.IUnitSetView;
import com.ido.life.util.LanguageManager;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UnitSetPresenter extends BaseCmdPresenter<IUnitSetView> {
    public int getTimeFormat() {
        Units units;
        if (SPHelper.isTimeFollowSys() || (units = LocalDataManager.getUnits()) == null) {
            return 0;
        }
        return units.timeMode;
    }

    public void sendTimeFormat2Device(int i) {
        SPHelper.setTimeFormat(i);
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            units = new Units();
        }
        if (i == 0) {
            units.timeMode = DateFormat.is24HourFormat(IdoApp.getAppContext()) ? 1 : 2;
        } else {
            units.timeMode = i;
        }
        BLEManager.setUnit(units);
    }

    public List<Language> getSupportLanguageList() {
        return LanguageManager.getSupportLanguageList(LocalDataManager.getSupportFunctionInfo());
    }

    public int getSelectedLanguage() {
        Units units;
        if (SPHelper.isLanguageFollowSys() || (units = LocalDataManager.getUnits()) == null) {
            return 0;
        }
        return units.language;
    }

    public void sendLanguage2Device(int i) {
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            units = new Units();
        }
        boolean z = i == 0;
        SPHelper.setLanguageMode(z);
        if (z) {
            DeviceConfigHelper.setUnitsFlowSystem(true);
            try {
                i = DeviceConfigHelper.formatLanguageCode(LocalDataManager.getUnits());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            units.language = i;
            BLEManager.setUnit(units);
        }
        EventBusHelper.postSticky(new BaseMessage(Constants.EventConstants.EVENT_SWITCH_LANGUAGE, Integer.valueOf(i)));
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView() && settingType == SettingCallBack.SettingType.UNIT) {
            ((IUnitSetView) getView()).onSetUnitFailed();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (isAttachView() && settingType == SettingCallBack.SettingType.UNIT) {
            ((IUnitSetView) getView()).onSetUnitSuccess();
        }
    }
}