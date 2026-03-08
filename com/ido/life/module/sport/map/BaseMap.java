package com.ido.life.module.sport.map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.InputDeviceCompat;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.Units;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.NumUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.constants.Constants;
import com.ido.life.data.ExecutorDispatcher;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.DateUtil;
import com.ido.life.util.FileUtil;
import com.ido.life.util.ObjectUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.ThreadUtil;
import com.ido.life.util.UnitUtil;
import com.veryfit.multi.nativeprotocol.b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.CharCompanionObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseMap<T extends View, L> implements MapScreenShotCallback {
    protected static final int HID_MAP_INDEX = 50;
    public static final int INVALID_TRAVEL_COLOR = 1295532630;
    public static final int INVALID_TRAVEL_DISTANCE = 300;
    protected static final int LINE_INDEX = 102;
    protected static final int MARKET_INDEX = 200;
    public static final int RUN_COLOR_DEFAULT = -15156300;
    public static final int SPORT_TYPE_CLIMB = 6;
    public static final int SPORT_TYPE_CYCLING = 3;
    public static final int SPORT_TYPE_NULL = 0;
    public static final int SPORT_TYPE_ONFOOT = 4;
    public static final int SPORT_TYPE_RUN = 2;
    public static final int SPORT_TYPE_SWIM = 5;
    public static final int SPORT_TYPE_WALK = 1;
    private static final String TAG = "BaseMap";
    public static final float speedThreshold = 2.0f;
    protected Activity activity;
    protected LatLngBean centerLatlng;
    protected LatLngBean currentLatLng;
    protected int distanceUnit;
    protected boolean isBaidu;
    private LatLngBean mCurrentLatLngBean;
    protected boolean mIsFinish;
    protected T mapView;
    public int paddingButtom;
    protected float[] speeds;
    protected int sporType;
    protected long startTime;
    protected static final int LINE_WIDTH = DipPixelUtil.dip2px(6.0f);
    protected static int UPDATE_INTERVAL = b.f4;
    protected static int MAP_SHOT_TIME_OUT = 5000;
    protected static int MAP_LOAD_TIME_OUT = MAP_SHOT_TIME_OUT * 2;
    public static final float[] runSpeed = {3.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f};
    public static final float[] bikeSpeed = {21.0f, 22.0f, 23.0f, 24.0f, 25.0f, 26.0f, 30.0f};
    public static final float[] walkSpeed = {3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 7.5f, 8.0f};
    public static final int[] colorArray = {com.ido.life.location.MapHelper.Standard_Color, -8913152, -4456704, InputDeviceCompat.SOURCE_ANY, -17664, -30720, -43759, -65536};
    int METRIC = 1;
    int BRITISH = 2;

    @Deprecated
    String IS_CM = "IS_CM";

    @Deprecated
    String WEIGHT_UNIT = "WEIGHT_UNIT";
    public boolean isHideMapView = false;
    public boolean mIsRunMap = true;
    public boolean isAddCircle = true;
    protected int mapLoadDelay = 1000;
    protected float zoomLevel = 17.0f;
    public List<LatLngBean> latLngBeanList = new ArrayList();
    public List<Integer> mInvalidLngLatList = new ArrayList();
    protected LatLngBean lastLatLng = null;
    protected boolean isMarker = true;
    protected List<L> latLngList = new ArrayList();
    protected boolean onMapLoaded = false;
    protected List<Integer> colorList = new ArrayList();
    protected List<Integer> mileMarkList = new ArrayList();
    protected Handler mapShotTimeOutHanlder = new Handler();

    public interface IDrawFinish {
        void onDrawFinish();
    }

    public abstract void addCurrentMarker(LatLngBean latLngBean, int i);

    public abstract void addDynamicEndMark(LatLngBean latLngBean, int i);

    public abstract void addDynamicStartMark(LatLngBean latLngBean, int i);

    public abstract void addEndMarker(LatLngBean latLngBean, int i);

    public abstract void addMarker(LatLngBean latLngBean, int i);

    public abstract void addMileMark();

    public abstract void addPolyline(LatLngBean latLngBean);

    public abstract void ajustMapView();

    public abstract void animateCamera(LatLngBean latLngBean);

    public abstract void clearMarker();

    protected abstract void drawPolyline();

    protected abstract L fromLatLngBean(LatLngBean latLngBean);

    public int getMinZoomLevel() {
        return 3;
    }

    public abstract void moveCamera(LatLngBean latLngBean);

    public void onCreate(Bundle bundle) {
    }

    public void onDestroy() {
    }

    public abstract void onMapScreenShot(MapScreenShotCallback mapScreenShotCallback);

    public void onPause() {
    }

    public void onResume() {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    protected abstract void preDrawAllAndShot();

    public abstract void setGesturesEnabled(boolean z);

    public abstract void setMapType(boolean z);

    protected abstract void showOrHideMap(LatLngBean latLngBean);

    public abstract List<Point> toScreenLocation(List<LatLngBean> list);

    public void setAddCircle(boolean z) {
        this.isAddCircle = z;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setPaddingButtom(int i) {
        this.paddingButtom = i;
    }

    public T getMapView() {
        return this.mapView;
    }

    public void checkMapLoaded(final OnMapLoadedListener onMapLoadedListener) {
        ExecutorDispatcher.getInstance().dispatch(new Runnable() { // from class: com.ido.life.module.sport.map.-$$Lambda$BaseMap$tMXgTGzIQOKLh6kLjzpMyF47bUo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$checkMapLoaded$1$BaseMap(onMapLoadedListener);
            }
        });
    }

    public /* synthetic */ void lambda$checkMapLoaded$1$BaseMap(final OnMapLoadedListener onMapLoadedListener) {
        long j = 300;
        int i = 0;
        while (!this.onMapLoaded && !this.mIsFinish) {
            try {
                CommonLogUtil.printAndSave("checkMapLoaded：" + i + "，delay：" + j);
                TimeUnit.MILLISECONDS.sleep(j);
                i++;
                if (i > 3) {
                    j = 1000;
                }
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                return;
            }
        }
        ExecutorDispatcher.getInstance().dispatchOnMainThread(new Runnable() { // from class: com.ido.life.module.sport.map.-$$Lambda$BaseMap$2-RGXYkzM46agUrTw-1ZUdz6Nws
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$null$0$BaseMap(onMapLoadedListener);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$BaseMap(OnMapLoadedListener onMapLoadedListener) {
        if (this.mIsFinish || onMapLoadedListener == null) {
            return;
        }
        onMapLoadedListener.onMapLoad(this.onMapLoaded);
    }

    public void getGoogleMap(final OnMapLoadedListener onMapLoadedListener) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.sport.map.BaseMap.1
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), BaseMap.TAG, "doInBackground: getGoogleMap");
                if (BaseMap.this.onMapLoaded || BaseMap.this.mIsFinish) {
                    return null;
                }
                try {
                    Thread.sleep(2000L);
                    return null;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                OnMapLoadedListener onMapLoadedListener2;
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), BaseMap.TAG, "onPostExecute: getGoogleMap");
                if (!BaseMap.this.onMapLoaded && !BaseMap.this.mIsFinish) {
                    OnMapLoadedListener onMapLoadedListener3 = onMapLoadedListener;
                    if (onMapLoadedListener3 != null) {
                        onMapLoadedListener3.onMapLoad(false);
                        BaseMap.this.getGoogleMap(onMapLoadedListener);
                    }
                } else if (!BaseMap.this.mIsFinish && (onMapLoadedListener2 = onMapLoadedListener) != null) {
                    onMapLoadedListener2.onMapLoad(true);
                }
                CommonLogUtil.d(BaseMap.TAG, "地图view对象 is not null ");
            }
        }).execute("");
    }

    public void getMap(final OnMapLoadedListener onMapLoadedListener) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.sport.map.BaseMap.2
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                while (BaseMap.this.mapView == null) {
                    try {
                        CommonLogUtil.d("地图view对象还未获取到 is null");
                        if (System.currentTimeMillis() - jCurrentTimeMillis > BaseMap.MAP_LOAD_TIME_OUT) {
                            if (onMapLoadedListener == null) {
                                return null;
                            }
                            onMapLoadedListener.onMapLoad(BaseMap.this.mapView != null);
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
                    onMapLoadedListener2.onMapLoad(BaseMap.this.mapView != null);
                }
                CommonLogUtil.d(BaseMap.TAG, "地图view对象 is not null ");
            }
        }).execute("");
    }

    public double getDistance(LatLngBean latLngBean, LatLngBean latLngBean2) {
        return com.ido.life.location.MapHelper.calculateLineDistance(latLngBean, latLngBean2);
    }

    protected void convert(LatLngBean latLngBean) {
        LatLng latLng = new LatLng(latLngBean.latitude, latLngBean.longitude);
        CoordinateConverter coordinateConverter = new CoordinateConverter(IdoApp.getAppContext());
        coordinateConverter.from(CoordinateConverter.CoordType.GPS);
        coordinateConverter.coord(latLng);
        LatLng latLngConvert = coordinateConverter.convert();
        latLngBean.latitude = latLngConvert.latitude;
        latLngBean.longitude = latLngConvert.longitude;
    }

    public void initMapView(T t) {
        this.mapView = t;
        Units units = LocalDataManager.getUnits();
        if (units == null) {
            units = new Units();
            units.dist = ((Boolean) SPUtils.get(this.IS_CM, true)).booleanValue() ? 1 : 2;
        }
        this.distanceUnit = units.dist;
    }

    public void addPolylineAndMove(LatLngBean latLngBean, boolean z) {
        addPolyline(latLngBean);
        this.latLngBeanList.add(latLngBean);
        if (this.latLngBeanList.size() == 1) {
            if (this.mIsRunMap) {
                addMarker(latLngBean, R.mipmap.ic_sport_map_go);
            } else {
                addEndMarker(latLngBean, R.mipmap.ic_sport_map_detail_start);
            }
        }
        animateCamera(latLngBean);
    }

    public void addCurrentMark(LatLngBean latLngBean) {
        if (this.latLngBeanList.size() <= 0 || !this.latLngBeanList.get(0).equals(latLngBean)) {
            addCurrentMarker(latLngBean, R.mipmap.ic_sport_map_end);
        }
    }

    public void move2CurrentLocation() {
        List<LatLngBean> list = this.latLngBeanList;
        if (list == null || list.size() == 0) {
            return;
        }
        animateCamera(this.latLngBeanList.get(r0.size() - 1));
    }

    public void drawAllPoint() {
        if (this.mapView == null || ObjectUtil.isCollectionEmpty(getLatLngBeanList())) {
            return;
        }
        preDrawAllAndShot();
        this.latLngList.clear();
        List<LatLngBean> latLngBeanList = getLatLngBeanList();
        if (latLngBeanList != null && latLngBeanList.size() > 0) {
            addMarker(latLngBeanList.get(0), R.mipmap.ic_sport_map_detail_start);
            culSpeedColors();
            int size = latLngBeanList.size();
            for (int i = 0; i < size; i++) {
                this.latLngList.add(fromLatLngBean(latLngBeanList.get(i)));
            }
            if (this.latLngList.size() >= 2) {
                drawPolyline();
            }
        }
        ajustMapView();
    }

    public void drawAllAndShot(long j, final int i, final int i2, final int i3, final boolean z, final IDrawFinish iDrawFinish) {
        if (this.mapView == null) {
            return;
        }
        this.sporType = i;
        if (ObjectUtil.isCollectionEmpty(getLatLngBeanList())) {
            return;
        }
        preDrawAllAndShot();
        this.latLngList.clear();
        this.startTime = j;
        final List<LatLngBean> latLngBeanList = getLatLngBeanList();
        if (getLatLngBeanList() == null || getLatLngBeanList().size() == 0 || this.mIsFinish) {
            return;
        }
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.sport.map.BaseMap.3
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), BaseMap.TAG, "doInBackground: drawAllAndShot");
                BaseMap baseMap = BaseMap.this;
                baseMap.mInvalidLngLatList = com.ido.life.location.MapHelper.completeColorByMile(baseMap.latLngBeanList, i2, i3, i, BaseMap.this.colorList, null, true);
                return null;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), BaseMap.TAG, "onPostExecute: drawAllAndShot");
                if (BaseMap.this.mapView == null || !BaseMap.this.onMapLoaded || BaseMap.this.mIsFinish) {
                    return;
                }
                if (z) {
                    BaseMap.this.addDynamicStartMark((LatLngBean) latLngBeanList.get(0), R.mipmap.ic_sport_locus_small_start);
                } else {
                    BaseMap.this.addDynamicStartMark((LatLngBean) latLngBeanList.get(0), R.mipmap.ic_sport_map_detail_start);
                }
                List<LatLngBean> latLngBeanList2 = BaseMap.this.getLatLngBeanList();
                int size = latLngBeanList2.size();
                for (int i4 = 0; i4 < size; i4++) {
                    if (BaseMap.this.mIsFinish) {
                        return;
                    }
                    BaseMap.this.latLngList.add((L) BaseMap.this.fromLatLngBean(latLngBeanList2.get(i4)));
                }
                BaseMap.this.drawPolyline();
                if (z) {
                    BaseMap.this.addDynamicEndMark(latLngBeanList2.get(latLngBeanList2.size() - 1), R.mipmap.ic_sport_locus_small_end);
                } else {
                    BaseMap.this.addDynamicEndMark(latLngBeanList2.get(latLngBeanList2.size() - 1), R.mipmap.ic_sport_map_detail_end);
                }
                IDrawFinish iDrawFinish2 = iDrawFinish;
                if (iDrawFinish2 != null) {
                    iDrawFinish2.onDrawFinish();
                }
            }
        }).execute("");
    }

    private void culSpeedColors() {
        this.colorList.clear();
        int size = this.latLngBeanList.size() - 2;
        LatLngBean latLngBean = null;
        double d2 = 0.0d;
        int i = 0;
        while (i < size) {
            LatLngBean latLngBean2 = latLngBean == null ? this.latLngBeanList.get(0) : latLngBean;
            LatLngBean latLngBean3 = this.latLngBeanList.get(i);
            i++;
            LatLngBean latLngBean4 = this.latLngBeanList.get(i);
            double dAbs = d2 + Math.abs(getDistance(latLngBean3, latLngBean4));
            this.colorList.add(Integer.valueOf(getColorList(latLngBean2, latLngBean3, latLngBean4, dAbs)));
            d2 = dAbs;
            latLngBean = latLngBean2;
        }
    }

    protected int getColorList(LatLngBean latLngBean, LatLngBean latLngBean2, LatLngBean latLngBean3, double d2) {
        long longFromDateStr = DateUtil.getLongFromDateStr(latLngBean.currentTimeMillis);
        long longFromDateStr2 = DateUtil.getLongFromDateStr(latLngBean2.currentTimeMillis);
        long longFromDateStr3 = DateUtil.getLongFromDateStr(latLngBean3.currentTimeMillis);
        long jAbs = Math.abs(longFromDateStr - longFromDateStr3) / 1000;
        if (jAbs == 0) {
            jAbs = 1;
        }
        double d3 = (d2 / jAbs) * 3.6d;
        double dAbs = (Math.abs(getDistance(latLngBean2, latLngBean3)) / (Math.abs(longFromDateStr3 - longFromDateStr2) / 1000 != 0 ? r5 : 1L)) * 3.6d;
        if (Math.abs(d3 - dAbs) > 2.0d) {
            d3 = (d3 + dAbs) / 2.0d;
        }
        setSpeedRange();
        if (this.mIsRunMap) {
            return RUN_COLOR_DEFAULT;
        }
        int i = 0;
        while (true) {
            if (i >= this.speeds.length) {
                return colorArray[r1.length - 1];
            }
            if (d3 < r2[i]) {
                return colorArray[i];
            }
            i++;
        }
    }

    private void completeColorByMile(List<LatLngBean> list, int i, int i2, int i3) {
        char c2;
        float f2;
        float f3;
        float f4;
        long j;
        long j2;
        long j3;
        List<LatLngBean> list2 = list;
        int unitSet = RunTimeUtil.getInstance().getUnitSet();
        int i4 = 2;
        int i5 = 0;
        char c3 = CharCompanionObject.MIN_VALUE;
        float f5 = 1000.0f;
        if (unitSet == 2) {
            float f6 = i2 / 1000.0f;
            if (UnitUtil.getKm2mile(f6) <= 1.0f && i > 0) {
                setColorByPace(i2 == 0 ? 0 : (int) (UnitUtil.getKm2mile(f6) / (i / 3600.0f)), list.size(), this.sporType, this.mInvalidLngLatList);
            }
        } else if (i2 <= 1000 && i > 0) {
            setColorByPace(i2 == 0 ? 0 : (int) ((i2 / 1000.0f) / (i / 3600.0f)), list.size(), this.sporType, this.mInvalidLngLatList);
        }
        float fCalculateLineDistance = 0.0f;
        float f7 = 0.0f;
        long j4 = 0;
        int i6 = 1;
        long j5 = 0;
        while (i5 < list.size()) {
            if (i5 > 0) {
                int i7 = i5 - 1;
                if (com.ido.life.location.MapHelper.calculateLineDistance(list2.get(i7), list2.get(i5)) > 300.0f) {
                    this.mInvalidLngLatList.add(Integer.valueOf(i7));
                    this.mInvalidLngLatList.add(Integer.valueOf(i5));
                }
                fCalculateLineDistance += com.ido.life.location.MapHelper.calculateLineDistance(list2.get(i7), list2.get(i5));
                try {
                    long longFromDateStr = (long) (j4 + ((DateUtil.getLongFromDateStr(list2.get(i5).currentTimeMillis) - DateUtil.getLongFromDateStr(list2.get(i7).currentTimeMillis)) / f5));
                    if (RunTimeUtil.getInstance().getUnitSet() == i4) {
                        if (UnitUtil.getKm2mile(fCalculateLineDistance / f5) >= i6) {
                            if (j5 == 0) {
                                f7 = fCalculateLineDistance;
                                j2 = longFromDateStr;
                                j3 = j2;
                            } else {
                                f7 = fCalculateLineDistance - f7;
                                j2 = longFromDateStr;
                                j3 = longFromDateStr - j5;
                            }
                            if (j3 > 0) {
                                float f8 = f7 / 1000.0f;
                                float f9 = j3 / 3600.0f;
                                int km2mile = (int) (UnitUtil.getKm2mile(f8) / f9);
                                CommonLogUtil.d(TAG, "completeColorByMile: " + f8 + AppInfo.DELIM + j3 + AppInfo.DELIM + f9 + AppInfo.DELIM + km2mile);
                                i6++;
                                setColorByPace(km2mile, i5, this.sporType, this.mInvalidLngLatList);
                            }
                            j5 = j3;
                        } else {
                            j2 = longFromDateStr;
                            if (i5 == list.size() - 1 && i > 0 && i2 > 0) {
                                setColorByPace((int) (UnitUtil.getKm2mile(i2 / 1000) / (i / 3600.0f)), i5, this.sporType, this.mInvalidLngLatList);
                            }
                        }
                        j4 = j2;
                        f2 = 1000.0f;
                        c2 = CharCompanionObject.MIN_VALUE;
                    } else if (fCalculateLineDistance / f5 >= i6) {
                        if (j5 == 0) {
                            f3 = fCalculateLineDistance;
                            f4 = f3;
                            j = longFromDateStr;
                        } else {
                            long j6 = longFromDateStr - j5;
                            f3 = fCalculateLineDistance;
                            f4 = fCalculateLineDistance - f7;
                            j = j6;
                        }
                        if (j > 0) {
                            float f10 = f4 / 1000.0f;
                            float f11 = j / 3600.0f;
                            int i8 = (int) (f10 / f11);
                            i6++;
                            CommonLogUtil.d(TAG, "completeColorByMile: " + f10 + AppInfo.DELIM + j + AppInfo.DELIM + f11 + AppInfo.DELIM + i8);
                            setColorByPace(i8, i5, this.sporType, this.mInvalidLngLatList);
                        }
                        f7 = f4;
                        j4 = longFromDateStr;
                        f2 = 1000.0f;
                        c2 = CharCompanionObject.MIN_VALUE;
                        j5 = j;
                        fCalculateLineDistance = f3;
                    } else {
                        if (i5 != list.size() - 1 || i <= 0) {
                            f2 = 1000.0f;
                            c2 = CharCompanionObject.MIN_VALUE;
                        } else {
                            f2 = 1000.0f;
                            c2 = CharCompanionObject.MIN_VALUE;
                            setColorByPace((int) ((i2 / 1000.0f) / (i / 3600.0f)), i5, this.sporType, this.mInvalidLngLatList);
                        }
                        fCalculateLineDistance = fCalculateLineDistance;
                        j4 = longFromDateStr;
                    }
                } catch (Exception unused) {
                    return;
                }
            } else {
                float f12 = f5;
                c2 = c3;
                f2 = f12;
            }
            i5++;
            list2 = list;
            i4 = 2;
            char c4 = c2;
            f5 = f2;
            c3 = c4;
        }
    }

    private void setColorByPace(int i, int i2, int i3, List<Integer> list) {
        CommonLogUtil.d(TAG, "ColorByPace: " + i + AppInfo.DELIM + i2 + AppInfo.DELIM + i3);
        StringBuilder sb = new StringBuilder();
        sb.append("setColorByPace:size ");
        sb.append(list.size());
        CommonLogUtil.d(TAG, sb.toString());
        for (int i4 = 0; i4 < list.size(); i4++) {
            CommonLogUtil.d(TAG, "setColorByPace: " + i4 + list.get(i4));
        }
        if (i3 == 48) {
            for (int size = this.colorList.size(); size < i2; size++) {
                CommonLogUtil.d(TAG, "setColorByPace: j" + size);
                float f2 = (float) i;
                float[] fArr = runSpeed;
                if (f2 >= fArr[fArr.length - 1]) {
                    if (list.contains(Integer.valueOf(size)) || list.contains(Integer.valueOf(size - 1))) {
                        this.colorList.add(1295532630);
                    } else {
                        List<Integer> list2 = this.colorList;
                        int[] iArr = colorArray;
                        list2.add(Integer.valueOf(iArr[iArr.length - 1]));
                    }
                } else if (f2 >= fArr[0]) {
                    int i5 = 1;
                    while (true) {
                        float[] fArr2 = runSpeed;
                        if (i5 >= fArr2.length) {
                            break;
                        }
                        if (f2 < fArr2[i5 - 1] || f2 >= fArr2[i5]) {
                            i5++;
                        } else if (list.contains(Integer.valueOf(size))) {
                            this.colorList.add(1295532630);
                        } else {
                            this.colorList.add(Integer.valueOf(colorArray[i5]));
                        }
                    }
                } else if (list.contains(Integer.valueOf(size))) {
                    this.colorList.add(1295532630);
                } else {
                    this.colorList.add(Integer.valueOf(colorArray[0]));
                }
            }
            return;
        }
        if (i3 == 1 || i3 == 1 || i3 == 52 || i3 == 4) {
            for (int size2 = this.colorList.size(); size2 < i2; size2++) {
                float f3 = i;
                float[] fArr3 = walkSpeed;
                if (f3 >= fArr3[fArr3.length - 1]) {
                    if (list.contains(Integer.valueOf(size2)) || list.contains(Integer.valueOf(size2 - 1))) {
                        this.colorList.add(1295532630);
                    } else {
                        List<Integer> list3 = this.colorList;
                        int[] iArr2 = colorArray;
                        list3.add(Integer.valueOf(iArr2[iArr2.length - 1]));
                    }
                } else if (f3 >= fArr3[0]) {
                    int i6 = 1;
                    while (true) {
                        float[] fArr4 = walkSpeed;
                        if (i6 >= fArr4.length) {
                            break;
                        }
                        if (f3 < fArr4[i6 - 1] || f3 >= fArr4[i6]) {
                            i6++;
                        } else if (list.contains(Integer.valueOf(size2))) {
                            this.colorList.add(1295532630);
                        } else {
                            this.colorList.add(Integer.valueOf(colorArray[i6]));
                        }
                    }
                } else if (list.contains(Integer.valueOf(size2))) {
                    this.colorList.add(1295532630);
                } else {
                    this.colorList.add(Integer.valueOf(colorArray[0]));
                }
            }
            return;
        }
        if (i3 == 50) {
            for (int size3 = this.colorList.size(); size3 < i2; size3++) {
                float f4 = i;
                float[] fArr5 = bikeSpeed;
                if (f4 >= fArr5[fArr5.length - 1]) {
                    if (list.contains(Integer.valueOf(size3)) || list.contains(Integer.valueOf(size3 - 1))) {
                        this.colorList.add(1295532630);
                    } else {
                        List<Integer> list4 = this.colorList;
                        int[] iArr3 = colorArray;
                        list4.add(Integer.valueOf(iArr3[iArr3.length - 1]));
                    }
                } else if (f4 >= fArr5[0]) {
                    int i7 = 1;
                    while (true) {
                        float[] fArr6 = bikeSpeed;
                        if (i7 >= fArr6.length) {
                            break;
                        }
                        if (f4 < fArr6[i7 - 1] || f4 >= fArr6[i7]) {
                            i7++;
                        } else if (list.contains(Integer.valueOf(size3))) {
                            this.colorList.add(1295532630);
                        } else {
                            this.colorList.add(Integer.valueOf(colorArray[i7]));
                        }
                    }
                } else if (list.contains(Integer.valueOf(size3))) {
                    this.colorList.add(1295532630);
                } else {
                    this.colorList.add(Integer.valueOf(colorArray[0]));
                }
            }
        }
    }

    private void comleteColorAndMileMark() {
        double d2;
        this.mileMarkList.clear();
        this.colorList.clear();
        int size = this.latLngBeanList.size() - 2;
        int i = 0;
        double dMax = 0.0d;
        double dMin = 0.0d;
        double dMax2 = 0.0d;
        double dMin2 = 0.0d;
        double d3 = 0.0d;
        while (i < size) {
            LatLngBean latLngBean = this.latLngBeanList.get(i);
            int i2 = i + 1;
            LatLngBean latLngBean2 = this.latLngBeanList.get(i2);
            double d4 = dMin2;
            double dAbs = Math.abs(getDistance(latLngBean, latLngBean2));
            int i3 = size;
            if (this.distanceUnit == this.BRITISH) {
                dAbs = UnitUtil.getKm2mile((float) dAbs);
            }
            this.colorList.add(Integer.valueOf(getColorList(latLngBean2, latLngBean)));
            d3 += dAbs;
            if (d3 > 1000.0d) {
                d3 -= 1000.0d;
                this.mileMarkList.add(Integer.valueOf(i));
            }
            dMax = Math.max(dMax, latLngBean.latitude);
            dMax2 = Math.max(dMax2, latLngBean.longitude);
            if (dMin == 0.0d) {
                dMin = latLngBean.latitude;
                d2 = latLngBean.longitude;
            } else {
                d2 = d4;
            }
            dMin = Math.min(dMin, latLngBean.latitude);
            dMin2 = Math.min(d2, latLngBean.longitude);
            size = i3;
            i = i2;
        }
        this.centerLatlng = new LatLngBean((dMax + dMin) / 2.0d, (dMax2 + dMin2) / 2.0d);
    }

    protected List<LatLngBean> getLatLngBeanList() {
        return this.latLngBeanList;
    }

    public void setLatLngBeanList(List<LatLngBean> list) {
        this.latLngBeanList = list;
    }

    public void setIsHideMapView(boolean z) {
        this.isHideMapView = z;
    }

    public void setMapLoadFinish(boolean z) {
        this.mIsFinish = z;
    }

    public void setIsRunMap(boolean z) {
        this.mIsRunMap = z;
    }

    protected boolean shouldMapScreenShot(long j) {
        if (j < 0) {
            return false;
        }
        return !FileUtil.fileExists(getShotFilePath());
    }

    protected String getShotFilePath() {
        File file = new File(LogPathImpl.getInstance().getPicPath());
        if (!file.exists()) {
            file.mkdirs();
        }
        return LogPathImpl.getInstance().getPicPath() + this.startTime + ".png";
    }

    public void setIsMarker(boolean z) {
        this.isMarker = z;
        drawAllAndShot(this.startTime, this.sporType, 0, 0, true, null);
    }

    private void setSpeedRange() {
        if (this.speeds != null) {
            return;
        }
        int i = this.sporType;
        if (i == 1) {
            this.speeds = walkSpeed;
            return;
        }
        if (i == 2) {
            this.speeds = runSpeed;
            return;
        }
        if (i == 3) {
            this.speeds = bikeSpeed;
        } else if (i == 4) {
            this.speeds = walkSpeed;
        } else {
            this.speeds = walkSpeed;
        }
    }

    protected int getColorList(LatLngBean latLngBean, LatLngBean latLngBean2) {
        double dAbs = Math.abs(com.ido.life.location.MapHelper.getDistance(latLngBean, latLngBean2));
        long jAbs = Math.abs(DateUtil.getLongFromDateStr(latLngBean.currentTimeMillis) - DateUtil.getLongFromDateStr(latLngBean2.currentTimeMillis)) / 1000;
        if (jAbs == 0) {
            jAbs = 1;
        }
        double d2 = (dAbs / jAbs) * 3.6d;
        setSpeedRange();
        if (this.mIsRunMap) {
            return RUN_COLOR_DEFAULT;
        }
        int i = 0;
        while (true) {
            if (i >= this.speeds.length) {
                return colorArray[r5.length - 1];
            }
            if (d2 < r6[i]) {
                return colorArray[i];
            }
            i++;
        }
    }

    @Override // com.ido.life.module.sport.map.MapScreenShotCallback
    public void shotComplet(Bitmap bitmap) {
        addMileMark();
        showOrHideMap(this.centerLatlng);
    }

    public void setZoomLevel(int i) {
        this.zoomLevel = i;
    }

    public void endAndShot(final MapScreenShotCallback mapScreenShotCallback) {
        addMarker(getLatLngBeanList().get(getLatLngBeanList().size() - 1), R.mipmap.ic_sport_map_end);
        ajustMapView();
        printLog("开始截图了，，，");
        ThreadUtil.delayTask(new Runnable() { // from class: com.ido.life.module.sport.map.BaseMap.4
            @Override // java.lang.Runnable
            public void run() {
                BaseMap.this.onMapScreenShot(mapScreenShotCallback);
            }
        }, 1000);
        removeTimeOut();
        this.mapShotTimeOutHanlder.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.map.BaseMap.5
            @Override // java.lang.Runnable
            public void run() {
                BaseMap.this.printLog("截图超时了，，，");
                MapScreenShotCallback mapScreenShotCallback2 = mapScreenShotCallback;
                if (mapScreenShotCallback2 != null) {
                    mapScreenShotCallback2.shotComplet(null);
                }
            }
        }, MAP_SHOT_TIME_OUT);
    }

    protected boolean isLocationChina() {
        String str = (String) SPUtils.get(Constants.POINT_KEY, "");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        float f2 = NumUtil.parseFloat(str.split(AppInfo.DELIM)[1]);
        float f3 = NumUtil.parseFloat(str.split(AppInfo.DELIM)[0]);
        return f2 > 3.0f && f2 < 53.0f && f3 > 73.0f && f3 < 135.0f;
    }

    protected final void removeTimeOut() {
        this.mapShotTimeOutHanlder.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), TAG, str);
    }
}