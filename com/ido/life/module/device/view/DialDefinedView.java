package com.ido.life.module.device.view;

import com.ido.life.data.api.entity.DialDefinedEntityNew;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;
import com.ido.life.data.api.entity.MyDialListEntity;

/* JADX INFO: loaded from: classes2.dex */
public interface DialDefinedView extends BaseDialView {
    void getDefinedEntity(DialDefinedEntityNew dialDefinedEntityNew);

    void getDefinedFuctionEntity(DialDefinedFunctionEntity dialDefinedFunctionEntity);

    void onAddAccountsuccess();

    void onCollectDial(boolean z);

    void onDeleteDial(boolean z);

    void onDialInstallFailed();

    void onDialInstallSuccess();

    void onDialSwitched(boolean z);

    void onDownloadFailed();

    void onDownloadsuccess();

    void onGetDialInfo(MyDialListEntity.DialInfo dialInfo);

    void onGetDialState(boolean z);

    void onInstallFailByNotMemory();

    void onProgress(int i, int i2);

    void onSwitchDialStart();
}