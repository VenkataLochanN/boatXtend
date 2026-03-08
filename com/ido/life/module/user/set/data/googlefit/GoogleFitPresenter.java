package com.ido.life.module.user.set.data.googlefit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.internal.zzi;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.HistoryClient;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.set.data.GoogleFitAccreditActivity;
import com.ido.life.module.user.set.data.GoogleFitPreference;
import com.ido.life.module.user.set.data.GoogleFitUpload;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class GoogleFitPresenter {
    public static final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 10;
    public static final int GOOGLE_SIGN_IN = 3;
    private static final String TAG = "GoogleFitPresenter";
    private static volatile GoogleFitPresenter instance = null;
    public static boolean subscribed = false;
    private GoogleSignInAccount account;
    private FitnessOptions fitnessOptions;
    private float mDeltaCalorie;
    private float mDeltaDistance;
    private int mDeltaStep;
    private GoogleFitPreference mGoogleFitPreference;
    private float mHeight;
    ReOpenChangeListener mListener;
    private SessionInsertRequest mSleepRequest;
    private long mSleepStartTime;
    private float mWeight;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    private GoogleFitPresenter() {
        saveGoogleFitLog("GoogleFitPresenter: 创建");
        this.fitnessOptions = FitnessOptions.builder().addDataType(DataType.TYPE_STEP_COUNT_DELTA, 1).addDataType(DataType.TYPE_DISTANCE_DELTA, 1).addDataType(DataType.TYPE_HEART_RATE_BPM, 1).addDataType(DataType.TYPE_WEIGHT, 1).addDataType(DataType.TYPE_CALORIES_EXPENDED, 1).addDataType(DataType.TYPE_HEIGHT, 1).build();
        this.mGoogleFitPreference = GoogleFitPreference.getInstance();
    }

    public static GoogleFitPresenter getInstance() {
        if (instance == null) {
            synchronized (GoogleFitPresenter.class) {
                if (instance == null) {
                    instance = new GoogleFitPresenter();
                }
            }
        }
        return instance;
    }

    public void setReOpenListener(ReOpenChangeListener reOpenChangeListener) {
        this.mListener = reOpenChangeListener;
    }

    public void uploadPrivateSafeSettingToServer(final boolean z) {
        SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem savePrivateSafeSettingBeanItem = new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem();
        savePrivateSafeSettingBeanItem.setAttrName(SavePrivateSafeSettingBean.TYPE_GOOGLE_FIT);
        if (z) {
            savePrivateSafeSettingBeanItem.setAttrValue(SavePrivateSafeSettingBean.ON);
        } else {
            savePrivateSafeSettingBeanItem.setAttrValue(SavePrivateSafeSettingBean.OFF);
        }
        savePrivateSafeSettingBeanItem.setTimestamp(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        arrayList.add(savePrivateSafeSettingBeanItem);
        AccountManager.savePriveteSafeSetting(new SavePrivateSafeSettingBean(arrayList), new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.module.user.set.data.googlefit.GoogleFitPresenter.1
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(BaseEntity baseEntity) {
                NormalToast.showToast("googlefit开关状态上传服务器成功：" + z, 2000);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str) {
                NormalToast.showToast("googlefit开关状态上传服务器失败：" + z, 2000);
            }
        });
    }

    public void connectGoogle(Activity activity) {
        saveGoogleFitLog("connectGoogle: 连接google");
        if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveGoogleFitLog("connectGoogle: 没有网络");
            return;
        }
        try {
            this.account = GoogleSignIn.getLastSignedInAccount(IdoApp.getAppContext());
            if (this.account == null) {
                saveGoogleFitLog("account is null,go to signIn");
                if (activity instanceof GoogleFitAccreditActivity) {
                    signIn(activity);
                }
            } else {
                saveGoogleFitLog("account is exist,go to subscriber");
                subscriber(activity);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            signIn(activity);
        }
    }

    private boolean getGoogleFitPermissionIsOpen() {
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            saveGoogleFitLog("getGoogleFitPermissionIsOpen()  返回的googlefit开关状态：" + privateSafeSettingQueryPrivateSafeSetting.getSaveToGoogleFit());
            return privateSafeSettingQueryPrivateSafeSetting.getSaveToGoogleFit();
        }
        saveGoogleFitLog("getGoogleFitPermissionIsOpen()  safeSetting为空，返回googlefit开关状态：false");
        return false;
    }

    public void subscriber(Activity activity) {
        try {
            this.account = GoogleSignIn.getLastSignedInAccount(getContext());
            if (this.account == null) {
                saveGoogleFitLog("account  is null");
                return;
            }
            if (!GoogleSignIn.hasPermissions(this.account, this.fitnessOptions)) {
                saveGoogleFitLog("subscriber: 没有权限，申请权限");
                GoogleSignIn.requestPermissions(activity, 10, this.account, this.fitnessOptions);
                return;
            }
            PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
            if (privateSafeSettingQueryPrivateSafeSetting != null) {
                privateSafeSettingQueryPrivateSafeSetting.setSaveToGoogleFit(true);
                privateSafeSettingQueryPrivateSafeSetting.update();
                ReOpenChangeListener reOpenChangeListener = this.mListener;
                if (reOpenChangeListener != null) {
                    reOpenChangeListener.reShowView();
                }
            }
            saveGoogleFitLog("是否订阅了.....subscribed:" + subscribed);
            if (!subscribed) {
                subscriptionData();
            } else {
                saveGoogleFitLog("subscriber入口: uploadData()");
                uploadData();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setSubScribed(boolean z) {
        subscribed = z;
    }

    private void subscriptionData() {
        subscribe(DataType.TYPE_STEP_COUNT_DELTA);
        subscribe(DataType.TYPE_DISTANCE_DELTA);
        subscribe(DataType.TYPE_HEIGHT);
        subscribe(DataType.TYPE_WEIGHT);
        subscribe(DataType.TYPE_HEART_RATE_BPM);
        subscribe(DataType.TYPE_CALORIES_EXPENDED);
    }

    private void subscribe(final DataType dataType) {
        try {
            this.account = GoogleSignIn.getLastSignedInAccount(getContext());
            if (this.account == null) {
                saveGoogleFitLog("subscribe account  is null");
                return;
            }
            try {
                Fitness.getRecordingClient(getContext(), this.account).subscribe(dataType).addOnSuccessListener(new OnSuccessListener() { // from class: com.ido.life.module.user.set.data.googlefit.-$$Lambda$GoogleFitPresenter$V_hQt3Mg1WpY5vLWCgWUDvWkpeI
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        this.f$0.lambda$subscribe$0$GoogleFitPresenter(dataType, (Void) obj);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.ido.life.module.user.set.data.googlefit.-$$Lambda$GoogleFitPresenter$HHYmQwh19EkBqWaFgBgmDsVpRgM
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        this.f$0.lambda$subscribe$1$GoogleFitPresenter(dataType, exc);
                    }
                });
            } catch (Exception e2) {
                saveGoogleFitLog("subscribe Exception! " + dataType + AppInfo.DELIM + e2.toString());
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public /* synthetic */ void lambda$subscribe$0$GoogleFitPresenter(DataType dataType, Void r3) {
        subscribed = true;
        saveGoogleFitLog("subscribed Successfully! " + dataType.toString());
        if (dataType == DataType.TYPE_CALORIES_EXPENDED) {
            saveGoogleFitLog("订阅完成入口: uploadData()");
            uploadData();
        }
    }

    public /* synthetic */ void lambda$subscribe$1$GoogleFitPresenter(DataType dataType, Exception exc) {
        saveGoogleFitLog("subscribed onFailure! " + dataType + AppInfo.DELIM + exc.toString());
    }

    public void uploadData() {
        if (!getGoogleFitPermissionIsOpen()) {
            saveGoogleFitLog("googleFit.....开关未开");
        } else if (!NetworkUtil.isConnected(VeryFitApp.getApp())) {
            saveGoogleFitLog("没有连接.....开关未开");
        } else {
            uploadData2GoogleFit(createDataSet());
        }
    }

    public void uploadData2GoogleFit(final List<DataUpdateRequest> list) {
        if ((list == null || list.isEmpty()) && this.mSleepRequest == null) {
            saveGoogleFitLog("uploadData2GoogleFit 没有需要上传的数据");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("upload: ");
        sb.append(getContext() == null);
        saveGoogleFitLog(sb.toString());
        this.account = GoogleSignIn.getLastSignedInAccount(IdoApp.getAppContext());
        if (this.account == null) {
            saveGoogleFitLog("account == null,取消上传数据");
        } else {
            new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.user.set.data.googlefit.GoogleFitPresenter.2
                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public void onPostExecute(Object obj) {
                }

                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public Object doInBackground(String... strArr) {
                    List list2 = list;
                    if (list2 != null) {
                        GoogleFitPresenter.this.updateHealthData(list2);
                    }
                    GoogleFitPresenter.this.uploadSleepData();
                    return null;
                }
            }).execute("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadSleepData() {
        if (this.mSleepRequest == null) {
            return;
        }
        if (this.account != null) {
            Fitness.getSessionsClient(getContext(), this.account).insertSession(this.mSleepRequest).addOnSuccessListener(new OnSuccessListener() { // from class: com.ido.life.module.user.set.data.googlefit.-$$Lambda$GoogleFitPresenter$Ngb5kRWl4IlOFQ4CUCp3M0x6UQk
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    this.f$0.lambda$uploadSleepData$2$GoogleFitPresenter((Void) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.ido.life.module.user.set.data.googlefit.-$$Lambda$GoogleFitPresenter$MyfRrCz15NKz434npDRfoeVV-OI
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    this.f$0.lambda$uploadSleepData$3$GoogleFitPresenter(exc);
                }
            });
        } else {
            saveGoogleFitLog("uploadSleepData account is null ");
        }
    }

    public /* synthetic */ void lambda$uploadSleepData$2$GoogleFitPresenter(Void r3) {
        saveGoogleFitLog("uploadSleepData inset sleep success");
        this.mGoogleFitPreference.saveGoogleFitStartTime(this.mSleepStartTime);
    }

    public /* synthetic */ void lambda$uploadSleepData$3$GoogleFitPresenter(Exception exc) {
        saveGoogleFitLog("uploadSleepData sleep There was a problem inserting the session: " + exc.getLocalizedMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHealthData(List<DataUpdateRequest> list) {
        saveGoogleFitLog("--------------更新健康数据到google fit------------");
        HistoryClient historyClient = Fitness.getHistoryClient(getContext(), this.account);
        final GoogleFitUpload googleFitUpload = this.mGoogleFitPreference.googleFitUpload();
        if (list == null || list.size() <= 0) {
            return;
        }
        for (DataUpdateRequest dataUpdateRequest : list) {
            final String name = dataUpdateRequest.getDataSet().getDataType().getName();
            historyClient.updateData(dataUpdateRequest).addOnSuccessListener(new OnSuccessListener() { // from class: com.ido.life.module.user.set.data.googlefit.-$$Lambda$GoogleFitPresenter$auEDPEOA_7YaYLgfsU9OTINDHUc
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    this.f$0.lambda$updateHealthData$4$GoogleFitPresenter(name, googleFitUpload, (Void) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.ido.life.module.user.set.data.googlefit.GoogleFitPresenter.3
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    if (name.contains("step_count")) {
                        GoogleFitPresenter.this.saveGoogleFitLog("上传的google fit的step_count失败");
                        return;
                    }
                    if (name.contains("distance")) {
                        GoogleFitPresenter.this.saveGoogleFitLog("上传的google fit的distance失败");
                        return;
                    }
                    if (name.contains(Field.NUTRIENT_CALORIES)) {
                        GoogleFitPresenter.this.saveGoogleFitLog("上传的google fit的calories失败");
                    } else if (name.contains("height")) {
                        GoogleFitPresenter.this.saveGoogleFitLog("上传的google fit的height失败");
                    } else if (name.contains("weight")) {
                        GoogleFitPresenter.this.saveGoogleFitLog("上传的google weight失败");
                    }
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateHealthData$4$GoogleFitPresenter(String str, GoogleFitUpload googleFitUpload, Void r5) {
        if (str.contains("step_count")) {
            saveGoogleFitLog("上传步数成功=" + this.mDeltaStep);
            this.mGoogleFitPreference.saveGoogleFitTime(System.currentTimeMillis());
            this.mGoogleFitPreference.saveGoogleFitStep(googleFitUpload.getLastStepCount() + this.mDeltaStep);
            return;
        }
        if (str.contains("distance")) {
            saveGoogleFitLog("上传距离成功=" + this.mDeltaDistance);
            this.mGoogleFitPreference.saveGoogleFitTime(System.currentTimeMillis());
            this.mGoogleFitPreference.saveGoogleFitDistance(googleFitUpload.getLastDistance() + this.mDeltaDistance);
            return;
        }
        if (str.contains(Field.NUTRIENT_CALORIES)) {
            saveGoogleFitLog("上传卡路里成功=" + this.mDeltaCalorie);
            this.mGoogleFitPreference.saveGoogleFitTime(System.currentTimeMillis());
            this.mGoogleFitPreference.saveGoogleFitCal(googleFitUpload.getLastCal() + this.mDeltaCalorie);
            return;
        }
        if (str.contains("height")) {
            saveGoogleFitLog("上传身高成功=" + this.mHeight);
            this.mGoogleFitPreference.saveGoogleFitTime(System.currentTimeMillis());
            this.mGoogleFitPreference.saveGoogleFitHeight(this.mHeight);
            return;
        }
        if (str.contains("weight")) {
            saveGoogleFitLog("上传体重成功=" + this.mWeight);
            this.mGoogleFitPreference.saveGoogleFitTime(System.currentTimeMillis());
            this.mGoogleFitPreference.saveGoogleFitWeight(this.mWeight);
        }
    }

    private List<DataUpdateRequest> createDataSet() {
        DataSet dataSetCreateDataForRequest;
        ArrayList arrayList = new ArrayList();
        long time = DateUtil.getTodayDate().getTime();
        long lastStartTime = this.mGoogleFitPreference.getLastStartTime();
        if (lastStartTime < TimeUtil.zero()) {
            lastStartTime = TimeUtil.zero() + 1;
            this.mGoogleFitPreference.resetGoogleFitUpload();
        }
        long j = lastStartTime;
        GoogleFitUpload googleFitUpload = this.mGoogleFitPreference.googleFitUpload();
        saveGoogleFitLog(googleFitUpload.toString());
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mDeltaStep = getDeltaStep(googleFitUpload);
        if (this.mDeltaStep > 0) {
            saveGoogleFitLog("createDataSet mDeltaStep = " + this.mDeltaStep);
            DataSet dataSetCreateDataForRequest2 = createDataForRequest(DataType.TYPE_STEP_COUNT_DELTA, Field.FIELD_STEPS, Integer.valueOf(this.mDeltaStep), j, time, timeUnit);
            if (dataSetCreateDataForRequest2 != null) {
                arrayList.add(new DataUpdateRequest(j, time, dataSetCreateDataForRequest2, null));
            }
        }
        this.mDeltaDistance = getDeltaDistance(googleFitUpload);
        saveGoogleFitLog("createDataSet mDeltaDistance = " + this.mDeltaDistance);
        if (this.mDeltaDistance > 0.0f && (dataSetCreateDataForRequest = createDataForRequest(DataType.TYPE_DISTANCE_DELTA, Field.FIELD_DISTANCE, Float.valueOf(this.mDeltaDistance), j, time, timeUnit)) != null) {
            arrayList.add(new DataUpdateRequest(j, time, dataSetCreateDataForRequest, null));
        }
        this.mDeltaCalorie = getDeltaCalorie(googleFitUpload);
        if (this.mDeltaCalorie > 0.0f) {
            saveGoogleFitLog("createDataSet mDeltaCalorie = " + this.mDeltaCalorie);
            DataSet dataSetCreateDataForRequest3 = createDataForRequest(DataType.TYPE_CALORIES_EXPENDED, Field.FIELD_CALORIES, Float.valueOf(this.mDeltaCalorie), j, time, timeUnit);
            if (dataSetCreateDataForRequest3 != null) {
                arrayList.add(new DataUpdateRequest(j, time, dataSetCreateDataForRequest3, null));
            }
        }
        float avgHeartRate = getAvgHeartRate();
        if (avgHeartRate > 0.0f) {
            saveGoogleFitLog("createDataSet avgHeartRate = " + avgHeartRate);
            DataSet dataSetCreateDataForRequest4 = createDataForRequest(DataType.TYPE_HEART_RATE_BPM, Field.FIELD_BPM, Float.valueOf(avgHeartRate), j, time, timeUnit);
            if (dataSetCreateDataForRequest4 != null) {
                arrayList.add(new DataUpdateRequest(j, time, dataSetCreateDataForRequest4, null));
            }
        }
        boolean zIsToday = DateUtil.isToday(DateUtil.formatTimeMills2Date(googleFitUpload.getLastSysTime()));
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo != null) {
            this.mHeight = userInfo.getHeight();
        }
        if (this.mHeight > 0.0f && (!zIsToday || googleFitUpload.getHeight() != this.mHeight)) {
            saveGoogleFitLog("createDataSet mHeight = " + this.mHeight);
            DataSet dataSetCreateDataForRequest5 = createDataForRequest(DataType.TYPE_HEIGHT, Field.FIELD_HEIGHT, Float.valueOf(this.mHeight / 100.0f), j, time, timeUnit);
            if (dataSetCreateDataForRequest5 != null) {
                arrayList.add(new DataUpdateRequest(j, time, dataSetCreateDataForRequest5, null));
            }
        }
        if (userInfo != null) {
            this.mWeight = userInfo.getWeight();
        }
        if (this.mWeight > 0.0f && (!zIsToday || googleFitUpload.getWeight() != this.mWeight)) {
            saveGoogleFitLog("createDataSet mWeight = " + this.mWeight);
            DataSet dataSetCreateDataForRequest6 = createDataForRequest(DataType.TYPE_WEIGHT, Field.FIELD_WEIGHT, Float.valueOf(this.mWeight), j, time, timeUnit);
            if (dataSetCreateDataForRequest6 != null) {
                arrayList.add(new DataUpdateRequest(j, time, dataSetCreateDataForRequest6, null));
            }
        }
        createSleepDataRequest(googleFitUpload);
        return arrayList;
    }

    private void createSleepDataRequest(GoogleFitUpload googleFitUpload) {
        this.mSleepRequest = null;
        ServerSleepDayData sleepDailyDataByDate = LocalHealthDataManager.getInstance().getSleepDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        if (sleepDailyDataByDate == null) {
            saveGoogleFitLog("无睡眠数据上传");
            return;
        }
        this.mSleepStartTime = DateUtil.getLongFromDateStr(sleepDailyDataByDate.getStartTime());
        long longFromDateStr = DateUtil.getLongFromDateStr(sleepDailyDataByDate.getEndTime());
        long j = this.mSleepStartTime;
        if (j <= 0 || longFromDateStr <= 0 || longFromDateStr < j) {
            saveGoogleFitLog("睡眠数据异常，不上传");
            return;
        }
        if (this.mSleepStartTime == googleFitUpload.getSleepStartTime()) {
            saveGoogleFitLog("该睡眠数据已上传过，不需要重复上传");
        } else {
            this.mSleepRequest = new SessionInsertRequest.Builder().setSession(new Session.Builder().setName(FitnessActivities.SLEEP).setIdentifier("sleep_identifier").setDescription("sleep_description").setStartTime(this.mSleepStartTime, TimeUnit.MILLISECONDS).setEndTime(longFromDateStr, TimeUnit.MILLISECONDS).setActivity(FitnessActivities.SLEEP).build()).build();
        }
    }

    private int getAvgHeartRate() {
        ServerHeartRateDayData heartRateDailyDataByDate = LocalHealthDataManager.getInstance().getHeartRateDailyDataByDate(RunTimeUtil.getInstance().getUserId(), DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        if (heartRateDailyDataByDate == null) {
            return 0;
        }
        return heartRateDailyDataByDate.getAvgValue();
    }

    private float getDeltaCalorie(GoogleFitUpload googleFitUpload) {
        float lastCal = googleFitUpload.getLastCal();
        saveGoogleFitLog("上次上传的卡路里是:" + lastCal);
        StepDayData stepDailyDataByDate = LocalHealthDataManager.getInstance().getStepDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        StringBuilder sb = new StringBuilder();
        sb.append("本次卡路里增量是:");
        sb.append(stepDailyDataByDate == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : Integer.valueOf(stepDailyDataByDate.getCalories()));
        saveGoogleFitLog(sb.toString());
        float calories = stepDailyDataByDate == null ? 0.0f : stepDailyDataByDate.getCalories() - lastCal;
        if (calories > 0.0f) {
            return calories;
        }
        return 0.0f;
    }

    private int getDeltaStep(GoogleFitUpload googleFitUpload) {
        int lastStepCount = googleFitUpload.getLastStepCount();
        saveGoogleFitLog("上次上传的步数是:" + lastStepCount);
        StepDayData stepDailyDataByDate = LocalHealthDataManager.getInstance().getStepDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        StringBuilder sb = new StringBuilder();
        sb.append("目前步数总量是:");
        sb.append(stepDailyDataByDate == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : Integer.valueOf(stepDailyDataByDate.getNumSteps()));
        saveGoogleFitLog(sb.toString());
        int numSteps = stepDailyDataByDate == null ? 0 : stepDailyDataByDate.getNumSteps() - lastStepCount;
        saveGoogleFitLog("本次步数增量是:" + numSteps);
        if (numSteps > 0) {
            return numSteps;
        }
        return 0;
    }

    private float getDeltaDistance(GoogleFitUpload googleFitUpload) {
        float lastDistance = googleFitUpload.getLastDistance();
        saveGoogleFitLog("上次上传的的距离是:" + lastDistance);
        StepDayData stepDailyDataByDate = LocalHealthDataManager.getInstance().getStepDailyDataByDate(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()));
        StringBuilder sb = new StringBuilder();
        sb.append("本次距离增量是:");
        sb.append(stepDailyDataByDate == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : Integer.valueOf(stepDailyDataByDate.getDistances()));
        saveGoogleFitLog(sb.toString());
        float distances = stepDailyDataByDate == null ? 0.0f : stepDailyDataByDate.getDistances() - lastDistance;
        if (distances > 0.0f) {
            return distances;
        }
        return 0.0f;
    }

    private DataSet createDataForRequest(DataType dataType, Field field, Object obj, long j, long j2, TimeUnit timeUnit) {
        long j3 = j >= j2 ? j2 - DateUtil.MINUTE : j;
        try {
            DataSet dataSetCreate = DataSet.create(new DataSource.Builder().setAppPackageName(IdoApp.getAppContext()).setDataType(dataType).setStreamName("Verfit - health data").setType(0).build());
            DataPoint timeInterval = dataSetCreate.createDataPoint().setTimeInterval(j3, j2, timeUnit);
            saveGoogleFitLog("时间戳开始时间:" + this.simpleDateFormat.format(new Date(j3)));
            saveGoogleFitLog("时间戳结束时间:" + this.simpleDateFormat.format(new Date(j2)));
            saveGoogleFitLog("dataType = " + dataType + "；数据为values = " + obj);
            if (dataType == DataType.TYPE_WEIGHT || dataType == DataType.TYPE_HEIGHT) {
                timeInterval.getValue(field).setFloat(((Float) obj).floatValue());
            } else if (obj instanceof Integer) {
                timeInterval.setIntValues(((Integer) obj).intValue());
            } else {
                timeInterval.setFloatValues(((Float) obj).floatValue());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("dataPoint = ");
            sb.append(timeInterval == null ? "null" : timeInterval.toString());
            saveGoogleFitLog(sb.toString());
            dataSetCreate.add(timeInterval);
            return dataSetCreate;
        } catch (Exception e2) {
            saveGoogleFitLog("createDataForRequest e :" + e2.getMessage());
            return null;
        }
    }

    private void signIn(Activity activity) {
        try {
            activity.startActivityForResult(GoogleSignIn.getClient(IdoApp.getAppContext(), new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()).getSignInIntent(), 3);
        } catch (Exception e2) {
            File file = new File(LogPathImpl.getInstance().getGoogleFitLogPath());
            if (!file.exists() || !file.isDirectory()) {
                file.mkdirs();
            }
            saveGoogleFitLog(e2.getMessage());
        }
    }

    public void saveGoogleFitLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), str);
    }

    private Context getContext() {
        return IdoApp.getAppContext();
    }

    public void handleSignInResult(int i, Intent intent, Activity activity) {
        saveGoogleFitLog("handleSignInResult: " + i);
        if (i == 3 || i == 10) {
            try {
                GoogleSignInResult signInResultFromIntent = zzi.getSignInResultFromIntent(intent);
                if (signInResultFromIntent != null && signInResultFromIntent.isSuccess()) {
                    this.account = GoogleSignIn.getSignedInAccountFromIntent(intent).getResult(ApiException.class);
                    subscriber(activity);
                    return;
                }
                saveGoogleFitLog("signInResult:failed ");
                if (signInResultFromIntent != null) {
                    saveGoogleFitLog("signInResult:failed " + signInResultFromIntent.getStatus().toString());
                }
            } catch (ApiException e2) {
                saveGoogleFitLog("signInResult:failed code=" + e2.getStatusCode());
            }
        }
    }
}