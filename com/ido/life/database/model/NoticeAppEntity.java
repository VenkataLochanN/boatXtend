package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class NoticeAppEntity {
    private Long Id;
    private int Type;
    private String appName;
    private long appUpdateTime;
    private transient DaoSession daoSession;
    private boolean isPush;
    private String mac;
    private transient NoticeAppEntityDao myDao;
    private String pkgName;
    private long reminderCounts;

    public NoticeAppEntity() {
    }

    public NoticeAppEntity(Long l, int i, String str, long j, long j2, String str2, boolean z, String str3) {
        this.Id = l;
        this.Type = i;
        this.pkgName = str;
        this.reminderCounts = j;
        this.appUpdateTime = j2;
        this.appName = str2;
        this.isPush = z;
        this.mac = str3;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long l) {
        this.Id = l;
    }

    public int getType() {
        return this.Type;
    }

    public void setType(int i) {
        this.Type = i;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public long getReminderCounts() {
        return this.reminderCounts;
    }

    public void setReminderCounts(long j) {
        this.reminderCounts = j;
    }

    public long getAppUpdateTime() {
        return this.appUpdateTime;
    }

    public void setAppUpdateTime(long j) {
        this.appUpdateTime = j;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public boolean getIsPush() {
        return this.isPush;
    }

    public void setIsPush(boolean z) {
        this.isPush = z;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void delete() {
        NoticeAppEntityDao noticeAppEntityDao = this.myDao;
        if (noticeAppEntityDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        noticeAppEntityDao.delete(this);
    }

    public void refresh() {
        NoticeAppEntityDao noticeAppEntityDao = this.myDao;
        if (noticeAppEntityDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        noticeAppEntityDao.refresh(this);
    }

    public void update() {
        NoticeAppEntityDao noticeAppEntityDao = this.myDao;
        if (noticeAppEntityDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        noticeAppEntityDao.update(this);
    }

    public String toString() {
        return "NoticeAppEntity{Id=" + this.Id + ", Type=" + this.Type + ", pkgName='" + this.pkgName + "', reminderCounts=" + this.reminderCounts + ", appUpdateTime=" + this.appUpdateTime + ", appName='" + this.appName + "', isPush=" + this.isPush + ", mac='" + this.mac + '}';
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getNoticeAppEntityDao() : null;
    }
}