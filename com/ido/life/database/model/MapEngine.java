package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class MapEngine {
    public static final int ENGINE_GAODE = 2;
    public static final int ENGINE_GOOGLE = 1;
    public static final String TYPE_AMAP = "AMAP";
    public static final String TYPE_BAIDU_MAP = "BAIDU_MAP";
    public static final String TYPE_GOOGLE_MAP = "GOOGLE_MAP";
    private long CreateTime;
    private boolean HasUpload;
    private Long Id;
    private int MapEngine;
    private long UpdateTime;
    private long UserId;
    private transient DaoSession daoSession;
    private transient MapEngineDao myDao;

    public MapEngine(Long l, long j, int i, boolean z, long j2, long j3) {
        this.Id = l;
        this.UserId = j;
        this.MapEngine = i;
        this.HasUpload = z;
        this.CreateTime = j2;
        this.UpdateTime = j3;
    }

    public MapEngine() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.UpdateTime = jCurrentTimeMillis;
        this.CreateTime = jCurrentTimeMillis;
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

    public int getMapEngine() {
        return this.MapEngine;
    }

    public void setMapEngine(int i) {
        this.MapEngine = i;
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

    public void delete() {
        MapEngineDao mapEngineDao = this.myDao;
        if (mapEngineDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mapEngineDao.delete(this);
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

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getMapEngineDao() : null;
    }
}