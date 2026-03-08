package com.baidu.mapsdkplatform.comapi.synchronization.c;

import com.baidu.mapsdkplatform.comapi.synchronization.c.a;

/* JADX INFO: loaded from: classes.dex */
class b extends a.AbstractRunnableC0033a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f3681a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3682b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ a f3683c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    b(a aVar, e eVar, String str) {
        super(null);
        this.f3683c = aVar;
        this.f3681a = eVar;
        this.f3682b = str;
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.c.a.AbstractRunnableC0033a
    public void a() throws Throwable {
        c cVar = new c("GET", this.f3681a);
        cVar.b(this.f3683c.f3678a);
        cVar.a(this.f3683c.f3679b);
        cVar.a(this.f3682b);
    }
}