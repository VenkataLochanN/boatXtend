package com.ido.common.utils;

import android.util.Log;
import com.ido.common.log.CommonLogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class GZipUtil {
    private static final String TAG = "GZipUtil";

    public static byte[] gzipStringToByte(byte[] bArr) {
        byte[] byteArray = null;
        if (bArr == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.flush();
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e2) {
            e2.printStackTrace();
            return byteArray;
        }
    }

    public static byte[] gzipStringToByte(String str) {
        byte[] byteArray = null;
        if (str == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(Charset.defaultCharset()));
            gZIPOutputStream.flush();
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e2) {
            e2.printStackTrace();
            return byteArray;
        }
    }

    public static String ungzipString(byte[] bArr) {
        String string = null;
        if (bArr == null) {
            return null;
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr2 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = gZIPInputStream.read(bArr2, 0, bArr2.length);
                if (-1 != i) {
                    byteArrayOutputStream.write(bArr2, 0, i);
                } else {
                    string = byteArrayOutputStream.toString();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return string;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return string;
        }
    }

    public static String ungzipString(String str) {
        String string = null;
        if (str == null) {
            return null;
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Charset.defaultCharset()));
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = gZIPInputStream.read(bArr, 0, bArr.length);
                if (-1 != i) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    string = byteArrayOutputStream.toString();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return string;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return string;
        }
    }

    public static void unZipFolder(String str, String str2, String str3) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                if (nextEntry.isDirectory()) {
                    str3 = str3.substring(0, str3.length() - 1);
                    new File(str2 + File.separator + str3).mkdirs();
                } else {
                    Log.e(TAG, str2 + File.separator + str3);
                    File file = new File(str2 + File.separator + str3);
                    if (!file.exists()) {
                        Log.e(TAG, "Create the file:" + str2 + File.separator + str3);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            } else {
                zipInputStream.close();
                return;
            }
        }
    }

    public static void ZipFolder(String str, String str2) throws Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
        File file = new File(str);
        CommonLogUtil.d("---->" + file.getParent() + "===" + file.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        sb.append(file.getParent());
        sb.append(File.separator);
        ZipFiles(sb.toString(), file.getName(), zipOutputStream);
        zipOutputStream.finish();
        zipOutputStream.close();
    }

    private static void ZipFiles(String str, String str2, ZipOutputStream zipOutputStream) throws Exception {
        CommonLogUtil.d("folderString:" + str + "\nfileString:" + str2 + "\n==========================");
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
                ZipFiles(str + str2 + "/", str3, zipOutputStream);
            }
        }
    }

    public static void UnZipFolder(String str, String str2) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    new File(str2 + File.separator + name.substring(0, name.length() - 1)).mkdirs();
                } else {
                    Log.e(TAG, str2 + File.separator + name);
                    File file = new File(str2 + File.separator + name);
                    if (!file.exists()) {
                        Log.e(TAG, "Create the file:" + str2 + File.separator + name);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            } else {
                zipInputStream.close();
                return;
            }
        }
    }
}