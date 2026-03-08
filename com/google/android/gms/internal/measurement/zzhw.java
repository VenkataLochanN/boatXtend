package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzhw {
    private static final zzhw zza = new zzhw(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public static zzhw zza() {
        return zza;
    }

    static zzhw zzb() {
        return new zzhw();
    }

    static zzhw zza(zzhw zzhwVar, zzhw zzhwVar2) {
        int i = zzhwVar.zzb + zzhwVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzhwVar.zzc, i);
        System.arraycopy(zzhwVar2.zzc, 0, iArrCopyOf, zzhwVar.zzb, zzhwVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzhwVar.zzd, i);
        System.arraycopy(zzhwVar2.zzd, 0, objArrCopyOf, zzhwVar.zzb, zzhwVar2.zzb);
        return new zzhw(i, iArrCopyOf, objArrCopyOf, true);
    }

    private zzhw() {
        this(0, new int[8], new Object[8], true);
    }

    private zzhw(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public final void zzc() {
        this.zzf = false;
    }

    final void zza(zziq zziqVar) throws IOException {
        if (zziqVar.zza() == zzfe.zzf.zzk) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zziqVar.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zziqVar.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    public final void zzb(zziq zziqVar) throws IOException {
        if (this.zzb == 0) {
            return;
        }
        if (zziqVar.zza() == zzfe.zzf.zzj) {
            for (int i = 0; i < this.zzb; i++) {
                zza(this.zzc[i], this.zzd[i], zziqVar);
            }
            return;
        }
        for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
            zza(this.zzc[i2], this.zzd[i2], zziqVar);
        }
    }

    private static void zza(int i, Object obj, zziq zziqVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zziqVar.zza(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zziqVar.zzd(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zziqVar.zza(i2, (zzdw) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zziqVar.zzd(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzfm.zzf());
        }
        if (zziqVar.zza() == zzfe.zzf.zzj) {
            zziqVar.zza(i2);
            ((zzhw) obj).zzb(zziqVar);
            zziqVar.zzb(i2);
        } else {
            zziqVar.zzb(i2);
            ((zzhw) obj).zzb(zziqVar);
            zziqVar.zza(i2);
        }
    }

    public final int zzd() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzd = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            iZzd += zzel.zzd(this.zzc[i2] >>> 3, (zzdw) this.zzd[i2]);
        }
        this.zze = iZzd;
        return iZzd;
    }

    public final int zze() {
        int iZze;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                iZze = zzel.zze(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 1) {
                iZze = zzel.zzg(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 2) {
                iZze = zzel.zzc(i5, (zzdw) this.zzd[i3]);
            } else if (i6 == 3) {
                iZze = (zzel.zze(i5) << 1) + ((zzhw) this.zzd[i3]).zze();
            } else if (i6 == 5) {
                iZze = zzel.zzi(i5, ((Integer) this.zzd[i3]).intValue());
            } else {
                throw new IllegalStateException(zzfm.zzf());
            }
            i2 += iZze;
        }
        this.zze = i2;
        return i2;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzhw)) {
            return false;
        }
        zzhw zzhwVar = (zzhw) obj;
        int i = this.zzb;
        if (i == zzhwVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzhwVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                }
                if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                }
                i2++;
            }
            if (z) {
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzhwVar.zzd;
                int i3 = this.zzb;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    }
                    if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    }
                    i4++;
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzgr.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zza(int i, Object obj) {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.zzb;
        if (i2 == this.zzc.length) {
            int i3 = this.zzb + (i2 < 4 ? 8 : i2 >> 1);
            this.zzc = Arrays.copyOf(this.zzc, i3);
            this.zzd = Arrays.copyOf(this.zzd, i3);
        }
        int[] iArr = this.zzc;
        int i4 = this.zzb;
        iArr[i4] = i;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }
}