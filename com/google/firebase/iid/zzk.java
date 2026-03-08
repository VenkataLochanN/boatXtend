package com.google.firebase.iid;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.iid.FirebaseInstanceId;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzk implements EventHandler {
    private final FirebaseInstanceId.zza zza;

    zzk(FirebaseInstanceId.zza zzaVar) {
        this.zza = zzaVar;
    }

    @Override // com.google.firebase.events.EventHandler
    public final void handle(Event event) {
        FirebaseInstanceId.zza zzaVar = this.zza;
        synchronized (zzaVar) {
            if (zzaVar.zza()) {
                FirebaseInstanceId.this.zzj();
            }
        }
    }
}