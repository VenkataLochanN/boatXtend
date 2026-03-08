package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class WalkMonthTotalData {
    private float avgDayHour;
    private transient DaoSession daoSession;
    private Long id;
    private String month;
    private transient WalkMonthTotalDataDao myDao;
    private long userId;

    public WalkMonthTotalData(Long l, long j, float f2, String str) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.avgDayHour = f2;
        this.month = str;
    }

    public WalkMonthTotalData() {
        this.userId = -1L;
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

    public float getAvgDayHour() {
        return this.avgDayHour;
    }

    public void setAvgDayHour(float f2) {
        this.avgDayHour = f2;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public String toString() {
        return "WalkMonthTotalData{id=" + this.id + ", userId=" + this.userId + ", avgDayHour=" + this.avgDayHour + ", month='" + this.month + "'}";
    }

    public void delete() {
        WalkMonthTotalDataDao walkMonthTotalDataDao = this.myDao;
        if (walkMonthTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkMonthTotalDataDao.delete(this);
    }

    public void refresh() {
        WalkMonthTotalDataDao walkMonthTotalDataDao = this.myDao;
        if (walkMonthTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkMonthTotalDataDao.refresh(this);
    }

    public void update() {
        WalkMonthTotalDataDao walkMonthTotalDataDao = this.myDao;
        if (walkMonthTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkMonthTotalDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getWalkMonthTotalDataDao() : null;
    }
}