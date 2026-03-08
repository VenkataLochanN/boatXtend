package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* JADX INFO: loaded from: classes.dex */
public final class zzq {
    private static zzq zzfa;
    private Storage zzfb;
    private GoogleSignInAccount zzfc;
    private GoogleSignInOptions zzfd;

    private zzq(Context context) {
        this.zzfb = Storage.getInstance(context);
        this.zzfc = this.zzfb.getSavedDefaultGoogleSignInAccount();
        this.zzfd = this.zzfb.getSavedDefaultGoogleSignInOptions();
    }

    public static synchronized zzq zze(Context context) {
        return zzf(context.getApplicationContext());
    }

    private static synchronized zzq zzf(Context context) {
        if (zzfa == null) {
            zzfa = new zzq(context);
        }
        return zzfa;
    }

    public final synchronized void clear() {
        this.zzfb.clear();
        this.zzfc = null;
        this.zzfd = null;
    }

    public final synchronized void zzd(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.zzfb.saveDefaultGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
        this.zzfc = googleSignInAccount;
        this.zzfd = googleSignInOptions;
    }

    public final synchronized GoogleSignInAccount zzo() {
        return this.zzfc;
    }

    public final synchronized GoogleSignInOptions zzp() {
        return this.zzfd;
    }
}