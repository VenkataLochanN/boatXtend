package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzgz extends zzha {
    private boolean zza;

    zzgz(zzgf zzgfVar) {
        super(zzgfVar);
        this.zzx.zza(this);
    }

    protected void f_() {
    }

    protected abstract boolean zze();

    final boolean zzz() {
        return this.zza;
    }

    protected final void zzaa() {
        if (!zzz()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzab() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zze()) {
            return;
        }
        this.zzx.zzag();
        this.zza = true;
    }

    public final void zzac() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        f_();
        this.zzx.zzag();
        this.zza = true;
    }
}