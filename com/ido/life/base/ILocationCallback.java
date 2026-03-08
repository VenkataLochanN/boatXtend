package com.ido.life.base;

import com.ido.life.location.LocationMessage;

/* JADX INFO: loaded from: classes2.dex */
public interface ILocationCallback {
    void onLocationFailed(String str);

    void onLocationUpdate(LocationMessage locationMessage);
}