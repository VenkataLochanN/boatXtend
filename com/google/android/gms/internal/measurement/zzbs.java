package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.google.android.gms.internal.measurement.zzfe;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbs {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzc extends zzfe<zzc, zza> implements zzgo {
        private static final zzc zzi;
        private static volatile zzgz<zzc> zzj;
        private int zzc;
        private zzfn<zze> zzd = zzbp();
        private String zze = "";
        private long zzf;
        private long zzg;
        private int zzh;

        private zzc() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzc, zza> implements zzgo {
            private zza() {
                super(zzc.zzi);
            }

            public final List<zze> zza() {
                return Collections.unmodifiableList(((zzc) this.zza).zza());
            }

            public final int zzb() {
                return ((zzc) this.zza).zzb();
            }

            public final zze zza(int i) {
                return ((zzc) this.zza).zza(i);
            }

            public final zza zza(int i, zze zzeVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(i, zzeVar);
                return this;
            }

            public final zza zza(int i, zze.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(i, (zze) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final zza zza(zze zzeVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(zzeVar);
                return this;
            }

            public final zza zza(zze.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza((zze) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final zza zza(Iterable<? extends zze> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(iterable);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzm();
                return this;
            }

            public final zza zzb(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzb(i);
                return this;
            }

            public final String zzd() {
                return ((zzc) this.zza).zzc();
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(str);
                return this;
            }

            public final boolean zze() {
                return ((zzc) this.zza).zzd();
            }

            public final long zzf() {
                return ((zzc) this.zza).zze();
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(j);
                return this;
            }

            public final long zzg() {
                return ((zzc) this.zza).zzg();
            }

            public final zza zzb(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzb(j);
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final List<zze> zza() {
            return this.zzd;
        }

        public final int zzb() {
            return this.zzd.size();
        }

        public final zze zza(int i) {
            return this.zzd.get(i);
        }

        private final void zzl() {
            if (this.zzd.zza()) {
                return;
            }
            this.zzd = zzfe.zza(this.zzd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zze zzeVar) {
            zzeVar.getClass();
            zzl();
            this.zzd.set(i, zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zze zzeVar) {
            zzeVar.getClass();
            zzl();
            this.zzd.add(zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zze> iterable) {
            zzl();
            zzdm.zza(iterable, this.zzd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzd = zzbp();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(int i) {
            zzl();
            this.zzd.remove(i);
        }

        public final String zzc() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zze = str;
        }

        public final boolean zzd() {
            return (this.zzc & 2) != 0;
        }

        public final long zze() {
            return this.zzf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 2;
            this.zzf = j;
        }

        public final boolean zzf() {
            return (this.zzc & 4) != 0;
        }

        public final long zzg() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j) {
            this.zzc |= 4;
            this.zzg = j;
        }

        public final boolean zzh() {
            return (this.zzc & 8) != 0;
        }

        public final int zzi() {
            return this.zzh;
        }

        public static zza zzj() {
            return zzi.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002\b\u0000\u0003\u0002\u0001\u0004\u0002\u0002\u0005\u0004\u0003", new Object[]{"zzc", "zzd", zze.class, "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzgz<zzc> zzcVar = zzj;
                    if (zzcVar == null) {
                        synchronized (zzc.class) {
                            zzcVar = zzj;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzi);
                                zzj = zzcVar;
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
            zzi = zzcVar;
            zzfe.zza((Class<zzc>) zzc.class, zzcVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzf extends zzfe<zzf, zza> implements zzgo {
        private static final zzf zzd;
        private static volatile zzgz<zzf> zze;
        private zzfn<zzg> zzc = zzbp();

        private zzf() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzf, zza> implements zzgo {
            private zza() {
                super(zzf.zzd);
            }

            public final zzg zza(int i) {
                return ((zzf) this.zza).zza(0);
            }

            public final zza zza(zzg.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzf) this.zza).zza((zzg) ((zzfe) zzaVar.zzv()));
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final List<zzg> zza() {
            return this.zzc;
        }

        public final zzg zza(int i) {
            return this.zzc.get(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzg zzgVar) {
            zzgVar.getClass();
            if (!this.zzc.zza()) {
                this.zzc = zzfe.zza(this.zzc);
            }
            this.zzc.add(zzgVar);
        }

        public static zza zzb() {
            return zzd.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzg.class});
                case 4:
                    return zzd;
                case 5:
                    zzgz<zzf> zzcVar = zze;
                    if (zzcVar == null) {
                        synchronized (zzf.class) {
                            zzcVar = zze;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzd);
                                zze = zzcVar;
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
            zzf zzfVar = new zzf();
            zzd = zzfVar;
            zzfe.zza((Class<zzf>) zzf.class, zzfVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zza extends zzfe<zza, C0042zza> implements zzgo {
        private static final zza zzh;
        private static volatile zzgz<zza> zzi;
        private int zzc;
        private int zzd;
        private zzi zze;
        private zzi zzf;
        private boolean zzg;

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.measurement.zzbs$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class C0042zza extends zzfe.zza<zza, C0042zza> implements zzgo {
            private C0042zza() {
                super(zza.zzh);
            }

            public final C0042zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(i);
                return this;
            }

            public final C0042zza zza(zzi.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza((zzi) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final boolean zza() {
                return ((zza) this.zza).zzd();
            }

            public final zzi zzb() {
                return ((zza) this.zza).zze();
            }

            public final C0042zza zza(zzi zziVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zzb(zziVar);
                return this;
            }

            public final C0042zza zza(boolean z) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(z);
                return this;
            }

            /* synthetic */ C0042zza(zzbr zzbrVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i) {
            this.zzc |= 1;
            this.zzd = i;
        }

        public final zzi zzc() {
            zzi zziVar = this.zze;
            return zziVar == null ? zzi.zzj() : zziVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzi zziVar) {
            zziVar.getClass();
            this.zze = zziVar;
            this.zzc |= 2;
        }

        public final boolean zzd() {
            return (this.zzc & 4) != 0;
        }

        public final zzi zze() {
            zzi zziVar = this.zzf;
            return zziVar == null ? zzi.zzj() : zziVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzi zziVar) {
            zziVar.getClass();
            this.zzf = zziVar;
            this.zzc |= 4;
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final boolean zzg() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 8;
            this.zzg = z;
        }

        public static C0042zza zzh() {
            return zzh.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0042zza(zzbrVar);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\t\u0001\u0003\t\u0002\u0004\u0007\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
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
        private static final zzb zzf;
        private static volatile zzgz<zzb> zzg;
        private int zzc;
        private int zzd;
        private long zze;

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzb, zza> implements zzgo {
            private zza() {
                super(zzb.zzf);
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i);
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(j);
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i) {
            this.zzc |= 1;
            this.zzd = i;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final long zzd() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 2;
            this.zze = j;
        }

        public static zza zze() {
            return zzf.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0002\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzgz<zzb> zzcVar = zzg;
                    if (zzcVar == null) {
                        synchronized (zzb.class) {
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
            zzb zzbVar = new zzb();
            zzf = zzbVar;
            zzfe.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzd extends zzfe<zzd, zza> implements zzgo {
        private static final zzd zzf;
        private static volatile zzgz<zzd> zzg;
        private int zzc;
        private String zzd = "";
        private long zze;

        private zzd() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzd, zza> implements zzgo {
            private zza() {
                super(zzd.zzf);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzd) this.zza).zza(str);
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzd) this.zza).zza(j);
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 2;
            this.zze = j;
        }

        public static zza zza() {
            return zzf.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzgz<zzd> zzcVar = zzg;
                    if (zzcVar == null) {
                        synchronized (zzd.class) {
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
            zzd zzdVar = new zzd();
            zzf = zzdVar;
            zzfe.zza((Class<zzd>) zzd.class, zzdVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zze extends zzfe<zze, zza> implements zzgo {
        private static final zze zzj;
        private static volatile zzgz<zze> zzk;
        private int zzc;
        private long zzf;
        private float zzg;
        private double zzh;
        private String zzd = "";
        private String zze = "";
        private zzfn<zze> zzi = zzbp();

        private zze() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zze, zza> implements zzgo {
            private zza() {
                super(zze.zzj);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzb(str);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzm();
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(j);
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzn();
                return this;
            }

            public final zza zza(double d2) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(d2);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzo();
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final String zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzc &= -3;
            this.zze = zzj.zze;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final long zzf() {
            return this.zzf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 4;
            this.zzf = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn() {
            this.zzc &= -5;
            this.zzf = 0L;
        }

        public final boolean zzg() {
            return (this.zzc & 16) != 0;
        }

        public final double zzh() {
            return this.zzh;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(double d2) {
            this.zzc |= 16;
            this.zzh = d2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo() {
            this.zzc &= -17;
            this.zzh = 0.0d;
        }

        public final List<zze> zzi() {
            return this.zzi;
        }

        public final int zzj() {
            return this.zzi.size();
        }

        public static zza zzk() {
            return zzj.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0001\u0003\u0005\u0000\u0004\u0006\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", zze.class});
                case 4:
                    return zzj;
                case 5:
                    zzgz<zze> zzcVar = zzk;
                    if (zzcVar == null) {
                        synchronized (zze.class) {
                            zzcVar = zzk;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzj);
                                zzk = zzcVar;
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
            zze zzeVar = new zze();
            zzj = zzeVar;
            zzfe.zza((Class<zze>) zze.class, zzeVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzg extends zzfe<zzg, zza> implements zzgo {
        private static final zzg zzav;
        private static volatile zzgz<zzg> zzaw;
        private int zzaa;
        private boolean zzad;
        private int zzag;
        private int zzah;
        private int zzai;
        private long zzak;
        private long zzal;
        private int zzao;
        private zzh zzaq;
        private long zzas;
        private long zzat;
        private int zzc;
        private int zzd;
        private int zze;
        private long zzh;
        private long zzi;
        private long zzj;
        private long zzk;
        private long zzl;
        private int zzq;
        private long zzu;
        private long zzv;
        private boolean zzx;
        private long zzz;
        private zzfn<zzc> zzf = zzbp();
        private zzfn<zzk> zzg = zzbp();
        private String zzm = "";
        private String zzn = "";
        private String zzo = "";
        private String zzp = "";
        private String zzr = "";
        private String zzs = "";
        private String zzt = "";
        private String zzw = "";
        private String zzy = "";
        private String zzab = "";
        private String zzac = "";
        private zzfn<zza> zzae = zzbp();
        private String zzaf = "";
        private String zzaj = "";
        private String zzam = "";
        private String zzan = "";
        private String zzap = "";
        private zzfl zzar = zzbn();
        private String zzau = "";

        private zzg() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzg, zza> implements zzgo {
            private zza() {
                super(zzg.zzav);
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(1);
                return this;
            }

            public final List<zzc> zza() {
                return Collections.unmodifiableList(((zzg) this.zza).zzc());
            }

            public final int zzb() {
                return ((zzg) this.zza).zzd();
            }

            public final zzc zzb(int i) {
                return ((zzg) this.zza).zza(i);
            }

            public final zza zza(int i, zzc.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(i, (zzc) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final zza zza(zzc.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzc) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final zza zza(Iterable<? extends zzc> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(iterable);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbt();
                return this;
            }

            public final zza zzc(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(i);
                return this;
            }

            public final List<zzk> zzd() {
                return Collections.unmodifiableList(((zzg) this.zza).zze());
            }

            public final int zze() {
                return ((zzg) this.zza).zzf();
            }

            public final zzk zzd(int i) {
                return ((zzg) this.zza).zzb(i);
            }

            public final zza zza(int i, zzk zzkVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(i, zzkVar);
                return this;
            }

            public final zza zza(zzk zzkVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(zzkVar);
                return this;
            }

            public final zza zza(zzk.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzk) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final zza zzb(Iterable<? extends zzk> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(iterable);
                return this;
            }

            public final zza zze(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(i);
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(j);
                return this;
            }

            public final long zzf() {
                return ((zzg) this.zza).zzj();
            }

            public final zza zzb(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(j);
                return this;
            }

            public final long zzg() {
                return ((zzg) this.zza).zzl();
            }

            public final zza zzc(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(j);
                return this;
            }

            public final zza zzd(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(j);
                return this;
            }

            public final zza zzh() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbv();
                return this;
            }

            public final zza zze(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(j);
                return this;
            }

            public final zza zzi() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbw();
                return this;
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(str);
                return this;
            }

            public final zza zzf(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(i);
                return this;
            }

            public final zza zze(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(str);
                return this;
            }

            public final String zzj() {
                return ((zzg) this.zza).zzx();
            }

            public final zza zzf(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(str);
                return this;
            }

            public final zza zzg(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(str);
                return this;
            }

            public final zza zzf(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(j);
                return this;
            }

            public final zza zzg(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(j);
                return this;
            }

            public final zza zzh(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(str);
                return this;
            }

            public final zza zza(boolean z) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(z);
                return this;
            }

            public final zza zzi(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(str);
                return this;
            }

            public final zza zzh(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(j);
                return this;
            }

            public final zza zzg(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(i);
                return this;
            }

            public final zza zzj(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(str);
                return this;
            }

            public final zza zzk() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbx();
                return this;
            }

            public final String zzl() {
                return ((zzg) this.zza).zzam();
            }

            public final zza zzk(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzk(str);
                return this;
            }

            public final zza zzb(boolean z) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(z);
                return this;
            }

            public final zza zzc(Iterable<? extends zza> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(iterable);
                return this;
            }

            public final zza zzm() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzby();
                return this;
            }

            public final zza zzl(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzl(str);
                return this;
            }

            public final zza zzh(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(i);
                return this;
            }

            public final zza zzm(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzm(str);
                return this;
            }

            public final zza zzi(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(j);
                return this;
            }

            public final zza zzj(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(j);
                return this;
            }

            public final zza zzn(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzn(null);
                return this;
            }

            public final zza zzn() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbz();
                return this;
            }

            public final zza zzi(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(i);
                return this;
            }

            public final zza zzo(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzo(str);
                return this;
            }

            public final zza zza(zzh.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzh) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final zza zzd(Iterable<? extends Integer> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(iterable);
                return this;
            }

            public final zza zzk(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzk(j);
                return this;
            }

            public final zza zzl(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzl(j);
                return this;
            }

            public final String zzo() {
                return ((zzg) this.zza).zzbe();
            }

            public final zza zzp(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzp(str);
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(int i) {
            this.zzc |= 1;
            this.zze = i;
        }

        public final List<zzc> zzc() {
            return this.zzf;
        }

        public final int zzd() {
            return this.zzf.size();
        }

        public final zzc zza(int i) {
            return this.zzf.get(i);
        }

        private final void zzbs() {
            if (this.zzf.zza()) {
                return;
            }
            this.zzf = zzfe.zza(this.zzf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zzc zzcVar) {
            zzcVar.getClass();
            zzbs();
            this.zzf.set(i, zzcVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzc zzcVar) {
            zzcVar.getClass();
            zzbs();
            this.zzf.add(zzcVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zzc> iterable) {
            zzbs();
            zzdm.zza(iterable, this.zzf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbt() {
            this.zzf = zzbp();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(int i) {
            zzbs();
            this.zzf.remove(i);
        }

        public final List<zzk> zze() {
            return this.zzg;
        }

        public final int zzf() {
            return this.zzg.size();
        }

        public final zzk zzb(int i) {
            return this.zzg.get(i);
        }

        private final void zzbu() {
            if (this.zzg.zza()) {
                return;
            }
            this.zzg = zzfe.zza(this.zzg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zzk zzkVar) {
            zzkVar.getClass();
            zzbu();
            this.zzg.set(i, zzkVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzk zzkVar) {
            zzkVar.getClass();
            zzbu();
            this.zzg.add(zzkVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<? extends zzk> iterable) {
            zzbu();
            zzdm.zza(iterable, this.zzg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(int i) {
            zzbu();
            this.zzg.remove(i);
        }

        public final boolean zzg() {
            return (this.zzc & 2) != 0;
        }

        public final long zzh() {
            return this.zzh;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 2;
            this.zzh = j;
        }

        public final boolean zzi() {
            return (this.zzc & 4) != 0;
        }

        public final long zzj() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j) {
            this.zzc |= 4;
            this.zzi = j;
        }

        public final boolean zzk() {
            return (this.zzc & 8) != 0;
        }

        public final long zzl() {
            return this.zzj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(long j) {
            this.zzc |= 8;
            this.zzj = j;
        }

        public final boolean zzm() {
            return (this.zzc & 16) != 0;
        }

        public final long zzn() {
            return this.zzk;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(long j) {
            this.zzc |= 16;
            this.zzk = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbv() {
            this.zzc &= -17;
            this.zzk = 0L;
        }

        public final boolean zzo() {
            return (this.zzc & 32) != 0;
        }

        public final long zzp() {
            return this.zzl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(long j) {
            this.zzc |= 32;
            this.zzl = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbw() {
            this.zzc &= -33;
            this.zzl = 0L;
        }

        public final String zzq() {
            return this.zzm;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 64;
            this.zzm = str;
        }

        public final String zzr() {
            return this.zzn;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 128;
            this.zzn = str;
        }

        public final String zzs() {
            return this.zzo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 256;
            this.zzo = str;
        }

        public final String zzt() {
            return this.zzp;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 512;
            this.zzp = str;
        }

        public final boolean zzu() {
            return (this.zzc & 1024) != 0;
        }

        public final int zzv() {
            return this.zzq;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(int i) {
            this.zzc |= 1024;
            this.zzq = i;
        }

        public final String zzw() {
            return this.zzr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zzc |= 2048;
            this.zzr = str;
        }

        public final String zzx() {
            return this.zzs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zzc |= 4096;
            this.zzs = str;
        }

        public final String zzy() {
            return this.zzt;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(String str) {
            str.getClass();
            this.zzc |= 8192;
            this.zzt = str;
        }

        public final boolean zzz() {
            return (this.zzc & 16384) != 0;
        }

        public final long zzaa() {
            return this.zzu;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(long j) {
            this.zzc |= 16384;
            this.zzu = j;
        }

        public final boolean zzab() {
            return (this.zzc & 32768) != 0;
        }

        public final long zzac() {
            return this.zzv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(long j) {
            this.zzc |= 32768;
            this.zzv = j;
        }

        public final String zzad() {
            return this.zzw;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(String str) {
            str.getClass();
            this.zzc |= 65536;
            this.zzw = str;
        }

        public final boolean zzae() {
            return (this.zzc & 131072) != 0;
        }

        public final boolean zzaf() {
            return this.zzx;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 131072;
            this.zzx = z;
        }

        public final String zzag() {
            return this.zzy;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(String str) {
            str.getClass();
            this.zzc |= 262144;
            this.zzy = str;
        }

        public final boolean zzah() {
            return (this.zzc & 524288) != 0;
        }

        public final long zzai() {
            return this.zzz;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(long j) {
            this.zzc |= 524288;
            this.zzz = j;
        }

        public final boolean zzaj() {
            return (this.zzc & 1048576) != 0;
        }

        public final int zzak() {
            return this.zzaa;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(int i) {
            this.zzc |= 1048576;
            this.zzaa = i;
        }

        public final String zzal() {
            return this.zzab;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(String str) {
            str.getClass();
            this.zzc |= 2097152;
            this.zzab = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbx() {
            this.zzc &= -2097153;
            this.zzab = zzav.zzab;
        }

        public final String zzam() {
            return this.zzac;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzk(String str) {
            str.getClass();
            this.zzc |= 4194304;
            this.zzac = str;
        }

        public final boolean zzan() {
            return (this.zzc & 8388608) != 0;
        }

        public final boolean zzao() {
            return this.zzad;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzc |= 8388608;
            this.zzad = z;
        }

        public final List<zza> zzap() {
            return this.zzae;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(Iterable<? extends zza> iterable) {
            if (!this.zzae.zza()) {
                this.zzae = zzfe.zza(this.zzae);
            }
            zzdm.zza(iterable, this.zzae);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzby() {
            this.zzae = zzbp();
        }

        public final String zzaq() {
            return this.zzaf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(String str) {
            str.getClass();
            this.zzc |= 16777216;
            this.zzaf = str;
        }

        public final boolean zzar() {
            return (this.zzc & 33554432) != 0;
        }

        public final int zzas() {
            return this.zzag;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(int i) {
            this.zzc |= 33554432;
            this.zzag = i;
        }

        public final String zzat() {
            return this.zzaj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(String str) {
            str.getClass();
            this.zzc |= 268435456;
            this.zzaj = str;
        }

        public final boolean zzau() {
            return (this.zzc & 536870912) != 0;
        }

        public final long zzav() {
            return this.zzak;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(long j) {
            this.zzc |= 536870912;
            this.zzak = j;
        }

        public final boolean zzaw() {
            return (this.zzc & BasicMeasure.EXACTLY) != 0;
        }

        public final long zzax() {
            return this.zzal;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(long j) {
            this.zzc |= BasicMeasure.EXACTLY;
            this.zzal = j;
        }

        public final String zzay() {
            return this.zzam;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(String str) {
            str.getClass();
            this.zzc |= Integer.MIN_VALUE;
            this.zzam = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbz() {
            this.zzc &= Integer.MAX_VALUE;
            this.zzam = zzav.zzam;
        }

        public final boolean zzaz() {
            return (this.zzd & 2) != 0;
        }

        public final int zzba() {
            return this.zzao;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(int i) {
            this.zzd |= 2;
            this.zzao = i;
        }

        public final String zzbb() {
            return this.zzap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(String str) {
            str.getClass();
            this.zzd |= 4;
            this.zzap = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzh zzhVar) {
            zzhVar.getClass();
            this.zzaq = zzhVar;
            this.zzd |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(Iterable<? extends Integer> iterable) {
            if (!this.zzar.zza()) {
                zzfl zzflVar = this.zzar;
                int size = zzflVar.size();
                this.zzar = zzflVar.zza(size == 0 ? 10 : size << 1);
            }
            zzdm.zza(iterable, this.zzar);
        }

        public final boolean zzbc() {
            return (this.zzd & 16) != 0;
        }

        public final long zzbd() {
            return this.zzas;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzk(long j) {
            this.zzd |= 16;
            this.zzas = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(long j) {
            this.zzd |= 32;
            this.zzat = j;
        }

        public final String zzbe() {
            return this.zzau;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(String str) {
            str.getClass();
            this.zzd |= 64;
            this.zzau = str;
        }

        public static zza zzbf() {
            return zzav.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzav, "\u0001+\u0000\u0002\u00012+\u0000\u0004\u0000\u0001\u0004\u0000\u0002\u001b\u0003\u001b\u0004\u0002\u0001\u0005\u0002\u0002\u0006\u0002\u0003\u0007\u0002\u0005\b\b\u0006\t\b\u0007\n\b\b\u000b\b\t\f\u0004\n\r\b\u000b\u000e\b\f\u0010\b\r\u0011\u0002\u000e\u0012\u0002\u000f\u0013\b\u0010\u0014\u0007\u0011\u0015\b\u0012\u0016\u0002\u0013\u0017\u0004\u0014\u0018\b\u0015\u0019\b\u0016\u001a\u0002\u0004\u001c\u0007\u0017\u001d\u001b\u001e\b\u0018\u001f\u0004\u0019 \u0004\u001a!\u0004\u001b\"\b\u001c#\u0002\u001d$\u0002\u001e%\b\u001f&\b '\u0004!)\b\",\t#-\u001d.\u0002$/\u0002%2\b&", new Object[]{"zzc", "zzd", "zze", "zzf", zzc.class, "zzg", zzk.class, "zzh", "zzi", "zzj", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzk", "zzad", "zzae", zza.class, "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau"});
                case 4:
                    return zzav;
                case 5:
                    zzgz<zzg> zzcVar = zzaw;
                    if (zzcVar == null) {
                        synchronized (zzg.class) {
                            zzcVar = zzaw;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzav);
                                zzaw = zzcVar;
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
            zzg zzgVar = new zzg();
            zzav = zzgVar;
            zzfe.zza((Class<zzg>) zzg.class, zzgVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzh extends zzfe<zzh, zza> implements zzgo {
        private static final zzh zzf;
        private static volatile zzgz<zzh> zzg;
        private int zzc;
        private int zzd = 1;
        private zzfn<zzd> zze = zzbp();

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public enum zzb implements zzfg {
            RADS(1),
            PROVISIONING(2);

            private static final zzfj<zzb> zzc = new zzbt();
            private final int zzd;

            @Override // com.google.android.gms.internal.measurement.zzfg
            public final int zza() {
                return this.zzd;
            }

            public static zzb zza(int i) {
                if (i == 1) {
                    return RADS;
                }
                if (i != 2) {
                    return null;
                }
                return PROVISIONING;
            }

            public static zzfi zzb() {
                return zzbu.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + Typography.greater;
            }

            zzb(int i) {
                this.zzd = i;
            }
        }

        private zzh() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzh, zza> implements zzgo {
            private zza() {
                super(zzh.zzf);
            }

            public final zza zza(zzd.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzh) this.zza).zza((zzd) ((zzfe) zzaVar.zzv()));
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzd zzdVar) {
            zzdVar.getClass();
            if (!this.zze.zza()) {
                this.zze = zzfe.zza(this.zze);
            }
            this.zze.add(zzdVar);
        }

        public static zza zza() {
            return zzf.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u001b", new Object[]{"zzc", "zzd", zzb.zzb(), "zze", zzd.class});
                case 4:
                    return zzf;
                case 5:
                    zzgz<zzh> zzcVar = zzg;
                    if (zzcVar == null) {
                        synchronized (zzh.class) {
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
            zzh zzhVar = new zzh();
            zzf = zzhVar;
            zzfe.zza((Class<zzh>) zzh.class, zzhVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzi extends zzfe<zzi, zza> implements zzgo {
        private static final zzi zzg;
        private static volatile zzgz<zzi> zzh;
        private zzfk zzc = zzbo();
        private zzfk zzd = zzbo();
        private zzfn<zzb> zze = zzbp();
        private zzfn<zzj> zzf = zzbp();

        private zzi() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzi, zza> implements zzgo {
            private zza() {
                super(zzi.zzg);
            }

            public final zza zza(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zza(iterable);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzl();
                return this;
            }

            public final zza zzb(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzb(iterable);
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzm();
                return this;
            }

            public final zza zzc(Iterable<? extends zzb> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzc(iterable);
                return this;
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzd(i);
                return this;
            }

            public final zza zzd(Iterable<? extends zzj> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzd(iterable);
                return this;
            }

            public final zza zzb(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zze(i);
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final List<Long> zza() {
            return this.zzc;
        }

        public final int zzb() {
            return this.zzc.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends Long> iterable) {
            if (!this.zzc.zza()) {
                this.zzc = zzfe.zza(this.zzc);
            }
            zzdm.zza(iterable, this.zzc);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzc = zzbo();
        }

        public final List<Long> zzc() {
            return this.zzd;
        }

        public final int zzd() {
            return this.zzd.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<? extends Long> iterable) {
            if (!this.zzd.zza()) {
                this.zzd = zzfe.zza(this.zzd);
            }
            zzdm.zza(iterable, this.zzd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzd = zzbo();
        }

        public final List<zzb> zze() {
            return this.zze;
        }

        public final int zzf() {
            return this.zze.size();
        }

        public final zzb zza(int i) {
            return this.zze.get(i);
        }

        private final void zzn() {
            if (this.zze.zza()) {
                return;
            }
            this.zze = zzfe.zza(this.zze);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(Iterable<? extends zzb> iterable) {
            zzn();
            zzdm.zza(iterable, this.zze);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(int i) {
            zzn();
            this.zze.remove(i);
        }

        public final List<zzj> zzg() {
            return this.zzf;
        }

        public final int zzh() {
            return this.zzf.size();
        }

        public final zzj zzb(int i) {
            return this.zzf.get(i);
        }

        private final void zzo() {
            if (this.zzf.zza()) {
                return;
            }
            this.zzf = zzfe.zza(this.zzf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(Iterable<? extends zzj> iterable) {
            zzo();
            zzdm.zza(iterable, this.zzf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(int i) {
            zzo();
            this.zzf.remove(i);
        }

        public static zza zzi() {
            return zzg.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzg, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zzc", "zzd", "zze", zzb.class, "zzf", zzj.class});
                case 4:
                    return zzg;
                case 5:
                    zzgz<zzi> zzcVar = zzh;
                    if (zzcVar == null) {
                        synchronized (zzi.class) {
                            zzcVar = zzh;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzg);
                                zzh = zzcVar;
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

        public static zzi zzj() {
            return zzg;
        }

        static {
            zzi zziVar = new zzi();
            zzg = zziVar;
            zzfe.zza((Class<zzi>) zzi.class, zziVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzj extends zzfe<zzj, zza> implements zzgo {
        private static final zzj zzf;
        private static volatile zzgz<zzj> zzg;
        private int zzc;
        private int zzd;
        private zzfk zze = zzbo();

        private zzj() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzj, zza> implements zzgo {
            private zza() {
                super(zzj.zzf);
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzj) this.zza).zzb(i);
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzj) this.zza).zza(j);
                return this;
            }

            public final zza zza(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzj) this.zza).zza(iterable);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzj) this.zza).zzh();
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(int i) {
            this.zzc |= 1;
            this.zzd = i;
        }

        public final List<Long> zzc() {
            return this.zze;
        }

        public final int zzd() {
            return this.zze.size();
        }

        public final long zza(int i) {
            return this.zze.zzb(i);
        }

        private final void zzg() {
            if (this.zze.zza()) {
                return;
            }
            this.zze = zzfe.zza(this.zze);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            zzg();
            this.zze.zza(j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends Long> iterable) {
            zzg();
            zzdm.zza(iterable, this.zze);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh() {
            this.zze = zzbo();
        }

        public static zza zze() {
            return zzf.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0004\u0000\u0002\u0014", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzgz<zzj> zzcVar = zzg;
                    if (zzcVar == null) {
                        synchronized (zzj.class) {
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
            zzj zzjVar = new zzj();
            zzf = zzjVar;
            zzfe.zza((Class<zzj>) zzj.class, zzjVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzk extends zzfe<zzk, zza> implements zzgo {
        private static final zzk zzj;
        private static volatile zzgz<zzk> zzk;
        private int zzc;
        private long zzd;
        private String zze = "";
        private String zzf = "";
        private long zzg;
        private float zzh;
        private double zzi;

        private zzk() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzk, zza> implements zzgo {
            private zza() {
                super(zzk.zzj);
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(j);
                return this;
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzb(str);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzl();
                return this;
            }

            public final zza zzb(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzb(j);
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzm();
                return this;
            }

            public final zza zza(double d2) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(d2);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzn();
                return this;
            }

            /* synthetic */ zza(zzbr zzbrVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 1;
            this.zzd = j;
        }

        public final String zzc() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        public final boolean zzd() {
            return (this.zzc & 4) != 0;
        }

        public final String zze() {
            return this.zzf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzc &= -5;
            this.zzf = zzj.zzf;
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final long zzg() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j) {
            this.zzc |= 8;
            this.zzg = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzc &= -9;
            this.zzg = 0L;
        }

        public final boolean zzh() {
            return (this.zzc & 32) != 0;
        }

        public final double zzi() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(double d2) {
            this.zzc |= 32;
            this.zzi = d2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn() {
            this.zzc &= -33;
            this.zzi = 0.0d;
        }

        public static zza zzj() {
            return zzj.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbr zzbrVar = null;
            switch (zzbr.zza[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(zzbrVar);
                case 3:
                    return zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0002\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0002\u0003\u0005\u0001\u0004\u0006\u0000\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzj;
                case 5:
                    zzgz<zzk> zzcVar = zzk;
                    if (zzcVar == null) {
                        synchronized (zzk.class) {
                            zzcVar = zzk;
                            if (zzcVar == null) {
                                zzcVar = new zzfe.zzc<>(zzj);
                                zzk = zzcVar;
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
            zzk zzkVar = new zzk();
            zzj = zzkVar;
            zzfe.zza((Class<zzk>) zzk.class, zzkVar);
        }
    }
}