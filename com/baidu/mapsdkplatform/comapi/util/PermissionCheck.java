package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.map.device.token.Token;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PermissionCheck {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Context f3814e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f3815f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static Hashtable<String, String> f3816g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3813d = PermissionCheck.class.getSimpleName();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static LBSAuthManager f3817h = null;
    private static LBSAuthManagerListener i = null;
    private static c j = null;
    private static int k = 601;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3810a = 200;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f3811b = com.veryfit.multi.nativeprotocol.b.G0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f3812c = 252;

    private static class a implements LBSAuthManagerListener {
        private a() {
        }

        @Override // com.baidu.lbsapi.auth.LBSAuthManagerListener
        public void onAuthResult(int i, String str) {
            if (str == null) {
                Log.e(PermissionCheck.f3813d, "The result is null");
                int iPermissionCheck = PermissionCheck.permissionCheck();
                Log.d(PermissionCheck.f3813d, "onAuthResult try permissionCheck result is: " + iPermissionCheck);
                return;
            }
            b bVar = new b();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                    bVar.f3818a = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
                }
                if (jSONObject.has("appid")) {
                    bVar.f3820c = jSONObject.optString("appid");
                }
                if (jSONObject.has("uid")) {
                    bVar.f3819b = jSONObject.optString("uid");
                }
                if (jSONObject.has("message")) {
                    bVar.f3821d = jSONObject.optString("message");
                }
                if (jSONObject.has(Token.KEY_TOKEN)) {
                    bVar.f3822e = jSONObject.optString(Token.KEY_TOKEN);
                }
                if (jSONObject.has("ak_permission")) {
                    bVar.f3823f = jSONObject.optInt("ak_permission");
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            int unused = PermissionCheck.k = bVar.f3818a;
            if (PermissionCheck.j != null) {
                PermissionCheck.j.a(bVar);
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3818a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3819b = "-1";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3820c = "-1";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3821d = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3822e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3823f;

        public String toString() {
            return String.format("=============================================\n----------------- 鉴权错误信息 ------------\nsha1;package:%s\nkey:%s\nerrorcode: %d uid: %s appid %s msg: %s\n请仔细核查 SHA1、package与key申请信息是否对应，key是否删除，平台是否匹配\nerrorcode为230时，请参考论坛链接：\nhttp://bbs.lbsyun.baidu.com/forum.php?mod=viewthread&tid=106461\n=============================================\n", com.baidu.mapsdkplatform.comapi.util.a.a(PermissionCheck.f3814e), PermissionCheck.f3815f, Integer.valueOf(this.f3818a), this.f3819b, this.f3820c, this.f3821d);
        }
    }

    public interface c {
        void a(b bVar);
    }

    public static void destory() {
        j = null;
        f3814e = null;
        i = null;
    }

    public static int getPermissionResult() {
        return k;
    }

    public static void init(Context context) {
        ApplicationInfo applicationInfo;
        String string;
        f3814e = context;
        try {
            applicationInfo = f3814e.getPackageManager().getApplicationInfo(f3814e.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            f3815f = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
        }
        if (f3816g == null) {
            f3816g = new Hashtable<>();
        }
        if (f3817h == null) {
            f3817h = LBSAuthManager.getInstance(f3814e);
        }
        if (i == null) {
            i = new a();
        }
        try {
            string = context.getPackageManager().getPackageInfo(f3814e.getPackageName(), 0).applicationInfo.loadLabel(f3814e.getPackageManager()).toString();
        } catch (Exception e3) {
            e3.printStackTrace();
            string = "";
        }
        Bundle bundleB = i.b();
        if (bundleB != null) {
            f3816g.put("mb", bundleB.getString("mb"));
            f3816g.put("os", bundleB.getString("os"));
            f3816g.put("sv", bundleB.getString("sv"));
            f3816g.put("imt", "1");
            f3816g.put("net", bundleB.getString("net"));
            f3816g.put("cpu", bundleB.getString("cpu"));
            f3816g.put("glr", bundleB.getString("glr"));
            f3816g.put("glv", bundleB.getString("glv"));
            f3816g.put("resid", bundleB.getString("resid"));
            f3816g.put("appid", "-1");
            f3816g.put("ver", "1");
            f3816g.put("screen", String.format("(%d,%d)", Integer.valueOf(bundleB.getInt("screen_x")), Integer.valueOf(bundleB.getInt("screen_y"))));
            f3816g.put("dpi", String.format("(%d,%d)", Integer.valueOf(bundleB.getInt("dpi_x")), Integer.valueOf(bundleB.getInt("dpi_y"))));
            f3816g.put("pcn", bundleB.getString("pcn"));
            f3816g.put("cuid", bundleB.getString("cuid"));
            f3816g.put(AppMeasurementSdk.ConditionalUserProperty.NAME, string);
        }
    }

    public static synchronized int permissionCheck() {
        if (f3817h != null && i != null && f3814e != null) {
            int iAuthenticate = f3817h.authenticate(false, "lbs_androidmapsdk", f3816g, i);
            if (iAuthenticate != 0) {
                Log.e(f3813d, "permission check result is: " + iAuthenticate);
            }
            return iAuthenticate;
        }
        Log.e(f3813d, "The authManager is: " + f3817h + "; the authCallback is: " + i + "; the mContext is: " + f3814e);
        return 0;
    }

    public static void setPermissionCheckResultListener(c cVar) {
        j = cVar;
    }
}