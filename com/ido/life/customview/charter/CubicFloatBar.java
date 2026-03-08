package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.R;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.FloatBarPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CubicFloatBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0002CDB\u001d\b\u0007\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u00104\u001a\u000205H\u0014J\b\u00106\u001a\u000207H\u0014J\b\u00108\u001a\u000205H\u0016J\u0010\u00109\u001a\u0002052\u0006\u0010:\u001a\u00020;H\u0014J\u0012\u0010<\u001a\u0002052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010=\u001a\u00020>H\u0014J\u0010\u0010?\u001a\u0002052\u0006\u0010@\u001a\u00020\u000fH\u0014J\u0010\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\u000fH\u0016R\u0019\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\u001a\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001a\u0010\u001d\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R\u001a\u0010 \u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0011\"\u0004\b\"\u0010\u0013R\u001a\u0010#\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0011\"\u0004\b%\u0010\u0013R\u001a\u0010&\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0011\"\u0004\b(\u0010\u0013R\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0011\"\u0004\b0\u0010\u0013R\u001a\u00101\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0011\"\u0004\b3\u0010\u0013¨\u0006E"}, d2 = {"Lcom/ido/life/customview/charter/CubicFloatBar;", "T", "Lcom/ido/life/bean/FloatBarPoint;", "Lcom/ido/life/customview/charter/CustomChatBar;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "mBigCircleColor", "", "getMBigCircleColor", "()I", "setMBigCircleColor", "(I)V", "mBigCircleRadius", "getMBigCircleRadius", "setMBigCircleRadius", "mFillEndColor", "getMFillEndColor", "setMFillEndColor", "mFillStartColor", "getMFillStartColor", "setMFillStartColor", "mLineBottomColor", "getMLineBottomColor", "setMLineBottomColor", "mLineBottomWidth", "getMLineBottomWidth", "setMLineBottomWidth", "mLineTopColor", "getMLineTopColor", "setMLineTopColor", "mLineTopWidth", "getMLineTopWidth", "setMLineTopWidth", "mRegionList", "", "Lcom/ido/life/customview/charter/CubicFloatBar$CubicFloatChartBean;", "mShader", "Landroid/graphics/Shader;", "mSmallCircleColor", "getMSmallCircleColor", "setMSmallCircleColor", "mSmallCircleRadius", "getMSmallCircleRadius", "setMSmallCircleRadius", "caluteCirclePosition", "", "canTouch", "", "clearList", "drawChat", "canvas", "Landroid/graphics/Canvas;", "initAttrs", "measureLeftLineToLeftDistance", "", "onChartClick", "index", "queryNearestTouchIndex", "x", "CircleBean", "CubicFloatChartBean", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CubicFloatBar<T extends FloatBarPoint> extends CustomChatBar<T> {
    private final String TAG;
    private HashMap _$_findViewCache;
    private int mBigCircleColor;
    private int mBigCircleRadius;
    private int mFillEndColor;
    private int mFillStartColor;
    private int mLineBottomColor;
    private int mLineBottomWidth;
    private int mLineTopColor;
    private int mLineTopWidth;
    private List<List<CubicFloatChartBean>> mRegionList;
    private Shader mShader;
    private int mSmallCircleColor;
    private int mSmallCircleRadius;

    public CubicFloatBar(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public CubicFloatBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = CubicFloatBar.class.getSimpleName();
        this.mLineTopColor = Color.parseColor("#FC4B69");
        this.mLineBottomColor = Color.parseColor("#DBA5AE");
        this.mLineTopWidth = 5;
        this.mLineBottomWidth = 2;
        this.mFillStartColor = Color.parseColor("#FFCDD5");
        this.mFillEndColor = Color.parseColor("#FFFFFF");
        this.mBigCircleColor = this.mFillStartColor;
        this.mBigCircleRadius = 5;
        this.mSmallCircleColor = this.mFillEndColor;
        this.mSmallCircleRadius = 2;
        this.mRegionList = new ArrayList();
        initAttrs(attributeSet);
    }

    public /* synthetic */ CubicFloatBar(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    /* JADX INFO: compiled from: CubicFloatBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006!"}, d2 = {"Lcom/ido/life/customview/charter/CubicFloatBar$CubicFloatChartBean;", "Lcom/ido/life/bean/BaseCharBean;", "dataIndex", "", "locationX", "", "minY", "maxY", "(IFFF)V", "getDataIndex", "()I", "setDataIndex", "(I)V", "getLocationX", "()F", "setLocationX", "(F)V", "getMaxY", "setMaxY", "getMinY", "setMinY", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class CubicFloatChartBean extends BaseCharBean {
        private int dataIndex;
        private float locationX;
        private float maxY;
        private float minY;

        public static /* synthetic */ CubicFloatChartBean copy$default(CubicFloatChartBean cubicFloatChartBean, int i, float f2, float f3, float f4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = cubicFloatChartBean.dataIndex;
            }
            if ((i2 & 2) != 0) {
                f2 = cubicFloatChartBean.locationX;
            }
            if ((i2 & 4) != 0) {
                f3 = cubicFloatChartBean.minY;
            }
            if ((i2 & 8) != 0) {
                f4 = cubicFloatChartBean.maxY;
            }
            return cubicFloatChartBean.copy(i, f2, f3, f4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getDataIndex() {
            return this.dataIndex;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getLocationX() {
            return this.locationX;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getMinY() {
            return this.minY;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getMaxY() {
            return this.maxY;
        }

        public final CubicFloatChartBean copy(int dataIndex, float locationX, float minY, float maxY) {
            return new CubicFloatChartBean(dataIndex, locationX, minY, maxY);
        }

        @Override // android.graphics.PointF
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CubicFloatChartBean)) {
                return false;
            }
            CubicFloatChartBean cubicFloatChartBean = (CubicFloatChartBean) other;
            return this.dataIndex == cubicFloatChartBean.dataIndex && Float.compare(this.locationX, cubicFloatChartBean.locationX) == 0 && Float.compare(this.minY, cubicFloatChartBean.minY) == 0 && Float.compare(this.maxY, cubicFloatChartBean.maxY) == 0;
        }

        @Override // android.graphics.PointF
        public int hashCode() {
            return (((((Integer.valueOf(this.dataIndex).hashCode() * 31) + Float.valueOf(this.locationX).hashCode()) * 31) + Float.valueOf(this.minY).hashCode()) * 31) + Float.valueOf(this.maxY).hashCode();
        }

        @Override // android.graphics.PointF
        public String toString() {
            return "CubicFloatChartBean(dataIndex=" + this.dataIndex + ", locationX=" + this.locationX + ", minY=" + this.minY + ", maxY=" + this.maxY + ")";
        }

        public CubicFloatChartBean(int i, float f2, float f3, float f4) {
            super(i, f2, f4);
            this.dataIndex = i;
            this.locationX = f2;
            this.minY = f3;
            this.maxY = f4;
        }

        public final int getDataIndex() {
            return this.dataIndex;
        }

        public final float getLocationX() {
            return this.locationX;
        }

        public final float getMaxY() {
            return this.maxY;
        }

        public final float getMinY() {
            return this.minY;
        }

        public final void setDataIndex(int i) {
            this.dataIndex = i;
        }

        public final void setLocationX(float f2) {
            this.locationX = f2;
        }

        public final void setMaxY(float f2) {
            this.maxY = f2;
        }

        public final void setMinY(float f2) {
            this.minY = f2;
        }
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: CubicFloatBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/ido/life/customview/charter/CubicFloatBar$CircleBean;", "Landroid/graphics/PointF;", "maxY", "", "minY", "locationX", "locationY", "(FFFF)V", "getLocationX", "()F", "setLocationX", "(F)V", "getLocationY", "setLocationY", "getMaxY", "setMaxY", "getMinY", "setMinY", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class CircleBean extends PointF {
        private float locationX;
        private float locationY;
        private float maxY;
        private float minY;

        public static /* synthetic */ CircleBean copy$default(CircleBean circleBean, float f2, float f3, float f4, float f5, int i, Object obj) {
            if ((i & 1) != 0) {
                f2 = circleBean.maxY;
            }
            if ((i & 2) != 0) {
                f3 = circleBean.minY;
            }
            if ((i & 4) != 0) {
                f4 = circleBean.locationX;
            }
            if ((i & 8) != 0) {
                f5 = circleBean.locationY;
            }
            return circleBean.copy(f2, f3, f4, f5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getMaxY() {
            return this.maxY;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getMinY() {
            return this.minY;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getLocationX() {
            return this.locationX;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getLocationY() {
            return this.locationY;
        }

        public final CircleBean copy(float maxY, float minY, float locationX, float locationY) {
            return new CircleBean(maxY, minY, locationX, locationY);
        }

        @Override // android.graphics.PointF
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CircleBean)) {
                return false;
            }
            CircleBean circleBean = (CircleBean) other;
            return Float.compare(this.maxY, circleBean.maxY) == 0 && Float.compare(this.minY, circleBean.minY) == 0 && Float.compare(this.locationX, circleBean.locationX) == 0 && Float.compare(this.locationY, circleBean.locationY) == 0;
        }

        @Override // android.graphics.PointF
        public int hashCode() {
            return (((((Float.valueOf(this.maxY).hashCode() * 31) + Float.valueOf(this.minY).hashCode()) * 31) + Float.valueOf(this.locationX).hashCode()) * 31) + Float.valueOf(this.locationY).hashCode();
        }

        @Override // android.graphics.PointF
        public String toString() {
            return "CircleBean(maxY=" + this.maxY + ", minY=" + this.minY + ", locationX=" + this.locationX + ", locationY=" + this.locationY + ")";
        }

        public CircleBean(float f2, float f3, float f4, float f5) {
            super(f4, f5);
            this.maxY = f2;
            this.minY = f3;
            this.locationX = f4;
            this.locationY = f5;
        }

        public final float getLocationX() {
            return this.locationX;
        }

        public final float getLocationY() {
            return this.locationY;
        }

        public final float getMaxY() {
            return this.maxY;
        }

        public final float getMinY() {
            return this.minY;
        }

        public final void setLocationX(float f2) {
            this.locationX = f2;
        }

        public final void setLocationY(float f2) {
            this.locationY = f2;
        }

        public final void setMaxY(float f2) {
            this.maxY = f2;
        }

        public final void setMinY(float f2) {
            this.minY = f2;
        }
    }

    public final int getMLineTopColor() {
        return this.mLineTopColor;
    }

    public final void setMLineTopColor(int i) {
        this.mLineTopColor = i;
    }

    public final int getMLineBottomColor() {
        return this.mLineBottomColor;
    }

    public final void setMLineBottomColor(int i) {
        this.mLineBottomColor = i;
    }

    public final int getMLineTopWidth() {
        return this.mLineTopWidth;
    }

    public final void setMLineTopWidth(int i) {
        this.mLineTopWidth = i;
    }

    public final int getMLineBottomWidth() {
        return this.mLineBottomWidth;
    }

    public final void setMLineBottomWidth(int i) {
        this.mLineBottomWidth = i;
    }

    public final int getMFillStartColor() {
        return this.mFillStartColor;
    }

    public final void setMFillStartColor(int i) {
        this.mFillStartColor = i;
    }

    public final int getMFillEndColor() {
        return this.mFillEndColor;
    }

    public final void setMFillEndColor(int i) {
        this.mFillEndColor = i;
    }

    public final int getMBigCircleColor() {
        return this.mBigCircleColor;
    }

    public final void setMBigCircleColor(int i) {
        this.mBigCircleColor = i;
    }

    public final int getMBigCircleRadius() {
        return this.mBigCircleRadius;
    }

    public final void setMBigCircleRadius(int i) {
        this.mBigCircleRadius = i;
    }

    public final int getMSmallCircleColor() {
        return this.mSmallCircleColor;
    }

    public final void setMSmallCircleColor(int i) {
        this.mSmallCircleColor = i;
    }

    public final int getMSmallCircleRadius() {
        return this.mSmallCircleRadius;
    }

    public final void setMSmallCircleRadius(int i) {
        this.mSmallCircleRadius = i;
    }

    private final void initAttrs(AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.CubicFloatBar);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr….styleable.CubicFloatBar)");
        this.mLineTopColor = typedArrayObtainStyledAttributes.getColor(8, this.mLineTopColor);
        this.mLineBottomColor = typedArrayObtainStyledAttributes.getColor(2, this.mLineBottomColor);
        this.mLineTopWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.mLineTopWidth);
        this.mLineBottomWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mLineBottomWidth);
        this.mFillStartColor = typedArrayObtainStyledAttributes.getColor(5, this.mFillStartColor);
        this.mFillEndColor = typedArrayObtainStyledAttributes.getColor(4, this.mFillEndColor);
        this.mBigCircleColor = typedArrayObtainStyledAttributes.getColor(0, this.mBigCircleColor);
        this.mBigCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mBigCircleRadius);
        this.mSmallCircleColor = typedArrayObtainStyledAttributes.getColor(6, this.mSmallCircleColor);
        this.mSmallCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, this.mSmallCircleRadius);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x01c5  */
    @Override // com.ido.life.customview.charter.CustomChatBar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawChat(android.graphics.Canvas r22) {
        /*
            Method dump skipped, instruction units count: 1042
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.CubicFloatBar.drawChat(android.graphics.Canvas):void");
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected float measureLeftLineToLeftDistance() {
        float fMax = 0.0f;
        float f2 = this.mLeftLineEnabled ? this.mLeftLineWidth + 0.0f : 0.0f;
        if (this.mLeftLabelEnabled && this.mLabelYLeftList != null && this.mLabelYLeftList.size() > 0) {
            fMax = measureLeftLabelMaxWidth() + this.mLeftLabelLineDistance;
        }
        if (measureBottomLableTitleRect() != null) {
            fMax = Math.max(fMax, r2.width() + this.mBottomTitleToYDistance);
        }
        return f2 + fMax + this.mCircleRadius + this.mCircleBorderWidth;
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        float f2;
        float f3;
        double d2;
        int i;
        if (!this.mRegionList.isEmpty()) {
            this.mRegionList.clear();
        }
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int circleSize = getCircleSize();
        this.mPaint.reset();
        Paint mPaint = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint, "mPaint");
        mPaint.setTextSize(this.mLeftLabelSize);
        Paint mPaint2 = this.mPaint;
        Intrinsics.checkExpressionValueIsNotNull(mPaint2, "mPaint");
        mPaint2.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int height = (getHeight() - measureBottomLineToBottomDistance()) - (rect.height() / 2);
        int iHeight = (rect.height() / 2) + height;
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
            iHeight -= this.mYGridBottomLineDistance;
        }
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        if (!this.mBottomLabelCenter) {
            fMeasureLeftLineToLeftDistance += circleSize;
            width -= circleSize * 2;
        }
        if (this.mXMaxValue - this.mXMinValue > 0) {
            if (this.mBottomLabelCenter) {
                d2 = ((double) width) * 1.0d;
                i = (this.mXMaxValue - this.mXMinValue) + 1;
            } else {
                d2 = ((double) width) * 1.0d;
                i = this.mXMaxValue - this.mXMinValue;
            }
            f2 = (float) (d2 / ((double) i));
        } else {
            f2 = 0.0f;
        }
        float f4 = this.mYMaxValue - this.mYMinValue > ((float) 0) ? (height * 1.0f) / (this.mYMaxValue - this.mYMinValue) : 0.0f;
        CommonLogUtil.d(this.TAG, "perY=" + f4 + ",mYMaxValue=" + this.mYMaxValue + ",mYMinValue=" + this.mYMinValue + ",endY=" + iHeight + ",yHeight=" + height);
        if (this.mList == null || this.mList.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.mRegionList.add(arrayList);
        int size = this.mList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = this.mList.get(i2);
            Intrinsics.checkExpressionValueIsNotNull(obj, "mList[i]");
            BaseCharBean baseCharBean = (BaseCharBean) obj;
            if (baseCharBean != null) {
                if (((int) baseCharBean.x) == 0 && ((int) baseCharBean.y) == 0) {
                    arrayList = new ArrayList();
                    this.mRegionList.add(arrayList);
                } else {
                    if (this.mBottomLabelCenter) {
                        f3 = (float) (((double) fMeasureLeftLineToLeftDistance) + (((double) f2) / 2.0d) + ((double) ((baseCharBean.x - this.mXMinValue) * f2)));
                    } else {
                        f3 = ((baseCharBean.x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance;
                    }
                    float f5 = iHeight;
                    FloatBarPoint floatBarPoint = (FloatBarPoint) baseCharBean;
                    arrayList.add(new CubicFloatChartBean(i2, f3, f5 - ((floatBarPoint.getMinValue() - this.mYMinValue) * f4), f5 - ((floatBarPoint.getMaxValue() - this.mYMinValue) * f4)));
                }
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void onChartClick(int index) {
        if (this.mClickListener == null) {
            return;
        }
        List<List<CubicFloatChartBean>> list = this.mRegionList;
        if ((list == null || list.isEmpty()) || index < 0) {
            return;
        }
        int size = this.mRegionList.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            List<CubicFloatChartBean> list2 = this.mRegionList.get(i);
            int size2 = list2.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size2) {
                    break;
                }
                CubicFloatChartBean cubicFloatChartBean = list2.get(i2);
                if (cubicFloatChartBean.getDataIndex() == index) {
                    this.mClickListener.onChartClick(this, new RectF(cubicFloatChartBean.getLocationX(), cubicFloatChartBean.getMaxY(), cubicFloatChartBean.getLocationX(), cubicFloatChartBean.getMinY()), cubicFloatChartBean.getDataIndex());
                    z = true;
                    break;
                }
                i2++;
            }
            if (z) {
                return;
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    public int queryNearestTouchIndex(int x) {
        if (this.mClickListener == null || !(!this.mRegionList.isEmpty())) {
            return -1;
        }
        int size = this.mRegionList.size();
        float f2 = Float.MAX_VALUE;
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            List<CubicFloatChartBean> list = this.mRegionList.get(i2);
            List<CubicFloatChartBean> list2 = list;
            if (!(list2 == null || list2.isEmpty())) {
                int size2 = list.size();
                float fAbs = f2;
                int dataIndex = i;
                for (int i3 = 0; i3 < size2; i3++) {
                    CubicFloatChartBean cubicFloatChartBean = list.get(i3);
                    float f3 = x;
                    if (Math.abs(cubicFloatChartBean.getLocationX() - f3) < fAbs) {
                        fAbs = Math.abs(cubicFloatChartBean.getLocationX() - f3);
                        dataIndex = cubicFloatChartBean.getDataIndex();
                    }
                }
                i = dataIndex;
                f2 = fAbs;
            }
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
    
        if (r0.isStarted() != false) goto L8;
     */
    @Override // com.ido.life.customview.charter.CustomChatBar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean canTouch() {
        /*
            r3 = this;
            android.animation.ValueAnimator r0 = r3.mAnimator
            r1 = 0
            if (r0 == 0) goto L1e
            android.animation.ValueAnimator r0 = r3.mAnimator
            java.lang.String r2 = "mAnimator"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L1d
            android.animation.ValueAnimator r0 = r3.mAnimator
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            boolean r0 = r0.isStarted()
            if (r0 == 0) goto L1e
        L1d:
            return r1
        L1e:
            java.util.List<java.util.List<com.ido.life.customview.charter.CubicFloatBar$CubicFloatChartBean>> r0 = r3.mRegionList
            java.util.Collection r0 = (java.util.Collection) r0
            r2 = 1
            if (r0 == 0) goto L2e
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L2c
            goto L2e
        L2c:
            r0 = r1
            goto L2f
        L2e:
            r0 = r2
        L2f:
            if (r0 == 0) goto L32
            return r1
        L32:
            com.ido.life.customview.charter.CustomChatBar$ChartClickListener r0 = r3.mClickListener
            if (r0 == 0) goto L37
            r1 = r2
        L37:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.CubicFloatBar.canTouch():boolean");
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    public void clearList() {
        super.clearList();
        if (!this.mRegionList.isEmpty()) {
            this.mRegionList.clear();
        }
    }
}