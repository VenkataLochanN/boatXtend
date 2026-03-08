package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class SportGpsData {
    private transient DaoSession daoSession;
    private SportGps gpsData;
    private Long id;
    private boolean isDown;
    private transient SportGpsDataDao myDao;
    public long timeMillis;
    private long userId;

    public SportGpsData() {
        this.userId = -1L;
    }

    public SportGpsData(Long l, SportGps sportGps, long j, long j2, boolean z) {
        this.userId = -1L;
        this.id = l;
        this.gpsData = sportGps;
        this.timeMillis = j;
        this.userId = j2;
        this.isDown = z;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public SportGps getGpsData() {
        return this.gpsData;
    }

    public void setGpsData(SportGps sportGps) {
        this.gpsData = sportGps;
    }

    public long getTimeMillis() {
        return this.timeMillis;
    }

    public void setTimeMillis(long j) {
        this.timeMillis = j;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public boolean isDown() {
        return this.isDown;
    }

    public void setDown(boolean z) {
        this.isDown = z;
    }

    public boolean getIsDown() {
        return this.isDown;
    }

    public void setIsDown(boolean z) {
        this.isDown = z;
    }

    public void delete() {
        SportGpsDataDao sportGpsDataDao = this.myDao;
        if (sportGpsDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportGpsDataDao.delete(this);
    }

    public void refresh() {
        SportGpsDataDao sportGpsDataDao = this.myDao;
        if (sportGpsDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportGpsDataDao.refresh(this);
    }

    public void update() {
        SportGpsDataDao sportGpsDataDao = this.myDao;
        if (sportGpsDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportGpsDataDao.update(this);
    }

    public String toString() {
        return "SportGpsData{id=" + this.id + ", gpsData=" + this.gpsData + ", timeMillis=" + this.timeMillis + ", userId=" + this.userId + ", isDown=" + this.isDown + '}';
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getSportGpsDataDao() : null;
    }
}