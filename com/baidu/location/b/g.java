package com.baidu.location.b;

import android.os.Environment;
import android.text.TextUtils;
import com.baidu.location.g.k;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.Thread;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class g implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static g f2245a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2246b = 0;

    private g() {
    }

    public static g a() {
        if (f2245a == null) {
            f2245a = new g();
        }
        return f2245a;
    }

    private String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    private void a(File file, String str, String str2) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(280L);
            randomAccessFile.writeInt(12346);
            randomAccessFile.seek(300L);
            randomAccessFile.writeLong(System.currentTimeMillis());
            byte[] bytes = str.getBytes();
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes, 0, bytes.length);
            randomAccessFile.seek(600L);
            byte[] bytes2 = str2.getBytes();
            randomAccessFile.writeInt(bytes2.length);
            randomAccessFile.write(bytes2, 0, bytes2.length);
            if (!a(str, str2)) {
                randomAccessFile.seek(280L);
                randomAccessFile.writeInt(1326);
            }
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    private boolean a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !com.baidu.location.e.i.j()) {
            return false;
        }
        try {
            URL url = new URL(k.f2504e);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("e0");
            stringBuffer.append("=");
            stringBuffer.append(str);
            stringBuffer.append("&");
            stringBuffer.append("e1");
            stringBuffer.append("=");
            stringBuffer.append(str2);
            stringBuffer.append("&");
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(com.baidu.location.g.a.f2453b);
            httpURLConnection.setReadTimeout(com.baidu.location.g.a.f2453b);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpURLConnection.setRequestProperty("Accept-Charset", Key.STRING_CHARSET_NAME);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(stringBuffer.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception unused) {
        }
        return httpURLConnection.getResponseCode() == 200;
    }

    public void b() {
        String str;
        try {
            File file = new File((Environment.getExternalStorageDirectory().getPath() + "/traces") + "/error_fs2.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(280L);
                if (1326 == randomAccessFile.readInt()) {
                    randomAccessFile.seek(308L);
                    int i = randomAccessFile.readInt();
                    String str2 = null;
                    if (i <= 0 || i >= 2048) {
                        str = null;
                    } else {
                        byte[] bArr = new byte[i];
                        randomAccessFile.read(bArr, 0, i);
                        str = new String(bArr, 0, i);
                    }
                    randomAccessFile.seek(600L);
                    int i2 = randomAccessFile.readInt();
                    if (i2 > 0 && i2 < 2048) {
                        byte[] bArr2 = new byte[i2];
                        randomAccessFile.read(bArr2, 0, i2);
                        str2 = new String(bArr2, 0, i2);
                    }
                    if (a(str, str2)) {
                        randomAccessFile.seek(280L);
                        randomAccessFile.writeInt(12346);
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0088  */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uncaughtException(java.lang.Thread r8, java.lang.Throwable r9) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.g.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}