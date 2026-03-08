package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzn extends zzkk {
    private String zzb;
    private Set<Integer> zzc;
    private Map<Integer, zzp> zzd;
    private Long zze;
    private Long zzf;

    zzn(zzkj zzkjVar) {
        super(zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkk
    protected final boolean zze() {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:238:0x0704, code lost:
    
        r8 = zzr().zzi();
        r11 = com.google.android.gms.measurement.internal.zzfb.zza(r52.zzb);
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x0716, code lost:
    
        if (r9.zza() == false) goto L241;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0718, code lost:
    
        r9 = java.lang.Integer.valueOf(r9.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x0721, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0722, code lost:
    
        r8.zza("Invalid property filter ID. appId, id", r11, java.lang.String.valueOf(r9));
        r11 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x032c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.List<com.google.android.gms.internal.measurement.zzbs.zza> zza(java.lang.String r53, java.util.List<com.google.android.gms.internal.measurement.zzbs.zzc> r54, java.util.List<com.google.android.gms.internal.measurement.zzbs.zzk> r55, java.lang.Long r56, java.lang.Long r57) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2054
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzn.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    private final zzp zza(int i) {
        if (this.zzd.containsKey(Integer.valueOf(i))) {
            return this.zzd.get(Integer.valueOf(i));
        }
        zzp zzpVar = new zzp(this, this.zzb, null);
        this.zzd.put(Integer.valueOf(i), zzpVar);
        return zzpVar;
    }

    private final boolean zza(int i, int i2) {
        if (this.zzd.get(Integer.valueOf(i)) == null) {
            return false;
        }
        return this.zzd.get(Integer.valueOf(i)).zzd.get(i2);
    }
}