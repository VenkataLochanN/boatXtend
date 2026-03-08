package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.gj;
import com.amap.api.maps.AMapException;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.Hashtable;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: OfflineInitHandlerAbstract.java */
/* JADX INFO: loaded from: classes.dex */
public class bc extends by<String, bb> {
    public bc(Context context, String str) {
        super(context, str);
    }

    @Override // com.amap.api.mapcore.util.by
    protected JSONObject a(gj.b bVar) {
        if (bVar == null || bVar.f1070f == null) {
            return null;
        }
        return bVar.f1070f.optJSONObject("016");
    }

    @Override // com.amap.api.mapcore.util.by
    protected String a() {
        return "016";
    }

    @Override // com.amap.api.mapcore.util.by
    protected Map<String, String> b() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("mapver", this.f314a);
        return hashtable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.mapcore.util.by
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public bb b(JSONObject jSONObject) throws AMapException {
        bb bbVar = new bb();
        try {
            String strOptString = jSONObject.optString("update", "");
            if (strOptString.equals(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE)) {
                bbVar.a(false);
            } else if (strOptString.equals("1")) {
                bbVar.a(true);
            }
            bbVar.a(jSONObject.optString("version", ""));
        } catch (Throwable th) {
            hn.c(th, "OfflineInitHandlerAbstract", "loadData parseJson");
        }
        return bbVar;
    }
}