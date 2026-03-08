package com.baidu.mapsdkplatform.comapi.synchronization.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceConstant;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceData;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceQueryOptions;
import com.baidu.mapapi.synchronization.histroytrace.OnHistoryTraceListener;
import com.baidu.mapsdkplatform.comapi.synchronization.b.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class a extends Handler {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static OnHistoryTraceListener f3646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HistoryTraceData f3648c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<HistoryTraceData.HistoryTracePoint> f3649d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b.a f3650e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private HistoryTraceQueryOptions f3651f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3652g;
    private int i;
    private String j;
    private SparseArray<List<HistoryTraceData.HistoryTracePoint>> k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3645a = a.class.getSimpleName();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static boolean f3647h = false;

    a(Looper looper) {
        super(looper);
        this.f3652g = false;
        this.i = 0;
        this.j = HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_SUCCESS;
        this.f3649d = new ArrayList();
        this.f3648c = new HistoryTraceData();
        this.k = new SparseArray<>();
    }

    private LatLng a(String str) {
        if (TextUtils.isEmpty(str)) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Coord string is null");
            return null;
        }
        String[] strArrSplit = str.split(AppInfo.DELIM);
        if (strArrSplit.length == 0 || 2 != strArrSplit.length) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Coord result is error");
            return null;
        }
        try {
            try {
                return new LatLng(Double.parseDouble(strArrSplit[1]), Double.parseDouble(strArrSplit[0]));
            } catch (NumberFormatException e2) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3645a, "Parser coord latitude failed", e2);
                return null;
            }
        } catch (NumberFormatException e3) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3645a, "Parser coord longitude failed", e3);
            return null;
        }
    }

    private List<HistoryTraceData.HistoryTracePoint> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Request result not contain points info");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                HistoryTraceData.HistoryTracePoint historyTracePoint = new HistoryTraceData.HistoryTracePoint();
                historyTracePoint.setPoint(new LatLng(jSONObjectOptJSONObject.optDouble("latitude"), jSONObjectOptJSONObject.optDouble("longitude")));
                historyTracePoint.setLocationTime(jSONObjectOptJSONObject.optLong("loc_time"));
                historyTracePoint.setCreateTime(jSONObjectOptJSONObject.optString("create_time"));
                arrayList.add(historyTracePoint);
            }
        }
        return arrayList;
    }

    private void a(int i) {
        if (this.f3650e == null) {
            this.f3650e = new b.a();
        }
        Message messageObtainMessage = this.f3650e.obtainMessage();
        messageObtainMessage.what = i;
        messageObtainMessage.obj = this.f3651f;
        this.f3650e.sendMessage(messageObtainMessage);
    }

    private void a(int i, String str, int i2, HistoryTraceData historyTraceData) {
        OnHistoryTraceListener onHistoryTraceListener = f3646b;
        if (onHistoryTraceListener == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "OnHistoryTraceListener is null");
        } else if (1 == i2) {
            onHistoryTraceListener.onQueryHistroyTraceData(i, str, historyTraceData);
        } else {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Undefined message type to notify");
        }
    }

    private boolean a(String str, HistoryTraceData historyTraceData, int i) {
        if (TextUtils.isEmpty(str) || historyTraceData == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Parameter error when parser");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_RESULT_PARSER_FAILED, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_QUERY_RESULT_PARSER_FAILED, i, null);
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!a(jSONObject, i)) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Request result contain error");
                return false;
            }
            this.f3652g = false;
            int iOptInt = jSONObject.optInt("total");
            historyTraceData.setTotalPoints(iOptInt);
            int iOptInt2 = jSONObject.optInt("size");
            int iOptInt3 = jSONObject.optInt("req_page_index");
            if (iOptInt2 * iOptInt3 < iOptInt) {
                f3647h = true;
                iOptInt3++;
                f.a(iOptInt3);
            }
            historyTraceData.setCurrentPageIndex(iOptInt3);
            historyTraceData.setDistance(jSONObject.optDouble("distance"));
            historyTraceData.setTollDiatance(jSONObject.optDouble("toll_distance"));
            historyTraceData.setCurrentOrderState(jSONObject.optInt("o_status"));
            historyTraceData.setOrderStartPosition(a(jSONObject.optString("o_start_point")));
            historyTraceData.setOrderEndPosition(a(jSONObject.optString("o_end_point")));
            List<HistoryTraceData.HistoryTracePoint> list = this.f3649d;
            if (list != null && !list.isEmpty()) {
                this.f3649d.clear();
            }
            List<HistoryTraceData.HistoryTracePoint> listA = a(jSONObject.optJSONArray("points"));
            if (listA != null && !listA.isEmpty()) {
                this.f3649d.addAll(listA);
            }
            return true;
        } catch (JSONException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3645a, "JSONException happened when parser request result", e2);
            return false;
        }
    }

    private boolean a(JSONObject jSONObject, int i) {
        if (jSONObject == null || !jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_RESULT_PARSER_FAILED, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_QUERY_RESULT_PARSER_FAILED, i, null);
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Request result no status");
            return false;
        }
        this.i = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
        this.j = jSONObject.optString("message");
        int i2 = this.i;
        if (i2 == 0) {
            this.f3652g = false;
            return true;
        }
        if (1 != i2 || this.f3652g) {
            return true;
        }
        a(i);
        this.f3652g = true;
        return false;
    }

    public void a() {
        if (f3646b != null) {
            f3646b = null;
        }
        List<HistoryTraceData.HistoryTracePoint> list = this.f3649d;
        if (list != null) {
            list.clear();
            this.f3649d = null;
        }
        this.f3648c = null;
    }

    public void a(HistoryTraceQueryOptions historyTraceQueryOptions) {
        this.f3651f = historyTraceQueryOptions;
    }

    public void a(OnHistoryTraceListener onHistoryTraceListener) {
        f3646b = onHistoryTraceListener;
    }

    public void a(b.a aVar) {
        this.f3650e = aVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(f3645a, "Message type = " + message.what);
        if (message.what != 3) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3645a, "Undefined message type");
            return;
        }
        int i = message.arg1;
        String str = (String) message.obj;
        if (str == null) {
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_QUERY_RESULT_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_QUERY_RESULT_NULL, i, null);
            return;
        }
        boolean zA = a(str, this.f3648c, i);
        if (zA) {
            this.k.put(this.f3648c.getCurrentPageIndex() - 1, this.f3649d);
            if (f3647h) {
                a(i);
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            List<HistoryTraceData.HistoryTracePoint> list = this.k.get(i2);
            if (list != null && !list.isEmpty()) {
                arrayList.addAll(list);
            }
        }
        this.f3648c.setPointsList(arrayList);
        HistoryTraceData historyTraceData = this.f3648c;
        if (zA && !f3647h) {
            a(this.i, this.j, i, historyTraceData);
            this.f3648c = null;
            this.f3648c = new HistoryTraceData();
            this.f3649d.clear();
            this.f3649d = null;
            this.f3649d = new ArrayList();
            this.k.clear();
        }
        f3647h = false;
    }
}