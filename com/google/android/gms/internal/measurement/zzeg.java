package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
class zzeg extends zzed {
    protected final byte[] zzb;

    zzeg(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzb = bArr;
    }

    protected int zze() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    public byte zza(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    byte zzb(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    public int zza() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    public final zzdw zza(int i, int i2) {
        int iZzb = zzb(0, i2, zza());
        if (iZzb == 0) {
            return zzdw.zza;
        }
        return new zzdz(this.zzb, zze(), iZzb);
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    final void zza(zzdt zzdtVar) throws IOException {
        zzdtVar.zza(this.zzb, zze(), zza());
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    protected final String zza(Charset charset) {
        return new String(this.zzb, zze(), zza(), charset);
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    public final boolean zzc() {
        int iZze = zze();
        return zzif.zza(this.zzb, iZze, zza() + iZze);
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdw) || zza() != ((zzdw) obj).zza()) {
            return false;
        }
        if (zza() == 0) {
            return true;
        }
        if (obj instanceof zzeg) {
            zzeg zzegVar = (zzeg) obj;
            int iZzd = zzd();
            int iZzd2 = zzegVar.zzd();
            if (iZzd == 0 || iZzd2 == 0 || iZzd == iZzd2) {
                return zza(zzegVar, 0, zza());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzed
    final boolean zza(zzdw zzdwVar, int i, int i2) {
        if (i2 > zzdwVar.zza()) {
            int iZza = zza();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(iZza);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 > zzdwVar.zza()) {
            int iZza2 = zzdwVar.zza();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(iZza2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (zzdwVar instanceof zzeg) {
            zzeg zzegVar = (zzeg) zzdwVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzegVar.zzb;
            int iZze = zze() + i2;
            int iZze2 = zze();
            int iZze3 = zzegVar.zze();
            while (iZze2 < iZze) {
                if (bArr[iZze2] != bArr2[iZze3]) {
                    return false;
                }
                iZze2++;
                iZze3++;
            }
            return true;
        }
        return zzdwVar.zza(0, i2).equals(zza(0, i2));
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    protected final int zza(int i, int i2, int i3) {
        return zzfh.zza(i, this.zzb, zze(), i3);
    }
}