package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfz extends zzkk implements zzz {
    private static int zzb = 65535;
    private static int zzc = 2;
    private final Map<String, Map<String, String>> zzd;
    private final Map<String, Map<String, Boolean>> zze;
    private final Map<String, Map<String, Boolean>> zzf;
    private final Map<String, zzbq.zzb> zzg;
    private final Map<String, Map<String, Integer>> zzh;
    private final Map<String, String> zzi;

    zzfz(zzkj zzkjVar) {
        super(zzkjVar);
        this.zzd = new ArrayMap();
        this.zze = new ArrayMap();
        this.zzf = new ArrayMap();
        this.zzg = new ArrayMap();
        this.zzi = new ArrayMap();
        this.zzh = new ArrayMap();
    }

    @Override // com.google.android.gms.measurement.internal.zzkk
    protected final boolean zze() {
        return false;
    }

    private final void zzi(String str) throws Throwable {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        if (this.zzg.get(str) == null) {
            byte[] bArrZzd = zzi().zzd(str);
            if (bArrZzd == null) {
                this.zzd.put(str, null);
                this.zze.put(str, null);
                this.zzf.put(str, null);
                this.zzg.put(str, null);
                this.zzi.put(str, null);
                this.zzh.put(str, null);
                return;
            }
            zzbq.zzb.zza zzaVarZzbl = zza(str, bArrZzd).zzbl();
            zza(str, zzaVarZzbl);
            this.zzd.put(str, zza((zzbq.zzb) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzbl.zzv())));
            this.zzg.put(str, (zzbq.zzb) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzbl.zzv()));
            this.zzi.put(str, null);
        }
    }

    protected final zzbq.zzb zza(String str) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        zzi(str);
        return this.zzg.get(str);
    }

    protected final String zzb(String str) {
        zzd();
        return this.zzi.get(str);
    }

    protected final void zzc(String str) {
        zzd();
        this.zzi.put(str, null);
    }

    final void zzd(String str) {
        zzd();
        this.zzg.remove(str);
    }

    final boolean zze(String str) {
        zzd();
        zzbq.zzb zzbVarZza = zza(str);
        if (zzbVarZza == null) {
            return false;
        }
        return zzbVarZza.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzz
    public final String zza(String str, String str2) throws Throwable {
        zzd();
        zzi(str);
        Map<String, String> map = this.zzd.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    private static Map<String, String> zza(zzbq.zzb zzbVar) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzbVar != null) {
            for (zzbq.zzc zzcVar : zzbVar.zze()) {
                arrayMap.put(zzcVar.zza(), zzcVar.zzb());
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzbq.zzb.zza zzaVar) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zzaVar != null) {
            for (int i = 0; i < zzaVar.zza(); i++) {
                zzbq.zza.C0041zza c0041zzaZzbl = zzaVar.zza(i).zzbl();
                if (TextUtils.isEmpty(c0041zzaZzbl.zza())) {
                    zzr().zzi().zza("EventConfig contained null event name");
                } else {
                    String strZzb = zzhe.zzb(c0041zzaZzbl.zza());
                    if (!TextUtils.isEmpty(strZzb)) {
                        c0041zzaZzbl = c0041zzaZzbl.zza(strZzb);
                        zzaVar.zza(i, c0041zzaZzbl);
                    }
                    arrayMap.put(c0041zzaZzbl.zza(), Boolean.valueOf(c0041zzaZzbl.zzb()));
                    arrayMap2.put(c0041zzaZzbl.zza(), Boolean.valueOf(c0041zzaZzbl.zzc()));
                    if (c0041zzaZzbl.zzd()) {
                        if (c0041zzaZzbl.zze() < zzc || c0041zzaZzbl.zze() > zzb) {
                            zzr().zzi().zza("Invalid sampling rate. Event name, sample rate", c0041zzaZzbl.zza(), Integer.valueOf(c0041zzaZzbl.zze()));
                        } else {
                            arrayMap3.put(c0041zzaZzbl.zza(), Integer.valueOf(c0041zzaZzbl.zze()));
                        }
                    }
                }
            }
        }
        this.zze.put(str, arrayMap);
        this.zzf.put(str, arrayMap2);
        this.zzh.put(str, arrayMap3);
    }

    protected final boolean zza(String str, byte[] bArr, String str2) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        zzbq.zzb.zza zzaVarZzbl = zza(str, bArr).zzbl();
        if (zzaVarZzbl == null) {
            return false;
        }
        zza(str, zzaVarZzbl);
        this.zzg.put(str, (zzbq.zzb) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzbl.zzv()));
        this.zzi.put(str, str2);
        this.zzd.put(str, zza((zzbq.zzb) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzbl.zzv())));
        zzi().zzb(str, new ArrayList(zzaVarZzbl.zzb()));
        try {
            zzaVarZzbl.zzc();
            bArr = ((zzbq.zzb) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzbl.zzv())).zzbi();
        } catch (RuntimeException e2) {
            zzr().zzi().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzfb.zza(str), e2);
        }
        zzac zzacVarZzi = zzi();
        Preconditions.checkNotEmpty(str);
        zzacVarZzi.zzd();
        zzacVarZzi.zzak();
        new ContentValues().put("remote_config", bArr);
        try {
            if (zzacVarZzi.c_().update("apps", r2, "app_id = ?", new String[]{str}) == 0) {
                zzacVarZzi.zzr().zzf().zza("Failed to update remote config (got 0). appId", zzfb.zza(str));
            }
        } catch (SQLiteException e3) {
            zzacVarZzi.zzr().zzf().zza("Error storing remote config. appId", zzfb.zza(str), e3);
        }
        this.zzg.put(str, (zzbq.zzb) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzbl.zzv()));
        return true;
    }

    final boolean zzb(String str, String str2) throws Throwable {
        Boolean bool;
        zzd();
        zzi(str);
        if (zzg(str) && zzkv.zze(str2)) {
            return true;
        }
        if (zzh(str) && zzkv.zza(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zze.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    final boolean zzc(String str, String str2) throws Throwable {
        Boolean bool;
        zzd();
        zzi(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zzf.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    final int zzd(String str, String str2) throws Throwable {
        Integer num;
        zzd();
        zzi(str);
        Map<String, Integer> map = this.zzh.get(str);
        if (map == null || (num = map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    final long zzf(String str) throws Throwable {
        String strZza = zza(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(strZza)) {
            return 0L;
        }
        try {
            return Long.parseLong(strZza);
        } catch (NumberFormatException e2) {
            zzr().zzi().zza("Unable to parse timezone offset. appId", zzfb.zza(str), e2);
            return 0L;
        }
    }

    private final zzbq.zzb zza(String str, byte[] bArr) {
        if (bArr == null) {
            return zzbq.zzb.zzj();
        }
        try {
            zzbq.zzb zzbVar = (zzbq.zzb) ((com.google.android.gms.internal.measurement.zzfe) ((zzbq.zzb.zza) zzkr.zza(zzbq.zzb.zzi(), bArr)).zzv());
            zzr().zzx().zza("Parsed config. version, gmp_app_id", zzbVar.zza() ? Long.valueOf(zzbVar.zzb()) : null, zzbVar.zzc() ? zzbVar.zzd() : null);
            return zzbVar;
        } catch (com.google.android.gms.internal.measurement.zzfm e2) {
            zzr().zzi().zza("Unable to merge remote config. appId", zzfb.zza(str), e2);
            return zzbq.zzb.zzj();
        } catch (RuntimeException e3) {
            zzr().zzi().zza("Unable to merge remote config. appId", zzfb.zza(str), e3);
            return zzbq.zzb.zzj();
        }
    }

    final boolean zzg(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    final boolean zzh(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzkr zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzn e_() {
        return super.e_();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzfz zzj() {
        return super.zzj();
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