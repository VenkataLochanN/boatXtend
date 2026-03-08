package com.amap.api.maps.interfaces;

import android.content.Context;
import android.os.RemoteException;
import com.amap.api.mapcore.util.de;
import com.amap.api.mapcore.util.x;
import com.amap.api.maps.model.BaseOptions;
import com.amap.api.maps.model.BaseOverlay;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: loaded from: classes.dex */
public interface IGlOverlayLayer {
    boolean IsCircleContainPoint(CircleOptions circleOptions, LatLng latLng);

    boolean IsPolygonContainsPoint(PolygonOptions polygonOptions, LatLng latLng);

    BaseOverlay addOverlayObject(String str, BaseOverlay baseOverlay, BaseOptions baseOptions);

    void addRecycleTextureIds(x xVar);

    void changeOverlayIndex();

    void clear(String str);

    String createId(String str);

    void destroy();

    void draw(boolean z, int i);

    int getCurrentParticleNum(String str);

    float[] getFinalMatrix();

    de getGLShaderManager();

    Polyline getHitOverlay(LatLng latLng);

    IAMapDelegate getMap();

    Object getNativeProperties(String str, String str2, Object[] objArr);

    LatLng getNearestLatLng(PolylineOptions polylineOptions, LatLng latLng);

    x getTextureItem(BitmapDescriptor bitmapDescriptor);

    void loadBitmapDescription(Context context);

    void onCreateAMapInstance();

    void prepareIcon(String str, Object obj);

    void processCircleHoleOption(CircleOptions circleOptions);

    void processPolygonHoleOption(PolygonOptions polygonOptions);

    boolean removeOverlay(String str) throws RemoteException;

    boolean removeOverlay(String str, boolean z) throws RemoteException;

    void setGlShaderManager(de deVar);

    void setRunLowFrame(boolean z);

    void updateOption(String str, BaseOptions baseOptions);
}