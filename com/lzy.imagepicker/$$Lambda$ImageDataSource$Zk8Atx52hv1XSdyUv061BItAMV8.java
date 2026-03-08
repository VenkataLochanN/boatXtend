package com.lzy.imagepicker;

import android.database.Cursor;

/* JADX INFO: renamed from: com.lzy.imagepicker.-$$Lambda$ImageDataSource$Zk8Atx52hv1XSdyUv061BItAMV8 */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$ImageDataSource$Zk8Atx52hv1XSdyUv061BItAMV8 implements Runnable {
    private final /* synthetic */ Cursor f$1;

    public /* synthetic */ $$Lambda$ImageDataSource$Zk8Atx52hv1XSdyUv061BItAMV8(Cursor cursor) {
        cursor = cursor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$onLoadFinished$1$ImageDataSource(cursor);
    }
}