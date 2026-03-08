package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.util.SparseIntArray;
import androidx.collection.LongSparseArray;
import com.baidu.mapapi.model.LatLng;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
public class HeatMap {
    public static final Gradient DEFAULT_GRADIENT;
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2835b = HeatMap.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final SparseIntArray f2836c = new SparseIntArray();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f2837d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final float[] f2838e;
    private static int r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    BaiduMap f2839a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private o<WeightedLatLng> f2840f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Collection<WeightedLatLng> f2841g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2842h;
    private Gradient i;
    private double j;
    private h k;
    private int[] l;
    private double[] m;
    private double[] n;
    private HashMap<String, Tile> o;
    private ExecutorService p;
    private HashSet<String> q;

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Collection<WeightedLatLng> f2843a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2844b = 12;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Gradient f2845c = HeatMap.DEFAULT_GRADIENT;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private double f2846d = 0.6d;

        public HeatMap build() {
            if (this.f2843a != null) {
                return new HeatMap(this, null);
            }
            throw new IllegalStateException("BDMapSDKException: No input data: you must use either .data or .weightedData before building");
        }

        public Builder data(Collection<LatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("BDMapSDKException: No input points.");
            }
            if (collection.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: input points can not contain null.");
            }
            return weightedData(HeatMap.c(collection));
        }

        public Builder gradient(Gradient gradient) {
            if (gradient == null) {
                throw new IllegalArgumentException("BDMapSDKException: gradient can not be null");
            }
            this.f2845c = gradient;
            return this;
        }

        public Builder opacity(double d2) {
            this.f2846d = d2;
            double d3 = this.f2846d;
            if (d3 < 0.0d || d3 > 1.0d) {
                throw new IllegalArgumentException("BDMapSDKException: Opacity must be in range [0, 1]");
            }
            return this;
        }

        public Builder radius(int i) {
            this.f2844b = i;
            int i2 = this.f2844b;
            if (i2 < 10 || i2 > 50) {
                throw new IllegalArgumentException("BDMapSDKException: Radius not within bounds.");
            }
            return this;
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("BDMapSDKException: No input points.");
            }
            if (collection.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: input points can not contain null.");
            }
            ArrayList arrayList = new ArrayList();
            for (WeightedLatLng weightedLatLng : collection) {
                LatLng latLng = weightedLatLng.latLng;
                if (latLng.latitude < 0.37532d || latLng.latitude > 54.562495d || latLng.longitude < 72.508319d || latLng.longitude > 135.942198d) {
                    arrayList.add(weightedLatLng);
                }
            }
            collection.removeAll(arrayList);
            this.f2843a = collection;
            return this;
        }
    }

    static {
        f2836c.put(3, 8388608);
        f2836c.put(4, 4194304);
        f2836c.put(5, 2097152);
        f2836c.put(6, 1048576);
        f2836c.put(7, 524288);
        f2836c.put(8, 262144);
        f2836c.put(9, 131072);
        f2836c.put(10, 65536);
        f2836c.put(11, 32768);
        f2836c.put(12, 16384);
        f2836c.put(13, 8192);
        f2836c.put(14, 4096);
        f2836c.put(15, 2048);
        f2836c.put(16, 1024);
        f2836c.put(17, 512);
        f2836c.put(18, 256);
        f2836c.put(19, 128);
        f2836c.put(20, 64);
        f2837d = new int[]{Color.rgb(0, 0, 200), Color.rgb(0, 225, 0), Color.rgb(255, 0, 0)};
        f2838e = new float[]{0.08f, 0.4f, 1.0f};
        DEFAULT_GRADIENT = new Gradient(f2837d, f2838e);
        r = 0;
    }

    private HeatMap(Builder builder) {
        this.o = new HashMap<>();
        this.p = Executors.newFixedThreadPool(1);
        this.q = new HashSet<>();
        this.f2841g = builder.f2843a;
        this.f2842h = builder.f2844b;
        this.i = builder.f2845c;
        this.j = builder.f2846d;
        int i = this.f2842h;
        this.m = a(i, ((double) i) / 3.0d);
        a(this.i);
        b(this.f2841g);
    }

    /* synthetic */ HeatMap(Builder builder, j jVar) {
        this(builder);
    }

    private static double a(Collection<WeightedLatLng> collection, h hVar, int i, int i2) {
        double d2 = hVar.f3040a;
        double d3 = hVar.f3042c;
        double d4 = hVar.f3041b;
        double d5 = d3 - d2;
        double d6 = hVar.f3043d - d4;
        if (d5 <= d6) {
            d5 = d6;
        }
        double d7 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / d5;
        LongSparseArray longSparseArray = new LongSparseArray();
        double dDoubleValue = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            double d8 = weightedLatLng.a().x;
            int i3 = (int) ((((double) weightedLatLng.a().y) - d4) * d7);
            long j = (int) ((d8 - d2) * d7);
            LongSparseArray longSparseArray2 = (LongSparseArray) longSparseArray.get(j);
            if (longSparseArray2 == null) {
                longSparseArray2 = new LongSparseArray();
                longSparseArray.put(j, longSparseArray2);
            }
            long j2 = i3;
            Double dValueOf = (Double) longSparseArray2.get(j2);
            if (dValueOf == null) {
                dValueOf = Double.valueOf(0.0d);
            }
            LongSparseArray longSparseArray3 = longSparseArray;
            double d9 = d2;
            Double dValueOf2 = Double.valueOf(dValueOf.doubleValue() + weightedLatLng.intensity);
            longSparseArray2.put(j2, dValueOf2);
            if (dValueOf2.doubleValue() > dDoubleValue) {
                dDoubleValue = dValueOf2.doubleValue();
            }
            longSparseArray = longSparseArray3;
            d2 = d9;
        }
        return dDoubleValue;
    }

    private static Bitmap a(double[][] dArr, int[] iArr, double d2) {
        int i = iArr[iArr.length - 1];
        double length = ((double) (iArr.length - 1)) / d2;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d3 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d3 * length);
                if (d3 == 0.0d) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return bitmapCreateBitmap;
    }

    private static Tile a(Bitmap bitmap) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        return new Tile(256, 256, byteBufferAllocate.array());
    }

    private void a(Gradient gradient) {
        this.i = gradient;
        this.l = gradient.a(this.j);
    }

    private synchronized void a(String str, Tile tile) {
        this.o.put(str, tile);
    }

    private synchronized boolean a(String str) {
        return this.q.contains(str);
    }

    private double[] a(int i) {
        int i2;
        double[] dArr = new double[20];
        int i3 = 5;
        while (true) {
            if (i3 >= 11) {
                break;
            }
            dArr[i3] = a(this.f2841g, this.k, i, (int) (Math.pow(2.0d, i3 - 3) * 1280.0d));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
            i3++;
        }
        for (i2 = 11; i2 < 20; i2++) {
            dArr[i2] = dArr[10];
        }
        return dArr;
    }

    private static double[] a(int i, double d2) {
        double[] dArr = new double[(i * 2) + 1];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d2) * d2));
        }
        return dArr;
    }

    private static double[][] a(double[][] dArr, double[] dArr2) {
        int iFloor = (int) Math.floor(((double) dArr2.length) / 2.0d);
        int length = dArr.length;
        int i = length - (iFloor * 2);
        int i2 = (iFloor + i) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, length, length);
        int i3 = 0;
        while (true) {
            double d2 = 0.0d;
            if (i3 >= length) {
                break;
            }
            int i4 = 0;
            while (i4 < length) {
                double d3 = dArr[i3][i4];
                if (d3 != d2) {
                    int i5 = i3 + iFloor;
                    if (i2 < i5) {
                        i5 = i2;
                    }
                    int i6 = i5 + 1;
                    int i7 = i3 - iFloor;
                    for (int i8 = iFloor > i7 ? iFloor : i7; i8 < i6; i8++) {
                        double[] dArr4 = dArr3[i8];
                        dArr4[i4] = dArr4[i4] + (dArr2[i8 - i7] * d3);
                    }
                }
                i4++;
                d2 = 0.0d;
            }
            i3++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) double.class, i, i);
        for (int i9 = iFloor; i9 < i2 + 1; i9++) {
            for (int i10 = 0; i10 < length; i10++) {
                double d4 = dArr3[i9][i10];
                if (d4 != 0.0d) {
                    int i11 = i10 + iFloor;
                    if (i2 < i11) {
                        i11 = i2;
                    }
                    int i12 = i11 + 1;
                    int i13 = i10 - iFloor;
                    for (int i14 = iFloor > i13 ? iFloor : i13; i14 < i12; i14++) {
                        double[] dArr6 = dArr5[i9 - iFloor];
                        int i15 = i14 - iFloor;
                        dArr6[i15] = dArr6[i15] + (dArr2[i14 - i13] * d4);
                    }
                }
            }
        }
        return dArr5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, int i3) {
        double d2 = f2836c.get(i3);
        int i4 = this.f2842h;
        double d3 = (((double) i4) * d2) / 256.0d;
        double d4 = ((2.0d * d3) + d2) / ((double) ((i4 * 2) + 256));
        if (i < 0 || i2 < 0) {
            return;
        }
        double d5 = (((double) i) * d2) - d3;
        double d6 = (((double) (i + 1)) * d2) + d3;
        double d7 = (((double) i2) * d2) - d3;
        double d8 = (((double) (i2 + 1)) * d2) + d3;
        h hVar = new h(d5, d6, d7, d8);
        if (hVar.a(new h(this.k.f3040a - d3, this.k.f3042c + d3, this.k.f3041b - d3, this.k.f3043d + d3))) {
            Collection<T> collectionA = this.f2840f.a(hVar);
            if (collectionA.isEmpty()) {
                return;
            }
            int i5 = this.f2842h;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, (i5 * 2) + 256, (i5 * 2) + 256);
            for (T t : collectionA) {
                Point pointA = t.a();
                int i6 = (int) ((((double) pointA.x) - d5) / d4);
                double d9 = d5;
                int i7 = (int) ((d8 - ((double) pointA.y)) / d4);
                int i8 = this.f2842h;
                if (i6 >= (i8 * 2) + 256) {
                    i6 = ((i8 * 2) + 256) - 1;
                }
                int i9 = this.f2842h;
                if (i7 >= (i9 * 2) + 256) {
                    i7 = ((i9 * 2) + 256) - 1;
                }
                double[] dArr2 = dArr[i6];
                dArr2[i7] = dArr2[i7] + t.intensity;
                d5 = d9;
                d8 = d8;
            }
            Bitmap bitmapA = a(a(dArr, this.m), this.l, this.n[i3 - 1]);
            Tile tileA = a(bitmapA);
            bitmapA.recycle();
            a(i + "_" + i2 + "_" + i3, tileA);
            if (this.o.size() > r) {
                a();
            }
            BaiduMap baiduMap = this.f2839a;
            if (baiduMap != null) {
                baiduMap.a();
            }
        }
    }

    private synchronized void b(String str) {
        this.q.add(str);
    }

    private void b(Collection<WeightedLatLng> collection) {
        this.f2841g = collection;
        if (this.f2841g.isEmpty()) {
            throw new IllegalArgumentException("BDMapSDKException: No input points.");
        }
        this.k = d(this.f2841g);
        this.f2840f = new o<>(this.k);
        Iterator<WeightedLatLng> it = this.f2841g.iterator();
        while (it.hasNext()) {
            this.f2840f.a(it.next());
        }
        this.n = a(this.f2842h);
    }

    private synchronized Tile c(String str) {
        if (!this.o.containsKey(str)) {
            return null;
        }
        Tile tile = this.o.get(str);
        this.o.remove(str);
        return tile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Collection<WeightedLatLng> c(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(new WeightedLatLng(it.next()));
        }
        return arrayList;
    }

    private static h d(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d2 = next.a().x;
        double d3 = d2;
        double d4 = next.a().x;
        double d5 = next.a().y;
        double d6 = next.a().y;
        while (it.hasNext()) {
            WeightedLatLng next2 = it.next();
            double d7 = next2.a().x;
            double d8 = next2.a().y;
            if (d7 < d3) {
                d3 = d7;
            }
            if (d7 > d4) {
                d4 = d7;
            }
            if (d8 < d5) {
                d5 = d8;
            }
            if (d8 > d6) {
                d6 = d8;
            }
        }
        return new h(d3, d4, d5, d6);
    }

    private synchronized void d() {
        this.o.clear();
    }

    Tile a(int i, int i2, int i3) {
        String str = i + "_" + i2 + "_" + i3;
        Tile tileC = c(str);
        if (tileC != null) {
            return tileC;
        }
        if (a(str)) {
            return null;
        }
        BaiduMap baiduMap = this.f2839a;
        if (baiduMap != null && r == 0) {
            MapStatus mapStatus = baiduMap.getMapStatus();
            r = (((mapStatus.f2869a.j.right - mapStatus.f2869a.j.left) / 256) + 2) * (((mapStatus.f2869a.j.bottom - mapStatus.f2869a.j.top) / 256) + 2) * 4;
        }
        if (this.o.size() > r) {
            a();
        }
        if (this.p.isShutdown()) {
            return null;
        }
        try {
            this.p.execute(new j(this, i, i2, i3));
            b(str);
            return null;
        } catch (RejectedExecutionException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    synchronized void a() {
        this.q.clear();
        this.o.clear();
    }

    void b() {
        d();
    }

    void c() {
        this.p.shutdownNow();
    }

    public void removeHeatMap() {
        BaiduMap baiduMap = this.f2839a;
        if (baiduMap != null) {
            baiduMap.a(this);
        }
    }
}