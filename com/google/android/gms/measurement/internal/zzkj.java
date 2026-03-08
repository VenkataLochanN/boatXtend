package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzln;
import com.ido.life.util.DateUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public class zzkj implements zzhc {
    private static volatile zzkj zza;
    private zzfz zzb;
    private zzff zzc;
    private zzac zzd;
    private zzfm zze;
    private zzkf zzf;
    private zzn zzg;
    private final zzkr zzh;
    private zzim zzi;
    private final zzgf zzj;
    private boolean zzk;
    private boolean zzl;
    private boolean zzm;
    private long zzn;
    private List<Runnable> zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private FileLock zzu;
    private FileChannel zzv;
    private List<Long> zzw;
    private List<Long> zzx;
    private long zzy;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
    class zza implements zzae {
        zzbs.zzg zza;
        List<Long> zzb;
        List<zzbs.zzc> zzc;
        private long zzd;

        private zza() {
        }

        @Override // com.google.android.gms.measurement.internal.zzae
        public final void zza(zzbs.zzg zzgVar) {
            Preconditions.checkNotNull(zzgVar);
            this.zza = zzgVar;
        }

        @Override // com.google.android.gms.measurement.internal.zzae
        public final boolean zza(long j, zzbs.zzc zzcVar) {
            Preconditions.checkNotNull(zzcVar);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (this.zzc.size() > 0 && zza(this.zzc.get(0)) != zza(zzcVar)) {
                return false;
            }
            long jZzbm = this.zzd + ((long) zzcVar.zzbm());
            if (jZzbm >= Math.max(0, zzap.zzh.zza(null).intValue())) {
                return false;
            }
            this.zzd = jZzbm;
            this.zzc.add(zzcVar);
            this.zzb.add(Long.valueOf(j));
            return this.zzc.size() < Math.max(1, zzap.zzi.zza(null).intValue());
        }

        private static long zza(zzbs.zzc zzcVar) {
            return ((zzcVar.zze() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzkj zzkjVar, zzkm zzkmVar) {
            this();
        }
    }

    public static zzkj zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzkj.class) {
                if (zza == null) {
                    zza = new zzkj(new zzkp(context));
                }
            }
        }
        return zza;
    }

    private zzkj(zzkp zzkpVar) {
        this(zzkpVar, null);
    }

    private zzkj(zzkp zzkpVar, zzgf zzgfVar) {
        this.zzk = false;
        Preconditions.checkNotNull(zzkpVar);
        this.zzj = zzgf.zza(zzkpVar.zza, (com.google.android.gms.internal.measurement.zzv) null);
        this.zzy = -1L;
        zzkr zzkrVar = new zzkr(this);
        zzkrVar.zzal();
        this.zzh = zzkrVar;
        zzff zzffVar = new zzff(this);
        zzffVar.zzal();
        this.zzc = zzffVar;
        zzfz zzfzVar = new zzfz(this);
        zzfzVar.zzal();
        this.zzb = zzfzVar;
        this.zzj.zzq().zza(new zzkm(this, zzkpVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzkp zzkpVar) {
        this.zzj.zzq().zzd();
        zzac zzacVar = new zzac(this);
        zzacVar.zzal();
        this.zzd = zzacVar;
        this.zzj.zzb().zza(this.zzb);
        zzn zznVar = new zzn(this);
        zznVar.zzal();
        this.zzg = zznVar;
        zzim zzimVar = new zzim(this);
        zzimVar.zzal();
        this.zzi = zzimVar;
        zzkf zzkfVar = new zzkf(this);
        zzkfVar.zzal();
        this.zzf = zzkfVar;
        this.zze = new zzfm(this);
        if (this.zzp != this.zzq) {
            this.zzj.zzr().zzf().zza("Not all upload components initialized", Integer.valueOf(this.zzp), Integer.valueOf(this.zzq));
        }
        this.zzk = true;
    }

    protected final void zza() {
        this.zzj.zzq().zzd();
        zze().zzv();
        if (this.zzj.zzc().zzc.zza() == 0) {
            this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
        }
        zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final zzw zzu() {
        return this.zzj.zzu();
    }

    public final zzx zzb() {
        return this.zzj.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final zzfb zzr() {
        return this.zzj.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final zzgc zzq() {
        return this.zzj.zzq();
    }

    public final zzfz zzc() {
        zzb(this.zzb);
        return this.zzb;
    }

    public final zzff zzd() {
        zzb(this.zzc);
        return this.zzc;
    }

    public final zzac zze() {
        zzb(this.zzd);
        return this.zzd;
    }

    private final zzfm zzt() {
        zzfm zzfmVar = this.zze;
        if (zzfmVar != null) {
            return zzfmVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzkf zzv() {
        zzb(this.zzf);
        return this.zzf;
    }

    public final zzn zzf() {
        zzb(this.zzg);
        return this.zzg;
    }

    public final zzim zzg() {
        zzb(this.zzi);
        return this.zzi;
    }

    public final zzkr zzh() {
        zzb(this.zzh);
        return this.zzh;
    }

    public final zzez zzi() {
        return this.zzj.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final Context zzn() {
        return this.zzj.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final Clock zzm() {
        return this.zzj.zzm();
    }

    public final zzkv zzj() {
        return this.zzj.zzi();
    }

    private final void zzw() {
        this.zzj.zzq().zzd();
    }

    final void zzk() {
        if (!this.zzk) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zzb(zzkk zzkkVar) {
        if (zzkkVar == null) {
            throw new IllegalStateException("Upload Component not created");
        }
        if (zzkkVar.zzaj()) {
            return;
        }
        String strValueOf = String.valueOf(zzkkVar.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 27);
        sb.append("Component not initialized: ");
        sb.append(strValueOf);
        throw new IllegalStateException(sb.toString());
    }

    private final long zzx() {
        long jCurrentTimeMillis = this.zzj.zzm().currentTimeMillis();
        zzfo zzfoVarZzc = this.zzj.zzc();
        zzfoVarZzc.zzaa();
        zzfoVarZzc.zzd();
        long jZza = zzfoVarZzc.zzg.zza();
        if (jZza == 0) {
            jZza = 1 + ((long) zzfoVarZzc.zzp().zzh().nextInt(86400000));
            zzfoVarZzc.zzg.zza(jZza);
        }
        return ((((jCurrentTimeMillis + jZza) / 1000) / 60) / 60) / 24;
    }

    final void zza(zzan zzanVar, String str) {
        zzg zzgVarZzb = zze().zzb(str);
        if (zzgVarZzb == null || TextUtils.isEmpty(zzgVarZzb.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping event", str);
            return;
        }
        Boolean boolZzb = zzb(zzgVarZzb);
        if (boolZzb == null) {
            if (!"_ui".equals(zzanVar.zza)) {
                this.zzj.zzr().zzi().zza("Could not find package. appId", zzfb.zza(str));
            }
        } else if (!boolZzb.booleanValue()) {
            this.zzj.zzr().zzf().zza("App version does not match; dropping event. appId", zzfb.zza(str));
            return;
        }
        zza(zzanVar, new zzm(str, zzgVarZzb.zze(), zzgVarZzb.zzl(), zzgVarZzb.zzm(), zzgVarZzb.zzn(), zzgVarZzb.zzo(), zzgVarZzb.zzp(), (String) null, zzgVarZzb.zzr(), false, zzgVarZzb.zzi(), zzgVarZzb.zzae(), 0L, 0, zzgVarZzb.zzaf(), zzgVarZzb.zzag(), false, zzgVarZzb.zzf(), zzgVarZzb.zzah(), zzgVarZzb.zzq(), zzgVarZzb.zzai(), (zzln.zzb() && this.zzj.zzb().zze(zzgVarZzb.zzc(), zzap.zzcf)) ? zzgVarZzb.zzg() : null));
    }

    final void zza(zzan zzanVar, zzm zzmVar) {
        List<zzv> listZza;
        List<zzv> listZza2;
        List<zzv> listZza3;
        zzan zzanVar2 = zzanVar;
        Preconditions.checkNotNull(zzmVar);
        Preconditions.checkNotEmpty(zzmVar.zza);
        zzw();
        zzk();
        String str = zzmVar.zza;
        long j = zzanVar2.zzd;
        if (zzh().zza(zzanVar2, zzmVar)) {
            if (!zzmVar.zzh) {
                zzc(zzmVar);
                return;
            }
            if (this.zzj.zzb().zze(str, zzap.zzbj) && zzmVar.zzu != null) {
                if (zzmVar.zzu.contains(zzanVar2.zza)) {
                    Bundle bundleZzb = zzanVar2.zzb.zzb();
                    bundleZzb.putLong("ga_safelisted", 1L);
                    zzanVar2 = new zzan(zzanVar2.zza, new zzam(bundleZzb), zzanVar2.zzc, zzanVar2.zzd);
                } else {
                    this.zzj.zzr().zzw().zza("Dropping non-safelisted event. appId, event name, origin", str, zzanVar2.zza, zzanVar2.zzc);
                    return;
                }
            }
            zze().zzf();
            try {
                zzac zzacVarZze = zze();
                Preconditions.checkNotEmpty(str);
                zzacVarZze.zzd();
                zzacVarZze.zzak();
                if (j < 0) {
                    zzacVarZze.zzr().zzi().zza("Invalid time querying timed out conditional properties", zzfb.zza(str), Long.valueOf(j));
                    listZza = Collections.emptyList();
                } else {
                    listZza = zzacVarZze.zza("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzv zzvVar : listZza) {
                    if (zzvVar != null) {
                        if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzj.zzb().zze(zzmVar.zza, zzap.zzcx)) {
                            this.zzj.zzr().zzx().zza("User property timed out", zzvVar.zza, this.zzj.zzj().zzc(zzvVar.zzc.zza), zzvVar.zzc.zza());
                        } else {
                            this.zzj.zzr().zzw().zza("User property timed out", zzvVar.zza, this.zzj.zzj().zzc(zzvVar.zzc.zza), zzvVar.zzc.zza());
                        }
                        if (zzvVar.zzg != null) {
                            zzb(new zzan(zzvVar.zzg, j), zzmVar);
                        }
                        zze().zze(str, zzvVar.zzc.zza);
                    }
                }
                zzac zzacVarZze2 = zze();
                Preconditions.checkNotEmpty(str);
                zzacVarZze2.zzd();
                zzacVarZze2.zzak();
                if (j < 0) {
                    zzacVarZze2.zzr().zzi().zza("Invalid time querying expired conditional properties", zzfb.zza(str), Long.valueOf(j));
                    listZza2 = Collections.emptyList();
                } else {
                    listZza2 = zzacVarZze2.zza("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(listZza2.size());
                for (zzv zzvVar2 : listZza2) {
                    if (zzvVar2 != null) {
                        if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzj.zzb().zze(zzmVar.zza, zzap.zzcx)) {
                            this.zzj.zzr().zzx().zza("User property expired", zzvVar2.zza, this.zzj.zzj().zzc(zzvVar2.zzc.zza), zzvVar2.zzc.zza());
                        } else {
                            this.zzj.zzr().zzw().zza("User property expired", zzvVar2.zza, this.zzj.zzj().zzc(zzvVar2.zzc.zza), zzvVar2.zzc.zza());
                        }
                        zze().zzb(str, zzvVar2.zzc.zza);
                        if (zzvVar2.zzk != null) {
                            arrayList.add(zzvVar2.zzk);
                        }
                        zze().zze(str, zzvVar2.zzc.zza);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzb(new zzan((zzan) obj, j), zzmVar);
                }
                zzac zzacVarZze3 = zze();
                String str2 = zzanVar2.zza;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zzacVarZze3.zzd();
                zzacVarZze3.zzak();
                if (j < 0) {
                    zzacVarZze3.zzr().zzi().zza("Invalid time querying triggered conditional properties", zzfb.zza(str), zzacVarZze3.zzo().zza(str2), Long.valueOf(j));
                    listZza3 = Collections.emptyList();
                } else {
                    listZza3 = zzacVarZze3.zza("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(listZza3.size());
                for (zzv zzvVar3 : listZza3) {
                    if (zzvVar3 != null) {
                        zzkq zzkqVar = zzvVar3.zzc;
                        zzks zzksVar = new zzks(zzvVar3.zza, zzvVar3.zzb, zzkqVar.zza, j, zzkqVar.zza());
                        if (zze().zza(zzksVar)) {
                            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzj.zzb().zze(zzmVar.zza, zzap.zzcx)) {
                                this.zzj.zzr().zzx().zza("User property triggered", zzvVar3.zza, this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                            } else {
                                this.zzj.zzr().zzw().zza("User property triggered", zzvVar3.zza, this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                            }
                        } else {
                            this.zzj.zzr().zzf().zza("Too many active user properties, ignoring", zzfb.zza(zzvVar3.zza), this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                        }
                        if (zzvVar3.zzi != null) {
                            arrayList3.add(zzvVar3.zzi);
                        }
                        zzvVar3.zzc = new zzkq(zzksVar);
                        zzvVar3.zze = true;
                        zze().zza(zzvVar3);
                    }
                }
                zzb(zzanVar2, zzmVar);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj2 = arrayList4.get(i2);
                    i2++;
                    zzb(new zzan((zzan) obj2, j), zzmVar);
                }
                zze().b_();
            } finally {
                zze().zzh();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:248:0x088b A[Catch: all -> 0x0916, TryCatch #1 {all -> 0x0916, blocks: (B:33:0x0108, B:36:0x0117, B:83:0x02b6, B:85:0x02f5, B:87:0x02fb, B:88:0x0314, B:92:0x0325, B:94:0x0339, B:96:0x033f, B:97:0x0358, B:101:0x037b, B:105:0x03a0, B:106:0x03b9, B:109:0x03c8, B:112:0x03eb, B:113:0x0407, B:116:0x0411, B:118:0x0421, B:120:0x042d, B:122:0x0433, B:123:0x043e, B:125:0x0446, B:127:0x0456, B:129:0x0466, B:130:0x046e, B:132:0x047a, B:133:0x0491, B:135:0x04bb, B:138:0x04cb, B:141:0x0506, B:143:0x052d, B:145:0x0567, B:146:0x056c, B:148:0x0574, B:149:0x0579, B:151:0x0581, B:152:0x0586, B:154:0x058f, B:155:0x0595, B:157:0x05a2, B:158:0x05a7, B:160:0x05ad, B:162:0x05bd, B:164:0x05c7, B:166:0x05cf, B:167:0x05d4, B:169:0x05de, B:171:0x05e8, B:173:0x05f0, B:184:0x0629, B:186:0x0631, B:187:0x0636, B:189:0x064b, B:191:0x0655, B:192:0x0658, B:194:0x0666, B:196:0x0670, B:198:0x0674, B:200:0x067f, B:212:0x06ed, B:214:0x0735, B:216:0x073e, B:217:0x0743, B:219:0x074f, B:220:0x07b6, B:222:0x07c0, B:223:0x07c7, B:225:0x07d1, B:226:0x07d8, B:227:0x07e3, B:229:0x07e9, B:231:0x081a, B:232:0x082a, B:234:0x0832, B:235:0x0838, B:237:0x083e, B:246:0x0885, B:248:0x088b, B:251:0x08a7, B:253:0x08b4, B:255:0x08c4, B:257:0x08d1, B:240:0x084b, B:242:0x0870, B:250:0x088f, B:201:0x068b, B:203:0x069d, B:205:0x06a1, B:207:0x06b3, B:211:0x06ea, B:208:0x06cd, B:210:0x06d3, B:174:0x05f6, B:176:0x0604, B:178:0x060e, B:180:0x0616, B:181:0x061c, B:183:0x0624, B:142:0x051f, B:40:0x0125, B:43:0x0138, B:45:0x014f, B:50:0x0168, B:53:0x0194, B:55:0x019a, B:57:0x01a8, B:59:0x01b4, B:61:0x01be, B:63:0x01c9, B:66:0x01d0, B:74:0x0266, B:76:0x0270, B:80:0x02a7, B:67:0x01ff, B:68:0x021d, B:73:0x024b, B:72:0x023a, B:60:0x01b9, B:51:0x016d, B:52:0x018a), top: B:265:0x0108, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02a7 A[Catch: all -> 0x0916, TRY_LEAVE, TryCatch #1 {all -> 0x0916, blocks: (B:33:0x0108, B:36:0x0117, B:83:0x02b6, B:85:0x02f5, B:87:0x02fb, B:88:0x0314, B:92:0x0325, B:94:0x0339, B:96:0x033f, B:97:0x0358, B:101:0x037b, B:105:0x03a0, B:106:0x03b9, B:109:0x03c8, B:112:0x03eb, B:113:0x0407, B:116:0x0411, B:118:0x0421, B:120:0x042d, B:122:0x0433, B:123:0x043e, B:125:0x0446, B:127:0x0456, B:129:0x0466, B:130:0x046e, B:132:0x047a, B:133:0x0491, B:135:0x04bb, B:138:0x04cb, B:141:0x0506, B:143:0x052d, B:145:0x0567, B:146:0x056c, B:148:0x0574, B:149:0x0579, B:151:0x0581, B:152:0x0586, B:154:0x058f, B:155:0x0595, B:157:0x05a2, B:158:0x05a7, B:160:0x05ad, B:162:0x05bd, B:164:0x05c7, B:166:0x05cf, B:167:0x05d4, B:169:0x05de, B:171:0x05e8, B:173:0x05f0, B:184:0x0629, B:186:0x0631, B:187:0x0636, B:189:0x064b, B:191:0x0655, B:192:0x0658, B:194:0x0666, B:196:0x0670, B:198:0x0674, B:200:0x067f, B:212:0x06ed, B:214:0x0735, B:216:0x073e, B:217:0x0743, B:219:0x074f, B:220:0x07b6, B:222:0x07c0, B:223:0x07c7, B:225:0x07d1, B:226:0x07d8, B:227:0x07e3, B:229:0x07e9, B:231:0x081a, B:232:0x082a, B:234:0x0832, B:235:0x0838, B:237:0x083e, B:246:0x0885, B:248:0x088b, B:251:0x08a7, B:253:0x08b4, B:255:0x08c4, B:257:0x08d1, B:240:0x084b, B:242:0x0870, B:250:0x088f, B:201:0x068b, B:203:0x069d, B:205:0x06a1, B:207:0x06b3, B:211:0x06ea, B:208:0x06cd, B:210:0x06d3, B:174:0x05f6, B:176:0x0604, B:178:0x060e, B:180:0x0616, B:181:0x061c, B:183:0x0624, B:142:0x051f, B:40:0x0125, B:43:0x0138, B:45:0x014f, B:50:0x0168, B:53:0x0194, B:55:0x019a, B:57:0x01a8, B:59:0x01b4, B:61:0x01be, B:63:0x01c9, B:66:0x01d0, B:74:0x0266, B:76:0x0270, B:80:0x02a7, B:67:0x01ff, B:68:0x021d, B:73:0x024b, B:72:0x023a, B:60:0x01b9, B:51:0x016d, B:52:0x018a), top: B:265:0x0108, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzb(com.google.android.gms.measurement.internal.zzan r29, com.google.android.gms.measurement.internal.zzm r30) {
        /*
            Method dump skipped, instruction units count: 2336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zzb(com.google.android.gms.measurement.internal.zzan, com.google.android.gms.measurement.internal.zzm):void");
    }

    final void zzl() {
        zzg zzgVarZzb;
        String strZzad;
        zzw();
        zzk();
        this.zzt = true;
        try {
            this.zzj.zzu();
            Boolean boolZzag = this.zzj.zzw().zzag();
            if (boolZzag == null) {
                this.zzj.zzr().zzi().zza("Upload data called on the client side before use of service was decided");
                return;
            }
            if (boolZzag.booleanValue()) {
                this.zzj.zzr().zzf().zza("Upload called in the client side when service should be used");
                return;
            }
            if (this.zzn > 0) {
                zzz();
                return;
            }
            zzw();
            if (this.zzw != null) {
                this.zzj.zzr().zzx().zza("Uploading requested multiple times");
                return;
            }
            if (!zzd().zzf()) {
                this.zzj.zzr().zzx().zza("Network not connected, ignoring upload request");
                zzz();
                return;
            }
            long jCurrentTimeMillis = this.zzj.zzm().currentTimeMillis();
            zza((String) null, jCurrentTimeMillis - zzx.zzk());
            long jZza = this.zzj.zzc().zzc.zza();
            if (jZza != 0) {
                this.zzj.zzr().zzw().zza("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(jCurrentTimeMillis - jZza)));
            }
            String strD_ = zze().d_();
            if (!TextUtils.isEmpty(strD_)) {
                if (this.zzy == -1) {
                    this.zzy = zze().zzaa();
                }
                List<Pair<zzbs.zzg, Long>> listZza = zze().zza(strD_, this.zzj.zzb().zzb(strD_, zzap.zzf), Math.max(0, this.zzj.zzb().zzb(strD_, zzap.zzg)));
                if (!listZza.isEmpty()) {
                    Iterator<Pair<zzbs.zzg, Long>> it = listZza.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            strZzad = null;
                            break;
                        }
                        zzbs.zzg zzgVar = (zzbs.zzg) it.next().first;
                        if (!TextUtils.isEmpty(zzgVar.zzad())) {
                            strZzad = zzgVar.zzad();
                            break;
                        }
                    }
                    if (strZzad != null) {
                        int i = 0;
                        while (true) {
                            if (i >= listZza.size()) {
                                break;
                            }
                            zzbs.zzg zzgVar2 = (zzbs.zzg) listZza.get(i).first;
                            if (!TextUtils.isEmpty(zzgVar2.zzad()) && !zzgVar2.zzad().equals(strZzad)) {
                                listZza = listZza.subList(0, i);
                                break;
                            }
                            i++;
                        }
                    }
                    zzbs.zzf.zza zzaVarZzb = zzbs.zzf.zzb();
                    int size = listZza.size();
                    ArrayList arrayList = new ArrayList(listZza.size());
                    boolean zZzd = this.zzj.zzb().zzd(strD_);
                    for (int i2 = 0; i2 < size; i2++) {
                        zzbs.zzg.zza zzaVarZzbl = ((zzbs.zzg) listZza.get(i2).first).zzbl();
                        arrayList.add((Long) listZza.get(i2).second);
                        zzbs.zzg.zza zzaVarZza = zzaVarZzbl.zzg(this.zzj.zzb().zze()).zza(jCurrentTimeMillis);
                        this.zzj.zzu();
                        zzaVarZza.zzb(false);
                        if (!zZzd) {
                            zzaVarZzbl.zzn();
                        }
                        if (this.zzj.zzb().zze(strD_, zzap.zzbe)) {
                            zzaVarZzbl.zzl(zzh().zza(((zzbs.zzg) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzbl.zzv())).zzbi()));
                        }
                        zzaVarZzb.zza(zzaVarZzbl);
                    }
                    String strZza = this.zzj.zzr().zza(2) ? zzh().zza((zzbs.zzf) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzb.zzv())) : null;
                    zzh();
                    byte[] bArrZzbi = ((zzbs.zzf) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzb.zzv())).zzbi();
                    String strZza2 = zzap.zzp.zza(null);
                    try {
                        URL url = new URL(strZza2);
                        Preconditions.checkArgument(!arrayList.isEmpty());
                        if (this.zzw != null) {
                            this.zzj.zzr().zzf().zza("Set uploading progress before finishing the previous upload");
                        } else {
                            this.zzw = new ArrayList(arrayList);
                        }
                        this.zzj.zzc().zzd.zza(jCurrentTimeMillis);
                        this.zzj.zzr().zzx().zza("Uploading data. app, uncompressed size, data", size > 0 ? zzaVarZzb.zza(0).zzx() : "?", Integer.valueOf(bArrZzbi.length), strZza);
                        this.zzs = true;
                        zzff zzffVarZzd = zzd();
                        zzkl zzklVar = new zzkl(this, strD_);
                        zzffVarZzd.zzd();
                        zzffVarZzd.zzak();
                        Preconditions.checkNotNull(url);
                        Preconditions.checkNotNull(bArrZzbi);
                        Preconditions.checkNotNull(zzklVar);
                        zzffVarZzd.zzq().zzb(new zzfj(zzffVarZzd, strD_, url, bArrZzbi, null, zzklVar));
                    } catch (MalformedURLException unused) {
                        this.zzj.zzr().zzf().zza("Failed to parse upload URL. Not uploading. appId", zzfb.zza(strD_), strZza2);
                    }
                }
            } else {
                this.zzy = -1L;
                String strZza3 = zze().zza(jCurrentTimeMillis - zzx.zzk());
                if (!TextUtils.isEmpty(strZza3) && (zzgVarZzb = zze().zzb(strZza3)) != null) {
                    zza(zzgVarZzb);
                }
            }
        } finally {
            this.zzt = false;
            zzaa();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:123:0x028f A[Catch: all -> 0x0fa1, TRY_ENTER, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0296 A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02a4 A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x05d6 A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x06b3 A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x07b6  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x081a  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0869 A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:362:0x095c A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:437:0x0bac A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0bbf  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0bc2 A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:441:0x0be9 A[Catch: all -> 0x0fa1, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x011d A[Catch: all -> 0x0135, SQLiteException -> 0x013b, TRY_ENTER, TRY_LEAVE, TryCatch #16 {SQLiteException -> 0x013b, all -> 0x0135, blocks: (B:50:0x011d, B:62:0x015c, B:66:0x0177), top: B:586:0x011b }] */
    /* JADX WARN: Removed duplicated region for block: B:553:0x0f87 A[Catch: all -> 0x0fa1, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0f9d A[Catch: all -> 0x0fa1, TRY_ENTER, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:569:0x0140 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:627:? A[Catch: all -> 0x0fa1, SYNTHETIC, TRY_LEAVE, TryCatch #5 {all -> 0x0fa1, blocks: (B:3:0x0009, B:25:0x0083, B:124:0x0292, B:126:0x0296, B:132:0x02a4, B:133:0x02cf, B:136:0x02df, B:139:0x0305, B:141:0x033c, B:147:0x0352, B:149:0x035c, B:311:0x0832, B:151:0x0384, B:154:0x039a, B:183:0x03fb, B:186:0x0405, B:188:0x0413, B:192:0x0462, B:189:0x0434, B:191:0x0444, B:196:0x046f, B:199:0x04a2, B:200:0x04d0, B:202:0x0503, B:204:0x0509, B:207:0x0515, B:209:0x054a, B:210:0x0567, B:212:0x056d, B:214:0x057b, B:218:0x0590, B:215:0x0585, B:221:0x0597, B:223:0x059d, B:224:0x05bb, B:226:0x05d6, B:227:0x05e2, B:229:0x05e8, B:235:0x0610, B:232:0x05fd, B:238:0x0616, B:240:0x0622, B:242:0x062e, B:258:0x0680, B:261:0x069f, B:263:0x06b3, B:265:0x06bd, B:268:0x06d2, B:270:0x06e5, B:272:0x06f3, B:300:0x07bc, B:302:0x07c6, B:304:0x07cc, B:305:0x07e6, B:307:0x07f9, B:308:0x0813, B:310:0x081c, B:277:0x070e, B:279:0x071c, B:282:0x072f, B:284:0x0742, B:286:0x0750, B:288:0x075d, B:290:0x0773, B:292:0x077f, B:295:0x0792, B:297:0x07a5, B:246:0x0652, B:250:0x0666, B:252:0x066c, B:255:0x0677, B:161:0x03bc, B:164:0x03c6, B:167:0x03d0, B:316:0x0848, B:318:0x0856, B:320:0x0861, B:331:0x0893, B:321:0x0869, B:323:0x0872, B:325:0x0878, B:328:0x0884, B:330:0x088e, B:333:0x0898, B:336:0x08b0, B:337:0x08b8, B:339:0x08be, B:344:0x08d5, B:345:0x08e0, B:347:0x08e6, B:349:0x08f8, B:354:0x0905, B:356:0x090b, B:358:0x0914, B:360:0x0928, B:361:0x0942, B:366:0x097e, B:368:0x0990, B:370:0x09af, B:372:0x09bd, B:374:0x09c3, B:376:0x09cd, B:377:0x09ff, B:379:0x0a05, B:381:0x0a15, B:385:0x0a20, B:382:0x0a1a, B:386:0x0a23, B:388:0x0a35, B:389:0x0a38, B:391:0x0a75, B:392:0x0a8a, B:394:0x0a90, B:396:0x0aa8, B:398:0x0ac3, B:399:0x0ad4, B:401:0x0ad8, B:403:0x0ae4, B:404:0x0aee, B:406:0x0af2, B:408:0x0afa, B:409:0x0b08, B:410:0x0b13, B:488:0x0da2, B:412:0x0b1e, B:416:0x0b52, B:417:0x0b5a, B:419:0x0b60, B:421:0x0b72, B:423:0x0b76, B:437:0x0bac, B:440:0x0bc2, B:441:0x0be9, B:443:0x0bf5, B:445:0x0c0b, B:447:0x0c38, B:448:0x0c5e, B:451:0x0c85, B:455:0x0c9d, B:457:0x0ca4, B:459:0x0cb5, B:461:0x0cb9, B:463:0x0cbd, B:465:0x0cc1, B:466:0x0ccd, B:467:0x0cd2, B:469:0x0cd8, B:471:0x0cf9, B:472:0x0d02, B:487:0x0d9f, B:473:0x0d17, B:475:0x0d1e, B:479:0x0d40, B:481:0x0d6c, B:482:0x0d7a, B:483:0x0d8a, B:485:0x0d90, B:476:0x0d29, B:425:0x0b84, B:427:0x0b88, B:429:0x0b92, B:431:0x0b96, B:489:0x0dab, B:491:0x0db8, B:492:0x0dbf, B:493:0x0dc7, B:495:0x0dcd, B:497:0x0de4, B:499:0x0df6, B:500:0x0df9, B:502:0x0e0b, B:522:0x0e80, B:524:0x0e86, B:526:0x0e9b, B:529:0x0ea2, B:534:0x0ed5, B:530:0x0eaa, B:532:0x0eb6, B:533:0x0ebc, B:535:0x0ee6, B:536:0x0efd, B:539:0x0f05, B:540:0x0f0a, B:541:0x0f1a, B:543:0x0f34, B:544:0x0f4d, B:545:0x0f55, B:550:0x0f77, B:549:0x0f66, B:503:0x0e25, B:505:0x0e2b, B:507:0x0e35, B:509:0x0e3c, B:515:0x0e4c, B:517:0x0e53, B:519:0x0e72, B:521:0x0e79, B:520:0x0e76, B:516:0x0e50, B:508:0x0e39, B:362:0x095c, B:363:0x0961, B:365:0x0973, B:553:0x0f87, B:52:0x0130, B:76:0x01d3, B:84:0x020c, B:92:0x022c, B:559:0x0f9d, B:560:0x0fa0, B:123:0x028f, B:102:0x0252, B:43:0x00e0, B:59:0x0144), top: B:567:0x0009, inners: #13, #18 }] */
    /* JADX WARN: Type inference failed for: r22v0 */
    /* JADX WARN: Type inference failed for: r22v1 */
    /* JADX WARN: Type inference failed for: r22v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r22v3 */
    /* JADX WARN: Type inference failed for: r22v4 */
    /* JADX WARN: Type inference failed for: r22v5 */
    /* JADX WARN: Type inference failed for: r22v6 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v109 */
    /* JADX WARN: Type inference failed for: r6v110 */
    /* JADX WARN: Type inference failed for: r6v111 */
    /* JADX WARN: Type inference failed for: r6v112 */
    /* JADX WARN: Type inference failed for: r6v113 */
    /* JADX WARN: Type inference failed for: r6v114 */
    /* JADX WARN: Type inference failed for: r6v115 */
    /* JADX WARN: Type inference failed for: r6v117, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v118 */
    /* JADX WARN: Type inference failed for: r6v123 */
    /* JADX WARN: Type inference failed for: r6v127, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v128, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v130, types: [com.google.android.gms.measurement.internal.zzfd] */
    /* JADX WARN: Type inference failed for: r6v133 */
    /* JADX WARN: Type inference failed for: r6v134 */
    /* JADX WARN: Type inference failed for: r6v135 */
    /* JADX WARN: Type inference failed for: r6v136 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zza(java.lang.String r64, long r65) {
        /*
            Method dump skipped, instruction units count: 4011
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zza(java.lang.String, long):boolean");
    }

    private static void zza(zzbs.zzg.zza zzaVar) {
        zzaVar.zzb(LongCompanionObject.MAX_VALUE).zzc(Long.MIN_VALUE);
        for (int i = 0; i < zzaVar.zzb(); i++) {
            zzbs.zzc zzcVarZzb = zzaVar.zzb(i);
            if (zzcVarZzb.zze() < zzaVar.zzf()) {
                zzaVar.zzb(zzcVarZzb.zze());
            }
            if (zzcVarZzb.zze() > zzaVar.zzg()) {
                zzaVar.zzc(zzcVarZzb.zze());
            }
        }
    }

    private final void zza(zzbs.zzg.zza zzaVar, long j, boolean z) {
        zzks zzksVar;
        String str = z ? "_se" : "_lte";
        zzks zzksVarZzc = zze().zzc(zzaVar.zzj(), str);
        if (zzksVarZzc == null || zzksVarZzc.zze == null) {
            zzksVar = new zzks(zzaVar.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzksVar = new zzks(zzaVar.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(((Long) zzksVarZzc.zze).longValue() + j));
        }
        zzbs.zzk zzkVar = (zzbs.zzk) ((com.google.android.gms.internal.measurement.zzfe) zzbs.zzk.zzj().zza(str).zza(this.zzj.zzm().currentTimeMillis()).zzb(((Long) zzksVar.zze).longValue()).zzv());
        boolean z2 = false;
        int iZza = zzkr.zza(zzaVar, str);
        if (iZza >= 0) {
            zzaVar.zza(iZza, zzkVar);
            z2 = true;
        }
        if (!z2) {
            zzaVar.zza(zzkVar);
        }
        if (j > 0) {
            zze().zza(zzksVar);
            String str2 = z ? "session-scoped" : "lifetime";
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzj.zzb().zze(zzaVar.zzj(), zzap.zzcx)) {
                this.zzj.zzr().zzx().zza("Updated engagement user property. scope, value", str2, zzksVar.zze);
            } else {
                this.zzj.zzr().zzw().zza("Updated engagement user property. scope, value", str2, zzksVar.zze);
            }
        }
    }

    private final boolean zza(zzbs.zzc.zza zzaVar, zzbs.zzc.zza zzaVar2) {
        Preconditions.checkArgument("_e".equals(zzaVar.zzd()));
        zzh();
        zzbs.zze zzeVarZza = zzkr.zza((zzbs.zzc) ((com.google.android.gms.internal.measurement.zzfe) zzaVar.zzv()), "_sc");
        String strZzd = zzeVarZza == null ? null : zzeVarZza.zzd();
        zzh();
        zzbs.zze zzeVarZza2 = zzkr.zza((zzbs.zzc) ((com.google.android.gms.internal.measurement.zzfe) zzaVar2.zzv()), "_pc");
        String strZzd2 = zzeVarZza2 != null ? zzeVarZza2.zzd() : null;
        if (strZzd2 == null || !strZzd2.equals(strZzd)) {
            return false;
        }
        zzb(zzaVar, zzaVar2);
        return true;
    }

    private final void zzb(zzbs.zzc.zza zzaVar, zzbs.zzc.zza zzaVar2) {
        Preconditions.checkArgument("_e".equals(zzaVar.zzd()));
        zzh();
        zzbs.zze zzeVarZza = zzkr.zza((zzbs.zzc) ((com.google.android.gms.internal.measurement.zzfe) zzaVar.zzv()), "_et");
        if (!zzeVarZza.zze() || zzeVarZza.zzf() <= 0) {
            return;
        }
        long jZzf = zzeVarZza.zzf();
        zzh();
        zzbs.zze zzeVarZza2 = zzkr.zza((zzbs.zzc) ((com.google.android.gms.internal.measurement.zzfe) zzaVar2.zzv()), "_et");
        if (zzeVarZza2 != null && zzeVarZza2.zzf() > 0) {
            jZzf += zzeVarZza2.zzf();
        }
        zzh();
        zzkr.zza(zzaVar2, "_et", Long.valueOf(jZzf));
        zzh();
        zzkr.zza(zzaVar, "_fr", (Object) 1L);
    }

    private static void zza(zzbs.zzc.zza zzaVar, String str) {
        List<zzbs.zze> listZza = zzaVar.zza();
        for (int i = 0; i < listZza.size(); i++) {
            if (str.equals(listZza.get(i).zzb())) {
                zzaVar.zzb(i);
                return;
            }
        }
    }

    private static void zza(zzbs.zzc.zza zzaVar, int i, String str) {
        List<zzbs.zze> listZza = zzaVar.zza();
        for (int i2 = 0; i2 < listZza.size(); i2++) {
            if ("_err".equals(listZza.get(i2).zzb())) {
                return;
            }
        }
        zzaVar.zza((zzbs.zze) ((com.google.android.gms.internal.measurement.zzfe) zzbs.zze.zzk().zza("_err").zza(Long.valueOf(i).longValue()).zzv())).zza((zzbs.zze) ((com.google.android.gms.internal.measurement.zzfe) zzbs.zze.zzk().zza("_ev").zzb(str).zzv()));
    }

    final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzac zzacVarZze;
        long jLongValue;
        zzw();
        zzk();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzs = false;
                zzaa();
            }
        }
        List<Long> list = this.zzw;
        this.zzw = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
                this.zzj.zzc().zzd.zza(0L);
                zzz();
                this.zzj.zzr().zzx().zza("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zze().zzf();
                try {
                    for (Long l : list) {
                        try {
                            zzacVarZze = zze();
                            jLongValue = l.longValue();
                            zzacVarZze.zzd();
                            zzacVarZze.zzak();
                        } catch (SQLiteException e2) {
                            if (this.zzx == null || !this.zzx.contains(l)) {
                                throw e2;
                            }
                        }
                        try {
                            if (zzacVarZze.c_().delete("queue", "rowid=?", new String[]{String.valueOf(jLongValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e3) {
                            zzacVarZze.zzr().zzf().zza("Failed to delete a bundle in a queue table", e3);
                            throw e3;
                        }
                    }
                    zze().b_();
                    zze().zzh();
                    this.zzx = null;
                    if (zzd().zzf() && zzy()) {
                        zzl();
                    } else {
                        this.zzy = -1L;
                        zzz();
                    }
                    this.zzn = 0L;
                } catch (Throwable th2) {
                    zze().zzh();
                    throw th2;
                }
            } catch (SQLiteException e4) {
                this.zzj.zzr().zzf().zza("Database error while trying to delete uploaded bundles", e4);
                this.zzn = this.zzj.zzm().elapsedRealtime();
                this.zzj.zzr().zzx().zza("Disable upload, time", Long.valueOf(this.zzn));
            }
        } else {
            this.zzj.zzr().zzx().zza("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzj.zzc().zzd.zza(this.zzj.zzm().currentTimeMillis());
            if (i != 503 && i != 429) {
                z = false;
            }
            if (z) {
                this.zzj.zzc().zze.zza(this.zzj.zzm().currentTimeMillis());
            }
            zze().zza(list);
            zzz();
        }
    }

    private final boolean zzy() {
        zzw();
        zzk();
        return zze().zzy() || !TextUtils.isEmpty(zze().d_());
    }

    private final void zza(zzg zzgVar) {
        ArrayMap arrayMap;
        zzw();
        if (zzln.zzb() && this.zzj.zzb().zze(zzgVar.zzc(), zzap.zzcf)) {
            if (TextUtils.isEmpty(zzgVar.zze()) && TextUtils.isEmpty(zzgVar.zzg()) && TextUtils.isEmpty(zzgVar.zzf())) {
                zza(zzgVar.zzc(), 204, null, null, null);
                return;
            }
        } else if (TextUtils.isEmpty(zzgVar.zze()) && TextUtils.isEmpty(zzgVar.zzf())) {
            zza(zzgVar.zzc(), 204, null, null, null);
            return;
        }
        String strZza = this.zzj.zzb().zza(zzgVar);
        try {
            URL url = new URL(strZza);
            this.zzj.zzr().zzx().zza("Fetching remote configuration", zzgVar.zzc());
            zzbq.zzb zzbVarZza = zzc().zza(zzgVar.zzc());
            String strZzb = zzc().zzb(zzgVar.zzc());
            if (zzbVarZza == null || TextUtils.isEmpty(strZzb)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("If-Modified-Since", strZzb);
                arrayMap = arrayMap2;
            }
            this.zzr = true;
            zzff zzffVarZzd = zzd();
            String strZzc = zzgVar.zzc();
            zzko zzkoVar = new zzko(this);
            zzffVarZzd.zzd();
            zzffVarZzd.zzak();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkoVar);
            zzffVarZzd.zzq().zzb(new zzfj(zzffVarZzd, strZzc, url, null, arrayMap, zzkoVar));
        } catch (MalformedURLException unused) {
            this.zzj.zzr().zzf().zza("Failed to parse config URL. Not fetching. appId", zzfb.zza(zzgVar.zzc()), strZza);
        }
    }

    final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        zzw();
        zzk();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } finally {
                this.zzr = false;
                zzaa();
            }
        }
        this.zzj.zzr().zzx().zza("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zze().zzf();
        try {
            zzg zzgVarZzb = zze().zzb(str);
            boolean z = true;
            boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
            if (zzgVarZzb == null) {
                this.zzj.zzr().zzi().zza("App does not exist in onConfigFetched. appId", zzfb.zza(str));
            } else if (z2 || i == 404) {
                List<String> list = map != null ? map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : list.get(0);
                if (i == 404 || i == 304) {
                    if (zzc().zza(str) == null && !zzc().zza(str, null, null)) {
                        return;
                    }
                } else if (!zzc().zza(str, bArr, str2)) {
                    return;
                }
                zzgVarZzb.zzh(this.zzj.zzm().currentTimeMillis());
                zze().zza(zzgVarZzb);
                if (i == 404) {
                    this.zzj.zzr().zzk().zza("Config not found. Using empty config. appId", str);
                } else {
                    this.zzj.zzr().zzx().zza("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (zzd().zzf() && zzy()) {
                    zzl();
                } else {
                    zzz();
                }
            } else {
                zzgVarZzb.zzi(this.zzj.zzm().currentTimeMillis());
                zze().zza(zzgVarZzb);
                this.zzj.zzr().zzx().zza("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzc().zzc(str);
                this.zzj.zzc().zzd.zza(this.zzj.zzm().currentTimeMillis());
                if (i != 503 && i != 429) {
                    z = false;
                }
                if (z) {
                    this.zzj.zzc().zze.zza(this.zzj.zzm().currentTimeMillis());
                }
                zzz();
            }
            zze().b_();
        } finally {
            zze().zzh();
        }
    }

    private final void zzz() {
        long jMax;
        long jMax2;
        zzw();
        zzk();
        if (zzac() || this.zzj.zzb().zza(zzap.zzbb)) {
            if (this.zzn > 0) {
                long jAbs = DateUtil.HOUR - Math.abs(this.zzj.zzm().elapsedRealtime() - this.zzn);
                if (jAbs > 0) {
                    this.zzj.zzr().zzx().zza("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(jAbs));
                    zzt().zzb();
                    zzv().zzf();
                    return;
                }
                this.zzn = 0L;
            }
            if (!this.zzj.zzah() || !zzy()) {
                this.zzj.zzr().zzx().zza("Nothing to upload or uploading impossible");
                zzt().zzb();
                zzv().zzf();
                return;
            }
            long jCurrentTimeMillis = this.zzj.zzm().currentTimeMillis();
            long jMax3 = Math.max(0L, zzap.zzz.zza(null).longValue());
            boolean z = zze().zzz() || zze().zzk();
            if (z) {
                String strZzv = this.zzj.zzb().zzv();
                if (!TextUtils.isEmpty(strZzv) && !".none.".equals(strZzv)) {
                    jMax = Math.max(0L, zzap.zzu.zza(null).longValue());
                } else {
                    jMax = Math.max(0L, zzap.zzt.zza(null).longValue());
                }
            } else {
                jMax = Math.max(0L, zzap.zzs.zza(null).longValue());
            }
            long jZza = this.zzj.zzc().zzc.zza();
            long jZza2 = this.zzj.zzc().zzd.zza();
            long j = jMax;
            long jMax4 = Math.max(zze().zzw(), zze().zzx());
            if (jMax4 == 0) {
                jMax2 = 0;
            } else {
                long jAbs2 = jCurrentTimeMillis - Math.abs(jMax4 - jCurrentTimeMillis);
                long jAbs3 = jCurrentTimeMillis - Math.abs(jZza - jCurrentTimeMillis);
                long jAbs4 = jCurrentTimeMillis - Math.abs(jZza2 - jCurrentTimeMillis);
                long jMax5 = Math.max(jAbs3, jAbs4);
                long jMin = jAbs2 + jMax3;
                if (z && jMax5 > 0) {
                    jMin = Math.min(jAbs2, jMax5) + j;
                }
                jMax2 = !zzh().zza(jMax5, j) ? jMax5 + j : jMin;
                if (jAbs4 != 0 && jAbs4 >= jAbs2) {
                    for (int i = 0; i < Math.min(20, Math.max(0, zzap.zzab.zza(null).intValue())); i++) {
                        jMax2 += Math.max(0L, zzap.zzaa.zza(null).longValue()) * (1 << i);
                        if (jMax2 > jAbs4) {
                            break;
                        }
                    }
                    jMax2 = 0;
                }
            }
            if (jMax2 == 0) {
                this.zzj.zzr().zzx().zza("Next upload time is 0");
                zzt().zzb();
                zzv().zzf();
                return;
            }
            if (!zzd().zzf()) {
                this.zzj.zzr().zzx().zza("No network");
                zzt().zza();
                zzv().zzf();
                return;
            }
            long jZza3 = this.zzj.zzc().zze.zza();
            long jMax6 = Math.max(0L, zzap.zzq.zza(null).longValue());
            if (!zzh().zza(jZza3, jMax6)) {
                jMax2 = Math.max(jMax2, jZza3 + jMax6);
            }
            zzt().zzb();
            long jCurrentTimeMillis2 = jMax2 - this.zzj.zzm().currentTimeMillis();
            if (jCurrentTimeMillis2 <= 0) {
                jCurrentTimeMillis2 = Math.max(0L, zzap.zzv.zza(null).longValue());
                this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
            }
            this.zzj.zzr().zzx().zza("Upload scheduled in approximately ms", Long.valueOf(jCurrentTimeMillis2));
            zzv().zza(jCurrentTimeMillis2);
        }
    }

    final void zza(Runnable runnable) {
        zzw();
        if (this.zzo == null) {
            this.zzo = new ArrayList();
        }
        this.zzo.add(runnable);
    }

    private final void zzaa() {
        zzw();
        if (this.zzr || this.zzs || this.zzt) {
            this.zzj.zzr().zzx().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzr), Boolean.valueOf(this.zzs), Boolean.valueOf(this.zzt));
            return;
        }
        this.zzj.zzr().zzx().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzo;
        if (list == null) {
            return;
        }
        Iterator<Runnable> it = list.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.zzo.clear();
    }

    private final Boolean zzb(zzg zzgVar) {
        try {
            if (zzgVar.zzm() != -2147483648L) {
                if (zzgVar.zzm() == Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzgVar.zzc(), 0).versionCode) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzgVar.zzc(), 0).versionName;
                if (zzgVar.zzl() != null && zzgVar.zzl().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final boolean zzab() {
        FileLock fileLock;
        zzw();
        if (this.zzj.zzb().zza(zzap.zzcd) && (fileLock = this.zzu) != null && fileLock.isValid()) {
            this.zzj.zzr().zzx().zza("Storage concurrent access okay");
            return true;
        }
        try {
            this.zzv = new RandomAccessFile(new File(this.zzj.zzn().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzu = this.zzv.tryLock();
            if (this.zzu != null) {
                this.zzj.zzr().zzx().zza("Storage concurrent access okay");
                return true;
            }
            this.zzj.zzr().zzf().zza("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e2) {
            this.zzj.zzr().zzf().zza("Failed to acquire storage lock", e2);
            return false;
        } catch (IOException e3) {
            this.zzj.zzr().zzf().zza("Failed to access storage lock file", e3);
            return false;
        } catch (OverlappingFileLockException e4) {
            this.zzj.zzr().zzi().zza("Storage lock already acquired", e4);
            return false;
        }
    }

    private final int zza(FileChannel fileChannel) {
        zzw();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0L);
            int i = fileChannel.read(byteBufferAllocate);
            if (i == 4) {
                byteBufferAllocate.flip();
                return byteBufferAllocate.getInt();
            }
            if (i != -1) {
                this.zzj.zzr().zzi().zza("Unexpected data length. Bytes read", Integer.valueOf(i));
            }
            return 0;
        } catch (IOException e2) {
            this.zzj.zzr().zzf().zza("Failed to read from channel", e2);
            return 0;
        }
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzw();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(i);
        byteBufferAllocate.flip();
        try {
            fileChannel.truncate(0L);
            if (this.zzj.zzb().zza(zzap.zzcs) && Build.VERSION.SDK_INT <= 19) {
                fileChannel.position(0L);
            }
            fileChannel.write(byteBufferAllocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzj.zzr().zzf().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e2) {
            this.zzj.zzr().zzf().zza("Failed to write to channel", e2);
            return false;
        }
    }

    final void zzo() {
        zzw();
        zzk();
        if (!this.zzm) {
            this.zzm = true;
            zzw();
            zzk();
            if ((this.zzj.zzb().zza(zzap.zzbb) || zzac()) && zzab()) {
                int iZza = zza(this.zzv);
                int iZzaf = this.zzj.zzy().zzaf();
                zzw();
                if (iZza > iZzaf) {
                    this.zzj.zzr().zzf().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzaf));
                } else if (iZza < iZzaf) {
                    if (zza(iZzaf, this.zzv)) {
                        this.zzj.zzr().zzx().zza("Storage version upgraded. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzaf));
                    } else {
                        this.zzj.zzr().zzf().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(iZza), Integer.valueOf(iZzaf));
                    }
                }
            }
        }
        if (this.zzl || this.zzj.zzb().zza(zzap.zzbb)) {
            return;
        }
        this.zzj.zzr().zzv().zza("This instance being marked as an uploader");
        this.zzl = true;
        zzz();
    }

    private final boolean zzac() {
        zzw();
        zzk();
        return this.zzl;
    }

    final void zza(zzm zzmVar) {
        if (this.zzw != null) {
            this.zzx = new ArrayList();
            this.zzx.addAll(this.zzw);
        }
        zzac zzacVarZze = zze();
        String str = zzmVar.zza;
        Preconditions.checkNotEmpty(str);
        zzacVarZze.zzd();
        zzacVarZze.zzak();
        try {
            SQLiteDatabase sQLiteDatabaseC_ = zzacVarZze.c_();
            String[] strArr = {str};
            int iDelete = sQLiteDatabaseC_.delete("apps", "app_id=?", strArr) + 0 + sQLiteDatabaseC_.delete("events", "app_id=?", strArr) + sQLiteDatabaseC_.delete("user_attributes", "app_id=?", strArr) + sQLiteDatabaseC_.delete("conditional_properties", "app_id=?", strArr) + sQLiteDatabaseC_.delete("raw_events", "app_id=?", strArr) + sQLiteDatabaseC_.delete("raw_events_metadata", "app_id=?", strArr) + sQLiteDatabaseC_.delete("queue", "app_id=?", strArr) + sQLiteDatabaseC_.delete("audience_filter_values", "app_id=?", strArr) + sQLiteDatabaseC_.delete("main_event_params", "app_id=?", strArr);
            if (iDelete > 0) {
                zzacVarZze.zzr().zzx().zza("Reset analytics data. app, records", str, Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e2) {
            zzacVarZze.zzr().zzf().zza("Error resetting analytics data. appId, error", zzfb.zza(str), e2);
        }
        if (com.google.android.gms.internal.measurement.zzkp.zzb() && this.zzj.zzb().zza(zzap.zzck)) {
            if (zzmVar.zzh) {
                zzb(zzmVar);
            }
        } else {
            zzm zzmVarZza = zza(this.zzj.zzn(), zzmVar.zza, zzmVar.zzb, zzmVar.zzh, zzmVar.zzo, zzmVar.zzp, zzmVar.zzm, zzmVar.zzr, zzmVar.zzv);
            if (zzmVar.zzh) {
                zzb(zzmVarZza);
            }
        }
    }

    private final zzm zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j, String str3, String str4) {
        String installerPackageName;
        String str5;
        int i;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzj.zzr().zzf().zza("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            installerPackageName = packageManager.getInstallerPackageName(str);
        } catch (IllegalArgumentException unused) {
            this.zzj.zzr().zzf().zza("Error retrieving installer package name. appId", zzfb.zza(str));
            installerPackageName = "Unknown";
        }
        if (installerPackageName == null) {
            installerPackageName = "manual_install";
        } else if ("com.android.vending".equals(installerPackageName)) {
            installerPackageName = "";
        }
        String str6 = installerPackageName;
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    applicationLabel.toString();
                }
                str5 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                str5 = "Unknown";
                i = Integer.MIN_VALUE;
            }
            return new zzm(str, str2, str5, i, str6, this.zzj.zzb().zze(), this.zzj.zzi().zza(context, str), (String) null, z, false, "", 0L, j, 0, z2, z3, false, str3, (Boolean) null, 0L, (List<String>) null, (zzln.zzb() && this.zzj.zzb().zze(str, zzap.zzcf)) ? str4 : null);
        } catch (PackageManager.NameNotFoundException unused2) {
            this.zzj.zzr().zzf().zza("Error retrieving newly installed package info. appId, appName", zzfb.zza(str), "Unknown");
            return null;
        }
    }

    final void zza(zzkq zzkqVar, zzm zzmVar) {
        zzaj zzajVarZza;
        zzw();
        zzk();
        if (zze(zzmVar)) {
            if (!zzmVar.zzh) {
                zzc(zzmVar);
                return;
            }
            int iZzc = this.zzj.zzi().zzc(zzkqVar.zza);
            int length = 0;
            if (iZzc != 0) {
                this.zzj.zzi();
                this.zzj.zzi().zza(zzmVar.zza, iZzc, "_ev", zzkv.zza(zzkqVar.zza, 24, true), zzkqVar.zza != null ? zzkqVar.zza.length() : 0);
                return;
            }
            int iZzb = this.zzj.zzi().zzb(zzkqVar.zza, zzkqVar.zza());
            if (iZzb != 0) {
                this.zzj.zzi();
                String strZza = zzkv.zza(zzkqVar.zza, 24, true);
                Object objZza = zzkqVar.zza();
                if (objZza != null && ((objZza instanceof String) || (objZza instanceof CharSequence))) {
                    length = String.valueOf(objZza).length();
                }
                this.zzj.zzi().zza(zzmVar.zza, iZzb, "_ev", strZza, length);
                return;
            }
            Object objZzc = this.zzj.zzi().zzc(zzkqVar.zza, zzkqVar.zza());
            if (objZzc == null) {
                return;
            }
            if ("_sid".equals(zzkqVar.zza) && this.zzj.zzb().zze(zzmVar.zza, zzap.zzap)) {
                long j = zzkqVar.zzb;
                String str = zzkqVar.zze;
                long jLongValue = 0;
                zzks zzksVarZzc = zze().zzc(zzmVar.zza, "_sno");
                if (zzksVarZzc != null && (zzksVarZzc.zze instanceof Long)) {
                    jLongValue = ((Long) zzksVarZzc.zze).longValue();
                } else {
                    if (zzksVarZzc != null) {
                        this.zzj.zzr().zzi().zza("Retrieved last session number from database does not contain a valid (long) value", zzksVarZzc.zze);
                    }
                    if (this.zzj.zzb().zze(zzmVar.zza, zzap.zzas) && (zzajVarZza = zze().zza(zzmVar.zza, "_s")) != null) {
                        jLongValue = zzajVarZza.zzc;
                        this.zzj.zzr().zzx().zza("Backfill the session number. Last used session number", Long.valueOf(jLongValue));
                    }
                }
                zza(new zzkq("_sno", j, Long.valueOf(jLongValue + 1), str), zzmVar);
            }
            zzks zzksVar = new zzks(zzmVar.zza, zzkqVar.zze, zzkqVar.zza, zzkqVar.zzb, objZzc);
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zzj.zzb().zze(zzmVar.zza, zzap.zzcx)) {
                this.zzj.zzr().zzx().zza("Setting user property", this.zzj.zzj().zzc(zzksVar.zzc), objZzc);
            } else {
                this.zzj.zzr().zzw().zza("Setting user property", this.zzj.zzj().zzc(zzksVar.zzc), objZzc);
            }
            zze().zzf();
            try {
                zzc(zzmVar);
                boolean zZza = zze().zza(zzksVar);
                zze().b_();
                if (zZza) {
                    if (!com.google.android.gms.internal.measurement.zzkw.zzb() || !this.zzj.zzb().zze(zzmVar.zza, zzap.zzcx)) {
                        this.zzj.zzr().zzw().zza("User property set", this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                    }
                } else {
                    this.zzj.zzr().zzf().zza("Too many unique user properties are set. Ignoring user property", this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                    this.zzj.zzi().zza(zzmVar.zza, 9, (String) null, (String) null, 0);
                }
            } finally {
                zze().zzh();
            }
        }
    }

    final void zzb(zzkq zzkqVar, zzm zzmVar) {
        zzw();
        zzk();
        if (zze(zzmVar)) {
            if (!zzmVar.zzh) {
                zzc(zzmVar);
                return;
            }
            if (this.zzj.zzb().zze(zzmVar.zza, zzap.zzaz)) {
                if ("_npa".equals(zzkqVar.zza) && zzmVar.zzs != null) {
                    this.zzj.zzr().zzw().zza("Falling back to manifest metadata value for ad personalization");
                    zza(new zzkq("_npa", this.zzj.zzm().currentTimeMillis(), Long.valueOf(zzmVar.zzs.booleanValue() ? 1L : 0L), "auto"), zzmVar);
                    return;
                }
                this.zzj.zzr().zzw().zza("Removing user property", this.zzj.zzj().zzc(zzkqVar.zza));
                zze().zzf();
                try {
                    zzc(zzmVar);
                    zze().zzb(zzmVar.zza, zzkqVar.zza);
                    zze().b_();
                    this.zzj.zzr().zzw().zza("User property removed", this.zzj.zzj().zzc(zzkqVar.zza));
                    return;
                } finally {
                }
            }
            this.zzj.zzr().zzw().zza("Removing user property", this.zzj.zzj().zzc(zzkqVar.zza));
            zze().zzf();
            try {
                zzc(zzmVar);
                zze().zzb(zzmVar.zza, zzkqVar.zza);
                zze().b_();
                this.zzj.zzr().zzw().zza("User property removed", this.zzj.zzj().zzc(zzkqVar.zza));
            } finally {
            }
        }
    }

    final void zza(zzkk zzkkVar) {
        this.zzp++;
    }

    final void zzp() {
        this.zzq++;
    }

    final zzgf zzs() {
        return this.zzj;
    }

    /* JADX WARN: Removed duplicated region for block: B:166:0x04b2 A[Catch: all -> 0x04dd, TryCatch #4 {all -> 0x04dd, blocks: (B:27:0x00ae, B:29:0x00be, B:31:0x00cc, B:33:0x00d6, B:35:0x00da, B:39:0x00eb, B:41:0x0103, B:48:0x012a, B:50:0x0136, B:52:0x014d, B:53:0x0175, B:55:0x017c, B:57:0x01c0, B:67:0x01ec, B:69:0x01f7, B:74:0x0206, B:76:0x020e, B:78:0x0214, B:82:0x0223, B:84:0x0226, B:87:0x024b, B:89:0x0250, B:95:0x0270, B:98:0x0283, B:100:0x02aa, B:101:0x02b8, B:103:0x02ef, B:104:0x02f2, B:106:0x02f6, B:107:0x02f9, B:109:0x031a, B:149:0x03fc, B:150:0x0401, B:160:0x046d, B:162:0x047d, B:164:0x0497, B:165:0x049e, B:169:0x04ce, B:112:0x0336, B:117:0x0361, B:119:0x0369, B:121:0x0373, B:125:0x0387, B:129:0x0395, B:133:0x03a0, B:136:0x03b3, B:141:0x03de, B:143:0x03e4, B:144:0x03e9, B:146:0x03ef, B:139:0x03c6, B:126:0x038d, B:115:0x0349, B:153:0x0419, B:155:0x0450, B:156:0x0453, B:158:0x0457, B:159:0x045a, B:166:0x04b2, B:168:0x04b6, B:92:0x0260, B:63:0x01d6, B:43:0x010d, B:46:0x0117), top: B:183:0x00ae, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0226 A[Catch: all -> 0x04dd, TryCatch #4 {all -> 0x04dd, blocks: (B:27:0x00ae, B:29:0x00be, B:31:0x00cc, B:33:0x00d6, B:35:0x00da, B:39:0x00eb, B:41:0x0103, B:48:0x012a, B:50:0x0136, B:52:0x014d, B:53:0x0175, B:55:0x017c, B:57:0x01c0, B:67:0x01ec, B:69:0x01f7, B:74:0x0206, B:76:0x020e, B:78:0x0214, B:82:0x0223, B:84:0x0226, B:87:0x024b, B:89:0x0250, B:95:0x0270, B:98:0x0283, B:100:0x02aa, B:101:0x02b8, B:103:0x02ef, B:104:0x02f2, B:106:0x02f6, B:107:0x02f9, B:109:0x031a, B:149:0x03fc, B:150:0x0401, B:160:0x046d, B:162:0x047d, B:164:0x0497, B:165:0x049e, B:169:0x04ce, B:112:0x0336, B:117:0x0361, B:119:0x0369, B:121:0x0373, B:125:0x0387, B:129:0x0395, B:133:0x03a0, B:136:0x03b3, B:141:0x03de, B:143:0x03e4, B:144:0x03e9, B:146:0x03ef, B:139:0x03c6, B:126:0x038d, B:115:0x0349, B:153:0x0419, B:155:0x0450, B:156:0x0453, B:158:0x0457, B:159:0x045a, B:166:0x04b2, B:168:0x04b6, B:92:0x0260, B:63:0x01d6, B:43:0x010d, B:46:0x0117), top: B:183:0x00ae, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0250 A[Catch: all -> 0x04dd, TryCatch #4 {all -> 0x04dd, blocks: (B:27:0x00ae, B:29:0x00be, B:31:0x00cc, B:33:0x00d6, B:35:0x00da, B:39:0x00eb, B:41:0x0103, B:48:0x012a, B:50:0x0136, B:52:0x014d, B:53:0x0175, B:55:0x017c, B:57:0x01c0, B:67:0x01ec, B:69:0x01f7, B:74:0x0206, B:76:0x020e, B:78:0x0214, B:82:0x0223, B:84:0x0226, B:87:0x024b, B:89:0x0250, B:95:0x0270, B:98:0x0283, B:100:0x02aa, B:101:0x02b8, B:103:0x02ef, B:104:0x02f2, B:106:0x02f6, B:107:0x02f9, B:109:0x031a, B:149:0x03fc, B:150:0x0401, B:160:0x046d, B:162:0x047d, B:164:0x0497, B:165:0x049e, B:169:0x04ce, B:112:0x0336, B:117:0x0361, B:119:0x0369, B:121:0x0373, B:125:0x0387, B:129:0x0395, B:133:0x03a0, B:136:0x03b3, B:141:0x03de, B:143:0x03e4, B:144:0x03e9, B:146:0x03ef, B:139:0x03c6, B:126:0x038d, B:115:0x0349, B:153:0x0419, B:155:0x0450, B:156:0x0453, B:158:0x0457, B:159:0x045a, B:166:0x04b2, B:168:0x04b6, B:92:0x0260, B:63:0x01d6, B:43:0x010d, B:46:0x0117), top: B:183:0x00ae, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0270 A[Catch: all -> 0x04dd, TRY_LEAVE, TryCatch #4 {all -> 0x04dd, blocks: (B:27:0x00ae, B:29:0x00be, B:31:0x00cc, B:33:0x00d6, B:35:0x00da, B:39:0x00eb, B:41:0x0103, B:48:0x012a, B:50:0x0136, B:52:0x014d, B:53:0x0175, B:55:0x017c, B:57:0x01c0, B:67:0x01ec, B:69:0x01f7, B:74:0x0206, B:76:0x020e, B:78:0x0214, B:82:0x0223, B:84:0x0226, B:87:0x024b, B:89:0x0250, B:95:0x0270, B:98:0x0283, B:100:0x02aa, B:101:0x02b8, B:103:0x02ef, B:104:0x02f2, B:106:0x02f6, B:107:0x02f9, B:109:0x031a, B:149:0x03fc, B:150:0x0401, B:160:0x046d, B:162:0x047d, B:164:0x0497, B:165:0x049e, B:169:0x04ce, B:112:0x0336, B:117:0x0361, B:119:0x0369, B:121:0x0373, B:125:0x0387, B:129:0x0395, B:133:0x03a0, B:136:0x03b3, B:141:0x03de, B:143:0x03e4, B:144:0x03e9, B:146:0x03ef, B:139:0x03c6, B:126:0x038d, B:115:0x0349, B:153:0x0419, B:155:0x0450, B:156:0x0453, B:158:0x0457, B:159:0x045a, B:166:0x04b2, B:168:0x04b6, B:92:0x0260, B:63:0x01d6, B:43:0x010d, B:46:0x0117), top: B:183:0x00ae, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void zzb(com.google.android.gms.measurement.internal.zzm r22) {
        /*
            Method dump skipped, instruction units count: 1254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zzb(com.google.android.gms.measurement.internal.zzm):void");
    }

    private final zzm zza(String str) {
        zzg zzgVarZzb = zze().zzb(str);
        if (zzgVarZzb == null || TextUtils.isEmpty(zzgVarZzb.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping", str);
            return null;
        }
        Boolean boolZzb = zzb(zzgVarZzb);
        if (boolZzb != null && !boolZzb.booleanValue()) {
            this.zzj.zzr().zzf().zza("App version does not match; dropping. appId", zzfb.zza(str));
            return null;
        }
        return new zzm(str, zzgVarZzb.zze(), zzgVarZzb.zzl(), zzgVarZzb.zzm(), zzgVarZzb.zzn(), zzgVarZzb.zzo(), zzgVarZzb.zzp(), (String) null, zzgVarZzb.zzr(), false, zzgVarZzb.zzi(), zzgVarZzb.zzae(), 0L, 0, zzgVarZzb.zzaf(), zzgVarZzb.zzag(), false, zzgVarZzb.zzf(), zzgVarZzb.zzah(), zzgVarZzb.zzq(), zzgVarZzb.zzai(), (zzln.zzb() && this.zzj.zzb().zze(str, zzap.zzcf)) ? zzgVarZzb.zzg() : null);
    }

    final void zza(zzv zzvVar) {
        zzm zzmVarZza = zza(zzvVar.zza);
        if (zzmVarZza != null) {
            zza(zzvVar, zzmVarZza);
        }
    }

    final void zza(zzv zzvVar, zzm zzmVar) {
        Preconditions.checkNotNull(zzvVar);
        Preconditions.checkNotEmpty(zzvVar.zza);
        Preconditions.checkNotNull(zzvVar.zzb);
        Preconditions.checkNotNull(zzvVar.zzc);
        Preconditions.checkNotEmpty(zzvVar.zzc.zza);
        zzw();
        zzk();
        if (zze(zzmVar)) {
            if (!zzmVar.zzh) {
                zzc(zzmVar);
                return;
            }
            zzv zzvVar2 = new zzv(zzvVar);
            boolean z = false;
            zzvVar2.zze = false;
            zze().zzf();
            try {
                zzv zzvVarZzd = zze().zzd(zzvVar2.zza, zzvVar2.zzc.zza);
                if (zzvVarZzd != null && !zzvVarZzd.zzb.equals(zzvVar2.zzb)) {
                    this.zzj.zzr().zzi().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzj.zzj().zzc(zzvVar2.zzc.zza), zzvVar2.zzb, zzvVarZzd.zzb);
                }
                if (zzvVarZzd != null && zzvVarZzd.zze) {
                    zzvVar2.zzb = zzvVarZzd.zzb;
                    zzvVar2.zzd = zzvVarZzd.zzd;
                    zzvVar2.zzh = zzvVarZzd.zzh;
                    zzvVar2.zzf = zzvVarZzd.zzf;
                    zzvVar2.zzi = zzvVarZzd.zzi;
                    zzvVar2.zze = zzvVarZzd.zze;
                    zzvVar2.zzc = new zzkq(zzvVar2.zzc.zza, zzvVarZzd.zzc.zzb, zzvVar2.zzc.zza(), zzvVarZzd.zzc.zze);
                } else if (TextUtils.isEmpty(zzvVar2.zzf)) {
                    zzvVar2.zzc = new zzkq(zzvVar2.zzc.zza, zzvVar2.zzd, zzvVar2.zzc.zza(), zzvVar2.zzc.zze);
                    zzvVar2.zze = true;
                    z = true;
                }
                if (zzvVar2.zze) {
                    zzkq zzkqVar = zzvVar2.zzc;
                    zzks zzksVar = new zzks(zzvVar2.zza, zzvVar2.zzb, zzkqVar.zza, zzkqVar.zzb, zzkqVar.zza());
                    if (zze().zza(zzksVar)) {
                        this.zzj.zzr().zzw().zza("User property updated immediately", zzvVar2.zza, this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                    } else {
                        this.zzj.zzr().zzf().zza("(2)Too many active user properties, ignoring", zzfb.zza(zzvVar2.zza), this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                    }
                    if (z && zzvVar2.zzi != null) {
                        zzb(new zzan(zzvVar2.zzi, zzvVar2.zzd), zzmVar);
                    }
                }
                if (zze().zza(zzvVar2)) {
                    this.zzj.zzr().zzw().zza("Conditional property added", zzvVar2.zza, this.zzj.zzj().zzc(zzvVar2.zzc.zza), zzvVar2.zzc.zza());
                } else {
                    this.zzj.zzr().zzf().zza("Too many conditional properties, ignoring", zzfb.zza(zzvVar2.zza), this.zzj.zzj().zzc(zzvVar2.zzc.zza), zzvVar2.zzc.zza());
                }
                zze().b_();
            } finally {
                zze().zzh();
            }
        }
    }

    final void zzb(zzv zzvVar) {
        zzm zzmVarZza = zza(zzvVar.zza);
        if (zzmVarZza != null) {
            zzb(zzvVar, zzmVarZza);
        }
    }

    final void zzb(zzv zzvVar, zzm zzmVar) {
        Preconditions.checkNotNull(zzvVar);
        Preconditions.checkNotEmpty(zzvVar.zza);
        Preconditions.checkNotNull(zzvVar.zzc);
        Preconditions.checkNotEmpty(zzvVar.zzc.zza);
        zzw();
        zzk();
        if (zze(zzmVar)) {
            if (!zzmVar.zzh) {
                zzc(zzmVar);
                return;
            }
            zze().zzf();
            try {
                zzc(zzmVar);
                zzv zzvVarZzd = zze().zzd(zzvVar.zza, zzvVar.zzc.zza);
                if (zzvVarZzd != null) {
                    this.zzj.zzr().zzw().zza("Removing conditional user property", zzvVar.zza, this.zzj.zzj().zzc(zzvVar.zzc.zza));
                    zze().zze(zzvVar.zza, zzvVar.zzc.zza);
                    if (zzvVarZzd.zze) {
                        zze().zzb(zzvVar.zza, zzvVar.zzc.zza);
                    }
                    if (zzvVar.zzk != null) {
                        zzb(this.zzj.zzi().zza(zzvVar.zza, zzvVar.zzk.zza, zzvVar.zzk.zzb != null ? zzvVar.zzk.zzb.zzb() : null, zzvVarZzd.zzb, zzvVar.zzk.zzd, true, false), zzmVar);
                    }
                } else {
                    this.zzj.zzr().zzi().zza("Conditional user property doesn't exist", zzfb.zza(zzvVar.zza), this.zzj.zzj().zzc(zzvVar.zzc.zza));
                }
                zze().b_();
            } finally {
                zze().zzh();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0194  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.android.gms.measurement.internal.zzg zza(com.google.android.gms.measurement.internal.zzm r8, com.google.android.gms.measurement.internal.zzg r9, java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zza(com.google.android.gms.measurement.internal.zzm, com.google.android.gms.measurement.internal.zzg, java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    final zzg zzc(zzm zzmVar) {
        zzw();
        zzk();
        Preconditions.checkNotNull(zzmVar);
        Preconditions.checkNotEmpty(zzmVar.zza);
        zzg zzgVarZzb = zze().zzb(zzmVar.zza);
        String strZzb = this.zzj.zzc().zzb(zzmVar.zza);
        if (com.google.android.gms.internal.measurement.zzkq.zzb() && this.zzj.zzb().zza(zzap.zzcn)) {
            if (zzgVarZzb == null) {
                zzgVarZzb = new zzg(this.zzj, zzmVar.zza);
                zzgVarZzb.zza(this.zzj.zzi().zzk());
                zzgVarZzb.zze(strZzb);
            } else if (!strZzb.equals(zzgVarZzb.zzh())) {
                zzgVarZzb.zze(strZzb);
                zzgVarZzb.zza(this.zzj.zzi().zzk());
            }
            zzgVarZzb.zzb(zzmVar.zzb);
            zzgVarZzb.zzc(zzmVar.zzr);
            if (zzln.zzb() && this.zzj.zzb().zze(zzgVarZzb.zzc(), zzap.zzcf)) {
                zzgVarZzb.zzd(zzmVar.zzv);
            }
            if (!TextUtils.isEmpty(zzmVar.zzk)) {
                zzgVarZzb.zzf(zzmVar.zzk);
            }
            if (zzmVar.zze != 0) {
                zzgVarZzb.zzd(zzmVar.zze);
            }
            if (!TextUtils.isEmpty(zzmVar.zzc)) {
                zzgVarZzb.zzg(zzmVar.zzc);
            }
            zzgVarZzb.zzc(zzmVar.zzj);
            if (zzmVar.zzd != null) {
                zzgVarZzb.zzh(zzmVar.zzd);
            }
            zzgVarZzb.zze(zzmVar.zzf);
            zzgVarZzb.zza(zzmVar.zzh);
            if (!TextUtils.isEmpty(zzmVar.zzg)) {
                zzgVarZzb.zzi(zzmVar.zzg);
            }
            zzgVarZzb.zzp(zzmVar.zzl);
            zzgVarZzb.zzb(zzmVar.zzo);
            zzgVarZzb.zzc(zzmVar.zzp);
            if (this.zzj.zzb().zze(zzmVar.zza, zzap.zzaz)) {
                zzgVarZzb.zza(zzmVar.zzs);
            }
            zzgVarZzb.zzf(zzmVar.zzt);
            if (zzgVarZzb.zza()) {
                zze().zza(zzgVarZzb);
            }
            return zzgVarZzb;
        }
        return zza(zzmVar, zzgVarZzb, strZzb);
    }

    final String zzd(zzm zzmVar) {
        try {
            return (String) this.zzj.zzq().zza(new zzkn(this, zzmVar)).get(30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e2) {
            this.zzj.zzr().zzf().zza("Failed to get app instance id. appId", zzfb.zza(zzmVar.zza), e2);
            return null;
        }
    }

    final void zza(boolean z) {
        zzz();
    }

    private final boolean zze(zzm zzmVar) {
        return (zzln.zzb() && this.zzj.zzb().zze(zzmVar.zza, zzap.zzcf)) ? (TextUtils.isEmpty(zzmVar.zzb) && TextUtils.isEmpty(zzmVar.zzv) && TextUtils.isEmpty(zzmVar.zzr)) ? false : true : (TextUtils.isEmpty(zzmVar.zzb) && TextUtils.isEmpty(zzmVar.zzr)) ? false : true;
    }
}