package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzce implements zzcd {
    private static zzce zza;

    @Nullable
    private final Context zzb;

    @Nullable
    private final ContentObserver zzc;

    static zzce zza(Context context) {
        zzce zzceVar;
        synchronized (zzce.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzce(context) : new zzce();
            }
            zzceVar = zza;
        }
        return zzceVar;
    }

    private zzce(Context context) {
        this.zzb = context;
        this.zzc = new zzcg(this, null);
        context.getContentResolver().registerContentObserver(zzbv.zza, true, this.zzc);
    }

    private zzce() {
        this.zzb = null;
        this.zzc = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzcd
    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final String zza(final String str) {
        if (this.zzb == null) {
            return null;
        }
        try {
            return (String) zzcc.zza(new zzcf(this, str) { // from class: com.google.android.gms.internal.measurement.zzch
                private final zzce zza;
                private final String zzb;

                {
                    this.zza = this;
                    this.zzb = str;
                }

                @Override // com.google.android.gms.internal.measurement.zzcf
                public final Object zza() {
                    return this.zza.zzb(this.zzb);
                }
            });
        } catch (IllegalStateException | SecurityException e2) {
            String strValueOf = String.valueOf(str);
            Log.e("GservicesLoader", strValueOf.length() != 0 ? "Unable to read GServices for: ".concat(strValueOf) : new String("Unable to read GServices for: "), e2);
            return null;
        }
    }

    static synchronized void zza() {
        if (zza != null && zza.zzb != null && zza.zzc != null) {
            zza.zzb.getContentResolver().unregisterContentObserver(zza.zzc);
        }
        zza = null;
    }

    final /* synthetic */ String zzb(String str) {
        return zzbv.zza(this.zzb.getContentResolver(), str, (String) null);
    }
}