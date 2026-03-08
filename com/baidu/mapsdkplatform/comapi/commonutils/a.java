package com.baidu.mapsdkplatform.comapi.commonutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3480a;

    static {
        f3480a = Build.VERSION.SDK_INT >= 8;
    }

    public static Bitmap a(String str, Context context) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            if (inputStreamOpen != null) {
                return BitmapFactory.decodeStream(inputStreamOpen);
            }
            return null;
        } catch (Exception unused) {
            return BitmapFactory.decodeFile(b("assets/" + str, str, context));
        }
    }

    private static void a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, i);
                }
            } finally {
            }
        }
        fileOutputStream.flush();
        try {
            inputStream.close();
        } catch (IOException unused) {
        }
        try {
            fileOutputStream.close();
        } catch (IOException unused2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0097 A[Catch: IOException -> 0x0093, TRY_LEAVE, TryCatch #1 {IOException -> 0x0093, blocks: (B:38:0x008f, B:42:0x0097), top: B:47:0x008f }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r6, java.lang.String r7, android.content.Context r8) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.res.AssetManager r1 = r8.getAssets()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6a
            java.io.InputStream r1 = r1.open(r6)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6a
            if (r1 == 0) goto L55
            int r2 = r1.available()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r1.read(r2)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r4.<init>()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.io.File r5 = r8.getFilesDir()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.lang.String r5 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r4.append(r5)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.lang.String r5 = "/"
            r4.append(r5)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r4.append(r7)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            boolean r4 = r3.exists()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            if (r4 == 0) goto L3e
            r3.delete()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
        L3e:
            r3.createNewFile()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
            r4.write(r2)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
            r4.close()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
            r0 = r4
            goto L55
        L4e:
            r6 = move-exception
            goto L8c
        L50:
            r6 = move-exception
            goto L8d
        L52:
            r4 = r0
        L53:
            r0 = r1
            goto L6b
        L55:
            if (r1 == 0) goto L5d
            r1.close()     // Catch: java.io.IOException -> L5b
            goto L5d
        L5b:
            r6 = move-exception
            goto L63
        L5d:
            if (r0 == 0) goto L89
            r0.close()     // Catch: java.io.IOException -> L5b
            goto L89
        L63:
            r6.printStackTrace()
            goto L89
        L67:
            r6 = move-exception
            r1 = r0
            goto L8d
        L6a:
            r4 = r0
        L6b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a
            r1.<init>()     // Catch: java.lang.Throwable -> L8a
            java.lang.String r2 = "assets/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L8a
            r1.append(r6)     // Catch: java.lang.Throwable -> L8a
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Throwable -> L8a
            b(r6, r7, r8)     // Catch: java.lang.Throwable -> L8a
            if (r0 == 0) goto L84
            r0.close()     // Catch: java.io.IOException -> L5b
        L84:
            if (r4 == 0) goto L89
            r4.close()     // Catch: java.io.IOException -> L5b
        L89:
            return
        L8a:
            r6 = move-exception
            r1 = r0
        L8c:
            r0 = r4
        L8d:
            if (r1 == 0) goto L95
            r1.close()     // Catch: java.io.IOException -> L93
            goto L95
        L93:
            r7 = move-exception
            goto L9b
        L95:
            if (r0 == 0) goto L9e
            r0.close()     // Catch: java.io.IOException -> L93
            goto L9e
        L9b:
            r7.printStackTrace()
        L9e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.commonutils.a.a(java.lang.String, java.lang.String, android.content.Context):void");
    }

    private static String b(String str, String str2, Context context) throws Throwable {
        ZipFile zipFile;
        File file;
        File file2;
        StringBuilder sb = new StringBuilder(context.getFilesDir().getAbsolutePath());
        ZipFile zipFile2 = null;
        try {
            try {
                try {
                    zipFile = new ZipFile(f3480a ? context.getPackageCodePath() : "");
                    try {
                        int iLastIndexOf = str2.lastIndexOf("/");
                        if (iLastIndexOf > 0) {
                            file = new File(context.getFilesDir().getAbsolutePath());
                            String strSubstring = str2.substring(0, iLastIndexOf);
                            file2 = new File(file.getAbsolutePath() + "/" + strSubstring, str2.substring(iLastIndexOf + 1, str2.length()));
                        } else {
                            file = new File(context.getFilesDir(), "assets");
                            file2 = new File(file.getAbsolutePath(), str2);
                        }
                        file.mkdirs();
                        ZipEntry entry = zipFile.getEntry(str);
                        if (entry == null) {
                            try {
                                zipFile.close();
                            } catch (IOException unused) {
                            }
                            return null;
                        }
                        a(zipFile.getInputStream(entry), new FileOutputStream(file2));
                        sb.append("/");
                        sb.append(str);
                        zipFile.close();
                    } catch (Exception e2) {
                        e = e2;
                        zipFile2 = zipFile;
                        Log.e(a.class.getSimpleName(), "copyAssetsError", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    zipFile = zipFile2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException unused3) {
        }
        return sb.toString();
    }
}