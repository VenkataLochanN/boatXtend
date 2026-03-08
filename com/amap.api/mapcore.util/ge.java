package com.amap.api.mapcore.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.trace.LBSTraceBase;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceStatusListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: TraceManager.java */
/* JADX INFO: loaded from: classes.dex */
public class ge implements LocationSource.OnLocationChangedListener, LBSTraceBase {
    private static final TimeUnit s = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private CoordinateConverter f1014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ExecutorService f1015d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ExecutorService f1016e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TraceStatusListener f1019h;
    private as i;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f1017f = 2000;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1018g = 5;
    private List<TraceLocation> j = new ArrayList();
    private int k = 0;
    private int l = 0;
    private long m = 0;
    private TraceLocation o = null;
    private List<LatLng> p = new ArrayList();
    private List<LatLng> q = new ArrayList();
    private List<LatLng> r = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1012a = Runtime.getRuntime().availableProcessors();
    private BlockingQueue<Runnable> t = new LinkedBlockingQueue();
    private BlockingQueue<Runnable> u = new LinkedBlockingQueue();
    private c n = new c(Looper.getMainLooper());

    public ge(Context context) {
        this.f1013b = context.getApplicationContext();
        this.f1014c = new CoordinateConverter(this.f1013b);
        go.a().a(this.f1013b);
        this.f1015d = new ThreadPoolExecutor(1, this.f1012a * 2, 1L, s, this.t, new ee("AMapTraceManagerProcess"), new ThreadPoolExecutor.AbortPolicy());
        this.f1016e = new ThreadPoolExecutor(1, this.f1012a * 2, 1L, s, this.u, new ee("AMapTraceManagerRequest"), new ThreadPoolExecutor.AbortPolicy());
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void queryProcessedTrace(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
        try {
            this.f1015d.execute(new a(i, list, i2, traceListener));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void setLocationInterval(long j) {
        this.f1017f = j;
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void setTraceStatusInterval(int i) {
        this.f1018g = Math.max(i, 2);
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void startTrace(TraceStatusListener traceStatusListener) {
        if (this.f1013b == null) {
            Log.w("LBSTraceClient", "Context need to be initialized");
            return;
        }
        this.m = System.currentTimeMillis();
        this.f1019h = traceStatusListener;
        if (this.i == null) {
            this.i = new as(this.f1013b);
            this.i.a(this.f1017f);
            this.i.activate(this);
        }
    }

    @Override // com.amap.api.maps.LocationSource.OnLocationChangedListener
    public void onLocationChanged(Location location) {
        if (this.f1019h != null) {
            try {
                if (System.currentTimeMillis() - this.m >= 30000 && this.f1019h != null) {
                    this.f1019h.onTraceStatus(null, null, LBSTraceClient.LOCATE_TIMEOUT_ERROR);
                }
                this.m = System.currentTimeMillis();
                Bundle extras = location.getExtras();
                int i = extras.getInt(MyLocationStyle.ERROR_CODE);
                if (i != 0) {
                    Log.w("LBSTraceClient", "Locate failed [errorCode:\"" + i + "\"  errorInfo:" + extras.getString(MyLocationStyle.ERROR_INFO) + "\"]");
                    return;
                }
                synchronized (this.j) {
                    TraceLocation traceLocation = new TraceLocation(location.getLatitude(), location.getLongitude(), location.getSpeed(), location.getBearing(), location.getTime());
                    if (a(this.o, traceLocation)) {
                        return;
                    }
                    this.j.add(traceLocation);
                    this.o = traceLocation;
                    this.k++;
                    if (this.k == this.f1018g) {
                        this.l += this.k;
                        a();
                        this.k = 0;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean a(TraceLocation traceLocation, TraceLocation traceLocation2) {
        return traceLocation != null && traceLocation2 != null && traceLocation.getLatitude() == traceLocation2.getLatitude() && traceLocation.getLongitude() == traceLocation2.getLongitude();
    }

    private void a() {
        int size = this.j.size();
        int i = this.f1018g;
        if (size < i) {
            return;
        }
        if (size <= 50) {
            ArrayList arrayList = new ArrayList(this.j);
            queryProcessedTrace(0, arrayList, 1, new b(arrayList));
            return;
        }
        int i2 = size - 50;
        if (i2 < 0) {
            return;
        }
        a(new ArrayList(this.j.subList(i2 - i, i2)));
        ArrayList arrayList2 = new ArrayList(this.j.subList(i2, size));
        queryProcessedTrace(i2, arrayList2, 1, new b(arrayList2));
    }

    private void a(List<TraceLocation> list) {
        synchronized (this.r) {
            if (list != null) {
                if (list.size() >= 1) {
                    if (this.r.size() < 1) {
                        return;
                    }
                    LatLng latLng = null;
                    double dA = 0.0d;
                    TraceLocation traceLocation = null;
                    double d2 = 0.0d;
                    for (TraceLocation traceLocation2 : list) {
                        if (traceLocation2 != null) {
                            if (traceLocation != null) {
                                double dA2 = a(traceLocation.getLatitude(), traceLocation.getLongitude(), traceLocation2.getLatitude(), traceLocation2.getLongitude());
                                if (dA2 <= 100.0d) {
                                    d2 += dA2;
                                }
                            }
                            traceLocation = traceLocation2;
                        }
                    }
                    Iterator<LatLng> it = this.r.iterator();
                    while (it.hasNext()) {
                        LatLng next = it.next();
                        if (next == null) {
                            it.remove();
                        } else {
                            if (latLng == null) {
                                this.p.add(next);
                                it.remove();
                            } else {
                                dA += a(latLng.latitude, latLng.longitude, next.latitude, next.longitude);
                                if (dA >= d2) {
                                    break;
                                }
                                this.p.add(next);
                                it.remove();
                            }
                            latLng = next;
                        }
                    }
                }
            }
        }
    }

    private double a(double d2, double d3, double d4, double d5) {
        double d6 = d2 > d4 ? d2 - d4 : d4 - d2;
        double d7 = d3 > d5 ? d3 - d5 : d5 - d3;
        return Math.sqrt((d6 * d6) + (d7 * d7));
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void stopTrace() {
        b();
        c();
    }

    private void b() {
        as asVar = this.i;
        if (asVar != null) {
            asVar.deactivate();
            this.i = null;
        }
    }

    private void c() {
        this.t.clear();
        this.u.clear();
        List<TraceLocation> list = this.j;
        if (list != null) {
            synchronized (list) {
                if (this.j != null) {
                    this.j.clear();
                }
                this.l = 0;
                this.k = 0;
                this.m = 0L;
                this.o = null;
            }
        }
    }

    @Override // com.amap.api.trace.LBSTraceBase
    public void destroy() {
        try {
            stopTrace();
            if (this.f1015d != null && !this.f1015d.isShutdown()) {
                this.f1015d.shutdownNow();
                this.f1015d = null;
            }
            if (this.f1016e != null && !this.f1016e.isShutdown()) {
                this.f1016e.shutdownNow();
                this.f1016e = null;
            }
            this.j = null;
            this.f1019h = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f1013b = null;
        this.f1014c = null;
    }

    /* JADX INFO: compiled from: TraceManager.java */
    class b implements TraceListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<TraceLocation> f1028b;

        @Override // com.amap.api.trace.TraceListener
        public void onTraceProcessing(int i, int i2, List<LatLng> list) {
        }

        public b(List<TraceLocation> list) {
            this.f1028b = list;
        }

        @Override // com.amap.api.trace.TraceListener
        public void onRequestFailed(int i, String str) {
            ArrayList arrayList = new ArrayList();
            if (ge.this.r != null) {
                arrayList.addAll(ge.this.r);
            }
            List<TraceLocation> list = this.f1028b;
            if (list != null) {
                int size = list.size();
                if (this.f1028b.size() > ge.this.f1018g) {
                    for (int i2 = size - ge.this.f1018g; i2 < size; i2++) {
                        TraceLocation traceLocation = this.f1028b.get(i2);
                        if (traceLocation != null) {
                            arrayList.add(new LatLng(traceLocation.getLatitude(), traceLocation.getLongitude()));
                        }
                    }
                }
            }
            a(i, arrayList);
        }

        @Override // com.amap.api.trace.TraceListener
        public void onFinished(int i, List<LatLng> list, int i2, int i3) {
            a(i, list);
        }

        private void a(int i, List<LatLng> list) {
            try {
                synchronized (ge.this.r) {
                    ge.this.r.clear();
                    ge.this.r.addAll(list);
                }
                ge.this.q.clear();
                if (i == 0) {
                    ge.this.q.addAll(ge.this.r);
                } else {
                    ge.this.q.addAll(ge.this.p);
                    ge.this.q.addAll(ge.this.r);
                }
                ge.this.f1019h.onTraceStatus(ge.this.j, ge.this.q, LBSTraceClient.TRACE_SUCCESS);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: TraceManager.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1022c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f1023d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private List<TraceLocation> f1024e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private TraceListener f1026g;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<TraceLocation> f1021b = new ArrayList();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f1025f = ej.a();

        public a(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
            this.f1022c = i2;
            this.f1023d = i;
            this.f1024e = list;
            this.f1026g = traceListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ge.this.n.a(this.f1026g);
                int iA = a();
                if (this.f1024e != null && this.f1024e.size() >= 2) {
                    Iterator<TraceLocation> it = this.f1024e.iterator();
                    while (it.hasNext()) {
                        TraceLocation traceLocationCopy = it.next().copy();
                        if (traceLocationCopy != null && traceLocationCopy.getLatitude() > 0.0d && traceLocationCopy.getLongitude() > 0.0d) {
                            this.f1021b.add(traceLocationCopy);
                        }
                    }
                    int size = (this.f1021b.size() - 2) / 500;
                    gf.a().a(this.f1025f, this.f1023d, size, iA);
                    int i = 0;
                    int size2 = 500;
                    while (i <= size) {
                        if (i == size) {
                            size2 = this.f1021b.size();
                        }
                        int i2 = size2;
                        ArrayList arrayList = new ArrayList();
                        for (int i3 = 0; i3 < i2; i3++) {
                            TraceLocation traceLocationRemove = this.f1021b.remove(0);
                            if (traceLocationRemove != null) {
                                if (this.f1022c != 1) {
                                    if (this.f1022c == 3) {
                                        ge.this.f1014c.from(CoordinateConverter.CoordType.BAIDU);
                                    } else if (this.f1022c == 2) {
                                        ge.this.f1014c.from(CoordinateConverter.CoordType.GPS);
                                    }
                                    ge.this.f1014c.coord(new LatLng(traceLocationRemove.getLatitude(), traceLocationRemove.getLongitude()));
                                    LatLng latLngConvert = ge.this.f1014c.convert();
                                    if (latLngConvert != null) {
                                        traceLocationRemove.setLatitude(latLngConvert.latitude);
                                        traceLocationRemove.setLongitude(latLngConvert.longitude);
                                    }
                                }
                                arrayList.add(traceLocationRemove);
                            }
                        }
                        if (arrayList.size() >= 2 && arrayList.size() <= 500) {
                            ge.this.f1016e.execute(new gd(ge.this.f1013b, ge.this.n, arrayList, this.f1022c, this.f1025f, this.f1023d, i));
                            i++;
                            try {
                                Thread.sleep(50L);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                        size2 = i2;
                    }
                    return;
                }
                gf.a().a(ge.this.n, this.f1023d, LBSTraceClient.MIN_GRASP_POINT_ERROR);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        private int a() {
            List<TraceLocation> list = this.f1024e;
            int iA = 0;
            if (list != null && list.size() != 0) {
                ArrayList arrayList = new ArrayList();
                for (TraceLocation traceLocation : this.f1024e) {
                    if (traceLocation != null) {
                        if (traceLocation.getSpeed() < 0.01d) {
                            arrayList.add(traceLocation);
                        } else {
                            iA += a(arrayList);
                            arrayList.clear();
                        }
                    }
                }
            }
            return iA;
        }

        private int a(List<TraceLocation> list) {
            int size = list.size();
            if (size <= 1) {
                return 0;
            }
            TraceLocation traceLocation = list.get(0);
            TraceLocation traceLocation2 = list.get(size - 1);
            if (traceLocation == null || traceLocation2 == null || traceLocation == null || traceLocation2 == null) {
                return 0;
            }
            return (int) ((traceLocation2.getTime() - traceLocation.getTime()) / 1000);
        }
    }

    /* JADX INFO: compiled from: TraceManager.java */
    static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private TraceListener f1029a;

        public c(Looper looper) {
            super(looper);
        }

        public void a(TraceListener traceListener) {
            this.f1029a = traceListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data;
            try {
                if (this.f1029a == null || (data = message.getData()) == null) {
                    return;
                }
                int i = data.getInt("lineID");
                switch (message.what) {
                    case 100:
                        this.f1029a.onTraceProcessing(i, message.arg1, (List) message.obj);
                        break;
                    case 101:
                        this.f1029a.onFinished(i, (List) message.obj, message.arg1, message.arg2);
                        break;
                    case 102:
                        this.f1029a.onRequestFailed(i, (String) message.obj);
                        break;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}