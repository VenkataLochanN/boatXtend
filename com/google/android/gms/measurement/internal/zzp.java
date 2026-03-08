package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzms;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzp {
    private String zza;
    private boolean zzb;
    private zzbs.zzi zzc;
    private BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzn zzh;

    private zzp(zzn zznVar, String str) {
        this.zzh = zznVar;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new ArrayMap();
        this.zzg = new ArrayMap();
    }

    private zzp(zzn zznVar, String str, zzbs.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zznVar;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new ArrayMap();
        if (map2 != null) {
            for (Integer num : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(num));
                this.zzg.put(num, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zziVar;
    }

    final void zza(zzu zzuVar) {
        int iZza = zzuVar.zza();
        if (zzuVar.zzc != null) {
            this.zze.set(iZza, zzuVar.zzc.booleanValue());
        }
        if (zzuVar.zzd != null) {
            this.zzd.set(iZza, zzuVar.zzd.booleanValue());
        }
        if (zzuVar.zze != null) {
            Long l = this.zzf.get(Integer.valueOf(iZza));
            long jLongValue = zzuVar.zze.longValue() / 1000;
            if (l == null || jLongValue > l.longValue()) {
                this.zzf.put(Integer.valueOf(iZza), Long.valueOf(jLongValue));
            }
        }
        if (zzuVar.zzf != null) {
            List<Long> arrayList = this.zzg.get(Integer.valueOf(iZza));
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.zzg.put(Integer.valueOf(iZza), arrayList);
            }
            if (zzms.zzb() && this.zzh.zzt().zzd(this.zza, zzap.zzbr) && zzuVar.zzb()) {
                arrayList.clear();
            }
            if (com.google.android.gms.internal.measurement.zzkd.zzb() && this.zzh.zzt().zzd(this.zza, zzap.zzbv) && zzuVar.zzc()) {
                arrayList.clear();
            }
            if (com.google.android.gms.internal.measurement.zzkd.zzb() && this.zzh.zzt().zzd(this.zza, zzap.zzbv)) {
                long jLongValue2 = zzuVar.zzf.longValue() / 1000;
                if (arrayList.contains(Long.valueOf(jLongValue2))) {
                    return;
                }
                arrayList.add(Long.valueOf(jLongValue2));
                return;
            }
            arrayList.add(Long.valueOf(zzuVar.zzf.longValue() / 1000));
        }
    }

    final zzbs.zza zza(int i, List<Integer> list) {
        ArrayList arrayList;
        List listEmptyList;
        zzbs.zza.C0042zza c0042zzaZzh = zzbs.zza.zzh();
        c0042zzaZzh.zza(i);
        c0042zzaZzh.zza(this.zzb);
        zzbs.zzi zziVar = this.zzc;
        if (zziVar != null) {
            c0042zzaZzh.zza(zziVar);
        }
        zzbs.zzi.zza zzaVarZza = zzbs.zzi.zzi().zzb(zzkr.zza(this.zzd)).zza(zzkr.zza(this.zze));
        Map<Integer, Long> map = this.zzf;
        if (map == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(map.size());
            Iterator<Integer> it = this.zzf.keySet().iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                arrayList.add((zzbs.zzb) ((com.google.android.gms.internal.measurement.zzfe) zzbs.zzb.zze().zza(iIntValue).zza(this.zzf.get(Integer.valueOf(iIntValue)).longValue()).zzv()));
            }
        }
        zzaVarZza.zzc(arrayList);
        Map<Integer, List<Long>> map2 = this.zzg;
        if (map2 == null) {
            listEmptyList = Collections.emptyList();
        } else {
            ArrayList arrayList2 = new ArrayList(map2.size());
            for (Integer num : this.zzg.keySet()) {
                zzbs.zzj.zza zzaVarZza2 = zzbs.zzj.zze().zza(num.intValue());
                List<Long> list2 = this.zzg.get(num);
                if (list2 != null) {
                    Collections.sort(list2);
                    zzaVarZza2.zza(list2);
                }
                arrayList2.add((zzbs.zzj) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZza2.zzv()));
            }
            listEmptyList = arrayList2;
        }
        if ((!zzms.zzb() || !this.zzh.zzt().zzd(this.zza, zzap.zzbr)) && c0042zzaZzh.zza()) {
            List<zzbs.zzj> listZzg = c0042zzaZzh.zzb().zzg();
            if (!listZzg.isEmpty()) {
                ArrayList arrayList3 = new ArrayList(listEmptyList);
                ArrayMap arrayMap = new ArrayMap();
                for (zzbs.zzj zzjVar : listZzg) {
                    if (zzjVar.zza() && zzjVar.zzd() > 0) {
                        arrayMap.put(Integer.valueOf(zzjVar.zzb()), Long.valueOf(zzjVar.zza(zzjVar.zzd() - 1)));
                    }
                }
                for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                    zzbs.zzj zzjVar2 = (zzbs.zzj) arrayList3.get(i2);
                    Long l = (Long) arrayMap.remove(zzjVar2.zza() ? Integer.valueOf(zzjVar2.zzb()) : null);
                    if (l != null && (list == null || !list.contains(Integer.valueOf(zzjVar2.zzb())))) {
                        ArrayList arrayList4 = new ArrayList();
                        if (l.longValue() < zzjVar2.zza(0)) {
                            arrayList4.add(l);
                        }
                        arrayList4.addAll(zzjVar2.zzc());
                        arrayList3.set(i2, (zzbs.zzj) ((com.google.android.gms.internal.measurement.zzfe) zzjVar2.zzbl().zza().zza(arrayList4).zzv()));
                    }
                }
                for (Integer num2 : arrayMap.keySet()) {
                    arrayList3.add((zzbs.zzj) ((com.google.android.gms.internal.measurement.zzfe) zzbs.zzj.zze().zza(num2.intValue()).zza(((Long) arrayMap.get(num2)).longValue()).zzv()));
                }
                listEmptyList = arrayList3;
            }
        }
        zzaVarZza.zzd(listEmptyList);
        c0042zzaZzh.zza(zzaVarZza);
        return (zzbs.zza) ((com.google.android.gms.internal.measurement.zzfe) c0042zzaZzh.zzv());
    }

    /* synthetic */ zzp(zzn zznVar, String str, zzbs.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzq zzqVar) {
        this(zznVar, str, zziVar, bitSet, bitSet2, map, map2);
    }

    /* synthetic */ zzp(zzn zznVar, String str, zzq zzqVar) {
        this(zznVar, str);
    }
}