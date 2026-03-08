package com.amap.api.trace;

import android.content.Context;
import com.amap.api.mapcore.util.ge;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LBSTraceClient {
    public static final String LOCATE_TIMEOUT_ERROR = "定位超时";
    public static final String MIN_GRASP_POINT_ERROR = "轨迹点太少或距离太近,轨迹纠偏失败";
    public static final String TRACE_SUCCESS = "纠偏成功";
    public static final int TYPE_AMAP = 1;
    public static final int TYPE_BAIDU = 3;
    public static final int TYPE_GPS = 2;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static LBSTraceBase f1952a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile LBSTraceClient f1953b;

    public LBSTraceClient(Context context) {
        a(context);
    }

    private LBSTraceClient() {
    }

    public static LBSTraceClient getInstance(Context context) {
        if (f1953b == null) {
            synchronized (LBSTraceClient.class) {
                if (f1953b == null) {
                    a(context);
                    f1953b = new LBSTraceClient();
                }
            }
        }
        return f1953b;
    }

    private static void a(Context context) {
        if (context != null) {
            f1952a = new ge(context.getApplicationContext());
        }
    }

    public void queryProcessedTrace(int i, List<TraceLocation> list, int i2, TraceListener traceListener) {
        LBSTraceBase lBSTraceBase = f1952a;
        if (lBSTraceBase != null) {
            lBSTraceBase.queryProcessedTrace(i, list, i2, traceListener);
        }
    }

    public void startTrace(TraceStatusListener traceStatusListener) {
        LBSTraceBase lBSTraceBase = f1952a;
        if (lBSTraceBase != null) {
            lBSTraceBase.startTrace(traceStatusListener);
        }
    }

    public void stopTrace() {
        LBSTraceBase lBSTraceBase = f1952a;
        if (lBSTraceBase != null) {
            lBSTraceBase.stopTrace();
        }
    }

    public void destroy() {
        LBSTraceBase lBSTraceBase = f1952a;
        if (lBSTraceBase != null) {
            lBSTraceBase.destroy();
            a();
        }
    }

    private static void a() {
        f1952a = null;
        f1953b = null;
    }
}