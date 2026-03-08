package com.ido.life.module.sport.history.indoor;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StringUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.data.api.entity.SportHealthDetailEntity;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.IHealthRepository;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.module.sport.history.indoor.SportIndoorRunContract;
import com.ido.life.module.sport.setting.SportSettingPreference;
import com.ido.life.module.sport.util.BigDecimalUtil;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SportDataUtil;
import com.ido.life.util.UnitUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportIndoorRunPresenter implements SportIndoorRunContract.Presenter {
    private static final int DEFAULT_MINUTE = 60;
    private static final String TAG = "SportIndoorRunPresenter";
    private IHealthRepository mHealthRepository = HealthRepository.getInstance();
    private String mSid;
    private int mSportType;
    private SportIndoorRunContract.View mView;

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.Presenter
    public void getSportData(SportHealth sportHealth) {
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.Presenter
    public void getSportDataBySid(String str) {
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportIndoorRunPresenter(SportIndoorRunContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.Presenter
    public void getSportDataByDate(int i, String str, final int i2) {
        SportHealth sportHealthByDateTime = this.mHealthRepository.getSportHealthByDateTime(str);
        if (sportHealthByDateTime == null) {
            this.mView.toBack();
            return;
        }
        this.mSid = sportHealthByDateTime.getSid();
        CommonLogUtil.d(TAG, "getSportDataByDate: " + sportHealthByDateTime.toString());
        if (!sportHealthByDateTime.isLoadDetail() && RunTimeUtil.getInstance().getUserId() > 0) {
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                this.mView.showLoading();
                HealthRepository.getInstance().getSportDetailBySid(sportHealthByDateTime.getSid(), new HealthManager.OnUserSportRecordDetailCallback() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunPresenter.1
                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                    public void onSuccess(SportHealthDetailEntity sportHealthDetailEntity) {
                        SportHealth result = sportHealthDetailEntity.getResult();
                        result.setLoadDetail(true);
                        result.setIsUploaded(true);
                        LocalHealthDataManager.getInstance().saveActivityData(result);
                        SportIndoorRunPresenter.this.showSportDetail(result, i2);
                        Log.d(SportIndoorRunPresenter.TAG, "onSuccess: " + result.toString());
                        SportIndoorRunPresenter.this.mView.hideLoading();
                        CommonLogUtil.d(SportIndoorRunPresenter.TAG, "onSuccess: " + sportHealthDetailEntity.toString());
                    }

                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                    public void onFailed(String str2) {
                        SportIndoorRunPresenter.this.mView.hideLoading();
                        SportIndoorRunPresenter.this.showRetryView();
                        CommonLogUtil.d(SportIndoorRunPresenter.TAG, "onFailed: " + str2);
                    }
                });
                return;
            } else {
                showNetErrorView();
                return;
            }
        }
        showSportDetail(sportHealthByDateTime, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRetryView() {
        this.mView.showSportRetryView(true);
        this.mView.showSportDataView(false);
        this.mView.showSportNoNet(false);
        this.mView.setLoadLoadTitleShow(true);
        this.mView.showRightBtn(false);
    }

    private void showNetErrorView() {
        this.mView.showSportRetryView(false);
        this.mView.showSportDataView(false);
        this.mView.showSportNoNet(true);
        this.mView.setLoadLoadTitleShow(true);
        this.mView.showRightBtn(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSportDetail(SportHealth sportHealth, int i) {
        if (i == 0) {
            this.mView.showRightBtn(false);
        } else {
            this.mView.showRightBtn(true);
        }
        this.mView.showSportRetryView(false);
        this.mView.showSportDataView(true);
        this.mView.showSportNoNet(false);
        this.mView.setLoadLoadTitleShow(false);
        this.mView.setSportStartTime(DateUtil.format(DateUtil.string2Date(sportHealth.getDateTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_DMYHm));
        setSportTarget(sportHealth);
        showItemsByType(this.mSportType, sportHealth);
        getUnitSpeed(sportHealth);
        setSportStepFrequency(sportHealth);
        this.mView.setFirstItemValue(DateUtil.computeTimeHMS(sportHealth.getTotalSeconds()));
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
        if (sportHealth.getType() == 53 || sportHealth.getType() == 49) {
            if (avgHrValue == 0) {
                this.mView.setSixItemValue("--");
            } else {
                this.mView.setSixItemValue(String.valueOf(avgHrValue));
            }
            this.mView.setSixItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_rate));
            this.mView.setSixItemUnit(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
            this.mView.setFiveItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_step));
            this.mView.setFiveItemValue(String.valueOf(sportHealth.getNumSteps()));
            this.mView.setFiveItemUnit(LanguageUtil.getLanguageText(R.string.step_unit));
        } else {
            if (sportHealth.getStepRange() == 0) {
                this.mView.setSixItemValue(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            } else {
                this.mView.setSixItemValue(String.valueOf(sportHealth.getStepRange()));
            }
            setRangeSixUnit();
            this.mView.setFiveItemValue(String.valueOf(sportHealth.getStepRate()));
        }
        if (sportHealth.getHeartrate() != null && !TextUtils.isEmpty(sportHealth.getHeartrate().getItmes()) && sportHealth.getHeartrate().getItmes().length() > 2) {
            this.mView.showSportItemRate(true);
            this.mView.setXLabelList(getBottomLabelList(sportHealth.getTotalSeconds()));
            this.mView.setYLabelList(getYLabelList(sportHealth.getMaxHrValue()));
            this.mView.setRateList(getChartRate(sportHealth.getHeartrate().getItmes(), sportHealth.getMaxHrValue()));
            this.mView.setSportRateMax(String.valueOf(sportHealth.getMaxHrValue()));
            this.mView.setSportChartRateAvg(String.valueOf(avgHrValue));
            getHeartRate(sportHealth.getHeartrate().getItmes(), sportHealth);
            return;
        }
        this.mView.showSportItemRate(false);
    }

    private void setRangeSixUnit() {
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mView.setSixItemUnit(LanguageUtil.getLanguageText(R.string.public_unit_cm));
        } else {
            this.mView.setSixItemUnit(LanguageUtil.getLanguageText(R.string.public_unit_inch));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getUnitSpeed(com.ido.life.database.model.SportHealth r12) {
        /*
            Method dump skipped, instruction units count: 515
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.history.indoor.SportIndoorRunPresenter.getUnitSpeed(com.ido.life.database.model.SportHealth):void");
    }

    private String getMaxSpeed(SportHealth sportHealth, int i) {
        if (sportHealth.getMaxSpeed() != 0) {
            return RunTimeUtil.getInstance().getUnitSet() == 2 ? SportDataUtil.changeSpeed2mile(sportHealth.getMaxSpeed()) : SportDataUtil.formatAvgSpeed(sportHealth.getMaxSpeed() / 100.0f);
        }
        return SportDataUtil.getSpeedByPeace(i);
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

    private String getAvgSpeed(SportHealth sportHealth) {
        if (sportHealth.getAvgSpeed() != 0) {
            return RunTimeUtil.getInstance().getUnitSet() == 2 ? SportDataUtil.changeSpeed2mile(sportHealth.getMaxSpeed()) : SportDataUtil.formatAvgSpeed(sportHealth.getAvgSpeed() / 100.0f);
        }
        return SportDataUtil.computeTimeSpeed(sportHealth.getDistance(), sportHealth.getTotalSeconds());
    }

    private String getAvgPace(SportHealth sportHealth) {
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

    private void setSportStepFrequency(SportHealth sportHealth) {
        if (sportHealth.getDistance() == 0) {
            this.mView.showSportItemFrequency(false);
            return;
        }
        if (sportHealth.getType() == 49 || sportHealth.getType() == 53 || sportHealth.getType() == 2 || sportHealth.getType() == 4 || sportHealth.getType() == 52 || sportHealth.getType() == 48) {
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
            if (getStepChartRate(sportHealth.getRate().getItmes(), sportHealth.getStepRateMax()) != null) {
                this.mView.setStepFrequencyList(getStepChartRate(sportHealth.getRate().getItmes(), sportHealth.getStepRateMax()));
                return;
            }
            return;
        }
        this.mView.showSportItemFrequency(false);
    }

    public List<String> getStepBottomLabelList(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 4; i2 <= 4 && i2 > 0; i2--) {
            arrayList.add(String.valueOf((i / i2) / 60));
        }
        arrayList.add(LanguageUtil.getLanguageText(R.string.sport_record_chart_step_time_unit));
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

    /* JADX WARN: Removed duplicated region for block: B:53:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0179  */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void showItemsByType(int r7, com.ido.life.database.model.SportHealth r8) {
        /*
            Method dump skipped, instruction units count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.history.indoor.SportIndoorRunPresenter.showItemsByType(int, com.ido.life.database.model.SportHealth):void");
    }

    private void setOtherSportType(SportHealth sportHealth) {
        this.mView.showSportDataItemOne(true);
        this.mView.showSportDataItemTwo(false);
        this.mView.showSportDataItemThree(false);
        this.mView.showSportDataItemFour(false);
        this.mView.setSportDistanceUnit(LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        this.mView.setSportDistance(String.valueOf(sportHealth.getNumCalories()), 0);
        this.mView.setSecondItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_rate));
        if (sportHealth.getAvgHrValue() == 0) {
            this.mView.setSecondItemValue("--");
        } else {
            this.mView.setSecondItemValue(String.valueOf(sportHealth.getAvgHrValue()));
        }
        this.mView.setSecondItemUnit(LanguageUtil.getLanguageText(R.string.sport_record_heart_rate_unit));
        this.mView.showFourItem(false);
    }

    private void setSportDistance(SportHealth sportHealth) {
        String str;
        if (sportHealth.getNumCalories() == 0) {
            this.mView.setSecondItemValue(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        } else {
            this.mView.setSecondItemValue(String.valueOf(sportHealth.getNumCalories()));
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float km2mile = UnitUtil.getKm2mile(sportHealth.getDistance() / 1000.0f);
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            str = decimalFormat.format(Float.parseFloat(NumUtil.float2String(r2, 2)));
            this.mView.setSportDistanceUnit(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit));
        } else {
            str = decimalFormat.format(Float.parseFloat(NumUtil.float2String(km2mile, 2)));
            this.mView.setSportDistanceUnit(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi));
        }
        if (this.mSportType == 50 && sportHealth.getSourceType() == 2) {
            this.mView.setSportDistance(str, 2);
        } else {
            this.mView.setSportDistance(str, 0);
        }
        String avgPace = getAvgPace(sportHealth);
        int i = this.mSportType;
        if (i != 50 && i != 51) {
            this.mView.setThreeItemValue(avgPace);
            setPeaceThreeUnit();
        }
        String avgSpeed = getAvgSpeed(sportHealth);
        int i2 = this.mSportType;
        if (i2 == 50 || i2 == 51) {
            if (!TextUtils.isEmpty(avgSpeed)) {
                this.mView.setThreeItemValue(avgSpeed);
            } else {
                this.mView.setThreeItemValue(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            }
            this.mView.setThreeItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_speed_avg));
            setThreeSpeedUnit();
            this.mView.setFourItemValue(String.valueOf(sportHealth.getAvgHrValue()));
            this.mView.setFourItemDesc(LanguageUtil.getLanguageText(R.string.sport_detail_rate));
            this.mView.setFourItemUnit(LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
            return;
        }
        this.mView.setFourItemValue(avgSpeed);
        setFourSpeedUnit();
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

    private List<BaseCharBean> getChartRate(String str, int i) {
        int[] iArr = (int[]) GsonUtil.fromJson(str, int[].class);
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
            CommonLogUtil.d(TAG, "initData: " + i3 + AppInfo.DELIM + f2);
            arrayList.add(new BaseCharBean(0, (float) i3, f2));
        }
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

    private int getHeartRateAvg(String str) {
        int i;
        int[] iArr = (int[]) GsonUtil.fromJson(str, int[].class);
        if (iArr == null || iArr.length <= 0) {
            i = 0;
        } else {
            i = 0;
            for (int i2 : iArr) {
                i += i2;
            }
        }
        if (iArr.length > 0) {
            return i / iArr.length;
        }
        return 0;
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
            CommonLogUtil.d(TAG, "getTodayLastHeartRate: " + extremeSecond + AppInfo.DELIM + anaerobicSecond + AppInfo.DELIM + aerobicSeconds + AppInfo.DELIM + burnFatSeconds + AppInfo.DELIM + warmupSeconds);
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

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.Presenter
    public void deleteRecord(final String str) {
        this.mView.showLoading();
        if (!TextUtils.isEmpty(this.mSid)) {
            HealthRepository.getInstance().deleteRecord(this.mSid, new OnResponseCallback() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunPresenter.2
                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onSuccess() {
                    SportIndoorRunPresenter.this.mView.hideLoading();
                    CommonLogUtil.d(SportIndoorRunPresenter.TAG, "onSuccess: " + SportIndoorRunPresenter.this.mSid);
                    HealthManager.getInstance();
                    HealthManager.deleteLocalSportRecord(str);
                    SportIndoorRunPresenter.this.mView.toBack();
                }

                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onFailed(String str2) {
                    SportIndoorRunPresenter.this.mView.hideLoading();
                    CommonLogUtil.d(SportIndoorRunPresenter.TAG, "onFailed: " + str2);
                }
            });
            return;
        }
        HealthManager.getInstance();
        HealthManager.deleteLocalSportRecord(str);
        this.mView.toBack();
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.Presenter
    public void getSportNameByType(int i) {
        this.mSportType = i;
        this.mView.setSportName(SportDataUtil.getSportNameByType(i));
        this.mView.setLoadTitleText(SportDataUtil.getSportNameByType(i));
    }

    @Override // com.ido.life.module.sport.history.indoor.SportIndoorRunContract.Presenter
    public void clearSportTarget() {
        SportSettingPreference.clear();
    }
}