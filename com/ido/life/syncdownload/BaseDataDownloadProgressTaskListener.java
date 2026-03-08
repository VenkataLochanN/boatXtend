package com.ido.life.syncdownload;

import kotlin.Metadata;

/* JADX INFO: compiled from: BaseDataDownloadProgressTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR&\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00058G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/ido/life/syncdownload/BaseDataDownloadProgressTaskListener;", "Lcom/ido/life/syncdownload/BaseDataDownloadTaskListener;", "userId", "", "taskCount", "", "(JI)V", "SQL_INSERT_MAX_COUNT", "getSQL_INSERT_MAX_COUNT", "()I", "setSQL_INSERT_MAX_COUNT", "(I)V", "<set-?>", "mCurrProgress", "getProgress", "setProgress", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDataDownloadProgressTaskListener extends BaseDataDownloadTaskListener {
    private int SQL_INSERT_MAX_COUNT;
    private int mCurrProgress;

    public BaseDataDownloadProgressTaskListener(long j, int i) {
        super(j, i);
        this.SQL_INSERT_MAX_COUNT = 50;
    }

    public int getSQL_INSERT_MAX_COUNT() {
        return this.SQL_INSERT_MAX_COUNT;
    }

    public void setSQL_INSERT_MAX_COUNT(int i) {
        this.SQL_INSERT_MAX_COUNT = i;
    }

    /* JADX INFO: renamed from: getProgress, reason: from getter */
    public final int getMCurrProgress() {
        return this.mCurrProgress;
    }

    public final void setProgress(int i) {
        this.mCurrProgress = i;
    }
}