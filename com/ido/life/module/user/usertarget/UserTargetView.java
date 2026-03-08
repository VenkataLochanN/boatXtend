package com.ido.life.module.user.usertarget;

import com.ido.life.base.IBaseLoadingView;

/* JADX INFO: loaded from: classes3.dex */
public interface UserTargetView extends IBaseLoadingView {
    void onDeviceDisConnected();

    void refreshUnitSetting(int i);

    void setStep(int i);

    void setWeight(int i, int i2);

    void showFirstSuccess();

    void showMessage(String str);

    void showSuccess();
}