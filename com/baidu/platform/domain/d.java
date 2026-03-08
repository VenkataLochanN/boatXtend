package com.baidu.platform.domain;

import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static c a() {
        return HttpClient.isHttpsEnable ? new b() : new a();
    }
}