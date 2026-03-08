package com.google.android.gms.internal.measurement;

import com.autonavi.amap.mapcore.tools.GlMapUtil;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzii extends zzih {
    zzii() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x008f, code lost:
    
        return -1;
     */
    @Override // com.google.android.gms.internal.measurement.zzih
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zza(int r18, byte[] r19, int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzii.zza(int, byte[], int, int):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    final String zza(byte[] bArr, int i, int i2) throws zzfm {
        if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte bZza = zzid.zza(bArr, i);
            if (!zzie.zzd(bZza)) {
                break;
            }
            i++;
            zzie.zzb(bZza, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte bZza2 = zzid.zza(bArr, i);
            if (zzie.zzd(bZza2)) {
                int i7 = i5 + 1;
                zzie.zzb(bZza2, cArr, i5);
                while (i6 < i3) {
                    byte bZza3 = zzid.zza(bArr, i6);
                    if (!zzie.zzd(bZza3)) {
                        break;
                    }
                    i6++;
                    zzie.zzb(bZza3, cArr, i7);
                    i7++;
                }
                i = i6;
                i5 = i7;
            } else if (zzie.zze(bZza2)) {
                if (i6 < i3) {
                    zzie.zzb(bZza2, zzid.zza(bArr, i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else {
                    throw zzfm.zzh();
                }
            } else if (zzie.zzf(bZza2)) {
                if (i6 < i3 - 1) {
                    int i8 = i6 + 1;
                    zzie.zzb(bZza2, zzid.zza(bArr, i6), zzid.zza(bArr, i8), cArr, i5);
                    i = i8 + 1;
                    i5++;
                } else {
                    throw zzfm.zzh();
                }
            } else {
                if (i6 >= i3 - 2) {
                    throw zzfm.zzh();
                }
                int i9 = i6 + 1;
                byte bZza4 = zzid.zza(bArr, i6);
                int i10 = i9 + 1;
                zzie.zzb(bZza2, bZza4, zzid.zza(bArr, i9), zzid.zza(bArr, i10), cArr, i5);
                i = i10 + 1;
                i5 = i5 + 1 + 1;
            }
        }
        return new String(cArr, 0, i5);
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    final int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c2;
        long j;
        long j2;
        long j3;
        char c3;
        int i3;
        char cCharAt;
        long j4 = i;
        long j5 = ((long) i2) + j4;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char cCharAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(cCharAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c2 = 128;
            j = 1;
            if (i4 >= length || (cCharAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzid.zza(bArr, j4, (byte) cCharAt);
            i4++;
            j4 = 1 + j4;
        }
        if (i4 == length) {
            return (int) j4;
        }
        while (i4 < length) {
            char cCharAt3 = charSequence.charAt(i4);
            if (cCharAt3 < c2 && j4 < j5) {
                long j6 = j4 + j;
                zzid.zza(bArr, j4, (byte) cCharAt3);
                j3 = j;
                j2 = j6;
                c3 = c2;
            } else if (cCharAt3 < 2048 && j4 <= j5 - 2) {
                long j7 = j4 + j;
                zzid.zza(bArr, j4, (byte) ((cCharAt3 >>> 6) | 960));
                long j8 = j7 + j;
                zzid.zza(bArr, j7, (byte) ((cCharAt3 & '?') | 128));
                long j9 = j;
                c3 = 128;
                j2 = j8;
                j3 = j9;
            } else {
                if ((cCharAt3 >= 55296 && 57343 >= cCharAt3) || j4 > j5 - 3) {
                    if (j4 <= j5 - 4) {
                        int i5 = i4 + 1;
                        if (i5 != length) {
                            char cCharAt4 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                long j10 = j4 + 1;
                                zzid.zza(bArr, j4, (byte) ((codePoint >>> 18) | GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN));
                                long j11 = j10 + 1;
                                c3 = 128;
                                zzid.zza(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j12 = j11 + 1;
                                zzid.zza(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                                j3 = 1;
                                j2 = j12 + 1;
                                zzid.zza(bArr, j12, (byte) ((codePoint & 63) | 128));
                                i4 = i5;
                            } else {
                                i4 = i5;
                            }
                        }
                        throw new zzij(i4 - 1, length);
                    }
                    if (55296 <= cCharAt3 && cCharAt3 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt3, charSequence.charAt(i3)))) {
                        throw new zzij(i4, length);
                    }
                    StringBuilder sb2 = new StringBuilder(46);
                    sb2.append("Failed writing ");
                    sb2.append(cCharAt3);
                    sb2.append(" at index ");
                    sb2.append(j4);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
                long j13 = j4 + j;
                zzid.zza(bArr, j4, (byte) ((cCharAt3 >>> '\f') | GlMapUtil.DEVICE_DISPLAY_DPI_XHIGH));
                long j14 = j13 + j;
                zzid.zza(bArr, j13, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                zzid.zza(bArr, j14, (byte) ((cCharAt3 & '?') | 128));
                j2 = j14 + 1;
                j3 = 1;
                c3 = 128;
            }
            i4++;
            c2 = c3;
            long j15 = j3;
            j4 = j2;
            j = j15;
        }
        return (int) j4;
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            return zzif.zzb(i);
        }
        if (i2 == 1) {
            return zzif.zzb(i, zzid.zza(bArr, j));
        }
        if (i2 == 2) {
            return zzif.zzb(i, zzid.zza(bArr, j), zzid.zza(bArr, j + 1));
        }
        throw new AssertionError();
    }
}