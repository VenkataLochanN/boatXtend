package com.baidu.platform.base;

import com.baidu.mapapi.search.core.SearchResult;

/* JADX INFO: loaded from: classes.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f3898a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ SearchResult f3899b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ Object f3900c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ a f3901d;

    c(a aVar, d dVar, SearchResult searchResult, Object obj) {
        this.f3901d = aVar;
        this.f3898a = dVar;
        this.f3899b = searchResult;
        this.f3900c = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f3898a != null) {
            this.f3901d.f3890a.lock();
            try {
                this.f3898a.a(this.f3899b, this.f3900c);
            } finally {
                this.f3901d.f3890a.unlock();
            }
        }
    }
}