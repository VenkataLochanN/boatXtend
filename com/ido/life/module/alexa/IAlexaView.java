package com.ido.life.module.alexa;

import com.ido.alexa.bean.AvsException;
import com.ido.alexa.data.UserCodeResponse;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
interface IAlexaView extends IBaseView {
    void loadDefaultAlexaLanguage(String str);

    void onAuthorizeCancel();

    void onAuthorizeFailed(Exception exc);

    void onAuthorizeSuccess(UserCodeResponse userCodeResponse);

    void onGetDeviceTokenByCBLAuthFailed(AvsException avsException);

    void onGetDeviceTokenByCBLAuthSuccess();

    void onGetTokenFailed(AvsException avsException);

    void onGetTokenSuccess(String str);

    void onLogoutFailed(AvsException avsException);

    void onLogoutSuccess();

    void onLostPackageData(String str);

    void switchLanguageResult(boolean z);
}