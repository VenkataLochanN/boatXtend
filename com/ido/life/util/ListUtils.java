package com.ido.life.util;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ListUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006J\u0014\u0010\u0007\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006¨\u0006\b"}, d2 = {"Lcom/ido/life/util/ListUtils;", "", "()V", "isNotEmpty", "", "list", "", "isNullOrEmpty", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ListUtils {
    public static final ListUtils INSTANCE = new ListUtils();

    private ListUtils() {
    }

    public final boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public final boolean isNotEmpty(List<?> list) {
        return list != null && (list.isEmpty() ^ true);
    }
}