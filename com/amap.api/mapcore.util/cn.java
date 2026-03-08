package com.amap.api.mapcore.util;

import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: compiled from: AbstractGlOverlay.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class cn {
    private IAMapDelegate map;

    public abstract int getZIndex();

    public abstract void onDrawFrame(GL10 gl10);

    public void destroy() {
        IAMapDelegate iAMapDelegate = this.map;
    }
}