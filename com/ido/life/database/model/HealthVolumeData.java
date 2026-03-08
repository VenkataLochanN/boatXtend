package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class HealthVolumeData {
    private int avgValue;
    private transient DaoSession daoSession;
    private String date;
    private String deviceName;

    @Expose(deserialize = false, serialize = false)
    private boolean hasUpdate;
    private int highLevelSeconds;

    @Expose(deserialize = false, serialize = false)
    private Long id;
    private int interval;
    private String items;
    private int latestValue;
    private int lowLevelSeconds;
    private int maxValue;
    private int minValue;
    private transient HealthVolumeDataDao myDao;
    private int normalLevelSeconds;
    private String sourceMac;
    private int superHighLevelSeconds;
    public long timestamp;
    private int totalSeconds;

    @Expose(deserialize = false, serialize = false)
    private long userId;

    public HealthVolumeData(Long l, long j, String str, long j2, int i, int i2, int i3, int i4, int i5, String str2, String str3, String str4, int i6, int i7, int i8, int i9, int i10, boolean z) {
        this.userId = -1L;
        this.id = l;
        this.timestamp = j;
        this.date = str;
        this.userId = j2;
        this.avgValue = i;
        this.maxValue = i2;
        this.minValue = i3;
        this.latestValue = i4;
        this.interval = i5;
        this.sourceMac = str2;
        this.deviceName = str3;
        this.items = str4;
        this.totalSeconds = i6;
        this.superHighLevelSeconds = i7;
        this.highLevelSeconds = i8;
        this.normalLevelSeconds = i9;
        this.lowLevelSeconds = i10;
        this.hasUpdate = z;
    }

    public HealthVolumeData() {
        this.userId = -1L;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public int getAvgValue() {
        return this.avgValue;
    }

    public void setAvgValue(int i) {
        this.avgValue = i;
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

    public int getLatestValue() {
        return this.latestValue;
    }

    public void setLatestValue(int i) {
        this.latestValue = i;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int i) {
        this.interval = i;
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

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
    }

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    public void setTotalSeconds(int i) {
        this.totalSeconds = i;
    }

    public int getSuperHighLevelSeconds() {
        return this.superHighLevelSeconds;
    }

    public void setSuperHighLevelSeconds(int i) {
        this.superHighLevelSeconds = i;
    }

    public int getHighLevelSeconds() {
        return this.highLevelSeconds;
    }

    public void setHighLevelSeconds(int i) {
        this.highLevelSeconds = i;
    }

    public int getNormalLevelSeconds() {
        return this.normalLevelSeconds;
    }

    public void setNormalLevelSeconds(int i) {
        this.normalLevelSeconds = i;
    }

    public int getLowLevelSeconds() {
        return this.lowLevelSeconds;
    }

    public void setLowLevelSeconds(int i) {
        this.lowLevelSeconds = i;
    }

    public boolean isHasUpdate() {
        return this.hasUpdate;
    }

    public void setHasUpdate(boolean z) {
        this.hasUpdate = z;
    }

    public String toString() {
        return "HealthVolumeData{id=" + this.id + ", timestamp=" + this.timestamp + ", date='" + this.date + "', userId=" + this.userId + ", avgValue=" + this.avgValue + ", maxValue=" + this.maxValue + ", minValue=" + this.minValue + ", latestValue=" + this.latestValue + ", interval=" + this.interval + ", sourceMac='" + this.sourceMac + "', deviceName='" + this.deviceName + "', items='" + this.items + "', totalSeconds=" + this.totalSeconds + ", superHighLevelSeconds=" + this.superHighLevelSeconds + ", highLevelSeconds=" + this.highLevelSeconds + ", normalLevelSeconds=" + this.normalLevelSeconds + ", lowLevelSeconds=" + this.lowLevelSeconds + ", hasUpdate=" + this.hasUpdate + '}';
    }

    public boolean getHasUpdate() {
        return this.hasUpdate;
    }

    public void delete() {
        HealthVolumeDataDao healthVolumeDataDao = this.myDao;
        if (healthVolumeDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        healthVolumeDataDao.delete(this);
    }

    public void refresh() {
        HealthVolumeDataDao healthVolumeDataDao = this.myDao;
        if (healthVolumeDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        healthVolumeDataDao.refresh(this);
    }

    public void update() {
        HealthVolumeDataDao healthVolumeDataDao = this.myDao;
        if (healthVolumeDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        healthVolumeDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getHealthVolumeDataDao() : null;
    }
}