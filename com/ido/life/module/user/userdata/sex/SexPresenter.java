package com.ido.life.module.user.userdata.sex;

import com.ido.life.data.me.AccountRepository;
import com.ido.life.module.user.userdata.sex.SexContract;

/* JADX INFO: loaded from: classes3.dex */
public class SexPresenter implements SexContract.Presenter {
    private SexContract.View mView;

    @Override // com.ido.life.module.user.userdata.sex.SexContract.Presenter
    public void initSex() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SexPresenter(SexContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.userdata.sex.SexContract.Presenter
    public void saveSex(int i) {
        AccountRepository.getInstance().updateGender(i);
        this.mView.toForward();
    }
}