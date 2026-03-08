package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.data.cache.ShortcutAppWrapper;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: compiled from: IShortcutAppListView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J,\u0010\u0004\u001a\u00020\u00032\"\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\tH&¨\u0006\n"}, d2 = {"Lcom/ido/life/module/device/view/IShortcutAppListView;", "Lcom/ido/life/base/IBaseView;", "onGetShortcutAppFailed", "", "onGetShortcutAppSuccess", "shortcutAppData", "Ljava/util/ArrayList;", "Lcom/ido/life/data/cache/ShortcutAppWrapper;", "Lcom/ido/life/bean/ShortcutAppData;", "Lkotlin/collections/ArrayList;", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IShortcutAppListView extends IBaseView {
    void onGetShortcutAppFailed();

    void onGetShortcutAppSuccess(ArrayList<ShortcutAppWrapper<ShortcutAppData>> shortcutAppData);
}