package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzhd implements zzgk {
    private final zzgm zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzhd(zzgm zzgmVar, String str, Object[] objArr) {
        this.zza = zzgmVar;
        this.zzb = str;
        this.zzc = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.zzd = cCharAt;
            return;
        }
        int i = cCharAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 < 55296) {
                this.zzd = i | (cCharAt2 << i2);
                return;
            } else {
                i |= (cCharAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    final String zzd() {
        return this.zzb;
    }

    final Object[] zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzgk
    public final zzgm zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzgk
    public final int zza() {
        return (this.zzd & 1) == 1 ? zzfe.zzf.zzh : zzfe.zzf.zzi;
    }

    @Override // com.google.android.gms.internal.measurement.zzgk
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }
}