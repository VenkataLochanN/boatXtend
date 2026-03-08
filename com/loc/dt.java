package com.loc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

/* JADX INFO: compiled from: AmapSensorManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dt implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SensorManager f5050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Sensor f5051b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Sensor f5052c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Sensor f5053d;
    private Context s;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5054e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f5055f = 0.0d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f5056g = 0.0f;
    private float t = 1013.25f;
    private float u = 0.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    Handler f5057h = new Handler();
    double i = 0.0d;
    double j = 0.0d;
    double k = 0.0d;
    double l = 0.0d;
    double[] m = new double[3];
    volatile double n = 0.0d;
    long o = 0;
    long p = 0;
    final int q = 100;
    final int r = 30;

    public dt(Context context) {
        this.s = null;
        this.f5050a = null;
        this.f5051b = null;
        this.f5052c = null;
        this.f5053d = null;
        try {
            this.s = context;
            if (this.f5050a == null) {
                this.f5050a = (SensorManager) this.s.getSystemService("sensor");
            }
            try {
                this.f5051b = this.f5050a.getDefaultSensor(6);
            } catch (Throwable unused) {
            }
            try {
                this.f5052c = this.f5050a.getDefaultSensor(11);
            } catch (Throwable unused2) {
            }
            try {
                this.f5053d = this.f5050a.getDefaultSensor(1);
            } catch (Throwable unused3) {
            }
        } catch (Throwable th) {
            ej.a(th, "AMapSensorManager", "<init>");
        }
    }

    public final void a() {
        SensorManager sensorManager = this.f5050a;
        if (sensorManager == null || this.f5054e) {
            return;
        }
        this.f5054e = true;
        try {
            if (this.f5051b != null) {
                sensorManager.registerListener(this, this.f5051b, 3, this.f5057h);
            }
        } catch (Throwable th) {
            ej.a(th, "AMapSensorManager", "registerListener mPressure");
        }
        try {
            if (this.f5052c != null) {
                this.f5050a.registerListener(this, this.f5052c, 3, this.f5057h);
            }
        } catch (Throwable th2) {
            ej.a(th2, "AMapSensorManager", "registerListener mRotationVector");
        }
        try {
            if (this.f5053d != null) {
                this.f5050a.registerListener(this, this.f5053d, 3, this.f5057h);
            }
        } catch (Throwable th3) {
            ej.a(th3, "AMapSensorManager", "registerListener mAcceleroMeterVector");
        }
    }

    public final void b() {
        SensorManager sensorManager = this.f5050a;
        if (sensorManager == null || !this.f5054e) {
            return;
        }
        this.f5054e = false;
        try {
            if (this.f5051b != null) {
                sensorManager.unregisterListener(this, this.f5051b);
            }
        } catch (Throwable unused) {
        }
        try {
            if (this.f5052c != null) {
                this.f5050a.unregisterListener(this, this.f5052c);
            }
        } catch (Throwable unused2) {
        }
        try {
            if (this.f5053d != null) {
                this.f5050a.unregisterListener(this, this.f5053d);
            }
        } catch (Throwable unused3) {
        }
    }

    public final float c() {
        return this.u;
    }

    public final double d() {
        return this.l;
    }

    public final void e() {
        try {
            b();
            this.f5051b = null;
            this.f5052c = null;
            this.f5050a = null;
            this.f5053d = null;
            this.f5054e = false;
        } catch (Throwable th) {
            ej.a(th, "AMapSensorManager", "destroy");
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr;
        if (sensorEvent == null) {
            return;
        }
        try {
            int type = sensorEvent.sensor.getType();
            if (type == 1) {
                if (this.f5053d != null) {
                    float[] fArr2 = (float[]) sensorEvent.values.clone();
                    this.m[0] = (this.m[0] * 0.800000011920929d) + ((double) (fArr2[0] * 0.19999999f));
                    this.m[1] = (this.m[1] * 0.800000011920929d) + ((double) (fArr2[1] * 0.19999999f));
                    this.m[2] = (this.m[2] * 0.800000011920929d) + ((double) (fArr2[2] * 0.19999999f));
                    this.i = ((double) fArr2[0]) - this.m[0];
                    this.j = ((double) fArr2[1]) - this.m[1];
                    this.k = ((double) fArr2[2]) - this.m[2];
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - this.o >= 100) {
                        double dSqrt = Math.sqrt((this.i * this.i) + (this.j * this.j) + (this.k * this.k));
                        this.p++;
                        this.o = jCurrentTimeMillis;
                        this.n += dSqrt;
                        if (this.p >= 30) {
                            this.l = this.n / this.p;
                            this.n = 0.0d;
                            this.p = 0L;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (type == 6) {
                try {
                    if (this.f5051b != null) {
                        float[] fArr3 = (float[]) sensorEvent.values.clone();
                        if (fArr3 != null) {
                            this.f5056g = fArr3[0];
                        }
                        if (fArr3 != null) {
                            this.f5055f = ep.a(SensorManager.getAltitude(this.t, fArr3[0]));
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            if (type != 11) {
                return;
            }
            try {
                if (this.f5052c == null || (fArr = (float[]) sensorEvent.values.clone()) == null) {
                    return;
                }
                float[] fArr4 = new float[9];
                SensorManager.getRotationMatrixFromVector(fArr4, fArr);
                SensorManager.getOrientation(fArr4, new float[3]);
                this.u = (float) Math.toDegrees(r12[0]);
                this.u = (float) Math.floor(this.u > 0.0f ? this.u : this.u + 360.0f);
            } catch (Throwable unused2) {
            }
        } catch (Throwable unused3) {
        }
    }
}