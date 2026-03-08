package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

/* JADX INFO: loaded from: classes2.dex */
public final class Projection {
    private final IProjectionDelegate zzbm;

    Projection(IProjectionDelegate iProjectionDelegate) {
        this.zzbm = iProjectionDelegate;
    }

    public final LatLng fromScreenLocation(Point point) {
        try {
            return this.zzbm.fromScreenLocation(ObjectWrapper.wrap(point));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final VisibleRegion getVisibleRegion() {
        try {
            return this.zzbm.getVisibleRegion();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final Point toScreenLocation(LatLng latLng) {
        try {
            return (Point) ObjectWrapper.unwrap(this.zzbm.toScreenLocation(latLng));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}