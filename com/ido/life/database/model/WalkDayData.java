package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class WalkDayData implements Cloneable {
    private transient DaoSession daoSession;
    private String date;
    private String deviceName;
    private String endTime;
    private Long id;
    private String items;
    private transient WalkDayDataDao myDao;
    private int reachSeconds;

    @SerializedName("totalSedentaryDuration")
    private int sedentaryDuration;
    private String sourceMac;
    private String startTime;
    private int targetSteps;
    private int targetWalkDuration;
    private long timestamp;
    private boolean uploaded;
    private long userId;

    @Expose(deserialize = false, serialize = false)
    private List<Integer> wearDurationList;

    public WalkDayData() {
        this.userId = -1L;
        this.timestamp = System.currentTimeMillis();
    }

    public WalkDayData(Long l, long j, String str, int i, int i2, String str2, String str3, String str4, String str5, String str6, List<Integer> list, int i3, int i4, long j2, boolean z) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.date = str;
        this.reachSeconds = i;
        this.targetSteps = i2;
        this.startTime = str2;
        this.endTime = str3;
        this.items = str4;
        this.sourceMac = str5;
        this.deviceName = str6;
        this.wearDurationList = list;
        this.sedentaryDuration = i3;
        this.targetWalkDuration = i4;
        this.timestamp = j2;
        this.uploaded = z;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public WalkDayData m29clone() {
        WalkDayData walkDayData = new WalkDayData();
        walkDayData.id = this.id;
        walkDayData.userId = this.userId;
        walkDayData.reachSeconds = this.reachSeconds;
        walkDayData.targetSteps = this.targetSteps;
        walkDayData.startTime = this.startTime;
        walkDayData.endTime = this.endTime;
        walkDayData.items = this.items;
        walkDayData.sourceMac = this.sourceMac;
        walkDayData.deviceName = this.deviceName;
        walkDayData.wearDurationList = this.wearDurationList;
        walkDayData.sedentaryDuration = this.sedentaryDuration;
        walkDayData.timestamp = this.timestamp;
        walkDayData.uploaded = true;
        return walkDayData;
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

    public int getReachSeconds() {
        return this.reachSeconds;
    }

    public void setReachSeconds(int i) {
        this.reachSeconds = i;
    }

    public int getTargetSteps() {
        return this.targetSteps;
    }

    public void setTargetSteps(int i) {
        this.targetSteps = i;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
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

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public boolean getUploaded() {
        return this.uploaded;
    }

    public void setUploaded(boolean z) {
        this.uploaded = z;
    }

    public boolean isUploaded() {
        return this.uploaded;
    }

    public List<Integer> getWearDurationList() {
        return this.wearDurationList;
    }

    public void setWearDurationList(List<Integer> list) {
        this.wearDurationList = list;
    }

    public int getSedentaryDuration() {
        return this.sedentaryDuration;
    }

    public void setSedentaryDuration(int i) {
        this.sedentaryDuration = i;
    }

    public void delete() {
        WalkDayDataDao walkDayDataDao = this.myDao;
        if (walkDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkDayDataDao.delete(this);
    }

    public void refresh() {
        WalkDayDataDao walkDayDataDao = this.myDao;
        if (walkDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkDayDataDao.refresh(this);
    }

    public void update() {
        WalkDayDataDao walkDayDataDao = this.myDao;
        if (walkDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        walkDayDataDao.update(this);
    }

    public int getTargetWalkDuration() {
        return this.targetWalkDuration;
    }

    public void setTargetWalkDuration(int i) {
        this.targetWalkDuration = i;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getWalkDayDataDao() : null;
    }
}