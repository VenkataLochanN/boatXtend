package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzt extends zzu {
    private zzbk.zze zzg;
    private final /* synthetic */ zzn zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzt(zzn zznVar, String str, int i, zzbk.zze zzeVar) {
        super(str, i);
        this.zzh = zznVar;
        this.zzg = zzeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzu
    final boolean zzb() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzu
    final boolean zzc() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzu
    final int zza() {
        return this.zzg.zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    final boolean zza(Long l, Long l2, zzbs.zzk zzkVar, boolean z) {
        boolean zZzd = this.zzh.zzt().zzd(this.zza, zzap.zzbk);
        boolean zZzd2 = this.zzh.zzt().zzd(this.zza, zzap.zzbq);
        byte b2 = com.google.android.gms.internal.measurement.zzkd.zzb() && this.zzh.zzt().zzd(this.zza, zzap.zzbt);
        boolean zZze = this.zzg.zze();
        boolean zZzf = this.zzg.zzf();
        byte b3 = zZzd && this.zzg.zzh();
        byte b4 = zZze || zZzf || b3 == true;
        Boolean boolZza = null;
        boolZza = null;
        boolZza = null;
        boolZza = null;
        boolZza = null;
        if (z && b4 == false) {
            this.zzh.zzr().zzx().zza("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzb), this.zzg.zza() ? Integer.valueOf(this.zzg.zzb()) : null);
            return true;
        }
        zzbk.zzc zzcVarZzd = this.zzg.zzd();
        boolean zZzf2 = zzcVarZzd.zzf();
        if (zzkVar.zzf()) {
            if (!zzcVarZzd.zzc()) {
                this.zzh.zzr().zzi().zza("No number filter for long property. property", this.zzh.zzo().zzc(zzkVar.zzc()));
            } else {
                boolZza = zza(zza(zzkVar.zzg(), zzcVarZzd.zzd()), zZzf2);
            }
        } else if (zzkVar.zzh()) {
            if (!zzcVarZzd.zzc()) {
                this.zzh.zzr().zzi().zza("No number filter for double property. property", this.zzh.zzo().zzc(zzkVar.zzc()));
            } else {
                boolZza = zza(zza(zzkVar.zzi(), zzcVarZzd.zzd()), zZzf2);
            }
        } else if (zzkVar.zzd()) {
            if (!zzcVarZzd.zza()) {
                if (!zzcVarZzd.zzc()) {
                    this.zzh.zzr().zzi().zza("No string or number filter defined. property", this.zzh.zzo().zzc(zzkVar.zzc()));
                } else if (zzkr.zza(zzkVar.zze())) {
                    boolZza = zza(zza(zzkVar.zze(), zzcVarZzd.zzd()), zZzf2);
                } else {
                    this.zzh.zzr().zzi().zza("Invalid user property value for Numeric number filter. property, value", this.zzh.zzo().zzc(zzkVar.zzc()), zzkVar.zze());
                }
            } else {
                boolZza = zza(zza(zzkVar.zze(), zzcVarZzd.zzb(), this.zzh.zzr()), zZzf2);
            }
        } else {
            this.zzh.zzr().zzi().zza("User property has no value, property", this.zzh.zzo().zzc(zzkVar.zzc()));
        }
        this.zzh.zzr().zzx().zza("Property filter result", boolZza == null ? "null" : boolZza);
        if (boolZza == null) {
            return false;
        }
        this.zzc = true;
        if (zZzd && b3 == true && !boolZza.booleanValue()) {
            return true;
        }
        if (!z || this.zzg.zze()) {
            this.zzd = boolZza;
        }
        if (boolZza.booleanValue() && b4 != false && zzkVar.zza()) {
            long jZzb = zzkVar.zzb();
            if (zZzd2 && l != null) {
                jZzb = l.longValue();
            }
            if (b2 != false && this.zzg.zze() && !this.zzg.zzf() && l2 != null) {
                jZzb = l2.longValue();
            }
            if (this.zzg.zzf()) {
                this.zzf = Long.valueOf(jZzb);
            } else {
                this.zze = Long.valueOf(jZzb);
            }
        }
        return true;
    }
}