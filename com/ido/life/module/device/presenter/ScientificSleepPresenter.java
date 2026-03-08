package com.ido.life.module.device.presenter;

import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.device.view.IScientificSleepView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScientificSleepPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u001c\u0010\n\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/device/presenter/ScientificSleepPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/IScientificSleepView;", "()V", "getSleepMonitoringPara", "Lcom/ido/ble/protocol/model/SleepMonitoringPara;", "onSetCmdFailed", "", "settingType", "Lcom/ido/ble/callback/SettingCallBack$SettingType;", "onSetCmdSuccess", "o", "", "sendMonitoring", "param", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ScientificSleepPresenter extends BaseCmdPresenter<IScientificSleepView> {
    public final SleepMonitoringPara getSleepMonitoringPara() {
        SleepMonitoringPara sleepMonitoringPara = LocalDataManager.getSleepMonitoringPara();
        if (sleepMonitoringPara != null) {
            return sleepMonitoringPara;
        }
        SleepMonitoringPara sleepMonitoringPara2 = new SleepMonitoringPara();
        sleepMonitoringPara2.mode = 170;
        return sleepMonitoringPara2;
    }

    public final void sendMonitoring(SleepMonitoringPara param) {
        if (param != null) {
            String string = param.toString();
            if (!Intrinsics.areEqual(string, getSleepMonitoringPara() != null ? r1.toString() : null)) {
                CommonLogUtil.d("sendMonitoring param = " + param);
                BLEManager.setSleepMonitoringPara(param);
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object o) {
        if (settingType == SettingCallBack.SettingType.SLEEP_MONITORING) {
            CommonLogUtil.d("sendMonitoring onSetCmdSuccess");
            IScientificSleepView iScientificSleepView = (IScientificSleepView) getView();
            if (iScientificSleepView != null) {
                iScientificSleepView.onSetScientificSleepSuccess();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        if (settingType == SettingCallBack.SettingType.SLEEP_MONITORING) {
            CommonLogUtil.d("sendMonitoring onSetCmdFailed");
            if (getView() != 0) {
                IScientificSleepView iScientificSleepView = (IScientificSleepView) getView();
                if (iScientificSleepView != null) {
                    iScientificSleepView.onSetScientificSleepFailed();
                    return;
                }
                return;
            }
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_set_failed));
        }
    }
}