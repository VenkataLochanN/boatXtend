package com.ido.ble.common;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Gson f4231a = new Gson();

    static class a extends TypeToken<ArrayList<JsonObject>> {
        a() {
        }
    }

    public static String a(Object obj) {
        return f4231a.toJson(obj);
    }

    public static <T> List<T> a(String str, Class<T[]> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Arrays.asList((Object[]) f4231a.fromJson(str, (Class) cls));
    }

    public static <T> List<T> b(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) f4231a.fromJson(str, new a().getType());
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(f4231a.fromJson((JsonElement) it.next(), (Class) cls));
        }
        return arrayList2;
    }

    public static <T> T c(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (T) f4231a.fromJson(str, (Class) cls);
    }
}