package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzgk extends zzew {
    private final zzkj zza;
    private Boolean zzb;
    private String zzc;

    public zzgk(zzkj zzkjVar) {
        this(zzkjVar, null);
    }

    private zzgk(zzkj zzkjVar, String str) {
        Preconditions.checkNotNull(zzkjVar);
        this.zza = zzkjVar;
        this.zzc = null;
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zzb(zzm zzmVar) {
        zzb(zzmVar, false);
        zza(new zzgj(this, zzmVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zza(zzan zzanVar, zzm zzmVar) {
        Preconditions.checkNotNull(zzanVar);
        zzb(zzmVar, false);
        zza(new zzgs(this, zzanVar, zzmVar));
    }

    final zzan zzb(zzan zzanVar, zzm zzmVar) {
        boolean z = false;
        if ("_cmp".equals(zzanVar.zza) && zzanVar.zzb != null && zzanVar.zzb.zza() != 0) {
            String strZzd = zzanVar.zzb.zzd("_cis");
            if (!TextUtils.isEmpty(strZzd) && (("referrer broadcast".equals(strZzd) || "referrer API".equals(strZzd)) && this.zza.zzb().zze(zzmVar.zza, zzap.zzan))) {
                z = true;
            }
        }
        if (!z) {
            return zzanVar;
        }
        this.zza.zzr().zzv().zza("Event has been filtered ", zzanVar.toString());
        return new zzan("_cmpx", zzanVar.zzb, zzanVar.zzc, zzanVar.zzd);
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zza(zzan zzanVar, String str, String str2) {
        Preconditions.checkNotNull(zzanVar);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zza(new zzgr(this, zzanVar, str));
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final byte[] zza(zzan zzanVar, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzanVar);
        zza(str, true);
        this.zza.zzr().zzw().zza("Log and bundle. event", this.zza.zzi().zza(zzanVar.zza));
        long jNanoTime = this.zza.zzm().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzq().zzb(new zzgu(this, zzanVar, str)).get();
            if (bArr == null) {
                this.zza.zzr().zzf().zza("Log and bundle returned null. appId", zzfb.zza(str));
                bArr = new byte[0];
            }
            this.zza.zzr().zzw().zza("Log and bundle processed. event, size, time_ms", this.zza.zzi().zza(zzanVar.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzm().nanoTime() / 1000000) - jNanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzr().zzf().zza("Failed to log and bundle. appId, event, error", zzfb.zza(str), this.zza.zzi().zza(zzanVar.zza), e2);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zza(zzkq zzkqVar, zzm zzmVar) {
        Preconditions.checkNotNull(zzkqVar);
        zzb(zzmVar, false);
        zza(new zzgt(this, zzkqVar, zzmVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final List<zzkq> zza(zzm zzmVar, boolean z) {
        zzb(zzmVar, false);
        try {
            List<zzks> list = (List) this.zza.zzq().zza(new zzgw(this, zzmVar)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzks zzksVar : list) {
                if (z || !zzkv.zze(zzksVar.zzc)) {
                    arrayList.add(new zzkq(zzksVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e2) {
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zza.zzb().zze(zzmVar.zza, zzap.zzcx)) {
                this.zza.zzr().zzf().zza("Failed to get user properties. appId", zzfb.zza(zzmVar.zza), e2);
                return null;
            }
            this.zza.zzr().zzf().zza("Failed to get user attributes. appId", zzfb.zza(zzmVar.zza), e2);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zza(zzm zzmVar) {
        zzb(zzmVar, false);
        zza(new zzgv(this, zzmVar));
    }

    private final void zzb(zzm zzmVar, boolean z) {
        Preconditions.checkNotNull(zzmVar);
        zza(zzmVar.zza, false);
        this.zza.zzj().zza(zzmVar.zzb, zzmVar.zzr, zzmVar.zzv);
    }

    private final void zza(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.zza.zzr().zzf().zza("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzb == null) {
                    this.zzb = Boolean.valueOf("com.google.android.gms".equals(this.zzc) || UidVerifier.isGooglePlayServicesUid(this.zza.zzn(), Binder.getCallingUid()) || GoogleSignatureVerifier.getInstance(this.zza.zzn()).isUidGoogleSigned(Binder.getCallingUid()));
                }
                if (this.zzb.booleanValue()) {
                    return;
                }
            } catch (SecurityException e2) {
                this.zza.zzr().zzf().zza("Measurement Service called with invalid calling package. appId", zzfb.zza(str));
                throw e2;
            }
        }
        if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzn(), Binder.getCallingUid(), str)) {
            this.zzc = str;
        }
        if (str.equals(this.zzc)) {
        } else {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zza(long j, String str, String str2, String str3) {
        zza(new zzgy(this, str2, str3, str, j));
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final String zzc(zzm zzmVar) {
        zzb(zzmVar, false);
        return this.zza.zzd(zzmVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zza(zzv zzvVar, zzm zzmVar) {
        Preconditions.checkNotNull(zzvVar);
        Preconditions.checkNotNull(zzvVar.zzc);
        zzb(zzmVar, false);
        zzv zzvVar2 = new zzv(zzvVar);
        zzvVar2.zza = zzmVar.zza;
        zza(new zzgx(this, zzvVar2, zzmVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zza(zzv zzvVar) {
        Preconditions.checkNotNull(zzvVar);
        Preconditions.checkNotNull(zzvVar.zzc);
        zza(zzvVar.zza, true);
        zza(new zzgm(this, new zzv(zzvVar)));
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final List<zzkq> zza(String str, String str2, boolean z, zzm zzmVar) {
        zzb(zzmVar, false);
        try {
            List<zzks> list = (List) this.zza.zzq().zza(new zzgl(this, zzmVar, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzks zzksVar : list) {
                if (z || !zzkv.zze(zzksVar.zzc)) {
                    arrayList.add(new zzkq(zzksVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e2) {
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zza.zzb().zze(zzmVar.zza, zzap.zzcx)) {
                this.zza.zzr().zzf().zza("Failed to query user properties. appId", zzfb.zza(zzmVar.zza), e2);
            } else {
                this.zza.zzr().zzf().zza("Failed to get user attributes. appId", zzfb.zza(zzmVar.zza), e2);
            }
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final List<zzkq> zza(String str, String str2, String str3, boolean z) {
        zza(str, true);
        try {
            List<zzks> list = (List) this.zza.zzq().zza(new zzgo(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzks zzksVar : list) {
                if (z || !zzkv.zze(zzksVar.zzc)) {
                    arrayList.add(new zzkq(zzksVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e2) {
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zza.zzb().zze(str, zzap.zzcx)) {
                this.zza.zzr().zzf().zza("Failed to get user properties as. appId", zzfb.zza(str), e2);
            } else {
                this.zza.zzr().zzf().zza("Failed to get user attributes. appId", zzfb.zza(str), e2);
            }
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final List<zzv> zza(String str, String str2, zzm zzmVar) {
        zzb(zzmVar, false);
        try {
            return (List) this.zza.zzq().zza(new zzgn(this, zzmVar, str, str2)).get();
        } catch (InterruptedException | ExecutionException e2) {
            this.zza.zzr().zzf().zza("Failed to get conditional user properties", e2);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final List<zzv> zza(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zza.zzq().zza(new zzgq(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e2) {
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zza.zzb().zze(str, zzap.zzcx)) {
                this.zza.zzr().zzf().zza("Failed to get conditional user properties as", e2);
            } else {
                this.zza.zzr().zzf().zza("Failed to get conditional user properties", e2);
            }
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzet
    public final void zzd(zzm zzmVar) {
        zza(zzmVar.zza, false);
        zza(new zzgp(this, zzmVar));
    }

    private final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzq().zzg()) {
            runnable.run();
        } else {
            this.zza.zzq().zza(runnable);
        }
    }
}