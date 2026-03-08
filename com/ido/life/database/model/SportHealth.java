package com.ido.life.database.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.boat.Xtend.two.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.common.utils.ResourceUtil;
import java.io.Serializable;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class SportHealth implements Serializable, Parcelable {
    public static final Parcelable.Creator<SportHealth> CREATOR = new Parcelable.Creator<SportHealth>() { // from class: com.ido.life.database.model.SportHealth.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SportHealth createFromParcel(Parcel parcel) {
            return new SportHealth(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SportHealth[] newArray(int i) {
            return new SportHealth[i];
        }
    };
    public static final int DATA_FROM_APP = 1;
    public static final int DATA_FROM_APP_AND_DEVICE = 3;
    public static final int DATA_FROM_DEVICE = 2;
    public static final int DATA_FROM_RECORD = 1;
    public static final int DATA_FROM_RUN = 0;
    public static final int DATA_GPS_SOURCE_TYPE_APP = 1;
    public static final int DATA_GPS_SOURCE_TYPE_DEVICE = 2;
    public static final int DATA_GPS_SOURCE_TYPE_NO = 0;
    public static final int DATA_IS_LOCUS = 1;
    public static final int DATA_IS_N0_LOCUS = 0;
    public static final int DATA_SOURCE_OS_ANDROID = 2;
    public static final int DATA_SOURCE_OS_IOS = 1;
    public static final long serialVersionUID = 1;

    @Expose(deserialize = false, serialize = false)
    private Long activityId;
    private int aerobicSeconds;

    @SerializedName("anaerobicSeconds")
    private int anaerobicSecond;

    @SerializedName("avgSwolfValue")
    private int averageSWOLF;

    @Expose(deserialize = false, serialize = false)
    private int avgFrequency;
    private int avgHrValue;
    private int avgPace;
    private int avgSpeed;

    @SerializedName("minSwolfValue")
    private int bestSWOLF;
    private int burnFatSeconds;
    private int cumulativeClimb;
    private int cumulativeDecline;
    private transient DaoSession daoSession;

    @SerializedName("datetime")
    private String dateTime;
    private String deviceName;

    @SerializedName("recoveDatetime")
    private String discoverDateTime;
    private int distance;
    private String endTime;

    @SerializedName("extremeSeconds")
    private int extremeSecond;

    @Expose(deserialize = false, serialize = false)
    private int fast_km_speed;
    private SportGps gps;
    private int gpsSourceType;
    private SportItem heartrate;

    @Expose(deserialize = false, serialize = false)
    private int hrDataIntervalMinute;

    @Expose(deserialize = false, serialize = false)
    private String hr_data_vlaue_json;
    private String icon;

    @Expose(deserialize = false, serialize = false)
    private int intervalSecond;
    private boolean isLoadDetail;
    private int isLocus;
    private int isSupportTrainingEffect;

    @Expose(deserialize = false, serialize = false)
    private boolean isUploaded;

    @Expose(deserialize = false, serialize = false)
    @Deprecated
    private boolean isUploadedStrava;

    @Expose(deserialize = false, serialize = false)
    private int maxFrequency;
    private int maxHrValue;
    private int maxPace;
    private int maxSpeed;

    @SerializedName("maxSwolfValue")
    private int maxSwolf;
    private int minHrValue;
    private int minPace;
    private int minRate;
    private int minSpeed;
    private transient SportHealthDao myDao;
    private int numCalories;
    private int numSteps;
    private SportItemPace pace;

    @SerializedName("swPoolLength")
    private int poolDistance;
    private SportItem range;

    @Expose(deserialize = false, serialize = false)
    private String rangeItem;
    private SportItem rate;
    private SportRealTimePace realTimePace;
    private int recoverTime;
    private String sid;
    private String sourceMac;
    private int sourceOs;
    private int sourceType;
    private String startTime;

    @Expose(deserialize = false, serialize = false)
    private String stepItem;
    private int stepRange;

    @Expose(deserialize = false, serialize = false)
    private int stepRangeMax;

    @SerializedName("avgRate")
    private int stepRate;

    @SerializedName("maxRate")
    private int stepRateMax;
    private int subType;

    @Expose(deserialize = false, serialize = false)
    private String swimmingDetailStr;

    @SerializedName("swType")
    private int swimmingPosture;
    private SportSwimSwolf swolf;
    private int targetType;
    private int targetValue;
    private long timestamp;
    private int totalSeconds;

    @SerializedName("swHitNums")
    private int totalStrokesNumber;
    private float trainingEffectScore;

    @SerializedName("swTrips")
    private int trips;

    @SerializedName("type")
    private int type;
    private int uploadedStrava;

    @Expose(deserialize = false, serialize = false)
    private long userId;

    @SerializedName("maximalOxygenUptake")
    private int vo2max;
    private int warmupSeconds;

    public static int getDataFromApp() {
        return 1;
    }

    public static int getDataFromDevice() {
        return 2;
    }

    public static long getSerialVersionUID() {
        return 1L;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SportHealth(String str) {
        this.sourceMac = str;
    }

    protected SportHealth(Parcel parcel) {
        this.sid = parcel.readString();
        this.isUploaded = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            this.activityId = null;
        } else {
            this.activityId = Long.valueOf(parcel.readLong());
        }
        if (parcel.readByte() == 0) {
            this.userId = -1L;
        } else {
            this.userId = parcel.readLong();
        }
        this.dateTime = parcel.readString();
        this.type = parcel.readInt();
        this.totalSeconds = parcel.readInt();
        this.numCalories = parcel.readInt();
        this.distance = parcel.readInt();
        this.numSteps = parcel.readInt();
        this.startTime = parcel.readString();
        this.endTime = parcel.readString();
        this.maxHrValue = parcel.readInt();
        this.warmupSeconds = parcel.readInt();
        this.burnFatSeconds = parcel.readInt();
        this.aerobicSeconds = parcel.readInt();
        this.extremeSecond = parcel.readInt();
        this.sourceMac = parcel.readString();
        this.avgHrValue = parcel.readInt();
        this.minHrValue = parcel.readInt();
        this.anaerobicSecond = parcel.readInt();
        this.isLocus = parcel.readInt();
        this.maxSpeed = parcel.readInt();
        this.minSpeed = parcel.readInt();
        this.avgSpeed = parcel.readInt();
        this.stepRate = parcel.readInt();
        this.stepRateMax = parcel.readInt();
        this.stepRange = parcel.readInt();
        this.stepRangeMax = parcel.readInt();
        this.sourceType = parcel.readInt();
        this.avgPace = parcel.readInt();
        this.maxPace = parcel.readInt();
        this.uploadedStrava = parcel.readInt();
        this.hr_data_vlaue_json = parcel.readString();
        this.stepItem = parcel.readString();
        this.hrDataIntervalMinute = parcel.readInt();
        this.fast_km_speed = parcel.readInt();
        this.averageSWOLF = parcel.readInt();
        this.bestSWOLF = parcel.readInt();
        this.maxSwolf = parcel.readInt();
        this.avgFrequency = parcel.readInt();
        this.maxFrequency = parcel.readInt();
        this.swimmingPosture = parcel.readInt();
        this.trips = parcel.readInt();
        this.totalStrokesNumber = parcel.readInt();
        this.poolDistance = parcel.readInt();
        this.swimmingDetailStr = parcel.readString();
        this.targetType = parcel.readInt();
        this.targetValue = parcel.readInt();
        this.deviceName = parcel.readString();
        this.minPace = parcel.readInt();
        this.timestamp = parcel.readLong();
        this.intervalSecond = parcel.readInt();
        this.subType = parcel.readInt();
        this.minRate = parcel.readInt();
        this.sourceOs = parcel.readInt();
        this.gpsSourceType = parcel.readInt();
        this.icon = parcel.readString();
        this.isLoadDetail = parcel.readByte() != 0;
        this.vo2max = parcel.readInt();
        this.recoverTime = parcel.readInt();
        this.trainingEffectScore = parcel.readFloat();
        this.discoverDateTime = parcel.readString();
        this.isSupportTrainingEffect = parcel.readInt();
        this.cumulativeDecline = parcel.readInt();
        this.cumulativeClimb = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.sid);
        parcel.writeByte(this.isUploaded ? (byte) 1 : (byte) 0);
        if (this.activityId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.activityId.longValue());
        }
        if (this.userId == 0) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.userId);
        }
        parcel.writeString(this.dateTime);
        parcel.writeInt(this.type);
        parcel.writeInt(this.totalSeconds);
        parcel.writeInt(this.numCalories);
        parcel.writeInt(this.distance);
        parcel.writeInt(this.numSteps);
        parcel.writeString(this.startTime);
        parcel.writeString(this.endTime);
        parcel.writeInt(this.maxHrValue);
        parcel.writeInt(this.warmupSeconds);
        parcel.writeInt(this.burnFatSeconds);
        parcel.writeInt(this.aerobicSeconds);
        parcel.writeInt(this.extremeSecond);
        parcel.writeString(this.sourceMac);
        parcel.writeInt(this.avgHrValue);
        parcel.writeInt(this.minHrValue);
        parcel.writeInt(this.anaerobicSecond);
        parcel.writeInt(this.isLocus);
        parcel.writeInt(this.maxSpeed);
        parcel.writeInt(this.minSpeed);
        parcel.writeInt(this.avgSpeed);
        parcel.writeInt(this.stepRate);
        parcel.writeInt(this.stepRateMax);
        parcel.writeInt(this.stepRange);
        parcel.writeInt(this.stepRangeMax);
        parcel.writeInt(this.sourceType);
        parcel.writeInt(this.avgPace);
        parcel.writeInt(this.maxPace);
        parcel.writeInt(this.uploadedStrava);
        parcel.writeString(this.hr_data_vlaue_json);
        parcel.writeString(this.stepItem);
        parcel.writeInt(this.hrDataIntervalMinute);
        parcel.writeInt(this.fast_km_speed);
        parcel.writeInt(this.averageSWOLF);
        parcel.writeInt(this.bestSWOLF);
        parcel.writeInt(this.maxSwolf);
        parcel.writeInt(this.avgFrequency);
        parcel.writeInt(this.maxFrequency);
        parcel.writeInt(this.swimmingPosture);
        parcel.writeInt(this.trips);
        parcel.writeInt(this.totalStrokesNumber);
        parcel.writeInt(this.poolDistance);
        parcel.writeString(this.swimmingDetailStr);
        parcel.writeLong(this.timestamp);
        parcel.writeInt(this.sourceOs);
        parcel.writeInt(this.gpsSourceType);
        parcel.writeString(this.icon);
        parcel.writeInt(this.vo2max);
        parcel.writeInt(this.recoverTime);
        parcel.writeFloat(this.trainingEffectScore);
        parcel.writeString(this.discoverDateTime);
        parcel.writeInt(this.isSupportTrainingEffect);
        parcel.writeInt(this.cumulativeDecline);
        parcel.writeInt(this.cumulativeClimb);
    }

    public SportHealth() {
    }

    public SportHealth(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, String str3, String str4, int i7, int i8, int i9, int i10, int i11, int i12, int i13, String str5, String str6, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, int i32, int i33, int i34, int i35, int i36, SportGps sportGps, SportItem sportItem, SportItem sportItem2, SportItem sportItem3, SportItemPace sportItemPace, SportRealTimePace sportRealTimePace, SportSwimSwolf sportSwimSwolf, int i37, boolean z, int i38, boolean z2, Long l, long j, String str7, String str8, String str9, int i39, int i40, int i41, int i42, String str10, int i43, long j2, int i44, String str11, boolean z3, int i45, int i46, String str12, float f2, int i47, int i48, int i49) {
        this.sid = str;
        this.dateTime = str2;
        this.type = i;
        this.subType = i2;
        this.totalSeconds = i3;
        this.numCalories = i4;
        this.numSteps = i5;
        this.distance = i6;
        this.startTime = str3;
        this.endTime = str4;
        this.targetType = i7;
        this.targetValue = i8;
        this.warmupSeconds = i9;
        this.burnFatSeconds = i10;
        this.aerobicSeconds = i11;
        this.anaerobicSecond = i12;
        this.extremeSecond = i13;
        this.sourceMac = str5;
        this.deviceName = str6;
        this.sourceType = i14;
        this.minHrValue = i15;
        this.maxHrValue = i16;
        this.avgHrValue = i17;
        this.minSpeed = i18;
        this.maxSpeed = i19;
        this.avgSpeed = i20;
        this.minPace = i21;
        this.maxPace = i22;
        this.avgPace = i23;
        this.isLocus = i24;
        this.stepRange = i25;
        this.minRate = i26;
        this.stepRateMax = i27;
        this.stepRate = i28;
        this.swimmingPosture = i29;
        this.totalStrokesNumber = i30;
        this.poolDistance = i31;
        this.trips = i32;
        this.bestSWOLF = i33;
        this.maxSwolf = i34;
        this.averageSWOLF = i35;
        this.sourceOs = i36;
        this.gps = sportGps;
        this.heartrate = sportItem;
        this.rate = sportItem2;
        this.range = sportItem3;
        this.pace = sportItemPace;
        this.realTimePace = sportRealTimePace;
        this.swolf = sportSwimSwolf;
        this.stepRangeMax = i37;
        this.isUploadedStrava = z;
        this.uploadedStrava = i38;
        this.isUploaded = z2;
        this.activityId = l;
        this.userId = j;
        this.hr_data_vlaue_json = str7;
        this.stepItem = str8;
        this.rangeItem = str9;
        this.hrDataIntervalMinute = i39;
        this.fast_km_speed = i40;
        this.avgFrequency = i41;
        this.maxFrequency = i42;
        this.swimmingDetailStr = str10;
        this.intervalSecond = i43;
        this.timestamp = j2;
        this.gpsSourceType = i44;
        this.icon = str11;
        this.isLoadDetail = z3;
        this.vo2max = i45;
        this.recoverTime = i46;
        this.discoverDateTime = str12;
        this.trainingEffectScore = f2;
        this.isSupportTrainingEffect = i47;
        this.cumulativeClimb = i48;
        this.cumulativeDecline = i49;
    }

    public int getMinRate() {
        return this.minRate;
    }

    public void setMinRate(int i) {
        this.minRate = i;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String str) {
        this.sid = str;
    }

    public boolean isUploaded() {
        return this.isUploaded;
    }

    public void setUploaded(boolean z) {
        this.isUploaded = z;
    }

    public Long getActivityId() {
        return this.activityId;
    }

    public void setActivityId(Long l) {
        this.activityId = l;
    }

    public Long getUserId() {
        return Long.valueOf(this.userId);
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String str) {
        this.dateTime = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    public void setTotalSeconds(int i) {
        this.totalSeconds = i;
    }

    public int getNumCalories() {
        return this.numCalories;
    }

    public void setNumCalories(int i) {
        this.numCalories = i;
    }

    public int getNumSteps() {
        return this.numSteps;
    }

    public void setNumSteps(int i) {
        this.numSteps = i;
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

    public int getMaxHrValue() {
        return this.maxHrValue;
    }

    public void setMaxHrValue(int i) {
        this.maxHrValue = i;
    }

    public int getWarmupSeconds() {
        return this.warmupSeconds;
    }

    public void setWarmupSeconds(int i) {
        this.warmupSeconds = i;
    }

    public int getBurnFatSeconds() {
        return this.burnFatSeconds;
    }

    public void setBurnFatSeconds(int i) {
        this.burnFatSeconds = i;
    }

    public int getAerobicSeconds() {
        return this.aerobicSeconds;
    }

    public void setAerobicSeconds(int i) {
        this.aerobicSeconds = i;
    }

    public int getExtremeSecond() {
        return this.extremeSecond;
    }

    public void setExtremeSecond(int i) {
        this.extremeSecond = i;
    }

    public String getSourceMac() {
        return this.sourceMac;
    }

    public void setSourceMac(String str) {
        this.sourceMac = str;
    }

    public int getAvgHrValue() {
        return this.avgHrValue;
    }

    public void setAvgHrValue(int i) {
        this.avgHrValue = i;
    }

    public int getMinHrValue() {
        return this.minHrValue;
    }

    public void setMinHrValue(int i) {
        this.minHrValue = i;
    }

    public int getAnaerobicSecond() {
        return this.anaerobicSecond;
    }

    public void setAnaerobicSecond(int i) {
        this.anaerobicSecond = i;
    }

    public int getIsLocus() {
        return this.isLocus;
    }

    public void setIsLocus(int i) {
        this.isLocus = i;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int i) {
        this.maxSpeed = i;
    }

    public int getMinSpeed() {
        return this.minSpeed;
    }

    public void setMinSpeed(int i) {
        this.minSpeed = i;
    }

    public int getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(int i) {
        this.avgSpeed = i;
    }

    public int getStepRate() {
        return this.stepRate;
    }

    public void setStepRate(int i) {
        this.stepRate = i;
    }

    public int getStepRange() {
        return this.stepRange;
    }

    public void setStepRange(int i) {
        this.stepRange = i;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public int getAvgPace() {
        return this.avgPace;
    }

    public void setAvgPace(int i) {
        this.avgPace = i;
    }

    public int getMaxPace() {
        return this.maxPace;
    }

    public void setMaxPace(int i) {
        this.maxPace = i;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public String getHr_data_vlaue_json() {
        return this.hr_data_vlaue_json;
    }

    public void setHr_data_vlaue_json(String str) {
        this.hr_data_vlaue_json = str;
        SportItem sportItem = new SportItem(str);
        int i = this.intervalSecond;
        if (i == 0) {
            sportItem.setInterval(5);
        } else {
            sportItem.setInterval(i);
        }
        this.heartrate = sportItem;
    }

    public int getAverageSWOLF() {
        return this.averageSWOLF;
    }

    public void setAverageSWOLF(int i) {
        this.averageSWOLF = i;
    }

    public int getBestSWOLF() {
        return this.bestSWOLF;
    }

    public void setBestSWOLF(int i) {
        this.bestSWOLF = i;
    }

    public int getMaxSwolf() {
        return this.maxSwolf;
    }

    public void setmaxSwolf(int i) {
        this.maxSwolf = i;
    }

    public int getAvgFrequency() {
        return this.avgFrequency;
    }

    public void setAvgFrequency(int i) {
        this.avgFrequency = i;
    }

    public int getMaxFrequency() {
        return this.maxFrequency;
    }

    public void setMaxFrequency(int i) {
        this.maxFrequency = i;
    }

    public int getSubType() {
        return this.subType;
    }

    public void setSubType(int i) {
        this.subType = i;
    }

    public int getHrDataIntervalMinute() {
        return this.hrDataIntervalMinute;
    }

    public void setHrDataIntervalMinute(int i) {
        this.hrDataIntervalMinute = i;
    }

    public int getIntervalSecond() {
        return this.intervalSecond;
    }

    public void setIntervalSecond(int i) {
        this.intervalSecond = i;
    }

    public int getSwimmingPosture() {
        return this.swimmingPosture;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public int getMinPeace() {
        return this.minPace;
    }

    public void setMinPeace(int i) {
        this.minPace = i;
    }

    public int getTargetType() {
        return this.targetType;
    }

    public void setTargetType(int i) {
        this.targetType = i;
    }

    public String getRangeItem() {
        return this.rangeItem;
    }

    public void setRangeItem(String str) {
        this.rangeItem = str;
        this.range = new SportItem(str);
    }

    public int getTargetValue() {
        return this.targetValue;
    }

    public void setTargetValue(int i) {
        this.targetValue = i;
    }

    public int getGpsSourceType() {
        return this.gpsSourceType;
    }

    public void setGpsSourceType(int i) {
        this.gpsSourceType = i;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public boolean isLoadDetail() {
        return this.isLoadDetail;
    }

    public void setLoadDetail(boolean z) {
        this.isLoadDetail = z;
    }

    public String getSwimmingPostureName() {
        int i = this.swimmingPosture;
        if (i == 1) {
            return ResourceUtil.getString(R.string.swim_free);
        }
        if (i == 2) {
            return ResourceUtil.getString(R.string.swim_breaststroke);
        }
        if (i == 4) {
            return ResourceUtil.getString(R.string.swim_other);
        }
        return ResourceUtil.getString(R.string.swim_medley);
    }

    public void setSwimmingPosture(int i) {
        this.swimmingPosture = i;
    }

    public int getTrips() {
        return this.trips;
    }

    public void setTrips(int i) {
        this.trips = i;
    }

    public int getTotalStrokesNumber() {
        return this.totalStrokesNumber;
    }

    public void setTotalStrokesNumber(int i) {
        this.totalStrokesNumber = i;
    }

    public int getPoolDistance() {
        return this.poolDistance;
    }

    public void setPoolDistance(int i) {
        this.poolDistance = i;
    }

    public String getSwimmingDetailStr() {
        return this.swimmingDetailStr;
    }

    public void setSwimmingDetailStr(String str) {
        this.swimmingDetailStr = str;
    }

    public boolean isUploadedStrava() {
        return this.isUploadedStrava;
    }

    public void setUploadedStrava(boolean z) {
        this.isUploadedStrava = z;
    }

    public int getStepRateMax() {
        return this.stepRateMax;
    }

    public void setStepRateMax(int i) {
        this.stepRateMax = i;
    }

    public int getStepRangeMax() {
        return this.stepRangeMax;
    }

    public void setStepRangeMax(int i) {
        this.stepRangeMax = i;
    }

    public String getStepItem() {
        return this.stepItem;
    }

    public void setStepItem(String str) {
        this.stepItem = str;
        this.rate = new SportItem(str);
    }

    public boolean getIsUploaded() {
        return this.isUploaded;
    }

    public void setIsUploaded(boolean z) {
        this.isUploaded = z;
    }

    public void setMaxSwolf(int i) {
        this.maxSwolf = i;
    }

    public int getFast_km_speed() {
        return this.fast_km_speed;
    }

    public void setFast_km_speed(int i) {
        this.fast_km_speed = i;
    }

    public SportItem getHeartrate() {
        return this.heartrate;
    }

    public void setHeartrate(SportItem sportItem) {
        this.heartrate = sportItem;
        if (sportItem != null) {
            this.hr_data_vlaue_json = sportItem.getItmes();
        }
    }

    public SportItem getRate() {
        return this.rate;
    }

    public void setRate(SportItem sportItem) {
        this.rate = sportItem;
        if (sportItem != null) {
            this.stepItem = sportItem.getItmes();
        }
    }

    public SportItem getRange() {
        return this.range;
    }

    public void setRange(SportItem sportItem) {
        this.range = sportItem;
        if (sportItem != null) {
            this.rangeItem = sportItem.getItmes();
        }
    }

    public SportItemPace getPace() {
        return this.pace;
    }

    public void setPace(SportItemPace sportItemPace) {
        this.pace = sportItemPace;
    }

    public SportSwimSwolf getSwolf() {
        return this.swolf;
    }

    public void setSwolf(SportSwimSwolf sportSwimSwolf) {
        this.swolf = sportSwimSwolf;
    }

    public int getSourceOs() {
        return this.sourceOs;
    }

    public void setSourceOs(int i) {
        this.sourceOs = i;
    }

    public void delete() {
        SportHealthDao sportHealthDao = this.myDao;
        if (sportHealthDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportHealthDao.delete(this);
    }

    public void refresh() {
        SportHealthDao sportHealthDao = this.myDao;
        if (sportHealthDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportHealthDao.refresh(this);
    }

    public void update() {
        SportHealthDao sportHealthDao = this.myDao;
        if (sportHealthDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportHealthDao.update(this);
    }

    public SportGps getGps() {
        return this.gps;
    }

    public void setGps(SportGps sportGps) {
        this.gps = sportGps;
    }

    public int getMinPace() {
        return this.minPace;
    }

    public void setMinPace(int i) {
        this.minPace = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public boolean getIsLoadDetail() {
        return this.isLoadDetail;
    }

    public void setIsLoadDetail(boolean z) {
        this.isLoadDetail = z;
    }

    public int getVo2max() {
        return this.vo2max;
    }

    public void setVo2max(int i) {
        this.vo2max = i;
    }

    public int getRecoverTime() {
        return this.recoverTime;
    }

    public void setRecoverTime(int i) {
        this.recoverTime = i;
    }

    public String getDiscoverDateTime() {
        return this.discoverDateTime;
    }

    public void setDiscoverDateTime(String str) {
        this.discoverDateTime = str;
    }

    public float getTrainingEffectScore() {
        return this.trainingEffectScore;
    }

    public void setTrainingEffectScore(float f2) {
        this.trainingEffectScore = f2;
    }

    public int getUploadedStrava() {
        return this.uploadedStrava;
    }

    public void setUploadedStrava(int i) {
        this.uploadedStrava = i;
        this.isUploadedStrava = i != 0;
    }

    @Deprecated
    public boolean getIsUploadedStrava() {
        return this.isUploadedStrava;
    }

    @Deprecated
    public void setIsUploadedStrava(boolean z) {
        this.isUploadedStrava = z;
        this.uploadedStrava = z ? 1 : 0;
    }

    public boolean isSupportTrain() {
        return this.isSupportTrainingEffect != 0;
    }

    public int getIsSupportTrainingEffect() {
        return this.isSupportTrainingEffect;
    }

    public void setIsSupportTrainingEffect(int i) {
        this.isSupportTrainingEffect = i;
    }

    public int getCumulativeClimb() {
        return this.cumulativeClimb;
    }

    public void setCumulativeClimb(int i) {
        this.cumulativeClimb = i;
    }

    public int getCumulativeDecline() {
        return this.cumulativeDecline;
    }

    public void setCumulativeDecline(int i) {
        this.cumulativeDecline = i;
    }

    public SportRealTimePace getRealTimePace() {
        return this.realTimePace;
    }

    public void setRealTimePace(SportRealTimePace sportRealTimePace) {
        this.realTimePace = sportRealTimePace;
    }

    public String toString() {
        return "SportHealth{sid='" + this.sid + "', dateTime='" + this.dateTime + "', type=" + this.type + ", subType=" + this.subType + ", totalSeconds=" + this.totalSeconds + ", numCalories=" + this.numCalories + ", numSteps=" + this.numSteps + ", distance=" + this.distance + ", startTime='" + this.startTime + "', endTime='" + this.endTime + "', targetType=" + this.targetType + ", targetValue=" + this.targetValue + ", warmupSeconds=" + this.warmupSeconds + ", burnFatSeconds=" + this.burnFatSeconds + ", aerobicSeconds=" + this.aerobicSeconds + ", anaerobicSecond=" + this.anaerobicSecond + ", extremeSecond=" + this.extremeSecond + ", sourceMac='" + this.sourceMac + "', deviceName='" + this.deviceName + "', sourceType=" + this.sourceType + ", minHrValue=" + this.minHrValue + ", maxHrValue=" + this.maxHrValue + ", avgHrValue=" + this.avgHrValue + ", minSpeed=" + this.minSpeed + ", maxSpeed=" + this.maxSpeed + ", avgSpeed=" + this.avgSpeed + ", minPace=" + this.minPace + ", maxPace=" + this.maxPace + ", avgPace=" + this.avgPace + ", isLocus=" + this.isLocus + ", stepRange=" + this.stepRange + ", minRate=" + this.minRate + ", stepRateMax=" + this.stepRateMax + ", stepRate=" + this.stepRate + ", swimmingPosture=" + this.swimmingPosture + ", totalStrokesNumber=" + this.totalStrokesNumber + ", poolDistance=" + this.poolDistance + ", trips=" + this.trips + ", bestSWOLF=" + this.bestSWOLF + ", maxSwolf=" + this.maxSwolf + ", averageSWOLF=" + this.averageSWOLF + ", sourceOs=" + this.sourceOs + ", gps=" + this.gps + ", heartrate=" + this.heartrate + ", rate=" + this.rate + ", range=" + this.range + ", pace=" + this.pace + ", realTimePace=" + this.realTimePace + ", swolf=" + this.swolf + ", stepRangeMax=" + this.stepRangeMax + ", isUploadedStrava=" + this.isUploadedStrava + ", uploadedStrava=" + this.uploadedStrava + ", isUploaded=" + this.isUploaded + ", activityId=" + this.activityId + ", userId=" + this.userId + ", hr_data_vlaue_json='" + this.hr_data_vlaue_json + "', stepItem='" + this.stepItem + "', rangeItem='" + this.rangeItem + "', hrDataIntervalMinute=" + this.hrDataIntervalMinute + ", fast_km_speed=" + this.fast_km_speed + ", avgFrequency=" + this.avgFrequency + ", maxFrequency=" + this.maxFrequency + ", swimmingDetailStr='" + this.swimmingDetailStr + "', intervalSecond=" + this.intervalSecond + ", timestamp=" + this.timestamp + ", gpsSourceType=" + this.gpsSourceType + ", icon='" + this.icon + "', isLoadDetail=" + this.isLoadDetail + ", vo2max=" + this.vo2max + ", recoverTime=" + this.recoverTime + ", discoverDateTime='" + this.discoverDateTime + "', trainingEffectScore=" + this.trainingEffectScore + ", isSupportTrainingEffect=" + this.isSupportTrainingEffect + ", cumulativeClimb=" + this.cumulativeClimb + ", cumulativeDecline=" + this.cumulativeDecline + '}';
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getSportHealthDao() : null;
    }
}