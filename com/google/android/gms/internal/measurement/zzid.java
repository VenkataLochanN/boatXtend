package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzid {
    static final boolean zza;
    private static final Logger zzb = Logger.getLogger(zzid.class.getName());
    private static final Unsafe zzc = zzc();
    private static final Class<?> zzd = zzdp.zzb();
    private static final boolean zze = zzd(Long.TYPE);
    private static final boolean zzf = zzd(Integer.TYPE);
    private static final zzc zzg;
    private static final boolean zzh;
    private static final boolean zzi;
    private static final long zzj;
    private static final long zzk;
    private static final long zzl;
    private static final long zzm;
    private static final long zzn;
    private static final long zzo;
    private static final long zzp;
    private static final long zzq;
    private static final long zzr;
    private static final long zzs;
    private static final long zzt;
    private static final long zzu;
    private static final long zzv;
    private static final long zzw;
    private static final int zzx;

    private zzid() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    static final class zza extends zzc {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final byte zza(Object obj, long j) {
            if (zzid.zza) {
                return zzid.zzk(obj, j);
            }
            return zzid.zzl(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, byte b2) {
            if (zzid.zza) {
                zzid.zzc(obj, j, b2);
            } else {
                zzid.zzd(obj, j, b2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final boolean zzb(Object obj, long j) {
            if (zzid.zza) {
                return zzid.zzm(obj, j);
            }
            return zzid.zzn(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, boolean z) {
            if (zzid.zza) {
                zzid.zzd(obj, j, z);
            } else {
                zzid.zze(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final float zzc(Object obj, long j) {
            return Float.intBitsToFloat(zze(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, float f2) {
            zza(obj, j, Float.floatToIntBits(f2));
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final double zzd(Object obj, long j) {
            return Double.longBitsToDouble(zzf(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, double d2) {
            zza(obj, j, Double.doubleToLongBits(d2));
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    static final class zzb extends zzc {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final byte zza(Object obj, long j) {
            if (zzid.zza) {
                return zzid.zzk(obj, j);
            }
            return zzid.zzl(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, byte b2) {
            if (zzid.zza) {
                zzid.zzc(obj, j, b2);
            } else {
                zzid.zzd(obj, j, b2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final boolean zzb(Object obj, long j) {
            if (zzid.zza) {
                return zzid.zzm(obj, j);
            }
            return zzid.zzn(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, boolean z) {
            if (zzid.zza) {
                zzid.zzd(obj, j, z);
            } else {
                zzid.zze(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final float zzc(Object obj, long j) {
            return Float.intBitsToFloat(zze(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, float f2) {
            zza(obj, j, Float.floatToIntBits(f2));
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final double zzd(Object obj, long j) {
            return Double.longBitsToDouble(zzf(obj, j));
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, double d2) {
            zza(obj, j, Double.doubleToLongBits(d2));
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    static final class zzd extends zzc {
        zzd(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final byte zza(Object obj, long j) {
            return this.zza.getByte(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, byte b2) {
            this.zza.putByte(obj, j, b2);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final boolean zzb(Object obj, long j) {
            return this.zza.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, boolean z) {
            this.zza.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final float zzc(Object obj, long j) {
            return this.zza.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, float f2) {
            this.zza.putFloat(obj, j, f2);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final double zzd(Object obj, long j) {
            return this.zza.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzid.zzc
        public final void zza(Object obj, long j, double d2) {
            this.zza.putDouble(obj, j, d2);
        }
    }

    static boolean zza() {
        return zzi;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    static abstract class zzc {
        Unsafe zza;

        zzc(Unsafe unsafe) {
            this.zza = unsafe;
        }

        public abstract byte zza(Object obj, long j);

        public abstract void zza(Object obj, long j, byte b2);

        public abstract void zza(Object obj, long j, double d2);

        public abstract void zza(Object obj, long j, float f2);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract boolean zzb(Object obj, long j);

        public abstract float zzc(Object obj, long j);

        public abstract double zzd(Object obj, long j);

        public final int zze(Object obj, long j) {
            return this.zza.getInt(obj, j);
        }

        public final void zza(Object obj, long j, int i) {
            this.zza.putInt(obj, j, i);
        }

        public final long zzf(Object obj, long j) {
            return this.zza.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zza.putLong(obj, j, j2);
        }
    }

    static boolean zzb() {
        return zzh;
    }

    static <T> T zza(Class<T> cls) {
        try {
            return (T) zzc.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private static int zzb(Class<?> cls) {
        if (zzi) {
            return zzg.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzc(Class<?> cls) {
        if (zzi) {
            return zzg.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zza(Object obj, long j) {
        return zzg.zze(obj, j);
    }

    static void zza(Object obj, long j, int i) {
        zzg.zza(obj, j, i);
    }

    static long zzb(Object obj, long j) {
        return zzg.zzf(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzg.zza(obj, j, j2);
    }

    static boolean zzc(Object obj, long j) {
        return zzg.zzb(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzg.zza(obj, j, z);
    }

    static float zzd(Object obj, long j) {
        return zzg.zzc(obj, j);
    }

    static void zza(Object obj, long j, float f2) {
        zzg.zza(obj, j, f2);
    }

    static double zze(Object obj, long j) {
        return zzg.zzd(obj, j);
    }

    static void zza(Object obj, long j, double d2) {
        zzg.zza(obj, j, d2);
    }

    static Object zzf(Object obj, long j) {
        return zzg.zza.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzg.zza.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzg.zza(bArr, zzj + j);
    }

    static void zza(byte[] bArr, long j, byte b2) {
        zzg.zza((Object) bArr, zzj + j, b2);
    }

    static Unsafe zzc() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzic());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzd() {
        Unsafe unsafe = zzc;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzdp.zza()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger = zzb;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zze() {
        Unsafe unsafe = zzc;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzf() == null) {
                return false;
            }
            if (zzdp.zza()) {
                return true;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger = zzb;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzd(Class<?> cls) {
        if (!zzdp.zza()) {
            return false;
        }
        try {
            Class<?> cls2 = zzd;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzf() {
        Field fieldZza;
        if (zzdp.zza() && (fieldZza = zza((Class<?>) Buffer.class, "effectiveDirectAddress")) != null) {
            return fieldZza;
        }
        Field fieldZza2 = zza((Class<?>) Buffer.class, "address");
        if (fieldZza2 == null || fieldZza2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZza2;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzk(Object obj, long j) {
        return (byte) (zza(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzl(Object obj, long j) {
        return (byte) (zza(obj, (-4) & j) >>> ((int) ((j & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int iZza = zza(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zza(obj, j2, ((255 & b2) << i) | (iZza & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b2) << i) | (zza(obj, j2) & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzm(Object obj, long j) {
        return zzk(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzn(Object obj, long j) {
        return zzl(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Object obj, long j, boolean z) {
        zzc(obj, j, z ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zze(Object obj, long j, boolean z) {
        zzd(obj, j, z ? (byte) 1 : (byte) 0);
    }

    static {
        zzc zzcVar;
        zzc zzdVar = null;
        if (zzc != null) {
            if (zzdp.zza()) {
                if (zze) {
                    zzdVar = new zza(zzc);
                } else if (zzf) {
                    zzdVar = new zzb(zzc);
                }
            } else {
                zzdVar = new zzd(zzc);
            }
        }
        zzg = zzdVar;
        zzh = zze();
        zzi = zzd();
        zzj = zzb(byte[].class);
        zzk = zzb(boolean[].class);
        zzl = zzc(boolean[].class);
        zzm = zzb(int[].class);
        zzn = zzc(int[].class);
        zzo = zzb(long[].class);
        zzp = zzc(long[].class);
        zzq = zzb(float[].class);
        zzr = zzc(float[].class);
        zzs = zzb(double[].class);
        zzt = zzc(double[].class);
        zzu = zzb(Object[].class);
        zzv = zzc(Object[].class);
        Field fieldZzf = zzf();
        zzw = (fieldZzf == null || (zzcVar = zzg) == null) ? -1L : zzcVar.zza.objectFieldOffset(fieldZzf);
        zzx = (int) (zzj & 7);
        zza = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }
}