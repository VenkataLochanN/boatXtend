package com.ido.life.data.me;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface ISettingRepository {
    void clearCache(Context context);

    String getCacheFileSize(Context context);
}