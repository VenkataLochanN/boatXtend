package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class HealthPressure {

    @SerializedName("avgValue")
    private int AvgPressure;

    @SerializedName("date")
    private String Date;

    @SerializedName("deviceName")
    private String DeviceName;

    @SerializedName("higherCount")
    private int HigherCount;

    @SerializedName("higherRatio")
    private int HigherRatio;

    @Expose(deserialize = false, serialize = false)
    private Long Id;

    @SerializedName("items")
    private String Items;

    @SerializedName("latestValue")
    private int LastestPressure;

    @SerializedName("maxValue")
    private int MaxPressure;

    @SerializedName("mediumCount")
    private int MediumCount;

    @SerializedName("mediumRatio")
    private int MediumRatio;

    @SerializedName("minValue")
    private int MinPressure;

    @SerializedName("normalCount")
    private int NormalCount;

    @SerializedName("normalRatio")
    private int NormalRatio;

    @SerializedName("relaxCount")
    private int RelaxCount;

    @SerializedName("relaxRatio")
    private int RelaxRatio;

    @SerializedName("sourceMac")
    private String SourceMac;

    @SerializedName("timestamp")
    private long TimeStamp;

    @Expose(deserialize = false, serialize = false)
    private boolean UploadSuccess;

    @Expose(deserialize = false, serialize = false)
    private long UserId;
    private transient DaoSession daoSession;

    @Expose(deserialize = false)
    private boolean loadDetail;
    private transient HealthPressureDao myDao;

    public HealthPressure() {
        this.loadDetail = true;
        this.UploadSuccess = false;
    }

    public HealthPressure(Long l, long j, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str2, String str3, String str4, long j2, int i9, int i10, int i11, int i12, boolean z, boolean z2) {
        this.loadDetail = true;
        this.UploadSuccess = false;
        this.Id = l;
        this.UserId = j;
        this.Date = str;
        this.MaxPressure = i;
        this.MinPressure = i2;
        this.AvgPressure = i3;
        this.LastestPressure = i4;
        this.RelaxRatio = i5;
        this.NormalRatio = i6;
        this.MediumRatio = i7;
        this.HigherRatio = i8;
        this.SourceMac = str2;
        this.DeviceName = str3;
        this.Items = str4;
        this.TimeStamp = j2;
        this.RelaxCount = i9;
        this.NormalCount = i10;
        this.MediumCount = i11;
        this.HigherCount = i12;
        this.loadDetail = z;
        this.UploadSuccess = z2;
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

    public String getDate() {
        return this.Date;
    }

    public void setDate(String str) {
        this.Date = str;
    }

    public int getAvgPressure() {
        return this.AvgPressure;
    }

    public void setAvgPressure(int i) {
        this.AvgPressure = i;
    }

    public int getMaxPressure() {
        return this.MaxPressure;
    }

    public void setMaxPressure(int i) {
        this.MaxPressure = i;
    }

    public int getMinPressure() {
        return this.MinPressure;
    }

    public void setMinPressure(int i) {
        this.MinPressure = i;
    }

    public int getLastestPressure() {
        return this.LastestPressure;
    }

    public void setLastestPressure(int i) {
        this.LastestPressure = i;
    }

    public int getRelaxRatio() {
        return this.RelaxRatio;
    }

    public void setRelaxRatio(int i) {
        this.RelaxRatio = i;
    }

    public int getNormalRatio() {
        return this.NormalRatio;
    }

    public void setNormalRatio(int i) {
        this.NormalRatio = i;
    }

    public int getMediumRatio() {
        return this.MediumRatio;
    }

    public void setMediumRatio(int i) {
        this.MediumRatio = i;
    }

    public int getHigherRatio() {
        return this.HigherRatio;
    }

    public void setHigherRatio(int i) {
        this.HigherRatio = i;
    }

    public String getSourceMac() {
        return this.SourceMac;
    }

    public void setSourceMac(String str) {
        this.SourceMac = str;
    }

    public String getDeviceName() {
        return this.DeviceName;
    }

    public void setDeviceName(String str) {
        this.DeviceName = str;
    }

    public String getItems() {
        return this.Items;
    }

    public void setItems(String str) {
        this.Items = str;
    }

    public long getTimeStamp() {
        return this.TimeStamp;
    }

    public void setTimeStamp(long j) {
        this.TimeStamp = j;
    }

    public boolean getUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.UploadSuccess = z;
    }

    public int getRelaxCount() {
        return this.RelaxCount;
    }

    public void setRelaxCount(int i) {
        this.RelaxCount = i;
    }

    public int getNormalCount() {
        return this.NormalCount;
    }

    public void setNormalCount(int i) {
        this.NormalCount = i;
    }

    public int getMediumCount() {
        return this.MediumCount;
    }

    public void setMediumCount(int i) {
        this.MediumCount = i;
    }

    public int getHigherCount() {
        return this.HigherCount;
    }

    public void setHigherCount(int i) {
        this.HigherCount = i;
    }

    public boolean isUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setLoadDetail(boolean z) {
        this.loadDetail = z;
    }

    public boolean getLoadDetail() {
        return this.loadDetail;
    }

    public String toString() {
        return "HealthPressure{Id=" + this.Id + ", UserId=" + this.UserId + ", Date='" + this.Date + "', MaxPressure=" + this.MaxPressure + ", MinPressure=" + this.MinPressure + ", AvgPressure=" + this.AvgPressure + ", LastestPressure=" + this.LastestPressure + ", RelaxRatio=" + this.RelaxRatio + ", NormalRatio=" + this.NormalRatio + ", MediumRatio=" + this.MediumRatio + ", HigherRatio=" + this.HigherRatio + ", SourceMac='" + this.SourceMac + "', DeviceName='" + this.DeviceName + "', TimeStamp=" + this.TimeStamp + ", RelaxCount=" + this.RelaxCount + ", NormalCount=" + this.NormalCount + ", MediumCount=" + this.MediumCount + ", HigherCount=" + this.HigherCount + ", loadDetail=" + this.loadDetail + ", UploadSuccess=" + this.UploadSuccess + '}';
    }

    public void delete() {
        HealthPressureDao healthPressureDao = this.myDao;
        if (healthPressureDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        healthPressureDao.delete(this);
    }

    public void refresh() {
        HealthPressureDao healthPressureDao = this.myDao;
        if (healthPressureDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        healthPressureDao.refresh(this);
    }

    public void update() {
        HealthPressureDao healthPressureDao = this.myDao;
        if (healthPressureDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        healthPressureDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getHealthPressureDao() : null;
    }
}