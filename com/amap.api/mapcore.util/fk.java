package com.amap.api.mapcore.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: compiled from: MethodCallHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class fk {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f888b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ArrayList<a> f887a = new ArrayList<>();

    public synchronized void a() {
        Class<?> cls;
        if (this.f888b) {
            return;
        }
        this.f888b = true;
        for (int i = 0; i < this.f887a.size(); i++) {
            a aVar = this.f887a.get(i);
            try {
                try {
                    try {
                        if (aVar.f890b != null && (cls = aVar.f890b.getClass()) != null) {
                            Method declaredMethod = null;
                            try {
                                declaredMethod = cls.getDeclaredMethod(aVar.f889a, aVar.f891c);
                            } catch (NoSuchMethodException unused) {
                                if (aVar.f891c.length > 0) {
                                    Class<?>[] clsArr = new Class[aVar.f891c.length];
                                    for (int i2 = 0; i2 < aVar.f891c.length; i2++) {
                                        if (aVar.f891c[i2].getInterfaces().length > 0) {
                                            clsArr[i2] = aVar.f891c[i2].getInterfaces()[0];
                                        }
                                    }
                                    declaredMethod = cls.getDeclaredMethod(aVar.f889a, clsArr);
                                }
                            }
                            if (declaredMethod != null) {
                                declaredMethod.setAccessible(true);
                                declaredMethod.invoke(aVar.f890b, aVar.f892d);
                            }
                        }
                    } catch (SecurityException e2) {
                        e2.printStackTrace();
                    } catch (InvocationTargetException e3) {
                        e3.printStackTrace();
                    }
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                } catch (IllegalArgumentException e5) {
                    e5.printStackTrace();
                }
            } catch (NoSuchMethodException e6) {
                e6.printStackTrace();
            }
        }
        this.f887a.clear();
    }

    public synchronized void a(Object obj, Object... objArr) {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 3) {
                this.f887a.add(new a(obj, stackTrace[3].getMethodName(), objArr));
            }
        } catch (Throwable unused) {
        }
        this.f888b = false;
    }

    /* JADX INFO: compiled from: MethodCallHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f889a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Object f890b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Class<?>[] f891c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Object[] f892d;

        public a(Object obj, String str, Object... objArr) {
            this.f890b = obj;
            this.f889a = str;
            if (objArr == null || objArr.length <= 0) {
                return;
            }
            this.f891c = new Class[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                this.f891c[i] = objArr[i].getClass();
            }
            this.f892d = new Object[objArr.length];
            for (int i2 = 0; i2 < objArr.length; i2++) {
                this.f892d[i2] = objArr[i2];
            }
        }
    }
}