package com.ido.life.module.sport.screen;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.module.sport.map.MapScreenShotCallback;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class GaoDeMapScreen extends BaseMapScreen<TextureMapView, LatLng> {
    private static final String TAG = "GaoDeMap";
    private AMap aMap;
    private LatLngBounds latLngBounds;
    private int paddingBottom = 0;

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void animateCamera(LatLngBean latLngBean) {
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void initMapView(TextureMapView textureMapView) {
        super.initMapView(textureMapView);
        if (this.aMap == null) {
            this.aMap = textureMapView.getMap();
            this.aMap.getUiSettings().setZoomControlsEnabled(false);
            this.aMap.getUiSettings().setRotateGesturesEnabled(false);
            this.aMap.getUiSettings().setMyLocationButtonEnabled(false);
            CommonLogUtil.d("aMap.getMaxZoomLevel():" + this.aMap.getMaxZoomLevel() + ",getMinZoomLevel:" + this.aMap.getMinZoomLevel());
        }
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void adjustMapView() {
        if (this.aMap == null) {
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < getLatLngBeanList().size(); i++) {
            builder.include(fromLatLngBean(getLatLngBeanList().get(i)));
        }
        this.latLngBounds = builder.build();
        this.aMap.moveCamera(CameraUpdateFactory.newLatLngBoundsRect(this.latLngBounds, ScreenUtil.getScreenW() / 6, ScreenUtil.getScreenW() / 6, DipPixelUtil.dip2px(350.0f) / 6, (DipPixelUtil.dip2px(350.0f) / 6) + this.paddingBottom));
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((TextureMapView) this.mapView).onCreate(bundle);
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onResume() {
        super.onResume();
        ((TextureMapView) this.mapView).onResume();
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onPause() {
        super.onPause();
        ((TextureMapView) this.mapView).onPause();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onDestroy() {
        if (this.mapView != 0) {
            try {
                try {
                    ((TextureMapView) this.mapView).onDestroy();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                this.mapView = null;
            }
        }
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void showMapText(boolean z) {
        AMap aMap = this.aMap;
        if (aMap != null) {
            aMap.showMapText(false);
        }
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void hideLogo() {
        AMap aMap = this.aMap;
        if (aMap != null) {
            aMap.getUiSettings().setLogoBottomMargin(-150);
        }
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void addMarker(LatLngBean latLngBean, int i) {
        if (this.aMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(i));
        markerOptions.anchor(0.5f, 1.0f);
        markerOptions.setFlat(false);
        this.aMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void drawPolyline() {
        if (this.latLngList != null && this.latLngList.size() == 2) {
            this.colorList.add(Integer.valueOf(colorArray[0]));
        }
        if (this.colorList == null || this.colorList.size() == 0) {
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.useGradient(true);
            polylineOptions.addAll(this.latLngList);
            polylineOptions.color(colorArray[0]);
            polylineOptions.zIndex(100.0f);
            this.aMap.addPolyline(polylineOptions);
            return;
        }
        PolylineOptions polylineOptions2 = new PolylineOptions();
        if (this.mInvalidLngLatList != null && this.mInvalidLngLatList.size() > 0) {
            drawInvalidTravel(polylineOptions2);
            return;
        }
        polylineOptions2.useGradient(true);
        polylineOptions2.addAll(this.latLngList);
        polylineOptions2.colorValues(this.colorList);
        polylineOptions2.zIndex(100.0f);
        this.aMap.addPolyline(polylineOptions2);
    }

    private void drawInvalidTravel(PolylineOptions polylineOptions) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        PolylineOptions polylineOptions2 = polylineOptions;
        int i2 = 0;
        while (i2 < this.latLngList.size() && !this.mIsFinish) {
            if (this.mInvalidLngLatList.get(this.mInvalidLngLatList.size() - 1).intValue() < i2) {
                arrayList = new ArrayList();
                for (int iIntValue = this.mInvalidLngLatList.get(this.mInvalidLngLatList.size() - 1).intValue(); iIntValue < this.colorList.size(); iIntValue++) {
                    arrayList.add(this.colorList.get(iIntValue));
                }
                polylineOptions2.colorValues(arrayList);
                polylineOptions2.add((LatLng) this.latLngList.get(i2));
                if (i2 == this.latLngList.size() - 1) {
                    polylineOptions2.useGradient(true);
                    polylineOptions2.zIndex(100.0f);
                    this.aMap.addPolyline(polylineOptions2);
                }
            }
            polylineOptions2.add((LatLng) this.latLngList.get(i2));
            ArrayList arrayList2 = arrayList;
            int i3 = i;
            PolylineOptions polylineOptions3 = polylineOptions2;
            for (int i4 = 0; i4 < this.mInvalidLngLatList.size(); i4++) {
                if (this.mIsFinish) {
                    return;
                }
                if (i2 == this.mInvalidLngLatList.get(i4).intValue() && i2 % 2 != 0) {
                    while (i3 < i2) {
                        if (this.colorList.size() > i3) {
                            arrayList2.add(this.colorList.get(i3));
                        } else {
                            arrayList2.add(1295532630);
                        }
                        i3++;
                    }
                    CommonLogUtil.d(TAG, "drawInvalidTravel: colorIndex" + i2 + AppInfo.DELIM + i4);
                    polylineOptions3.useGradient(true);
                    polylineOptions3.zIndex(100.0f);
                    polylineOptions3.colorValues(arrayList2);
                    this.aMap.addPolyline(polylineOptions3);
                    arrayList2 = new ArrayList();
                    polylineOptions3 = new PolylineOptions();
                    polylineOptions3.add((LatLng) this.latLngList.get(i2));
                    arrayList2.add(1295532630);
                } else if (i2 == this.mInvalidLngLatList.get(i4).intValue() && i2 % 2 == 0) {
                    while (i3 < i2) {
                        if (this.mIsFinish) {
                            return;
                        }
                        if (this.colorList.size() > i3) {
                            arrayList2.add(this.colorList.get(i3));
                        } else {
                            arrayList2.add(1295532630);
                        }
                        i3++;
                    }
                    CommonLogUtil.d(TAG, "drawInvalidTravel: colorIndex" + i2 + AppInfo.DELIM + i2 + AppInfo.DELIM + this.mInvalidLngLatList.get(i4));
                    polylineOptions3.useGradient(false);
                    polylineOptions3.zIndex(100.0f);
                    polylineOptions3.colorValues(arrayList2);
                    this.aMap.addPolyline(polylineOptions3);
                    arrayList2 = new ArrayList();
                    if (i2 == 0) {
                        arrayList2.add(1295532630);
                    } else {
                        arrayList2.add(Integer.valueOf(colorArray[0]));
                    }
                    polylineOptions3 = new PolylineOptions();
                    polylineOptions3.add((LatLng) this.latLngList.get(i2));
                }
                i3 = i2;
            }
            i2++;
            polylineOptions2 = polylineOptions3;
            i = i3;
            arrayList = arrayList2;
        }
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onMapScreenShot(final MapScreenShotCallback mapScreenShotCallback) {
        this.aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() { // from class: com.ido.life.module.sport.screen.GaoDeMapScreen.1
            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap) {
            }

            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap, int i) {
                CommonLogUtil.d("onMapScreenShot");
                MapScreenShotCallback mapScreenShotCallback2 = mapScreenShotCallback;
                if (mapScreenShotCallback2 != null) {
                    mapScreenShotCallback2.shotComplet(bitmap);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public LatLng fromLatLngBean(LatLngBean latLngBean) {
        return new LatLng(latLngBean.latitude, latLngBean.longitude);
    }
}