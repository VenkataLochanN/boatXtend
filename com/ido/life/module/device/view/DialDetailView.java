package com.ido.life.module.device.view;

import com.ido.life.data.api.entity.MyDialListEntity;

/* JADX INFO: loaded from: classes2.dex */
public interface DialDetailView extends BaseDialView {
    void onCollectDial(boolean z);

    void onDeleteDial(boolean z);

    void onDialInstallFailed();

    void onDialInstallSuccess();

    void onDialSwitched(boolean z);

    void onDownloadFailed();

    void onGetDialInfo(MyDialListEntity.DialInfo dialInfo);

    void onGetDialState(boolean z);

    void onInstallFailByNotMemory();

    void onProgress(int i, int i2);

    void onRemoveDial(boolean z);

    void onSwitchDialStart();
}