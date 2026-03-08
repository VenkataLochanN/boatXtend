package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzcb extends ContentObserver {
    private final /* synthetic */ zzbz zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcb(zzbz zzbzVar, Handler handler) {
        super(null);
        this.zza = zzbzVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zza.zzb();
    }
}