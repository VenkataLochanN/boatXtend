package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.module.device.view.IFitnessGuidanceView;
import com.ido.life.util.SPHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FitnessGuidancePresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u0004\u0018\u00010\u0005J\u0013\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u001a\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/ido/life/module/device/presenter/FitnessGuidancePresenter;", "Lcom/ido/life/module/device/presenter/BaseMonitoringPresenter;", "Lcom/ido/life/module/device/view/IFitnessGuidanceView;", "()V", "fitnessGuidance", "Lcom/ido/ble/protocol/model/FitnessGuidance;", "mItemArr", "", "", "[Ljava/lang/String;", "getFitnessGuidance", "getGoalStepItems", "()[Ljava/lang/String;", d.m, "", "isSupportFitnessGuidance", "", "isSupportSetWalkRemindNotifyFlag", "onSetCmdFailed", "settingType", "Lcom/ido/ble/callback/SettingCallBack$SettingType;", "onSetCmdSuccess", "o", "", "sendFitnessGuide2Device", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FitnessGuidancePresenter extends BaseMonitoringPresenter<IFitnessGuidanceView> {
    private FitnessGuidance fitnessGuidance;
    private String[] mItemArr;

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        removeCallBack();
    }

    public final FitnessGuidance getFitnessGuidance() {
        this.fitnessGuidance = SPHelper.getFitnessGuidance();
        return this.fitnessGuidance;
    }

    public final boolean isSupportFitnessGuidance() {
        return getSupportFunctionInfo().support_set_fitness_guidance;
    }

    public final boolean isSupportSetWalkRemindNotifyFlag() {
        return getSupportFunctionInfo().V3_walk_reminder_add_notify_flag;
    }

    public final void sendFitnessGuide2Device(FitnessGuidance fitnessGuidance) {
        Intrinsics.checkParameterIsNotNull(fitnessGuidance, "fitnessGuidance");
        this.fitnessGuidance = fitnessGuidance;
        CommonLogUtil.d("sendFitnessGuide2Device fitnessGuidance = " + fitnessGuidance);
        BLEManager.setFitnessGuidance(fitnessGuidance);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        Intrinsics.checkParameterIsNotNull(settingType, "settingType");
        CommonLogUtil.d("onSetCmdFailed");
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.FITNESS_GUIDANCE) {
            this.fitnessGuidance = (FitnessGuidance) null;
            CommonLogUtil.d("sendFitnessGuide2Device onSetCmdFailed");
            IFitnessGuidanceView iFitnessGuidanceView = (IFitnessGuidanceView) getView();
            if (iFitnessGuidanceView != null) {
                iFitnessGuidanceView.onSetFitnessGuidanceFailed();
            }
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter, com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object o) {
        Intrinsics.checkParameterIsNotNull(settingType, "settingType");
        super.onSetCmdSuccess(settingType, o);
        CommonLogUtil.d("onSetCmdSuccess settingType = " + settingType);
        if (settingType == SettingCallBack.SettingType.FITNESS_GUIDANCE) {
            CommonLogUtil.d("sendFitnessGuide2Device onSetCmdSuccess");
            SPHelper.saveFitnessGuidance(this.fitnessGuidance);
            IFitnessGuidanceView iFitnessGuidanceView = (IFitnessGuidanceView) getView();
            if (iFitnessGuidanceView != null) {
                iFitnessGuidanceView.onSetFitnessGuidanceSuccess();
            }
        }
    }

    public final String[] getGoalStepItems() {
        if (this.mItemArr == null) {
            this.mItemArr = new String[18];
            for (int i = 0; i < 18; i++) {
                String[] strArr = this.mItemArr;
                if (strArr == null) {
                    Intrinsics.throwNpe();
                }
                strArr[i] = String.valueOf((i * 25) + 75);
            }
        }
        String[] strArr2 = this.mItemArr;
        if (strArr2 == null) {
            Intrinsics.throwNpe();
        }
        return strArr2;
    }
}