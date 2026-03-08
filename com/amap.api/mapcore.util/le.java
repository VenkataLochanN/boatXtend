package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AmapCellWcdma.java */
/* JADX INFO: loaded from: classes.dex */
public final class le extends la {
    public int j;
    public int k;
    public int l;
    public int m;

    public le(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
    }

    @Override // com.amap.api.mapcore.util.la
    /* JADX INFO: renamed from: a */
    public final la clone() {
        le leVar = new le(this.f1650h, this.i);
        leVar.a(this);
        leVar.j = this.j;
        leVar.k = this.k;
        leVar.l = this.l;
        leVar.m = this.m;
        return leVar;
    }

    @Override // com.amap.api.mapcore.util.la
    public final String toString() {
        return "AmapCellWcdma{lac=" + this.j + ", cid=" + this.k + ", psc=" + this.l + ", uarfcn=" + this.m + '}' + super.toString();
    }
}