package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.ab;
import java.util.Iterator;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class c implements com.baidu.mapsdkplatform.comapi.map.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ BaiduMap f3035a;

    c(BaiduMap baiduMap) {
        this.f3035a = baiduMap;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a() {
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(Bitmap bitmap) {
        if (this.f3035a.B != null) {
            this.f3035a.B.onSnapshotReady(bitmap);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(MotionEvent motionEvent) {
        if (this.f3035a.r != null) {
            this.f3035a.r.onTouch(motionEvent);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(GeoPoint geoPoint) {
        if (this.f3035a.s != null) {
            this.f3035a.s.onMapClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(ab abVar) {
        if (!this.f3035a.K.values().isEmpty()) {
            for (InfoWindow infoWindow : this.f3035a.K.values()) {
                if (infoWindow.f2848b != null && !infoWindow.i) {
                    infoWindow.f2848b.setVisibility(4);
                }
            }
        }
        int i = (BaiduMap.mapStatusReason & 256) == 256 ? 3 : (BaiduMap.mapStatusReason & 16) == 16 ? 2 : 1;
        if (this.f3035a.q != null) {
            MapStatus mapStatusA = MapStatus.a(abVar);
            this.f3035a.q.onMapStatusChangeStart(mapStatusA);
            this.f3035a.q.onMapStatusChangeStart(mapStatusA, i);
        }
        if (this.f3035a.F != null) {
            this.f3035a.F.onMapStatusChangeReason(i);
        }
        BaiduMap.mapStatusReason = 0;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(String str) {
        ab abVarE;
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONArray("dataset").optJSONObject(0);
            GeoPoint geoPointB = this.f3035a.i.b(jSONObject.optInt("px"), jSONObject.optInt("py"));
            int iOptInt = jSONObjectOptJSONObject.optInt("ty");
            if (iOptInt == 17) {
                if (this.f3035a.s != null) {
                    MapPoi mapPoi = new MapPoi();
                    mapPoi.a(jSONObjectOptJSONObject);
                    this.f3035a.s.onMapPoiClick(mapPoi);
                    return;
                }
                return;
            }
            if (iOptInt != 18) {
                if (iOptInt == 19) {
                    if (this.f3035a.i == null || (abVarE = this.f3035a.i.E()) == null) {
                        return;
                    }
                    abVarE.f3520c = 0;
                    abVarE.f3519b = 0;
                    BaiduMap.mapStatusReason |= 16;
                    this.f3035a.i.a(abVarE, 300);
                    return;
                }
                if (iOptInt != 90909) {
                    if (iOptInt == 90910) {
                        String strOptString = jSONObjectOptJSONObject.optString("polyline_id");
                        for (Overlay overlay : this.f3035a.k) {
                            if ((overlay instanceof Polyline) && overlay.y.equals(strOptString)) {
                                if (this.f3035a.y.isEmpty()) {
                                    a(geoPointB);
                                } else {
                                    Iterator it = this.f3035a.y.iterator();
                                    while (it.hasNext()) {
                                        ((BaiduMap.OnPolylineClickListener) it.next()).onPolylineClick((Polyline) overlay);
                                    }
                                }
                            }
                        }
                        return;
                    }
                    return;
                }
                String strOptString2 = jSONObjectOptJSONObject.optString("marker_id");
                Set<String> setKeySet = this.f3035a.K.keySet();
                if (!setKeySet.isEmpty() && setKeySet.contains(strOptString2)) {
                    for (String str2 : setKeySet) {
                        if (str2 != null && str2.equals(strOptString2)) {
                            InfoWindow infoWindow = (InfoWindow) this.f3035a.K.get(str2);
                            if (infoWindow != null && infoWindow.f2850d != null) {
                                infoWindow.f2850d.onInfoWindowClick();
                                return;
                            }
                        }
                    }
                    return;
                }
                for (Overlay overlay2 : this.f3035a.k) {
                    if ((overlay2 instanceof Marker) && overlay2.y.equals(strOptString2)) {
                        if (!this.f3035a.x.isEmpty()) {
                            Iterator it2 = this.f3035a.x.iterator();
                            while (it2.hasNext()) {
                                ((BaiduMap.OnMarkerClickListener) it2.next()).onMarkerClick((Marker) overlay2);
                            }
                            return;
                        }
                        a(geoPointB);
                    }
                }
                return;
            }
            if (this.f3035a.A != null) {
                this.f3035a.A.onMyLocationClick();
                return;
            }
            a(geoPointB);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(GL10 gl10, ab abVar) {
        if (this.f3035a.C != null) {
            this.f3035a.C.onMapDrawFrame(MapStatus.a(abVar));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(boolean z) {
        if (this.f3035a.D != null) {
            this.f3035a.D.onBaseIndoorMapMode(z, this.f3035a.getFocusedBaseIndoorMapInfo());
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void a(boolean z, int i) {
        if (this.f3035a.E != null) {
            this.f3035a.E.onMapRenderValidData(z, i, this.f3035a.a(i));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void b() {
        BaiduMap baiduMap = this.f3035a;
        baiduMap.f2776f = new Projection(baiduMap.i);
        this.f3035a.S = true;
        if (this.f3035a.t != null) {
            this.f3035a.t.onMapLoaded();
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void b(GeoPoint geoPoint) {
        if (this.f3035a.v != null) {
            this.f3035a.v.onMapDoubleClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void b(ab abVar) {
        if (this.f3035a.q != null) {
            this.f3035a.q.onMapStatusChange(MapStatus.a(abVar));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public boolean b(String str) {
        try {
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONArray("dataset").optJSONObject(0);
            if (jSONObjectOptJSONObject.optInt("ty") != 90909) {
                return false;
            }
            String strOptString = jSONObjectOptJSONObject.optString("marker_id");
            Set setKeySet = this.f3035a.K.keySet();
            if (!setKeySet.isEmpty() && setKeySet.contains(strOptString)) {
                return false;
            }
            for (Overlay overlay : this.f3035a.k) {
                if ((overlay instanceof Marker) && overlay.y.equals(strOptString)) {
                    Marker marker = (Marker) overlay;
                    if (!marker.f2915f) {
                        return false;
                    }
                    this.f3035a.M = marker;
                    this.f3035a.M.setPosition(this.f3035a.f2776f.fromScreenLocation(new Point(this.f3035a.f2776f.toScreenLocation(this.f3035a.M.f2910a).x, r5.y - 60)));
                    if (this.f3035a.z != null) {
                        this.f3035a.z.onMarkerDragStart(this.f3035a.M);
                    }
                    return true;
                }
            }
            return false;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void c() {
        if (this.f3035a.u != null) {
            this.f3035a.u.onMapRenderFinished();
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void c(GeoPoint geoPoint) {
        if (this.f3035a.w != null) {
            this.f3035a.w.onMapLongClick(CoordUtil.mc2ll(geoPoint));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void c(ab abVar) {
        if (!this.f3035a.K.values().isEmpty()) {
            Iterator it = this.f3035a.K.values().iterator();
            while (it.hasNext()) {
                View view = ((InfoWindow) it.next()).f2848b;
                if (view != null) {
                    view.setVisibility(0);
                }
            }
        }
        if (this.f3035a.q != null) {
            this.f3035a.q.onMapStatusChangeFinish(MapStatus.a(abVar));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void d() {
        this.f3035a.I.lock();
        try {
            if (this.f3035a.H != null) {
                this.f3035a.H.a();
            }
        } finally {
            this.f3035a.I.unlock();
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void d(GeoPoint geoPoint) {
        if (this.f3035a.M == null || !this.f3035a.M.f2915f) {
            return;
        }
        this.f3035a.M.setPosition(this.f3035a.f2776f.fromScreenLocation(new Point(this.f3035a.f2776f.toScreenLocation(CoordUtil.mc2ll(geoPoint)).x, r3.y - 60)));
        if (this.f3035a.z == null || !this.f3035a.M.f2915f) {
            return;
        }
        this.f3035a.z.onMarkerDrag(this.f3035a.M);
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void e() {
        this.f3035a.I.lock();
        try {
            if (this.f3035a.H != null) {
                this.f3035a.H.a();
                this.f3035a.i.o();
            }
        } finally {
            this.f3035a.I.unlock();
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void e(GeoPoint geoPoint) {
        if (this.f3035a.M == null || !this.f3035a.M.f2915f) {
            return;
        }
        this.f3035a.M.setPosition(this.f3035a.f2776f.fromScreenLocation(new Point(this.f3035a.f2776f.toScreenLocation(CoordUtil.mc2ll(geoPoint)).x, r3.y - 60)));
        if (this.f3035a.z != null && this.f3035a.M.f2915f) {
            this.f3035a.z.onMarkerDragEnd(this.f3035a.M);
        }
        this.f3035a.M = null;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.l
    public void f() {
        this.f3035a.i.b(false);
        this.f3035a.I.lock();
        try {
            if (this.f3035a.H != null) {
                this.f3035a.a(this.f3035a.H);
            }
        } finally {
            this.f3035a.I.unlock();
        }
    }
}