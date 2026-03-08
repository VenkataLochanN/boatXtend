package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.an;

/* JADX INFO: compiled from: ScaleRotateGestureDetector.java */
/* JADX INFO: loaded from: classes.dex */
public class ao extends an {

    /* JADX INFO: compiled from: ScaleRotateGestureDetector.java */
    public static abstract class a implements an.a {
        public abstract boolean a(ao aoVar);

        public abstract boolean b(ao aoVar);

        public abstract void c(ao aoVar);

        @Override // com.amap.api.mapcore.util.an.a
        public boolean a(an anVar) {
            return a((ao) anVar);
        }

        @Override // com.amap.api.mapcore.util.an.a
        public boolean b(an anVar) {
            return b((ao) anVar);
        }

        @Override // com.amap.api.mapcore.util.an.a
        public void c(an anVar) {
            c((ao) anVar);
        }
    }

    public ao(Context context, a aVar) {
        super(context, aVar);
    }

    public float l() {
        return (float) (((Math.atan2(i(), h()) - Math.atan2(f(), e())) * 180.0d) / 3.141592653589793d);
    }
}