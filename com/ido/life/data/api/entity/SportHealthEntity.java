package com.ido.life.data.api.entity;

/* JADX INFO: loaded from: classes2.dex */
public class SportHealthEntity {
    private Long activityId;
    private int aerobicSeconds;
    private int anaerobicSecond;
    private int avgHrValue;
    private String avgPace;
    private int avgSpeed;
    private int burnFatSeconds;
    private String dateTime;
    private int distance;
    private String endTime;
    private int extremeSecond;
    private Gps gps;
    private HeartRate heartrate;
    private int isLocus;
    private int maxHrValue;
    private int maxSpeed;
    private int minHrValue;
    private int minSpeed;
    private int numCalories;
    private int numSteps;
    private Range range;
    private Rate rate;
    private String sid;
    private String sourceMac;
    private int sourceType;
    private String startTime;
    private int stepRange;
    private int stepRate;
    private int subType;
    private int totalSeconds;
    private int type;
    private Long userId;
    private int warmupSeconds;

    public SportHealthEntity() {
    }

    public SportHealthEntity(String str, Long l, Long l2, String str2, int i, int i2, int i3, int i4, int i5, int i6, String str3, String str4, int i7, int i8, int i9, int i10, int i11, String str5, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, String str6, Gps gps, HeartRate heartRate, Rate rate, Range range) {
        this.sid = str;
        this.activityId = l;
        this.userId = l2;
        this.dateTime = str2;
        this.type = i;
        this.subType = i2;
        this.totalSeconds = i3;
        this.numCalories = i4;
        this.distance = i5;
        this.numSteps = i6;
        this.startTime = str3;
        this.endTime = str4;
        this.maxHrValue = i7;
        this.warmupSeconds = i8;
        this.burnFatSeconds = i9;
        this.aerobicSeconds = i10;
        this.extremeSecond = i11;
        this.sourceMac = str5;
        this.avgHrValue = i12;
        this.minHrValue = i13;
        this.anaerobicSecond = i14;
        this.isLocus = i15;
        this.maxSpeed = i16;
        this.minSpeed = i17;
        this.avgSpeed = i18;
        this.stepRate = i19;
        this.stepRange = i20;
        this.sourceType = i21;
        this.avgPace = str6;
        this.gps = gps;
        this.heartrate = heartRate;
        this.rate = rate;
        this.range = range;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String str) {
        this.sid = str;
    }

    public Long getActivityId() {
        return this.activityId;
    }

    public void setActivityId(Long l) {
        this.activityId = l;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long l) {
        this.userId = l;
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

    public int getSubType() {
        return this.subType;
    }

    public void setSubType(int i) {
        this.subType = i;
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

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
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

    public String getAvgPace() {
        return this.avgPace;
    }

    public void setAvgPace(String str) {
        this.avgPace = str;
    }

    public Gps getGps() {
        return this.gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public HeartRate getHeartRate() {
        return this.heartrate;
    }

    public void setHeartRate(HeartRate heartRate) {
        this.heartrate = heartRate;
    }

    public Rate getRate() {
        return this.rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public class Gps {
        private int interval;
        private String items;

        public Gps() {
        }

        public Gps(int i, String str) {
            this.interval = i;
            this.items = str;
        }

        public int getInterval() {
            return this.interval;
        }

        public void setInterval(int i) {
            this.interval = i;
        }

        public String getItems() {
            return this.items;
        }

        public void setItems(String str) {
            this.items = str;
        }

        public String toString() {
            return "Gps{interval=" + this.interval + ", items='" + this.items + "'}";
        }
    }

    public class HeartRate {
        private String items;

        public HeartRate(String str) {
            this.items = str;
        }

        public String getItems() {
            return this.items;
        }

        public void setItems(String str) {
            this.items = str;
        }

        public String toString() {
            return "HeartRate{items='" + this.items + "'}";
        }
    }

    private class Rate {
        private String items;

        private Rate() {
        }

        public String getItems() {
            return this.items;
        }

        public void setItems(String str) {
            this.items = str;
        }

        public String toString() {
            return "Rate{items='" + this.items + "'}";
        }
    }

    private class Range {
        private String items;

        public Range(String str) {
            this.items = str;
        }

        public String toString() {
            return "Range{items='" + this.items + "'}";
        }
    }

    public String toString() {
        return "SportHealthEntity{sid='" + this.sid + "', activityId=" + this.activityId + ", userId=" + this.userId + ", dateTime='" + this.dateTime + "', type=" + this.type + ", subType=" + this.subType + ", totalSeconds=" + this.totalSeconds + ", numCalories=" + this.numCalories + ", distance=" + this.distance + ", numSteps=" + this.numSteps + ", startTime='" + this.startTime + "', endTime='" + this.endTime + "', maxHrValue=" + this.maxHrValue + ", warmupSeconds=" + this.warmupSeconds + ", burnFatSeconds=" + this.burnFatSeconds + ", aerobicSeconds=" + this.aerobicSeconds + ", extremeSecond=" + this.extremeSecond + ", sourceMac='" + this.sourceMac + "', avgHrValue=" + this.avgHrValue + ", minHrValue=" + this.minHrValue + ", anaerobicSecond=" + this.anaerobicSecond + ", isLocus=" + this.isLocus + ", maxSpeed=" + this.maxSpeed + ", minSpeed=" + this.minSpeed + ", avgSpeed=" + this.avgSpeed + ", stepRate=" + this.stepRate + ", stepRange=" + this.stepRange + ", sourceType=" + this.sourceType + ", avgPace='" + this.avgPace + "', gps=" + this.gps + ", heartRate=" + this.heartrate + ", rate=" + this.rate + ", range=" + this.range + '}';
    }
}