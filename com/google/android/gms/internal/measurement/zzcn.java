package com.google.android.gms.internal.measurement;

import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzcn<T> {
    private static zzdb<zzcw<zzcj>> zzd;
    private final zzct zze;
    private final String zzf;
    private final T zzg;
    private volatile int zzi;
    private volatile T zzj;
    private static final Object zza = new Object();
    private static Context zzb = null;
    private static boolean zzc = false;
    private static final AtomicInteger zzh = new AtomicInteger();

    public static void zza(Context context) {
        synchronized (zza) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzb != context) {
                zzbz.zzc();
                zzcs.zza();
                zzce.zza();
                zzh.incrementAndGet();
                zzb = context;
                zzd = zzda.zza(zzcm.zza);
            }
        }
    }

    abstract T zza(Object obj);

    static void zza() {
        zzh.incrementAndGet();
    }

    private zzcn(zzct zzctVar, String str, T t) {
        this.zzi = -1;
        if (zzctVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zze = zzctVar;
        this.zzf = str;
        this.zzg = t;
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzf;
        }
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf(this.zzf);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    public final String zzb() {
        return zza(this.zze.zzd);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009c A[Catch: all -> 0x00f5, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0013, B:11:0x0023, B:17:0x0035, B:19:0x003b, B:21:0x0047, B:25:0x0060, B:27:0x006a, B:45:0x00bb, B:47:0x00c9, B:49:0x00df, B:50:0x00e2, B:51:0x00e6, B:38:0x009c, B:40:0x00b0, B:44:0x00b9, B:23:0x0058, B:28:0x006f, B:30:0x0078, B:32:0x008a, B:34:0x0095, B:33:0x008f, B:52:0x00eb, B:53:0x00f2, B:54:0x00f3), top: B:61:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c9 A[Catch: all -> 0x00f5, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0013, B:11:0x0023, B:17:0x0035, B:19:0x003b, B:21:0x0047, B:25:0x0060, B:27:0x006a, B:45:0x00bb, B:47:0x00c9, B:49:0x00df, B:50:0x00e2, B:51:0x00e6, B:38:0x009c, B:40:0x00b0, B:44:0x00b9, B:23:0x0058, B:28:0x006f, B:30:0x0078, B:32:0x008a, B:34:0x0095, B:33:0x008f, B:52:0x00eb, B:53:0x00f2, B:54:0x00f3), top: B:61:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T zzc() {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcn.zzc():java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcn<Long> zzb(zzct zzctVar, String str, long j) {
        return new zzcp(zzctVar, str, Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcn<Boolean> zzb(zzct zzctVar, String str, boolean z) {
        return new zzco(zzctVar, str, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcn<Double> zzb(zzct zzctVar, String str, double d2) {
        return new zzcr(zzctVar, str, Double.valueOf(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcn<String> zzb(zzct zzctVar, String str, String str2) {
        return new zzcq(zzctVar, str, str2);
    }

    static final /* synthetic */ zzcw zzd() {
        new zzci();
        return zzci.zza(zzb);
    }

    /* synthetic */ zzcn(zzct zzctVar, String str, Object obj, zzcp zzcpVar) {
        this(zzctVar, str, obj);
    }
}