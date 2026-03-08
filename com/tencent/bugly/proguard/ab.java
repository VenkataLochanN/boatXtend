package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.bumptech.glide.load.Key;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.UByte;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Map<String, String> f5598a;

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (y.a(th2)) {
                return "fail";
            }
            th2.printStackTrace();
            return "fail";
        }
    }

    public static String a() {
        return a(System.currentTimeMillis());
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static byte[] a(File file, String str, String str2) {
        ZipOutputStream zipOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        if (str == null || str.length() == 0) {
            return null;
        }
        y.c("rqdp{  ZF start}", new Object[0]);
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Key.STRING_CHARSET_NAME));
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        } catch (Throwable th) {
            th = th;
            zipOutputStream = null;
        }
        try {
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = byteArrayInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                zipOutputStream.write(bArr, 0, i);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            zipOutputStream.finish();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                zipOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            y.c("rqdp{  ZF end}", new Object[0]);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                y.c("rqdp{  ZF end}", new Object[0]);
                return null;
            } catch (Throwable th3) {
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                y.c("rqdp{  ZF end}", new Object[0]);
                throw th3;
            }
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        y.c("[Util] Zip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            af afVarA = ae.a(2);
            if (afVarA == null) {
                return null;
            }
            return afVarA.a(bArr);
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        y.c("[Util] Unzip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            af afVarA = ae.a(2);
            if (afVarA == null) {
                return null;
            }
            return afVarA.b(bArr);
        } catch (Throwable th) {
            if (th.getMessage() != null && th.getMessage().contains("Not in GZIP format")) {
                y.d(th.getMessage(), new Object[0]);
            } else if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static long b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / DateUtil.DAY) * DateUtil.DAY) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (y.a(th)) {
                return -1L;
            }
            th.printStackTrace();
            return -1L;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bArr);
            byte[] bArrDigest = messageDigest.digest();
            if (bArrDigest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (y.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static boolean a(File file, File file2, int i) {
        FileInputStream fileInputStream;
        ZipOutputStream zipOutputStream;
        y.c("rqdp{  ZF start}", new Object[0]);
        if (!a(file, file2)) {
            return false;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
            } catch (Throwable th) {
                th = th;
                zipOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            zipOutputStream = null;
        }
        try {
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            byte[] bArr = new byte[Math.max(5000, 1000)];
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 <= 0) {
                    break;
                }
                zipOutputStream.write(bArr, 0, i2);
            }
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                zipOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            y.c("rqdp{  ZF end}", new Object[0]);
            return true;
        } catch (Throwable th3) {
            th = th3;
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                y.c("rqdp{  ZF end}", new Object[0]);
                return false;
            } catch (Throwable th4) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                y.c("rqdp{  ZF end}", new Object[0]);
                throw th4;
            }
        }
    }

    private static boolean a(File file, File file2) {
        if (file == null || file2 == null || file.equals(file2)) {
            y.d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        }
        if (!file.exists() || !file.canRead()) {
            y.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
        try {
            if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
        }
        return file2.exists() && file2.canWrite();
    }

    private static ArrayList<String> a(Context context, String[] strArr) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Process processExec;
        if (AppInfo.e(context)) {
            return new ArrayList<>(Arrays.asList("unknown(low memory)"));
        }
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            processExec = Runtime.getRuntime().exec(strArr);
            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
            bufferedReader2 = null;
        }
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                arrayList.add(line);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = null;
            }
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return null;
            } finally {
            }
        }
        bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
        while (true) {
            try {
                String line2 = bufferedReader2.readLine();
                if (line2 != null) {
                    arrayList.add(line2);
                } else {
                    try {
                        break;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        bufferedReader.close();
        try {
            bufferedReader2.close();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        return arrayList;
    }

    public static String a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (f5598a == null) {
            f5598a = new HashMap();
            ArrayList<String> arrayListA = a(context, new String[]{(new File("/system/bin/sh").exists() && new File("/system/bin/sh").canExecute()) ? "/system/bin/sh" : "sh", "-c", "getprop"});
            if (arrayListA != null && arrayListA.size() > 0) {
                y.b(ab.class, "Successfully get 'getprop' list.", new Object[0]);
                Pattern patternCompile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                Iterator<String> it = arrayListA.iterator();
                while (it.hasNext()) {
                    Matcher matcher = patternCompile.matcher(it.next());
                    if (matcher.find()) {
                        f5598a.put(matcher.group(1), matcher.group(2));
                    }
                }
                y.b(ab.class, "Systems properties number: %d.", Integer.valueOf(f5598a.size()));
            }
        }
        return f5598a.containsKey(str) ? f5598a.get(str) : "fail";
    }

    public static void b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static void b(String str) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.isFile() && file.exists() && file.canWrite()) {
            file.delete();
        }
    }

    public static byte[] c(long j) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            return sb.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static long b(byte[] bArr) {
        if (bArr == null) {
            return -1L;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return -1L;
        }
    }

    public static Context a(Context context) {
        Context applicationContext;
        return (context == null || (applicationContext = context.getApplicationContext()) == null) ? context : applicationContext;
    }

    public static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (Exception unused) {
        }
    }

    public static Object a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry<String, PlugInBean> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).f5411a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).f5413c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).f5412b);
        }
        parcel.writeBundle(bundle);
    }

    public static Map<String, PlugInBean> a(Parcel parcel) {
        Bundle bundle = parcel.readBundle();
        HashMap map = null;
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iIntValue = ((Integer) bundle.get("pluginNum")).intValue();
        for (int i = 0; i < iIntValue; i++) {
            arrayList.add(bundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < iIntValue; i2++) {
            arrayList2.add(new PlugInBean(bundle.getString("pluginVal" + i2 + "plugInId"), bundle.getString("pluginVal" + i2 + "plugInVersion"), bundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            map = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                map.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
        } else {
            y.e("map plugin parcel error!", new Object[0]);
        }
        return map;
    }

    public static void b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        ArrayList<String> arrayList2 = new ArrayList<>(size);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    public static Map<String, String> b(Parcel parcel) {
        Bundle bundle = parcel.readBundle();
        HashMap map = null;
        if (bundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("values");
        if (stringArrayList != null && stringArrayList2 != null && stringArrayList.size() == stringArrayList2.size()) {
            map = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                map.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
        } else {
            y.e("map parcel error!", new Object[0]);
        }
        return map;
    }

    public static byte[] a(Parcelable parcelable) {
        Parcel parcelObtain = Parcel.obtain();
        parcelable.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    public static <T> T a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        try {
            return creator.createFromParcel(parcelObtain);
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                if (parcelObtain == null) {
                    return null;
                }
                parcelObtain.recycle();
                return null;
            } finally {
                if (parcelObtain != null) {
                    parcelObtain.recycle();
                }
            }
        }
    }

    public static String a(int i, String str) {
        String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        Process processExec = null;
        StringBuilder sb = new StringBuilder();
        try {
            processExec = Runtime.getRuntime().exec(strArr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                if (i > 0 && sb.length() > i) {
                    sb.delete(0, sb.length() - i);
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                sb.append("\n[error:");
                sb.append(th.toString());
                sb.append("]");
                String string = sb.toString();
                if (processExec != null) {
                    try {
                        processExec.getOutputStream().close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        processExec.getInputStream().close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    try {
                        processExec.getErrorStream().close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return string;
            } finally {
                if (processExec != null) {
                    try {
                        processExec.getOutputStream().close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        processExec.getInputStream().close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    try {
                        processExec.getErrorStream().close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
            }
        }
    }

    public static Map<String, String> a(boolean z, int i, boolean z2) {
        if (!z) {
            y.c("get all thread stack not enable", new Object[0]);
            return new HashMap();
        }
        Map<String, String> mapA = a(i, false);
        return mapA == null ? new HashMap() : mapA;
    }

    private static Map<String, String> a(int i, boolean z) {
        HashMap map = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        long id = Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            if (!z || id != entry.getKey().getId()) {
                int i2 = 0;
                sb.setLength(0);
                if (entry.getValue() != null && entry.getValue().length != 0) {
                    StackTraceElement[] value = entry.getValue();
                    int length = value.length;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        StackTraceElement stackTraceElement = value[i2];
                        if (i > 0 && sb.length() >= i) {
                            sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                            break;
                        }
                        sb.append(stackTraceElement.toString());
                        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                        i2++;
                    }
                    map.put(entry.getKey().getName() + "(" + entry.getKey().getId() + ")", sb.toString());
                }
            }
        }
        return map;
    }

    public static String a(Thread thread) {
        if (thread == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.toString();
    }

    public static boolean a(Context context, String str, long j) {
        y.c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                    return false;
                }
                y.c("[Util] Lock file (%s) is expired, unlock it.", str);
                b(context, str);
            }
            if (file.createNewFile()) {
                y.c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            y.c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            y.a(th);
            return false;
        }
    }

    public static boolean b(Context context, String str) {
        y.c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            y.c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            y.a(th);
            return false;
        }
    }

    public static String a(File file, int i, boolean z) {
        BufferedReader bufferedReader;
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
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
                        return null;
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                                y.a(e2);
                            }
                        }
                    }
                }
            }
            String string = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception e3) {
                y.a(e3);
            }
            return string;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    private static BufferedReader a(File file) {
        if (file != null && file.exists() && file.canRead()) {
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } catch (Throwable th) {
                y.a(th);
            }
        }
        return null;
    }

    public static BufferedReader a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (file.exists() && file.canRead()) {
                return a(file);
            }
            return null;
        } catch (NullPointerException e2) {
            y.a(e2);
            return null;
        }
    }

    public static Thread a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            y.e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    public static boolean a(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        x xVarA = x.a();
        if (xVarA != null) {
            return xVarA.a(runnable);
        }
        String[] strArrSplit = runnable.getClass().getName().split("\\.");
        return a(runnable, strArrSplit[strArrSplit.length - 1]) != null;
    }

    public static boolean c(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        if (str.length() > 255) {
            y.a("URL(%s)'s length is larger than 255.", str);
            return false;
        }
        if (str.toLowerCase().startsWith("http")) {
            return true;
        }
        y.a("URL(%s) is not start with \"http\".", str);
        return false;
    }

    public static SharedPreferences a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    public static void b(String str, String str2) {
        if (com.tencent.bugly.crashreport.common.info.a.b() == null || com.tencent.bugly.crashreport.common.info.a.b().E == null) {
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.b().E.edit().putString(str, str2).apply();
    }

    public static String c(String str, String str2) {
        return (com.tencent.bugly.crashreport.common.info.a.b() == null || com.tencent.bugly.crashreport.common.info.a.b().E == null) ? "" : com.tencent.bugly.crashreport.common.info.a.b().E.getString(str, str2);
    }
}