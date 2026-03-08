package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzgd implements zzhe {
    private static final zzgn zzb = new zzgc();
    private final zzgn zza;

    public zzgd() {
        this(new zzgf(zzfc.zza(), zza()));
    }

    private zzgd(zzgn zzgnVar) {
        this.zza = (zzgn) zzfh.zza(zzgnVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.measurement.zzhe
    public final <T> zzhf<T> zza(Class<T> cls) {
        zzhh.zza((Class<?>) cls);
        zzgk zzgkVarZzb = this.zza.zzb(cls);
        if (zzgkVarZzb.zzb()) {
            if (zzfe.class.isAssignableFrom(cls)) {
                return zzgs.zza(zzhh.zzc(), zzev.zza(), zzgkVarZzb.zzc());
            }
            return zzgs.zza(zzhh.zza(), zzev.zzb(), zzgkVarZzb.zzc());
        }
        if (zzfe.class.isAssignableFrom(cls)) {
            if (zza(zzgkVarZzb)) {
                return zzgq.zza(cls, zzgkVarZzb, zzgw.zzb(), zzfw.zzb(), zzhh.zzc(), zzev.zza(), zzgl.zzb());
            }
            return zzgq.zza(cls, zzgkVarZzb, zzgw.zzb(), zzfw.zzb(), zzhh.zzc(), (zzet<?>) null, zzgl.zzb());
        }
        if (zza(zzgkVarZzb)) {
            return zzgq.zza(cls, zzgkVarZzb, zzgw.zza(), zzfw.zza(), zzhh.zza(), zzev.zzb(), zzgl.zza());
        }
        return zzgq.zza(cls, zzgkVarZzb, zzgw.zza(), zzfw.zza(), zzhh.zzb(), (zzet<?>) null, zzgl.zza());
    }

    private static boolean zza(zzgk zzgkVar) {
        return zzgkVar.zza() == zzfe.zzf.zzh;
    }

    private static zzgn zza() {
        try {
            return (zzgn) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzb;
        }
    }
}