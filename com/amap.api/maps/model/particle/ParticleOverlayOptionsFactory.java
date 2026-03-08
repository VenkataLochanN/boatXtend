package com.amap.api.maps.model.particle;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class ParticleOverlayOptionsFactory {
    public static final int PARTICLE_TYPE_HAZE = 3;
    public static final int PARTICLE_TYPE_RAIN = 1;
    public static final int PARTICLE_TYPE_SNOWY = 2;
    public static final int PARTICLE_TYPE_SUNNY = 0;

    public static List<ParticleOverlayOptions> defaultOptions(int i) {
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            arrayList.addAll(d());
        } else if (i == 1) {
            arrayList.add(c());
        } else if (i == 2) {
            arrayList.add(b());
        } else if (i == 3) {
            arrayList.addAll(a());
        }
        return arrayList;
    }

    private static List<ParticleOverlayOptions> a() {
        ArrayList arrayList = new ArrayList();
        ParticleOverlayOptions particleOverlayOptions = new ParticleOverlayOptions();
        particleOverlayOptions.setLoop(false);
        particleOverlayOptions.setMaxParticles(1);
        particleOverlayOptions.setDuration(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions.setParticleEmissionModule(new ParticleEmissionModule(1, (int) DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT));
        particleOverlayOptions.setParticleShapeModule(new SinglePointParticleShape(0.5f, 0.5f, 0.0f, true));
        particleOverlayOptions.setParticleLifeTime(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions.setParticleStartSpeed(new RandomVelocityBetweenTwoConstants(-10.0f, -0.0f, -0.0f, -20.0f, 0.0f, 0.0f));
        BitmapDescriptor bitmapDescriptorFromAsset = BitmapDescriptorFactory.fromAsset("map_custom/particle/fog.png");
        if (bitmapDescriptorFromAsset != null) {
            particleOverlayOptions.icon(bitmapDescriptorFromAsset);
            particleOverlayOptions.setStartParticleSize(bitmapDescriptorFromAsset.getWidth() * 5, bitmapDescriptorFromAsset.getWidth() * 5);
        }
        arrayList.add(particleOverlayOptions);
        ParticleOverlayOptions particleOverlayOptions2 = new ParticleOverlayOptions();
        particleOverlayOptions2.setMaxParticles(1000);
        particleOverlayOptions2.setDuration(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions2.setParticleEmissionModule(new ParticleEmissionModule(30, (int) 2500));
        particleOverlayOptions2.setLoop(true);
        particleOverlayOptions2.setParticleShapeModule(new RectParticleShape(0.5f, 0.5f, 1.0f, 1.0f, true));
        particleOverlayOptions2.setParticleLifeTime(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions2.setParticleStartSpeed(new RandomVelocityBetweenTwoConstants(-100.0f, -100.0f, -1.0f, -200.0f, 100.0f, 1.0f));
        BitmapDescriptor bitmapDescriptorFromAsset2 = BitmapDescriptorFactory.fromAsset("map_custom/particle/haze.png");
        if (bitmapDescriptorFromAsset2 != null) {
            particleOverlayOptions2.icon(bitmapDescriptorFromAsset2);
            particleOverlayOptions2.setStartParticleSize(bitmapDescriptorFromAsset2.getWidth(), bitmapDescriptorFromAsset2.getHeight());
        }
        particleOverlayOptions2.setParticleStartColor(new RandomColorBetWeenTwoConstants(255.0f, 255.0f, 255.0f, 102.0f, 255.0f, 255.0f, 255.0f, 255.0f));
        arrayList.add(particleOverlayOptions2);
        return arrayList;
    }

    private static ParticleOverlayOptions b() {
        ParticleOverlayOptions particleOverlayOptions = new ParticleOverlayOptions();
        particleOverlayOptions.setMaxParticles(1000);
        particleOverlayOptions.setDuration(BootloaderScanner.TIMEOUT);
        particleOverlayOptions.setParticleEmissionModule(new ParticleEmissionModule(20, 1000));
        particleOverlayOptions.setLoop(true);
        particleOverlayOptions.setParticleShapeModule(new RectParticleShape(0.0f, 0.0f, 1.0f, 0.1f, true));
        particleOverlayOptions.setParticleLifeTime(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions.setParticleStartSpeed(new RandomVelocityBetweenTwoConstants(-50.0f, 100.0f, 0.0f, 50.0f, 200.0f, 0.0f));
        BitmapDescriptor bitmapDescriptorFromAsset = BitmapDescriptorFactory.fromAsset("map_custom/particle/snow.png");
        if (bitmapDescriptorFromAsset != null) {
            particleOverlayOptions.icon(bitmapDescriptorFromAsset);
            particleOverlayOptions.setStartParticleSize(bitmapDescriptorFromAsset.getWidth(), bitmapDescriptorFromAsset.getHeight());
        }
        return particleOverlayOptions;
    }

    private static ParticleOverlayOptions c() {
        ParticleOverlayOptions particleOverlayOptions = new ParticleOverlayOptions();
        particleOverlayOptions.setMaxParticles(1000);
        particleOverlayOptions.setDuration(BootloaderScanner.TIMEOUT);
        particleOverlayOptions.setParticleEmissionModule(new ParticleEmissionModule(100, 1000));
        particleOverlayOptions.setLoop(true);
        particleOverlayOptions.setParticleLifeTime(BootloaderScanner.TIMEOUT);
        particleOverlayOptions.setParticleStartSpeed(new RandomVelocityBetweenTwoConstants(10.0f, 1000.0f, 0.0f, 100.0f, 1000.0f, 0.0f));
        particleOverlayOptions.setParticleShapeModule(new RectParticleShape(0.0f, 0.0f, 1.0f, 0.1f, true));
        BitmapDescriptor bitmapDescriptorFromAsset = BitmapDescriptorFactory.fromAsset("map_custom/particle/rain.png");
        if (bitmapDescriptorFromAsset != null) {
            particleOverlayOptions.icon(bitmapDescriptorFromAsset);
            particleOverlayOptions.setStartParticleSize(bitmapDescriptorFromAsset.getWidth() * 2, bitmapDescriptorFromAsset.getHeight() * 2);
        }
        return particleOverlayOptions;
    }

    private static List<ParticleOverlayOptions> d() {
        ArrayList arrayList = new ArrayList();
        ParticleOverlayOptions particleOverlayOptions = new ParticleOverlayOptions();
        particleOverlayOptions.setMaxParticles(1);
        particleOverlayOptions.setDuration(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        int i = (int) 2500;
        particleOverlayOptions.setParticleEmissionModule(new ParticleEmissionModule(1, i));
        particleOverlayOptions.setLoop(true);
        particleOverlayOptions.setParticleShapeModule(new SinglePointParticleShape(0.0f, 0.0f, 0.0f));
        particleOverlayOptions.setParticleLifeTime(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions.setParticleStartSpeed(new RandomVelocityBetweenTwoConstants(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        particleOverlayOptions.setParticleOverLifeModule(new ParticleOverLifeModule());
        particleOverlayOptions.icon(BitmapDescriptorFactory.fromAsset("map_custom/particle/sun_0.png"));
        particleOverlayOptions.setStartParticleSize(1000, 1000);
        arrayList.add(particleOverlayOptions);
        ParticleOverlayOptions particleOverlayOptions2 = new ParticleOverlayOptions();
        particleOverlayOptions2.setMaxParticles(1);
        particleOverlayOptions2.setDuration(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions2.setParticleEmissionModule(new ParticleEmissionModule(1, i));
        particleOverlayOptions2.setLoop(true);
        particleOverlayOptions2.setParticleShapeModule(new SinglePointParticleShape(0.0f, 0.0f, 0.0f));
        particleOverlayOptions2.setParticleLifeTime(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        particleOverlayOptions2.setParticleStartSpeed(new RandomVelocityBetweenTwoConstants(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        ParticleOverLifeModule particleOverLifeModule = new ParticleOverLifeModule();
        particleOverLifeModule.setRotateOverLife(new ConstantRotationOverLife(45.0f));
        particleOverlayOptions2.setParticleOverLifeModule(particleOverLifeModule);
        particleOverlayOptions2.icon(BitmapDescriptorFactory.fromAsset("map_custom/particle/sun_1.png"));
        particleOverlayOptions2.setStartParticleSize(1000, 1000);
        arrayList.add(particleOverlayOptions2);
        return arrayList;
    }
}