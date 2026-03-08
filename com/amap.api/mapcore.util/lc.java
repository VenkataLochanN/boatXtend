package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AmapCellGsm.java */
/* JADX INFO: loaded from: classes.dex */
public final class lc extends la {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;

    public lc(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
        this.o = Integer.MAX_VALUE;
    }

    @Override // com.amap.api.mapcore.util.la
    /* JADX INFO: renamed from: a */
    public final la clone() {
        lc lcVar = new lc(this.f1650h, this.i);
        lcVar.a(this);
        lcVar.j = this.j;
        lcVar.k = this.k;
        lcVar.l = this.l;
        lcVar.m = this.m;
        lcVar.n = this.n;
        lcVar.o = this.o;
        return lcVar;
    }

    @Override // com.amap.api.mapcore.util.la
    public final String toString() {
        return "AmapCellGsm{lac=" + this.j + ", cid=" + this.k + ", psc=" + this.l + ", arfcn=" + this.m + ", bsic=" + this.n + ", timingAdvance=" + this.o + '}' + super.toString();
    }
}