package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class TempUnitSetting {
    public static final String KEY_C = "CELSIUS";
    public static final String KEY_F = "FAHRENHEIT";
    public static final int Temp_C = 1;
    public static final int Temp_F = 2;
    private long CreateTime;
    private boolean HasSyncDeviceSuccess;
    private boolean HasUpload;
    private Long Id;
    private int Temp;
    private long UpdateTime;
    private long UserId;
    private transient DaoSession daoSession;
    private transient TempUnitSettingDao myDao;

    public TempUnitSetting(Long l, long j, int i, boolean z, boolean z2, long j2, long j3) {
        this.Id = l;
        this.UserId = j;
        this.Temp = i;
        this.HasUpload = z;
        this.HasSyncDeviceSuccess = z2;
        this.CreateTime = j2;
        this.UpdateTime = j3;
    }

    public TempUnitSetting() {
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

    public int getTemp() {
        return this.Temp;
    }

    public void setTemp(int i) {
        this.Temp = i;
    }

    public boolean getHasUpload() {
        return this.HasUpload;
    }

    public void setHasUpload(boolean z) {
        this.HasUpload = z;
    }

    public boolean getHasSyncDeviceSuccess() {
        return this.HasSyncDeviceSuccess;
    }

    public void setHasSyncDeviceSuccess(boolean z) {
        this.HasSyncDeviceSuccess = z;
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

    public void delete() {
        TempUnitSettingDao tempUnitSettingDao = this.myDao;
        if (tempUnitSettingDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        tempUnitSettingDao.delete(this);
    }

    public void refresh() {
        TempUnitSettingDao tempUnitSettingDao = this.myDao;
        if (tempUnitSettingDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        tempUnitSettingDao.refresh(this);
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
        this.myDao = daoSession != null ? daoSession.getTempUnitSettingDao() : null;
    }
}