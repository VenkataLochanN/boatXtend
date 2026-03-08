package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.ShortcutAppData;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: compiled from: IShortcutAppEditView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J \u0010\u0004\u001a\u00020\u00032\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/ido/life/module/device/view/IShortcutAppEditView;", "Lcom/ido/life/base/IBaseView;", "onGetEditedShortcutAppFailed", "", "onGetEditedShortcutAppSuccess", "shortcutAppData", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/ShortcutAppData;", "Lkotlin/collections/ArrayList;", "onSetShortcutAppFailed", "onSetShortcutAppSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IShortcutAppEditView extends IBaseView {
    void onGetEditedShortcutAppFailed();

    void onGetEditedShortcutAppSuccess(ArrayList<ShortcutAppData> shortcutAppData);

    void onSetShortcutAppFailed();

    void onSetShortcutAppSuccess();
}