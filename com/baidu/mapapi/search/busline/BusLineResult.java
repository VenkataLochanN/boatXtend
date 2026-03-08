package com.baidu.mapapi.search.busline;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.SearchResult;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BusLineResult extends SearchResult implements Parcelable {
    public static final Parcelable.Creator<BusLineResult> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3097a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3098b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3099c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Date f3100d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Date f3101e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f3102f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<BusStation> f3103g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<BusStep> f3104h;
    private float i;
    private float j;
    private String k;

    public static class BusStation extends RouteNode {
    }

    public static class BusStep extends RouteStep {
    }

    public BusLineResult() {
        this.f3097a = null;
        this.f3098b = null;
        this.f3103g = null;
        this.f3104h = null;
        this.k = null;
    }

    BusLineResult(Parcel parcel) {
        this.f3097a = null;
        this.f3098b = null;
        this.f3103g = null;
        this.f3104h = null;
        this.k = null;
        this.f3097a = parcel.readString();
        this.f3098b = parcel.readString();
        this.f3099c = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.f3100d = (Date) parcel.readValue(Date.class.getClassLoader());
        this.f3101e = (Date) parcel.readValue(Date.class.getClassLoader());
        this.f3102f = parcel.readString();
        this.f3103g = parcel.readArrayList(BusStation.class.getClassLoader());
        this.f3104h = parcel.readArrayList(RouteStep.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getBasePrice() {
        return this.i;
    }

    public String getBusCompany() {
        return this.f3097a;
    }

    public String getBusLineName() {
        return this.f3098b;
    }

    public Date getEndTime() {
        return this.f3101e;
    }

    public String getLineDirection() {
        return this.k;
    }

    public float getMaxPrice() {
        return this.j;
    }

    public Date getStartTime() {
        return this.f3100d;
    }

    public List<BusStation> getStations() {
        return this.f3103g;
    }

    public List<BusStep> getSteps() {
        return this.f3104h;
    }

    public String getUid() {
        return this.f3102f;
    }

    public boolean isMonthTicket() {
        return this.f3099c;
    }

    public void setBasePrice(float f2) {
        this.i = f2;
    }

    public void setBusLineName(String str) {
        this.f3098b = str;
    }

    public void setEndTime(Date date) {
        this.f3101e = date;
    }

    public void setLineDirection(String str) {
        this.k = str;
    }

    public void setMaxPrice(float f2) {
        this.j = f2;
    }

    public void setMonthTicket(boolean z) {
        this.f3099c = z;
    }

    public void setStartTime(Date date) {
        this.f3100d = date;
    }

    public void setStations(List<BusStation> list) {
        this.f3103g = list;
    }

    public void setSteps(List<BusStep> list) {
        this.f3104h = list;
    }

    public void setUid(String str) {
        this.f3102f = str;
    }

    @Override // com.baidu.mapapi.search.core.SearchResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3097a);
        parcel.writeString(this.f3098b);
        parcel.writeValue(Boolean.valueOf(this.f3099c));
        parcel.writeValue(this.f3100d);
        parcel.writeValue(this.f3101e);
        parcel.writeString(this.f3102f);
        parcel.writeList(this.f3103g);
        parcel.writeList(this.f3104h);
    }
}