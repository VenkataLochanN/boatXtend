package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class MessageEntity {
    public static final int TYPE_HEALTH_REPORT = 3;
    public static final int TYPE_NEW_MEDAL = 2;
    public static final int TYPE_NEW_VERSION = 1;
    private long CreateTime;
    private int DescType;
    private boolean HasRead;
    private boolean HasUpload;
    private Long Id;
    private boolean MondayGenerate;
    private boolean SaturdayGenerate;
    private String StartDayMonday;
    private String StartDaySaturday;
    private String StartDaySunday;
    private int SubType;
    private boolean SundayGenerate;
    private int Type;
    private long UpdateTime;
    private long UserId;
    private transient DaoSession daoSession;
    private transient MessageEntityDao myDao;

    public MessageEntity() {
        this.MondayGenerate = false;
        this.SaturdayGenerate = false;
        this.SundayGenerate = false;
        this.HasUpload = false;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.UpdateTime = jCurrentTimeMillis;
        this.CreateTime = jCurrentTimeMillis;
    }

    public MessageEntity(Long l, long j, int i, int i2, String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, long j2, int i3, long j3, boolean z5) {
        this.MondayGenerate = false;
        this.SaturdayGenerate = false;
        this.SundayGenerate = false;
        this.HasUpload = false;
        this.Id = l;
        this.UserId = j;
        this.Type = i;
        this.SubType = i2;
        this.StartDayMonday = str;
        this.StartDaySaturday = str2;
        this.StartDaySunday = str3;
        this.MondayGenerate = z;
        this.SaturdayGenerate = z2;
        this.SundayGenerate = z3;
        this.HasRead = z4;
        this.CreateTime = j2;
        this.DescType = i3;
        this.UpdateTime = j3;
        this.HasUpload = z5;
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

    public int getType() {
        return this.Type;
    }

    public void setType(int i) {
        this.Type = i;
    }

    public int getSubType() {
        return this.SubType;
    }

    public void setSubType(int i) {
        this.SubType = i;
    }

    public boolean isHasRead() {
        return this.HasRead;
    }

    public void setHasRead(boolean z) {
        this.HasRead = z;
    }

    public long getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(long j) {
        this.CreateTime = j;
    }

    public int getDescType() {
        return this.DescType;
    }

    public void setDescType(int i) {
        this.DescType = i;
    }

    public long getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(long j) {
        this.UpdateTime = j;
    }

    public boolean getHasRead() {
        return this.HasRead;
    }

    public String getStartDayMonday() {
        return this.StartDayMonday;
    }

    public void setStartDayMonday(String str) {
        this.StartDayMonday = str;
    }

    public String getStartDaySaturday() {
        return this.StartDaySaturday;
    }

    public void setStartDaySaturday(String str) {
        this.StartDaySaturday = str;
    }

    public String getStartDaySunday() {
        return this.StartDaySunday;
    }

    public void setStartDaySunday(String str) {
        this.StartDaySunday = str;
    }

    public boolean getMondayGenerate() {
        return this.MondayGenerate;
    }

    public void setMondayGenerate(boolean z) {
        this.MondayGenerate = z;
    }

    public boolean getSaturdayGenerate() {
        return this.SaturdayGenerate;
    }

    public void setSaturdayGenerate(boolean z) {
        this.SaturdayGenerate = z;
    }

    public boolean getSundayGenerate() {
        return this.SundayGenerate;
    }

    public void setSundayGenerate(boolean z) {
        this.SundayGenerate = z;
    }

    public boolean getHasUpload() {
        return this.HasUpload;
    }

    public void setHasUpload(boolean z) {
        this.HasUpload = z;
    }

    public String toString() {
        return "MessageEntity{Id=" + this.Id + ", UserId=" + this.UserId + ", Type=" + this.Type + ", SubType=" + this.SubType + ", StartDayMonday='" + this.StartDayMonday + "', StartDaySaturday='" + this.StartDaySaturday + "', StartDaySunday='" + this.StartDaySunday + "', MondayGenerate=" + this.MondayGenerate + ", SaturdayGenerate=" + this.SaturdayGenerate + ", SundayGenerate=" + this.SundayGenerate + ", HasRead=" + this.HasRead + ", CreateTime=" + this.CreateTime + ", DescType=" + this.DescType + ", UpdateTime=" + this.UpdateTime + ", HasUpload=" + this.HasUpload + '}';
    }

    public void delete() {
        MessageEntityDao messageEntityDao = this.myDao;
        if (messageEntityDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        messageEntityDao.delete(this);
    }

    public void refresh() {
        MessageEntityDao messageEntityDao = this.myDao;
        if (messageEntityDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        messageEntityDao.refresh(this);
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
        this.myDao = daoSession != null ? daoSession.getMessageEntityDao() : null;
    }
}