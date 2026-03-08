package com.ido.ble.dfu.nodic.firmware;

import com.ido.ble.dfu.nodic.firmware.FirmwareListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static void a(CheckNewVersionPara checkNewVersionPara, FirmwareListener.ICheckNewVersionListener iCheckNewVersionListener) {
        a.a(checkNewVersionPara, iCheckNewVersionListener);
    }

    public static void a(String str, String str2, String str3, FirmwareListener.IDownloadListener iDownloadListener) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request requestBuild = new Request.Builder().url(str).build();
        try {
            String str4 = str2 + File.separator + str3;
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            iDownloadListener.onStart();
            Response responseExecute = okHttpClient.newCall(requestBuild).execute();
            if (!responseExecute.isSuccessful()) {
                return;
            }
            ResponseBody responseBodyBody = responseExecute.body();
            long jContentLength = responseBodyBody.contentLength();
            long j = 0;
            InputStream inputStreamByteStream = responseBodyBody.byteStream();
            FileOutputStream fileOutputStream = new FileOutputStream(str4);
            byte[] bArr = new byte[512];
            while (true) {
                int i = inputStreamByteStream.read(bArr);
                if (i == -1) {
                    fileOutputStream.close();
                    iDownloadListener.onSuccess();
                    return;
                } else {
                    fileOutputStream.write(bArr, 0, i);
                    j += (long) i;
                    iDownloadListener.onProgress(Math.round((100 * j) / (jContentLength * 1.0f)));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            iDownloadListener.onFailed();
        }
    }
}