package com.ido.life.database.model;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.ido.life.util.DateUtil;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class StepDayData implements Comparable<StepDayData>, Cloneable {

    @Expose(deserialize = false)
    private transient int DayAvgStep;
    private int calories;
    private transient DaoSession daoSession;
    private String date;
    private String deviceName;
    private int distances;
    private int effectiveSteps;
    private Long id;
    private String items;

    @Expose(deserialize = false)
    private boolean loadDetail;
    private int maxNumSteps;
    private transient StepDayDataDao myDao;
    private int numSteps;
    private String sourceMac;
    private int targetSteps;
    private int timeOfSeconds;
    private long timestamp;

    @Expose(deserialize = false, serialize = false)
    private boolean uploaded;

    @Expose(deserialize = false, serialize = false)
    private long userId;

    public StepDayData() {
        this.userId = -1L;
        this.loadDetail = true;
        this.timestamp = System.currentTimeMillis();
    }

    public StepDayData(Long l, long j, String str, int i, int i2, int i3, int i4, String str2, String str3, String str4, int i5, int i6, int i7, long j2, boolean z, boolean z2) {
        this.userId = -1L;
        this.loadDetail = true;
        this.id = l;
        this.userId = j;
        this.date = str;
        this.distances = i;
        this.numSteps = i2;
        this.calories = i3;
        this.timeOfSeconds = i4;
        this.items = str2;
        this.sourceMac = str3;
        this.deviceName = str4;
        this.effectiveSteps = i5;
        this.maxNumSteps = i6;
        this.targetSteps = i7;
        this.timestamp = j2;
        this.loadDetail = z;
        this.uploaded = z2;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public StepDayData m26clone() {
        StepDayData stepDayData = new StepDayData();
        stepDayData.setNumSteps(this.numSteps);
        stepDayData.setItems(this.items);
        stepDayData.setDate(this.date);
        stepDayData.setDeviceName(this.deviceName);
        stepDayData.setId(this.id);
        stepDayData.setSourceMac(this.sourceMac);
        stepDayData.setUserId(this.userId);
        stepDayData.setTargetSteps(this.targetSteps);
        stepDayData.setItems(this.items);
        stepDayData.setUploaded(this.uploaded);
        return stepDayData;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public int getDistances() {
        return this.distances;
    }

    public void setDistances(int i) {
        this.distances = i;
    }

    public int getNumSteps() {
        return this.numSteps;
    }

    public void setNumSteps(int i) {
        this.numSteps = i;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int i) {
        this.calories = i;
    }

    public int getTimeOfSeconds() {
        return this.timeOfSeconds;
    }

    public void setTimeOfSeconds(int i) {
        this.timeOfSeconds = i;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
    }

    public String getSourceMac() {
        return this.sourceMac;
    }

    public void setSourceMac(String str) {
        this.sourceMac = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public int getEffectiveSteps() {
        return this.effectiveSteps;
    }

    public void setEffectiveSteps(int i) {
        this.effectiveSteps = i;
    }

    public int getTargetSteps() {
        return this.targetSteps;
    }

    public void setTargetSteps(int i) {
        this.targetSteps = i;
    }

    public int getMaxNumSteps() {
        return this.maxNumSteps;
    }

    public void setMaxNumSteps(int i) {
        this.maxNumSteps = i;
    }

    public boolean getUploaded() {
        return this.uploaded;
    }

    public void setUploaded(boolean z) {
        this.uploaded = z;
    }

    public boolean isUploaded() {
        return this.uploaded;
    }

    public long getTimeStamp() {
        return this.timestamp;
    }

    public void setTimeStamp(long j) {
        this.timestamp = j;
    }

    public int getDayAvgStep() {
        return this.DayAvgStep;
    }

    public void setDayAvgStep(int i) {
        this.DayAvgStep = i;
    }

    public void setLoadDetail(boolean z) {
        this.loadDetail = z;
    }

    public boolean getLoadDetail() {
        return this.loadDetail;
    }

    public String toString() {
        return "StepDayData{id=" + this.id + ", userId=" + this.userId + ", date='" + this.date + "', distances=" + this.distances + ", numSteps=" + this.numSteps + ", calories=" + this.calories + ", timeOfSeconds=" + this.timeOfSeconds + ", items='" + this.items + "', sourceMac='" + this.sourceMac + "', deviceName='" + this.deviceName + "', effectiveSteps=" + this.effectiveSteps + ", maxNumSteps=" + this.maxNumSteps + ", targetSteps=" + this.targetSteps + ", timestamp=" + this.timestamp + ", loadDetail=" + this.loadDetail + ", DayAvgStep=" + this.DayAvgStep + ", uploaded=" + this.uploaded + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(StepDayData stepDayData) {
        if (stepDayData != null && !TextUtils.isEmpty(this.date) && !TextUtils.isEmpty(stepDayData.date)) {
            try {
                return DateUtil.string2Date(this.date, DateUtil.DATE_FORMAT_YMD).compareTo(DateUtil.string2Date(stepDayData.date, DateUtil.DATE_FORMAT_YMD));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    public void delete() {
        StepDayDataDao stepDayDataDao = this.myDao;
        if (stepDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        stepDayDataDao.delete(this);
    }

    public void refresh() {
        StepDayDataDao stepDayDataDao = this.myDao;
        if (stepDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        stepDayDataDao.refresh(this);
    }

    public void update() {
        StepDayDataDao stepDayDataDao = this.myDao;
        if (stepDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        stepDayDataDao.update(this);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getStepDayDataDao() : null;
    }
}