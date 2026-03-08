package com.baidu.mapsdkplatform.comapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.inner.GeoPoint;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class af {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comjni.map.basemap.a f3543a;

    public af(com.baidu.mapsdkplatform.comjni.map.basemap.a aVar) {
        this.f3543a = aVar;
    }

    public Point a(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        Point point = new Point(0, 0);
        String strB = this.f3543a.b((int) geoPoint.getLongitudeE6(), (int) geoPoint.getLatitudeE6());
        if (strB != null && !strB.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(strB);
                point.x = jSONObject.getInt("scrx");
                point.y = jSONObject.getInt("scry");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return point;
    }

    public GeoPoint a(int i, int i2) {
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        String strA = this.f3543a.a(i, i2);
        if (strA != null && !strA.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(strA);
                geoPoint.setLongitudeE6(jSONObject.getInt("geox"));
                geoPoint.setLatitudeE6(jSONObject.getInt("geoy"));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return geoPoint;
    }
}