package com.amazon.identity.auth.device.utils;

import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class JSONUtils {
    private static final String LOG_TAG = JSONUtils.class.getName();

    public static String[] getStringArray(JSONObject jSONObject, String str) {
        List<String> stringList = getStringList(jSONObject, str);
        if (stringList == null) {
            return null;
        }
        return (String[]) stringList.toArray(new String[stringList.size()]);
    }

    public static List<String> getStringList(JSONObject jSONObject, String str) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            try {
                arrayList.add(jSONArrayOptJSONArray.getString(i));
            } catch (JSONException unused) {
                MAPLog.i(LOG_TAG, str + " has no mapping in json, returning null array");
                return null;
            }
        }
        return arrayList;
    }
}