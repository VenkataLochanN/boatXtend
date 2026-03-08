package com.ido.life.data.listener;

import com.autonavi.base.amap.mapcore.AeUtil;
import kotlin.Metadata;

/* JADX INFO: compiled from: Callback.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H&J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/ido/life/data/listener/Callback;", "T", "", "onFailed", "", "errMsg", "", "onSuccess", AeUtil.ROOT_DATA_PATH_OLD_NAME, "(Ljava/lang/Object;)V", "app_release"}, k = 1, mv = {1, 1, 16})
public interface Callback<T> {
    void onFailed(String errMsg);

    void onSuccess(T data);
}