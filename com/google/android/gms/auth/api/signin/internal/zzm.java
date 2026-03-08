package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
final class zzm extends zzd {
    private final /* synthetic */ zzl zzey;

    zzm(zzl zzlVar) {
        this.zzey = zzlVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzd, com.google.android.gms.auth.api.signin.internal.zzt
    public final void zzg(Status status) throws RemoteException {
        this.zzey.setResult(status);
    }
}