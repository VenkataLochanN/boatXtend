package com.ido.life.module.user.set.modify;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.net.http.Result;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.set.modify.ModifyPwdContract;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyPwdPresenter implements ModifyPwdContract.Presenter {
    private static final String TAG = "ModifyPwdPresenter";
    private String account;
    private ModifyPwdContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public ModifyPwdPresenter(ModifyPwdContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.Presenter
    public void initData() {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            this.account = userInfoQueryUserInfo.getEmail();
        }
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.Presenter
    public void checkSubmitEnable(String str, String str2, String str3) {
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && str.length() >= 6 && str2.length() >= 6 && str3.length() >= 6 && str2.length() == str3.length());
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.Presenter
    public void doResetPassword(String str, String str2, String str3) {
        this.mView.showLoading();
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(ResourceUtil.getString(R.string.public_net_unuse));
            this.mView.hideLoading();
        } else if (str2.equals(str3)) {
            AccountRepository.getInstance().requestPasswordModify(str, str2, new OnResultCallback() { // from class: com.ido.life.module.user.set.modify.ModifyPwdPresenter.1
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    ModifyPwdPresenter.this.mView.showSuccess();
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str4) {
                    ModifyPwdPresenter.this.mView.showError(str4);
                }
            });
        } else {
            this.mView.hideLoading();
            NormalToast.showToast(ResourceUtil.getString(R.string.me_modify_password_inconsistent));
        }
    }
}