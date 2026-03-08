package com.ido.life.module.sport.screen;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.module.sport.map.MapScreenShotCallback;

/* JADX INFO: loaded from: classes2.dex */
public class GoogleMapScreen extends BaseMapScreen<FrameLayout, LatLng> {
    private static final String TAG = "GoogleMapScreen";
    public GoogleMap mGoogleMap;
    protected Handler mapShotTimeOutHandler = new Handler();
    Handler handler = new Handler();
    private boolean isSetOnCameraChangeListener = false;

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
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
        fragmentManager.beginTransaction().replace(R.id.fl_googlemap_screen, mapFragment).commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() { // from class: com.ido.life.module.sport.screen.GoogleMapScreen.1
            @Override // com.google.android.gms.maps.OnMapReadyCallback
            public void onMapReady(GoogleMap googleMap) {
                CommonLogUtil.d(GoogleMapScreen.TAG, "onMapReady 加载完成");
                GoogleMapScreen.this.printLog("onMapReady 加载完成");
                if (GoogleMapScreen.this.mIsFinish) {
                    return;
                }
                GoogleMapScreen.this.setGoogMap(googleMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGoogMap(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.mGoogleMap.setLocationSource(new LocationSource() { // from class: com.ido.life.module.sport.screen.GoogleMapScreen.2
            @Override // com.google.android.gms.maps.LocationSource
            public void deactivate() {
            }

            @Override // com.google.android.gms.maps.LocationSource
            public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
                CommonLogUtil.d(GoogleMapScreen.TAG, "GoogleMap activate");
            }
        });
        this.mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() { // from class: com.ido.life.module.sport.screen.GoogleMapScreen.3
            @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
            public void onMapLoaded() {
                CommonLogUtil.d(GoogleMapScreen.TAG, "onMapLoaded 加载完成");
                GoogleMapScreen.this.printLog("onMapLoaded 加载完成");
            }
        });
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void adjustMapView() {
        if (this.mGoogleMap == null) {
            this.handler.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.screen.GoogleMapScreen.4
                @Override // java.lang.Runnable
                public void run() {
                    GoogleMapScreen.this.adjustMapView();
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
            this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBoundsBuild, screenW + this.paddingBottom));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void move2CurrentLocation() {
        if (this.latLngBeanList == null || this.latLngBeanList.size() == 0) {
            return;
        }
        animateCamera(this.latLngBeanList.get(this.latLngBeanList.size() - 1));
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void animateCamera(LatLngBean latLngBean) {
        GoogleMap googleMap = this.mGoogleMap;
        if (googleMap == null) {
            return;
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromLatLngBean(latLngBean), this.zoomLevel));
        if (this.isSetOnCameraChangeListener) {
            return;
        }
        this.mGoogleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() { // from class: com.ido.life.module.sport.screen.GoogleMapScreen.5
            @Override // com.google.android.gms.maps.GoogleMap.OnCameraChangeListener
            public void onCameraChange(CameraPosition cameraPosition) {
                GoogleMapScreen.this.zoomLevel = cameraPosition.zoom;
            }
        });
        this.isSetOnCameraChangeListener = true;
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void showMapText(boolean z) {
        if (this.mGoogleMap == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public LatLng fromLatLngBean(LatLngBean latLngBean) {
        return new LatLng(latLngBean.latitude, latLngBean.longitude);
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void hideLogo() {
        if (this.mGoogleMap == null) {
        }
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
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

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void drawPolyline() {
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
            Polyline polylineAddPolyline = this.mGoogleMap.addPolyline(new PolylineOptions().add((LatLng) this.latLngList.get(i2), (LatLng) this.latLngList.get(i2)).color(iIntValue).width(9.6f).zIndex(100.0f));
            polylineAddPolyline.setJointType(2);
            polylineAddPolyline.setStartCap(new RoundCap());
        }
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onMapScreenShot(final MapScreenShotCallback mapScreenShotCallback) {
        this.mapShotTimeOutHandler.removeCallbacksAndMessages(null);
        this.mapShotTimeOutHandler.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.screen.-$$Lambda$GoogleMapScreen$sgBjB2nMYYO-jxaYwHg_PHp0QHQ
            @Override // java.lang.Runnable
            public final void run() {
                GoogleMapScreen.lambda$onMapScreenShot$0(mapScreenShotCallback);
            }
        }, MAP_SHOT_TIME_OUT / 2);
        this.mGoogleMap.snapshot(new GoogleMap.SnapshotReadyCallback() { // from class: com.ido.life.module.sport.screen.-$$Lambda$GoogleMapScreen$D69I6nD25L4lVbxMSuBQq3MCBrE
            @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
            public final void onSnapshotReady(Bitmap bitmap) {
                this.f$0.lambda$onMapScreenShot$1$GoogleMapScreen(mapScreenShotCallback, bitmap);
            }
        });
    }

    static /* synthetic */ void lambda$onMapScreenShot$0(MapScreenShotCallback mapScreenShotCallback) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), TAG, "截图超时了，，，");
        if (mapScreenShotCallback != null) {
            mapScreenShotCallback.shotComplet(null);
        }
    }

    public /* synthetic */ void lambda$onMapScreenShot$1$GoogleMapScreen(MapScreenShotCallback mapScreenShotCallback, Bitmap bitmap) {
        removeTimeOut();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), TAG, "截图完成，，，");
        mapScreenShotCallback.shotComplet(bitmap);
    }

    protected final void removeTimeOut() {
        this.mapShotTimeOutHandler.removeCallbacksAndMessages(null);
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

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onResume() {
        super.onResume();
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.ido.life.module.sport.screen.BaseMapScreen
    public void onPause() {
        super.onPause();
    }
}