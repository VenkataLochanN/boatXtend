package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.synchronization.DisplayOptions;
import com.baidu.mapapi.synchronization.RoleOptions;
import com.baidu.mapapi.synchronization.SynchronizationConstants;
import com.baidu.mapsdkplatform.comapi.synchronization.data.RouteLineInfo;
import com.tencent.bugly.BuglyStrategy;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3749a = g.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private RoleOptions f3750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DisplayOptions f3751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BlockingQueue<SyncResponseResult> f3752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private i f3753e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f3754f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f3755g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.c.a f3756h;
    private HandlerThread i;
    private b j;
    private volatile int k;
    private k l;
    private boolean m;
    private float n;
    private long o;
    private int p;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final g f3757a = new g(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH /* 100000 */:
                    SyncResponseResult syncResponseResultB = g.this.b((String) message.obj);
                    if (syncResponseResultB != null) {
                        g.this.a(syncResponseResultB);
                        g.this.j();
                        g.this.b(syncResponseResultB);
                    } else {
                        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(g.f3749a, "parser response data is null");
                    }
                    break;
                case 100001:
                    g.this.k();
                    break;
                default:
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(g.f3749a, "Undefined message type");
                    break;
            }
        }
    }

    private g() {
        this.f3752d = new LinkedBlockingQueue();
        this.f3754f = String.valueOf(0);
        this.f3755g = String.valueOf(0);
        this.f3756h = new com.baidu.mapsdkplatform.comapi.synchronization.c.a();
        this.k = 0;
        this.m = false;
        this.n = 0.0f;
        this.o = 0L;
        this.p = 0;
    }

    /* synthetic */ g(h hVar) {
        this();
    }

    private f a(int i) {
        if (this.f3750b == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "RoleOptions is null");
            return null;
        }
        f fVar = new f();
        fVar.a(this.f3750b.getOrderId());
        fVar.b(this.f3750b.getDriverId());
        fVar.c(this.f3750b.getUserId());
        fVar.a(i);
        if (this.m) {
            this.f3754f = String.valueOf(0);
            this.f3755g = String.valueOf(0);
        }
        fVar.d(this.f3754f);
        fVar.e(this.f3755g);
        return fVar;
    }

    public static g a() {
        return a.f3757a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SyncResponseResult syncResponseResult) {
        if (this.f3752d == null) {
            this.f3752d = new LinkedBlockingQueue();
        }
        if (this.m) {
            this.m = false;
            this.f3752d.clear();
        }
        try {
            this.f3752d.put(syncResponseResult);
        } catch (InterruptedException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "InterruptedException happened when put item into queue", e2);
            Thread.currentThread().interrupt();
        }
    }

    private void a(String str) {
        com.baidu.mapsdkplatform.comapi.synchronization.c.a aVar = this.f3756h;
        if (aVar == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "HttpClient cannot be null");
        } else {
            aVar.a(str, new h(this));
        }
    }

    private void a(String str, SyncResponseResult syncResponseResult) {
        LatLng latLng;
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length == 0) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "There's no section route data");
            return;
        }
        String[] strArrSplit2 = strArrSplit[0].split(AppInfo.DELIM);
        if (2 != strArrSplit2.length) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "Section start position latlng invalid: " + strArrSplit[0]);
            return;
        }
        LatLng latLng2 = null;
        try {
            latLng = new LatLng(Double.valueOf(strArrSplit2[1]).doubleValue(), Double.valueOf(strArrSplit2[0]).doubleValue());
        } catch (NumberFormatException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get startPosition failed", e2);
            latLng = null;
        }
        for (int i = 1; i < strArrSplit.length; i++) {
            RouteLineInfo.RouteSectionInfo routeSectionInfo = new RouteLineInfo.RouteSectionInfo();
            routeSectionInfo.a(latLng);
            String[] strArrSplit3 = strArrSplit[i].split(AppInfo.DELIM);
            if (2 != strArrSplit3.length) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "Section position latlng invalid: " + strArrSplit[i]);
            } else {
                try {
                    latLng2 = new LatLng(Double.valueOf(strArrSplit3[1]).doubleValue(), Double.valueOf(strArrSplit3[0]).doubleValue());
                } catch (NumberFormatException e3) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get endPosition failed", e3);
                }
                routeSectionInfo.b(latLng2);
                syncResponseResult.a().a(routeSectionInfo);
                latLng = latLng2;
            }
        }
    }

    private void a(JSONArray jSONArray, SyncResponseResult syncResponseResult) {
        double dDoubleValue;
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(jSONArray.length() - 1);
        if (jSONObjectOptJSONObject == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "Invalid driver position data");
            return;
        }
        syncResponseResult.c().setTimeStamp(jSONObjectOptJSONObject.optString("t"));
        String strOptString = jSONObjectOptJSONObject.optString("p");
        if (strOptString == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "No position info data");
            return;
        }
        String[] strArrSplit = strOptString.split(";");
        if (strArrSplit.length == 0) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "Position info array is empty");
            return;
        }
        int iIntValue = 0;
        String[] strArrSplit2 = strArrSplit[0].split(AppInfo.DELIM);
        if (2 != strArrSplit2.length) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "Position latlng invalid");
            return;
        }
        LatLng latLng = null;
        try {
            latLng = new LatLng(Double.valueOf(strArrSplit2[1]).doubleValue(), Double.valueOf(strArrSplit2[0]).doubleValue());
        } catch (NumberFormatException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get driver position failed", e2);
        }
        syncResponseResult.c().setPoint(latLng);
        double dDoubleValue2 = 0.0d;
        try {
            dDoubleValue = Double.valueOf(strArrSplit[1]).doubleValue();
        } catch (NumberFormatException e3) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get angle failed", e3);
            dDoubleValue = 0.0d;
        }
        syncResponseResult.c().setAngle(dDoubleValue);
        try {
            dDoubleValue2 = Double.valueOf(strArrSplit[2]).doubleValue();
        } catch (NumberFormatException e4) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get speed failed", e4);
        }
        syncResponseResult.c().setSpeed(dDoubleValue2);
        try {
            iIntValue = Integer.valueOf(strArrSplit[3]).intValue();
        } catch (NumberFormatException e5) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get orderStateInPosition failed", e5);
        }
        syncResponseResult.c().setOrderStateInPosition(iIntValue);
    }

    private void a(JSONObject jSONObject, SyncResponseResult syncResponseResult) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("route");
        if (jSONObjectOptJSONObject != null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "parser route data");
            b(jSONObjectOptJSONObject, syncResponseResult);
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("traffic");
        if (jSONObjectOptJSONObject2 != null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "parser traffic data");
            c(jSONObjectOptJSONObject2, syncResponseResult);
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("positions");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            this.l.c(2004, SynchronizationConstants.LBS_STATUS_MESSAGE_QUERY_TRACK_DRIVER_POSITION_FAILED);
        } else {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "parser driver position data");
            a(jSONArrayOptJSONArray, syncResponseResult);
        }
        String strOptString = jSONObject.optString("run");
        if (!TextUtils.isEmpty(strOptString)) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "parser travelled distance and elapsed time data");
            c(strOptString, syncResponseResult);
        }
        String strOptString2 = jSONObject.optString("remain");
        if (!TextUtils.isEmpty(strOptString2)) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "parser remain distance and estimated time data");
            d(strOptString2, syncResponseResult);
        }
        syncResponseResult.a(jSONObject.optInt("s"));
        syncResponseResult.a(jSONObject.optString("mtime"));
        syncResponseResult.b(jSONObject.optString("ext"));
    }

    private boolean a(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
            k kVar = this.l;
            if (kVar != null) {
                kVar.c(SynchronizationConstants.LBS_ERROR_QUERY_TRACK_ROUTE_FAILED, SynchronizationConstants.LBS_STATUS_MESSAGE_QUERY_TRACK_ROUTE_FAILED);
            }
            return false;
        }
        int iOptInt = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
        String strOptString = jSONObject.optString("message");
        if (iOptInt != 0) {
            k kVar2 = this.l;
            if (kVar2 != null) {
                kVar2.c(iOptInt, strOptString);
            }
            return false;
        }
        k kVar3 = this.l;
        if (kVar3 == null) {
            return true;
        }
        kVar3.b(iOptInt, strOptString);
        return true;
    }

    static /* synthetic */ int b(g gVar) {
        int i = gVar.k;
        gVar.k = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SyncResponseResult b(String str) {
        if (TextUtils.isEmpty(str)) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "Response result is null");
            return null;
        }
        SyncResponseResult syncResponseResult = new SyncResponseResult();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!a(jSONObject)) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "Response result is invalid");
                return null;
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(AeUtil.ROOT_DATA_PATH_OLD_NAME);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() > 0) {
                a(jSONObjectOptJSONObject, syncResponseResult);
                return syncResponseResult;
            }
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "No route and traffic and driver data");
            return null;
        } catch (JSONException unused) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "JSONException happened when parser");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(SyncResponseResult syncResponseResult) {
        float fD = syncResponseResult.d();
        long jE = syncResponseResult.e();
        int i = this.p;
        if (1 == i || 2 == i || 4 == i) {
            if (this.p != 1 && this.m) {
                this.n = 0.0f;
                this.o = 0L;
                jE = 0;
                fD = 0.0f;
            }
            if (0.0f != fD) {
                this.n = fD;
            }
            if (0 != jE) {
                this.o = jE;
            }
        } else {
            this.n = 0.0f;
            this.o = 0L;
        }
        k kVar = this.l;
        if (kVar != null) {
            kVar.a(this.n, this.o);
        }
    }

    private void b(String str, SyncResponseResult syncResponseResult) {
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length == 0) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "There's no section traffic data");
            return;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String str2 : strArrSplit) {
            String[] strArrSplit2 = str2.split(AppInfo.DELIM);
            if (3 != strArrSplit2.length) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "section traffic data is invalid: " + str2);
            } else {
                try {
                    iIntValue = Integer.valueOf(strArrSplit2[0]).intValue();
                    try {
                        iIntValue2 = Integer.valueOf(strArrSplit2[1]).intValue();
                        try {
                            iIntValue3 = Integer.valueOf(strArrSplit2[2]).intValue();
                        } catch (NumberFormatException e2) {
                            e = e2;
                            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get traffic status info failed", e);
                            iIntValue3 = 0;
                        }
                    } catch (NumberFormatException e3) {
                        e = e3;
                        iIntValue2 = 0;
                    }
                } catch (NumberFormatException e4) {
                    e = e4;
                    iIntValue = 0;
                    iIntValue2 = 0;
                }
                for (int i = iIntValue; i < iIntValue + iIntValue2; i++) {
                    arrayList.add(Integer.valueOf(iIntValue3));
                }
                syncResponseResult.b().a(arrayList);
            }
        }
    }

    private void b(JSONObject jSONObject, SyncResponseResult syncResponseResult) {
        syncResponseResult.a().a(jSONObject.optInt("c") != 0);
        this.f3754f = jSONObject.optString("f");
        syncResponseResult.a().a(this.f3754f);
        String strOptString = jSONObject.optString("d");
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        a(strOptString, syncResponseResult);
    }

    private void c(String str, SyncResponseResult syncResponseResult) {
        String[] strArrSplit = str.split(";");
        if (2 != strArrSplit.length) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "The travelled data is null or invalid");
            return;
        }
        float fFloatValue = 0.0f;
        try {
            fFloatValue = Float.valueOf(strArrSplit[0]).floatValue();
        } catch (NumberFormatException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get travelledDistance failed", e2);
        }
        syncResponseResult.a(fFloatValue);
        long jLongValue = 0;
        try {
            jLongValue = Long.valueOf(strArrSplit[1]).longValue();
        } catch (NumberFormatException e3) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get elapsedTime failed", e3);
        }
        syncResponseResult.a(jLongValue);
    }

    private void c(JSONObject jSONObject, SyncResponseResult syncResponseResult) {
        syncResponseResult.b().a(jSONObject.optInt("c") != 0);
        this.f3755g = jSONObject.optString("f");
        syncResponseResult.b().a(this.f3755g);
        String strOptString = jSONObject.optString("d");
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        b(strOptString, syncResponseResult);
    }

    private void d(String str, SyncResponseResult syncResponseResult) {
        String[] strArrSplit = str.split(";");
        if (2 != strArrSplit.length) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3749a, "The remain data is null or invalid: ");
            return;
        }
        float fFloatValue = 0.0f;
        try {
            fFloatValue = Float.valueOf(strArrSplit[0]).floatValue();
        } catch (NumberFormatException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get remainDistance failed", e2);
        }
        syncResponseResult.b(fFloatValue);
        long jLongValue = 0;
        try {
            jLongValue = Long.valueOf(strArrSplit[1]).longValue();
        } catch (NumberFormatException e3) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3749a, "Get estimatedTime failed", e3);
        }
        syncResponseResult.b(jLongValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        i iVar = this.f3753e;
        if (iVar != null) {
            iVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        i iVar = this.f3753e;
        if (iVar != null) {
            iVar.b();
        }
    }

    void a(int i, boolean z) {
        String str;
        String str2;
        this.m = z;
        this.p = i;
        f fVarA = a(i);
        if (fVarA == null) {
            str = f3749a;
            str2 = "Data request option is null";
        } else {
            String strA = new m(fVarA).a();
            if (strA != null) {
                a(strA);
                return;
            } else {
                str = f3749a;
                str2 = "send url string is null";
            }
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(str, str2);
    }

    synchronized void a(View view) {
        if (this.f3751c != null) {
            this.f3751c.setStartPositionInfoWindowView(view);
        } else {
            if (this.l != null) {
                this.l.c(1004, SynchronizationConstants.LBS_STATUS_MESSAGE_DISPLAYOPTIONS_INSTANCE_INVALID);
            }
        }
    }

    synchronized void a(DisplayOptions displayOptions) {
        this.f3751c = displayOptions;
    }

    synchronized void a(RoleOptions roleOptions) {
        this.f3750b = roleOptions;
    }

    public void a(i iVar) {
        this.f3753e = iVar;
    }

    void a(k kVar) {
        this.l = kVar;
    }

    public void b() {
        this.i = new HandlerThread("SyncDataStorage");
        this.i.start();
        this.j = new b(this.i.getLooper());
    }

    synchronized void b(View view) {
        if (this.f3751c != null) {
            this.f3751c.setEndPositionInfoWindowView(view);
        } else {
            if (this.l != null) {
                this.l.c(1004, SynchronizationConstants.LBS_STATUS_MESSAGE_DISPLAYOPTIONS_INSTANCE_INVALID);
            }
        }
    }

    public void c() {
        if (this.f3753e != null) {
            this.f3753e = null;
        }
    }

    synchronized void c(View view) {
        if (this.f3751c != null) {
            this.f3751c.setCarInfoWindowView(view);
        } else {
            if (this.l != null) {
                this.l.c(1004, SynchronizationConstants.LBS_STATUS_MESSAGE_DISPLAYOPTIONS_INSTANCE_INVALID);
            }
        }
    }

    int d() {
        return this.k;
    }

    public RoleOptions e() {
        return this.f3750b;
    }

    public DisplayOptions f() {
        return this.f3751c;
    }

    public BlockingQueue<SyncResponseResult> g() {
        return this.f3752d;
    }

    public void h() {
        this.m = false;
        this.n = 0.0f;
        this.o = 0L;
        this.j.removeCallbacksAndMessages(null);
        this.i.quit();
    }
}