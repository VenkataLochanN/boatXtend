package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzdl;
import com.google.android.gms.internal.measurement.zzdm;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdl<MessageType extends zzdm<MessageType, BuilderType>, BuilderType extends zzdl<MessageType, BuilderType>> implements zzgp {
    protected abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzei zzeiVar, zzer zzerVar) throws IOException;

    @Override // 
    /* JADX INFO: renamed from: zzp, reason: merged with bridge method [inline-methods] */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i, int i2) throws zzfm {
        try {
            zzei zzeiVarZza = zzei.zza(bArr, 0, i2, false);
            zza(zzeiVarZza, zzer.zza());
            zzeiVarZza.zza(0);
            return this;
        } catch (zzfm e2) {
            throw e2;
        } catch (IOException e3) {
            throw new RuntimeException(zza("byte array"), e3);
        }
    }

    public BuilderType zza(byte[] bArr, int i, int i2, zzer zzerVar) throws zzfm {
        try {
            zzei zzeiVarZza = zzei.zza(bArr, 0, i2, false);
            zza(zzeiVarZza, zzerVar);
            zzeiVarZza.zza(0);
            return this;
        } catch (zzfm e2) {
            throw e2;
        } catch (IOException e3) {
            throw new RuntimeException(zza("byte array"), e3);
        }
    }

    private final String zza(String str) {
        String name = getClass().getName();
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 60 + String.valueOf(str).length());
        sb.append("Reading ");
        sb.append(name);
        sb.append(" from a ");
        sb.append(str);
        sb.append(" threw an IOException (should never happen).");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzgp
    public final /* synthetic */ zzgp zza(zzgm zzgmVar) {
        if (!h_().getClass().isInstance(zzgmVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zza((zzdm) zzgmVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzgp
    public final /* synthetic */ zzgp zza(byte[] bArr, zzer zzerVar) throws zzfm {
        return zza(bArr, 0, bArr.length, zzerVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzgp
    public final /* synthetic */ zzgp zza(byte[] bArr) throws zzfm {
        return zza(bArr, 0, bArr.length);
    }
}