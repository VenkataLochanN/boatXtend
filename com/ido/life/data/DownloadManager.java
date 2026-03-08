package com.ido.life.data;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.data.api.CommApi;
import com.ido.life.util.FileUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadManager {
    private static final int BUFFER_SIZE = 1024;
    private static final String TAG = "DownloadManager";

    public interface DownloadListener {
        void onDownloadFailed(int i, String str);

        void onDownloadFinish(String str);

        void onDownloadProgress(int i);

        void onDownloadStart();
    }

    public static void download(String str, final String str2, final DownloadListener downloadListener) {
        if (TextUtils.isEmpty(str)) {
            downloadFailed(str2, -1, "download url is empty", downloadListener);
            return;
        }
        if (downloadListener != null) {
            downloadListener.onDownloadStart();
        }
        CommApi.api.download(str).enqueue(new Callback<ResponseBody>() { // from class: com.ido.life.data.DownloadManager.1
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) throws Throwable {
                DownloadManager.writeFileFromIS(new File(str2), response.body(), downloadListener);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                DownloadManager.downloadFailed(str2, -1, "network error, download failed: " + th, downloadListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeFileFromIS(File file, ResponseBody responseBody, DownloadListener downloadListener) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        IOException e2;
        byte[] bArr;
        int i;
        if (responseBody == null) {
            downloadFailed(file.getAbsolutePath(), -1, "file not exist", downloadListener);
            return;
        }
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                downloadFailed(file.getAbsolutePath(), -1, "file path not exist", downloadListener);
                return;
            }
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e3) {
                e3.printStackTrace();
                downloadFailed(file.getAbsolutePath(), -2, "createNewFile IOException : " + e3.getMessage(), downloadListener);
                return;
            }
        }
        InputStream inputStreamByteStream = responseBody.byteStream();
        long jContentLength = responseBody.contentLength();
        CommonLogUtil.d("writeFileFromIS()  response.body()---- 总长度(单位字节)：" + jContentLength);
        long j = 0;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (IOException e4) {
            bufferedOutputStream = null;
            e2 = e4;
        } catch (Throwable th) {
            th = th;
            bufferedOutputStream = null;
        }
        try {
            try {
                bArr = new byte[1024];
                i = 0;
            } catch (Throwable th2) {
                th = th2;
            }
            while (true) {
                int i2 = inputStreamByteStream.read(bArr);
                if (i2 == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, i2);
                j += (long) i2;
                int i3 = (int) ((j * 100.0f) / jContentLength);
                if (downloadListener != null && i3 > i) {
                    downloadListener.onDownloadProgress(i3);
                    i = i3;
                }
                th = th2;
                try {
                    inputStreamByteStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                        throw th;
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        throw th;
                    }
                }
                throw th;
            }
            bufferedOutputStream.flush();
            try {
                inputStreamByteStream.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            try {
                bufferedOutputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            if (downloadListener != null) {
                downloadListener.onDownloadFinish(file.getAbsolutePath());
            }
        } catch (IOException e9) {
            e2 = e9;
            e2.printStackTrace();
            downloadFailed(file.getAbsolutePath(), -3, "write file IOException : " + e2.getMessage(), downloadListener);
            try {
                inputStreamByteStream.close();
            } catch (IOException e10) {
                e10.printStackTrace();
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e11) {
                    e11.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void downloadFailed(String str, int i, String str2, DownloadListener downloadListener) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "下载文件失败：" + str2);
        FileUtil.deleteFile(str);
        if (downloadListener != null) {
            downloadListener.onDownloadFailed(i, str2);
        }
    }
}