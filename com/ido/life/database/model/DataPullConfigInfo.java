package com.ido.life.database.model;

import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class DataPullConfigInfo {
    private boolean AutoDownload;
    private long BloodEndTime;
    private int BloodOxyCount;
    private long BloodStartTime;
    private int CalorieCount;
    private long CalorieEndTime;
    private long CalorieStartTime;
    private int DataTotalCount;
    private int DistanceCount;
    private long DistanceEndTime;
    private long DistanceStartTime;
    private int ExerciseCount;
    private long ExerciseEndTime;
    private long ExerciseStartTime;
    private AtomicInteger HasDownloadCount;
    private int HeartRateCount;
    private long HeartRateEndTime;
    private long HeartRateStartTime;
    private Long Id;
    private int MensesCount;
    private long MensesEndTime;
    private long MensesStartTime;
    private int NoiseCount;
    private long NoiseEndTime;
    private long NoiseStartTime;
    private int PressureCount;
    private long PressureEndTime;
    private long PressureStartTime;
    private long RateNoticeCount;
    private long RateNoticeEndTime;
    private long RateNoticeStartTime;
    private boolean ShowState;
    private boolean ShowTipDialog;
    private int SleepCount;
    private long SleepEndTime;
    private long SleepStartTime;
    private int SportCount;
    private long SportEndTime;
    private long SportStartTime;
    private int StepCount;
    private long StepEndTime;
    private long StepStartTime;
    private int TargetCount;
    private long TargetEndTime;
    private long TargetStartTime;
    private long TemperatureCout;
    private long TemperatureEndTime;
    private long TemperatureStartTime;
    private long UserId;
    private int WalkCount;
    private long WalkEndTime;
    private long WalkStartTime;
    private int WeightCount;
    private long WeightEndTime;
    private long WeightStartTime;
    private transient DaoSession daoSession;
    private transient DataPullConfigInfoDao myDao;

    public DataPullConfigInfo() {
        this.AutoDownload = false;
        this.HasDownloadCount = new AtomicInteger(0);
        this.DataTotalCount = 0;
        this.BloodOxyCount = 0;
        this.CalorieCount = 0;
        this.DistanceCount = 0;
        this.ExerciseCount = 0;
        this.MensesCount = 0;
        this.HeartRateCount = 0;
        this.PressureCount = 0;
        this.SleepCount = 0;
        this.SportCount = 0;
        this.StepCount = 0;
        this.WalkCount = 0;
        this.WeightCount = 0;
        this.TargetCount = 0;
        this.TargetStartTime = 0L;
        this.TargetEndTime = 0L;
        this.NoiseCount = 0;
        this.TemperatureCout = 0L;
        this.TemperatureStartTime = 0L;
        this.TemperatureEndTime = 0L;
        this.RateNoticeCount = 0L;
        this.RateNoticeStartTime = 0L;
        this.RateNoticeEndTime = 0L;
        this.ShowTipDialog = true;
        this.ShowState = true;
    }

    public DataPullConfigInfo(Long l, long j, boolean z, AtomicInteger atomicInteger, int i, int i2, long j2, long j3, int i3, long j4, long j5, int i4, long j6, long j7, int i5, long j8, long j9, int i6, long j10, long j11, int i7, long j12, long j13, int i8, long j14, long j15, int i9, long j16, long j17, int i10, long j18, long j19, int i11, long j20, long j21, int i12, long j22, long j23, int i13, long j24, long j25, int i14, long j26, long j27, int i15, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, boolean z2, boolean z3) {
        this.AutoDownload = false;
        this.HasDownloadCount = new AtomicInteger(0);
        this.DataTotalCount = 0;
        this.BloodOxyCount = 0;
        this.CalorieCount = 0;
        this.DistanceCount = 0;
        this.ExerciseCount = 0;
        this.MensesCount = 0;
        this.HeartRateCount = 0;
        this.PressureCount = 0;
        this.SleepCount = 0;
        this.SportCount = 0;
        this.StepCount = 0;
        this.WalkCount = 0;
        this.WeightCount = 0;
        this.TargetCount = 0;
        this.TargetStartTime = 0L;
        this.TargetEndTime = 0L;
        this.NoiseCount = 0;
        this.TemperatureCout = 0L;
        this.TemperatureStartTime = 0L;
        this.TemperatureEndTime = 0L;
        this.RateNoticeCount = 0L;
        this.RateNoticeStartTime = 0L;
        this.RateNoticeEndTime = 0L;
        this.ShowTipDialog = true;
        this.ShowState = true;
        this.Id = l;
        this.UserId = j;
        this.AutoDownload = z;
        this.HasDownloadCount = atomicInteger;
        this.DataTotalCount = i;
        this.BloodOxyCount = i2;
        this.BloodStartTime = j2;
        this.BloodEndTime = j3;
        this.CalorieCount = i3;
        this.CalorieStartTime = j4;
        this.CalorieEndTime = j5;
        this.DistanceCount = i4;
        this.DistanceStartTime = j6;
        this.DistanceEndTime = j7;
        this.ExerciseCount = i5;
        this.ExerciseStartTime = j8;
        this.ExerciseEndTime = j9;
        this.MensesCount = i6;
        this.MensesStartTime = j10;
        this.MensesEndTime = j11;
        this.HeartRateCount = i7;
        this.HeartRateStartTime = j12;
        this.HeartRateEndTime = j13;
        this.PressureCount = i8;
        this.PressureStartTime = j14;
        this.PressureEndTime = j15;
        this.SleepCount = i9;
        this.SleepStartTime = j16;
        this.SleepEndTime = j17;
        this.SportCount = i10;
        this.SportStartTime = j18;
        this.SportEndTime = j19;
        this.StepCount = i11;
        this.StepStartTime = j20;
        this.StepEndTime = j21;
        this.WalkCount = i12;
        this.WalkStartTime = j22;
        this.WalkEndTime = j23;
        this.WeightCount = i13;
        this.WeightStartTime = j24;
        this.WeightEndTime = j25;
        this.TargetCount = i14;
        this.TargetStartTime = j26;
        this.TargetEndTime = j27;
        this.NoiseCount = i15;
        this.NoiseStartTime = j28;
        this.NoiseEndTime = j29;
        this.TemperatureCout = j30;
        this.TemperatureStartTime = j31;
        this.TemperatureEndTime = j32;
        this.RateNoticeCount = j33;
        this.RateNoticeStartTime = j34;
        this.RateNoticeEndTime = j35;
        this.ShowTipDialog = z2;
        this.ShowState = z3;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long l) {
        this.Id = l;
    }

    public long getUserId() {
        return this.UserId;
    }

    public void setUserId(long j) {
        this.UserId = j;
    }

    public boolean getAutoDownload() {
        return this.AutoDownload;
    }

    public void setAutoDownload(boolean z) {
        this.AutoDownload = z;
    }

    public AtomicInteger getHasDownloadCount() {
        return this.HasDownloadCount;
    }

    public void setHasDownloadCount(int i) {
        this.HasDownloadCount.set(i);
    }

    public int getDataTotalCount() {
        return this.DataTotalCount;
    }

    public void setDataTotalCount(int i) {
        this.DataTotalCount = i;
    }

    public int getBloodOxyCount() {
        return this.BloodOxyCount;
    }

    public void setBloodOxyCount(int i) {
        this.BloodOxyCount = i;
    }

    public int getCalorieCount() {
        return this.CalorieCount;
    }

    public void setCalorieCount(int i) {
        this.CalorieCount = i;
    }

    public int getDistanceCount() {
        return this.DistanceCount;
    }

    public void setDistanceCount(int i) {
        this.DistanceCount = i;
    }

    public int getExerciseCount() {
        return this.ExerciseCount;
    }

    public void setExerciseCount(int i) {
        this.ExerciseCount = i;
    }

    public int getMensesCount() {
        return this.MensesCount;
    }

    public void setMensesCount(int i) {
        this.MensesCount = i;
    }

    public int getHeartRateCount() {
        return this.HeartRateCount;
    }

    public void setHeartRateCount(int i) {
        this.HeartRateCount = i;
    }

    public int getPressureCount() {
        return this.PressureCount;
    }

    public void setPressureCount(int i) {
        this.PressureCount = i;
    }

    public int getSleepCount() {
        return this.SleepCount;
    }

    public void setSleepCount(int i) {
        this.SleepCount = i;
    }

    public int getSportCount() {
        return this.SportCount;
    }

    public void setSportCount(int i) {
        this.SportCount = i;
    }

    public int getStepCount() {
        return this.StepCount;
    }

    public void setStepCount(int i) {
        this.StepCount = i;
    }

    public int getWalkCount() {
        return this.WalkCount;
    }

    public void setWalkCount(int i) {
        this.WalkCount = i;
    }

    public int getWeightCount() {
        return this.WeightCount;
    }

    public void setWeightCount(int i) {
        this.WeightCount = i;
    }

    public boolean isShowTipDialog() {
        return this.ShowTipDialog;
    }

    public void setShowTipDialog(boolean z) {
        this.ShowTipDialog = z;
    }

    public boolean getShowTipDialog() {
        return this.ShowTipDialog;
    }

    public long getBloodStartTime() {
        return this.BloodStartTime;
    }

    public void setBloodStartTime(long j) {
        this.BloodStartTime = j;
    }

    public long getBloodEndTime() {
        return this.BloodEndTime;
    }

    public void setBloodEndTime(long j) {
        this.BloodEndTime = j;
    }

    public long getCalorieStartTime() {
        return this.CalorieStartTime;
    }

    public void setCalorieStartTime(long j) {
        this.CalorieStartTime = j;
    }

    public long getCalorieEndTime() {
        return this.CalorieEndTime;
    }

    public void setCalorieEndTime(long j) {
        this.CalorieEndTime = j;
    }

    public long getDistanceStartTime() {
        return this.DistanceStartTime;
    }

    public void setDistanceStartTime(long j) {
        this.DistanceStartTime = j;
    }

    public long getDistanceEndTime() {
        return this.DistanceEndTime;
    }

    public void setDistanceEndTime(long j) {
        this.DistanceEndTime = j;
    }

    public long getExerciseStartTime() {
        return this.ExerciseStartTime;
    }

    public void setExerciseStartTime(long j) {
        this.ExerciseStartTime = j;
    }

    public long getExerciseEndTime() {
        return this.ExerciseEndTime;
    }

    public void setExerciseEndTime(long j) {
        this.ExerciseEndTime = j;
    }

    public long getMensesStartTime() {
        return this.MensesStartTime;
    }

    public void setMensesStartTime(long j) {
        this.MensesStartTime = j;
    }

    public long getMensesEndTime() {
        return this.MensesEndTime;
    }

    public void setMensesEndTime(long j) {
        this.MensesEndTime = j;
    }

    public long getHeartRateStartTime() {
        return this.HeartRateStartTime;
    }

    public void setHeartRateStartTime(long j) {
        this.HeartRateStartTime = j;
    }

    public long getHeartRateEndTime() {
        return this.HeartRateEndTime;
    }

    public void setHeartRateEndTime(long j) {
        this.HeartRateEndTime = j;
    }

    public long getPressureStartTime() {
        return this.PressureStartTime;
    }

    public void setPressureStartTime(long j) {
        this.PressureStartTime = j;
    }

    public long getPressureEndTime() {
        return this.PressureEndTime;
    }

    public void setPressureEndTime(long j) {
        this.PressureEndTime = j;
    }

    public long getSleepStartTime() {
        return this.SleepStartTime;
    }

    public void setSleepStartTime(long j) {
        this.SleepStartTime = j;
    }

    public long getSleepEndTime() {
        return this.SleepEndTime;
    }

    public void setSleepEndTime(long j) {
        this.SleepEndTime = j;
    }

    public long getSportStartTime() {
        return this.SportStartTime;
    }

    public void setSportStartTime(long j) {
        this.SportStartTime = j;
    }

    public long getSportEndTime() {
        return this.SportEndTime;
    }

    public void setSportEndTime(long j) {
        this.SportEndTime = j;
    }

    public long getStepStartTime() {
        return this.StepStartTime;
    }

    public void setStepStartTime(long j) {
        this.StepStartTime = j;
    }

    public long getStepEndTime() {
        return this.StepEndTime;
    }

    public void setStepEndTime(long j) {
        this.StepEndTime = j;
    }

    public long getWalkStartTime() {
        return this.WalkStartTime;
    }

    public void setWalkStartTime(long j) {
        this.WalkStartTime = j;
    }

    public long getWalkEndTime() {
        return this.WalkEndTime;
    }

    public void setWalkEndTime(long j) {
        this.WalkEndTime = j;
    }

    public long getWeightStartTime() {
        return this.WeightStartTime;
    }

    public void setWeightStartTime(long j) {
        this.WeightStartTime = j;
    }

    public long getWeightEndTime() {
        return this.WeightEndTime;
    }

    public void setWeightEndTime(long j) {
        this.WeightEndTime = j;
    }

    public boolean isAutoDownload() {
        return this.AutoDownload;
    }

    public void setShowState(boolean z) {
        this.ShowState = z;
    }

    public boolean isShowState() {
        return this.ShowState;
    }

    public void setHasDownloadCount(AtomicInteger atomicInteger) {
        this.HasDownloadCount = atomicInteger;
    }

    public boolean getShowState() {
        return this.ShowState;
    }

    public int getTargetCount() {
        return this.TargetCount;
    }

    public void setTargetCount(int i) {
        this.TargetCount = i;
    }

    public long getTargetStartTime() {
        return this.TargetStartTime;
    }

    public void setTargetStartTime(long j) {
        this.TargetStartTime = j;
    }

    public long getTargetEndTime() {
        return this.TargetEndTime;
    }

    public void setTargetEndTime(long j) {
        this.TargetEndTime = j;
    }

    public int getNoiseCount() {
        return this.NoiseCount;
    }

    public void setNoiseCount(int i) {
        this.NoiseCount = i;
    }

    public long getNoiseStartTime() {
        return this.NoiseStartTime;
    }

    public void setNoiseStartTime(long j) {
        this.NoiseStartTime = j;
    }

    public long getNoiseEndTime() {
        return this.NoiseEndTime;
    }

    public void setNoiseEndTime(long j) {
        this.NoiseEndTime = j;
    }

    public void delete() {
        DataPullConfigInfoDao dataPullConfigInfoDao = this.myDao;
        if (dataPullConfigInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        dataPullConfigInfoDao.delete(this);
    }

    public void refresh() {
        DataPullConfigInfoDao dataPullConfigInfoDao = this.myDao;
        if (dataPullConfigInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        dataPullConfigInfoDao.refresh(this);
    }

    public void update() {
        DataPullConfigInfoDao dataPullConfigInfoDao = this.myDao;
        if (dataPullConfigInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        dataPullConfigInfoDao.update(this);
    }

    public long getTemperatureCout() {
        return this.TemperatureCout;
    }

    public void setTemperatureCout(long j) {
        this.TemperatureCout = j;
    }

    public long getTemperatureStartTime() {
        return this.TemperatureStartTime;
    }

    public void setTemperatureStartTime(long j) {
        this.TemperatureStartTime = j;
    }

    public long getTemperatureEndTime() {
        return this.TemperatureEndTime;
    }

    public void setTemperatureEndTime(long j) {
        this.TemperatureEndTime = j;
    }

    public long getRateNoticeCount() {
        return this.RateNoticeCount;
    }

    public void setRateNoticeCount(long j) {
        this.RateNoticeCount = j;
    }

    public long getRateNoticeStartTime() {
        return this.RateNoticeStartTime;
    }

    public void setRateNoticeStartTime(long j) {
        this.RateNoticeStartTime = j;
    }

    public long getRateNoticeEndTime() {
        return this.RateNoticeEndTime;
    }

    public void setRateNoticeEndTime(long j) {
        this.RateNoticeEndTime = j;
    }

    public static DataPullConfigInfo generateDefaultPullConfigInfo(long j) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.add(1, -100);
        long timeInMillis2 = calendar.getTimeInMillis();
        DataPullConfigInfo dataPullConfigInfo = new DataPullConfigInfo();
        dataPullConfigInfo.setHasDownloadCount(0);
        dataPullConfigInfo.setDataTotalCount(0);
        dataPullConfigInfo.setBloodOxyCount(0);
        dataPullConfigInfo.setBloodStartTime(timeInMillis2);
        dataPullConfigInfo.setBloodEndTime(timeInMillis);
        dataPullConfigInfo.setCalorieCount(0);
        dataPullConfigInfo.setCalorieStartTime(timeInMillis2);
        dataPullConfigInfo.setCalorieEndTime(timeInMillis);
        dataPullConfigInfo.setDistanceCount(0);
        dataPullConfigInfo.setDistanceStartTime(timeInMillis2);
        dataPullConfigInfo.setDistanceEndTime(timeInMillis);
        dataPullConfigInfo.setExerciseCount(0);
        dataPullConfigInfo.setExerciseStartTime(timeInMillis2);
        dataPullConfigInfo.setExerciseEndTime(timeInMillis);
        dataPullConfigInfo.setMensesCount(0);
        dataPullConfigInfo.setMensesStartTime(timeInMillis2);
        dataPullConfigInfo.setMensesEndTime(timeInMillis);
        dataPullConfigInfo.setHeartRateCount(0);
        dataPullConfigInfo.setHeartRateStartTime(timeInMillis2);
        dataPullConfigInfo.setHeartRateEndTime(timeInMillis);
        dataPullConfigInfo.setPressureCount(0);
        dataPullConfigInfo.setPressureStartTime(timeInMillis2);
        dataPullConfigInfo.setPressureEndTime(timeInMillis);
        dataPullConfigInfo.setSleepCount(0);
        dataPullConfigInfo.setSleepStartTime(timeInMillis2);
        dataPullConfigInfo.setSleepEndTime(timeInMillis);
        dataPullConfigInfo.setSportCount(0);
        dataPullConfigInfo.setSportStartTime(timeInMillis2);
        dataPullConfigInfo.setSportEndTime(timeInMillis);
        dataPullConfigInfo.setStepCount(0);
        dataPullConfigInfo.setStepStartTime(timeInMillis2);
        dataPullConfigInfo.setStepEndTime(timeInMillis);
        dataPullConfigInfo.setWalkCount(0);
        dataPullConfigInfo.setWalkStartTime(timeInMillis2);
        dataPullConfigInfo.setWalkEndTime(timeInMillis);
        dataPullConfigInfo.setWeightCount(0);
        dataPullConfigInfo.setWeightStartTime(timeInMillis2);
        dataPullConfigInfo.setWeightEndTime(timeInMillis);
        dataPullConfigInfo.setTargetCount(0);
        dataPullConfigInfo.setTargetStartTime(timeInMillis2);
        dataPullConfigInfo.setTargetEndTime(timeInMillis);
        dataPullConfigInfo.setNoiseCount(0);
        dataPullConfigInfo.setNoiseStartTime(timeInMillis2);
        dataPullConfigInfo.setNoiseEndTime(timeInMillis);
        dataPullConfigInfo.setTemperatureCout(0L);
        dataPullConfigInfo.setTemperatureStartTime(timeInMillis2);
        dataPullConfigInfo.setTemperatureEndTime(timeInMillis);
        dataPullConfigInfo.setRateNoticeCount(0L);
        dataPullConfigInfo.setRateNoticeStartTime(timeInMillis2);
        dataPullConfigInfo.setRateNoticeEndTime(timeInMillis);
        dataPullConfigInfo.setShowTipDialog(false);
        dataPullConfigInfo.setShowState(false);
        dataPullConfigInfo.setUserId(j);
        dataPullConfigInfo.setAutoDownload(false);
        return dataPullConfigInfo;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getDataPullConfigInfoDao() : null;
    }
}