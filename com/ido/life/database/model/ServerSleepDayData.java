package com.ido.life.database.model;

import com.google.gson.annotations.Expose;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class ServerSleepDayData {
    private int awakeRatio;
    private int awakeSeconds;
    private int breathRate;
    private transient DaoSession daoSession;
    private String date;
    private int deeplyRatio;
    private int deeplySeconds;
    private String deviceName;
    private String endTime;

    @Expose(deserialize = false, serialize = false)
    private int endTimeMinuteOffset;
    private int eyeMovementRatio;
    private int eyeMovementSeconds;
    private Long id;
    private String items;
    private int lightlyRatio;
    private int lightlySeconds;
    private transient ServerSleepDayDataDao myDao;
    private int score;
    private String sourceMac;
    private String startTime;

    @Expose(deserialize = false, serialize = false)
    private int startTimeMinuteOffset;
    private long timestamp;
    private int totalSeconds;
    private int type;

    @Expose(deserialize = false, serialize = false)
    private boolean uploaded;
    private long userId;

    public ServerSleepDayData(Long l, long j, String str, int i, int i2, int i3, String str2, String str3, String str4, String str5, String str6, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j2, int i12, int i13, boolean z) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.date = str;
        this.awakeSeconds = i;
        this.lightlySeconds = i2;
        this.deeplySeconds = i3;
        this.startTime = str2;
        this.endTime = str3;
        this.items = str4;
        this.sourceMac = str5;
        this.deviceName = str6;
        this.totalSeconds = i4;
        this.eyeMovementSeconds = i5;
        this.awakeRatio = i6;
        this.lightlyRatio = i7;
        this.deeplyRatio = i8;
        this.eyeMovementRatio = i9;
        this.score = i10;
        this.breathRate = i11;
        this.timestamp = j2;
        this.startTimeMinuteOffset = i12;
        this.endTimeMinuteOffset = i13;
        this.uploaded = z;
    }

    public ServerSleepDayData() {
        this.userId = -1L;
        this.timestamp = System.currentTimeMillis();
    }

    public ServerSleepDayData(Long l, long j, String str, int i, int i2, int i3, int i4, int i5, String str2, String str3, String str4, String str5, String str6, int i6, int i7, int i8, int i9, int i10, int i11, long j2, int i12, int i13, int i14, boolean z) {
        this.userId = -1L;
        this.id = l;
        this.userId = j;
        this.date = str;
        this.awakeSeconds = i;
        this.lightlySeconds = i2;
        this.deeplySeconds = i3;
        this.eyeMovementSeconds = i4;
        this.totalSeconds = i5;
        this.startTime = str2;
        this.endTime = str3;
        this.items = str4;
        this.sourceMac = str5;
        this.deviceName = str6;
        this.awakeRatio = i6;
        this.lightlyRatio = i7;
        this.deeplyRatio = i8;
        this.eyeMovementRatio = i9;
        this.score = i10;
        this.breathRate = i11;
        this.timestamp = j2;
        this.type = i12;
        this.startTimeMinuteOffset = i13;
        this.endTimeMinuteOffset = i14;
        this.uploaded = z;
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

    public int getAwakeSeconds() {
        return this.awakeSeconds;
    }

    public void setAwakeSeconds(int i) {
        this.awakeSeconds = i;
    }

    public int getLightlySeconds() {
        return this.lightlySeconds;
    }

    public void setLightlySeconds(int i) {
        this.lightlySeconds = i;
    }

    public int getDeeplySeconds() {
        return this.deeplySeconds;
    }

    public void setDeeplySeconds(int i) {
        this.deeplySeconds = i;
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

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    public void setTotalSeconds(int i) {
        this.totalSeconds = i;
    }

    public int getEyeMovementSeconds() {
        return this.eyeMovementSeconds;
    }

    public void setEyeMovementSeconds(int i) {
        this.eyeMovementSeconds = i;
    }

    public int getAwakeRatio() {
        return this.awakeRatio;
    }

    public void setAwakeRatio(int i) {
        this.awakeRatio = i;
    }

    public int getLightlyRatio() {
        return this.lightlyRatio;
    }

    public void setLightlyRatio(int i) {
        this.lightlyRatio = i;
    }

    public int getDeeplyRatio() {
        return this.deeplyRatio;
    }

    public void setDeeplyRatio(int i) {
        this.deeplyRatio = i;
    }

    public int getEyeMovementRatio() {
        return this.eyeMovementRatio;
    }

    public void setEyeMovementRatio(int i) {
        this.eyeMovementRatio = i;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public int getBreathRate() {
        return this.breathRate;
    }

    public void setBreathRate(int i) {
        this.breathRate = i;
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

    public int getStartTimeMinuteOffset() {
        return this.startTimeMinuteOffset;
    }

    public void setStartTimeMinuteOffset(int i) {
        this.startTimeMinuteOffset = i;
    }

    public int getEndTimeMinuteOffset() {
        return this.endTimeMinuteOffset;
    }

    public void setEndTimeMinuteOffset(int i) {
        this.endTimeMinuteOffset = i;
    }

    public String toString() {
        return "ServerSleepDayData{id=" + this.id + ", userId=" + this.userId + ", date='" + this.date + "', awakeSeconds=" + this.awakeSeconds + ", lightlySeconds=" + this.lightlySeconds + ", deeplySeconds=" + this.deeplySeconds + ", startTime='" + this.startTime + "', endTime='" + this.endTime + "', items='" + this.items + "', sourceMac='" + this.sourceMac + "', deviceName='" + this.deviceName + "', totalSeconds=" + this.totalSeconds + ", eyeMovementSeconds=" + this.eyeMovementSeconds + ", awakeRatio=" + this.awakeRatio + ", lightlyRatio=" + this.lightlyRatio + ", deeplyRatio=" + this.deeplyRatio + ", eyeMovementRatio=" + this.eyeMovementRatio + ", score=" + this.score + ", breathRate=" + this.breathRate + ", timestamp=" + this.timestamp + ", startTimeMinuteOffset=" + this.startTimeMinuteOffset + ", endTimeMinuteOffset=" + this.endTimeMinuteOffset + ", uploaded=" + this.uploaded + '}';
    }

    public void delete() {
        ServerSleepDayDataDao serverSleepDayDataDao = this.myDao;
        if (serverSleepDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverSleepDayDataDao.delete(this);
    }

    public void refresh() {
        ServerSleepDayDataDao serverSleepDayDataDao = this.myDao;
        if (serverSleepDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverSleepDayDataDao.refresh(this);
    }

    public void update() {
        ServerSleepDayDataDao serverSleepDayDataDao = this.myDao;
        if (serverSleepDayDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        serverSleepDayDataDao.update(this);
    }

    public int getType() {
        return this.type;
    }

    public boolean supportEyeMovement() {
        return ((this.type & 5) == 0) || getEyeMovementSeconds() > 0;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getServerSleepDayDataDao() : null;
    }
}