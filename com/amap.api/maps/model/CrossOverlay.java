package com.amap.api.maps.model;

import android.graphics.Bitmap;
import com.autonavi.ae.gmap.gloverlay.AVectorCrossAttr;
import com.autonavi.amap.mapcore.interfaces.ICrossVectorOverlay;

/* JADX INFO: loaded from: classes.dex */
public class CrossOverlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ICrossVectorOverlay f1855a;

    public interface GenerateCrossImageListener {
        void onGenerateComplete(Bitmap bitmap, int i);
    }

    public CrossOverlay(CrossOverlayOptions crossOverlayOptions, ICrossVectorOverlay iCrossVectorOverlay) {
        this.f1855a = null;
        this.f1855a = iCrossVectorOverlay;
    }

    public int setData(byte[] bArr) {
        ICrossVectorOverlay iCrossVectorOverlay;
        if (bArr == null || (iCrossVectorOverlay = this.f1855a) == null) {
            return -1;
        }
        try {
            return iCrossVectorOverlay.setData(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public void setAttribute(AVectorCrossAttr aVectorCrossAttr) {
        try {
            this.f1855a.setAttribute(aVectorCrossAttr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setVisible(boolean z) {
        ICrossVectorOverlay iCrossVectorOverlay = this.f1855a;
        if (iCrossVectorOverlay != null) {
            try {
                iCrossVectorOverlay.setVisible(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void remove() {
        ICrossVectorOverlay iCrossVectorOverlay = this.f1855a;
        if (iCrossVectorOverlay != null) {
            try {
                iCrossVectorOverlay.remove();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setImageMode(boolean z) {
        ICrossVectorOverlay iCrossVectorOverlay = this.f1855a;
        if (iCrossVectorOverlay != null) {
            try {
                iCrossVectorOverlay.setImageMode(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void setGenerateCrossImageListener(GenerateCrossImageListener generateCrossImageListener) {
        ICrossVectorOverlay iCrossVectorOverlay = this.f1855a;
        if (iCrossVectorOverlay != null) {
            try {
                iCrossVectorOverlay.setGenerateCrossImageListener(generateCrossImageListener);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}