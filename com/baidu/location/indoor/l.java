package com.baidu.location.indoor;

import android.location.Location;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<Location> f2592a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2593b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Location f2594c = null;

    l(String str, Location[] locationArr) {
        if (locationArr == null || locationArr.length <= 0) {
            return;
        }
        a(locationArr);
        this.f2593b = str;
    }

    private void a(Location[] locationArr) {
        if (locationArr == null || locationArr.length <= 0) {
            return;
        }
        if (this.f2592a == null) {
            this.f2592a = new ArrayList();
        }
        double latitude = 0.0d;
        double longitude = 0.0d;
        for (int i = 0; i < locationArr.length; i++) {
            latitude += locationArr[i].getLatitude();
            longitude += locationArr[i].getLongitude();
            this.f2592a.add(locationArr[i]);
        }
        if (this.f2594c == null) {
            this.f2594c = new Location("gps");
            this.f2594c.setLatitude(latitude / ((double) locationArr.length));
            this.f2594c.setLongitude(longitude / ((double) locationArr.length));
        }
    }

    public String a() {
        return this.f2593b;
    }
}