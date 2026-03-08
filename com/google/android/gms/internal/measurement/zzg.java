package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzg extends zzc implements zzd {
    public static zzd zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        if (iInterfaceQueryLocalInterface instanceof zzd) {
            return (zzd) iInterfaceQueryLocalInterface;
        }
        return new zzf(iBinder);
    }
}