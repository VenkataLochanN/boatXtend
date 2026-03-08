package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzdl;
import com.google.android.gms.internal.measurement.zzdm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdm<MessageType extends zzdm<MessageType, BuilderType>, BuilderType extends zzdl<MessageType, BuilderType>> implements zzgm {
    protected int zza = 0;

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final zzdw zzbh() {
        try {
            zzee zzeeVarZzc = zzdw.zzc(zzbm());
            zza(zzeeVarZzc.zzb());
            return zzeeVarZzc.zza();
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    public final byte[] zzbi() {
        try {
            byte[] bArr = new byte[zzbm()];
            zzel zzelVarZza = zzel.zza(bArr);
            zza(zzelVarZza);
            zzelVarZza.zzb();
            return bArr;
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "byte array".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    int zzbj() {
        throw new UnsupportedOperationException();
    }

    void zzc(int i) {
        throw new UnsupportedOperationException();
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzfh.zza(iterable);
        if (iterable instanceof zzfx) {
            List<?> listZzd = ((zzfx) iterable).zzd();
            zzfx zzfxVar = (zzfx) list;
            int size = list.size();
            for (Object obj : listZzd) {
                if (obj == null) {
                    int size2 = zzfxVar.size() - size;
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(size2);
                    sb.append(" is null.");
                    String string = sb.toString();
                    for (int size3 = zzfxVar.size() - 1; size3 >= size; size3--) {
                        zzfxVar.remove(size3);
                    }
                    throw new NullPointerException(string);
                }
                if (obj instanceof zzdw) {
                    zzfxVar.zza((zzdw) obj);
                } else {
                    zzfxVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzgy) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
        }
        int size4 = list.size();
        for (T t : iterable) {
            if (t == null) {
                int size5 = list.size() - size4;
                StringBuilder sb2 = new StringBuilder(37);
                sb2.append("Element at index ");
                sb2.append(size5);
                sb2.append(" is null.");
                String string2 = sb2.toString();
                for (int size6 = list.size() - 1; size6 >= size4; size6--) {
                    list.remove(size6);
                }
                throw new NullPointerException(string2);
            }
            list.add(t);
        }
    }
}