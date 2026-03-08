package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class WeightItemBean implements Comparable<WeightItemBean> {

    @SerializedName("bmi")
    private float Bmi;

    @SerializedName("date")
    private String Date;

    @Expose(deserialize = false, serialize = false)
    private Long Id;

    @SerializedName("timestamp")
    private long TimeStamp;

    @SerializedName("totalWeight")
    private float TotalWeight;

    @Expose(deserialize = false, serialize = false)
    private boolean UploadSuccess;

    @Expose(deserialize = false, serialize = false)
    private long UserId;
    private transient DaoSession daoSession;
    private boolean loadDetail;
    private transient WeightItemBeanDao myDao;

    public WeightItemBean() {
        this.loadDetail = true;
        this.UserId = -1L;
        this.UploadSuccess = false;
        this.TimeStamp = System.currentTimeMillis();
    }

    public WeightItemBean(Long l, String str, float f2, float f3, long j, boolean z, long j2, boolean z2) {
        this.loadDetail = true;
        this.UserId = -1L;
        this.UploadSuccess = false;
        this.Id = l;
        this.Date = str;
        this.TotalWeight = f2;
        this.Bmi = f3;
        this.TimeStamp = j;
        this.loadDetail = z;
        this.UserId = j2;
        this.UploadSuccess = z2;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long l) {
        this.Id = l;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String str) {
        this.Date = str;
    }

    public float getTotalWeight() {
        return this.TotalWeight;
    }

    public void setTotalWeight(float f2) {
        this.TotalWeight = f2;
    }

    public float getBmi() {
        return this.Bmi;
    }

    public void setBmi(float f2) {
        this.Bmi = f2;
    }

    public long getTimeStamp() {
        return this.TimeStamp;
    }

    public void setTimeStamp(long j) {
        this.TimeStamp = j;
    }

    public long getUserId() {
        return this.UserId;
    }

    public void setUserId(long j) {
        this.UserId = j;
    }

    public boolean isUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.UploadSuccess = z;
    }

    public boolean getUploadSuccess() {
        return this.UploadSuccess;
    }

    public boolean getLoadDetail() {
        return this.loadDetail;
    }

    public void setLoadDetail(boolean z) {
        this.loadDetail = z;
    }

    public String toString() {
        return "WeightItemBean{Id=" + this.Id + ", Date='" + this.Date + "', TotalWeight=" + this.TotalWeight + ", Bmi=" + this.Bmi + ", TimeStamp=" + this.TimeStamp + ", loadDetail=" + this.loadDetail + ", UserId=" + this.UserId + ", UploadSuccess=" + this.UploadSuccess + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(WeightItemBean weightItemBean) {
        if (weightItemBean == null) {
            return 0;
        }
        return getTimeStamp() > weightItemBean.getTimeStamp() ? 1 : -1;
    }

    public void delete() {
        WeightItemBeanDao weightItemBeanDao = this.myDao;
        if (weightItemBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        weightItemBeanDao.delete(this);
    }

    public void refresh() {
        WeightItemBeanDao weightItemBeanDao = this.myDao;
        if (weightItemBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        weightItemBeanDao.refresh(this);
    }

    public void update() {
        if (this.myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        this.TimeStamp = System.currentTimeMillis();
        this.myDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getWeightItemBeanDao() : null;
    }
}