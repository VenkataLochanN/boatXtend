package com.baidu.location.a;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.location.indoor.mapversion.IndoorJni;

/* JADX INFO: loaded from: classes.dex */
public class n implements SensorEventListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static n f2156d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float[] f2157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float[] f2158b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SensorManager f2159c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f2160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2161f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2162g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2163h = false;

    private n() {
    }

    public static synchronized n a() {
        if (f2156d == null) {
            f2156d = new n();
        }
        return f2156d;
    }

    public void a(boolean z) {
        this.f2161f = z;
    }

    public synchronized void b() {
        if (this.f2163h) {
            return;
        }
        if (this.f2161f) {
            if (this.f2159c == null) {
                this.f2159c = (SensorManager) com.baidu.location.f.getServiceContext().getSystemService("sensor");
            }
            if (this.f2159c != null) {
                Sensor defaultSensor = this.f2159c.getDefaultSensor(11);
                if (defaultSensor != null && this.f2161f) {
                    this.f2159c.registerListener(this, defaultSensor, 3);
                }
                Sensor defaultSensor2 = this.f2159c.getDefaultSensor(2);
                if (defaultSensor2 != null && this.f2161f) {
                    this.f2159c.registerListener(this, defaultSensor2, 3);
                }
            }
            this.f2163h = true;
        }
    }

    public void b(boolean z) {
        this.f2162g = z;
    }

    public synchronized void c() {
        if (this.f2163h) {
            if (this.f2159c != null) {
                this.f2159c.unregisterListener(this);
                this.f2159c = null;
            }
            this.f2163h = false;
        }
    }

    public boolean d() {
        return this.f2161f;
    }

    public float e() {
        return this.f2160e;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type == 2) {
            this.f2158b = (float[]) sensorEvent.values.clone();
            float[] fArr = this.f2158b;
            double dSqrt = Math.sqrt((fArr[0] * fArr[0]) + (fArr[1] * fArr[1]) + (fArr[2] * fArr[2]));
            if (this.f2158b != null) {
                try {
                    if (com.baidu.location.indoor.g.a().e()) {
                        IndoorJni.setPfGeomag(dSqrt);
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            return;
        }
        if (type != 11) {
            return;
        }
        this.f2157a = (float[]) sensorEvent.values.clone();
        float[] fArr2 = this.f2157a;
        if (fArr2 != null) {
            float[] fArr3 = new float[9];
            try {
                SensorManager.getRotationMatrixFromVector(fArr3, fArr2);
                SensorManager.getOrientation(fArr3, new float[3]);
                this.f2160e = (float) Math.toDegrees(r5[0]);
                this.f2160e = (float) Math.floor(this.f2160e >= 0.0f ? this.f2160e : this.f2160e + 360.0f);
            } catch (Exception unused) {
                this.f2160e = 0.0f;
            }
        }
    }
}