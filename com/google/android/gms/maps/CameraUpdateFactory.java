package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate zzf;

    private CameraUpdateFactory() {
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(zzb().newCameraPosition(cameraPosition));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        try {
            return new CameraUpdate(zzb().newLatLng(latLng));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        try {
            return new CameraUpdate(zzb().newLatLngBounds(latLngBounds, i));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3) {
        try {
            return new CameraUpdate(zzb().newLatLngBoundsWithSize(latLngBounds, i, i2, i3));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f2) {
        try {
            return new CameraUpdate(zzb().newLatLngZoom(latLng, f2));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate scrollBy(float f2, float f3) {
        try {
            return new CameraUpdate(zzb().scrollBy(f2, f3));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate zoomBy(float f2) {
        try {
            return new CameraUpdate(zzb().zoomBy(f2));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate zoomBy(float f2, Point point) {
        try {
            return new CameraUpdate(zzb().zoomByWithFocus(f2, point.x, point.y));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(zzb().zoomIn());
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(zzb().zoomOut());
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static CameraUpdate zoomTo(float f2) {
        try {
            return new CameraUpdate(zzb().zoomTo(f2));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public static void zza(ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        zzf = (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(iCameraUpdateFactoryDelegate);
    }

    private static ICameraUpdateFactoryDelegate zzb() {
        return (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(zzf, "CameraUpdateFactory is not initialized");
    }
}