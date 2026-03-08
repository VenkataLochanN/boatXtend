package com.ido.life.location;

import com.ido.life.base.IBaseLocation;

/* JADX INFO: loaded from: classes2.dex */
public class LocationManager {
    public static IBaseLocation getLocation(int i) {
        if (i == 0) {
            return new BaiduLocation();
        }
        if (i == 1) {
            return new GDLocation();
        }
        if (i != 2) {
            return null;
        }
        return new GoogleLocation();
    }
}