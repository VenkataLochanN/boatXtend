package com.google.android.gms.internal.measurement;

import java.io.IOException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzek extends zzei {
    private final byte[] zzd;
    private final boolean zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    private zzek(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzk = Integer.MAX_VALUE;
        this.zzd = bArr;
        this.zzf = i2 + i;
        this.zzh = i;
        this.zzi = this.zzh;
        this.zze = z;
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zza() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        this.zzj = zzv();
        int i = this.zzj;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw zzfm.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final void zza(int i) throws zzfm {
        if (this.zzj != i) {
            throw zzfm.zze();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final boolean zzb(int i) throws IOException {
        int iZza;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzf - this.zzh >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zzd;
                    int i4 = this.zzh;
                    this.zzh = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzfm.zzc();
            }
            while (i3 < 10) {
                if (zzaa() < 0) {
                    i3++;
                }
            }
            throw zzfm.zzc();
            return true;
        }
        if (i2 == 1) {
            zzf(8);
            return true;
        }
        if (i2 == 2) {
            zzf(zzv());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                zzf(4);
                return true;
            }
            throw zzfm.zzf();
        }
        do {
            iZza = zza();
            if (iZza == 0) {
                break;
            }
        } while (zzb(iZza));
        zza(((i >>> 3) << 3) | 4);
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzx());
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final long zzd() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final long zze() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzf() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final long zzg() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzh() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final boolean zzi() throws IOException {
        return zzw() != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final String zzj() throws IOException {
        int iZzv = zzv();
        if (iZzv > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (iZzv <= i - i2) {
                String str = new String(this.zzd, i2, iZzv, zzfh.zza);
                this.zzh += iZzv;
                return str;
            }
        }
        if (iZzv == 0) {
            return "";
        }
        if (iZzv < 0) {
            throw zzfm.zzb();
        }
        throw zzfm.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final String zzk() throws IOException {
        int iZzv = zzv();
        if (iZzv > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (iZzv <= i - i2) {
                String strZzb = zzif.zzb(this.zzd, i2, iZzv);
                this.zzh += iZzv;
                return strZzb;
            }
        }
        if (iZzv == 0) {
            return "";
        }
        if (iZzv <= 0) {
            throw zzfm.zzb();
        }
        throw zzfm.zza();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    @Override // com.google.android.gms.internal.measurement.zzei
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.measurement.zzdw zzl() throws java.io.IOException {
        /*
            r3 = this;
            int r0 = r3.zzv()
            if (r0 <= 0) goto L19
            int r1 = r3.zzf
            int r2 = r3.zzh
            int r1 = r1 - r2
            if (r0 > r1) goto L19
            byte[] r1 = r3.zzd
            com.google.android.gms.internal.measurement.zzdw r1 = com.google.android.gms.internal.measurement.zzdw.zza(r1, r2, r0)
            int r2 = r3.zzh
            int r2 = r2 + r0
            r3.zzh = r2
            return r1
        L19:
            if (r0 != 0) goto L1e
            com.google.android.gms.internal.measurement.zzdw r0 = com.google.android.gms.internal.measurement.zzdw.zza
            return r0
        L1e:
            if (r0 <= 0) goto L33
            int r1 = r3.zzf
            int r2 = r3.zzh
            int r1 = r1 - r2
            if (r0 > r1) goto L33
            int r0 = r0 + r2
            r3.zzh = r0
            byte[] r0 = r3.zzd
            int r1 = r3.zzh
            byte[] r0 = java.util.Arrays.copyOfRange(r0, r2, r1)
            goto L39
        L33:
            if (r0 > 0) goto L43
            if (r0 != 0) goto L3e
            byte[] r0 = com.google.android.gms.internal.measurement.zzfh.zzb
        L39:
            com.google.android.gms.internal.measurement.zzdw r0 = com.google.android.gms.internal.measurement.zzdw.zza(r0)
            return r0
        L3e:
            com.google.android.gms.internal.measurement.zzfm r0 = com.google.android.gms.internal.measurement.zzfm.zzb()
            throw r0
        L43:
            com.google.android.gms.internal.measurement.zzfm r0 = com.google.android.gms.internal.measurement.zzfm.zza()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzl():com.google.android.gms.internal.measurement.zzdw");
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzm() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzn() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzo() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final long zzp() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzq() throws IOException {
        return zze(zzv());
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final long zzr() throws IOException {
        return zza(zzw());
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
    
        if (r2[r3] >= 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzv() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzh
            int r1 = r5.zzf
            if (r1 == r0) goto L6b
            byte[] r2 = r5.zzd
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L11
            r5.zzh = r3
            return r0
        L11:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6b
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L22
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L68
        L22:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L2f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2d:
            r1 = r3
            goto L68
        L2f:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L68
        L3d:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 < 0) goto L6b
        L68:
            r5.zzh = r1
            return r0
        L6b:
            long r0 = r5.zzs()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzv():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
    
        if (r2[r0] >= 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final long zzw() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.zzh
            int r1 = r11.zzf
            if (r1 == r0) goto Lb8
            byte[] r2 = r11.zzd
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r11.zzh = r3
            long r0 = (long) r0
            return r0
        L12:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto Lb8
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L25
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
        L22:
            long r2 = (long) r0
            goto Lb5
        L25:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L36
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r2 = r9
            goto Lb5
        L36:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L44
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L22
        L44:
            long r3 = (long) r0
            int r0 = r1 + 1
            r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L5b
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L57:
            long r2 = r3 ^ r1
            r1 = r0
            goto Lb5
        L5b:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L70
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L6d:
            long r2 = r3 ^ r5
            goto Lb5
        L70:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L83
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L57
        L83:
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L96
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L6d
        L96:
            int r0 = r1 + 1
            r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto Lb3
            int r1 = r0 + 1
            r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 < 0) goto Lb8
            goto Lb4
        Lb3:
            r1 = r0
        Lb4:
            r2 = r3
        Lb5:
            r11.zzh = r1
            return r2
        Lb8:
            long r0 = r11.zzs()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzw():long");
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte bZzaa = zzaa();
            j |= ((long) (bZzaa & ByteCompanionObject.MAX_VALUE)) << i;
            if ((bZzaa & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzfm.zzc();
    }

    private final int zzx() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 4) {
            throw zzfm.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i + 4;
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    private final long zzy() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 8) {
            throw zzfm.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzc(int i) throws zzfm {
        if (i < 0) {
            throw zzfm.zzb();
        }
        int iZzu = i + zzu();
        int i2 = this.zzk;
        if (iZzu > i2) {
            throw zzfm.zza();
        }
        this.zzk = iZzu;
        zzz();
        return i2;
    }

    private final void zzz() {
        this.zzf += this.zzg;
        int i = this.zzf;
        int i2 = i - this.zzi;
        int i3 = this.zzk;
        if (i2 > i3) {
            this.zzg = i2 - i3;
            this.zzf = i - this.zzg;
        } else {
            this.zzg = 0;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final void zzd(int i) {
        this.zzk = i;
        zzz();
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final boolean zzt() throws IOException {
        return this.zzh == this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzei
    public final int zzu() {
        return this.zzh - this.zzi;
    }

    private final byte zzaa() throws IOException {
        int i = this.zzh;
        if (i == this.zzf) {
            throw zzfm.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i + 1;
        return bArr[i];
    }

    private final void zzf(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (i <= i2 - i3) {
                this.zzh = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzfm.zzb();
        }
        throw zzfm.zza();
    }
}