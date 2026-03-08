package com.ido.life.module.sport.screen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.InputDeviceCompat;
import com.ido.life.bean.LatLngBean;
import com.ido.life.location.MapHelper;
import com.ido.life.module.sport.map.MapScreenShotCallback;
import com.ido.life.util.AsyncTaskUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseMapScreen<T extends View, L> {
    public static final int INVALID_TRAVEL_COLOR = 1295532630;
    protected static final int LINE_WIDTH = 12;
    protected static int MAP_SHOT_TIME_OUT = 5000;
    public static final int[] colorArray = {MapHelper.Standard_Color, -8913152, -4456704, InputDeviceCompat.SOURCE_ANY, -17664, -30720, -43759, -65536};
    protected Activity activity;
    protected T mapView;
    public int paddingBottom;
    protected float zoomLevel = 17.0f;
    public List<Integer> mInvalidLngLatList = new ArrayList();
    public List<LatLngBean> latLngBeanList = new ArrayList();
    protected List<Integer> colorList = new ArrayList();
    protected List<L> latLngList = new ArrayList();
    protected boolean mIsFinish = false;

    public abstract void addMarker(LatLngBean latLngBean, int i);

    public abstract void adjustMapView();

    public abstract void animateCamera(LatLngBean latLngBean);

    public abstract void drawPolyline();

    protected abstract L fromLatLngBean(LatLngBean latLngBean);

    public abstract void hideLogo();

    public void onCreate(Bundle bundle) {
    }

    public void onDestroy() {
    }

    public abstract void onMapScreenShot(MapScreenShotCallback mapScreenShotCallback);

    public void onPause() {
    }

    public void onResume() {
    }

    public abstract void showMapText(boolean z);

    public T getMapView() {
        return this.mapView;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setLatLngBeanList(List<LatLngBean> list) {
        this.latLngBeanList = list;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this.latLngList.add(fromLatLngBean(list.get(i)));
        }
    }

    public List<LatLngBean> getLatLngBeanList() {
        return this.latLngBeanList;
    }

    public void initMapView(T t) {
        this.mapView = t;
    }

    public void setLocusColor(final int i, final int i2, final int i3) {
        if (getLatLngBeanList() == null || getLatLngBeanList().size() == 0) {
            return;
        }
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.sport.screen.BaseMapScreen.1
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                BaseMapScreen baseMapScreen = BaseMapScreen.this;
                baseMapScreen.mInvalidLngLatList = MapHelper.completeColorByMile(baseMapScreen.latLngBeanList, i, i2, i3, BaseMapScreen.this.colorList, null, true);
                return null;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                if (BaseMapScreen.this.mapView == null || BaseMapScreen.this.mIsFinish) {
                    return;
                }
                BaseMapScreen.this.drawPolyline();
            }
        }).execute("");
    }
}