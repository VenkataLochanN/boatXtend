package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjc extends zzaf {
    private final /* synthetic */ zzis zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzjc(zzis zzisVar, zzhc zzhcVar) {
        super(zzhcVar);
        this.zza = zzisVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzaf
    public final void zza() {
        this.zza.zzr().zzi().zza("Tasks have been queued for a long time");
    }
}