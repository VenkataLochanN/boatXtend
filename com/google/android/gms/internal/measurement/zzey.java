package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzey {
    static final /* synthetic */ int[] zza;
    static final /* synthetic */ int[] zzb = new int[zzfo.values().length];

    static {
        try {
            zzb[zzfo.BYTE_STRING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzb[zzfo.MESSAGE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzb[zzfo.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        zza = new int[zzfb.values().length];
        try {
            zza[zzfb.MAP.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zza[zzfb.VECTOR.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zza[zzfb.SCALAR.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
    }
}