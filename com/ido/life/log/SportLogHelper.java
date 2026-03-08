package com.ido.life.log;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;

/* JADX INFO: loaded from: classes2.dex */
public class SportLogHelper {
    public static void saveSportLog(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), str, str2);
    }

    public static void saveGpsLog(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGpsLogPath(), str, str2);
    }
}