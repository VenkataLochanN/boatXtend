package com.ido.life.data.me;

import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.data.me.remote.ThirdLoginManager;
import com.ido.life.database.model.Feedback;
import com.ido.life.database.model.ThirdLogin;
import com.ido.life.database.model.UserInfo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IAccountRepository {
    void bindThirdAccount(String str, String str2, AccountManager.OnCommCallback<String> onCommCallback);

    void cancelAccount(String str, String str2, OnResultCallback onResultCallback);

    void cancelThirdAccount(String str, String str2, AccountManager.OnCommCallback<Boolean> onCommCallback);

    void checkEmail(String str, OnResultCallback onResultCallback);

    void clearToken();

    void clearUserInfo();

    void getBindAlreadyRegisterCallback(Long l, String str, String str2, OnResultCallback onResultCallback);

    UserInfo getUserInfo();

    void getUserInfo(AccountManager.OnUserCallback onUserCallback);

    void getUserTarget(AccountManager.OnUserTargetCallback onUserTargetCallback);

    boolean isUserSignIn();

    void judgeEamilIsExist(String str, OnResultCallback onResultCallback);

    void loginThirdThroughAccessToken(String str, String str2, String str3, String str4, OnResultCallback onResultCallback);

    void modifyEmail(String str, String str2, OnResultCallback onResultCallback);

    void queryThirdAccount(String str, AccountManager.OnCommCallback<List<ThirdLogin>> onCommCallback);

    void requestBindSignUp(long j, String str, String str2, String str3, String str4, OnResultCallback onResultCallback);

    void requestCheckCode(String str, String str2, String str3, OnResponseCallback onResponseCallback);

    void requestGetCode(String str, String str2, String str3, OnResponseCallback onResponseCallback);

    void requestPasswordModify(String str, String str2, OnResultCallback onResultCallback);

    void requestPasswordReset(String str, String str2, String str3, OnResultCallback onResultCallback);

    void requestSignIn(String str, String str2, String str3, OnResultLoginRegisterCallback onResultLoginRegisterCallback);

    void requestSignUp(String str, String str2, String str3, String str4, String str5, String str6, String str7, OnResultLoginRegisterCallback onResultLoginRegisterCallback);

    void saveUserInfo(UserInfo userInfo);

    void saveUserToken(String str);

    void thirdLogin(int i, ThirdLoginManager.OnThirdCallback onThirdCallback);

    void thirdLogin(String str, ThirdLoginManager.OnThirdCallback onThirdCallback);

    void toCreateFeedback(Feedback feedback, OnResultCallback onResultCallback);

    void updateAvatar(String str);

    void updateBirthday(String str);

    void updateBirthday(String str, OnResultCallback onResultCallback);

    void updateDisplayName(String str, OnResultCallback onResultCallback);

    void updateDisplayName(String str, String str2, boolean z);

    void updateFile(String str, OnResultCallback onResultCallback);

    void updateFileFdImg(String str, OnResultCallback onResultCallback);

    void updateFileFeedback(String str, OnResultCallback onResultCallback);

    void updateGender(int i);

    void updateGender(int i, OnResultCallback onResultCallback);

    void updateHeight(float f2, int i);

    void updateHeight(int i, int i2, OnResultCallback onResultCallback);

    void updateUserInfo(UserInfo userInfo, OnResultCallback onResultCallback);

    void updateUserInfoFirstLogin(String str, String str2, int i, int i2, int i3, float f2, int i4, String str3, OnResultCallback onResultCallback);

    void updateWeight(float f2, int i);

    void updateWeight(int i, int i2, OnResultCallback onResultCallback);

    void validAccount(String str, OnResultCallback onResultCallback);
}