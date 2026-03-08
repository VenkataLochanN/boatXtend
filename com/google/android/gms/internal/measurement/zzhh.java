package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzhh {
    private static final Class<?> zza = zzd();
    private static final zzhx<?, ?> zzb = zza(false);
    private static final zzhx<?, ?> zzc = zza(true);
    private static final zzhx<?, ?> zzd = new zzhz();

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzfe.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzg(i, list, z);
    }

    public static void zzb(int i, List<Float> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzf(i, list, z);
    }

    public static void zzc(int i, List<Long> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzn(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zze(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzl(i, list, z);
    }

    public static void zzh(int i, List<Integer> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zza(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzj(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zziq zziqVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzi(i, list, z);
    }

    public static void zza(int i, List<String> list, zziq zziqVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zza(i, list);
    }

    public static void zzb(int i, List<zzdw> list, zziq zziqVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzb(i, list);
    }

    public static void zza(int i, List<?> list, zziq zziqVar, zzhf zzhfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zza(i, list, zzhfVar);
    }

    public static void zzb(int i, List<?> list, zziq zziqVar, zzhf zzhfVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zziqVar.zzb(i, list, zzhfVar);
    }

    static int zza(List<Long> list) {
        int iZzd;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            iZzd = 0;
            while (i < size) {
                iZzd += zzel.zzd(zzgaVar.zzb(i));
                i++;
            }
        } else {
            iZzd = 0;
            while (i < size) {
                iZzd += zzel.zzd(list.get(i).longValue());
                i++;
            }
        }
        return iZzd;
    }

    static int zza(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzel.zze(i));
    }

    static int zzb(List<Long> list) {
        int iZze;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            iZze = 0;
            while (i < size) {
                iZze += zzel.zze(zzgaVar.zzb(i));
                i++;
            }
        } else {
            iZze = 0;
            while (i < size) {
                iZze += zzel.zze(list.get(i).longValue());
                i++;
            }
        }
        return iZze;
    }

    static int zzb(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzel.zze(i));
    }

    static int zzc(List<Long> list) {
        int iZzf;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            iZzf = 0;
            while (i < size) {
                iZzf += zzel.zzf(zzgaVar.zzb(i));
                i++;
            }
        } else {
            iZzf = 0;
            while (i < size) {
                iZzf += zzel.zzf(list.get(i).longValue());
                i++;
            }
        }
        return iZzf;
    }

    static int zzc(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzel.zze(i));
    }

    static int zzd(List<Integer> list) {
        int iZzk;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            iZzk = 0;
            while (i < size) {
                iZzk += zzel.zzk(zzffVar.zzc(i));
                i++;
            }
        } else {
            iZzk = 0;
            while (i < size) {
                iZzk += zzel.zzk(list.get(i).intValue());
                i++;
            }
        }
        return iZzk;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzel.zze(i));
    }

    static int zze(List<Integer> list) {
        int iZzf;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            iZzf = 0;
            while (i < size) {
                iZzf += zzel.zzf(zzffVar.zzc(i));
                i++;
            }
        } else {
            iZzf = 0;
            while (i < size) {
                iZzf += zzel.zzf(list.get(i).intValue());
                i++;
            }
        }
        return iZzf;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzel.zze(i));
    }

    static int zzf(List<Integer> list) {
        int iZzg;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            iZzg = 0;
            while (i < size) {
                iZzg += zzel.zzg(zzffVar.zzc(i));
                i++;
            }
        } else {
            iZzg = 0;
            while (i < size) {
                iZzg += zzel.zzg(list.get(i).intValue());
                i++;
            }
        }
        return iZzg;
    }

    static int zzf(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzel.zze(i));
    }

    static int zzg(List<Integer> list) {
        int iZzh;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            iZzh = 0;
            while (i < size) {
                iZzh += zzel.zzh(zzffVar.zzc(i));
                i++;
            }
        } else {
            iZzh = 0;
            while (i < size) {
                iZzh += zzel.zzh(list.get(i).intValue());
                i++;
            }
        }
        return iZzh;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzel.zze(i));
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzel.zzi(i, 0);
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzi(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzel.zzg(i, 0L);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzj(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzel.zzb(i, true);
    }

    static int zza(int i, List<?> list) {
        int iZzb;
        int iZzb2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZze = zzel.zze(i) * size;
        if (list instanceof zzfx) {
            zzfx zzfxVar = (zzfx) list;
            while (i2 < size) {
                Object objZzb = zzfxVar.zzb(i2);
                if (objZzb instanceof zzdw) {
                    iZzb2 = zzel.zzb((zzdw) objZzb);
                } else {
                    iZzb2 = zzel.zzb((String) objZzb);
                }
                iZze += iZzb2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzdw) {
                    iZzb = zzel.zzb((zzdw) obj);
                } else {
                    iZzb = zzel.zzb((String) obj);
                }
                iZze += iZzb;
                i2++;
            }
        }
        return iZze;
    }

    static int zza(int i, Object obj, zzhf zzhfVar) {
        if (obj instanceof zzfv) {
            return zzel.zza(i, (zzfv) obj);
        }
        return zzel.zzb(i, (zzgm) obj, zzhfVar);
    }

    static int zza(int i, List<?> list, zzhf zzhfVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZze = zzel.zze(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzfv) {
                iZza = zzel.zza((zzfv) obj);
            } else {
                iZza = zzel.zza((zzgm) obj, zzhfVar);
            }
            iZze += iZza;
        }
        return iZze;
    }

    static int zzb(int i, List<zzdw> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZze = size * zzel.zze(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZze += zzel.zzb(list.get(i2));
        }
        return iZze;
    }

    static int zzb(int i, List<zzgm> list, zzhf zzhfVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzc = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzc += zzel.zzc(i, list.get(i2), zzhfVar);
        }
        return iZzc;
    }

    public static zzhx<?, ?> zza() {
        return zzb;
    }

    public static zzhx<?, ?> zzb() {
        return zzc;
    }

    public static zzhx<?, ?> zzc() {
        return zzd;
    }

    private static zzhx<?, ?> zza(boolean z) {
        try {
            Class<?> clsZze = zze();
            if (clsZze == null) {
                return null;
            }
            return (zzhx) clsZze.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzgj zzgjVar, T t, T t2, long j) {
        zzid.zza(t, j, zzgjVar.zza(zzid.zzf(t, j), zzid.zzf(t2, j)));
    }

    static <T, FT extends zzew<FT>> void zza(zzet<FT> zzetVar, T t, T t2) {
        zzeu<T> zzeuVarZza = zzetVar.zza(t2);
        if (zzeuVarZza.zza.isEmpty()) {
            return;
        }
        zzetVar.zzb(t).zza((zzeu) zzeuVarZza);
    }

    static <T, UT, UB> void zza(zzhx<UT, UB> zzhxVar, T t, T t2) {
        zzhxVar.zza(t, zzhxVar.zzc(zzhxVar.zzb(t), zzhxVar.zzb(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzfi zzfiVar, UB ub, zzhx<UT, UB> zzhxVar) {
        UB ub2;
        int iIntValue;
        if (zzfiVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue2 = list.get(i3).intValue();
                if (zzfiVar.zza(iIntValue2)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue2));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, iIntValue2, ub2, zzhxVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            loop1: while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    iIntValue = it.next().intValue();
                    if (!zzfiVar.zza(iIntValue)) {
                        break;
                    }
                }
                ub = (UB) zza(i, iIntValue, ub2, zzhxVar);
                it.remove();
            }
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzhx<UT, UB> zzhxVar) {
        if (ub == null) {
            ub = zzhxVar.zza();
        }
        zzhxVar.zza(ub, i, i2);
        return ub;
    }
}