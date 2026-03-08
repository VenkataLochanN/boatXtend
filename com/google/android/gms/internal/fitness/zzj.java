package com.google.android.gms.internal.fitness;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class zzj {
    public static <DP, DT> String zza(DP dp, zzg<DP, DT> zzgVar) {
        double dZza;
        zzh<DT> zzhVarZzb = zzgVar.zzb();
        if (!zzhVarZzb.zzb(zzgVar.zzb(dp))) {
            return null;
        }
        DT dtZza = zzgVar.zza(dp);
        for (int i = 0; i < zzhVarZzb.zzc(dtZza); i++) {
            String strZzf = zzhVarZzb.zzf(dtZza, i);
            if (zzgVar.zzc(dp, i)) {
                double dZzd = zzhVarZzb.zzd(dtZza, i);
                if (dZzd == 1.0d) {
                    dZza = zzgVar.zzb(dp, i);
                } else if (dZzd == 2.0d) {
                    dZza = zzgVar.zza(dp, i);
                } else {
                    continue;
                }
                zzm zzmVarZzk = zzk.zzs().zzk(strZzf);
                if (zzmVarZzk != null && !zzmVarZzk.zza(dZza)) {
                    return "Field out of range";
                }
                zzm zzmVarZza = zzk.zzs().zza(zzhVarZzb.zzd(dtZza), strZzf);
                if (zzmVarZza != null) {
                    long jZza = zzgVar.zza(dp, TimeUnit.NANOSECONDS);
                    if (jZza == 0) {
                        if (dZza == 0.0d) {
                            return null;
                        }
                        return "DataPoint out of range";
                    }
                    if (!zzmVarZza.zza(dZza / jZza)) {
                        return "DataPoint out of range";
                    }
                } else {
                    continue;
                }
            } else if (!zzhVarZzb.zze(dtZza, i) && !zzk.zzep.contains(strZzf)) {
                return String.valueOf(strZzf).concat(" not set");
            }
        }
        return null;
    }
}