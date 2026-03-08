package com.ido.life.module.user.set.data;

import com.ido.life.module.user.set.data.DataShareContract;

/* JADX INFO: loaded from: classes3.dex */
public class DataSharePresenter implements DataShareContract.Presenter {
    private DataShareContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public DataSharePresenter(DataShareContract.View view) {
        this.mView = view;
    }
}