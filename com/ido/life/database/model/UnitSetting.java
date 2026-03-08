package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class UnitSetting {
    public static final String BRITISH_SYSTEM_UNIT = "BRITISH_SYSTEM_UNIT";
    public static final String METRIC_SYSTEM_UNIT = "METRIC_SYSTEM_UNIT";
    public static final int Unit_Em = 2;
    public static final int Unit_Km = 1;
    private long CreateTime;
    private boolean HasSyncDeviceSuccess;
    private boolean HasUpload;
    private Long Id;
    private int Unit;
    private long UpdateTime;
    private long UserId;
    private transient DaoSession daoSession;
    private transient UnitSettingDao myDao;

    public UnitSetting(Long l, int i, int i2, boolean z, boolean z2, long j, long j2, DaoSession daoSession, UnitSettingDao unitSettingDao) {
        this.Id = l;
        this.UserId = i;
        this.Unit = i2;
        this.HasUpload = z;
        this.HasSyncDeviceSuccess = z2;
        this.CreateTime = j;
        this.UpdateTime = j2;
        this.daoSession = daoSession;
        this.myDao = unitSettingDao;
    }

    public UnitSetting() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.CreateTime = jCurrentTimeMillis;
        this.UpdateTime = jCurrentTimeMillis;
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

    public int getUnit() {
        return this.Unit;
    }

    public void setUnit(int i) {
        this.Unit = i;
    }

    public boolean isHasUpload() {
        return this.HasUpload;
    }

    public void setHasUpload(boolean z) {
        this.HasUpload = z;
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

    public boolean getHasUpload() {
        return this.HasUpload;
    }

    public boolean isHasSyncDeviceSuccess() {
        return this.HasSyncDeviceSuccess;
    }

    public boolean getHasSyncDeviceSuccess() {
        return this.HasSyncDeviceSuccess;
    }

    public void setHasSyncDeviceSuccess(boolean z) {
        this.HasSyncDeviceSuccess = z;
    }

    public void delete() {
        UnitSettingDao unitSettingDao = this.myDao;
        if (unitSettingDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        unitSettingDao.delete(this);
    }

    public void refresh() {
        UnitSettingDao unitSettingDao = this.myDao;
        if (unitSettingDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        unitSettingDao.refresh(this);
    }

    public void update() {
        if (this.myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        this.UpdateTime = System.currentTimeMillis();
        this.myDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getUnitSettingDao() : null;
    }
}