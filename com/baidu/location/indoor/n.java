package com.baidu.location.indoor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/* JADX INFO: loaded from: classes.dex */
class n implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ m f2691a;

    n(m mVar) {
        this.f2691a = mVar;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type == 1) {
            float[] fArr = (float[]) sensorEvent.values.clone();
            this.f2691a.q = (float[]) fArr.clone();
            if (this.f2691a.k && com.baidu.location.indoor.mapversion.a.b()) {
                com.baidu.location.indoor.mapversion.a.a(1, fArr, sensorEvent.timestamp);
            }
            float[] fArrA = this.f2691a.a(fArr[0], fArr[1], fArr[2]);
            if (m.b(this.f2691a) >= 20) {
                double d2 = (fArrA[0] * fArrA[0]) + (fArrA[1] * fArrA[1]) + (fArrA[2] * fArrA[2]);
                if (this.f2691a.n == 0) {
                    if (d2 > 4.0d) {
                        this.f2691a.n = 1;
                        return;
                    }
                    return;
                } else {
                    if (d2 < 0.009999999776482582d) {
                        this.f2691a.n = 0;
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (type != 3) {
            if (type != 4) {
                return;
            }
            float[] fArr2 = (float[]) sensorEvent.values.clone();
            if (this.f2691a.k && com.baidu.location.indoor.mapversion.a.b()) {
                com.baidu.location.indoor.mapversion.a.a(4, fArr2, sensorEvent.timestamp);
                return;
            }
            return;
        }
        float[] fArr3 = (float[]) sensorEvent.values.clone();
        if (this.f2691a.k && com.baidu.location.indoor.mapversion.a.b()) {
            com.baidu.location.indoor.mapversion.a.a(5, fArr3, sensorEvent.timestamp);
        }
        this.f2691a.P[this.f2691a.O] = fArr3[0];
        m.f(this.f2691a);
        if (this.f2691a.O == this.f2691a.N) {
            this.f2691a.O = 0;
        }
        if (m.h(this.f2691a) >= 20) {
            m mVar = this.f2691a;
            mVar.Q = mVar.i();
            if (!this.f2691a.Q) {
                this.f2691a.f2598d.unregisterListener(this.f2691a.f2596b, this.f2691a.f2602h);
            }
            double[] dArr = this.f2691a.r;
            m mVar2 = this.f2691a;
            dArr[0] = mVar2.a(mVar2.r[0], fArr3[0], 0.7d);
            this.f2691a.r[1] = fArr3[1];
            this.f2691a.r[2] = fArr3[2];
        }
    }
}