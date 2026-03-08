package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzln;
import com.google.android.gms.internal.measurement.zznd;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzey extends zze {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private long zzg;
    private List<String> zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;

    zzey(zzgf zzgfVar, long j) {
        super(zzgfVar);
        this.zzg = j;
    }

    @Override // com.google.android.gms.measurement.internal.zze
    protected final boolean zzz() {
        return true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:0|2|(1:4)(26:138|6|(1:10)(2:11|(1:13))|142|14|(4:16|(1:18)(1:20)|136|21)|26|(1:31)(1:30)|32|(1:37)(1:36)|38|(1:(1:41)(1:42))|(1:77)(2:44|(3:61|(2:63|(1:65))(1:(1:(2:76|60)(1:75))(2:69|(1:71)))|77)(3:48|SW:49|(1:60)(0)))|78|(1:80)|140|81|(1:83)(1:84)|85|86|(2:99|(1:101))(4:90|(1:92)(1:93)|94|(1:98))|(3:103|(1:105)(1:106)|107)|111|(3:113|(3:115|(1:117)(3:119|(3:122|(1:145)(1:146)|120)|144)|118)|(1:126))|127|(1:(2:130|131)(2:132|133))(2:134|135))|5|26|(2:28|31)(0)|32|(2:34|37)(0)|38|(0)|(0)(0)|78|(0)|140|81|(0)(0)|85|86|(4:88|99|(0)|(0))(0)|111|(0)|127|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02aa, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02ab, code lost:
    
        zzr().zzf().zza("Fetching Google App Id failed with exception. appId", com.google.android.gms.measurement.internal.zzfb.zza(r0), r2);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x027c A[Catch: IllegalStateException -> 0x02aa, TryCatch #2 {IllegalStateException -> 0x02aa, blocks: (B:81:0x0227, B:85:0x0234, B:88:0x023e, B:90:0x024a, B:94:0x0261, B:96:0x0269, B:103:0x028d, B:105:0x02a1, B:107:0x02a6, B:106:0x02a4, B:98:0x026f, B:99:0x0276, B:101:0x027c), top: B:140:0x0227 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x028d A[Catch: IllegalStateException -> 0x02aa, TryCatch #2 {IllegalStateException -> 0x02aa, blocks: (B:81:0x0227, B:85:0x0234, B:88:0x023e, B:90:0x024a, B:94:0x0261, B:96:0x0269, B:103:0x028d, B:105:0x02a1, B:107:0x02a6, B:106:0x02a4, B:98:0x026f, B:99:0x0276, B:101:0x027c), top: B:140:0x0227 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0276 A[Catch: IllegalStateException -> 0x02aa, TryCatch #2 {IllegalStateException -> 0x02aa, blocks: (B:81:0x0227, B:85:0x0234, B:88:0x023e, B:90:0x024a, B:94:0x0261, B:96:0x0269, B:103:0x028d, B:105:0x02a1, B:107:0x02a6, B:106:0x02a4, B:98:0x026f, B:99:0x0276, B:101:0x027c), top: B:140:0x0227 }] */
    @Override // com.google.android.gms.measurement.internal.zze
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void zzaa() {
        /*
            Method dump skipped, instruction units count: 830
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzey.zzaa():void");
    }

    final zzm zza(String str) {
        boolean z;
        Boolean boolValueOf;
        Boolean boolZzb;
        zzd();
        zzb();
        String strZzab = zzab();
        String strZzac = zzac();
        zzw();
        String str2 = this.zzb;
        long jZzaf = zzaf();
        zzw();
        String str3 = this.zzd;
        long jZze = zzt().zze();
        zzw();
        zzd();
        if (this.zzf == 0) {
            this.zzf = this.zzx.zzi().zza(zzn(), zzn().getPackageName());
        }
        long j = this.zzf;
        boolean zZzab = this.zzx.zzab();
        boolean z2 = !zzs().zzs;
        zzd();
        zzb();
        String strZzai = !this.zzx.zzab() ? null : zzai();
        long jZzad = this.zzx.zzad();
        int iZzag = zzag();
        boolean zBooleanValue = zzt().zzi().booleanValue();
        zzx zzxVarZzt = zzt();
        zzxVarZzt.zzb();
        Boolean boolZzb2 = zzxVarZzt.zzb("google_analytics_ssaid_collection_enabled");
        boolean zBooleanValue2 = Boolean.valueOf(boolZzb2 == null || boolZzb2.booleanValue()).booleanValue();
        zzfo zzfoVarZzs = zzs();
        zzfoVarZzs.zzd();
        boolean z3 = zzfoVarZzs.zzg().getBoolean("deferred_analytics_collection", false);
        String strZzad = zzad();
        if (!zzt().zza(zzap.zzaz) || (boolZzb = zzt().zzb("google_analytics_default_allow_ad_personalization_signals")) == null) {
            z = z2;
            boolValueOf = null;
        } else {
            boolValueOf = Boolean.valueOf(!boolZzb.booleanValue());
            z = z2;
        }
        return new zzm(strZzab, strZzac, str2, jZzaf, str3, jZze, j, str, zZzab, z, strZzai, 0L, jZzad, iZzag, zBooleanValue, zBooleanValue2, z3, strZzad, boolValueOf, this.zzg, zzt().zza(zzap.zzbj) ? this.zzh : null, (zzln.zzb() && zzt().zza(zzap.zzcf)) ? zzae() : null);
    }

    private final String zzai() {
        if (zznd.zzb() && zzt().zza(zzap.zzci)) {
            zzr().zzx().zza("Disabled IID for tests.");
            return null;
        }
        try {
            Class<?> clsLoadClass = zzn().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (clsLoadClass == null) {
                return null;
            }
            try {
                Object objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, zzn());
                if (objInvoke == null) {
                    return null;
                }
                try {
                    return (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(objInvoke, new Object[0]);
                } catch (Exception unused) {
                    zzr().zzk().zza("Failed to retrieve Firebase Instance Id");
                    return null;
                }
            } catch (Exception unused2) {
                zzr().zzj().zza("Failed to obtain Firebase Analytics instance");
                return null;
            }
        } catch (ClassNotFoundException unused3) {
        }
    }

    final String zzab() {
        zzw();
        return this.zza;
    }

    final String zzac() {
        zzw();
        return this.zzj;
    }

    final String zzad() {
        zzw();
        return this.zzk;
    }

    final String zzae() {
        zzw();
        return this.zzl;
    }

    final int zzaf() {
        zzw();
        return this.zzc;
    }

    final int zzag() {
        zzw();
        return this.zzi;
    }

    final List<String> zzah() {
        return this.zzh;
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzb zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzhk zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzey zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzis zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzin zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzex zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzjt zzk() {
        return super.zzk();
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