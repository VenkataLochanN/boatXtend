package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.zzi;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: classes.dex */
public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions> {
    private static final zzd zzef = new zzd(null);
    private static int zzeg = zze.zzei;

    private static class zzd implements PendingResultUtil.ResultConverter<GoogleSignInResult, GoogleSignInAccount> {
        private zzd() {
        }

        /* synthetic */ zzd(com.google.android.gms.auth.api.signin.zzd zzdVar) {
            this();
        }

        @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
        public final /* synthetic */ GoogleSignInAccount convert(Result result) {
            return ((GoogleSignInResult) result).getSignInAccount();
        }
    }

    enum zze {
        public static final int zzei = 1;
        public static final int zzej = 2;
        public static final int zzek = 3;
        public static final int zzel = 4;
        private static final /* synthetic */ int[] zzem = {zzei, zzej, zzek, zzel};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UPRDECNM2TBKD0NM2S395TPMIPRED5N2UHRFDTJMOPAJD5JMSIBE8DM6IPBEEGI4IRBGDHIMQPBEEHGN8QBFDOTG____0() {
            return (int[]) zzem.clone();
        }
    }

    GoogleSignInClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        super(activity, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    GoogleSignInClient(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new ApiExceptionMapper());
    }

    private final synchronized int zzl() {
        if (zzeg == zze.zzei) {
            Context applicationContext = getApplicationContext();
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(applicationContext, 12451000);
            zzeg = iIsGooglePlayServicesAvailable == 0 ? zze.zzel : (googleApiAvailability.getErrorResolutionIntent(applicationContext, iIsGooglePlayServicesAvailable, null) != null || DynamiteModule.getLocalVersion(applicationContext, "com.google.android.gms.auth.api.fallback") == 0) ? zze.zzej : zze.zzek;
        }
        return zzeg;
    }

    public Intent getSignInIntent() {
        Context applicationContext = getApplicationContext();
        int i = com.google.android.gms.auth.api.signin.zzd.zzeh[zzl() - 1];
        return i != 1 ? i != 2 ? zzi.zzf(applicationContext, getApiOptions()) : zzi.zzd(applicationContext, getApiOptions()) : zzi.zze(applicationContext, getApiOptions());
    }

    public Task<Void> revokeAccess() {
        return PendingResultUtil.toVoidTask(zzi.zze(asGoogleApiClient(), getApplicationContext(), zzl() == zze.zzek));
    }

    public Task<Void> signOut() {
        return PendingResultUtil.toVoidTask(zzi.zzd(asGoogleApiClient(), getApplicationContext(), zzl() == zze.zzek));
    }

    public Task<GoogleSignInAccount> silentSignIn() {
        return PendingResultUtil.toTask(zzi.zzd(asGoogleApiClient(), getApplicationContext(), getApiOptions(), zzl() == zze.zzek), zzef);
    }
}