package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class UserMedalInfo {

    @SerializedName("createTime")
    private String CreateTime;

    @Expose(deserialize = false, serialize = false)
    private String Date;

    @Expose(deserialize = false, serialize = false)
    private Long Id;

    @SerializedName("medalId")
    private int MedalId;

    @Expose(deserialize = false, serialize = false)
    private boolean ShowToUser;

    @Expose(deserialize = false, serialize = false)
    private boolean UploadSuccess;

    @SerializedName("userId")
    private long UserId;
    private transient DaoSession daoSession;
    private transient UserMedalInfoDao myDao;

    public UserMedalInfo(Long l, long j, int i, String str, String str2, boolean z, boolean z2) {
        this.UploadSuccess = false;
        this.Id = l;
        this.UserId = j;
        this.MedalId = i;
        this.CreateTime = str;
        this.Date = str2;
        this.ShowToUser = z;
        this.UploadSuccess = z2;
    }

    public UserMedalInfo() {
        this.UploadSuccess = false;
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

    public int getMedalId() {
        return this.MedalId;
    }

    public void setMedalId(int i) {
        this.MedalId = i;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String str) {
        this.Date = str;
    }

    public boolean isShowToUser() {
        return this.ShowToUser;
    }

    public void setShowToUser(boolean z) {
        this.ShowToUser = z;
    }

    public boolean isUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.UploadSuccess = z;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String str) {
        this.CreateTime = str;
    }

    public boolean getShowToUser() {
        return this.ShowToUser;
    }

    public boolean getUploadSuccess() {
        return this.UploadSuccess;
    }

    public void delete() {
        UserMedalInfoDao userMedalInfoDao = this.myDao;
        if (userMedalInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userMedalInfoDao.delete(this);
    }

    public void refresh() {
        UserMedalInfoDao userMedalInfoDao = this.myDao;
        if (userMedalInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userMedalInfoDao.refresh(this);
    }

    public void update() {
        UserMedalInfoDao userMedalInfoDao = this.myDao;
        if (userMedalInfoDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userMedalInfoDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getUserMedalInfoDao() : null;
    }
}