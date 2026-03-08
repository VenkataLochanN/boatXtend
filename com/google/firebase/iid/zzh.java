package com.google.firebase.iid;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzh implements Runnable {
    private final FirebaseInstanceId zza;

    zzh(FirebaseInstanceId firebaseInstanceId) {
        this.zza = firebaseInstanceId;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzi();
    }
}