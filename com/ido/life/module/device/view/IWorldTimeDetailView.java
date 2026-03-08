package com.ido.life.module.device.view;

import com.autonavi.base.amap.mapcore.AeUtil;
import com.ido.life.base.IBaseView;
import kotlin.Metadata;
import kotlin.Pair;

/* JADX INFO: compiled from: IWorldTimeDetailView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001c\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\b"}, d2 = {"Lcom/ido/life/module/device/view/IWorldTimeDetailView;", "Lcom/ido/life/base/IBaseView;", "onGetSunRiseSetTimeFailed", "", "onGetSunRiseSetTimeSuccess", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Lkotlin/Pair;", "", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IWorldTimeDetailView extends IBaseView {
    void onGetSunRiseSetTimeFailed();

    void onGetSunRiseSetTimeSuccess(Pair<String, String> data);
}