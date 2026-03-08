package com.loc;

/* JADX INFO: compiled from: UpdateStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class bq {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    bq f4878c;

    public bq() {
    }

    public bq(bq bqVar) {
        this.f4878c = bqVar;
    }

    public void a(int i) {
        bq bqVar = this.f4878c;
        if (bqVar != null) {
            bqVar.a(i);
        }
    }

    public void a(boolean z) {
        bq bqVar = this.f4878c;
        if (bqVar != null) {
            bqVar.a(z);
        }
    }

    protected abstract boolean a();

    public int b() {
        bq bqVar = this.f4878c;
        return Math.min(Integer.MAX_VALUE, bqVar != null ? bqVar.b() : Integer.MAX_VALUE);
    }

    public final boolean c() {
        bq bqVar = this.f4878c;
        if (bqVar != null ? bqVar.c() : true) {
            return a();
        }
        return false;
    }
}