package com.ido.life.module.device.presenter;

import com.ido.life.base.BasePresenter;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.data.Func;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.IMotionTypeChooseView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionTypeChoosePresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/ido/life/module/device/presenter/MotionTypeChoosePresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/device/view/IMotionTypeChooseView;", "()V", "getAllMotionTypes", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionTypeChoosePresenter extends BasePresenter<IMotionTypeChooseView> {
    public static final /* synthetic */ IMotionTypeChooseView access$getView(MotionTypeChoosePresenter motionTypeChoosePresenter) {
        return motionTypeChoosePresenter.getView();
    }

    public final void getAllMotionTypes() {
        addAutoCancelSubscriber(new Func<List<? extends MotionTypeBean>>() { // from class: com.ido.life.module.device.presenter.MotionTypeChoosePresenter.getAllMotionTypes.1
            @Override // com.ido.life.data.Func
            public List<? extends MotionTypeBean> call() {
                ArrayList allMotionTypes = MotionTypeManager.INSTANCE.getInstance().getAllMotionTypes();
                if (allMotionTypes == null) {
                    allMotionTypes = new ArrayList();
                }
                return CollectionsKt.sortedWith(allMotionTypes, new Comparator<T>() { // from class: com.ido.life.module.device.presenter.MotionTypeChoosePresenter$getAllMotionTypes$1$call$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((MotionTypeBean) t).getGroupType()), Integer.valueOf(((MotionTypeBean) t2).getGroupType()));
                    }
                });
            }
        }, new Callback<List<? extends MotionTypeBean>>() { // from class: com.ido.life.module.device.presenter.MotionTypeChoosePresenter.getAllMotionTypes.2
            @Override // com.ido.life.data.listener.Callback
            public /* bridge */ /* synthetic */ void onSuccess(List<? extends MotionTypeBean> list) {
                onSuccess2((List<MotionTypeBean>) list);
            }

            /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(List<MotionTypeBean> data) {
                Intrinsics.checkParameterIsNotNull(data, "data");
                IMotionTypeChooseView iMotionTypeChooseViewAccess$getView = MotionTypeChoosePresenter.access$getView(MotionTypeChoosePresenter.this);
                if (iMotionTypeChooseViewAccess$getView != null) {
                    iMotionTypeChooseViewAccess$getView.onGetAllMotionTypesSuccess(data);
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                IMotionTypeChooseView iMotionTypeChooseViewAccess$getView = MotionTypeChoosePresenter.access$getView(MotionTypeChoosePresenter.this);
                if (iMotionTypeChooseViewAccess$getView != null) {
                    iMotionTypeChooseViewAccess$getView.onGetAllMotionTypesFailed();
                }
            }
        });
    }
}