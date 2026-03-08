package com.baidu.mapsdkplatform.comapi.synchronization.data;

import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.map.device.token.Token;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.baidu.mapsdkplatform.comapi.synchronization.data.f;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3762a = m.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f3763e = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.d.d f3764b = new com.baidu.mapsdkplatform.comapi.synchronization.d.d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3765c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3766d = true;

    public m(f fVar) {
        a(fVar);
    }

    private void a(f fVar) {
        this.f3764b.a("order_id", b(fVar));
        this.f3764b.a("company", fVar.c());
        this.f3764b.a("order_attr", fVar.b());
        this.f3764b.a(NotificationCompat.CATEGORY_STATUS, String.valueOf(fVar.h()));
        this.f3764b.a("pull_type", String.valueOf(fVar.i()));
        this.f3764b.a("route_finger", fVar.d());
        this.f3764b.a("traffic_finger", fVar.e());
        this.f3764b.a("pos_num", String.valueOf(fVar.j()));
        c(fVar);
        d(fVar);
        if (this.f3765c) {
            b();
        }
    }

    private String b(f fVar) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(fVar.c().toLowerCase());
        stringBuffer.append("-");
        stringBuffer.append(fVar.a().toLowerCase());
        stringBuffer.append("-");
        stringBuffer.append("9sc87244121ip32590fq234mn6641tx7".toLowerCase());
        String strA = com.baidu.mapsdkplatform.comapi.synchronization.d.c.a(stringBuffer.toString());
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3762a, "The orderId = " + stringBuffer.toString() + "; result = " + strA);
        return strA;
    }

    private void b() {
        String authToken = SyncSysInfo.getAuthToken();
        if (authToken == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3762a, "Token is null, permission check again");
            int iPermissionCheck = PermissionCheck.permissionCheck();
            if (iPermissionCheck != 0) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3762a, "Permission check result is: " + iPermissionCheck);
            }
            authToken = SyncSysInfo.getAuthToken();
        }
        this.f3764b.a(Token.KEY_TOKEN, authToken);
    }

    private String c() {
        return f3763e ? com.baidu.mapsdkplatform.comapi.synchronization.c.f.a() : com.baidu.mapsdkplatform.comapi.synchronization.c.f.b();
    }

    private void c(f fVar) {
        f.b bVarG = fVar.g();
        if (f.b.DRIVING != bVarG && f.b.RIDING == bVarG) {
            this.f3764b.a("trip_mode", "riding");
        } else {
            this.f3764b.a("trip_mode", "driving");
        }
    }

    private void d(f fVar) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.d dVar;
        String str;
        f.a aVarF = fVar.f();
        if (f.a.BD09LL != aVarF) {
            if (f.a.BD09MC == aVarF) {
                dVar = this.f3764b;
                str = CoordinateType.BD09MC;
            } else if (f.a.GPS == aVarF) {
                dVar = this.f3764b;
                str = CoordinateType.WGS84;
            } else if (f.a.COMMON == aVarF) {
                dVar = this.f3764b;
                str = CoordinateType.GCJ02;
            }
            dVar.a("coord_type", str);
            return;
        }
        this.f3764b.a("coord_type", "bd09ll");
    }

    String a() {
        StringBuffer stringBuffer = new StringBuffer(this.f3764b.a());
        stringBuffer.append(SyncSysInfo.getPhoneInfo());
        if (this.f3766d) {
            String signMD5String = AppMD5.getSignMD5String(stringBuffer.toString());
            stringBuffer.append("&sign=");
            stringBuffer.append(signMD5String);
        }
        StringBuffer stringBuffer2 = new StringBuffer(c());
        stringBuffer2.append("?");
        stringBuffer2.append(stringBuffer);
        return stringBuffer2.toString();
    }
}