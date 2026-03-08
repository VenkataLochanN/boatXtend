package com.ido.life.module.device.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.LatLngBean;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.constants.Constants;
import com.ido.life.module.device.presenter.WorldTimeDetailPresenter;
import com.ido.life.module.device.view.IWorldTimeDetailView;
import com.ido.life.module.sport.map.BaseMap;
import com.ido.life.module.sport.map.MapFactory;
import com.ido.life.module.sport.map.OnMapLoadedListener;
import com.ido.life.util.CalendarUtils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0014J\u001f\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u000eH\u0014J\b\u0010\u0013\u001a\u00020\u000eH\u0014J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0012\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0018\u001a\u00020\u000eH\u0014J\b\u0010\u0019\u001a\u00020\u000eH\u0016J\u001c\u0010\u001a\u001a\u00020\u000e2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0\u001cH\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0014J\b\u0010\u001f\u001a\u00020\u000eH\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/ido/life/module/device/activity/WorldTimeDetailActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/WorldTimeDetailPresenter;", "Lcom/ido/life/module/device/view/IWorldTimeDetailView;", "()V", "city", "Lcom/ido/life/bean/WorldTimeCity;", "mapModel", "Lcom/ido/life/module/sport/map/BaseMap;", "mapView", "Landroid/view/View;", "getLayoutResId", "", "getSunRiseSetTime", "", Constants.AppPackage.CALENDAR, "Ljava/util/Calendar;", "(Lcom/ido/life/bean/WorldTimeCity;Ljava/util/Calendar;)Lkotlin/Unit;", "initData", "initLabelLanguage", "initMap", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onDestroy", "onGetSunRiseSetTimeFailed", "onGetSunRiseSetTimeSuccess", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Lkotlin/Pair;", "", "onPause", "onResume", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeDetailActivity extends BaseActivity<WorldTimeDetailPresenter> implements IWorldTimeDetailView {
    private HashMap _$_findViewCache;
    private WorldTimeCity city;
    private BaseMap<?, ?> mapModel;
    private View mapView;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_world_time_detail;
    }

    @Override // com.ido.life.module.device.view.IWorldTimeDetailView
    public void onGetSunRiseSetTimeFailed() {
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.city != null) {
            initMap(savedInstanceState);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.onResume();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.onPause();
        }
    }

    private final void initMap(Bundle savedInstanceState) {
        WorldTimeDetailActivity worldTimeDetailActivity = this;
        this.mapView = MapFactory.getMapView(worldTimeDetailActivity);
        this.mapModel = MapFactory.getMap();
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.setActivity(worldTimeDetailActivity);
        }
        CommonLogUtil.d("map load start");
        BaseMap<?, ?> baseMap2 = this.mapModel;
        if (baseMap2 != null) {
            baseMap2.initMapView(this.mapView);
        }
        BaseMap<?, ?> baseMap3 = this.mapModel;
        if (baseMap3 != null) {
            baseMap3.onCreate(savedInstanceState);
        }
        BaseMap<?, ?> baseMap4 = this.mapModel;
        if (baseMap4 != null) {
            baseMap4.checkMapLoaded(new OnMapLoadedListener() { // from class: com.ido.life.module.device.activity.WorldTimeDetailActivity.initMap.1
                @Override // com.ido.life.module.sport.map.OnMapLoadedListener
                public final void onMapLoad(boolean z) {
                    CommonLogUtil.printAndSave("地图加载结果：" + z);
                    if (z) {
                        BaseMap baseMap5 = WorldTimeDetailActivity.this.mapModel;
                        if (baseMap5 != null) {
                            BaseMap baseMap6 = WorldTimeDetailActivity.this.mapModel;
                            if (baseMap6 == null) {
                                Intrinsics.throwNpe();
                            }
                            baseMap5.setZoomLevel(baseMap6.getMinZoomLevel());
                        }
                        BaseMap baseMap7 = WorldTimeDetailActivity.this.mapModel;
                        if (baseMap7 != null) {
                            baseMap7.setGesturesEnabled(false);
                        }
                        LatLngBean latLngBean = new LatLngBean();
                        WorldTimeCity worldTimeCity = WorldTimeDetailActivity.this.city;
                        if (worldTimeCity == null) {
                            Intrinsics.throwNpe();
                        }
                        latLngBean.latitude = worldTimeCity.getLatitude();
                        WorldTimeCity worldTimeCity2 = WorldTimeDetailActivity.this.city;
                        if (worldTimeCity2 == null) {
                            Intrinsics.throwNpe();
                        }
                        latLngBean.longitude = worldTimeCity2.getLongitude();
                        BaseMap baseMap8 = WorldTimeDetailActivity.this.mapModel;
                        if (baseMap8 != null) {
                            baseMap8.moveCamera(latLngBean);
                        }
                        BaseMap baseMap9 = WorldTimeDetailActivity.this.mapModel;
                        if (baseMap9 != null) {
                            baseMap9.addMarker(latLngBean, R.mipmap.icon_location);
                        }
                    }
                }
            });
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.world_time_title));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.city = (WorldTimeCity) getIntent().getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        WorldTimeCity worldTimeCity = this.city;
        if (worldTimeCity != null) {
            TextView tvCityName = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvCityName);
            Intrinsics.checkExpressionValueIsNotNull(tvCityName, "tvCityName");
            tvCityName.setText(worldTimeCity.getName());
            TextView tvTimeDiff = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTimeDiff);
            Intrinsics.checkExpressionValueIsNotNull(tvTimeDiff, "tvTimeDiff");
            CalendarUtils calendarUtils = CalendarUtils.INSTANCE;
            TimeZone timeZone = TimeZone.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
            TimeZone timeZone2 = TimeZone.getTimeZone(worldTimeCity.getTimeZoneName());
            Intrinsics.checkExpressionValueIsNotNull(timeZone2, "TimeZone.getTimeZone(it.timeZoneName)");
            tvTimeDiff.setText(calendarUtils.getTimezoneDifferenceStr(timeZone, timeZone2));
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(worldTimeCity.getTimeZoneName()));
            TextView tvTime = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvTime);
            Intrinsics.checkExpressionValueIsNotNull(tvTime, "tvTime");
            CalendarUtils calendarUtils2 = CalendarUtils.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            tvTime.setText(calendarUtils2.getTime(calendar));
            TextView tvDateTime = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvDateTime);
            Intrinsics.checkExpressionValueIsNotNull(tvDateTime, "tvDateTime");
            tvDateTime.setText(CalendarUtils.INSTANCE.getWorldTimeDate(calendar));
            getSunRiseSetTime(worldTimeCity, calendar);
        }
    }

    private final Unit getSunRiseSetTime(WorldTimeCity city, Calendar calendar) {
        WorldTimeDetailPresenter worldTimeDetailPresenter = (WorldTimeDetailPresenter) this.mPresenter;
        if (worldTimeDetailPresenter == null) {
            return null;
        }
        worldTimeDetailPresenter.getSunRiseSetTime(city.getLongitude(), city.getLatitude(), calendar);
        return Unit.INSTANCE;
    }

    @Override // com.ido.life.module.device.view.IWorldTimeDetailView
    public void onGetSunRiseSetTimeSuccess(Pair<String, String> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        TextView tvSunrise = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvSunrise);
        Intrinsics.checkExpressionValueIsNotNull(tvSunrise, "tvSunrise");
        String languageText = getLanguageText(R.string.world_time_sun_rise);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.world_time_sun_rise)");
        Object[] objArr = {data.getFirst()};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        tvSunrise.setText(str);
        TextView tvSunset = (TextView) _$_findCachedViewById(com.ido.life.R.id.tvSunset);
        Intrinsics.checkExpressionValueIsNotNull(tvSunset, "tvSunset");
        String languageText2 = getLanguageText(R.string.world_time_sun_set);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "getLanguageText(R.string.world_time_sun_set)");
        Object[] objArr2 = {data.getSecond()};
        String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        tvSunset.setText(str2);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.ido.life.module.device.activity.WorldTimeDetailActivity$onDestroy$$inlined$let$lambda$1] */
    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BaseMap<?, ?> baseMap = this.mapModel;
        if (baseMap != null) {
            baseMap.setMapLoadFinish(true);
        }
        BaseMap<?, ?> baseMap2 = this.mapModel;
        if (baseMap2 != null) {
            baseMap2.setMapLoadFinish(true);
            new Thread() { // from class: com.ido.life.module.device.activity.WorldTimeDetailActivity$onDestroy$$inlined$let$lambda$1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    if (this.this$0.mapModel == null) {
                        return;
                    }
                    try {
                        try {
                            BaseMap baseMap3 = this.this$0.mapModel;
                            if (baseMap3 != null) {
                                baseMap3.onDestroy();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } finally {
                        this.this$0.mapModel = (BaseMap) null;
                    }
                }
            }.start();
        }
    }
}