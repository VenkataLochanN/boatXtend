package com.baidu.mapsdkplatform.comapi.synchronization.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceConstant;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceData;
import com.baidu.mapapi.synchronization.histroytrace.HistoryTraceDisplayOptions;
import com.baidu.mapapi.synchronization.histroytrace.OnHistoryTraceListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
class e extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3666a = e.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static OnHistoryTraceListener f3667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HistoryTraceDisplayOptions f3668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BaiduMap f3669d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3670e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Marker f3671f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Marker f3672g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Marker f3673h;
    private List<Polyline> i;

    e(Looper looper) {
        super(looper);
        this.i = new CopyOnWriteArrayList();
    }

    private void a(int i, String str) {
        OnHistoryTraceListener onHistoryTraceListener = f3667b;
        if (onHistoryTraceListener == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "OnHistoryTraceListener is null");
        } else {
            onHistoryTraceListener.onRenderHistroyTrace(i, str);
        }
    }

    private void a(BitmapDescriptor bitmapDescriptor, int i, List<HistoryTraceData.HistoryTracePoint> list) {
        List<List<LatLng>> listB = b(list);
        if (listB == null || listB.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Calculate sub section points error");
            return;
        }
        b();
        for (int i2 = 0; i2 < listB.size(); i2++) {
            List<LatLng> list2 = listB.get(i2);
            if (list2 != null && !list2.isEmpty()) {
                if (list2.size() < 2) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Error points list, index = " + i2);
                } else {
                    a((Polyline) this.f3669d.addOverlay(new PolylineOptions().width(i).points(list2).dottedLine(true).customTexture(bitmapDescriptor).zIndex(4)));
                }
            }
        }
    }

    private void a(Polyline polyline) {
        this.i.add(polyline);
    }

    private void a(LatLng latLng) {
        if (!this.f3668c.isShowStartPositionIcon()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "User set not render start point marker");
            return;
        }
        if (latLng == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Start point is null");
            a(100020, "History trace end point is null, can't render start point marker");
            return;
        }
        BitmapDescriptor startPositionIcon = this.f3668c.getStartPositionIcon();
        if (startPositionIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "There is no startPositionIcon");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_START_POINT_ICON_NULL, "History trace end point icon is null, can't render start point marker");
        } else {
            this.f3671f = (Marker) this.f3669d.addOverlay(new MarkerOptions().position(latLng).icon(startPositionIcon).zIndex(5));
        }
    }

    private void a(HistoryTraceData historyTraceData) {
        if (5 != this.f3670e) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Current order state not the complete state, render forbidden");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_CURRENT_ORDER_STATE_NOT_COMPLETE, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_CURRENT_ORDER_STATE_NOT_COMPLETE);
            return;
        }
        BaiduMap baiduMap = this.f3669d;
        if (baiduMap == null) {
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_BAIDUMAP_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_BAIDUMAP_NULL);
            return;
        }
        baiduMap.clear();
        if (this.f3668c == null) {
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_DISPLAY_OPTIONS_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_DISPLAY_OPTIONS_NULL);
            this.f3668c = new HistoryTraceDisplayOptions();
        }
        a(historyTraceData.getOrderStartPosition());
        b(historyTraceData.getOrderEndPosition());
        List<HistoryTraceData.HistoryTracePoint> pointsList = historyTraceData.getPointsList();
        if (pointsList != null && !pointsList.isEmpty()) {
            c(pointsList.get(0).getPoint());
        }
        a(pointsList);
        c();
    }

    private void a(List<HistoryTraceData.HistoryTracePoint> list) {
        if (!this.f3668c.isShowRoutePlan()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "User set not render route polyline");
            return;
        }
        if (list == null || list.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "There is no points data");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_POINTS_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_POINTS_NULL);
            return;
        }
        if (list.size() < 2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "History trace points less than 2, can't render polyline");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_POINTS_LESS, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_POINTS_LESS);
            return;
        }
        BitmapDescriptor routeLineTexture = this.f3668c.getRouteLineTexture();
        if (routeLineTexture == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Route polyline texture is null");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_ROUTE_TEXTURE_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_ROUTE_TEXTURE_NULL);
            return;
        }
        int routeLineWidth = this.f3668c.getRouteLineWidth();
        if (this.f3668c.isRouteLineRenderBySubSection()) {
            b(routeLineTexture, routeLineWidth, list);
        } else {
            a(routeLineTexture, routeLineWidth, list);
        }
    }

    private List<List<LatLng>> b(List<HistoryTraceData.HistoryTracePoint> list) {
        String str;
        String str2;
        HistoryTraceData.HistoryTracePoint historyTracePoint;
        if (list == null || list.isEmpty()) {
            str = f3666a;
            str2 = "History trace point list is null";
        } else {
            if (list.size() >= 2) {
                CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < list.size() - 1; i++) {
                    HistoryTraceData.HistoryTracePoint historyTracePoint2 = list.get(i);
                    if (historyTracePoint2 != null && (historyTracePoint = list.get(i + 1)) != null) {
                        arrayList.add(historyTracePoint2.getPoint());
                        if (historyTracePoint.getLocationTime() - historyTracePoint2.getLocationTime() > 300) {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.addAll(arrayList);
                            copyOnWriteArrayList.add(arrayList2);
                            arrayList.clear();
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    arrayList.add(list.get(list.size() - 1).getPoint());
                    copyOnWriteArrayList.add(arrayList);
                }
                return copyOnWriteArrayList;
            }
            str = f3666a;
            str2 = "History trace point list size is less than 2, can't render polyline";
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(str, str2);
        return null;
    }

    private void b() {
        List<Polyline> list = this.i;
        if (list != null && !list.isEmpty()) {
            this.i.clear();
        }
        if (this.i == null) {
            this.i = new CopyOnWriteArrayList();
        }
    }

    private void b(BitmapDescriptor bitmapDescriptor, int i, List<HistoryTraceData.HistoryTracePoint> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                arrayList.add(list.get(i2).getPoint());
            }
        }
        Polyline polyline = (Polyline) this.f3669d.addOverlay(new PolylineOptions().width(i).points(arrayList).dottedLine(true).customTexture(bitmapDescriptor).zIndex(4));
        b();
        a(polyline);
    }

    private void b(LatLng latLng) {
        if (!this.f3668c.isShowEndPositionIcon()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "User set not render end point marker");
            return;
        }
        if (latLng == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "End point is null");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_END_POINT_NULL, "History trace end point is null, can't render start point marker");
            return;
        }
        BitmapDescriptor endPositionIcon = this.f3668c.getEndPositionIcon();
        if (endPositionIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "There is no endPositionIcon");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_END_POINT_ICON_NULL, "History trace end point icon is null, can't render start point marker");
        } else {
            this.f3672g = (Marker) this.f3669d.addOverlay(new MarkerOptions().position(latLng).icon(endPositionIcon).zIndex(5));
        }
    }

    private void c() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Marker marker = this.f3671f;
        if (marker != null) {
            builder.include(marker.getPosition());
        }
        Marker marker2 = this.f3672g;
        if (marker2 != null) {
            builder.include(marker2.getPosition());
        }
        Marker marker3 = this.f3673h;
        if (marker3 != null) {
            builder.include(marker3.getPosition());
        }
        List<Polyline> list = this.i;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < this.i.size(); i++) {
                Polyline polyline = this.i.get(i);
                if (polyline != null && polyline.getPoints() != null && !polyline.getPoints().isEmpty()) {
                    for (int i2 = 0; i2 < polyline.getPoints().size(); i2++) {
                        builder.include(polyline.getPoints().get(i2));
                    }
                }
            }
        }
        LatLngBounds latLngBoundsBuild = builder.build();
        if (latLngBoundsBuild == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Visibility span is null");
            return;
        }
        this.f3669d.animateMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBoundsBuild, this.f3668c.getPaddingLeft(), this.f3668c.getPaddingTop(), this.f3668c.getPaddingRight(), this.f3668c.getPaddingBottom()));
    }

    private void c(LatLng latLng) {
        if (!this.f3668c.isShowCarIcon()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "User set not render car marker");
            return;
        }
        if (latLng == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Car point is null");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_CAR_POINT_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_CAR_POINT_NULL);
            return;
        }
        BitmapDescriptor carIcon = this.f3668c.getCarIcon();
        if (carIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Car icon is null");
            a(HistoryTraceConstant.LBS_HISTORY_TRACE_CODE_CAR_POINT_ICON_NULL, HistoryTraceConstant.LBS_HISTORY_TRACE_MESSAGE_CAR_POINT_ICON_NULL);
        } else {
            this.f3673h = (Marker) this.f3669d.addOverlay(new MarkerOptions().position(latLng).icon(carIcon).flat(true).rotate(0.0f).zIndex(5).anchor(0.5f, 0.5f));
        }
    }

    public void a() {
        f3667b = null;
        Marker marker = this.f3671f;
        if (marker != null) {
            marker.remove();
            this.f3671f = null;
        }
        Marker marker2 = this.f3672g;
        if (marker2 != null) {
            marker2.remove();
            this.f3672g = null;
        }
        Marker marker3 = this.f3673h;
        if (marker3 != null) {
            marker3.remove();
            this.f3673h = null;
        }
        List<Polyline> list = this.i;
        if (list != null && !list.isEmpty()) {
            this.i.clear();
            this.i = null;
        }
        HistoryTraceDisplayOptions historyTraceDisplayOptions = this.f3668c;
        if (historyTraceDisplayOptions != null) {
            historyTraceDisplayOptions.getCarIcon().recycle();
            this.f3668c.getStartPositionIcon().recycle();
            this.f3668c.getEndPositionIcon().recycle();
            this.f3668c.getRouteLineTexture().recycle();
            this.f3668c = null;
        }
        BaiduMap baiduMap = this.f3669d;
        if (baiduMap != null) {
            baiduMap.clear();
        }
        removeCallbacksAndMessages(null);
    }

    public void a(HistoryTraceDisplayOptions historyTraceDisplayOptions, BaiduMap baiduMap, int i) {
        this.f3668c = historyTraceDisplayOptions;
        this.f3669d = baiduMap;
        this.f3670e = i;
    }

    public void a(OnHistoryTraceListener onHistoryTraceListener) {
        f3667b = onHistoryTraceListener;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Render message type = " + message.what);
        if (message.what != 4) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3666a, "Undefine Render message");
        } else {
            a((HistoryTraceData) message.obj);
        }
    }
}