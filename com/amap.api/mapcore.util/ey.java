package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: compiled from: LinkLogManager.java */
/* JADX INFO: loaded from: classes.dex */
public class ey {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Map<String, ez> f801a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f802b = "";

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            b();
            hf.a(er.e()).a(context.getApplicationContext());
        } catch (Throwable unused) {
        }
    }

    private static void b() {
        try {
            f801a.put("overlay", new fb());
            f801a.put("normal", new fa());
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, String str2) {
        a(0, "normal", f802b, str, str2);
    }

    public static void b(String str, String str2) {
        a(1, "normal", f802b, str, str2);
    }

    public static void c(String str, String str2) {
        a(1, "overlay", f802b, str, str2);
    }

    private static void a(int i, String str, String str2, String str3, String str4) {
        ez ezVar;
        try {
            String str5 = str3 + str4;
            if (ex.f795b) {
                a(i, str2, str5);
            }
            if (!ex.f794a || f801a == null || (ezVar = f801a.get(str)) == null) {
                return;
            }
            ezVar.a(i, str2, str5);
        } catch (Throwable unused) {
        }
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            boolean zA = gj.a(jSONObject.optString("able", ""), false);
            boolean zA2 = gj.a(jSONObject.optString("mobile", ""), false);
            boolean zA3 = gj.a(jSONObject.optString("debugupload", ""), false);
            boolean zA4 = gj.a(jSONObject.optString("debugwrite", ""), false);
            boolean zA5 = gj.a(jSONObject.optString("forcedUpload", ""), false);
            ex.f794a = zA;
            boolean zA6 = gj.a(jSONObject.optString("di", ""), false);
            String strOptString = jSONObject.optString("dis", "");
            if (!zA6 || gt.f(strOptString)) {
                hf.a(er.e()).a(zA, zA2, zA4, zA3, Arrays.asList(jSONObject.optString("filter", "").split("&")));
                if (zA5) {
                    hf.a(er.e()).a(zA5);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a() {
        try {
            if (ex.f794a) {
                Iterator<Map.Entry<String, ez>> it = f801a.entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().a();
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(int i, String str, String str2) {
        if (i == 0) {
            Log.i("linklog", str + " " + str2);
            return;
        }
        Log.e("linklog", str + " " + str2);
    }

    public static void a(String str, String str2, MarkerOptions markerOptions) {
        if (markerOptions != null) {
            c(str, str2 + " " + markerOptions.getPosition() + " " + markerOptions.getIcons());
            return;
        }
        c(str, str2);
    }

    public static void a(String str, String str2, List<MarkerOptions> list) {
        if (list != null) {
            Iterator<MarkerOptions> it = list.iterator();
            while (it.hasNext()) {
                a(str, str2, it.next());
            }
        }
    }

    public static void a(String str, String str2, PolylineOptions polylineOptions) {
        if (polylineOptions != null) {
            StringBuilder sb = new StringBuilder();
            List<LatLng> points = polylineOptions.getPoints();
            if (points != null) {
                sb.append("points size =");
                sb.append(points.size());
            }
            sb.append(";width=");
            sb.append(polylineOptions.getWidth());
            sb.append(";color=");
            sb.append(polylineOptions.getColor());
            sb.append(";visible=");
            sb.append(polylineOptions.isVisible());
            c(str, str2 + " " + sb.toString());
            return;
        }
        c(str, str2);
    }

    public static void a(String str, String str2, PolygonOptions polygonOptions) {
        if (polygonOptions != null) {
            StringBuilder sb = new StringBuilder();
            List<LatLng> points = polygonOptions.getPoints();
            if (points != null) {
                sb.append("points size =");
                sb.append(points.size());
            }
            sb.append(";width=");
            sb.append(polygonOptions.getStrokeWidth());
            sb.append(";fillColor=");
            sb.append(polygonOptions.getFillColor());
            sb.append(";strokeColor=");
            sb.append(polygonOptions.getStrokeColor());
            sb.append(";visible=");
            sb.append(polygonOptions.isVisible());
            c(str, str2 + " " + sb.toString());
            return;
        }
        c(str, str2);
    }
}