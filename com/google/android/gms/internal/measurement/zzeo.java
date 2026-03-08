package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzeo implements zziq {
    private final zzel zza;

    public static zzeo zza(zzel zzelVar) {
        return zzelVar.zza != null ? zzelVar.zza : new zzeo(zzelVar);
    }

    private zzeo(zzel zzelVar) {
        this.zza = (zzel) zzfh.zza(zzelVar, "output");
        this.zza.zza = this;
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final int zza() {
        return zzfe.zzf.zzj;
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, int i2) throws IOException {
        this.zza.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(int i, long j) throws IOException {
        this.zza.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, float f2) throws IOException {
        this.zza.zza(i, f2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, double d2) throws IOException {
        this.zza.zza(i, d2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzc(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzc(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzd(int i, long j) throws IOException {
        this.zza.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzd(int i, int i2) throws IOException {
        this.zza.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, boolean z) throws IOException {
        this.zza.zza(i, z);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, String str) throws IOException {
        this.zza.zza(i, str);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, zzdw zzdwVar) throws IOException {
        this.zza.zza(i, zzdwVar);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zze(int i, int i2) throws IOException {
        this.zza.zzc(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzf(int i, int i2) throws IOException {
        this.zza.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zze(int i, long j) throws IOException {
        this.zza.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, Object obj, zzhf zzhfVar) throws IOException {
        this.zza.zza(i, (zzgm) obj, zzhfVar);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(int i, Object obj, zzhf zzhfVar) throws IOException {
        zzel zzelVar = this.zza;
        zzelVar.zza(i, 3);
        zzhfVar.zza((zzgm) obj, zzelVar.zza);
        zzelVar.zza(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i) throws IOException {
        this.zza.zza(i, 3);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(int i) throws IOException {
        this.zza.zza(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzdw) {
            this.zza.zzb(i, (zzdw) obj);
        } else {
            this.zza.zza(i, (zzgm) obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzf = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzf += zzel.zzf(list.get(i3).intValue());
            }
            this.zza.zzb(iZzf);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzi = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzi += zzel.zzi(list.get(i3).intValue());
            }
            this.zza.zzb(iZzi);
            while (i2 < list.size()) {
                this.zza.zzd(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzd = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzd += zzel.zzd(list.get(i3).longValue());
            }
            this.zza.zzb(iZzd);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZze = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZze += zzel.zze(list.get(i3).longValue());
            }
            this.zza.zzb(iZze);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzg = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzg += zzel.zzg(list.get(i3).longValue());
            }
            this.zza.zzb(iZzg);
            while (i2 < list.size()) {
                this.zza.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzb = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzb += zzel.zzb(list.get(i3).floatValue());
            }
            this.zza.zzb(iZzb);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzb = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzb += zzel.zzb(list.get(i3).doubleValue());
            }
            this.zza.zzb(iZzb);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzk = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzk += zzel.zzk(list.get(i3).intValue());
            }
            this.zza.zzb(iZzk);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzb = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzb += zzel.zzb(list.get(i3).booleanValue());
            }
            this.zza.zzb(iZzb);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzfx) {
            zzfx zzfxVar = (zzfx) list;
            while (i2 < list.size()) {
                Object objZzb = zzfxVar.zzb(i2);
                if (objZzb instanceof String) {
                    this.zza.zza(i, (String) objZzb);
                } else {
                    this.zza.zza(i, (zzdw) objZzb);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(int i, List<zzdw> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzg = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzg += zzel.zzg(list.get(i3).intValue());
            }
            this.zza.zzb(iZzg);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzc(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzj = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzj += zzel.zzj(list.get(i3).intValue());
            }
            this.zza.zzb(iZzj);
            while (i2 < list.size()) {
                this.zza.zzd(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzh = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzh += zzel.zzh(list.get(i3).longValue());
            }
            this.zza.zzb(iZzh);
            while (i2 < list.size()) {
                this.zza.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzh = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzh += zzel.zzh(list.get(i3).intValue());
            }
            this.zza.zzb(iZzh);
            while (i2 < list.size()) {
                this.zza.zzc(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zza.zza(i, 2);
            int iZzf = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzf += zzel.zzf(list.get(i3).longValue());
            }
            this.zza.zzb(iZzf);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zza(int i, List<?> list, zzhf zzhfVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzhfVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final void zzb(int i, List<?> list, zzhf zzhfVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzhfVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zziq
    public final <K, V> void zza(int i, zzgh<K, V> zzghVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zza.zza(i, 2);
            this.zza.zzb(zzge.zza(zzghVar, entry.getKey(), entry.getValue()));
            zzge.zza(this.zza, zzghVar, entry.getKey(), entry.getValue());
        }
    }
}