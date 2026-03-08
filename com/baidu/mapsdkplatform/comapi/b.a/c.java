package com.baidu.mapsdkplatform.comapi.b.a;

import android.content.Context;
import android.os.Build;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.mapsdkplatform.comapi.util.h;
import com.baidu.mapsdkplatform.comapi.util.i;
import com.baidu.mapsdkplatform.comjni.util.JNIHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3469a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3470b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3471c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3472d;

    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final c f3473a = new c();
    }

    public static c a() {
        return a.f3473a;
    }

    private void a(InputStream inputStream, OutputStream outputStream) throws Exception {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr, 0, 1024);
            if (i == -1) {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                try {
                    outputStream.close();
                    inputStream.close();
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            gZIPOutputStream.write(bArr, 0, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(File[] fileArr) {
        int length = fileArr.length;
        for (int i = 0; i < length - 10; i++) {
            int i2 = i + 10;
            if (fileArr[i2] != null && fileArr[i2].exists()) {
                fileArr[i2].delete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:107:0x010f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x011e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[Catch: all -> 0x012b, SYNTHETIC, TRY_ENTER, TRY_LEAVE, TryCatch #9 {, blocks: (B:7:0x000b, B:39:0x00ea, B:42:0x00f1, B:44:0x00f9, B:58:0x010f, B:61:0x0116, B:63:0x011e, B:64:0x0121, B:68:0x0127, B:74:0x0131, B:76:0x0139), top: B:106:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean a(java.io.File r10) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.b.a.c.a(java.io.File):boolean");
    }

    private byte[] a(byte[] bArr) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        a(byteArrayInputStream, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byteArrayInputStream.close();
        return byteArray;
    }

    private StringBuilder b(File file) {
        String[] strArrSplit = file.getName().substring(0, file.getName().length() - 4).split("_");
        StringBuilder sb = new StringBuilder();
        sb.append("--bd_map_sdk_cc");
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        sb.append("Content-Disposition: form-data; name=\"phoneinfo\"\r\n");
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        sb.append(URLDecoder.decode(SyncSysInfo.getPhoneInfo() + "&abi=" + f3471c));
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        sb.append("--bd_map_sdk_cc");
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        if (strArrSplit[0] != null && !strArrSplit[0].isEmpty()) {
            sb.append("Content-Disposition: form-data; name=\"packname\"\r\n");
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            sb.append(strArrSplit[0]);
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            sb.append("--bd_map_sdk_cc");
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        if (strArrSplit[1] != null && !strArrSplit[1].isEmpty()) {
            sb.append("Content-Disposition: form-data; name=\"version\"\r\n");
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            sb.append(strArrSplit[1]);
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            sb.append("--bd_map_sdk_cc");
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        if (strArrSplit[2] != null && !strArrSplit[2].isEmpty()) {
            sb.append("Content-Disposition: form-data; name=\"timestamp\"\r\n");
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            sb.append(strArrSplit[2]);
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            sb.append("--bd_map_sdk_cc");
            sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        sb.append("Content-Disposition: form-data; name=\"os\"\r\n");
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        sb.append("android");
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        sb.append("--bd_map_sdk_cc");
        sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        return sb;
    }

    private void d() {
        if (h.a().b() == null) {
            return;
        }
        String strB = h.a().b().b();
        if (strB.isEmpty()) {
            return;
        }
        String str = strB + File.separator + "crash";
        File file = new File(str);
        if (file.exists() || file.mkdir()) {
            f3469a = str;
        } else {
            f3469a = strB;
        }
    }

    private void e() {
        String str;
        String str2 = f3469a;
        if (str2 == null || str2.isEmpty() || (str = f3470b) == null || str.isEmpty()) {
            return;
        }
        String str3 = f3469a + File.separator + f3470b;
        com.baidu.mapsdkplatform.comapi.b.a.a.a().a(str3);
        JNIHandler.registerNativeHandler(str3);
    }

    private void f() {
        if (NetworkUtil.isNetworkAvailable(this.f3472d)) {
            new Thread(new d(this)).start();
        }
    }

    private HttpURLConnection g() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.map.baidu.com/lbs_sdkcc/report").openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=bd_map_sdk_cc");
            httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
            httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
            httpURLConnection.setConnectTimeout(10000);
            return httpURLConnection;
        } catch (Exception unused) {
            return null;
        }
    }

    public void a(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (Build.SUPPORTED_ABIS.length > 0) {
            f3471c = Build.SUPPORTED_ABIS[0];
        }
        this.f3472d = context;
        String strN = i.n();
        if (strN.isEmpty()) {
            return;
        }
        if (strN.contains("_")) {
            strN = strN.replaceAll("_", "");
        }
        f3470b = strN + "_" + i.i() + "_";
        d();
        e();
        f();
    }
}