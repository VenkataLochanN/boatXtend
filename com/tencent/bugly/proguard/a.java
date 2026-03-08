package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Proxy f5577e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected HashMap<String, HashMap<String, byte[]>> f5578a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected String f5579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    h f5580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<String, Object> f5581d;

    a() {
        new HashMap();
        this.f5581d = new HashMap<>();
        this.f5579b = "GBK";
        this.f5580c = new h();
    }

    public static void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            f5577e = null;
        } else {
            f5577e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    public static boolean a(ActivityManager activityManager) {
        boolean z;
        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                y.c("running proc info list is empty, my proc not running.", new Object[0]);
            } else {
                int iMyPid = Process.myPid();
                Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                while (it.hasNext()) {
                    if (it.next().pid == iMyPid) {
                        y.c("my proc is running.", new Object[0]);
                    }
                }
                y.c("proc not in running proc info list, my proc not running.", new Object[0]);
            }
            z = false;
            return z && a(activityManager, 0L) != null;
        }
        y.c("is proc running, ActivityManager is null", new Object[0]);
        z = true;
        if (z) {
            return false;
        }
    }

    public static void a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            f5577e = null;
        } else {
            f5577e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    public void a(String str) {
        this.f5579b = str;
    }

    public static ar a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        ar arVar = new ar();
        arVar.f5660a = userInfoBean.f5383e;
        arVar.f5664e = userInfoBean.j;
        arVar.f5663d = userInfoBean.f5381c;
        arVar.f5662c = userInfoBean.f5382d;
        arVar.f5666g = userInfoBean.o == 1;
        int i = userInfoBean.f5380b;
        if (i == 1) {
            arVar.f5661b = (byte) 1;
        } else if (i == 2) {
            arVar.f5661b = (byte) 4;
        } else if (i == 3) {
            arVar.f5661b = (byte) 2;
        } else if (i == 4) {
            arVar.f5661b = (byte) 3;
        } else if (i == 8) {
            arVar.f5661b = (byte) 8;
        } else {
            if (userInfoBean.f5380b < 10 || userInfoBean.f5380b >= 20) {
                y.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f5380b));
                return null;
            }
            arVar.f5661b = (byte) userInfoBean.f5380b;
        }
        arVar.f5665f = new HashMap();
        if (userInfoBean.p >= 0) {
            Map<String, String> map = arVar.f5665f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.q >= 0) {
            Map<String, String> map2 = arVar.f5665f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Map.Entry<String, String> entry : userInfoBean.r.entrySet()) {
                arVar.f5665f.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Map.Entry<String, String> entry2 : userInfoBean.s.entrySet()) {
                arVar.f5665f.put("C04_" + entry2.getKey(), entry2.getValue());
            }
        }
        Map<String, String> map3 = arVar.f5665f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.l);
        map3.put("A36", sb3.toString());
        Map<String, String> map4 = arVar.f5665f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.f5385g);
        map4.put("F02", sb4.toString());
        Map<String, String> map5 = arVar.f5665f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.f5386h);
        map5.put("F03", sb5.toString());
        arVar.f5665f.put("F04", userInfoBean.j);
        Map<String, String> map6 = arVar.f5665f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.i);
        map6.put("F05", sb6.toString());
        arVar.f5665f.put("F06", userInfoBean.m);
        Map<String, String> map7 = arVar.f5665f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.k);
        map7.put("F10", sb7.toString());
        y.c("summary type %d vm:%d", Byte.valueOf(arVar.f5661b), Integer.valueOf(arVar.f5665f.size()));
        return arVar;
    }

    public static Proxy b() {
        return f5577e;
    }

    public static ActivityManager.ProcessErrorStateInfo a(ActivityManager activityManager, long j) {
        ActivityManager.ProcessErrorStateInfo next;
        if (activityManager == null) {
            y.c("get anr state, ActivityManager is null", new Object[0]);
            return null;
        }
        y.c("get anr state, timeout:%d", Long.valueOf(j));
        long j2 = j / 500;
        int i = 0;
        while (true) {
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState == null || processesInErrorState.isEmpty()) {
                y.c("error state info list is null", new Object[0]);
            } else {
                int iMyPid = Process.myPid();
                Iterator<ActivityManager.ProcessErrorStateInfo> it = processesInErrorState.iterator();
                while (it.hasNext()) {
                    next = it.next();
                    if (next.pid == iMyPid) {
                        y.c("found current proc in the error state", new Object[0]);
                        break;
                    }
                }
                y.c("current proc not in the error state", new Object[0]);
            }
            next = null;
            if (next == null) {
                y.c("found proc state is null", new Object[0]);
            } else {
                if (next.condition == 2) {
                    y.c("found proc state is anr! proc:%s", next.processName);
                    return next;
                }
                if (next.condition == 1) {
                    y.c("found proc state is crashed!", new Object[0]);
                    return null;
                }
            }
            int i2 = i + 1;
            if (i < j2) {
                y.c("try the %s times:", Integer.valueOf(i2));
                ab.b(500L);
                i = i2;
            } else {
                ActivityManager.ProcessErrorStateInfo processErrorStateInfo = new ActivityManager.ProcessErrorStateInfo();
                processErrorStateInfo.pid = Process.myPid();
                processErrorStateInfo.processName = AppInfo.a(Process.myPid());
                processErrorStateInfo.shortMsg = "Find process anr, but unable to get anr message.";
                return processErrorStateInfo;
            }
        }
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            String str = "map";
            if (i < arrayList.size()) {
                String str2 = arrayList.get(i);
                if (str2.equals("java.lang.Integer") || str2.equals("int")) {
                    str = "int32";
                } else if (str2.equals("java.lang.Boolean") || str2.equals("boolean")) {
                    str = "bool";
                } else if (str2.equals("java.lang.Byte") || str2.equals("byte")) {
                    str = "char";
                } else if (str2.equals("java.lang.Double") || str2.equals("double")) {
                    str = "double";
                } else if (str2.equals("java.lang.Float") || str2.equals("float")) {
                    str = "float";
                } else if (str2.equals("java.lang.Long") || str2.equals("long")) {
                    str = "int64";
                } else if (str2.equals("java.lang.Short") || str2.equals("short")) {
                    str = "short";
                } else {
                    if (str2.equals("java.lang.Character")) {
                        throw new IllegalArgumentException("can not support java.lang.Character");
                    }
                    if (str2.equals("java.lang.String")) {
                        str = "string";
                    } else if (str2.equals("java.util.List")) {
                        str = "list";
                    } else if (!str2.equals("java.util.Map")) {
                        str = str2;
                    }
                }
                arrayList.set(i, str);
                i++;
            } else {
                Collections.reverse(arrayList);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String str3 = arrayList.get(i2);
                    if (str3.equals("list")) {
                        int i3 = i2 - 1;
                        arrayList.set(i3, "<" + arrayList.get(i3));
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals("map")) {
                        int i4 = i2 - 1;
                        arrayList.set(i4, "<" + arrayList.get(i4) + com.amazon.identity.auth.device.dataobject.AppInfo.DELIM);
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals("Array")) {
                        int i5 = i2 - 1;
                        arrayList.set(i5, "<" + arrayList.get(i5));
                        arrayList.set(0, arrayList.get(0) + ">");
                    }
                }
                Collections.reverse(arrayList);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(it.next());
                }
                return stringBuffer.toString();
            }
        }
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        i iVar = new i();
        iVar.a(this.f5579b);
        iVar.a(t, 0);
        byte[] bArrA = k.a(iVar.a());
        HashMap<String, byte[]> map = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        a(arrayList, t);
        map.put(a(arrayList), bArrA);
        this.f5581d.remove(str);
        this.f5578a.put(str, map);
    }

    public static as a(List<UserInfoBean> list, int i) {
        com.tencent.bugly.crashreport.common.info.a aVarB;
        if (list == null || list.size() == 0 || (aVarB = com.tencent.bugly.crashreport.common.info.a.b()) == null) {
            return null;
        }
        aVarB.s();
        as asVar = new as();
        asVar.f5671b = aVarB.f5417d;
        asVar.f5672c = aVarB.k();
        ArrayList<ar> arrayList = new ArrayList<>();
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            ar arVarA = a(it.next());
            if (arVarA != null) {
                arrayList.add(arVarA);
            }
        }
        asVar.f5673d = arrayList;
        asVar.f5674e = new HashMap();
        Map<String, String> map = asVar.f5674e;
        StringBuilder sb = new StringBuilder();
        aVarB.getClass();
        map.put("A7", sb.toString());
        asVar.f5674e.put("A6", com.tencent.bugly.crashreport.common.info.a.r());
        asVar.f5674e.put("A5", aVarB.q());
        Map<String, String> map2 = asVar.f5674e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(aVarB.o());
        map2.put("A2", sb2.toString());
        Map<String, String> map3 = asVar.f5674e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(aVarB.o());
        map3.put("A1", sb3.toString());
        asVar.f5674e.put("A24", aVarB.f5420g);
        Map<String, String> map4 = asVar.f5674e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(aVarB.p());
        map4.put("A17", sb4.toString());
        asVar.f5674e.put("A15", aVarB.u());
        Map<String, String> map5 = asVar.f5674e;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(aVarB.v());
        map5.put("A13", sb5.toString());
        asVar.f5674e.put("F08", aVarB.u);
        asVar.f5674e.put("F09", aVarB.v);
        Map<String, String> mapC = aVarB.C();
        if (mapC != null && mapC.size() > 0) {
            for (Map.Entry<String, String> entry : mapC.entrySet()) {
                asVar.f5674e.put("C04_" + entry.getKey(), entry.getValue());
            }
        }
        if (i == 1) {
            asVar.f5670a = (byte) 1;
        } else {
            if (i != 2) {
                y.e("unknown up type %d ", Integer.valueOf(i));
                return null;
            }
            asVar.f5670a = (byte) 2;
        }
        return asVar;
    }

    public static <T extends j> T a(byte[] bArr, Class<T> cls) {
        if (bArr != null && bArr.length > 0) {
            try {
                T tNewInstance = cls.newInstance();
                h hVar = new h(bArr);
                hVar.a("utf-8");
                tNewInstance.a(hVar);
                return tNewInstance;
            } catch (Throwable th) {
                if (!y.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static an a(Context context, int i, byte[] bArr) {
        com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
        StrategyBean strategyBeanC = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (aVarB == null || strategyBeanC == null) {
            y.e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            an anVar = new an();
            synchronized (aVarB) {
                anVar.f5634a = 1;
                anVar.f5635b = aVarB.f();
                anVar.f5636c = aVarB.f5416c;
                anVar.f5637d = aVarB.i;
                anVar.f5638e = aVarB.k;
                anVar.f5639f = aVarB.f5419f;
                anVar.f5640g = i;
                if (bArr == null) {
                    bArr = "".getBytes();
                }
                anVar.f5641h = bArr;
                anVar.i = aVarB.l();
                anVar.j = aVarB.f5420g;
                anVar.k = new HashMap();
                anVar.l = aVarB.e();
                anVar.m = strategyBeanC.n;
                anVar.o = aVarB.k();
                anVar.p = com.tencent.bugly.crashreport.common.info.b.b(context);
                anVar.q = System.currentTimeMillis();
                anVar.r = aVarB.m();
                anVar.s = aVarB.k();
                anVar.t = anVar.p;
                aVarB.getClass();
                anVar.n = "com.tencent.bugly";
                anVar.k.put("A26", aVarB.w());
                Map<String, String> map = anVar.k;
                StringBuilder sb = new StringBuilder();
                sb.append(com.tencent.bugly.crashreport.common.info.a.H());
                map.put("A62", sb.toString());
                Map<String, String> map2 = anVar.k;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(com.tencent.bugly.crashreport.common.info.a.I());
                map2.put("A63", sb2.toString());
                Map<String, String> map3 = anVar.k;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(aVarB.z);
                map3.put("F11", sb3.toString());
                Map<String, String> map4 = anVar.k;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(aVarB.y);
                map4.put("F12", sb4.toString());
                anVar.k.put("D3", aVarB.j);
                if (com.tencent.bugly.b.f5372b != null) {
                    for (com.tencent.bugly.a aVar : com.tencent.bugly.b.f5372b) {
                        if (aVar.versionKey != null && aVar.version != null) {
                            anVar.k.put(aVar.versionKey, aVar.version);
                        }
                    }
                }
                anVar.k.put("G15", ab.c("G15", ""));
                anVar.k.put("G10", ab.c("G10", ""));
                anVar.k.put("D4", ab.c("D4", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE));
            }
            Map<String, String> mapB = aVarB.B();
            if (mapB != null) {
                for (Map.Entry<String, String> entry : mapB.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getValue())) {
                        anVar.k.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            return anVar;
        } catch (Throwable th) {
            if (!y.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
                return;
            } else {
                arrayList.add("Array");
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        }
        if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
                return;
            } else {
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
            return;
        }
        arrayList.add(obj.getClass().getName());
    }

    public byte[] a() {
        i iVar = new i(0);
        iVar.a(this.f5579b);
        iVar.a((Map) this.f5578a, 0);
        return k.a(iVar.a());
    }

    public static byte[] a(Object obj) {
        try {
            d dVar = new d();
            dVar.c();
            dVar.a("utf-8");
            dVar.a(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", obj);
            return dVar.a();
        } catch (Throwable th) {
            if (y.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public void a(byte[] bArr) {
        this.f5580c.a(bArr);
        this.f5580c.a(this.f5579b);
        HashMap map = new HashMap(1);
        HashMap map2 = new HashMap(1);
        map2.put("", new byte[0]);
        map.put("", map2);
        this.f5578a = this.f5580c.a((Map) map, 0, false);
    }

    public static ao b(byte[] bArr) {
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.c();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object objB = dVar.b("detail", new ao());
                if (ao.class.isInstance(objB)) {
                    return (ao) ao.class.cast(objB);
                }
                return null;
            } catch (Throwable th) {
                if (!y.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(j jVar) {
        try {
            i iVar = new i();
            iVar.a("utf-8");
            jVar.a(iVar);
            return iVar.b();
        } catch (Throwable th) {
            if (y.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}