package com.ido.common.widget.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.ido.common.R;

/* JADX INFO: loaded from: classes2.dex */
public class CustomizeImageView extends AppCompatImageView {
    private int borderColor;
    private float[] borderRadii;
    private RectF borderRectF;
    private int borderWidth;
    private Context context;
    private int cornerBottomLeftRadius;
    private int cornerBottomRightRadius;
    private int cornerRadius;
    private int cornerTopLeftRadius;
    private int cornerTopRightRadius;
    private int height;
    private int innerBorderColor;
    private int innerBorderWidth;
    private boolean isCircle;
    private boolean isCoverSrc;
    private int maskColor;
    private Paint paint;
    private Path path;
    private float radius;
    private Path srcPath;
    private float[] srcRadii;
    private RectF srcRectF;
    private int width;
    private Xfermode xfermode;

    public CustomizeImageView(Context context) {
        this(context, null);
    }

    public CustomizeImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomizeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.borderColor = -1;
        this.innerBorderColor = -1;
        this.context = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomizeImageView, 0, 0);
        for (int i2 = 0; i2 < typedArrayObtainStyledAttributes.getIndexCount(); i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.CustomizeImageView_is_cover_src) {
                this.isCoverSrc = typedArrayObtainStyledAttributes.getBoolean(index, this.isCoverSrc);
            } else if (index == R.styleable.CustomizeImageView_is_circle) {
                this.isCircle = typedArrayObtainStyledAttributes.getBoolean(index, this.isCircle);
            } else if (index == R.styleable.CustomizeImageView_border_width) {
                this.borderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.borderWidth);
            } else if (index == R.styleable.CustomizeImageView_border_color) {
                this.borderColor = typedArrayObtainStyledAttributes.getColor(index, this.borderColor);
            } else if (index == R.styleable.CustomizeImageView_inner_border_width) {
                this.innerBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.innerBorderWidth);
            } else if (index == R.styleable.CustomizeImageView_inner_border_color) {
                this.innerBorderColor = typedArrayObtainStyledAttributes.getColor(index, this.innerBorderColor);
            } else if (index == R.styleable.CustomizeImageView_corner_radius) {
                this.cornerRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.cornerRadius);
            } else if (index == R.styleable.CustomizeImageView_corner_top_left_radius) {
                this.cornerTopLeftRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.cornerTopLeftRadius);
            } else if (index == R.styleable.CustomizeImageView_corner_top_right_radius) {
                this.cornerTopRightRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.cornerTopRightRadius);
            } else if (index == R.styleable.CustomizeImageView_corner_bottom_left_radius) {
                this.cornerBottomLeftRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.cornerBottomLeftRadius);
            } else if (index == R.styleable.CustomizeImageView_corner_bottom_right_radius) {
                this.cornerBottomRightRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.cornerBottomRightRadius);
            } else if (index == R.styleable.CustomizeImageView_mask_color) {
                this.maskColor = typedArrayObtainStyledAttributes.getColor(index, this.maskColor);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        this.borderRadii = new float[8];
        this.srcRadii = new float[8];
        this.borderRectF = new RectF();
        this.srcRectF = new RectF();
        this.paint = new Paint();
        this.path = new Path();
        if (Build.VERSION.SDK_INT <= 27) {
            this.xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        } else {
            this.xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
            this.srcPath = new Path();
        }
        calculateRadii();
        clearInnerBorderWidth();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.width = i;
        this.height = i2;
        initBorderRectF();
        initSrcRectF();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(this.srcRectF, null, 31);
        if (!this.isCoverSrc) {
            int i = this.width;
            int i2 = this.borderWidth;
            int i3 = this.innerBorderWidth;
            int i4 = this.height;
            canvas.scale((((i - (i2 * 2)) - (i3 * 2)) * 1.0f) / i, (((i4 - (i2 * 2)) - (i3 * 2)) * 1.0f) / i4, i / 2.0f, i4 / 2.0f);
        }
        super.onDraw(canvas);
        this.paint.reset();
        this.path.reset();
        if (this.isCircle) {
            this.path.addCircle(this.width / 2.0f, this.height / 2.0f, this.radius, Path.Direction.CCW);
        } else {
            this.path.addRoundRect(this.srcRectF, this.srcRadii, Path.Direction.CCW);
        }
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setXfermode(this.xfermode);
        if (Build.VERSION.SDK_INT <= 27) {
            canvas.drawPath(this.path, this.paint);
        } else {
            this.srcPath.addRect(this.srcRectF, Path.Direction.CCW);
            this.srcPath.op(this.path, Path.Op.DIFFERENCE);
            canvas.drawPath(this.srcPath, this.paint);
        }
        this.paint.setXfermode(null);
        int i5 = this.maskColor;
        if (i5 != 0) {
            this.paint.setColor(i5);
            canvas.drawPath(this.path, this.paint);
        }
        canvas.restore();
        drawBorders(canvas);
    }

    private void drawBorders(Canvas canvas) {
        if (this.isCircle) {
            int i = this.borderWidth;
            if (i > 0) {
                drawCircleBorder(canvas, i, this.borderColor, this.radius - (i / 2.0f));
            }
            int i2 = this.innerBorderWidth;
            if (i2 > 0) {
                drawCircleBorder(canvas, i2, this.innerBorderColor, (this.radius - this.borderWidth) - (i2 / 2.0f));
                return;
            }
            return;
        }
        int i3 = this.borderWidth;
        if (i3 > 0) {
            drawRectFBorder(canvas, i3, this.borderColor, this.borderRectF, this.borderRadii);
        }
    }

    private void drawCircleBorder(Canvas canvas, int i, int i2, float f2) {
        initBorderPaint(i, i2);
        this.path.addCircle(this.width / 2.0f, this.height / 2.0f, f2, Path.Direction.CCW);
        canvas.drawPath(this.path, this.paint);
    }

    private void drawRectFBorder(Canvas canvas, int i, int i2, RectF rectF, float[] fArr) {
        initBorderPaint(i, i2);
        this.path.addRoundRect(rectF, fArr, Path.Direction.CCW);
        canvas.drawPath(this.path, this.paint);
    }

    private void initBorderPaint(int i, int i2) {
        this.path.reset();
        this.paint.setStrokeWidth(i);
        this.paint.setColor(i2);
        this.paint.setStyle(Paint.Style.STROKE);
    }

    private void initBorderRectF() {
        if (this.isCircle) {
            return;
        }
        RectF rectF = this.borderRectF;
        int i = this.borderWidth;
        rectF.set(i / 2.0f, i / 2.0f, this.width - (i / 2.0f), this.height - (i / 2.0f));
    }

    private void initSrcRectF() {
        if (this.isCircle) {
            this.radius = Math.min(this.width, this.height) / 2.0f;
            RectF rectF = this.srcRectF;
            int i = this.width;
            float f2 = this.radius;
            int i2 = this.height;
            rectF.set((i / 2.0f) - f2, (i2 / 2.0f) - f2, (i / 2.0f) + f2, (i2 / 2.0f) + f2);
            return;
        }
        this.srcRectF.set(0.0f, 0.0f, this.width, this.height);
        if (this.isCoverSrc) {
            this.srcRectF = this.borderRectF;
        }
    }

    private void calculateRadii() {
        if (this.isCircle) {
            return;
        }
        int i = 0;
        if (this.cornerRadius <= 0) {
            float[] fArr = this.borderRadii;
            int i2 = this.cornerTopLeftRadius;
            float f2 = i2;
            fArr[1] = f2;
            fArr[0] = f2;
            int i3 = this.cornerTopRightRadius;
            float f3 = i3;
            fArr[3] = f3;
            fArr[2] = f3;
            int i4 = this.cornerBottomRightRadius;
            float f4 = i4;
            fArr[5] = f4;
            fArr[4] = f4;
            int i5 = this.cornerBottomLeftRadius;
            float f5 = i5;
            fArr[7] = f5;
            fArr[6] = f5;
            float[] fArr2 = this.srcRadii;
            int i6 = this.borderWidth;
            float f6 = i2 - (i6 / 2.0f);
            fArr2[1] = f6;
            fArr2[0] = f6;
            float f7 = i3 - (i6 / 2.0f);
            fArr2[3] = f7;
            fArr2[2] = f7;
            float f8 = i4 - (i6 / 2.0f);
            fArr2[5] = f8;
            fArr2[4] = f8;
            float f9 = i5 - (i6 / 2.0f);
            fArr2[7] = f9;
            fArr2[6] = f9;
            return;
        }
        while (true) {
            float[] fArr3 = this.borderRadii;
            if (i >= fArr3.length) {
                return;
            }
            int i7 = this.cornerRadius;
            fArr3[i] = i7;
            this.srcRadii[i] = i7 - (this.borderWidth / 2.0f);
            i++;
        }
    }

    private void calculateRadiiAndRectF(boolean z) {
        if (z) {
            this.cornerRadius = 0;
        }
        calculateRadii();
        initBorderRectF();
        invalidate();
    }

    private void clearInnerBorderWidth() {
        if (this.isCircle) {
            return;
        }
        this.innerBorderWidth = 0;
    }

    public void isCoverSrc(boolean z) {
        this.isCoverSrc = z;
        initSrcRectF();
        invalidate();
    }

    public void isCircle(boolean z) {
        this.isCircle = z;
        clearInnerBorderWidth();
        initSrcRectF();
        invalidate();
    }

    public void setBorderWidth(int i) {
        this.borderWidth = dp2px(this.context, i);
        calculateRadiiAndRectF(false);
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    public void setInnerBorderWidth(int i) {
        this.innerBorderWidth = dp2px(this.context, i);
        clearInnerBorderWidth();
        invalidate();
    }

    public void setInnerBorderColor(int i) {
        this.innerBorderColor = i;
        invalidate();
    }

    public void setCornerRadius(int i) {
        this.cornerRadius = dp2px(this.context, i);
        calculateRadiiAndRectF(false);
    }

    public void setCornerTopLeftRadius(int i) {
        this.cornerTopLeftRadius = dp2px(this.context, i);
        calculateRadiiAndRectF(true);
    }

    public void setCornerTopRightRadius(int i) {
        this.cornerTopRightRadius = dp2px(this.context, i);
        calculateRadiiAndRectF(true);
    }

    public void setCornerBottomLeftRadius(int i) {
        this.cornerBottomLeftRadius = dp2px(this.context, i);
        calculateRadiiAndRectF(true);
    }

    public void setCornerBottomRightRadius(int i) {
        this.cornerBottomRightRadius = dp2px(this.context, i);
        calculateRadiiAndRectF(true);
    }

    public void setMaskColor(int i) {
        this.maskColor = i;
        invalidate();
    }

    private int dp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}