package com.google.firebase.iid;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzz implements Handler.Callback {
    private final zzw zza;

    zzz(zzw zzwVar) {
        this.zza = zzwVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        return this.zza.zza(message);
    }
}