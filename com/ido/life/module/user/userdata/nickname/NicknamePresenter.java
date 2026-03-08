package com.ido.life.module.user.userdata.nickname;

import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.module.user.userdata.nickname.NicknameContract;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class NicknamePresenter implements NicknameContract.Presenter {
    private static final String TAG = "NicknamePresenter";
    private NicknameContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public NicknamePresenter(NicknameContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.Presenter
    public void checkNicknameSize(String str, String str2, boolean z) {
        if (str.length() <= 60) {
            saveNickname(str, str2, z);
            this.mView.toForward();
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "昵称名字超过允许长度nickname:" + str);
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.Presenter
    public void saveNickname(String str, String str2, boolean z) {
        AccountRepository.getInstance().updateDisplayName(str, str2, z);
    }

    @Override // com.ido.life.module.user.userdata.nickname.NicknameContract.Presenter
    public void updateProfile(String str) {
        this.mView.showLoading();
        this.mView.setAvatar(str);
        this.mView.saveAvatarPath(str, true);
        if (RunTimeUtil.getInstance().getUserId() == -1) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客模式注册，头像不用上传到服务器。");
            this.mView.hideLoading();
            this.mView.showMessage(LanguageUtil.getLanguageText(R.string.register_avatar_set_success));
        } else {
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                AccountRepository.getInstance().updateFile(str, new OnResultCallback() { // from class: com.ido.life.module.user.userdata.nickname.NicknamePresenter.1
                    @Override // com.ido.life.data.listener.OnResultCallback
                    public void onSuccess(Result result) {
                        NicknamePresenter.this.mView.hideLoading();
                        NicknamePresenter.this.mView.saveAvatarPath(result.getData().toString(), false);
                        NicknamePresenter.this.mView.showMessage(LanguageUtil.getLanguageText(R.string.register_avatar_set_success));
                        CommonLogUtil.d(NicknamePresenter.TAG, "onSuccess: " + result.getData().toString());
                    }

                    @Override // com.ido.life.data.listener.OnResultCallback
                    public void onFailed(String str2) {
                        NicknamePresenter.this.mView.hideLoading();
                        NicknamePresenter.this.mView.showMessage(LanguageUtil.getLanguageText(R.string.register_avatar_set_success));
                        CommonLogUtil.d(NicknamePresenter.TAG, "onFailed: " + str2);
                    }
                });
                return;
            }
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "登录用户上传头像，但是此时没有网络，就将头像保存到本地");
            this.mView.hideLoading();
            this.mView.showMessage(LanguageUtil.getLanguageText(R.string.register_avatar_set_success));
        }
    }
}