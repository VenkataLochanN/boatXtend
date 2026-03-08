package com.ido.life.module.sport.history.swim;

import android.text.TextUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.NumUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.SportHealthDetailEntity;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.IHealthRepository;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportSwimItem;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.sport.history.swim.SportSwimContract;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportSwimPresenter implements SportSwimContract.Presenter {
    private static final String TAG = "SportSwimPresenter";
    private IHealthRepository mHealthRepository = HealthRepository.getInstance();
    private int mSportType;
    private long mUserId;
    SportSwimContract.View mView;
    SportHealth sportHealth;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportSwimPresenter(SportSwimContract.View view, long j) {
        this.mView = view;
        this.mUserId = j;
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.Presenter
    public void getSwimSportData(String str) {
        this.sportHealth = this.mHealthRepository.getSportHealthByDateTime(str);
        SportHealth sportHealth = this.sportHealth;
        if (sportHealth == null) {
            SportSwimContract.View view = this.mView;
            if (view == null) {
                return;
            }
            view.toBack();
            return;
        }
        if (!sportHealth.isLoadDetail() && RunTimeUtil.getInstance().getUserId() > 0) {
            SportSwimContract.View view2 = this.mView;
            if (view2 == null) {
                return;
            }
            view2.showLoading();
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                HealthRepository.getInstance().getSportDetailBySid(this.sportHealth.getSid(), new HealthManager.OnUserSportRecordDetailCallback() { // from class: com.ido.life.module.sport.history.swim.SportSwimPresenter.1
                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                    public void onSuccess(SportHealthDetailEntity sportHealthDetailEntity) {
                        if (SportSwimPresenter.this.mView == null) {
                            return;
                        }
                        if (sportHealthDetailEntity == null || sportHealthDetailEntity.getResult() == null) {
                            SportSwimPresenter.this.mView.toBack();
                            return;
                        }
                        SportSwimPresenter.this.sportHealth = sportHealthDetailEntity.getResult();
                        SportSwimPresenter.this.sportHealth.setLoadDetail(true);
                        SportSwimPresenter.this.sportHealth.setIsUploaded(true);
                        SportSwimPresenter.this.sportHealth.setUserId(SportSwimPresenter.this.mUserId);
                        GreenDaoUtil.saveActivityData(SportSwimPresenter.this.sportHealth);
                        SportSwimPresenter.this.mView.hideLoading();
                        SportSwimPresenter.this.showSportDetail();
                        CommonLogUtil.d(SportSwimPresenter.TAG, "onSuccess: " + sportHealthDetailEntity.toString());
                    }

                    @Override // com.ido.life.data.health.remote.HealthManager.OnUserSportRecordDetailCallback
                    public void onFailed(String str2) {
                        if (SportSwimPresenter.this.mView == null) {
                            return;
                        }
                        SportSwimPresenter.this.mView.hideLoading();
                        SportSwimPresenter.this.showRetryView();
                        CommonLogUtil.d(SportSwimPresenter.TAG, "onFailed: " + str2);
                    }
                });
                return;
            } else {
                showNetErrorView();
                return;
            }
        }
        showSportDetail();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRetryView() {
        SportSwimContract.View view = this.mView;
        if (view == null) {
            return;
        }
        view.showSportRetryView(true);
        this.mView.showSportDataView(false);
        this.mView.showSportNoNet(false);
        this.mView.setLoadLoadTitleShow(true);
        this.mView.showRightBtn(false);
        this.mView.setShareView(false);
        this.mView.setRightButtonView(false);
    }

    private void showNetErrorView() {
        SportSwimContract.View view = this.mView;
        if (view == null) {
            return;
        }
        view.showSportRetryView(false);
        this.mView.showSportDataView(false);
        this.mView.showSportNoNet(true);
        this.mView.setLoadLoadTitleShow(true);
        this.mView.showRightBtn(false);
        this.mView.setShareView(false);
        this.mView.setRightButtonView(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSportDetail() {
        SportSwimContract.View view = this.mView;
        if (view == null) {
            return;
        }
        int i = 0;
        view.showSportRetryView(false);
        this.mView.showSportDataView(true);
        this.mView.showSportNoNet(false);
        this.mView.setLoadLoadTitleShow(false);
        this.mView.setShareView(true);
        this.mView.setRightButtonView(true);
        SportLogHelper.saveSportLog(TAG, "getSwimSportData: " + this.sportHealth.toString());
        if (this.mSportType == 55) {
            this.mView.hidePoolSwimView();
            this.mView.setOpenWaterData(this.sportHealth.getStartTime(), this.sportHealth.getNumCalories(), this.sportHealth.getTotalSeconds());
            return;
        }
        this.mView.hideOpenWaterSwimView();
        this.mView.setIndoorSwimNormalData(this.sportHealth);
        ArrayList arrayList = new ArrayList();
        int totalSeconds = this.sportHealth.getTotalSeconds() / 60;
        if (totalSeconds <= 3) {
            arrayList.add(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            if (totalSeconds > 1 && totalSeconds <= 2) {
                arrayList.add("1");
                arrayList.add("2");
            } else if (totalSeconds > 2 && totalSeconds <= 3) {
                arrayList.add("1");
                arrayList.add("2");
                arrayList.add(Constants.DIALDEFNED_VERSION_CONNECT);
            }
        } else {
            int i2 = totalSeconds / 4;
            arrayList.add(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            for (int i3 = 1; i3 < 4; i3++) {
                arrayList.add((i2 * i3) + "");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        float fFloat2float = NumUtil.float2float((float) ((((double) this.sportHealth.getMinPace()) / 60.0d) / 4.0d));
        for (int i4 = 1; i4 < 5; i4++) {
            if (i4 == 1) {
                arrayList2.add(DateUtil.computeTimePace(NumUtil.save2Point(fFloat2float / 10.0f)));
            }
            arrayList2.add(DateUtil.computeTimePace(NumUtil.save2Point(i4 * fFloat2float)));
        }
        ArrayList arrayList3 = new ArrayList();
        int maxFrequency = this.sportHealth.getMaxFrequency() / 4;
        for (int i5 = 1; i5 < 5; i5++) {
            arrayList3.add((maxFrequency * i5) + "");
        }
        ArrayList arrayList4 = new ArrayList();
        int maxSwolf = this.sportHealth.getMaxSwolf() / 4;
        for (int i6 = 1; i6 < 5; i6++) {
            arrayList4.add((maxSwolf * i6) + "");
        }
        if (this.sportHealth.getSwolf() == null) {
            this.mView.setPeaceVisible(false);
            this.mView.setFrequencyChartVisible(false);
            this.mView.setSwolfChartVisible(false);
            return;
        }
        String items = this.sportHealth.getSwolf().getItems();
        if (TextUtils.isEmpty(items) || "null".equals(items)) {
            this.mView.setPeaceVisible(false);
            this.mView.setFrequencyChartVisible(false);
            this.mView.setSwolfChartVisible(false);
            return;
        }
        List listAnalysisJsonObjectToList = GsonUtil.analysisJsonObjectToList(this.sportHealth.getSwolf().getItems(), SportSwimItem.class);
        if (listAnalysisJsonObjectToList == null || listAnalysisJsonObjectToList.size() <= 0) {
            return;
        }
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        int i7 = 0;
        while (i7 < listAnalysisJsonObjectToList.size()) {
            if (i7 == 0) {
                arrayList5.add(Integer.valueOf(i));
                arrayList6.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i)).speed));
                arrayList7.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i)).frequency));
                arrayList8.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i)).swolf));
            } else {
                int i8 = i7 - 1;
                if (((SportSwimItem) listAnalysisJsonObjectToList.get(i8)).differenceTime + ((SportSwimItem) listAnalysisJsonObjectToList.get(i8)).stopTime > 0) {
                    arrayList5.add(Integer.valueOf(((Integer) arrayList5.get(arrayList5.size() - 1)).intValue() + ((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).differenceTime + ((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).stopTime));
                    arrayList6.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).speed));
                    arrayList7.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).frequency));
                    arrayList8.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).swolf));
                }
            }
            arrayList5.add(Integer.valueOf(((Integer) arrayList5.get(arrayList5.size() - 1)).intValue() + ((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).duration));
            arrayList6.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).speed));
            arrayList7.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).frequency));
            arrayList8.add(Integer.valueOf(((SportSwimItem) listAnalysisJsonObjectToList.get(i7)).swolf));
            i7++;
            i = 0;
        }
        try {
            int size = arrayList5.size() < arrayList6.size() ? arrayList5.size() : arrayList6.size();
            if (arrayList7.size() < size) {
                size = arrayList7.size();
            }
            if (arrayList8.size() < size) {
                size = arrayList8.size();
            }
            ArrayList arrayList9 = new ArrayList();
            ArrayList arrayList10 = new ArrayList();
            ArrayList arrayList11 = new ArrayList();
            int i9 = 0;
            while (i9 < size) {
                arrayList9.add(new BaseCharBean(0, ((Integer) arrayList5.get(i9)).intValue(), ((Integer) arrayList6.get(i9)).intValue()));
                arrayList10.add(new BaseCharBean(0, ((Integer) arrayList5.get(i9)).intValue(), ((Integer) arrayList7.get(i9)).intValue()));
                arrayList11.add(new BaseCharBean(0, ((Integer) arrayList5.get(i9)).intValue(), ((Integer) arrayList8.get(i9)).intValue()));
                i9++;
                size = size;
                arrayList3 = arrayList3;
                arrayList4 = arrayList4;
                arrayList6 = arrayList6;
            }
            CommonLogUtil.d(TAG, "getSwimSportData: ");
            this.mView.setPaceChartView(arrayList, arrayList2, arrayList9, this.sportHealth.getTotalSeconds(), this.sportHealth.getMinPace());
            this.mView.setFrequencyChartView(arrayList, arrayList3, arrayList10, this.sportHealth.getTotalSeconds(), this.sportHealth.getMaxFrequency());
            this.mView.setSwolfChartView(arrayList, arrayList4, arrayList11, this.sportHealth.getTotalSeconds(), this.sportHealth.getMaxSwolf());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.Presenter
    public void getSwimNameByType(int i) {
        SportSwimContract.View view = this.mView;
        if (view == null) {
            return;
        }
        this.mSportType = i;
        view.setSwimName(MotionTypeManager.INSTANCE.getMotionTypeName(i));
    }

    @Override // com.ido.life.module.sport.history.swim.SportSwimContract.Presenter
    public void deleteRecord(final String str, final String str2) {
        SportSwimContract.View view = this.mView;
        if (view == null) {
            return;
        }
        view.showLoading();
        if (!TextUtils.isEmpty(str2)) {
            if (HealthRepository.getInstance() == null) {
                return;
            }
            HealthRepository.getInstance().deleteRecord(str2, new OnResponseCallback() { // from class: com.ido.life.module.sport.history.swim.SportSwimPresenter.2
                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onSuccess() {
                    SportSwimPresenter.this.mView.hideLoading();
                    CommonLogUtil.d(SportSwimPresenter.TAG, "onSuccess: " + str2);
                    HealthManager.deleteLocalSportRecord(str);
                    SportSwimPresenter.this.mView.toBack();
                }

                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onFailed(String str3) {
                    SportSwimPresenter.this.mView.hideLoading();
                    CommonLogUtil.d(SportSwimPresenter.TAG, "onFailed: " + str3);
                }
            });
        } else {
            HealthManager.deleteLocalSportRecord(str);
            this.mView.toBack();
        }
    }
}