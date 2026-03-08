package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AmapCellLte.java */
/* JADX INFO: loaded from: classes.dex */
public final class ld extends la {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public ld(boolean z) {
        super(z, true);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
    }

    @Override // com.amap.api.mapcore.util.la
    /* JADX INFO: renamed from: a */
    public final la clone() {
        ld ldVar = new ld(this.f1650h);
        ldVar.a(this);
        ldVar.j = this.j;
        ldVar.k = this.k;
        ldVar.l = this.l;
        ldVar.m = this.m;
        ldVar.n = this.n;
        return ldVar;
    }

    @Override // com.amap.api.mapcore.util.la
    public final String toString() {
        return "AmapCellLte{lac=" + this.j + ", cid=" + this.k + ", pci=" + this.l + ", earfcn=" + this.m + ", timingAdvance=" + this.n + '}' + super.toString();
    }
}