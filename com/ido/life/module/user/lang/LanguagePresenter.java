package com.ido.life.module.user.lang;

import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.module.user.lang.LanguageActivity;
import com.ido.life.module.user.lang.LanguageContract;
import com.ido.life.util.LanguageManager;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class LanguagePresenter implements LanguageContract.Presenter {
    private static final String TAG = "LanguagePresenter";
    private String mRegion;
    private LanguageContract.View view;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public LanguagePresenter(LanguageContract.View view) {
        this.view = (LanguageContract.View) Objects.requireNonNull(view);
        view.setPresenter(this);
    }

    @Override // com.ido.life.module.user.lang.LanguageContract.Presenter
    public void doChangeLanguage(String str) {
        this.mRegion = str;
        CommonLogUtil.d(TAG, "doChangeLanguage: " + this.mRegion);
        save();
    }

    @Override // com.ido.life.module.user.lang.LanguageContract.Presenter
    public void save() {
        CommonLogUtil.d(TAG, "save: " + this.mRegion);
        LanguageManager.setLanguage(IdoApp.getAppContext(), this.mRegion);
    }

    @Override // com.ido.life.module.user.lang.LanguageContract.Presenter
    public void getMultilLanguage(String str, LanguageActivity.FinishCallBack finishCallBack) {
        com.ido.life.data.me.remote.LanguageManager.getAppMultiLang(str, finishCallBack);
    }
}