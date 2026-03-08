package com.ido.life.util;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;

/* JADX INFO: loaded from: classes3.dex */
public class ConnectLogHelper {
    public static void saveLog(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getConnectLogPath(), str, str2);
    }
}