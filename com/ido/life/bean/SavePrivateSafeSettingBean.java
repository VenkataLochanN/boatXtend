package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SavePrivateSafeSettingBean {
    public static final String HOUR_TIME_UNIT = "HOUR_TIME_UNIT";
    public static final String INDEX_CARD_HIDDEN_POSITION = "INDEX_CARD_HIDDEN_POSITION";
    public static final String INDEX_CARD_POSITION = "INDEX_CARD_POSITION";
    public static final String MAP_ENGINE = "MAP_ENGINE";
    public static final String MENCES_CONFIG = "MENCES_CONFIG";
    public static final String OFF = "OFF";
    public static final String ON = "ON";
    public static final String SPORT_TYPE_POSITION = "SPORT_TYPE_POSITION";
    public static final String SYSTEM_UNIT = "SYSTEM_UNIT";
    public static final String TEMP_UNIT = "TEMPERATURE";
    public static final String TYPE_GOOGLE_FIT = "GOOGLE_FIT_DATA_AUTH";
    public static final String TYPE_HEALTH = "UPLOAD_HEALTH_DATA";
    public static final String TYPE_PRIVATE = "UPLOAD_USER_INFO";
    public static final String TYPE_SPORT = "UPLOAD_SPORT_DATA";
    public static final String TYPE_STRAVA = "STRAVA_DATA_AUTH";
    public static final String WEEK_START = "WEEK_START";

    @SerializedName("prefs")
    private List<SavePrivateSafeSettingBeanItem> mList;

    @SerializedName("timestamp")
    private long mTimeStamp;

    public SavePrivateSafeSettingBean(List<SavePrivateSafeSettingBeanItem> list) {
        this.mList = list;
    }

    public SavePrivateSafeSettingBean(List<SavePrivateSafeSettingBeanItem> list, long j) {
        this.mList = list;
        this.mTimeStamp = j;
    }

    public List<SavePrivateSafeSettingBeanItem> getList() {
        return this.mList;
    }

    public void setList(List<SavePrivateSafeSettingBeanItem> list) {
        this.mList = list;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(long j) {
        this.mTimeStamp = j;
    }

    public static class SavePrivateSafeSettingBeanItem {

        @SerializedName("attrName")
        private String mAttrName;

        @SerializedName("attrValue")
        private String mAttrValue;

        @SerializedName("timestamp")
        private long mTimestamp;

        public SavePrivateSafeSettingBeanItem() {
            this.mTimestamp = System.currentTimeMillis();
        }

        public SavePrivateSafeSettingBeanItem(String str, String str2) {
            this.mAttrName = str;
            this.mAttrValue = str2;
            this.mTimestamp = System.currentTimeMillis();
        }

        public SavePrivateSafeSettingBeanItem(String str, String str2, long j) {
            this.mAttrName = str;
            this.mAttrValue = str2;
            this.mTimestamp = j;
        }

        public String getAttrName() {
            return this.mAttrName;
        }

        public void setAttrName(String str) {
            this.mAttrName = str;
        }

        public String getAttrValue() {
            return this.mAttrValue;
        }

        public void setAttrValue(String str) {
            this.mAttrValue = str;
        }

        public long getTimestamp() {
            return this.mTimestamp;
        }

        public void setTimestamp(long j) {
            this.mTimestamp = j;
        }
    }
}