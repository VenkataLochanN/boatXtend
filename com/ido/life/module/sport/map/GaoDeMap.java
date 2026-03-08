package com.ido.life.module.sport.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.bean.LatLngBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GaoDeMap extends BaseMap<TextureMapView, LatLng> implements LocationSource {
    private static final String TAG = "GaoDeMap";
    private AMap aMap;
    private PolylineOptions lastestPolylineOptions;
    private List<LatLng> lastestPolylineOptionsPoints;
    private LatLngBounds latLngBounds;
    private Marker mEndMarker;
    private Marker mMarker;
    private Marker mStartMarker;
    private List<PolylineOptions> optionses;

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setLatLngBeanList(List<LatLngBean> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        getLatLngBeanList().clear();
        for (LatLngBean latLngBean : list) {
            LatLngBean latLngBeanM22clone = latLngBean.m22clone();
            if (latLngBean.isGps && isLocationChina()) {
                convert(latLngBeanM22clone);
            }
            getLatLngBeanList().add(latLngBeanM22clone);
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void initMapView(TextureMapView textureMapView) {
        super.initMapView(textureMapView);
        if (this.aMap == null) {
            this.aMap = textureMapView.getMap();
            this.aMap.getUiSettings().setZoomControlsEnabled(false);
            this.aMap.getUiSettings().setRotateGesturesEnabled(false);
            this.aMap.setLocationSource(this);
            this.aMap.getUiSettings().setMyLocationButtonEnabled(false);
            CommonLogUtil.d(TAG, "aMap.getMaxZoomLevel():" + this.aMap.getMaxZoomLevel() + ",getMinZoomLevel:" + this.aMap.getMinZoomLevel());
            this.aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() { // from class: com.ido.life.module.sport.map.GaoDeMap.1
                @Override // com.amap.api.maps.AMap.OnCameraChangeListener
                public void onCameraChange(CameraPosition cameraPosition) {
                    CommonLogUtil.d(GaoDeMap.TAG, "onCameraChange: ");
                }

                @Override // com.amap.api.maps.AMap.OnCameraChangeListener
                public void onCameraChangeFinish(CameraPosition cameraPosition) {
                    GaoDeMap.this.zoomLevel = cameraPosition.zoom;
                    CommonLogUtil.d("zoomLevel:" + GaoDeMap.this.zoomLevel + ",getScalePerPixel:" + GaoDeMap.this.aMap.getScalePerPixel());
                }
            });
            this.aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() { // from class: com.ido.life.module.sport.map.GaoDeMap.2
                @Override // com.amap.api.maps.AMap.OnMapLoadedListener
                public void onMapLoaded() {
                    CommonLogUtil.d("onMapLoaded");
                    GaoDeMap.this.onMapLoaded = true;
                }
            });
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public List<Point> toScreenLocation(List<LatLngBean> list) {
        if (list == null || this.aMap == null || this.mIsFinish) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<LatLngBean> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(this.aMap.getProjection().toScreenLocation(fromLatLngBean(it.next())));
        }
        return arrayList;
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onResume() {
        ((TextureMapView) this.mapView).onResume();
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onPause() {
        ((TextureMapView) this.mapView).onPause();
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onDestroy() {
        try {
            ((TextureMapView) this.mapView).onDestroy();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onCreate(Bundle bundle) {
        ((TextureMapView) this.mapView).onCreate(bundle);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setMapType(boolean z) {
        this.aMap.setMapType(z ? 1 : 2);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onSaveInstanceState(Bundle bundle) {
        ((TextureMapView) this.mapView).onSaveInstanceState(bundle);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setIsHideMapView(boolean z) {
        super.setIsHideMapView(z);
        this.aMap.getUiSettings().setAllGesturesEnabled(!z);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
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

    @Override // com.ido.life.module.sport.map.BaseMap
    public void clearMarker() {
        if (this.aMap == null) {
            return;
        }
        if (this.latLngBeanList != null && this.latLngBeanList.size() > 0) {
            this.latLngBeanList.clear();
            this.lastLatLng = null;
        }
        List<PolylineOptions> list = this.optionses;
        if (list != null && list.size() > 0) {
            this.optionses.clear();
            this.optionses = null;
        }
        this.aMap.clear();
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addDynamicStartMark(LatLngBean latLngBean, int i) {
        if (this.aMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(i));
        markerOptions.anchor(0.5f, 1.0f);
        markerOptions.setFlat(false);
        Marker marker = this.mStartMarker;
        if (marker != null) {
            marker.remove();
        }
        this.mStartMarker = this.aMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addDynamicEndMark(LatLngBean latLngBean, int i) {
        if (this.aMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(i));
        markerOptions.anchor(0.5f, 1.0f);
        markerOptions.setFlat(false);
        Marker marker = this.mEndMarker;
        if (marker != null) {
            marker.remove();
        }
        this.mEndMarker = this.aMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addCurrentMarker(LatLngBean latLngBean, int i) {
        if (this.aMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(i));
        markerOptions.anchor(0.5f, 0.5f);
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.remove();
        }
        this.mMarker = this.aMap.addMarker(markerOptions);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f);
        scaleAnimation.setDuration(1000L);
        scaleAnimation.setInterpolator(new LinearInterpolator());
        this.mMarker.setAnimation(scaleAnimation);
        this.mMarker.startAnimation();
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addEndMarker(LatLngBean latLngBean, int i) {
        if (this.aMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(i));
        markerOptions.anchor(0.5f, 1.0f);
        this.aMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addMileMark() {
        if (this.aMap != null && this.isMarker) {
            Iterator<Integer> it = this.mileMarkList.iterator();
            int i = 1;
            while (it.hasNext()) {
                LatLng latLngFromLatLngBean = fromLatLngBean(getLatLngBeanList().get(it.next().intValue()));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLngFromLatLngBean);
                markerOptions.draggable(true);
                markerOptions.zIndex(100.0f);
                Bitmap bitmapCopy = BitmapFactory.decodeResource(IdoApp.getAppContext().getResources(), R.mipmap.mark_bg).copy(Bitmap.Config.ARGB_8888, true);
                Canvas canvas = new Canvas(bitmapCopy);
                Paint paint = new Paint();
                paint.setColor(-1);
                paint.setTextSize(40.0f);
                String strValueOf = String.valueOf(i);
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                canvas.drawText(strValueOf, (int) ((bitmapCopy.getWidth() - paint.measureText(strValueOf)) / 2.0f), (int) (((bitmapCopy.getHeight() - fontMetrics.descent) - fontMetrics.ascent) / 2.0f), paint);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmapCopy));
                markerOptions.anchor(0.5f, 0.5f);
                this.aMap.addMarker(markerOptions);
                i++;
            }
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void ajustMapView() {
        if (this.aMap == null || this.mIsFinish) {
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<LatLngBean> it = getLatLngBeanList().iterator();
        while (it.hasNext()) {
            builder.include(fromLatLngBean(it.next()));
        }
        this.latLngBounds = builder.build();
        this.aMap.moveCamera(CameraUpdateFactory.newLatLngBoundsRect(this.latLngBounds, ScreenUtil.getScreenW() / 6, ScreenUtil.getScreenW() / 6, DipPixelUtil.dip2px(350.0f) / 6, (DipPixelUtil.dip2px(350.0f) / 6) + this.paddingButtom));
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setGesturesEnabled(boolean z) {
        UiSettings uiSettings;
        AMap aMap = this.aMap;
        if (aMap == null || (uiSettings = aMap.getUiSettings()) == null) {
            return;
        }
        uiSettings.setAllGesturesEnabled(z);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addPolyline(LatLngBean latLngBean) {
        PolylineOptions options;
        if (this.aMap == null) {
            return;
        }
        initPolylineOptions();
        List<PolylineOptions> list = this.optionses;
        this.lastestPolylineOptions = list.get(list.size() - 1);
        this.lastestPolylineOptionsPoints = this.lastestPolylineOptions.getPoints();
        if (this.lastestPolylineOptionsPoints.size() > 50) {
            options = getOptions();
            options.add(fromLatLngBean(this.lastLatLng), fromLatLngBean(latLngBean));
            this.optionses.add(options);
        } else {
            options = this.lastestPolylineOptions;
            options.add(fromLatLngBean(latLngBean));
        }
        this.currentLatLng = latLngBean;
        if (this.lastLatLng != null) {
            this.colorList.add(Integer.valueOf(getColorList(this.lastLatLng, this.currentLatLng)));
            options.colorValues(this.colorList);
        }
        this.aMap.addPolyline(options);
        this.lastLatLng = this.currentLatLng;
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    protected void preDrawAllAndShot() {
        Log.d(TAG, "preDrawAllAndShot: ");
        AMap aMap = this.aMap;
        if (aMap == null) {
            return;
        }
        aMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.life.module.sport.map.BaseMap
    public LatLng fromLatLngBean(LatLngBean latLngBean) {
        return new LatLng(latLngBean.latitude, latLngBean.longitude);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    protected void drawPolyline() {
        if (this.aMap == null || this.mIsFinish) {
            return;
        }
        if (!this.isHideMapView) {
            if (this.latLngList != null && this.latLngList.size() == 2) {
                this.colorList.add(Integer.valueOf(colorArray[0]));
            }
            if (this.colorList == null || this.colorList.size() == 0) {
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.useGradient(true);
                polylineOptions.addAll(this.latLngList);
                polylineOptions.color(colorArray[0]);
                polylineOptions.width(LINE_WIDTH);
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
            polylineOptions2.width(LINE_WIDTH);
            this.aMap.addPolyline(polylineOptions2);
            return;
        }
        PolylineOptions polylineOptions3 = new PolylineOptions();
        polylineOptions3.useGradient(true);
        polylineOptions3.addAll(this.latLngList);
        CommonLogUtil.d(TAG, "drawPolyline: ++++++");
        polylineOptions3.color(com.ido.life.location.MapHelper.Standard_Color);
        polylineOptions3.width(LINE_WIDTH);
        polylineOptions3.zIndex(100.0f);
        this.aMap.addPolyline(polylineOptions3);
    }

    private void drawInvalidTravel(PolylineOptions polylineOptions) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        PolylineOptions polylineOptions2 = polylineOptions;
        int i2 = 0;
        while (i2 < this.latLngList.size()) {
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
                    polylineOptions2.width(LINE_WIDTH);
                    this.aMap.addPolyline(polylineOptions2);
                }
            }
            polylineOptions2.add((LatLng) this.latLngList.get(i2));
            ArrayList arrayList2 = arrayList;
            int i3 = i;
            PolylineOptions polylineOptions3 = polylineOptions2;
            for (int i4 = 0; i4 < this.mInvalidLngLatList.size(); i4++) {
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
                    polylineOptions3.width((float) LINE_WIDTH);
                    polylineOptions3.colorValues(arrayList2);
                    this.aMap.addPolyline(polylineOptions3);
                    arrayList2 = new ArrayList();
                    polylineOptions3 = new PolylineOptions();
                    polylineOptions3.add((LatLng) this.latLngList.get(i2));
                    arrayList2.add(1295532630);
                } else if (i2 == this.mInvalidLngLatList.get(i4).intValue() && i2 % 2 == 0) {
                    while (i3 < i2) {
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
                    polylineOptions3.width((float) LINE_WIDTH);
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

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onMapScreenShot(final MapScreenShotCallback mapScreenShotCallback) {
        this.aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() { // from class: com.ido.life.module.sport.map.GaoDeMap.3
            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap) {
            }

            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap, int i) throws Throwable {
                CommonLogUtil.d("onMapScreenShot");
                GaoDeMap.this.removeTimeOut();
                BitmapUtil.saveBitmap(bitmap, GaoDeMap.this.getShotFilePath());
                mapScreenShotCallback.shotComplet(bitmap);
            }
        });
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    protected void showOrHideMap(LatLngBean latLngBean) {
        CommonLogUtil.d("isHideMapView:" + this.isHideMapView);
        LatLng latLngFromLatLngBean = fromLatLngBean(latLngBean);
        if (this.isHideMapView) {
            GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
            groundOverlayOptions.image(BitmapDescriptorFactory.fromResource(R.mipmap.hide_map_bg));
            float scalePerPixel = this.aMap.getScalePerPixel() + 0.5f;
            groundOverlayOptions.position(latLngFromLatLngBean, ScreenUtil.getScreenW() * scalePerPixel, ScreenUtil.getScreenH() * scalePerPixel);
            groundOverlayOptions.zIndex(10.0f);
            this.aMap.addGroundOverlay(groundOverlayOptions);
            return;
        }
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.zIndex(10.0f);
        circleOptions.radius(1.0E7d);
        circleOptions.center(latLngFromLatLngBean);
        circleOptions.fillColor(Color.argb(125, 0, 0, 0));
        this.aMap.addCircle(circleOptions);
    }

    private void initPolylineOptions() {
        if (this.optionses == null) {
            this.optionses = new ArrayList();
            this.optionses.add(getOptions());
        }
    }

    private PolylineOptions getOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.useGradient(true);
        polylineOptions.zIndex(100.0f);
        polylineOptions.width(LINE_WIDTH);
        return polylineOptions;
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void animateCamera(LatLngBean latLngBean) {
        AMap aMap = this.aMap;
        if (aMap == null) {
            return;
        }
        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fromLatLngBean(latLngBean), this.zoomLevel), 1000L, null);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void moveCamera(LatLngBean latLngBean) {
        AMap aMap = this.aMap;
        if (aMap == null) {
            return;
        }
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromLatLngBean(latLngBean), this.zoomLevel));
    }

    @Override // com.amap.api.maps.LocationSource
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        CommonLogUtil.d(TAG, "activate");
    }

    @Override // com.amap.api.maps.LocationSource
    public void deactivate() {
        CommonLogUtil.d(TAG, "deactivate");
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public int getMinZoomLevel() {
        AMap aMap = this.aMap;
        if (aMap != null) {
            return (int) aMap.getMinZoomLevel();
        }
        return 3;
    }
}