package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zze extends zzf {
    private boolean zza;

    zze(zzgf zzgfVar) {
        super(zzgfVar);
        this.zzx.zza(this);
    }

    protected void zzaa() {
    }

    protected abstract boolean zzz();

    final boolean zzv() {
        return this.zza;
    }

    protected final void zzw() {
        if (!zzv()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzx() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzz()) {
            return;
        }
        this.zzx.zzag();
        this.zza = true;
    }

    public final void zzy() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzaa();
        this.zzx.zzag();
        this.zza = true;
    }
}