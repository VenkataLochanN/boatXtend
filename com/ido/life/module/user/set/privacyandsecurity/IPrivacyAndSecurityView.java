package com.ido.life.module.user.set.privacyandsecurity;

import com.ido.life.base.IBaseLoadingView;
import com.ido.life.database.model.PrivateSafeSetting;

/* JADX INFO: loaded from: classes3.dex */
public interface IPrivacyAndSecurityView extends IBaseLoadingView {
    void deleteServerDataFailed();

    void deleteServerDataSuccess();

    void getConfigFailed();

    void getConfigSuccess(PrivateSafeSetting privateSafeSetting);

    void onConfigFailed();

    void onConfigSuccess();
}