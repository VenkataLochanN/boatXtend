package com.ido.life.module.user.userinfo;

import com.ido.life.base.IBaseLoadingView;

/* JADX INFO: loaded from: classes3.dex */
public interface IUserInfoView extends IBaseLoadingView {
    void onEmptyName();

    void saveInfoFailed(String str);

    void saveInfoSuccess();

    void setArea(String str);

    void setAvatarUrl(String str);

    void setAvatarUrlFailed(String str);

    void setAvatarUrlSuccess(String str);

    void setBirthday(String str);

    void setBirthday(int[] iArr, int[] iArr2, int[] iArr3);

    void setEmail(String str);

    void setGender(int i);

    void setHeight(String str);

    void setHeightUnit(String str);

    void setUserName(String str);

    void setWeight(String str);

    void setWeightUnit(String str);

    void showMessage(String str);

    void showPickDialog(int i, String str);
}