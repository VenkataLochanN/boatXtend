package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class WeekStartSetting {
    public static final String FRIDAY = "FRIDAY";
    public static final String MONDAY = "MONDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    private long CreateTime;
    private boolean HasSyncDeviceSuccess;
    private boolean HasUpload;
    private Long Id;
    private long UpdateTime;
    private long UserId;
    private int WeekStart;
    private transient DaoSession daoSession;
    private transient WeekStartSettingDao myDao;

    public WeekStartSetting(Long l, int i, int i2, boolean z, long j, long j2, boolean z2, DaoSession daoSession, WeekStartSettingDao weekStartSettingDao) {
        this.Id = l;
        this.UserId = i;
        this.WeekStart = i2;
        this.HasUpload = z;
        this.CreateTime = j;
        this.UpdateTime = j2;
        this.HasSyncDeviceSuccess = z2;
        this.daoSession = daoSession;
        this.myDao = weekStartSettingDao;
    }

    public WeekStartSetting() {
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

    public int getWeekStart() {
        return this.WeekStart;
    }

    public void setWeekStart(int i) {
        this.WeekStart = i;
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

    public boolean getHasSyncDeviceSuccess() {
        return this.HasSyncDeviceSuccess;
    }

    public boolean isHasSyncDeviceSuccess() {
        return this.HasSyncDeviceSuccess;
    }

    public void setHasSyncDeviceSuccess(boolean z) {
        this.HasSyncDeviceSuccess = z;
    }

    public void delete() {
        WeekStartSettingDao weekStartSettingDao = this.myDao;
        if (weekStartSettingDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        weekStartSettingDao.delete(this);
    }

    public void refresh() {
        WeekStartSettingDao weekStartSettingDao = this.myDao;
        if (weekStartSettingDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        weekStartSettingDao.refresh(this);
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
        this.myDao = daoSession != null ? daoSession.getWeekStartSettingDao() : null;
    }
}