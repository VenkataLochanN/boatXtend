package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdw implements Serializable, Iterable<Byte> {
    public static final zzdw zza = new zzeg(zzfh.zzb);
    private static final zzec zzb;
    private static final Comparator<zzdw> zzd;
    private int zzc = 0;

    zzdw() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzb(byte b2) {
        return b2 & UByte.MAX_VALUE;
    }

    public abstract boolean equals(Object obj);

    public abstract byte zza(int i);

    public abstract int zza();

    protected abstract int zza(int i, int i2, int i3);

    public abstract zzdw zza(int i, int i2);

    protected abstract String zza(Charset charset);

    abstract void zza(zzdt zzdtVar) throws IOException;

    abstract byte zzb(int i);

    public abstract boolean zzc();

    public static zzdw zza(byte[] bArr, int i, int i2) {
        zzb(i, i + i2, bArr.length);
        return new zzeg(zzb.zza(bArr, i, i2));
    }

    static zzdw zza(byte[] bArr) {
        return new zzeg(bArr);
    }

    public static zzdw zza(String str) {
        return new zzeg(str.getBytes(zzfh.zza));
    }

    public final String zzb() {
        return zza() == 0 ? "" : zza(zzfh.zza);
    }

    public final int hashCode() {
        int iZza = this.zzc;
        if (iZza == 0) {
            int iZza2 = zza();
            iZza = zza(iZza2, 0, iZza2);
            if (iZza == 0) {
                iZza = 1;
            }
            this.zzc = iZza;
        }
        return iZza;
    }

    static zzee zzc(int i) {
        return new zzee(i, null);
    }

    protected final int zzd() {
        return this.zzc;
    }

    static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("End index: ");
        sb3.append(i2);
        sb3.append(" >= ");
        sb3.append(i3);
        throw new IndexOutOfBoundsException(sb3.toString());
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zza());
        objArr[2] = zza() <= 50 ? zzht.zza(this) : String.valueOf(zzht.zza(zza(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzdv(this);
    }

    static {
        zzdv zzdvVar = null;
        zzb = zzdp.zza() ? new zzef(zzdvVar) : new zzea(zzdvVar);
        zzd = new zzdy();
    }
}