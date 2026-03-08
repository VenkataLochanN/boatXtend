package com.ido.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectUtil {
    public static Object invokeMethod(Object obj, String str, Class[] clsArr, Object... objArr) {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Object invokeMethod(Object obj, String str, Object... objArr) {
        try {
            for (Method method : obj.getClass().getDeclaredMethods()) {
                if (method.getName().equals(str)) {
                    method.setAccessible(true);
                    return method.invoke(obj, objArr);
                }
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Field getField(Class<?> cls, String str) {
        Field declaredField;
        try {
            declaredField = cls.getDeclaredField(str);
        } catch (NoSuchFieldException e2) {
            e = e2;
            declaredField = null;
        }
        try {
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e3) {
            e = e3;
            e.printStackTrace();
        }
        return declaredField;
    }

    public static void setField(Object obj, String str, Object obj2) {
        Field field = getField(obj.getClass(), str);
        if (field != null) {
            try {
                field.set(obj, obj2);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
    }
}