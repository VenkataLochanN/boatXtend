package com.ido.life.database.model;

import java.util.Map;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class DataDownLoadState {
    public static final int EXCUTING = 2;
    public static final int FAILED = 4;
    public static final int SUCCESS = 3;
    public static final int WAIT_START = 1;
    private String DataName;
    private int DownloadState;
    private long DownloadTimeStamp;
    private Long Id;
    private Map<String, String> ParamsMap;
    private int TaskId;
    private String Url;
    private long UserId;
    private transient DaoSession daoSession;
    private transient DataDownLoadStateDao myDao;

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

    public String getDataName() {
        return this.DataName;
    }

    public void setDataName(String str) {
        this.DataName = str;
    }

    public Map<String, String> getParamsMap() {
        return this.ParamsMap;
    }

    public void setParamsMap(Map<String, String> map) {
        this.ParamsMap = map;
    }

    public long getDownloadTimeStamp() {
        return this.DownloadTimeStamp;
    }

    public void setDownloadTimeStamp(long j) {
        this.DownloadTimeStamp = j;
    }

    public String getUrl() {
        return this.Url;
    }

    public void setUrl(String str) {
        this.Url = str;
    }

    public int getDownloadState() {
        return this.DownloadState;
    }

    public void setDownloadState(int i) {
        this.DownloadState = i;
    }

    public int getTaskId() {
        return this.TaskId;
    }

    public void setTaskId(int i) {
        this.TaskId = i;
    }

    public String toString() {
        return "DataDownLoadState{Id=" + this.Id + ", UserId=" + this.UserId + ", DataName='" + this.DataName + "', DownloadState=" + this.DownloadState + ", TaskId=" + this.TaskId + ", ParamsMap=" + this.ParamsMap + ", Url='" + this.Url + "', DownloadTimeStamp=" + this.DownloadTimeStamp + '}';
    }

    public void update() {
        DataDownLoadStateDao dataDownLoadStateDao = this.myDao;
        if (dataDownLoadStateDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        dataDownLoadStateDao.update(this);
    }

    public void delete() {
        DataDownLoadStateDao dataDownLoadStateDao = this.myDao;
        if (dataDownLoadStateDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        dataDownLoadStateDao.delete(this);
    }

    public void refresh() {
        DataDownLoadStateDao dataDownLoadStateDao = this.myDao;
        if (dataDownLoadStateDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        dataDownLoadStateDao.refresh(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getDataDownLoadStateDao() : null;
    }
}