package com.ido.life.base;

import android.content.Context;
import com.ido.life.location.LocationMessage;

/* JADX INFO: loaded from: classes2.dex */
public interface IBaseLocation {
    void onDestroy();

    void onLocationChanged(LocationMessage locationMessage);

    String[] permissions();

    void setLocationListener(ILocationCallback iLocationCallback);

    void startLocation(Context context, boolean z);

    void stopLocation();
}