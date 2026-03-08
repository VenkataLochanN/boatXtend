package com.google.android.gms.internal.fitness;

/* JADX INFO: loaded from: classes.dex */
public final class zzm {
    private final double zzet;
    private final double zzeu;

    private zzm(double d2, double d3) {
        this.zzet = d2;
        this.zzeu = d3;
    }

    public final boolean zza(double d2) {
        return d2 >= this.zzet && d2 <= this.zzeu;
    }
}