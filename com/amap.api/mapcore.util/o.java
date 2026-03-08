package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLES20;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.GL3DModelOptions;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Gl3dModelManager.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1728b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IAMapDelegate f1729c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f1727a = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<cr> f1730d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<Integer> f1731e = new ArrayList();

    public o(Context context, IAMapDelegate iAMapDelegate) {
        this.f1728b = context;
        this.f1729c = iAMapDelegate;
    }

    public GL3DModel a(GL3DModelOptions gL3DModelOptions) {
        GL3DModel gL3DModel;
        if (gL3DModelOptions == null) {
            return null;
        }
        cr crVar = new cr(this, gL3DModelOptions, this.f1729c);
        StringBuilder sb = new StringBuilder();
        sb.append("model_");
        long j = this.f1727a;
        this.f1727a = 1 + j;
        sb.append(j);
        crVar.a(sb.toString());
        synchronized (this.f1730d) {
            this.f1730d.add(crVar);
            gL3DModel = new GL3DModel(crVar);
        }
        return gL3DModel;
    }

    public void a() {
        for (cr crVar : this.f1730d) {
            if (crVar.isVisible()) {
                crVar.a();
            }
        }
    }

    public void b() {
        List<cr> list = this.f1730d;
        if (list != null) {
            list.clear();
        }
    }

    public void c() {
        List<cr> list = this.f1730d;
        if (list != null) {
            Iterator<cr> it = list.iterator();
            while (it.hasNext()) {
                it.next().destroy();
            }
            this.f1730d.clear();
        }
    }

    public void d() {
        List<Integer> list = this.f1731e;
        if (list != null) {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                GLES20.glDeleteTextures(1, new int[]{it.next().intValue()}, 0);
            }
        }
    }

    public void a(String str) {
        try {
            if (this.f1730d == null || this.f1730d.size() <= 0) {
                return;
            }
            cr crVar = null;
            for (int i = 0; i < this.f1730d.size(); i++) {
                crVar = this.f1730d.get(i);
                if (str.equals(crVar.getId())) {
                    break;
                }
            }
            if (crVar != null) {
                this.f1730d.remove(crVar);
                crVar.destroy();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(int i) {
        this.f1731e.add(Integer.valueOf(i));
    }

    public boolean a(cr crVar) {
        return this.f1730d.contains(crVar);
    }
}