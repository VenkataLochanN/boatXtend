package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzia implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzhy zzb;

    zzia(zzhy zzhyVar) {
        this.zzb = zzhyVar;
        this.zza = this.zzb.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }
}