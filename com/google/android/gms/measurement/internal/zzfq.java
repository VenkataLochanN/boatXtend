package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfq {
    private final String zza;
    private final boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private final /* synthetic */ zzfo zze;

    public zzfq(zzfo zzfoVar, String str, boolean z) {
        this.zze = zzfoVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = z;
    }

    public final boolean zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzg().getBoolean(this.zza, this.zzb);
        }
        return this.zzd;
    }

    public final void zza(boolean z) {
        SharedPreferences.Editor editorEdit = this.zze.zzg().edit();
        editorEdit.putBoolean(this.zza, z);
        editorEdit.apply();
        this.zzd = z;
    }
}