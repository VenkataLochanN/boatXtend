package com.baidu.mapsdkplatform.comjni.map.commonmemcache;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f3865a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private JNICommonMemCache f3866b;

    public a() {
        this.f3866b = null;
        this.f3866b = new JNICommonMemCache();
    }

    public long a() {
        if (this.f3865a == 0) {
            this.f3865a = this.f3866b.Create();
        }
        return this.f3865a;
    }

    public void b() {
        long j = this.f3865a;
        if (j != 0) {
            this.f3866b.Init(j);
        }
    }
}