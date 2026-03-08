package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class DrawableTextView extends AppCompatTextView {
    private int drawableBottomHeight;
    private int drawableBottomWidth;
    private int drawableLeftHeight;
    private int drawableLeftWidth;
    private int drawableRightHeight;
    private int drawableRightWidth;
    private int drawableTopHeight;
    private int drawableTopWidth;
    private boolean isAliganCenter;
    private boolean isDwMath_content;
    private int mHeight;
    private int mWidth;

    public DrawableTextView(Context context) {
        this(context, null);
    }

    public DrawableTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isAliganCenter = true;
        this.isDwMath_content = false;
        initView(context, attributeSet, i);
    }

    private void initView(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DrawableTextView);
        this.drawableLeftWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.drawableTopWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.drawableRightWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
        this.drawableBottomWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.drawableLeftHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.drawableTopHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.drawableRightHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0);
        this.drawableBottomHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.isAliganCenter = typedArrayObtainStyledAttributes.getBoolean(8, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = i;
        this.mHeight = i2;
        Drawable[] compoundDrawables = getCompoundDrawables();
        Drawable drawable = compoundDrawables[0];
        Drawable drawable2 = compoundDrawables[1];
        Drawable drawable3 = compoundDrawables[2];
        Drawable drawable4 = compoundDrawables[3];
        if (drawable != null) {
            setDrawable(drawable, 0, this.drawableLeftWidth, this.drawableLeftHeight);
        }
        if (drawable2 != null) {
            setDrawable(drawable2, 1, this.drawableTopWidth, this.drawableTopHeight);
        }
        if (drawable3 != null) {
            setDrawable(drawable3, 2, this.drawableRightWidth, this.drawableRightHeight);
        }
        if (drawable4 != null) {
            setDrawable(drawable4, 3, this.drawableBottomWidth, this.drawableBottomHeight);
        }
        setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setDrawable(android.graphics.drawable.Drawable r5, int r6, int r7, int r8) {
        /*
            r4 = this;
            if (r7 != 0) goto L6
            int r7 = r5.getIntrinsicWidth()
        L6:
            if (r8 != 0) goto Lc
            int r8 = r5.getIntrinsicHeight()
        Lc:
            r0 = 2
            r1 = 0
            if (r6 == 0) goto L2c
            r2 = 1
            if (r6 == r2) goto L19
            if (r6 == r0) goto L2c
            r6 = r1
            r7 = r6
            r8 = r7
            goto L44
        L19:
            boolean r6 = r4.isAliganCenter
            if (r6 == 0) goto L1f
            r6 = r1
            goto L26
        L1f:
            int r6 = r4.mWidth
            int r6 = -r6
            int r6 = r6 / r0
            int r0 = r7 / 2
            int r6 = r6 + r0
        L26:
            int r7 = r7 + r6
            int r8 = r8 + r1
            r3 = r1
            r1 = r6
            r6 = r3
            goto L44
        L2c:
            boolean r6 = r4.isAliganCenter
            if (r6 == 0) goto L32
            r6 = r1
            goto L43
        L32:
            int r6 = r4.getLineCount()
            int r6 = -r6
            int r2 = r4.getLineHeight()
            int r6 = r6 * r2
            int r6 = r6 / r0
            int r2 = r4.getLineHeight()
            int r2 = r2 / r0
            int r6 = r6 + r2
        L43:
            int r8 = r8 + r6
        L44:
            r5.setBounds(r1, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.DrawableTextView.setDrawable(android.graphics.drawable.Drawable, int, int, int):void");
    }
}