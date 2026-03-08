package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfb extends zzgz {
    private char zza;
    private long zzb;
    private String zzc;
    private final zzfd zzd;
    private final zzfd zze;
    private final zzfd zzf;
    private final zzfd zzg;
    private final zzfd zzh;
    private final zzfd zzi;
    private final zzfd zzj;
    private final zzfd zzk;
    private final zzfd zzl;

    zzfb(zzgf zzgfVar) {
        super(zzgfVar);
        this.zza = (char) 0;
        this.zzb = -1L;
        this.zzd = new zzfd(this, 6, false, false);
        this.zze = new zzfd(this, 6, true, false);
        this.zzf = new zzfd(this, 6, false, true);
        this.zzg = new zzfd(this, 5, false, false);
        this.zzh = new zzfd(this, 5, true, false);
        this.zzi = new zzfd(this, 5, false, true);
        this.zzj = new zzfd(this, 4, false, false);
        this.zzk = new zzfd(this, 3, false, false);
        this.zzl = new zzfd(this, 2, false, false);
    }

    @Override // com.google.android.gms.measurement.internal.zzgz
    protected final boolean zze() {
        return false;
    }

    public final zzfd zzf() {
        return this.zzd;
    }

    public final zzfd zzg() {
        return this.zze;
    }

    public final zzfd zzh() {
        return this.zzf;
    }

    public final zzfd zzi() {
        return this.zzg;
    }

    public final zzfd zzj() {
        return this.zzh;
    }

    public final zzfd zzk() {
        return this.zzi;
    }

    public final zzfd zzv() {
        return this.zzj;
    }

    public final zzfd zzw() {
        return this.zzk;
    }

    public final zzfd zzx() {
        return this.zzl;
    }

    protected static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzfg(str);
    }

    protected final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zza(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (z2 || i < 5) {
            return;
        }
        Preconditions.checkNotNull(str);
        zzgc zzgcVarZzg = this.zzx.zzg();
        if (zzgcVarZzg == null) {
            zza(6, "Scheduler not set. Not logging error/warn");
            return;
        }
        if (!zzgcVarZzg.zzz()) {
            zza(6, "Scheduler not initialized. Not logging error/warn");
            return;
        }
        if (i < 0) {
            i = 0;
        }
        if (i >= 9) {
            i = 8;
        }
        zzgcVarZzg.zza(new zzfe(this, i, str, obj, obj2, obj3));
    }

    protected final boolean zza(int i) {
        return Log.isLoggable(zzad(), i);
    }

    protected final void zza(int i, String str) {
        Log.println(i, zzad(), str);
    }

    private final String zzad() {
        String str;
        String strZzs;
        synchronized (this) {
            if (this.zzc == null) {
                if (this.zzx.zzs() != null) {
                    strZzs = this.zzx.zzs();
                } else {
                    zzt().zzu();
                    strZzs = "FA";
                }
                this.zzc = strZzs;
            }
            str = this.zzc;
        }
        return str;
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String strZza = zza(z, obj);
        String strZza2 = zza(z, obj2);
        String strZza3 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(strZza)) {
            sb.append(str2);
            sb.append(strZza);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZza2)) {
            sb.append(str2);
            sb.append(strZza2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZza3)) {
            sb.append(str2);
            sb.append(strZza3);
        }
        return sb.toString();
    }

    private static String zza(boolean z, Object obj) {
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String strValueOf = String.valueOf(Math.abs(l.longValue()));
            long jRound = Math.round(Math.pow(10.0d, strValueOf.length() - 1));
            long jRound2 = Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(str.length() + 43 + str.length());
            sb.append(str);
            sb.append(jRound);
            sb.append("...");
            sb.append(str);
            sb.append(jRound2);
            return sb.toString();
        }
        if (obj instanceof Boolean) {
            return String.valueOf(obj);
        }
        if (obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String strZzb = zzb(zzgf.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzb(className).equals(strZzb)) {
                    sb2.append(": ");
                    sb2.append(stackTraceElement);
                    break;
                }
                i++;
            }
            return sb2.toString();
        }
        if (obj instanceof zzfg) {
            return ((zzfg) obj).zza;
        }
        return z ? "-" : String.valueOf(obj);
    }

    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
    }

    public final String zzy() {
        Pair<String, Long> pairZza = zzs().zzb.zza();
        if (pairZza == null || pairZza == zzfo.zza) {
            return null;
        }
        String strValueOf = String.valueOf(pairZza.second);
        String str = (String) pairZza.first;
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(str).length());
        sb.append(strValueOf);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzah zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzez zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzkv zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzgc zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzfb zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzfo zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzw zzu() {
        return super.zzu();
    }
}