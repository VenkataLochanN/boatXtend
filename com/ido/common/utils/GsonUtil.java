package com.ido.common.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ido.common.log.CommonLogUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GsonUtil {
    public static <T> T fromJsonType(String str, final Class<T> cls, final Class cls2) {
        Gson gson = new Gson();
        final ParameterizedType parameterizedType = new ParameterizedType() { // from class: com.ido.common.utils.GsonUtil.1
            @Override // java.lang.reflect.ParameterizedType
            public Type getOwnerType() {
                return null;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getRawType() {
                return List.class;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type[] getActualTypeArguments() {
                return new Type[]{cls2};
            }
        };
        return (T) gson.fromJson(str, new ParameterizedType() { // from class: com.ido.common.utils.GsonUtil.2
            @Override // java.lang.reflect.ParameterizedType
            public Type getOwnerType() {
                return null;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getRawType() {
                return cls;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type[] getActualTypeArguments() {
                return new Type[]{parameterizedType};
            }
        });
    }

    public static <T> List<T> analysisJsonArrayToList(String str, Class<T[]> cls) {
        Object[] objArr;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            objArr = (Object[]) new Gson().fromJson(str, (Class) cls);
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        if (objArr == null) {
            return null;
        }
        return Arrays.asList(objArr);
    }

    public static <T> List<T> analysisJsonObjectToList(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Gson gson = new Gson();
        ArrayList arrayList = (ArrayList) gson.fromJson(str, new TypeToken<ArrayList<JsonObject>>() { // from class: com.ido.common.utils.GsonUtil.3
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

    public static String toJson(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <K, V> Map<K, V> fromJson2Map(String str) {
        try {
            return (Map) new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() { // from class: com.ido.common.utils.GsonUtil.4
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static <K, V> Map fromJsonToMap(String str) {
        try {
            return (Map) new Gson().fromJson(str, new TypeToken<Map<K, V>>() { // from class: com.ido.common.utils.GsonUtil.5
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJsonToCollect(String str) {
        try {
            return (T) new Gson().fromJson(str, new TypeToken<T>() { // from class: com.ido.common.utils.GsonUtil.6
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        try {
            return (T) new Gson().fromJson(str, (Class) cls);
        } catch (Exception e2) {
            CommonLogUtil.d(str);
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String str, Class<T> cls, Class<?> cls2) {
        try {
            return (T) new Gson().fromJson(str, type(cls, cls2));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static ParameterizedType type(final Class cls, final Type... typeArr) {
        return new ParameterizedType() { // from class: com.ido.common.utils.GsonUtil.7
            @Override // java.lang.reflect.ParameterizedType
            public Type getOwnerType() {
                return null;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getRawType() {
                return cls;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type[] getActualTypeArguments() {
                return typeArr;
            }
        };
    }
}