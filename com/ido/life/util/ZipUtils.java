package com.ido.life.util;

import android.util.Log;
import com.ido.common.utils.StringUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes3.dex */
public class ZipUtils {
    private static final int BUFF_SIZE = 4096;
    private Zip zip;

    public interface Zip {
        void onFinish();
    }

    private static void zip(String str, File file, ZipOutputStream zipOutputStream) throws Exception {
        if (file == null) {
            return;
        }
        if (file.isFile()) {
            byte[] bArr = new byte[1024];
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.indexOf(str) != -1) {
                absolutePath = absolutePath.substring(str.length());
            }
            zipOutputStream.putNextEntry(new ZipEntry(absolutePath));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            while (true) {
                int i = bufferedInputStream.read(bArr, 0, 1024);
                if (i != -1) {
                    zipOutputStream.write(bArr, 0, i);
                } else {
                    bufferedInputStream.close();
                    zipOutputStream.closeEntry();
                    return;
                }
            }
        } else {
            File[] fileArrListFiles = file.listFiles();
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                fileArrListFiles[i2].getAbsolutePath().indexOf(file.getAbsolutePath());
                zip(str, fileArrListFiles[i2], zipOutputStream);
            }
        }
    }

    public static void zip(String str, String str2, String str3) throws Exception {
        int iLastIndexOf;
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || StringUtil.isEmpty(str3)) {
            throw new Exception("para is empty");
        }
        ZipOutputStream zipOutputStream = null;
        try {
            try {
                File file = new File(str);
                if (file.isDirectory() && str2.indexOf(str) != -1) {
                    throw new Exception("zipPath must not be the child directory of srcPath.");
                }
                File file2 = new File(str2);
                if (!file2.exists() || !file2.isDirectory()) {
                    file2.mkdirs();
                }
                String str4 = str2 + File.separator + str3;
                File file3 = new File(str4);
                if (file3.exists()) {
                    new SecurityManager().checkDelete(str4);
                    file3.delete();
                }
                ZipOutputStream zipOutputStream2 = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(file3), new CRC32()));
                try {
                    if (file.isFile() && (iLastIndexOf = str.lastIndexOf(File.separator)) != -1) {
                        str = str.substring(0, iLastIndexOf);
                    }
                    zip(str, file, zipOutputStream2);
                    zipOutputStream2.flush();
                    try {
                        zipOutputStream2.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                } catch (Throwable th) {
                    th = th;
                    zipOutputStream = zipOutputStream2;
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                throw e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean unzip(String str, String str2) {
        try {
            ZipFile zipFile = new ZipFile(str);
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                if (zipEntryNextElement.isDirectory()) {
                    new File(str2 + "/" + zipEntryNextElement.getName()).mkdirs();
                } else {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                    File file = new File(str2 + "/" + zipEntryNextElement.getName());
                    File parentFile = file.getParentFile();
                    if (parentFile != null && !parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = bufferedInputStream.read(bArr, 0, 4096);
                        if (i == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, i);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            zipFile.close();
            return true;
        } catch (Exception e2) {
            Log.e("ZipUtil", "unzip error! zip file:" + str + " unzip to path:" + str2);
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean unzip(InputStream inputStream, String str) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        String name = nextEntry.getName();
                        if (nextEntry.isDirectory()) {
                            new File(str + File.separator + name.substring(0, name.length() - 1)).mkdirs();
                        } else {
                            File file = new File(str + File.separator + name);
                            if (!file.exists()) {
                                file.getParentFile().mkdirs();
                                file.createNewFile();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i = zipInputStream.read(bArr);
                                if (i != -1) {
                                    fileOutputStream.write(bArr, 0, i);
                                    fileOutputStream.flush();
                                }
                            }
                        }
                    } else {
                        zipInputStream.close();
                        return true;
                    }
                } finally {
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void ZipFolder(String str, String str2) throws Exception {
        Log.v("XZip", "ZipFolder(String, String)");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
        File file = new File(str);
        ZipFiles(file.getParent() + File.separator, file.getName(), zipOutputStream);
        zipOutputStream.finish();
        zipOutputStream.close();
        this.zip.onFinish();
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
                ZipFiles(str, str2 + File.separator + str3, zipOutputStream);
            }
        }
    }

    public void OnZip(Zip zip) {
        this.zip = zip;
    }
}