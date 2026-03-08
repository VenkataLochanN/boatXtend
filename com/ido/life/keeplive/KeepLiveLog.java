package com.ido.life.keeplive;

import android.content.Context;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import java.io.File;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class KeepLiveLog {
    private static String sLogPath;

    public static void initLogPath(Context context) {
        sLogPath = ((File) Objects.requireNonNull(context.getExternalFilesDir(null))).getPath() + "/boatWave/";
    }

    static void saveLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getKeepLiveLogPath(), str);
    }
}