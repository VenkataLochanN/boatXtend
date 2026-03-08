package com.king.zxing;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import com.king.zxing.camera.CameraManager;
import com.king.zxing.camera.FrontLightMode;

/* JADX INFO: loaded from: classes3.dex */
final class AmbientLightManager implements SensorEventListener {
    protected static final float BRIGHT_ENOUGH_LUX = 100.0f;
    protected static final float TOO_DARK_LUX = 45.0f;
    private CameraManager cameraManager;
    private final Context context;
    private Sensor lightSensor;
    private float tooDarkLux = TOO_DARK_LUX;
    private float brightEnoughLux = BRIGHT_ENOUGH_LUX;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    AmbientLightManager(Context context) {
        this.context = context;
    }

    void start(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        if (FrontLightMode.readPref(PreferenceManager.getDefaultSharedPreferences(this.context)) == FrontLightMode.AUTO) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            this.lightSensor = sensorManager.getDefaultSensor(5);
            Sensor sensor = this.lightSensor;
            if (sensor != null) {
                sensorManager.registerListener(this, sensor, 3);
            }
        }
    }

    void stop() {
        if (this.lightSensor != null) {
            ((SensorManager) this.context.getSystemService("sensor")).unregisterListener(this);
            this.cameraManager = null;
            this.lightSensor = null;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f2 = sensorEvent.values[0];
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            if (f2 <= this.tooDarkLux) {
                cameraManager.sensorChanged(true, f2);
            } else if (f2 >= this.brightEnoughLux) {
                cameraManager.sensorChanged(false, f2);
            }
        }
    }

    public void setTooDarkLux(float f2) {
        this.tooDarkLux = f2;
    }

    public void setBrightEnoughLux(float f2) {
        this.brightEnoughLux = f2;
    }
}