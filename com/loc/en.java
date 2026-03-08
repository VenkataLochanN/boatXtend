package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: ReportUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class en {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SparseArray<Long> f5171a = new SparseArray<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5172b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5173c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String[] f5174d = {"ol", "cl", "gl", "ha", "bs", "ds"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5175e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f5176f = -1;
    private static List<bb> i = new ArrayList();
    private static JSONArray j = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static AMapLocation f5169g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static boolean f5170h = false;

    /* JADX INFO: renamed from: com.loc.en$1, reason: invalid class name */
    /* JADX INFO: compiled from: ReportUtil.java */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5177a = new int[AMapLocationClientOption.AMapLocationMode.values().length];

        static {
            try {
                f5177a[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5177a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5177a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void a(long j2, long j3) {
        try {
            if (f5170h) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("gpsTime:");
            stringBuffer.append(ep.a(j2, "yyyy-MM-dd HH:mm:ss.SSS"));
            stringBuffer.append(AppInfo.DELIM);
            stringBuffer.append("sysTime:");
            stringBuffer.append(ep.a(j3, "yyyy-MM-dd HH:mm:ss.SSS"));
            stringBuffer.append(AppInfo.DELIM);
            long jU = ei.u();
            String strA = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            if (0 != jU) {
                strA = ep.a(jU, "yyyy-MM-dd HH:mm:ss.SSS");
            }
            stringBuffer.append("serverTime:");
            stringBuffer.append(strA);
            a("checkgpstime", stringBuffer.toString());
            if (0 != jU && Math.abs(j2 - jU) < 31536000000L) {
                stringBuffer.append(", correctError");
                a("checkgpstimeerror", stringBuffer.toString());
            }
            stringBuffer.delete(0, stringBuffer.length());
            f5170h = true;
        } catch (Throwable unused) {
        }
    }

    public static synchronized void a(Context context) {
        if (context != null) {
            try {
                if (ei.a()) {
                    if (i != null && i.size() > 0) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(i);
                        bc.b(arrayList, context);
                        i.clear();
                    }
                    f(context);
                }
            } catch (Throwable th) {
                ej.a(th, "ReportUtil", "destroy");
            }
        }
    }

    public static void a(Context context, int i2, int i3, long j2, long j3) {
        if (i2 == -1 || i3 == -1 || context == null) {
            return;
        }
        try {
            if (ei.a()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("param_int_first", i2);
                jSONObject.put("param_int_second", i3);
                jSONObject.put("param_long_first", j2);
                jSONObject.put("param_long_second", j3);
                a(context, "O012", jSONObject);
            }
        } catch (Throwable th) {
            try {
                ej.a(th, "ReportUtil", "applyStatisticsEx");
            } catch (Throwable th2) {
                ej.a(th2, "ReportUtil", "reportServiceAliveTime");
            }
        }
    }

    public static void a(Context context, long j2, boolean z) {
        if (context != null) {
            try {
                if (ei.a()) {
                    int iIntValue = Long.valueOf(j2).intValue();
                    String str = z ? "domestic" : "abroad";
                    if (context != null) {
                        try {
                            if (ei.a()) {
                                JSONObject jSONObject = new JSONObject();
                                if (!TextUtils.isEmpty(str)) {
                                    jSONObject.put("param_string_first", str);
                                }
                                if (!TextUtils.isEmpty(null)) {
                                    jSONObject.put("param_string_second", (Object) null);
                                }
                                if (iIntValue != Integer.MAX_VALUE) {
                                    jSONObject.put("param_int_first", iIntValue);
                                }
                                a(context, "O015", jSONObject);
                            }
                        } catch (Throwable th) {
                            ej.a(th, "ReportUtil", "applyStatisticsEx");
                        }
                    }
                }
            } catch (Throwable th2) {
                ej.a(th2, "ReportUtil", "reportGPSLocUseTime");
            }
        }
    }

    public static synchronized void a(Context context, AMapLocation aMapLocation) {
        int i2;
        try {
            if (ep.a(aMapLocation)) {
                int locationType = aMapLocation.getLocationType();
                int i3 = 0;
                if (locationType == 1) {
                    i2 = i3;
                    i3 = 1;
                } else if (locationType == 2 || locationType == 4) {
                    i2 = 1;
                    i3 = 1;
                } else if (locationType == 8) {
                    i3 = 3;
                    i2 = i3;
                    i3 = 1;
                } else if (locationType != 9) {
                    i2 = 0;
                } else {
                    i2 = 2;
                    i3 = 1;
                }
                if (i3 != 0) {
                    int iC = ei.c();
                    if (iC != 0) {
                        if (i2 == 0) {
                            if (iC == 2) {
                                return;
                            }
                        } else if (iC == 1) {
                            return;
                        }
                    }
                    if (j == null) {
                        j = new JSONArray();
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("lon", ep.b(aMapLocation.getLongitude()));
                    jSONObject.put("lat", ep.b(aMapLocation.getLatitude()));
                    jSONObject.put("type", i2);
                    jSONObject.put("timestamp", ep.a());
                    if (aMapLocation.getCoordType().equalsIgnoreCase(AMapLocation.COORD_TYPE_WGS84)) {
                        jSONObject.put("coordType", 1);
                    } else {
                        jSONObject.put("coordType", 2);
                    }
                    if (i2 == 0) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("accuracy", ep.c(aMapLocation.getAccuracy()));
                        jSONObject2.put("altitude", ep.c(aMapLocation.getAltitude()));
                        jSONObject2.put("bearing", ep.c(aMapLocation.getBearing()));
                        jSONObject2.put("speed", ep.c(aMapLocation.getSpeed()));
                        jSONObject.put("extension", jSONObject2);
                    }
                    JSONArray jSONArrayPut = j.put(jSONObject);
                    j = jSONArrayPut;
                    if (jSONArrayPut.length() >= ei.b()) {
                        f(context);
                    }
                }
            }
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "recordOfflineLocLog");
        }
    }

    public static void a(Context context, AMapLocation aMapLocation, dm dmVar) {
        boolean z;
        if (aMapLocation == null) {
            return;
        }
        try {
            if ("gps".equalsIgnoreCase(aMapLocation.getProvider())) {
                return;
            }
            int i2 = 1;
            if (aMapLocation.getLocationType() == 1) {
                return;
            }
            if (ep.a(aMapLocation)) {
                if (!ej.a(aMapLocation.getLatitude(), aMapLocation.getLongitude())) {
                }
            } else {
                z = "http://abroad.apilocate.amap.com/mobile/binary".equals(ej.f5159a);
            }
            String str = z ? "abroad" : "domestic";
            String str2 = "net";
            if (aMapLocation.getErrorCode() != 0) {
                int errorCode = aMapLocation.getErrorCode();
                if (errorCode == 4 || errorCode == 5 || errorCode == 6 || errorCode == 11) {
                    i2 = 0;
                } else {
                    i2 = 0;
                    str2 = "cache";
                }
            } else {
                int locationType = aMapLocation.getLocationType();
                if (locationType != 5 && locationType != 6) {
                    str2 = "cache";
                }
            }
            int errorCode2 = aMapLocation.getErrorCode();
            if (context != null) {
                try {
                    if (ei.a()) {
                        JSONObject jSONObject = new JSONObject();
                        if (!TextUtils.isEmpty(str2)) {
                            jSONObject.put("param_string_first", str2);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject.put("param_string_second", str);
                        }
                        if (i2 != Integer.MAX_VALUE) {
                            jSONObject.put("param_int_first", i2);
                        }
                        if (errorCode2 != Integer.MAX_VALUE) {
                            jSONObject.put("param_int_second", errorCode2);
                        }
                        if (dmVar != null) {
                            if (!TextUtils.isEmpty(dmVar.d())) {
                                jSONObject.put("dns", dmVar.d());
                            }
                            if (!TextUtils.isEmpty(dmVar.e())) {
                                jSONObject.put("domain", dmVar.e());
                            }
                            if (!TextUtils.isEmpty(dmVar.f())) {
                                jSONObject.put("type", dmVar.f());
                            }
                            if (!TextUtils.isEmpty(dmVar.g())) {
                                jSONObject.put("reason", dmVar.g());
                            }
                            if (!TextUtils.isEmpty(dmVar.c())) {
                                jSONObject.put("ip", dmVar.c());
                            }
                            if (!TextUtils.isEmpty(dmVar.b())) {
                                jSONObject.put("stack", dmVar.b());
                            }
                            if (dmVar.h() > 0) {
                                jSONObject.put("ctime", String.valueOf(dmVar.h()));
                            }
                            if (dmVar.a() > 0) {
                                jSONObject.put("ntime", String.valueOf(dmVar.a()));
                            }
                        }
                        a(context, "O016", jSONObject);
                    }
                } catch (Throwable th) {
                    ej.a(th, "ReportUtil", "applyStatisticsEx");
                }
            }
        } catch (Throwable th2) {
            ej.a(th2, "ReportUtil", "reportBatting");
        }
    }

    public static synchronized void a(Context context, String str, JSONObject jSONObject) {
        if (context != null) {
            try {
                if (ei.a()) {
                    bb bbVar = new bb(context, "loc", "5.2.0", str);
                    if (jSONObject != null) {
                        bbVar.a(jSONObject.toString());
                    }
                    i.add(bbVar);
                    if (i.size() >= 30) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(i);
                        bc.b(arrayList, context);
                        i.clear();
                    }
                }
            } catch (Throwable th) {
                ej.a(th, "ReportUtil", "applyStatistics");
            }
        }
    }

    public static void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        try {
            if (f5169g == null) {
                if (!ep.a(aMapLocation)) {
                    f5169g = aMapLocation2;
                    return;
                }
                f5169g = aMapLocation.m7clone();
            }
            if (ep.a(f5169g) && ep.a(aMapLocation2)) {
                AMapLocation aMapLocationM7clone = aMapLocation2.m7clone();
                if (f5169g.getLocationType() != 1 && f5169g.getLocationType() != 9 && !"gps".equalsIgnoreCase(f5169g.getProvider()) && f5169g.getLocationType() != 7 && aMapLocationM7clone.getLocationType() != 1 && aMapLocationM7clone.getLocationType() != 9 && !"gps".equalsIgnoreCase(aMapLocationM7clone.getProvider()) && aMapLocationM7clone.getLocationType() != 7) {
                    long jAbs = Math.abs(aMapLocationM7clone.getTime() - f5169g.getTime()) / 1000;
                    if (jAbs <= 0) {
                        jAbs = 1;
                    }
                    if (jAbs <= 1800) {
                        float fA = ep.a(f5169g, aMapLocationM7clone);
                        float f2 = fA / jAbs;
                        if (fA > 30000.0f && f2 > 1000.0f) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(f5169g.getLatitude());
                            sb.append(AppInfo.DELIM);
                            sb.append(f5169g.getLongitude());
                            sb.append(AppInfo.DELIM);
                            sb.append(f5169g.getAccuracy());
                            sb.append(AppInfo.DELIM);
                            sb.append(f5169g.getLocationType());
                            sb.append(AppInfo.DELIM);
                            if (aMapLocation.getTime() != 0) {
                                sb.append(ep.a(f5169g.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                sb.append(f5169g.getTime());
                            }
                            sb.append("#");
                            sb.append(aMapLocationM7clone.getLatitude());
                            sb.append(AppInfo.DELIM);
                            sb.append(aMapLocationM7clone.getLongitude());
                            sb.append(AppInfo.DELIM);
                            sb.append(aMapLocationM7clone.getAccuracy());
                            sb.append(AppInfo.DELIM);
                            sb.append(aMapLocationM7clone.getLocationType());
                            sb.append(AppInfo.DELIM);
                            if (aMapLocationM7clone.getTime() != 0) {
                                sb.append(ep.a(aMapLocationM7clone.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                sb.append(aMapLocationM7clone.getTime());
                            }
                            a("bigshiftstatistics", sb.toString());
                            sb.delete(0, sb.length());
                        }
                    }
                }
                f5169g = aMapLocationM7clone;
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, int i2) {
        String str2;
        String strValueOf = String.valueOf(i2);
        if (i2 == 2011) {
            str2 = "ContextIsNull";
        } else if (i2 == 2031) {
            str2 = "CreateApsReqException";
        } else if (i2 == 2041) {
            str2 = "ResponseResultIsNull";
        } else if (i2 == 2081) {
            str2 = "LocalLocException";
        } else if (i2 == 2091) {
            str2 = "InitException";
        } else if (i2 == 2111) {
            str2 = "ErrorCgiInfo";
        } else if (i2 == 2121) {
            str2 = "NotLocPermission";
        } else if (i2 == 2141) {
            str2 = "NoEnoughStatellites";
        } else if (i2 == 2021) {
            str2 = "OnlyMainWifi";
        } else if (i2 == 2022) {
            str2 = "OnlyOneWifiButNotMain";
        } else if (i2 == 2061) {
            str2 = "ServerRetypeError";
        } else if (i2 == 2062) {
            str2 = "ServerLocFail";
        } else if (i2 == 2151) {
            str2 = "MaybeMockNetLoc";
        } else if (i2 != 2152) {
            switch (i2) {
                case 2051:
                    str2 = "NeedLoginNetWork\t";
                    break;
                case 2052:
                    str2 = "MaybeIntercepted";
                    break;
                case 2053:
                    str2 = "DecryptResponseException";
                    break;
                case 2054:
                    str2 = "ParserDataException";
                    break;
                default:
                    switch (i2) {
                        case 2101:
                            str2 = "BindAPSServiceException";
                            break;
                        case 2102:
                            str2 = "AuthClientScodeFail";
                            break;
                        case 2103:
                            str2 = "NotConfigAPSService";
                            break;
                        default:
                            switch (i2) {
                                case 2131:
                                    str2 = "NoCgiOAndWifiInfo";
                                    break;
                                case 2132:
                                    str2 = "AirPlaneModeAndWifiOff";
                                    break;
                                case 2133:
                                    str2 = "NoCgiAndWifiOff";
                                    break;
                                default:
                                    str2 = "";
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            str2 = "MaybeMockGPSLoc";
        }
        a(str, strValueOf, str2);
    }

    public static void a(String str, String str2) {
        try {
            ab.b(ej.c(), str2, str);
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "reportLog");
        }
    }

    public static void a(String str, String str2, String str3) {
        try {
            ab.a(ej.c(), "/mobile/binary", str3, str, str2);
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, Throwable th) {
        try {
            if (th instanceof j) {
                ab.a(ej.c(), str, (j) th);
            }
        } catch (Throwable unused) {
        }
    }

    private static void f(Context context) {
        try {
            if (j == null || j.length() <= 0) {
                return;
            }
            ba.a(new az(context, ej.c(), j.toString()), context);
            j = null;
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "writeOfflineLocLog");
        }
    }

    public final void a(Context context, int i2) {
        try {
            if (this.f5172b == i2) {
                return;
            }
            if (this.f5172b != -1 && this.f5172b != i2) {
                this.f5171a.append(this.f5172b, Long.valueOf((ep.b() - this.f5173c) + this.f5171a.get(this.f5172b, 0L).longValue()));
            }
            this.f5173c = ep.b() - eo.a(context, "pref1", this.f5174d[i2], 0L);
            this.f5172b = i2;
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "setLocationType");
        }
    }

    public final void a(Context context, AMapLocationClientOption aMapLocationClientOption) {
        try {
            int i2 = AnonymousClass1.f5177a[aMapLocationClientOption.getLocationMode().ordinal()];
            int i3 = 3;
            if (i2 == 1) {
                i3 = 4;
            } else if (i2 == 2) {
                i3 = 5;
            } else if (i2 != 3) {
                i3 = -1;
            }
            if (this.f5175e == i3) {
                return;
            }
            if (this.f5175e != -1 && this.f5175e != i3) {
                this.f5171a.append(this.f5175e, Long.valueOf((ep.b() - this.f5176f) + this.f5171a.get(this.f5175e, 0L).longValue()));
            }
            this.f5176f = ep.b() - eo.a(context, "pref1", this.f5174d[i3], 0L);
            this.f5175e = i3;
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "setLocationMode");
        }
    }

    public final void b(Context context) {
        try {
            long jB = ep.b() - this.f5173c;
            if (this.f5172b != -1) {
                this.f5171a.append(this.f5172b, Long.valueOf(jB + this.f5171a.get(this.f5172b, 0L).longValue()));
            }
            long jB2 = ep.b() - this.f5176f;
            if (this.f5175e != -1) {
                this.f5171a.append(this.f5175e, Long.valueOf(jB2 + this.f5171a.get(this.f5175e, 0L).longValue()));
            }
            SharedPreferences.Editor editorA = eo.a(context, "pref1");
            for (int i2 = 0; i2 < this.f5174d.length; i2++) {
                long jLongValue = this.f5171a.get(i2, 0L).longValue();
                if (jLongValue > 0 && jLongValue > eo.a(context, "pref1", this.f5174d[i2], 0L)) {
                    eo.a(editorA, this.f5174d[i2], jLongValue);
                }
            }
            eo.a(editorA);
        } catch (Throwable th) {
            ej.a(th, "ReportUtil", "saveLocationTypeAndMode");
        }
    }

    public final int c(Context context) {
        try {
            long jA = eo.a(context, "pref1", this.f5174d[2], 0L);
            long jA2 = eo.a(context, "pref1", this.f5174d[0], 0L);
            long jA3 = eo.a(context, "pref1", this.f5174d[1], 0L);
            if (jA == 0 && jA2 == 0 && jA3 == 0) {
                return -1;
            }
            long j2 = jA2 - jA;
            long j3 = jA3 - jA;
            return jA > j2 ? jA > j3 ? 2 : 1 : j2 > j3 ? 0 : 1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public final int d(Context context) {
        try {
            long jA = eo.a(context, "pref1", this.f5174d[3], 0L);
            long jA2 = eo.a(context, "pref1", this.f5174d[4], 0L);
            long jA3 = eo.a(context, "pref1", this.f5174d[5], 0L);
            if (jA == 0 && jA2 == 0 && jA3 == 0) {
                return -1;
            }
            return jA > jA2 ? jA > jA3 ? 3 : 5 : jA2 > jA3 ? 4 : 5;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public final void e(Context context) {
        try {
            SharedPreferences.Editor editorA = eo.a(context, "pref1");
            for (int i2 = 0; i2 < this.f5174d.length; i2++) {
                eo.a(editorA, this.f5174d[i2], 0L);
            }
            eo.a(editorA);
        } catch (Throwable unused) {
        }
    }
}