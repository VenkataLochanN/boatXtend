package com.ido.life.module.sport.history;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KmProgress.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\nR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/sport/history/KmProgress;", "", "pace", "", NotificationCompat.CATEGORY_PROGRESS, "", "isFaster", "", "isReach", "(Ljava/lang/String;IZZ)V", "()Z", "getPace", "()Ljava/lang/String;", "getProgress", "()I", "app_release"}, k = 1, mv = {1, 1, 16})
public final class KmProgress {
    private final boolean isFaster;
    private final boolean isReach;
    private final String pace;
    private final int progress;

    public KmProgress(String pace, int i, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(pace, "pace");
        this.pace = pace;
        this.progress = i;
        this.isFaster = z;
        this.isReach = z2;
    }

    public final String getPace() {
        return this.pace;
    }

    public final int getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: isFaster, reason: from getter */
    public final boolean getIsFaster() {
        return this.isFaster;
    }

    /* JADX INFO: renamed from: isReach, reason: from getter */
    public final boolean getIsReach() {
        return this.isReach;
    }
}