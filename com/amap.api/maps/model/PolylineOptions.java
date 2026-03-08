package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class PolylineOptions extends BaseOptions implements Parcelable {
    public static final PolylineOptionsCreator CREATOR = new PolylineOptionsCreator();
    public static final int DOTTEDLINE_TYPE_CIRCLE = 1;
    public static final int DOTTEDLINE_TYPE_SQUARE = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f1869a;
    private String bitmapSymbol;
    private List<String> bitmapSymbolList;
    private List<Integer> colorValues;
    private List<Integer> customIndexs;
    private BitmapDescriptor customTexture;
    private List<BitmapDescriptor> customTextureList;
    private String eraseBitmapSymbol;
    private BitmapDescriptor eraseTexture;
    private String footPrintBitmapSymbol;
    private BitmapDescriptor footPrintTexture;
    private int[] jniColorValues;
    private int[] jniCustomIndexes;
    private double[] pointList;
    private float width = 10.0f;
    private int color = ViewCompat.MEASURED_STATE_MASK;
    private float zIndex = 0.0f;
    private boolean isVisible = true;
    private boolean isUseTexture = true;
    private boolean isGeodesic = false;
    private boolean isDottedLine = false;
    private boolean isGradient = false;
    private float transparency = 1.0f;
    private boolean isAboveMaskLayer = false;
    private int dottedLineType = 0;
    private LineCapType lineCapType = LineCapType.LineCapRound;
    private LineJoinType lineJoinType = LineJoinType.LineJoinBevel;
    private int nLineCapType = 3;
    private int nLineJoinType = 0;
    private float shownRatio = -1.0f;
    private float shownRangeBegin = -1.0f;
    private float shownRangeEnd = -1.0f;
    private float footPrintGap = 0.0f;
    private boolean eraseVisible = false;
    private int eraseColor = -7829368;
    private final String type = "PolylineOptions";
    private boolean isPointsUpdated = false;
    private boolean isCustomTextureListUpdated = false;
    private boolean isColorValuesUpdated = false;
    private boolean isCustomIndexesUpdated = false;
    private float shownPolylineRangeBegin = 0.0f;
    private float shownPolylineRangeEnd = 0.0f;
    private boolean showPolylineRangeEnable = false;
    private final List<LatLng> points = new ArrayList();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PolylineOptions setUseTexture(boolean z) {
        this.isUseTexture = z;
        return this;
    }

    public PolylineOptions setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        this.customTexture = bitmapDescriptor;
        if (bitmapDescriptor != null) {
            this.bitmapSymbol = bitmapDescriptor.getId();
        }
        return this;
    }

    public BitmapDescriptor getCustomTexture() {
        return this.customTexture;
    }

    public PolylineOptions setCustomTextureList(List<BitmapDescriptor> list) {
        this.customTextureList = list;
        if (list != null) {
            try {
                this.bitmapSymbolList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    this.bitmapSymbolList.add(list.get(i).getId());
                }
                this.isCustomTextureListUpdated = true;
            } catch (Throwable unused) {
            }
        }
        return this;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        return this.customTextureList;
    }

    public PolylineOptions setCustomTextureIndex(List<Integer> list) {
        try {
            this.customIndexs = list;
            this.jniCustomIndexes = new int[list.size()];
            for (int i = 0; i < this.jniCustomIndexes.length; i++) {
                this.jniCustomIndexes[i] = list.get(i).intValue();
            }
            this.isCustomIndexesUpdated = true;
        } catch (Throwable unused) {
        }
        return this;
    }

    public List<Integer> getCustomTextureIndex() {
        return this.customIndexs;
    }

    public PolylineOptions colorValues(List<Integer> list) {
        try {
            this.colorValues = list;
            this.jniColorValues = new int[list.size()];
            for (int i = 0; i < this.jniColorValues.length; i++) {
                this.jniColorValues[i] = list.get(i).intValue();
            }
            this.isColorValuesUpdated = true;
        } catch (Throwable unused) {
        }
        return this;
    }

    public List<Integer> getColorValues() {
        return this.colorValues;
    }

    public PolylineOptions useGradient(boolean z) {
        this.isGradient = z;
        return this;
    }

    public boolean isUseGradient() {
        return this.isGradient;
    }

    public boolean isUseTexture() {
        return this.isUseTexture;
    }

    public boolean isGeodesic() {
        return this.isGeodesic;
    }

    public PolylineOptions add(LatLng latLng) {
        if (latLng != null) {
            try {
                this.points.add(latLng);
                this.isPointsUpdated = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        if (latLngArr != null) {
            try {
                this.points.addAll(Arrays.asList(latLngArr));
                this.isPointsUpdated = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        if (iterable != null) {
            try {
                Iterator<LatLng> it = iterable.iterator();
                while (it.hasNext()) {
                    this.points.add(it.next());
                }
                this.isPointsUpdated = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return this;
    }

    public PolylineOptions width(float f2) {
        this.width = f2;
        return this;
    }

    public PolylineOptions color(int i) {
        this.color = i;
        return this;
    }

    public PolylineOptions zIndex(float f2) {
        this.zIndex = f2;
        return this;
    }

    public PolylineOptions visible(boolean z) {
        this.isVisible = z;
        return this;
    }

    public PolylineOptions geodesic(boolean z) {
        this.isGeodesic = z;
        return this;
    }

    public PolylineOptions setDottedLine(boolean z) {
        this.isDottedLine = z;
        return this;
    }

    public boolean isDottedLine() {
        return this.isDottedLine;
    }

    public PolylineOptions setDottedLineType(int i) {
        this.dottedLineType = i == 0 ? 0 : 1;
        return this;
    }

    public PolylineOptions lineCapType(LineCapType lineCapType) {
        if (lineCapType != null) {
            this.lineCapType = lineCapType;
            this.nLineCapType = lineCapType.getTypeValue();
        }
        return this;
    }

    public PolylineOptions lineJoinType(LineJoinType lineJoinType) {
        if (lineJoinType != null) {
            this.lineJoinType = lineJoinType;
            this.nLineJoinType = lineJoinType.getTypeValue();
        }
        return this;
    }

    public LineCapType getLineCapType() {
        return this.lineCapType;
    }

    public LineJoinType getLineJoinType() {
        return this.lineJoinType;
    }

    public int getDottedLineType() {
        return this.dottedLineType;
    }

    public List<LatLng> getPoints() {
        return this.points;
    }

    public float getWidth() {
        return this.width;
    }

    public int getColor() {
        return this.color;
    }

    public float getZIndex() {
        return this.zIndex;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.points);
        parcel.writeFloat(this.width);
        parcel.writeInt(this.color);
        parcel.writeInt(this.dottedLineType);
        parcel.writeFloat(this.zIndex);
        parcel.writeFloat(this.transparency);
        parcel.writeString(this.f1869a);
        parcel.writeInt(this.lineCapType.getTypeValue());
        parcel.writeInt(this.lineJoinType.getTypeValue());
        parcel.writeBooleanArray(new boolean[]{this.isVisible, this.isDottedLine, this.isGeodesic, this.isGradient, this.isAboveMaskLayer});
        BitmapDescriptor bitmapDescriptor = this.customTexture;
        if (bitmapDescriptor != null) {
            parcel.writeParcelable(bitmapDescriptor, i);
        }
        List<BitmapDescriptor> list = this.customTextureList;
        if (list != null) {
            parcel.writeList(list);
        }
        List<Integer> list2 = this.customIndexs;
        if (list2 != null) {
            parcel.writeList(list2);
        }
        List<Integer> list3 = this.colorValues;
        if (list3 != null) {
            parcel.writeList(list3);
        }
        parcel.writeFloat(this.shownRatio);
    }

    public PolylineOptions transparency(float f2) {
        this.transparency = f2;
        return this;
    }

    public float getTransparency() {
        return this.transparency;
    }

    public PolylineOptions aboveMaskLayer(boolean z) {
        this.isAboveMaskLayer = z;
        return this;
    }

    public boolean isAboveMaskLayer() {
        return this.isAboveMaskLayer;
    }

    public void setPoints(List<LatLng> list) {
        List<LatLng> list2;
        if (list == null || (list2 = this.points) == list) {
            return;
        }
        try {
            list2.clear();
            this.points.addAll(list);
            this.isPointsUpdated = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getShownRatio() {
        return this.shownRatio;
    }

    public PolylineOptions setShownRatio(float f2) {
        this.shownRatio = f2;
        return this;
    }

    public PolylineOptions setShownRange(float f2, float f3) {
        this.shownRangeBegin = f2;
        this.shownRangeEnd = f3;
        return this;
    }

    public float getShownRangeBegin() {
        return this.shownRangeBegin;
    }

    public float getShownRangeEnd() {
        return this.shownRangeEnd;
    }

    public PolylineOptions showPolylineRangeEnabled(boolean z) {
        this.showPolylineRangeEnable = z;
        return this;
    }

    public boolean isShowPolylineRangeEnable() {
        return this.showPolylineRangeEnable;
    }

    public PolylineOptions setPolylineShowRange(float f2, float f3) {
        this.shownPolylineRangeBegin = f2;
        this.shownPolylineRangeEnd = f3;
        return this;
    }

    public float getPolylineShownRangeBegin() {
        return this.shownPolylineRangeBegin;
    }

    public float getPolylineShownRangeEnd() {
        return this.shownPolylineRangeEnd;
    }

    public PolylineOptions setFootPrintTexture(BitmapDescriptor bitmapDescriptor) {
        this.footPrintTexture = bitmapDescriptor;
        if (bitmapDescriptor != null) {
            this.footPrintBitmapSymbol = bitmapDescriptor.getId();
        }
        return this;
    }

    public BitmapDescriptor getFootPrintTexture() {
        return this.footPrintTexture;
    }

    public PolylineOptions setFootPrintGap(float f2) {
        this.footPrintGap = f2;
        return this;
    }

    public float getFootPrintGap() {
        return this.footPrintGap;
    }

    public PolylineOptions setEraseTexture(boolean z, BitmapDescriptor bitmapDescriptor) {
        this.eraseVisible = z;
        this.eraseTexture = bitmapDescriptor;
        if (bitmapDescriptor != null) {
            this.eraseBitmapSymbol = bitmapDescriptor.getId();
        }
        return this;
    }

    public BitmapDescriptor getEraseTexture() {
        return this.eraseTexture;
    }

    public boolean getEraseVisible() {
        return this.eraseVisible;
    }

    public PolylineOptions setEraseColor(boolean z, int i) {
        this.eraseVisible = z;
        this.eraseColor = i;
        return this;
    }

    public int getEraseColor() {
        return this.eraseColor;
    }

    public enum LineCapType {
        LineCapButt(0),
        LineCapSquare(1),
        LineCapArrow(2),
        LineCapRound(3);

        private int type;

        LineCapType(int i) {
            this.type = i;
        }

        public static LineCapType valueOf(int i) {
            LineCapType[] lineCapTypeArrValues = values();
            return lineCapTypeArrValues[Math.max(0, Math.min(i, lineCapTypeArrValues.length))];
        }

        public int getTypeValue() {
            return this.type;
        }
    }

    public enum LineJoinType {
        LineJoinBevel(0),
        LineJoinMiter(1),
        LineJoinRound(2);

        private int type;

        LineJoinType(int i) {
            this.type = i;
        }

        public int getTypeValue() {
            return this.type;
        }

        public static LineJoinType valueOf(int i) {
            LineJoinType[] lineJoinTypeArrValues = values();
            return lineJoinTypeArrValues[Math.max(0, Math.min(i, lineJoinTypeArrValues.length))];
        }
    }
}