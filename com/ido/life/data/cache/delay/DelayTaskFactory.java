package com.ido.life.data.cache.delay;

import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: compiled from: DelayTaskFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/ido/life/data/cache/delay/DelayTaskFactory;", "", "()V", "mTaskPool", "Ljava/util/ArrayList;", "Lcom/ido/life/data/cache/delay/DelayTask;", "Lkotlin/collections/ArrayList;", "getMTaskPool", "()Ljava/util/ArrayList;", "create", "", "type", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DelayTaskFactory {
    public static final DelayTaskFactory INSTANCE = new DelayTaskFactory();
    private static final ArrayList<DelayTask> mTaskPool = new ArrayList<>();

    public final void create(int type) {
    }

    private DelayTaskFactory() {
    }

    public final ArrayList<DelayTask> getMTaskPool() {
        return mTaskPool;
    }
}