package com.ido.common.net;

import com.ido.common.log.CommonLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class UploadUtil {
    private static final String TAG = "UploadUtil";
    private OkHttpClient okHttpClient;

    public interface OnCommCallback<T> {
        void onFailed(String str);

        void onSuccess(T t);
    }

    private UploadUtil() {
        this.okHttpClient = new OkHttpClient();
    }

    private static class UploadUtilInstance {
        private static final UploadUtil INSTANCE = new UploadUtil();

        private UploadUtilInstance() {
        }
    }

    public static UploadUtil getInstance() {
        return UploadUtilInstance.INSTANCE;
    }

    public void upload(String str, String str2, Map<String, Object> map, final OnCommCallback<String> onCommCallback) {
        CommonLogUtil.d(TAG, "upload: " + str);
        File file = new File(str2);
        if (!file.exists()) {
            onCommCallback.onFailed("文件不存在");
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (map != null) {
            for (String str3 : map.keySet()) {
                Object obj = map.get(str3);
                builder.addFormDataPart(str3, obj != null ? obj.toString() : "");
            }
        }
        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), file));
        this.okHttpClient.newCall(new Request.Builder().url(str).post(builder.build()).build()).enqueue(new Callback() { // from class: com.ido.common.net.UploadUtil.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(iOException.toString());
                    CommonLogUtil.d(UploadUtil.TAG, "onFailure: " + iOException.toString());
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    try {
                        onCommCallback2.onSuccess(response.body().string());
                    } catch (IOException e2) {
                        onCommCallback.onFailed(e2.toString());
                        e2.printStackTrace();
                    }
                }
            }
        });
    }
}