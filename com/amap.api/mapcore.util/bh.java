package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.gj;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: OfflineUpdateCityHandlerAbstract.java */
/* JADX INFO: loaded from: classes.dex */
public class bh extends by<String, List<OfflineMapProvince>> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f267d;

    public bh(Context context, String str) {
        super(context, str);
    }

    public void a(Context context) {
        this.f267d = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.mapcore.util.by
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<OfflineMapProvince> b(JSONObject jSONObject) throws AMapException {
        try {
            if (this.f267d != null) {
                bx.c(jSONObject.toString(), this.f267d);
            }
        } catch (Throwable th) {
            hn.c(th, "OfflineUpdateCityHandlerAbstract", "loadData jsonInit");
            th.printStackTrace();
        }
        try {
            if (this.f267d != null) {
                return bx.a(jSONObject, this.f267d);
            }
            return null;
        } catch (JSONException e2) {
            hn.c(e2, "OfflineUpdateCityHandlerAbstract", "loadData parseJson");
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.mapcore.util.by
    protected JSONObject a(gj.b bVar) {
        if (bVar == null || bVar.f1070f == null) {
            return null;
        }
        JSONObject jSONObjectOptJSONObject = bVar.f1070f.optJSONObject("015");
        if (!jSONObjectOptJSONObject.has("result")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", new JSONObject().put("offlinemap_with_province_vfour", jSONObjectOptJSONObject));
                return jSONObject;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObjectOptJSONObject;
    }

    @Override // com.amap.api.mapcore.util.by
    protected String a() {
        return "015";
    }

    @Override // com.amap.api.mapcore.util.by
    protected Map<String, String> b() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("mapver", this.f314a);
        return hashtable;
    }
}