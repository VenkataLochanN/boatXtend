package com.ido.life.module.user.sportrecord.model;

import com.ido.life.module.user.sportrecord.adapter.ExpandableRecyclerAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class SportChildItem extends SportItem {
    private Long activityId;
    private int aerobicSeconds;
    private int anaerobicSecond;
    private int avgHrValue;
    private int avgPace;
    private int avgSpeed;
    private int avgWeekTime;
    private int burnFatSeconds;
    private String dateTime;
    private int distance;
    private String endTime;
    private int extremeSecond;
    public SportGroupItem group;
    private String hr_data_vlaue_json;
    private String icon;
    private int isLocus;
    private boolean isUploadedStrava;
    private int maxHrValue;
    private int maxSpeed;
    private int minHrValue;
    private int minSpeed;
    private int numCalories;
    private int numSteps;
    private int poolDistance;
    private String sid;
    private String sourceMac;
    private int sourceType;
    private String startTime;
    private int stepRange;
    private int stepRate;
    private int totalCategory;
    private int totalCount;
    private int totalSeconds;
    private int totalTime;
    private int type;
    private Long userId;
    private int warmupSeconds;

    @Override // com.ido.life.module.user.sportrecord.model.SportItem
    public int type() {
        return ExpandableRecyclerAdapter.CHILD;
    }

    public SportGroupItem getGroup() {
        return this.group;
    }

    public void setGroup(SportGroupItem sportGroupItem) {
        this.group = sportGroupItem;
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

    public int getAvgPace() {
        return this.avgPace;
    }

    public void setAvgPace(int i) {
        this.avgPace = i;
    }

    public boolean isUploadedStrava() {
        return this.isUploadedStrava;
    }

    public void setUploadedStrava(boolean z) {
        this.isUploadedStrava = z;
    }

    public String getHr_data_vlaue_json() {
        return this.hr_data_vlaue_json;
    }

    public void setHr_data_vlaue_json(String str) {
        this.hr_data_vlaue_json = str;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int i) {
        this.totalTime = i;
    }

    public int getAvgWeekTime() {
        return this.avgWeekTime;
    }

    public void setAvgWeekTime(int i) {
        this.avgWeekTime = i;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public int getTotalCategory() {
        return this.totalCategory;
    }

    public void setTotalCategory(int i) {
        this.totalCategory = i;
    }

    public int getPoolDistance() {
        return this.poolDistance;
    }

    public void setPoolDistance(int i) {
        this.poolDistance = i;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String toString() {
        return "SportChildItem{group=" + this.group + ", sid='" + this.sid + "', activityId=" + this.activityId + ", userId=" + this.userId + ", dateTime='" + this.dateTime + "', type=" + this.type + ", totalSeconds=" + this.totalSeconds + ", numCalories=" + this.numCalories + ", distance=" + this.distance + ", numSteps=" + this.numSteps + ", startTime='" + this.startTime + "', endTime='" + this.endTime + "', maxHrValue=" + this.maxHrValue + ", warmupSeconds=" + this.warmupSeconds + ", burnFatSeconds=" + this.burnFatSeconds + ", aerobicSeconds=" + this.aerobicSeconds + ", extremeSecond=" + this.extremeSecond + ", sourceMac='" + this.sourceMac + "', avgHrValue=" + this.avgHrValue + ", minHrValue=" + this.minHrValue + ", anaerobicSecond=" + this.anaerobicSecond + ", isLocus=" + this.isLocus + ", maxSpeed=" + this.maxSpeed + ", minSpeed=" + this.minSpeed + ", avgSpeed=" + this.avgSpeed + ", stepRate=" + this.stepRate + ", stepRange=" + this.stepRange + ", sourceType=" + this.sourceType + ", avgPace='" + this.avgPace + "', isUploadedStrava=" + this.isUploadedStrava + ", hr_data_vlaue_json='" + this.hr_data_vlaue_json + "', totalTime=" + this.totalTime + ", avgWeekTime=" + this.avgWeekTime + ", totalCount=" + this.totalCount + ", totalCategory=" + this.totalCategory + ", poolDistance=" + this.poolDistance + ", icon" + this.icon + '}';
    }
}