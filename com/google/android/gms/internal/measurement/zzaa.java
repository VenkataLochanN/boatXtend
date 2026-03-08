package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzx;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzaa extends zzx.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzx zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaa(zzx zzxVar, String str, String str2, Context context, Bundle bundle) {
        super(zzxVar);
        this.zzg = zzxVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzx.zza
    public final void zza() {
        String str;
        String str2;
        String str3;
        int iMax;
        boolean z;
        try {
            this.zzg.zzf = new ArrayList();
            zzx zzxVar = this.zzg;
            if (zzx.zzc(this.zzc, this.zzd)) {
                String str4 = this.zzd;
                str2 = this.zzc;
                str3 = str4;
                str = this.zzg.zzc;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            zzx.zzi(this.zze);
            boolean z2 = zzx.zzi.booleanValue() || str2 != null;
            this.zzg.zzr = this.zzg.zza(this.zze, z2);
            if (this.zzg.zzr == null) {
                Log.w(this.zzg.zzc, "Failed to connect to measurement client.");
                return;
            }
            int iZzh = zzx.zzh(this.zze);
            int iZzg = zzx.zzg(this.zze);
            if (z2) {
                iMax = Math.max(iZzh, iZzg);
                z = iZzg < iZzh;
            } else {
                iMax = iZzh > 0 ? iZzh : iZzg;
                if (iZzh > 0) {
                }
            }
            this.zzg.zzr.initialize(ObjectWrapper.wrap(this.zze), new zzv(21028L, iMax, z, str, str2, str3, this.zzf), this.zza);
        } catch (Exception e2) {
            this.zzg.zza(e2, true, false);
        }
    }
}