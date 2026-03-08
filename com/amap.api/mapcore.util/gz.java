package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.gm;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NetReuestParam.java */
/* JADX INFO: loaded from: classes.dex */
public class gz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f1176a = gt.c("SRFZHZUVZT3BOa0ZiemZRQQ");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f1177b = gt.c("FbGJzX3Nkaw");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f1178c = gt.c("SWjJuYVh2eEMwSzVmNklFSmh0UXpVb2xtOVM4eU9Ua3E");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f1179d = gt.c("FQU5EU0RLMTA");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f1180e = gt.c("FMTAw");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f1181f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f1182g = "";

    public static gm.a a() {
        return new gm.a() { // from class: com.amap.api.mapcore.util.gz.1

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private gz f1183a = new gz();

            @Override // com.amap.api.mapcore.util.gm.a
            public iq a(byte[] bArr, Map<String, String> map) {
                return new ii(bArr, map);
            }

            @Override // com.amap.api.mapcore.util.gm.a
            public String a() {
                return this.f1183a.c();
            }

            @Override // com.amap.api.mapcore.util.gm.a
            public String a(Context context, String str) {
                return this.f1183a.a(context, str);
            }

            @Override // com.amap.api.mapcore.util.gm.a
            public Map<String, String> b() {
                return this.f1183a.b();
            }

            @Override // com.amap.api.mapcore.util.gm.a
            public String a(String str, String str2, String str3, String str4) {
                return this.f1183a.a(str, str2, str3, str4);
            }
        };
    }

    public String a(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(gt.c("UY29kZQ")) != 1) {
                return "";
            }
            String strOptString = new JSONObject(jSONObject.optString(gt.c("FZGF0YQ"))).optString(gt.c("FYWRpdQ"));
            if (TextUtils.isEmpty(strOptString)) {
                return "";
            }
            ha.a(strOptString);
            gv.a(context).a(strOptString);
            return strOptString;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public synchronized Map<String, String> b() {
        if (f1181f) {
            return null;
        }
        f1181f = true;
        HashMap map = new HashMap();
        map.put(gt.c("FZW50"), gt.c("FMg"));
        StringBuilder sb = new StringBuilder();
        sb.append(gt.c("SY2hhbm5lbD0"));
        sb.append(f1177b);
        sb.append(gt.c("SJmRpdj0"));
        sb.append(f1179d);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f1177b);
        stringBuffer.append(f1179d);
        stringBuffer.append(gt.c("FQA"));
        stringBuffer.append(f1178c);
        String strA = hc.a(stringBuffer.toString());
        sb.append(gt.c("FJnNpZ249"));
        sb.append(strA.toUpperCase(Locale.US));
        sb.append(gt.c("SJm91dHB1dD1qc29u") + "\u0000");
        map.put(gt.c("FaW4"), gx.a(hd.a(sb.toString().getBytes(), f1176a.getBytes())));
        map.put(gt.c("Sa2V5dA"), f1180e);
        return map;
    }

    private String d() {
        if (!TextUtils.isEmpty(this.f1182g)) {
            return this.f1182g;
        }
        String strA = gn.a("TUpJaVFGNk5LXHtSX1ZwQlRiV1VVZmtYWU1haV1hYWHCiXJtZcKLdmp8wpFewo1/wphwwoFzZmR8aWp6X2k6XsKDwoF+WGbChGdAScKLwoVXfmNxYEvCjcKLSG7CjGNvwoZtVFZ7WMKXYMKfwo5dZcKHfzZXUG85X0hNOVJrb2U8ZlJGW8KCe8KOV8KQWllrcGrCjcKIT25lUHPCicKGVsKKeG5fwp56XsKbc8KJbUVYR0pqU09gfE5/WT5YeHNAwoDCh1Z4V8KQT3JQYmxQbcKYwpFxdG/Ci3rCmMKQwop+YVbCmWFxwpxBdW07Zjp/ODlAbcKEY1pQwoJowohbV1VmV1laWmtcYGbClXfCk2NvesKdwohdWFnCol/CjWTCmMKicG1ENnAvPFtpcXtfclhfXsKAwolgRWNbS29OwpFafV3CkMKLTcKCwolrU3DCmGnCmX9wdsKPcXDCg3LCnFpGcDVTeTxNWW07bXJePVRfQn3ChGNraFhbwpNcwpXChMKNaFVjeVF8wojChm9YbmvChGDCmHvChGVQWjo0Z3o9djleOztWcVxSfWE9woLChkZdcGTCgVzCjMKUVE12wpV5bcKVwprCnntZworCgsKfwpHCksKnwpHClURURW9YaDtwXU1bck5YX3hSVFZUYlxKWFlua1xeYm9jU8KDa3ZrwpZ5am9Za3jCknR3fA");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strA.length(); i++) {
            stringBuffer.append((char) (strA.charAt(i) - (i % 48)));
        }
        String string = stringBuffer.toString();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int i2 = 0; i2 < string.length() / 2; i2++) {
            stringBuffer2.append((char) ((string.charAt(i2) + string.charAt((string.length() - 1) - i2)) / 2));
        }
        this.f1182g = stringBuffer2.toString();
        return this.f1182g;
    }

    public String a(String str, String str2, String str3, String str4) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(gt.c("LdGlk"), str);
            jSONObject.put(gt.c("FZGl1"), str2);
            jSONObject.put(gt.c("AZGl1Mg"), str3);
            jSONObject.put(gt.c("EZGl1Mw"), str4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        String string = jSONObject.toString();
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String strA = hc.a();
        if (!TextUtils.isEmpty(strA)) {
            String strA2 = gx.a(hd.a((string + "\u0000").getBytes(), strA.getBytes()));
            if (!TextUtils.isEmpty(strA2)) {
                try {
                    return gt.c("Fa2V5PQ") + URLEncoder.encode(gx.a(hb.a(strA.getBytes("utf-8"), hb.a(d())))) + gt.c("SJmRhdGE9") + URLEncoder.encode(strA2);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
        return null;
    }

    public String c() {
        return ha.a();
    }
}