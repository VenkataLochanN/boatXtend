package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import java.util.List;
import kotlin.text.Typography;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbk {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zza extends zzfe<zza, C0040zza> implements zzgo {
        private static final zza zzi;
        private static volatile zzgz<zza> zzj;
        private int zzc;
        private int zzd;
        private zzfn<zze> zze = zzbp();
        private zzfn<zzb> zzf = zzbp();
        private boolean zzg;
        private boolean zzh;

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.measurement.zzbk$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class C0040zza extends zzfe.zza<zza, C0040zza> implements zzgo {
            private C0040zza() {
                super(zza.zzi);
            }

            public final int zza() {
                return ((zza) this.zza).zzd();
            }

            public final zze zza(int i) {
                return ((zza) this.zza).zza(i);
            }

            public final C0040zza zza(int i, zze.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(i, (zze) ((zzfe) zzaVar.zzv()));
                return this;
            }

            public final int zzb() {
                return ((zza) this.zza).zzf();
            }

            public final zzb zzb(int i) {
                return ((zza) this.zza).zzb(i);
            }

            public final C0040zza zza(int i, zzb.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(i, (zzb) ((zzfe) zzaVar.zzv()));
                return this;
            }

            /* synthetic */ C0040zza(zzbj zzbjVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        public final List<zze> zzc() {
            return this.zze;
        }

        public final int zzd() {
            return this.zze.size();
        }

        public final zze zza(int i) {
            return this.zze.get(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zze zzeVar) {
            zzeVar.getClass();
            if (!this.zze.zza()) {
                this.zze = zzfe.zza(this.zze);
            }
            this.zze.set(i, zzeVar);
        }

        public final List<zzb> zze() {
            return this.zzf;
        }

        public final int zzf() {
            return this.zzf.size();
        }

        public final zzb zzb(int i) {
            return this.zzf.get(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zzb zzbVar) {
            zzbVar.getClass();
            if (!this.zzf.zza()) {
                this.zzf = zzfe.zza(this.zzf);
            }
            this.zzf.set(i, zzbVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0040zza(zzbjVar);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001\u0004\u0000\u0002\u001b\u0003\u001b\u0004\u0007\u0001\u0005\u0007\u0002", new Object[]{"zzc", "zzd", "zze", zze.class, "zzf", zzb.class, "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzgz<zza> zzcVar = zzj;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
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
            zza zzaVar = new zza();
            zzi = zzaVar;
            zzfe.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzb extends zzfe<zzb, zza> implements zzgo {
        private static final zzb zzl;
        private static volatile zzgz<zzb> zzm;
        private int zzc;
        private int zzd;
        private String zze = "";
        private zzfn<zzc> zzf = zzbp();
        private boolean zzg;
        private zzd zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzb, zza> implements zzgo {
            private zza() {
                super(zzb.zzl);
            }

            public final String zza() {
                return ((zzb) this.zza).zzc();
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(str);
                return this;
            }

            public final int zzb() {
                return ((zzb) this.zza).zze();
            }

            public final zzc zza(int i) {
                return ((zzb) this.zza).zza(i);
            }

            public final zza zza(int i, zzc zzcVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i, zzcVar);
                return this;
            }

            /* synthetic */ zza(zzbj zzbjVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
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

        public final List<zzc> zzd() {
            return this.zzf;
        }

        public final int zze() {
            return this.zzf.size();
        }

        public final zzc zza(int i) {
            return this.zzf.get(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zzc zzcVar) {
            zzcVar.getClass();
            if (!this.zzf.zza()) {
                this.zzf = zzfe.zza(this.zzf);
            }
            this.zzf.set(i, zzcVar);
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final zzd zzg() {
            zzd zzdVar = this.zzh;
            return zzdVar == null ? zzd.zzk() : zzdVar;
        }

        public final boolean zzh() {
            return this.zzi;
        }

        public final boolean zzi() {
            return this.zzj;
        }

        public final boolean zzj() {
            return (this.zzc & 64) != 0;
        }

        public final boolean zzk() {
            return this.zzk;
        }

        public static zza zzl() {
            return zzl.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzbjVar);
                case 3:
                    return zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\u001b\u0004\u0007\u0002\u0005\t\u0003\u0006\u0007\u0004\u0007\u0007\u0005\b\u0007\u0006", new Object[]{"zzc", "zzd", "zze", "zzf", zzc.class, "zzg", "zzh", "zzi", "zzj", "zzk"});
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

        static {
            zzb zzbVar = new zzb();
            zzl = zzbVar;
            zzfe.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzd extends zzfe<zzd, zza> implements zzgo {
        private static final zzd zzi;
        private static volatile zzgz<zzd> zzj;
        private int zzc;
        private int zzd;
        private boolean zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public enum zzb implements zzfg {
            UNKNOWN_COMPARISON_TYPE(0),
            LESS_THAN(1),
            GREATER_THAN(2),
            EQUAL(3),
            BETWEEN(4);

            private static final zzfj<zzb> zzf = new zzbl();
            private final int zzg;

            @Override // com.google.android.gms.internal.measurement.zzfg
            public final int zza() {
                return this.zzg;
            }

            public static zzb zza(int i) {
                if (i == 0) {
                    return UNKNOWN_COMPARISON_TYPE;
                }
                if (i == 1) {
                    return LESS_THAN;
                }
                if (i == 2) {
                    return GREATER_THAN;
                }
                if (i == 3) {
                    return EQUAL;
                }
                if (i != 4) {
                    return null;
                }
                return BETWEEN;
            }

            public static zzfi zzb() {
                return zzbm.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            zzb(int i) {
                this.zzg = i;
            }
        }

        private zzd() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzd, zza> implements zzgo {
            private zza() {
                super(zzd.zzi);
            }

            /* synthetic */ zza(zzbj zzbjVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final zzb zzb() {
            zzb zzbVarZza = zzb.zza(this.zzd);
            return zzbVarZza == null ? zzb.UNKNOWN_COMPARISON_TYPE : zzbVarZza;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final boolean zzd() {
            return this.zze;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final String zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return (this.zzc & 8) != 0;
        }

        public final String zzh() {
            return this.zzg;
        }

        public final boolean zzi() {
            return (this.zzc & 16) != 0;
        }

        public final String zzj() {
            return this.zzh;
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(zzbjVar);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0000\u0002\u0007\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004", new Object[]{"zzc", "zzd", zzb.zzb(), "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzgz<zzd> zzcVar = zzj;
                    if (zzcVar == null) {
                        synchronized (zzd.class) {
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

        public static zzd zzk() {
            return zzi;
        }

        static {
            zzd zzdVar = new zzd();
            zzi = zzdVar;
            zzfe.zza((Class<zzd>) zzd.class, zzdVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzf extends zzfe<zzf, zzb> implements zzgo {
        private static final zzf zzh;
        private static volatile zzgz<zzf> zzi;
        private int zzc;
        private int zzd;
        private boolean zzf;
        private String zze = "";
        private zzfn<String> zzg = zzfe.zzbp();

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public enum zza implements zzfg {
            UNKNOWN_MATCH_TYPE(0),
            REGEXP(1),
            BEGINS_WITH(2),
            ENDS_WITH(3),
            PARTIAL(4),
            EXACT(5),
            IN_LIST(6);

            private static final zzfj<zza> zzh = new zzbo();
            private final int zzi;

            @Override // com.google.android.gms.internal.measurement.zzfg
            public final int zza() {
                return this.zzi;
            }

            public static zza zza(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_MATCH_TYPE;
                    case 1:
                        return REGEXP;
                    case 2:
                        return BEGINS_WITH;
                    case 3:
                        return ENDS_WITH;
                    case 4:
                        return PARTIAL;
                    case 5:
                        return EXACT;
                    case 6:
                        return IN_LIST;
                    default:
                        return null;
                }
            }

            public static zzfi zzb() {
                return zzbn.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzi + " name=" + name() + Typography.greater;
            }

            zza(int i) {
                this.zzi = i;
            }
        }

        private zzf() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zzb extends zzfe.zza<zzf, zzb> implements zzgo {
            private zzb() {
                super(zzf.zzh);
            }

            /* synthetic */ zzb(zzbj zzbjVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final zza zzb() {
            zza zzaVarZza = zza.zza(this.zzd);
            return zzaVarZza == null ? zza.UNKNOWN_MATCH_TYPE : zzaVarZza;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public final List<String> zzg() {
            return this.zzg;
        }

        public final int zzh() {
            return this.zzg.size();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zzb(zzbjVar);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\f\u0000\u0002\b\u0001\u0003\u0007\u0002\u0004\u001a", new Object[]{"zzc", "zzd", zza.zzb(), "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzgz<zzf> zzcVar = zzi;
                    if (zzcVar == null) {
                        synchronized (zzf.class) {
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

        public static zzf zzi() {
            return zzh;
        }

        static {
            zzf zzfVar = new zzf();
            zzh = zzfVar;
            zzfe.zza((Class<zzf>) zzf.class, zzfVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zzc extends zzfe<zzc, zza> implements zzgo {
        private static final zzc zzh;
        private static volatile zzgz<zzc> zzi;
        private int zzc;
        private zzf zzd;
        private zzd zze;
        private boolean zzf;
        private String zzg = "";

        private zzc() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
        public static final class zza extends zzfe.zza<zzc, zza> implements zzgo {
            private zza() {
                super(zzc.zzh);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(str);
                return this;
            }

            /* synthetic */ zza(zzbj zzbjVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final zzf zzb() {
            zzf zzfVar = this.zzd;
            return zzfVar == null ? zzf.zzi() : zzfVar;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final zzd zzd() {
            zzd zzdVar = this.zze;
            return zzdVar == null ? zzd.zzk() : zzdVar;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return (this.zzc & 8) != 0;
        }

        public final String zzh() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 8;
            this.zzg = str;
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzbjVar);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\u0007\u0002\u0004\b\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzgz<zzc> zzcVar = zzi;
                    if (zzcVar == null) {
                        synchronized (zzc.class) {
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

        public static zzc zzi() {
            return zzh;
        }

        static {
            zzc zzcVar = new zzc();
            zzh = zzcVar;
            zzfe.zza((Class<zzc>) zzc.class, zzcVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    public static final class zze extends zzfe<zze, zza> implements zzgo {
        private static final zze zzj;
        private static volatile zzgz<zze> zzk;
        private int zzc;
        private int zzd;
        private String zze = "";
        private zzc zzf;
        private boolean zzg;
        private boolean zzh;
        private boolean zzi;

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

            /* synthetic */ zza(zzbj zzbjVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
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

        public final zzc zzd() {
            zzc zzcVar = this.zzf;
            return zzcVar == null ? zzc.zzi() : zzcVar;
        }

        public final boolean zze() {
            return this.zzg;
        }

        public final boolean zzf() {
            return this.zzh;
        }

        public final boolean zzg() {
            return (this.zzc & 32) != 0;
        }

        public final boolean zzh() {
            return this.zzi;
        }

        public static zza zzi() {
            return zzj.zzbk();
        }

        @Override // com.google.android.gms.internal.measurement.zzfe
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzbjVar);
                case 3:
                    return zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\t\u0002\u0004\u0007\u0003\u0005\u0007\u0004\u0006\u0007\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
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
}