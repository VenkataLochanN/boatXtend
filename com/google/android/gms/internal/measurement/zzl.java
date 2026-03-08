package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzl extends zzc implements zzm {
    public zzl() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzm asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        if (iInterfaceQueryLocalInterface instanceof zzm) {
            return (zzm) iInterfaceQueryLocalInterface;
        }
        return new zzo(iBinder);
    }

    @Override // com.google.android.gms.internal.measurement.zzc
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzn zzpVar;
        zzn zznVar;
        zzn zzpVar2 = null;
        zzn zzpVar3 = null;
        zzn zzpVar4 = null;
        zzs zzuVar = null;
        zzs zzuVar2 = null;
        zzs zzuVar3 = null;
        zzn zzpVar5 = null;
        zzn zzpVar6 = null;
        zzn zzpVar7 = null;
        zzn zzpVar8 = null;
        zzn zzpVar9 = null;
        zzn zzpVar10 = null;
        zzt zzwVar = null;
        zzn zzpVar11 = null;
        zzn zzpVar12 = null;
        zzn zzpVar13 = null;
        zzn zzpVar14 = null;
        switch (i) {
            case 1:
                initialize(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzv) zzb.zza(parcel, zzv.CREATOR), parcel.readLong());
                break;
            case 2:
                logEvent(parcel.readString(), parcel.readString(), (Bundle) zzb.zza(parcel, Bundle.CREATOR), zzb.zza(parcel), zzb.zza(parcel), parcel.readLong());
                break;
            case 3:
                String string = parcel.readString();
                String string2 = parcel.readString();
                Bundle bundle = (Bundle) zzb.zza(parcel, Bundle.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zznVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface instanceof zzn) {
                        zzpVar = (zzn) iInterfaceQueryLocalInterface;
                    } else {
                        zzpVar = new zzp(strongBinder);
                    }
                    zznVar = zzpVar;
                }
                logEventAndBundle(string, string2, bundle, zznVar, parcel.readLong());
                break;
            case 4:
                setUserProperty(parcel.readString(), parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzb.zza(parcel), parcel.readLong());
                break;
            case 5:
                String string3 = parcel.readString();
                String string4 = parcel.readString();
                boolean zZza = zzb.zza(parcel);
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface2 instanceof zzn) {
                        zzpVar2 = (zzn) iInterfaceQueryLocalInterface2;
                    } else {
                        zzpVar2 = new zzp(strongBinder2);
                    }
                }
                getUserProperties(string3, string4, zZza, zzpVar2);
                break;
            case 6:
                String string5 = parcel.readString();
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface3 instanceof zzn) {
                        zzpVar14 = (zzn) iInterfaceQueryLocalInterface3;
                    } else {
                        zzpVar14 = new zzp(strongBinder3);
                    }
                }
                getMaxUserProperties(string5, zzpVar14);
                break;
            case 7:
                setUserId(parcel.readString(), parcel.readLong());
                break;
            case 8:
                setConditionalUserProperty((Bundle) zzb.zza(parcel, Bundle.CREATOR), parcel.readLong());
                break;
            case 9:
                clearConditionalUserProperty(parcel.readString(), parcel.readString(), (Bundle) zzb.zza(parcel, Bundle.CREATOR));
                break;
            case 10:
                String string6 = parcel.readString();
                String string7 = parcel.readString();
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface4 instanceof zzn) {
                        zzpVar13 = (zzn) iInterfaceQueryLocalInterface4;
                    } else {
                        zzpVar13 = new zzp(strongBinder4);
                    }
                }
                getConditionalUserProperties(string6, string7, zzpVar13);
                break;
            case 11:
                setMeasurementEnabled(zzb.zza(parcel), parcel.readLong());
                break;
            case 12:
                resetAnalyticsData(parcel.readLong());
                break;
            case 13:
                setMinimumSessionDuration(parcel.readLong());
                break;
            case 14:
                setSessionTimeoutDuration(parcel.readLong());
                break;
            case 15:
                setCurrentScreen(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readLong());
                break;
            case 16:
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 != null) {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface5 instanceof zzn) {
                        zzpVar12 = (zzn) iInterfaceQueryLocalInterface5;
                    } else {
                        zzpVar12 = new zzp(strongBinder5);
                    }
                }
                getCurrentScreenName(zzpVar12);
                break;
            case 17:
                IBinder strongBinder6 = parcel.readStrongBinder();
                if (strongBinder6 != null) {
                    IInterface iInterfaceQueryLocalInterface6 = strongBinder6.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface6 instanceof zzn) {
                        zzpVar11 = (zzn) iInterfaceQueryLocalInterface6;
                    } else {
                        zzpVar11 = new zzp(strongBinder6);
                    }
                }
                getCurrentScreenClass(zzpVar11);
                break;
            case 18:
                IBinder strongBinder7 = parcel.readStrongBinder();
                if (strongBinder7 != null) {
                    IInterface iInterfaceQueryLocalInterface7 = strongBinder7.queryLocalInterface("com.google.android.gms.measurement.api.internal.IStringProvider");
                    if (iInterfaceQueryLocalInterface7 instanceof zzt) {
                        zzwVar = (zzt) iInterfaceQueryLocalInterface7;
                    } else {
                        zzwVar = new zzw(strongBinder7);
                    }
                }
                setInstanceIdProvider(zzwVar);
                break;
            case 19:
                IBinder strongBinder8 = parcel.readStrongBinder();
                if (strongBinder8 != null) {
                    IInterface iInterfaceQueryLocalInterface8 = strongBinder8.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface8 instanceof zzn) {
                        zzpVar10 = (zzn) iInterfaceQueryLocalInterface8;
                    } else {
                        zzpVar10 = new zzp(strongBinder8);
                    }
                }
                getCachedAppInstanceId(zzpVar10);
                break;
            case 20:
                IBinder strongBinder9 = parcel.readStrongBinder();
                if (strongBinder9 != null) {
                    IInterface iInterfaceQueryLocalInterface9 = strongBinder9.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface9 instanceof zzn) {
                        zzpVar9 = (zzn) iInterfaceQueryLocalInterface9;
                    } else {
                        zzpVar9 = new zzp(strongBinder9);
                    }
                }
                getAppInstanceId(zzpVar9);
                break;
            case 21:
                IBinder strongBinder10 = parcel.readStrongBinder();
                if (strongBinder10 != null) {
                    IInterface iInterfaceQueryLocalInterface10 = strongBinder10.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface10 instanceof zzn) {
                        zzpVar8 = (zzn) iInterfaceQueryLocalInterface10;
                    } else {
                        zzpVar8 = new zzp(strongBinder10);
                    }
                }
                getGmpAppId(zzpVar8);
                break;
            case 22:
                IBinder strongBinder11 = parcel.readStrongBinder();
                if (strongBinder11 != null) {
                    IInterface iInterfaceQueryLocalInterface11 = strongBinder11.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface11 instanceof zzn) {
                        zzpVar7 = (zzn) iInterfaceQueryLocalInterface11;
                    } else {
                        zzpVar7 = new zzp(strongBinder11);
                    }
                }
                generateEventId(zzpVar7);
                break;
            case 23:
                beginAdUnitExposure(parcel.readString(), parcel.readLong());
                break;
            case 24:
                endAdUnitExposure(parcel.readString(), parcel.readLong());
                break;
            case 25:
                onActivityStarted(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 26:
                onActivityStopped(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 27:
                onActivityCreated(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (Bundle) zzb.zza(parcel, Bundle.CREATOR), parcel.readLong());
                break;
            case 28:
                onActivityDestroyed(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 29:
                onActivityPaused(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 30:
                onActivityResumed(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                break;
            case 31:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IBinder strongBinder12 = parcel.readStrongBinder();
                if (strongBinder12 != null) {
                    IInterface iInterfaceQueryLocalInterface12 = strongBinder12.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface12 instanceof zzn) {
                        zzpVar6 = (zzn) iInterfaceQueryLocalInterface12;
                    } else {
                        zzpVar6 = new zzp(strongBinder12);
                    }
                }
                onActivitySaveInstanceState(iObjectWrapperAsInterface, zzpVar6, parcel.readLong());
                break;
            case 32:
                Bundle bundle2 = (Bundle) zzb.zza(parcel, Bundle.CREATOR);
                IBinder strongBinder13 = parcel.readStrongBinder();
                if (strongBinder13 != null) {
                    IInterface iInterfaceQueryLocalInterface13 = strongBinder13.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface13 instanceof zzn) {
                        zzpVar5 = (zzn) iInterfaceQueryLocalInterface13;
                    } else {
                        zzpVar5 = new zzp(strongBinder13);
                    }
                }
                performAction(bundle2, zzpVar5, parcel.readLong());
                break;
            case 33:
                logHealthData(parcel.readInt(), parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 34:
                IBinder strongBinder14 = parcel.readStrongBinder();
                if (strongBinder14 != null) {
                    IInterface iInterfaceQueryLocalInterface14 = strongBinder14.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    if (iInterfaceQueryLocalInterface14 instanceof zzs) {
                        zzuVar3 = (zzs) iInterfaceQueryLocalInterface14;
                    } else {
                        zzuVar3 = new zzu(strongBinder14);
                    }
                }
                setEventInterceptor(zzuVar3);
                break;
            case 35:
                IBinder strongBinder15 = parcel.readStrongBinder();
                if (strongBinder15 != null) {
                    IInterface iInterfaceQueryLocalInterface15 = strongBinder15.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    if (iInterfaceQueryLocalInterface15 instanceof zzs) {
                        zzuVar2 = (zzs) iInterfaceQueryLocalInterface15;
                    } else {
                        zzuVar2 = new zzu(strongBinder15);
                    }
                }
                registerOnMeasurementEventListener(zzuVar2);
                break;
            case 36:
                IBinder strongBinder16 = parcel.readStrongBinder();
                if (strongBinder16 != null) {
                    IInterface iInterfaceQueryLocalInterface16 = strongBinder16.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    if (iInterfaceQueryLocalInterface16 instanceof zzs) {
                        zzuVar = (zzs) iInterfaceQueryLocalInterface16;
                    } else {
                        zzuVar = new zzu(strongBinder16);
                    }
                }
                unregisterOnMeasurementEventListener(zzuVar);
                break;
            case 37:
                initForTests(zzb.zzb(parcel));
                break;
            case 38:
                IBinder strongBinder17 = parcel.readStrongBinder();
                if (strongBinder17 != null) {
                    IInterface iInterfaceQueryLocalInterface17 = strongBinder17.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface17 instanceof zzn) {
                        zzpVar4 = (zzn) iInterfaceQueryLocalInterface17;
                    } else {
                        zzpVar4 = new zzp(strongBinder17);
                    }
                }
                getTestFlag(zzpVar4, parcel.readInt());
                break;
            case 39:
                setDataCollectionEnabled(zzb.zza(parcel));
                break;
            case 40:
                IBinder strongBinder18 = parcel.readStrongBinder();
                if (strongBinder18 != null) {
                    IInterface iInterfaceQueryLocalInterface18 = strongBinder18.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    if (iInterfaceQueryLocalInterface18 instanceof zzn) {
                        zzpVar3 = (zzn) iInterfaceQueryLocalInterface18;
                    } else {
                        zzpVar3 = new zzp(strongBinder18);
                    }
                }
                isDataCollectionEnabled(zzpVar3);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}