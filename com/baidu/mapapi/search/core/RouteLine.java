package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RouteLine<T extends RouteStep> implements Parcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    TYPE f3120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private RouteNode f3121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RouteNode f3122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3123d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<T> f3124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3125f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3126g;

    protected enum TYPE {
        DRIVESTEP(0),
        TRANSITSTEP(1),
        WALKSTEP(2),
        BIKINGSTEP(3);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3128a;

        TYPE(int i) {
            this.f3128a = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a() {
            return this.f3128a;
        }
    }

    protected RouteLine() {
    }

    protected RouteLine(Parcel parcel) {
        Object obj;
        int i = parcel.readInt();
        this.f3121b = (RouteNode) parcel.readValue(RouteNode.class.getClassLoader());
        this.f3122c = (RouteNode) parcel.readValue(RouteNode.class.getClassLoader());
        this.f3123d = parcel.readString();
        if (i == 0) {
            obj = DrivingRouteLine.DrivingStep.CREATOR;
        } else if (i == 1) {
            obj = TransitRouteLine.TransitStep.CREATOR;
        } else {
            if (i != 2) {
                if (i == 3) {
                    obj = BikingRouteLine.BikingStep.CREATOR;
                }
                this.f3125f = parcel.readInt();
                this.f3126g = parcel.readInt();
            }
            obj = WalkingRouteLine.WalkingStep.CREATOR;
        }
        this.f3124e = parcel.createTypedArrayList(obj);
        this.f3125f = parcel.readInt();
        this.f3126g = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<T> getAllStep() {
        return this.f3124e;
    }

    public int getDistance() {
        return this.f3125f;
    }

    public int getDuration() {
        return this.f3126g;
    }

    public RouteNode getStarting() {
        return this.f3121b;
    }

    public RouteNode getTerminal() {
        return this.f3122c;
    }

    public String getTitle() {
        return this.f3123d;
    }

    protected TYPE getType() {
        return this.f3120a;
    }

    public void setDistance(int i) {
        this.f3125f = i;
    }

    public void setDuration(int i) {
        this.f3126g = i;
    }

    public void setStarting(RouteNode routeNode) {
        this.f3121b = routeNode;
    }

    public void setSteps(List<T> list) {
        this.f3124e = list;
    }

    public void setTerminal(RouteNode routeNode) {
        this.f3122c = routeNode;
    }

    public void setTitle(String str) {
        this.f3123d = str;
    }

    protected void setType(TYPE type) {
        this.f3120a = type;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TYPE type = this.f3120a;
        parcel.writeInt(type != null ? type.a() : 10);
        parcel.writeValue(this.f3121b);
        parcel.writeValue(this.f3122c);
        parcel.writeString(this.f3123d);
        if (this.f3120a != null) {
            parcel.writeTypedList(this.f3124e);
        }
        parcel.writeInt(this.f3125f);
        parcel.writeInt(this.f3126g);
    }
}