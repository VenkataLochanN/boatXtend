package com.ido.life.module.user.userdata;

import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.user.userdata.UserDataContract;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class UserDataPresenter implements UserDataContract.Presenter {
    private static final int STEP_PROGRESS = 25;
    private static final String TAG = "UserDataPresenter";
    private UserDataContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public UserDataPresenter(UserDataContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.Presenter
    public void back() {
        int index = this.mView.getIndex();
        if (index != 0) {
            int i = index - 1;
            this.mView.showCurrentPage(i);
            this.mView.showProgress(i * 25);
            return;
        }
        this.mView.back();
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.Presenter
    public void forward() {
        int index = this.mView.getIndex();
        if (index == 4) {
            judge();
            return;
        }
        int i = index + 1;
        this.mView.showCurrentPage(i);
        this.mView.showProgress(i * 25);
    }

    private void judge() {
        UserTargetNew userTarget = HealthRepository.getInstance().getUserTarget(-1L);
        if (RunTimeUtil.getInstance().getUserId() == -1) {
            if (userTarget.getStep() == -1 && userTarget.getWeight() == -1.0f) {
                this.mView.toUserTarget();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客模式：步数、体重都是-1，没有设置过，跳转设置目标界面");
            } else {
                this.mView.toMain();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客模式：目标设置过，跳转主页");
            }
            this.mView.hideLoading();
            return;
        }
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            if (userTarget.getStep() == -1 && userTarget.getWeight() == -1.0f) {
                this.mView.toUserTarget();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "无网络：步数、体重都是-1，没有设置过，跳转设置目标界面");
            } else {
                this.mView.toMain();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "无网络：目标设置过，跳转主页");
            }
            this.mView.hideLoading();
            return;
        }
        AccountRepository.getInstance().getUserTarget(new AccountManager.OnUserTargetCallback() { // from class: com.ido.life.module.user.userdata.UserDataPresenter.1
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserTargetCallback
            public void onSuccess(UserTargetNew userTargetNew) {
                if (userTargetNew.getStep() != 0 || userTargetNew.getWeight() != 0.0f) {
                    UserDataPresenter.this.mView.toMain();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), UserDataPresenter.TAG, "登录用户：目标设置过，跳转主界面");
                } else {
                    UserDataPresenter.this.mView.toUserTarget();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), UserDataPresenter.TAG, "登录用户：步数、体重都是0，没有设置过，跳转设置目标界面");
                }
                UserDataPresenter.this.mView.hideLoading();
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserTargetCallback
            public void onFailed(String str) {
                UserDataPresenter.this.mView.hideLoading();
                UserDataPresenter.this.mView.showMessage(str);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), UserDataPresenter.TAG, "登录用户：向服务器请求用户目标失败--" + str);
            }
        });
    }
}