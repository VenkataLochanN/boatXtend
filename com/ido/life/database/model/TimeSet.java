package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class TimeSet {
    public static final int FLOW_SYSTEM = 0;
    public static final int FORMAT_12 = 2;
    public static final int FORMAT_24 = 1;
    public static final String KEY_FLOW_SYSTEM = "SYSTEM_HOUR_CLOCK";
    public static final String KEY_HOUR_12 = "HOUR_CLOCK_12";
    public static final String KEY_HOUR_24 = "HOUR_CLOCK_24";
    private long CreateTime;
    private boolean HasSyncDeviceSuccess;
    private boolean HasUpload;
    private Long Id;
    private int TimeFormat;
    private long UpdateTime;
    private long UserId;
    private transient DaoSession daoSession;
    private transient TimeSetDao myDao;

    public TimeSet() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.CreateTime = jCurrentTimeMillis;
        this.UpdateTime = jCurrentTimeMillis;
    }

    public TimeSet(Long l, int i, int i2, boolean z, boolean z2, long j, long j2, DaoSession daoSession, TimeSetDao timeSetDao) {
        this.Id = l;
        this.UserId = i;
        this.TimeFormat = i2;
        this.HasUpload = z;
        this.HasSyncDeviceSuccess = z2;
        this.CreateTime = j;
        this.UpdateTime = j2;
        this.daoSession = daoSession;
        this.myDao = timeSetDao;
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

    public int getTimeFormat() {
        return this.TimeFormat;
    }

    public void setTimeFormat(int i) {
        this.TimeFormat = i;
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
        TimeSetDao timeSetDao = this.myDao;
        if (timeSetDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        timeSetDao.delete(this);
    }

    public void refresh() {
        TimeSetDao timeSetDao = this.myDao;
        if (timeSetDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        timeSetDao.refresh(this);
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
        this.myDao = daoSession != null ? daoSession.getTimeSetDao() : null;
    }
}