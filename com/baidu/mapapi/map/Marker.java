package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public final class Marker extends Overlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    LatLng f2910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    BitmapDescriptor f2911b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f2912c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f2913d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f2914e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f2915f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f2916g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    String f2917h;
    int i;
    float l;
    int m;
    ArrayList<BitmapDescriptor> o;
    Animation q;
    Point u;
    InfoWindow v;
    InfoWindow.a w;
    boolean j = false;
    boolean k = false;
    boolean n = false;
    int p = 20;
    float r = 1.0f;
    float s = 1.0f;
    float t = 1.0f;
    boolean x = false;

    Marker() {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.marker;
    }

    private void a(ArrayList<BitmapDescriptor> arrayList, Bundle bundle) {
        int i;
        ArrayList arrayList2 = new ArrayList();
        Iterator<BitmapDescriptor> it = arrayList.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            BitmapDescriptor next = it.next();
            ParcelItem parcelItem = new ParcelItem();
            Bundle bundle2 = new Bundle();
            Bitmap bitmap = next.f2787a;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            byte[] bArrArray = byteBufferAllocate.array();
            bundle2.putByteArray("image_data", bArrArray);
            bundle2.putInt("image_width", bitmap.getWidth());
            bundle2.putInt("image_height", bitmap.getHeight());
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
            if (messageDigest != null) {
                messageDigest.update(bArrArray, 0, bArrArray.length);
                byte[] bArrDigest = messageDigest.digest();
                StringBuilder sb = new StringBuilder("");
                while (i < bArrDigest.length) {
                    sb.append(Integer.toString((bArrDigest[i] & UByte.MAX_VALUE) + 256, 16).substring(1));
                    i++;
                }
                bundle2.putString("image_hashcode", sb.toString());
            }
            parcelItem.setBundle(bundle2);
            arrayList2.add(parcelItem);
        }
        if (arrayList2.size() > 0) {
            ParcelItem[] parcelItemArr = new ParcelItem[arrayList2.size()];
            while (i < arrayList2.size()) {
                parcelItemArr[i] = (ParcelItem) arrayList2.get(i);
                i++;
            }
            bundle.putParcelableArray("icons", parcelItemArr);
        }
    }

    @Override // com.baidu.mapapi.map.Overlay
    Bundle a(Bundle bundle) {
        super.a(bundle);
        Bundle bundle2 = new Bundle();
        BitmapDescriptor bitmapDescriptor = this.f2911b;
        if (bitmapDescriptor != null) {
            bundle.putBundle("image_info", bitmapDescriptor.b());
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f2910a);
        bundle.putInt("animatetype", this.m);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("perspective", this.f2914e ? 1 : 0);
        bundle.putFloat("anchor_x", this.f2912c);
        bundle.putFloat("anchor_y", this.f2913d);
        bundle.putFloat("rotate", this.f2916g);
        bundle.putInt("y_offset", this.i);
        bundle.putInt("isflat", this.j ? 1 : 0);
        bundle.putInt("istop", this.k ? 1 : 0);
        bundle.putInt("period", this.p);
        bundle.putFloat("alpha", this.l);
        bundle.putFloat("scaleX", this.r);
        bundle.putFloat("scaleY", this.s);
        Point point = this.u;
        if (point != null) {
            bundle.putInt("fix_x", point.x);
            bundle.putInt("fix_y", this.u.y);
        }
        bundle.putInt("isfixed", this.n ? 1 : 0);
        ArrayList<BitmapDescriptor> arrayList = this.o;
        if (arrayList != null && arrayList.size() > 0) {
            a(this.o, bundle);
        }
        bundle2.putBundle("param", bundle);
        return bundle;
    }

    public void cancelAnimation() {
        Animation animation = this.q;
        if (animation != null) {
            animation.bdAnimation.b();
        }
    }

    public float getAlpha() {
        return this.l;
    }

    public float getAnchorX() {
        return this.f2912c;
    }

    public float getAnchorY() {
        return this.f2913d;
    }

    public Point getFixedPosition() {
        return this.u;
    }

    public BitmapDescriptor getIcon() {
        return this.f2911b;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.o;
    }

    public String getId() {
        return this.y;
    }

    public InfoWindow getInfoWindow() {
        return this.v;
    }

    public int getPeriod() {
        return this.p;
    }

    public LatLng getPosition() {
        return this.f2910a;
    }

    public float getRotate() {
        return this.f2916g;
    }

    public float getScale() {
        return this.t;
    }

    public float getScaleX() {
        return this.r;
    }

    public float getScaleY() {
        return this.s;
    }

    public String getTitle() {
        return this.f2917h;
    }

    public int getYOffset() {
        return this.i;
    }

    public void hideInfoWindow() {
        InfoWindow.a aVar = this.w;
        if (aVar != null) {
            aVar.a(this.v);
            this.x = false;
        }
    }

    public boolean isDraggable() {
        return this.f2915f;
    }

    public boolean isFixed() {
        return this.n;
    }

    public boolean isFlat() {
        return this.j;
    }

    public boolean isInfoWindowEnabled() {
        return this.x;
    }

    public boolean isPerspective() {
        return this.f2914e;
    }

    public void setAlpha(float f2) {
        if (f2 < 0.0f || f2 > 1.0d) {
            this.l = 1.0f;
        } else {
            this.l = f2;
            this.listener.b(this);
        }
    }

    public void setAnchor(float f2, float f3) {
        if (f2 < 0.0f || f2 > 1.0f || f3 < 0.0f || f3 > 1.0f) {
            return;
        }
        this.f2912c = f2;
        this.f2913d = f3;
        this.listener.b(this);
    }

    public void setAnimateType(int i) {
        this.m = i;
        this.listener.b(this);
    }

    public void setAnimation(Animation animation) {
        if (animation != null) {
            this.q = animation;
            this.q.bdAnimation.a(this, animation);
        }
    }

    public void setDraggable(boolean z) {
        this.f2915f = z;
        this.listener.b(this);
    }

    public void setFixedScreenPosition(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("BDMapSDKException: the screenPosition can not be null");
        }
        this.u = point;
        this.n = true;
        this.listener.b(this);
    }

    public void setFlat(boolean z) {
        this.j = z;
        this.listener.b(this);
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's icon can not be null");
        }
        this.f2911b = bitmapDescriptor;
        this.listener.b(this);
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        BitmapDescriptor bitmapDescriptor;
        if (arrayList == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's icons can not be null");
        }
        if (arrayList.size() == 0) {
            return;
        }
        if (arrayList.size() == 1) {
            bitmapDescriptor = arrayList.get(0);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) == null || arrayList.get(i).f2787a == null) {
                    return;
                }
            }
            this.o = (ArrayList) arrayList.clone();
            bitmapDescriptor = null;
        }
        this.f2911b = bitmapDescriptor;
        this.listener.b(this);
    }

    public void setPeriod(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("BDMapSDKException: marker's period must be greater than zero ");
        }
        this.p = i;
        this.listener.b(this);
    }

    public void setPerspective(boolean z) {
        this.f2914e = z;
        this.listener.b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's position can not be null");
        }
        this.f2910a = latLng;
        this.listener.b(this);
    }

    public void setPositionWithInfoWindow(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's position can not be null");
        }
        this.f2910a = latLng;
        this.listener.b(this);
        InfoWindow infoWindow = this.v;
        if (infoWindow != null) {
            infoWindow.setPosition(latLng);
        }
    }

    public void setRotate(float f2) {
        while (f2 < 0.0f) {
            f2 += 360.0f;
        }
        this.f2916g = f2 % 360.0f;
        this.listener.b(this);
    }

    public void setScale(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.r = f2;
        this.s = f2;
        this.listener.b(this);
    }

    public void setScaleX(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.r = f2;
        this.listener.b(this);
    }

    public void setScaleY(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.s = f2;
        this.listener.b(this);
    }

    public void setTitle(String str) {
        this.f2917h = str;
    }

    public void setToTop() {
        this.k = true;
        this.listener.b(this);
    }

    public void setYOffset(int i) {
        this.i = i;
        this.listener.b(this);
    }

    public void showInfoWindow(InfoWindow infoWindow) {
        if (infoWindow == null) {
            throw new IllegalArgumentException("BDMapSDKException: the InfoWindow can not be null");
        }
        this.v = infoWindow;
        InfoWindow.a aVar = this.w;
        if (aVar != null) {
            aVar.b(infoWindow);
            this.x = true;
        }
    }

    public void showSmoothMoveInfoWindow(InfoWindow infoWindow) {
        if (infoWindow == null) {
            return;
        }
        if (!infoWindow.j) {
            throw new IllegalArgumentException("BDMapSDKException: the SmoothMoveInfoWindow must build with View");
        }
        if (infoWindow.f2848b == null) {
            throw new IllegalArgumentException("BDMapSDKException: the SmoothMoveInfoWindow's View can not be null");
        }
        this.v = infoWindow;
        this.v.i = true;
        InfoWindow.a aVar = this.w;
        if (aVar != null) {
            aVar.b(infoWindow);
            this.x = true;
        }
    }

    public void startAnimation() {
        Animation animation = this.q;
        if (animation != null) {
            animation.bdAnimation.a();
        }
    }

    public void updateInfoWindowBitmapDescriptor(BitmapDescriptor bitmapDescriptor) {
        InfoWindow infoWindow = this.v;
        if (infoWindow == null || infoWindow.k) {
            return;
        }
        this.v.setBitmapDescriptor(bitmapDescriptor);
    }

    public void updateInfoWindowPosition(LatLng latLng) {
        InfoWindow infoWindow = this.v;
        if (infoWindow != null) {
            infoWindow.setPosition(latLng);
        }
    }

    public void updateInfoWindowView(View view) {
        InfoWindow infoWindow = this.v;
        if (infoWindow == null || !infoWindow.j) {
            return;
        }
        this.v.setView(view);
    }

    public void updateInfoWindowYOffset(int i) {
        InfoWindow infoWindow = this.v;
        if (infoWindow != null) {
            infoWindow.setYOffset(i);
        }
    }
}