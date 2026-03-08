package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.MotionTypeBean;
import com.ido.life.customview.OnSyncChangeListener;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: compiled from: IMotionTypesView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J \u0010\u0005\u001a\u00020\u00042\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tH&J\b\u0010\n\u001a\u00020\u0004H&J\b\u0010\u000b\u001a\u00020\u0004H&¨\u0006\f"}, d2 = {"Lcom/ido/life/module/device/view/IMotionTypesView;", "Lcom/ido/life/base/IBaseView;", "Lcom/ido/life/customview/OnSyncChangeListener;", "onGetDeviceMotionTypesFailed", "", "onGetDeviceMotionTypesSuccess", "list", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/MotionTypeBean;", "Lkotlin/collections/ArrayList;", "onSetMotionTypesFailed", "onSetMotionTypesSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IMotionTypesView extends IBaseView, OnSyncChangeListener {
    void onGetDeviceMotionTypesFailed();

    void onGetDeviceMotionTypesSuccess(ArrayList<MotionTypeBean> list);

    void onSetMotionTypesFailed();

    void onSetMotionTypesSuccess();
}