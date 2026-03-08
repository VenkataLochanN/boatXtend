package com.ido.common.base;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class OrientationListener extends OrientationEventListener {
    private int mRotation;

    private static boolean isRotation0(int i) {
        return i > 340 || i < 20;
    }

    private static boolean isRotation180(int i) {
        return i > 160 && i < 200;
    }

    private static boolean isRotation270(int i) {
        return i > 250 && i < 290;
    }

    private static boolean isRotation90(int i) {
        return i > 70 && i < 110;
    }

    protected abstract void onRotationChanged(int i);

    public OrientationListener(Context context) {
        super(context);
        this.mRotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        int iTranslate2Rotation;
        if (i == -1 || this.mRotation == (iTranslate2Rotation = translate2Rotation(i, this.mRotation))) {
            return;
        }
        this.mRotation = iTranslate2Rotation;
        onRotationChanged(this.mRotation);
    }

    public static int translate2Rotation(int i, int i2) {
        if (isRotation270(i)) {
            return 3;
        }
        if (isRotation180(i)) {
            return 2;
        }
        if (isRotation90(i)) {
            return 1;
        }
        if (isRotation0(i)) {
            return 0;
        }
        return i2;
    }
}