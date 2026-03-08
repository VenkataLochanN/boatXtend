package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.y;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static List<File> f5576a = new ArrayList();

    private static Map<String, Integer> d(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap map = new HashMap();
            for (String str2 : str.split(AppInfo.DELIM)) {
                String[] strArrSplit = str2.split(":");
                if (strArrSplit.length != 2) {
                    y.e("error format at %s", str2);
                    return null;
                }
                map.put(strArrSplit[0], Integer.valueOf(Integer.parseInt(strArrSplit[1])));
            }
            return map;
        } catch (Exception e2) {
            y.e("error format intStateStr %s", str);
            e2.printStackTrace();
            return null;
        }
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] strArrSplit = str.split(IOUtils.LINE_SEPARATOR_UNIX);
        if (strArrSplit == null || strArrSplit.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
        return sb.toString();
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        boolean z;
        String str;
        String str2;
        boolean zEqualsIgnoreCase;
        if (map != null) {
            if (com.tencent.bugly.crashreport.common.info.a.a(context) == null) {
                y.e("abnormal com info not created", new Object[0]);
            } else {
                String str3 = map.get("intStateStr");
                if (str3 == null || str3.trim().length() <= 0) {
                    y.e("no intStateStr", new Object[0]);
                } else {
                    z = true;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (!z) {
            return null;
        }
        Map<String, Integer> mapD = d(map.get("intStateStr"));
        if (mapD == null) {
            y.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            mapD.get("sino").intValue();
            mapD.get("sud").intValue();
            String str4 = map.get("soVersion");
            if (TextUtils.isEmpty(str4)) {
                y.e("error format at version", new Object[0]);
                return null;
            }
            String str5 = (String) a(map, "codeMsg", "unknown");
            String str6 = (String) a(map, "signalName", "unknown");
            map.get("errnoMsg");
            String str7 = (String) a(map, "stack", "unknown");
            String str8 = map.get("jstack");
            if (str8 != null) {
                str7 = str7 + "java:\n" + str8;
            }
            Integer num = mapD.get("sico");
            if (num == null || num.intValue() <= 0) {
                str = str5;
                str2 = str6;
            } else {
                str2 = str6 + "(" + str5 + ")";
                str = "KERNEL";
            }
            String str9 = map.get("nativeLog");
            byte[] bArrA = (str9 == null || str9.isEmpty()) ? null : ab.a((File) null, str9, "BuglyNativeLog.txt");
            String str10 = (String) a(map, "sendingProcess", "unknown");
            Integer num2 = mapD.get("spd");
            if (num2 != null) {
                str10 = str10 + "(" + num2 + ")";
            }
            String str11 = str10;
            String str12 = (String) a(map, "threadName", "unknown");
            Integer num3 = mapD.get("et");
            if (num3 != null) {
                str12 = str12 + "(" + num3 + ")";
            }
            String str13 = str12;
            String str14 = (String) a(map, "processName", "unknown");
            Integer num4 = mapD.get("ep");
            if (num4 != null) {
                str14 = str14 + "(" + num4 + ")";
            }
            CrashDetailBean crashDetailBeanPackageCrashDatas = nativeExceptionHandler.packageCrashDatas(str14, str13, (((long) mapD.get("ets").intValue()) * 1000) + (((long) mapD.get("etms").intValue()) / 1000), str2, (String) a(map, "errorAddr", "unknown"), a(str7), str, str11, (String) a(map, "tombPath", "unknown"), map.get("sysLogPath"), map.get("jniLogPath"), str4, bArrA, a(map), false, false);
            if (crashDetailBeanPackageCrashDatas != null) {
                crashDetailBeanPackageCrashDatas.m = (String) a(map, "userId", crashDetailBeanPackageCrashDatas.m);
                y.c("[Native record info] userId: %s", crashDetailBeanPackageCrashDatas.m);
                crashDetailBeanPackageCrashDatas.w = (String) a(map, "sysLog", crashDetailBeanPackageCrashDatas.w);
                crashDetailBeanPackageCrashDatas.f5454f = (String) a(map, "appVersion", crashDetailBeanPackageCrashDatas.w);
                y.c("[Native record info] appVersion: %s", crashDetailBeanPackageCrashDatas.f5454f);
                String str15 = map.get("isAppForeground");
                if (str15 != null) {
                    y.c("[Native record info] isAppForeground: %s", str15);
                    zEqualsIgnoreCase = str15.equalsIgnoreCase("true");
                } else {
                    zEqualsIgnoreCase = false;
                }
                crashDetailBeanPackageCrashDatas.Q = zEqualsIgnoreCase;
                crashDetailBeanPackageCrashDatas.P = b(map);
                crashDetailBeanPackageCrashDatas.z = null;
                crashDetailBeanPackageCrashDatas.k = true;
            }
            return crashDetailBeanPackageCrashDatas;
        } catch (Throwable th) {
            y.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    private static <KeyT, ValueT> ValueT a(Map<KeyT, ValueT> map, KeyT keyt, ValueT valuet) {
        ValueT valuet2;
        try {
            valuet2 = map.get(keyt);
        } catch (Exception e2) {
            y.a(e2);
        }
        return valuet2 != null ? valuet2 : valuet;
    }

    private static String a(BufferedInputStream bufferedInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bufferedInputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(1024);
            while (true) {
                try {
                    int i = bufferedInputStream.read();
                    if (i == -1) {
                        byteArrayOutputStream.close();
                        break;
                    }
                    if (i == 0) {
                        String str = new String(byteArrayOutputStream.toByteArray(), "UTf-8");
                        byteArrayOutputStream.close();
                        return str;
                    }
                    byteArrayOutputStream.write(i);
                } catch (Throwable th) {
                    th = th;
                    try {
                        y.a(th);
                        return null;
                    } finally {
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r7v6 */
    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) throws Throwable {
        BufferedInputStream bufferedInputStream;
        String str2;
        String strA;
        if (context == null || str == null || nativeExceptionHandler == null) {
            y.e("get eup record file args error", new Object[0]);
            return null;
        }
        File file = new File(str, "rqd_record.eup");
        if (file.exists()) {
            ?? CanRead = file.canRead();
            try {
                if (CanRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    } catch (IOException e2) {
                        e = e2;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        CanRead = 0;
                        if (CanRead != 0) {
                            try {
                                CanRead.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        String strA2 = a(bufferedInputStream);
                        if (strA2 != null && strA2.equals("NATIVE_RQD_REPORT")) {
                            HashMap map = new HashMap();
                            loop0: while (true) {
                                str2 = null;
                                while (true) {
                                    strA = a(bufferedInputStream);
                                    if (strA == null) {
                                        break loop0;
                                    }
                                    if (str2 == null) {
                                        str2 = strA;
                                    }
                                }
                                map.put(str2, strA);
                            }
                            if (str2 != null) {
                                y.e("record not pair! drop! %s", str2);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                                return null;
                            }
                            CrashDetailBean crashDetailBeanA = a(context, map, nativeExceptionHandler);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                            return crashDetailBeanA;
                        }
                        y.e("record read fail! %s", strA2);
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                        return null;
                    } catch (IOException e7) {
                        e = e7;
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e8) {
                                e8.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    private static String b(String str, String str2) {
        BufferedReader bufferedReaderA = ab.a(str, "reg_record.txt");
        if (bufferedReaderA == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReaderA.readLine();
            if (line != null && line.startsWith(str2)) {
                int i = 18;
                int i2 = 0;
                int length = 0;
                while (true) {
                    String line2 = bufferedReaderA.readLine();
                    if (line2 == null) {
                        break;
                    }
                    if (i2 % 4 == 0) {
                        if (i2 > 0) {
                            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                        }
                        sb.append("  ");
                    } else {
                        if (line2.length() > 16) {
                            i = 28;
                        }
                        sb.append("                ".substring(0, i - length));
                    }
                    length = line2.length();
                    sb.append(line2);
                    i2++;
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                return sb.toString();
            }
            if (bufferedReaderA != null) {
                try {
                    bufferedReaderA.close();
                } catch (Exception e2) {
                    y.a(e2);
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                y.a(th);
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e3) {
                        y.a(e3);
                    }
                }
                return null;
            } finally {
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e4) {
                        y.a(e4);
                    }
                }
            }
        }
    }

    private static String c(String str, String str2) {
        BufferedReader bufferedReaderA = ab.a(str, "map_record.txt");
        if (bufferedReaderA == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReaderA.readLine();
            if (line != null && line.startsWith(str2)) {
                while (true) {
                    String line2 = bufferedReaderA.readLine();
                    if (line2 == null) {
                        break;
                    }
                    sb.append("  ");
                    sb.append(line2);
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
                return sb.toString();
            }
            if (bufferedReaderA != null) {
                try {
                    bufferedReaderA.close();
                } catch (Exception e2) {
                    y.a(e2);
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                y.a(th);
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e3) {
                        y.a(e3);
                    }
                }
                return null;
            } finally {
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e4) {
                        y.a(e4);
                    }
                }
            }
        }
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String strB = b(str, str2);
        if (strB != null && !strB.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(strB);
        }
        String strC = c(str, str2);
        if (strC != null && !strC.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
            sb.append("System SO infos:\n");
            sb.append(strC);
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static void c(String str) {
        File[] fileArrListFiles;
        if (str == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.canRead() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                        file2.delete();
                        y.c("Delete empty record file %s", file2.getAbsoluteFile());
                    }
                }
            }
        } catch (Throwable th) {
            y.a(th);
        }
    }

    public static void a(boolean z, String str) {
        if (str != null) {
            f5576a.add(new File(str, "rqd_record.eup"));
            f5576a.add(new File(str, "reg_record.txt"));
            f5576a.add(new File(str, "map_record.txt"));
            f5576a.add(new File(str, "backup_record.txt"));
            if (z) {
                c(str);
            }
        }
        List<File> list = f5576a;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (File file : f5576a) {
            if (file.exists() && file.canWrite()) {
                file.delete();
                y.c("Delete record file %s", file.getAbsoluteFile());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.String] */
    public static String a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader;
        if (str != null && i > 0) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                y.a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
                f5576a.add(file);
                y.c("Add this record file to list for cleaning lastly.", new Object[0]);
                if (str2 == null) {
                    return ab.a(new File(str), i, z);
                }
                String sb = new StringBuilder();
                try {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                if (Pattern.compile(str2 + "[ ]*:").matcher(line).find()) {
                                    sb.append(line);
                                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                                }
                                if (i > 0 && sb.length() > i) {
                                    if (z) {
                                        sb.delete(i, sb.length());
                                        break;
                                    }
                                    sb.delete(0, sb.length() - i);
                                }
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    y.a(th);
                                    sb.append("\n[error:" + th.toString() + "]");
                                    String string = sb.toString();
                                    if (bufferedReader == null) {
                                        return string;
                                    }
                                    bufferedReader.close();
                                    sb = string;
                                } catch (Throwable th2) {
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e2) {
                                            y.a(e2);
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        }
                        String string2 = sb.toString();
                        bufferedReader.close();
                        sb = string2;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = null;
                    }
                    return sb;
                } catch (Exception e3) {
                    y.a(e3);
                    return sb;
                }
            }
        }
        return null;
    }

    private static Map<String, String> a(Map<String, String> map) {
        String str = map.get("key-value");
        if (str == null) {
            return null;
        }
        HashMap map2 = new HashMap();
        for (String str2 : str.split(IOUtils.LINE_SEPARATOR_UNIX)) {
            String[] strArrSplit = str2.split("=");
            if (strArrSplit.length == 2) {
                map2.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        return map2;
    }

    private static long b(Map<String, String> map) {
        String str = map.get("launchTime");
        if (str == null) {
            return -1L;
        }
        y.c("[Native record info] launchTime: %s", str);
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            if (y.a(e2)) {
                return -1L;
            }
            e2.printStackTrace();
            return -1L;
        }
    }
}