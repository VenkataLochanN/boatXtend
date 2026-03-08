package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzhl extends zzhr {
    private final /* synthetic */ zzhg zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzhl(zzhg zzhgVar) {
        super(zzhgVar, null);
        this.zza = zzhgVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzhr, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzhi(this.zza, null);
    }

    /* synthetic */ zzhl(zzhg zzhgVar, zzhj zzhjVar) {
        this(zzhgVar);
    }
}