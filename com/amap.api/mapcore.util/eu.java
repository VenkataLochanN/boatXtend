package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.amap.api.mapcore.util.dc;
import com.amap.api.mapcore.util.ev;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AbstractImageWorker.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class eu {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ev f768a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ev.a f769b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Resources f771d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f772e = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f770c = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Object f773f = new Object();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c f774g = null;

    /* JADX INFO: compiled from: AbstractImageWorker.java */
    public interface c {
        void a();
    }

    protected abstract Bitmap a(Object obj);

    protected eu(Context context) {
        this.f771d = context.getResources();
    }

    public void a(boolean z, dc.a aVar) {
        if (aVar == null) {
            return;
        }
        Bitmap bitmapA = null;
        try {
            if (this.f768a != null) {
                bitmapA = this.f768a.a(aVar.f547a + "-" + aVar.f548b + "-" + aVar.f549c);
            }
            if (bitmapA != null) {
                aVar.a(bitmapA);
                return;
            }
            a aVar2 = new a(aVar);
            aVar.j = aVar2;
            aVar2.a(dq.f644c, Boolean.valueOf(z));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(ev.a aVar) {
        this.f769b = aVar;
        this.f768a = ev.a(this.f769b);
        new b().c(1);
    }

    public void a(boolean z) {
        this.f772e = z;
        b(false);
    }

    protected ev a() {
        return this.f768a;
    }

    public static void a(dc.a aVar) {
        a aVarC = c(aVar);
        if (aVarC != null) {
            aVarC.a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a c(dc.a aVar) {
        if (aVar != null) {
            return aVar.j;
        }
        return null;
    }

    /* JADX INFO: compiled from: AbstractImageWorker.java */
    public class a extends dq<Boolean, Void, Bitmap> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final WeakReference<dc.a> f776e;

        public a(dc.a aVar) {
            this.f776e = new WeakReference<>(aVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amap.api.mapcore.util.dq
        public Bitmap a(Boolean... boolArr) {
            try {
                boolean zBooleanValue = boolArr[0].booleanValue();
                dc.a aVar = this.f776e.get();
                if (aVar == null) {
                    return null;
                }
                String str = aVar.f547a + "-" + aVar.f548b + "-" + aVar.f549c;
                synchronized (eu.this.f773f) {
                    while (eu.this.f770c && !d()) {
                        eu.this.f773f.wait();
                    }
                }
                Bitmap bitmapB = (eu.this.f768a == null || d() || e() == null || eu.this.f772e) ? null : eu.this.f768a.b(str);
                if (zBooleanValue && bitmapB == null && !d() && e() != null && !eu.this.f772e) {
                    synchronized (eu.class) {
                        bitmapB = eu.this.a((Object) aVar);
                    }
                }
                if (bitmapB != null && eu.this.f768a != null) {
                    eu.this.f768a.a(str, bitmapB);
                }
                return bitmapB;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amap.api.mapcore.util.dq
        public void a(Bitmap bitmap) {
            try {
                if (d() || eu.this.f772e) {
                    bitmap = null;
                }
                dc.a aVarE = e();
                if (bitmap == null || bitmap.isRecycled() || aVarE == null) {
                    return;
                }
                aVarE.a(bitmap);
                if (eu.this.f774g != null) {
                    eu.this.f774g.a();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amap.api.mapcore.util.dq
        public void b(Bitmap bitmap) {
            super.b(bitmap);
            synchronized (eu.this.f773f) {
                try {
                    eu.this.f773f.notifyAll();
                } finally {
                }
            }
        }

        private dc.a e() {
            dc.a aVar = this.f776e.get();
            if (this == eu.c(aVar)) {
                return aVar;
            }
            return null;
        }
    }

    public void b(boolean z) {
        synchronized (this.f773f) {
            this.f770c = z;
            if (!this.f770c) {
                try {
                    this.f773f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: compiled from: AbstractImageWorker.java */
    protected class b extends dq<Object, Void, Void> {
        protected b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amap.api.mapcore.util.dq
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Void a(Object... objArr) {
            try {
                int iIntValue = ((Integer) objArr[0]).intValue();
                if (iIntValue == 0) {
                    eu.this.c();
                } else if (iIntValue == 1) {
                    eu.this.b();
                } else if (iIntValue == 2) {
                    eu.this.d();
                } else if (iIntValue == 3) {
                    eu.this.c(((Boolean) objArr[1]).booleanValue());
                } else if (iIntValue == 4) {
                    eu.this.e();
                }
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    protected void b() {
        ev evVar = this.f768a;
        if (evVar != null) {
            evVar.a();
        }
    }

    protected void c() {
        ev evVar = this.f768a;
        if (evVar != null) {
            evVar.b();
        }
    }

    protected void d() {
        ev evVar = this.f768a;
        if (evVar != null) {
            evVar.c();
        }
    }

    protected void c(boolean z) {
        ev evVar = this.f768a;
        if (evVar != null) {
            evVar.a(z);
            this.f768a = null;
        }
    }

    protected void e() {
        ev evVar = this.f768a;
        if (evVar != null) {
            evVar.a(false);
            this.f768a.a();
        }
    }

    public void f() {
        new b().c(0);
    }

    public void d(boolean z) {
        new b().c(3, Boolean.valueOf(z));
    }

    public void a(c cVar) {
        this.f774g = cVar;
    }

    public void a(String str) {
        this.f769b.b(str);
        new b().c(4);
    }
}