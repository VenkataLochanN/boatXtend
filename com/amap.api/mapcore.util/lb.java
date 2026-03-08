package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AmapCellCdma.java */
/* JADX INFO: loaded from: classes.dex */
public final class lb extends la {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public lb(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }

    @Override // com.amap.api.mapcore.util.la
    /* JADX INFO: renamed from: a */
    public final la clone() {
        lb lbVar = new lb(this.f1650h, this.i);
        lbVar.a(this);
        this.j = lbVar.j;
        this.k = lbVar.k;
        this.l = lbVar.l;
        this.m = lbVar.m;
        this.n = lbVar.n;
        return lbVar;
    }

    @Override // com.amap.api.mapcore.util.la
    public final String toString() {
        return "AmapCellCdma{sid=" + this.j + ", nid=" + this.k + ", bid=" + this.l + ", latitude=" + this.m + ", longitude=" + this.n + '}' + super.toString();
    }
}