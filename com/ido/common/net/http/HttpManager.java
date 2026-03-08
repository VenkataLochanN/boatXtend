package com.ido.common.net.http;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class HttpManager {
    private static final String TAG = "HttpManager";
    private static long TIME_OUT = 15000;
    private static volatile HttpManager instance;
    private OkHttpClient okHttpClient;

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private HttpManager() {
        this.okHttpClient = null;
        this.okHttpClient = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).readTimeout(TIME_OUT, TimeUnit.SECONDS).writeTimeout(TIME_OUT, TimeUnit.SECONDS).build();
    }

    public void getRequestString(String str, Map<String, Object> map, final IHttpCallback<String> iHttpCallback, boolean z) {
        Request requestBuild;
        if (map == null) {
            map = new HashMap<>();
        }
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        StringBuilder sb = new StringBuilder();
        sb.append("getRequestString------------>subUrl:");
        sb.append(str);
        sb.append(",isGet:");
        sb.append(z);
        sb.append(",parameter ");
        sb.append(map == null ? " null " : map.toString());
        printLog(sb.toString());
        if (z) {
            requestBuild = builder.build();
        } else {
            FormBody.Builder builder2 = new FormBody.Builder();
            if (map != null) {
                for (String str2 : map.keySet()) {
                    Object obj = map.get(str2);
                    builder2.add(str2, obj != null ? obj.toString() : "");
                }
            }
            builder.post(builder2.build());
            requestBuild = builder.build();
        }
        this.okHttpClient.newCall(requestBuild).enqueue(new Callback() { // from class: com.ido.common.net.http.HttpManager.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                IHttpCallback iHttpCallback2 = iHttpCallback;
                if (iHttpCallback2 != null) {
                    iHttpCallback2.onFaild(iOException.toString());
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                IHttpCallback iHttpCallback2 = iHttpCallback;
                if (iHttpCallback2 != null) {
                    iHttpCallback2.onSuccess(response.body().string());
                }
            }
        });
    }

    private void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getServerLogPath(), TAG, str);
    }
}