package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzfu extends zzdq<String> implements zzfx, RandomAccess {
    private static final zzfu zza;
    private static final zzfx zzb;
    private final List<Object> zzc;

    public zzfu() {
        this(10);
    }

    public zzfu(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzfu(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzc();
        if (collection instanceof zzfx) {
            collection = ((zzfx) collection).zzd();
        }
        boolean zAddAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzc();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzfx
    public final void zza(zzdw zzdwVar) {
        zzc();
        this.zzc.add(zzdwVar);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzfx
    public final Object zzb(int i) {
        return this.zzc.get(i);
    }

    private static String zza(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdw) {
            return ((zzdw) obj).zzb();
        }
        return zzfh.zzb((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzfx
    public final List<?> zzd() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.measurement.zzfx
    public final zzfx zze() {
        return zza() ? new zzhy(this) : this;
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        zzc();
        return zza(this.zzc.set(i, (String) obj));
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzc();
        Object objRemove = this.zzc.remove(i);
        this.modCount++;
        return zza(objRemove);
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, com.google.android.gms.internal.measurement.zzfn
    public final /* bridge */ /* synthetic */ boolean zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzc();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.measurement.zzdq, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzfn
    public final /* synthetic */ zzfn zza(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzfu((ArrayList<Object>) arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdw) {
            zzdw zzdwVar = (zzdw) obj;
            String strZzb = zzdwVar.zzb();
            if (zzdwVar.zzc()) {
                this.zzc.set(i, strZzb);
            }
            return strZzb;
        }
        byte[] bArr = (byte[]) obj;
        String strZzb2 = zzfh.zzb(bArr);
        if (zzfh.zza(bArr)) {
            this.zzc.set(i, strZzb2);
        }
        return strZzb2;
    }

    static {
        zzfu zzfuVar = new zzfu();
        zza = zzfuVar;
        zzfuVar.zzb();
        zzb = zza;
    }
}