package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzln;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzim extends zzkk {
    public zzim(zzkj zzkjVar) {
        super(zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkk
    protected final boolean zze() {
        return false;
    }

    public final byte[] zza(zzan zzanVar, String str) {
        zzks next;
        zzg zzgVar;
        zzbs.zzg.zza zzaVar;
        zzbs.zzf.zza zzaVar2;
        Bundle bundle;
        byte[] bArr;
        long j;
        zzaj zzajVarZza;
        zzd();
        this.zzx.zzaf();
        Preconditions.checkNotNull(zzanVar);
        Preconditions.checkNotEmpty(str);
        if (!zzt().zze(str, zzap.zzbd)) {
            zzr().zzw().zza("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        }
        if (!"_iap".equals(zzanVar.zza) && !"_iapx".equals(zzanVar.zza)) {
            zzr().zzw().zza("Generating a payload for this event is not available. package_name, event_name", str, zzanVar.zza);
            return null;
        }
        zzbs.zzf.zza zzaVarZzb = zzbs.zzf.zzb();
        zzi().zzf();
        try {
            zzg zzgVarZzb = zzi().zzb(str);
            if (zzgVarZzb == null) {
                zzr().zzw().zza("Log and bundle not available. package_name", str);
                return new byte[0];
            }
            if (!zzgVarZzb.zzr()) {
                zzr().zzw().zza("Log and bundle disabled. package_name", str);
                return new byte[0];
            }
            zzbs.zzg.zza zzaVarZza = zzbs.zzg.zzbf().zza(1).zza("android");
            if (!TextUtils.isEmpty(zzgVarZzb.zzc())) {
                zzaVarZza.zzf(zzgVarZzb.zzc());
            }
            if (!TextUtils.isEmpty(zzgVarZzb.zzn())) {
                zzaVarZza.zze(zzgVarZzb.zzn());
            }
            if (!TextUtils.isEmpty(zzgVarZzb.zzl())) {
                zzaVarZza.zzg(zzgVarZzb.zzl());
            }
            if (zzgVarZzb.zzm() != -2147483648L) {
                zzaVarZza.zzh((int) zzgVarZzb.zzm());
            }
            zzaVarZza.zzf(zzgVarZzb.zzo()).zzk(zzgVarZzb.zzq());
            if (zzln.zzb() && zzt().zze(zzgVarZzb.zzc(), zzap.zzcf)) {
                if (!TextUtils.isEmpty(zzgVarZzb.zze())) {
                    zzaVarZza.zzk(zzgVarZzb.zze());
                } else if (!TextUtils.isEmpty(zzgVarZzb.zzg())) {
                    zzaVarZza.zzp(zzgVarZzb.zzg());
                } else if (!TextUtils.isEmpty(zzgVarZzb.zzf())) {
                    zzaVarZza.zzo(zzgVarZzb.zzf());
                }
            } else if (!TextUtils.isEmpty(zzgVarZzb.zze())) {
                zzaVarZza.zzk(zzgVarZzb.zze());
            } else if (!TextUtils.isEmpty(zzgVarZzb.zzf())) {
                zzaVarZza.zzo(zzgVarZzb.zzf());
            }
            zzaVarZza.zzh(zzgVarZzb.zzp());
            if (this.zzx.zzab() && zzt().zzd(zzaVarZza.zzj())) {
                zzaVarZza.zzj();
                if (!TextUtils.isEmpty(null)) {
                    zzaVarZza.zzn(null);
                }
            }
            Pair<String, Boolean> pairZza = zzs().zza(zzgVarZzb.zzc());
            if (zzgVarZzb.zzaf() && pairZza != null && !TextUtils.isEmpty((CharSequence) pairZza.first)) {
                zzaVarZza.zzh(zza((String) pairZza.first, Long.toString(zzanVar.zzd)));
                if (pairZza.second != null) {
                    zzaVarZza.zza(((Boolean) pairZza.second).booleanValue());
                }
            }
            zzl().zzaa();
            zzbs.zzg.zza zzaVarZzc = zzaVarZza.zzc(Build.MODEL);
            zzl().zzaa();
            zzaVarZzc.zzb(Build.VERSION.RELEASE).zzf((int) zzl().zzf()).zzd(zzl().zzg());
            zzaVarZza.zzi(zza(zzgVarZzb.zzd(), Long.toString(zzanVar.zzd)));
            if (!TextUtils.isEmpty(zzgVarZzb.zzi())) {
                zzaVarZza.zzl(zzgVarZzb.zzi());
            }
            String strZzc = zzgVarZzb.zzc();
            List<zzks> listZza = zzi().zza(strZzc);
            Iterator<zzks> it = listZza.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if ("_lte".equals(next.zzc)) {
                    break;
                }
            }
            if (next == null || next.zze == null) {
                zzks zzksVar = new zzks(strZzc, "auto", "_lte", zzm().currentTimeMillis(), 0L);
                listZza.add(zzksVar);
                zzi().zza(zzksVar);
            }
            if (zzt().zze(strZzc, zzap.zzaz)) {
                zzkr zzkrVarZzg = zzg();
                zzkrVarZzg.zzr().zzx().zza("Checking account type status for ad personalization signals");
                if (zzkrVarZzg.zzl().zzj()) {
                    String strZzc2 = zzgVarZzb.zzc();
                    if (zzgVarZzb.zzaf() && zzkrVarZzg.zzj().zze(strZzc2)) {
                        zzkrVarZzg.zzr().zzw().zza("Turning off ad personalization due to account type");
                        Iterator<zzks> it2 = listZza.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            if ("_npa".equals(it2.next().zzc)) {
                                it2.remove();
                                break;
                            }
                        }
                        listZza.add(new zzks(strZzc2, "auto", "_npa", zzkrVarZzg.zzm().currentTimeMillis(), 1L));
                    }
                }
            }
            zzbs.zzk[] zzkVarArr = new zzbs.zzk[listZza.size()];
            for (int i = 0; i < listZza.size(); i++) {
                zzbs.zzk.zza zzaVarZza2 = zzbs.zzk.zzj().zza(listZza.get(i).zzc).zza(listZza.get(i).zzd);
                zzg().zza(zzaVarZza2, listZza.get(i).zze);
                zzkVarArr[i] = (zzbs.zzk) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZza2.zzv());
            }
            zzaVarZza.zzb(Arrays.asList(zzkVarArr));
            Bundle bundleZzb = zzanVar.zzb.zzb();
            bundleZzb.putLong("_c", 1L);
            zzr().zzw().zza("Marking in-app purchase as real-time");
            bundleZzb.putLong("_r", 1L);
            bundleZzb.putString("_o", zzanVar.zzc);
            if (zzp().zzf(zzaVarZza.zzj())) {
                zzp().zza(bundleZzb, "_dbg", (Object) 1L);
                zzp().zza(bundleZzb, "_r", (Object) 1L);
            }
            zzaj zzajVarZza2 = zzi().zza(str, zzanVar.zza);
            if (zzajVarZza2 == null) {
                zzgVar = zzgVarZzb;
                zzaVar = zzaVarZza;
                zzaVar2 = zzaVarZzb;
                bundle = bundleZzb;
                bArr = null;
                zzajVarZza = new zzaj(str, zzanVar.zza, 0L, 0L, zzanVar.zzd, 0L, null, null, null, null);
                j = 0;
            } else {
                zzgVar = zzgVarZzb;
                zzaVar = zzaVarZza;
                zzaVar2 = zzaVarZzb;
                bundle = bundleZzb;
                bArr = null;
                j = zzajVarZza2.zzf;
                zzajVarZza = zzajVarZza2.zza(zzanVar.zzd);
            }
            zzi().zza(zzajVarZza);
            zzak zzakVar = new zzak(this.zzx, zzanVar.zzc, str, zzanVar.zza, zzanVar.zzd, j, bundle);
            zzbs.zzc.zza zzaVarZzb2 = zzbs.zzc.zzj().zza(zzakVar.zzc).zza(zzakVar.zzb).zzb(zzakVar.zzd);
            for (String str2 : zzakVar.zze) {
                zzbs.zze.zza zzaVarZza3 = zzbs.zze.zzk().zza(str2);
                zzg().zza(zzaVarZza3, zzakVar.zze.zza(str2));
                zzaVarZzb2.zza(zzaVarZza3);
            }
            zzbs.zzg.zza zzaVar3 = zzaVar;
            zzaVar3.zza(zzaVarZzb2).zza(zzbs.zzh.zza().zza(zzbs.zzd.zza().zza(zzajVarZza.zzc).zza(zzanVar.zza)));
            zzaVar3.zzc(e_().zza(zzgVar.zzc(), Collections.emptyList(), zzaVar3.zzd(), Long.valueOf(zzaVarZzb2.zzf()), Long.valueOf(zzaVarZzb2.zzf())));
            if (zzaVarZzb2.zze()) {
                zzaVar3.zzb(zzaVarZzb2.zzf()).zzc(zzaVarZzb2.zzf());
            }
            long jZzk = zzgVar.zzk();
            if (jZzk != 0) {
                zzaVar3.zze(jZzk);
            }
            long jZzj = zzgVar.zzj();
            if (jZzj != 0) {
                zzaVar3.zzd(jZzj);
            } else if (jZzk != 0) {
                zzaVar3.zzd(jZzk);
            }
            zzgVar.zzv();
            zzaVar3.zzg((int) zzgVar.zzs()).zzg(zzt().zze()).zza(zzm().currentTimeMillis()).zzb(Boolean.TRUE.booleanValue());
            zzbs.zzf.zza zzaVar4 = zzaVar2;
            zzaVar4.zza(zzaVar3);
            zzg zzgVar2 = zzgVar;
            zzgVar2.zza(zzaVar3.zzf());
            zzgVar2.zzb(zzaVar3.zzg());
            zzi().zza(zzgVar2);
            zzi().b_();
            try {
                return zzg().zzc(((zzbs.zzf) ((com.google.android.gms.internal.measurement.zzfe) zzaVar4.zzv())).zzbi());
            } catch (IOException e2) {
                zzr().zzf().zza("Data loss. Failed to bundle and serialize. appId", zzfb.zza(str), e2);
                return bArr;
            }
        } catch (SecurityException e3) {
            zzr().zzw().zza("Resettable device id encryption failed", e3.getMessage());
            return new byte[0];
        } catch (SecurityException e4) {
            zzr().zzw().zza("app instance id encryption failed", e4.getMessage());
            return new byte[0];
        } finally {
            zzi().zzh();
        }
    }

    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }
}