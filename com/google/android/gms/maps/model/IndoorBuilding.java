package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class IndoorBuilding {
    private final com.google.android.gms.internal.maps.zzn zzdc;
    private final zza zzdd;

    static class zza {
        public static final zza zzde = new zza();

        private zza() {
        }

        public static com.google.android.gms.internal.maps.zzq zza(IBinder iBinder) {
            return com.google.android.gms.internal.maps.zzr.zzf(iBinder);
        }

        public static IndoorLevel zza(com.google.android.gms.internal.maps.zzq zzqVar) {
            return new IndoorLevel(zzqVar);
        }
    }

    public IndoorBuilding(com.google.android.gms.internal.maps.zzn zznVar) {
        this(zznVar, zza.zzde);
    }

    private IndoorBuilding(com.google.android.gms.internal.maps.zzn zznVar, zza zzaVar) {
        this.zzdc = (com.google.android.gms.internal.maps.zzn) Preconditions.checkNotNull(zznVar, "delegate");
        this.zzdd = (zza) Preconditions.checkNotNull(zzaVar, "shim");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.zzdc.zzb(((IndoorBuilding) obj).zzdc);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final int getActiveLevelIndex() {
        try {
            return this.zzdc.getActiveLevelIndex();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final int getDefaultLevelIndex() {
        try {
            return this.zzdc.getDefaultLevelIndex();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final List<IndoorLevel> getLevels() {
        try {
            List<IBinder> levels = this.zzdc.getLevels();
            ArrayList arrayList = new ArrayList(levels.size());
            Iterator<IBinder> it = levels.iterator();
            while (it.hasNext()) {
                arrayList.add(zza.zza(zza.zza(it.next())));
            }
            return arrayList;
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdc.zzi();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final boolean isUnderground() {
        try {
            return this.zzdc.isUnderground();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}