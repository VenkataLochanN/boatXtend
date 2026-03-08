package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX INFO: loaded from: classes2.dex */
public class CustomRoundAngleImageView extends AppCompatImageView {
    float height;
    int radiu;
    float width;

    public CustomRoundAngleImageView(Context context, int i) {
        this(context, (AttributeSet) null);
        this.radiu = i;
        init(context, null);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        init(context, attributeSet);
    }

    public CustomRoundAngleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.radiu = 0;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.width = getWidth();
        this.height = getHeight();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        float f2 = this.width;
        int i = this.radiu;
        if (f2 >= i && this.height > i) {
            Path path = new Path();
            path.moveTo(this.radiu, 0.0f);
            path.lineTo(this.width - this.radiu, 0.0f);
            float f3 = this.width;
            path.quadTo(f3, 0.0f, f3, this.radiu);
            path.lineTo(this.width, this.height - this.radiu);
            float f4 = this.width;
            float f5 = this.height;
            path.quadTo(f4, f5, f4 - this.radiu, f5);
            path.lineTo(this.radiu, this.height);
            float f6 = this.height;
            path.quadTo(0.0f, f6, 0.0f, f6 - this.radiu);
            path.lineTo(0.0f, this.radiu);
            path.quadTo(0.0f, 0.0f, this.radiu, 0.0f);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}