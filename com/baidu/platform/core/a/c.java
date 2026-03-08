package com.baidu.platform.core.a;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.baidu.platform.base.e {
    public c(String str) {
        a(str);
    }

    private void a(String str) {
        this.f3903a.a("qt", "ext");
        this.f3903a.a("num", "1000");
        this.f3903a.a("l", "10");
        this.f3903a.a("ie", "utf-8");
        this.f3903a.a("oue", "1");
        this.f3903a.a("res", "api");
        this.f3903a.a("fromproduct", "android_map_sdk");
        this.f3903a.a("uid", str);
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.o();
    }
}