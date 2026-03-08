package com.baidu.lbsapi.auth;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<HashMap<String, String>> f1996b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a<String> f1997c = null;

    interface a<Result> {
        void a(Result result);
    }

    protected e(Context context) {
        this.f1995a = context;
    }

    private List<HashMap<String, String>> a(HashMap<String, String> map, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr == null || strArr.length <= 0) {
            HashMap map2 = new HashMap();
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String string = it.next().toString();
                map2.put(string, map.get(string));
            }
            arrayList.add(map2);
        } else {
            for (String str : strArr) {
                HashMap map3 = new HashMap();
                Iterator<String> it2 = map.keySet().iterator();
                while (it2.hasNext()) {
                    String string2 = it2.next().toString();
                    map3.put(string2, map.get(string2));
                }
                map3.put("mcode", str);
                arrayList.add(map3);
            }
        }
        return arrayList;
    }

    private void a(String str) {
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
        a<String> aVar = this.f1997c;
        if (aVar != null) {
            aVar.a(jSONObject.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HashMap<String, String>> list) throws Throwable {
        int i;
        com.baidu.lbsapi.auth.a.a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            com.baidu.lbsapi.auth.a.c("syncConnect failed,params list is null or size is 0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            com.baidu.lbsapi.auth.a.a("syncConnect resuest " + i2 + "  start!!!");
            HashMap<String, String> map = list.get(i2);
            g gVar = new g(this.f1995a);
            if (gVar.a()) {
                String strA = gVar.a(map);
                if (strA == null) {
                    strA = "";
                }
                com.baidu.lbsapi.auth.a.a("syncConnect resuest " + i2 + "  result:" + strA);
                arrayList.add(strA);
                try {
                    JSONObject jSONObject = new JSONObject(strA);
                    if (jSONObject.has(NotificationCompat.CATEGORY_STATUS) && jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 0) {
                        com.baidu.lbsapi.auth.a.a("auth end and break");
                        a(strA);
                        return;
                    }
                } catch (JSONException unused) {
                    com.baidu.lbsapi.auth.a.a("continue-------------------------------");
                }
            } else {
                com.baidu.lbsapi.auth.a.a("Current network is not available.");
                arrayList.add(ErrorMessage.a("Current network is not available."));
            }
            com.baidu.lbsapi.auth.a.a("syncConnect end");
            i2++;
        }
        com.baidu.lbsapi.auth.a.a("--iiiiii:" + i2 + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
        if (list.size() <= 0 || i2 != list.size() || arrayList.size() <= 0 || i2 != arrayList.size() || i2 - 1 <= 0) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject((String) arrayList.get(i));
            if (!jSONObject2.has(NotificationCompat.CATEGORY_STATUS) || jSONObject2.getInt(NotificationCompat.CATEGORY_STATUS) == 0) {
                return;
            }
            com.baidu.lbsapi.auth.a.a("i-1 result is not 0,return first result");
            a((String) arrayList.get(0));
        } catch (JSONException e2) {
            a(ErrorMessage.a("JSONException:" + e2.getMessage()));
        }
    }

    protected void a(HashMap<String, String> map, String[] strArr, a<String> aVar) {
        this.f1996b = a(map, strArr);
        this.f1997c = aVar;
        new Thread(new f(this)).start();
    }
}