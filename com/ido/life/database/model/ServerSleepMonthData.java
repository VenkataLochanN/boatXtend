package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerSleepMonthData {
    private int avgAwakeSeconds;
    private int avgDeeplySeconds;
    private int avgEyeMovementSeconds;
    private int avgLightlySeconds;
    private int avgTotalSeconds;
    private int awakeRatio;
    private transient DaoSession daoSession;
    private int deeplyRatio;
    private int eyeMovementRatio;
    private Long id;
    private int lightlyRatio;
    private String month;
    private transient ServerSleepMonthDataDao myDao;
    private long userId;

    public ServerSleepMonthData(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str) {
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
        this.month = str;
    }

    public ServerSleepMonthData() {
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

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public String toString() {
        return "ServerSleepMonthData{id=" + this.id + ", userId=" + this.userId + ", avgAwakeSeconds=" + this.avgAwakeSeconds + ", avgLightlySeconds=" + this.avgLightlySeconds + ", avgDeeplySeconds=" + this.avgDeeplySeconds + ", avgEyeMovementSeconds=" + this.avgEyeMovementSeconds + ", avgTotalSeconds=" + this.avgTotalSeconds + ", awakeRatio=" + this.awakeRatio + ", lightlyRatio=" + this.lightlyRatio + ", deeplyRatio=" + this.deeplyRatio + ", eyeMovementRatio=" + this.eyeMovementRatio + ", month='" + this.month + "'}";
    }

    public void delete() {
        ServerSleepMonthDataDao serverSleepMonthDataDao = this.myDao;
        if (serverSleepMonthDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverSleepMonthDataDao.delete(this);
    }

    public void refresh() {
        ServerSleepMonthDataDao serverSleepMonthDataDao = this.myDao;
        if (serverSleepMonthDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverSleepMonthDataDao.refresh(this);
    }

    public void update() {
        ServerSleepMonthDataDao serverSleepMonthDataDao = this.myDao;
        if (serverSleepMonthDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverSleepMonthDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerSleepMonthDataDao() : null;
    }
}