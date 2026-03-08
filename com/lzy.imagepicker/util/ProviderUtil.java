package com.lzy.imagepicker.util;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class ProviderUtil {
    public static String getFileProviderName(Context context) {
        return context.getPackageName() + ".provider";
    }
}