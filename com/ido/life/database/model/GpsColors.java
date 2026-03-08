package com.ido.life.database.model;

import android.graphics.PointF;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class GpsColors {
    private List<Integer> colors;
    private transient DaoSession daoSession;
    private String dateTime;
    private Long id;
    private transient GpsColorsDao myDao;
    private List<PointF> pointFList;
    private List<Float> position;
    private long userId;

    public GpsColors(Long l, String str, List<Integer> list, List<Float> list2, List<PointF> list3, long j) {
        this.userId = -1L;
        this.id = l;
        this.dateTime = str;
        this.colors = list;
        this.position = list2;
        this.pointFList = list3;
        this.userId = j;
    }

    public GpsColors() {
        this.userId = -1L;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String str) {
        this.dateTime = str;
    }

    public List<Integer> getColors() {
        return this.colors;
    }

    public void setColors(List<Integer> list) {
        this.colors = list;
    }

    public List<Float> getPosition() {
        return this.position;
    }

    public void setPosition(List<Float> list) {
        this.position = list;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public List<PointF> getPointFList() {
        return this.pointFList;
    }

    public void setPointFList(List<PointF> list) {
        this.pointFList = list;
    }

    public void delete() {
        GpsColorsDao gpsColorsDao = this.myDao;
        if (gpsColorsDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        gpsColorsDao.delete(this);
    }

    public void refresh() {
        GpsColorsDao gpsColorsDao = this.myDao;
        if (gpsColorsDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        gpsColorsDao.refresh(this);
    }

    public void update() {
        GpsColorsDao gpsColorsDao = this.myDao;
        if (gpsColorsDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        gpsColorsDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getGpsColorsDao() : null;
    }
}