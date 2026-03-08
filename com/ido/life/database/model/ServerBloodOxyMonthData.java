package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerBloodOxyMonthData {
    private int avgValue;
    private transient DaoSession daoSession;
    private int days;
    private Long id;
    private int maxValue;
    private int minValue;
    private String month;
    private transient ServerBloodOxyMonthDataDao myDao;
    private int totalMeasurementTimes;
    private long userId;

    public ServerBloodOxyMonthData(Long l, long j, int i, int i2, int i3, int i4, String str, int i5) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.minValue = i;
        this.maxValue = i2;
        this.avgValue = i3;
        this.totalMeasurementTimes = i4;
        this.month = str;
        this.days = i5;
    }

    public ServerBloodOxyMonthData() {
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

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int i) {
        this.days = i;
    }

    public String toString() {
        return "ServerBloodOxyMonthData{id=" + this.id + ", userId=" + this.userId + ", minValue=" + this.minValue + ", maxValue=" + this.maxValue + ", avgValue=" + this.avgValue + ", totalMeasurementTimes=" + this.totalMeasurementTimes + ", month='" + this.month + "', days=" + this.days + '}';
    }

    public void delete() {
        ServerBloodOxyMonthDataDao serverBloodOxyMonthDataDao = this.myDao;
        if (serverBloodOxyMonthDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverBloodOxyMonthDataDao.delete(this);
    }

    public void refresh() {
        ServerBloodOxyMonthDataDao serverBloodOxyMonthDataDao = this.myDao;
        if (serverBloodOxyMonthDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverBloodOxyMonthDataDao.refresh(this);
    }

    public void update() {
        ServerBloodOxyMonthDataDao serverBloodOxyMonthDataDao = this.myDao;
        if (serverBloodOxyMonthDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverBloodOxyMonthDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerBloodOxyMonthDataDao() : null;
    }
}