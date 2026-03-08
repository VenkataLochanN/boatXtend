package com.ido.ble.common;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f4218a;

    public static String a() {
        File externalFilesDir;
        if (TextUtils.isEmpty(f4218a)) {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (Build.VERSION.SDK_INT >= 29 && (externalFilesDir = com.ido.ble.b.b().getExternalFilesDir("")) != null) {
                absolutePath = externalFilesDir.getAbsolutePath();
            }
            f4218a = absolutePath + File.separator + "IDO_BLE_SDK" + File.separator + com.ido.ble.b.b().getPackageName() + File.separator;
        }
        return f4218a;
    }
}