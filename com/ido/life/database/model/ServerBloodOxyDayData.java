package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerBloodOxyDayData {
    private int avgValue;
    private transient DaoSession daoSession;
    private String date;
    private Long id;
    private String items;
    private int latestValue;
    private int maxValue;
    private int measurementTimes;
    private int minValue;
    private transient ServerBloodOxyDayDataDao myDao;
    private String sourceMac;
    private long timestamp;

    @Expose(deserialize = false, serialize = false)
    private boolean uploaded;
    private long userId;

    public ServerBloodOxyDayData() {
        this.userId = -1L;
        this.timestamp = System.currentTimeMillis();
    }

    public ServerBloodOxyDayData(Long l, long j, String str, int i, int i2, int i3, int i4, int i5, String str2, String str3, long j2, boolean z) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.date = str;
        this.maxValue = i;
        this.minValue = i2;
        this.avgValue = i3;
        this.latestValue = i4;
        this.measurementTimes = i5;
        this.sourceMac = str2;
        this.items = str3;
        this.timestamp = j2;
        this.uploaded = z;
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

    public int getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(int i) {
        this.maxValue = i;
    }

    public int getMinValue() {
        return this.minValue;
    }

    public void setMinValue(int i) {
        this.minValue = i;
    }

    public int getAvgValue() {
        return this.avgValue;
    }

    public void setAvgValue(int i) {
        this.avgValue = i;
    }

    public int getMeasurementTimes() {
        return this.measurementTimes;
    }

    public void setMeasurementTimes(int i) {
        this.measurementTimes = i;
    }

    public String getSourceMac() {
        return this.sourceMac;
    }

    public void setSourceMac(String str) {
        this.sourceMac = str;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public boolean getUploaded() {
        return this.uploaded;
    }

    public void setUploaded(boolean z) {
        this.uploaded = z;
    }

    public int getLatestValue() {
        return this.latestValue;
    }

    public void setLatestValue(int i) {
        this.latestValue = i;
    }

    public boolean isUploaded() {
        return this.uploaded;
    }

    public String toString() {
        return "ServerBloodOxyDayData{id=" + this.id + ", userId=" + this.userId + ", date='" + this.date + "', maxValue=" + this.maxValue + ", minValue=" + this.minValue + ", avgValue=" + this.avgValue + ", measurementTimes=" + this.measurementTimes + ", sourceMac='" + this.sourceMac + "', items='" + this.items + "', timestamp=" + this.timestamp + ", uploaded=" + this.uploaded + '}';
    }

    public void delete() {
        ServerBloodOxyDayDataDao serverBloodOxyDayDataDao = this.myDao;
        if (serverBloodOxyDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverBloodOxyDayDataDao.delete(this);
    }

    public void refresh() {
        ServerBloodOxyDayDataDao serverBloodOxyDayDataDao = this.myDao;
        if (serverBloodOxyDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverBloodOxyDayDataDao.refresh(this);
    }

    public void update() {
        ServerBloodOxyDayDataDao serverBloodOxyDayDataDao = this.myDao;
        if (serverBloodOxyDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverBloodOxyDayDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerBloodOxyDayDataDao() : null;
    }
}