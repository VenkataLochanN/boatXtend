package com.autonavi.base.ae.gmap.gloverlay;

import android.content.Context;
import android.graphics.Bitmap;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import com.autonavi.base.ae.gmap.gloverlay.GLOverlay;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMapOverlay<T extends GLOverlay, E> implements Serializable {
    private static final long serialVersionUID = 1;
    protected Context mContext;
    protected int mEngineID;
    protected T mGLOverlay;
    protected Vector<E> mItemList;
    protected int mLastFocusedIndex;
    protected IAMapDelegate mMapView;

    public abstract void addItem(E e2);

    protected abstract void iniGLOverlay();

    public abstract void resumeMarker(Bitmap bitmap);

    public BaseMapOverlay(int i, Context context, IAMap iAMap) {
        this.mItemList = null;
        this.mEngineID = 1;
        this.mEngineID = i;
        this.mContext = context;
        try {
            this.mMapView = (IAMapDelegate) iAMap;
        } catch (Throwable unused) {
        }
        this.mItemList = new Vector<>();
        iniGLOverlay();
    }

    public T getGLOverlay() {
        return this.mGLOverlay;
    }

    public void setVisible(boolean z) {
        T t = this.mGLOverlay;
        if (t != null) {
            t.setVisible(z);
        }
    }

    public boolean isVisible() {
        T t = this.mGLOverlay;
        if (t != null) {
            return t.isVisible();
        }
        return false;
    }

    public void setClickable(boolean z) {
        T t = this.mGLOverlay;
        if (t != null) {
            t.setClickable(z);
        }
    }

    public boolean isClickable() {
        T t = this.mGLOverlay;
        if (t != null) {
            return t.isClickable();
        }
        return false;
    }

    public int getSize() {
        return this.mItemList.size();
    }

    public boolean clear() {
        try {
            this.mItemList.clear();
            clearFocus();
            if (this.mGLOverlay == null) {
                return true;
            }
            this.mGLOverlay.removeAll();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean removeAll() {
        return clear();
    }

    public void clearFocus() {
        this.mLastFocusedIndex = -1;
        this.mGLOverlay.clearFocus();
    }

    public final E getItem(int i) {
        try {
            synchronized (this.mItemList) {
                if (i >= 0) {
                    if (i <= this.mItemList.size() - 1) {
                        return this.mItemList.get(i);
                    }
                }
                return null;
            }
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public void removeItem(int i) {
        if (i >= 0) {
            try {
                if (i > this.mItemList.size() - 1) {
                    return;
                }
                if (i == this.mLastFocusedIndex) {
                    this.mLastFocusedIndex = -1;
                    clearFocus();
                }
                this.mItemList.remove(i);
                if (this.mGLOverlay != null) {
                    this.mGLOverlay.removeItem(i);
                }
            } catch (IndexOutOfBoundsException unused) {
            }
        }
    }

    public void removeItem(E e2) {
        if (e2 == null) {
            return;
        }
        try {
            synchronized (this.mItemList) {
                removeItem(this.mItemList.indexOf(e2));
            }
        } catch (IndexOutOfBoundsException unused) {
        }
    }

    public List<E> getItems() {
        return this.mItemList;
    }

    public void releaseInstance() {
        this.mMapView.queueEvent(new Runnable() { // from class: com.autonavi.base.ae.gmap.gloverlay.BaseMapOverlay.1
            @Override // java.lang.Runnable
            public void run() {
                if (BaseMapOverlay.this.getGLOverlay() != null) {
                    if (BaseMapOverlay.this.mMapView != null && BaseMapOverlay.this.mMapView.isMaploaded()) {
                        BaseMapOverlay.this.mMapView.removeEngineGLOverlay(BaseMapOverlay.this);
                    }
                    BaseMapOverlay.this.getGLOverlay().releaseInstance();
                    BaseMapOverlay.this.mGLOverlay = null;
                }
            }
        });
    }
}