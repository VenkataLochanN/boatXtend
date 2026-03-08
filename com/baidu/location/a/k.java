package com.baidu.location.a;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class k {
    private static long j = 12000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f2132a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2133b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private WebView f2134c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LocationClient f2135d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f2136e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<b> f2137f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2138g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f2139h;
    private BDLocation i;
    private f k;
    private boolean l;

    private class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        private String a(BDLocation bDLocation) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("latitude", bDLocation.getLatitude());
                jSONObject.put("longitude", bDLocation.getLongitude());
                jSONObject.put("radius", bDLocation.getRadius());
                jSONObject.put("errorcode", 1);
                if (bDLocation.hasAltitude()) {
                    jSONObject.put("altitude", bDLocation.getAltitude());
                }
                if (bDLocation.hasSpeed()) {
                    jSONObject.put("speed", bDLocation.getSpeed() / 3.6f);
                }
                if (bDLocation.getLocType() == 61) {
                    jSONObject.put("direction", bDLocation.getDirection());
                }
                if (bDLocation.getBuildingName() != null) {
                    jSONObject.put("buildingname", bDLocation.getBuildingName());
                }
                if (bDLocation.getBuildingID() != null) {
                    jSONObject.put("buildingid", bDLocation.getBuildingID());
                }
                if (bDLocation.getFloor() != null) {
                    jSONObject.put("floor", bDLocation.getFloor());
                }
                return jSONObject.toString();
            } catch (Exception unused) {
                return null;
            }
        }

        private void a(String str) {
            if (k.this.l) {
                k.this.f2136e.removeCallbacks(k.this.k);
                k.this.l = false;
            }
            if (k.this.f2137f == null || k.this.f2137f.size() <= 0) {
                return;
            }
            Iterator it = k.this.f2137f.iterator();
            while (it.hasNext()) {
                try {
                    b bVar = (b) it.next();
                    if (bVar.b() != null) {
                        k.this.f2134c.loadUrl(BridgeUtil.JAVASCRIPT_STR + bVar.b() + "('" + str + "')");
                    }
                    it.remove();
                } catch (Exception unused) {
                    return;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0115  */
        /* JADX WARN: Type inference failed for: r4v0, types: [com.baidu.location.a.k$1] */
        /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.String] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r10) {
            /*
                Method dump skipped, instruction units count: 368
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.k.a.handleMessage(android.os.Message):void");
        }
    }

    private class b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2142b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2143c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f2144d;

        b(String str) {
            this.f2142b = null;
            this.f2143c = null;
            this.f2144d = 0L;
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("action")) {
                    this.f2142b = jSONObject.getString("action");
                }
                if (jSONObject.has("callback")) {
                    this.f2143c = jSONObject.getString("callback");
                }
                if (jSONObject.has("timeout")) {
                    long j = jSONObject.getLong("timeout");
                    if (j >= 1000) {
                        long unused = k.j = j;
                    }
                }
                this.f2144d = System.currentTimeMillis();
            } catch (Exception unused2) {
                this.f2142b = null;
                this.f2143c = null;
            }
        }

        public String a() {
            return this.f2142b;
        }

        public String b() {
            return this.f2143c;
        }
    }

    private static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final k f2145a = new k();
    }

    private class d {
        private d() {
        }

        @JavascriptInterface
        public void sendMessage(String str) {
            if (str == null || !k.this.f2138g) {
                return;
            }
            b bVar = k.this.new b(str);
            if (bVar.a() == null || !bVar.a().equals("requestLoc") || k.this.f2136e == null) {
                return;
            }
            Message messageObtainMessage = k.this.f2136e.obtainMessage(1);
            messageObtainMessage.obj = bVar;
            messageObtainMessage.sendToTarget();
        }

        @JavascriptInterface
        public void showLog(String str) {
        }
    }

    public class e extends BDAbstractLocationListener {
        public e() {
        }

        @Override // com.baidu.location.BDAbstractLocationListener
        public void onReceiveLocation(BDLocation bDLocation) {
            Message messageObtainMessage;
            String str;
            if (!k.this.f2138g || bDLocation == null) {
                return;
            }
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            int locType = bDLocation2.getLocType();
            String coorType = bDLocation2.getCoorType();
            if (locType == 61 || locType == 161 || locType == 66) {
                if (coorType != null) {
                    if (coorType.equals(CoordinateType.GCJ02)) {
                        bDLocation2 = LocationClient.getBDLocationInCoorType(bDLocation2, "gcj2wgs");
                    } else {
                        if (coorType.equals(BDLocation.BDLOCATION_GCJ02_TO_BD09)) {
                            str = BDLocation.BDLOCATION_BD09_TO_GCJ02;
                        } else if (coorType.equals("bd09ll")) {
                            str = BDLocation.BDLOCATION_BD09LL_TO_GCJ02;
                        }
                        bDLocation2 = LocationClient.getBDLocationInCoorType(LocationClient.getBDLocationInCoorType(bDLocation2, str), "gcj2wgs");
                    }
                }
                k.this.f2139h = System.currentTimeMillis();
                k.this.i = new BDLocation(bDLocation2);
                messageObtainMessage = k.this.f2136e.obtainMessage(2);
                messageObtainMessage.obj = bDLocation2;
            } else {
                messageObtainMessage = k.this.f2136e.obtainMessage(5);
            }
            messageObtainMessage.sendToTarget();
        }
    }

    private class f implements Runnable {
        private f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k.this.l = false;
            k.this.f2136e.obtainMessage(6).sendToTarget();
        }
    }

    private k() {
        this.f2133b = null;
        this.f2135d = null;
        this.f2132a = new e();
        this.f2136e = null;
        this.f2137f = null;
        this.f2138g = false;
        this.f2139h = 0L;
        this.i = null;
        this.k = null;
        this.l = false;
    }

    public static k a() {
        return c.f2145a;
    }

    private void a(WebView webView) {
        webView.addJavascriptInterface(new d(), "BaiduLocAssistant");
    }

    public void a(Context context, WebView webView, LocationClient locationClient) {
        if (!this.f2138g && Integer.valueOf(Build.VERSION.SDK_INT).intValue() >= 17) {
            this.f2133b = context;
            this.f2134c = webView;
            this.f2135d = locationClient;
            this.f2136e = new a(Looper.getMainLooper());
            this.f2136e.obtainMessage(3).sendToTarget();
            webView.getSettings().setJavaScriptEnabled(true);
            a(this.f2134c);
            this.f2138g = true;
        }
    }

    public void b() {
        if (this.f2138g) {
            this.f2136e.obtainMessage(4).sendToTarget();
            this.f2138g = false;
        }
    }
}