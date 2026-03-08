package com.amap.api.mapcore.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.LBSTraceClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: TraceResultPool.java */
/* JADX INFO: loaded from: classes.dex */
public class gf {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile gf f1030b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, a> f1031a;

    public gf() {
        this.f1031a = null;
        this.f1031a = Collections.synchronizedMap(new HashMap());
    }

    public static gf a() {
        if (f1030b == null) {
            synchronized (gf.class) {
                if (f1030b == null) {
                    f1030b = new gf();
                }
            }
        }
        return f1030b;
    }

    public synchronized void a(String str, int i, List<LatLng> list) {
        if (this.f1031a != null) {
            this.f1031a.get(str).a().put(Integer.valueOf(i), list);
        }
    }

    public synchronized void a(String str, int i, int i2, int i3) {
        if (this.f1031a != null) {
            this.f1031a.put(str, new a(i, i2, i3, new HashMap(16)));
        }
    }

    public synchronized a a(String str) {
        if (this.f1031a == null) {
            return null;
        }
        return this.f1031a.get(str);
    }

    public void a(Handler handler, int i, String str) {
        Message messageObtainMessage = handler.obtainMessage();
        messageObtainMessage.obj = str;
        messageObtainMessage.what = 102;
        Bundle bundle = new Bundle();
        bundle.putInt("lineID", i);
        messageObtainMessage.setData(bundle);
        handler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: compiled from: TraceResultPool.java */
    class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1033b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1034c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1036e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private HashMap<Integer, List<LatLng>> f1038g;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f1035d = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f1037f = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private List<LatLng> f1039h = new ArrayList();

        public a(int i, int i2, int i3, HashMap<Integer, List<LatLng>> map) {
            this.f1033b = 0;
            this.f1034c = 0;
            this.f1036e = 0;
            this.f1033b = i2;
            this.f1038g = map;
            this.f1034c = i;
            this.f1036e = i3;
        }

        public HashMap<Integer, List<LatLng>> a() {
            return this.f1038g;
        }

        public void a(Handler handler) {
            List<LatLng> list;
            for (int i = this.f1035d; i <= this.f1033b && (list = this.f1038g.get(Integer.valueOf(i))) != null; i++) {
                this.f1039h.addAll(list);
                a(handler, list);
            }
            if (this.f1035d == this.f1033b + 1) {
                b(handler);
            }
        }

        private void a(Handler handler, List<LatLng> list) {
            Message messageObtainMessage = handler.obtainMessage();
            messageObtainMessage.obj = list;
            messageObtainMessage.what = 100;
            messageObtainMessage.arg1 = this.f1035d;
            Bundle bundle = new Bundle();
            bundle.putInt("lineID", this.f1034c);
            messageObtainMessage.setData(bundle);
            handler.sendMessage(messageObtainMessage);
            this.f1035d++;
            this.f1037f++;
        }

        private void b(Handler handler) {
            if (this.f1037f > 0) {
                int iA = gc.a(this.f1039h);
                Message messageObtainMessage = handler.obtainMessage();
                messageObtainMessage.obj = this.f1039h;
                messageObtainMessage.what = 101;
                messageObtainMessage.arg1 = iA;
                messageObtainMessage.arg2 = this.f1036e;
                Bundle bundle = new Bundle();
                bundle.putInt("lineID", this.f1034c);
                messageObtainMessage.setData(bundle);
                handler.sendMessage(messageObtainMessage);
                return;
            }
            gf.this.a(handler, this.f1034c, LBSTraceClient.MIN_GRASP_POINT_ERROR);
        }
    }
}