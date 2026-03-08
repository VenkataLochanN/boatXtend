package com.ido.life.module.sport.history;

import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StringUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.LatLngBean;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.SportHealthDetailEntity;
import com.ido.life.data.api.entity.SportHealthDetailSidEntity;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.SportGps;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.sport.bean.GpsAlgSmoothDataMode;
import com.ido.life.module.sport.bean.HistoryRecordDetailsData;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.module.sport.bean.TimeLineHeartRateItem;
import com.ido.life.module.sport.history.SportHistoryDetailContract;
import com.ido.life.module.sport.map.MapHelper;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.module.sport.util.BigDecimalUtil;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SportDataUtil;
import com.ido.life.util.UnitUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportHistoryDetailPresenter implements SportHistoryDetailContract.Presenter {
    private static final int DEFAULT_MINUTE = 60;
    private static final String TAG = "SportHistoryDetailPresenter";
    private boolean mHasDestroy = false;
    private boolean mNeedRefreshSportRecordList = false;
    private String mSid;
    private int mSportType;
    private SportHistoryDetailContract.View mView;

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void clearSportTarget() {
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void getUserInfo() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportHistoryDetailPresenter(SportHistoryDetailContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void saveSportData(String str, String str2) {
        if (this.mHasDestroy) {
            return;
        }
        HealthRepository.getInstance().getSportHealthByDateTime(str).setIcon(str2);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "更新本地图片");
        updateFile(str2, str);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "saveSportData: 更新本地文件成功");
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void getSportNameByType(int i) {
        this.mSportType = i;
        this.mView.setSportName(SportDataUtil.getSportNameByType(i));
        this.mView.setLoadTitleText(SportDataUtil.getSportNameByType(i));
    }

    private void showSportData(SportHealth sportHealth) {
        this.mView.showSportRetryView(false);
        this.mView.showSportDataView(true);
        this.mView.showSportNoNet(false);
        this.mView.setLoadLoadTitleShow(false);
        this.mSid = sportHealth.getSid();
        setSportTarget(sportHealth);
        this.mView.setSportStartTime(DateUtil.format(DateUtil.string2Date(sportHealth.getDateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMYHm));
        if (sportHealth.getSourceType() == 1) {
            this.mView.showSportDataItemOne(true);
            this.mView.showSportDataItemTwo(true);
            this.mView.showSportDataItemThree(false);
            this.mView.showSportDataItemFour(false);
        } else {
            showItemsByType(this.mSportType);
        }
        setSportDistance(sportHealth);
        setSportStepFrequency(sportHealth);
        this.mView.setFirstItemValue(DateUtil.computeTimeHMS(sportHealth.getTotalSeconds()));
        this.mView.setSecondItemValue(String.valueOf(sportHealth.getNumCalories()));
        if (sportHealth.getNumSteps() == 0) {
            this.mView.setSevenItemValue(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mView.setSevenItemValue(String.valueOf(sportHealth.getNumSteps()));
        }
        int avgHrValue = sportHealth.getAvgHrValue();
        if (avgHrValue == 0) {
            this.mView.setEightItemValue("--");
        } else {
            this.mView.setEightItemValue(String.valueOf(avgHrValue));
        }
        if (sportHealth.getStepRate() == 0) {
            this.mView.setFiveItemValue(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mView.setFiveItemValue(String.valueOf(sportHealth.getStepRate()));
        }
        if (sportHealth.getStepRange() == 0) {
            this.mView.setSixItemValue(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mView.setSixItemValue(String.valueOf(sportHealth.getStepRange()));
        }
        setRangeSixUnit();
        if (sportHealth.getHeartrate() != null && !TextUtils.isEmpty(sportHealth.getHeartrate().getItmes()) && sportHealth.getHeartrate().getItmes().length() > 2) {
            resovleHeartRate(sportHealth.getHeartrate().getItmes(), sportHealth.getHrDataIntervalMinute());
            this.mView.setXLabelList(getBottomLabelList(sportHealth.getTotalSeconds()));
            this.mView.setYLabelList(getYLabelList(sportHealth.getMaxHrValue()));
            if (getChartRate(sportHealth.getHeartrate().getItmes(), sportHealth.getMaxHrValue()) != null) {
                this.mView.setRateList(getChartRate(sportHealth.getHeartrate().getItmes(), sportHealth.getMaxHrValue()));
            }
            this.mView.setSportRateMax(String.valueOf(sportHealth.getMaxHrValue()));
            this.mView.setSportChartRateAvg(String.valueOf(avgHrValue));
            getHeartRate(sportHealth.getHeartrate().getItmes(), sportHealth);
            return;
        }
        this.mView.showSportItemRate(false);
    }

    private void setSportStepFrequency(SportHealth sportHealth) {
        if (sportHealth.getDistance() == 0) {
            this.mView.showSportItemFrequency(false);
            return;
        }
        if (sportHealth.getType() == 50) {
            this.mView.showSportItemFrequency(false);
            return;
        }
        if (sportHealth.getRate() == null) {
            this.mView.showSportItemFrequency(false);
            return;
        }
        String itmes = sportHealth.getRate().getItmes();
        if (TextUtils.isEmpty(itmes)) {
            this.mView.showSportItemFrequency(false);
            return;
        }
        int[] iArr = (int[]) GsonUtil.fromJson(itmes, int[].class);
        if (iArr == null || iArr.length == 0) {
            this.mView.showSportItemFrequency(false);
            return;
        }
        this.mView.showSportItemFrequency(true);
        this.mView.setStepFrequencyAvg(String.valueOf(sportHealth.getStepRate()));
        this.mView.setStepFrequencyMax(String.valueOf(sportHealth.getStepRateMax()));
        this.mView.setXStepLabelList(getStepBottomLabelList(sportHealth.getTotalSeconds()));
        this.mView.setYStepLabelList(getYStepLabelList(sportHealth.getStepRateMax()));
        if (getStepChartRate(sportHealth.getRate().getItmes(), sportHealth.getStepRate()) != null) {
            this.mView.setStepFrequencyList(getStepChartRate(sportHealth.getRate().getItmes(), sportHealth.getStepRateMax()));
        }
    }

    private void setSportTarget(SportHealth sportHealth) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "setSportTarget:运动目标 ,运动数据" + sportHealth.toString());
        if (sportHealth != null && sportHealth.getTargetType() != 0) {
            int targetValue = sportHealth.getTargetType() == 1 ? sportHealth.getTargetValue() : 0;
            int targetValue2 = sportHealth.getTargetType() == 2 ? sportHealth.getTargetValue() : 0;
            int targetValue3 = sportHealth.getTargetType() == 3 ? sportHealth.getTargetValue() : 0;
            int targetValue4 = sportHealth.getTargetType() == 4 ? sportHealth.getTargetValue() : 0;
            this.mView.showSeekBarStepNum(true);
            this.mView.showUserTargetDiff(true);
            if (targetValue != 0) {
                if (targetValue >= sportHealth.getNumSteps()) {
                    this.mView.setSeekBarProcess(sportHealth.getNumSteps(), targetValue);
                    this.mView.setTargetDiff(String.format(LanguageUtil.getLanguageText(R.string.sport_detail_target_step_diff), Integer.valueOf(targetValue - sportHealth.getNumSteps())));
                } else {
                    this.mView.showUserTargetDiff(true);
                    this.mView.setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                    this.mView.setSeekBarProcess(100, 100);
                }
            }
            if (targetValue2 != 0) {
                if (targetValue2 > sportHealth.getDistance()) {
                    this.mView.setSeekBarProcess(sportHealth.getDistance(), targetValue2);
                    if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                        this.mView.setTargetDiff(String.format(LanguageUtil.getLanguageText(R.string.sport_detail_target_distance_diff), BigDecimalUtil.div(targetValue2 - sportHealth.getDistance(), 1000.0d, 2) + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit)));
                    } else {
                        float km2mile = UnitUtil.getKm2mile(sportHealth.getDistance());
                        this.mView.setTargetDiff(String.format(LanguageUtil.getLanguageText(R.string.sport_detail_target_distance_diff), BigDecimalUtil.div(UnitUtil.getKm2mile(targetValue2) - km2mile, 1000.0d, 2) + LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi)));
                    }
                } else {
                    this.mView.showUserTargetDiff(true);
                    this.mView.setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                    this.mView.setSeekBarProcess(100, 100);
                }
            }
            if (targetValue3 != 0) {
                if (targetValue3 > sportHealth.getNumCalories()) {
                    this.mView.setSeekBarProcess(sportHealth.getNumCalories(), targetValue3);
                    this.mView.setTargetDiff(String.format(LanguageUtil.getLanguageText(R.string.sport_detail_target_category_diff), Integer.valueOf(targetValue3 - sportHealth.getNumCalories())));
                } else {
                    this.mView.showUserTargetDiff(true);
                    this.mView.setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                    this.mView.setSeekBarProcess(100, 100);
                }
            }
            if (targetValue4 != 0) {
                if (targetValue4 > sportHealth.getTotalSeconds()) {
                    this.mView.setSeekBarProcess(sportHealth.getTotalSeconds(), targetValue4);
                    int totalSeconds = (targetValue4 - sportHealth.getTotalSeconds()) / 60;
                    if (totalSeconds == 0) {
                        totalSeconds = 1;
                    }
                    this.mView.setTargetDiff(String.format(LanguageUtil.getLanguageText(R.string.sport_detail_target_time_diff), Integer.valueOf(totalSeconds)));
                    return;
                }
                this.mView.showUserTargetDiff(true);
                this.mView.setTargetDiff(LanguageUtil.getLanguageText(R.string.sport_detail_target_complete));
                this.mView.setSeekBarProcess(100, 100);
                return;
            }
            return;
        }
        this.mView.showSeekBarStepNum(false);
        this.mView.showUserTargetDiff(false);
    }

    private void showItemsByType(int i) {
        if (i != 48) {
            if (i == 50) {
                this.mView.showSportDataItemOne(true);
                this.mView.showSportDataItemTwo(true);
                this.mView.showSportDataItemThree(false);
                this.mView.showSportDataItemFour(false);
                return;
            }
            if (i != 52 && i != 58) {
                return;
            }
        }
        this.mView.showSportDataItemOne(true);
        this.mView.showSportDataItemTwo(true);
        this.mView.showSportDataItemThree(true);
        this.mView.showSportDataItemFour(true);
    }

    private void setSportDistance(SportHealth sportHealth) {
        String str;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float distance = sportHealth.getDistance() / 1000.0f;
        float km2mile = UnitUtil.getKm2mile(distance);
        CommonLogUtil.d(TAG, "setSportDistance: " + distance + AppInfo.DELIM + NumUtil.float2String(distance, 2));
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            str = decimalFormat.format(Float.parseFloat(NumUtil.float2String(distance, 2)));
            this.mView.setSportDistanceUnit(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit));
        } else {
            str = decimalFormat.format(Float.parseFloat(NumUtil.float2String(km2mile, 2)));
            this.mView.setSportDistanceUnit(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi));
        }
        this.mView.setSportDistance(str);
        String avgPace = getAvgPace(sportHealth);
        String avgSpeed = getAvgSpeed(sportHealth);
        if (this.mSportType == 50) {
            this.mView.setThreeItemValue(avgSpeed);
            setThreeSpeedUnit();
            this.mView.setThreeItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_avg));
            this.mView.setFourItemValue(String.valueOf(sportHealth.getAvgHrValue()));
            this.mView.setFourItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_rate));
            this.mView.setFourItemUnit(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
            return;
        }
        CommonLogUtil.d(TAG, "setSportDistance: " + sportHealth.getAvgPace() + AppInfo.DELIM + sportHealth.getDistance());
        this.mView.setThreeItemValue(avgPace);
        setPeaceThreeUnit();
        this.mView.setFourItemValue(avgSpeed);
        setFourSpeedUnit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAvgPace(SportHealth sportHealth) {
        String strComputeTimePace;
        String str = StringUtil.format("%.2f", Float.valueOf(0.0f));
        if (sportHealth.getDistance() == 0) {
            return str;
        }
        if (sportHealth.getAvgPace() != 0) {
            strComputeTimePace = DateUtil.computeTimeMS(sportHealth.getAvgPace());
            if (RunTimeUtil.getInstance().getUnitSet() == 2) {
                strComputeTimePace = SportDataUtil.changePeace2mile(sportHealth.getAvgPace());
            }
        } else {
            strComputeTimePace = SportDataUtil.computeTimePace(sportHealth.getDistance(), sportHealth.getTotalSeconds());
        }
        if (!strComputeTimePace.contains("'")) {
            return strComputeTimePace;
        }
        try {
            return Integer.parseInt(strComputeTimePace.split("'")[0]) > 99 ? DateUtil.computeTimePace("99.99") : strComputeTimePace;
        } catch (Exception e2) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "getAvgPace: " + e2.toString());
            return strComputeTimePace;
        }
    }

    private String getMaxPace(SportHealth sportHealth, int i) {
        String peace;
        StringUtil.format("%.2f", Float.valueOf(0.0f));
        if (sportHealth.getMinPace() != 0) {
            peace = DateUtil.computeTimeMS(sportHealth.getMinPace());
            if (RunTimeUtil.getInstance().getUnitSet() == 2) {
                peace = SportDataUtil.changePeace2mile(sportHealth.getMinPace());
            }
        } else {
            peace = SportDataUtil.formatPeace(i);
        }
        if (peace.contains("'") && Integer.parseInt(peace.split("'")[0]) > 99) {
            peace = DateUtil.computeTimePace("99.99");
        }
        if (!peace.contains("'")) {
            return peace;
        }
        try {
            return Integer.parseInt(peace.split("'")[0]) > 99 ? DateUtil.computeTimePace("99.99") : peace;
        } catch (Exception e2) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "getAvgPace: " + e2.toString());
            return peace;
        }
    }

    private void setPeaceThreeUnit() {
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mView.setThreeItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_unit));
        } else {
            this.mView.setThreeItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_unit_mi));
        }
    }

    private void setPeaceFourUnit() {
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mView.setFourItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_unit));
        } else {
            this.mView.setFourItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_unit_mi));
        }
    }

    private void setThreeSpeedUnit() {
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mView.setThreeItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit));
        } else {
            this.mView.setThreeItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit_mi));
        }
    }

    private void setFourSpeedUnit() {
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mView.setFourItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit));
        } else {
            this.mView.setFourItemUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit_mi));
        }
    }

    private void setRangeSixUnit() {
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mView.setSixItemUnit(LanguageUtil.getLanguageText(R.string.public_unit_cm));
        } else {
            this.mView.setSixItemUnit(LanguageUtil.getLanguageText(R.string.public_unit_inch));
        }
    }

    private String getMaxSpeed(SportHealth sportHealth, int i) {
        if (sportHealth.getMaxSpeed() != 0) {
            return RunTimeUtil.getInstance().getUnitSet() == 2 ? SportDataUtil.changeSpeed2mile(sportHealth.getMaxSpeed()) : SportDataUtil.formatAvgSpeed(sportHealth.getMaxSpeed() / 100.0f);
        }
        return SportDataUtil.getSpeedByPeace(i);
    }

    private List<BaseCharBean> getStepChartRate(String str, int i) {
        int[] iArr = (int[]) GsonUtil.fromJson(str, int[].class);
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        this.mView.setStepXMinValue(0);
        this.mView.setStepXMaxValue(length);
        this.mView.setStepYMinValue(0);
        this.mView.setStepYMaxValue(i);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f2 = iArr[i2];
            float f3 = 0;
            if (f2 < f3) {
                f2 = f3;
            }
            arrayList.add(new BaseCharBean(0, i2, f2));
        }
        return arrayList;
    }

    private List<BaseCharBean> getChartRate(String str, int i) {
        int[] iArr = (int[]) GsonUtil.fromJson(str, int[].class);
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        int i2 = i / 5;
        this.mView.setRateXMinValue(0);
        this.mView.setRateXMaxValue(length);
        this.mView.setRateYMinValue(i2);
        this.mView.setRateYMaxValue(i);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f2 = iArr[i3];
            float f3 = i2;
            if (f2 < f3) {
                f2 = f3;
            }
            arrayList.add(new BaseCharBean(0, i3, f2));
        }
        return arrayList;
    }

    public List<String> getStepBottomLabelList(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 4; i2 <= 4 && i2 > 0; i2--) {
            arrayList.add(String.valueOf((i / i2) / 60));
        }
        arrayList.add(LanguageUtil.getLanguageText(R.string.sport_record_chart_step_time_unit));
        return arrayList;
    }

    public List<String> getBottomLabelList(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 5; i2 <= 5 && i2 > 0; i2--) {
            int i3 = i / i2;
            if (i > 3600) {
                arrayList.add(DateUtil.computeTimeHM(i3));
            } else {
                arrayList.add(DateUtil.computeTimeMS(i3));
            }
        }
        if (i > 3600) {
            arrayList.add(LanguageUtil.getLanguageText(R.string.sport_record_chart_time_unit));
        } else {
            arrayList.add(LanguageUtil.getLanguageText(R.string.sport_record_chart_m_s_unit));
        }
        return arrayList;
    }

    public List<String> getYStepLabelList(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = i / 2;
        for (int i3 = 0; i3 <= 2; i3++) {
            arrayList.add(String.valueOf((i3 * i2) + 0));
        }
        return arrayList;
    }

    public List<String> getYLabelList(int i) {
        int i2 = i / 5;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < 5; i3++) {
            if (i3 == 4) {
                arrayList.add(String.valueOf(i));
            } else {
                arrayList.add(String.valueOf((i3 * i2) + i2));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getUnitSpeed(java.util.List<com.ido.life.bean.LatLngBean> r11, java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 498
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.history.SportHistoryDetailPresenter.getUnitSpeed(java.util.List, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAvgSpeed(SportHealth sportHealth) {
        if (sportHealth.getAvgSpeed() != 0) {
            return RunTimeUtil.getInstance().getUnitSet() == 2 ? SportDataUtil.changeSpeed2mile(sportHealth.getAvgSpeed()) : SportDataUtil.formatAvgSpeed(sportHealth.getAvgSpeed() / 100.0f);
        }
        return SportDataUtil.computeTimeSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds());
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void getSportDataByDateTime(String str) {
        SportHealth sportHealthByDateTime = HealthRepository.getInstance().getSportHealthByDateTime(str);
        if (sportHealthByDateTime == null) {
            this.mView.toBack();
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "数据库中的数据 getSportDataByDateTime: " + sportHealthByDateTime.toString());
        getSportLatLngBeanList(str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void getSportLatLngBeanList(final String str) {
        List<LatLngBean> latLngByDateTime = HealthRepository.getInstance().getLatLngByDateTime(DateUtil.getLongFromDateStr(str));
        CommonLogUtil.d(TAG, "getSportLatLngBeanList: " + latLngByDateTime.size());
        SportHealth sportHealthByDateTime = HealthRepository.getInstance().getSportHealthByDateTime(str);
        if (sportHealthByDateTime == null) {
            return;
        }
        CommonLogUtil.d(TAG, "getSportLatLngBeanList: " + sportHealthByDateTime.toString());
        if (!sportHealthByDateTime.getIsLoadDetail() && RunTimeUtil.getInstance().getUserId() > 0) {
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                this.mView.showLoading();
                HealthRepository.getInstance().getSportDetailBySid(sportHealthByDateTime.getSid(), new HealthManager.OnUserSportRecordDetailCallback() { // from class: com.ido.life.module.sport.history.SportHistoryDetailPresenter.1
                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                    public void onSuccess(SportHealthDetailEntity sportHealthDetailEntity) {
                        if (SportHistoryDetailPresenter.this.mHasDestroy) {
                            return;
                        }
                        SportHealth result = sportHealthDetailEntity.getResult();
                        result.setLoadDetail(true);
                        result.setIsUploaded(true);
                        LocalHealthDataManager.getInstance().saveActivityData(result);
                        new SportGps().setInterval(result.getIntervalSecond());
                        SportGpsData sportGpsData = new SportGpsData();
                        sportGpsData.setGpsData(result.getGps());
                        sportGpsData.timeMillis = TimeUtil.convTimeYmdhmsToLong(result.getStartTime());
                        sportGpsData.setUserId(RunTimeUtil.getInstance().getUserId());
                        sportGpsData.setDown(true);
                        LocalHealthDataManager.getInstance().addAppGpsData(sportGpsData);
                        SportHistoryDetailPresenter.this.mView.hideLoading();
                        SportHistoryDetailPresenter.this.showSportDetailData(result, HealthRepository.getInstance().getLatLngByDateTime(DateUtil.getLongFromDateStr(str)), str);
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailPresenter.TAG, "onSuccess: 获取运动详细信息" + sportHealthDetailEntity.toString());
                    }

                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                    public void onFailed(String str2) {
                        SportHistoryDetailPresenter.this.mView.hideLoading();
                        SportHistoryDetailPresenter.this.showRetryView();
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailPresenter.TAG, "onFailed: 获取运动信息失败" + str2);
                    }
                });
                return;
            } else {
                showNetErrorView();
                return;
            }
        }
        showSportDetailData(sportHealthByDateTime, latLngByDateTime, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRetryView() {
        this.mView.showSportRetryView(true);
        this.mView.showSportDataView(false);
        this.mView.showSportNoNet(false);
        this.mView.setLoadLoadTitleShow(true);
    }

    private void showNetErrorView() {
        this.mView.showSportRetryView(false);
        this.mView.showSportDataView(false);
        this.mView.showSportNoNet(true);
        this.mView.setLoadLoadTitleShow(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSportDetailData(SportHealth sportHealth, List<LatLngBean> list, String str) {
        showSportData(sportHealth);
        if (sportHealth.getDistance() < 5 && list != null && list.size() > 0) {
            LatLngBean latLngBean = new LatLngBean();
            if (list != null && list.size() > 0) {
                latLngBean = list.get(0);
            }
            list.clear();
            list = new ArrayList<>();
            list.add(latLngBean);
        }
        Protocol protocol = Protocol.getInstance();
        protocol.initType(this.mSportType);
        protocol.initParameter();
        GpsAlgSmoothDataMode gpsAlgSmoothDataMode = new GpsAlgSmoothDataMode();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (LatLngBean latLngBean2 : list) {
            arrayList.add(Double.valueOf(latLngBean2.latitude));
            arrayList2.add(Double.valueOf(latLngBean2.longitude));
        }
        gpsAlgSmoothDataMode.setLat(arrayList);
        gpsAlgSmoothDataMode.setLon(arrayList2);
        gpsAlgSmoothDataMode.setLen(list.size());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "getSportLatLngBeanList: 入参输入 " + gpsAlgSmoothDataMode.toString());
        String strSmoothData = protocol.smoothData(GsonUtil.toJson(gpsAlgSmoothDataMode));
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "getSportLatLngBeanList: 出参" + strSmoothData);
        GpsAlgSmoothDataMode gpsAlgSmoothDataMode2 = (GpsAlgSmoothDataMode) GsonUtil.fromJson(strSmoothData, GpsAlgSmoothDataMode.class);
        if (gpsAlgSmoothDataMode2 != null && gpsAlgSmoothDataMode2.getLat() != null && gpsAlgSmoothDataMode2.getLon() != null) {
            for (int i = 0; i < gpsAlgSmoothDataMode2.getLat().size(); i++) {
                list.get(i).setLatitude(gpsAlgSmoothDataMode2.getLat().get(i).doubleValue());
            }
            for (int i2 = 0; i2 < gpsAlgSmoothDataMode.getLon().size(); i2++) {
                list.get(i2).setLongitude(gpsAlgSmoothDataMode2.getLon().get(i2).doubleValue());
            }
        }
        this.mView.addPolylineAndMove(list, sportHealth.getType(), sportHealth.getTotalSeconds(), sportHealth.getDistance());
        getUnitSpeed(list, str);
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void deleteRecord(final String str) {
        this.mView.showLoading();
        if (!TextUtils.isEmpty(this.mSid)) {
            HealthRepository.getInstance().deleteRecord(this.mSid, new OnResponseCallback() { // from class: com.ido.life.module.sport.history.SportHistoryDetailPresenter.2
                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onSuccess() {
                    SportHistoryDetailPresenter.this.mView.hideLoading();
                    CommonLogUtil.d(SportHistoryDetailPresenter.TAG, "onSuccess: " + SportHistoryDetailPresenter.this.mSid);
                    HealthManager.getInstance();
                    HealthManager.deleteLocalSportRecord(str);
                    EventBusHelper.post(Constants.EventConstants.EVENT_REFRESH_MY_DATA);
                    SportHistoryDetailPresenter.this.mView.toBack();
                }

                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onFailed(String str2) {
                    SportHistoryDetailPresenter.this.mView.hideLoading();
                    CommonLogUtil.d(SportHistoryDetailPresenter.TAG, "onFailed: " + str2);
                }
            });
            return;
        }
        HealthManager.getInstance();
        HealthManager.deleteLocalSportRecord(str);
        EventBusHelper.post(Constants.EventConstants.EVENT_REFRESH_MY_DATA);
        this.mView.toBack();
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public boolean needRefreshSportRecordList() {
        return this.mNeedRefreshSportRecordList;
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void destroy() {
        this.mHasDestroy = true;
        SportSettingPreference.clear();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "页面已经销毁。");
    }

    @Override // com.ido.life.module.sport.history.SportHistoryDetailContract.Presenter
    public void updateFile(String str, final String str2) {
        HealthRepository.getInstance().uploadFile(str, new OnResultCallback() { // from class: com.ido.life.module.sport.history.SportHistoryDetailPresenter.3
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                if (SportHistoryDetailPresenter.this.mHasDestroy) {
                    return;
                }
                String str3 = (String) result.getData();
                SportHealth sportHealthByDateTime = HealthRepository.getInstance().getSportHealthByDateTime(str2);
                Date timeAfterSeconds = DateUtil.getTimeAfterSeconds(DateUtil.getLongFromDate(sportHealthByDateTime.getDateTime()), sportHealthByDateTime.getTotalSeconds());
                sportHealthByDateTime.setEndTime(DateUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", timeAfterSeconds));
                sportHealthByDateTime.setTimestamp(timeAfterSeconds.getTime());
                sportHealthByDateTime.setIcon(str3);
                LocalHealthDataManager.getInstance().saveActivityData(sportHealthByDateTime);
                sportHealthByDateTime.setGps(LocalHealthDataManager.getInstance().getSportGpsData(RunTimeUtil.getInstance().getUserId(), DateUtil.getLongFromDateStr(str2)).getGpsData());
                HealthRepository.getInstance().uploadSportHealth(sportHealthByDateTime, new HealthManager.OnUserSportDetailSidCallback() { // from class: com.ido.life.module.sport.history.SportHistoryDetailPresenter.3.1
                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportDetailSidCallback
                    public void onSuccess(SportHealthDetailSidEntity sportHealthDetailSidEntity) {
                        SportHistoryDetailPresenter.this.mNeedRefreshSportRecordList = true;
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailPresenter.TAG, "单条运动记录上传成功 +onSuccess: " + sportHealthDetailSidEntity.toString());
                    }

                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportDetailSidCallback
                    public void onFailed(String str4) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailPresenter.TAG, "单条运动记录上传失败 onFailed: " + str4);
                    }
                });
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str3) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), SportHistoryDetailPresenter.TAG, "上传图片失败onFailed: ");
            }
        });
    }

    private void getHeartRate(String str, SportHealth sportHealth) {
        int[] iArr = (int[]) GsonUtil.fromJson(str, int[].class);
        if (iArr != null && iArr.length > 0) {
            int extremeSecond = sportHealth.getExtremeSecond();
            int anaerobicSecond = sportHealth.getAnaerobicSecond();
            int aerobicSeconds = sportHealth.getAerobicSeconds();
            int burnFatSeconds = sportHealth.getBurnFatSeconds();
            int warmupSeconds = sportHealth.getWarmupSeconds();
            CommonLogUtil.d(TAG, "getTodayLastHeartRate: " + extremeSecond + AppInfo.DELIM + anaerobicSecond + AppInfo.DELIM + aerobicSeconds + "，" + burnFatSeconds + "，" + warmupSeconds);
            int[] iArr2 = {ResourceUtil.getColor(R.color.color_sport_red), ResourceUtil.getColor(R.color.color_sport_orange), ResourceUtil.getColor(R.color.color_sport_yellow), ResourceUtil.getColor(R.color.color_sport_green), ResourceUtil.getColor(R.color.color_sport_cyan)};
            ArrayList arrayList = new ArrayList();
            PieChartBean pieChartBean = new PieChartBean();
            pieChartBean.setColor(iArr2[0]);
            pieChartBean.setValue((float) extremeSecond);
            arrayList.add(pieChartBean);
            PieChartBean pieChartBean2 = new PieChartBean();
            pieChartBean2.setColor(iArr2[1]);
            pieChartBean2.setValue((float) anaerobicSecond);
            arrayList.add(pieChartBean2);
            PieChartBean pieChartBean3 = new PieChartBean();
            pieChartBean3.setColor(iArr2[2]);
            pieChartBean3.setValue(aerobicSeconds);
            arrayList.add(pieChartBean3);
            PieChartBean pieChartBean4 = new PieChartBean();
            pieChartBean4.setColor(iArr2[3]);
            pieChartBean4.setValue(burnFatSeconds);
            arrayList.add(pieChartBean4);
            PieChartBean pieChartBean5 = new PieChartBean();
            pieChartBean5.setColor(iArr2[4]);
            pieChartBean5.setValue(warmupSeconds);
            arrayList.add(pieChartBean5);
            if (extremeSecond == 0 && anaerobicSecond == 0 && aerobicSeconds == 0 && burnFatSeconds == 0 && warmupSeconds == 0) {
                setDefaultPieChart();
            } else {
                this.mView.setHeartPieChart(arrayList);
            }
            if (extremeSecond == 0) {
                this.mView.setMaxExerciseTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            } else if (extremeSecond < 60 && extremeSecond > 0) {
                this.mView.setMaxExerciseTime("<1");
            } else {
                this.mView.setMaxExerciseTime(String.valueOf(extremeSecond / 60));
            }
            if (anaerobicSecond != 0 && anaerobicSecond < 60 && anaerobicSecond > 0) {
                this.mView.setNoEndurance(String.valueOf(anaerobicSecond / 60));
            } else {
                this.mView.setNoEndurance(String.valueOf(anaerobicSecond / 60));
            }
            if (aerobicSeconds == 0) {
                this.mView.setAerobicEndurance(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            } else if (aerobicSeconds < 60 && aerobicSeconds > 0) {
                this.mView.setAerobicEndurance("<1");
            } else {
                this.mView.setAerobicEndurance(String.valueOf(aerobicSeconds / 60));
            }
            if (burnFatSeconds == 0) {
                this.mView.setBurningHeartRate(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            } else if (burnFatSeconds < 60 && burnFatSeconds > 0) {
                this.mView.setBurningHeartRate("<1");
            } else {
                this.mView.setBurningHeartRate(String.valueOf(burnFatSeconds / 60));
            }
            if (warmupSeconds == 0) {
                this.mView.setWarmUpHeart(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                return;
            } else if (warmupSeconds < 60 && warmupSeconds > 0) {
                this.mView.setWarmUpHeart("<1");
                return;
            } else {
                this.mView.setWarmUpHeart(String.valueOf(warmupSeconds / 60));
                return;
            }
        }
        setDefaultPieChart();
    }

    private void setDefaultPieChart() {
        int[] iArr = {ResourceUtil.getColor(R.color.color_sport_gray)};
        ArrayList arrayList = new ArrayList();
        PieChartBean pieChartBean = new PieChartBean();
        pieChartBean.setColor(iArr[0]);
        pieChartBean.setValue(100.0f);
        arrayList.add(pieChartBean);
        this.mView.setHeartPieChart(arrayList);
        this.mView.setMaxExerciseTime(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mView.setNoEndurance(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mView.setAerobicEndurance(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mView.setBurningHeartRate(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mView.setWarmUpHeart(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
    }

    public static List<TimeLineHeartRateItem> resovleHeartRate(String str, int i) {
        int[] iArr = (int[]) GsonUtil.fromJson(str, int[].class);
        ArrayList arrayList = new ArrayList();
        if (iArr != null) {
            int i2 = 0;
            for (int i3 : iArr) {
                TimeLineHeartRateItem timeLineHeartRateItem = new TimeLineHeartRateItem();
                timeLineHeartRateItem.setHrTime(i2);
                timeLineHeartRateItem.setHrValue(i3);
                arrayList.add(timeLineHeartRateItem);
                i2 = i != 0 ? i2 + 1 : i2 + 5;
            }
        }
        return arrayList;
    }

    private List<HistoryRecordDetailsData> completePace(final List<LatLngBean> list, final int i, final int i2, final boolean z, final SportHealth sportHealth) {
        final ArrayList arrayList = new ArrayList();
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.sport.history.SportHistoryDetailPresenter.4
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                int i3;
                int i4;
                int i5;
                Object obj = null;
                int i6 = 2;
                if ((RunTimeUtil.getInstance().getUnitSet() == 2 && UnitUtil.getKm2mile(i2) <= 1000.0f) || i2 <= 1000) {
                    if (RunTimeUtil.getInstance().getUnitSet() != 2) {
                        int i7 = i2;
                        if (i7 <= 1000) {
                            arrayList.add(new HistoryRecordDetailsData("1", i7 != 0 ? (int) (i / (i7 / 1000.0f)) : 0, i));
                        }
                    } else if (UnitUtil.getKm2mile(i2 / 1000.0f) <= 1.0f) {
                        int i8 = i2;
                        arrayList.add(new HistoryRecordDetailsData("1", i8 != 0 ? (int) (i / UnitUtil.getKm2mile(i8 / 1000.0f)) : 0, i));
                    }
                } else {
                    float fCalculateLineDistance = 0.0f;
                    long longFromDateStr = 0;
                    int iMin = 0;
                    int i9 = 1;
                    while (i < list.size()) {
                        if (SportHistoryDetailPresenter.this.mHasDestroy) {
                            return obj;
                        }
                        if (i > 0) {
                            int i10 = i - 1;
                            fCalculateLineDistance += MapHelper.calculateLineDistance((LatLngBean) list.get(i10), (LatLngBean) list.get(i));
                            try {
                                StringBuilder sb = new StringBuilder();
                                sb.append("completePace: 时间差 ");
                                sb.append(i);
                                sb.append(AppInfo.DELIM);
                                sb.append(((LatLngBean) list.get(i)).currentTimeMillis);
                                sb.append(AppInfo.DELIM);
                                sb.append(((LatLngBean) list.get(i)).currentTimeMillis);
                                sb.append(AppInfo.DELIM);
                                i5 = i9;
                                sb.append(DateUtil.getLongFromDateStr(((LatLngBean) list.get(i)).currentTimeMillis) - DateUtil.getLongFromDateStr(((LatLngBean) list.get(i10)).currentTimeMillis));
                                CommonLogUtil.d(SportHistoryDetailPresenter.TAG, sb.toString());
                                longFromDateStr = (long) (longFromDateStr + ((DateUtil.getLongFromDateStr(((LatLngBean) list.get(i)).currentTimeMillis) - DateUtil.getLongFromDateStr(((LatLngBean) list.get(i10)).currentTimeMillis)) / 1000.0f));
                                CommonLogUtil.d(SportHistoryDetailPresenter.TAG, "completePace: tempDurations" + fCalculateLineDistance + AppInfo.DELIM + longFromDateStr);
                            } catch (Exception unused) {
                            }
                            if (RunTimeUtil.getInstance().getUnitSet() != i6) {
                                i3 = i5;
                                if (fCalculateLineDistance / 1000.0f >= i3) {
                                    String strValueOf = String.valueOf(i3);
                                    int i11 = (int) (longFromDateStr / ((long) i3));
                                    CommonLogUtil.d(SportHistoryDetailPresenter.TAG, "completePace: useTime" + longFromDateStr + AppInfo.DELIM + i3 + AppInfo.DELIM + i11);
                                    int iMin2 = Math.min(iMin, i11);
                                    HistoryRecordDetailsData historyRecordDetailsData = new HistoryRecordDetailsData(strValueOf, i11, longFromDateStr);
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("添加的配速item");
                                    sb2.append(historyRecordDetailsData.toString());
                                    CommonLogUtil.d(SportHistoryDetailPresenter.TAG, sb2.toString());
                                    i4 = i3 + 1;
                                    arrayList.add(historyRecordDetailsData);
                                    iMin = iMin2;
                                } else {
                                    int i12 = i2;
                                    if (i3 == (i12 / 1000) + 1) {
                                        int i13 = (int) (((long) i) - longFromDateStr);
                                        int i14 = i12 - ((i12 / 1000) * 1000);
                                        HistoryRecordDetailsData historyRecordDetailsData2 = new HistoryRecordDetailsData(String.valueOf(i14), (int) (i13 / (i14 / 1000.0f)), i13);
                                        CommonLogUtil.d(SportHistoryDetailPresenter.TAG, "添加的配速item-=-==>" + historyRecordDetailsData2.toString());
                                        arrayList.add(historyRecordDetailsData2);
                                        return null;
                                    }
                                }
                            } else if (UnitUtil.getKm2mile(fCalculateLineDistance / 1000.0f) >= i5) {
                                String strValueOf2 = String.valueOf(i5);
                                int i15 = (int) (longFromDateStr / ((long) i5));
                                iMin = Math.min(iMin, i15);
                                HistoryRecordDetailsData historyRecordDetailsData3 = new HistoryRecordDetailsData(strValueOf2, i15, longFromDateStr);
                                i4 = i5 + 1;
                                arrayList.add(historyRecordDetailsData3);
                            } else {
                                if (i5 == ((int) (UnitUtil.getKm2mile(i2) / 1000.0f)) + 1) {
                                    int i16 = (int) (((long) i) - longFromDateStr);
                                    int km2mile = (int) (UnitUtil.getKm2mile(i2) - (((int) (UnitUtil.getKm2mile(i2) / 1000.0f)) * 1000));
                                    arrayList.add(new HistoryRecordDetailsData(String.valueOf(km2mile), (int) (i16 / (km2mile / 1000.0f)), i16));
                                    return null;
                                }
                                i4 = i5;
                            }
                            i++;
                            i9 = i4;
                            obj = null;
                            i6 = 2;
                        } else {
                            i3 = i9;
                        }
                        i4 = i3;
                        i++;
                        i9 = i4;
                        obj = null;
                        i6 = 2;
                    }
                }
                return obj;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                int iMax = 0;
                int iMin = 0;
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (SportHistoryDetailPresenter.this.mHasDestroy) {
                        return;
                    }
                    if (i3 == 0) {
                        iMin = ((HistoryRecordDetailsData) arrayList.get(0)).getPaceSpeed();
                    }
                    iMin = Math.min(iMin, ((HistoryRecordDetailsData) arrayList.get(i3)).getPaceSpeed());
                    iMax = Math.max(iMax, ((HistoryRecordDetailsData) arrayList.get(i3)).getPaceSpeed());
                }
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    if (SportHistoryDetailPresenter.this.mHasDestroy) {
                        return;
                    }
                    CommonLogUtil.d(SportHistoryDetailPresenter.TAG, "getUnitSpeed: " + ((HistoryRecordDetailsData) arrayList.get(i4)).toString());
                    if (iMin == ((HistoryRecordDetailsData) arrayList.get(i4)).getPaceSpeed()) {
                        ((HistoryRecordDetailsData) arrayList.get(i4)).setFaster(true);
                    } else {
                        ((HistoryRecordDetailsData) arrayList.get(i4)).setFaster(false);
                    }
                }
                if (iMax == 0 || iMin == 0) {
                    SportHistoryDetailPresenter.this.mView.showSportItemSpeed(false);
                    SportHistoryDetailPresenter.this.mView.showSportItemPace(false);
                    return;
                }
                if (z) {
                    SportHistoryDetailPresenter.this.mView.showSportItemPace(true);
                    int i5 = i2;
                    if (i5 < 1000 || i5 % 1000 != 0) {
                        SportHistoryDetailPresenter.this.mView.setKmSpace(arrayList, iMin, iMax, false);
                    } else {
                        SportHistoryDetailPresenter.this.mView.setKmSpace(arrayList, iMin, iMax, true);
                    }
                } else {
                    SportHistoryDetailPresenter.this.mView.showSportItemSpeed(true);
                    if (UnitUtil.getKm2mile(i2) < 1000.0f || UnitUtil.getKm2mile(i2) % 1000.0f != 0.0f) {
                        SportHistoryDetailPresenter.this.mView.setKmSpeed(arrayList, iMin, iMax, false);
                    } else {
                        SportHistoryDetailPresenter.this.mView.setKmSpeed(arrayList, iMin, iMax, true);
                    }
                }
                if (z) {
                    if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                        SportHistoryDetailPresenter.this.mView.setSportItemDistanceLabel(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit));
                        SportHistoryDetailPresenter.this.mView.setSportItemSpaceTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_unit));
                    } else {
                        SportHistoryDetailPresenter.this.mView.setSportItemDistanceLabel(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
                        SportHistoryDetailPresenter.this.mView.setSportItemSpaceTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_peace_unit_mi));
                    }
                    SportHistoryDetailPresenter.this.mView.setSportItemPaceAverage(SportHistoryDetailPresenter.this.getAvgPace(sportHealth));
                    SportHistoryDetailPresenter.this.mView.setSportItemPaceFaster(DateUtil.computeTimeMS(iMin));
                    return;
                }
                if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                    SportHistoryDetailPresenter.this.mView.setSportItemSpeedTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit));
                    SportHistoryDetailPresenter.this.mView.setSportSpeedItemKm(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit));
                } else {
                    SportHistoryDetailPresenter.this.mView.setSportItemSpeedTitleUnit(LanguageUtil.getLanguageText(R.string.sport_detail_speed_unit_mi));
                    SportHistoryDetailPresenter.this.mView.setSportSpeedItemKm(LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
                }
                SportHistoryDetailPresenter.this.mView.setSportItemSpeedAverage(SportHistoryDetailPresenter.this.getAvgSpeed(sportHealth));
            }
        }).execute("");
        return arrayList;
    }
}