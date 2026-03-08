package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzel extends zzdt {
    private static final Logger zzb = Logger.getLogger(zzel.class.getName());
    private static final boolean zzc = zzid.zza();
    zzeo zza;

    public static zzel zza(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzb(double d2) {
        return 8;
    }

    public static int zzb(float f2) {
        return 4;
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zze(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static int zzg(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzg(long j) {
        return 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzi(int i) {
        return 4;
    }

    private static long zzi(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzj(int i) {
        return 4;
    }

    private static int zzm(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract int zza();

    public abstract void zza(byte b2) throws IOException;

    public abstract void zza(int i) throws IOException;

    public abstract void zza(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzdw zzdwVar) throws IOException;

    public abstract void zza(int i, zzgm zzgmVar) throws IOException;

    abstract void zza(int i, zzgm zzgmVar, zzhf zzhfVar) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(long j) throws IOException;

    public abstract void zza(zzdw zzdwVar) throws IOException;

    public abstract void zza(zzgm zzgmVar) throws IOException;

    public abstract void zza(String str) throws IOException;

    public abstract void zzb(int i) throws IOException;

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, zzdw zzdwVar) throws IOException;

    abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzc(long j) throws IOException;

    public abstract void zzd(int i) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        zzb(String str, Throwable th) {
            String strValueOf = String.valueOf(str);
            super(strValueOf.length() != 0 ? "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(strValueOf) : new String("CodedOutputStream was writing to a flat byte array and ran out of space.: "), th);
        }
    }

    private zzel() {
    }

    public final void zzd(int i, int i2) throws IOException {
        zzc(i, zzm(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzi(j));
    }

    public final void zza(int i, float f2) throws IOException {
        zze(i, Float.floatToRawIntBits(f2));
    }

    public final void zza(int i, double d2) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d2));
    }

    public final void zzc(int i) throws IOException {
        zzb(zzm(i));
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    static class zza extends zzel {
        private final byte[] zzb;
        private final int zzc;
        private final int zzd;
        private int zze;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i3 = i2 + 0;
            if ((i2 | 0 | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)));
            }
            this.zzb = bArr;
            this.zzc = 0;
            this.zze = 0;
            this.zzd = i3;
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(int i, int i2) throws IOException {
            zzb((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzb(int i, int i2) throws IOException {
            zza(i, 0);
            zza(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzc(int i, int i2) throws IOException {
            zza(i, 0);
            zzb(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zze(int i, int i2) throws IOException {
            zza(i, 5);
            zzd(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(int i, long j) throws IOException {
            zza(i, 0);
            zza(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzc(int i, long j) throws IOException {
            zza(i, 1);
            zzc(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(int i, boolean z) throws IOException {
            zza(i, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(int i, String str) throws IOException {
            zza(i, 2);
            zza(str);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(int i, zzdw zzdwVar) throws IOException {
            zza(i, 2);
            zza(zzdwVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(zzdw zzdwVar) throws IOException {
            zzb(zzdwVar.zza());
            zzdwVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzb(i2);
            zzc(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        final void zza(int i, zzgm zzgmVar, zzhf zzhfVar) throws IOException {
            zza(i, 2);
            zzdm zzdmVar = (zzdm) zzgmVar;
            int iZzbj = zzdmVar.zzbj();
            if (iZzbj == -1) {
                iZzbj = zzhfVar.zzb(zzdmVar);
                zzdmVar.zzc(iZzbj);
            }
            zzb(iZzbj);
            zzhfVar.zza(zzgmVar, this.zza);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(int i, zzgm zzgmVar) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, 2);
            zza(zzgmVar);
            zza(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzb(int i, zzdw zzdwVar) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, zzdwVar);
            zza(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(zzgm zzgmVar) throws IOException {
            zzb(zzgmVar.zzbm());
            zzgmVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(byte b2) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zze;
                this.zze = i + 1;
                bArr[i] = b2;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(int i) throws IOException {
            if (i >= 0) {
                zzb(i);
            } else {
                zza(i);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzb(int i) throws IOException {
            if (!zzel.zzc || zzdp.zza() || zza() < 5) {
                while ((i & (-128)) != 0) {
                    try {
                        byte[] bArr = this.zzb;
                        int i2 = this.zze;
                        this.zze = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (IndexOutOfBoundsException e2) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
                    }
                }
                byte[] bArr2 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr2[i3] = (byte) i;
                return;
            }
            if ((i & (-128)) == 0) {
                byte[] bArr3 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                zzid.zza(bArr3, i4, (byte) i);
                return;
            }
            byte[] bArr4 = this.zzb;
            int i5 = this.zze;
            this.zze = i5 + 1;
            zzid.zza(bArr4, i5, (byte) (i | 128));
            int i6 = i >>> 7;
            if ((i6 & (-128)) == 0) {
                byte[] bArr5 = this.zzb;
                int i7 = this.zze;
                this.zze = i7 + 1;
                zzid.zza(bArr5, i7, (byte) i6);
                return;
            }
            byte[] bArr6 = this.zzb;
            int i8 = this.zze;
            this.zze = i8 + 1;
            zzid.zza(bArr6, i8, (byte) (i6 | 128));
            int i9 = i6 >>> 7;
            if ((i9 & (-128)) == 0) {
                byte[] bArr7 = this.zzb;
                int i10 = this.zze;
                this.zze = i10 + 1;
                zzid.zza(bArr7, i10, (byte) i9);
                return;
            }
            byte[] bArr8 = this.zzb;
            int i11 = this.zze;
            this.zze = i11 + 1;
            zzid.zza(bArr8, i11, (byte) (i9 | 128));
            int i12 = i9 >>> 7;
            if ((i12 & (-128)) == 0) {
                byte[] bArr9 = this.zzb;
                int i13 = this.zze;
                this.zze = i13 + 1;
                zzid.zza(bArr9, i13, (byte) i12);
                return;
            }
            byte[] bArr10 = this.zzb;
            int i14 = this.zze;
            this.zze = i14 + 1;
            zzid.zza(bArr10, i14, (byte) (i12 | 128));
            byte[] bArr11 = this.zzb;
            int i15 = this.zze;
            this.zze = i15 + 1;
            zzid.zza(bArr11, i15, (byte) (i12 >>> 7));
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzd(int i) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.zzb;
                int i5 = this.zze;
                this.zze = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(long j) throws IOException {
            if (zzel.zzc && zza() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.zzb;
                    int i = this.zze;
                    this.zze = i + 1;
                    zzid.zza(bArr, i, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                zzid.zza(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.zzb;
                    int i3 = this.zze;
                    this.zze = i3 + 1;
                    bArr3[i3] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e2) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
                }
            }
            byte[] bArr4 = this.zzb;
            int i4 = this.zze;
            this.zze = i4 + 1;
            bArr4[i4] = (byte) j;
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zzc(long j) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zze;
                this.zze = i + 1;
                bArr[i] = (byte) j;
                byte[] bArr2 = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                bArr2[i2] = (byte) (j >> 8);
                byte[] bArr3 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr3[i3] = (byte) (j >> 16);
                byte[] bArr4 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                bArr4[i4] = (byte) (j >> 24);
                byte[] bArr5 = this.zzb;
                int i5 = this.zze;
                this.zze = i5 + 1;
                bArr5[i5] = (byte) (j >> 32);
                byte[] bArr6 = this.zzb;
                int i6 = this.zze;
                this.zze = i6 + 1;
                bArr6[i6] = (byte) (j >> 40);
                byte[] bArr7 = this.zzb;
                int i7 = this.zze;
                this.zze = i7 + 1;
                bArr7[i7] = (byte) (j >> 48);
                byte[] bArr8 = this.zzb;
                int i8 = this.zze;
                this.zze = i8 + 1;
                bArr8[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1), e2);
            }
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.zzb, this.zze, i2);
                this.zze += i2;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(i2)), e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzdt
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final void zza(String str) throws IOException {
            int i = this.zze;
            try {
                int iZzg = zzg(str.length() * 3);
                int iZzg2 = zzg(str.length());
                if (iZzg2 == iZzg) {
                    this.zze = i + iZzg2;
                    int iZza = zzif.zza(str, this.zzb, this.zze, zza());
                    this.zze = i;
                    zzb((iZza - i) - iZzg2);
                    this.zze = iZza;
                    return;
                }
                zzb(zzif.zza(str));
                this.zze = zzif.zza(str, this.zzb, this.zze, zza());
            } catch (zzij e2) {
                this.zze = i;
                zza(str, e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(e3);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzel
        public final int zza() {
            return this.zzd - this.zze;
        }
    }

    public final void zzb(long j) throws IOException {
        zza(zzi(j));
    }

    public final void zza(float f2) throws IOException {
        zzd(Float.floatToRawIntBits(f2));
    }

    public final void zza(double d2) throws IOException {
        zzc(Double.doubleToRawLongBits(d2));
    }

    public final void zza(boolean z) throws IOException {
        zza(z ? (byte) 1 : (byte) 0);
    }

    public static int zzf(int i, int i2) {
        return zze(i) + zzf(i2);
    }

    public static int zzg(int i, int i2) {
        return zze(i) + zzg(i2);
    }

    public static int zzh(int i, int i2) {
        return zze(i) + zzg(zzm(i2));
    }

    public static int zzi(int i, int i2) {
        return zze(i) + 4;
    }

    public static int zzj(int i, int i2) {
        return zze(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zze(i) + zze(j);
    }

    public static int zze(int i, long j) {
        return zze(i) + zze(j);
    }

    public static int zzf(int i, long j) {
        return zze(i) + zze(zzi(j));
    }

    public static int zzg(int i, long j) {
        return zze(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zze(i) + 8;
    }

    public static int zzb(int i, float f2) {
        return zze(i) + 4;
    }

    public static int zzb(int i, double d2) {
        return zze(i) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zze(i) + 1;
    }

    public static int zzk(int i, int i2) {
        return zze(i) + zzf(i2);
    }

    public static int zzb(int i, String str) {
        return zze(i) + zzb(str);
    }

    public static int zzc(int i, zzdw zzdwVar) {
        int iZze = zze(i);
        int iZza = zzdwVar.zza();
        return iZze + zzg(iZza) + iZza;
    }

    public static int zza(int i, zzfv zzfvVar) {
        int iZze = zze(i);
        int iZzb = zzfvVar.zzb();
        return iZze + zzg(iZzb) + iZzb;
    }

    static int zzb(int i, zzgm zzgmVar, zzhf zzhfVar) {
        return zze(i) + zza(zzgmVar, zzhfVar);
    }

    public static int zzb(int i, zzgm zzgmVar) {
        return (zze(1) << 1) + zzg(2, i) + zze(3) + zzb(zzgmVar);
    }

    public static int zzd(int i, zzdw zzdwVar) {
        return (zze(1) << 1) + zzg(2, i) + zzc(3, zzdwVar);
    }

    public static int zzb(int i, zzfv zzfvVar) {
        return (zze(1) << 1) + zzg(2, i) + zza(3, zzfvVar);
    }

    public static int zze(int i) {
        return zzg(i << 3);
    }

    public static int zzf(int i) {
        if (i >= 0) {
            return zzg(i);
        }
        return 10;
    }

    public static int zzh(int i) {
        return zzg(zzm(i));
    }

    public static int zzd(long j) {
        return zze(j);
    }

    public static int zzf(long j) {
        return zze(zzi(j));
    }

    public static int zzk(int i) {
        return zzf(i);
    }

    public static int zzb(String str) {
        int length;
        try {
            length = zzif.zza(str);
        } catch (zzij unused) {
            length = str.getBytes(zzfh.zza).length;
        }
        return zzg(length) + length;
    }

    public static int zza(zzfv zzfvVar) {
        int iZzb = zzfvVar.zzb();
        return zzg(iZzb) + iZzb;
    }

    public static int zzb(zzdw zzdwVar) {
        int iZza = zzdwVar.zza();
        return zzg(iZza) + iZza;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        return zzg(length) + length;
    }

    public static int zzb(zzgm zzgmVar) {
        int iZzbm = zzgmVar.zzbm();
        return zzg(iZzbm) + iZzbm;
    }

    static int zza(zzgm zzgmVar, zzhf zzhfVar) {
        zzdm zzdmVar = (zzdm) zzgmVar;
        int iZzbj = zzdmVar.zzbj();
        if (iZzbj == -1) {
            iZzbj = zzhfVar.zzb(zzdmVar);
            zzdmVar.zzc(iZzbj);
        }
        return zzg(iZzbj) + iZzbj;
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zza(String str, zzij zzijVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzijVar);
        byte[] bytes = str.getBytes(zzfh.zza);
        try {
            zzb(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (zzb e2) {
            throw e2;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzb(e3);
        }
    }

    @Deprecated
    static int zzc(int i, zzgm zzgmVar, zzhf zzhfVar) {
        int iZze = zze(i) << 1;
        zzdm zzdmVar = (zzdm) zzgmVar;
        int iZzbj = zzdmVar.zzbj();
        if (iZzbj == -1) {
            iZzbj = zzhfVar.zzb(zzdmVar);
            zzdmVar.zzc(iZzbj);
        }
        return iZze + iZzbj;
    }

    @Deprecated
    public static int zzc(zzgm zzgmVar) {
        return zzgmVar.zzbm();
    }

    @Deprecated
    public static int zzl(int i) {
        return zzg(i);
    }
}