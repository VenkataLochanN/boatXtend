package com.ido.life.module.device.presenter;

import com.ido.ble.protocol.model.SmallQuickModule;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.data.Func;
import com.ido.life.data.cache.ShortcutAppManager;
import com.ido.life.data.cache.ShortcutAppWrapper;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IShortcutAppListView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppListPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/ido/life/module/device/presenter/ShortcutAppListPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/IShortcutAppListView;", "()V", "convertSizeType", "", "", "support_size_type", "getShortcutAppItemList", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppListPresenter extends BaseCmdPresenter<IShortcutAppListView> {
    public static final /* synthetic */ IShortcutAppListView access$getView(ShortcutAppListPresenter shortcutAppListPresenter) {
        return (IShortcutAppListView) shortcutAppListPresenter.getView();
    }

    public final void getShortcutAppItemList() {
        addAutoCancelSubscriber(new Func<ArrayList<ShortcutAppWrapper<ShortcutAppData>>>() { // from class: com.ido.life.module.device.presenter.ShortcutAppListPresenter.getShortcutAppItemList.1
            @Override // com.ido.life.data.Func
            public ArrayList<ShortcutAppWrapper<ShortcutAppData>> call() {
                SmallQuickModule.QueryResponse queryResponse = ShortcutAppManager.INSTANCE.getInstance().getQueryResponse();
                ArrayList arrayList = null;
                List<SmallQuickModule.SupportInfo> list = queryResponse != null ? queryResponse.support_items : null;
                ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList2 = new ArrayList<>();
                if (list != null) {
                    List<SmallQuickModule.SupportInfo> list2 = list;
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (SmallQuickModule.SupportInfo supportInfo : list2) {
                        arrayList3.add(new ShortcutAppWrapper(ShortcutAppManager.INSTANCE.getInstance().getShortcutAppGroupType(supportInfo.widgets_type), false, false, new ShortcutAppData(supportInfo.widgets_type, (List<Integer>) ShortcutAppListPresenter.this.convertSizeType(supportInfo.support_size_type)), 6, null));
                    }
                    arrayList = arrayList3;
                }
                if (arrayList != null) {
                    ArrayList arrayList4 = arrayList;
                    if (!arrayList4.isEmpty()) {
                        arrayList2.addAll(arrayList4);
                    }
                }
                ArrayList<ShortcutAppWrapper<ShortcutAppData>> arrayList5 = arrayList2;
                if (arrayList5.size() > 1) {
                    CollectionsKt.sortWith(arrayList5, new Comparator<T>() { // from class: com.ido.life.module.device.presenter.ShortcutAppListPresenter$getShortcutAppItemList$1$call$$inlined$sortBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(((ShortcutAppWrapper) t).getType()), Integer.valueOf(((ShortcutAppWrapper) t2).getType()));
                        }
                    });
                }
                return arrayList2;
            }
        }, new Callback<ArrayList<ShortcutAppWrapper<ShortcutAppData>>>() { // from class: com.ido.life.module.device.presenter.ShortcutAppListPresenter.getShortcutAppItemList.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(ArrayList<ShortcutAppWrapper<ShortcutAppData>> data) {
                if (data != null) {
                    IShortcutAppListView iShortcutAppListViewAccess$getView = ShortcutAppListPresenter.access$getView(ShortcutAppListPresenter.this);
                    if (iShortcutAppListViewAccess$getView != null) {
                        iShortcutAppListViewAccess$getView.onGetShortcutAppSuccess(data);
                        return;
                    }
                    return;
                }
                IShortcutAppListView iShortcutAppListViewAccess$getView2 = ShortcutAppListPresenter.access$getView(ShortcutAppListPresenter.this);
                if (iShortcutAppListViewAccess$getView2 != null) {
                    iShortcutAppListViewAccess$getView2.onGetShortcutAppFailed();
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IShortcutAppListView iShortcutAppListViewAccess$getView = ShortcutAppListPresenter.access$getView(ShortcutAppListPresenter.this);
                if (iShortcutAppListViewAccess$getView != null) {
                    iShortcutAppListViewAccess$getView.onGetShortcutAppFailed();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Integer> convertSizeType(int support_size_type) {
        if (support_size_type != 1) {
            return support_size_type != 2 ? CollectionsKt.mutableListOf(2, 1) : CollectionsKt.mutableListOf(2);
        }
        return CollectionsKt.mutableListOf(1);
    }
}