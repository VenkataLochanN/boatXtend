package com.ido.common.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes2.dex */
public class HookClickListenerHelper {
    private static final int MAX_DELAY_TIME = 500;

    public static void hookOnClickAuto(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        System.currentTimeMillis();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                hookViewGroup((ViewGroup) childAt);
            } else {
                hookOnClick(childAt);
            }
        }
    }

    public static void hookViewGroup(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                hookOnClick(childAt);
                hookViewGroup((ViewGroup) childAt);
            } else {
                hookOnClick(childAt);
            }
        }
    }

    public static void hookOnClick(View... viewArr) {
        for (View view : viewArr) {
            try {
                Method declaredMethod = View.class.getDeclaredMethod("getListenerInfo", new Class[0]);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(view, new Object[0]);
                if (objInvoke != null) {
                    Field declaredField = objInvoke.getClass().getDeclaredField("mOnClickListener");
                    declaredField.setAccessible(true);
                    View.OnClickListener onClickListener = (View.OnClickListener) declaredField.get(objInvoke);
                    if (onClickListener != null) {
                        declaredField.set(objInvoke, Proxy.newProxyInstance(view.getClass().getClassLoader(), new Class[]{View.OnClickListener.class}, new ClickListenerInvocationHandler(onClickListener)));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    static class ClickListenerInvocationHandler implements InvocationHandler {
        private long lastTime = 0;
        Object target;

        public ClickListenerInvocationHandler(View.OnClickListener onClickListener) {
            this.target = onClickListener;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (Math.abs(jCurrentTimeMillis - this.lastTime) < 500) {
                return null;
            }
            this.lastTime = jCurrentTimeMillis;
            try {
                method.invoke(this.target, objArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }
    }
}