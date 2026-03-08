package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzo extends zza implements zzm {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void initialize(IObjectWrapper iObjectWrapper, zzv zzvVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        zzb.zza(parcelA_, zzvVar);
        parcelA_.writeLong(j);
        zzb(1, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb.zza(parcelA_, bundle);
        zzb.zza(parcelA_, z);
        zzb.zza(parcelA_, z2);
        parcelA_.writeLong(j);
        zzb(2, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzn zznVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb.zza(parcelA_, bundle);
        zzb.zza(parcelA_, zznVar);
        parcelA_.writeLong(j);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb.zza(parcelA_, iObjectWrapper);
        zzb.zza(parcelA_, z);
        parcelA_.writeLong(j);
        zzb(4, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getUserProperties(String str, String str2, boolean z, zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb.zza(parcelA_, z);
        zzb.zza(parcelA_, zznVar);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getMaxUserProperties(String str, zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzb.zza(parcelA_, zznVar);
        zzb(6, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setUserId(String str, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeLong(j);
        zzb(7, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, bundle);
        parcelA_.writeLong(j);
        zzb(8, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb.zza(parcelA_, bundle);
        zzb(9, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getConditionalUserProperties(String str, String str2, zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzb.zza(parcelA_, zznVar);
        zzb(10, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, z);
        parcelA_.writeLong(j);
        zzb(11, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void resetAnalyticsData(long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeLong(j);
        zzb(12, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setMinimumSessionDuration(long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeLong(j);
        zzb(13, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setSessionTimeoutDuration(long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeLong(j);
        zzb(14, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        parcelA_.writeLong(j);
        zzb(15, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getCurrentScreenName(zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        zzb(16, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getCurrentScreenClass(zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        zzb(17, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setInstanceIdProvider(zzt zztVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zztVar);
        zzb(18, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getCachedAppInstanceId(zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        zzb(19, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getAppInstanceId(zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        zzb(20, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getGmpAppId(zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        zzb(21, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void generateEventId(zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        zzb(22, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void beginAdUnitExposure(String str, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeLong(j);
        zzb(23, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void endAdUnitExposure(String str, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeLong(j);
        zzb(24, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(25, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(26, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        zzb.zza(parcelA_, bundle);
        parcelA_.writeLong(j);
        zzb(27, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(28, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(29, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(30, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzn zznVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, iObjectWrapper);
        zzb.zza(parcelA_, zznVar);
        parcelA_.writeLong(j);
        zzb(31, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void performAction(Bundle bundle, zzn zznVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, bundle);
        zzb.zza(parcelA_, zznVar);
        parcelA_.writeLong(j);
        zzb(32, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        parcelA_.writeString(str);
        zzb.zza(parcelA_, iObjectWrapper);
        zzb.zza(parcelA_, iObjectWrapper2);
        zzb.zza(parcelA_, iObjectWrapper3);
        zzb(33, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setEventInterceptor(zzs zzsVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zzsVar);
        zzb(34, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void registerOnMeasurementEventListener(zzs zzsVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zzsVar);
        zzb(35, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void unregisterOnMeasurementEventListener(zzs zzsVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zzsVar);
        zzb(36, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void initForTests(Map map) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeMap(map);
        zzb(37, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void getTestFlag(zzn zznVar, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        parcelA_.writeInt(i);
        zzb(38, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, z);
        zzb(39, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzm
    public final void isDataCollectionEnabled(zzn zznVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzb.zza(parcelA_, zznVar);
        zzb(40, parcelA_);
    }
}