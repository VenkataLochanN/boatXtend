package com.ido.life.util;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ido.life.base.BasePresenter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ObjectUtil {
    public static <P extends BasePresenter> P getParameterizedType(Class<?> cls) {
        Class<?> parameterizedType = getParameterizedType(cls, false);
        if (parameterizedType == null) {
            return null;
        }
        try {
            return (P) parameterizedType.newInstance();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Class<?> getParameterizedType(Class<?> cls, boolean z) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (z) {
            try {
                genericSuperclass = cls.getGenericInterfaces()[0];
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        if (type instanceof Class) {
            return (Class) actualTypeArguments[0];
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        return null;
    }

    public static boolean isList(Class cls) {
        if (cls == List.class) {
            return true;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces != null && interfaces.length != 0) {
            for (Class<?> cls2 : interfaces) {
                if (cls2 == List.class) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0012 A[Catch: Exception -> 0x0033, TryCatch #0 {Exception -> 0x0033, blocks: (B:4:0x0008, B:5:0x000e, B:7:0x0012, B:9:0x001e, B:11:0x0023, B:13:0x0027), top: B:19:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Class<?> getParameterizedTypeList(java.lang.Class<?> r3, boolean r4) {
        /*
            java.lang.reflect.Type r0 = r3.getGenericSuperclass()
            r1 = 0
            r2 = 0
            if (r4 == 0) goto Le
            java.lang.reflect.Type[] r3 = r3.getGenericInterfaces()     // Catch: java.lang.Exception -> L33
            r0 = r3[r2]     // Catch: java.lang.Exception -> L33
        Le:
            boolean r3 = r0 instanceof java.lang.reflect.ParameterizedType     // Catch: java.lang.Exception -> L33
            if (r3 == 0) goto L37
            java.lang.reflect.ParameterizedType r0 = (java.lang.reflect.ParameterizedType) r0     // Catch: java.lang.Exception -> L33
            java.lang.reflect.Type[] r3 = r0.getActualTypeArguments()     // Catch: java.lang.Exception -> L33
            r4 = r3[r2]     // Catch: java.lang.Exception -> L33
            boolean r0 = r4 instanceof java.lang.Class     // Catch: java.lang.Exception -> L33
            if (r0 == 0) goto L23
            r3 = r3[r2]     // Catch: java.lang.Exception -> L33
            java.lang.Class r3 = (java.lang.Class) r3     // Catch: java.lang.Exception -> L33
            return r3
        L23:
            boolean r3 = r4 instanceof java.lang.reflect.ParameterizedType     // Catch: java.lang.Exception -> L33
            if (r3 == 0) goto L32
            java.lang.reflect.ParameterizedType r4 = (java.lang.reflect.ParameterizedType) r4     // Catch: java.lang.Exception -> L33
            java.lang.reflect.Type[] r3 = r4.getActualTypeArguments()     // Catch: java.lang.Exception -> L33
            r3 = r3[r2]     // Catch: java.lang.Exception -> L33
            java.lang.Class r3 = (java.lang.Class) r3     // Catch: java.lang.Exception -> L33
            return r3
        L32:
            return r1
        L33:
            r3 = move-exception
            r3.printStackTrace()
        L37:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.ObjectUtil.getParameterizedTypeList(java.lang.Class, boolean):java.lang.Class");
    }

    public static boolean isCollectionEmpty(Collection collection) {
        if (collection == null) {
            return true;
        }
        return collection.isEmpty();
    }

    public static boolean isArrayEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    static void resolveJson(String str) {
        System.out.println("*******************************************");
        System.out.println(str);
        Gson gson = new Gson();
        JsonElement jsonElement = ((JsonObject) gson.fromJson(str, JsonObject.class)).get(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        System.out.println(jsonElement.toString());
        if (jsonElement.isJsonNull()) {
            System.out.println("data is null");
            return;
        }
        if (jsonElement.isJsonPrimitive()) {
        } else if (jsonElement.isJsonArray()) {
        }
    }

    public static <T extends Fragment> T newInstance(Class cls, Bundle bundle) {
        T t;
        try {
            t = (T) cls.newInstance();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            t = null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            t = null;
        }
        if (t != null) {
            t.setArguments(bundle);
        }
        return t;
    }
}