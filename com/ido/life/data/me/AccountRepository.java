package com.ido.life.data.me;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.data.me.remote.ThirdLoginManager;
import com.ido.life.database.model.Feedback;
import com.ido.life.database.model.ThirdLogin;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AccountRepository implements IAccountRepository {
    private static final String TAG = "AccountRepository";
    private static AccountRepository mInstance = new AccountRepository();

    public static AccountRepository getInstance() {
        return mInstance;
    }

    private AccountRepository() {
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void thirdLogin(int i, ThirdLoginManager.OnThirdCallback onThirdCallback) {
        ThirdLoginManager.thirdLogin(i, onThirdCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void thirdLogin(String str, ThirdLoginManager.OnThirdCallback onThirdCallback) {
        ThirdLoginManager.thirdLogin(str, onThirdCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void loginThirdThroughAccessToken(String str, String str2, String str3, String str4, OnResultCallback onResultCallback) {
        AccountManager.loginThirdThroughAccessToken(str, str2, str3, str4, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void queryThirdAccount(String str, AccountManager.OnCommCallback<List<ThirdLogin>> onCommCallback) {
        AccountManager.queryThirdAccount(str, onCommCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void cancelThirdAccount(String str, String str2, AccountManager.OnCommCallback<Boolean> onCommCallback) {
        AccountManager.cancelThirdAccount(str, str2, onCommCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void bindThirdAccount(String str, String str2, AccountManager.OnCommCallback<String> onCommCallback) {
        AccountManager.bindThirdAccount(str, str2, onCommCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public boolean isUserSignIn() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "判断用户是否已登录");
        return AuthorizationPreference.isUserSignIn(IdoApp.getAppContext());
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void clearToken() {
        AuthorizationPreference.clear();
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void clearUserInfo() {
        GreenDaoUtil.clearTable(GreenDaoUtil.getDaoSession().getUserInfoDao());
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateGender(int i) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            userInfoQueryUserInfo.setUploadSuccess(false);
            userInfoQueryUserInfo.setGender(i);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "本地变更用户的gender：" + i);
            userInfoQueryUserInfo.update();
        }
    }

    public int getUserGender() {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            return userInfoQueryUserInfo.getGender();
        }
        return -1;
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateBirthday(String str) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            userInfoQueryUserInfo.setBirthday(str);
            userInfoQueryUserInfo.setUploadSuccess(false);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "本地变更用户的birthday：" + str);
            userInfoQueryUserInfo.update();
        }
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateHeight(float f2, int i) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            userInfoQueryUserInfo.setHeight(f2);
            userInfoQueryUserInfo.setHeightUnit(i);
            userInfoQueryUserInfo.setUploadSuccess(false);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "本地变更用户的身高、单位：" + f2 + ",heightUnit:" + i);
            userInfoQueryUserInfo.update();
        }
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateWeight(float f2, int i) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            userInfoQueryUserInfo.setWeight(f2);
            userInfoQueryUserInfo.setWeightUnit(i);
            String str = (String) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, "");
            if (!TextUtils.isEmpty(str)) {
                String strTrim = str.trim();
                try {
                    userInfoQueryUserInfo.setAreaCode(strTrim);
                    userInfoQueryUserInfo.setCountry(ResourceUtil.getString(ResourceUtil.getResources().getIdentifier("country_" + strTrim, "string", IdoApp.getAppContext().getPackageName())));
                } catch (Exception e2) {
                    SPUtils.put(Constants.CHOOSE_COUNTRY_CODE, "");
                    e2.printStackTrace();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "用户信息国家码错误 countryCode=" + strTrim);
                }
            }
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "本地设置保存用户的体重、单位：" + f2 + ",weightUnit:" + i + ",本地设置的用户国家和区号：" + userInfoQueryUserInfo.getAreaCode() + AppInfo.DELIM + userInfoQueryUserInfo.getCountry());
            userInfoQueryUserInfo.setUploadSuccess(false);
            userInfoQueryUserInfo.update();
            String loginRegisterLogPath = LogPathImpl.getInstance().getLoginRegisterLogPath();
            StringBuilder sb = new StringBuilder();
            sb.append("updateWeight: 更新体重时保存的userInfo：");
            sb.append(userInfoQueryUserInfo.toString());
            CommonLogUtil.printAndSave(loginRegisterLogPath, TAG, sb.toString());
            WeightItemBean weightItemBean = new WeightItemBean();
            weightItemBean.setTotalWeight(f2);
            weightItemBean.setUserId(RunTimeUtil.getInstance().getUserId());
            weightItemBean.setBmi(WeightBmiEnum.caluteBmi(f2, userInfoQueryUserInfo.getHeightCm()));
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            weightItemBean.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
            weightItemBean.setTimeStamp(calendar.getTimeInMillis());
            GreenDaoUtil.addWeight(weightItemBean);
        }
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateDisplayName(String str, String str2, boolean z) {
        long userId = RunTimeUtil.getInstance().getUserId();
        if (userId == -1) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(-1L);
            userInfo.setDisplayName(str);
            userInfo.setServerImageUrl(!z);
            userInfo.setAvatarUrl(str2);
            saveUserInfo(userInfo);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "updateDisplayName: 游客 " + userInfo.toString());
            return;
        }
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(userId);
        if (userInfoQueryUserInfo != null) {
            userInfoQueryUserInfo.setUploadSuccess(false);
            userInfoQueryUserInfo.setDisplayName(str);
            userInfoQueryUserInfo.setAvatarUrl(str2);
            userInfoQueryUserInfo.setServerImageUrl(!z);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "updateDisplayName: 非游客 " + userInfoQueryUserInfo.toString());
            userInfoQueryUserInfo.update();
        }
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateAvatar(String str) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            userInfoQueryUserInfo.setAvatarUrl(str);
            userInfoQueryUserInfo.setUploadSuccess(true);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "本地变更用户的avatar：" + str);
            userInfoQueryUserInfo.update();
        }
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public UserInfo getUserInfo() {
        UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
        if (userInfoQueryLatestUserInfo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "获取本地保存的用户信息：" + userInfoQueryLatestUserInfo.toString());
        }
        return userInfoQueryLatestUserInfo;
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void requestSignIn(String str, String str2, String str3, OnResultLoginRegisterCallback onResultLoginRegisterCallback) {
        AccountManager.requestSignIn(str, str2, str3, onResultLoginRegisterCallback);
        CommonLogUtil.d(TAG, "用户请求登录name：" + str2 + ",password:" + str3 + ",countryCode:" + str);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void getBindAlreadyRegisterCallback(Long l, String str, String str2, OnResultCallback onResultCallback) {
        AccountManager.bindAlreadyRegister(l, str, str2, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void requestBindSignUp(long j, String str, String str2, String str3, String str4, OnResultCallback onResultCallback) {
        AccountManager.requestBindSignUp(j, str, str2, str3, str4, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void judgeEamilIsExist(String str, OnResultCallback onResultCallback) {
        AccountManager.judgeEamilIsExist(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void getUserInfo(AccountManager.OnUserCallback onUserCallback) {
        AccountManager.getUserInfo(onUserCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void getUserTarget(AccountManager.OnUserTargetCallback onUserTargetCallback) {
        AccountManager.getUserTarget(onUserTargetCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void saveUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        GreenDaoUtil.addUserInfo(userInfo);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void saveUserToken(String str) {
        AuthorizationPreference.setToken(IdoApp.getAppContext(), str);
    }

    public static String getToken(Context context) {
        return AuthorizationPreference.getToken(context);
    }

    public static void setIsNewUser(boolean z) {
        AuthorizationPreference.setIsNewUser(IdoApp.getAppContext(), z);
    }

    public static boolean getIsNewUser() {
        return AuthorizationPreference.getIsNewUser(IdoApp.getAppContext());
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void requestGetCode(String str, String str2, String str3, OnResponseCallback onResponseCallback) {
        AccountManager.requestGetCode(str, str2, str3, onResponseCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void requestCheckCode(String str, String str2, String str3, OnResponseCallback onResponseCallback) {
        AccountManager.requestCheckCode(str, str2, str3, onResponseCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void requestSignUp(String str, String str2, String str3, String str4, String str5, String str6, String str7, OnResultLoginRegisterCallback onResultLoginRegisterCallback) {
        AccountManager.requestSignUp(str, str2, str3, str4, str5, str6, str7, onResultLoginRegisterCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void validAccount(String str, OnResultCallback onResultCallback) {
        AccountManager.validAccount(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void requestPasswordReset(String str, String str2, String str3, OnResultCallback onResultCallback) {
        AccountManager.requestPasswordReset(str, str2, str3, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void requestPasswordModify(String str, String str2, OnResultCallback onResultCallback) {
        AccountManager.requestPasswordModify(str, str2, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateGender(int i, OnResultCallback onResultCallback) {
        AccountManager.updateGender(i, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateDisplayName(String str, OnResultCallback onResultCallback) {
        AccountManager.updateUserDisplayName(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateHeight(int i, int i2, OnResultCallback onResultCallback) {
        AccountManager.updateHeight(i, i2, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateWeight(int i, int i2, OnResultCallback onResultCallback) {
        AccountManager.updateWeight(i, i2, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateBirthday(String str, OnResultCallback onResultCallback) {
        AccountManager.updateUserBirthday(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateFile(String str, OnResultCallback onResultCallback) {
        AccountManager.updateHead(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateFileFdImg(String str, OnResultCallback onResultCallback) {
        AccountManager.updateFileFdImage(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateUserInfo(UserInfo userInfo, OnResultCallback onResultCallback) {
        if (userInfo == null) {
            return;
        }
        AccountManager.updateUserInfo(userInfo, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateUserInfoFirstLogin(String str, String str2, int i, int i2, int i3, float f2, int i4, String str3, OnResultCallback onResultCallback) {
        AccountManager.updateUserInfoFirstLogin(str, str2, i, i2, i3, f2, i4, str3, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void cancelAccount(String str, String str2, OnResultCallback onResultCallback) {
        AccountManager.cancelAccount(str, str2, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void checkEmail(String str, OnResultCallback onResultCallback) {
        AccountManager.checkEmail(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void modifyEmail(String str, String str2, OnResultCallback onResultCallback) {
        AccountManager.modifyEmail(str, str2, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void updateFileFeedback(String str, OnResultCallback onResultCallback) {
        AccountManager.updateFileFd(str, onResultCallback);
    }

    @Override // com.ido.life.data.me.IAccountRepository
    public void toCreateFeedback(Feedback feedback, OnResultCallback onResultCallback) {
        AccountManager.createFeedback(feedback, onResultCallback);
    }
}