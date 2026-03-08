package com.baidu.android.bbalbs.common.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.system.ErrnoException;
import android.system.Os;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.android.bbalbs.common.a.d;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.crypto.Cipher;
import kotlin.UByte;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f1968a = new String(com.baidu.android.bbalbs.common.a.b.a(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61})) + new String(com.baidu.android.bbalbs.common.a.b.a(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static C0014b f1969e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f1970b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1971c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private PublicKey f1972d;

    /* JADX INFO: Access modifiers changed from: private */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ApplicationInfo f1973a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1974b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1975c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1976d;

        private a() {
            this.f1974b = 0;
            this.f1975c = false;
            this.f1976d = false;
        }

        /* synthetic */ a(com.baidu.android.bbalbs.common.util.c cVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.baidu.android.bbalbs.common.util.b$b, reason: collision with other inner class name */
    private static class C0014b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1977a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1978b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1979c;

        private C0014b() {
            this.f1979c = 2;
        }

        /* synthetic */ C0014b(com.baidu.android.bbalbs.common.util.c cVar) {
            this();
        }

        public static C0014b a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("deviceid");
                String string2 = jSONObject.getString("imei");
                int i = jSONObject.getInt("ver");
                if (!TextUtils.isEmpty(string) && string2 != null) {
                    C0014b c0014b = new C0014b();
                    c0014b.f1977a = string;
                    c0014b.f1978b = string2;
                    c0014b.f1979c = i;
                    return c0014b;
                }
            } catch (JSONException e2) {
                b.b(e2);
            }
            return null;
        }

        public String a() {
            try {
                return new JSONObject().put("deviceid", this.f1977a).put("imei", this.f1978b).put("ver", this.f1979c).toString();
            } catch (JSONException e2) {
                b.b(e2);
                return null;
            }
        }

        public String b() {
            String str = this.f1978b;
            if (TextUtils.isEmpty(str)) {
                str = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            }
            return this.f1977a + "|" + new StringBuffer(str).reverse().toString();
        }
    }

    static class c {
        static boolean a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e2) {
                b.b(e2);
                return false;
            }
        }
    }

    private b(Context context) throws Throwable {
        this.f1970b = context.getApplicationContext();
        a();
    }

    public static String a(Context context) {
        return c(context).b();
    }

    private static String a(File file) throws Throwable {
        FileReader fileReader;
        char[] cArr;
        CharArrayWriter charArrayWriter;
        try {
            fileReader = new FileReader(file);
        } catch (Exception e2) {
            e = e2;
            fileReader = null;
        } catch (Throwable th) {
            th = th;
            fileReader = null;
        }
        try {
            try {
                cArr = new char[8192];
                charArrayWriter = new CharArrayWriter();
            } catch (Throwable th2) {
                th = th2;
            }
            while (true) {
                int i = fileReader.read(cArr);
                if (i <= 0) {
                    break;
                }
                charArrayWriter.write(cArr, 0, i);
                th = th2;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Exception e3) {
                        b(e3);
                    }
                }
                throw th;
            }
            String string = charArrayWriter.toString();
            try {
                fileReader.close();
            } catch (Exception e4) {
                b(e4);
            }
            return string;
        } catch (Exception e5) {
            e = e5;
            b(e);
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e6) {
                    b(e6);
                }
            }
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb;
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String string = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb = new StringBuilder();
                sb.append(string);
                string = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            } else {
                sb = new StringBuilder();
            }
            sb.append(string);
            sb.append(hexString);
            string = sb.toString();
        }
        return string.toLowerCase();
    }

    private List<a> a(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.f1970b.getPackageManager();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.applicationInfo != null) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] bArrA = com.baidu.android.bbalbs.common.a.b.a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(bArrA));
                                a aVar = new a(null);
                                aVar.f1974b = jSONObject.getInt("priority");
                                aVar.f1973a = resolveInfo.activityInfo.applicationInfo;
                                if (this.f1970b.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    aVar.f1976d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (int i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (a(strArr, a(packageInfo.signatures))) {
                                            byte[] bArrA2 = a(com.baidu.android.bbalbs.common.a.b.a(string2.getBytes()), this.f1972d);
                                            if (bArrA2 != null && Arrays.equals(bArrA2, d.a(bArrA))) {
                                                aVar.f1975c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(aVar);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new com.baidu.android.bbalbs.common.util.c(this));
        return arrayList;
    }

    private void a() throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(com.baidu.android.bbalbs.common.util.a.a());
            } catch (Exception unused) {
                byteArrayInputStream = null;
            } catch (Throwable th2) {
                byteArrayInputStream = null;
                th = th2;
            }
            try {
                this.f1972d = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
                byteArrayInputStream.close();
            } catch (Exception unused2) {
                if (byteArrayInputStream == null) {
                } else {
                    byteArrayInputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception e2) {
                        b(e2);
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            b(e3);
        }
    }

    private boolean a(String str) {
        int i = Build.VERSION.SDK_INT >= 24 ? 0 : 1;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            try {
                fileOutputStreamOpenFileOutput = this.f1970b.openFileOutput("libcuid.so", i);
                fileOutputStreamOpenFileOutput.write(str.getBytes());
                fileOutputStreamOpenFileOutput.flush();
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e2) {
                        b(e2);
                    }
                }
                if (i == 0) {
                    return c.a(new File(this.f1970b.getFilesDir(), "libcuid.so").getAbsolutePath(), 436);
                }
                return true;
            } catch (Exception e3) {
                b(e3);
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e4) {
                        b(e4);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception e5) {
                    b(e5);
                }
            }
            throw th;
        }
    }

    private boolean a(String str, String str2) {
        try {
            return Settings.System.putString(this.f1970b.getContentResolver(), str, str2);
        } catch (Exception e2) {
            b(e2);
            return false;
        }
    }

    private boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : strArr2) {
            hashSet2.add(str2);
        }
        return hashSet.equals(hashSet2);
    }

    private static byte[] a(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        return cipher.doFinal(bArr);
    }

    private String[] a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = a(d.a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01be  */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.baidu.android.bbalbs.common.util.b] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.baidu.android.bbalbs.common.util.c] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.android.bbalbs.common.util.b.C0014b b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 576
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.bbalbs.common.util.b.b():com.baidu.android.bbalbs.common.util.b$b");
    }

    public static String b(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    private String b(String str) {
        try {
            return Settings.System.getString(this.f1970b.getContentResolver(), str);
        } catch (Exception e2) {
            b(e2);
            return null;
        }
    }

    private static void b(String str, String str2) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(com.baidu.android.bbalbs.common.a.b.a(com.baidu.android.bbalbs.common.a.a.a(f1968a, f1968a, (str + "=" + str2).getBytes()), "utf-8"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Throwable th) {
    }

    private static C0014b c(Context context) {
        if (f1969e == null) {
            synchronized (C0014b.class) {
                if (f1969e == null) {
                    SystemClock.uptimeMillis();
                    f1969e = new b(context).b();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return f1969e;
    }

    private boolean c() {
        return c("android.permission.WRITE_SETTINGS");
    }

    private boolean c(String str) {
        return this.f1970b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private C0014b d() {
        String strB = b("com.baidu.deviceid");
        String strB2 = b("bd_setting_i");
        if (TextUtils.isEmpty(strB2)) {
            strB2 = h("");
            TextUtils.isEmpty(strB2);
        }
        if (TextUtils.isEmpty(strB)) {
            strB = b(com.baidu.android.bbalbs.common.a.c.a(("com.baidu" + strB2 + b(this.f1970b)).getBytes(), true));
        }
        com.baidu.android.bbalbs.common.util.c cVar = null;
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        C0014b c0014b = new C0014b(cVar);
        c0014b.f1977a = strB;
        c0014b.f1978b = strB2;
        return c0014b;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ab A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.android.bbalbs.common.util.b.C0014b d(java.lang.String r11) {
        /*
            r10 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 1
            r3 = 23
            if (r0 >= r3) goto La
            r0 = r2
            goto Lb
        La:
            r0 = r1
        Lb:
            r3 = 0
            if (r0 == 0) goto L15
            boolean r4 = android.text.TextUtils.isEmpty(r11)
            if (r4 == 0) goto L15
            return r3
        L15:
            java.lang.String r4 = ""
            java.io.File r5 = new java.io.File
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r7 = "baidu/.cuid"
            r5.<init>(r6, r7)
            boolean r6 = r5.exists()
            if (r6 == 0) goto L2a
            r6 = r1
            goto L36
        L2a:
            java.io.File r5 = new java.io.File
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r7 = "backups/.SystemConfig/.cuid"
            r5.<init>(r6, r7)
            r6 = r2
        L36:
            java.io.FileReader r7 = new java.io.FileReader     // Catch: java.lang.Throwable -> L9b
            r7.<init>(r5)     // Catch: java.lang.Throwable -> L9b
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L9b
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L9b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b
            r7.<init>()     // Catch: java.lang.Throwable -> L9b
        L45:
            java.lang.String r8 = r5.readLine()     // Catch: java.lang.Throwable -> L9b
            if (r8 == 0) goto L54
            r7.append(r8)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r8 = "\r\n"
            r7.append(r8)     // Catch: java.lang.Throwable -> L9b
            goto L45
        L54:
            r5.close()     // Catch: java.lang.Throwable -> L9b
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L9b
            java.lang.String r8 = com.baidu.android.bbalbs.common.util.b.f1968a     // Catch: java.lang.Throwable -> L9b
            java.lang.String r9 = com.baidu.android.bbalbs.common.util.b.f1968a     // Catch: java.lang.Throwable -> L9b
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L9b
            byte[] r7 = r7.getBytes()     // Catch: java.lang.Throwable -> L9b
            byte[] r7 = com.baidu.android.bbalbs.common.a.b.a(r7)     // Catch: java.lang.Throwable -> L9b
            byte[] r7 = com.baidu.android.bbalbs.common.a.a.b(r8, r9, r7)     // Catch: java.lang.Throwable -> L9b
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r7 = "="
            java.lang.String[] r5 = r5.split(r7)     // Catch: java.lang.Throwable -> L9b
            if (r5 == 0) goto L96
            int r7 = r5.length     // Catch: java.lang.Throwable -> L9b
            r8 = 2
            if (r7 != r8) goto L96
            if (r0 == 0) goto L89
            r1 = r5[r1]     // Catch: java.lang.Throwable -> L9b
            boolean r1 = r11.equals(r1)     // Catch: java.lang.Throwable -> L9b
            if (r1 == 0) goto L89
            r0 = r5[r2]     // Catch: java.lang.Throwable -> L9b
            goto L95
        L89:
            if (r0 != 0) goto L96
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L9b
            if (r0 == 0) goto L93
            r11 = r5[r2]     // Catch: java.lang.Throwable -> L9b
        L93:
            r0 = r5[r2]     // Catch: java.lang.Throwable -> L9b
        L95:
            r4 = r0
        L96:
            if (r6 != 0) goto L9b
            b(r11, r4)     // Catch: java.lang.Throwable -> L9b
        L9b:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto Lab
            com.baidu.android.bbalbs.common.util.b$b r0 = new com.baidu.android.bbalbs.common.util.b$b
            r0.<init>(r3)
            r0.f1977a = r4
            r0.f1978b = r11
            return r0
        Lab:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.bbalbs.common.util.b.d(java.lang.String):com.baidu.android.bbalbs.common.util.b$b");
    }

    private C0014b e() throws Throwable {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (!file.exists()) {
            return null;
        }
        String strA = a(file);
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        try {
            return C0014b.a(new String(com.baidu.android.bbalbs.common.a.a.b(f1968a, f1968a, com.baidu.android.bbalbs.common.a.b.a(strA.getBytes()))));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return com.baidu.android.bbalbs.common.a.b.a(com.baidu.android.bbalbs.common.a.a.a(f1968a, f1968a, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException | Exception e2) {
            b(e2);
            return "";
        }
    }

    private static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(com.baidu.android.bbalbs.common.a.a.b(f1968a, f1968a, com.baidu.android.bbalbs.common.a.b.a(str.getBytes())));
        } catch (Exception e2) {
            b(e2);
            return "";
        }
    }

    private static void g(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid2");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    private String h(String str) {
        String deviceId = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f1970b.getSystemService("phone");
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
            }
        } catch (Exception e2) {
            Log.e("DeviceId", "Read IMEI failed", e2);
        }
        String strI = i(deviceId);
        return TextUtils.isEmpty(strI) ? str : strI;
    }

    private static String i(String str) {
        return (str == null || !str.contains(":")) ? str : "";
    }
}