package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzfy extends zzfw {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzfy() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzfw
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.measurement.zzfw
    final void zzb(Object obj, long j) {
        Object objUnmodifiableList;
        List list = (List) zzid.zzf(obj, j);
        if (list instanceof zzfx) {
            objUnmodifiableList = ((zzfx) list).zze();
        } else {
            if (zza.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof zzgy) && (list instanceof zzfn)) {
                zzfn zzfnVar = (zzfn) list;
                if (zzfnVar.zza()) {
                    zzfnVar.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        zzid.zza(obj, j, objUnmodifiableList);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        Object obj2;
        List<L> arrayList;
        List<L> listZzc = zzc(obj, j);
        if (listZzc.isEmpty()) {
            if (listZzc instanceof zzfx) {
                arrayList = new zzfu(i);
            } else if ((listZzc instanceof zzgy) && (listZzc instanceof zzfn)) {
                arrayList = ((zzfn) listZzc).zza(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzid.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(listZzc.getClass())) {
            ArrayList arrayList2 = new ArrayList(listZzc.size() + i);
            arrayList2.addAll(listZzc);
            zzid.zza(obj, j, arrayList2);
            obj2 = arrayList2;
        } else if (listZzc instanceof zzhy) {
            zzfu zzfuVar = new zzfu(listZzc.size() + i);
            zzfuVar.addAll((zzhy) listZzc);
            zzid.zza(obj, j, zzfuVar);
            obj2 = zzfuVar;
        } else {
            if (!(listZzc instanceof zzgy) || !(listZzc instanceof zzfn)) {
                return listZzc;
            }
            zzfn zzfnVar = (zzfn) listZzc;
            if (zzfnVar.zza()) {
                return listZzc;
            }
            zzfn zzfnVarZza = zzfnVar.zza(listZzc.size() + i);
            zzid.zza(obj, j, zzfnVarZza);
            return zzfnVarZza;
        }
        return (List<L>) obj2;
    }

    @Override // com.google.android.gms.internal.measurement.zzfw
    final <E> void zza(Object obj, Object obj2, long j) {
        List listZzc = zzc(obj2, j);
        List listZza = zza(obj, j, listZzc.size());
        int size = listZza.size();
        int size2 = listZzc.size();
        if (size > 0 && size2 > 0) {
            listZza.addAll(listZzc);
        }
        if (size > 0) {
            listZzc = listZza;
        }
        zzid.zza(obj, j, listZzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzid.zzf(obj, j);
    }
}