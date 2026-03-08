package com.baidu.mapsdkplatform.comapi.map;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.TextureView;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.m;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ac extends TextureView implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, TextureView.SurfaceTextureListener, m.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f3535b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f3536c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private GestureDetector f3537d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler f3538e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3539f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private SurfaceTexture f3540g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private m f3541h;
    private e i;

    public ac(Context context, z zVar, String str, int i) throws Throwable {
        super(context);
        this.f3539f = false;
        this.f3541h = null;
        a(context, zVar, str, i);
    }

    private void a(Context context, z zVar, String str, int i) throws Throwable {
        setSurfaceTextureListener(this);
        if (context == null) {
            throw new RuntimeException("BDMapSDKException: when you create an mapview, the context can not be null");
        }
        this.f3537d = new GestureDetector(context, this);
        EnvironmentUtilities.initAppDirectory(context);
        if (this.i == null) {
            this.i = new e(context, str, i);
        }
        this.i.a(context.hashCode());
        this.i.a();
        this.i.a(zVar);
        e();
        this.i.a(this.f3538e);
        this.i.f();
    }

    private void e() {
        this.f3538e = new ad(this);
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.m.a
    public int a() {
        e eVar = this.i;
        if (eVar == null) {
            return 0;
        }
        if (f3536c <= 1) {
            MapRenderer.nativeResize(eVar.j, f3534a, f3535b);
            f3536c++;
        }
        return MapRenderer.nativeRender(this.i.j);
    }

    public void a(int i) {
        synchronized (this.i) {
            if (this.i.f3567h != null) {
                for (l lVar : this.i.f3567h) {
                    if (lVar != null) {
                        lVar.f();
                    }
                }
            }
            if (this.i != null) {
                this.i.b(this.f3538e);
                this.i.b(i);
                this.i = null;
            }
            this.f3538e.removeCallbacksAndMessages(null);
            if (this.f3541h != null) {
                this.f3541h.c();
                this.f3541h = null;
            }
            if (this.f3540g != null) {
                if (Build.VERSION.SDK_INT >= 19) {
                    this.f3540g.release();
                }
                this.f3540g = null;
            }
        }
    }

    public void a(String str, Rect rect) {
        m mVar;
        e eVar = this.i;
        if (eVar == null || eVar.i == null) {
            return;
        }
        if (rect != null) {
            int i = rect.left;
            int i2 = f3535b < rect.bottom ? 0 : f3535b - rect.bottom;
            int iWidth = rect.width();
            int iHeight = rect.height();
            if (i < 0 || i2 < 0 || iWidth <= 0 || iHeight <= 0) {
                return;
            }
            if (iWidth > f3534a) {
                iWidth = Math.abs(rect.width()) - (rect.right - f3534a);
            }
            if (iHeight > f3535b) {
                iHeight = Math.abs(rect.height()) - (rect.bottom - f3535b);
            }
            if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                this.i.i.a(str, (Bundle) null);
                m mVar2 = this.f3541h;
                if (mVar2 != null) {
                    mVar2.a();
                    return;
                }
                return;
            }
            f3534a = iWidth;
            f3535b = iHeight;
            Bundle bundle = new Bundle();
            bundle.putInt("x", i);
            bundle.putInt("y", i2);
            bundle.putInt("width", iWidth);
            bundle.putInt("height", iHeight);
            this.i.i.a(str, bundle);
            mVar = this.f3541h;
            if (mVar == null) {
                return;
            }
        } else {
            this.i.i.a(str, (Bundle) null);
            mVar = this.f3541h;
            if (mVar == null) {
                return;
            }
        }
        mVar.a();
    }

    public e b() {
        return this.i;
    }

    public void c() {
        e eVar = this.i;
        if (eVar == null || eVar.i == null) {
            return;
        }
        if (this.i.f3567h != null) {
            for (l lVar : this.i.f3567h) {
                if (lVar != null) {
                    lVar.d();
                }
            }
        }
        this.i.i.g();
        this.i.i.d();
        this.i.i.n();
        m mVar = this.f3541h;
        if (mVar != null) {
            mVar.a();
        }
        if (this.i.b()) {
            this.f3539f = true;
        }
    }

    public void d() {
        e eVar = this.i;
        if (eVar == null || eVar.i == null) {
            return;
        }
        this.f3539f = false;
        this.i.i.c();
        synchronized (this.i) {
            this.i.i.c();
            if (this.f3541h != null) {
                this.f3541h.b();
            }
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        e eVar = this.i;
        if (eVar == null || eVar.i == null || !this.i.k) {
            return true;
        }
        GeoPoint geoPointB = this.i.b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (geoPointB != null) {
            if (this.i.f3567h != null) {
                for (l lVar : this.i.f3567h) {
                    if (lVar != null) {
                        lVar.b(geoPointB);
                    }
                }
            }
            if (this.i.f3565f) {
                ab abVarE = this.i.E();
                abVarE.f3518a += 1.0f;
                if (!this.i.f3566g) {
                    abVarE.f3521d = geoPointB.getLongitudeE6();
                    abVarE.f3522e = geoPointB.getLatitudeE6();
                }
                BaiduMap.mapStatusReason |= 1;
                this.i.a(abVarE, 300);
                e eVar2 = this.i;
                e.m = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        e eVar = this.i;
        if (eVar == null || eVar.i == null || !this.i.k) {
            return true;
        }
        if (!this.i.f3564e) {
            return false;
        }
        float fSqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
        if (fSqrt <= 500.0f) {
            return false;
        }
        BaiduMap.mapStatusReason |= 1;
        this.i.A();
        this.i.a(34, (int) (fSqrt * 0.6f), ((int) motionEvent2.getX()) | (((int) motionEvent2.getY()) << 16));
        this.i.M();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        e eVar = this.i;
        if (eVar == null || eVar.i == null || !this.i.k) {
            return;
        }
        String strA = this.i.i.a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.i.l);
        if (this.i.f3567h == null) {
            return;
        }
        if (strA == null || strA.equals("")) {
            for (l lVar : this.i.f3567h) {
                GeoPoint geoPointB = this.i.b((int) motionEvent.getX(), (int) motionEvent.getY());
                if (lVar != null) {
                    lVar.c(geoPointB);
                }
            }
            return;
        }
        for (l lVar2 : this.i.f3567h) {
            if (lVar2.b(strA)) {
                this.i.p = true;
            } else {
                lVar2.c(this.i.b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        JSONObject jSONObject;
        e eVar = this.i;
        if (eVar == null || eVar.i == null || !this.i.k || this.i.f3567h == null) {
            return true;
        }
        String strA = this.i.i.a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.i.l);
        if (strA == null || strA.equals("")) {
            for (l lVar : this.i.f3567h) {
                if (lVar != null) {
                    lVar.a(this.i.b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            }
        } else {
            try {
                jSONObject = new JSONObject(strA);
            } catch (JSONException e2) {
                e = e2;
                jSONObject = null;
            }
            try {
                jSONObject.put("px", (int) motionEvent.getX());
                jSONObject.put("py", (int) motionEvent.getY());
            } catch (JSONException e3) {
                e = e3;
                e.printStackTrace();
            }
            for (l lVar2 : this.i.f3567h) {
                if (jSONObject != null && lVar2 != null) {
                    lVar2.a(jSONObject.toString());
                }
            }
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.i == null) {
            return;
        }
        SurfaceTexture surfaceTexture2 = this.f3540g;
        if (surfaceTexture2 != null) {
            setSurfaceTexture(surfaceTexture2);
            return;
        }
        this.f3540g = surfaceTexture;
        this.f3541h = new m(this.f3540g, this, new AtomicBoolean(true), this);
        this.f3541h.start();
        f3534a = i;
        f3535b = i2;
        ab abVarE = this.i.E();
        if (abVarE == null) {
            return;
        }
        if (abVarE.f3523f == 0 || abVarE.f3523f == -1 || abVarE.f3523f == (abVarE.j.left - abVarE.j.right) / 2) {
            abVarE.f3523f = -1;
        }
        if (abVarE.f3524g == 0 || abVarE.f3524g == -1 || abVarE.f3524g == (abVarE.j.bottom - abVarE.j.top) / 2) {
            abVarE.f3524g = -1;
        }
        abVarE.j.left = 0;
        abVarE.j.top = 0;
        abVarE.j.bottom = i2;
        abVarE.j.right = i;
        this.i.a(abVarE);
        this.i.a(f3534a, f3535b);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        e eVar = this.i;
        if (eVar == null) {
            return;
        }
        f3534a = i;
        f3535b = i2;
        f3536c = 1;
        ab abVarE = eVar.E();
        if (abVarE.f3523f == 0 || abVarE.f3523f == -1 || abVarE.f3523f == (abVarE.j.left - abVarE.j.right) / 2) {
            abVarE.f3523f = -1;
        }
        if (abVarE.f3524g == 0 || abVarE.f3524g == -1 || abVarE.f3524g == (abVarE.j.bottom - abVarE.j.top) / 2) {
            abVarE.f3524g = -1;
        }
        abVarE.j.left = 0;
        abVarE.j.top = 0;
        abVarE.j.bottom = i2;
        abVarE.j.right = i;
        this.i.a(abVarE);
        this.i.a(f3534a, f3535b);
        MapRenderer.nativeResize(this.i.j, i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        m mVar;
        if (!this.f3539f || (mVar = this.f3541h) == null) {
            return;
        }
        mVar.a();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        e eVar = this.i;
        if (eVar == null || eVar.i == null) {
            return true;
        }
        super.onTouchEvent(motionEvent);
        if (this.i.f3567h != null) {
            for (l lVar : this.i.f3567h) {
                if (lVar != null) {
                    lVar.a(motionEvent);
                }
            }
        }
        if (this.f3537d.onTouchEvent(motionEvent)) {
            return true;
        }
        return this.i.a(motionEvent);
    }
}