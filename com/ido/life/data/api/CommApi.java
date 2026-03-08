package com.ido.life.data.api;

import com.ido.common.net.RetrofitService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/* JADX INFO: loaded from: classes2.dex */
public interface CommApi {
    public static final CommApi api = (CommApi) RetrofitService.getInstance().initDownloadRetrofit().create(CommApi.class);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String str);
}