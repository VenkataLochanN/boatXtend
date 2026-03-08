package com.ido.life.database.model;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.common.utils.TimeUtil;
import com.ido.life.util.DateUtil;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveTimeDayData implements Comparable<ActiveTimeDayData>, Cloneable {
    private transient int DayAvgSecond;
    private transient DaoSession daoSession;
    private String date;
    private String deviceName;

    @Expose(deserialize = false)
    private Long id;
    private String items;
    private boolean loadDetail;
    private int mediumOrHigherSeconds;
    private transient ActiveTimeDayDataDao myDao;
    private String sourceMac;
    private int targetExerciseDuration;
    private int targetTotalSeconds;
    private long timestamp;
    private int totalDistance;
    private int totalSeconds;

    @SerializedName("totalWearDuration")
    private int totalWearDuration;

    @Expose(deserialize = false, serialize = false)
    private boolean uploaded;

    @Expose(deserialize = false)
    private long userId;

    @Expose(deserialize = false, serialize = false)
    private List<Integer> wearDurationList;

    public ActiveTimeDayData() {
        this.userId = -1L;
        this.loadDetail = true;
        this.timestamp = System.currentTimeMillis();
    }

    public ActiveTimeDayData(Long l, long j, String str, String str2, String str3, String str4, int i, int i2, int i3, long j2, int i4, int i5, boolean z, List<Integer> list, int i6, boolean z2) {
        this.userId = -1L;
        this.loadDetail = true;
        this.id = l;
        this.userId = j;
        this.date = str;
        this.sourceMac = str2;
        this.deviceName = str3;
        this.items = str4;
        this.totalDistance = i;
        this.mediumOrHigherSeconds = i2;
        this.targetTotalSeconds = i3;
        this.timestamp = j2;
        this.targetExerciseDuration = i4;
        this.totalSeconds = i5;
        this.loadDetail = z;
        this.wearDurationList = list;
        this.totalWearDuration = i6;
        this.uploaded = z2;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ActiveTimeDayData m23clone() {
        ActiveTimeDayData activeTimeDayData = new ActiveTimeDayData();
        activeTimeDayData.setTotalSeconds(this.totalSeconds);
        activeTimeDayData.setItems(this.items);
        activeTimeDayData.setDate(this.date);
        activeTimeDayData.setDeviceName(this.deviceName);
        activeTimeDayData.setId(this.id);
        activeTimeDayData.setSourceMac(this.sourceMac);
        activeTimeDayData.setUserId(this.userId);
        activeTimeDayData.setItems(this.items);
        activeTimeDayData.setUploaded(this.uploaded);
        activeTimeDayData.setWearDurationList(this.wearDurationList);
        activeTimeDayData.setTotalWearDuration(this.totalWearDuration);
        return activeTimeDayData;
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

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    public void setTotalSeconds(int i) {
        this.totalSeconds = i;
    }

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public int getMediumOrHigherSeconds() {
        return this.mediumOrHigherSeconds;
    }

    public void setMediumOrHigherSeconds(int i) {
        this.mediumOrHigherSeconds = i;
    }

    public int getTargetTotalSeconds() {
        return this.targetTotalSeconds;
    }

    public void setTargetTotalSeconds(int i) {
        this.targetTotalSeconds = i;
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

    public void setUploaded(boolean z) {
        this.uploaded = z;
    }

    public int getDayAvgSecond() {
        return this.DayAvgSecond;
    }

    public void setDayAvgSecond(int i) {
        this.DayAvgSecond = i;
    }

    public boolean getLoadDetail() {
        return this.loadDetail;
    }

    public boolean getUploaded() {
        return this.uploaded;
    }

    public List<Integer> getWearDurationList() {
        return this.wearDurationList;
    }

    public void setWearDurationList(List<Integer> list) {
        this.wearDurationList = list;
    }

    public int getTotalWearDuration() {
        return this.totalWearDuration;
    }

    public void setTotalWearDuration(int i) {
        this.totalWearDuration = i;
    }

    public String toString() {
        return "ActiveTimeDayData{id=" + this.id + ", userId=" + this.userId + ", date='" + this.date + "', sourceMac='" + this.sourceMac + "', deviceName='" + this.deviceName + "', items='" + this.items + "', totalDistance=" + this.totalDistance + ", mediumOrHigherSeconds=" + this.mediumOrHigherSeconds + ", targetTotalSeconds=" + this.targetTotalSeconds + ", timestamp=" + this.timestamp + ", totalSeconds=" + this.totalSeconds + ", loadDetail=" + this.loadDetail + ", DayAvgSecond=" + this.DayAvgSecond + ", mWearDurationList=" + this.wearDurationList + ", mTotalWearDuration=" + this.totalWearDuration + ", uploaded=" + this.uploaded + '}';
    }

    public void delete() {
        ActiveTimeDayDataDao activeTimeDayDataDao = this.myDao;
        if (activeTimeDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        activeTimeDayDataDao.delete(this);
    }

    public void refresh() {
        ActiveTimeDayDataDao activeTimeDayDataDao = this.myDao;
        if (activeTimeDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        activeTimeDayDataDao.refresh(this);
    }

    public void update() {
        ActiveTimeDayDataDao activeTimeDayDataDao = this.myDao;
        if (activeTimeDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        activeTimeDayDataDao.update(this);
    }

    @Override // java.lang.Comparable
    public int compareTo(ActiveTimeDayData activeTimeDayData) {
        if (TextUtils.isEmpty(getDate()) || TextUtils.isEmpty(activeTimeDayData.getDate())) {
            return 0;
        }
        return DateUtil.string2Date(TimeUtil.convTimeDetail(this.timestamp), DateUtil.DATE_FORMAT_YMD).compareTo(DateUtil.string2Date(activeTimeDayData.getDate(), DateUtil.DATE_FORMAT_YMD));
    }

    public void setLoadDetail(boolean z) {
        this.loadDetail = z;
    }

    public int getTargetExerciseDuration() {
        return this.targetExerciseDuration;
    }

    public void setTargetExerciseDuration(int i) {
        this.targetExerciseDuration = i;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getActiveTimeDayDataDao() : null;
    }
}