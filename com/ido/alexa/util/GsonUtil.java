package com.ido.alexa.util;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GsonUtil {
    public static String toJson(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> analysisJsonObjectToList(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Gson gson = new Gson();
        ArrayList arrayList = (ArrayList) gson.fromJson(str, new TypeToken<ArrayList<JsonObject>>() { // from class: com.ido.alexa.util.GsonUtil.1
        }.getType());
        if (arrayList == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(gson.fromJson((JsonElement) it.next(), (Class) cls));
        }
        return arrayList2;
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        try {
            return (T) new Gson().fromJson(str, (Class) cls);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}