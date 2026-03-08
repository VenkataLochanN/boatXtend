package com.ido.life.module.user.set.settingitem;

import com.ido.life.base.IBaseLoadingView;
import kotlin.Metadata;

/* JADX INFO: compiled from: ISettingItemView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/ido/life/module/user/set/settingitem/ISettingItemView;", "Lcom/ido/life/base/IBaseLoadingView;", "onConfigFailed", "", "onConfigSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ISettingItemView extends IBaseLoadingView {
    void onConfigFailed();

    void onConfigSuccess();
}