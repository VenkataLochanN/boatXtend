package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class NotifyRadioButton extends MediumRadioButton {
    private Context context;
    boolean notify;
    Paint paint;
    float radius;

    public NotifyRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint(1);
        this.radius = TypedValue.applyDimension(0, 9.0f, context.getResources().getDisplayMetrics());
        this.context = context;
        initTypeFace(context);
        setButtonDrawable((Drawable) null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.notify) {
            this.paint.setColor(this.context.getResources().getColor(R.color.white));
            Rect bounds = getCompoundDrawables()[1].getBounds();
            canvas.drawCircle((getMeasuredWidth() / 2) + (bounds.width() / 2), getPaddingTop() + (bounds.height() / 5), this.radius, this.paint);
        } else {
            this.paint.setColor(0);
            canvas.drawLine(0.0f, 0.0f, 0.0f, 0.0f, this.paint);
        }
    }

    public void notify(boolean z) {
        this.notify = z;
        invalidate();
    }

    private void initTypeFace(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/MetricTest-Medium.otf"));
    }
}