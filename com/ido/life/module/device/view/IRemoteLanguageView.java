package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import com.ido.life.data.api.entity.RemoteLanguage;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IRemoteLanguageView extends IBaseView {
    void onGetDeviceLanguageInfoFailed();

    void onGetDeviceLanguageInfoSuccess();

    void onLanguageProgress(int i, int i2);

    void onProgressComplete(int i, boolean z);

    void onRequestLanguageSuccess(int i, List<RemoteLanguage.LanguageInfo> list);
}