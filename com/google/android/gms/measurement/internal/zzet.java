package com.google.android.gms.measurement.internal;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzet extends IInterface {
    List<zzkq> zza(zzm zzmVar, boolean z) throws RemoteException;

    List<zzv> zza(String str, String str2, zzm zzmVar) throws RemoteException;

    List<zzv> zza(String str, String str2, String str3) throws RemoteException;

    List<zzkq> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzkq> zza(String str, String str2, boolean z, zzm zzmVar) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(zzan zzanVar, zzm zzmVar) throws RemoteException;

    void zza(zzan zzanVar, String str, String str2) throws RemoteException;

    void zza(zzkq zzkqVar, zzm zzmVar) throws RemoteException;

    void zza(zzm zzmVar) throws RemoteException;

    void zza(zzv zzvVar) throws RemoteException;

    void zza(zzv zzvVar, zzm zzmVar) throws RemoteException;

    byte[] zza(zzan zzanVar, String str) throws RemoteException;

    void zzb(zzm zzmVar) throws RemoteException;

    String zzc(zzm zzmVar) throws RemoteException;

    void zzd(zzm zzmVar) throws RemoteException;
}