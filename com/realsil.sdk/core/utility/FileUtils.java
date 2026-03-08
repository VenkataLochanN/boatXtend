package com.realsil.sdk.core.utility;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class FileUtils {
    public static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static void copyFile(String str, String str2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        File file = new File(str);
        if (!file.exists()) {
            ZLogger.w(str + " not exist");
            return;
        }
        File file2 = new File(str2);
        if (file2.exists()) {
            try {
                boolean zCreateNewFile = file2.createNewFile();
                StringBuilder sb = new StringBuilder();
                sb.append("createNewFile: ");
                sb.append(String.valueOf(zCreateNewFile));
                ZLogger.e(sb.toString());
            } catch (IOException e2) {
                ZLogger.e("createFile failed: " + e2.toString());
            }
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (FileNotFoundException e3) {
                e = e3;
                ZLogger.e(e.toString());
                fileOutputStream = null;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            fileInputStream = null;
        }
        try {
            try {
                ZLogger.d(String.format("copyFile from %s to %s", str, str2));
                copyFile(fileInputStream, fileOutputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                    }
                }
                if (fileOutputStream == null) {
                    return;
                }
            } finally {
            }
        } catch (IOException e5) {
            ZLogger.e(e5.toString());
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused2) {
                }
            }
            if (fileOutputStream == null) {
                return;
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException unused3) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyFileStream(java.io.File r2, android.net.Uri r3, android.content.Context r4) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e
            java.io.InputStream r3 = r4.openInputStream(r3)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L29
            if (r3 == 0) goto L21
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L3f
        L14:
            int r0 = r3.read(r2)     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L3f
            if (r0 <= 0) goto L21
            r1 = 0
            r4.write(r2, r1, r0)     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L3f
            goto L14
        L1f:
            r2 = move-exception
            goto L31
        L21:
            if (r3 == 0) goto L3b
            r3.close()
            goto L3b
        L27:
            r2 = move-exception
            goto L41
        L29:
            r2 = move-exception
            goto L30
        L2b:
            r2 = move-exception
            r3 = r0
            goto L41
        L2e:
            r2 = move-exception
            r3 = r0
        L30:
            r4 = r0
        L31:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L39
            r3.close()
        L39:
            if (r4 == 0) goto L3e
        L3b:
            r4.close()
        L3e:
            return
        L3f:
            r2 = move-exception
            r0 = r4
        L41:
            if (r3 == 0) goto L46
            r3.close()
        L46:
            if (r0 == 0) goto L4b
            r0.close()
        L4b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.copyFileStream(java.io.File, android.net.Uri, android.content.Context):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:6|92|7|101|8|98|9|10|103|11|(6:12|(1:14)(1:105)|89|65|91|69)|15|16|17|18|19|83|20|(5:25|89|65|91|69)(1:106)) */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0057, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
    
        r6.printStackTrace();
        r6 = r6;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyFromAssetsToSdcard(android.content.Context r5, boolean r6, java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            boolean r0 = r0.exists()
            if (r0 == 0) goto Le
            if (r6 != 0) goto Le
            return
        Le:
            r6 = 0
            android.content.res.AssetManager r0 = r5.getAssets()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L84 java.io.FileNotFoundException -> L96
            java.io.InputStream r0 = r0.open(r7)     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L84 java.io.FileNotFoundException -> L96
            android.content.res.Resources r5 = r5.getResources()     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L7d java.io.FileNotFoundException -> L7f
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L7d java.io.FileNotFoundException -> L7f
            java.io.InputStream r5 = r5.open(r7)     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L7d java.io.FileNotFoundException -> L7f
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L73 java.io.FileNotFoundException -> L77
            r0.<init>(r8)     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L73 java.io.FileNotFoundException -> L77
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r6]     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
        L2c:
            r2 = 0
            int r3 = r5.read(r1, r2, r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            if (r3 < 0) goto L37
            r0.write(r1, r2, r3)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            goto L2c
        L37:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            r6.<init>()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            java.lang.String r1 = "source = "
            r6.append(r1)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            r6.append(r7)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            java.lang.String r7 = ", dest = "
            r6.append(r7)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            r6.append(r8)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            com.realsil.sdk.core.logger.ZLogger.v(r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L6a
            r0.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r6 = move-exception
            r6.printStackTrace()
        L5b:
            if (r5 == 0) goto Laf
            r0 = r5
            goto La7
        L60:
            r6 = move-exception
            r7 = r6
            r6 = r0
            goto L71
        L64:
            r6 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
            goto L86
        L6a:
            r6 = move-exception
            r4 = r0
            r0 = r5
            r5 = r6
            r6 = r4
            goto L98
        L70:
            r7 = move-exception
        L71:
            r0 = r5
            goto Lb2
        L73:
            r7 = move-exception
            r0 = r5
            r5 = r7
            goto L86
        L77:
            r7 = move-exception
            r0 = r5
            r5 = r7
            goto L98
        L7b:
            r7 = move-exception
            goto Lb2
        L7d:
            r5 = move-exception
            goto L86
        L7f:
            r5 = move-exception
            goto L98
        L81:
            r7 = move-exception
            r0 = r6
            goto Lb2
        L84:
            r5 = move-exception
            r0 = r6
        L86:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> Lb0
            if (r6 == 0) goto L93
            r6.close()     // Catch: java.io.IOException -> L8f
            goto L93
        L8f:
            r5 = move-exception
            r5.printStackTrace()
        L93:
            if (r0 == 0) goto Laf
            goto La7
        L96:
            r5 = move-exception
            r0 = r6
        L98:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> Lb0
            if (r6 == 0) goto La5
            r6.close()     // Catch: java.io.IOException -> La1
            goto La5
        La1:
            r5 = move-exception
            r5.printStackTrace()
        La5:
            if (r0 == 0) goto Laf
        La7:
            r0.close()     // Catch: java.io.IOException -> Lab
            goto Laf
        Lab:
            r5 = move-exception
            r5.printStackTrace()
        Laf:
            return
        Lb0:
            r5 = move-exception
            r7 = r5
        Lb2:
            if (r6 == 0) goto Lbc
            r6.close()     // Catch: java.io.IOException -> Lb8
            goto Lbc
        Lb8:
            r5 = move-exception
            r5.printStackTrace()
        Lbc:
            if (r0 == 0) goto Lc6
            r0.close()     // Catch: java.io.IOException -> Lc2
            goto Lc6
        Lc2:
            r5 = move-exception
            r5.printStackTrace()
        Lc6:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.copyFromAssetsToSdcard(android.content.Context, boolean, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:6|87|7|89|8|9|91|10|(6:11|(1:13)(1:94)|78|58|86|62)|14|15|16|17|18|80|19|(2:78|58)|86|62) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
    
        r5.printStackTrace();
        r5 = r5;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.res.AssetManager] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyFromAssetsToSdcard(android.content.res.AssetManager r4, boolean r5, java.lang.String r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r7)
            boolean r0 = r0.exists()
            if (r0 == 0) goto Le
            if (r5 != 0) goto Le
            return
        Le:
            r5 = 0
            java.io.InputStream r4 = r4.open(r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L63 java.io.FileNotFoundException -> L76
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c java.io.FileNotFoundException -> L5e
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5c java.io.FileNotFoundException -> L5e
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r5]     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
        L1c:
            r2 = 0
            int r3 = r4.read(r1, r2, r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            if (r3 < 0) goto L27
            r0.write(r1, r2, r3)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            goto L1c
        L27:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            r5.<init>()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            java.lang.String r1 = "source = "
            r5.append(r1)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            r5.append(r6)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            java.lang.String r6 = ", dest = "
            r5.append(r6)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            r5.append(r7)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            com.realsil.sdk.core.logger.ZLogger.v(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L52 java.io.FileNotFoundException -> L56
            r0.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r5 = move-exception
            r5.printStackTrace()
        L4b:
            if (r4 == 0) goto L90
            goto L88
        L4e:
            r5 = move-exception
            r6 = r5
            r5 = r0
            goto L91
        L52:
            r5 = move-exception
            r6 = r5
            r5 = r0
            goto L66
        L56:
            r5 = move-exception
            r6 = r5
            r5 = r0
            goto L79
        L5a:
            r6 = move-exception
            goto L91
        L5c:
            r6 = move-exception
            goto L66
        L5e:
            r6 = move-exception
            goto L79
        L60:
            r6 = move-exception
            r4 = r5
            goto L91
        L63:
            r4 = move-exception
            r6 = r4
            r4 = r5
        L66:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L5a
            if (r5 == 0) goto L73
            r5.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r5 = move-exception
            r5.printStackTrace()
        L73:
            if (r4 == 0) goto L90
            goto L88
        L76:
            r4 = move-exception
            r6 = r4
            r4 = r5
        L79:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L5a
            if (r5 == 0) goto L86
            r5.close()     // Catch: java.io.IOException -> L82
            goto L86
        L82:
            r5 = move-exception
            r5.printStackTrace()
        L86:
            if (r4 == 0) goto L90
        L88:
            r4.close()     // Catch: java.io.IOException -> L8c
            goto L90
        L8c:
            r4 = move-exception
            r4.printStackTrace()
        L90:
            return
        L91:
            if (r5 == 0) goto L9b
            r5.close()     // Catch: java.io.IOException -> L97
            goto L9b
        L97:
            r5 = move-exception
            r5.printStackTrace()
        L9b:
            if (r4 == 0) goto La5
            r4.close()     // Catch: java.io.IOException -> La1
            goto La5
        La1:
            r4 = move-exception
            r4.printStackTrace()
        La5:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.copyFromAssetsToSdcard(android.content.res.AssetManager, boolean, java.lang.String, java.lang.String):void");
    }

    public static File createDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static boolean exists(String str) {
        try {
            return new File(str).exists();
        } catch (Exception e2) {
            ZLogger.e(e2.getMessage());
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0051  */
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
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r6 = 0
            r2 = r9
            r4 = r10
            r5 = r11
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            if (r8 == 0) goto L33
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L4e
            if (r9 == 0) goto L33
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L4e
            if (r9 < 0) goto L2b
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L4e
            if (r8 == 0) goto L2a
            r8.close()
        L2a:
            return r9
        L2b:
            java.lang.String r9 = "column '_data' does not exist. "
            com.realsil.sdk.core.logger.ZLogger.w(r9)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L4e
            goto L33
        L31:
            r9 = move-exception
            goto L3e
        L33:
            if (r8 == 0) goto L38
            r8.close()
        L38:
            return r7
        L39:
            r9 = move-exception
            r8 = r7
            goto L4f
        L3c:
            r9 = move-exception
            r8 = r7
        L3e:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L4e
            com.realsil.sdk.core.logger.ZLogger.w(r9)     // Catch: java.lang.Throwable -> L4e
            if (r8 == 0) goto L4d
            r8.close()
        L4d:
            return r7
        L4e:
            r9 = move-exception
        L4f:
            if (r8 == 0) goto L54
            r8.close()
        L54:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getFileExtensionFromUrl(String str) {
        int iLastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf2 = str.lastIndexOf(35);
        if (iLastIndexOf2 > 0) {
            str = str.substring(0, iLastIndexOf2);
        }
        int iLastIndexOf3 = str.lastIndexOf(63);
        if (iLastIndexOf3 > 0) {
            str = str.substring(0, iLastIndexOf3);
        }
        int iLastIndexOf4 = str.lastIndexOf(47);
        if (iLastIndexOf4 >= 0) {
            str = str.substring(iLastIndexOf4 + 1);
        }
        return (str.isEmpty() || (iLastIndexOf = str.lastIndexOf(46)) < 0) ? "" : str.substring(iLastIndexOf + 1);
    }

    public static String getFileExtensionFromUrl2(String str) {
        int iLastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf2 = str.lastIndexOf(35);
        ZLogger.d("fragment=" + iLastIndexOf2);
        if (iLastIndexOf2 > 0) {
            str = str.substring(0, iLastIndexOf2);
        }
        int iLastIndexOf3 = str.lastIndexOf(63);
        ZLogger.d("query=" + iLastIndexOf3);
        if (iLastIndexOf3 > 0) {
            str = str.substring(0, iLastIndexOf3);
        }
        int iLastIndexOf4 = str.lastIndexOf(47);
        if (iLastIndexOf4 >= 0) {
            str = str.substring(iLastIndexOf4 + 1);
        }
        return (str.isEmpty() || !Pattern.matches("[a-zA-Z_0-9\\+\\.\\-\\(\\)\\%]+", str) || (iLastIndexOf = str.lastIndexOf(46)) < 0) ? "" : str.substring(iLastIndexOf + 1);
    }

    public static long getFileSize(String str) {
        File file = new File(str);
        if (!file.isDirectory()) {
            return file.length();
        }
        long fileSize = 0;
        for (File file2 : file.listFiles()) {
            fileSize += getFileSize(file2.getAbsolutePath());
        }
        return fileSize;
    }

    public static String getPath(Context context, Uri uri) throws Throwable {
        try {
            if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
                if (isExternalStorageDocument(uri)) {
                    String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                    if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(Environment.getExternalStorageDirectory());
                        sb.append("/");
                        sb.append(strArrSplit[1]);
                        return sb.toString();
                    }
                } else {
                    if (isDownloadsDocument(uri)) {
                        String documentId = DocumentsContract.getDocumentId(uri);
                        String[] strArrSplit2 = documentId.split(":");
                        return "raw".equalsIgnoreCase(strArrSplit2[0]) ? strArrSplit2[1] : getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
                    }
                    if (isMediaDocument(uri)) {
                        String[] strArrSplit3 = DocumentsContract.getDocumentId(uri).split(":");
                        String str = strArrSplit3[0];
                        return getDataColumn(context, "image".equals(str) ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : "video".equals(str) ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : "audio".equals(str) ? MediaStore.Audio.Media.EXTERNAL_CONTENT_URI : null, "_id=?", new String[]{strArrSplit3[1]});
                    }
                }
            } else {
                if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                    if (isGooglePhotosUri(uri)) {
                        return uri.getLastPathSegment();
                    }
                    if (isOppoUri(uri)) {
                        String path = uri.getPath();
                        File file = new File(path);
                        if (!file.exists()) {
                            return path.contains("/file_share/") ? path.replace("/file_share", "") : path;
                        }
                        ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file.getPath(), file.getName(), Long.valueOf(file.length())));
                        return path;
                    }
                    if (!isHuaweiUri(uri)) {
                        String dataColumn = getDataColumn(context, uri, null, null);
                        return !TextUtils.isEmpty(dataColumn) ? dataColumn : uri.getPath();
                    }
                    String path2 = uri.getPath();
                    File file2 = new File(path2);
                    if (!file2.exists()) {
                        return path2.contains("/root/") ? path2.replace("/root", "") : path2;
                    }
                    ZLogger.v(String.format(Locale.US, ">>>> %s\n%s\n%d", file2.getPath(), file2.getName(), Long.valueOf(file2.length())));
                    return path2;
                }
                if ("file".equalsIgnoreCase(uri.getScheme())) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("file:");
                    sb2.append(uri.getPath());
                    ZLogger.v(sb2.toString());
                    return uri.getPath();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            ZLogger.e(e2.toString());
        }
        return null;
    }

    public static String getSDCardAbsPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static File getSaveFile(String str, String str2) {
        return getSaveFile(str, str2, true);
    }

    public static File getSaveFile(String str, String str2, boolean z) {
        return getSaveFile(getSavePath(str) + File.separator + str2, z);
    }

    public static File getSaveFile(String str, boolean z) {
        File file = new File(str);
        try {
            if (!file.exists() && z) {
                file.createNewFile();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return file;
    }

    public static File getSaveFolder(String str) {
        File file = new File(getSDCardAbsPath() + File.separator + str + File.separator);
        file.mkdirs();
        return file;
    }

    public static String getSavePath(String str) {
        return getSaveFolder(str).getAbsolutePath();
    }

    public static String getSuffix(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        return getSuffix(file.getName());
    }

    public static String getSuffix(String str) {
        int iLastIndexOf;
        if (TextUtils.isEmpty(str) || str.endsWith(".") || (iLastIndexOf = str.lastIndexOf(".")) == -1) {
            return null;
        }
        return str.substring(iLastIndexOf + 1).toLowerCase(Locale.US);
    }

    public static int getUrlFileSize(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);
            int contentLength = httpURLConnection.getContentLength();
            httpURLConnection.disconnect();
            return contentLength;
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
            return 0;
        }
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isHuaweiUri(Uri uri) {
        return "com.huawei.hidisk.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isOppoUri(Uri uri) {
        return "com.coloros.fileprovider".equals(uri.getAuthority());
    }

    public static void makeDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }
}