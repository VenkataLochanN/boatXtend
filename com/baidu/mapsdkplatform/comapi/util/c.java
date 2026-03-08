package com.baidu.mapsdkplatform.comapi.util;

import android.util.Log;
import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: loaded from: classes.dex */
class c extends HttpClient.ProtoResultCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ CustomMapStyleLoader f3832a;

    c(CustomMapStyleLoader customMapStyleLoader) {
        this.f3832a = customMapStyleLoader;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        Log.e(CustomMapStyleLoader.f3801a, "sendRequest onFailed error = " + httpStateError);
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        this.f3832a.b(str);
    }
}