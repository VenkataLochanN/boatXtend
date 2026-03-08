package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.interfaces.IMapConfig;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import java.util.ArrayList;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

/* JADX INFO: compiled from: AMapExtraInterfaceManager.java */
/* JADX INFO: loaded from: classes.dex */
public class jx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<jw> f1492a = new ArrayList();

    public void a(jw jwVar) {
        synchronized (jx.class) {
            if (this.f1492a != null) {
                this.f1492a.add(jwVar);
            }
        }
    }

    public void a(Context context, IAMapDelegate iAMapDelegate, boolean z) {
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    jwVar.a(context, iAMapDelegate, z);
                }
            }
        }
    }

    public void a(GL10 gl10, EGLConfig eGLConfig) {
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    jwVar.a(gl10, eGLConfig);
                }
            }
        }
    }

    public void a(GL10 gl10, int i, int i2) {
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    jwVar.a(gl10, i, i2);
                }
            }
        }
    }

    public void a(IGLMapState iGLMapState, IMapConfig iMapConfig) {
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    jwVar.a(iGLMapState, iMapConfig);
                }
            }
        }
    }

    public void a() {
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    jwVar.a();
                }
            }
            this.f1492a.clear();
        }
    }

    public String b() {
        String string;
        synchronized (jx.class) {
            StringBuffer stringBuffer = new StringBuffer();
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    String strB = jwVar.b();
                    if (!TextUtils.isEmpty(strB)) {
                        stringBuffer.append(strB);
                        if (!strB.endsWith(";")) {
                            stringBuffer.append(";");
                        }
                    }
                }
            }
            string = stringBuffer.toString();
        }
        return string;
    }

    public void a(JSONObject jSONObject) {
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    jwVar.a(jSONObject);
                }
            }
        }
    }

    public void a(String str, Object obj) {
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null) {
                    jwVar.a(str, obj);
                }
            }
        }
    }

    public Object a(String str) {
        Object objA;
        synchronized (jx.class) {
            for (jw jwVar : this.f1492a) {
                if (jwVar != null && (objA = jwVar.a(str)) != null) {
                    return objA;
                }
            }
            return null;
        }
    }
}