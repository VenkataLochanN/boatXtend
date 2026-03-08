package com.amap.api.maps.model.particle;

import android.text.TextUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BaseOverlay;
import com.autonavi.amap.api.mapcore.overlays.IParticleLatyer;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class ParticleOverlay extends BaseOverlay {
    private WeakReference<IGlOverlayLayer> glOverlayLayerRef;
    private ParticleOverlayOptions options;
    private IParticleLatyer overlayDelegate;

    public ParticleOverlay(IParticleLatyer iParticleLatyer) {
        super("");
        this.overlayDelegate = iParticleLatyer;
    }

    public ParticleOverlay(IGlOverlayLayer iGlOverlayLayer, ParticleOverlayOptions particleOverlayOptions, String str) {
        super(str);
        this.glOverlayLayerRef = new WeakReference<>(iGlOverlayLayer);
        this.options = particleOverlayOptions;
    }

    public void setVisible(boolean z) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setVisible(z);
            } else if (this.options != null) {
                this.options.setVisible(z);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.destroy();
            } else {
                IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
                if (iGlOverlayLayer != null) {
                    iGlOverlayLayer.removeOverlay(this.overlayName);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setStartParticleSize(int i, int i2) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setStartParticleSize(i, i2);
            } else if (this.options != null) {
                this.options.setStartParticleSize(i, i2);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setMaxParticles(int i) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setMaxParticles(i);
            } else if (this.options != null) {
                this.options.setMaxParticles(i);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setDuration(long j) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setDuration(j);
            } else if (this.options != null) {
                this.options.setDuration(j);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setParticleLifeTime(long j) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setParticleLifeTime(j);
            } else if (this.options != null) {
                this.options.setParticleLifeTime(j);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setParticleStartSpeed(VelocityGenerate velocityGenerate) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setParticleStartSpeed(velocityGenerate);
            } else if (this.options != null) {
                this.options.setParticleStartSpeed(velocityGenerate);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLoop(boolean z) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setLoop(z);
            } else if (this.options != null) {
                this.options.setLoop(z);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setParticleShapeModule(ParticleShapeModule particleShapeModule) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setParticleShapeModule(particleShapeModule);
            } else if (this.options != null) {
                this.options.setParticleShapeModule(particleShapeModule);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setParticleEmission(ParticleEmissionModule particleEmissionModule) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setParticleEmission(particleEmissionModule);
            } else if (this.options != null) {
                this.options.setParticleEmissionModule(particleEmissionModule);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getCurrentParticleNum() {
        try {
            if (this.overlayDelegate != null) {
                return this.overlayDelegate.getCurrentParticleNum();
            }
            IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
            if (iGlOverlayLayer != null) {
                return iGlOverlayLayer.getCurrentParticleNum(this.overlayName);
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setParticleOverLifeModule(ParticleOverLifeModule particleOverLifeModule) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setParticleOverLifeModule(particleOverLifeModule);
            } else if (this.options != null) {
                this.options.setParticleOverLifeModule(particleOverLifeModule);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setStartColor(ColorGenerate colorGenerate) {
        try {
            if (this.overlayDelegate != null) {
                this.overlayDelegate.setStartColor(colorGenerate);
            } else if (this.options != null) {
                this.options.setParticleStartColor(colorGenerate);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() {
        IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
        if (TextUtils.isEmpty(this.overlayName) || iGlOverlayLayer == null) {
            return;
        }
        iGlOverlayLayer.updateOption(this.overlayName, this.options);
    }
}