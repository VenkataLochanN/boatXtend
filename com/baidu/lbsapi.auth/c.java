package com.baidu.lbsapi.auth;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<String, String> f1992b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a<String> f1993c = null;

    interface a<Result> {
        void a(Result result);
    }

    protected c(Context context) {
        this.f1991a = context;
    }

    private HashMap<String, String> a(HashMap<String, String> map) {
        HashMap<String, String> map2 = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            }
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        a<String> aVar = this.f1993c;
        if (aVar != null) {
            aVar.a(jSONObject.toString());
        }
    }

    protected void a(HashMap<String, String> map, a<String> aVar) {
        this.f1992b = a(map);
        this.f1993c = aVar;
        new Thread(new d(this)).start();
    }
}