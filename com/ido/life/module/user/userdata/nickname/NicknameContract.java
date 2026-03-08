package com.ido.life.module.user.userdata.nickname;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class NicknameContract {

    interface Presenter extends BasePresenter {
        void checkNicknameSize(String str, String str2, boolean z);

        void saveNickname(String str, String str2, boolean z);

        void updateProfile(String str);
    }

    interface View extends BaseView<Presenter> {
        void changeForwardEnable(boolean z);

        void hideLoading();

        void saveAvatarPath(String str, boolean z);

        void setAvatar(String str);

        void showLoading();

        void showMessage(String str);

        void toForward();
    }
}