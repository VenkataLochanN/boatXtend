package com.autonavi.base.amap.mapcore;

import com.amap.api.mapcore.util.ec;
import com.autonavi.base.amap.mapcore.annotations.ParameterIsClass;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class NativeBase {
    private static final String CREATE_OVERLAY = "createOverlay";
    private static final int STACK_NUMBER = 3;
    private static final String UPDATE_OPTIONS = "updateOptions";
    protected long mNative = 0;
    private boolean mCalledFuntion = false;
    protected boolean useRunnable = true;
    private volatile boolean destory = false;
    ArrayList<Method> methodMap = new ArrayList<>();
    ArrayList<Runnable> runnableArrayList = new ArrayList<>();

    protected abstract void createNative();

    protected abstract void finalizeNative();

    protected void finalize() throws Throwable {
        super.finalize();
        try {
            finalizeNative();
        } catch (Throwable th) {
            ec.a(111, getClass().getSimpleName(), "execute error: ", th.toString());
        }
    }

    protected boolean isReady() {
        return this.mNative != 0;
    }

    public void validate() {
        ParameterIsClass parameterIsClass;
        Class<?>[] parameterTypes;
        java.lang.reflect.Method[] methods = getClass().getMethods();
        if (methods == null) {
            return;
        }
        for (java.lang.reflect.Method method : methods) {
            if (method.isAnnotationPresent(ParameterIsClass.class) && (parameterIsClass = (ParameterIsClass) method.getAnnotation(ParameterIsClass.class)) != null && parameterIsClass.required() && (parameterTypes = method.getParameterTypes()) != null) {
                for (Class<?> cls : parameterTypes) {
                    if (!(cls instanceof Object)) {
                        throw new RuntimeException("函数：" + method + " 参数不是对象类型");
                    }
                }
            }
        }
    }

    public synchronized void callAllFunction() {
        Class<?> cls;
        java.lang.reflect.Method declaredMethod;
        if (isReady() && !this.destory) {
            if (this.mCalledFuntion) {
                return;
            }
            try {
                this.mCalledFuntion = true;
            } catch (Throwable unused) {
            }
            if (this.useRunnable) {
                while (this.runnableArrayList.size() > 0 && !this.destory) {
                    Runnable runnable = this.runnableArrayList.get(0);
                    if (runnable != null) {
                        runnable.run();
                    }
                    this.runnableArrayList.remove(0);
                }
                return;
            }
            for (Method method : this.methodMap) {
                if (this.destory) {
                    break;
                }
                if (method.object != null && (cls = method.object.getClass()) != null && (declaredMethod = cls.getDeclaredMethod(method.methodName, method.clazz)) != null) {
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(method.object, method.param);
                }
            }
            this.methodMap.clear();
        }
    }

    public void destroy() {
        this.destory = true;
        synchronized (this) {
            this.runnableArrayList.clear();
            this.methodMap.clear();
        }
    }

    public synchronized void storeUncallFunction(Object obj, Object obj2, Object... objArr) {
        try {
            if (this.useRunnable && obj2 != null) {
                synchronized (this.runnableArrayList) {
                    this.runnableArrayList.add((Runnable) obj2);
                }
            } else {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                if (stackTrace != null && stackTrace.length >= 3) {
                    this.methodMap.add(new Method(obj, stackTrace[3].getMethodName(), false, objArr));
                }
            }
            this.mCalledFuntion = false;
        } catch (Throwable unused) {
        }
    }

    public static class Method {
        private boolean baseClass;
        private Class<?>[] clazz;
        private String methodName;
        private Object object;
        private Object[] param;

        public Method(Object obj, String str, boolean z, Object... objArr) {
            this.object = obj;
            this.methodName = str;
            this.baseClass = z;
            if (objArr != null) {
                try {
                    if (objArr.length > 0) {
                        this.clazz = new Class[objArr.length];
                        if ((NativeBase.CREATE_OVERLAY.equals(str) || NativeBase.UPDATE_OPTIONS.equals(str)) ? true : z) {
                            for (int i = 0; i < objArr.length; i++) {
                                if (i == 1) {
                                    this.clazz[i] = objArr[i].getClass().getSuperclass();
                                } else {
                                    this.clazz[i] = objArr[i].getClass();
                                }
                            }
                        } else {
                            for (int i2 = 0; i2 < objArr.length; i2++) {
                                this.clazz[i2] = objArr[i2].getClass();
                            }
                        }
                        this.param = new Object[objArr.length];
                        for (int i3 = 0; i3 < objArr.length; i3++) {
                            this.param[i3] = objArr[i3];
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }
}