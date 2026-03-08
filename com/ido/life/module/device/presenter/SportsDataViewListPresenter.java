package com.ido.life.module.device.presenter;

import com.ido.life.bean.MotionTypeBean;
import com.ido.life.data.Func;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.ISportsDataViewListView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SportsDataViewListPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J \u0010\u0006\u001a\u00020\u00052\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0014J\u0006\u0010\u000b\u001a\u00020\u0005¨\u0006\f"}, d2 = {"Lcom/ido/life/module/device/presenter/SportsDataViewListPresenter;", "Lcom/ido/life/module/device/presenter/BaseMotionTypesPresenter;", "Lcom/ido/life/module/device/view/ISportsDataViewListView;", "()V", "onGetDeviceMotionTypesFailed", "", "onGetDeviceMotionTypesSuccess", "list", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/MotionTypeBean;", "Lkotlin/collections/ArrayList;", "queryBasicMotionTypes", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportsDataViewListPresenter extends BaseMotionTypesPresenter<ISportsDataViewListView> {
    public static final /* synthetic */ ISportsDataViewListView access$getView(SportsDataViewListPresenter sportsDataViewListPresenter) {
        return (ISportsDataViewListView) sportsDataViewListPresenter.getView();
    }

    public final void queryBasicMotionTypes() {
        getDeviceMotionTypes(false);
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onGetDeviceMotionTypesSuccess(final ArrayList<MotionTypeBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        super.onGetDeviceMotionTypesSuccess(list);
        addAutoCancelSubscriber(new Func<List<MotionTypeBean>>() { // from class: com.ido.life.module.device.presenter.SportsDataViewListPresenter.onGetDeviceMotionTypesSuccess.1
            /* JADX WARN: Removed duplicated region for block: B:10:0x0032  */
            @Override // com.ido.life.data.Func
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List<com.ido.life.bean.MotionTypeBean> call() {
                /*
                    Method dump skipped, instruction units count: 240
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.device.presenter.SportsDataViewListPresenter.AnonymousClass1.call():java.util.List");
            }
        }, new Callback<List<MotionTypeBean>>() { // from class: com.ido.life.module.device.presenter.SportsDataViewListPresenter.onGetDeviceMotionTypesSuccess.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(List<MotionTypeBean> data) {
                Intrinsics.checkParameterIsNotNull(data, "data");
                ISportsDataViewListView iSportsDataViewListViewAccess$getView = SportsDataViewListPresenter.access$getView(SportsDataViewListPresenter.this);
                if (iSportsDataViewListViewAccess$getView != null) {
                    iSportsDataViewListViewAccess$getView.onGetDeviceMotionTypesSuccess(data);
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                ISportsDataViewListView iSportsDataViewListViewAccess$getView = SportsDataViewListPresenter.access$getView(SportsDataViewListPresenter.this);
                if (iSportsDataViewListViewAccess$getView != null) {
                    iSportsDataViewListViewAccess$getView.onGetDeviceMotionTypesFailed();
                }
            }
        });
    }

    @Override // com.ido.life.module.device.presenter.BaseMotionTypesPresenter
    protected void onGetDeviceMotionTypesFailed() {
        ISportsDataViewListView iSportsDataViewListView = (ISportsDataViewListView) getView();
        if (iSportsDataViewListView != null) {
            iSportsDataViewListView.onGetDeviceMotionTypesFailed();
        }
    }
}