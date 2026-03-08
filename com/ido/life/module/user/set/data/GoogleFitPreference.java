package com.ido.life.module.user.set.data;

import android.content.Context;
import com.ido.common.IdoApp;
import com.ido.common.base.BasePreference;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;

/* JADX INFO: loaded from: classes3.dex */
public class GoogleFitPreference extends BasePreference {
    private static final String NAME = "google_fit_preference";
    private static final String TAG_LAST_CAL = "last_cal";
    private static final String TAG_LAST_HEIGHT = "last_height";
    private static final String TAG_LAST_SLEEP_START_TIME = "last_sleep_start_time";
    private static final String TAG_LAST_STEP_COUNT = "last_step_count";
    private static final String TAG_LAST_STEP_DISTANCE = "last_step_distance";
    private static final String TAG_LAST_SYS_TIME = "last_sys_time";
    private static final String TAG_LAST_WEIGHT = "last_weight";
    private static final String TAG_STATE = "State";
    private static volatile GoogleFitPreference instance;

    private GoogleFitPreference() {
    }

    private static Context getContext() {
        return IdoApp.getAppContext();
    }

    public static GoogleFitPreference getInstance() {
        if (instance == null) {
            synchronized (GoogleFitPreference.class) {
                if (instance == null) {
                    instance = new GoogleFitPreference();
                }
            }
        }
        return instance;
    }

    public GoogleFitUpload googleFitUpload() {
        GoogleFitUpload googleFitUpload = new GoogleFitUpload();
        googleFitUpload.setState(getBoolean(getContext(), NAME, TAG_STATE));
        googleFitUpload.setLastSysTime(getLong(getContext(), NAME, TAG_LAST_SYS_TIME, 0L));
        googleFitUpload.setLastStepCount(getInt(getContext(), NAME, TAG_LAST_STEP_COUNT, 0));
        googleFitUpload.setLastDistance(getFloat(getContext(), NAME, TAG_LAST_STEP_DISTANCE, 0.0f));
        googleFitUpload.setLastCal(getFloat(getContext(), NAME, TAG_LAST_CAL, 0.0f));
        googleFitUpload.setHeight(getFloat(getContext(), NAME, TAG_LAST_HEIGHT, 0.0f));
        googleFitUpload.setWeight(getFloat(getContext(), NAME, TAG_LAST_WEIGHT, 0.0f));
        googleFitUpload.setSleepStartTime(getLong(getContext(), NAME, TAG_LAST_SLEEP_START_TIME, 0L));
        return googleFitUpload;
    }

    public long getLastStartTime() {
        return getLong(getContext(), NAME, TAG_LAST_SYS_TIME, 0L);
    }

    public void resetGoogleFitUpload() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "重置步数，距离，卡路里数据为0：");
        saveInt(getContext(), NAME, TAG_LAST_STEP_COUNT, 0);
        saveFloat(getContext(), NAME, TAG_LAST_STEP_DISTANCE, 0.0f);
        saveFloat(getContext(), NAME, TAG_LAST_CAL, 0.0f);
    }

    public void saveGoogleFitTime(long j) {
        saveLong(getContext(), NAME, TAG_LAST_SYS_TIME, j);
    }

    public void saveGoogleFitStep(int i) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "已上传的累加步数是：" + i);
        saveInt(getContext(), NAME, TAG_LAST_STEP_COUNT, i);
    }

    public void saveGoogleFitDistance(float f2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "已上传的累加距离是：" + f2);
        saveFloat(getContext(), NAME, TAG_LAST_STEP_DISTANCE, f2);
    }

    public void saveGoogleFitCal(float f2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "已上传的累加卡路里是：" + f2);
        saveFloat(getContext(), NAME, TAG_LAST_CAL, f2);
    }

    public void saveGoogleFitHeight(float f2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "已上传的累加身高是：" + f2);
        saveFloat(getContext(), NAME, TAG_LAST_HEIGHT, f2);
    }

    public void saveGoogleFitWeight(float f2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "已上传的累加体重是：" + f2);
        saveFloat(getContext(), NAME, TAG_LAST_WEIGHT, f2);
    }

    public void saveGoogleFitStartTime(long j) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "已上传的累加开始时间是：" + j);
        saveLong(getContext(), NAME, TAG_LAST_SLEEP_START_TIME, j);
    }

    public static void clear() {
        clear(IdoApp.getAppContext(), NAME);
    }
}