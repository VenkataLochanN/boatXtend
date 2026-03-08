package com.ido.life.module.device.presenter;

import com.autonavi.base.amap.mapcore.AeUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.SportSubItemParaSort;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.SportsDataViewDetailBean;
import com.ido.life.data.Func;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.ISportsDataViewDetailListView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SportsDataViewDetailListPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\u001a\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0011\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0012\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017J*\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¨\u0006\u001d"}, d2 = {"Lcom/ido/life/module/device/presenter/SportsDataViewDetailListPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/ISportsDataViewDetailListView;", "Lcom/ido/ble/callback/OperateCallBack$ICallBack;", "()V", "convertData", "", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Lcom/ido/ble/protocol/model/SportSubItemParaSort;", "detachView", d.m, "onAddResult", "p0", "Lcom/ido/ble/callback/OperateCallBack$OperateType;", "p1", "", "onDeleteResult", "onModifyResult", "onQueryResult", "", "onSetResult", "querySportSubItemParaSort", "motionType", "", "setSportSubItemParaSort", "mList", "", "Lcom/ido/life/bean/SportsDataViewDetailBean;", "mNotAddedList", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportsDataViewDetailListPresenter extends BaseCmdPresenter<ISportsDataViewDetailListView> implements OperateCallBack.ICallBack {
    @Override // com.ido.ble.callback.OperateCallBack.ICallBack
    public void onAddResult(OperateCallBack.OperateType p0, boolean p1) {
    }

    @Override // com.ido.ble.callback.OperateCallBack.ICallBack
    public void onDeleteResult(OperateCallBack.OperateType p0, boolean p1) {
    }

    @Override // com.ido.ble.callback.OperateCallBack.ICallBack
    public void onModifyResult(OperateCallBack.OperateType p0, boolean p1) {
    }

    public static final /* synthetic */ ISportsDataViewDetailListView access$getView(SportsDataViewDetailListPresenter sportsDataViewDetailListPresenter) {
        return (ISportsDataViewDetailListView) sportsDataViewDetailListPresenter.getView();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerOperateCallBack(this);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterOperateCallBack(this);
    }

    public final void querySportSubItemParaSort(int motionType) {
        CommonLogUtil.printAndSave("querySportSubItemParaSort motionType = " + motionType);
        BLEManager.querySportSubItemParaSort(motionType);
    }

    public final void setSportSubItemParaSort(int motionType, List<SportsDataViewDetailBean> mList, List<SportsDataViewDetailBean> mNotAddedList) {
        Intrinsics.checkParameterIsNotNull(mList, "mList");
        Intrinsics.checkParameterIsNotNull(mNotAddedList, "mNotAddedList");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(mList);
        arrayList.addAll(mNotAddedList);
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(Integer.valueOf(((SportsDataViewDetailBean) it.next()).getType()));
        }
        ArrayList arrayList4 = arrayList3;
        CommonLogUtil.d("setSportSubItemParaSort subItemParaSortList = " + arrayList4);
        BLEManager.setSportSubItemParaSort(arrayList4, mList.size(), motionType);
    }

    @Override // com.ido.ble.callback.OperateCallBack.ICallBack
    public void onSetResult(OperateCallBack.OperateType p0, boolean p1) {
        if (p0 == OperateCallBack.OperateType.SPORT_SUB_ITEM_PARA_SORT) {
            CommonLogUtil.d("onSetResult result = " + p1);
            if (p1) {
                ISportsDataViewDetailListView iSportsDataViewDetailListView = (ISportsDataViewDetailListView) getView();
                if (iSportsDataViewDetailListView != null) {
                    iSportsDataViewDetailListView.onSetDataSuccess();
                    return;
                }
                return;
            }
            ISportsDataViewDetailListView iSportsDataViewDetailListView2 = (ISportsDataViewDetailListView) getView();
            if (iSportsDataViewDetailListView2 != null) {
                iSportsDataViewDetailListView2.onSetDataFailed();
            }
        }
    }

    @Override // com.ido.ble.callback.OperateCallBack.ICallBack
    public void onQueryResult(OperateCallBack.OperateType p0, Object p1) {
        if (p0 == OperateCallBack.OperateType.SPORT_SUB_ITEM_PARA_SORT) {
            CommonLogUtil.d("onQueryResult result = " + p1);
            SportSubItemParaSort sportSubItemParaSort = (SportSubItemParaSort) p1;
            if (sportSubItemParaSort != null) {
                convertData(sportSubItemParaSort);
                return;
            }
            ISportsDataViewDetailListView iSportsDataViewDetailListView = (ISportsDataViewDetailListView) getView();
            if (iSportsDataViewDetailListView != null) {
                iSportsDataViewDetailListView.onGetDataFailed();
            }
        }
    }

    private final void convertData(final SportSubItemParaSort data) {
        addAutoCancelSubscriber(new Func<Pair<? extends List<SportsDataViewDetailBean>, ? extends List<SportsDataViewDetailBean>>>() { // from class: com.ido.life.module.device.presenter.SportsDataViewDetailListPresenter.convertData.1
            @Override // com.ido.life.data.Func
            public Pair<? extends List<SportsDataViewDetailBean>, ? extends List<SportsDataViewDetailBean>> call() {
                try {
                    List<Integer> list = data.items;
                    Intrinsics.checkExpressionValueIsNotNull(list, "data.items");
                    List<Integer> list2 = list;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (Integer it : list2) {
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        arrayList.add(new SportsDataViewDetailBean(it.intValue(), MotionTypeManager.INSTANCE.getMotionTypeDetailName(it.intValue())));
                    }
                    ArrayList arrayList2 = arrayList;
                    return new Pair<>(CollectionsKt.toMutableList((Collection) arrayList2.subList(0, data.now_user_location)), CollectionsKt.toMutableList((Collection) arrayList2.subList(data.now_user_location, arrayList2.size())));
                } catch (Exception unused) {
                    return new Pair<>(new ArrayList(), new ArrayList());
                }
            }
        }, new Callback<Pair<? extends List<SportsDataViewDetailBean>, ? extends List<SportsDataViewDetailBean>>>() { // from class: com.ido.life.module.device.presenter.SportsDataViewDetailListPresenter.convertData.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Pair<? extends List<SportsDataViewDetailBean>, ? extends List<SportsDataViewDetailBean>> data2) {
                Intrinsics.checkParameterIsNotNull(data2, "data");
                ISportsDataViewDetailListView iSportsDataViewDetailListViewAccess$getView = SportsDataViewDetailListPresenter.access$getView(SportsDataViewDetailListPresenter.this);
                if (iSportsDataViewDetailListViewAccess$getView != null) {
                    iSportsDataViewDetailListViewAccess$getView.onGetDataSuccess(data2.getFirst(), data2.getSecond());
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                ISportsDataViewDetailListView iSportsDataViewDetailListViewAccess$getView = SportsDataViewDetailListPresenter.access$getView(SportsDataViewDetailListPresenter.this);
                if (iSportsDataViewDetailListViewAccess$getView != null) {
                    iSportsDataViewDetailListViewAccess$getView.onSetDataFailed();
                }
            }
        });
    }
}