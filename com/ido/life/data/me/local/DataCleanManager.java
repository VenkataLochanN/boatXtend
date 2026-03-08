package com.ido.life.data.me.local;

import android.content.Context;
import android.os.Environment;
import com.ido.common.utils.FileSizeUtil;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class DataCleanManager {
    public static String getCachesSize(Context context) {
        try {
            return FileSizeUtil.getFormatSize((Environment.getExternalStorageState().equals("mounted") ? 0 + getFolderSize(context.getExternalCacheDir()) : 0L) + getFolderSize(context.getCacheDir()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void cleanApplicationData(Context context, String... strArr) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            cleanCustomCache(str);
        }
    }

    public static void cleanInternalCache(Context context) {
        deleteDir(context.getCacheDir());
    }

    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    public static void cleanCustomCache(String str) {
        deleteDir(new File(str));
    }

    private static boolean deleteDir(File file) {
        String[] list;
        if (file == null) {
            return false;
        }
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static long getFolderSize(File file) throws Exception {
        long length;
        long j = 0;
        try {
            File[] fileArrListFiles = file.listFiles();
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    length = getFolderSize(fileArrListFiles[i]);
                } else {
                    length = fileArrListFiles[i].length();
                }
                j += length;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return j;
    }
}