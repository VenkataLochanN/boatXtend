package com.google.firebase.iid;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzay implements OnCompleteListener {
    private final zzbd zza;

    zzay(zzbd zzbdVar) {
        this.zza = zzbdVar;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        this.zza.zza();
    }
}