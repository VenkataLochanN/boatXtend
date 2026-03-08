package com.autonavi.base.amap.api.mapcore.infowindow;

import android.os.RemoteException;
import android.view.MotionEvent;
import com.amap.api.mapcore.util.ar;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;

/* JADX INFO: loaded from: classes.dex */
public interface IInfoWindowAction {
    void hideInfoWindow();

    boolean isInfoWindowShown();

    boolean onInfoWindowTap(MotionEvent motionEvent);

    void redrawInfoWindow();

    void setInfoWindowAdapterManager(ar arVar);

    void showInfoWindow(BaseOverlayImp baseOverlayImp) throws RemoteException;
}