package com.baidu.mapsdkplatform.comapi.synchronization.b;

import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceQueryOptions;
import com.baidu.mapsdkplatform.comapi.synchronization.b.b;
import com.baidu.mapsdkplatform.comapi.synchronization.c.c;

/* JADX INFO: loaded from: classes.dex */
class d extends com.baidu.mapsdkplatform.comapi.synchronization.c.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f3662a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ HistoryTraceQueryOptions f3663b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3664c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ b.a f3665d;

    d(b.a aVar, int i, HistoryTraceQueryOptions historyTraceQueryOptions, String str) {
        this.f3665d = aVar;
        this.f3662a = i;
        this.f3663b = historyTraceQueryOptions;
        this.f3664c = str;
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.c.e
    public void a(c.a aVar) {
        if ((c.a.SERVER_ERROR == aVar || c.a.NETWORK_ERROR == aVar || c.a.INNER_ERROR == aVar || c.a.REQUEST_ERROR == aVar) && b.f3657g <= 2) {
            this.f3665d.a(this.f3664c, this.f3662a, this.f3663b);
            b.f3657g++;
        }
        if (2 < b.f3657g) {
            this.f3665d.a(aVar, this.f3662a);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.synchronization.c.e
    public void a(String str) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3653a, "Request success, the result = " + str);
        this.f3665d.a(3, str, this.f3662a, this.f3663b);
        int unused = b.f3657g = 0;
    }
}