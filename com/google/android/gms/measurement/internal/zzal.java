package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzal implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzam zzb;

    zzal(zzam zzamVar) {
        this.zzb = zzamVar;
        this.zza = this.zzb.zza.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }
}