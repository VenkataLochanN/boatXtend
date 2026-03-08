package com.realsil.sdk.core.utility;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import com.ido.common.constant.LanguageRegion;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceUtils {
    public static boolean isZh(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return (Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale).getLanguage().endsWith(LanguageRegion.ZH);
    }
}