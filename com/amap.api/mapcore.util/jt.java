package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.gs;
import com.github.lzyzsd.library.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SDKSPUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class jt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private gs f1489a;

    public jt(String str) {
        this.f1489a = null;
        try {
            this.f1489a = new gs.a(str, "1.0", BuildConfig.VERSION_NAME).a(new String[]{"info"}).a();
        } catch (gh unused) {
        }
    }

    public final void a(Context context, gs gsVar) {
        if (gsVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(gsVar);
        String string = a(arrayList).toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        hz.a(context, this.f1489a, "rbck", string);
    }

    public final List<gs> a(Context context) {
        try {
            return a(new JSONArray(hz.a(context, this.f1489a, "rbck")));
        } catch (JSONException unused) {
            return new ArrayList();
        }
    }

    private JSONArray a(List<gs> list) {
        if (list.size() == 0) {
            return new JSONArray();
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<gs> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(a(it.next()));
        }
        return jSONArray;
    }

    private static JSONObject a(gs gsVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", gsVar.a());
            jSONObject.put("b", gsVar.b());
            jSONObject.put("c", gsVar.c());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; gsVar.g() != null && i < gsVar.g().length; i++) {
                jSONArray.put(gsVar.g()[i]);
            }
            jSONObject.put("d", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private static gs a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            String strOptString = jSONObject.optString("a");
            String strOptString2 = jSONObject.optString("b");
            String strOptString3 = jSONObject.optString("c");
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("d");
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.getString(i));
            }
            return new gs.a(strOptString, strOptString2, strOptString).a(strOptString3).a((String[]) arrayList.toArray(new String[0])).a();
        } catch (Throwable unused) {
            return null;
        }
    }

    private List<gs> a(JSONArray jSONArray) {
        if (jSONArray.length() == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            gs gsVarA = null;
            try {
                gsVarA = a(jSONArray.getJSONObject(i));
            } catch (JSONException unused) {
            }
            if (gsVarA != null) {
                arrayList.add(gsVarA);
            }
        }
        return arrayList;
    }
}