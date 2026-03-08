package com.ido.common.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.bumptech.glide.load.Key;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static String getSdcard() {
        String absolutePath;
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                absolutePath = ((File) Objects.requireNonNull(IdoApp.getAppContext().getExternalFilesDir(null))).getAbsolutePath();
            } catch (Exception e2) {
                e2.printStackTrace();
                absolutePath = "/sdcard";
            }
        } else {
            absolutePath = "/sdcard";
        }
        Log.d(TAG, "getSdcard path = " + absolutePath);
        return absolutePath;
    }

    public static boolean ensureDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return ensureDir(new File(str));
    }

    public static boolean ensureDir(File file) {
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        try {
            return file.mkdirs();
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean ensureFile(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return true;
        }
        try {
            file.createNewFile();
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String getFileSuffix(String str) {
        int iLastIndexOf;
        if (str == null || (iLastIndexOf = str.lastIndexOf(".")) <= 0) {
            return null;
        }
        return str.substring(iLastIndexOf + 1).toLowerCase();
    }

    public static String getFileNameFromPath(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf("/");
        return iLastIndexOf > 0 ? str.substring(iLastIndexOf + 1) : str;
    }

    public static String getNoSuffixFileNameFromPath(String str) {
        if (str != null) {
            return getFileNameFromPath(str).replaceAll("[.][^.]+$", "");
        }
        return null;
    }

    public static boolean isPathEqual(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        if (!str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        return str.equalsIgnoreCase(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.io.Closeable, java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r5v14, types: [java.io.Closeable, java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16, types: [java.io.BufferedOutputStream, java.io.Closeable, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.io.Closeable, java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v7 */
    public static String zipFile(String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        ?? r6;
        BufferedInputStream bufferedInputStream2;
        ?? r2;
        BufferedInputStream bufferedInputStream3;
        ?? r62;
        ?? r22;
        ?? r23;
        ?? r24;
        BufferedInputStream bufferedInputStream4;
        BufferedInputStream bufferedInputStream5;
        BufferedInputStream bufferedInputStream6;
        ?? r25;
        ?? r26;
        ?? bufferedOutputStream;
        ?? r27;
        ?? fileOutputStream;
        ?? zipOutputStream;
        ?? r7;
        ?? r63;
        ?? r5;
        ?? r28;
        byte[] bArr;
        String str2;
        ?? r0 = 0;
        if (str == null) {
            return null;
        }
        File file = new File(str);
        ?? Exists = file.exists();
        try {
            if (Exists == 0) {
                return null;
            }
            try {
                Exists = new FileInputStream(file);
                try {
                    bufferedInputStream2 = new BufferedInputStream(Exists);
                } catch (FileNotFoundException e2) {
                    e = e2;
                    bufferedInputStream2 = null;
                    r24 = Exists;
                    bufferedInputStream6 = bufferedInputStream2;
                    r27 = r24;
                    bufferedOutputStream = bufferedInputStream6;
                    Exists = r27;
                    fileOutputStream = bufferedInputStream6;
                    zipOutputStream = bufferedOutputStream;
                    e.printStackTrace();
                    r28 = Exists;
                    r5 = fileOutputStream;
                    r63 = bufferedOutputStream;
                    r7 = zipOutputStream;
                    IOUtil.close(r28);
                    IOUtil.close(bufferedInputStream2);
                    IOUtil.close(r5);
                    IOUtil.close(r63);
                    IOUtil.close(r7);
                    return null;
                } catch (IOException e3) {
                    e = e3;
                    bufferedInputStream2 = null;
                    r23 = Exists;
                    bufferedInputStream5 = bufferedInputStream2;
                    r26 = r23;
                    bufferedOutputStream = bufferedInputStream5;
                    Exists = r26;
                    fileOutputStream = bufferedInputStream5;
                    zipOutputStream = bufferedOutputStream;
                    e.printStackTrace();
                    r28 = Exists;
                    r5 = fileOutputStream;
                    r63 = bufferedOutputStream;
                    r7 = zipOutputStream;
                    IOUtil.close(r28);
                    IOUtil.close(bufferedInputStream2);
                    IOUtil.close(r5);
                    IOUtil.close(r63);
                    IOUtil.close(r7);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream2 = null;
                    r22 = Exists;
                    bufferedInputStream4 = bufferedInputStream2;
                    r25 = r22;
                    bufferedInputStream = bufferedInputStream4;
                    r2 = r25;
                    r6 = bufferedInputStream4;
                    IOUtil.close(r2);
                    IOUtil.close(bufferedInputStream2);
                    IOUtil.close(r0);
                    IOUtil.close(r6);
                    IOUtil.close(bufferedInputStream);
                    throw th;
                }
                try {
                    bArr = new byte[1024];
                    str2 = str + FileDialDefinedUtil.FILE_ZIP;
                    File file2 = new File(str2);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    fileOutputStream = new FileOutputStream(str2);
                    try {
                        bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                        try {
                            zipOutputStream = new ZipOutputStream(bufferedOutputStream);
                        } catch (FileNotFoundException e4) {
                            e = e4;
                            zipOutputStream = 0;
                        } catch (IOException e5) {
                            e = e5;
                            zipOutputStream = 0;
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream3 = null;
                            r62 = bufferedOutputStream;
                            r0 = fileOutputStream;
                            r2 = Exists;
                            r6 = r62;
                            bufferedInputStream = bufferedInputStream3;
                            IOUtil.close(r2);
                            IOUtil.close(bufferedInputStream2);
                            IOUtil.close(r0);
                            IOUtil.close(r6);
                            IOUtil.close(bufferedInputStream);
                            throw th;
                        }
                    } catch (FileNotFoundException e6) {
                        e = e6;
                        bufferedOutputStream = 0;
                        Exists = Exists;
                        fileOutputStream = fileOutputStream;
                        zipOutputStream = bufferedOutputStream;
                        e.printStackTrace();
                        r28 = Exists;
                        r5 = fileOutputStream;
                        r63 = bufferedOutputStream;
                        r7 = zipOutputStream;
                        IOUtil.close(r28);
                        IOUtil.close(bufferedInputStream2);
                        IOUtil.close(r5);
                        IOUtil.close(r63);
                        IOUtil.close(r7);
                        return null;
                    } catch (IOException e7) {
                        e = e7;
                        bufferedOutputStream = 0;
                        Exists = Exists;
                        fileOutputStream = fileOutputStream;
                        zipOutputStream = bufferedOutputStream;
                        e.printStackTrace();
                        r28 = Exists;
                        r5 = fileOutputStream;
                        r63 = bufferedOutputStream;
                        r7 = zipOutputStream;
                        IOUtil.close(r28);
                        IOUtil.close(bufferedInputStream2);
                        IOUtil.close(r5);
                        IOUtil.close(r63);
                        IOUtil.close(r7);
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        r62 = 0;
                        bufferedInputStream3 = null;
                    }
                } catch (FileNotFoundException e8) {
                    e = e8;
                    bufferedInputStream6 = null;
                    r27 = Exists;
                    bufferedOutputStream = bufferedInputStream6;
                    Exists = r27;
                    fileOutputStream = bufferedInputStream6;
                    zipOutputStream = bufferedOutputStream;
                    e.printStackTrace();
                    r28 = Exists;
                    r5 = fileOutputStream;
                    r63 = bufferedOutputStream;
                    r7 = zipOutputStream;
                    IOUtil.close(r28);
                    IOUtil.close(bufferedInputStream2);
                    IOUtil.close(r5);
                    IOUtil.close(r63);
                    IOUtil.close(r7);
                    return null;
                } catch (IOException e9) {
                    e = e9;
                    bufferedInputStream5 = null;
                    r26 = Exists;
                    bufferedOutputStream = bufferedInputStream5;
                    Exists = r26;
                    fileOutputStream = bufferedInputStream5;
                    zipOutputStream = bufferedOutputStream;
                    e.printStackTrace();
                    r28 = Exists;
                    r5 = fileOutputStream;
                    r63 = bufferedOutputStream;
                    r7 = zipOutputStream;
                    IOUtil.close(r28);
                    IOUtil.close(bufferedInputStream2);
                    IOUtil.close(r5);
                    IOUtil.close(r63);
                    IOUtil.close(r7);
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedInputStream4 = null;
                    r25 = Exists;
                    bufferedInputStream = bufferedInputStream4;
                    r2 = r25;
                    r6 = bufferedInputStream4;
                    IOUtil.close(r2);
                    IOUtil.close(bufferedInputStream2);
                    IOUtil.close(r0);
                    IOUtil.close(r6);
                    IOUtil.close(bufferedInputStream);
                    throw th;
                }
            } catch (FileNotFoundException e10) {
                e = e10;
                r24 = 0;
                bufferedInputStream2 = null;
            } catch (IOException e11) {
                e = e11;
                r23 = 0;
                bufferedInputStream2 = null;
            } catch (Throwable th5) {
                th = th5;
                r22 = 0;
                bufferedInputStream2 = null;
            }
            try {
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                while (true) {
                    int i = bufferedInputStream2.read(bArr);
                    if (i != -1) {
                        zipOutputStream.write(bArr, 0, i);
                        zipOutputStream.flush();
                    } else {
                        IOUtil.close(Exists);
                        IOUtil.close(bufferedInputStream2);
                        IOUtil.close(fileOutputStream);
                        IOUtil.close(bufferedOutputStream);
                        IOUtil.close(zipOutputStream);
                        return str2;
                    }
                }
            } catch (FileNotFoundException e12) {
                e = e12;
                e.printStackTrace();
                r28 = Exists;
                r5 = fileOutputStream;
                r63 = bufferedOutputStream;
                r7 = zipOutputStream;
                IOUtil.close(r28);
                IOUtil.close(bufferedInputStream2);
                IOUtil.close(r5);
                IOUtil.close(r63);
                IOUtil.close(r7);
                return null;
            } catch (IOException e13) {
                e = e13;
                e.printStackTrace();
                r28 = Exists;
                r5 = fileOutputStream;
                r63 = bufferedOutputStream;
                r7 = zipOutputStream;
                IOUtil.close(r28);
                IOUtil.close(bufferedInputStream2);
                IOUtil.close(r5);
                IOUtil.close(r63);
                IOUtil.close(r7);
                return null;
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    public static String getFileTypeByName(String str, String str2) {
        int iLastIndexOf;
        return (str == null || (iLastIndexOf = str.lastIndexOf(".")) == -1) ? str2 : str.substring(iLastIndexOf + 1);
    }

    public static boolean isFileExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).isFile();
    }

    public static boolean isDirExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).isDirectory();
    }

    public static long getFileSize(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        File file = new File(str);
        if (file.isFile()) {
            return file.length();
        }
        return 0L;
    }

    public static boolean deleteFile(String str) {
        if (str == null) {
            return true;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.BufferedInputStream, java.io.Closeable] */
    public static String getFilecharset(File file) throws Throwable {
        String str;
        ?? bufferedInputStream;
        String str2;
        int i;
        String str3;
        byte[] bArr = new byte[3];
        ?? r2 = 0;
        ?? r22 = 0;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    bufferedInputStream.mark(0);
                    i = bufferedInputStream.read(bArr, 0, 3);
                    System.out.println("字节大小：" + i);
                } catch (Exception e2) {
                    e = e2;
                    str = null;
                    r22 = bufferedInputStream;
                    e.printStackTrace();
                    IOUtil.close(r22);
                    str2 = str;
                    r2 = r22;
                } catch (Throwable th) {
                    th = th;
                    IOUtil.close(bufferedInputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                str = null;
            }
            if (i == -1) {
                bufferedInputStream.close();
                IOUtil.close(bufferedInputStream);
                return null;
            }
            if (bArr[0] == 92 && bArr[1] == 117) {
                str3 = "ANSI";
            } else if (bArr[0] == -1 && bArr[1] == -2) {
                str3 = "UTF-16LE";
            } else if (bArr[0] == -2 && bArr[1] == -1) {
                str3 = "UTF-16BE";
            } else {
                str3 = (bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) ? Key.STRING_CHARSET_NAME : "GBK";
            }
            String str4 = str3;
            bufferedInputStream.reset();
            IOUtil.close(bufferedInputStream);
            str2 = str4;
            r2 = str4;
            return str2;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = r2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0077 A[Catch: IOException -> 0x0073, TRY_LEAVE, TryCatch #3 {IOException -> 0x0073, blocks: (B:45:0x006f, B:49:0x0077), top: B:53:0x006f }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyFile2Folder(java.lang.String r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L11
            boolean r1 = r0.isDirectory()
            if (r1 != 0) goto L14
        L11:
            r0.mkdirs()
        L14:
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            r4 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L53
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L53
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r3.<init>(r5, r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
        L31:
            int r5 = r1.read(r4)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r0 = -1
            if (r5 == r0) goto L3d
            r0 = 0
            r2.write(r4, r0, r5)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            goto L31
        L3d:
            r1.close()     // Catch: java.io.IOException -> L5e
            r2.close()     // Catch: java.io.IOException -> L5e
            goto L69
        L44:
            r4 = move-exception
            r5 = r4
            goto L6c
        L47:
            r4 = move-exception
            r5 = r4
            goto L4e
        L4a:
            r5 = move-exception
            goto L6d
        L4c:
            r5 = move-exception
            r2 = r4
        L4e:
            r4 = r1
            goto L55
        L50:
            r5 = move-exception
            r1 = r4
            goto L6d
        L53:
            r5 = move-exception
            r2 = r4
        L55:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6a
            if (r4 == 0) goto L60
            r4.close()     // Catch: java.io.IOException -> L5e
            goto L60
        L5e:
            r4 = move-exception
            goto L66
        L60:
            if (r2 == 0) goto L69
            r2.close()     // Catch: java.io.IOException -> L5e
            goto L69
        L66:
            r4.printStackTrace()
        L69:
            return
        L6a:
            r5 = move-exception
            r1 = r4
        L6c:
            r4 = r2
        L6d:
            if (r1 == 0) goto L75
            r1.close()     // Catch: java.io.IOException -> L73
            goto L75
        L73:
            r4 = move-exception
            goto L7b
        L75:
            if (r4 == 0) goto L7e
            r4.close()     // Catch: java.io.IOException -> L73
            goto L7e
        L7b:
            r4.printStackTrace()
        L7e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.utils.FileUtil.copyFile2Folder(java.lang.String, java.lang.String):void");
    }

    public static void copyDirectiory(String str, String str2) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        new File(str2).mkdirs();
        File[] fileArrListFiles = new File(str).listFiles();
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isFile()) {
                File file = fileArrListFiles[i];
                File file2 = new File(new File(str2).getAbsolutePath() + File.separator + fileArrListFiles[i].getName());
                if (file2.exists()) {
                    file2.delete();
                }
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                        fileOutputStream = null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileInputStream = null;
                }
                copyFile(fileInputStream, fileOutputStream);
            }
        }
    }

    public static void zipFolder(String str, String str2) {
        Log.v("XZip", "srcFileString=" + str + " zipFileString=" + str2);
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
            File file = new File(str);
            zipFiles(file.getParent() + File.separator, file.getName(), zipOutputStream);
            zipOutputStream.finish();
            zipOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static void zipFiles(String str, String str2, ZipOutputStream zipOutputStream) {
        Log.v("XZip", "folderString=" + str + " fileString=" + str2);
        if (zipOutputStream == null) {
            return;
        }
        try {
            File file = new File(str + str2);
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry(str2);
                FileInputStream fileInputStream = new FileInputStream(file);
                zipOutputStream.putNextEntry(zipEntry);
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i != -1) {
                        try {
                            zipOutputStream.write(bArr, 0, i);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        zipOutputStream.closeEntry();
                        return;
                    }
                }
            } else {
                String[] list = file.list();
                if (list == null) {
                    return;
                }
                if (list.length <= 0) {
                    zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                    zipOutputStream.closeEntry();
                }
                for (String str3 : list) {
                    zipFiles(str, str2 + File.separator + str3, zipOutputStream);
                }
            }
        } catch (Exception e3) {
            CommonLogUtil.d(TAG, "zipFiles: " + e3.toString());
            e3.printStackTrace();
        }
    }

    public static void copyFile(String str, String str2, boolean z) throws Throwable {
        FileInputStream fileInputStream;
        if (str.equals(str2)) {
            return;
        }
        File file = new File(str);
        File file2 = new File(str2);
        if (z && file2.exists()) {
            file2.delete();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException e2) {
            e = e2;
            fileInputStream = null;
        }
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
        }
        copyFile(fileInputStream, fileOutputStream);
    }

    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws Throwable {
        FileChannel channel;
        Throwable th;
        FileChannel channel2;
        IOException e2;
        try {
            channel2 = fileInputStream.getChannel();
            try {
                channel = fileOutputStream.getChannel();
            } catch (IOException e3) {
                e2 = e3;
                channel = null;
            } catch (Throwable th2) {
                th = th2;
                channel = null;
                IOUtil.close(channel2);
                IOUtil.close(channel);
                throw th;
            }
        } catch (IOException e4) {
            channel = null;
            e2 = e4;
            channel2 = null;
        } catch (Throwable th3) {
            channel = null;
            th = th3;
            channel2 = null;
        }
        try {
            try {
                channel2.transferTo(0L, channel2.size(), channel);
                channel2.close();
            } catch (Throwable th4) {
                th = th4;
                IOUtil.close(channel2);
                IOUtil.close(channel);
                throw th;
            }
        } catch (IOException e5) {
            e2 = e5;
            e2.printStackTrace();
        }
        IOUtil.close(channel2);
        IOUtil.close(channel);
    }

    public static boolean isFilePicture(String str) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            String[] strArrSplit = TextUtils.split(str, "\\.");
            if (strArrSplit.length > 1) {
                String[] strArr = {"jpg", "png", "bmp", "jpeg", "gif"};
                String str2 = strArrSplit[strArrSplit.length - 1];
                int i = 0;
                while (true) {
                    if (i >= strArr.length) {
                        break;
                    }
                    if (strArr[i].equalsIgnoreCase(str2)) {
                        z = true;
                        break;
                    }
                    i++;
                }
            }
        }
        if (z) {
            return z;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        if (options.outHeight <= 0 || options.outWidth <= 0) {
            return z;
        }
        return true;
    }

    @Deprecated
    public static boolean isFilePicture(Activity activity, Uri uri) {
        return isFilePicture(getPath(activity, uri));
    }

    public static boolean isFileVideo(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isFileVideo(new File(str));
    }

    public static boolean isFileVideo(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return false;
        }
        return file.getAbsolutePath().toLowerCase().endsWith(".mp4");
    }

    public static boolean isSDCardExist() {
        return "mounted".equalsIgnoreCase(Environment.getExternalStorageState());
    }

    public static byte[] readFile(File file) throws Throwable {
        FileInputStream fileInputStream;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                IOUtil.close(null);
                throw th;
            }
            try {
                byte[] bArr = new byte[(int) file.length()];
                fileInputStream.read(bArr);
                IOUtil.close(fileInputStream);
                return bArr;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                IOUtil.close(fileInputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            IOUtil.close(null);
            throw th;
        }
    }

    public static void writeFile(File file, String str) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(str.getBytes());
                    IOUtil.close(fileOutputStream2);
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    IOUtil.close(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    IOUtil.close(fileOutputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void writeToFile(String str, String str2) throws Throwable {
        boolean zCreateNewFile;
        File file = new File(str);
        if (file.exists()) {
            zCreateNewFile = true;
        } else {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (IOException e2) {
                Log.e(TAG, e2.toString());
                zCreateNewFile = true;
            }
        }
        if (!zCreateNewFile) {
            Log.e(TAG, "create log file failed!");
            return;
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, true));
                try {
                    bufferedWriter2.write(str2);
                    IOUtil.close(bufferedWriter2);
                } catch (Exception e3) {
                    e = e3;
                    bufferedWriter = bufferedWriter2;
                    Log.e(TAG, e.toString());
                    IOUtil.close(bufferedWriter);
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    IOUtil.close(bufferedWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r7 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L31 java.lang.IllegalArgumentException -> L34
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L31 java.lang.IllegalArgumentException -> L34
            if (r8 == 0) goto L2b
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L29 java.lang.Throwable -> L53
            if (r9 == 0) goto L2b
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.IllegalArgumentException -> L29 java.lang.Throwable -> L53
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.IllegalArgumentException -> L29 java.lang.Throwable -> L53
            if (r8 == 0) goto L28
            r8.close()
        L28:
            return r9
        L29:
            r9 = move-exception
            goto L36
        L2b:
            if (r8 == 0) goto L52
        L2d:
            r8.close()
            goto L52
        L31:
            r9 = move-exception
            r8 = r7
            goto L54
        L34:
            r9 = move-exception
            r8 = r7
        L36:
            java.lang.String r10 = "FileUtil"
            java.util.Locale r11 = java.util.Locale.getDefault()     // Catch: java.lang.Throwable -> L53
            java.lang.String r0 = "getDataColumn: _data - [%s]"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L53
            r2 = 0
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L53
            r1[r2] = r9     // Catch: java.lang.Throwable -> L53
            java.lang.String r9 = java.lang.String.format(r11, r0, r1)     // Catch: java.lang.Throwable -> L53
            android.util.Log.i(r10, r9)     // Catch: java.lang.Throwable -> L53
            if (r8 == 0) goto L52
            goto L2d
        L52:
            return r7
        L53:
            r9 = move-exception
        L54:
            if (r8 == 0) goto L59
            r8.close()
        L59:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.utils.FileUtil.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] strArrSplit = TextUtils.split(DocumentsContract.getDocumentId(uri), ":");
                if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    String documentId = DocumentsContract.getDocumentId(uri);
                    if (documentId.startsWith("raw:")) {
                        return documentId.replaceFirst("raw:", "");
                    }
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
                }
                if (isMediaDocument(uri)) {
                    String[] strArrSplit2 = TextUtils.split(DocumentsContract.getDocumentId(uri), ":");
                    String str = strArrSplit2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit2[1]});
                }
            }
        } else {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    public static Uri toProviderUri(Context context, String str) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(str));
    }

    public static String deleteSuffix(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        return iLastIndexOf < -1 ? str : str.substring(0, iLastIndexOf);
    }

    public static void addToMediaStore(Context context, String str) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        context.sendBroadcast(intent);
    }

    public static boolean removeFromMediaStore(Context context, String str) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(str);
        context.getContentResolver().delete(MediaStore.Files.getContentUri("external"), "_data LIKE ? ", new String[]{str});
        return deleteFile(str);
    }

    public static boolean save(String str, byte[] bArr) throws Throwable {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str));
                try {
                    bufferedOutputStream2.write(bArr);
                    bufferedOutputStream2.flush();
                    bufferedOutputStream2.close();
                    IOUtil.close(bufferedOutputStream2);
                } catch (IOException e2) {
                    e = e2;
                    bufferedOutputStream = bufferedOutputStream2;
                    e.printStackTrace();
                    IOUtil.close(bufferedOutputStream);
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    IOUtil.close(bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
        return isFileExist(str);
    }

    public static boolean unpackZip(String str, String str2) throws Throwable {
        ZipInputStream zipInputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (!str2.endsWith(FileDialDefinedUtil.FILE_ZIP)) {
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
                                name = name.substring(name.indexOf(File.separator) + 1);
                            }
                            if (nextEntry.isDirectory()) {
                                new File(str + name).mkdirs();
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

    public static File getFirstUpgradeFileBySuffix(String str, String str2) {
        File firstUpgradeFileBySuffix;
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        if (file.isFile()) {
            if (file.getName().endsWith(str2)) {
                return file;
            }
        } else {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    if (!file2.isHidden() && file2.getName().endsWith(str2)) {
                        return file2;
                    }
                } else if (file2.isDirectory() && (firstUpgradeFileBySuffix = getFirstUpgradeFileBySuffix(file2.getPath(), str2)) != null && firstUpgradeFileBySuffix.getName().endsWith(str2)) {
                    return firstUpgradeFileBySuffix;
                }
            }
        }
        return null;
    }
}