package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzjk implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzis zza;
    private volatile boolean zzb;
    private volatile zzfc zzc;

    protected zzjk(zzis zzisVar) {
        this.zza = zzisVar;
    }

    public final void zza(Intent intent) {
        this.zza.zzd();
        Context contextZzn = this.zza.zzn();
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzr().zzx().zza("Connection attempt already in progress");
                return;
            }
            this.zza.zzr().zzx().zza("Using local app measurement service");
            this.zzb = true;
            connectionTracker.bindService(contextZzn, intent, this.zza.zza, 129);
        }
    }

    public final void zza() {
        if (this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzet zzevVar;
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.zzb = false;
                this.zza.zzr().zzf().zza("Service connected with null binder");
                return;
            }
            zzet zzetVar = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    if (iBinder != null) {
                        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                        if (iInterfaceQueryLocalInterface instanceof zzet) {
                            zzevVar = (zzet) iInterfaceQueryLocalInterface;
                        } else {
                            zzevVar = new zzev(iBinder);
                        }
                        zzetVar = zzevVar;
                    }
                    this.zza.zzr().zzx().zza("Bound to IMeasurementService interface");
                } else {
                    this.zza.zzr().zzf().zza("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException unused) {
                this.zza.zzr().zzf().zza("Service connect failed to get IMeasurementService");
            }
            if (zzetVar == null) {
                this.zzb = false;
                try {
                    ConnectionTracker.getInstance().unbindService(this.zza.zzn(), this.zza.zza);
                } catch (IllegalArgumentException unused2) {
                }
            } else {
                this.zza.zzq().zza(new zzjj(this, zzetVar));
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zza.zzr().zzw().zza("Service disconnected");
        this.zza.zzq().zza(new zzjm(this, componentName));
    }

    public final void zzb() {
        this.zza.zzd();
        Context contextZzn = this.zza.zzn();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzr().zzx().zza("Connection attempt already in progress");
                return;
            }
            if (this.zzc != null && (this.zzc.isConnecting() || this.zzc.isConnected())) {
                this.zza.zzr().zzx().zza("Already awaiting connection attempt");
                return;
            }
            this.zzc = new zzfc(contextZzn, Looper.getMainLooper(), this, this);
            this.zza.zzr().zzx().zza("Connecting to remote service");
            this.zzb = true;
            this.zzc.checkAvailabilityAndConnect();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                this.zza.zzq().zza(new zzjl(this, this.zzc.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zza.zzr().zzw().zza("Service connection suspended");
        this.zza.zzq().zza(new zzjo(this));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzfb zzfbVarZzd = this.zza.zzx.zzd();
        if (zzfbVarZzd != null) {
            zzfbVarZzd.zzi().zza("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.zza.zzq().zza(new zzjn(this));
    }

    static /* synthetic */ boolean zza(zzjk zzjkVar, boolean z) {
        zzjkVar.zzb = false;
        return false;
    }
}