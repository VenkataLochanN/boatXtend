package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzms;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.utils.FileDialDefinedUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzhk extends zze {
    protected zzid zza;
    protected boolean zzb;
    private zzhf zzc;
    private final Set<zzhi> zzd;
    private boolean zze;
    private final AtomicReference<String> zzf;

    protected zzhk(zzgf zzgfVar) {
        super(zzgfVar);
        this.zzd = new CopyOnWriteArraySet();
        this.zzb = true;
        this.zzf = new AtomicReference<>();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    protected final boolean zzz() {
        return false;
    }

    public final void zzab() {
        if (zzn().getApplicationContext() instanceof Application) {
            ((Application) zzn().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    public final Boolean zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzq().zza(atomicReference, 15000L, "boolean test flag value", new zzhm(this, atomicReference));
    }

    public final String zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzq().zza(atomicReference, 15000L, "String test flag value", new zzhw(this, atomicReference));
    }

    public final Long zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzq().zza(atomicReference, 15000L, "long test flag value", new zzhy(this, atomicReference));
    }

    public final Integer zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzq().zza(atomicReference, 15000L, "int test flag value", new zzhx(this, atomicReference));
    }

    public final Double zzag() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzq().zza(atomicReference, 15000L, "double test flag value", new zzia(this, atomicReference));
    }

    public final void zza(boolean z) {
        zzw();
        zzb();
        zzq().zza(new zzhz(this, z));
    }

    public final void zzb(boolean z) {
        zzw();
        zzb();
        zzq().zza(new zzic(this, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(boolean z) {
        zzd();
        zzb();
        zzw();
        zzr().zzw().zza("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzs().zzb(z);
        zzam();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzam() {
        if (zzt().zza(zzap.zzba)) {
            zzd();
            String strZza = zzs().zzn.zza();
            if (strZza != null) {
                if ("unset".equals(strZza)) {
                    zza(FileDialDefinedUtil.APP_FILE, "_npa", (Object) null, zzm().currentTimeMillis());
                } else {
                    zza(FileDialDefinedUtil.APP_FILE, "_npa", Long.valueOf("true".equals(strZza) ? 1L : 0L), zzm().currentTimeMillis());
                }
            }
        }
        if (this.zzx.zzab() && this.zzb) {
            zzr().zzw().zza("Recording app launch after enabling measurement for the first time (FE)");
            zzai();
            if (zzlc.zzb() && zzt().zza(zzap.zzcp)) {
                zzk().zza.zza();
            }
            if (com.google.android.gms.internal.measurement.zzkj.zzb() && zzt().zza(zzap.zzcv)) {
                if (this.zzx.zzf().zza.zzc().zzi.zza() > 0) {
                    return;
                }
                this.zzx.zzf().zza();
                return;
            }
            return;
        }
        zzr().zzw().zza("Updating Scion state (FE)");
        zzh().zzac();
    }

    public final void zza(long j) {
        zzb();
        zzq().zza(new zzib(this, j));
    }

    public final void zzb(long j) {
        zzb();
        zzq().zza(new zzie(this, j));
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zza(str, str2, bundle, false, true, zzm().currentTimeMillis());
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzm().currentTimeMillis());
    }

    final void zzb(String str, String str2, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, zzm().currentTimeMillis(), bundle);
    }

    final void zza(String str, String str2, long j, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, j, bundle, true, this.zzc == null || zzkv.zze(str2), false, null);
    }

    protected final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        String str4;
        String str5;
        String str6;
        String str7;
        zzio zzioVar;
        int i;
        int i2;
        long j2;
        ArrayList arrayList;
        String str8;
        Bundle bundle2;
        boolean z4;
        Class<?> cls;
        List<String> listZzah;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzd();
        zzw();
        if (!this.zzx.zzab()) {
            zzr().zzw().zza("Event not sent since app measurement is disabled");
            return;
        }
        if (zzt().zza(zzap.zzbj) && (listZzah = zzg().zzah()) != null && !listZzah.contains(str2)) {
            zzr().zzw().zza("Dropping non-safelisted event. event name, origin", str2, str);
            return;
        }
        if (!this.zze) {
            this.zze = true;
            try {
                if (!this.zzx.zzt()) {
                    cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, zzn().getClassLoader());
                } else {
                    cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                }
                try {
                    cls.getDeclaredMethod("initialize", Context.class).invoke(null, zzn());
                } catch (Exception e2) {
                    zzr().zzi().zza("Failed to invoke Tag Manager's initialize() method", e2);
                }
            } catch (ClassNotFoundException unused) {
                zzr().zzv().zza("Tag Manager is not found and thus will not be used");
            }
        }
        if (zzt().zza(zzap.zzby) && "_cmp".equals(str2) && bundle.containsKey("gclid")) {
            zza("auto", "_lgclid", bundle.getString("gclid"), zzm().currentTimeMillis());
        }
        if (z3) {
            zzu();
            if (!"_iap".equals(str2)) {
                zzkv zzkvVarZzi = this.zzx.zzi();
                int i3 = 2;
                if (zzkvVarZzi.zza("event", str2)) {
                    if (!zzkvVarZzi.zza("event", zzhe.zza, str2)) {
                        i3 = 13;
                    } else if (zzkvVarZzi.zza("event", 40, str2)) {
                        i3 = 0;
                    }
                }
                if (i3 != 0) {
                    zzr().zzh().zza("Invalid public event name. Event will not be logged (FE)", zzo().zza(str2));
                    this.zzx.zzi();
                    this.zzx.zzi().zza(i3, "_ev", zzkv.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
            }
        }
        zzu();
        zzio zzioVarZzab = zzi().zzab();
        if (zzioVarZzab != null && !bundle.containsKey("_sc")) {
            zzioVarZzab.zzd = true;
        }
        zzin.zza(zzioVarZzab, bundle, z && z3);
        boolean zEquals = "am".equals(str);
        boolean zZze = zzkv.zze(str2);
        if (z && this.zzc != null && !zZze && !zEquals) {
            zzr().zzw().zza("Passing event to registered event handler (FE)", zzo().zza(str2), zzo().zza(bundle));
            this.zzc.interceptEvent(str, str2, bundle, j);
            return;
        }
        if (this.zzx.zzah()) {
            int iZzb = zzp().zzb(str2);
            if (iZzb != 0) {
                zzr().zzh().zza("Invalid event name. Event will not be logged (FE)", zzo().zza(str2));
                zzp();
                this.zzx.zzi().zza(str3, iZzb, "_ev", zzkv.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                return;
            }
            List<String> listListOf = CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"});
            Bundle bundleZza = zzp().zza(str3, str2, bundle, listListOf, z3, true);
            zzio zzioVar2 = (bundleZza != null && bundleZza.containsKey("_sc") && bundleZza.containsKey("_si")) ? new zzio(bundleZza.getString("_sn"), bundleZza.getString("_sc"), Long.valueOf(bundleZza.getLong("_si")).longValue()) : null;
            zzio zzioVar3 = zzioVar2 == null ? zzioVarZzab : zzioVar2;
            String str9 = "_ae";
            if (zzt().zza(zzap.zzax)) {
                zzu();
                if (zzi().zzab() != null && "_ae".equals(str2)) {
                    long jZzb = zzk().zzb.zzb();
                    if (jZzb > 0) {
                        zzp().zza(bundleZza, jZzb);
                    }
                }
            }
            if (com.google.android.gms.internal.measurement.zzjy.zzb() && zzt().zza(zzap.zzco)) {
                if (!"auto".equals(str) && "_ssr".equals(str2)) {
                    zzkv zzkvVarZzp = zzp();
                    String string = bundleZza.getString("_ffr");
                    String strTrim = Strings.isEmptyOrWhitespace(string) ? null : string.trim();
                    if (zzkv.zzc(strTrim, zzkvVarZzp.zzs().zzw.zza())) {
                        zzkvVarZzp.zzr().zzw().zza("Not logging duplicate session_start_with_rollout event");
                        z4 = false;
                    } else {
                        zzkvVarZzp.zzs().zzw.zza(strTrim);
                        z4 = true;
                    }
                    if (!z4) {
                        return;
                    }
                } else if ("_ae".equals(str2)) {
                    String strZza = zzp().zzs().zzw.zza();
                    if (!TextUtils.isEmpty(strZza)) {
                        bundleZza.putString("_ffr", strZza);
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(bundleZza);
            long jNextLong = zzp().zzh().nextLong();
            if (zzt().zza(zzap.zzar) && zzs().zzq.zza() > 0 && zzs().zza(j) && zzs().zzt.zza()) {
                zzr().zzx().zza("Current session is expired, remove the session number, ID, and engagement time");
                if (zzt().zza(zzap.zzao)) {
                    str4 = "_o";
                    zza("auto", "_sid", (Object) null, zzm().currentTimeMillis());
                } else {
                    str4 = "_o";
                }
                if (zzt().zza(zzap.zzap)) {
                    zza("auto", "_sno", (Object) null, zzm().currentTimeMillis());
                }
                if (zzms.zzb() && zzt().zza(zzap.zzbo)) {
                    zza("auto", "_se", (Object) null, zzm().currentTimeMillis());
                }
            } else {
                str4 = "_o";
            }
            if (zzt().zza(zzap.zzaq) && bundleZza.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, 0L) == 1) {
                zzr().zzx().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                str5 = str2;
                this.zzx.zze().zza.zza(j, true);
            } else {
                str5 = str2;
            }
            String[] strArr = (String[]) bundleZza.keySet().toArray(new String[bundle.size()]);
            Arrays.sort(strArr);
            int length = strArr.length;
            int length2 = 0;
            int i4 = 0;
            while (i4 < length) {
                String str10 = strArr[i4];
                Object obj = bundleZza.get(str10);
                zzp();
                String[] strArr2 = strArr;
                Bundle[] bundleArrZza = zzkv.zza(obj);
                if (bundleArrZza != null) {
                    i = length;
                    bundleZza.putInt(str10, bundleArrZza.length);
                    int i5 = 0;
                    while (i5 < bundleArrZza.length) {
                        Bundle bundle3 = bundleArrZza[i5];
                        zzin.zza(zzioVar3, bundle3, true);
                        int i6 = length2;
                        long j3 = jNextLong;
                        ArrayList arrayList3 = arrayList2;
                        Bundle bundleZza2 = zzp().zza(str3, "_ep", bundle3, listListOf, z3, false);
                        bundleZza2.putString("_en", str5);
                        bundleZza2.putLong("_eid", j3);
                        bundleZza2.putString("_gn", str10);
                        bundleZza2.putInt("_ll", bundleArrZza.length);
                        bundleZza2.putInt("_i", i5);
                        arrayList3.add(bundleZza2);
                        i5++;
                        str9 = str9;
                        bundleZza = bundleZza;
                        arrayList2 = arrayList3;
                        length2 = i6;
                        jNextLong = j3;
                        i4 = i4;
                        zzioVar3 = zzioVar3;
                    }
                    zzioVar = zzioVar3;
                    i2 = i4;
                    j2 = jNextLong;
                    arrayList = arrayList2;
                    str8 = str9;
                    bundle2 = bundleZza;
                    length2 += bundleArrZza.length;
                } else {
                    zzioVar = zzioVar3;
                    i = length;
                    i2 = i4;
                    j2 = jNextLong;
                    arrayList = arrayList2;
                    str8 = str9;
                    bundle2 = bundleZza;
                }
                i4 = i2 + 1;
                strArr = strArr2;
                str9 = str8;
                bundleZza = bundle2;
                arrayList2 = arrayList;
                jNextLong = j2;
                length = i;
                zzioVar3 = zzioVar;
            }
            int i7 = length2;
            long j4 = jNextLong;
            ArrayList arrayList4 = arrayList2;
            String str11 = str9;
            Bundle bundle4 = bundleZza;
            if (i7 != 0) {
                bundle4.putLong("_eid", j4);
                bundle4.putInt("_epc", i7);
            }
            int i8 = 0;
            while (i8 < arrayList4.size()) {
                Bundle bundleZza3 = (Bundle) arrayList4.get(i8);
                if (i8 != 0) {
                    str7 = "_ep";
                    str6 = str;
                } else {
                    str6 = str;
                    str7 = str5;
                }
                String str12 = str4;
                bundleZza3.putString(str12, str6);
                if (z2) {
                    bundleZza3 = zzp().zza(bundleZza3);
                }
                Bundle bundle5 = bundleZza3;
                if (!com.google.android.gms.internal.measurement.zzkv.zzb() || !zzt().zza(zzap.zzcw)) {
                    zzr().zzw().zza("Logging event (FE)", zzo().zza(str5), zzo().zza(bundle5));
                }
                ArrayList arrayList5 = arrayList4;
                String str13 = str5;
                zzh().zza(new zzan(str7, new zzam(bundle5), str, j), str3);
                if (!zEquals) {
                    Iterator<zzhi> it = this.zzd.iterator();
                    while (it.hasNext()) {
                        it.next().onEvent(str, str2, new Bundle(bundle5), j);
                    }
                }
                i8++;
                str4 = str12;
                arrayList4 = arrayList5;
                str5 = str13;
            }
            String str14 = str5;
            zzu();
            if (zzi().zzab() == null || !str11.equals(str14)) {
                return;
            }
            zzk().zza(true, true, zzm().elapsedRealtime());
        }
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        zzb();
        zzb(str == null ? FileDialDefinedUtil.APP_FILE : str, str2, j, bundle == null ? new Bundle() : bundle, z2, !z2 || this.zzc == null || zzkv.zze(str2), !z, null);
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzq().zza(new zzhl(this, str, str2, j, zzkv.zzb(bundle), z, z2, z3, str3));
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzm().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        if (str == null) {
            str = FileDialDefinedUtil.APP_FILE;
        }
        String str3 = str;
        int iZzc = 6;
        if (z) {
            iZzc = zzp().zzc(str2);
        } else {
            zzkv zzkvVarZzp = zzp();
            if (zzkvVarZzp.zza("user property", str2)) {
                if (!zzkvVarZzp.zza("user property", zzhg.zza, str2)) {
                    iZzc = 15;
                } else if (zzkvVarZzp.zza("user property", 24, str2)) {
                    iZzc = 0;
                }
            }
        }
        if (iZzc != 0) {
            zzp();
            this.zzx.zzi().zza(iZzc, "_ev", zzkv.zza(str2, 24, true), str2 != null ? str2.length() : 0);
            return;
        }
        if (obj != null) {
            int iZzb = zzp().zzb(str2, obj);
            if (iZzb != 0) {
                zzp();
                this.zzx.zzi().zza(iZzb, "_ev", zzkv.zza(str2, 24, true), ((obj instanceof String) || (obj instanceof CharSequence)) ? String.valueOf(obj).length() : 0);
                return;
            } else {
                Object objZzc = zzp().zzc(str2, obj);
                if (objZzc != null) {
                    zza(str3, str2, j, objZzc);
                    return;
                }
                return;
            }
        }
        zza(str3, str2, j, (Object) null);
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzq().zza(new zzho(this, str, str2, obj, j));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void zza(java.lang.String r9, java.lang.String r10, java.lang.Object r11, long r12) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r8.zzd()
            r8.zzb()
            r8.zzw()
            com.google.android.gms.measurement.internal.zzx r0 = r8.zzt()
            com.google.android.gms.measurement.internal.zzeu<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzap.zzba
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = "_npa"
            if (r0 == 0) goto L74
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L74
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L63
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L63
            java.util.Locale r10 = java.util.Locale.ENGLISH
            java.lang.String r10 = r0.toLowerCase(r10)
            java.lang.String r11 = "false"
            boolean r10 = r11.equals(r10)
            r2 = 1
            if (r10 == 0) goto L44
            r4 = r2
            goto L46
        L44:
            r4 = 0
        L46:
            java.lang.Long r10 = java.lang.Long.valueOf(r4)
            com.google.android.gms.measurement.internal.zzfo r0 = r8.zzs()
            com.google.android.gms.measurement.internal.zzfr r0 = r0.zzn
            r4 = r10
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L5e
            java.lang.String r11 = "true"
        L5e:
            r0.zza(r11)
            r6 = r10
            goto L72
        L63:
            if (r11 != 0) goto L74
            com.google.android.gms.measurement.internal.zzfo r10 = r8.zzs()
            com.google.android.gms.measurement.internal.zzfr r10 = r10.zzn
            java.lang.String r0 = "unset"
            r10.zza(r0)
            r6 = r11
        L72:
            r3 = r1
            goto L76
        L74:
            r3 = r10
            r6 = r11
        L76:
            com.google.android.gms.measurement.internal.zzgf r10 = r8.zzx
            boolean r10 = r10.zzab()
            if (r10 != 0) goto L8c
            com.google.android.gms.measurement.internal.zzfb r9 = r8.zzr()
            com.google.android.gms.measurement.internal.zzfd r9 = r9.zzx()
            java.lang.String r10 = "User property not set since app measurement is disabled"
            r9.zza(r10)
            return
        L8c:
            com.google.android.gms.measurement.internal.zzgf r10 = r8.zzx
            boolean r10 = r10.zzah()
            if (r10 != 0) goto L95
            return
        L95:
            com.google.android.gms.measurement.internal.zzkq r10 = new com.google.android.gms.measurement.internal.zzkq
            r2 = r10
            r4 = r12
            r7 = r9
            r2.<init>(r3, r4, r6, r7)
            com.google.android.gms.measurement.internal.zzis r9 = r8.zzh()
            r9.zza(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhk.zza(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public final List<zzkq> zzc(boolean z) {
        zzb();
        zzw();
        zzr().zzx().zza("Getting user properties (FE)");
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        }
        if (zzw.zza()) {
            zzr().zzf().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzx.zzq().zza(atomicReference, BootloaderScanner.TIMEOUT, "get user properties", new zzhn(this, atomicReference, z));
        List<zzkq> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzr().zzf().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
        return Collections.emptyList();
    }

    public final String zzah() {
        zzb();
        return this.zzf.get();
    }

    public final String zzc(long j) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot retrieve app instance id from analytics worker thread");
            return null;
        }
        if (zzw.zza()) {
            zzr().zzf().zza("Cannot retrieve app instance id from main thread");
            return null;
        }
        long jElapsedRealtime = zzm().elapsedRealtime();
        String strZze = zze(120000L);
        long jElapsedRealtime2 = zzm().elapsedRealtime() - jElapsedRealtime;
        return (strZze != null || jElapsedRealtime2 >= 120000) ? strZze : zze(120000 - jElapsedRealtime2);
    }

    final void zza(String str) {
        this.zzf.set(str);
    }

    private final String zze(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzq().zza(new zzhq(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException unused) {
                zzr().zzi().zza("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final void zzd(long j) {
        zza((String) null);
        zzq().zza(new zzhp(this, j));
    }

    public final void zzai() {
        zzd();
        zzb();
        zzw();
        if (this.zzx.zzah()) {
            if (zzt().zza(zzap.zzbw)) {
                zzx zzxVarZzt = zzt();
                zzxVarZzt.zzu();
                Boolean boolZzb = zzxVarZzt.zzb("google_analytics_deferred_deep_link_enabled");
                if (boolZzb != null && boolZzb.booleanValue()) {
                    zzr().zzw().zza("Deferred Deep Link feature enabled.");
                    zzq().zza(new Runnable(this) { // from class: com.google.android.gms.measurement.internal.zzhj
                        private final zzhk zza;

                        {
                            this.zza = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            zzhk zzhkVar = this.zza;
                            zzhkVar.zzd();
                            if (zzhkVar.zzs().zzu.zza()) {
                                zzhkVar.zzr().zzw().zza("Deferred Deep Link already retrieved. Not fetching again.");
                                return;
                            }
                            long jZza = zzhkVar.zzs().zzv.zza();
                            zzhkVar.zzs().zzv.zza(1 + jZza);
                            if (jZza < 5) {
                                zzhkVar.zzx.zzai();
                            } else {
                                zzhkVar.zzr().zzi().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                                zzhkVar.zzs().zzu.zza(true);
                            }
                        }
                    });
                }
            }
            zzh().zzae();
            this.zzb = false;
            String strZzw = zzs().zzw();
            if (TextUtils.isEmpty(strZzw)) {
                return;
            }
            zzl().zzaa();
            if (strZzw.equals(Build.VERSION.RELEASE)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", strZzw);
            zza("auto", "_ou", bundle);
        }
    }

    public final void zza(zzhf zzhfVar) {
        zzhf zzhfVar2;
        zzd();
        zzb();
        zzw();
        if (zzhfVar != null && zzhfVar != (zzhfVar2 = this.zzc)) {
            Preconditions.checkState(zzhfVar2 == null, "EventInterceptor already set.");
        }
        this.zzc = zzhfVar;
    }

    public final void zza(zzhi zzhiVar) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzhiVar);
        if (this.zzd.add(zzhiVar)) {
            return;
        }
        zzr().zzi().zza("OnEventListener already registered");
    }

    public final void zzb(zzhi zzhiVar) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzhiVar);
        if (this.zzd.remove(zzhiVar)) {
            return;
        }
        zzr().zzi().zza("OnEventListener had not been registered");
    }

    public final void zza(Bundle bundle) {
        zza(bundle, zzm().currentTimeMillis());
    }

    public final void zza(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzb();
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzr().zzi().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        zzb(bundle2, j);
    }

    public final void zzb(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("app_id"));
        zza();
        zzb(new Bundle(bundle), zzm().currentTimeMillis());
    }

    private final void zzb(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzhb.zza(bundle, "app_id", String.class, null);
        zzhb.zza(bundle, "origin", String.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzhb.zza(bundle, "value", Object.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzhb.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle.get("value");
        if (zzp().zzc(string) != 0) {
            zzr().zzf().zza("Invalid conditional user property name", zzo().zzc(string));
            return;
        }
        if (zzp().zzb(string, obj) != 0) {
            zzr().zzf().zza("Invalid conditional user property value", zzo().zzc(string), obj);
            return;
        }
        Object objZzc = zzp().zzc(string, obj);
        if (objZzc == null) {
            zzr().zzf().zza("Unable to normalize conditional user property value", zzo().zzc(string), obj);
            return;
        }
        zzhb.zza(bundle, objZzc);
        long j2 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
        if (!TextUtils.isEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) && (j2 > 15552000000L || j2 < 1)) {
            zzr().zzf().zza("Invalid conditional user property timeout", zzo().zzc(string), Long.valueOf(j2));
            return;
        }
        long j3 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
        if (j3 > 15552000000L || j3 < 1) {
            zzr().zzf().zza("Invalid conditional user property time to live", zzo().zzc(string), Long.valueOf(j3));
        } else {
            zzq().zza(new zzhr(this, bundle));
        }
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        zzb();
        zzb((String) null, str, str2, bundle);
    }

    public final void zza(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zza();
        zzb(str, str2, str3, bundle);
    }

    private final void zzb(String str, String str2, String str3, Bundle bundle) {
        long jCurrentTimeMillis = zzm().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        Bundle bundle2 = new Bundle();
        if (str != null) {
            bundle2.putString("app_id", str);
        }
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str2);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, jCurrentTimeMillis);
        if (str3 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str3);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzq().zza(new zzhu(this, bundle2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(Bundle bundle) {
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        if (!this.zzx.zzab()) {
            zzr().zzx().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzkq zzkqVar = new zzkq(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), bundle.getString("origin"));
        try {
            zzan zzanVarZza = zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false);
            zzh().zza(new zzv(bundle.getString("app_id"), bundle.getString("origin"), zzkqVar, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zzanVarZza, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(Bundle bundle) {
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        if (!this.zzx.zzab()) {
            zzr().zzx().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        try {
            zzh().zza(new zzv(bundle.getString("app_id"), bundle.getString("origin"), new zzkq(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), 0L, null, null), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        zzb();
        return zzb((String) null, str, str2);
    }

    public final ArrayList<Bundle> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3);
    }

    private final ArrayList<Bundle> zzb(String str, String str2, String str3) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        }
        if (zzw.zza()) {
            zzr().zzf().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzx.zzq().zza(atomicReference, BootloaderScanner.TIMEOUT, "get conditional user properties", new zzht(this, atomicReference, str, str2, str3));
        List list = (List) atomicReference.get();
        if (list == null) {
            zzr().zzf().zza("Timed out waiting for get conditional user properties", str);
            return new ArrayList<>();
        }
        return zzkv.zzb((List<zzv>) list);
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzb();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> zza(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3, z);
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        if (zzw.zza()) {
            zzr().zzf().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzx.zzq().zza(atomicReference, BootloaderScanner.TIMEOUT, "get user properties", new zzhv(this, atomicReference, str, str2, str3, z));
        List<zzkq> list = (List) atomicReference.get();
        if (list == null) {
            zzr().zzf().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzkq zzkqVar : list) {
            arrayMap.put(zzkqVar.zza, zzkqVar.zza());
        }
        return arrayMap;
    }

    public final String zzaj() {
        zzio zzioVarZzac = this.zzx.zzv().zzac();
        if (zzioVarZzac != null) {
            return zzioVarZzac.zza;
        }
        return null;
    }

    public final String zzak() {
        zzio zzioVarZzac = this.zzx.zzv().zzac();
        if (zzioVarZzac != null) {
            return zzioVarZzac.zzb;
        }
        return null;
    }

    public final String zzal() {
        if (this.zzx.zzo() != null) {
            return this.zzx.zzo();
        }
        try {
            return GoogleServices.getGoogleAppId();
        } catch (IllegalStateException e2) {
            this.zzx.zzr().zzf().zza("getGoogleAppId failed with exception", e2);
            return null;
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
}