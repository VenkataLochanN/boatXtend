package com.ido.life.module.sport.map;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.location.GpsCoordinateUtils;
import com.ido.life.util.AsyncTaskUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GoogleMap extends BaseMap<FrameLayout, LatLng> {
    private static final String TAG = GoogleMap.class.getSimpleName();
    private Marker mEndMarker;
    public com.google.android.gms.maps.GoogleMap mGoogleMap;
    private MapView mMapView;
    private Marker mMarker;
    private Marker mStarMarker;
    private boolean isSetMap = false;
    Handler handler = new Handler();
    PolylineOptions options = new PolylineOptions();
    private boolean isSetOnCameraChangeListener = false;

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setLatLngBeanList(List<LatLngBean> list) {
        try {
            for (LatLngBean latLngBean : list) {
                if (latLngBean.isGps) {
                    this.latLngBeanList.add(translate(latLngBean));
                } else {
                    this.latLngBeanList.add(latLngBean);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private LatLngBean translate(LatLngBean latLngBean) {
        double[] dArrCalWGS84toGCJ02 = GpsCoordinateUtils.calWGS84toGCJ02(latLngBean.latitude, latLngBean.longitude);
        LatLngBean latLngBeanM22clone = latLngBean.m22clone();
        latLngBeanM22clone.setLatitude(dArrCalWGS84toGCJ02[0]);
        latLngBeanM22clone.setLongitude(dArrCalWGS84toGCJ02[1]);
        return latLngBeanM22clone;
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setIsHideMapView(boolean z) {
        if (this.mGoogleMap == null) {
            return;
        }
        super.setIsHideMapView(z);
        this.mGoogleMap.getUiSettings().setAllGesturesEnabled(!z);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    protected void showOrHideMap(LatLngBean latLngBean) {
        if (this.mGoogleMap == null) {
            return;
        }
        CommonLogUtil.d("isHideMapView:" + this.isHideMapView);
        if (this.isHideMapView) {
            GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
            groundOverlayOptions.image(BitmapDescriptorFactory.fromResource(R.mipmap.hide_map_bg));
            groundOverlayOptions.position(fromLatLngBean(latLngBean), ScreenUtil.getScreenW() * 3, ScreenUtil.getScreenH() * 3);
            groundOverlayOptions.zIndex(10.0f);
            this.mGoogleMap.addGroundOverlay(groundOverlayOptions);
            return;
        }
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.zIndex(100.0f);
        circleOptions.radius(1.0E7d);
        circleOptions.center(fromLatLngBean(latLngBean));
        circleOptions.fillColor(Color.argb(125, 0, 0, 0));
        this.mGoogleMap.addCircle(circleOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void initMapView(FrameLayout frameLayout) {
        super.initMapView(frameLayout);
        CommonLogUtil.d("是否支持google地图显示:" + checkPlayServices(IdoApp.getAppContext()));
        printLog("是否支持google地图显示:" + checkPlayServices(IdoApp.getAppContext()));
        if (!checkPlayServices(IdoApp.getAppContext())) {
            printLog("GooglePlayServices 不可用");
            return;
        }
        FragmentManager fragmentManager = this.activity.getFragmentManager();
        MapFragment mapFragment = new MapFragment();
        fragmentManager.beginTransaction().replace(R.id.fl_googlemap, mapFragment).commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() { // from class: com.ido.life.module.sport.map.GoogleMap.1
            @Override // com.google.android.gms.maps.OnMapReadyCallback
            public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
                CommonLogUtil.d(GoogleMap.TAG, "onMapReady 加载完成");
                GoogleMap.this.printLog("onMapReady 加载完成");
                GoogleMap.this.setGoogMap(googleMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGoogMap(com.google.android.gms.maps.GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        this.isSetMap = true;
        this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.mGoogleMap.setLocationSource(new LocationSource() { // from class: com.ido.life.module.sport.map.GoogleMap.2
            @Override // com.google.android.gms.maps.LocationSource
            public void deactivate() {
            }

            @Override // com.google.android.gms.maps.LocationSource
            public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
                CommonLogUtil.d(GoogleMap.TAG, "GoogleMap activate");
            }
        });
        this.mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() { // from class: com.ido.life.module.sport.map.GoogleMap.3
            @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
            public void onMapLoaded() {
                CommonLogUtil.d(GoogleMap.TAG, "onMapLoaded 加载完成");
                GoogleMap.this.printLog("onMapLoaded 加载完成");
                GoogleMap.this.onMapLoaded = true;
            }
        });
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public List<Point> toScreenLocation(List<LatLngBean> list) {
        if (list == null || this.mGoogleMap == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<LatLngBean> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(this.mGoogleMap.getProjection().toScreenLocation(fromLatLngBean(it.next())));
        }
        return arrayList;
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void getMap(final OnMapLoadedListener onMapLoadedListener) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.sport.map.GoogleMap.4
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                while (!GoogleMap.this.isSetMap) {
                    try {
                        CommonLogUtil.d("google地图view对象还未获取到 is null");
                        if (System.currentTimeMillis() - jCurrentTimeMillis > BaseMap.MAP_LOAD_TIME_OUT) {
                            if (onMapLoadedListener == null) {
                                return null;
                            }
                            onMapLoadedListener.onMapLoad(GoogleMap.this.isSetMap);
                            return null;
                        }
                        Thread.sleep(200L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                OnMapLoadedListener onMapLoadedListener2 = onMapLoadedListener;
                if (onMapLoadedListener2 != null) {
                    onMapLoadedListener2.onMapLoad(GoogleMap.this.isSetMap);
                }
                CommonLogUtil.d(GoogleMap.TAG, "google地图view对象 is not null ");
            }
        }).execute("");
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setMapType(final boolean z) {
        com.google.android.gms.maps.GoogleMap googleMap = this.mGoogleMap;
        if (googleMap == null) {
            this.handler.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.map.GoogleMap.5
                @Override // java.lang.Runnable
                public void run() {
                    GoogleMap.this.setMapType(z);
                }
            }, 500L);
        } else {
            googleMap.setMapType(z ? 1 : 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.life.module.sport.map.BaseMap
    public LatLng fromLatLngBean(LatLngBean latLngBean) {
        return new LatLng(latLngBean.latitude, latLngBean.longitude);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    protected void drawPolyline() {
        if (this.mGoogleMap == null) {
            return;
        }
        int i = colorArray[0];
        int size = this.latLngList.size() - 1;
        int iIntValue = i;
        int i2 = 0;
        while (i2 < size) {
            if (this.colorList.size() > i2) {
                iIntValue = this.colorList.get(i2).intValue();
            }
            i2++;
            Polyline polylineAddPolyline = this.mGoogleMap.addPolyline(new PolylineOptions().add((LatLng) this.latLngList.get(i2), (LatLng) this.latLngList.get(i2)).color(iIntValue).width((float) (((double) LINE_WIDTH) * 0.8d)).zIndex(100.0f));
            polylineAddPolyline.setJointType(2);
            polylineAddPolyline.setStartCap(new RoundCap());
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addMarker(LatLngBean latLngBean, int i) {
        if (this.mGoogleMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.anchor(0.5f, 1.0f);
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(i);
        if (bitmapDescriptorFromResource != null) {
            markerOptions.icon(bitmapDescriptorFromResource);
        }
        this.mGoogleMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void clearMarker() {
        if (this.mGoogleMap == null) {
            return;
        }
        if (this.latLngBeanList != null && this.latLngBeanList.size() > 0) {
            this.latLngBeanList.clear();
            this.lastLatLng = null;
        }
        this.options = new PolylineOptions();
        this.mGoogleMap.clear();
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addDynamicStartMark(LatLngBean latLngBean, int i) {
        if (this.mGoogleMap == null || this.mIsFinish) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.anchor(0.5f, 1.0f);
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(i);
        if (bitmapDescriptorFromResource != null) {
            markerOptions.icon(bitmapDescriptorFromResource);
        }
        Marker marker = this.mStarMarker;
        if (marker != null) {
            marker.remove();
            this.mStarMarker.setVisible(false);
        }
        this.mStarMarker = this.mGoogleMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addDynamicEndMark(LatLngBean latLngBean, int i) {
        if (this.mGoogleMap == null || this.mIsFinish) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.anchor(0.5f, 1.0f);
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(i);
        if (bitmapDescriptorFromResource != null) {
            markerOptions.icon(bitmapDescriptorFromResource);
        }
        Marker marker = this.mEndMarker;
        if (marker != null) {
            marker.remove();
            this.mEndMarker.setVisible(false);
        }
        this.mEndMarker = this.mGoogleMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addCurrentMarker(LatLngBean latLngBean, int i) {
        if (this.mGoogleMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.anchor(0.5f, 0.5f);
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(i);
        if (bitmapDescriptorFromResource != null) {
            markerOptions.icon(bitmapDescriptorFromResource);
        }
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.remove();
        }
        this.mMarker = this.mGoogleMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addEndMarker(LatLngBean latLngBean, int i) {
        if (this.mGoogleMap == null) {
            return;
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fromLatLngBean(latLngBean));
        markerOptions.draggable(true);
        markerOptions.anchor(0.5f, 1.0f);
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(i);
        if (bitmapDescriptorFromResource != null) {
            markerOptions.icon(bitmapDescriptorFromResource);
        }
        this.mGoogleMap.addMarker(markerOptions);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void ajustMapView() {
        if (this.mGoogleMap == null) {
            this.handler.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.map.GoogleMap.6
                @Override // java.lang.Runnable
                public void run() {
                    GoogleMap.this.ajustMapView();
                }
            }, 200L);
            return;
        }
        try {
            if (getLatLngBeanList() != null && getLatLngBeanList().size() < 40) {
                move2CurrentLocation();
                return;
            }
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (LatLngBean latLngBean : getLatLngBeanList()) {
                builder.include(new LatLng(latLngBean.latitude, latLngBean.longitude));
            }
            LatLngBounds latLngBoundsBuild = builder.build();
            int screenW = ScreenUtil.getScreenW() / 6;
            if (screenW == 0) {
                screenW = 100;
            }
            CommonLogUtil.d("padding:" + screenW);
            this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBoundsBuild, screenW + this.paddingButtom));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void setGesturesEnabled(boolean z) {
        if (this.mGoogleMap == null || !this.onMapLoaded) {
            return;
        }
        this.mGoogleMap.getUiSettings().setAllGesturesEnabled(z);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addPolyline(LatLngBean latLngBean) {
        if (this.mGoogleMap == null) {
            return;
        }
        this.currentLatLng = latLngBean;
        if (this.lastLatLng != null) {
            this.colorList.add(Integer.valueOf(getColorList(this.lastLatLng, latLngBean)));
            this.options.color(BaseMap.RUN_COLOR_DEFAULT);
        }
        this.options.add(fromLatLngBean(this.currentLatLng));
        this.lastLatLng = this.currentLatLng;
        this.mGoogleMap.addPolyline(this.options);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    protected void preDrawAllAndShot() {
        com.google.android.gms.maps.GoogleMap googleMap = this.mGoogleMap;
        if (googleMap != null) {
            googleMap.clear();
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onMapScreenShot(final MapScreenShotCallback mapScreenShotCallback) {
        this.mapShotTimeOutHanlder.removeCallbacksAndMessages(null);
        this.mapShotTimeOutHanlder.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.map.-$$Lambda$GoogleMap$1ipDCWyGrB_MDehB2KVxD4_rj3U
            @Override // java.lang.Runnable
            public final void run() {
                GoogleMap.lambda$onMapScreenShot$0(mapScreenShotCallback);
            }
        }, MAP_SHOT_TIME_OUT / 2);
        this.mGoogleMap.snapshot(new GoogleMap.SnapshotReadyCallback() { // from class: com.ido.life.module.sport.map.-$$Lambda$GoogleMap$ZUq7SK7zSh9nAQkjRuwhVF4AqXc
            @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
            public final void onSnapshotReady(Bitmap bitmap) throws Throwable {
                this.f$0.lambda$onMapScreenShot$1$GoogleMap(mapScreenShotCallback, bitmap);
            }
        });
    }

    static /* synthetic */ void lambda$onMapScreenShot$0(MapScreenShotCallback mapScreenShotCallback) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), TAG, "截图超时了，，，");
        if (mapScreenShotCallback != null) {
            mapScreenShotCallback.shotComplet(null);
        }
    }

    public /* synthetic */ void lambda$onMapScreenShot$1$GoogleMap(MapScreenShotCallback mapScreenShotCallback, Bitmap bitmap) throws Throwable {
        removeTimeOut();
        BitmapUtil.saveBitmap(bitmap, getShotFilePath());
        mapScreenShotCallback.shotComplet(bitmap);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void addMileMark() {
        if (this.mGoogleMap != null && this.isMarker) {
            Iterator<Integer> it = this.mileMarkList.iterator();
            int i = 1;
            while (it.hasNext()) {
                LatLng latLngFromLatLngBean = fromLatLngBean(getLatLngBeanList().get(it.next().intValue()));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLngFromLatLngBean);
                markerOptions.draggable(true);
                markerOptions.anchor(0.5f, 0.5f);
                Bitmap bitmapCopy = BitmapFactory.decodeResource(IdoApp.getAppContext().getResources(), R.mipmap.mark_bg).copy(Bitmap.Config.ARGB_8888, true);
                Canvas canvas = new Canvas(bitmapCopy);
                Paint paint = new Paint();
                paint.setColor(-1);
                paint.setTextSize(40.0f);
                String strValueOf = String.valueOf(i);
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                canvas.drawText(strValueOf, (int) ((bitmapCopy.getWidth() - paint.measureText(strValueOf)) / 2.0f), (int) (((bitmapCopy.getHeight() - fontMetrics.descent) - fontMetrics.ascent) / 2.0f), paint);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmapCopy));
                this.mGoogleMap.addMarker(markerOptions);
                i++;
            }
        }
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void animateCamera(LatLngBean latLngBean) {
        com.google.android.gms.maps.GoogleMap googleMap = this.mGoogleMap;
        if (googleMap == null) {
            return;
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromLatLngBean(latLngBean), this.zoomLevel));
        if (this.isSetOnCameraChangeListener) {
            return;
        }
        this.mGoogleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() { // from class: com.ido.life.module.sport.map.GoogleMap.7
            @Override // com.google.android.gms.maps.GoogleMap.OnCameraChangeListener
            public void onCameraChange(CameraPosition cameraPosition) {
                GoogleMap.this.zoomLevel = cameraPosition.zoom;
            }
        });
        this.isSetOnCameraChangeListener = true;
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void moveCamera(LatLngBean latLngBean) {
        com.google.android.gms.maps.GoogleMap googleMap = this.mGoogleMap;
        if (googleMap == null) {
            return;
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromLatLngBean(latLngBean), this.zoomLevel));
    }

    public static boolean checkPlayServices(Context context) {
        return context != null && GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleMapLogPath(), TAG, str);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // com.ido.life.module.sport.map.BaseMap
    public int getMinZoomLevel() {
        com.google.android.gms.maps.GoogleMap googleMap = this.mGoogleMap;
        if (googleMap != null) {
            return (int) googleMap.getMinZoomLevel();
        }
        return 1;
    }
}