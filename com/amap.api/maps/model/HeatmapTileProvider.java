package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import androidx.collection.LongSparseArray;
import com.amap.api.mapcore.util.du;
import com.amap.api.maps.AMapException;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class HeatmapTileProvider implements TileProvider {
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 21;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 256;
    private du mBounds;
    private int[] mColorMap;
    private Collection<WeightedLatLng> mData;
    private Gradient mGradient;
    private double[] mKernel;
    private double[] mMaxIntensity;
    private double mOpacity;
    private int mRadius;
    private a mTree;
    private static final int[] DEFAULT_GRADIENT_COLORS = {Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
    private static final float[] DEFAULT_GRADIENT_START_POINTS = {0.2f, 1.0f};
    public static final Gradient DEFAULT_GRADIENT = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileHeight() {
        return 256;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileWidth() {
        return 256;
    }

    public static class Builder {
        private Collection<WeightedLatLng> data;
        private int radius = 12;
        private Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
        private double opacity = 0.6d;

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.d(collection));
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.data = collection;
            return this;
        }

        public Builder radius(int i) {
            this.radius = Math.max(10, Math.min(i, 50));
            return this;
        }

        public Builder gradient(Gradient gradient) {
            this.gradient = gradient;
            return this;
        }

        public Builder transparency(double d2) {
            this.opacity = Math.max(0.0d, Math.min(d2, 1.0d));
            return this;
        }

        public HeatmapTileProvider build() {
            Collection<WeightedLatLng> collection = this.data;
            if (collection == null || collection.size() == 0) {
                try {
                    throw new AMapException("No input points.");
                } catch (AMapException e2) {
                    Log.e("amap", e2.getErrorMessage());
                    e2.printStackTrace();
                    return null;
                }
            }
            try {
                return new HeatmapTileProvider(this);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    private HeatmapTileProvider(Builder builder) {
        this.mData = builder.data;
        this.mRadius = builder.radius;
        this.mGradient = builder.gradient;
        Gradient gradient = this.mGradient;
        if (gradient == null || !gradient.isAvailable()) {
            this.mGradient = DEFAULT_GRADIENT;
        }
        this.mOpacity = builder.opacity;
        int i = this.mRadius;
        this.mKernel = a(i, ((double) i) / 3.0d);
        a(this.mGradient);
        c(this.mData);
    }

    private void c(Collection<WeightedLatLng> collection) {
        try {
            ArrayList arrayList = new ArrayList();
            for (WeightedLatLng weightedLatLng : collection) {
                if (weightedLatLng.latLng.latitude < 85.0d && weightedLatLng.latLng.latitude > -85.0d) {
                    arrayList.add(weightedLatLng);
                }
            }
            this.mData = arrayList;
            this.mBounds = a(this.mData);
            this.mTree = new a(this.mBounds);
            Iterator<WeightedLatLng> it = this.mData.iterator();
            while (it.hasNext()) {
                this.mTree.a(it.next());
            }
            this.mMaxIntensity = a(this.mRadius);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Collection<WeightedLatLng> d(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(new WeightedLatLng(it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ac  */
    @Override // com.amap.api.maps.model.TileProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amap.api.maps.model.Tile getTile(int r36, int r37, int r38) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.model.HeatmapTileProvider.getTile(int, int, int):com.amap.api.maps.model.Tile");
    }

    private void a(Gradient gradient) {
        this.mGradient = gradient;
        this.mColorMap = gradient.generateColorMap(this.mOpacity);
    }

    private double[] a(int i) {
        int i2;
        double[] dArr = new double[21];
        int i3 = 5;
        while (true) {
            if (i3 >= 11) {
                break;
            }
            dArr[i3] = a(this.mData, this.mBounds, i, (int) (Math.pow(2.0d, i3) * 1280.0d));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
            i3++;
        }
        for (i2 = 11; i2 < 21; i2++) {
            dArr[i2] = dArr[10];
        }
        return dArr;
    }

    private static Tile a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Tile.obtain(256, 256, byteArrayOutputStream.toByteArray());
    }

    static du a(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d2 = next.getPoint().x;
        double d3 = next.getPoint().x;
        double d4 = d2;
        double d5 = d3;
        double d6 = next.getPoint().y;
        double d7 = next.getPoint().y;
        while (it.hasNext()) {
            WeightedLatLng next2 = it.next();
            double d8 = next2.getPoint().x;
            double d9 = next2.getPoint().y;
            if (d8 < d4) {
                d4 = d8;
            }
            if (d8 > d5) {
                d5 = d8;
            }
            if (d9 < d6) {
                d6 = d9;
            }
            if (d9 > d7) {
                d7 = d9;
            }
        }
        return new du(d4, d5, d6, d7);
    }

    static double[] a(int i, double d2) {
        double[] dArr = new double[(i * 2) + 1];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d2) * d2));
        }
        return dArr;
    }

    static double[][] a(double[][] dArr, double[] dArr2) {
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

    static Bitmap a(double[][] dArr, int[] iArr, double d2) {
        int i = iArr[iArr.length - 1];
        double length = ((double) (iArr.length - 1)) / d2;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d3 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d3 * length);
                if (d3 != 0.0d) {
                    if (i5 < iArr.length) {
                        iArr2[i4] = iArr[i5];
                    } else {
                        iArr2[i4] = i;
                    }
                } else {
                    iArr2[i4] = 0;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return bitmapCreateBitmap;
    }

    static double a(Collection<WeightedLatLng> collection, du duVar, int i, int i2) {
        double d2 = duVar.f677a;
        double d3 = duVar.f679c;
        double d4 = duVar.f678b;
        double d5 = d3 - d2;
        double d6 = duVar.f680d - d4;
        if (d5 <= d6) {
            d5 = d6;
        }
        double d7 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / d5;
        LongSparseArray longSparseArray = new LongSparseArray();
        double dDoubleValue = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            double d8 = weightedLatLng.getPoint().x;
            int i3 = (int) ((weightedLatLng.getPoint().y - d4) * d7);
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
}