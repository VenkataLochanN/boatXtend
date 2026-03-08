package com.baidu.location.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import com.baidu.location.Jni;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class o extends com.baidu.location.g.e {
    private static o q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2164a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f2165b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f2166c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f2167d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2168e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Handler f2169f;

    private o() {
        this.f2169f = null;
        this.f2169f = new Handler();
    }

    public static void a(File file, File file2) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
        try {
            byte[] bArr = new byte[5120];
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i == -1) {
                    bufferedOutputStream.flush();
                    file.delete();
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                    return;
                }
                bufferedOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) {
                return false;
            }
            String strA = com.baidu.location.e.d.a(com.baidu.location.e.b.a().e());
            if (strA.equals("3G")) {
                return true;
            }
            return strA.equals("4G");
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(String str, String str2) {
        File file = new File(com.baidu.location.g.k.j() + File.separator + "tmp");
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[4096];
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            }
            httpURLConnection.disconnect();
            fileOutputStream.close();
            bufferedInputStream.close();
            if (file.length() < 10240) {
                file.delete();
                return false;
            }
            file.renameTo(new File(com.baidu.location.g.k.j() + File.separator + str2));
            return true;
        } catch (Exception unused) {
            file.delete();
            return false;
        }
    }

    public static o b() {
        if (q == null) {
            q = new o();
        }
        return q;
    }

    private Handler d() {
        return this.f2169f;
    }

    private void e() {
        try {
            File file = new File(com.baidu.location.g.k.j() + "/grtcfrsa.dat");
            if (!file.exists()) {
                File file2 = new File(com.baidu.location.g.j.f2498a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2L);
                randomAccessFile.writeInt(0);
                randomAccessFile.seek(8L);
                byte[] bytes = "1980_01_01:0".getBytes();
                randomAccessFile.writeInt(bytes.length);
                randomAccessFile.write(bytes);
                randomAccessFile.seek(200L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.seek(800L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(200L);
            randomAccessFile2.writeBoolean(true);
            if (this.f2168e == 1) {
                randomAccessFile2.writeBoolean(true);
            } else {
                randomAccessFile2.writeBoolean(false);
            }
            if (this.f2167d != null) {
                byte[] bytes2 = this.f2167d.getBytes();
                randomAccessFile2.writeInt(bytes2.length);
                randomAccessFile2.write(bytes2);
            } else if (Math.abs(com.baidu.location.f.getFrameVersion() - 7.72f) < 1.0E-8f) {
                randomAccessFile2.writeInt(0);
            }
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.f2164a == null) {
            return;
        }
        new s(this).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        if (this.f2166c == null) {
            return true;
        }
        if (new File(com.baidu.location.g.k.j() + File.separator + this.f2166c).exists()) {
            return true;
        }
        return a("http://" + this.f2164a + "/" + this.f2166c, this.f2166c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() throws Throwable {
        if (this.f2165b == null) {
            return;
        }
        File file = new File(com.baidu.location.g.k.j() + File.separator + this.f2165b);
        if (file.exists()) {
            return;
        }
        if (a("http://" + this.f2164a + "/" + this.f2165b, this.f2165b)) {
            String strA = com.baidu.location.g.k.a(file, "SHA-256");
            String str = this.f2167d;
            if (str == null || strA == null || !com.baidu.location.g.k.b(strA, str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiP7BS5IjEOzrKGR9/Ww9oSDhdX1ir26VOsYjT1T6tk2XumRpkHRwZbrucDcNnvSB4QsqiEJnvTSRi7YMbh2H9sLMkcvHlMV5jAErNvnuskWfcvf7T2mq7EUZI/Hf4oVZhHV0hQJRFVdTcjWI6q2uaaKM3VMh+roDesiE7CR2biQIDAQAB")) {
                return;
            }
            File file2 = new File(com.baidu.location.g.k.j() + File.separator + com.baidu.location.f.replaceFileName);
            if (file2.exists()) {
                file2.delete();
            }
            try {
                a(file, file2);
            } catch (Exception unused) {
                file2.delete();
            }
        }
    }

    @Override // com.baidu.location.g.e
    public void a() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.72f);
        stringBuffer.append("&fw=");
        stringBuffer.append(com.baidu.location.f.getFrameVersion());
        stringBuffer.append("&suit=");
        stringBuffer.append(2);
        if (com.baidu.location.g.b.a().f2466c == null) {
            stringBuffer.append("&im=");
            str = com.baidu.location.g.b.a().f2464a;
        } else {
            stringBuffer.append("&cu=");
            str = com.baidu.location.g.b.a().f2466c;
        }
        stringBuffer.append(str);
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&sv=");
        String strSubstring = Build.VERSION.RELEASE;
        if (strSubstring != null && strSubstring.length() > 10) {
            strSubstring = strSubstring.substring(0, 10);
        }
        stringBuffer.append(strSubstring);
        String str2 = null;
        try {
            if (Build.VERSION.SDK_INT > 20) {
                String[] strArr = Build.SUPPORTED_ABIS;
                String str3 = null;
                for (int i = 0; i < strArr.length; i++) {
                    str3 = i == 0 ? strArr[i] + ";" : str3 + strArr[i] + ";";
                }
                str2 = str3;
            } else {
                str2 = Build.CPU_ABI2;
            }
        } catch (Error | Exception unused) {
        }
        if (str2 != null) {
            stringBuffer.append("&cpuabi=");
            stringBuffer.append(str2);
        }
        stringBuffer.append("&pack=");
        stringBuffer.append(com.baidu.location.g.b.f2460e);
        this.f2489h = com.baidu.location.g.k.f() + "?&it=" + Jni.en1(stringBuffer.toString());
    }

    @Override // com.baidu.location.g.e
    public void a(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(this.j);
                if ("up".equals(jSONObject.getString("res"))) {
                    this.f2164a = jSONObject.getString("upath");
                    if (jSONObject.has("u1")) {
                        this.f2165b = jSONObject.getString("u1");
                    }
                    if (jSONObject.has("u2")) {
                        this.f2166c = jSONObject.getString("u2");
                    }
                    if (jSONObject.has("u1_rsa")) {
                        this.f2167d = jSONObject.getString("u1_rsa");
                    }
                    d().post(new r(this));
                }
                if (jSONObject.has("ison")) {
                    this.f2168e = jSONObject.getInt("ison");
                }
                e();
            } catch (Exception unused) {
            }
        }
        com.baidu.location.g.c.a().a(System.currentTimeMillis());
    }

    public void c() {
        if (System.currentTimeMillis() - com.baidu.location.g.c.a().b() > DateUtil.DAY) {
            d().postDelayed(new p(this), DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
            d().postDelayed(new q(this), BootloaderScanner.TIMEOUT);
        }
    }
}