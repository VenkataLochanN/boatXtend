package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzfe;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbq {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zza extends zzfe<zza, C0041zza> implements zzgo {
        private static final zza zzh;
        private static volatile zzgz<zza> zzi;
        private int zzc;
        private String zzd = "";
        private boolean zze;
        private boolean zzf;
        private int zzg;

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.measurement.zzbq$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class C0041zza extends zzfe.zza<zza, C0041zza> implements zzgo {
            private C0041zza() {
                super(zza.zzh);
            }

            public final String zza() {
                return ((zza) this.zza).zza();
            }

            public final C0041zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(str);
                return this;
            }

            public final boolean zzb() {
                return ((zza) this.zza).zzb();
            }

            public final boolean zzc() {
                return ((zza) this.zza).zzc();
            }

            public final boolean zzd() {
                return ((zza) this.zza).zzd();
            }

            public final int zze() {
                return ((zza) this.zza).zze();
            }

            /* synthetic */ C0041zza(zzbp zzbpVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        public final boolean zzb() {
            return this.zze;
        }

        public final boolean zzc() {
            return this.zzf;
        }

        public final boolean zzd() {
            return (this.zzc & 8) != 0;
        }

        public final int zze() {
            return this.zzg;
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0041zza(zzbpVar);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\u0007\u0001\u0003\u0007\u0002\u0004\u0004\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzgz<zza> zzcVar = zzi;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzi;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzh);
                                zzi = zzcVar;
                            }
                            break;
                        }
                    }
                    return zzcVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zzaVar = new zza();
            zzh = zzaVar;
            zzfe.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzb extends zzfe<zzb, zza> implements zzgo {
        private static final zzb zzl;
        private static volatile zzgz<zzb> zzm;
        private int zzc;
        private long zzd;
        private int zzf;
        private boolean zzk;
        private String zze = "";
        private zzfn<zzc> zzg = zzbp();
        private zzfn<zza> zzh = zzbp();
        private zzfn<zzbk.zza> zzi = zzbp();
        private String zzj = "";

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzb, zza> implements zzgo {
            private zza() {
                super(zzb.zzl);
            }

            public final int zza() {
                return ((zzb) this.zza).zzf();
            }

            public final zza zza(int i) {
                return ((zzb) this.zza).zza(i);
            }

            public final zza zza(int i, zza.C0041zza c0041zza) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i, (zza) ((zzfe) c0041zza.zzv()));
                return this;
            }

            public final List<zzbk.zza> zzb() {
                return Collections.unmodifiableList(((zzb) this.zza).zzg());
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zzl();
                return this;
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzb() {
            return this.zzd;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        public final List<zzc> zze() {
            return this.zzg;
        }

        public final int zzf() {
            return this.zzh.size();
        }

        public final zza zza(int i) {
            return this.zzh.get(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zza zzaVar) {
            zzaVar.getClass();
            if (!this.zzh.zza()) {
                this.zzh = zzfe.zza(this.zzh);
            }
            this.zzh.set(i, zzaVar);
        }

        public final List<zzbk.zza> zzg() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzi = zzbp();
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public static zza zzi() {
            return zzl.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0003\u0000\u0001\u0002\u0000\u0002\b\u0001\u0003\u0004\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007\b\u0003\b\u0007\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzc.class, "zzh", zza.class, "zzi", zzbk.zza.class, "zzj", "zzk"});
                case 4:
                    return zzl;
                case 5:
                    zzgz<zzb> zzcVar = zzm;
                    if (zzcVar == null) {
                        synchronized (zzb.class) {
                            zzcVar = zzm;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzl);
                                zzm = zzcVar;
                            }
                            break;
                        }
                    }
                    return zzcVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzb zzj() {
            return zzl;
        }

        static {
            zzb zzbVar = new zzb();
            zzl = zzbVar;
            zzfe.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzc extends zzfe<zzc, zza> implements zzgo {
        private static final zzc zzf;
        private static volatile zzgz<zzc> zzg;
        private int zzc;
        private String zzd = "";
        private String zze = "";

        private zzc() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzc, zza> implements zzgo {
            private zza() {
                super(zzc.zzf);
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        public final String zzb() {
            return this.zze;
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzgz<zzc> zzcVar = zzg;
                    if (zzcVar == null) {
                        synchronized (zzc.class) {
                            zzcVar = zzg;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzf);
                                zzg = zzcVar;
                            }
                            break;
                        }
                    }
                    return zzcVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzf = zzcVar;
            zzfe.zza((Class<zzc>) zzc.class, zzcVar);
        }
    }
}