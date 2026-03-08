package com.ido.life.data.api.entity;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes2.dex */
public class WeeklyReportStatusEntity {

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private int mStatus;

    @SerializedName("timestamp")
    @Expose(deserialize = false)
    private long mTimeStamp;

    @SerializedName("weekNum")
    private int mWeekNum;

    @SerializedName("year")
    private int mYear;

    public int getYear() {
        return this.mYear;
    }

    public void setYear(int i) {
        this.mYear = i;
    }

    public int getWeekNum() {
        return this.mWeekNum;
    }

    public void setWeekNum(int i) {
        this.mWeekNum = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(long j) {
        this.mTimeStamp = j;
    }
}