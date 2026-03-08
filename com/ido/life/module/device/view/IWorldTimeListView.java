package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.WorldTimeCity;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IWorldTimeListView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lcom/ido/life/module/device/view/IWorldTimeListView;", "Lcom/ido/life/base/IBaseView;", "onGetWorldTimeListFailed", "", "onGetWorldTimeListSuccess", "list", "", "Lcom/ido/life/bean/WorldTimeCity;", "onSetWorldTimeFailed", "onSetWorldTimeSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IWorldTimeListView extends IBaseView {
    void onGetWorldTimeListFailed();

    void onGetWorldTimeListSuccess(List<WorldTimeCity> list);

    void onSetWorldTimeFailed();

    void onSetWorldTimeSuccess();
}