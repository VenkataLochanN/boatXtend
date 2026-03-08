package com.ido.life.module.sport.history.detail;

import android.text.TextUtils;
import android.util.Log;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.StringUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.LatLngBean;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.SportHealthDetailEntity;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.IHealthRepository;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.SportGps;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportItem;
import com.ido.life.enums.SportTargetEnum;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.bean.GpsAlgSmoothDataMode;
import com.ido.life.module.sport.bean.HistoryRecordDetailsData;
import com.ido.life.module.sport.history.KmProgress;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.module.sport.util.BigDecimalUtil;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SportDataUtil;
import com.ido.life.util.SportUnitUtil;
import com.ido.life.util.UnitUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* JADX INFO: compiled from: SportHistoryDetailNewPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 V2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001VB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\nJ\u0006\u0010\u0012\u001a\u00020\u0010J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J4\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001d2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u0005H\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\fH\u0002J\u001a\u0010%\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\fH\u0002J\u0010\u0010'\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J \u0010(\u001a\u00020\u00102\b\u0010)\u001a\u0004\u0018\u00010\n2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\u000eJ\u000e\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\fJ\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010/2\u0006\u00100\u001a\u00020\fJ \u00101\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010/2\u0006\u00103\u001a\u00020\n2\u0006\u00104\u001a\u00020\fH\u0002J\u0010\u00105\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u00106\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010/2\u0006\u00104\u001a\u00020\fJ\u000e\u00107\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\fJ\u0006\u00108\u001a\u00020\u0005J\"\u00109\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010:\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eJ,\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001d2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u0005H\u0002J\u0010\u0010<\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010=\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010>\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010?\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010@\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010A\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010B\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010C\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010D\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010E\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J \u0010F\u001a\u00020\u00102\u0006\u0010G\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\fH\u0002J\b\u0010H\u001a\u00020\u0010H\u0002J\u0010\u0010I\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010J\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010K\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010L\u001a\u00020\u0010H\u0002J\u0018\u0010M\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010N\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010O\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001a\u0010P\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010*\u001a\u00020\fH\u0002J2\u0010Q\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u000e\u0010R\u001a\n\u0012\u0004\u0012\u00020S\u0018\u00010\u001d2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010T\u001a\u00020\fH\u0002J\"\u0010U\u001a\u00020\u00102\b\u0010:\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/ido/life/module/sport/history/detail/SportHistoryDetailNewPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/sport/history/detail/ISportHistoryDetailNewView;", "()V", "mHasDestroy", "", "mHealthRepository", "Lcom/ido/life/data/health/IHealthRepository;", "mNeedRefreshSportRecordList", "mSid", "", "mSportType", "", "mUserId", "", "deleteRecord", "", "dateTime", "destroy", "getAvgKmPace", "kmSpeeds", "", "reach", "getAvgPace", "sportHealth", "Lcom/ido/life/database/model/SportHealth;", "getAvgSpeed", "isRide", "getKmSpeed", "", "Lcom/ido/life/module/sport/history/KmProgress;", "historyRecordDetailsDataList", "Lcom/ido/life/module/sport/bean/HistoryRecordDetailsData;", "minPeace", "maxPeace", "isReach", "getMaxKmPace", "getMaxPace", "maxPace", "getRide", "getSportDataByDateTime", "datetime", "fromType", "userId", "getSportNameByType", "sportType", "getStepBottomLabelList", "", "totalSecond", "getStepChartRate", "Lcom/ido/life/bean/BaseCharBean;", "stepData", "stepMax", "getUnitSpeed", "getYStepLabelList", "isShowSportNameExplain", "needRefreshSportRecordList", "saveSportData", "filePath", "setKmSpace", "setSportStepFrequency", "setSportTarget", "showClimbView", "showCricketView", "showDeviceNoBaiscDataSport", "showDeviceOutCycle", "showDeviceRateRangeSport", "showDeviceView1", "showIndoorLayout", "showItemsByType", "showKmSpeedLabel", "ride", "showNetErrorView", "showOldDeviceCycle", "showOpenWaterSwim", "showOutDoorLayout", "showRetryView", "showSportByAppAndDevice", "showSportByDevice", "showSportByPhone", "showSportDetail", "showSportDetailData", "latLngBeanList", "Lcom/ido/life/bean/LatLngBean;", "from", "updateFile", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportHistoryDetailNewPresenter extends BasePresenter<ISportHistoryDetailNewView> {
    private static final String TAG = "SportHistoryDetailNewPr";
    private boolean mHasDestroy;
    private IHealthRepository mHealthRepository = HealthRepository.getInstance();
    private final boolean mNeedRefreshSportRecordList;
    private String mSid;
    private int mSportType;
    private long mUserId;

    public final boolean isShowSportNameExplain(int sportType) {
        return sportType == 4 || sportType == 48 || sportType == 49 || sportType == 52 || sportType == 53;
    }

    public static final /* synthetic */ ISportHistoryDetailNewView access$getView(SportHistoryDetailNewPresenter sportHistoryDetailNewPresenter) {
        return sportHistoryDetailNewPresenter.getView();
    }

    public final void getSportDataByDateTime(final String datetime, final int fromType, long userId) {
        this.mUserId = userId;
        IHealthRepository iHealthRepository = this.mHealthRepository;
        if (iHealthRepository == null) {
            Intrinsics.throwNpe();
        }
        SportHealth sportHealthByDateTime = iHealthRepository.getSportHealthByDateTime(datetime);
        if (sportHealthByDateTime == null) {
            if (getView() == null) {
                return;
            }
            getView().toBack();
            return;
        }
        this.mSid = sportHealthByDateTime.getSid();
        CommonLogUtil.d(TAG, "getSportDataByDate: " + sportHealthByDateTime);
        if (!sportHealthByDateTime.isLoadDetail()) {
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            if (runTimeUtil.getUserId() > 0) {
                if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                    if (getView() == null) {
                        return;
                    }
                    getView().showLoading();
                    if (HealthRepository.getInstance() == null) {
                        return;
                    }
                    HealthRepository.getInstance().getSportDetailBySid(sportHealthByDateTime.getSid(), new HealthManager.OnUserSportRecordDetailCallback() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewPresenter.getSportDataByDateTime.1
                        @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                        public void onSuccess(SportHealthDetailEntity sportSummaryEntity) {
                            Intrinsics.checkParameterIsNotNull(sportSummaryEntity, "sportSummaryEntity");
                            SportHealth sportHealth = sportSummaryEntity.getResult();
                            sportHealth.setLoadDetail(true);
                            sportHealth.setIsUploaded(true);
                            LocalHealthDataManager.getInstance().saveActivityData(sportHealth);
                            Intrinsics.checkExpressionValueIsNotNull(sportHealth, "sportHealth");
                            if (sportHealth.getIsLocus() == 0) {
                                SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).setOutDoorLayoutVisible(false);
                                SportHistoryDetailNewPresenter.this.showSportDetail(sportHealth, fromType);
                            } else {
                                SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).setOutDoorLayoutVisible(true);
                                new SportGps().setInterval(sportHealth.getIntervalSecond());
                                SportGpsData sportGpsData = new SportGpsData();
                                sportGpsData.setGpsData(sportHealth.getGps());
                                sportGpsData.timeMillis = TimeUtil.convTimeYmdhmsToLong(sportHealth.getStartTime());
                                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                                sportGpsData.setUserId(runTimeUtil2.getUserId());
                                sportGpsData.setDown(true);
                                LocalHealthDataManager.getInstance().addAppGpsData(sportGpsData);
                                List<LatLngBean> latLngByDateTime = HealthRepository.getInstance().getLatLngByDateTime(DateUtil.getLongFromDateStr(datetime));
                                String str = datetime;
                                if (str != null) {
                                    SportHistoryDetailNewPresenter.this.showSportDetailData(sportHealth, latLngByDateTime, str, fromType);
                                }
                            }
                            Log.d(SportHistoryDetailNewPresenter.TAG, "onSuccess: " + sportHealth);
                            SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).hideLoading();
                            CommonLogUtil.d(SportHistoryDetailNewPresenter.TAG, "onSuccess: " + sportSummaryEntity);
                        }

                        @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                        public void onFailed(String message) {
                            Intrinsics.checkParameterIsNotNull(message, "message");
                            if (SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this) == null) {
                                return;
                            }
                            SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).hideLoading();
                            SportHistoryDetailNewPresenter.this.showRetryView();
                            CommonLogUtil.d(SportHistoryDetailNewPresenter.TAG, "onFailed: " + message);
                        }
                    });
                    return;
                }
                showNetErrorView();
                return;
            }
        }
        if (sportHealthByDateTime.getIsLocus() == 1) {
            getView().setOutDoorLayoutVisible(true);
            showSportDetailData(sportHealthByDateTime, HealthRepository.getInstance().getLatLngByDateTime(DateUtil.getLongFromDateStr(datetime)), datetime, fromType);
        } else {
            getView().setOutDoorLayoutVisible(false);
            showSportDetail(sportHealthByDateTime, fromType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSportDetailData(final SportHealth sportHealth, final List<LatLngBean> latLngBeanList, final String dateTime, final int from) {
        showSportDetail(sportHealth, from);
        if (from == 0) {
            getView().setOutLocusShareLayout(true);
        } else {
            getView().setOutLocusShareLayout(false);
        }
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewPresenter.showSportDetailData.1
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... arg0) {
                List list;
                Intrinsics.checkParameterIsNotNull(arg0, "arg0");
                if (sportHealth.getDistance() < 5 && (list = latLngBeanList) != null && list.size() > 0) {
                    LatLngBean latLngBean = new LatLngBean();
                    List list2 = latLngBeanList;
                    if (list2 != null && list2.size() > 0) {
                        latLngBean = (LatLngBean) latLngBeanList.get(0);
                    }
                    latLngBeanList.clear();
                    latLngBeanList.add(latLngBean);
                }
                if (from != 0) {
                    return null;
                }
                Protocol protocol = Protocol.getInstance();
                protocol.initType(SportHistoryDetailNewPresenter.this.mSportType);
                protocol.initParameter();
                GpsAlgSmoothDataMode gpsAlgSmoothDataMode = new GpsAlgSmoothDataMode();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                List<LatLngBean> list3 = latLngBeanList;
                if (list3 == null) {
                    Intrinsics.throwNpe();
                }
                for (LatLngBean latLngBean2 : list3) {
                    arrayList.add(Double.valueOf(latLngBean2.latitude));
                    arrayList2.add(Double.valueOf(latLngBean2.longitude));
                }
                gpsAlgSmoothDataMode.setLat(arrayList);
                gpsAlgSmoothDataMode.setLon(arrayList2);
                gpsAlgSmoothDataMode.setLen(latLngBeanList.size());
                SportLogHelper.saveSportLog(SportHistoryDetailNewPresenter.TAG, "getSportLatLngBeanList: 入参输入 " + gpsAlgSmoothDataMode);
                String strSmoothData = protocol.smoothData(GsonUtil.toJson(gpsAlgSmoothDataMode));
                SportLogHelper.saveSportLog(SportHistoryDetailNewPresenter.TAG, "getSportLatLngBeanList: 出参" + strSmoothData);
                GpsAlgSmoothDataMode gpsAlgSmoothDataMode2 = (GpsAlgSmoothDataMode) GsonUtil.fromJson(strSmoothData, GpsAlgSmoothDataMode.class);
                if (gpsAlgSmoothDataMode2 != null && gpsAlgSmoothDataMode2.getLat() != null && gpsAlgSmoothDataMode2.getLon() != null) {
                    List<Double> lat = gpsAlgSmoothDataMode2.getLat();
                    Intrinsics.checkExpressionValueIsNotNull(lat, "gpsAlgSmoothDataModeOut.lat");
                    int size = lat.size();
                    for (int i = 0; i < size; i++) {
                        LatLngBean latLngBean3 = (LatLngBean) latLngBeanList.get(i);
                        Double d2 = gpsAlgSmoothDataMode2.getLat().get(i);
                        Intrinsics.checkExpressionValueIsNotNull(d2, "gpsAlgSmoothDataModeOut.lat[i]");
                        latLngBean3.setLatitude(d2.doubleValue());
                    }
                    List<Double> lon = gpsAlgSmoothDataMode.getLon();
                    Intrinsics.checkExpressionValueIsNotNull(lon, "gpsAlgSmoothDataMode.lon");
                    int size2 = lon.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        LatLngBean latLngBean4 = (LatLngBean) latLngBeanList.get(i2);
                        Double d3 = gpsAlgSmoothDataMode2.getLon().get(i2);
                        Intrinsics.checkExpressionValueIsNotNull(d3, "gpsAlgSmoothDataModeOut.lon[i]");
                        latLngBean4.setLongitude(d3.doubleValue());
                    }
                }
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                SportGpsData sportGpsData = GreenDaoUtil.getSportGpsData(runTimeUtil.getUserId(), DateUtil.getLongFromDateStr(dateTime));
                Intrinsics.checkExpressionValueIsNotNull(sportGpsData, "sportGpsData");
                SportGps sportGps = sportGpsData.getGpsData();
                JSONArray jSONArray = new JSONArray();
                int size3 = latLngBeanList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    JSONArray jSONArray2 = new JSONArray();
                    LatLngBean latLngBean5 = (LatLngBean) latLngBeanList.get(i3);
                    jSONArray2.put(String.valueOf(latLngBean5.latitude));
                    jSONArray2.put(String.valueOf(latLngBean5.longitude));
                    jSONArray2.put(String.valueOf(TimeUtil.convTimeYmdhmsToLong(latLngBean5.currentTimeMillis)));
                    jSONArray.put(jSONArray2);
                }
                SportLogHelper.saveSportLog(SportHistoryDetailNewPresenter.TAG, "保存的经纬度" + jSONArray);
                Intrinsics.checkExpressionValueIsNotNull(sportGps, "sportGps");
                sportGps.setItems(jSONArray.toString());
                sportGpsData.setGpsData(sportGps);
                GreenDaoUtil.saveSportGpsData(sportGpsData);
                return null;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object result) {
                SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).addPolylineAndMove(latLngBeanList, sportHealth.getType(), sportHealth.getTotalSeconds(), sportHealth.getDistance());
            }
        }).execute("");
    }

    private final void showNetErrorView() {
        getView().showSportRetryView(true);
        getView().showSportDataView(false);
        getView().showSportNoNet(false);
        getView().setLoadLoadTitleShow(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showRetryView() {
        getView().showSportRetryView(false);
        getView().showSportDataView(false);
        getView().showSportNoNet(true);
        getView().setLoadLoadTitleShow(true);
    }

    public final void saveSportData(String dateTime, String filePath, long mUserId) {
        if (this.mHasDestroy) {
            return;
        }
        SportHealth sportHealth = HealthRepository.getInstance().getSportHealthByDateTime(dateTime);
        Intrinsics.checkExpressionValueIsNotNull(sportHealth, "sportHealth");
        sportHealth.setIcon(filePath);
        SportLogHelper.saveSportLog(TAG, "更新本地图片");
        updateFile(filePath, dateTime, mUserId);
        SportLogHelper.saveSportLog(TAG, "saveSportData: 更新本地文件成功");
    }

    public final void updateFile(String filePath, final String dateTime, long mUserId) {
        HealthRepository.getInstance().uploadFile(filePath, new OnResultCallback() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewPresenter.updateFile.1
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result<?> result) {
                Intrinsics.checkParameterIsNotNull(result, "result");
                if (SportHistoryDetailNewPresenter.this.mHasDestroy) {
                    return;
                }
                Object data = result.getData();
                if (data == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                SportHealth sportHealth = HealthRepository.getInstance().getSportHealthByDateTime(dateTime);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth, "sportHealth");
                sportHealth.setIcon((String) data);
                sportHealth.setIsUploaded(false);
                LocalHealthDataManager.getInstance().saveActivityData(sportHealth);
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String message) {
                Intrinsics.checkParameterIsNotNull(message, "message");
                SportLogHelper.saveSportLog(SportHistoryDetailNewPresenter.TAG, "上传图片失败onFailed: ");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSportDetail(SportHealth sportHealth, int fromType) {
        if (getView() == null) {
            return;
        }
        if (fromType == 0) {
            getView().showRightBtn(false);
        } else {
            getView().showRightBtn(true);
        }
        getView().showSportRetryView(false);
        getView().showSportDataView(true);
        getView().showSportNoNet(false);
        getView().setLoadLoadTitleShow(false);
        if (sportHealth == null) {
            Intrinsics.throwNpe();
        }
        String str = DateUtil.format(DateUtil.string2Date(sportHealth.getDateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMYHm);
        String str2 = DateUtil.format(DateUtil.string2Date(sportHealth.getEndTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_Hm);
        ISportHistoryDetailNewView view = getView();
        if (view != null) {
            view.setSportStartTime(str + '-' + str2);
        }
        setSportTarget(sportHealth);
        showItemsByType(this.mSportType, sportHealth);
    }

    private final void setSportTarget(SportHealth sportHealth) {
        if (getView() == null) {
            return;
        }
        SportLogHelper.saveSportLog(TAG, "setSportTarget:运动目标 ,运动数据" + sportHealth);
        if (sportHealth != null && sportHealth.getTargetType() != 0) {
            int targetValue = sportHealth.getTargetType() == SportTargetEnum.TARGET_STEP.getTarget() ? sportHealth.getTargetValue() : 0;
            int targetValue2 = sportHealth.getTargetType() == SportTargetEnum.TARGET_DISTANCE.getTarget() ? sportHealth.getTargetValue() : 0;
            int targetValue3 = sportHealth.getTargetType() == SportTargetEnum.TARGET_CATEGORY.getTarget() ? sportHealth.getTargetValue() : 0;
            int targetValue4 = sportHealth.getTargetType() == SportTargetEnum.TARGET_TIME.getTarget() ? sportHealth.getTargetValue() : 0;
            getView().showSeekBarStepNum(true);
            getView().showUserTargetDiff(true);
            if (targetValue != 0) {
                if (targetValue >= sportHealth.getNumSteps()) {
                    getView().setSeekBarProcess(sportHealth.getNumSteps(), targetValue);
                    ISportHistoryDetailNewView view = getView();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String languageText = LanguageUtil.getLanguageText(R.string.sport_detail_target_step_diff);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…_detail_target_step_diff)");
                    Object[] objArr = {Integer.valueOf(targetValue - sportHealth.getNumSteps())};
                    String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                    view.setTargetDiff(str);
                } else {
                    getView().showUserTargetDiff(true);
                    getView().setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                    getView().setSeekBarProcess(100, 100);
                }
            }
            if (targetValue2 != 0) {
                if (targetValue2 > sportHealth.getDistance()) {
                    getView().setSeekBarProcess(sportHealth.getDistance(), targetValue2);
                    if (sportHealth.getType() == 3 || sportHealth.getType() == 50 || sportHealth.getType() == 51) {
                        if (SportUnitUtil.isRideKm()) {
                            ISportHistoryDetailNewView view2 = getView();
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            String languageText2 = LanguageUtil.getLanguageText(R.string.sport_detail_target_distance_diff);
                            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ail_target_distance_diff)");
                            Object[] objArr2 = {String.valueOf(BigDecimalUtil.div(targetValue2 - sportHealth.getDistance(), 1000.0d, 2)) + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit)};
                            String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
                            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                            view2.setTargetDiff(str2);
                        } else {
                            float km2mile = UnitUtil.getKm2mile(sportHealth.getDistance());
                            ISportHistoryDetailNewView view3 = getView();
                            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                            String languageText3 = LanguageUtil.getLanguageText(R.string.sport_detail_target_distance_diff);
                            Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ail_target_distance_diff)");
                            Object[] objArr3 = {String.valueOf(BigDecimalUtil.div(UnitUtil.getKm2mile(targetValue2) - km2mile, 1000.0d, 2)) + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi)};
                            String str3 = String.format(languageText3, Arrays.copyOf(objArr3, objArr3.length));
                            Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                            view3.setTargetDiff(str3);
                        }
                    } else if (SportUnitUtil.isWalkOrRunKm()) {
                        ISportHistoryDetailNewView view4 = getView();
                        StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                        String languageText4 = LanguageUtil.getLanguageText(R.string.sport_detail_target_distance_diff);
                        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ail_target_distance_diff)");
                        Object[] objArr4 = {String.valueOf(BigDecimalUtil.div(targetValue2 - sportHealth.getDistance(), 1000.0d, 2)) + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit)};
                        String str4 = String.format(languageText4, Arrays.copyOf(objArr4, objArr4.length));
                        Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(format, *args)");
                        view4.setTargetDiff(str4);
                    } else {
                        float km2mile2 = UnitUtil.getKm2mile(sportHealth.getDistance());
                        ISportHistoryDetailNewView view5 = getView();
                        StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                        String languageText5 = LanguageUtil.getLanguageText(R.string.sport_detail_target_distance_diff);
                        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…ail_target_distance_diff)");
                        Object[] objArr5 = {String.valueOf(BigDecimalUtil.div(UnitUtil.getKm2mile(targetValue2) - km2mile2, 1000.0d, 2)) + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi)};
                        String str5 = String.format(languageText5, Arrays.copyOf(objArr5, objArr5.length));
                        Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(format, *args)");
                        view5.setTargetDiff(str5);
                    }
                } else {
                    getView().showUserTargetDiff(true);
                    getView().setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                    getView().setSeekBarProcess(100, 100);
                }
            }
            if (targetValue3 != 0) {
                if (targetValue3 > sportHealth.getNumCalories()) {
                    getView().setSeekBarProcess(sportHealth.getNumCalories(), targetValue3);
                    ISportHistoryDetailNewView view6 = getView();
                    StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                    String languageText6 = LanguageUtil.getLanguageText(R.string.sport_detail_target_category_diff);
                    Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…ail_target_category_diff)");
                    Object[] objArr6 = {Integer.valueOf(targetValue3 - sportHealth.getNumCalories())};
                    String str6 = String.format(languageText6, Arrays.copyOf(objArr6, objArr6.length));
                    Intrinsics.checkNotNullExpressionValue(str6, "java.lang.String.format(format, *args)");
                    view6.setTargetDiff(str6);
                } else {
                    getView().showUserTargetDiff(true);
                    getView().setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                    getView().setSeekBarProcess(100, 100);
                }
            }
            if (targetValue4 != 0) {
                if (targetValue4 > sportHealth.getTotalSeconds()) {
                    getView().setSeekBarProcess(sportHealth.getTotalSeconds(), targetValue4);
                    int totalSeconds = (targetValue4 - sportHealth.getTotalSeconds()) / 60;
                    if (totalSeconds == 0) {
                        totalSeconds = 1;
                    }
                    ISportHistoryDetailNewView view7 = getView();
                    StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                    String languageText7 = LanguageUtil.getLanguageText(R.string.sport_detail_target_time_diff);
                    Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…_detail_target_time_diff)");
                    Object[] objArr7 = {Integer.valueOf(totalSeconds)};
                    String str7 = String.format(languageText7, Arrays.copyOf(objArr7, objArr7.length));
                    Intrinsics.checkNotNullExpressionValue(str7, "java.lang.String.format(format, *args)");
                    view7.setTargetDiff(str7);
                    return;
                }
                getView().showUserTargetDiff(true);
                getView().setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                getView().setSeekBarProcess(100, 100);
                return;
            }
            return;
        }
        getView().showSeekBarStepNum(false);
        getView().showUserTargetDiff(false);
    }

    public final void getSportNameByType(int sportType) {
        this.mSportType = sportType;
        getView().setSportName(MotionTypeManager.INSTANCE.getMotionTypeName(sportType));
        getView().setLoadTitleText(MotionTypeManager.INSTANCE.getMotionTypeName(sportType));
        getView().setSportType(MotionTypeManager.INSTANCE.getSportDetailTypeIcon(sportType));
    }

    private final void showItemsByType(int mSportType, SportHealth sportHealth) {
        SportLogHelper.saveSportLog(TAG, "showItemsByType: " + GsonUtil.toJson(sportHealth));
        getView().showSportDataItemFour(false);
        if (getView() == null) {
            return;
        }
        if (sportHealth.getSourceType() == 1) {
            showSportByPhone(mSportType, sportHealth);
        } else if (sportHealth.getSourceType() == 2) {
            showSportByDevice(mSportType, sportHealth);
            if (mSportType != 5 && mSportType != 55 && mSportType != 178) {
                getView().showSportRate(sportHealth);
            }
            setSportStepFrequency(sportHealth);
            if (sportHealth.isSupportTrain()) {
                getView().showSportTrain(sportHealth);
            }
        } else if (sportHealth.getSourceType() == 3) {
            showSportByAppAndDevice(mSportType, sportHealth);
            setSportStepFrequency(sportHealth);
            if (mSportType != 5 && mSportType != 55 && mSportType != 178) {
                getView().showSportRate(sportHealth);
            }
            if (sportHealth.isSupportTrain()) {
                getView().showSportTrain(sportHealth);
            }
        }
        getUnitSpeed(sportHealth);
    }

    private final void showSportByPhone(int sportType, SportHealth sportHealth) {
        getView().setCalorie(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())) + SportUnitUtil.getCalorieUnit(), R.mipmap.ic_exercise_detail_kcal);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
        getView().showSportDataItemThree(false);
        getView().showSportDataItemFour(false);
        if (sportType != 4 && sportType != 48) {
            if (sportType == 50) {
                getView().setSportDistance(SportUnitUtil.getShowDistance(SportUnitUtil.isRideKm(), sportHealth.getDistance()), SportUnitUtil.getPaceUnit(SportUnitUtil.isRideKm()));
                getView().setFirstItemValue(SportUnitUtil.getMaxSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getMaxSpeed(), SportUnitUtil.isRideKm()));
                getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_faster));
                getView().setFirstItemUnit(SportUnitUtil.getSpeedUnit(SportUnitUtil.isRideKm()));
                getView().setSecondItemValue(SportUnitUtil.getAvgSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgSpeed(), SportUnitUtil.isRideKm()));
                getView().setSecondItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_avg));
                getView().setSecondItemUnit(SportUnitUtil.getSpeedUnit(SportUnitUtil.isRideKm()));
                ISportHistoryDetailNewView view2 = getView();
                if (view2 != null) {
                    view2.showSportDataItemTwo(false);
                    return;
                }
                return;
            }
            if (sportType != 52) {
                return;
            }
        }
        getView().setSportDistance(SportUnitUtil.getShowDistance(SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance()), SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setFirstItemValue(SportUnitUtil.getMaxPace(sportHealth.getMinPace(), SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getPace()));
        getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.sport_max_speed_distribution));
        getView().setFirstItemUnit(SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setSecondItemValue(SportUnitUtil.getAvgPace(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgPace(), SportUnitUtil.isWalkOrRunKm()));
        getView().setSecondItemDesc(LanguageUtil.getLanguageText(R.string.sport_average_speed_distribution));
        getView().setSecondItemUnit(SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setThreeItemValue(SportUnitUtil.getAvgSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgSpeed(), SportUnitUtil.isWalkOrRunKm()));
        getView().setThreeItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_avg));
        getView().setThreeItemUnit(SportUnitUtil.getSpeedUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().showFourItem(false);
    }

    private final void setSportStepFrequency(SportHealth sportHealth) {
        if (sportHealth.getDistance() == 0 || sportHealth.getRate() == null) {
            getView().showSportItemFrequency(false);
            return;
        }
        int type = sportHealth.getType();
        if (type == 4 || type == 56 || type == 48 || type == 49 || type == 52 || type == 53) {
            SportItem rate = sportHealth.getRate();
            Intrinsics.checkExpressionValueIsNotNull(rate, "sportHealth.rate");
            String itmes = rate.getItmes();
            if (TextUtils.isEmpty(itmes)) {
                getView().showSportItemFrequency(false);
                return;
            }
            int[] iArr = (int[]) GsonUtil.fromJson(itmes, int[].class);
            if (iArr != null) {
                if (!(iArr.length == 0) && sportHealth.getStepRate() != 0 && sportHealth.getStepRateMax() != 0) {
                    getView().showSportItemFrequency(true);
                    getView().setStepFrequencyAvg(String.valueOf(sportHealth.getStepRate()));
                    getView().setStepFrequencyMax(String.valueOf(sportHealth.getStepRateMax()));
                    getView().setXStepLabelList(getStepBottomLabelList(sportHealth.getTotalSeconds()));
                    getView().setYStepLabelList(getYStepLabelList(sportHealth.getStepRateMax()));
                    SportItem rate2 = sportHealth.getRate();
                    Intrinsics.checkExpressionValueIsNotNull(rate2, "sportHealth.rate");
                    String itmes2 = rate2.getItmes();
                    Intrinsics.checkExpressionValueIsNotNull(itmes2, "sportHealth.rate.itmes");
                    if (getStepChartRate(itmes2, sportHealth.getStepRate()) != null) {
                        ISportHistoryDetailNewView view = getView();
                        SportItem rate3 = sportHealth.getRate();
                        Intrinsics.checkExpressionValueIsNotNull(rate3, "sportHealth.rate");
                        String itmes3 = rate3.getItmes();
                        Intrinsics.checkExpressionValueIsNotNull(itmes3, "sportHealth.rate.itmes");
                        view.setStepFrequencyList(getStepChartRate(itmes3, sportHealth.getStepRateMax()));
                        return;
                    }
                    return;
                }
            }
            getView().showSportItemFrequency(false);
        }
    }

    private final List<BaseCharBean> getStepChartRate(String stepData, int stepMax) {
        int[] iArr = (int[]) GsonUtil.fromJson(stepData, int[].class);
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        getView().setStepXMinValue(0);
        getView().setStepXMaxValue(length);
        getView().setStepYMinValue(0);
        getView().setStepYMaxValue(stepMax);
        ArrayList arrayList = new ArrayList();
        int length2 = iArr.length;
        for (int i = 0; i < length2; i++) {
            float f2 = iArr[i];
            float f3 = 0;
            if (f2 < f3) {
                f2 = f3;
            }
            arrayList.add(new BaseCharBean(0, i, f2));
        }
        return arrayList;
    }

    public final List<String> getYStepLabelList(int stepMax) {
        ArrayList arrayList = new ArrayList();
        int i = stepMax / 2;
        for (int i2 = 0; i2 <= 2; i2++) {
            arrayList.add(String.valueOf((i2 * i) + 0));
        }
        return arrayList;
    }

    public final List<String> getStepBottomLabelList(int totalSecond) {
        ArrayList arrayList = new ArrayList();
        for (int i = 4; 1 <= i && 4 >= i; i--) {
            arrayList.add(String.valueOf((totalSecond / i) / 60));
        }
        return arrayList;
    }

    private final void showOpenWaterSwim(SportHealth sportHealth) {
        getView().setBasicDataViewVisible(false);
        getView().setSportDistance(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())), SportUnitUtil.getCalorieUnit());
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS(sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0057  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void showSportByDevice(int r8, com.ido.life.database.model.SportHealth r9) {
        /*
            Method dump skipped, instruction units count: 730
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.history.detail.SportHistoryDetailNewPresenter.showSportByDevice(int, com.ido.life.database.model.SportHealth):void");
    }

    private final void showOldDeviceCycle(SportHealth sportHealth) {
        getView().setSportDistance(SportUnitUtil.getShowDistance(SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance()), SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setCalorie(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())) + SportUnitUtil.getCalorieUnit(), R.mipmap.ic_exercise_detail_kcal);
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
        getView().setBasicDataViewVisible(false);
    }

    private final void showClimbView(SportHealth sportHealth) {
        getView().setSportDistance(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())), SportUnitUtil.getCalorieUnit());
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
        getView().setFirstItemValue(String.valueOf(sportHealth.getMinHrValue()) + "-" + String.valueOf(sportHealth.getMaxHrValue()));
        getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.home_heartbeat_range_rate));
        getView().setFirstItemUnit(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
        getView().setSecondItemValue(String.valueOf(sportHealth.getNumSteps()));
        getView().setSecondItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_step));
        getView().setSecondItemUnit(LanguageUtil.getLanguageText(R.string.step_unit));
        getView().showSportDataItemTwo(false);
        getView().showSportDataItemThree(false);
        getView().showSportDataItemFour(false);
    }

    private final void showDeviceView1(SportHealth sportHealth) {
        getView().setBasicDataViewVisible(false);
        getView().setSportDistance(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())), SportUnitUtil.getCalorieUnit());
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
    }

    private final void showCricketView(SportHealth sportHealth) {
        getView().setFirstItemValue(String.valueOf(sportHealth.getNumSteps()));
        getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_step));
        getView().setFirstItemUnit(LanguageUtil.getLanguageText(R.string.step_unit));
        getView().setSportDistance(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())), SportUnitUtil.getCalorieUnit());
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
        getView().showSportDataItemOneRight(false);
        getView().showSportDataItemTwo(false);
        getView().showSportDataItemThree(false);
        getView().showSportDataItemFour(false);
    }

    private final void showDeviceRateRangeSport(SportHealth sportHealth) {
        getView().setFirstItemValue(String.valueOf(sportHealth.getMinHrValue()) + "-" + String.valueOf(sportHealth.getMaxHrValue()));
        getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.home_heartbeat_range_rate));
        getView().setFirstItemUnit(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
        getView().setSportDistance(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())), SportUnitUtil.getCalorieUnit());
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
        getView().showSportDataItemOneRight(false);
        getView().showSportDataItemTwo(false);
        getView().showSportDataItemThree(false);
        getView().showSportDataItemFour(false);
    }

    private final void showDeviceNoBaiscDataSport(SportHealth sportHealth) {
        getView().setBasicDataViewVisible(false);
        getView().setSportDistance(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())), SportUnitUtil.getCalorieUnit());
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
    }

    private final void showDeviceOutCycle(SportHealth sportHealth) {
        getView().setFirstItemValue(SportUnitUtil.getMaxSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getMaxSpeed(), SportUnitUtil.isRideKm()));
        getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_faster));
        getView().setFirstItemUnit(SportUnitUtil.getSpeedUnit(SportUnitUtil.isRideKm()));
        getView().setSecondItemValue(SportUnitUtil.getAvgSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgSpeed(), SportUnitUtil.isRideKm()));
        getView().setSecondItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_avg));
        getView().setSecondItemUnit(SportUnitUtil.getSpeedUnit(SportUnitUtil.isRideKm()));
        ISportHistoryDetailNewView view = getView();
        if (view != null) {
            view.showSportDataItemTwo(false);
        }
        ISportHistoryDetailNewView view2 = getView();
        if (view2 != null) {
            view2.showSportDataItemThree(false);
        }
        ISportHistoryDetailNewView view3 = getView();
        if (view3 != null) {
            view3.showSportDataItemFour(false);
        }
        if (sportHealth.getSourceType() == 2 && sportHealth.getIsLocus() == 0) {
            ISportHistoryDetailNewView view4 = getView();
            if (view4 != null) {
                view4.setSportDistance(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())), SportUnitUtil.getCalorieUnit());
            }
            getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
            ISportHistoryDetailNewView view5 = getView();
            String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
            Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
            view5.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
            ISportHistoryDetailNewView view6 = getView();
            if (view6 != null) {
                String languageText = LanguageUtil.getLanguageText(R.string.sport_device_no_distance);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…sport_device_no_distance)");
                view6.setSportCycleNoDistance(false, languageText);
                return;
            }
            return;
        }
        getView().setCalorie(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())) + SportUnitUtil.getCalorieUnit(), R.mipmap.ic_exercise_detail_kcal);
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view7 = getView();
        String strComputeTimeHMS2 = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS2, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view7.setTotalSecond(strComputeTimeHMS2, R.mipmap.ic_exercise_detail_time);
        if (sportHealth.getDistance() == 0) {
            ISportHistoryDetailNewView view8 = getView();
            if (view8 != null) {
                view8.setSportDistance("--", "");
            }
            ISportHistoryDetailNewView view9 = getView();
            if (view9 != null) {
                String languageText2 = LanguageUtil.getLanguageText(R.string.sport_mobile_no_distance);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…sport_mobile_no_distance)");
                view9.setSportCycleNoDistance(true, languageText2);
                return;
            }
            return;
        }
        getView().setSportDistance(SportUnitUtil.getShowDistance(SportUnitUtil.isRideKm(), sportHealth.getDistance()), SportUnitUtil.getPaceUnit(SportUnitUtil.isRideKm()));
    }

    private final void showOutDoorLayout(SportHealth sportHealth) {
        getView().setSportDistance(SportUnitUtil.getShowDistance(SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance()), SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setCalorie(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())) + SportUnitUtil.getCalorieUnit(), R.mipmap.ic_exercise_detail_kcal);
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
        getView().setFirstItemValue(SportUnitUtil.getMaxPace(sportHealth.getMinPace(), SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getPace()));
        getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.sport_max_speed_distribution));
        getView().setFirstItemUnit(SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setSecondItemValue(SportUnitUtil.getAvgPace(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgPace(), SportUnitUtil.isWalkOrRunKm()));
        getView().setSecondItemDesc(LanguageUtil.getLanguageText(R.string.sport_average_speed_distribution));
        getView().setSecondItemUnit(SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setThreeItemValue(SportUnitUtil.getAvgSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgSpeed(), SportUnitUtil.isWalkOrRunKm()));
        getView().setThreeItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_avg));
        getView().setThreeItemUnit(SportUnitUtil.getSpeedUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setFourItemValue(String.valueOf(sportHealth.getStepRate()));
        getView().setFourItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_frequency_avg));
        getView().setFourItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_frequency_avg_unit));
        getView().setFiveItemValue(String.valueOf(sportHealth.getStepRange()));
        getView().setFiveItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_stride_avg));
        getView().setFiveItemUnit(SportUnitUtil.getRangeUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setSixItemValue(String.valueOf(sportHealth.getNumSteps()));
        getView().setSixItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_step));
        getView().setSixItemUnit(LanguageUtil.getLanguageText(R.string.step_unit));
    }

    private final void showIndoorLayout(SportHealth sportHealth) {
        getView().setSportDistance(SportUnitUtil.getShowDistance(SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance()), SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setCalorie(String.valueOf(SportUnitUtil.getCarieValue(sportHealth.getNumCalories())) + SportUnitUtil.getCalorieUnit(), R.mipmap.ic_exercise_detail_kcal);
        getView().setRate(String.valueOf(sportHealth.getAvgHrValue()) + LanguageUtil.getLanguageText(R.string.device_heart_rate_unit), R.mipmap.ic_exercise_detail_heart);
        ISportHistoryDetailNewView view = getView();
        String strComputeTimeHMS = DateUtil.computeTimeHMS((long) sportHealth.getTotalSeconds());
        Intrinsics.checkExpressionValueIsNotNull(strComputeTimeHMS, "DateUtil.computeTimeHMS(…th.totalSeconds.toLong())");
        view.setTotalSecond(strComputeTimeHMS, R.mipmap.ic_exercise_detail_time);
        getView().setFirstItemValue(SportUnitUtil.getMaxPace(sportHealth.getMinPace(), SportUnitUtil.isWalkOrRunKm(), sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getPace()));
        getView().setFirstItemDesc(LanguageUtil.getLanguageText(R.string.sport_max_speed_distribution));
        getView().setFirstItemUnit(SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setSecondItemValue(SportUnitUtil.getAvgPace(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgPace(), SportUnitUtil.isWalkOrRunKm()));
        getView().setSecondItemDesc(LanguageUtil.getLanguageText(R.string.sport_average_speed_distribution));
        getView().setSecondItemUnit(SportUnitUtil.getPaceUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setThreeItemValue(SportUnitUtil.getAvgSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds(), sportHealth.getAvgSpeed(), SportUnitUtil.isWalkOrRunKm()));
        getView().setThreeItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_avg));
        getView().setThreeItemUnit(SportUnitUtil.getSpeedUnit(SportUnitUtil.isWalkOrRunKm()));
        getView().setFourItemValue(String.valueOf(sportHealth.getStepRate()));
        getView().setFourItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_frequency_avg));
        getView().setFourItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_frequency_avg_unit));
        getView().setFiveItemValue(String.valueOf(sportHealth.getNumSteps()));
        getView().setFiveItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_step));
        getView().setFiveItemUnit(LanguageUtil.getLanguageText(R.string.step_unit));
        getView().setSixItemVisible(false);
        getView().showSportDataItemFour(false);
    }

    private final void showSportByAppAndDevice(int mSportType, SportHealth sportHealth) {
        if (mSportType != 3) {
            if (mSportType != 4) {
                if (mSportType != 178) {
                    switch (mSportType) {
                        case 49:
                        case 53:
                            showIndoorLayout(sportHealth);
                            break;
                        case 50:
                            showDeviceOutCycle(sportHealth);
                            break;
                        case 51:
                            showDeviceView1(sportHealth);
                            break;
                    }
                }
                showOpenWaterSwim(sportHealth);
                return;
            }
            showOutDoorLayout(sportHealth);
            return;
        }
        showOldDeviceCycle(sportHealth);
    }

    public final void deleteRecord(final String dateTime) {
        getView().showLoading();
        if (!TextUtils.isEmpty(this.mSid)) {
            HealthRepository.getInstance().deleteRecord(this.mSid, new OnResponseCallback() { // from class: com.ido.life.module.sport.history.detail.SportHistoryDetailNewPresenter.deleteRecord.1
                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onSuccess() {
                    SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).hideLoading();
                    SportLogHelper.saveSportLog(SportHistoryDetailNewPresenter.TAG, "deleteRecord onSuccess: " + SportHistoryDetailNewPresenter.this.mSid + ',' + dateTime);
                    HealthManager.deleteLocalSportRecord(dateTime);
                    EventBusHelper.post(Constants.EventConstants.EVENT_REFRESH_MY_DATA);
                    SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).toBack();
                }

                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onFailed(String message) {
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    SportHistoryDetailNewPresenter.access$getView(SportHistoryDetailNewPresenter.this).hideLoading();
                    SportLogHelper.saveSportLog(SportHistoryDetailNewPresenter.TAG, "onFailed: " + SportHistoryDetailNewPresenter.this.mSid + ',' + message);
                }
            });
            return;
        }
        SportLogHelper.saveSportLog(TAG, "mSid is null: " + dateTime);
        HealthManager.deleteLocalSportRecord(dateTime);
        EventBusHelper.post(Constants.EventConstants.EVENT_REFRESH_MY_DATA);
        getView().toBack();
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Unknown Source)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    private final void getUnitSpeed(com.ido.life.database.model.SportHealth r14) {
        /*
            Method dump skipped, instruction units count: 576
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.history.detail.SportHistoryDetailNewPresenter.getUnitSpeed(com.ido.life.database.model.SportHealth):void");
    }

    private final String getAvgKmPace(int[] kmSpeeds, boolean reach) {
        int length;
        if (kmSpeeds != null) {
            if (!(kmSpeeds.length == 0)) {
                if (kmSpeeds.length == 1) {
                    length = kmSpeeds[0];
                } else if (reach) {
                    int i = 0;
                    for (int i2 : kmSpeeds) {
                        i += i2;
                    }
                    length = i / kmSpeeds.length;
                } else {
                    int length2 = kmSpeeds.length - 2;
                    int i3 = 0;
                    if (length2 >= 0) {
                        int i4 = 0;
                        while (true) {
                            i3 += kmSpeeds[i4];
                            if (i4 == length2) {
                                break;
                            }
                            i4++;
                        }
                    }
                    length = i3 / (kmSpeeds.length - 1);
                }
                String peace = SportDataUtil.formatPeace(length);
                Intrinsics.checkExpressionValueIsNotNull(peace, "SportDataUtil.formatPeace(minPeace)");
                String str = peace;
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "'", false, 2, (Object) null)) {
                    Object[] array = StringsKt.split$default((CharSequence) str, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[0]);
                    if (array != null) {
                        if (Integer.parseInt(((String[]) array)[0]) > 99) {
                            peace = DateUtil.computeTimePace("99.99");
                            Intrinsics.checkExpressionValueIsNotNull(peace, "DateUtil.computeTimePace(\"99.99\")");
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
                String str2 = peace;
                if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "'", false, 2, (Object) null)) {
                    try {
                        Object[] array2 = StringsKt.split$default((CharSequence) str2, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[0]);
                        if (array2 != null) {
                            if (Integer.parseInt(((String[]) array2)[0]) > 99) {
                                String strComputeTimePace = DateUtil.computeTimePace("99.99");
                                Intrinsics.checkExpressionValueIsNotNull(strComputeTimePace, "DateUtil.computeTimePace(\"99.99\")");
                                return strComputeTimePace;
                            }
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    } catch (Exception e2) {
                        SportLogHelper.saveSportLog(TAG, "getAvgPace: " + e2);
                    }
                }
                return str2;
            }
        }
        return String.valueOf(0);
    }

    private final List<KmProgress> getKmSpeed(List<HistoryRecordDetailsData> historyRecordDetailsDataList, int minPeace, int maxPeace, boolean isReach) {
        int iDiv;
        ArrayList arrayList = new ArrayList();
        if (historyRecordDetailsDataList == null || minPeace <= 0) {
            return arrayList;
        }
        String speedMiByPeaceMi = SportDataUtil.getSpeedMiByPeaceMi(minPeace);
        Intrinsics.checkExpressionValueIsNotNull(speedMiByPeaceMi, "SportDataUtil.getSpeedMiByPeaceMi(minPeace)");
        CommonLogUtil.d("getKmSpeed-kelina===", minPeace + "---" + speedMiByPeaceMi);
        int i = 0;
        for (HistoryRecordDetailsData historyRecordDetailsData : historyRecordDetailsDataList) {
            boolean z = true;
            i++;
            String speedMiByPeaceMi2 = SportDataUtil.getSpeedMiByPeaceMi(historyRecordDetailsData.getPaceSpeed());
            Intrinsics.checkExpressionValueIsNotNull(speedMiByPeaceMi2, "SportDataUtil.getSpeedMi…ryRecordDetail.paceSpeed)");
            if (historyRecordDetailsData.getPaceSpeed() != 0) {
                CommonLogUtil.d("getKmSpeed-kelina===", historyRecordDetailsData + ".paceSpeed---" + speedMiByPeaceMi2 + "---" + minPeace + "---" + speedMiByPeaceMi);
                Double dValueOf = Double.valueOf(speedMiByPeaceMi2);
                Intrinsics.checkExpressionValueIsNotNull(dValueOf, "java.lang.Double.valueOf(speed)");
                double dDoubleValue = dValueOf.doubleValue();
                Double dValueOf2 = Double.valueOf(speedMiByPeaceMi);
                Intrinsics.checkExpressionValueIsNotNull(dValueOf2, "java.lang.Double.valueOf(maxSpeed)");
                iDiv = (int) (BigDecimalUtil.div(dDoubleValue, dValueOf2.doubleValue(), 2) * ((double) 100));
            } else {
                speedMiByPeaceMi2 = "0.0";
                iDiv = 0;
            }
            if (isReach || i == historyRecordDetailsDataList.size()) {
                z = isReach;
            }
            arrayList.add(new KmProgress(speedMiByPeaceMi2, iDiv, historyRecordDetailsData.isFaster(), z));
        }
        return arrayList;
    }

    private final List<KmProgress> setKmSpace(List<HistoryRecordDetailsData> historyRecordDetailsDataList, int maxPeace, boolean isReach) {
        String strComputeTimeMS;
        int iDiv;
        ArrayList arrayList = new ArrayList();
        if (historyRecordDetailsDataList == null || maxPeace == 0) {
            return arrayList;
        }
        int i = 0;
        for (HistoryRecordDetailsData historyRecordDetailsData : historyRecordDetailsDataList) {
            boolean z = true;
            i++;
            int paceSpeed = historyRecordDetailsData.getPaceSpeed();
            if (paceSpeed != 0) {
                iDiv = (int) (BigDecimalUtil.div(historyRecordDetailsData.getPaceSpeed(), maxPeace, 2) * ((double) 100));
                strComputeTimeMS = DateUtil.computeTimeMS(paceSpeed);
                Intrinsics.checkExpressionValueIsNotNull(strComputeTimeMS, "DateUtil.computeTimeMS(peace)");
            } else {
                strComputeTimeMS = "0.0";
                iDiv = 0;
            }
            if (isReach || i == historyRecordDetailsDataList.size()) {
                z = isReach;
            }
            arrayList.add(new KmProgress(strComputeTimeMS, iDiv, historyRecordDetailsData.isFaster(), z));
        }
        return arrayList;
    }

    private final void showKmSpeedLabel(boolean ride, SportHealth sportHealth, int minPeace) {
        if (!ride) {
            getView().setSportItemPaceFaster(getMaxKmPace(minPeace));
            if (SportUnitUtil.isWalkOrRunKm()) {
                getView().setSportItemPaceTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_item_unit));
                getView().setSportItemDistanceLabel(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit));
                return;
            } else {
                getView().setSportItemPaceTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_item_unit_mi));
                getView().setSportItemDistanceLabel(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
                return;
            }
        }
        if (SportUnitUtil.isRideKm()) {
            getView().setSportItemSpeedTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit));
            getView().setSportSpeedItemKm(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit));
        } else {
            getView().setSportItemSpeedTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit_mi));
            getView().setSportSpeedItemKm(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
        }
    }

    private final String getMaxKmPace(int minPeace) {
        String peace = SportDataUtil.formatPeace(minPeace);
        Intrinsics.checkExpressionValueIsNotNull(peace, "SportDataUtil.formatPeace(minPeace)");
        String str = peace;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "'", false, 2, (Object) null)) {
            Object[] array = StringsKt.split$default((CharSequence) str, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                if (Integer.parseInt(((String[]) array)[0]) > 99) {
                    peace = DateUtil.computeTimePace("99.99");
                    Intrinsics.checkExpressionValueIsNotNull(peace, "DateUtil.computeTimePace(\"99.99\")");
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        if (!StringsKt.contains$default((CharSequence) peace, (CharSequence) "'", false, 2, (Object) null)) {
            return peace;
        }
        try {
            Object[] array2 = StringsKt.split$default((CharSequence) peace, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array2 != null) {
                if (Integer.parseInt(((String[]) array2)[0]) <= 99) {
                    return peace;
                }
                String strComputeTimePace = DateUtil.computeTimePace("99.99");
                Intrinsics.checkExpressionValueIsNotNull(strComputeTimePace, "DateUtil.computeTimePace(\"99.99\")");
                return strComputeTimePace;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (Exception e2) {
            SportLogHelper.saveSportLog(TAG, "getAvgPace: " + e2);
            return peace;
        }
    }

    private final boolean getRide(SportHealth sportHealth) {
        if (sportHealth.getType() == 49 || sportHealth.getType() == 53 || sportHealth.getType() == 2 || sportHealth.getType() == 4 || sportHealth.getType() == 52 || sportHealth.getType() == 48) {
            return false;
        }
        return sportHealth.getType() == 3 || sportHealth.getType() == 50 || sportHealth.getType() == 51;
    }

    private final String getAvgSpeed(SportHealth sportHealth, boolean isRide) {
        if (sportHealth.getAvgSpeed() != 0) {
            if ((isRide && SportUnitUtil.isRideKm()) || (!isRide && SportUnitUtil.isWalkOrRunKm())) {
                return SportDataUtil.formatAvgSpeed(sportHealth.getAvgSpeed() / 100.0f);
            }
            return SportDataUtil.changeSpeed2mile(sportHealth.getAvgSpeed());
        }
        return SportDataUtil.computeTimeSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds());
    }

    private final String getAvgPace(SportHealth sportHealth) {
        String pace;
        String str = StringUtil.format("%.2f", Float.valueOf(0.0f));
        if (sportHealth.getDistance() == 0) {
            return str;
        }
        if (sportHealth.getAvgPace() != 0) {
            pace = DateUtil.computeTimeMS(sportHealth.getAvgPace());
            if (!SportUnitUtil.isWalkOrRunKm()) {
                pace = SportDataUtil.changePeace2mile(sportHealth.getAvgPace());
            }
        } else {
            pace = SportDataUtil.computeTimePace(sportHealth.getDistance(), sportHealth.getTotalSeconds());
        }
        Intrinsics.checkExpressionValueIsNotNull(pace, "pace");
        if (!StringsKt.contains$default((CharSequence) pace, (CharSequence) "'", false, 2, (Object) null)) {
            return pace;
        }
        try {
            Object[] array = StringsKt.split$default((CharSequence) pace, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                return Integer.parseInt(((String[]) array)[0]) > 99 ? DateUtil.computeTimePace("99.99") : pace;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (Exception e2) {
            SportLogHelper.saveSportLog(TAG, "getAvgPace: " + e2);
            return pace;
        }
    }

    private final String getMaxPace(SportHealth sportHealth, int maxPace) {
        String peace;
        if (sportHealth.getMinPace() != 0) {
            peace = DateUtil.computeTimeMS(sportHealth.getMinPace());
            Intrinsics.checkExpressionValueIsNotNull(peace, "DateUtil.computeTimeMS(sportHealth.minPace)");
            if (!SportUnitUtil.isWalkOrRunKm()) {
                peace = SportDataUtil.changePeace2mile(sportHealth.getMinPace());
                Intrinsics.checkExpressionValueIsNotNull(peace, "SportDataUtil.changePeac…mile(sportHealth.minPace)");
            }
        } else {
            peace = SportDataUtil.formatPeace(maxPace);
            Intrinsics.checkExpressionValueIsNotNull(peace, "SportDataUtil.formatPeace(maxPace)");
        }
        String str = peace;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "'", false, 2, (Object) null)) {
            Object[] array = StringsKt.split$default((CharSequence) str, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                if (Integer.parseInt(((String[]) array)[0]) > 99) {
                    peace = DateUtil.computeTimePace("99.99");
                    Intrinsics.checkExpressionValueIsNotNull(peace, "DateUtil.computeTimePace(\"99.99\")");
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        if (StringsKt.contains$default((CharSequence) peace, (CharSequence) "'", false, 2, (Object) null)) {
            try {
                Object[] array2 = StringsKt.split$default((CharSequence) peace, new String[]{"'"}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array2 != null) {
                    if (Integer.parseInt(((String[]) array2)[0]) > 99) {
                        String strComputeTimePace = DateUtil.computeTimePace("99.99");
                        Intrinsics.checkExpressionValueIsNotNull(strComputeTimePace, "DateUtil.computeTimePace(\"99.99\")");
                        return strComputeTimePace;
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } catch (Exception e2) {
                SportLogHelper.saveSportLog(TAG, "getAvgPace: " + e2);
            }
        }
        return peace;
    }

    /* JADX INFO: renamed from: needRefreshSportRecordList, reason: from getter */
    public final boolean getMNeedRefreshSportRecordList() {
        return this.mNeedRefreshSportRecordList;
    }

    public final void destroy() {
        this.mHasDestroy = true;
        SportSettingPreference.clear();
        SportLogHelper.saveSportLog(TAG, "页面已经销毁。");
    }
}