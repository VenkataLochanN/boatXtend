package com.ido.life.module.device.presenter;

import android.util.Log;
import com.google.gson.GsonBuilder;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.SmallQuickModule;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.ble.BaseOperateCallback;
import com.ido.life.data.cache.ShortcutAppManager;
import com.ido.life.module.device.view.IShortcutAppEditView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppEditPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0005\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0006\u0010\t\u001a\u00020\bJ\b\u0010\n\u001a\u00020\bH\u0016J\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/ido/life/module/device/presenter/ShortcutAppEditPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/IShortcutAppEditView;", "()V", "mOperateCallback", "com/ido/life/module/device/presenter/ShortcutAppEditPresenter$mOperateCallback$1", "Lcom/ido/life/module/device/presenter/ShortcutAppEditPresenter$mOperateCallback$1;", "detachView", "", "getEditedShortcutAppItemList", d.m, "setShortcutApps", "apps", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/ShortcutAppData;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppEditPresenter extends BaseCmdPresenter<IShortcutAppEditView> {
    public static final String TAG = "ShortcutAppEdit";
    private final ShortcutAppEditPresenter$mOperateCallback$1 mOperateCallback = new BaseOperateCallback() { // from class: com.ido.life.module.device.presenter.ShortcutAppEditPresenter$mOperateCallback$1
        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onSetResult(OperateCallBack.OperateType p0, boolean p1) {
            CommonLogUtil.printAndSave("设置快捷应用：" + p1);
            if (p0 == OperateCallBack.OperateType.SMALL_QUICK_MODULE_SORT) {
                if (p1) {
                    IShortcutAppEditView iShortcutAppEditViewAccess$getView = ShortcutAppEditPresenter.access$getView(this.this$0);
                    if (iShortcutAppEditViewAccess$getView != null) {
                        iShortcutAppEditViewAccess$getView.onSetShortcutAppSuccess();
                        return;
                    }
                    return;
                }
                IShortcutAppEditView iShortcutAppEditViewAccess$getView2 = ShortcutAppEditPresenter.access$getView(this.this$0);
                if (iShortcutAppEditViewAccess$getView2 != null) {
                    iShortcutAppEditViewAccess$getView2.onSetShortcutAppFailed();
                }
            }
        }

        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onQueryResult(OperateCallBack.OperateType p0, Object p1) {
            ArrayList arrayList;
            if (p0 == OperateCallBack.OperateType.SMALL_QUICK_MODULE_SORT) {
                CommonLogUtil.printAndSave("onQueryResult: " + new GsonBuilder().create().toJson(p1));
                SmallQuickModule.QueryResponse queryResponse = (SmallQuickModule.QueryResponse) p1;
                ShortcutAppManager.INSTANCE.getInstance().setQueryResponse(queryResponse);
                List<SmallQuickModule> list = queryResponse != null ? queryResponse.items : null;
                if (list != null) {
                    List<SmallQuickModule> list2 = list;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (SmallQuickModule smallQuickModule : list2) {
                        arrayList2.add(new ShortcutAppData(smallQuickModule.size_type, smallQuickModule.widgets_type));
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = new ArrayList();
                }
                ArrayList<ShortcutAppData> arrayList3 = new ArrayList<>();
                arrayList3.addAll(arrayList);
                IShortcutAppEditView iShortcutAppEditViewAccess$getView = ShortcutAppEditPresenter.access$getView(this.this$0);
                if (iShortcutAppEditViewAccess$getView != null) {
                    iShortcutAppEditViewAccess$getView.onGetEditedShortcutAppSuccess(arrayList3);
                }
            }
        }
    };

    public static final /* synthetic */ IShortcutAppEditView access$getView(ShortcutAppEditPresenter shortcutAppEditPresenter) {
        return (IShortcutAppEditView) shortcutAppEditPresenter.getView();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        BLEManager.registerOperateCallBack(this.mOperateCallback);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterOperateCallBack(this.mOperateCallback);
    }

    public final void setShortcutApps(ArrayList<ShortcutAppData> apps) {
        Intrinsics.checkParameterIsNotNull(apps, "apps");
        SmallQuickModule smallQuickModule = (SmallQuickModule) null;
        ArrayList<ShortcutAppData> arrayList = apps;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (ShortcutAppData shortcutAppData : arrayList) {
            SmallQuickModule smallQuickModule2 = new SmallQuickModule();
            smallQuickModule2.size_type = shortcutAppData.getSize_type();
            smallQuickModule2.widgets_type = shortcutAppData.getWidgets_type();
            if (smallQuickModule != null) {
                boolean z = smallQuickModule2.size_type == 1;
                if (smallQuickModule == null) {
                    Intrinsics.throwNpe();
                }
                boolean z2 = smallQuickModule.size_type == 1;
                if (smallQuickModule == null) {
                    Intrinsics.throwNpe();
                }
                int i = smallQuickModule.location_y;
                if (smallQuickModule == null) {
                    Intrinsics.throwNpe();
                }
                int i2 = smallQuickModule.location_x;
                if (z2 || z) {
                    smallQuickModule2.location_y = i + 1;
                    smallQuickModule2.location_x = 1;
                } else {
                    smallQuickModule2.location_x = (2 - i2) + 1;
                    if (smallQuickModule2.location_x == 1) {
                        i++;
                    }
                    smallQuickModule2.location_y = i;
                }
            } else {
                smallQuickModule2.location_y = 1;
                smallQuickModule2.location_x = 1;
            }
            arrayList2.add(smallQuickModule2);
            smallQuickModule = smallQuickModule2;
        }
        ArrayList arrayList3 = arrayList2;
        Log.e(TAG, "item = " + new GsonBuilder().create().toJson(arrayList3));
        BLEManager.setSmallQuickModule(arrayList3);
    }

    public final void getEditedShortcutAppItemList() {
        CommonLogUtil.d("querySmallQuickModule");
        BLEManager.querySmallQuickModule();
    }
}