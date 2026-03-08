package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class MenstruationConfig {
    private Long Id;
    private int MensCycle;
    private int MensLength;
    private long UserId;
    private transient DaoSession daoSession;
    private transient MenstruationConfigDao myDao;
    private long updateTimeStamp;
    private boolean uploadSuccess;

    public MenstruationConfig(Long l, long j, int i, int i2, long j2, boolean z) {
        this.MensLength = 0;
        this.MensCycle = 0;
        this.Id = l;
        this.UserId = j;
        this.MensLength = i;
        this.MensCycle = i2;
        this.updateTimeStamp = j2;
        this.uploadSuccess = z;
    }

    public MenstruationConfig() {
        this.MensLength = 0;
        this.MensCycle = 0;
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

    public int getMensLength() {
        return this.MensLength;
    }

    public void setMensLength(int i) {
        this.MensLength = i;
    }

    public int getMensCycle() {
        return this.MensCycle;
    }

    public void setMensCycle(int i) {
        this.MensCycle = i;
    }

    public long getUpdateTimeStamp() {
        return this.updateTimeStamp;
    }

    public void setUpdateTimeStamp(long j) {
        this.updateTimeStamp = j;
    }

    public boolean getUploadSuccess() {
        return this.uploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.uploadSuccess = z;
    }

    public void delete() {
        MenstruationConfigDao menstruationConfigDao = this.myDao;
        if (menstruationConfigDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        menstruationConfigDao.delete(this);
    }

    public void refresh() {
        MenstruationConfigDao menstruationConfigDao = this.myDao;
        if (menstruationConfigDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        menstruationConfigDao.refresh(this);
    }

    public void update() {
        MenstruationConfigDao menstruationConfigDao = this.myDao;
        if (menstruationConfigDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        menstruationConfigDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getMenstruationConfigDao() : null;
    }
}