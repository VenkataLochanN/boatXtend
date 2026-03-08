package com.ido.life.database.model;

import com.google.android.gms.fitness.data.Field;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.Locale;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class UserTargetNew implements Cloneable {

    @SerializedName("exerciseDurationTimes")
    private int ActivityTime;

    @SerializedName(Field.NUTRIENT_CALORIES)
    private int Calories;

    @Expose(deserialize = false, serialize = false)
    private long CreateTime;

    @SerializedName("date")
    private String Date;

    @SerializedName("distances")
    private int Distance;

    @Expose(deserialize = false, serialize = false)
    private boolean HasSyncToDevice;

    @Expose(deserialize = false, serialize = false)
    private boolean HasUpload;

    @Expose(deserialize = false, serialize = false)
    private Long Id;

    @SerializedName("sleepTimes")
    private int SleepTimes;

    @SerializedName("sportSteps")
    private int SportStep;

    @SerializedName("sportTimes")
    private int SportTimes;

    @SerializedName("numSteps")
    private int Step;

    @SerializedName("timestamp")
    private long UpdateTime;

    @Expose(deserialize = false, serialize = false)
    private long UserId;

    @SerializedName("walkTimes")
    private int Walk;

    @SerializedName("weight")
    private float Weight;

    @Expose(deserialize = false, serialize = false)
    private int WeightUnit;
    private transient DaoSession daoSession;
    private transient UserTargetNewDao myDao;

    public UserTargetNew() {
        this.Step = 10000;
        this.SportStep = 100;
        this.Distance = 5000;
        this.Calories = 500;
        this.Weight = 50.0f;
        this.ActivityTime = 1800;
        this.Walk = 43200;
        this.WeightUnit = 1;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.UpdateTime = jCurrentTimeMillis;
        this.CreateTime = jCurrentTimeMillis;
        this.Date = DateUtil.format(Calendar.getInstance(Locale.CHINA), DateUtil.DATE_FORMAT_YMD);
    }

    public UserTargetNew(Long l, long j, String str, int i, int i2, int i3, int i4, int i5, int i6, float f2, int i7, int i8, long j2, int i9, boolean z, boolean z2, long j3) {
        this.Step = 10000;
        this.SportStep = 100;
        this.Distance = 5000;
        this.Calories = 500;
        this.Weight = 50.0f;
        this.ActivityTime = 1800;
        this.Walk = 43200;
        this.WeightUnit = 1;
        this.Id = l;
        this.UserId = j;
        this.Date = str;
        this.Step = i;
        this.SportStep = i2;
        this.Distance = i3;
        this.Calories = i4;
        this.SleepTimes = i5;
        this.SportTimes = i6;
        this.Weight = f2;
        this.ActivityTime = i7;
        this.Walk = i8;
        this.UpdateTime = j2;
        this.WeightUnit = i9;
        this.HasSyncToDevice = z;
        this.HasUpload = z2;
        this.CreateTime = j3;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public UserTargetNew m28clone() {
        UserTargetNew userTargetNewGenerateDefaultUserTargetNew = RunTimeUtil.generateDefaultUserTargetNew(this.UserId);
        userTargetNewGenerateDefaultUserTargetNew.Date = this.Date;
        userTargetNewGenerateDefaultUserTargetNew.Step = this.Step;
        userTargetNewGenerateDefaultUserTargetNew.SportStep = this.SportStep;
        userTargetNewGenerateDefaultUserTargetNew.Distance = this.Distance;
        userTargetNewGenerateDefaultUserTargetNew.Calories = this.Calories;
        userTargetNewGenerateDefaultUserTargetNew.SleepTimes = this.SleepTimes;
        userTargetNewGenerateDefaultUserTargetNew.SportTimes = this.SportTimes;
        userTargetNewGenerateDefaultUserTargetNew.Weight = this.Weight;
        userTargetNewGenerateDefaultUserTargetNew.ActivityTime = this.ActivityTime;
        userTargetNewGenerateDefaultUserTargetNew.Walk = this.Walk;
        userTargetNewGenerateDefaultUserTargetNew.UpdateTime = this.UpdateTime;
        userTargetNewGenerateDefaultUserTargetNew.WeightUnit = this.WeightUnit;
        userTargetNewGenerateDefaultUserTargetNew.HasSyncToDevice = this.HasSyncToDevice;
        userTargetNewGenerateDefaultUserTargetNew.HasUpload = this.HasUpload;
        userTargetNewGenerateDefaultUserTargetNew.CreateTime = this.CreateTime;
        return userTargetNewGenerateDefaultUserTargetNew;
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

    public int getStep() {
        return this.Step;
    }

    public void setStep(int i) {
        this.Step = i;
    }

    public int getSportStep() {
        return this.SportStep;
    }

    public void setSportStep(int i) {
        this.SportStep = i;
    }

    public float getWeight() {
        return this.Weight;
    }

    public int getWeightInt() {
        return Math.round(this.Weight);
    }

    public void setWeight(float f2) {
        this.Weight = f2;
    }

    public boolean getHasUpload() {
        return this.HasUpload;
    }

    public void setHasUpload(boolean z) {
        this.HasUpload = z;
    }

    public long getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(long j) {
        this.CreateTime = j;
    }

    public long getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(int i) {
        this.UpdateTime = i;
    }

    public int getWeightUnit() {
        return this.WeightUnit;
    }

    public void setWeightUnit(int i) {
        this.WeightUnit = i;
    }

    public boolean isHasUpload() {
        return this.HasUpload;
    }

    public void setUpdateTime(long j) {
        this.UpdateTime = j;
    }

    public boolean isHasSyncToDevice() {
        return this.HasSyncToDevice;
    }

    public boolean getHasSyncToDevice() {
        return this.HasSyncToDevice;
    }

    public void setHasSyncToDevice(boolean z) {
        this.HasSyncToDevice = z;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String str) {
        this.Date = str;
    }

    public int getDistance() {
        return this.Distance;
    }

    public void setDistance(int i) {
        this.Distance = i;
    }

    public int getCalories() {
        return this.Calories;
    }

    public void setCalories(int i) {
        this.Calories = i;
    }

    public int getSleepTimes() {
        return this.SleepTimes;
    }

    public void setSleepTimes(int i) {
        this.SleepTimes = i;
    }

    public int getSportTimes() {
        return this.SportTimes;
    }

    public void setSportTimes(int i) {
        this.SportTimes = i;
    }

    public int getActivityTime() {
        return this.ActivityTime;
    }

    public void setActivityTime(int i) {
        this.ActivityTime = i;
    }

    public int getWalk() {
        return this.Walk;
    }

    public void setWalk(int i) {
        this.Walk = i;
    }

    public void delete() {
        UserTargetNewDao userTargetNewDao = this.myDao;
        if (userTargetNewDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userTargetNewDao.delete(this);
    }

    public void refresh() {
        UserTargetNewDao userTargetNewDao = this.myDao;
        if (userTargetNewDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userTargetNewDao.refresh(this);
    }

    public void update() {
        UserTargetNewDao userTargetNewDao = this.myDao;
        if (userTargetNewDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        userTargetNewDao.update(this);
    }

    public String toString() {
        return "UserTargetNew{Id=" + this.Id + ", UserId=" + this.UserId + ", Date='" + this.Date + "', Step=" + this.Step + ", Distance=" + this.Distance + ", Calories=" + this.Calories + ", SleepTimes=" + this.SleepTimes + ", SportTimes=" + this.SportTimes + ", Weight=" + this.Weight + ", ActivityTime=" + this.ActivityTime + ", Walk=" + this.Walk + ", UpdateTime=" + this.UpdateTime + ", WeightUnit=" + this.WeightUnit + ", HasSyncToDevice=" + this.HasSyncToDevice + ", HasUpload=" + this.HasUpload + ", CreateTime=" + this.CreateTime + '}';
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getUserTargetNewDao() : null;
    }
}