package com.ido.life.module.user.about;

import com.ido.common.log.CommonLogUtil;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.module.user.about.AboutUsContract;

/* JADX INFO: loaded from: classes.dex */
public class AboutUsPresenter implements AboutUsContract.Presenter {
    private AboutUsContract.View mView;

    @Override // com.ido.life.module.user.about.AboutUsContract.Presenter
    public void initData() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public AboutUsPresenter(AboutUsContract.View view) {
        this.mView = view;
    }

    public void requestVersionInfo() {
        AccountManager.requestAppVersionInfo(new AccountManager.OnCommCallback<AppInfoEntity.AppInfo>() { // from class: com.ido.life.module.user.about.AboutUsPresenter.1
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(AppInfoEntity.AppInfo appInfo) {
                if (AboutUsPresenter.this.mView == null || appInfo == null) {
                    return;
                }
                AboutUsPresenter.this.mView.onGetNewVersion(appInfo.getVersion());
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str) {
                CommonLogUtil.d("requestVersionInfo onFailed = " + str);
            }
        });
    }

    public void detachView() {
        this.mView = null;
    }
}