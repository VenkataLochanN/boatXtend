package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzcv implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final zzcs zza;

    zzcv(zzcs zzcsVar) {
        this.zza = zzcsVar;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}