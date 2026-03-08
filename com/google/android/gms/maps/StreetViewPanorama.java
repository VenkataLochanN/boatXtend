package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

/* JADX INFO: loaded from: classes2.dex */
public class StreetViewPanorama {
    private final IStreetViewPanoramaDelegate zzbn;

    public interface OnStreetViewPanoramaCameraChangeListener {
        void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera);
    }

    public interface OnStreetViewPanoramaChangeListener {
        void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation);
    }

    public interface OnStreetViewPanoramaClickListener {
        void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public interface OnStreetViewPanoramaLongClickListener {
        void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public StreetViewPanorama(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) {
        this.zzbn = (IStreetViewPanoramaDelegate) Preconditions.checkNotNull(iStreetViewPanoramaDelegate, "delegate");
    }

    public void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
        try {
            this.zzbn.animateTo(streetViewPanoramaCamera, j);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public StreetViewPanoramaLocation getLocation() {
        try {
            return this.zzbn.getStreetViewPanoramaLocation();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public StreetViewPanoramaCamera getPanoramaCamera() {
        try {
            return this.zzbn.getPanoramaCamera();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public boolean isPanningGesturesEnabled() {
        try {
            return this.zzbn.isPanningGesturesEnabled();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public boolean isStreetNamesEnabled() {
        try {
            return this.zzbn.isStreetNamesEnabled();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public boolean isUserNavigationEnabled() {
        try {
            return this.zzbn.isUserNavigationEnabled();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public boolean isZoomGesturesEnabled() {
        try {
            return this.zzbn.isZoomGesturesEnabled();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public Point orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        try {
            IObjectWrapper iObjectWrapperOrientationToPoint = this.zzbn.orientationToPoint(streetViewPanoramaOrientation);
            if (iObjectWrapperOrientationToPoint == null) {
                return null;
            }
            return (Point) ObjectWrapper.unwrap(iObjectWrapperOrientationToPoint);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public StreetViewPanoramaOrientation pointToOrientation(Point point) {
        try {
            return this.zzbn.pointToOrientation(ObjectWrapper.wrap(point));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        try {
            if (onStreetViewPanoramaCameraChangeListener == null) {
                this.zzbn.setOnStreetViewPanoramaCameraChangeListener(null);
            } else {
                this.zzbn.setOnStreetViewPanoramaCameraChangeListener(new zzae(this, onStreetViewPanoramaCameraChangeListener));
            }
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        try {
            if (onStreetViewPanoramaChangeListener == null) {
                this.zzbn.setOnStreetViewPanoramaChangeListener(null);
            } else {
                this.zzbn.setOnStreetViewPanoramaChangeListener(new zzad(this, onStreetViewPanoramaChangeListener));
            }
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        try {
            if (onStreetViewPanoramaClickListener == null) {
                this.zzbn.setOnStreetViewPanoramaClickListener(null);
            } else {
                this.zzbn.setOnStreetViewPanoramaClickListener(new zzaf(this, onStreetViewPanoramaClickListener));
            }
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void setOnStreetViewPanoramaLongClickListener(OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
        try {
            if (onStreetViewPanoramaLongClickListener == null) {
                this.zzbn.setOnStreetViewPanoramaLongClickListener(null);
            } else {
                this.zzbn.setOnStreetViewPanoramaLongClickListener(new zzag(this, onStreetViewPanoramaLongClickListener));
            }
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setPanningGesturesEnabled(boolean z) {
        try {
            this.zzbn.enablePanning(z);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            this.zzbn.setPosition(latLng);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setPosition(LatLng latLng, int i) {
        try {
            this.zzbn.setPositionWithRadius(latLng, i);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setPosition(LatLng latLng, int i, StreetViewSource streetViewSource) {
        try {
            this.zzbn.setPositionWithRadiusAndSource(latLng, i, streetViewSource);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setPosition(LatLng latLng, StreetViewSource streetViewSource) {
        try {
            this.zzbn.setPositionWithSource(latLng, streetViewSource);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setPosition(String str) {
        try {
            this.zzbn.setPositionWithID(str);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setStreetNamesEnabled(boolean z) {
        try {
            this.zzbn.enableStreetNames(z);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setUserNavigationEnabled(boolean z) {
        try {
            this.zzbn.enableUserNavigation(z);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void setZoomGesturesEnabled(boolean z) {
        try {
            this.zzbn.enableZoom(z);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}