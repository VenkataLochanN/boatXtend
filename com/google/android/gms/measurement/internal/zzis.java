package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzis extends zze {
    private final zzjk zza;
    private zzet zzb;
    private volatile Boolean zzc;
    private final zzaf zzd;
    private final zzkg zze;
    private final List<Runnable> zzf;
    private final zzaf zzg;

    protected zzis(zzgf zzgfVar) {
        super(zzgfVar);
        this.zzf = new ArrayList();
        this.zze = new zzkg(zzgfVar.zzm());
        this.zza = new zzjk(this);
        this.zzd = new zzir(this, zzgfVar);
        this.zzg = new zzjc(this, zzgfVar);
    }

    @Override // com.google.android.gms.measurement.internal.zze
    protected final boolean zzz() {
        return false;
    }

    public final boolean zzab() {
        zzd();
        zzw();
        return this.zzb != null;
    }

    protected final void zzac() {
        zzd();
        zzw();
        zza(new zzjb(this, zza(true)));
    }

    final void zza(zzet zzetVar, AbstractSafeParcelable abstractSafeParcelable, zzm zzmVar) {
        int size;
        List<AbstractSafeParcelable> listZza;
        zzd();
        zzb();
        zzw();
        boolean zZzai = zzai();
        int i = 0;
        int i2 = 100;
        while (i < 1001 && i2 == 100) {
            ArrayList arrayList = new ArrayList();
            if (!zZzai || (listZza = zzj().zza(100)) == null) {
                size = 0;
            } else {
                arrayList.addAll(listZza);
                size = listZza.size();
            }
            if (abstractSafeParcelable != null && size < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size2 = arrayList2.size();
            int i3 = 0;
            while (i3 < size2) {
                Object obj = arrayList2.get(i3);
                i3++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzan) {
                    try {
                        zzetVar.zza((zzan) abstractSafeParcelable2, zzmVar);
                    } catch (RemoteException e2) {
                        zzr().zzf().zza("Failed to send event to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkq) {
                    try {
                        zzetVar.zza((zzkq) abstractSafeParcelable2, zzmVar);
                    } catch (RemoteException e3) {
                        zzr().zzf().zza("Failed to send user property to the service", e3);
                    }
                } else if (abstractSafeParcelable2 instanceof zzv) {
                    try {
                        zzetVar.zza((zzv) abstractSafeParcelable2, zzmVar);
                    } catch (RemoteException e4) {
                        zzr().zzf().zza("Failed to send conditional user property to the service", e4);
                    }
                } else {
                    zzr().zzf().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i++;
            i2 = size;
        }
    }

    protected final void zza(zzan zzanVar, String str) {
        Preconditions.checkNotNull(zzanVar);
        zzd();
        zzw();
        boolean zZzai = zzai();
        zza(new zzje(this, zZzai, zZzai && zzj().zza(zzanVar), zzanVar, zza(true), str));
    }

    protected final void zza(zzv zzvVar) {
        Preconditions.checkNotNull(zzvVar);
        zzd();
        zzw();
        zzu();
        zza(new zzjd(this, true, zzj().zza(zzvVar), new zzv(zzvVar), zza(true), zzvVar));
    }

    protected final void zza(AtomicReference<List<zzv>> atomicReference, String str, String str2, String str3) {
        zzd();
        zzw();
        zza(new zzjg(this, atomicReference, str, str2, str3, zza(false)));
    }

    protected final void zza(com.google.android.gms.internal.measurement.zzn zznVar, String str, String str2) {
        zzd();
        zzw();
        zza(new zzjf(this, str, str2, zza(false), zznVar));
    }

    protected final void zza(AtomicReference<List<zzkq>> atomicReference, String str, String str2, String str3, boolean z) {
        zzd();
        zzw();
        zza(new zzji(this, atomicReference, str, str2, str3, z, zza(false)));
    }

    protected final void zza(com.google.android.gms.internal.measurement.zzn zznVar, String str, String str2, boolean z) {
        zzd();
        zzw();
        zza(new zzjh(this, str, str2, z, zza(false), zznVar));
    }

    protected final void zza(zzkq zzkqVar) {
        zzd();
        zzw();
        zza(new zziu(this, zzai() && zzj().zza(zzkqVar), zzkqVar, zza(true)));
    }

    protected final void zza(AtomicReference<List<zzkq>> atomicReference, boolean z) {
        zzd();
        zzw();
        zza(new zzit(this, atomicReference, zza(false), z));
    }

    protected final void zzad() {
        zzd();
        zzb();
        zzw();
        zzm zzmVarZza = zza(false);
        if (zzai()) {
            zzj().zzab();
        }
        zza(new zziw(this, zzmVarZza));
    }

    private final boolean zzai() {
        zzu();
        return true;
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzd();
        zzw();
        zza(new zziv(this, atomicReference, zza(false)));
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar) {
        zzd();
        zzw();
        zza(new zziy(this, zza(false), zznVar));
    }

    protected final void zzae() {
        zzd();
        zzw();
        zzm zzmVarZza = zza(true);
        boolean zZza = zzt().zza(zzap.zzbx);
        if (zZza) {
            zzj().zzac();
        }
        zza(new zzix(this, zzmVarZza, zZza));
    }

    protected final void zza(zzio zzioVar) {
        zzd();
        zzw();
        zza(new zzja(this, zzioVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzaj() {
        zzd();
        this.zze.zza();
        this.zzd.zza(zzap.zzaf.zza(null).longValue());
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void zzaf() {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzis.zzaf():void");
    }

    final Boolean zzag() {
        return this.zzc;
    }

    protected final void zza(zzet zzetVar) {
        zzd();
        Preconditions.checkNotNull(zzetVar);
        this.zzb = zzetVar;
        zzaj();
        zzal();
    }

    public final void zzah() {
        zzd();
        zzw();
        this.zza.zza();
        try {
            ConnectionTracker.getInstance().unbindService(zzn(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(ComponentName componentName) {
        zzd();
        if (this.zzb != null) {
            this.zzb = null;
            zzr().zzx().zza("Disconnected from device MeasurementService", componentName);
            zzd();
            zzaf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzak() {
        zzd();
        if (zzab()) {
            zzr().zzx().zza("Inactivity, disconnecting from the service");
            zzah();
        }
    }

    private final void zza(Runnable runnable) throws IllegalStateException {
        zzd();
        if (zzab()) {
            runnable.run();
        } else {
            if (this.zzf.size() >= 1000) {
                zzr().zzf().zza("Discarding data. Max runnable queue size reached");
                return;
            }
            this.zzf.add(runnable);
            this.zzg.zza(DateUtil.MINUTE);
            zzaf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzal() {
        zzd();
        zzr().zzx().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        Iterator<Runnable> it = this.zzf.iterator();
        while (it.hasNext()) {
            try {
                it.next().run();
            } catch (Exception e2) {
                zzr().zzf().zza("Task exception while flushing queue", e2);
            }
        }
        this.zzf.clear();
        this.zzg.zzc();
    }

    private final zzm zza(boolean z) {
        zzu();
        return zzg().zza(z ? zzr().zzy() : null);
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, zzan zzanVar, String str) {
        zzd();
        zzw();
        if (zzp().zza(12451000) != 0) {
            zzr().zzi().zza("Not bundling data. Service unavailable or out of date");
            zzp().zza(zznVar, new byte[0]);
        } else {
            zza(new zziz(this, zzanVar, str, zznVar));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzb zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzhk zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzey zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzis zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzin zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzex zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzjt zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzah zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzez zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzkv zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzgc zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzfb zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzfo zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzw zzu() {
        return super.zzu();
    }

    static /* synthetic */ zzet zza(zzis zzisVar, zzet zzetVar) {
        zzisVar.zzb = null;
        return null;
    }
}