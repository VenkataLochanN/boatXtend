package com.ido.life.data.me;

import android.content.Context;
import com.ido.life.data.me.local.DataCleanManager;

/* JADX INFO: loaded from: classes2.dex */
public class SettingRepository implements ISettingRepository {
    private static volatile SettingRepository instance;

    private SettingRepository() {
    }

    public static SettingRepository getInstance() {
        if (instance == null) {
            synchronized (SettingRepository.class) {
                if (instance == null) {
                    instance = new SettingRepository();
                }
            }
        }
        return instance;
    }

    @Override // com.ido.life.data.me.ISettingRepository
    public String getCacheFileSize(Context context) {
        return DataCleanManager.getCachesSize(context);
    }

    @Override // com.ido.life.data.me.ISettingRepository
    public void clearCache(Context context) {
        DataCleanManager.cleanApplicationData(context, new String[0]);
    }
}