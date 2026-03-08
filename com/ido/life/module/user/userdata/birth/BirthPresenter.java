package com.ido.life.module.user.userdata.birth;

import android.os.Handler;
import android.text.TextUtils;
import com.ido.common.net.http.Result;
import com.ido.common.utils.TimeUtil;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.userdata.birth.BirthContract;

/* JADX INFO: loaded from: classes3.dex */
public class BirthPresenter implements BirthContract.Presenter {
    private BirthContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public BirthPresenter(BirthContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.Presenter
    public void initAge() {
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo == null || TextUtils.isEmpty(userInfo.getBirthday())) {
            return;
        }
        String[] strArrSplit = userInfo.getBirthday().split("-");
        this.mView.setYear(strArrSplit[0]);
        this.mView.setMonth(strArrSplit[1]);
        this.mView.setDay(strArrSplit[2]);
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.Presenter
    public void getAge(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            this.mView.setForwardEnable(true);
        } else {
            this.mView.setForwardEnable(false);
        }
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.Presenter
    public void checkAge(String str, String str2, String str3) {
        int i = Integer.parseInt(TimeUtil.getCurrentYearDate()) - Integer.parseInt(str);
        if (i > 0 && i <= 100 && Integer.parseInt(str2) >= 1 && Integer.parseInt(str2) <= 12 && Integer.parseInt(str3) >= 0 && Integer.parseInt(str3) <= 31) {
            updateBirthday(str, str2, str3);
        } else {
            new Handler().postDelayed(new Runnable() { // from class: com.ido.life.module.user.userdata.birth.BirthPresenter.1
                @Override // java.lang.Runnable
                public void run() {
                    BirthPresenter.this.mView.clearYear();
                    BirthPresenter.this.mView.clearMonth();
                    BirthPresenter.this.mView.clearDay();
                    BirthPresenter.this.mView.showMessage();
                }
            }, 1000L);
        }
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.Presenter
    public void saveBirthday(String str) {
        AccountRepository.getInstance().updateBirthday(str);
        this.mView.setForward();
    }

    private void updateBirthday(String str, String str2, String str3) {
        this.mView.showLoading();
        if (Integer.parseInt(str2) < 10) {
            str2 = 0 + str2;
        }
        if (Integer.parseInt(str3) < 10) {
            str = 0 + str3;
        }
        AccountRepository.getInstance().updateBirthday(str + "-" + str2 + "-" + str3, new OnResultCallback() { // from class: com.ido.life.module.user.userdata.birth.BirthPresenter.2
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                BirthPresenter.this.mView.hideLoading();
                AccountRepository.getInstance().updateBirthday(result.getData().toString());
                BirthPresenter.this.mView.setForward();
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str4) {
                BirthPresenter.this.mView.hideLoading();
                BirthPresenter.this.mView.showMessage(str4);
            }
        });
    }
}