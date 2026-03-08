package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Build;
import android.os.RemoteException;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.particle.ColorGenerate;
import com.amap.api.maps.model.particle.ParticleEmissionModule;
import com.amap.api.maps.model.particle.ParticleOverLifeModule;
import com.amap.api.maps.model.particle.ParticleOverlayOptions;
import com.amap.api.maps.model.particle.ParticleShapeModule;
import com.amap.api.maps.model.particle.VelocityGenerate;
import com.autonavi.amap.api.mapcore.overlays.IParticleLatyer;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ParticleLayerDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cx implements IParticleLatyer, IOverlayDelegate {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private de f495e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private IGlOverlayLayer f496f;
    private String i;
    private BitmapDescriptor j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f494d = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f497g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f498h = 1.0f;
    private boolean k = false;
    private List<x> l = new ArrayList();
    private int m = 0;
    private ParticleOverlayOptions n = new ParticleOverlayOptions();
    private boolean o = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f491a = 1.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f492b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f493c = 0;
    private float p = -1.0f;
    private float q = -1.0f;
    private float[] r = new float[16];
    private float[] s = new float[16];
    private float[] t = new float[16];

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return false;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void pause() {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void start() {
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void stop() {
    }

    public cx(IGlOverlayLayer iGlOverlayLayer) {
        this.f496f = iGlOverlayLayer;
        try {
            this.i = getId();
        } catch (RemoteException e2) {
            hn.c(e2, "ParticleLayerDelegateImp", "create");
            e2.printStackTrace();
        }
    }

    public void a(ParticleOverlayOptions particleOverlayOptions) {
        synchronized (this) {
            if (particleOverlayOptions != null) {
                setCustomTexture(particleOverlayOptions.getIcon());
                this.n.setMaxParticles(particleOverlayOptions.getMaxParticles());
                this.n.setLoop(particleOverlayOptions.isLoop());
                this.n.setDuration(particleOverlayOptions.getDuration());
                this.n.setParticleLifeTime(particleOverlayOptions.getParticleLifeTime());
                this.n.setParticleEmissionModule(particleOverlayOptions.getParticleEmissionModule());
                this.n.setParticleShapeModule(particleOverlayOptions.getParticleShapeModule());
                this.n.setParticleStartSpeed(particleOverlayOptions.getParticleStartSpeed());
                this.n.setParticleStartColor(particleOverlayOptions.getParticleStartColor());
                this.n.setParticleOverLifeModule(particleOverlayOptions.getParticleOverLifeModule());
                this.n.setStartParticleSize(particleOverlayOptions.getStartParticleW(), particleOverlayOptions.getstartParticleH());
                this.n.zIndex(particleOverlayOptions.getZIndex());
                this.f498h = this.n.getZIndex();
                this.n.setVisible(particleOverlayOptions.isVisibile());
                this.f497g = this.n.isVisibile();
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.i == null) {
            this.i = this.f496f.createId("Particle");
        }
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) throws RemoteException {
        this.f498h = f2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.f498h;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer, com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.f497g = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.f497g;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer, com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        Bitmap bitmap;
        List<x> list = this.l;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.l.size(); i++) {
                x xVar = this.l.get(i);
                if (xVar != null) {
                    IGlOverlayLayer iGlOverlayLayer = this.f496f;
                    if (iGlOverlayLayer != null) {
                        iGlOverlayLayer.addRecycleTextureIds(xVar);
                    }
                    if (this.f496f.getMap() != null) {
                        this.f496f.getMap().removeTextureItem(xVar.p());
                    }
                }
            }
            this.l.clear();
        }
        BitmapDescriptor bitmapDescriptor = this.j;
        if (bitmapDescriptor != null && (bitmap = bitmapDescriptor.getBitmap()) != null) {
            er.b(bitmap);
            this.j = null;
        }
        long j = this.f494d;
        if (j != 0) {
            AMapNativeParticleSystem.nativeDestroy(j);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        de deVar;
        if (this.f495e == null) {
            this.f495e = this.f496f.getGLShaderManager();
        }
        if (this.f495e == null) {
            return;
        }
        if (this.f494d == 0) {
            this.f494d = AMapNativeParticleSystem.nativeCreate();
            long j = this.f494d;
            if (j != 0 && (deVar = this.f495e) != null) {
                AMapNativeParticleSystem.nativeSetGLShaderManager(j, deVar.a());
            }
        }
        if (this.f494d != 0) {
            synchronized (this) {
                if (this.o) {
                    d();
                    this.o = false;
                }
            }
            this.m = a();
            int i = this.m;
            if (i == 0) {
                return;
            }
            AMapNativeParticleSystem.nativeSetTextureId(this.f494d, i);
            IGlOverlayLayer iGlOverlayLayer = this.f496f;
            if (iGlOverlayLayer != null) {
                iGlOverlayLayer.setRunLowFrame(false);
            }
            if (this.f492b != mapConfig.getMapWidth() || this.f493c != mapConfig.getMapHeight()) {
                this.f492b = mapConfig.getMapWidth();
                this.f493c = mapConfig.getMapHeight();
                int i2 = this.f492b;
                int i3 = this.f493c;
                this.f491a = i2 > i3 ? i2 / i3 : i3 / i2;
                if (this.f492b > this.f493c) {
                    this.p = -this.f491a;
                    this.q = 1.0f;
                } else {
                    this.p = -1.0f;
                    this.q = this.f491a;
                }
                float[] fArr = this.r;
                float f2 = this.p;
                float f3 = this.q;
                Matrix.orthoM(fArr, 0, f2, -f2, -f3, f3, 3.0f, 7.0f);
                Matrix.setLookAtM(this.s, 0, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            }
            Matrix.multiplyMM(this.t, 0, this.r, 0, this.s, 0);
            Matrix.translateM(this.t, 0, this.p, this.q, 0.0f);
            Matrix.scaleM(this.t, 0, Math.abs(this.p * 2.0f) / this.f492b, Math.abs(this.q * 2.0f) / this.f493c, 0.0f);
            AMapNativeParticleSystem.nativeRender(this.f494d, (float[]) this.t.clone(), mapConfig.getProjectionMatrix(), (int) mapConfig.getSX(), (int) mapConfig.getSY(), mapConfig.getSZ(), this.f492b, this.f493c);
        }
    }

    private int a() {
        if (!this.k) {
            int iA = a(Build.VERSION.SDK_INT >= 12, this.j);
            this.k = true;
            return iA;
        }
        return this.m;
    }

    private void b() {
        IGlOverlayLayer iGlOverlayLayer;
        List<x> list = this.l;
        if (list != null) {
            for (x xVar : list) {
                if (xVar != null && (iGlOverlayLayer = this.f496f) != null) {
                    iGlOverlayLayer.addRecycleTextureIds(xVar);
                }
            }
            this.l.clear();
        }
    }

    private void a(x xVar) {
        if (xVar != null) {
            this.l.add(xVar);
            xVar.m();
        }
    }

    private int a(boolean z, BitmapDescriptor bitmapDescriptor) {
        x xVar;
        b();
        if (z) {
            xVar = this.f496f.getTextureItem(bitmapDescriptor);
            if (xVar != null) {
                int iK = xVar.k();
                a(xVar);
                return iK;
            }
        } else {
            xVar = null;
        }
        int iC = 0;
        if (xVar == null) {
            xVar = new x(bitmapDescriptor, 0);
        }
        Bitmap bitmap = bitmapDescriptor.getBitmap();
        if (bitmap != null && !bitmap.isRecycled()) {
            iC = c();
            xVar.a(iC);
            if (z) {
                this.f496f.getMap().addTextureItem(xVar);
            }
            a(xVar);
            er.b(iC, bitmap, true);
        }
        return iC;
    }

    private int c() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            return;
        }
        synchronized (this) {
            if (bitmapDescriptor.equals(this.j)) {
                return;
            }
            this.k = false;
            this.j = bitmapDescriptor;
        }
    }

    private void d() {
        if (this.f494d != 0) {
            setMaxParticles(this.n.getMaxParticles());
            setDuration(this.n.getDuration());
            setLoop(this.n.isLoop());
            setPreWram(true);
            setParticleLifeTime(this.n.getParticleLifeTime());
            setParticleStartSpeed(this.n.getParticleStartSpeed());
            if (this.n.getParticleEmissionModule() != null) {
                setParticleEmission(this.n.getParticleEmissionModule());
            }
            if (this.n.getParticleShapeModule() != null) {
                setParticleShapeModule(this.n.getParticleShapeModule());
            }
            if (this.n.getParticleStartColor() != null) {
                setStartColor(this.n.getParticleStartColor());
            }
            if (this.n.getParticleOverLifeModule() != null) {
                setParticleOverLifeModule(this.n.getParticleOverLifeModule());
            }
            setStartParticleSize(this.n.getStartParticleW(), this.n.getstartParticleH());
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setStartParticleSize(int i, int i2) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setStartParticleSize(i, i2);
        }
        long j = this.f494d;
        if (j != 0) {
            AMapNativeParticleSystem.setStartParticleSize(j, i, i2);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setMaxParticles(int i) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setMaxParticles(i);
        }
        long j = this.f494d;
        if (j != 0) {
            AMapNativeParticleSystem.setMaxParticles(j, i);
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setDuration(long j) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setDuration(j);
        }
        long j2 = this.f494d;
        if (j2 != 0) {
            AMapNativeParticleSystem.setDuration(j2, j);
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setParticleLifeTime(long j) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setParticleLifeTime(j);
        }
        long j2 = this.f494d;
        if (j2 != 0) {
            AMapNativeParticleSystem.setParticleLifeTime(j2, j);
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setParticleStartSpeed(VelocityGenerate velocityGenerate) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setParticleStartSpeed(velocityGenerate);
        }
        if (this.f494d != 0 && velocityGenerate != null) {
            if (velocityGenerate.getNativeInstance() == 0) {
                velocityGenerate.createNativeInstace();
            }
            AMapNativeParticleSystem.setParticleStartSpeed(this.f494d, velocityGenerate.getNativeInstance());
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setLoop(boolean z) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setLoop(z);
        }
        long j = this.f494d;
        if (j != 0) {
            AMapNativeParticleSystem.setLoop(j, z);
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setParticleShapeModule(ParticleShapeModule particleShapeModule) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setParticleShapeModule(particleShapeModule);
        }
        if (this.f494d != 0 && particleShapeModule != null) {
            if (particleShapeModule.getNativeInstance() == 0) {
                particleShapeModule.createNativeInstace();
            }
            AMapNativeParticleSystem.setParticleShapeModule(this.f494d, particleShapeModule.getNativeInstance());
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setParticleEmission(ParticleEmissionModule particleEmissionModule) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setParticleEmissionModule(particleEmissionModule);
        }
        if (this.f494d != 0 && particleEmissionModule != null) {
            if (particleEmissionModule.getNativeInstance() == 0) {
                particleEmissionModule.createNativeInstace();
            }
            AMapNativeParticleSystem.setParticleEmission(this.f494d, particleEmissionModule.getNativeInstance());
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public int getCurrentParticleNum() {
        long j = this.f494d;
        if (j != 0) {
            return AMapNativeParticleSystem.getCurrentParticleNum(j);
        }
        return 0;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setParticleOverLifeModule(ParticleOverLifeModule particleOverLifeModule) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setParticleOverLifeModule(particleOverLifeModule);
        }
        if (this.f494d != 0 && particleOverLifeModule != null) {
            if (particleOverLifeModule.getNativeInstance() == 0) {
                particleOverLifeModule.createNativeInstace();
            }
            AMapNativeParticleSystem.setParticleOverLifeModule(this.f494d, particleOverLifeModule.getNativeInstance());
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setPreWram(boolean z) {
        long j = this.f494d;
        if (j != 0) {
            AMapNativeParticleSystem.setPreWram(j, z);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IParticleLatyer
    public void setStartColor(ColorGenerate colorGenerate) {
        ParticleOverlayOptions particleOverlayOptions = this.n;
        if (particleOverlayOptions != null) {
            particleOverlayOptions.setParticleStartColor(colorGenerate);
        }
        if (this.f494d != 0 && colorGenerate != null) {
            if (colorGenerate.getNativeInstance() == 0) {
                colorGenerate.createNativeInstace();
            }
            AMapNativeParticleSystem.setStartColor(this.f494d, colorGenerate.getNativeInstance());
        } else if (this.n != null) {
            synchronized (this) {
                this.o = true;
            }
        }
    }
}