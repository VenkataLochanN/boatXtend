package com.ido.life.module.device.presenter;

import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.data.Func;
import com.ido.life.data.cache.WorldTimeCityManager;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IWorldTimeListView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeListPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u001c\u0010\n\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¨\u0006\u0012"}, d2 = {"Lcom/ido/life/module/device/presenter/WorldTimeListPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/IWorldTimeListView;", "()V", "getWorldTimeList", "", d.m, "onSetCmdFailed", "settingType", "Lcom/ido/ble/callback/SettingCallBack$SettingType;", "onSetCmdSuccess", "o", "", "setWorldTime", "", "cities", "", "Lcom/ido/life/bean/WorldTimeCity;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeListPresenter extends BaseCmdPresenter<IWorldTimeListView> {
    public static final /* synthetic */ IWorldTimeListView access$getView(WorldTimeListPresenter worldTimeListPresenter) {
        return (IWorldTimeListView) worldTimeListPresenter.getView();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        removeCallBack();
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object o) {
        IWorldTimeListView iWorldTimeListView;
        if (settingType != SettingCallBack.SettingType.WORLD_TIME || (iWorldTimeListView = (IWorldTimeListView) getView()) == null) {
            return;
        }
        iWorldTimeListView.onSetWorldTimeSuccess();
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        IWorldTimeListView iWorldTimeListView;
        if (settingType != SettingCallBack.SettingType.WORLD_TIME || (iWorldTimeListView = (IWorldTimeListView) getView()) == null) {
            return;
        }
        iWorldTimeListView.onSetWorldTimeFailed();
    }

    public final void getWorldTimeList() {
        addAutoCancelSubscriber(new Func<List<WorldTimeCity>>() { // from class: com.ido.life.module.device.presenter.WorldTimeListPresenter.getWorldTimeList.1
            @Override // com.ido.life.data.Func
            public List<WorldTimeCity> call() {
                return WorldTimeCityManager.INSTANCE.getInstance().getDeviceWorldTimeCities();
            }
        }, new Callback<List<WorldTimeCity>>() { // from class: com.ido.life.module.device.presenter.WorldTimeListPresenter.getWorldTimeList.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(List<WorldTimeCity> data) {
                if (data != null) {
                    IWorldTimeListView iWorldTimeListViewAccess$getView = WorldTimeListPresenter.access$getView(WorldTimeListPresenter.this);
                    if (iWorldTimeListViewAccess$getView != null) {
                        iWorldTimeListViewAccess$getView.onGetWorldTimeListSuccess(data);
                        return;
                    }
                    return;
                }
                IWorldTimeListView iWorldTimeListViewAccess$getView2 = WorldTimeListPresenter.access$getView(WorldTimeListPresenter.this);
                if (iWorldTimeListViewAccess$getView2 != null) {
                    iWorldTimeListViewAccess$getView2.onGetWorldTimeListFailed();
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IWorldTimeListView iWorldTimeListViewAccess$getView = WorldTimeListPresenter.access$getView(WorldTimeListPresenter.this);
                if (iWorldTimeListViewAccess$getView != null) {
                    iWorldTimeListViewAccess$getView.onGetWorldTimeListFailed();
                }
            }
        });
    }

    public final boolean setWorldTime(List<WorldTimeCity> cities) {
        Intrinsics.checkParameterIsNotNull(cities, "cities");
        addCallBack();
        WorldTimeCityManager companion = WorldTimeCityManager.INSTANCE.getInstance();
        List<WorldTimeCity> list = cities;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((WorldTimeCity) it.next()).getId()));
        }
        return companion.syncWorldTimeToDeviceById(CollectionsKt.toMutableList((Collection) arrayList), false);
    }
}