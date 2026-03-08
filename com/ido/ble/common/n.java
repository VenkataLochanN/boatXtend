package com.ido.ble.common;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4243b = 4096;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f4244a;

    public interface a {
        void onFinish();
    }

    private static void a(String str, File file, ZipOutputStream zipOutputStream) throws IOException {
        if (file == null) {
            return;
        }
        if (!file.isFile()) {
            File[] fileArrListFiles = file.listFiles();
            for (int i = 0; i < fileArrListFiles.length; i++) {
                fileArrListFiles[i].getAbsolutePath().indexOf(file.getAbsolutePath());
                a(str, fileArrListFiles[i], zipOutputStream);
            }
            return;
        }
        byte[] bArr = new byte[1024];
        String absolutePath = file.getAbsolutePath();
        if (absolutePath.indexOf(str) != -1) {
            absolutePath = absolutePath.substring(str.length() + File.separator.length());
        }
        zipOutputStream.putNextEntry(new ZipEntry(absolutePath));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        while (true) {
            int i2 = bufferedInputStream.read(bArr, 0, 1024);
            if (i2 == -1) {
                bufferedInputStream.close();
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r5, java.lang.String r6, java.lang.String r7) throws java.lang.Exception {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto Lb7
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto Lb7
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 != 0) goto Lb7
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r1.<init>(r5)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            boolean r2 = r1.isDirectory()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r3 = -1
            if (r2 == 0) goto L2f
            int r2 = r6.indexOf(r5)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            if (r2 != r3) goto L26
            goto L2f
        L26:
            java.lang.Exception r5 = new java.lang.Exception     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            java.lang.String r6 = "zipPath must not be the child directory of srcPath."
            r5.<init>(r6)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            throw r5     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
        L2f:
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.<init>(r6)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            boolean r4 = r2.exists()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            if (r4 == 0) goto L40
            boolean r4 = r2.isDirectory()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            if (r4 != 0) goto L43
        L40:
            r2.mkdirs()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
        L43:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.<init>()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.append(r6)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            java.lang.String r6 = java.io.File.separator     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.append(r6)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.append(r7)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r7.<init>(r6)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            boolean r2 = r7.exists()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            if (r2 == 0) goto L6d
            java.lang.SecurityManager r2 = new java.lang.SecurityManager     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.<init>()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.checkDelete(r6)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r7.delete()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
        L6d:
            java.util.zip.CheckedOutputStream r6 = new java.util.zip.CheckedOutputStream     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r2.<init>(r7)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            java.util.zip.CRC32 r7 = new java.util.zip.CRC32     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r7.<init>()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r6.<init>(r2, r7)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            java.util.zip.ZipOutputStream r7 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            r7.<init>(r6)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> La7
            boolean r6 = r1.isFile()     // Catch: java.lang.Exception -> La3 java.lang.Throwable -> Laa
            if (r6 == 0) goto L94
            java.lang.String r6 = java.io.File.separator     // Catch: java.lang.Exception -> La3 java.lang.Throwable -> Laa
            int r6 = r5.lastIndexOf(r6)     // Catch: java.lang.Exception -> La3 java.lang.Throwable -> Laa
            if (r6 == r3) goto L94
            r0 = 0
            java.lang.String r5 = r5.substring(r0, r6)     // Catch: java.lang.Exception -> La3 java.lang.Throwable -> Laa
        L94:
            a(r5, r1, r7)     // Catch: java.lang.Exception -> La3 java.lang.Throwable -> Laa
            r7.flush()     // Catch: java.lang.Exception -> La3 java.lang.Throwable -> Laa
            r7.close()     // Catch: java.lang.Exception -> L9e
            goto La2
        L9e:
            r5 = move-exception
            r5.printStackTrace()
        La2:
            return
        La3:
            r5 = move-exception
            goto La9
        La5:
            r5 = move-exception
            goto Lac
        La7:
            r5 = move-exception
            r7 = r0
        La9:
            throw r5     // Catch: java.lang.Throwable -> Laa
        Laa:
            r5 = move-exception
            r0 = r7
        Lac:
            if (r0 == 0) goto Lb6
            r0.close()     // Catch: java.lang.Exception -> Lb2
            goto Lb6
        Lb2:
            r6 = move-exception
            r6.printStackTrace()
        Lb6:
            throw r5
        Lb7:
            java.lang.Exception r5 = new java.lang.Exception
            java.lang.String r6 = "para is empty"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.common.n.a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private static void a(String str, String str2, ZipOutputStream zipOutputStream) throws IOException {
        Log.v("XZip", "ZipFiles(String, String, ZipOutputStream)");
        if (zipOutputStream == null) {
            return;
        }
        File file = new File(str + str2);
        if (!file.isFile()) {
            String[] list = file.list();
            if (list.length <= 0) {
                zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                zipOutputStream.closeEntry();
            }
            for (String str3 : list) {
                a(str, str2 + File.separator + str3, zipOutputStream);
            }
            return;
        }
        ZipEntry zipEntry = new ZipEntry(str2);
        FileInputStream fileInputStream = new FileInputStream(file);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bArr = new byte[4096];
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i == -1) {
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, i);
        }
    }

    public static boolean a(String str) {
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = new ZipFile(str).entries();
            while (enumerationEntries.hasMoreElements()) {
                if (enumerationEntries.nextElement().getName().contains("../")) {
                    return false;
                }
            }
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean b(String str, String str2) {
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

    public void a(a aVar) {
        this.f4244a = aVar;
    }

    public void a(String str, String str2) throws IOException {
        Log.v("XZip", "ZipFolder(String, String)");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
        File file = new File(str);
        a(file.getParent() + File.separator, file.getName(), zipOutputStream);
        zipOutputStream.finish();
        zipOutputStream.close();
        this.f4244a.onFinish();
    }
}