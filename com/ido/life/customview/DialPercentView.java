package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.ido.ble.protocol.model.Sport100Type;

/* JADX INFO: loaded from: classes2.dex */
public class DialPercentView extends AppCompatImageView {
    private String[] color;
    private Paint[] paints;
    private RectF rect;

    public DialPercentView(Context context) {
        super(context);
    }

    public DialPercentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DialPercentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void init(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.color = str.split("\\*");
        this.paints = new Paint[this.color.length];
        for (int i = 0; i < this.color.length; i++) {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor(this.color[i]));
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            this.paints[i] = paint;
        }
        this.rect = new RectF();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        float f2 = 0;
        float width = getWidth() + 0;
        this.rect.set(f2, f2, width, width);
        String[] strArr = this.color;
        if (strArr.length <= 2) {
            while (true) {
                String[] strArr2 = this.color;
                if (i >= strArr2.length) {
                    return;
                }
                canvas.drawArc(this.rect, (r1 * i) + Sport100Type.SPORT_TYPE_SQUASH, SpatialRelationUtil.A_CIRCLE_DEGREE / strArr2.length, true, this.paints[i]);
                i++;
            }
        } else {
            int[] iArr = new int[strArr.length];
            while (true) {
                String[] strArr3 = this.color;
                if (i < strArr3.length) {
                    iArr[i] = Color.parseColor(strArr3[i]);
                    i++;
                } else {
                    new Paint();
                    SweepGradient sweepGradient = new SweepGradient(getWidth() / 2, getHeight() / 2, iArr, (float[]) null);
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setShader(sweepGradient);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, 300.0f, paint);
                    return;
                }
            }
        }
    }
}