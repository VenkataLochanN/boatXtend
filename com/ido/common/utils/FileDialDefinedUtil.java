package com.ido.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ido.common.log.CommonLogUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FileDialDefinedUtil {
    public static final String APP_FILE = "app";
    public static final String APP_FILE_FIREWARE = "firmware_icons";
    public static final String APP_FILE_NEW = "images";
    public static final String APP_JSON = "app.json";
    public static final String DEVICE_JSON = "iwf.json";
    public static final String FILEDIR_NAME = "watchFile";
    public static final String FILEDIR_NAME_FUNCTION = "watchFileFunction";
    public static final String FILE_ZIP = ".zip";
    public static final int IMAGE_SIZE = 25;
    public static final String SAVEZIP_DIR = "/boAtWave" + File.separator + "/dialdefined" + File.separator + "/zip" + File.separator;
    public static final String STYLE_BG = "{style_bg}";
    private static final String TAG = "FileDialDefinedUtil";

    public static boolean unpackZip(String str, String str2) throws Throwable {
        ZipInputStream zipInputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (!str2.endsWith(FILE_ZIP)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str2);
            try {
                zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry != null) {
                            String name = nextEntry.getName();
                            if (name.contains(File.separator)) {
                                name.substring(name.indexOf(File.separator) + 1);
                            }
                            if (nextEntry.isDirectory()) {
                                new File(str + nextEntry).mkdirs();
                            } else {
                                fileOutputStream = new FileOutputStream(str + name);
                                while (true) {
                                    try {
                                        int i = zipInputStream.read(bArr);
                                        if (i == -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, 0, i);
                                    } catch (IOException e2) {
                                        e = e2;
                                        fileInputStream2 = fileInputStream;
                                        try {
                                            e.printStackTrace();
                                            IOUtil.close(fileInputStream2);
                                            IOUtil.close(zipInputStream);
                                            IOUtil.close(fileOutputStream);
                                            return false;
                                        } catch (Throwable th) {
                                            th = th;
                                            fileInputStream = fileInputStream2;
                                            fileOutputStream2 = fileOutputStream;
                                            IOUtil.close(fileInputStream);
                                            IOUtil.close(zipInputStream);
                                            IOUtil.close(fileOutputStream2);
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        fileOutputStream2 = fileOutputStream;
                                        IOUtil.close(fileInputStream);
                                        IOUtil.close(zipInputStream);
                                        IOUtil.close(fileOutputStream2);
                                        throw th;
                                    }
                                }
                                fileOutputStream.close();
                                zipInputStream.closeEntry();
                                fileOutputStream2 = fileOutputStream;
                            }
                        } else {
                            IOUtil.close(fileInputStream);
                            IOUtil.close(zipInputStream);
                            IOUtil.close(fileOutputStream2);
                            return true;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable th3) {
                    th = th3;
                    IOUtil.close(fileInputStream);
                    IOUtil.close(zipInputStream);
                    IOUtil.close(fileOutputStream2);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                zipInputStream = null;
                fileOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                zipInputStream = null;
            }
        } catch (IOException e5) {
            e = e5;
            zipInputStream = null;
            fileOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            zipInputStream = null;
            fileInputStream = null;
        }
    }

    public static boolean unpackCopyZip(String str, String str2) throws Throwable {
        ZipInputStream zipInputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (!str2.endsWith(FILE_ZIP)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str2);
            try {
                zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry != null) {
                            String name = nextEntry.getName();
                            if (name.contains(File.separator)) {
                                File file2 = new File(str + File.separator + name.substring(0, name.lastIndexOf(File.separator)));
                                if (!file2.exists()) {
                                    file2.mkdirs();
                                }
                            }
                            if (nextEntry.isDirectory()) {
                                new File(str + nextEntry).mkdirs();
                            } else {
                                fileOutputStream = new FileOutputStream(str + name);
                                while (true) {
                                    try {
                                        int i = zipInputStream.read(bArr);
                                        if (i == -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, 0, i);
                                    } catch (IOException e2) {
                                        e = e2;
                                        fileInputStream2 = fileInputStream;
                                        try {
                                            e.printStackTrace();
                                            IOUtil.close(fileInputStream2);
                                            IOUtil.close(zipInputStream);
                                            IOUtil.close(fileOutputStream);
                                            return false;
                                        } catch (Throwable th) {
                                            th = th;
                                            fileInputStream = fileInputStream2;
                                            fileOutputStream2 = fileOutputStream;
                                            IOUtil.close(fileInputStream);
                                            IOUtil.close(zipInputStream);
                                            IOUtil.close(fileOutputStream2);
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        fileOutputStream2 = fileOutputStream;
                                        IOUtil.close(fileInputStream);
                                        IOUtil.close(zipInputStream);
                                        IOUtil.close(fileOutputStream2);
                                        throw th;
                                    }
                                }
                                fileOutputStream.close();
                                zipInputStream.closeEntry();
                                fileOutputStream2 = fileOutputStream;
                            }
                        } else {
                            IOUtil.close(fileInputStream);
                            IOUtil.close(zipInputStream);
                            IOUtil.close(fileOutputStream2);
                            return true;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable th3) {
                    th = th3;
                    IOUtil.close(fileInputStream);
                    IOUtil.close(zipInputStream);
                    IOUtil.close(fileOutputStream2);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                zipInputStream = null;
                fileOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                zipInputStream = null;
            }
        } catch (IOException e5) {
            e = e5;
            zipInputStream = null;
            fileOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            zipInputStream = null;
            fileInputStream = null;
        }
    }

    public static boolean unpackCopyZip(String str, String str2, String str3) throws Throwable {
        ZipInputStream zipInputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (!str3.endsWith(FILE_ZIP)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        fileOutputStream2 = null;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str3);
            try {
                zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry != null) {
                            String name = nextEntry.getName();
                            if (name.contains(File.separator)) {
                                File file2 = new File(str + File.separator + name.substring(0, name.lastIndexOf(File.separator)));
                                if (!file2.exists()) {
                                    file2.mkdirs();
                                }
                            }
                            if (nextEntry.isDirectory()) {
                                new File(str + nextEntry).mkdirs();
                            } else {
                                fileOutputStream = new FileOutputStream(str + name);
                                while (true) {
                                    try {
                                        int i = zipInputStream.read(bArr);
                                        if (i == -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, 0, i);
                                    } catch (IOException e2) {
                                        e = e2;
                                        fileInputStream2 = fileInputStream;
                                        try {
                                            e.printStackTrace();
                                            IOUtil.close(fileInputStream2);
                                            IOUtil.close(zipInputStream);
                                            IOUtil.close(fileOutputStream);
                                            return false;
                                        } catch (Throwable th) {
                                            th = th;
                                            fileInputStream = fileInputStream2;
                                            fileOutputStream2 = fileOutputStream;
                                            IOUtil.close(fileInputStream);
                                            IOUtil.close(zipInputStream);
                                            IOUtil.close(fileOutputStream2);
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        fileOutputStream2 = fileOutputStream;
                                        IOUtil.close(fileInputStream);
                                        IOUtil.close(zipInputStream);
                                        IOUtil.close(fileOutputStream2);
                                        throw th;
                                    }
                                }
                                fileOutputStream.close();
                                zipInputStream.closeEntry();
                                fileOutputStream2 = fileOutputStream;
                            }
                        } else {
                            IOUtil.close(fileInputStream);
                            IOUtil.close(zipInputStream);
                            IOUtil.close(fileOutputStream2);
                            return true;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable th3) {
                    th = th3;
                    IOUtil.close(fileInputStream);
                    IOUtil.close(zipInputStream);
                    IOUtil.close(fileOutputStream2);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                zipInputStream = null;
                fileOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                zipInputStream = null;
            }
        } catch (IOException e5) {
            e = e5;
            zipInputStream = null;
            fileOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            zipInputStream = null;
            fileInputStream = null;
        }
    }

    public static boolean checkFileExist(String str, String str2) {
        String[] list = new File(str).list();
        if (list != null && list.length != 0) {
            for (int i = 0; i < list.length; i++) {
                String strSubstring = list[i];
                if (list[i].contains(".")) {
                    strSubstring = list[i].substring(0, list[i].lastIndexOf("."));
                }
                if (TextUtils.equals(strSubstring, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getWriteDir(int i) {
        return "/Veryfit" + File.separator + "/dialdefined" + File.separator + "/" + i + File.separator;
    }

    public static String getReadDir(int i) {
        return "/Veryfit/dialdefined/" + i + "/";
    }

    public static String unZipDir(Context context, int i) {
        return context.getFilesDir().getPath() + getWriteDir(i);
    }

    public static String zipDir(Context context, int i) {
        return context.getFilesDir().getPath() + getReadDir(i);
    }

    public static String copeFileDir(Context context, int i) {
        return context.getFilesDir().getPath() + getReadDir(i) + FILEDIR_NAME + "/";
    }

    public static String jsonDir(Context context, int i) {
        return context.getFilesDir().getPath() + getWriteDir(i) + FILEDIR_NAME;
    }

    public static String appFilePng(Context context, int i) {
        return context.getFilesDir().getPath() + getWriteDir(i) + FILEDIR_NAME + File.separator + APP_FILE;
    }

    public static String jsonDirNew(Context context, int i) {
        return context.getFilesDir().getPath() + getWriteDir(i) + FILEDIR_NAME_FUNCTION;
    }

    public static String jsonDirFunction(Context context, int i, String str) {
        return context.getFilesDir().getPath() + getWriteDir(i) + FILEDIR_NAME_FUNCTION + File.separator + str;
    }

    public static String appFilePngFunction(Context context, int i, String str) {
        return jsonDirFunction(context, i, str) + File.separator + APP_FILE_NEW;
    }

    public static String jsonDirFunctionAppFile(Context context, int i, String str) {
        return jsonDirFunction(context, i, str) + File.separator + APP_JSON;
    }

    public static String appFireWareIcons(Context context, int i, String str) {
        return appFilePngFunction(context, i, str) + File.separator + APP_FILE_FIREWARE;
    }

    public static String firewareFunctionDir(Context context, int i, String str) {
        return jsonDirFunction(context, i, str) + File.separator + str;
    }

    public static String firewareFunctionZip(Context context, int i, String str) {
        return jsonDirFunction(context, i, str) + File.separator + str + FILE_ZIP;
    }

    public static String fileDir(Context context, int i) {
        return context.getFilesDir().getPath() + getReadDir(i) + FILEDIR_NAME;
    }

    public static String saveZipDir(Context context) {
        return context.getFilesDir().getPath() + SAVEZIP_DIR;
    }

    public static void removeFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void zipDialFolder(String str, String str2, ArrayList<String> arrayList) {
        Log.v("XZip", "srcFileString=" + str + " zipFileString=" + str2);
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
            File file = new File(str);
            zipDialFiles(file.getParent() + File.separator + file.getName(), "", zipOutputStream, arrayList);
            zipOutputStream.finish();
            zipOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static void zipDialFiles(String str, String str2, ZipOutputStream zipOutputStream, ArrayList<String> arrayList) {
        boolean z;
        boolean z2;
        Log.v("XZip", "folderString=" + str + " fileString=" + str2);
        if (zipOutputStream == null) {
            return;
        }
        try {
            File file = new File(str + File.separator + str2);
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    z = false;
                    break;
                } else {
                    if (file.getName().equals(arrayList.get(i))) {
                        z = true;
                        break;
                    }
                    i++;
                }
            }
            if (!file.isFile()) {
                String[] list = file.list();
                if (list == null) {
                    return;
                }
                if (list.length <= 0) {
                    zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                    zipOutputStream.closeEntry();
                }
                for (int i2 = 0; i2 < list.length; i2++) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= arrayList.size()) {
                            z2 = false;
                            break;
                        } else {
                            if (file.getName().equals(arrayList.get(i3))) {
                                z2 = true;
                                break;
                            }
                            i3++;
                        }
                    }
                    if (!z2) {
                        if (TextUtils.isEmpty(str2)) {
                            zipDialFiles(str, list[i2], zipOutputStream, arrayList);
                        } else {
                            zipDialFiles(str, str2 + File.separator + list[i2], zipOutputStream, arrayList);
                        }
                    }
                }
                return;
            }
            if (z) {
                return;
            }
            ZipEntry zipEntry = new ZipEntry(str2);
            FileInputStream fileInputStream = new FileInputStream(file);
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bArr = new byte[4096];
            while (true) {
                int i4 = fileInputStream.read(bArr);
                if (i4 != -1) {
                    try {
                        zipOutputStream.write(bArr, 0, i4);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    zipOutputStream.closeEntry();
                    return;
                }
            }
        } catch (Exception e3) {
            CommonLogUtil.d(TAG, "zipFiles: " + e3.toString());
            e3.printStackTrace();
        }
    }

    private static void ZipFiles(String str, String str2, ZipOutputStream zipOutputStream) throws Exception {
        Log.v("XZip", "ZipFiles(String, String, ZipOutputStream)");
        if (zipOutputStream == null) {
            return;
        }
        File file = new File(str + str2);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(str2);
            FileInputStream fileInputStream = new FileInputStream(file);
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    zipOutputStream.write(bArr, 0, i);
                } else {
                    zipOutputStream.closeEntry();
                    return;
                }
            }
        } else {
            String[] list = file.list();
            if (list.length <= 0) {
                zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                zipOutputStream.closeEntry();
            }
            for (String str3 : list) {
                ZipFiles(str, str3, zipOutputStream);
            }
        }
    }

    public static boolean copyFolder(String str, String str2) {
        File file;
        try {
            File file2 = new File(str2);
            if (!file2.exists() && !file2.mkdirs()) {
                Log.e("--Method--", "copyFolder: cannot create directory.");
                return false;
            }
            for (String str3 : new File(str).list()) {
                if (str.endsWith(File.separator)) {
                    file = new File(str + str3);
                } else {
                    file = new File(str + File.separator + str3);
                }
                if (file.isDirectory()) {
                    copyFolder(str + "/" + str3, str2 + "/" + str3);
                } else {
                    if (!file.exists()) {
                        Log.e("--Method--", "copyFolder:  oldFile not exist.");
                        return false;
                    }
                    if (!file.isFile()) {
                        Log.e("--Method--", "copyFolder:  oldFile not file.");
                        return false;
                    }
                    if (!file.canRead()) {
                        Log.e("--Method--", "copyFolder:  oldFile cannot read.");
                        return false;
                    }
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + "/" + file.getName());
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:0|2|53|3|(1:5)|6|51|7|(6:49|8|(1:10)(1:55)|43|13|58)|11|39|12|43|13|58|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyFile(java.lang.String r3, java.lang.String r4) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            r1 = 0
            boolean r2 = r0.exists()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
            if (r2 != 0) goto L18
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
            java.lang.String r0 = r0.getParent()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
            r2.mkdirs()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
        L18:
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L5a
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L5b
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L5b
        L26:
            int r4 = r2.read(r0)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46
            r1 = -1
            if (r4 == r1) goto L32
            r1 = 0
            r3.write(r0, r1, r4)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46
            goto L26
        L32:
            r3.close()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46
            r2.close()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46
            r3.flush()     // Catch: java.lang.Exception -> L3e
            r3.close()     // Catch: java.lang.Exception -> L3e
        L3e:
            r2.close()     // Catch: java.lang.Exception -> L66
            goto L66
        L42:
            r4 = move-exception
            r1 = r3
            r3 = r4
            goto L4c
        L46:
            r1 = r3
            goto L5b
        L48:
            r3 = move-exception
            goto L4c
        L4a:
            r3 = move-exception
            r2 = r1
        L4c:
            if (r1 == 0) goto L54
            r1.flush()     // Catch: java.lang.Exception -> L54
            r1.close()     // Catch: java.lang.Exception -> L54
        L54:
            if (r2 == 0) goto L59
            r2.close()     // Catch: java.lang.Exception -> L59
        L59:
            throw r3
        L5a:
            r2 = r1
        L5b:
            if (r1 == 0) goto L63
            r1.flush()     // Catch: java.lang.Exception -> L63
            r1.close()     // Catch: java.lang.Exception -> L63
        L63:
            if (r2 == 0) goto L66
            goto L3e
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.utils.FileDialDefinedUtil.copyFile(java.lang.String, java.lang.String):void");
    }

    public static void deleteFile(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public static void deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isFile()) {
                    deleteFile(fileArrListFiles[i].getAbsolutePath());
                } else if (fileArrListFiles[i].isDirectory()) {
                    deleteDirectory(fileArrListFiles[i].getAbsolutePath());
                }
            }
            file.delete();
        }
    }
}