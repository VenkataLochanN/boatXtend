package com.ido.life.customview.charter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.ido.life.R;
import com.ido.life.bean.ValueRangePair;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class CharterBase extends View {
    boolean anim;
    private long animDuration;
    boolean animFinished;
    private Interpolator animInterpolator;
    private CharterAnimListener animListener;
    ValueAnimator animator;
    protected int criticalValue;
    protected int defaultMaxValue;
    private int gridLinesCount;
    float height;
    protected int increaseAmount;
    protected boolean isValuePair;
    protected Paint mAbscisDashPaint;
    protected Paint mAbscissaPaint;
    protected float mBarWidth;
    protected Context mContext;
    protected Paint mCoordinatePaint;
    protected int mHeight;
    protected int mSelected;
    protected float mSelectedTextMarging;
    protected int mTargetValue;
    protected int mType;
    protected int mWidth;
    int maxY;
    int minY;
    private Paint paintGrid;
    Path path;
    private boolean showGridLinesX;
    private boolean showGridLinesY;
    protected int timeType;
    private int topSpace;
    int topTextH;
    protected List<ValueRangePair> valueList;
    List<ValueRangePair> valueListTransition;
    float[] values;
    float[] valuesTransition;
    float width;

    private long checkAnimDuration(long j) {
        if (j < 100) {
            return 100L;
        }
        return j;
    }

    public int getCriticalValue() {
        return this.criticalValue;
    }

    public void setCriticalValue(int i) {
        this.criticalValue = i;
    }

    public int getTopSpace() {
        return this.topSpace;
    }

    public void setTopSpace(int i) {
        this.topSpace = i;
    }

    protected CharterBase(Context context) {
        this(context, null);
    }

    protected CharterBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected CharterBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSelected = -1;
        this.mBarWidth = -1.0f;
        this.mSelectedTextMarging = 4.0f;
        this.timeType = 0;
        this.mType = -1;
        init(context, attributeSet);
    }

    protected CharterBase(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mSelected = -1;
        this.mBarWidth = -1.0f;
        this.mSelectedTextMarging = 4.0f;
        this.timeType = 0;
        this.mType = -1;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        if (isInEditMode()) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Charter);
        Resources resources = getResources();
        this.anim = typedArrayObtainStyledAttributes.getBoolean(0, true);
        this.animDuration = checkAnimDuration(typedArrayObtainStyledAttributes.getInt(1, 300));
        this.showGridLinesX = typedArrayObtainStyledAttributes.getBoolean(36, false);
        this.showGridLinesY = typedArrayObtainStyledAttributes.getBoolean(37, false);
        this.gridLinesCount = typedArrayObtainStyledAttributes.getInteger(16, 1);
        int color = typedArrayObtainStyledAttributes.getColor(15, resources.getColor(com.boat.Xtend.two.R.color.translate));
        float dimension = typedArrayObtainStyledAttributes.getDimension(17, resources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1));
        setWillNotDraw(true ^ typedArrayObtainStyledAttributes.getBoolean(2, true));
        typedArrayObtainStyledAttributes.recycle();
        this.paintGrid = new Paint();
        this.paintGrid.setColor(color);
        this.paintGrid.setStrokeWidth(dimension);
        this.paintGrid.setStyle(Paint.Style.STROKE);
        this.paintGrid.setPathEffect(new DashPathEffect(new float[]{8.0f, 5.0f}, 3.0f));
        this.path = new Path();
        this.animFinished = false;
        this.animInterpolator = new LinearInterpolator();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.height = getMeasuredHeight();
        this.width = getMeasuredWidth();
        invalidate();
    }

    public int getDefaultMaxValue() {
        return this.defaultMaxValue;
    }

    public void setDefaultMaxValue(int i) {
        this.defaultMaxValue = i;
    }

    public int getIncreaseAmount() {
        return this.increaseAmount;
    }

    public void setIncreaseAmount(int i) {
        this.increaseAmount = i;
    }

    public void show() {
        setWillNotDraw(false);
        invalidate();
    }

    public float[] getValues() {
        return this.values;
    }

    public void setValues(float[] fArr) {
        this.values = fArr;
        getMaxMinValues(fArr);
        convert2List(fArr);
        replayAnim(true);
    }

    public void setValuesNoAnimator(float[] fArr) {
        this.values = fArr;
        getMaxMinValues(fArr);
        convert2List(fArr);
        replayAnim(false);
    }

    public void setIsValuePair(boolean z) {
        this.isValuePair = z;
    }

    public void resetSelectedBar() {
        if (this.mSelected != -1) {
            this.mSelected = -1;
            invalidate();
        }
    }

    private void convert2List(float[] fArr) {
        if (this.valueList == null) {
            this.valueList = new ArrayList();
        }
        this.valueList.clear();
        for (float f2 : fArr) {
            Float fValueOf = Float.valueOf(f2);
            ValueRangePair valueRangePair = new ValueRangePair();
            valueRangePair.maxValue = fValueOf.floatValue();
            if (this.isValuePair) {
                valueRangePair.minValue = fValueOf.floatValue();
            }
            this.valueList.add(valueRangePair);
        }
    }

    public void setValues(List<ValueRangePair> list) {
        if (this.valueList == null) {
            this.valueList = new ArrayList();
        }
        this.valueList.clear();
        this.valueList.addAll(list);
        getMaxMinValues(this.valueList);
        replayAnim(true);
    }

    public void setValuesNoAnimator(List<ValueRangePair> list) {
        if (this.valueList == null) {
            this.valueList = new ArrayList();
        }
        this.valueList.clear();
        this.valueList.addAll(list);
        getMaxMinValues(this.valueList);
        replayAnim(false);
    }

    public void resetValues() {
        float[] fArr = this.values;
        if (fArr == null || fArr.length == 0) {
            return;
        }
        int i = 0;
        while (true) {
            float[] fArr2 = this.values;
            if (i < fArr2.length) {
                fArr2[i] = this.minY;
                i++;
            } else {
                setValues(fArr2);
                return;
            }
        }
    }

    protected void getMaxMinValues(float[] fArr) {
        if (fArr == null || fArr.length <= 0) {
            return;
        }
        this.maxY = Math.round(fArr[0]);
        for (float f2 : fArr) {
            Float fValueOf = Float.valueOf(f2);
            if (fValueOf.floatValue() > this.maxY) {
                this.maxY = Math.round(fValueOf.floatValue());
            }
        }
        if (this.defaultMaxValue != 0) {
            if (this.mType == 4 && this.timeType != 0) {
                if (this.mTargetValue > 12 || this.maxY > 12) {
                    this.maxY = 24;
                    return;
                } else {
                    this.maxY = 12;
                    return;
                }
            }
            int i = this.maxY;
            if (i <= this.defaultMaxValue || this.increaseAmount == 0) {
                this.maxY = this.defaultMaxValue;
                return;
            }
            int iRound = Math.round(i);
            int i2 = this.increaseAmount;
            this.maxY = ((this.maxY / i2) + (iRound % i2 != 0 ? 1 : 0)) * this.increaseAmount;
            return;
        }
        this.maxY = (this.maxY / 10) * 11;
    }

    protected void getMaxMinValues(List<ValueRangePair> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.maxY = 0;
        this.minY = 0;
        for (ValueRangePair valueRangePair : list) {
            if (valueRangePair != null) {
                if (valueRangePair.maxValue > this.maxY) {
                    this.maxY = Math.round(valueRangePair.maxValue);
                }
                float f2 = valueRangePair.minValue;
                int i = this.minY;
                if (f2 < i || i == 0) {
                    this.minY = Math.round(valueRangePair.minValue);
                }
            }
        }
        int i2 = this.defaultMaxValue;
        if (i2 != 0) {
            int i3 = this.maxY;
            if (i3 <= i2 || this.increaseAmount == 0) {
                this.maxY = this.defaultMaxValue;
                return;
            }
            int iRound = Math.round(i3);
            int i4 = this.increaseAmount;
            this.maxY = ((this.maxY / i4) + (iRound % i4 != 0 ? 1 : 0)) * this.increaseAmount;
            return;
        }
        this.maxY = (this.maxY / 10) * 11;
    }

    private void initValuesTarget(float[] fArr) {
        this.valuesTransition = (float[]) fArr.clone();
        int i = 0;
        while (true) {
            float[] fArr2 = this.valuesTransition;
            if (i >= fArr2.length) {
                return;
            }
            fArr2[i] = this.minY;
            i++;
        }
    }

    private void initValuesTarget(List<ValueRangePair> list) {
        if (this.valueListTransition == null) {
            this.valueListTransition = new ArrayList();
        }
        this.valueListTransition.clear();
        for (int i = 0; i < list.size(); i++) {
            ValueRangePair valueRangePair = list.get(i);
            if (valueRangePair == null) {
                valueRangePair = new ValueRangePair();
            }
            ValueRangePair valueRangePair2 = new ValueRangePair();
            float f2 = 0.0f;
            if (valueRangePair.maxValue != 0.0f) {
                f2 = this.minY - 2;
            }
            valueRangePair2.maxValue = f2;
            valueRangePair2.minValue = valueRangePair.minValue;
            valueRangePair2.mBarColor = valueRangePair.mBarColor;
            this.valueListTransition.add(valueRangePair2);
        }
    }

    public float getMaxY() {
        return this.maxY;
    }

    public void setMaxY(int i) {
        if (this.values == null) {
            throw new IllegalStateException("You must call setValues() first");
        }
        this.maxY = i;
        invalidate();
    }

    public float getMinY() {
        return this.minY;
    }

    public void setMinY(float f2) {
        if (this.values == null) {
            throw new IllegalStateException("You must call setValues() first");
        }
        this.minY = Math.round(f2);
        invalidate();
    }

    private void calculateNextAnimStep(float f2) {
        int i = (int) (this.maxY * f2);
        for (int i2 = 0; i2 < this.valueListTransition.size(); i2++) {
            ValueRangePair valueRangePair = null;
            List<ValueRangePair> list = this.valueList;
            if (list != null && i2 < list.size()) {
                valueRangePair = this.valueList.get(i2);
            }
            ValueRangePair valueRangePair2 = this.valueListTransition.get(i2);
            if (valueRangePair == null) {
                valueRangePair2.maxValue = 0.0f;
            } else {
                float f3 = i;
                if (f3 >= valueRangePair.maxValue) {
                    f3 = valueRangePair.maxValue;
                }
                valueRangePair2.maxValue = f3;
            }
        }
        CharterAnimListener charterAnimListener = this.animListener;
        if (charterAnimListener != null) {
            charterAnimListener.onAnimProgress(f2);
        }
    }

    public void replayAnim(boolean z) {
        List<ValueRangePair> list = this.valueList;
        if (list == null || list.size() == 0) {
            return;
        }
        this.mSelected = -1;
        initValuesTarget(this.valueList);
        if (z) {
            playAnimation();
            this.animFinished = false;
        } else {
            calculateNextAnimStep(1.0f);
            this.animFinished = true;
        }
        invalidate();
    }

    public boolean isAnim() {
        return this.anim;
    }

    public void setAnim(boolean z) {
        this.anim = z;
        replayAnim(true);
    }

    public long getAnimDuration() {
        return this.animDuration;
    }

    public void setAnimDuration(long j) {
        this.animDuration = checkAnimDuration(j);
        replayAnim(true);
    }

    public void setAnimListener(CharterAnimListener charterAnimListener) {
        this.animListener = charterAnimListener;
    }

    public void setAnimInterpolator(Interpolator interpolator) {
        this.animInterpolator = interpolator;
    }

    void playAnimation() {
        if (this.anim) {
            if (this.animator == null) {
                this.animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            }
            if (this.animator.isRunning()) {
                this.animator.cancel();
            }
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.charter.-$$Lambda$CharterBase$1Z4YE-DJk_gUyTZOIa22ZUATWKQ
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$playAnimation$0$CharterBase(valueAnimator);
                }
            });
            this.animator.addListener(new Animator.AnimatorListener() { // from class: com.ido.life.customview.charter.CharterBase.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    if (CharterBase.this.animListener != null) {
                        CharterBase.this.animListener.onAnimStart();
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    CharterBase charterBase = CharterBase.this;
                    charterBase.animFinished = true;
                    if (charterBase.animListener != null) {
                        CharterBase.this.animListener.onAnimFinish();
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    if (CharterBase.this.animListener != null) {
                        CharterBase.this.animListener.onAnimCancel();
                    }
                }
            });
            this.animator.setDuration(this.animDuration);
            this.animator.setInterpolator(this.animInterpolator);
            this.animator.start();
        }
    }

    public /* synthetic */ void lambda$playAnimation$0$CharterBase(ValueAnimator valueAnimator) {
        calculateNextAnimStep(((Float) valueAnimator.getAnimatedValue()).floatValue());
        invalidate();
    }

    public void setShowGridLines(boolean z) {
        this.showGridLinesX = z;
        this.showGridLinesY = z;
    }

    public boolean isShowGridLinesX() {
        return this.showGridLinesX;
    }

    public void setSelected(int i) {
        this.mSelected = i;
    }

    public int getSelected() {
        return this.mSelected;
    }

    public void setShowGridLinesX(boolean z) {
        this.showGridLinesX = z;
    }

    public boolean isShowGridLinesY() {
        return this.showGridLinesY;
    }

    public void setShowGridLinesY(boolean z) {
        this.showGridLinesY = z;
    }

    public int getGridLinesCount() {
        return this.gridLinesCount;
    }

    public void setGridLinesCount(int i) {
        if (i <= 1) {
            i = 1;
        }
        this.gridLinesCount = i;
    }

    public Paint getPaintGrid() {
        return this.paintGrid;
    }

    public void setPaintGrid(Paint paint) {
        this.paintGrid = paint;
    }

    public void setGridLinesColor(int i) {
        this.paintGrid.setColor(i);
        invalidate();
    }

    public int getGridLinesColor() {
        return this.paintGrid.getColor();
    }

    public float getGridLinesStrokeSize() {
        return this.paintGrid.getStrokeWidth();
    }

    public void setGridLinesStrokeSize(float f2) {
        this.paintGrid.setStrokeWidth(f2);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f2 = this.height;
        float f3 = this.width / this.gridLinesCount;
        this.path.reset();
        if (this.showGridLinesX || this.showGridLinesY) {
            for (int i = 0; i < this.gridLinesCount; i++) {
                if (this.showGridLinesX) {
                    int i2 = this.topTextH;
                    if (i2 > 0 && i == 0) {
                        this.path.moveTo(0.0f, i2);
                        this.path.lineTo(this.width, this.topTextH);
                    } else {
                        float f4 = this.height;
                        int i3 = this.topTextH;
                        float f5 = ((f4 - i3) / this.gridLinesCount) * i;
                        this.path.moveTo(0.0f, i3 + f5);
                        this.path.lineTo(this.width, f5 + this.topTextH);
                    }
                }
                if (this.showGridLinesY) {
                    float f6 = i * f3;
                    this.path.moveTo(f6, 0.0f);
                    this.path.lineTo(f6, this.height);
                }
            }
            canvas.drawPath(this.path, this.paintGrid);
        }
    }
}