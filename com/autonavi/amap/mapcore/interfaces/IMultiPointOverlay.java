package com.autonavi.amap.mapcore.interfaces;

import android.os.RemoteException;
import com.amap.api.maps.model.MultiPointItem;
import com.autonavi.amap.mapcore.IPoint;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IMultiPointOverlay {
    void addItem(MultiPointItem multiPointItem);

    void addItems(List<MultiPointItem> list);

    void destroy(boolean z);

    String getId() throws RemoteException;

    MultiPointItem onClick(IPoint iPoint);

    void remove(boolean z);

    void setAnchor(float f2, float f3);

    void setVisible(boolean z);
}