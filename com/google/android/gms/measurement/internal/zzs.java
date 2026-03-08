package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzbs;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzs {
    private zzbs.zzc zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzn zzd;

    private zzs(zzn zznVar) {
        this.zzd = zznVar;
    }

    final zzbs.zzc zza(String str, zzbs.zzc zzcVar) {
        String str2;
        String strZzc = zzcVar.zzc();
        List<zzbs.zze> listZza = zzcVar.zza();
        this.zzd.zzg();
        Long l = (Long) zzkr.zzb(zzcVar, "_eid");
        boolean z = l != null;
        if (z && strZzc.equals("_ep")) {
            this.zzd.zzg();
            str2 = (String) zzkr.zzb(zzcVar, "_en");
            if (TextUtils.isEmpty(str2)) {
                if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzd.zzt().zzd(str, zzap.zzcx)) {
                    this.zzd.zzr().zzg().zza("Extra parameter without an event name. eventId", l);
                } else {
                    this.zzd.zzr().zzf().zza("Extra parameter without an event name. eventId", l);
                }
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair<zzbs.zzc, Long> pairZza = this.zzd.zzi().zza(str, l);
                if (pairZza == null || pairZza.first == null) {
                    if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzd.zzt().zzd(str, zzap.zzcx)) {
                        this.zzd.zzr().zzg().zza("Extra parameter without existing main event. eventName, eventId", str2, l);
                    } else {
                        this.zzd.zzr().zzf().zza("Extra parameter without existing main event. eventName, eventId", str2, l);
                    }
                    return null;
                }
                this.zza = (zzbs.zzc) pairZza.first;
                this.zzc = ((Long) pairZza.second).longValue();
                this.zzd.zzg();
                this.zzb = (Long) zzkr.zzb(this.zza, "_eid");
            }
            this.zzc--;
            if (this.zzc <= 0) {
                zzac zzacVarZzi = this.zzd.zzi();
                zzacVarZzi.zzd();
                zzacVarZzi.zzr().zzx().zza("Clearing complex main event info. appId", str);
                try {
                    zzacVarZzi.c_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e2) {
                    zzacVarZzi.zzr().zzf().zza("Error clearing complex main event", e2);
                }
            } else {
                this.zzd.zzi().zza(str, l, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzbs.zze zzeVar : this.zza.zza()) {
                this.zzd.zzg();
                if (zzkr.zza(zzcVar, zzeVar.zzb()) == null) {
                    arrayList.add(zzeVar);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.addAll(listZza);
                listZza = arrayList;
            } else if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzd.zzt().zzd(str, zzap.zzcx)) {
                this.zzd.zzr().zzg().zza("No unique parameters in main event. eventName", str2);
            } else {
                this.zzd.zzr().zzi().zza("No unique parameters in main event. eventName", str2);
            }
        } else {
            if (z) {
                this.zzb = l;
                this.zza = zzcVar;
                this.zzd.zzg();
                Object objZzb = zzkr.zzb(zzcVar, "_epc");
                this.zzc = ((Long) (objZzb != null ? objZzb : 0L)).longValue();
                if (this.zzc > 0) {
                    this.zzd.zzi().zza(str, l, this.zzc, zzcVar);
                } else if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzd.zzt().zzd(str, zzap.zzcx)) {
                    this.zzd.zzr().zzg().zza("Complex event with zero extra param count. eventName", strZzc);
                } else {
                    this.zzd.zzr().zzi().zza("Complex event with zero extra param count. eventName", strZzc);
                }
            }
            str2 = strZzc;
        }
        return (zzbs.zzc) ((com.google.android.gms.internal.measurement.zzfe) zzcVar.zzbl().zza(str2).zzc().zza(listZza).zzv());
    }

    /* synthetic */ zzs(zzn zznVar, zzq zzqVar) {
        this(zznVar);
    }
}