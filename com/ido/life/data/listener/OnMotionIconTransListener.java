package com.ido.life.data.listener;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: OnMotionIconTransListener.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&J\b\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/ido/life/data/listener/OnMotionIconTransListener;", "", "onIconTransComplete", "", "isSuccess", "", "onIconTransProgress", NotificationCompat.CATEGORY_PROGRESS, "", "maxCount", "onIconTransStart", "app_release"}, k = 1, mv = {1, 1, 16})
public interface OnMotionIconTransListener {
    void onIconTransComplete(boolean isSuccess);

    void onIconTransProgress(int progress, int maxCount);

    void onIconTransStart();
}