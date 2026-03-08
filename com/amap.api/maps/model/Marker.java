package com.amap.api.maps.model;

import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMarker;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Marker extends BasePointOverlay {
    private IMarker markerDelegate;

    public Marker(IMarker iMarker) {
        this.markerDelegate = iMarker;
    }

    public void setPeriod(int i) {
        try {
            this.markerDelegate.setPeriod(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getPeriod() {
        try {
            return this.markerDelegate.getPeriod();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        try {
            this.markerDelegate.setIcons(arrayList);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        try {
            return this.markerDelegate.getIcons();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void remove() {
        try {
            this.markerDelegate.remove();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void destroy() {
        try {
            if (this.markerDelegate != null) {
                this.markerDelegate.destroy(true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public String getId() {
        try {
            return this.markerDelegate.getId();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setPerspective(boolean z) {
        try {
            this.markerDelegate.setPerspective(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isPerspective() {
        try {
            return this.markerDelegate.isPerspective();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setPosition(LatLng latLng) {
        try {
            this.markerDelegate.setPosition(latLng);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public LatLng getPosition() {
        try {
            return this.markerDelegate.getPosition();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setTitle(String str) {
        try {
            this.markerDelegate.setTitle(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public String getTitle() {
        try {
            return this.markerDelegate.getTitle();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setSnippet(String str) {
        try {
            this.markerDelegate.setSnippet(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public String getSnippet() {
        try {
            return this.markerDelegate.getSnippet();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                this.markerDelegate.setIcon(bitmapDescriptor);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setAnchor(float f2, float f3) {
        try {
            this.markerDelegate.setAnchor(f2, f3);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setDraggable(boolean z) {
        try {
            this.markerDelegate.setDraggable(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isDraggable() {
        return this.markerDelegate.isDraggable();
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void showInfoWindow() {
        try {
            this.markerDelegate.showInfoWindow();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void hideInfoWindow() {
        try {
            this.markerDelegate.hideInfoWindow();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isInfoWindowShown() {
        return this.markerDelegate.isInfoWindowShown();
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setVisible(boolean z) {
        try {
            this.markerDelegate.setVisible(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public boolean isVisible() {
        try {
            return this.markerDelegate.isVisible();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Marker) {
                    return this.markerDelegate.equalsRemote(((Marker) obj).markerDelegate);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public int hashCode() {
        return this.markerDelegate.hashCodeRemote();
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setObject(Object obj) {
        this.markerDelegate.setObject(obj);
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public Object getObject() {
        return this.markerDelegate.getObject();
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setRotateAngle(float f2) {
        try {
            this.markerDelegate.setRotateAngle(f2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public float getRotateAngle() {
        return this.markerDelegate.getRotateAngle();
    }

    public void setToTop() {
        try {
            this.markerDelegate.set2Top();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setGeoPoint(IPoint iPoint) {
        this.markerDelegate.setGeoPoint(iPoint);
    }

    public IPoint getGeoPoint() {
        return this.markerDelegate.getGeoPoint();
    }

    public void setFlat(boolean z) {
        try {
            this.markerDelegate.setFlat(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isFlat() {
        return this.markerDelegate.isFlat();
    }

    public void setPositionByPixels(int i, int i2) {
        this.markerDelegate.setPositionByPixels(i, i2);
    }

    public void setZIndex(float f2) {
        this.markerDelegate.setZIndex(f2);
    }

    public float getZIndex() {
        return this.markerDelegate.getZIndex();
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public void setAnimation(Animation animation) {
        try {
            this.markerDelegate.setAnimation(animation);
        } catch (Throwable unused) {
        }
    }

    @Override // com.amap.api.maps.model.BasePointOverlay
    public boolean startAnimation() {
        return this.markerDelegate.startAnimation();
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.markerDelegate.setAnimationListener(animationListener);
    }

    public float getAlpha() {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.getAlpha();
        }
        return 1.0f;
    }

    public void setAlpha(float f2) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setAlpha(f2);
        }
    }

    public int getDisplayLevel() {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.getDisplayLevel();
        }
        return 5;
    }

    public MarkerOptions getOptions() {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.getOptions();
        }
        return null;
    }

    public boolean isClickable() {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.isClickable();
        }
        return false;
    }

    public boolean isInfoWindowAutoOverturn() {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.isInfoWindowAutoOverturn();
        }
        return false;
    }

    public boolean isInfoWindowEnable() {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            return iMarkerAction.isInfoWindowEnable();
        }
        return false;
    }

    public void setInfoWindowEnable(boolean z) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setInfoWindowEnable(z);
        }
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setMarkerOptions(markerOptions);
        }
    }

    public void setAutoOverturnInfoWindow(boolean z) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setAutoOverturnInfoWindow(z);
        }
    }

    public void setClickable(boolean z) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setClickable(z);
        }
    }

    public void setDisplayLevel(int i) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setDisplayLevel(i);
        }
    }

    public void setFixingPointEnable(boolean z) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setFixingPointEnable(z);
        }
    }

    public boolean isRemoved() {
        IMarker iMarker = this.markerDelegate;
        if (iMarker != null) {
            return iMarker.isRemoved();
        }
        return false;
    }

    public void setPositionNotUpdate(LatLng latLng) {
        setPosition(latLng);
    }

    public void setRotateAngleNotUpdate(float f2) {
        IMarkerAction iMarkerAction = this.markerDelegate.getIMarkerAction();
        if (iMarkerAction != null) {
            iMarkerAction.setRotateAngleNotUpdate(f2);
        }
    }

    public void setBelowMaskLayer(boolean z) {
        this.markerDelegate.setBelowMaskLayer(z);
    }

    public float getAnchorU() {
        return this.markerDelegate.getAnchorU();
    }

    public float getAnchorV() {
        return this.markerDelegate.getAnchorV();
    }
}