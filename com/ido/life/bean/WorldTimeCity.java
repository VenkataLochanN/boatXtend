package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.life.module.user.country.CountryChooseActivity;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeCity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\nHÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\nHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003Jc\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u0096\u0002J\b\u00105\u001a\u00020\u0003H\u0016J\b\u00106\u001a\u00020\u0005H\u0016R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012¨\u00067"}, d2 = {"Lcom/ido/life/bean/WorldTimeCity;", "Ljava/io/Serializable;", "id", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", CountryChooseActivity.COUNTRY, "timeZoneName", "Abbreviation", "latitude", "", "latitude_flag", "longitude", "longitude_flag", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DIDI)V", "getAbbreviation", "()Ljava/lang/String;", "setAbbreviation", "(Ljava/lang/String;)V", "getCountry", "setCountry", "getId", "()I", "setId", "(I)V", "getLatitude", "()D", "setLatitude", "(D)V", "getLatitude_flag", "setLatitude_flag", "getLongitude", "setLongitude", "getLongitude_flag", "setLongitude_flag", "getName", "setName", "getTimeZoneName", "setTimeZoneName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class WorldTimeCity implements Serializable {
    private String Abbreviation;
    private String country;
    private int id;
    private double latitude;
    private int latitude_flag;
    private double longitude;
    private int longitude_flag;
    private String name;
    private String timeZoneName;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTimeZoneName() {
        return this.timeZoneName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAbbreviation() {
        return this.Abbreviation;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getLatitude_flag() {
        return this.latitude_flag;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getLongitude_flag() {
        return this.longitude_flag;
    }

    public final WorldTimeCity copy(int id, String name, String country, String timeZoneName, String Abbreviation, double latitude, int latitude_flag, double longitude, int longitude_flag) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(country, "country");
        Intrinsics.checkParameterIsNotNull(timeZoneName, "timeZoneName");
        Intrinsics.checkParameterIsNotNull(Abbreviation, "Abbreviation");
        return new WorldTimeCity(id, name, country, timeZoneName, Abbreviation, latitude, latitude_flag, longitude, longitude_flag);
    }

    public WorldTimeCity(int i, String name, String country, String timeZoneName, String Abbreviation, double d2, int i2, double d3, int i3) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(country, "country");
        Intrinsics.checkParameterIsNotNull(timeZoneName, "timeZoneName");
        Intrinsics.checkParameterIsNotNull(Abbreviation, "Abbreviation");
        this.id = i;
        this.name = name;
        this.country = country;
        this.timeZoneName = timeZoneName;
        this.Abbreviation = Abbreviation;
        this.latitude = d2;
        this.latitude_flag = i2;
        this.longitude = d3;
        this.longitude_flag = i3;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getCountry() {
        return this.country;
    }

    public final void setCountry(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.country = str;
    }

    public final String getTimeZoneName() {
        return this.timeZoneName;
    }

    public final void setTimeZoneName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.timeZoneName = str;
    }

    public final String getAbbreviation() {
        return this.Abbreviation;
    }

    public final void setAbbreviation(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.Abbreviation = str;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(double d2) {
        this.latitude = d2;
    }

    public final int getLatitude_flag() {
        return this.latitude_flag;
    }

    public final void setLatitude_flag(int i) {
        this.latitude_flag = i;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(double d2) {
        this.longitude = d2;
    }

    public /* synthetic */ WorldTimeCity(int i, String str, String str2, String str3, String str4, double d2, int i2, double d3, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, str, (i4 & 4) != 0 ? "" : str2, (i4 & 8) != 0 ? "" : str3, (i4 & 16) != 0 ? "" : str4, (i4 & 32) != 0 ? 0.0d : d2, (i4 & 64) != 0 ? 1 : i2, (i4 & 128) != 0 ? 0.0d : d3, (i4 & 256) != 0 ? 1 : i3);
    }

    public final int getLongitude_flag() {
        return this.longitude_flag;
    }

    public final void setLongitude_flag(int i) {
        this.longitude_flag = i;
    }

    public String toString() {
        return "WorldTimeCity(id=" + this.id + ", name='" + this.name + "', country='" + this.country + "', timeZoneName='" + this.timeZoneName + "', latitude=" + this.latitude + ", longitude=" + this.longitude + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return this.id == ((WorldTimeCity) other).id;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.WorldTimeCity");
    }

    public int hashCode() {
        return this.id;
    }
}