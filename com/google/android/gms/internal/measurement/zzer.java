package com.google.android.gms.internal.measurement;

import androidx.core.internal.view.SupportMenu;
import com.google.android.gms.internal.measurement.zzfe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public class zzer {
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static volatile zzer zzc;
    private static volatile zzer zzd;
    private static final zzer zze = new zzer(true);
    private final Map<zza, zzfe.zzd<?, ?>> zzf;

    public static zzer zza() {
        zzer zzerVar = zzc;
        if (zzerVar == null) {
            synchronized (zzer.class) {
                zzerVar = zzc;
                if (zzerVar == null) {
                    zzerVar = zze;
                    zzc = zzerVar;
                }
            }
        }
        return zzerVar;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
    static final class zza {
        private final Object zza;
        private final int zzb;

        zza(Object obj, int i) {
            this.zza = obj;
            this.zzb = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * SupportMenu.USER_MASK) + this.zzb;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.zza == zzaVar.zza && this.zzb == zzaVar.zzb;
        }
    }

    public static zzer zzb() {
        zzer zzerVar = zzd;
        if (zzerVar != null) {
            return zzerVar;
        }
        synchronized (zzer.class) {
            zzer zzerVar2 = zzd;
            if (zzerVar2 != null) {
                return zzerVar2;
            }
            zzer zzerVarZza = zzfd.zza(zzer.class);
            zzd = zzerVarZza;
            return zzerVarZza;
        }
    }

    public final <ContainingType extends zzgm> zzfe.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzfe.zzd) this.zzf.get(new zza(containingtype, i));
    }

    zzer() {
        this.zzf = new HashMap();
    }

    private zzer(boolean z) {
        this.zzf = Collections.emptyMap();
    }
}