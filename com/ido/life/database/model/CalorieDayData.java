package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class CalorieDayData implements Comparable<CalorieDayData>, Cloneable {

    @Expose(deserialize = false)
    private transient int DayAvgActivityCalorie;

    @Expose(deserialize = false)
    private transient int DayAvgCalorie;
    private int activityCalorie;
    private String activityItems;
    private transient DaoSession daoSession;
    private String date;
    private String deviceName;

    @Expose(deserialize = false)
    private Long id;
    private String items;

    @Expose(deserialize = false)
    private boolean loadDetail;
    private transient CalorieDayDataDao myDao;
    private String sourceMac;

    @Expose(deserialize = false)
    private int targetCalorie;
    private long timestamp;
    private int totalCalorie;

    @Expose(deserialize = false, serialize = false)
    private boolean uploaded;

    @Expose(deserialize = false)
    private long userId;

    @Override // java.lang.Comparable
    public int compareTo(CalorieDayData calorieDayData) {
        return 0;
    }

    public CalorieDayData() {
        this.userId = -1L;
        this.loadDetail = true;
        this.timestamp = System.currentTimeMillis();
    }

    public CalorieDayData(Long l, long j, String str, int i, int i2, String str2, String str3, String str4, String str5, long j2, int i3, boolean z, boolean z2) {
        this.userId = -1L;
        this.loadDetail = true;
        this.id = l;
        this.userId = j;
        this.date = str;
        this.totalCalorie = i;
        this.activityCalorie = i2;
        this.sourceMac = str2;
        this.deviceName = str3;
        this.items = str4;
        this.activityItems = str5;
        this.timestamp = j2;
        this.targetCalorie = i3;
        this.loadDetail = z;
        this.uploaded = z2;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getSourceMac() {
        return this.sourceMac;
    }

    public void setSourceMac(String str) {
        this.sourceMac = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
    }

    public int getTotalCalorie() {
        return this.totalCalorie;
    }

    public void setTotalCalorie(int i) {
        this.totalCalorie = i;
    }

    public int getTargetCalorie() {
        return this.targetCalorie;
    }

    public void setTargetCalorie(int i) {
        this.targetCalorie = i;
    }

    public boolean getUploaded() {
        return this.uploaded;
    }

    public void setUploaded(boolean z) {
        this.uploaded = z;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public boolean isUploaded() {
        return this.uploaded;
    }

    public int getActivityCalorie() {
        return this.activityCalorie;
    }

    public void setActivityCalorie(int i) {
        this.activityCalorie = i;
    }

    public String getActivityItems() {
        return this.activityItems;
    }

    public void setActivityItems(String str) {
        this.activityItems = str;
    }

    public void setLoadDetail(boolean z) {
        this.loadDetail = z;
    }

    public boolean getLoadDetail() {
        return this.loadDetail;
    }

    public String toString() {
        return "CalorieDayData{id=" + this.id + ", userId=" + this.userId + ", date='" + this.date + "', sourceMac='" + this.sourceMac + "', deviceName='" + this.deviceName + "', items='" + this.items + "', totalCalorie=" + this.totalCalorie + ", activityCalorie=" + this.activityCalorie + ", activityItems='" + this.activityItems + "', targetCalorie=" + this.targetCalorie + ", timestamp=" + this.timestamp + ", loadDetail=" + this.loadDetail + ", DayAvgCalorie=" + this.DayAvgCalorie + ", DayAvgActivityCalorie=" + this.DayAvgActivityCalorie + ", uploaded=" + this.uploaded + '}';
    }

    public void delete() {
        CalorieDayDataDao calorieDayDataDao = this.myDao;
        if (calorieDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        calorieDayDataDao.delete(this);
    }

    public void refresh() {
        CalorieDayDataDao calorieDayDataDao = this.myDao;
        if (calorieDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        calorieDayDataDao.refresh(this);
    }

    public void update() {
        CalorieDayDataDao calorieDayDataDao = this.myDao;
        if (calorieDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        calorieDayDataDao.update(this);
    }

    public static List<int[]> genedefaultCalorieOffeset() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 24; i++) {
            arrayList.add(new int[]{i, 0});
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public CalorieDayData m24clone() {
        CalorieDayData calorieDayData = new CalorieDayData();
        calorieDayData.setTotalCalorie(this.totalCalorie);
        calorieDayData.setActivityCalorie(this.activityCalorie);
        calorieDayData.setActivityItems(this.activityItems);
        calorieDayData.setDate(this.date);
        calorieDayData.setDeviceName(this.deviceName);
        calorieDayData.setId(this.id);
        calorieDayData.setSourceMac(this.sourceMac);
        calorieDayData.setUserId(this.userId);
        calorieDayData.setTargetCalorie(this.targetCalorie);
        calorieDayData.setItems(this.items);
        calorieDayData.setUploaded(this.uploaded);
        calorieDayData.setLoadDetail(this.loadDetail);
        return calorieDayData;
    }

    public int getDayAvgCalorie() {
        return this.DayAvgCalorie;
    }

    public void setDayAvgCalorie(int i) {
        this.DayAvgCalorie = i;
    }

    public int getDayAvgActivityCalorie() {
        return this.DayAvgActivityCalorie;
    }

    public void setDayAvgActivityCalorie(int i) {
        this.DayAvgActivityCalorie = i;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getCalorieDayDataDao() : null;
    }
}