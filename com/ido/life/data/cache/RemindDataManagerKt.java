package com.ido.life.data.cache;

import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.life.bean.NotificationApp;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemindDataManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"has", "", "", "Lcom/ido/life/bean/NotificationApp;", FileDialDefinedUtil.APP_FILE, "app_release"}, k = 2, mv = {1, 1, 16})
public final class RemindDataManagerKt {
    public static final boolean has(List<NotificationApp> has, NotificationApp app) {
        Intrinsics.checkParameterIsNotNull(has, "$this$has");
        Intrinsics.checkParameterIsNotNull(app, "app");
        Iterator<NotificationApp> it = has.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getFieldInFunctionList(), app.getFieldInFunctionList())) {
                return true;
            }
        }
        return false;
    }
}