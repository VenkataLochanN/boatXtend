package com.autonavi.amap.mapcore.interfaces;

import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IPolyline extends IOverlay {
    int getColor() throws RemoteException;

    LatLng getNearestLatLng(LatLng latLng);

    PolylineOptions getOptions();

    List<LatLng> getPoints() throws RemoteException;

    float getShownRatio();

    float getWidth() throws RemoteException;

    boolean isDottedLine();

    boolean isGeodesic();

    void setColor(int i) throws RemoteException;

    void setCustemTextureIndex(List<Integer> list);

    void setCustomTexture(BitmapDescriptor bitmapDescriptor);

    void setCustomTextureList(List<BitmapDescriptor> list);

    void setDottedLine(boolean z);

    void setGeodesic(boolean z) throws RemoteException;

    void setOptions(PolylineOptions polylineOptions);

    void setPoints(List<LatLng> list) throws RemoteException;

    void setShowRange(float f2, float f3);

    void setShownRatio(float f2);

    void setTransparency(float f2);

    void setWidth(float f2) throws RemoteException;
}