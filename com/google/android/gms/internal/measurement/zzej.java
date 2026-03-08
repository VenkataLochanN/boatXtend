package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzej implements zzhc {
    private final zzei zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    public static zzej zza(zzei zzeiVar) {
        return zzeiVar.zzc != null ? zzeiVar.zzc : new zzej(zzeiVar);
    }

    private zzej(zzei zzeiVar) {
        this.zza = (zzei) zzfh.zza(zzeiVar, "input");
        this.zza.zzc = this;
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zza() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zza();
        }
        int i2 = this.zzb;
        if (i2 == 0 || i2 == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final boolean zzc() throws IOException {
        int i;
        if (this.zza.zzt() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzb(i);
    }

    private final void zza(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzfm.zzf();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final double zzd() throws IOException {
        zza(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final float zze() throws IOException {
        zza(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final long zzf() throws IOException {
        zza(0);
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final long zzg() throws IOException {
        zza(0);
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zzh() throws IOException {
        zza(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final long zzi() throws IOException {
        zza(1);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zzj() throws IOException {
        zza(5);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final boolean zzk() throws IOException {
        zza(0);
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final String zzl() throws IOException {
        zza(2);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final String zzm() throws IOException {
        zza(2);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final <T> T zza(zzhf<T> zzhfVar, zzer zzerVar) throws IOException {
        zza(2);
        return (T) zzc(zzhfVar, zzerVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final <T> T zzb(zzhf<T> zzhfVar, zzer zzerVar) throws IOException {
        zza(3);
        return (T) zzd(zzhfVar, zzerVar);
    }

    private final <T> T zzc(zzhf<T> zzhfVar, zzer zzerVar) throws IOException {
        int iZzm = this.zza.zzm();
        if (this.zza.zza >= this.zza.zzb) {
            throw new zzfm("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iZzc = this.zza.zzc(iZzm);
        T tZza = zzhfVar.zza();
        this.zza.zza++;
        zzhfVar.zza(tZza, this, zzerVar);
        zzhfVar.zzc(tZza);
        this.zza.zza(0);
        zzei zzeiVar = this.zza;
        zzeiVar.zza--;
        this.zza.zzd(iZzc);
        return tZza;
    }

    private final <T> T zzd(zzhf<T> zzhfVar, zzer zzerVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T tZza = zzhfVar.zza();
            zzhfVar.zza(tZza, this, zzerVar);
            zzhfVar.zzc(tZza);
            if (this.zzb == this.zzc) {
                return tZza;
            }
            throw zzfm.zzg();
        } finally {
            this.zzc = i;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final zzdw zzn() throws IOException {
        zza(2);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zzo() throws IOException {
        zza(0);
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zzp() throws IOException {
        zza(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zzq() throws IOException {
        zza(5);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final long zzr() throws IOException {
        zza(1);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final int zzs() throws IOException {
        zza(0);
        return this.zza.zzq();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final long zzt() throws IOException {
        zza(0);
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zza(List<Double> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzeq) {
            zzeq zzeqVar = (zzeq) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzeqVar.zza(this.zza.zzb());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzeqVar.zza(this.zza.zzb());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                list.add(Double.valueOf(this.zza.zzb()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Double.valueOf(this.zza.zzb()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzb(List<Float> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzfa) {
            zzfa zzfaVar = (zzfa) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzfaVar.zza(this.zza.zzc());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i == 5) {
                do {
                    zzfaVar.zza(this.zza.zzc());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Float.valueOf(this.zza.zzc()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Float.valueOf(this.zza.zzc()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzc(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzgaVar.zza(this.zza.zzd());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzgaVar.zza(this.zza.zzd());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzd()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zzd()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzd(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzgaVar.zza(this.zza.zze());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzgaVar.zza(this.zza.zze());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zze()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zze()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zze(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzffVar.zzd(this.zza.zzf());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzffVar.zzd(this.zza.zzf());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzf(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzgaVar.zza(this.zza.zzg());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzgaVar.zza(this.zza.zzg());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzg()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Long.valueOf(this.zza.zzg()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzg(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzffVar.zzd(this.zza.zzh());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i == 5) {
                do {
                    zzffVar.zzd(this.zza.zzh());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzh(List<Boolean> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzdu) {
            zzdu zzduVar = (zzdu) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzduVar.zza(this.zza.zzi());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzduVar.zza(this.zza.zzi());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zza.zzi()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Boolean.valueOf(this.zza.zzi()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzi(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzj(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int iZza;
        int iZza2;
        if ((this.zzb & 7) != 2) {
            throw zzfm.zzf();
        }
        if ((list instanceof zzfx) && !z) {
            zzfx zzfxVar = (zzfx) list;
            do {
                zzfxVar.zza(zzn());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza2 = this.zza.zza();
                }
            } while (iZza2 == this.zzb);
            this.zzd = iZza2;
            return;
        }
        do {
            list.add(z ? zzm() : zzl());
            if (this.zza.zzt()) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == this.zzb);
        this.zzd = iZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzhc
    public final <T> void zza(List<T> list, zzhf<T> zzhfVar, zzer zzerVar) throws IOException {
        int iZza;
        int i = this.zzb;
        if ((i & 7) != 2) {
            throw zzfm.zzf();
        }
        do {
            list.add(zzc(zzhfVar, zzerVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == i);
        this.zzd = iZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzhc
    public final <T> void zzb(List<T> list, zzhf<T> zzhfVar, zzer zzerVar) throws IOException {
        int iZza;
        int i = this.zzb;
        if ((i & 7) != 3) {
            throw zzfm.zzf();
        }
        do {
            list.add(zzd(zzhfVar, zzerVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == i);
        this.zzd = iZza;
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzk(List<zzdw> list) throws IOException {
        int iZza;
        if ((this.zzb & 7) != 2) {
            throw zzfm.zzf();
        }
        do {
            list.add(zzn());
            if (this.zza.zzt()) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == this.zzb);
        this.zzd = iZza;
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzl(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzffVar.zzd(this.zza.zzm());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzffVar.zzd(this.zza.zzm());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzm()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzm()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzm(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzffVar.zzd(this.zza.zzn());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzffVar.zzd(this.zza.zzn());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzn(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzffVar.zzd(this.zza.zzo());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i == 5) {
                do {
                    zzffVar.zzd(this.zza.zzo());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Integer.valueOf(this.zza.zzo()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zza.zzo()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzo(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzgaVar.zza(this.zza.zzp());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzgaVar.zza(this.zza.zzp());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzp()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Long.valueOf(this.zza.zzp()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzp(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzffVar.zzd(this.zza.zzq());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzffVar.zzd(this.zza.zzq());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzq()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzq()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzq(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzga) {
            zzga zzgaVar = (zzga) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzgaVar.zza(this.zza.zzr());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzgaVar.zza(this.zza.zzr());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzfm.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzr()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zzr()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzfm.zzf();
    }

    private static void zzb(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzfm.zzg();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
    
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0063, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzhc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.measurement.zzgh<K, V> r9, com.google.android.gms.internal.measurement.zzer r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zza(r0)
            com.google.android.gms.internal.measurement.zzei r1 = r7.zza
            int r1 = r1.zzm()
            com.google.android.gms.internal.measurement.zzei r2 = r7.zza
            int r1 = r2.zzc(r1)
            K r2 = r9.zzb
            V r3 = r9.zzd
        L14:
            int r4 = r7.zza()     // Catch: java.lang.Throwable -> L64
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L5b
            com.google.android.gms.internal.measurement.zzei r5 = r7.zza     // Catch: java.lang.Throwable -> L64
            boolean r5 = r5.zzt()     // Catch: java.lang.Throwable -> L64
            if (r5 != 0) goto L5b
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L46
            if (r4 == r0) goto L39
            boolean r4 = r7.zzc()     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            if (r4 == 0) goto L33
            goto L14
        L33:
            com.google.android.gms.internal.measurement.zzfm r4 = new com.google.android.gms.internal.measurement.zzfm     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            r4.<init>(r6)     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            throw r4     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
        L39:
            com.google.android.gms.internal.measurement.zzik r4 = r9.zzc     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            V r5 = r9.zzd     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            java.lang.Class r5 = r5.getClass()     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            java.lang.Object r3 = r7.zza(r4, r5, r10)     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            goto L14
        L46:
            com.google.android.gms.internal.measurement.zzik r4 = r9.zza     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            r5 = 0
            java.lang.Object r2 = r7.zza(r4, r5, r5)     // Catch: com.google.android.gms.internal.measurement.zzfp -> L4e java.lang.Throwable -> L64
            goto L14
        L4e:
            boolean r4 = r7.zzc()     // Catch: java.lang.Throwable -> L64
            if (r4 == 0) goto L55
            goto L14
        L55:
            com.google.android.gms.internal.measurement.zzfm r8 = new com.google.android.gms.internal.measurement.zzfm     // Catch: java.lang.Throwable -> L64
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L64
            throw r8     // Catch: java.lang.Throwable -> L64
        L5b:
            r8.put(r2, r3)     // Catch: java.lang.Throwable -> L64
            com.google.android.gms.internal.measurement.zzei r8 = r7.zza
            r8.zzd(r1)
            return
        L64:
            r8 = move-exception
            com.google.android.gms.internal.measurement.zzei r9 = r7.zza
            r9.zzd(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzej.zza(java.util.Map, com.google.android.gms.internal.measurement.zzgh, com.google.android.gms.internal.measurement.zzer):void");
    }

    private final Object zza(zzik zzikVar, Class<?> cls, zzer zzerVar) throws IOException {
        switch (zzem.zza[zzikVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzk());
            case 2:
                return zzn();
            case 3:
                return Double.valueOf(zzd());
            case 4:
                return Integer.valueOf(zzp());
            case 5:
                return Integer.valueOf(zzj());
            case 6:
                return Long.valueOf(zzi());
            case 7:
                return Float.valueOf(zze());
            case 8:
                return Integer.valueOf(zzh());
            case 9:
                return Long.valueOf(zzg());
            case 10:
                zza(2);
                return zzc(zzhb.zza().zza((Class) cls), zzerVar);
            case 11:
                return Integer.valueOf(zzq());
            case 12:
                return Long.valueOf(zzr());
            case 13:
                return Integer.valueOf(zzs());
            case 14:
                return Long.valueOf(zzt());
            case 15:
                return zzm();
            case 16:
                return Integer.valueOf(zzo());
            case 17:
                return Long.valueOf(zzf());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzc(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzfm.zzg();
        }
    }

    private final void zzd(int i) throws IOException {
        if (this.zza.zzu() != i) {
            throw zzfm.zza();
        }
    }
}