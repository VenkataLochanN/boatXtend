package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzfe.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzfe<MessageType extends zzfe<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdm<MessageType, BuilderType> {
    private static Map<Object, zzfe<?, ?>> zzd = new ConcurrentHashMap();
    protected zzhw zzb = zzhw.zza();
    private int zzc = -1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    public static class zzc<T extends zzfe<T, ?>> extends zzdn<T> {
        private final T zza;

        public zzc(T t) {
            this.zza = t;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    public static class zzd<ContainingType extends zzgm, Type> extends zzep<ContainingType, Type> {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    static final class zze implements zzew<zze> {
        @Override // com.google.android.gms.internal.measurement.zzew
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzew
        public final zzik zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzew
        public final zzir zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzew
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzew
        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzew
        public final zzgp zza(zzgp zzgpVar, zzgm zzgmVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzew
        public final zzgv zza(zzgv zzgvVar, zzgv zzgvVar2) {
            throw new NoSuchMethodError();
        }

        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    public enum zzf {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzl = {zza, zzb, zzc, zzd, zze, zzf, zzg};
        public static final int zzh = 1;
        public static final int zzi = 2;
        private static final /* synthetic */ int[] zzm = {zzh, zzi};
        public static final int zzj = 1;
        public static final int zzk = 2;
        private static final /* synthetic */ int[] zzn = {zzj, zzk};

        public static int[] zza() {
            return (int[]) zzl.clone();
        }
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzfe<MessageType, BuilderType> implements zzgo {
        protected zzeu<zze> zzc = zzeu.zza();

        final zzeu<zze> zza() {
            if (this.zzc.zzc()) {
                this.zzc = (zzeu) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    public String toString() {
        return zzgr.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zza != 0) {
            return this.zza;
        }
        this.zza = zzhb.zza().zza(this).zza(this);
        return this.zza;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    public static abstract class zza<MessageType extends zzfe<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdl<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb = false;
        private final MessageType zzc;

        protected zza(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (MessageType) messagetype.zza(zzf.zzd, null, null);
        }

        protected void zzq() {
            MessageType messagetype = (MessageType) this.zza.zza(zzf.zzd, null, null);
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        @Override // com.google.android.gms.internal.measurement.zzgo
        public final boolean g_() {
            return zzfe.zza(this.zza, false);
        }

        @Override // com.google.android.gms.internal.measurement.zzgp
        /* JADX INFO: renamed from: zzs, reason: merged with bridge method [inline-methods] */
        public MessageType zzu() {
            if (this.zzb) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzhb.zza().zza(messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        @Override // com.google.android.gms.internal.measurement.zzgp
        /* JADX INFO: renamed from: zzt, reason: merged with bridge method [inline-methods] */
        public final MessageType zzv() {
            MessageType messagetype = (MessageType) zzu();
            if (messagetype.g_()) {
                return messagetype;
            }
            throw new zzhu(messagetype);
        }

        @Override // com.google.android.gms.internal.measurement.zzdl
        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            zza(this.zza, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzhb.zza().zza(messagetype).zzb(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzer zzerVar) throws zzfm {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            try {
                zzhb.zza().zza(this.zza).zza(this.zza, bArr, 0, i2 + 0, new zzdr(zzerVar));
                return this;
            } catch (zzfm e2) {
                throw e2;
            } catch (IOException e3) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e3);
            } catch (IndexOutOfBoundsException unused) {
                throw zzfm.zza();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.internal.measurement.zzdl
        /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
        public final BuilderType zza(zzei zzeiVar, zzer zzerVar) throws IOException {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            try {
                zzhb.zza().zza(this.zza).zza(this.zza, zzej.zza(zzeiVar), zzerVar);
                return this;
            } catch (RuntimeException e2) {
                if (e2.getCause() instanceof IOException) {
                    throw ((IOException) e2.getCause());
                }
                throw e2;
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzdl
        public final /* synthetic */ zzdl zza(byte[] bArr, int i, int i2, zzer zzerVar) throws zzfm {
            return zzb(bArr, 0, i2, zzerVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzdl
        public final /* synthetic */ zzdl zza(byte[] bArr, int i, int i2) throws zzfm {
            return zzb(bArr, 0, i2, zzer.zza());
        }

        @Override // com.google.android.gms.internal.measurement.zzdl
        /* JADX INFO: renamed from: zzp */
        public final /* synthetic */ zzdl clone() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.measurement.zzgo
        public final /* synthetic */ zzgm h_() {
            return this.zzc;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.measurement.zzdl
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzc.zza(zzf.zze, null, null);
            zzaVar.zza((zzfe) zzu());
            return zzaVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzhb.zza().zza(this).zza(this, (zzfe) obj);
        }
        return false;
    }

    protected final <MessageType extends zzfe<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzbk() {
        return (BuilderType) zza(zzf.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzgo
    public final boolean g_() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    public final BuilderType zzbl() {
        BuilderType buildertype = (BuilderType) zza(zzf.zze, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    @Override // com.google.android.gms.internal.measurement.zzdm
    final int zzbj() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzdm
    final void zzc(int i) {
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final void zza(zzel zzelVar) throws IOException {
        zzhb.zza().zza(this).zza(this, zzeo.zza(zzelVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final int zzbm() {
        if (this.zzc == -1) {
            this.zzc = zzhb.zza().zza(this).zzb(this);
        }
        return this.zzc;
    }

    static <T extends zzfe<?, ?>> T zza(Class<T> cls) {
        zzfe<?, ?> zzfeVar = zzd.get(cls);
        if (zzfeVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzfeVar = zzd.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (zzfeVar == null) {
            zzfeVar = (T) ((zzfe) zzid.zza(cls)).zza(zzf.zzf, (Object) null, (Object) null);
            if (zzfeVar == null) {
                throw new IllegalStateException();
            }
            zzd.put(cls, zzfeVar);
        }
        return (T) zzfeVar;
    }

    protected static <T extends zzfe<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    protected static Object zza(zzgm zzgmVar, String str, Object[] objArr) {
        return new zzhd(zzgmVar, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static final <T extends zzfe<T, ?>> boolean zza(T t, boolean z) {
        byte bByteValue = ((Byte) t.zza(zzf.zza, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzd = zzhb.zza().zza(t).zzd(t);
        if (z) {
            t.zza(zzf.zzb, zZzd ? t : null, null);
        }
        return zZzd;
    }

    protected static zzfl zzbn() {
        return zzff.zzd();
    }

    protected static zzfk zzbo() {
        return zzga.zzd();
    }

    protected static zzfk zza(zzfk zzfkVar) {
        int size = zzfkVar.size();
        return zzfkVar.zza(size == 0 ? 10 : size << 1);
    }

    protected static <E> zzfn<E> zzbp() {
        return zzha.zzd();
    }

    protected static <E> zzfn<E> zza(zzfn<E> zzfnVar) {
        int size = zzfnVar.size();
        return zzfnVar.zza(size == 0 ? 10 : size << 1);
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final /* synthetic */ zzgp zzbq() {
        zza zzaVar = (zza) zza(zzf.zze, (Object) null, (Object) null);
        zzaVar.zza(this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzgm
    public final /* synthetic */ zzgp zzbr() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzgo
    public final /* synthetic */ zzgm h_() {
        return (zzfe) zza(zzf.zzf, (Object) null, (Object) null);
    }
}