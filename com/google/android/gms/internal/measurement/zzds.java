package com.google.android.gms.internal.measurement;

import java.io.IOException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzds {
    static int zza(byte[] bArr, int i, zzdr zzdrVar) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            return zza(b2, bArr, i2, zzdrVar);
        }
        zzdrVar.zza = b2;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzdr zzdrVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            zzdrVar.zza = i3 | (b2 << 7);
            return i4;
        }
        int i5 = i3 | ((b2 & ByteCompanionObject.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            zzdrVar.zza = i5 | (b3 << 14);
            return i6;
        }
        int i7 = i5 | ((b3 & ByteCompanionObject.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            zzdrVar.zza = i7 | (b4 << 21);
            return i8;
        }
        int i9 = i7 | ((b4 & ByteCompanionObject.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzdrVar.zza = i9 | (b5 << 28);
            return i10;
        }
        int i11 = i9 | ((b5 & ByteCompanionObject.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzdrVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzdr zzdrVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzdrVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | (((long) (b2 & ByteCompanionObject.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            byte b3 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b3 & ByteCompanionObject.MAX_VALUE)) << i4;
            b2 = b3;
            i3 = i5;
        }
        zzdrVar.zzb = j2;
        return i3;
    }

    static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    static long zzb(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzdr zzdrVar) throws zzfm {
        int iZza = zza(bArr, i, zzdrVar);
        int i2 = zzdrVar.zza;
        if (i2 < 0) {
            throw zzfm.zzb();
        }
        if (i2 == 0) {
            zzdrVar.zzc = "";
            return iZza;
        }
        zzdrVar.zzc = new String(bArr, iZza, i2, zzfh.zza);
        return iZza + i2;
    }

    static int zzd(byte[] bArr, int i, zzdr zzdrVar) throws zzfm {
        int iZza = zza(bArr, i, zzdrVar);
        int i2 = zzdrVar.zza;
        if (i2 < 0) {
            throw zzfm.zzb();
        }
        if (i2 == 0) {
            zzdrVar.zzc = "";
            return iZza;
        }
        zzdrVar.zzc = zzif.zzb(bArr, iZza, i2);
        return iZza + i2;
    }

    static int zze(byte[] bArr, int i, zzdr zzdrVar) throws zzfm {
        int iZza = zza(bArr, i, zzdrVar);
        int i2 = zzdrVar.zza;
        if (i2 < 0) {
            throw zzfm.zzb();
        }
        if (i2 > bArr.length - iZza) {
            throw zzfm.zza();
        }
        if (i2 == 0) {
            zzdrVar.zzc = zzdw.zza;
            return iZza;
        }
        zzdrVar.zzc = zzdw.zza(bArr, iZza, i2);
        return iZza + i2;
    }

    static int zza(zzhf zzhfVar, byte[] bArr, int i, int i2, zzdr zzdrVar) throws IOException {
        int iZza = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZza = zza(i3, bArr, iZza, zzdrVar);
            i3 = zzdrVar.zza;
        }
        int i4 = iZza;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzfm.zza();
        }
        Object objZza = zzhfVar.zza();
        int i5 = i3 + i4;
        zzhfVar.zza(objZza, bArr, i4, i5, zzdrVar);
        zzhfVar.zzc(objZza);
        zzdrVar.zzc = objZza;
        return i5;
    }

    static int zza(zzhf zzhfVar, byte[] bArr, int i, int i2, int i3, zzdr zzdrVar) throws IOException {
        zzgq zzgqVar = (zzgq) zzhfVar;
        Object objZza = zzgqVar.zza();
        int iZza = zzgqVar.zza(objZza, bArr, i, i2, i3, zzdrVar);
        zzgqVar.zzc(objZza);
        zzdrVar.zzc = objZza;
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzfn<?> zzfnVar, zzdr zzdrVar) {
        zzff zzffVar = (zzff) zzfnVar;
        int iZza = zza(bArr, i2, zzdrVar);
        zzffVar.zzd(zzdrVar.zza);
        while (iZza < i3) {
            int iZza2 = zza(bArr, iZza, zzdrVar);
            if (i != zzdrVar.zza) {
                break;
            }
            iZza = zza(bArr, iZza2, zzdrVar);
            zzffVar.zzd(zzdrVar.zza);
        }
        return iZza;
    }

    static int zza(byte[] bArr, int i, zzfn<?> zzfnVar, zzdr zzdrVar) throws IOException {
        zzff zzffVar = (zzff) zzfnVar;
        int iZza = zza(bArr, i, zzdrVar);
        int i2 = zzdrVar.zza + iZza;
        while (iZza < i2) {
            iZza = zza(bArr, iZza, zzdrVar);
            zzffVar.zzd(zzdrVar.zza);
        }
        if (iZza == i2) {
            return iZza;
        }
        throw zzfm.zza();
    }

    static int zza(zzhf<?> zzhfVar, int i, byte[] bArr, int i2, int i3, zzfn<?> zzfnVar, zzdr zzdrVar) throws IOException {
        int iZza = zza(zzhfVar, bArr, i2, i3, zzdrVar);
        zzfnVar.add(zzdrVar.zzc);
        while (iZza < i3) {
            int iZza2 = zza(bArr, iZza, zzdrVar);
            if (i != zzdrVar.zza) {
                break;
            }
            iZza = zza(zzhfVar, bArr, iZza2, i3, zzdrVar);
            zzfnVar.add(zzdrVar.zzc);
        }
        return iZza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzhw zzhwVar, zzdr zzdrVar) throws zzfm {
        if ((i >>> 3) == 0) {
            throw zzfm.zzd();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzb = zzb(bArr, i2, zzdrVar);
            zzhwVar.zza(i, Long.valueOf(zzdrVar.zzb));
            return iZzb;
        }
        if (i4 == 1) {
            zzhwVar.zza(i, Long.valueOf(zzb(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZza = zza(bArr, i2, zzdrVar);
            int i5 = zzdrVar.zza;
            if (i5 < 0) {
                throw zzfm.zzb();
            }
            if (i5 > bArr.length - iZza) {
                throw zzfm.zza();
            }
            if (i5 == 0) {
                zzhwVar.zza(i, zzdw.zza);
            } else {
                zzhwVar.zza(i, zzdw.zza(bArr, iZza, i5));
            }
            return iZza + i5;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                zzhwVar.zza(i, Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            }
            throw zzfm.zzd();
        }
        zzhw zzhwVarZzb = zzhw.zzb();
        int i6 = (i & (-8)) | 4;
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iZza2 = zza(bArr, i2, zzdrVar);
            int i8 = zzdrVar.zza;
            i7 = i8;
            if (i8 == i6) {
                i2 = iZza2;
                break;
            }
            int iZza3 = zza(i7, bArr, iZza2, i3, zzhwVarZzb, zzdrVar);
            i7 = i8;
            i2 = iZza3;
        }
        if (i2 > i3 || i7 != i6) {
            throw zzfm.zzg();
        }
        zzhwVar.zza(i, zzhwVarZzb);
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzdr zzdrVar) throws zzfm {
        if ((i >>> 3) == 0) {
            throw zzfm.zzd();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return zzb(bArr, i2, zzdrVar);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return zza(bArr, i2, zzdrVar) + zzdrVar.zza;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw zzfm.zzd();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = zza(bArr, i2, zzdrVar);
            i6 = zzdrVar.zza;
            if (i6 == i5) {
                break;
            }
            i2 = zza(i6, bArr, i2, i3, zzdrVar);
        }
        if (i2 > i3 || i6 != i5) {
            throw zzfm.zzg();
        }
        return i2;
    }
}