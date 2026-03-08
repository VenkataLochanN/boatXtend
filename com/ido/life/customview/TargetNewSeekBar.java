package com.ido.life.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.common.log.CommonLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TargetNewSeekBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u0002PQB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u001aH\u0002J0\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020\u000eH\u0002J8\u0010B\u001a\u00020;2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020%2\u0006\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020\u000eH\u0002J\u0010\u0010D\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020\u000eH\u0002J\n\u0010F\u001a\u0004\u0018\u000101H\u0002J\u0010\u0010G\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0014J\u0018\u0010H\u001a\u00020;2\u0006\u0010I\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020\u001aH\u0014J\u0018\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\u00012\u0006\u0010N\u001a\u00020OH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR&\u0010 \u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR2\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R&\u0010+\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R&\u00102\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u001a8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001d\"\u0004\b4\u0010\u001fR\u000e\u00105\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Lcom/ido/life/customview/TargetNewSeekBar;", "Landroid/view/View;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "MIN_TOUCH_TIMESTAMP", "", "TAG", "", "mDownTimeStamp", "mDownX", "", "mListener", "Lcom/ido/life/customview/TargetNewSeekBar$TouchListenter;", "getMListener", "()Lcom/ido/life/customview/TargetNewSeekBar$TouchListenter;", "setMListener", "(Lcom/ido/life/customview/TargetNewSeekBar$TouchListenter;)V", "mMoveTimeStamp", "mMoveX", "mPaint", "Landroid/graphics/Paint;", "<set-?>", "", "mProgress", "getProgress", "()I", "setProgress", "(I)V", "mProgressHeight", "getProgressHeight", "setProgressHeight", "it", "", "Lcom/ido/life/customview/TargetNewSeekBar$Property;", "mProperTyList", "getPropertyList", "()Ljava/util/List;", "setPropertyList", "(Ljava/util/List;)V", "mRadius", "getRadius", "()F", "setRadius", "(F)V", "mThumbBitmap", "Landroid/graphics/Bitmap;", "mThumbIcon", "getThumbIcon", "setThumbIcon", "mTotalSpace", "mTouchMin", "mTouchState", "caluteThmubCenterX", NotificationCompat.CATEGORY_PROGRESS, "drawProgress", "", "canvas", "Landroid/graphics/Canvas;", "left", "top", "right", "bottom", "drawSingleProgress", "property", "findProgress", "x", "getThumbBitmap", "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouch", "", "v", "event", "Landroid/view/MotionEvent;", "Property", "TouchListenter", "app_release"}, k = 1, mv = {1, 1, 16})
public final class TargetNewSeekBar extends View implements View.OnTouchListener {
    private final long MIN_TOUCH_TIMESTAMP;
    private final String TAG;
    private HashMap _$_findViewCache;
    private long mDownTimeStamp;
    private float mDownX;
    private TouchListenter mListener;
    private long mMoveTimeStamp;
    private float mMoveX;
    private final Paint mPaint;
    private int mProgress;
    private int mProgressHeight;
    private List<Property> mProperTyList;
    private float mRadius;
    private Bitmap mThumbBitmap;
    private int mThumbIcon;
    private float mTotalSpace;
    private final int mTouchMin;
    private int mTouchState;

    /* JADX INFO: compiled from: TargetNewSeekBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/ido/life/customview/TargetNewSeekBar$TouchListenter;", "", "onProgressChanged", "", NotificationCompat.CATEGORY_PROGRESS, "", "centerX", "", "touching", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface TouchListenter {
        void onProgressChanged(int progress, float centerX, boolean touching);
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TargetNewSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.TAG = "TargetNewSeekBar";
        this.mThumbIcon = -1;
        this.mProperTyList = new ArrayList();
        this.mProgressHeight = getResources().getDimensionPixelSize(R.dimen.sw_dp_6);
        this.mPaint = new Paint();
        this.MIN_TOUCH_TIMESTAMP = 2000L;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setDither(true);
        this.mPaint.setAntiAlias(true);
        setOnTouchListener(this);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        Intrinsics.checkExpressionValueIsNotNull(viewConfiguration, "ViewConfiguration.get(context)");
        this.mTouchMin = viewConfiguration.getScaledTouchSlop();
    }

    /* JADX INFO: renamed from: getProgress, reason: from getter */
    public final int getMProgress() {
        return this.mProgress;
    }

    public final void setProgress(int i) {
        this.mProgress = i;
    }

    /* JADX INFO: renamed from: getThumbIcon, reason: from getter */
    public final int getMThumbIcon() {
        return this.mThumbIcon;
    }

    public final void setThumbIcon(int i) {
        this.mThumbIcon = i;
        this.mThumbBitmap = (Bitmap) null;
    }

    public final List<Property> getPropertyList() {
        return this.mProperTyList;
    }

    public final void setPropertyList(List<Property> it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        this.mProperTyList.clear();
        List<Property> list = it;
        if (!list.isEmpty()) {
            this.mProperTyList.addAll(list);
        }
        this.mTotalSpace = 0.0f;
        Iterator<T> it2 = this.mProperTyList.iterator();
        while (it2.hasNext()) {
            this.mTotalSpace += ((Property) it2.next()).getRightSpace();
        }
    }

    /* JADX INFO: renamed from: getProgressHeight, reason: from getter */
    public final int getMProgressHeight() {
        return this.mProgressHeight;
    }

    public final void setProgressHeight(int i) {
        this.mProgressHeight = i;
    }

    /* JADX INFO: renamed from: getRadius, reason: from getter */
    public final float getMRadius() {
        return this.mRadius;
    }

    public final void setRadius(float f2) {
        this.mRadius = f2;
    }

    public final TouchListenter getMListener() {
        return this.mListener;
    }

    public final void setMListener(TouchListenter touchListenter) {
        this.mListener = touchListenter;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        Bitmap thumbBitmap = getThumbBitmap();
        float width = (thumbBitmap != null ? thumbBitmap.getWidth() : 0) / 2.0f;
        drawProgress(canvas, width, (getHeight() / 2.0f) - (this.mProgressHeight / 2.0f), getWidth() - width, (getHeight() / 2.0f) + (this.mProgressHeight / 2.0f));
        if (thumbBitmap != null) {
            float fMin = Math.min(Math.max(caluteThmubCenterX(this.mProgress), thumbBitmap.getWidth() / 2.0f), getWidth() - (thumbBitmap.getWidth() / 2.0f));
            CommonLogUtil.d(this.TAG, "thumbCenterX=" + fMin);
            canvas.drawBitmap(thumbBitmap, fMin - (((float) thumbBitmap.getWidth()) / 2.0f), (((float) getHeight()) / 2.0f) - ((float) (thumbBitmap.getHeight() / 2)), this.mPaint);
            TouchListenter touchListenter = this.mListener;
            if (touchListenter != null) {
                touchListenter.onProgressChanged(this.mProgress, fMin, this.mTouchState != 1);
            }
        }
    }

    private final void drawProgress(Canvas canvas, float left, float top, float right, float bottom) {
        int iSave = canvas.save();
        Path path = new Path();
        float f2 = this.mRadius;
        path.addRoundRect(left, top, right, bottom, f2, f2, Path.Direction.CCW);
        canvas.clipPath(path);
        int size = this.mProperTyList.size();
        float f3 = ((right - left) - this.mTotalSpace) / size;
        int i = 0;
        int rightSpace = 0;
        while (i < size) {
            Property property = this.mProperTyList.get(i);
            float f4 = rightSpace;
            int i2 = i + 1;
            drawSingleProgress(canvas, property, left + (i * f3) + f4, top, left + (i2 * f3) + f4, bottom);
            rightSpace += property.getRightSpace();
            i = i2;
            iSave = iSave;
        }
        canvas.restoreToCount(iSave);
    }

    private final void drawSingleProgress(Canvas canvas, Property property, float left, float top, float right, float bottom) {
        CommonLogUtil.d(this.TAG, "绘制单个进度块,property=" + property + ",left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
        this.mPaint.setShader((Shader) null);
        if (property.getStartColor() == property.getEndColor()) {
            this.mPaint.setColor(property.getStartColor());
        } else {
            this.mPaint.setShader(new LinearGradient(left, top, right, top, property.getStartColor(), property.getEndColor(), Shader.TileMode.REPEAT));
        }
        property.setLeft(left);
        property.setRight(right);
        canvas.drawRect(left, top, right, bottom, this.mPaint);
    }

    private final float caluteThmubCenterX(int progress) {
        if (progress <= 0) {
            if (getThumbBitmap() != null) {
                return r12.getWidth() / 2.0f;
            }
            return 0.0f;
        }
        int size = this.mProperTyList.size();
        if (size == 0) {
            return 0.0f;
        }
        Bitmap thumbBitmap = getThumbBitmap();
        float width = ((getWidth() - this.mTotalSpace) - (thumbBitmap != null ? thumbBitmap.getWidth() : 0)) / size;
        float progressCount = 0.0f;
        int i = 0;
        int progressCount2 = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            Property property = this.mProperTyList.get(i);
            if (progress >= property.getProgressCount() + progressCount2) {
                progressCount = property.getRight();
                progressCount2 += property.getProgressCount();
                i++;
            } else {
                if (progressCount == 0.0f && thumbBitmap != null) {
                    progressCount = thumbBitmap.getWidth() / 2.0f;
                }
                int progressCount3 = property.getProgressCount();
                for (int i2 = 0; i2 < progressCount3; i2++) {
                    progressCount2++;
                    if (progressCount2 > progress) {
                        break;
                    }
                    progressCount += width / property.getProgressCount();
                }
            }
        }
        return progressCount;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
            setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
            return;
        }
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        Bitmap thumbBitmap = getThumbBitmap();
        setMeasuredDimension(size, thumbBitmap != null ? thumbBitmap.getHeight() : this.mProgressHeight);
    }

    private final Bitmap getThumbBitmap() {
        if (this.mThumbBitmap == null) {
            try {
                this.mThumbBitmap = BitmapFactory.decodeResource(getResources(), this.mThumbIcon);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Bitmap bitmap = this.mThumbBitmap;
        if (bitmap == null) {
            Intrinsics.throwNpe();
        }
        return bitmap;
    }

    private final int findProgress(float x) {
        int size = this.mProperTyList.size();
        int i = 0;
        int i2 = 0;
        int progressCount = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            Property property = this.mProperTyList.get(i2);
            if (x >= property.getRight()) {
                progressCount += property.getProgressCount();
                i2++;
            } else {
                float right = (property.getRight() - property.getLeft()) / property.getProgressCount();
                int progressCount2 = property.getProgressCount();
                while (i < progressCount2) {
                    i++;
                    if (x < property.getLeft() + (i * right)) {
                        break;
                    }
                    progressCount++;
                }
            }
        }
        return progressCount;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getPointerCount() != 1) {
            return false;
        }
        this.mTouchState = event.getAction();
        int action = event.getAction();
        if (action == 0) {
            this.mDownX = event.getX();
            this.mDownTimeStamp = System.currentTimeMillis();
        } else if (action == 1) {
            this.mMoveX = event.getX();
            this.mMoveTimeStamp = System.currentTimeMillis();
            if (Math.abs(this.mDownX - this.mMoveX) >= this.mTouchMin || this.mMoveTimeStamp - this.mDownTimeStamp <= this.MIN_TOUCH_TIMESTAMP) {
                this.mProgress = findProgress(this.mMoveX);
                invalidate();
            }
        } else if (action == 2) {
            this.mMoveX = event.getX();
            if (Math.abs(this.mDownX - this.mMoveX) >= this.mTouchMin) {
                this.mProgress = findProgress(this.mMoveX);
                invalidate();
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: TargetNewSeekBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\f¨\u0006#"}, d2 = {"Lcom/ido/life/customview/TargetNewSeekBar$Property;", "", "progressCount", "", "startColor", "endColor", "rightSpace", "left", "", "right", "(IIIIFF)V", "getEndColor", "()I", "getLeft", "()F", "setLeft", "(F)V", "getProgressCount", "getRight", "setRight", "getRightSpace", "getStartColor", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Property {
        private final int endColor;
        private float left;
        private final int progressCount;
        private float right;
        private final int rightSpace;
        private final int startColor;

        public static /* synthetic */ Property copy$default(Property property, int i, int i2, int i3, int i4, float f2, float f3, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = property.progressCount;
            }
            if ((i5 & 2) != 0) {
                i2 = property.startColor;
            }
            int i6 = i2;
            if ((i5 & 4) != 0) {
                i3 = property.endColor;
            }
            int i7 = i3;
            if ((i5 & 8) != 0) {
                i4 = property.rightSpace;
            }
            int i8 = i4;
            if ((i5 & 16) != 0) {
                f2 = property.left;
            }
            float f4 = f2;
            if ((i5 & 32) != 0) {
                f3 = property.right;
            }
            return property.copy(i, i6, i7, i8, f4, f3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getProgressCount() {
            return this.progressCount;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getStartColor() {
            return this.startColor;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getEndColor() {
            return this.endColor;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getRightSpace() {
            return this.rightSpace;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final float getLeft() {
            return this.left;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final float getRight() {
            return this.right;
        }

        public final Property copy(int progressCount, int startColor, int endColor, int rightSpace, float left, float right) {
            return new Property(progressCount, startColor, endColor, rightSpace, left, right);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Property)) {
                return false;
            }
            Property property = (Property) other;
            return this.progressCount == property.progressCount && this.startColor == property.startColor && this.endColor == property.endColor && this.rightSpace == property.rightSpace && Float.compare(this.left, property.left) == 0 && Float.compare(this.right, property.right) == 0;
        }

        public int hashCode() {
            return (((((((((Integer.valueOf(this.progressCount).hashCode() * 31) + Integer.valueOf(this.startColor).hashCode()) * 31) + Integer.valueOf(this.endColor).hashCode()) * 31) + Integer.valueOf(this.rightSpace).hashCode()) * 31) + Float.valueOf(this.left).hashCode()) * 31) + Float.valueOf(this.right).hashCode();
        }

        public String toString() {
            return "Property(progressCount=" + this.progressCount + ", startColor=" + this.startColor + ", endColor=" + this.endColor + ", rightSpace=" + this.rightSpace + ", left=" + this.left + ", right=" + this.right + ")";
        }

        public Property(int i, int i2, int i3, int i4, float f2, float f3) {
            this.progressCount = i;
            this.startColor = i2;
            this.endColor = i3;
            this.rightSpace = i4;
            this.left = f2;
            this.right = f3;
        }

        public final int getProgressCount() {
            return this.progressCount;
        }

        public final int getStartColor() {
            return this.startColor;
        }

        public final int getEndColor() {
            return this.endColor;
        }

        public final int getRightSpace() {
            return this.rightSpace;
        }

        public final float getLeft() {
            return this.left;
        }

        public final void setLeft(float f2) {
            this.left = f2;
        }

        public /* synthetic */ Property(int i, int i2, int i3, int i4, float f2, float f3, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, i3, (i5 & 8) != 0 ? 0 : i4, (i5 & 16) != 0 ? 0.0f : f2, (i5 & 32) != 0 ? 0.0f : f3);
        }

        public final float getRight() {
            return this.right;
        }

        public final void setRight(float f2) {
            this.right = f2;
        }
    }
}