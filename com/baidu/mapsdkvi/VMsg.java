package com.baidu.mapsdkvi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public class VMsg {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Handler f3874b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static HandlerThread f3875c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3873a = VMsg.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static VMsg f3876d = new VMsg();

    static class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VMsg.OnUserCommand1(message.what, message.arg1, message.arg2, message.obj == null ? 0L : ((Long) message.obj).longValue());
        }
    }

    public static native void InitClass(Object obj);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void OnUserCommand1(int i, int i2, int i3, long j);

    public static void destroy() {
        f3875c.quit();
        f3875c = null;
        f3874b.removeCallbacksAndMessages(null);
        f3874b = null;
    }

    public static VMsg getInstance() {
        return f3876d;
    }

    public static void init() {
        f3875c = new HandlerThread("VIMsgThread");
        f3875c.start();
        f3874b = new a(f3875c.getLooper());
    }

    private static void postMessage(int i, int i2, int i3, long j) {
        Handler handler = f3874b;
        if (handler == null) {
            return;
        }
        Message.obtain(handler, i, i2, i3, j == 0 ? null : Long.valueOf(j)).sendToTarget();
    }
}