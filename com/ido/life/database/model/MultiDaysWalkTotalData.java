package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class MultiDaysWalkTotalData {
    private float avgHour;
    private transient DaoSession daoSession;
    private String endDate;
    private Long id;
    private transient MultiDaysWalkTotalDataDao myDao;
    private String startDate;
    private int totalHour;
    private long userId;

    public MultiDaysWalkTotalData(Long l, long j, int i, float f2, String str, String str2) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.totalHour = i;
        this.avgHour = f2;
        this.startDate = str;
        this.endDate = str2;
    }

    public MultiDaysWalkTotalData() {
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

    public float getAvgHour() {
        return this.avgHour;
    }

    public void setAvgHour(float f2) {
        this.avgHour = f2;
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
        return "MultiDaysWalkTotalData{id=" + this.id + ", userId=" + this.userId + ", totalHour=" + this.totalHour + ", avgHour=" + this.avgHour + ", startDate='" + this.startDate + "', endDate='" + this.endDate + "'}";
    }

    public void delete() {
        MultiDaysWalkTotalDataDao multiDaysWalkTotalDataDao = this.myDao;
        if (multiDaysWalkTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        multiDaysWalkTotalDataDao.delete(this);
    }

    public void refresh() {
        MultiDaysWalkTotalDataDao multiDaysWalkTotalDataDao = this.myDao;
        if (multiDaysWalkTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        multiDaysWalkTotalDataDao.refresh(this);
    }

    public void update() {
        MultiDaysWalkTotalDataDao multiDaysWalkTotalDataDao = this.myDao;
        if (multiDaysWalkTotalDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        multiDaysWalkTotalDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getMultiDaysWalkTotalDataDao() : null;
    }
}