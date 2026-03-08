package com.google.android.gms.internal.measurement;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzea implements zzec {
    private zzea() {
    }

    @Override // com.google.android.gms.internal.measurement.zzec
    public final byte[] zza(byte[] bArr, int i, int i2) {
        return Arrays.copyOfRange(bArr, i, i2 + i);
    }

    /* synthetic */ zzea(zzdv zzdvVar) {
        this();
    }
}