package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: UpdateStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class jp {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    jp f1480c;

    protected abstract boolean c();

    public jp() {
    }

    public jp(jp jpVar) {
        this.f1480c = jpVar;
    }

    public boolean d() {
        if (b()) {
            return c();
        }
        return false;
    }

    private boolean b() {
        jp jpVar = this.f1480c;
        if (jpVar != null) {
            return jpVar.d();
        }
        return true;
    }

    public void a_(boolean z) {
        jp jpVar = this.f1480c;
        if (jpVar != null) {
            jpVar.a_(z);
        }
    }

    public int a() {
        jp jpVar = this.f1480c;
        return Math.min(Integer.MAX_VALUE, jpVar != null ? jpVar.a() : Integer.MAX_VALUE);
    }

    public void a_(int i) {
        jp jpVar = this.f1480c;
        if (jpVar != null) {
            jpVar.a_(i);
        }
    }
}