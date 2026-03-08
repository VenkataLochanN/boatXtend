package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* JADX INFO: loaded from: classes2.dex */
public interface ICameraUpdateFactoryDelegate extends IInterface {
    IObjectWrapper newCameraPosition(CameraPosition cameraPosition) throws RemoteException;

    IObjectWrapper newLatLng(LatLng latLng) throws RemoteException;

    IObjectWrapper newLatLngBounds(LatLngBounds latLngBounds, int i) throws RemoteException;

    IObjectWrapper newLatLngBoundsWithSize(LatLngBounds latLngBounds, int i, int i2, int i3) throws RemoteException;

    IObjectWrapper newLatLngZoom(LatLng latLng, float f2) throws RemoteException;

    IObjectWrapper scrollBy(float f2, float f3) throws RemoteException;

    IObjectWrapper zoomBy(float f2) throws RemoteException;

    IObjectWrapper zoomByWithFocus(float f2, int i, int i2) throws RemoteException;

    IObjectWrapper zoomIn() throws RemoteException;

    IObjectWrapper zoomOut() throws RemoteException;

    IObjectWrapper zoomTo(float f2) throws RemoteException;
}