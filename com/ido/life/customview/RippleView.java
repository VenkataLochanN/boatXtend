package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class RippleView extends View {
    private int alphaCount;
    private int alphaCount2;
    private int alphaCount3;
    private int firstCount;
    private int firstRedius;
    private Handler handler;
    private boolean isStart;
    private int maxRadius;
    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private int sencondCount;
    private int sencondRedius;
    private int threeCount;
    private int threeRedius;

    public RippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxRadius = 0;
        this.handler = new Handler();
        this.firstCount = 40;
        this.sencondCount = 40;
        this.threeCount = 40;
        this.alphaCount = 80;
        this.alphaCount2 = 80;
        this.alphaCount3 = 80;
        this.isStart = false;
        init();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.maxRadius = (i / 2) + 120;
    }

    private void init() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setAlpha(100);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(1.0f);
        this.paint3 = new Paint();
        this.paint3.setAntiAlias(true);
        this.paint.setAlpha(50);
        this.paint3.setStyle(Paint.Style.FILL);
        this.paint3.setStrokeWidth(1.0f);
        this.paint2 = new Paint();
        this.paint2.setAntiAlias(true);
        this.paint.setAlpha(10);
        this.paint2.setStyle(Paint.Style.FILL);
        this.paint2.setStrokeWidth(1.0f);
        setPainColor(R.color.color_88C4F4);
    }

    public void setPainColor(int i) {
        this.paint.setColor(getResources().getColor(i));
        this.paint2.setColor(getResources().getColor(i));
        this.paint3.setColor(getResources().getColor(i));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isStart) {
            int i = this.maxRadius;
            this.firstRedius = ((41 - this.firstCount) * i) / 40;
            this.sencondRedius = ((41 - this.sencondCount) * i) / 40;
            this.threeRedius = ((41 - this.threeCount) * i) / 40;
            if (this.firstRedius < (i * 3) / 4) {
                this.paint.setAlpha(80);
            } else {
                this.alphaCount -= 10;
                int i2 = this.alphaCount;
                if (i2 > 0) {
                    this.paint.setAlpha(i2);
                } else {
                    this.paint.setAlpha(0);
                }
            }
            if (this.firstCount < 20) {
                if (this.sencondRedius < (this.maxRadius * 3) / 4) {
                    this.paint2.setAlpha(80);
                } else {
                    this.alphaCount2 -= 10;
                    int i3 = this.alphaCount2;
                    if (i3 > 0) {
                        this.paint2.setAlpha(i3);
                    } else {
                        this.paint2.setAlpha(0);
                    }
                }
                if (this.sencondCount > 1) {
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.sencondRedius, this.paint2);
                }
            }
            if (this.sencondCount < 20) {
                if (this.threeRedius < (this.maxRadius * 3) / 4) {
                    this.paint3.setAlpha(80);
                } else {
                    this.alphaCount3 -= 10;
                    int i4 = this.alphaCount3;
                    if (i4 > 0) {
                        this.paint3.setAlpha(i4);
                    } else {
                        this.paint3.setAlpha(0);
                    }
                }
                if (this.threeCount > 1) {
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.threeRedius, this.paint3);
                }
            }
            if (this.firstCount > 1) {
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, this.firstRedius, this.paint);
            }
            this.handler.postDelayed(new Runnable() { // from class: com.ido.life.customview.-$$Lambda$RippleView$ZuDMKyExDDu0MwhsDfWwv5bYXE0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDraw$1$RippleView();
                }
            }, 0L);
        }
    }

    public /* synthetic */ void lambda$onDraw$1$RippleView() {
        int i = this.firstCount;
        if (i > 1) {
            this.firstCount = i - 1;
            if (this.firstCount < 20) {
                this.sencondCount--;
            }
            invalidate();
            return;
        }
        int i2 = this.sencondCount;
        if (i2 > 1) {
            this.sencondCount = i2 - 1;
            if (this.sencondCount < 20) {
                this.threeCount--;
            }
            invalidate();
            return;
        }
        int i3 = this.threeCount;
        if (i3 > 1) {
            this.threeCount = i3 - 1;
            invalidate();
        } else {
            this.handler.postDelayed(new Runnable() { // from class: com.ido.life.customview.-$$Lambda$RippleView$Ybmy4i0mRps34yg1M7v-bsBkXx0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$null$0$RippleView();
                }
            }, 200L);
        }
    }

    public /* synthetic */ void lambda$null$0$RippleView() {
        reSet();
        invalidate();
    }

    private void reSet() {
        this.firstCount = 40;
        this.sencondCount = 40;
        this.threeCount = 40;
        this.alphaCount = 100;
        this.alphaCount2 = 100;
        this.alphaCount3 = 100;
    }

    public void startAnim() {
        this.isStart = true;
        invalidate();
    }

    public boolean isAnim() {
        return this.isStart;
    }

    public void stopAnim() {
        this.isStart = false;
        invalidate();
    }
}