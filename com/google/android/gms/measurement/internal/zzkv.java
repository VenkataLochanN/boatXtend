package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzln;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.util.DateUtil;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkv extends zzgz {
    private static final String[] zza = {"firebase_", "google_", "ga_"};
    private SecureRandom zzb;
    private final AtomicLong zzc;
    private int zzd;
    private Integer zze;

    zzkv(zzgf zzgfVar) {
        super(zzgfVar);
        this.zze = null;
        this.zzc = new AtomicLong(0L);
    }

    @Override // com.google.android.gms.measurement.internal.zzgz
    protected final boolean zze() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzgz
    protected final void f_() {
        zzd();
        SecureRandom secureRandom = new SecureRandom();
        long jNextLong = secureRandom.nextLong();
        if (jNextLong == 0) {
            jNextLong = secureRandom.nextLong();
            if (jNextLong == 0) {
                zzr().zzi().zza("Utils falling back to Random for random id");
            }
        }
        this.zzc.set(jNextLong);
    }

    public final long zzg() {
        long andIncrement;
        long j;
        if (this.zzc.get() == 0) {
            synchronized (this.zzc) {
                long jNextLong = new Random(System.nanoTime() ^ zzm().currentTimeMillis()).nextLong();
                int i = this.zzd + 1;
                this.zzd = i;
                j = jNextLong + ((long) i);
            }
            return j;
        }
        synchronized (this.zzc) {
            this.zzc.compareAndSet(-1L, 1L);
            andIncrement = this.zzc.getAndIncrement();
        }
        return andIncrement;
    }

    final SecureRandom zzh() {
        zzd();
        if (this.zzb == null) {
            this.zzb = new SecureRandom();
        }
        return this.zzb;
    }

    static boolean zza(String str) {
        Preconditions.checkNotEmpty(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    final Bundle zza(Uri uri) {
        String queryParameter;
        String queryParameter2;
        String queryParameter3;
        String queryParameter4;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                queryParameter = uri.getQueryParameter("utm_campaign");
                queryParameter2 = uri.getQueryParameter("utm_source");
                queryParameter3 = uri.getQueryParameter("utm_medium");
                queryParameter4 = uri.getQueryParameter("gclid");
            } else {
                queryParameter = null;
                queryParameter2 = null;
                queryParameter3 = null;
                queryParameter4 = null;
            }
            if (TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3) && TextUtils.isEmpty(queryParameter4)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(FirebaseAnalytics.Param.CAMPAIGN, queryParameter);
            }
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(FirebaseAnalytics.Param.SOURCE, queryParameter2);
            }
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(FirebaseAnalytics.Param.MEDIUM, queryParameter3);
            }
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString("gclid", queryParameter4);
            }
            String queryParameter5 = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter5);
            }
            String queryParameter6 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter6)) {
                bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter6);
            }
            String queryParameter7 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter7)) {
                bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter7);
            }
            String queryParameter8 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
            if (!TextUtils.isEmpty(queryParameter8)) {
                bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter8);
            }
            String queryParameter9 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter9)) {
                bundle.putString("anid", queryParameter9);
            }
            return bundle;
        } catch (UnsupportedOperationException e2) {
            zzr().zzi().zza("Install referrer url isn't a hierarchical URI", e2);
            return null;
        }
    }

    static boolean zza(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    final boolean zza(String str, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            zzr().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt)) {
            zzr().zzh().zza("Name must start with a letter. Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                zzr().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    final boolean zzb(String str, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            zzr().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt) && iCodePointAt != 95) {
            zzr().zzh().zza("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                zzr().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    final boolean zza(String str, String[] strArr, String str2) {
        boolean z;
        boolean z2;
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr2 = zza;
        int length = strArr2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (str2.startsWith(strArr2[i])) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            zzr().zzh().zza("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            Preconditions.checkNotNull(strArr);
            int length2 = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z2 = false;
                    break;
                }
                if (zzc(str2, strArr[i2])) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            if (z2) {
                zzr().zzh().zza("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    final boolean zza(String str, int i, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        }
        zzr().zzh().zza("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
        return false;
    }

    final int zzb(String str) {
        if (!zzb("event", str)) {
            return 2;
        }
        if (zza("event", zzhe.zza, str)) {
            return !zza("event", 40, str) ? 2 : 0;
        }
        return 13;
    }

    final int zzc(String str) {
        if (!zzb("user property", str)) {
            return 6;
        }
        if (zza("user property", zzhg.zza, str)) {
            return !zza("user property", 24, str) ? 6 : 0;
        }
        return 15;
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                String strValueOf = String.valueOf(obj);
                if (strValueOf.codePointCount(0, strValueOf.length()) > i) {
                    zzr().zzk().zza("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(strValueOf.length()));
                    return false;
                }
            } else {
                if ((obj instanceof Bundle) && z) {
                    return true;
                }
                if ((obj instanceof Parcelable[]) && z) {
                    for (Parcelable parcelable : (Parcelable[]) obj) {
                        if (!(parcelable instanceof Bundle)) {
                            zzr().zzk().zza("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str2);
                            return false;
                        }
                    }
                    return true;
                }
                if (!(obj instanceof ArrayList) || !z) {
                    return false;
                }
                ArrayList arrayList = (ArrayList) obj;
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList.get(i2);
                    i2++;
                    if (!(obj2 instanceof Bundle)) {
                        zzr().zzk().zza("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    final boolean zza(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (zzg(str)) {
                return true;
            }
            if (this.zzx.zzl()) {
                zzr().zzh().zza("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzfb.zza(str));
            }
            return false;
        }
        if (zzln.zzb() && zzt().zza(zzap.zzcf) && !TextUtils.isEmpty(str3)) {
            return true;
        }
        if (!TextUtils.isEmpty(str2)) {
            if (zzg(str2)) {
                return true;
            }
            zzr().zzh().zza("Invalid admob_app_id. Analytics disabled.", zzfb.zza(str2));
            return false;
        }
        if (this.zzx.zzl()) {
            zzr().zzh().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
        }
        return false;
    }

    static boolean zza(String str, String str2, String str3, String str4) {
        boolean zIsEmpty = TextUtils.isEmpty(str);
        boolean zIsEmpty2 = TextUtils.isEmpty(str2);
        if (!zIsEmpty && !zIsEmpty2) {
            return !str.equals(str2);
        }
        if (zIsEmpty && zIsEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        }
        if (zIsEmpty || !zIsEmpty2) {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
        if (TextUtils.isEmpty(str4)) {
            return false;
        }
        return TextUtils.isEmpty(str3) || !str3.equals(str4);
    }

    private static boolean zzg(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1L : 0L);
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            return zza(String.valueOf(obj), i, z);
        }
        return null;
    }

    public static String zza(String str, int i, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    final Object zza(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(256, obj, true);
        }
        return zza(zze(str) ? 256 : 100, obj, false);
    }

    static Bundle[] zza(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            return (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        }
        if (!(obj instanceof ArrayList)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) obj;
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0183 A[PHI: r14
  0x0183: PHI (r14v2 int) = (r14v1 int), (r14v3 int) binds: [B:82:0x0146, B:84:0x014c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final android.os.Bundle zza(java.lang.String r17, java.lang.String r18, android.os.Bundle r19, java.util.List<java.lang.String> r20, boolean r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkv.zza(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean, boolean):android.os.Bundle");
    }

    private static boolean zza(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", i);
        return true;
    }

    private static void zza(Bundle bundle, Object obj) {
        Preconditions.checkNotNull(bundle);
        if (obj != null) {
            if ((obj instanceof String) || (obj instanceof CharSequence)) {
                bundle.putLong("_el", String.valueOf(obj).length());
            }
        }
    }

    private final int zzh(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        if ("_id".equals(str)) {
            return 256;
        }
        return (zzt().zza(zzap.zzcc) && "_lgclid".equals(str)) ? 100 : 36;
    }

    final int zzb(String str, Object obj) {
        boolean zZza;
        if ("_ldl".equals(str)) {
            zZza = zza("user property referrer", str, zzh(str), obj, false);
        } else {
            zZza = zza("user property", str, zzh(str), obj, false);
        }
        return zZza ? 0 : 7;
    }

    final Object zzc(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zza(zzh(str), obj, true);
        }
        return zza(zzh(str), obj, false);
    }

    final void zza(Bundle bundle, String str, Object obj) {
        if (bundle == null) {
            return;
        }
        if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof String) {
            bundle.putString(str, String.valueOf(obj));
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (str != null) {
            zzr().zzk().zza("Not putting event parameter. Invalid value type. name, type", zzo().zzb(str), obj != null ? obj.getClass().getSimpleName() : null);
        }
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza((String) null, i, str, str2, i2);
    }

    final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zza(bundle, i);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", i2);
        }
        this.zzx.zzu();
        this.zzx.zzh().zza("auto", "_err", bundle);
    }

    static MessageDigest zzi() {
        MessageDigest messageDigest;
        for (int i = 0; i < 2; i++) {
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException unused) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
        }
        return null;
    }

    static long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int i = 0;
        Preconditions.checkState(bArr.length > 0);
        long j = 0;
        for (int length = bArr.length - 1; length >= 0 && length >= bArr.length - 8; length--) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
        }
        return j;
    }

    static boolean zza(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 24) {
            return zzb(context, "com.google.android.gms.measurement.AppMeasurementJobService");
        }
        return zzb(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    private static boolean zzb(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) != null) {
                if (serviceInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    final boolean zzd(String str) {
        zzd();
        if (Wrappers.packageManager(zzn()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzr().zzw().zza("Permission not granted", str);
        return false;
    }

    static boolean zze(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzc(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    static boolean zza(Boolean bool, Boolean bool2) {
        if (bool == null && bool2 == null) {
            return true;
        }
        if (bool == null) {
            return false;
        }
        return bool.equals(bool2);
    }

    static boolean zza(List<String> list, List<String> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        return list.equals(list2);
    }

    final boolean zzf(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strZzv = zzt().zzv();
        zzu();
        return strZzv.equals(str);
    }

    final Bundle zza(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object objZza = zza(str, bundle.get(str));
                if (objZza == null) {
                    zzr().zzk().zza("Param value can't be null", zzo().zzb(str));
                } else {
                    zza(bundle2, str, objZza);
                }
            }
        }
        return bundle2;
    }

    final zzan zza(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zzb(str2) != 0) {
            zzr().zzf().zza("Invalid conditional property event name", zzo().zzc(str2));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str3);
        return new zzan(str2, new zzam(zza(zza(str, str2, bundle2, CollectionUtils.listOf("_o"), false, false))), str3, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0076  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0069 -> B:17:0x0076). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final long zza(android.content.Context r7, java.lang.String r8) {
        /*
            r6 = this;
            r6.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            android.content.pm.PackageManager r0 = r7.getPackageManager()
            java.security.MessageDigest r1 = zzi()
            r2 = -1
            r4 = 0
            if (r1 != 0) goto L25
            com.google.android.gms.measurement.internal.zzfb r7 = r6.zzr()
            com.google.android.gms.measurement.internal.zzfd r7 = r7.zzf()
            java.lang.String r8 = "Could not get MD5 instance"
            r7.zza(r8)
            goto L77
        L25:
            if (r0 == 0) goto L76
            boolean r8 = r6.zzc(r7, r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            if (r8 != 0) goto L76
            com.google.android.gms.common.wrappers.PackageManagerWrapper r7 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            android.content.Context r8 = r6.zzn()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            java.lang.String r8 = r8.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            r0 = 64
            android.content.pm.PackageInfo r7 = r7.getPackageInfo(r8, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            android.content.pm.Signature[] r8 = r7.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            if (r8 == 0) goto L5a
            android.content.pm.Signature[] r8 = r7.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            int r8 = r8.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            if (r8 <= 0) goto L5a
            android.content.pm.Signature[] r7 = r7.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            r8 = 0
            r7 = r7[r8]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            byte[] r7 = r7.toByteArray()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            byte[] r7 = r1.digest(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            long r2 = zza(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            goto L77
        L5a:
            com.google.android.gms.measurement.internal.zzfb r7 = r6.zzr()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            com.google.android.gms.measurement.internal.zzfd r7 = r7.zzi()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            java.lang.String r8 = "Could not get signatures"
            r7.zza(r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            goto L77
        L68:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzfb r8 = r6.zzr()
            com.google.android.gms.measurement.internal.zzfd r8 = r8.zzf()
            java.lang.String r0 = "Package name not found"
            r8.zza(r0, r7)
        L76:
            r2 = r4
        L77:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkv.zza(android.content.Context, java.lang.String):long");
    }

    private final boolean zzc(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (PackageManager.NameNotFoundException e2) {
            zzr().zzf().zza("Package name not found", e2);
            return true;
        } catch (CertificateException e3) {
            zzr().zzf().zza("Error obtaining certificate", e3);
            return true;
        }
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            return parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }

    public static Bundle zzb(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str, new Bundle((Bundle) obj));
            } else {
                int i = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i < parcelableArr.length) {
                        if (parcelableArr[i] instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                        }
                        i++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if (obj2 instanceof Bundle) {
                            list.set(i, new Bundle((Bundle) obj2));
                        }
                        i++;
                    }
                }
            }
        }
        return bundle2;
    }

    public final int zzj() {
        if (this.zze == null) {
            this.zze = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(zzn()) / 1000);
        }
        return this.zze.intValue();
    }

    public final int zza(int i) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(zzn(), 12451000);
    }

    public static long zza(long j, long j2) {
        return (j + (j2 * DateUtil.MINUTE)) / DateUtil.DAY;
    }

    final String zzk() {
        byte[] bArr = new byte[16];
        zzh().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    final void zza(Bundle bundle, long j) {
        long j2 = bundle.getLong("_et");
        if (j2 != 0) {
            zzr().zzi().zza("Params already contained engagement", Long.valueOf(j2));
        }
        bundle.putLong("_et", j + j2);
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zznVar.zza(bundle);
        } catch (RemoteException e2) {
            this.zzx.zzr().zzi().zza("Error returning string value to wrapper", e2);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j);
        try {
            zznVar.zza(bundle);
        } catch (RemoteException e2) {
            this.zzx.zzr().zzi().zza("Error returning long value to wrapper", e2);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i);
        try {
            zznVar.zza(bundle);
        } catch (RemoteException e2) {
            this.zzx.zzr().zzi().zza("Error returning int value to wrapper", e2);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zznVar.zza(bundle);
        } catch (RemoteException e2) {
            this.zzx.zzr().zzi().zza("Error returning byte array to wrapper", e2);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            zznVar.zza(bundle);
        } catch (RemoteException e2) {
            this.zzx.zzr().zzi().zza("Error returning boolean value to wrapper", e2);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, Bundle bundle) {
        try {
            zznVar.zza(bundle);
        } catch (RemoteException e2) {
            this.zzx.zzr().zzi().zza("Error returning bundle value to wrapper", e2);
        }
    }

    public static Bundle zza(List<zzkq> list) {
        Bundle bundle = new Bundle();
        if (list == null) {
            return bundle;
        }
        for (zzkq zzkqVar : list) {
            if (zzkqVar.zzd != null) {
                bundle.putString(zzkqVar.zza, zzkqVar.zzd);
            } else if (zzkqVar.zzc != null) {
                bundle.putLong(zzkqVar.zza, zzkqVar.zzc.longValue());
            } else if (zzkqVar.zzf != null) {
                bundle.putDouble(zzkqVar.zza, zzkqVar.zzf.doubleValue());
            }
        }
        return bundle;
    }

    public final void zza(com.google.android.gms.internal.measurement.zzn zznVar, ArrayList<Bundle> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zznVar.zza(bundle);
        } catch (RemoteException e2) {
            this.zzx.zzr().zzi().zza("Error returning bundle list to wrapper", e2);
        }
    }

    public static ArrayList<Bundle> zzb(List<zzv> list) {
        if (list == null) {
            return new ArrayList<>(0);
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (zzv zzvVar : list) {
            Bundle bundle = new Bundle();
            bundle.putString("app_id", zzvVar.zza);
            bundle.putString("origin", zzvVar.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, zzvVar.zzd);
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzvVar.zzc.zza);
            zzhb.zza(bundle, zzvVar.zzc.zza());
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, zzvVar.zze);
            if (zzvVar.zzf != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzvVar.zzf);
            }
            if (zzvVar.zzg != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, zzvVar.zzg.zza);
                if (zzvVar.zzg.zzb != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, zzvVar.zzg.zzb.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, zzvVar.zzh);
            if (zzvVar.zzi != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, zzvVar.zzi.zza);
                if (zzvVar.zzi.zzb != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, zzvVar.zzi.zzb.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, zzvVar.zzc.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, zzvVar.zzj);
            if (zzvVar.zzk != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, zzvVar.zzk.zza);
                if (zzvVar.zzk.zzb != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, zzvVar.zzk.zzb.zzb());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    public final URL zza(long j, String str, String str2, long j2) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String strConcat = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", String.format("v%s.%s", Long.valueOf(j), Integer.valueOf(zzj())), str2, str, Long.valueOf(j2));
            if (str.equals(zzt().zzw())) {
                strConcat = strConcat.concat("&ddl_test=1");
            }
            return new URL(strConcat);
        } catch (IllegalArgumentException | MalformedURLException e2) {
            zzr().zzf().zza("Failed to create BOW URL for Deferred Deep Link. exception", e2.getMessage());
            return null;
        }
    }

    final boolean zza(String str, double d2) {
        try {
            SharedPreferences.Editor editorEdit = zzn().getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
            editorEdit.putString("deeplink", str);
            editorEdit.putLong("timestamp", Double.doubleToRawLongBits(d2));
            return editorEdit.commit();
        } catch (Exception e2) {
            zzr().zzf().zza("Failed to persist Deferred Deep Link. exception", e2);
            return false;
        }
    }

    public final boolean zzv() {
        try {
            zzn().getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
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