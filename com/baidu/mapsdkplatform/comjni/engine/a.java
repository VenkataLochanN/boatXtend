package com.baidu.mapsdkplatform.comjni.engine;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3856a = a.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseArray<List<Handler>> f3857b = new SparseArray<>();

    public static void a(int i, int i2, int i3, long j) {
        synchronized (f3857b) {
            List<Handler> list = f3857b.get(i);
            if (list != null && !list.isEmpty()) {
                Iterator<Handler> it = list.iterator();
                while (it.hasNext()) {
                    Message.obtain(it.next(), i, i2, i3, Long.valueOf(j)).sendToTarget();
                }
            }
        }
    }

    public static void a(int i, Handler handler) {
        synchronized (f3857b) {
            if (handler == null) {
                return;
            }
            List<Handler> list = f3857b.get(i);
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(handler);
                f3857b.put(i, arrayList);
            } else if (!list.contains(handler)) {
                list.add(handler);
            }
        }
    }

    public static void b(int i, Handler handler) {
        synchronized (f3857b) {
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                List<Handler> list = f3857b.get(i);
                if (list != null) {
                    list.remove(handler);
                }
            }
        }
    }
}