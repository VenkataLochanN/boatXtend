package com.baidu.location;

/* JADX INFO: loaded from: classes.dex */
class c extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ LocationClient f2263a;

    c(LocationClient locationClient) {
        this.f2263a = locationClient;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.f2263a.E == null) {
            LocationClient locationClient = this.f2263a;
            locationClient.E = new com.baidu.location.a.c(locationClient.f2039f, this.f2263a.f2037d, this.f2263a);
        }
        if (this.f2263a.E != null) {
            this.f2263a.E.c();
        }
    }
}