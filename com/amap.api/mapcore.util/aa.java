package com.amap.api.mapcore.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;
import com.amap.api.maps.model.Marker;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* JADX INFO: compiled from: SensorEventHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class aa implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SensorManager f104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Sensor f105b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f107d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f108e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private IAMapDelegate f109f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Marker f110g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f106c = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f111h = true;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public aa(Context context, IAMapDelegate iAMapDelegate) {
        this.f108e = context.getApplicationContext();
        this.f109f = iAMapDelegate;
        try {
            this.f104a = (SensorManager) context.getSystemService("sensor");
            if (this.f104a != null) {
                this.f105b = this.f104a.getDefaultSensor(3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a() {
        Sensor sensor;
        SensorManager sensorManager = this.f104a;
        if (sensorManager == null || (sensor = this.f105b) == null) {
            return;
        }
        sensorManager.registerListener(this, sensor, 3);
    }

    public void b() {
        Sensor sensor;
        SensorManager sensorManager = this.f104a;
        if (sensorManager == null || (sensor = this.f105b) == null) {
            return;
        }
        sensorManager.unregisterListener(this, sensor);
    }

    public void a(Marker marker) {
        this.f110g = marker;
    }

    public void a(boolean z) {
        this.f111h = z;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(final SensorEvent sensorEvent) {
        try {
            if (System.currentTimeMillis() - this.f106c < 100) {
                return;
            }
            if (this.f109f.getGLMapEngine() == null || this.f109f.getGLMapEngine().getAnimateionsCount() <= 0) {
                eq.a().a(new Runnable() { // from class: com.amap.api.mapcore.util.aa.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (sensorEvent.sensor.getType() != 3) {
                            return;
                        }
                        float fA = (sensorEvent.values[0] + aa.a(aa.this.f108e)) % 360.0f;
                        if (fA > 180.0f) {
                            fA -= 360.0f;
                        } else if (fA < -180.0f) {
                            fA += 360.0f;
                        }
                        if (Math.abs(aa.this.f107d - fA) < 3.0f) {
                            return;
                        }
                        aa aaVar = aa.this;
                        if (Float.isNaN(fA)) {
                            fA = 0.0f;
                        }
                        aaVar.f107d = fA;
                        if (aa.this.f110g != null) {
                            try {
                                if (!aa.this.f111h) {
                                    aa.this.f110g.setRotateAngle(360.0f - aa.this.f107d);
                                } else {
                                    aa.this.f109f.moveCamera(ah.d(aa.this.f107d));
                                    aa.this.f110g.setRotateAngle(-aa.this.f107d);
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                        aa.this.f106c = System.currentTimeMillis();
                    }
                });
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int a(Context context) {
        WindowManager windowManager;
        if (context != null && (windowManager = (WindowManager) context.getSystemService("window")) != null) {
            try {
                int rotation = windowManager.getDefaultDisplay().getRotation();
                if (rotation != 0) {
                    if (rotation == 1) {
                        return 90;
                    }
                    if (rotation == 2) {
                        return 180;
                    }
                    if (rotation == 3) {
                        return -90;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return 0;
    }
}