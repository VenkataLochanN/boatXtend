package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class WalkYearTotalData {
    private float avgDayHour;
    private transient DaoSession daoSession;
    private String endDate;
    private Long id;
    private transient WalkYearTotalDataDao myDao;
    private String startDate;
    private int totalHour;
    private long userId;

    public WalkYearTotalData(Long l, long j, int i, float f2, String str, String str2) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.totalHour = i;
        this.avgDayHour = f2;
        this.startDate = str;
        this.endDate = str2;
    }

    public WalkYearTotalData() {
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

    public int getTotalHour() {
        return this.totalHour;
    }

    public void setTotalHour(int i) {
        this.totalHour = i;
    }

    public float getAvgDayHour() {
        return this.avgDayHour;
    }

    public void setAvgDayHour(float f2) {
        this.avgDayHour = f2;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String toString() {
        return "WalkYearTotalData{id=" + this.id + ", userId=" + this.userId + ", totalHour=" + this.totalHour + ", avgDayHour=" + this.avgDayHour + ", startDate='" + this.startDate + "', endDate='" + this.endDate + "'}";
    }

    public void delete() {
        WalkYearTotalDataDao walkYearTotalDataDao = this.myDao;
        if (walkYearTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkYearTotalDataDao.delete(this);
    }

    public void refresh() {
        WalkYearTotalDataDao walkYearTotalDataDao = this.myDao;
        if (walkYearTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkYearTotalDataDao.refresh(this);
    }

    public void update() {
        WalkYearTotalDataDao walkYearTotalDataDao = this.myDao;
        if (walkYearTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkYearTotalDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getWalkYearTotalDataDao() : null;
    }
}