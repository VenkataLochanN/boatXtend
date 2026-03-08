package com.baidu.platform.base;

import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.token.Token;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3904b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3905c = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.baidu.platform.util.a f3903a = new com.baidu.platform.util.a();

    public String a() {
        String strA = a(com.baidu.platform.domain.d.a());
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            Log.e("SearchRequest", "toUrlString get authtoken failed");
            int iPermissionCheck = PermissionCheck.permissionCheck();
            if (iPermissionCheck != 0) {
                Log.e("SearchRequest", "try permissionCheck result is: " + iPermissionCheck);
                return null;
            }
            authToken = HttpClient.getAuthToken();
        }
        if (this.f3904b) {
            this.f3903a.a(Token.KEY_TOKEN, authToken);
        }
        String str = this.f3903a.a() + HttpClient.getPhoneInfo();
        if (this.f3905c) {
            str = str + "&sign=" + AppMD5.getSignMD5String(str);
        }
        return strA + "?" + str;
    }

    protected final String a(PlanNode planNode) {
        StringBuilder sb;
        if (planNode == null) {
            return null;
        }
        String str = new String("{");
        LatLng location = planNode.getLocation();
        if (location != null) {
            String str2 = str + "\"type\":1,";
            Point pointLl2point = CoordUtil.ll2point(location);
            sb = new StringBuilder();
            sb.append(str2);
            sb.append("\"xy\":\"");
            sb.append(pointLl2point.x);
            sb.append(AppInfo.DELIM);
            sb.append(pointLl2point.y);
        } else {
            if (planNode.getName() == null) {
                return str;
            }
            sb = new StringBuilder();
            sb.append(str + "\"type\":2,");
            sb.append("\"keyword\":\"");
            sb.append(planNode.getName());
        }
        sb.append("\"}");
        return sb.toString();
    }

    public abstract String a(com.baidu.platform.domain.c cVar);

    public void a(boolean z) {
        this.f3905c = z;
    }

    public void b(boolean z) {
        this.f3904b = z;
    }
}