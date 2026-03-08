package com.ido.life.database.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class LifeCycleItemBean {

    @SerializedName("deviceName")
    private String DeviceName;
    private Long Id;

    @SerializedName("items")
    private List<List<Integer>> ItemList;

    @SerializedName("mensesCycle")
    private int MensesCycle;

    @SerializedName("mensesDays")
    private int MensesDays;

    @SerializedName("mensesStartDay")
    private int MensesStartDay;

    @SerializedName("month")
    private String Month;
    private boolean NeedSyncToDevice;

    @SerializedName("ovulationDay")
    private int OvulationDay;

    @SerializedName("pregnancyDayBeforeRemind")
    private int PregnancyDayBeforeRemind;

    @SerializedName("reminderTime")
    private String ReminderTime;

    @SerializedName("sourceMac")
    private String SourceMac;

    @SerializedName("timestamp")
    private long TimeStamp;
    private boolean Upload;
    private long UserId;
    private transient DaoSession daoSession;
    private transient LifeCycleItemBeanDao myDao;

    public LifeCycleItemBean(Long l, long j, String str, int i, int i2, String str2, String str3, List<List<Integer>> list, long j2, int i3, int i4, int i5, String str4, boolean z, boolean z2) {
        this.Upload = false;
        this.NeedSyncToDevice = true;
        this.Id = l;
        this.UserId = j;
        this.Month = str;
        this.MensesCycle = i;
        this.MensesDays = i2;
        this.SourceMac = str2;
        this.DeviceName = str3;
        this.ItemList = list;
        this.TimeStamp = j2;
        this.MensesStartDay = i3;
        this.OvulationDay = i4;
        this.PregnancyDayBeforeRemind = i5;
        this.ReminderTime = str4;
        this.Upload = z;
        this.NeedSyncToDevice = z2;
    }

    public LifeCycleItemBean() {
        this.Upload = false;
        this.NeedSyncToDevice = true;
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

    public String getMonth() {
        return this.Month;
    }

    public void setMonth(String str) {
        this.Month = str;
    }

    public int getMensesCycle() {
        return this.MensesCycle;
    }

    public void setMensesCycle(int i) {
        this.MensesCycle = i;
    }

    public int getMensesDays() {
        return this.MensesDays;
    }

    public void setMensesDays(int i) {
        this.MensesDays = i;
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

    public List<List<Integer>> getItemList() {
        return this.ItemList;
    }

    public void setItemList(List<List<Integer>> list) {
        this.ItemList = list;
    }

    public long getTimeStamp() {
        return this.TimeStamp;
    }

    public void setTimeStamp(long j) {
        this.TimeStamp = j;
    }

    public int getMensesStartDay() {
        return this.MensesStartDay;
    }

    public void setMensesStartDay(int i) {
        this.MensesStartDay = i;
    }

    public int getOvulationDay() {
        return this.OvulationDay;
    }

    public void setOvulationDay(int i) {
        this.OvulationDay = i;
    }

    public int getPregnancyDayBeforeRemind() {
        return this.PregnancyDayBeforeRemind;
    }

    public void setPregnancyDayBeforeRemind(int i) {
        this.PregnancyDayBeforeRemind = i;
    }

    public String getReminderTime() {
        return this.ReminderTime;
    }

    public void setReminderTime(String str) {
        this.ReminderTime = str;
    }

    public boolean getUpload() {
        return this.Upload;
    }

    public void setUpload(boolean z) {
        this.Upload = z;
    }

    public void delete() {
        LifeCycleItemBeanDao lifeCycleItemBeanDao = this.myDao;
        if (lifeCycleItemBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        lifeCycleItemBeanDao.delete(this);
    }

    public void refresh() {
        LifeCycleItemBeanDao lifeCycleItemBeanDao = this.myDao;
        if (lifeCycleItemBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        lifeCycleItemBeanDao.refresh(this);
    }

    public void update() {
        LifeCycleItemBeanDao lifeCycleItemBeanDao = this.myDao;
        if (lifeCycleItemBeanDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        lifeCycleItemBeanDao.update(this);
    }

    public boolean getNeedSyncToDevice() {
        return this.NeedSyncToDevice;
    }

    public void setNeedSyncToDevice(boolean z) {
        this.NeedSyncToDevice = z;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getLifeCycleItemBeanDao() : null;
    }
}