package com.lzy.imagepicker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.lzy.imagepicker.R;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class CropImageView extends AppCompatImageView {
    private static final int DRAG = 1;
    private static final float MAX_SCALE = 4.0f;
    private static final int NONE = 0;
    private static final int ROTATE = 3;
    private static final int SAVE_ERROR = 1002;
    private static final int SAVE_SUCCESS = 1001;
    private static final int ZOOM = 2;
    private static final int ZOOM_OR_ROTATE = 4;
    private static Handler mHandler = new InnerHandler();
    private static OnBitmapSaveCompleteListener mListener;
    private PointF doubleClickPos;
    private long doubleClickTime;
    private boolean isInited;
    private int mBorderColor;
    private Paint mBorderPaint;
    private int mBorderWidth;
    private int mDefaultStyleIndex;
    private int mFocusHeight;
    private PointF mFocusMidPoint;
    private Path mFocusPath;
    private RectF mFocusRect;
    private int mFocusWidth;
    private int mImageHeight;
    private int mImageWidth;
    private int mMaskColor;
    private float mMaxScale;
    private int mRotatedImageHeight;
    private int mRotatedImageWidth;
    private boolean mSaving;
    private Style mStyle;
    private Matrix matrix;
    private PointF midPoint;
    private int mode;
    private float oldDist;
    private PointF pA;
    private PointF pB;
    private double rotation;
    private Matrix savedMatrix;
    private Style[] styles;
    private int sumRotateLevel;

    public interface OnBitmapSaveCompleteListener {
        void onBitmapSaveError(File file);

        void onBitmapSaveSuccess(File file);
    }

    public enum Style {
        RECTANGLE,
        CIRCLE
    }

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.styles = new Style[]{Style.RECTANGLE, Style.CIRCLE};
        this.mMaskColor = -1358954496;
        this.mBorderColor = -1434419072;
        this.mBorderWidth = 1;
        this.mFocusWidth = 250;
        this.mFocusHeight = 250;
        this.mDefaultStyleIndex = 0;
        this.mStyle = this.styles[this.mDefaultStyleIndex];
        this.mBorderPaint = new Paint();
        this.mFocusPath = new Path();
        this.mFocusRect = new RectF();
        this.matrix = new Matrix();
        this.savedMatrix = new Matrix();
        this.pA = new PointF();
        this.pB = new PointF();
        this.midPoint = new PointF();
        this.doubleClickPos = new PointF();
        this.mFocusMidPoint = new PointF();
        this.mode = 0;
        this.doubleClickTime = 0L;
        this.rotation = 0.0d;
        this.oldDist = 1.0f;
        this.sumRotateLevel = 0;
        this.mMaxScale = MAX_SCALE;
        this.isInited = false;
        this.mSaving = false;
        this.mFocusWidth = (int) TypedValue.applyDimension(1, this.mFocusWidth, getResources().getDisplayMetrics());
        this.mFocusHeight = (int) TypedValue.applyDimension(1, this.mFocusHeight, getResources().getDisplayMetrics());
        this.mBorderWidth = (int) TypedValue.applyDimension(1, this.mBorderWidth, getResources().getDisplayMetrics());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CropImageView);
        this.mMaskColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CropImageView_cropMaskColor, this.mMaskColor);
        this.mBorderColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CropImageView_cropBorderColor, this.mBorderColor);
        this.mBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_cropBorderWidth, this.mBorderWidth);
        this.mFocusWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_cropFocusWidth, this.mFocusWidth);
        this.mFocusHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CropImageView_cropFocusHeight, this.mFocusHeight);
        this.mDefaultStyleIndex = typedArrayObtainStyledAttributes.getInteger(R.styleable.CropImageView_cropStyle, this.mDefaultStyleIndex);
        this.mStyle = this.styles[this.mDefaultStyleIndex];
        typedArrayObtainStyledAttributes.recycle();
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        initImage();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        initImage();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        initImage();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        initImage();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.isInited = true;
        initImage();
    }

    private void initImage() {
        Drawable drawable = getDrawable();
        if (!this.isInited || drawable == null) {
            return;
        }
        this.mode = 0;
        this.matrix = getImageMatrix();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        this.mRotatedImageWidth = intrinsicWidth;
        this.mImageWidth = intrinsicWidth;
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.mRotatedImageHeight = intrinsicHeight;
        this.mImageHeight = intrinsicHeight;
        this.mFocusMidPoint = new PointF(getWidth() / 2.0f, getHeight() / 2.0f);
        if (this.mStyle == Style.CIRCLE) {
            int iMin = Math.min(this.mFocusWidth, this.mFocusHeight);
            this.mFocusWidth = iMin;
            this.mFocusHeight = iMin;
        }
        this.mFocusRect.left = this.mFocusMidPoint.x - (this.mFocusWidth / 2.0f);
        this.mFocusRect.right = this.mFocusMidPoint.x + (this.mFocusWidth / 2.0f);
        this.mFocusRect.top = this.mFocusMidPoint.y - (this.mFocusHeight / 2.0f);
        RectF rectF = this.mFocusRect;
        float f2 = this.mFocusMidPoint.y;
        int i = this.mFocusHeight;
        rectF.bottom = f2 + (i / 2.0f);
        float scale = getScale(this.mImageWidth, this.mImageHeight, this.mFocusWidth, i, true);
        this.mMaxScale = MAX_SCALE * scale;
        float fMax = Math.max(getScale(this.mImageWidth, this.mImageHeight, this.mFocusWidth, this.mFocusHeight, false), scale);
        this.matrix.setScale(fMax, fMax, this.mImageWidth / 2.0f, this.mImageHeight / 2.0f);
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        this.matrix.postTranslate(this.mFocusMidPoint.x - (fArr[2] + ((this.mImageWidth * fArr[0]) / 2.0f)), this.mFocusMidPoint.y - (fArr[5] + ((this.mImageHeight * fArr[4]) / 2.0f)));
        setImageMatrix(this.matrix);
        invalidate();
    }

    private float getScale(int i, int i2, int i3, int i4, boolean z) {
        float f2 = i3 / i;
        float f3 = i4 / i2;
        if (z) {
            return Math.max(f2, f3);
        }
        return Math.min(f2, f3);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Style.RECTANGLE == this.mStyle) {
            this.mFocusPath.addRect(this.mFocusRect, Path.Direction.CCW);
            canvas.save();
            canvas.clipRect(0, 0, getWidth(), getHeight());
            canvas.clipPath(this.mFocusPath, Region.Op.DIFFERENCE);
            canvas.drawColor(this.mMaskColor);
            canvas.restore();
        } else if (Style.CIRCLE == this.mStyle) {
            this.mFocusPath.addCircle(this.mFocusMidPoint.x, this.mFocusMidPoint.y, Math.min((this.mFocusRect.right - this.mFocusRect.left) / 2.0f, (this.mFocusRect.bottom - this.mFocusRect.top) / 2.0f), Path.Direction.CCW);
            canvas.save();
            canvas.clipRect(0, 0, getWidth(), getHeight());
            canvas.clipPath(this.mFocusPath, Region.Op.DIFFERENCE);
            canvas.drawColor(this.mMaskColor);
            canvas.restore();
        }
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
        this.mBorderPaint.setAntiAlias(true);
        canvas.drawPath(this.mFocusPath, this.mBorderPaint);
        this.mFocusPath.reset();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
    
        if (r2 != 6) goto L17;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r22) {
        /*
            Method dump skipped, instruction units count: 806
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lzy.imagepicker.view.CropImageView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void fixScale() {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        float fAbs = Math.abs(fArr[0]) + Math.abs(fArr[1]);
        float scale = getScale(this.mRotatedImageWidth, this.mRotatedImageHeight, this.mFocusWidth, this.mFocusHeight, true);
        this.mMaxScale = MAX_SCALE * scale;
        if (fAbs < scale) {
            float f2 = scale / fAbs;
            this.matrix.postScale(f2, f2);
            return;
        }
        float f3 = this.mMaxScale;
        if (fAbs > f3) {
            float f4 = f3 / fAbs;
            this.matrix.postScale(f4, f4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void fixTranslation() {
        /*
            r5 = this;
            android.graphics.RectF r0 = new android.graphics.RectF
            int r1 = r5.mImageWidth
            float r1 = (float) r1
            int r2 = r5.mImageHeight
            float r2 = (float) r2
            r3 = 0
            r0.<init>(r3, r3, r1, r2)
            android.graphics.Matrix r1 = r5.matrix
            r1.mapRect(r0)
            float r1 = r0.left
            android.graphics.RectF r2 = r5.mFocusRect
            float r2 = r2.left
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L24
            float r1 = r0.left
            float r1 = -r1
            android.graphics.RectF r2 = r5.mFocusRect
            float r2 = r2.left
        L22:
            float r1 = r1 + r2
            goto L37
        L24:
            float r1 = r0.right
            android.graphics.RectF r2 = r5.mFocusRect
            float r2 = r2.right
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 >= 0) goto L36
            float r1 = r0.right
            float r1 = -r1
            android.graphics.RectF r2 = r5.mFocusRect
            float r2 = r2.right
            goto L22
        L36:
            r1 = r3
        L37:
            float r2 = r0.top
            android.graphics.RectF r4 = r5.mFocusRect
            float r4 = r4.top
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L4b
            float r0 = r0.top
            float r0 = -r0
            android.graphics.RectF r2 = r5.mFocusRect
            float r2 = r2.top
        L48:
            float r3 = r0 + r2
            goto L5d
        L4b:
            float r2 = r0.bottom
            android.graphics.RectF r4 = r5.mFocusRect
            float r4 = r4.bottom
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L5d
            float r0 = r0.bottom
            float r0 = -r0
            android.graphics.RectF r2 = r5.mFocusRect
            float r2 = r2.bottom
            goto L48
        L5d:
            android.graphics.Matrix r0 = r5.matrix
            r0.postTranslate(r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lzy.imagepicker.view.CropImageView.fixTranslation():void");
    }

    private float maxPostScale() {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        return this.mMaxScale / (Math.abs(fArr[0]) + Math.abs(fArr[1]));
    }

    private float spacing(float f2, float f3, float f4, float f5) {
        float f6 = f2 - f4;
        float f7 = f3 - f5;
        return (float) Math.sqrt((f6 * f6) + (f7 * f7));
    }

    private float spacing(PointF pointF, PointF pointF2) {
        return spacing(pointF.x, pointF.y, pointF2.x, pointF2.y);
    }

    private void doubleClick(float f2, float f3) {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        float fAbs = Math.abs(fArr[0]) + Math.abs(fArr[1]);
        float scale = getScale(this.mRotatedImageWidth, this.mRotatedImageHeight, this.mFocusWidth, this.mFocusHeight, true);
        float f4 = this.mMaxScale;
        if (fAbs < f4) {
            float fMin = Math.min(scale + fAbs, f4) / fAbs;
            this.matrix.postScale(fMin, fMin, f2, f3);
        } else {
            float f5 = scale / fAbs;
            this.matrix.postScale(f5, f5, f2, f3);
            fixTranslation();
        }
        setImageMatrix(this.matrix);
    }

    public Bitmap getCropBitmap(int i, int i2, boolean z) {
        if (i <= 0 || i2 < 0) {
            return null;
        }
        return makeCropBitmap(rotate(((BitmapDrawable) getDrawable()).getBitmap(), this.sumRotateLevel * 90), this.mFocusRect, getImageMatrixRect(), i, i2, z);
    }

    public Bitmap rotate(Bitmap bitmap, int i) {
        if (i != 0 && bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(i, bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f);
            try {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                if (bitmap != bitmapCreateBitmap) {
                    return bitmapCreateBitmap;
                }
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }

    private RectF getImageMatrixRect() {
        RectF rectF = new RectF();
        rectF.set(0.0f, 0.0f, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        this.matrix.mapRect(rectF);
        return rectF;
    }

    private Bitmap makeCropBitmap(Bitmap bitmap, RectF rectF, RectF rectF2, int i, int i2, boolean z) {
        if (rectF2 == null || bitmap == null) {
            return null;
        }
        float fWidth = rectF2.width() / bitmap.getWidth();
        int i3 = (int) ((rectF.left - rectF2.left) / fWidth);
        int i4 = (int) ((rectF.top - rectF2.top) / fWidth);
        int iWidth = (int) (rectF.width() / fWidth);
        int iHeight = (int) (rectF.height() / fWidth);
        if (i3 < 0) {
            i3 = 0;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        if (i3 + iWidth > bitmap.getWidth()) {
            iWidth = bitmap.getWidth() - i3;
        }
        if (i4 + iHeight > bitmap.getHeight()) {
            iHeight = bitmap.getHeight() - i4;
        }
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i3, i4, iWidth, iHeight);
            if (i == iWidth && i2 == iHeight) {
                return bitmapCreateBitmap;
            }
            bitmap = Bitmap.createScaledBitmap(bitmapCreateBitmap, i, i2, true);
            if (this.mStyle != Style.CIRCLE || z) {
                return bitmap;
            }
            int iMin = Math.min(i, i2);
            int i5 = iMin / 2;
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(bitmapShader);
            canvas.drawCircle(i / 2.0f, i2 / 2.0f, i5, paint);
            return bitmapCreateBitmap2;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return bitmap;
        }
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.lzy.imagepicker.view.CropImageView$1] */
    public void saveBitmapToFile(File file, int i, int i2, boolean z) {
        if (this.mSaving) {
            return;
        }
        this.mSaving = true;
        final Bitmap cropBitmap = getCropBitmap(i, i2, z);
        final Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
        final File fileCreateFile = createFile(file, "IMG_", ".png");
        new Thread() { // from class: com.lzy.imagepicker.view.CropImageView.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                CropImageView.this.saveOutput(cropBitmap, compressFormat, fileCreateFile);
            }
        }.start();
    }

    private File createFile(File file, String str, String str2) {
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        try {
            File file2 = new File(file, ".nomedia");
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return new File(file, str + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date(System.currentTimeMillis())) + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveOutput(Bitmap bitmap, Bitmap.CompressFormat compressFormat, File file) {
        OutputStream outputStreamOpenOutputStream = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                outputStreamOpenOutputStream = getContext().getContentResolver().openOutputStream(Uri.fromFile(file));
                if (outputStreamOpenOutputStream != null) {
                    bitmap.compress(compressFormat, 90, outputStreamOpenOutputStream);
                }
                Message.obtain(mHandler, 1001, file).sendToTarget();
            } catch (IOException e3) {
                e3.printStackTrace();
                Message.obtain(mHandler, 1002, file).sendToTarget();
                if (outputStreamOpenOutputStream != null) {
                    outputStreamOpenOutputStream.close();
                }
            }
            if (outputStreamOpenOutputStream != null) {
                outputStreamOpenOutputStream.close();
            }
            this.mSaving = false;
            bitmap.recycle();
        } catch (Throwable th) {
            if (outputStreamOpenOutputStream != null) {
                try {
                    outputStreamOpenOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static class InnerHandler extends Handler {
        public InnerHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            File file = (File) message.obj;
            int i = message.what;
            if (i != 1001) {
                if (i == 1002 && CropImageView.mListener != null) {
                    CropImageView.mListener.onBitmapSaveError(file);
                    return;
                }
                return;
            }
            if (CropImageView.mListener != null) {
                CropImageView.mListener.onBitmapSaveSuccess(file);
            }
        }
    }

    public void setOnBitmapSaveCompleteListener(OnBitmapSaveCompleteListener onBitmapSaveCompleteListener) {
        mListener = onBitmapSaveCompleteListener;
    }

    public int getFocusWidth() {
        return this.mFocusWidth;
    }

    public void setFocusWidth(int i) {
        this.mFocusWidth = i;
        initImage();
    }

    public int getFocusHeight() {
        return this.mFocusHeight;
    }

    public void setFocusHeight(int i) {
        this.mFocusHeight = i;
        initImage();
    }

    public int getMaskColor() {
        return this.mMaskColor;
    }

    public void setMaskColor(int i) {
        this.mMaskColor = i;
        invalidate();
    }

    public int getFocusColor() {
        return this.mBorderColor;
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        invalidate();
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidth(int i) {
        this.mBorderWidth = i;
        invalidate();
    }

    public void setFocusStyle(Style style) {
        this.mStyle = style;
        invalidate();
    }

    public Style getFocusStyle() {
        return this.mStyle;
    }
}