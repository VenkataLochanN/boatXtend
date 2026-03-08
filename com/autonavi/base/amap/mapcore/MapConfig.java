package com.autonavi.base.amap.mapcore;

import android.opengl.Matrix;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMapConfig;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class MapConfig implements IMapConfig, Cloneable {
    public static final int DEFAULT_RATIO = 1;
    private static final int GEO_POINT_ZOOM = 20;
    public static final float MAX_ZOOM = 20.0f;
    public static final float MAX_ZOOM_INDOOR = 20.0f;
    public static final float MIN_ZOOM = 3.0f;
    public static final int MSG_ACTION_ONBASEPOICLICK = 20;
    public static final int MSG_ACTION_ONMAPCLICK = 19;
    public static final int MSG_AUTH_FAILURE = 2;
    public static final int MSG_CALLBACK_MAPLOADED = 16;
    public static final int MSG_CALLBACK_ONTOUCHEVENT = 14;
    public static final int MSG_CALLBACK_SCREENSHOT = 15;
    public static final int MSG_CAMERAUPDATE_CHANGE = 10;
    public static final int MSG_CAMERAUPDATE_FINISH = 11;
    public static final int MSG_COMPASSVIEW_CHANGESTATE = 13;
    public static final int MSG_INFOWINDOW_UPDATE = 18;
    public static final int MSG_TILEOVERLAY_REFRESH = 17;
    public static final int MSG_ZOOMVIEW_CHANGESTATE = 12;
    private static final int TILE_SIZE_POW = 8;
    private String customTextureResourcePath;
    private boolean isSetLimitZoomLevel;
    MapConfig lastMapconfig;
    private IPoint[] limitIPoints;
    private LatLngBounds limitLatLngBounds;
    private String mCustomStyleID;
    private String mCustomStylePath;
    private int mapHeight;
    private float mapPerPixelUnitLength;
    private int mapWidth;
    private float skyHeight;
    public float maxZoomLevel = 20.0f;
    public float minZoomLevel = 3.0f;
    private FPoint[] mapRect = null;
    private Rectangle geoRectangle = new Rectangle();
    private boolean isIndoorEnable = false;
    private boolean isBuildingEnable = true;
    private boolean isMapTextEnable = true;
    private boolean isTrafficEnabled = false;
    private boolean isCustomStyleEnabled = false;
    private double sX = 2.21010267E8d;
    private double sY = 1.01697799E8d;
    private DPoint mapGeoCenter = new DPoint(this.sX, this.sY);
    private float sZ = 10.0f;
    private float sC = 0.0f;
    private float sR = 0.0f;
    private boolean isCenterChanged = false;
    private boolean isZoomChanged = false;
    private boolean isTiltChanged = false;
    private boolean isBearingChanged = false;
    private boolean isNeedUpdateZoomControllerState = false;
    private boolean isNeedUpdateMapRectNextFrame = false;
    private int mMapStyleMode = 0;
    private int mMapStyleTime = 0;
    private int mMapStyleState = 0;
    private int anchorX = 0;
    private String mMapLanguage = "zh_cn";
    private boolean isHideLogoEnable = false;
    private boolean isWorldMapEnable = false;
    private boolean isTouchPoiEnable = true;
    private int abroadState = 1;
    private boolean isAbroadEnable = false;
    float[] viewMatrix = new float[16];
    float[] projectionMatrix = new float[16];
    float[] mvpMatrix = new float[16];
    private int[] tilsIDs = new int[100];
    private boolean mapEnable = true;
    private int anchorY = 0;
    private boolean isProFunctionAuthEnable = true;
    private boolean isUseProFunction = false;
    private int customBackgroundColor = -1;
    private float mapZoomScale = 1.0f;
    private AtomicInteger changedCounter = new AtomicInteger(0);
    private volatile double changeRatio = 1.0d;
    private volatile double changeGridRatio = 1.0d;
    private int gridX = 0;
    private int gridY = 0;

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getAnchorY() {
        return this.anchorY;
    }

    public void setAnchorY(int i) {
        this.anchorY = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getAnchorX() {
        return this.anchorX;
    }

    public void setAnchorX(int i) {
        this.anchorX = i;
    }

    public MapConfig(boolean z) {
        this.lastMapconfig = null;
        if (z) {
            this.lastMapconfig = new MapConfig(false);
            this.lastMapconfig.setGridXY(0, 0);
            this.lastMapconfig.setSX(0.0d);
            this.lastMapconfig.setSY(0.0d);
            this.lastMapconfig.setSZ(0.0f);
            this.lastMapconfig.setSC(0.0f);
            this.lastMapconfig.setSR(0.0f);
        }
    }

    public int getChangedCounter() {
        return this.changedCounter.get();
    }

    public void resetChangedCounter() {
        this.changedCounter.set(0);
    }

    public void addChangedCounter() {
        this.changedCounter.incrementAndGet();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isMapStateChange() {
        /*
            r11 = this;
            com.autonavi.base.amap.mapcore.MapConfig r0 = r11.lastMapconfig
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lad
            double r3 = r0.getSX()
            com.autonavi.base.amap.mapcore.MapConfig r0 = r11.lastMapconfig
            double r5 = r0.getSY()
            com.autonavi.base.amap.mapcore.MapConfig r0 = r11.lastMapconfig
            float r0 = r0.getSZ()
            com.autonavi.base.amap.mapcore.MapConfig r7 = r11.lastMapconfig
            float r7 = r7.getSC()
            com.autonavi.base.amap.mapcore.MapConfig r8 = r11.lastMapconfig
            float r8 = r8.getSR()
            double r9 = r11.sX
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 == 0) goto L2a
            r3 = r1
            goto L2b
        L2a:
            r3 = r2
        L2b:
            r11.isCenterChanged = r3
            double r3 = r11.sY
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 == 0) goto L35
            r3 = r1
            goto L37
        L35:
            boolean r3 = r11.isCenterChanged
        L37:
            r11.isCenterChanged = r3
            float r3 = r11.sZ
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 == 0) goto L41
            r3 = r1
            goto L42
        L41:
            r3 = r2
        L42:
            r11.isZoomChanged = r3
            boolean r3 = r11.isZoomChanged
            if (r3 == 0) goto L64
            float r3 = r11.minZoomLevel
            int r4 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r4 <= 0) goto L62
            float r4 = r11.sZ
            int r3 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r3 <= 0) goto L62
            float r3 = r11.maxZoomLevel
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L62
            int r0 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r0 < 0) goto L5f
            goto L62
        L5f:
            r11.isNeedUpdateZoomControllerState = r2
            goto L64
        L62:
            r11.isNeedUpdateZoomControllerState = r1
        L64:
            float r0 = r11.sC
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 == 0) goto L6c
            r0 = r1
            goto L6d
        L6c:
            r0 = r2
        L6d:
            r11.isTiltChanged = r0
            float r0 = r11.sR
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 == 0) goto L77
            r0 = r1
            goto L78
        L77:
            r0 = r2
        L78:
            r11.isBearingChanged = r0
            boolean r0 = r11.isCenterChanged
            if (r0 != 0) goto L91
            boolean r0 = r11.isZoomChanged
            if (r0 != 0) goto L91
            boolean r0 = r11.isTiltChanged
            if (r0 != 0) goto L91
            boolean r0 = r11.isBearingChanged
            if (r0 != 0) goto L91
            boolean r0 = r11.isNeedUpdateMapRectNextFrame
            if (r0 == 0) goto L8f
            goto L91
        L8f:
            r0 = r2
            goto L92
        L91:
            r0 = r1
        L92:
            if (r0 == 0) goto Lae
            r11.isNeedUpdateMapRectNextFrame = r2
            float r2 = r11.sZ
            int r2 = (int) r2
            double r3 = r11.sX
            int r3 = (int) r3
            int r2 = 20 - r2
            int r2 = r2 + 8
            int r3 = r3 >> r2
            double r4 = r11.sY
            int r4 = (int) r4
            int r2 = r4 >> r2
            r11.setGridXY(r3, r2)
            r11.changeRatio()
            goto Lae
        Lad:
            r0 = r2
        Lae:
            r2 = 45
            float r3 = r11.sC
            float r2 = (float) r2
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 < 0) goto Lbf
            float r2 = r11.skyHeight
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto Lbf
            return r1
        Lbf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.base.amap.mapcore.MapConfig.isMapStateChange():boolean");
    }

    protected void setGridXY(int i, int i2) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setGridXY(this.gridX, this.gridY);
        }
        this.gridX = i;
        this.gridY = i2;
    }

    protected int getGridX() {
        return this.gridX;
    }

    protected int getGridY() {
        return this.gridY;
    }

    public double getChangeRatio() {
        return this.changeRatio;
    }

    public double getChangeGridRatio() {
        return this.changeGridRatio;
    }

    private void changeRatio() {
        double sx = this.lastMapconfig.getSX();
        double sy = this.lastMapconfig.getSY();
        float sz = this.lastMapconfig.getSZ();
        float sc = this.lastMapconfig.getSC();
        float sr = this.lastMapconfig.getSR();
        this.changeRatio = Math.abs(this.sX - sx) + Math.abs(this.sY - sy);
        this.changeRatio = this.changeRatio == 0.0d ? 1.0d : this.changeRatio * 2.0d;
        this.changeRatio = this.changeRatio * (sz == this.sZ ? 1.0d : Math.abs(sz - r11));
        float f2 = this.sC;
        float fAbs = sc == f2 ? 1.0f : Math.abs(sc - f2);
        float f3 = this.sR;
        float fAbs2 = sr != f3 ? Math.abs(sr - f3) : 1.0f;
        double d2 = fAbs;
        this.changeRatio *= d2;
        double d3 = fAbs2;
        this.changeRatio *= d3;
        this.changeGridRatio = Math.abs(this.lastMapconfig.getGridX() - this.gridX) + (this.lastMapconfig.getGridY() - this.gridY);
        this.changeGridRatio = this.changeGridRatio != 0.0d ? this.changeGridRatio * 2.0d : 1.0d;
        this.changeGridRatio *= d2;
        this.changeGridRatio *= d3;
    }

    public String toString() {
        return " sX: " + this.sX + " sY: " + this.sY + " sZ: " + this.sZ + " sC: " + this.sC + " sR: " + this.sR + " skyHeight: " + this.skyHeight;
    }

    public boolean isZoomChanged() {
        return this.isZoomChanged;
    }

    public boolean isTiltChanged() {
        return this.isTiltChanged;
    }

    public boolean isBearingChanged() {
        return this.isBearingChanged;
    }

    public boolean isIndoorEnable() {
        return this.isIndoorEnable;
    }

    public void setIndoorEnable(boolean z) {
        this.isIndoorEnable = z;
    }

    public boolean isBuildingEnable() {
        return this.isBuildingEnable;
    }

    public void setBuildingEnable(boolean z) {
        this.isBuildingEnable = z;
    }

    public boolean isMapTextEnable() {
        return this.isMapTextEnable;
    }

    public void setMapTextEnable(boolean z) {
        this.isMapTextEnable = z;
    }

    public boolean isTrafficEnabled() {
        return this.isTrafficEnabled;
    }

    public void setTrafficEnabled(boolean z) {
        this.isTrafficEnabled = z;
    }

    public boolean isNeedUpdateZoomControllerState() {
        return this.isNeedUpdateZoomControllerState;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public double getSX() {
        return this.sX;
    }

    public void setSX(double d2) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSX(this.sX);
        }
        this.sX = d2;
        this.mapGeoCenter.x = this.sX;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public double getSY() {
        return this.sY;
    }

    public void setSY(double d2) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSY(this.sY);
        }
        this.sY = d2;
        this.mapGeoCenter.x = this.sY;
    }

    public DPoint getMapGeoCenter() {
        return this.mapGeoCenter;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getSZ() {
        return this.sZ;
    }

    public void setSZ(float f2) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSZ(this.sZ);
        }
        this.sZ = f2;
    }

    public float getSC() {
        return this.sC;
    }

    public void setSC(float f2) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSC(this.sC);
        }
        this.sC = f2;
    }

    public float getSR() {
        return this.sR;
    }

    public void setSR(float f2) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setSR(this.sR);
        }
        this.sR = f2;
    }

    public FPoint[] getMapRect() {
        return this.mapRect;
    }

    public void setMapRect(FPoint[] fPointArr) {
        MapConfig mapConfig = this.lastMapconfig;
        if (mapConfig != null) {
            mapConfig.setMapRect(fPointArr);
        }
        this.mapRect = fPointArr;
    }

    public Rectangle getGeoRectangle() {
        return this.geoRectangle;
    }

    public void setMaxZoomLevel(float f2) {
        if (f2 > 20.0f) {
            f2 = 20.0f;
        }
        if (f2 < 3.0f) {
            f2 = 3.0f;
        }
        if (f2 < getMinZoomLevel()) {
            f2 = getMinZoomLevel();
        }
        this.isSetLimitZoomLevel = true;
        this.maxZoomLevel = f2;
    }

    public void setMinZoomLevel(float f2) {
        if (f2 < 3.0f) {
            f2 = 3.0f;
        }
        if (f2 > 20.0f) {
            f2 = 20.0f;
        }
        if (f2 > getMaxZoomLevel()) {
            f2 = getMaxZoomLevel();
        }
        this.isSetLimitZoomLevel = true;
        this.minZoomLevel = f2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getMaxZoomLevel() {
        return this.maxZoomLevel;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getMinZoomLevel() {
        return this.minZoomLevel;
    }

    public IPoint[] getLimitIPoints() {
        return this.limitIPoints;
    }

    public void setLimitIPoints(IPoint[] iPointArr) {
        this.limitIPoints = iPointArr;
    }

    public boolean isSetLimitZoomLevel() {
        return this.isSetLimitZoomLevel;
    }

    public LatLngBounds getLimitLatLngBounds() {
        return this.limitLatLngBounds;
    }

    public void setLimitLatLngBounds(LatLngBounds latLngBounds) {
        this.limitLatLngBounds = latLngBounds;
        if (latLngBounds == null) {
            resetMinMaxZoomPreference();
        }
    }

    public void resetMinMaxZoomPreference() {
        this.minZoomLevel = 3.0f;
        this.maxZoomLevel = 20.0f;
        this.isSetLimitZoomLevel = false;
    }

    public void updateMapRectNextFrame(boolean z) {
        this.isNeedUpdateMapRectNextFrame = z;
    }

    public void setMapPerPixelUnitLength(float f2) {
        this.mapPerPixelUnitLength = f2;
    }

    public float getMapPerPixelUnitLength() {
        return this.mapPerPixelUnitLength;
    }

    public void setCustomStylePath(String str) {
        this.mCustomStylePath = str;
    }

    public String getCustomStylePath() {
        return this.mCustomStylePath;
    }

    public String getCustomStyleID() {
        return this.mCustomStyleID;
    }

    public void setCustomStyleID(String str) {
        this.mCustomStyleID = str;
    }

    public void setCustomStyleEnable(boolean z) {
        this.isCustomStyleEnabled = z;
    }

    public boolean isCustomStyleEnable() {
        return this.isCustomStyleEnabled;
    }

    public int getMapStyleTime() {
        return this.mMapStyleTime;
    }

    public void setMapStyleTime(int i) {
        this.mMapStyleTime = i;
    }

    public int getMapStyleMode() {
        return this.mMapStyleMode;
    }

    public void setMapStyleMode(int i) {
        this.mMapStyleMode = i;
    }

    public int getMapStyleState() {
        return this.mMapStyleState;
    }

    public void setMapStyleState(int i) {
        this.mMapStyleState = i;
    }

    public void setCustomTextureResourcePath(String str) {
        this.customTextureResourcePath = str;
    }

    public String getCustomTextureResourcePath() {
        return this.customTextureResourcePath;
    }

    public boolean isProFunctionAuthEnable() {
        return this.isProFunctionAuthEnable;
    }

    public void setProFunctionAuthEnable(boolean z) {
        this.isProFunctionAuthEnable = z;
    }

    public boolean isUseProFunction() {
        return this.isUseProFunction;
    }

    public void setUseProFunction(boolean z) {
        this.isUseProFunction = z;
    }

    public void setCustomBackgroundColor(int i) {
        this.customBackgroundColor = i;
    }

    public int getCustomBackgroundColor() {
        return this.customBackgroundColor;
    }

    public void setMapZoomScale(float f2) {
        this.mapZoomScale = f2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public float getMapZoomScale() {
        return this.mapZoomScale;
    }

    public void setMapWidth(int i) {
        this.mapWidth = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getMapWidth() {
        return this.mapWidth;
    }

    public void setMapHeight(int i) {
        this.mapHeight = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getMapHeight() {
        return this.mapHeight;
    }

    public void setMapLanguage(String str) {
        this.mMapLanguage = str;
    }

    public String getMapLanguage() {
        return this.mMapLanguage;
    }

    public void setHideLogoEnble(boolean z) {
        this.isHideLogoEnable = z;
    }

    public boolean isHideLogoEnable() {
        return this.isHideLogoEnable;
    }

    public void setWorldMapEnable(boolean z) {
        this.isWorldMapEnable = z;
    }

    public boolean isWorldMapEnable() {
        return this.isWorldMapEnable;
    }

    public float getSkyHeight() {
        return this.skyHeight;
    }

    public void setSkyHeight(float f2) {
        this.skyHeight = f2;
    }

    public float[] getViewMatrix() {
        return this.viewMatrix;
    }

    public float[] getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public float[] getMvpMatrix() {
        return this.mvpMatrix;
    }

    public void updateFinalMatrix() {
        Matrix.multiplyMM(this.mvpMatrix, 0, this.projectionMatrix, 0, this.viewMatrix, 0);
    }

    public int[] getCurTileIds() {
        return this.tilsIDs;
    }

    public boolean isTouchPoiEnable() {
        return this.isTouchPoiEnable;
    }

    public void setTouchPoiEnable(boolean z) {
        this.isTouchPoiEnable = z;
    }

    public boolean isMapEnable() {
        return this.mapEnable;
    }

    public void setMapEnable(boolean z) {
        this.mapEnable = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public int getAbroadState() {
        return this.abroadState;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public void setAbroadState(int i) {
        this.abroadState = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public boolean isAbroadEnable() {
        return this.isAbroadEnable;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMapConfig
    public void setAbroadEnable(boolean z) {
        this.isAbroadEnable = z;
    }
}