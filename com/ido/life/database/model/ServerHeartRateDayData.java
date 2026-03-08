package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerHeartRateDayData {
    private int aerobicSeconds;
    private int aerobicThreshold;
    private int anaerobicSeconds;
    private int anaerobicThreshold;
    private int avgValue;
    private int burnFatSeconds;
    private int burnFatThreshold;

    @Expose(deserialize = false, serialize = false)
    private String chartItems;
    private transient DaoSession daoSession;
    private String date;
    private String deviceName;
    private int heartMonitorType;

    @Expose(deserialize = false, serialize = false)
    private Long id;
    private String items;
    private int latestValue;
    private int limitSeconds;
    private int limitThreshold;
    private int maxValue;
    private int minValue;
    private transient ServerHeartRateDayDataDao myDao;
    private int silentValue;
    private String sourceMac;
    private int startTimeValue;

    @Expose(deserialize = false, serialize = false)
    private boolean uploadSuccess;
    private int warmUpSeconds;
    private int warmUpThreshold;

    @Expose(deserialize = false, serialize = false)
    private long userId = -1;

    @Expose(deserialize = false, serialize = false)
    private boolean loadDetail = true;
    private long timestamp = System.currentTimeMillis();

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

    public boolean getUploadSuccess() {
        return this.uploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.uploadSuccess = z;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public int getWarmUpSeconds() {
        return this.warmUpSeconds;
    }

    public void setWarmUpSeconds(int i) {
        this.warmUpSeconds = i;
    }

    public int getWarmUpThreshold() {
        return this.warmUpThreshold;
    }

    public void setWarmUpThreshold(int i) {
        this.warmUpThreshold = i;
    }

    public int getBurnFatSeconds() {
        return this.burnFatSeconds;
    }

    public void setBurnFatSeconds(int i) {
        this.burnFatSeconds = i;
    }

    public int getBurnFatThreshold() {
        return this.burnFatThreshold;
    }

    public void setBurnFatThreshold(int i) {
        this.burnFatThreshold = i;
    }

    public int getAerobicSeconds() {
        return this.aerobicSeconds;
    }

    public void setAerobicSeconds(int i) {
        this.aerobicSeconds = i;
    }

    public int getAerobicThreshold() {
        return this.aerobicThreshold;
    }

    public void setAerobicThreshold(int i) {
        this.aerobicThreshold = i;
    }

    public int getAnaerobicSeconds() {
        return this.anaerobicSeconds;
    }

    public void setAnaerobicSeconds(int i) {
        this.anaerobicSeconds = i;
    }

    public int getAnaerobicThreshold() {
        return this.anaerobicThreshold;
    }

    public void setAnaerobicThreshold(int i) {
        this.anaerobicThreshold = i;
    }

    public int getLimitSeconds() {
        return this.limitSeconds;
    }

    public void setLimitSeconds(int i) {
        this.limitSeconds = i;
    }

    public int getLimitThreshold() {
        return this.limitThreshold;
    }

    public void setLimitThreshold(int i) {
        this.limitThreshold = i;
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

    public int getSilentValue() {
        return this.silentValue;
    }

    public void setSilentValue(int i) {
        this.silentValue = i;
    }

    public int getLatestValue() {
        return this.latestValue;
    }

    public void setLatestValue(int i) {
        this.latestValue = i;
    }

    public int getStartTimeValue() {
        return this.startTimeValue;
    }

    public void setStartTimeValue(int i) {
        this.startTimeValue = i;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
    }

    public int getHeartMonitorType() {
        return this.heartMonitorType;
    }

    public void setHeartMonitorType(int i) {
        this.heartMonitorType = i;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getSourceMac() {
        return this.sourceMac;
    }

    public void setSourceMac(String str) {
        this.sourceMac = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public boolean isUploadSuccess() {
        return this.uploadSuccess;
    }

    public boolean getLoadDetail() {
        return this.loadDetail;
    }

    public void setLoadDetail(boolean z) {
        this.loadDetail = z;
    }

    public String getChartItems() {
        return this.chartItems;
    }

    public void setChartItems(String str) {
        this.chartItems = str;
    }

    public String toString() {
        return "ServerHeartRateDayData{id=" + this.id + ", userId=" + this.userId + ", uploadSuccess=" + this.uploadSuccess + ", date='" + this.date + "', warmUpSeconds=" + this.warmUpSeconds + ", warmUpThreshold=" + this.warmUpThreshold + ", burnFatSeconds=" + this.burnFatSeconds + ", burnFatThreshold=" + this.burnFatThreshold + ", aerobicSeconds=" + this.aerobicSeconds + ", aerobicThreshold=" + this.aerobicThreshold + ", anaerobicSeconds=" + this.anaerobicSeconds + ", anaerobicThreshold=" + this.anaerobicThreshold + ", limitSeconds=" + this.limitSeconds + ", limitThreshold=" + this.limitThreshold + ", minValue=" + this.minValue + ", maxValue=" + this.maxValue + ", avgValue=" + this.avgValue + ", silentValue=" + this.silentValue + ", latestValue=" + this.latestValue + ", startTimeValue=" + this.startTimeValue + ", heartMonitorType=" + this.heartMonitorType + ", deviceName='" + this.deviceName + "', sourceMac='" + this.sourceMac + "', timestamp=" + this.timestamp + ", loadDetail=" + this.loadDetail + '}';
    }

    public void delete() {
        ServerHeartRateDayDataDao serverHeartRateDayDataDao = this.myDao;
        if (serverHeartRateDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverHeartRateDayDataDao.delete(this);
    }

    public void refresh() {
        ServerHeartRateDayDataDao serverHeartRateDayDataDao = this.myDao;
        if (serverHeartRateDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverHeartRateDayDataDao.refresh(this);
    }

    public void update() {
        ServerHeartRateDayDataDao serverHeartRateDayDataDao = this.myDao;
        if (serverHeartRateDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverHeartRateDayDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerHeartRateDayDataDao() : null;
    }
}