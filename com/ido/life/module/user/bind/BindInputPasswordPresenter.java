package com.ido.life.module.user.bind;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.local.UserTargetPreference;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.user.bind.BindInputPasswordContract;

/* JADX INFO: loaded from: classes3.dex */
public class BindInputPasswordPresenter implements BindInputPasswordContract.Presenter {
    private static final String TAG = "BindInputPasswordPresenter";
    private BindInputPasswordContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public BindInputPasswordPresenter(BindInputPasswordContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.Presenter
    public void checkSubmitEnable(String str) {
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str));
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.Presenter
    public void doJudgeEamilIsExist(String str) {
        this.mView.showLoading();
        AccountRepository.getInstance().judgeEamilIsExist(str, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindInputPasswordPresenter.1
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                CommonLogUtil.d(BindInputPasswordPresenter.TAG, "onSuccess: " + result.getData().toString());
                if (((Boolean) result.getData()).booleanValue()) {
                    BindInputPasswordPresenter.this.mView.goPasswordCode();
                } else {
                    BindInputPasswordPresenter.this.mView.showError(LanguageUtil.getLanguageText(R.string.me_request_error));
                }
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str2) {
                CommonLogUtil.d(BindInputPasswordPresenter.TAG, "onFailed: " + str2);
                BindInputPasswordPresenter.this.mView.showError(str2);
            }
        });
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.Presenter
    public void doBindAlreadyRegister(Long l, String str, String str2, final String str3, final String str4, final String str5) {
        this.mView.showLoading();
        AccountRepository.getInstance().getBindAlreadyRegisterCallback(l, str, str2, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindInputPasswordPresenter.2
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                CommonLogUtil.d(BindInputPasswordPresenter.TAG, "onSuccess: " + result.getData().toString());
                result.getData();
                BindInputPasswordPresenter.this.bindThirdAccount(str3, str4, str5);
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str6) {
                CommonLogUtil.d(BindInputPasswordPresenter.TAG, "onFailed: " + str6);
                BindInputPasswordPresenter.this.mView.showError(str6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThirdAccount(String str, String str2, String str3) {
        AccountRepository.getInstance().loginThirdThroughAccessToken(str, str2, "", str3, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindInputPasswordPresenter.3
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                AccountRepository.getInstance().saveUserToken((String) result.getData());
                BindInputPasswordPresenter.this.getUserInfo();
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str4) {
                BindInputPasswordPresenter.this.mView.showError(str4);
                CommonLogUtil.d(BindInputPasswordPresenter.TAG, "onFailed: " + str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.user.bind.BindInputPasswordPresenter.4
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                CommonLogUtil.d(BindInputPasswordPresenter.TAG, "onSuccess: " + userInfo.toString());
                if (userInfo != null) {
                    BindInputPasswordPresenter.this.judge(userInfo);
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                CommonLogUtil.d(BindInputPasswordPresenter.TAG, "onFailed: " + str);
                BindInputPasswordPresenter.this.mView.showError(str);
            }
        });
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.Presenter
    public void clickResetPassword(final String str) {
        if (TextUtils.isEmpty(str)) {
            this.mView.goForgetPassword(null);
        } else {
            AccountRepository.getInstance().validAccount(str, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindInputPasswordPresenter.5
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    if (BindInputPasswordPresenter.this.mView == null) {
                        return;
                    }
                    if (((Boolean) result.getData()).booleanValue()) {
                        BindInputPasswordPresenter.this.mView.goForgetPassword(str);
                    } else {
                        BindInputPasswordPresenter.this.mView.goForgetPassword(null);
                    }
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    if (BindInputPasswordPresenter.this.mView != null) {
                        BindInputPasswordPresenter.this.mView.goForgetPassword(null);
                    }
                }
            });
        }
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.Presenter
    public void judge(UserInfo userInfo) {
        if (TextUtils.isEmpty(userInfo.getDisplayName())) {
            this.mView.goUserData(-1);
            return;
        }
        if (userInfo.getGender() != 1 && userInfo.getGender() != 2) {
            this.mView.goUserData(-1);
            return;
        }
        if (TextUtils.isEmpty(userInfo.getBirthday())) {
            this.mView.goUserData(-1);
            return;
        }
        if (userInfo.getHeight() < 24.0f) {
            this.mView.goUserData(-1);
        } else if (userInfo.getWeight() < 20.0f) {
            this.mView.goUserData(-1);
        } else {
            getUserTarget();
        }
    }

    private void getUserTarget() {
        HealthRepository.getInstance().getUserTarget(new HealthManager.OnUserTargetCallback() { // from class: com.ido.life.module.user.bind.BindInputPasswordPresenter.6
            @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
            public void onSuccess(UserTargetEntity userTargetEntity) {
                int step = userTargetEntity.getResult().getStep();
                int weightInt = userTargetEntity.getResult().getWeightInt();
                if (userTargetEntity != null && userTargetEntity.getResult() != null) {
                    UserTargetNew userTarget = UserTargetPreference.getUserTarget();
                    if (userTarget == null) {
                        userTarget = new UserTargetNew();
                    }
                    userTarget.setStep(step);
                    userTarget.setWeight(weightInt);
                    userTarget.setHasUpload(userTargetEntity.getResult().getHasUpload());
                    userTarget.setUserId(userTargetEntity.getResult().getUserId());
                    UserTargetPreference.savaUserTarget(userTarget);
                }
                if (5000 > step || step > 25000 || weightInt <= 0) {
                    BindInputPasswordPresenter.this.mView.goUserTarget();
                    BindInputPasswordPresenter.this.mView.showError(LanguageUtil.getLanguageText(R.string.login_target_set_wrong));
                } else {
                    BindInputPasswordPresenter.this.mView.goMain();
                }
            }

            @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
            public void onFailed(String str) {
                BindInputPasswordPresenter.this.mView.showError(str);
            }
        });
    }
}