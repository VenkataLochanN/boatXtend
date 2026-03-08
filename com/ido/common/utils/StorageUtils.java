package com.ido.common.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class StorageUtils {
    private static String getAppName(Context context) {
        return AppInfoUtil.getAppName(context).replaceAll(" ", "");
    }

    public static String getCacheDir(Context context) {
        return context == null ? "" : context.getCacheDir().getAbsolutePath();
    }

    public static String getFilesDir(Context context) {
        return context == null ? "" : context.getFilesDir().getAbsolutePath();
    }

    public static String getExternalDir(Context context) {
        if (context == null) {
            return "";
        }
        File file = new File(Environment.getExternalStorageDirectory(), getAppName(context));
        FileUtil.ensureDir(file);
        return file.getAbsolutePath();
    }

    public static String getExternalShareDir(Context context) {
        if (context == null) {
            return "";
        }
        File file = new File(getExternalDir(context), "Share");
        FileUtil.ensureDir(file);
        return file.getAbsolutePath();
    }

    public static String getExternalCacheDir(Context context) {
        if (context == null) {
            return "";
        }
        File file = new File(getExternalDir(context), "Cache");
        FileUtil.ensureDir(file);
        return file.getAbsolutePath();
    }

    public static String getExternalCameraDir(Context context) {
        if (context == null) {
            return "";
        }
        File file = new File(getExternalDir(context), "Camera");
        FileUtil.ensureDir(file);
        return file.getAbsolutePath();
    }

    public static String getLutDir(Context context) {
        if (context == null) {
            return "";
        }
        File file = new File(getFilesDir(context), "Lut");
        FileUtil.ensureDir(file);
        return file.getAbsolutePath();
    }

    public static String getDownloadAppUpgradeDir(Context context) {
        if (context == null) {
            return "";
        }
        String str = getCacheDir(context) + File.separator + "AppUpgrade";
        return FileUtil.ensureDir(str) ? str : getCacheDir(context);
    }

    public static String getSplashAdDir(Context context) {
        if (context == null) {
            return "";
        }
        String str = context.getCacheDir() + File.separator + "Splash";
        return FileUtil.ensureDir(str) ? str : getCacheDir(context);
    }

    public static String getDownloadstabilizatorFirmwareDir(Context context) {
        if (context == null) {
            return "";
        }
        String str = getFilesDir(context) + File.separator + "StabilizatorFirmware";
        return FileUtil.ensureDir(str) ? str : getCacheDir(context);
    }

    public static String getSmallPicDir(Context context) {
        if (context == null) {
            return "";
        }
        String str = getCacheDir(context) + File.separator + "smallPic";
        return FileUtil.ensureDir(str) ? str : getCacheDir(context);
    }

    @Deprecated
    public static String getMediaFileDir(Context context) {
        if (context == null) {
            return "";
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), getAppName(context));
        FileUtil.ensureDir(file);
        return file.getAbsolutePath();
    }

    public static String getExternalProductInstructionDir(Context context) {
        if (context == null) {
            return "";
        }
        File file = new File(getFilesDir(context), "ProductInstruction");
        FileUtil.ensureDir(file);
        return file.getAbsolutePath();
    }

    public static String createProductInstructionFileName(String str, String str2, String str3) {
        return str + "_" + str2 + "_" + str3 + ".pdf";
    }

    private static String createMediaFileName(String str, String str2, String str3) {
        return str + "_" + new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US).format(new Date()) + "." + str3;
    }

    public static String createPhotoFileName(Context context) {
        if (context == null) {
            return "";
        }
        return getExternalCameraDir(context) + File.separator + createMediaFileName("PIC", "", "jpeg");
    }

    public static String createPanoFileName(Context context) {
        if (context == null) {
            return "";
        }
        return getExternalCameraDir(context) + File.separator + createMediaFileName("PIC", "PANO", "jpeg");
    }

    public static String createVideoFileName(Context context) {
        if (context == null) {
            return "";
        }
        return getExternalCameraDir(context) + File.separator + createMediaFileName("VID", "", "mp4");
    }

    @Deprecated
    public static long freeMemory() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        return ((statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong()) / 1024) / 1024;
    }
}