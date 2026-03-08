package com.amap.api.maps;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.model.Arc;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.BuildingOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.CrossOverlay;
import com.amap.api.maps.model.CrossOverlayOptions;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.GL3DModelOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.HeatMapLayer;
import com.amap.api.maps.model.HeatMapLayerOptions;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.RouteOverlay;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.particle.ParticleOverlay;
import com.amap.api.maps.model.particle.ParticleOverlayOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AMap {
    public static final String CHINESE = "zh_cn";
    private static final String CLASSNAME = "AMap";
    public static final String CUSTOM = "custom";
    public static final String ENGLISH = "en";
    public static final String LOCAL = "local";
    public static final int LOCATION_TYPE_LOCATE = 1;
    public static final int LOCATION_TYPE_MAP_FOLLOW = 2;
    public static final int LOCATION_TYPE_MAP_ROTATE = 3;
    public static final int MAP_TYPE_BUS = 5;
    public static final int MAP_TYPE_NAVI = 4;
    public static final int MAP_TYPE_NIGHT = 3;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MASK_LAYER_NONE = -1;
    public static final int MASK_LAYER_UNDER_LINE = 1;
    public static final int MASK_LAYER_UNDER_MARKER = 0;
    public static final String STYLE_CHINESE = "style_zh_cn";
    private final IAMap mapDelegate;
    private MyTrafficStyle myTrafficStyle;
    private Projection projection;
    private UiSettings uiSettings;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface CommonInfoWindowAdapter {
        InfoWindowParams getInfoWindowParams(BasePointOverlay basePointOverlay);
    }

    public interface ImageInfoWindowAdapter extends InfoWindowAdapter {
        long getInfoWindowUpdateTime();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    public interface MultiPositionInfoWindowAdapter extends InfoWindowAdapter {
        View getInfoWindowClick(Marker marker);

        View getOverturnInfoWindow(Marker marker);

        View getOverturnInfoWindowClick(Marker marker);
    }

    public interface OnCacheRemoveListener {
        void onRemoveCacheFinish(boolean z);
    }

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);

        void onCameraChangeFinish(CameraPosition cameraPosition);
    }

    public interface OnIndoorBuildingActiveListener {
        void OnIndoorBuilding(IndoorBuildingInfo indoorBuildingInfo);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedListener {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMapScreenShotListener {
        void onMapScreenShot(Bitmap bitmap);

        void onMapScreenShot(Bitmap bitmap, int i);
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMultiPointClickListener {
        boolean onPointClick(MultiPointItem multiPointItem);
    }

    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    public interface OnPOIClickListener {
        void onPOIClick(Poi poi);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline polyline);
    }

    public interface onMapPrintScreenListener {
        void onMapPrint(Drawable drawable);
    }

    @Deprecated
    public static String getVersion() {
        return "7.6.0";
    }

    protected AMap(IAMap iAMap) {
        this.mapDelegate = iAMap;
    }

    public final CameraPosition getCameraPosition() {
        try {
            return this.mapDelegate.getCameraPosition();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final float getMaxZoomLevel() {
        return this.mapDelegate.getMaxZoomLevel();
    }

    public final float getMinZoomLevel() {
        return this.mapDelegate.getMinZoomLevel();
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        try {
            this.mapDelegate.moveCamera(cameraUpdate);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        try {
            this.mapDelegate.animateCamera(cameraUpdate);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        try {
            this.mapDelegate.animateCameraWithCallback(cameraUpdate, cancelableCallback);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, long j, CancelableCallback cancelableCallback) {
        if (j <= 0) {
            try {
                Log.w(CLASSNAME, "durationMs must be positive");
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.mapDelegate.animateCameraWithDurationAndCallback(cameraUpdate, j, cancelableCallback);
    }

    public final void stopAnimation() {
        try {
            this.mapDelegate.stopAnimation();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final NavigateArrow addNavigateArrow(NavigateArrowOptions navigateArrowOptions) {
        try {
            return this.mapDelegate.addNavigateArrow(navigateArrowOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final Polyline addPolyline(PolylineOptions polylineOptions) {
        try {
            return this.mapDelegate.addPolyline(polylineOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final BuildingOverlay addBuildingOverlay() {
        try {
            return this.mapDelegate.addBuildingOverlay();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final Circle addCircle(CircleOptions circleOptions) {
        try {
            return this.mapDelegate.addCircle(circleOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final Arc addArc(ArcOptions arcOptions) {
        try {
            return this.mapDelegate.addArc(arcOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            return this.mapDelegate.addPolygon(polygonOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        try {
            return this.mapDelegate.addGroundOverlay(groundOverlayOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        try {
            return this.mapDelegate.addMarker(markerOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public GL3DModel addGL3DModel(GL3DModelOptions gL3DModelOptions) {
        try {
            return this.mapDelegate.addGLModel(gL3DModelOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final Text addText(TextOptions textOptions) {
        try {
            return this.mapDelegate.addText(textOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) {
        try {
            return this.mapDelegate.addMarkers(arrayList, z);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final List<Marker> getMapScreenMarkers() {
        try {
            return this.mapDelegate.getMapScreenMarkers();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        try {
            return this.mapDelegate.addTileOverlay(tileOverlayOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final HeatMapLayer addHeatMapLayer(HeatMapLayerOptions heatMapLayerOptions) {
        try {
            return this.mapDelegate.addHeatMapLayer(heatMapLayerOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public MultiPointOverlay addMultiPointOverlay(MultiPointOverlayOptions multiPointOverlayOptions) {
        try {
            return this.mapDelegate.addMultiPointOverlay(multiPointOverlayOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ParticleOverlay addParticleOverlay(ParticleOverlayOptions particleOverlayOptions) {
        try {
            return this.mapDelegate.addParticleOverlay(particleOverlayOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void clear() {
        try {
            this.mapDelegate.clear();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void clear(boolean z) {
        try {
            this.mapDelegate.clear(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final int getMapType() {
        try {
            return this.mapDelegate.getMapType();
        } catch (Throwable th) {
            th.printStackTrace();
            return 1;
        }
    }

    public final void setMapType(int i) {
        try {
            this.mapDelegate.setMapType(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final boolean isTrafficEnabled() {
        try {
            return this.mapDelegate.isTrafficEnabled();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void setTrafficEnabled(boolean z) {
        try {
            this.mapDelegate.setTrafficEnabled(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showMapText(boolean z) {
        try {
            this.mapDelegate.setMapTextEnable(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showIndoorMap(boolean z) {
        try {
            this.mapDelegate.setIndoorEnabled(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showBuildings(boolean z) {
        try {
            this.mapDelegate.set3DBuildingEnabled(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) {
        try {
            this.myTrafficStyle = myTrafficStyle;
            this.mapDelegate.setMyTrafficStyle(myTrafficStyle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public MyTrafficStyle getMyTrafficStyle() {
        return this.myTrafficStyle;
    }

    public final boolean isMyLocationEnabled() {
        try {
            return this.mapDelegate.isMyLocationEnabled();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public final void setMyLocationEnabled(boolean z) {
        try {
            this.mapDelegate.setMyLocationEnabled(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final Location getMyLocation() {
        try {
            return this.mapDelegate.getMyLocation();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setLocationSource(LocationSource locationSource) {
        try {
            this.mapDelegate.setLocationSource(locationSource);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        try {
            this.mapDelegate.setMyLocationStyle(myLocationStyle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final MyLocationStyle getMyLocationStyle() {
        try {
            return this.mapDelegate.getMyLocationStyle();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setMyLocationType(int i) {
        try {
            this.mapDelegate.setMyLocationType(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setMyLocationRotateAngle(float f2) {
        try {
            this.mapDelegate.setMyLocationRotateAngle(f2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final UiSettings getUiSettings() {
        try {
            if (this.uiSettings == null) {
                this.uiSettings = this.mapDelegate.getAMapUiSettings();
            }
            return this.uiSettings;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final Projection getProjection() {
        try {
            if (this.projection == null) {
                this.projection = this.mapDelegate.getAMapProjection();
            }
            return this.projection;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) {
        try {
            this.mapDelegate.setOnCameraChangeListener(onCameraChangeListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        try {
            this.mapDelegate.setOnMapClickListener(onMapClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        try {
            this.mapDelegate.setOnMapTouchListener(onMapTouchListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnPOIClickListener(OnPOIClickListener onPOIClickListener) {
        try {
            this.mapDelegate.setOnPOIClickListener(onPOIClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        try {
            this.mapDelegate.setOnMyLocationChangeListener(onMyLocationChangeListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        try {
            this.mapDelegate.setOnMapLongClickListener(onMapLongClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        try {
            this.mapDelegate.setOnMarkerClickListener(onMarkerClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        try {
            this.mapDelegate.setOnPolylineClickListener(onPolylineClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        try {
            this.mapDelegate.setOnMarkerDragListener(onMarkerDragListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) {
        try {
            this.mapDelegate.setOnInfoWindowClickListener(onInfoWindowClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) {
        try {
            this.mapDelegate.setInfoWindowAdapter(infoWindowAdapter);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setCommonInfoWindowAdapter(CommonInfoWindowAdapter commonInfoWindowAdapter) {
        try {
            this.mapDelegate.setInfoWindowAdapter(commonInfoWindowAdapter);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener) {
        try {
            this.mapDelegate.setOnMaploadedListener(onMapLoadedListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) {
        try {
            this.mapDelegate.setOnIndoorBuildingActiveListener(onIndoorBuildingActiveListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setOnMultiPointClickListener(OnMultiPointClickListener onMultiPointClickListener) {
        try {
            this.mapDelegate.setOnMultiPointClickListener(onMultiPointClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void getMapPrintScreen(onMapPrintScreenListener onmapprintscreenlistener) {
        this.mapDelegate.getMapPrintScreen(onmapprintscreenlistener);
    }

    public void getMapScreenShot(OnMapScreenShotListener onMapScreenShotListener) {
        this.mapDelegate.getMapScreenShot(onMapScreenShotListener);
    }

    public float getScalePerPixel() {
        try {
            return this.mapDelegate.getScalePerPixel();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void runOnDrawFrame() {
        this.mapDelegate.setRunLowFrame(false);
    }

    public void removecache() {
        try {
            this.mapDelegate.removecache();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removecache(OnCacheRemoveListener onCacheRemoveListener) {
        try {
            this.mapDelegate.removecache(onCacheRemoveListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setCustomRenderer(CustomRenderer customRenderer) {
        try {
            this.mapDelegate.setCustomRenderer(customRenderer);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setPointToCenter(int i, int i2) {
        try {
            this.mapDelegate.setCenterToPixel(i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setMapTextZIndex(int i) {
        try {
            this.mapDelegate.setMapTextZIndex(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void setLoadOfflineData(boolean z) {
        try {
            this.mapDelegate.setLoadOfflineData(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final int getMapTextZIndex() {
        try {
            return this.mapDelegate.getMapTextZIndex();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void reloadMap() {
        this.mapDelegate.reloadMap();
    }

    public void setRenderFps(int i) {
        this.mapDelegate.setRenderFps(i);
    }

    public void setIndoorBuildingInfo(IndoorBuildingInfo indoorBuildingInfo) {
        try {
            this.mapDelegate.setIndoorBuildingInfo(indoorBuildingInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setAMapGestureListener(AMapGestureListener aMapGestureListener) {
        this.mapDelegate.setAMapGestureListener(aMapGestureListener);
    }

    public float getZoomToSpanLevel(LatLng latLng, LatLng latLng2) {
        return this.mapDelegate.getZoomToSpanLevel(latLng, latLng2);
    }

    public Pair<Float, LatLng> calculateZoomToSpanLevel(int i, int i2, int i3, int i4, LatLng latLng, LatLng latLng2) {
        return this.mapDelegate.calculateZoomToSpanLevel(i, i2, i3, i4, latLng, latLng2);
    }

    public final InfoWindowAnimationManager getInfoWindowAnimationManager() {
        return this.mapDelegate.getInfoWindowAnimationManager();
    }

    public void setMaskLayerParams(int i, int i2, int i3, int i4, int i5, long j) {
        this.mapDelegate.setMaskLayerParams(i, i2, i3, i4, i5, j);
    }

    public void setMaxZoomLevel(float f2) {
        this.mapDelegate.setMaxZoomLevel(f2);
    }

    public void setMinZoomLevel(float f2) {
        this.mapDelegate.setMinZoomLevel(f2);
    }

    public void resetMinMaxZoomPreference() {
        this.mapDelegate.resetMinMaxZoomPreference();
    }

    public void setMapStatusLimits(LatLngBounds latLngBounds) {
        try {
            this.mapDelegate.setMapStatusLimits(latLngBounds);
            moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public CrossOverlay addCrossOverlay(CrossOverlayOptions crossOverlayOptions) {
        try {
            return this.mapDelegate.addCrossVector(crossOverlayOptions);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public RouteOverlay addRouteOverlay() {
        return this.mapDelegate.addNaviRouteOverlay();
    }

    public float[] getViewMatrix() {
        return this.mapDelegate.getViewMatrix();
    }

    public float[] getProjectionMatrix() {
        return this.mapDelegate.getProjectionMatrix();
    }

    public void setMapCustomEnable(boolean z) {
        Log.e("amap", "该方法已无效，请到官网(lbs.amap.com)更新新版样式文件并使用setCustomMapStyleOptions");
    }

    public void setCustomMapStylePath(String str) {
        Log.e("amap", "该方法已无效，请到官网(lbs.amap.com)更新新版样式文件并使用setCustomMapStyleOptions");
    }

    public void setCustomMapStyle(CustomMapStyleOptions customMapStyleOptions) {
        this.mapDelegate.setCustomMapStyle(customMapStyleOptions);
    }

    public void setCustomMapStyleID(String str) {
        Log.e("amap", "该方法已无效，请到官网(lbs.amap.com)更新新版样式文件并使用setCustomMapStyleOptions");
    }

    public void setCustomTextureResourcePath(String str) {
        Log.e("amap", "该方法已无效，请到官网(lbs.amap.com)更新新版样式文件并使用setCustomMapStyleOptions");
    }

    public void setRenderMode(int i) {
        this.mapDelegate.setRenderMode(i);
    }

    public void getP20MapCenter(IPoint iPoint) {
        if (iPoint == null) {
            try {
                iPoint = new IPoint();
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        iPoint.x = this.mapDelegate.getSX();
        iPoint.y = this.mapDelegate.getSY();
    }

    public String getMapContentApprovalNumber() {
        try {
            return this.mapDelegate.getMapContentApprovalNumber();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String getSatelliteImageApprovalNumber() {
        try {
            return this.mapDelegate.getSatelliteImageApprovalNumber();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setMapLanguage(String str) {
        try {
            this.mapDelegate.setMapLanguage(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setRoadArrowEnable(boolean z) {
        try {
            this.mapDelegate.setRoadArrowEnable(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setNaviLabelEnable(boolean z, int i, int i2) {
        try {
            this.mapDelegate.setNaviLabelEnable(z, i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setTouchPoiEnable(boolean z) {
        try {
            this.mapDelegate.setTouchPoiEnable(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isTouchPoiEnable() {
        try {
            return this.mapDelegate.isTouchPoiEnable();
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public long getNativeMapController() {
        try {
            return this.mapDelegate.getNativeMapController();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    public final void addOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) {
        try {
            this.mapDelegate.addOnCameraChangeListener(onCameraChangeListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addOnMapClickListener(OnMapClickListener onMapClickListener) {
        try {
            this.mapDelegate.addOnMapClickListener(onMapClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        try {
            this.mapDelegate.addOnMarkerDragListener(onMarkerDragListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener) {
        try {
            this.mapDelegate.addOnMapLoadedListener(onMapLoadedListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        try {
            this.mapDelegate.addOnMapTouchListener(onMapTouchListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        try {
            this.mapDelegate.addOnMarkerClickListener(onMarkerClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        try {
            this.mapDelegate.addOnPolylineClickListener(onPolylineClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void addOnPOIClickListener(OnPOIClickListener onPOIClickListener) {
        try {
            this.mapDelegate.addOnPOIClickListener(onPOIClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        try {
            this.mapDelegate.addOnMapLongClickListener(onMapLongClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) {
        try {
            this.mapDelegate.addOnInfoWindowClickListener(onInfoWindowClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) {
        try {
            this.mapDelegate.addOnIndoorBuildingActiveListener(onIndoorBuildingActiveListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        try {
            this.mapDelegate.addOnMyLocationChangeListener(onMyLocationChangeListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) {
        try {
            this.mapDelegate.removeOnCameraChangeListener(onCameraChangeListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnMapClickListener(OnMapClickListener onMapClickListener) {
        try {
            this.mapDelegate.removeOnMapClickListener(onMapClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        try {
            this.mapDelegate.removeOnMarkerDragListener(onMarkerDragListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener) {
        try {
            this.mapDelegate.removeOnMapLoadedListener(onMapLoadedListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        try {
            this.mapDelegate.removeOnMapTouchListener(onMapTouchListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        try {
            this.mapDelegate.removeOnMarkerClickListener(onMarkerClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        try {
            this.mapDelegate.removeOnPolylineClickListener(onPolylineClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void removeOnPOIClickListener(OnPOIClickListener onPOIClickListener) {
        try {
            this.mapDelegate.removeOnPOIClickListener(onPOIClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        try {
            this.mapDelegate.removeOnMapLongClickListener(onMapLongClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) {
        try {
            this.mapDelegate.removeOnInfoWindowClickListener(onInfoWindowClickListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) {
        try {
            this.mapDelegate.removeOnIndoorBuildingActiveListener(onIndoorBuildingActiveListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        try {
            this.mapDelegate.removeOnMyLocationChangeListener(onMyLocationChangeListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setWorldVectorMapStyle(String str) {
        try {
            this.mapDelegate.setWorldVectorMapStyle(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getCurrentStyle() {
        try {
            return this.mapDelegate != null ? this.mapDelegate.getCurrentWorldVectorMapStyle() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public void accelerateNetworkInChinese(boolean z) {
        try {
            if (this.mapDelegate != null) {
                this.mapDelegate.accelerateNetworkInChinese(z);
            }
        } catch (Throwable unused) {
        }
    }
}