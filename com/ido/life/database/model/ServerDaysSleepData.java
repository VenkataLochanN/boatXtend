package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerDaysSleepData {
    private int avgAwakeSeconds;
    private int avgBreathRate;
    private int avgDeeplySeconds;
    private int avgEndTimeMinute;
    private int avgEyeMovementSeconds;
    private int avgLightlySeconds;
    private int avgScore;
    private int avgStartTimeMinute;
    private int avgTotalSeconds;
    private int awakeRatio;
    private transient DaoSession daoSession;
    private int deeplyRatio;
    private String endDate;
    private int eyeMovementRatio;
    private Long id;
    private int lightlyRatio;
    private transient ServerDaysSleepDataDao myDao;
    private String startDate;
    private long userId;

    public ServerDaysSleepData(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, String str, String str2) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.avgAwakeSeconds = i;
        this.avgLightlySeconds = i2;
        this.avgDeeplySeconds = i3;
        this.avgEyeMovementSeconds = i4;
        this.avgTotalSeconds = i5;
        this.awakeRatio = i6;
        this.lightlyRatio = i7;
        this.deeplyRatio = i8;
        this.eyeMovementRatio = i9;
        this.avgScore = i10;
        this.avgBreathRate = i11;
        this.avgStartTimeMinute = i12;
        this.avgEndTimeMinute = i13;
        this.startDate = str;
        this.endDate = str2;
    }

    public ServerDaysSleepData() {
        this.userId = -1L;
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

    public int getAvgAwakeSeconds() {
        return this.avgAwakeSeconds;
    }

    public void setAvgAwakeSeconds(int i) {
        this.avgAwakeSeconds = i;
    }

    public int getAvgLightlySeconds() {
        return this.avgLightlySeconds;
    }

    public void setAvgLightlySeconds(int i) {
        this.avgLightlySeconds = i;
    }

    public int getAvgDeeplySeconds() {
        return this.avgDeeplySeconds;
    }

    public void setAvgDeeplySeconds(int i) {
        this.avgDeeplySeconds = i;
    }

    public int getAvgEyeMovementSeconds() {
        return this.avgEyeMovementSeconds;
    }

    public void setAvgEyeMovementSeconds(int i) {
        this.avgEyeMovementSeconds = i;
    }

    public int getAvgTotalSeconds() {
        return this.avgTotalSeconds;
    }

    public void setAvgTotalSeconds(int i) {
        this.avgTotalSeconds = i;
    }

    public int getAwakeRatio() {
        return this.awakeRatio;
    }

    public void setAwakeRatio(int i) {
        this.awakeRatio = i;
    }

    public int getLightlyRatio() {
        return this.lightlyRatio;
    }

    public void setLightlyRatio(int i) {
        this.lightlyRatio = i;
    }

    public int getDeeplyRatio() {
        return this.deeplyRatio;
    }

    public void setDeeplyRatio(int i) {
        this.deeplyRatio = i;
    }

    public int getEyeMovementRatio() {
        return this.eyeMovementRatio;
    }

    public void setEyeMovementRatio(int i) {
        this.eyeMovementRatio = i;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public int getAvgScore() {
        return this.avgScore;
    }

    public void setAvgScore(int i) {
        this.avgScore = i;
    }

    public int getAvgBreathRate() {
        return this.avgBreathRate;
    }

    public void setAvgBreathRate(int i) {
        this.avgBreathRate = i;
    }

    public int getAvgStartTimeMinute() {
        return this.avgStartTimeMinute;
    }

    public void setAvgStartTimeMinute(int i) {
        this.avgStartTimeMinute = i;
    }

    public int getAvgEndTimeMinute() {
        return this.avgEndTimeMinute;
    }

    public void setAvgEndTimeMinute(int i) {
        this.avgEndTimeMinute = i;
    }

    public String toString() {
        return "ServerDaysSleepData{id=" + this.id + ", userId=" + this.userId + ", avgAwakeSeconds=" + this.avgAwakeSeconds + ", avgLightlySeconds=" + this.avgLightlySeconds + ", avgDeeplySeconds=" + this.avgDeeplySeconds + ", avgEyeMovementSeconds=" + this.avgEyeMovementSeconds + ", avgTotalSeconds=" + this.avgTotalSeconds + ", awakeRatio=" + this.awakeRatio + ", lightlyRatio=" + this.lightlyRatio + ", deeplyRatio=" + this.deeplyRatio + ", eyeMovementRatio=" + this.eyeMovementRatio + ", avgScore=" + this.avgScore + ", avgBreathRate=" + this.avgBreathRate + ", avgStartTimeMinute=" + this.avgStartTimeMinute + ", avgEndTimeMinute=" + this.avgEndTimeMinute + ", startDate='" + this.startDate + "', endDate='" + this.endDate + "'}";
    }

    public void delete() {
        ServerDaysSleepDataDao serverDaysSleepDataDao = this.myDao;
        if (serverDaysSleepDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverDaysSleepDataDao.delete(this);
    }

    public void refresh() {
        ServerDaysSleepDataDao serverDaysSleepDataDao = this.myDao;
        if (serverDaysSleepDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverDaysSleepDataDao.refresh(this);
    }

    public void update() {
        ServerDaysSleepDataDao serverDaysSleepDataDao = this.myDao;
        if (serverDaysSleepDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverDaysSleepDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerDaysSleepDataDao() : null;
    }
}