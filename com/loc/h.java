package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.MyLocationStyle;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.ido.life.module.user.country.CountryChooseActivity;
import org.json.JSONObject;

/* JADX INFO: compiled from: H5LocationClient.java */
/* JADX INFO: loaded from: classes3.dex */
public final class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    a f5211c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f5212d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private WebView f5214f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Object f5209a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private AMapLocationClient f5213e = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5215g = "AMap.Geolocation.cbk";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    AMapLocationClientOption f5210b = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private volatile boolean f5216h = false;

    /* JADX INFO: compiled from: H5LocationClient.java */
    class a implements AMapLocationListener {
        a() {
        }

        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            if (h.this.f5216h) {
                h.a(h.this, h.b(aMapLocation));
            }
        }
    }

    public h(Context context, WebView webView) {
        this.f5214f = null;
        this.f5211c = null;
        this.f5212d = context.getApplicationContext();
        this.f5214f = webView;
        this.f5211c = new a();
    }

    static /* synthetic */ void a(h hVar, final String str) {
        try {
            if (hVar.f5214f != null) {
                if (Build.VERSION.SDK_INT < 19) {
                    hVar.f5214f.post(new Runnable() { // from class: com.loc.h.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            h.this.f5214f.loadUrl(BridgeUtil.JAVASCRIPT_STR + h.this.f5215g + "('" + str + "')");
                        }
                    });
                    return;
                }
                hVar.f5214f.evaluateJavascript(BridgeUtil.JAVASCRIPT_STR + hVar.f5215g + "('" + str + "')", new ValueCallback<String>() { // from class: com.loc.h.1
                    @Override // android.webkit.ValueCallback
                    public final /* bridge */ /* synthetic */ void onReceiveValue(String str2) {
                    }
                });
            }
        } catch (Throwable th) {
            ej.a(th, "H5LocationClient", "callbackJs()");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(AMapLocation aMapLocation) {
        String locationDetail;
        JSONObject jSONObject = new JSONObject();
        String str = MyLocationStyle.ERROR_INFO;
        if (aMapLocation == null) {
            jSONObject.put(MyLocationStyle.ERROR_CODE, -1);
            locationDetail = "unknownError";
        } else {
            if (aMapLocation.getErrorCode() == 0) {
                jSONObject.put(MyLocationStyle.ERROR_CODE, 0);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", aMapLocation.getLongitude());
                jSONObject2.put("y", aMapLocation.getLatitude());
                jSONObject2.put("precision", aMapLocation.getAccuracy());
                jSONObject2.put("type", aMapLocation.getLocationType());
                jSONObject2.put(CountryChooseActivity.COUNTRY, aMapLocation.getCountry());
                jSONObject2.put("province", aMapLocation.getProvince());
                jSONObject2.put("city", aMapLocation.getCity());
                jSONObject2.put("cityCode", aMapLocation.getCityCode());
                jSONObject2.put("district", aMapLocation.getDistrict());
                jSONObject2.put("adCode", aMapLocation.getAdCode());
                jSONObject2.put("street", aMapLocation.getStreet());
                jSONObject2.put("streetNum", aMapLocation.getStreetNum());
                jSONObject2.put("floor", aMapLocation.getFloor());
                jSONObject2.put("address", aMapLocation.getAddress());
                jSONObject.put("result", jSONObject2);
                return jSONObject.toString();
            }
            jSONObject.put(MyLocationStyle.ERROR_CODE, aMapLocation.getErrorCode());
            jSONObject.put(MyLocationStyle.ERROR_INFO, aMapLocation.getErrorInfo());
            str = "locationDetail";
            locationDetail = aMapLocation.getLocationDetail();
        }
        jSONObject.put(str, locationDetail);
        return jSONObject.toString();
    }

    public final void a() {
        if (this.f5214f == null || this.f5212d == null || Build.VERSION.SDK_INT < 17 || this.f5216h) {
            return;
        }
        try {
            this.f5214f.getSettings().setJavaScriptEnabled(true);
            this.f5214f.addJavascriptInterface(this, "AMapAndroidLoc");
            if (!TextUtils.isEmpty(this.f5214f.getUrl())) {
                this.f5214f.reload();
            }
            if (this.f5213e == null) {
                this.f5213e = new AMapLocationClient(this.f5212d);
                this.f5213e.setLocationListener(this.f5211c);
            }
            this.f5216h = true;
        } catch (Throwable unused) {
        }
    }

    public final void b() {
        synchronized (this.f5209a) {
            this.f5216h = false;
            if (this.f5213e != null) {
                this.f5213e.unRegisterLocationListener(this.f5211c);
                this.f5213e.stopLocation();
                this.f5213e.onDestroy();
                this.f5213e = null;
            }
            this.f5210b = null;
        }
    }

    @JavascriptInterface
    public final void getLocation(String str) {
        boolean z;
        boolean z2;
        AMapLocationClientOption aMapLocationClientOption;
        AMapLocationClientOption.AMapLocationMode aMapLocationMode;
        synchronized (this.f5209a) {
            if (this.f5216h) {
                if (this.f5210b == null) {
                    this.f5210b = new AMapLocationClientOption();
                }
                int iOptInt = 5;
                long jOptLong = 30000;
                boolean z3 = true;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jOptLong = jSONObject.optLong("to", 30000L);
                    z = jSONObject.optInt("useGPS", 1) == 1;
                    try {
                        z2 = jSONObject.optInt("watch", 0) == 1;
                        try {
                            iOptInt = jSONObject.optInt("interval", 5);
                            String strOptString = jSONObject.optString("callback", null);
                            if (TextUtils.isEmpty(strOptString)) {
                                strOptString = "AMap.Geolocation.cbk";
                            }
                            this.f5215g = strOptString;
                        } catch (Throwable unused) {
                        }
                    } catch (Throwable unused2) {
                        z2 = false;
                    }
                } catch (Throwable unused3) {
                    z = false;
                    z2 = false;
                }
                try {
                    this.f5210b.setHttpTimeOut(jOptLong);
                    if (z) {
                        aMapLocationClientOption = this.f5210b;
                        aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
                    } else {
                        aMapLocationClientOption = this.f5210b;
                        aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Battery_Saving;
                    }
                    aMapLocationClientOption.setLocationMode(aMapLocationMode);
                    AMapLocationClientOption aMapLocationClientOption2 = this.f5210b;
                    if (z2) {
                        z3 = false;
                    }
                    aMapLocationClientOption2.setOnceLocation(z3);
                    if (z2) {
                        this.f5210b.setInterval(iOptInt * 1000);
                    }
                } catch (Throwable unused4) {
                }
                if (this.f5213e != null) {
                    this.f5213e.setLocationOption(this.f5210b);
                    this.f5213e.stopLocation();
                    this.f5213e.startLocation();
                }
            }
        }
    }

    @JavascriptInterface
    public final void stopLocation() {
        AMapLocationClient aMapLocationClient;
        if (this.f5216h && (aMapLocationClient = this.f5213e) != null) {
            aMapLocationClient.stopLocation();
        }
    }
}