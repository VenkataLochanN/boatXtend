package com.ido.alexa.log;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLogPathImpl implements AlexaLogPath {
    private static Context mContext;
    private static AlexaLogPathImpl mInstance = new AlexaLogPathImpl();
    private static String sRootPath;

    public static AlexaLogPath getInstance() {
        return mInstance;
    }

    public static void initLogPath(Context context) {
        if (context == null) {
            return;
        }
        mContext = context;
        sRootPath = context.getFilesDir().getAbsolutePath() + "/AlexaSDK/";
    }

    @Override // com.ido.alexa.log.AlexaLogPath
    public String getRootPath() {
        if (TextUtils.isEmpty(sRootPath) && mContext != null) {
            sRootPath = mContext.getFilesDir().getAbsolutePath() + "/AlexaSDK/";
        }
        return sRootPath;
    }

    @Override // com.ido.alexa.log.AlexaLogPath
    public String getAlexaPath() {
        return getRootPath().concat("alexa/");
    }

    @Override // com.ido.alexa.log.AlexaLogPath
    public String getAlexaPCMPath() {
        return getRootPath().concat("voice/");
    }
}