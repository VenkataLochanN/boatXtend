package com.ido.life.module.user;

import com.ido.life.base.IBaseView;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.module.user.UserFragment;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IUserView extends IBaseView {
    void flashLogCollectionFailed();

    void flashLogCollectionSuccess();

    void onGetDeviceInstruction(String str);

    void onGetNewVersionFailed(String str);

    void onGetNewVersionSuccess(AppInfoEntity.AppInfo appInfo);

    void onGetUserMedalFailed(String str);

    void onGetUserMedalSuccess(List<UserFragment.UserModelnfo> list);

    void onUnReadMessageCount(long j);

    void setAvatarUrl(String str);

    void setEmailAddress(String str);

    void setUserName(String str);

    void showLoginPage();

    void showUnLoginPage();
}