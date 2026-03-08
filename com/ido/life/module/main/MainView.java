package com.ido.life.module.main;

import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
interface MainView extends IBaseView {
    void initConfigFailed();

    void initConfigSuccess();

    void jump2UserDataActivity();

    void jump2UserDataActivityUserFailed(boolean z);

    void jump2UserTargetActivity();

    void jumpNotificationSettingPage();

    void onBindWrongDevice();

    void showHistoryDataOverLoadTip();

    void startInitConfig();
}