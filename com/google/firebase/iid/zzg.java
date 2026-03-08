package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzg implements Continuation {
    private final FirebaseInstanceId zza;
    private final String zzb;
    private final String zzc;

    zzg(FirebaseInstanceId firebaseInstanceId, String str, String str2) {
        this.zza = firebaseInstanceId;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return this.zza.zza(this.zzb, this.zzc, task);
    }
}