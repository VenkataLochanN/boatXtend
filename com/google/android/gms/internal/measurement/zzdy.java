package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzdy implements Comparator<zzdw> {
    zzdy() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzdw zzdwVar, zzdw zzdwVar2) {
        zzdw zzdwVar3 = zzdwVar;
        zzdw zzdwVar4 = zzdwVar2;
        zzeb zzebVar = (zzeb) zzdwVar3.iterator();
        zzeb zzebVar2 = (zzeb) zzdwVar4.iterator();
        while (zzebVar.hasNext() && zzebVar2.hasNext()) {
            int iCompare = Integer.compare(zzdw.zzb(zzebVar.zza()), zzdw.zzb(zzebVar2.zza()));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(zzdwVar3.zza(), zzdwVar4.zza());
    }
}