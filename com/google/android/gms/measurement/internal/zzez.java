package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzez extends zzgz {
    private static final AtomicReference<String[]> zza = new AtomicReference<>();
    private static final AtomicReference<String[]> zzb = new AtomicReference<>();
    private static final AtomicReference<String[]> zzc = new AtomicReference<>();

    zzez(zzgf zzgfVar) {
        super(zzgfVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzgz
    protected final boolean zze() {
        return false;
    }

    private final boolean zzg() {
        zzu();
        return this.zzx.zzl() && this.zzx.zzr().zza(3);
    }

    protected final String zza(String str) {
        if (str == null) {
            return null;
        }
        return !zzg() ? str : zza(str, zzhe.zzb, zzhe.zza, zza);
    }

    protected final String zzb(String str) {
        if (str == null) {
            return null;
        }
        return !zzg() ? str : zza(str, zzhd.zzb, zzhd.zza, zzb);
    }

    protected final String zzc(String str) {
        if (str == null) {
            return null;
        }
        if (!zzg()) {
            return str;
        }
        if (str.startsWith("_exp_")) {
            return "experiment_id(" + str + ")";
        }
        return zza(str, zzhg.zzb, zzhg.zza, zzc);
    }

    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzkv.zzc(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        strArr3[i] = strArr2[i] + "(" + strArr[i] + ")";
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    protected final String zza(zzan zzanVar) {
        if (zzanVar == null) {
            return null;
        }
        if (!zzg()) {
            return zzanVar.toString();
        }
        return "origin=" + zzanVar.zzc + ",name=" + zza(zzanVar.zza) + ",params=" + zza(zzanVar.zzb);
    }

    protected final String zza(zzak zzakVar) {
        if (zzakVar == null) {
            return null;
        }
        if (!zzg()) {
            return zzakVar.toString();
        }
        return "Event{appId='" + zzakVar.zza + "', name='" + zza(zzakVar.zzb) + "', params=" + zza(zzakVar.zze) + "}";
    }

    private final String zza(zzam zzamVar) {
        if (zzamVar == null) {
            return null;
        }
        if (!zzg()) {
            return zzamVar.toString();
        }
        return zza(zzamVar.zzb());
    }

    protected final String zza(Bundle bundle) {
        String strValueOf;
        if (bundle == null) {
            return null;
        }
        if (!zzg()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (String str : bundle.keySet()) {
            if (sb.length() != 8) {
                sb.append(", ");
            }
            sb.append(zzb(str));
            sb.append("=");
            if (com.google.android.gms.internal.measurement.zzjr.zzb() && zzt().zza(zzap.zzcy)) {
                Object obj = bundle.get(str);
                if (obj instanceof Bundle) {
                    strValueOf = zza(new Object[]{obj});
                } else if (obj instanceof Object[]) {
                    strValueOf = zza((Object[]) obj);
                } else if (obj instanceof ArrayList) {
                    strValueOf = zza(((ArrayList) obj).toArray());
                } else {
                    strValueOf = String.valueOf(obj);
                }
                sb.append(strValueOf);
            } else {
                sb.append(bundle.get(str));
            }
        }
        sb.append("}]");
        return sb.toString();
    }

    private final String zza(Object[] objArr) {
        String strValueOf;
        if (objArr == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : objArr) {
            if (obj instanceof Bundle) {
                strValueOf = zza((Bundle) obj);
            } else {
                strValueOf = String.valueOf(obj);
            }
            if (strValueOf != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(strValueOf);
            }
        }
        sb.append("]");
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