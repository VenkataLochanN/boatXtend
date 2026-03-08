package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class PoiFilter implements Parcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3185b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3186c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3187d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3188e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Map<SortName, String> f3183f = new HashMap();
    public static final Parcelable.Creator<PoiFilter> CREATOR = new c();

    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f3189a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3190b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3191c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f3192d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f3193e;

        public Builder() {
            PoiFilter.f3183f.put(SortName.HotelSortName.DEFAULT, "default");
            PoiFilter.f3183f.put(SortName.HotelSortName.HOTEL_LEVEL, FirebaseAnalytics.Param.LEVEL);
            PoiFilter.f3183f.put(SortName.HotelSortName.HOTEL_PRICE, FirebaseAnalytics.Param.PRICE);
            PoiFilter.f3183f.put(SortName.HotelSortName.HOTEL_DISTANCE, "distance");
            PoiFilter.f3183f.put(SortName.HotelSortName.HOTEL_HEALTH_SCORE, "health_score");
            PoiFilter.f3183f.put(SortName.HotelSortName.HOTEL_TOTAL_SCORE, "total_score");
            PoiFilter.f3183f.put(SortName.CaterSortName.DEFAULT, "default");
            PoiFilter.f3183f.put(SortName.CaterSortName.CATER_DISTANCE, "distance");
            PoiFilter.f3183f.put(SortName.CaterSortName.CATER_PRICE, FirebaseAnalytics.Param.PRICE);
            PoiFilter.f3183f.put(SortName.CaterSortName.CATER_OVERALL_RATING, "overall_rating");
            PoiFilter.f3183f.put(SortName.CaterSortName.CATER_SERVICE_RATING, "service_rating");
            PoiFilter.f3183f.put(SortName.CaterSortName.CATER_TASTE_RATING, "taste_rating");
            PoiFilter.f3183f.put(SortName.LifeSortName.DEFAULT, "default");
            PoiFilter.f3183f.put(SortName.LifeSortName.PRICE, FirebaseAnalytics.Param.PRICE);
            PoiFilter.f3183f.put(SortName.LifeSortName.LIFE_COMMENT_RATING, "comment_num");
            PoiFilter.f3183f.put(SortName.LifeSortName.LIFE_OVERALL_RATING, "overall_rating");
            PoiFilter.f3183f.put(SortName.LifeSortName.DISTANCE, "distance");
        }

        public PoiFilter build() {
            return new PoiFilter(this.f3189a, this.f3190b, this.f3191c, this.f3193e, this.f3192d);
        }

        public Builder industryType(IndustryType industryType) {
            int i = d.f3210a[industryType.ordinal()];
            this.f3189a = i != 1 ? i != 2 ? i != 3 ? "" : "life" : "cater" : "hotel";
            return this;
        }

        public Builder isDiscount(boolean z) {
            this.f3193e = z ? "1" : AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            return this;
        }

        public Builder isGroupon(boolean z) {
            this.f3192d = z ? "1" : AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            return this;
        }

        public Builder sortName(SortName sortName) {
            if (!TextUtils.isEmpty(this.f3189a) && sortName != null) {
                this.f3190b = (String) PoiFilter.f3183f.get(sortName);
            }
            return this;
        }

        public Builder sortRule(int i) {
            this.f3191c = i + "";
            return this;
        }
    }

    public enum IndustryType {
        HOTEL,
        CATER,
        LIFE
    }

    public interface SortName {

        public enum CaterSortName implements SortName {
            DEFAULT,
            CATER_PRICE,
            CATER_DISTANCE,
            CATER_TASTE_RATING,
            CATER_OVERALL_RATING,
            CATER_SERVICE_RATING
        }

        public enum HotelSortName implements SortName {
            DEFAULT,
            HOTEL_PRICE,
            HOTEL_DISTANCE,
            HOTEL_TOTAL_SCORE,
            HOTEL_LEVEL,
            HOTEL_HEALTH_SCORE
        }

        public enum LifeSortName implements SortName {
            DEFAULT,
            PRICE,
            DISTANCE,
            LIFE_OVERALL_RATING,
            LIFE_COMMENT_RATING
        }
    }

    protected PoiFilter(Parcel parcel) {
        this.f3184a = "";
        this.f3185b = "";
        this.f3186c = "";
        this.f3187d = "";
        this.f3188e = "";
        this.f3184a = parcel.readString();
        this.f3185b = parcel.readString();
        this.f3186c = parcel.readString();
        this.f3188e = parcel.readString();
        this.f3187d = parcel.readString();
    }

    PoiFilter(String str, String str2, String str3, String str4, String str5) {
        this.f3184a = "";
        this.f3185b = "";
        this.f3186c = "";
        this.f3187d = "";
        this.f3188e = "";
        this.f3184a = str;
        this.f3185b = str2;
        this.f3186c = str3;
        this.f3188e = str4;
        this.f3187d = str5;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.f3184a)) {
            sb.append("industry_type:");
            sb.append(this.f3184a);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f3185b)) {
            sb.append("sort_name:");
            sb.append(this.f3185b);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f3186c)) {
            sb.append("sort_rule:");
            sb.append(this.f3186c);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f3188e)) {
            sb.append("discount:");
            sb.append(this.f3188e);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(this.f3187d)) {
            sb.append("groupon:");
            sb.append(this.f3187d);
            sb.append("|");
        }
        if (!TextUtils.isEmpty(sb.toString())) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3184a);
        parcel.writeString(this.f3185b);
        parcel.writeString(this.f3186c);
        parcel.writeString(this.f3188e);
        parcel.writeString(this.f3187d);
    }
}