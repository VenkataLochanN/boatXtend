package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.module.device.view.INoiseMonitoringView;
import com.ido.life.util.SPHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoiseMonitoringPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ido/life/module/device/presenter/NoiseMonitoringPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/INoiseMonitoringView;", "()V", "mNoisePara", "Lcom/ido/ble/protocol/model/NoisePara;", "getNoisePara", "defaultMode", "", "onSetCmdFailed", "", "settingType", "Lcom/ido/ble/callback/SettingCallBack$SettingType;", "onSetCmdSuccess", "o", "", "sendNoiseParam", "param", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NoiseMonitoringPresenter extends BaseCmdPresenter<INoiseMonitoringView> {
    private NoisePara mNoisePara;

    public final void sendNoiseParam(NoisePara param) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        this.mNoisePara = param;
        BLEManager.setNoisePara(param);
    }

    public final NoisePara getNoisePara(boolean defaultMode) {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        NoisePara noiseMode = SPHelper.getNoiseMode(currentDeviceInfo != null ? currentDeviceInfo.mDeviceAddress : null);
        if (noiseMode == null) {
            noiseMode = new NoisePara();
            noiseMode.mode = defaultMode ? 170 : 85;
        }
        return noiseMode;
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object o) {
        if (settingType == SettingCallBack.SettingType.NOISE) {
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            SPHelper.setNoiseMode(currentDeviceInfo != null ? currentDeviceInfo.mDeviceAddress : null, this.mNoisePara);
            INoiseMonitoringView iNoiseMonitoringView = (INoiseMonitoringView) getView();
            if (iNoiseMonitoringView != null) {
                iNoiseMonitoringView.onSetNoiseSuccess();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        INoiseMonitoringView iNoiseMonitoringView;
        if (settingType != SettingCallBack.SettingType.NOISE || (iNoiseMonitoringView = (INoiseMonitoringView) getView()) == null) {
            return;
        }
        iNoiseMonitoringView.onSetNoiseFailed();
    }
}