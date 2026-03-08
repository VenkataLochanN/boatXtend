package com.ido.life.util;

import android.os.Handler;
import android.os.Looper;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.data.listener.Callback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RxUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u000bH\u0007J4\u0010\f\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u00072\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u000bH\u0007J\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0007J\u0016\u0010\u000f\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/ido/life/util/RxUtils;", "", "()V", "mMainHandler", "Landroid/os/Handler;", "async", "", "T", "func", "Lkotlin/Function0;", "callback", "Lcom/ido/life/data/listener/Callback;", "delay", "delayMill", "", "doOnUI", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RxUtils {
    public static final RxUtils INSTANCE = new RxUtils();
    private static final Handler mMainHandler = new Handler(Looper.getMainLooper());

    private RxUtils() {
    }

    public static final /* synthetic */ Handler access$getMMainHandler$p(RxUtils rxUtils) {
        return mMainHandler;
    }

    @JvmStatic
    public static final void doOnUI(final Function0<Unit> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        mMainHandler.post(new Runnable() { // from class: com.ido.life.util.RxUtils.doOnUI.1
            @Override // java.lang.Runnable
            public final void run() {
                func.invoke();
            }
        });
    }

    @JvmStatic
    public static final <T> void async(final Function0<? extends T> func, final Callback<T> callback) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.util.RxUtils.async.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    final Object objInvoke = func.invoke();
                    RxUtils.access$getMMainHandler$p(RxUtils.INSTANCE).post(new Runnable() { // from class: com.ido.life.util.RxUtils.async.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Callback callback2 = callback;
                            if (callback2 != null) {
                                callback2.onSuccess(objInvoke);
                            }
                        }
                    });
                } catch (Exception unused) {
                    RxUtils.access$getMMainHandler$p(RxUtils.INSTANCE).post(new Runnable() { // from class: com.ido.life.util.RxUtils.async.1.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            Callback callback2 = callback;
                            if (callback2 != null) {
                                callback2.onFailed("");
                            }
                        }
                    });
                }
            }
        });
    }

    @JvmStatic
    public static final void delay(long delayMill, final Function0<Unit> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        mMainHandler.postDelayed(new Runnable() { // from class: com.ido.life.util.RxUtils$sam$java_lang_Runnable$0
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                Intrinsics.checkExpressionValueIsNotNull(func.invoke(), "invoke(...)");
            }
        }, delayMill);
    }

    @JvmStatic
    public static final <T> void delay(final long delayMill, final Function0<? extends T> func, final Callback<T> callback) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.util.RxUtils.delay.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    Thread.sleep(delayMill);
                    final Object objInvoke = func.invoke();
                    RxUtils.access$getMMainHandler$p(RxUtils.INSTANCE).post(new Runnable() { // from class: com.ido.life.util.RxUtils.delay.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Callback callback2 = callback;
                            if (callback2 != null) {
                                callback2.onSuccess(objInvoke);
                            }
                        }
                    });
                } catch (Exception unused) {
                    RxUtils.access$getMMainHandler$p(RxUtils.INSTANCE).post(new Runnable() { // from class: com.ido.life.util.RxUtils.delay.1.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            Callback callback2 = callback;
                            if (callback2 != null) {
                                callback2.onFailed("");
                            }
                        }
                    });
                }
            }
        });
    }
}