package com.amap.api.maps.model;

import android.graphics.Typeface;
import com.autonavi.amap.mapcore.interfaces.IText;

/* JADX INFO: loaded from: classes.dex */
public final class Text {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;
    private IText textDelegate;

    public Text(IText iText) {
        this.textDelegate = iText;
    }

    public void remove() {
        try {
            this.textDelegate.remove();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (this.textDelegate != null) {
                this.textDelegate.destroy(true);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getId() {
        try {
            return this.textDelegate.getId();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            this.textDelegate.setPosition(latLng);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public LatLng getPosition() {
        try {
            return this.textDelegate.getPosition();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setText(String str) {
        try {
            this.textDelegate.setText(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getText() {
        try {
            return this.textDelegate.getText();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setBackgroundColor(int i) {
        try {
            this.textDelegate.setBackgroundColor(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getBackgroundColor() {
        try {
            return this.textDelegate.getBackgroundColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setFontColor(int i) {
        try {
            this.textDelegate.setFontColor(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getFontColor() {
        try {
            return this.textDelegate.getFontColor();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setFontSize(int i) {
        try {
            this.textDelegate.setFontSize(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getFontSize() {
        try {
            return this.textDelegate.getFontSize();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setTypeface(Typeface typeface) {
        try {
            this.textDelegate.setTypeface(typeface);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public Typeface getTypeface() {
        try {
            return this.textDelegate.getTypeface();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setAlign(int i, int i2) {
        try {
            this.textDelegate.setAlign(i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getAlignX() {
        try {
            return this.textDelegate.getAlignX();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public int getAlignY() {
        try {
            return this.textDelegate.getAlignY();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setVisible(boolean z) {
        try {
            this.textDelegate.setVisible(z);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isVisible() {
        try {
            return this.textDelegate.isVisible();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean equals(Object obj) {
        try {
            if (obj instanceof Text) {
                return this.textDelegate.equalsRemote(((Text) obj).textDelegate);
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public int hashCode() {
        return this.textDelegate.hashCodeRemote();
    }

    public void setObject(Object obj) {
        this.textDelegate.setObject(obj);
    }

    public Object getObject() {
        return this.textDelegate.getObject();
    }

    public void setRotate(float f2) {
        try {
            this.textDelegate.setRotateAngle(f2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getRotate() {
        return this.textDelegate.getRotateAngle();
    }

    public void setZIndex(float f2) {
        this.textDelegate.setZIndex(f2);
    }

    public float getZIndex() {
        return this.textDelegate.getZIndex();
    }
}