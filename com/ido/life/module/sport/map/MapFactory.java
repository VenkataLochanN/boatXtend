package com.ido.life.module.sport.map;

import android.app.Activity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ZoomControls;
import com.baidu.mapapi.map.TextureMapView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.module.sport.screen.BaseMapScreen;
import com.ido.life.module.sport.screen.GaoDeMapScreen;
import com.ido.life.module.sport.screen.GoogleMapScreen;

/* JADX INFO: loaded from: classes2.dex */
public class MapFactory {
    static String MAP_SOUCE_KEY = "MAP_SOUCE_KEY";
    public static final int MAP_TYPE_BAIDU = 0;
    public static final int MAP_TYPE_GAODE = 1;
    public static final int MAP_TYPE_GOOGLE = 2;
    private static final String TAG = "MapFactory";

    public static BaseMap getMap() {
        int mapSource = MapHelper.getMapSource();
        CommonLogUtil.d(TAG, "getMap: " + mapSource);
        if (mapSource == 1) {
            return new GaoDeMap();
        }
        return new GoogleMap();
    }

    public static BaseMapScreen getMapScreen() {
        int mapSource = MapHelper.getMapSource();
        CommonLogUtil.d(TAG, "getMap: " + mapSource);
        if (mapSource == 1) {
            return new GaoDeMapScreen();
        }
        return new GoogleMapScreen();
    }

    public static View getMapView(Activity activity) {
        int mapSource = MapHelper.getMapSource();
        if (mapSource == 0) {
            ((ViewStub) activity.findViewById(R.id.vs_baidu)).inflate();
            View viewFindViewById = activity.findViewById(R.id.mapViewBaidu);
            initBaiduMapSetting((TextureMapView) viewFindViewById);
            return viewFindViewById;
        }
        if (mapSource == 1) {
            ((ViewStub) activity.findViewById(R.id.vs_gaode)).inflate();
            View viewFindViewById2 = activity.findViewById(R.id.mapViewGaode);
            initGaoDeMapSettings((com.amap.api.maps.TextureMapView) viewFindViewById2);
            return viewFindViewById2;
        }
        if (mapSource == 2) {
            ((ViewStub) activity.findViewById(R.id.vs_google)).inflate();
            return activity.findViewById(R.id.fl_googlemap);
        }
        ((ViewStub) activity.findViewById(R.id.vs_gaode)).inflate();
        return activity.findViewById(R.id.mapViewGaode);
    }

    public static View getMapViewScreen(Activity activity) {
        int mapSource = MapHelper.getMapSource();
        if (mapSource == 1) {
            ((ViewStub) activity.findViewById(R.id.vs_gaode_screen)).inflate();
            View viewFindViewById = activity.findViewById(R.id.mapViewGaodeScreen);
            initGaoDeMapSettings((com.amap.api.maps.TextureMapView) viewFindViewById);
            return viewFindViewById;
        }
        if (mapSource == 2) {
            ((ViewStub) activity.findViewById(R.id.vs_google_screen)).inflate();
            return activity.findViewById(R.id.fl_googlemap_screen);
        }
        ((ViewStub) activity.findViewById(R.id.vs_gaode_screen)).inflate();
        return activity.findViewById(R.id.mapViewGaodeScreen);
    }

    public static View getMapView(View view) {
        int mapSource = MapHelper.getMapSource();
        if (mapSource == 0) {
            ((ViewStub) view.findViewById(R.id.vs_baidu)).inflate();
            View viewFindViewById = view.findViewById(R.id.mapViewBaidu);
            initBaiduMapSetting((TextureMapView) viewFindViewById);
            return viewFindViewById;
        }
        if (mapSource == 1) {
            ((ViewStub) view.findViewById(R.id.vs_gaode)).inflate();
            View viewFindViewById2 = view.findViewById(R.id.mapViewGaode);
            initGaoDeMapSettings((com.amap.api.maps.TextureMapView) viewFindViewById2);
            return viewFindViewById2;
        }
        if (mapSource == 2) {
            ((ViewStub) view.findViewById(R.id.vs_google)).inflate();
            return view.findViewById(R.id.fl_googlemap);
        }
        ((ViewStub) view.findViewById(R.id.vs_gaode)).inflate();
        return view.findViewById(R.id.mapViewGaode);
    }

    private static void initBaiduMapSetting(TextureMapView textureMapView) {
        textureMapView.showZoomControls(false);
        textureMapView.getMap().getUiSettings().setRotateGesturesEnabled(false);
        int childCount = textureMapView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = textureMapView.getChildAt(i);
            if (childAt instanceof ZoomControls) {
                childAt.setVisibility(4);
            }
        }
    }

    private static void initGaoDeMapSettings(com.amap.api.maps.TextureMapView textureMapView) {
        textureMapView.getMap().getUiSettings().setZoomControlsEnabled(false);
        textureMapView.getMap().getUiSettings().setRotateGesturesEnabled(false);
    }
}