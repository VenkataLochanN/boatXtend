package com.baidu.mapsdkplatform.comapi.synchronization.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceConstant;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceData;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceDisplayOptions;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceQueryOptions;
import com.baidu.mapapi.synchronization.histroytrace.OnHistoryTraceListener;
import com.baidu.mapsdkplatform.comapi.synchronization.c.c;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static com.baidu.mapsdkplatform.comapi.synchronization.c.a f3654b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static OnHistoryTraceListener f3655c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static com.baidu.mapsdkplatform.comapi.synchronization.b.a f3656e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HandlerThread f3658d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f3659f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private HandlerThread f3660h;
    private e i;
    private volatile boolean j = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3653a = b.class.getSimpleName();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f3657g = 0;

    public static class a extends Handler {
        a() {
        }

        private String a(HistoryTraceQueryOptions historyTraceQueryOptions) {
            String strA = new f(historyTraceQueryOptions).a();
            if (!TextUtils.isEmpty(strA)) {
                return strA;
            }
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3653a, "Build request url failed");
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, String str, int i2, HistoryTraceQueryOptions historyTraceQueryOptions) {
            if (b.f3656e == null) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3653a, "Data parser handler is null");
                return;
            }
            Message messageObtainMessage = b.f3656e.obtainMessage();
            messageObtainMessage.what = i;
            messageObtainMessage.arg1 = i2;
            messageObtainMessage.obj = str;
            b.f3656e.sendMessage(messageObtainMessage);
            b.f3656e.a(historyTraceQueryOptions);
        }

        private void a(HistoryTraceQueryOptions historyTraceQueryOptions, int i) {
            String strA = a(historyTraceQueryOptions);
            if (TextUtils.isEmpty(strA)) {
                b.b(10002, "QueryOptions is null, please check.", i);
            } else {
                a(strA, i, historyTraceQueryOptions);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(c.a aVar, int i) {
            int i2;
            String str;
            int i3 = c.f3661a[aVar.ordinal()];
            if (i3 == 1) {
                i2 = 0;
                str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_SUCCESS;
            } else if (i3 == 2 || i3 == 3) {
                i2 = HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_SERVER_INNER_ERROR;
                str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_SERVER_INNER_ERROR;
            } else if (i3 == 4) {
                i2 = 100010;
                str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_NETWORK_ERROR;
            } else if (i3 != 5) {
                i2 = HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_UNDEFINE_ERROR;
                str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_UNDEFINE_ERROR;
            } else {
                i2 = HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_REQUEST_PARAMETER_ERROR;
                str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_REQUEST_PARAMETER_ERROR;
            }
            b.b(i2, str, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, int i, HistoryTraceQueryOptions historyTraceQueryOptions) {
            if (TextUtils.isEmpty(str)) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3653a, "Request url is null");
                return;
            }
            if (b.f3654b == null) {
                com.baidu.mapsdkplatform.comapi.synchronization.c.a unused = b.f3654b = new com.baidu.mapsdkplatform.comapi.synchronization.c.a();
            }
            b.f3654b.a(str, new d(this, i, historyTraceQueryOptions, str));
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(b.f3653a, "The query type is: " + i);
            HistoryTraceQueryOptions historyTraceQueryOptions = (HistoryTraceQueryOptions) message.obj;
            if (i != 1) {
                return;
            }
            a(historyTraceQueryOptions, i);
        }
    }

    public b() {
        f3654b = new com.baidu.mapsdkplatform.comapi.synchronization.c.a();
        this.f3659f = new a();
        this.f3658d = new HandlerThread("HistoryTraceDataParser");
        this.f3658d.start();
        f3656e = new com.baidu.mapsdkplatform.comapi.synchronization.b.a(this.f3658d.getLooper());
        f3656e.a(this.f3659f);
        this.f3660h = new HandlerThread("HistoryTraceRender");
        this.f3660h.start();
        this.i = new e(this.f3660h.getLooper());
    }

    private void a(HistoryTraceData historyTraceData, int i) {
        if (this.i == null) {
            this.i = new e(this.f3660h.getLooper());
        }
        Message messageObtainMessage = this.i.obtainMessage();
        messageObtainMessage.what = i;
        if (historyTraceData != null) {
            messageObtainMessage.obj = historyTraceData;
        }
        this.i.sendMessage(messageObtainMessage);
    }

    private boolean a(HistoryTraceQueryOptions historyTraceQueryOptions, int i) {
        int i2;
        String str;
        if (historyTraceQueryOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "QueryOptions is null, please check!");
            i2 = 10001;
            str = "QueryOptions is null, please check.";
        } else if (TextUtils.isEmpty(historyTraceQueryOptions.getOrderId())) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "Query orderId is null, please check");
            i2 = 10003;
            str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_QUERY_ORDER_ID_NULL;
        } else if (historyTraceQueryOptions.getRoleType() != 0) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "Current role type not the passenger");
            i2 = HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_ROLE_TYPE_ERROR;
            str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_QUERY_ROLE_TYPE_ERROR;
        } else if (TextUtils.isEmpty(historyTraceQueryOptions.getUserId())) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "Order's user id is null");
            i2 = HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_USER_ID_NULL;
            str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_QUERY_USER_ID_NULL;
        } else {
            if (!TextUtils.isEmpty(historyTraceQueryOptions.getDriverId())) {
                return true;
            }
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "Driver id is null");
            i2 = HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_DRIVER_ID_NULL;
            str = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_QUERY_DRIVER_ID_NULL;
        }
        b(i2, str, i);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i, String str, int i2) {
        OnHistoryTraceListener onHistoryTraceListener = f3655c;
        if (onHistoryTraceListener == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "OnHistoryTraceListener is null");
        } else if (i2 == 1) {
            onHistoryTraceListener.onQueryHistroyTraceData(i, str, null);
        } else {
            if (i2 != 2) {
                return;
            }
            onHistoryTraceListener.onRenderHistroyTrace(i, str);
        }
    }

    private void b(HistoryTraceQueryOptions historyTraceQueryOptions, int i) {
        if (this.f3659f == null) {
            this.f3659f = new a();
        }
        Message messageObtainMessage = this.f3659f.obtainMessage();
        messageObtainMessage.what = i;
        messageObtainMessage.obj = historyTraceQueryOptions;
        this.f3659f.sendMessage(messageObtainMessage);
    }

    public void a() {
        if (f3654b != null) {
            f3654b = null;
        }
        if (f3655c != null) {
            f3655c = null;
        }
        a aVar = this.f3659f;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
            this.f3659f = null;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.b.a aVar2 = f3656e;
        if (aVar2 != null) {
            aVar2.removeCallbacksAndMessages(null);
            f3656e.a();
            f3656e = null;
        }
        HandlerThread handlerThread = this.f3658d;
        if (handlerThread != null) {
            handlerThread.quit();
            this.f3658d = null;
        }
        e eVar = this.i;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
            this.i.a();
            this.i = null;
        }
        HandlerThread handlerThread2 = this.f3660h;
        if (handlerThread2 != null) {
            handlerThread2.quit();
            this.f3660h = null;
        }
        this.j = false;
    }

    public void a(BaiduMap baiduMap, HistoryTraceData historyTraceData, HistoryTraceDisplayOptions historyTraceDisplayOptions, int i) {
        if (baiduMap == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "BaiduMap instance is null");
            OnHistoryTraceListener onHistoryTraceListener = f3655c;
            if (onHistoryTraceListener != null) {
                onHistoryTraceListener.onRenderHistroyTrace(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_BAIDUMAP_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_BAIDUMAP_NULL);
                return;
            }
            return;
        }
        if (5 == i) {
            this.i.a(historyTraceDisplayOptions, baiduMap, i);
            a(historyTraceData, 4);
        } else {
            OnHistoryTraceListener onHistoryTraceListener2 = f3655c;
            if (onHistoryTraceListener2 != null) {
                onHistoryTraceListener2.onRenderHistroyTrace(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_CURRENT_ORDER_STATE_NOT_COMPLETE, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_CURRENT_ORDER_STATE_NOT_COMPLETE);
            }
        }
    }

    public void a(HistoryTraceQueryOptions historyTraceQueryOptions) {
        if (a(historyTraceQueryOptions, 1)) {
            b(historyTraceQueryOptions, 1);
        } else {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3653a, "QueryOptions error, please check!");
        }
    }

    public void a(OnHistoryTraceListener onHistoryTraceListener) {
        f3655c = onHistoryTraceListener;
        f3656e.a(onHistoryTraceListener);
        this.i.a(onHistoryTraceListener);
    }

    public void a(boolean z) {
        com.baidu.mapsdkplatform.comapi.synchronization.c.c.f3684b = z;
    }

    public boolean b() {
        return com.baidu.mapsdkplatform.comapi.synchronization.c.c.f3684b;
    }
}