package com.ido.life.data.cache.delay;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: compiled from: OperateProxyHandler.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J6\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\fH\u0096\u0002¢\u0006\u0002\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/ido/life/data/cache/delay/OperateProxyHandler;", "Ljava/lang/reflect/InvocationHandler;", "obj", "", "(Ljava/lang/Object;)V", "getObj", "()Ljava/lang/Object;", "invoke", "proxy", FirebaseAnalytics.Param.METHOD, "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class OperateProxyHandler implements InvocationHandler {
    private final Object obj;

    public OperateProxyHandler(Object obj) {
        this.obj = obj;
    }

    public final Object getObj() {
        return this.obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        sb.append("invoke method = ");
        sb.append(method != null ? method.getName() : null);
        sb.append(", args = ");
        sb.append(Arrays.toString(args));
        Log.e("OperateProxyHandler", sb.toString());
        if (method != null) {
            Object obj = this.obj;
            if (args == null) {
                args = new Object[0];
            }
            method.invoke(obj, Arrays.copyOf(args, args.length));
        }
        return null;
    }
}