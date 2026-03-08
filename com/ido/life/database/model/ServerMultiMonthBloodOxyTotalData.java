package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiMonthBloodOxyTotalData {
    private int avgValue;
    private transient DaoSession daoSession;
    private String endDate;
    private Long id;
    private int maxValue;
    private int minValue;
    private transient ServerMultiMonthBloodOxyTotalDataDao myDao;
    private String startDate;
    private int totalMeasurementTimes;
    private long userId;

    public ServerMultiMonthBloodOxyTotalData(Long l, long j, int i, int i2, int i3, int i4, String str, String str2) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.minValue = i;
        this.maxValue = i2;
        this.avgValue = i3;
        this.totalMeasurementTimes = i4;
        this.startDate = str;
        this.endDate = str2;
    }

    public ServerMultiMonthBloodOxyTotalData() {
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

    public int getMinValue() {
        return this.minValue;
    }

    public void setMinValue(int i) {
        this.minValue = i;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(int i) {
        this.maxValue = i;
    }

    public int getAvgValue() {
        return this.avgValue;
    }

    public void setAvgValue(int i) {
        this.avgValue = i;
    }

    public int getTotalMeasurementTimes() {
        return this.totalMeasurementTimes;
    }

    public void setTotalMeasurementTimes(int i) {
        this.totalMeasurementTimes = i;
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
        return "ServerMultiMonthBloodOxyTotalData{id=" + this.id + ", userId=" + this.userId + ", minValue=" + this.minValue + ", maxValue=" + this.maxValue + ", avgValue=" + this.avgValue + ", totalMeasurementTimes=" + this.totalMeasurementTimes + ", startDate='" + this.startDate + "', endDate='" + this.endDate + "'}";
    }

    public void delete() {
        ServerMultiMonthBloodOxyTotalDataDao serverMultiMonthBloodOxyTotalDataDao = this.myDao;
        if (serverMultiMonthBloodOxyTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverMultiMonthBloodOxyTotalDataDao.delete(this);
    }

    public void refresh() {
        ServerMultiMonthBloodOxyTotalDataDao serverMultiMonthBloodOxyTotalDataDao = this.myDao;
        if (serverMultiMonthBloodOxyTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverMultiMonthBloodOxyTotalDataDao.refresh(this);
    }

    public void update() {
        ServerMultiMonthBloodOxyTotalDataDao serverMultiMonthBloodOxyTotalDataDao = this.myDao;
        if (serverMultiMonthBloodOxyTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverMultiMonthBloodOxyTotalDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerMultiMonthBloodOxyTotalDataDao() : null;
    }
}