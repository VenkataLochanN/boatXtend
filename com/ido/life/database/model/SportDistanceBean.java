package com.ido.life.database.model;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class SportDistanceBean implements Comparable<SportDistanceBean>, Cloneable {

    @SerializedName("date")
    private String Date;

    @Expose(deserialize = false)
    private transient float DayAvgDistance;

    @SerializedName("deviceName")
    private String DeviceName;

    @Expose(deserialize = false, serialize = false)
    private Long Id;

    @Expose(deserialize = false, serialize = false)
    private String Items;

    @Expose(deserialize = false)
    private boolean LoadDetail;

    @SerializedName("items")
    private String OrignalItems;

    @SerializedName("sourceMac")
    private String SourceMac;

    @SerializedName("timestamp")
    private long TimeStamp;

    @SerializedName("distances")
    private float TotalDistance;

    @Expose(deserialize = false, serialize = false)
    private boolean UploadSuccess;

    @Expose(deserialize = false, serialize = false)
    private long UserId;
    private transient DaoSession daoSession;
    private transient SportDistanceBeanDao myDao;

    public SportDistanceBean() {
        this.LoadDetail = true;
        this.TimeStamp = System.currentTimeMillis();
    }

    public SportDistanceBean(Long l, long j, String str, float f2, String str2, String str3, String str4, String str5, long j2, boolean z, boolean z2) {
        this.LoadDetail = true;
        this.Id = l;
        this.UserId = j;
        this.Date = str;
        this.TotalDistance = f2;
        this.OrignalItems = str2;
        this.Items = str3;
        this.SourceMac = str4;
        this.DeviceName = str5;
        this.TimeStamp = j2;
        this.LoadDetail = z;
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

    public String getItems() {
        return this.Items;
    }

    public void setItems(String str) {
        this.Items = str;
    }

    public float getTotalDistance() {
        return this.TotalDistance;
    }

    public void setTotalDistance(float f2) {
        this.TotalDistance = f2;
    }

    public String getSourceMac() {
        return this.SourceMac;
    }

    public void setSourceMac(String str) {
        this.SourceMac = str;
    }

    public String getDeviceName() {
        return this.DeviceName;
    }

    public void setDeviceName(String str) {
        this.DeviceName = str;
    }

    public long getTimeStamp() {
        return this.TimeStamp;
    }

    public void setTimeStamp(long j) {
        this.TimeStamp = j;
    }

    public boolean isUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.UploadSuccess = z;
    }

    public long getUserId() {
        return this.UserId;
    }

    public void setUserId(long j) {
        this.UserId = j;
    }

    public String getOrignalItems() {
        return this.OrignalItems;
    }

    public void setOrignalItems(String str) {
        this.OrignalItems = str;
    }

    public float getDayAvgDistance() {
        return this.DayAvgDistance;
    }

    public void setDayAvgDistance(float f2) {
        this.DayAvgDistance = f2;
    }

    public void setLoadDetail(boolean z) {
        this.LoadDetail = z;
    }

    public boolean getLoadDetail() {
        return this.LoadDetail;
    }

    @Override // java.lang.Comparable
    public int compareTo(SportDistanceBean sportDistanceBean) {
        if (TextUtils.isEmpty(getDate()) || TextUtils.isEmpty(sportDistanceBean.getDate())) {
            return 0;
        }
        return DateUtil.string2Date(this.Date, DateUtil.DATE_FORMAT_YMD).compareTo(DateUtil.string2Date(sportDistanceBean.getDate(), DateUtil.DATE_FORMAT_YMD));
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SportDistanceBean m25clone() {
        SportDistanceBean sportDistanceBean = new SportDistanceBean();
        sportDistanceBean.setTotalDistance(this.TotalDistance);
        sportDistanceBean.setItems(this.Items);
        sportDistanceBean.setOrignalItems(this.OrignalItems);
        sportDistanceBean.setDate(this.Date);
        sportDistanceBean.setDeviceName(this.DeviceName);
        sportDistanceBean.setSourceMac(this.SourceMac);
        sportDistanceBean.setTimeStamp(this.TimeStamp);
        sportDistanceBean.setUploadSuccess(this.UploadSuccess);
        sportDistanceBean.setLoadDetail(this.LoadDetail);
        return sportDistanceBean;
    }

    public String toString() {
        return "SportDistanceBean{Id=" + this.Id + ", UserId=" + this.UserId + ", Date='" + this.Date + "', TotalDistance=" + this.TotalDistance + ", OrignalItems='" + this.OrignalItems + "', Items='" + this.Items + "', SourceMac='" + this.SourceMac + "', DeviceName='" + this.DeviceName + "', TimeStamp=" + this.TimeStamp + ", DayAvgDistance=" + this.DayAvgDistance + ", loadDetail=" + this.LoadDetail + ", UploadSuccess=" + this.UploadSuccess + '}';
    }

    public boolean getUploadSuccess() {
        return this.UploadSuccess;
    }

    public void delete() {
        SportDistanceBeanDao sportDistanceBeanDao = this.myDao;
        if (sportDistanceBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportDistanceBeanDao.delete(this);
    }

    public void refresh() {
        SportDistanceBeanDao sportDistanceBeanDao = this.myDao;
        if (sportDistanceBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportDistanceBeanDao.refresh(this);
    }

    public void update() {
        SportDistanceBeanDao sportDistanceBeanDao = this.myDao;
        if (sportDistanceBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportDistanceBeanDao.update(this);
    }

    public static List<int[]> genedefaultDistanceOffeset() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 24; i++) {
            arrayList.add(new int[]{i, 0});
        }
        return arrayList;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getSportDistanceBeanDao() : null;
    }
}