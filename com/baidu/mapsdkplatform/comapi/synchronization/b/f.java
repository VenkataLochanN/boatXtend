package com.baidu.mapsdkplatform.comapi.synchronization.b;

import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.map.device.token.Token;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceQueryOptions;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3674a = f.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f3675c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f3676d = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.d.d f3677b = new com.baidu.mapsdkplatform.comapi.synchronization.d.d();

    public f(HistoryTraceQueryOptions historyTraceQueryOptions) {
        a(historyTraceQueryOptions);
    }

    public static void a(int i) {
        f3676d = i;
    }

    private void a(HistoryTraceQueryOptions historyTraceQueryOptions) {
        this.f3677b.a("order_id", b(historyTraceQueryOptions));
        this.f3677b.a("original_order_id", historyTraceQueryOptions.getOrderId().toLowerCase());
        this.f3677b.a("company", historyTraceQueryOptions.getUserId());
        this.f3677b.a("order_attr", historyTraceQueryOptions.getDriverId());
        this.f3677b.a("track_status", String.valueOf(historyTraceQueryOptions.getQueryOrderState()));
        this.f3677b.a(NotificationCompat.CATEGORY_STATUS, String.valueOf(historyTraceQueryOptions.getCurrentOrderState()));
        if (CoordType.BD09LL != SDKInitializer.getCoordType() && CoordType.GCJ02 == SDKInitializer.getCoordType()) {
            this.f3677b.a("coord_type", CoordinateType.GCJ02);
        } else {
            this.f3677b.a("coord_type", "bd09ll");
        }
        this.f3677b.a("page_index", String.valueOf(f3676d));
        f3676d = 1;
        this.f3677b.a("page_size", "5000");
        this.f3677b.a("is_processed", "1");
        b();
    }

    private String b(HistoryTraceQueryOptions historyTraceQueryOptions) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(historyTraceQueryOptions.getUserId().toLowerCase());
        stringBuffer.append("-");
        stringBuffer.append(historyTraceQueryOptions.getOrderId().toLowerCase());
        stringBuffer.append("-");
        stringBuffer.append("9sc87244121ip32590fq234mn6641tx7".toLowerCase());
        String strA = com.baidu.mapsdkplatform.comapi.synchronization.d.c.a(stringBuffer.toString());
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3674a, "The orderId = " + stringBuffer.toString() + "; result = " + strA);
        return strA;
    }

    private void b() {
        String authToken = SyncSysInfo.getAuthToken();
        if (authToken == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3674a, "Token is null, permission check again");
            int iPermissionCheck = PermissionCheck.permissionCheck();
            if (iPermissionCheck != 0) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3674a, "Permission check result is: " + iPermissionCheck);
                return;
            }
            authToken = SyncSysInfo.getAuthToken();
        }
        this.f3677b.a(Token.KEY_TOKEN, authToken);
    }

    private String c() {
        return f3675c ? g.a() : g.b();
    }

    public String a() {
        StringBuffer stringBuffer = new StringBuffer(this.f3677b.a());
        stringBuffer.append(SyncSysInfo.getPhoneInfo());
        String signMD5String = AppMD5.getSignMD5String(stringBuffer.toString());
        stringBuffer.append("&sign=");
        stringBuffer.append(signMD5String);
        StringBuffer stringBuffer2 = new StringBuffer(c());
        stringBuffer2.append("?");
        stringBuffer2.append(stringBuffer);
        return stringBuffer2.toString();
    }
}