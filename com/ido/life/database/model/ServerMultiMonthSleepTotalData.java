package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiMonthSleepTotalData {
    private int avgAwakeSeconds;
    private int avgDeeplySeconds;
    private int avgEyeMovementSeconds;
    private int avgLightlySeconds;
    private int avgTotalSeconds;
    private int awakeRatio;
    private transient DaoSession daoSession;
    private int deeplyRatio;
    private String endDate;
    private int eyeMovementRatio;
    private Long id;
    private int lightlyRatio;
    private transient ServerMultiMonthSleepTotalDataDao myDao;
    private String startDate;
    private long userId;

    public ServerMultiMonthSleepTotalData(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str, String str2) {
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
        this.startDate = str;
        this.endDate = str2;
    }

    public ServerMultiMonthSleepTotalData() {
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

    public String toString() {
        return "ServerMultiMonthSleepTotalData{id=" + this.id + ", userId=" + this.userId + ", avgAwakeSeconds=" + this.avgAwakeSeconds + ", avgLightlySeconds=" + this.avgLightlySeconds + ", avgDeeplySeconds=" + this.avgDeeplySeconds + ", avgEyeMovementSeconds=" + this.avgEyeMovementSeconds + ", avgTotalSeconds=" + this.avgTotalSeconds + ", awakeRatio=" + this.awakeRatio + ", lightlyRatio=" + this.lightlyRatio + ", deeplyRatio=" + this.deeplyRatio + ", eyeMovementRatio=" + this.eyeMovementRatio + ", startDate='" + this.startDate + "', endDate='" + this.endDate + "'}";
    }

    public void delete() {
        ServerMultiMonthSleepTotalDataDao serverMultiMonthSleepTotalDataDao = this.myDao;
        if (serverMultiMonthSleepTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverMultiMonthSleepTotalDataDao.delete(this);
    }

    public void refresh() {
        ServerMultiMonthSleepTotalDataDao serverMultiMonthSleepTotalDataDao = this.myDao;
        if (serverMultiMonthSleepTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverMultiMonthSleepTotalDataDao.refresh(this);
    }

    public void update() {
        ServerMultiMonthSleepTotalDataDao serverMultiMonthSleepTotalDataDao = this.myDao;
        if (serverMultiMonthSleepTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverMultiMonthSleepTotalDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerMultiMonthSleepTotalDataDao() : null;
    }
}