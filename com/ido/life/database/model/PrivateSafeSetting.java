package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class PrivateSafeSetting {
    public static final int Version = 1;
    private long CreateTime;
    private Long Id;
    private boolean SaveHealthData;
    private boolean SavePrivateData;
    private boolean SaveSportData;
    private boolean SaveToGoogleFit;
    private boolean SaveToStrava;
    private long UpdateTime;
    private boolean UploadSuccess;
    private long UserId;
    private transient DaoSession daoSession;
    private transient PrivateSafeSettingDao myDao;

    public PrivateSafeSetting() {
        this.SaveToStrava = true;
        this.UploadSuccess = false;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.UpdateTime = jCurrentTimeMillis;
        this.CreateTime = jCurrentTimeMillis;
    }

    public PrivateSafeSetting(long j, boolean z, boolean z2, boolean z3, long j2, boolean z4) {
        this.SaveToStrava = true;
        this.UploadSuccess = false;
        this.UserId = j;
        this.SavePrivateData = z;
        this.SaveSportData = z2;
        this.SaveHealthData = z3;
        this.UploadSuccess = z4;
        this.CreateTime = j2;
        this.UpdateTime = j2;
    }

    public PrivateSafeSetting(Long l, long j, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, long j2, long j3) {
        this.SaveToStrava = true;
        this.UploadSuccess = false;
        this.Id = l;
        this.UserId = j;
        this.SavePrivateData = z;
        this.SaveSportData = z2;
        this.SaveHealthData = z3;
        this.SaveToGoogleFit = z4;
        this.SaveToStrava = z5;
        this.UploadSuccess = z6;
        this.CreateTime = j2;
        this.UpdateTime = j3;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(long j) {
        this.Id = Long.valueOf(j);
    }

    public long getUserId() {
        return this.UserId;
    }

    public boolean getSavePrivateData() {
        return this.SavePrivateData;
    }

    public void setSavePrivateData(boolean z) {
        this.SavePrivateData = z;
    }

    public boolean getSaveSportData() {
        return this.SaveSportData;
    }

    public void setSaveSportData(boolean z) {
        this.SaveSportData = z;
    }

    public boolean getSaveHealthData() {
        return this.SaveHealthData;
    }

    public void setSaveHealthData(boolean z) {
        this.SaveHealthData = z;
    }

    public long getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(long j) {
        this.CreateTime = j;
    }

    public long getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(long j) {
        this.UpdateTime = j;
    }

    public boolean isUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.UploadSuccess = z;
    }

    public void setUserId(long j) {
        this.UserId = j;
    }

    public boolean isSavePrivateData() {
        return this.SavePrivateData;
    }

    public boolean isSaveSportData() {
        return this.SaveSportData;
    }

    public boolean isSaveHealthData() {
        return this.SaveHealthData;
    }

    public boolean isSaveToGoogleFit() {
        return this.SaveToGoogleFit;
    }

    public void setSaveToGoogleFit(boolean z) {
        this.SaveToGoogleFit = z;
    }

    public boolean isSaveToStrava() {
        return this.SaveToStrava;
    }

    public void setSaveToStrava(boolean z) {
        this.SaveToStrava = z;
    }

    public void delete() {
        PrivateSafeSettingDao privateSafeSettingDao = this.myDao;
        if (privateSafeSettingDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        privateSafeSettingDao.delete(this);
    }

    public void refresh() {
        if (this.myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        this.UpdateTime = System.currentTimeMillis();
        this.myDao.refresh(this);
    }

    public void update() {
        if (this.myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        this.UpdateTime = System.currentTimeMillis();
        this.myDao.update(this);
    }

    public boolean getUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setId(Long l) {
        this.Id = l;
    }

    public boolean getSaveToGoogleFit() {
        return this.SaveToGoogleFit;
    }

    public boolean getSaveToStrava() {
        return this.SaveToStrava;
    }

    public String toString() {
        return "PrivateSafeSetting{Id=" + this.Id + ", UserId=" + this.UserId + ", SavePrivateData=" + this.SavePrivateData + ", SaveSportData=" + this.SaveSportData + ", SaveHealthData=" + this.SaveHealthData + ", SaveToGoogleFit=" + this.SaveToGoogleFit + ", SaveToStrava=" + this.SaveToStrava + ", UploadSuccess=" + this.UploadSuccess + ", CreateTime=" + this.CreateTime + ", UpdateTime=" + this.UpdateTime + '}';
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getPrivateSafeSettingDao() : null;
    }
}