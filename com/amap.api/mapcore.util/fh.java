package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: IndoorFloorSwitchView.java */
/* JADX INFO: loaded from: classes.dex */
public class fh extends ScrollView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f841a = fh.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f842b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f843c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LinearLayout f844d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f845e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<String> f846f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f847g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f848h;
    private Bitmap i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private Runnable p;
    private int q;
    private a r;

    /* JADX INFO: compiled from: IndoorFloorSwitchView.java */
    public interface a {
        void a(int i);
    }

    public fh(Context context) {
        super(context);
        this.f845e = 0;
        this.f847g = -1;
        this.i = null;
        this.j = Color.parseColor("#eeffffff");
        this.k = Color.parseColor("#44383838");
        this.l = 4;
        this.m = 1;
        this.f842b = 1;
        this.q = 50;
        a(context);
    }

    private void a(Context context) {
        this.f843c = context;
        setVerticalScrollBarEnabled(false);
        try {
            if (this.i == null) {
                InputStream inputStreamOpen = el.a(context).open("map_indoor_select.png");
                this.i = BitmapFactory.decodeStream(inputStreamOpen);
                inputStreamOpen.close();
            }
        } catch (Throwable unused) {
        }
        this.f844d = new LinearLayout(context);
        this.f844d.setOrientation(1);
        addView(this.f844d);
        this.p = new Runnable() { // from class: com.amap.api.mapcore.util.fh.1
            @Override // java.lang.Runnable
            public void run() {
                if (fh.this.o - fh.this.getScrollY() == 0) {
                    if (fh.this.f845e == 0) {
                        return;
                    }
                    final int i = fh.this.o % fh.this.f845e;
                    final int i2 = fh.this.o / fh.this.f845e;
                    if (i != 0) {
                        if (i > fh.this.f845e / 2) {
                            fh.this.post(new Runnable() { // from class: com.amap.api.mapcore.util.fh.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    fh.this.smoothScrollTo(0, (fh.this.o - i) + fh.this.f845e);
                                    fh.this.f842b = i2 + fh.this.m + 1;
                                    fh.this.g();
                                }
                            });
                            return;
                        } else {
                            fh.this.post(new Runnable() { // from class: com.amap.api.mapcore.util.fh.1.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    fh.this.smoothScrollTo(0, fh.this.o - i);
                                    fh.this.f842b = i2 + fh.this.m;
                                    fh.this.g();
                                }
                            });
                            return;
                        }
                    }
                    fh fhVar = fh.this;
                    fhVar.f842b = i2 + fhVar.m;
                    fh.this.g();
                    return;
                }
                fh fhVar2 = fh.this;
                fhVar2.o = fhVar2.getScrollY();
                fh fhVar3 = fh.this;
                fhVar3.postDelayed(fhVar3.p, fh.this.q);
            }
        };
    }

    public void a() {
        this.o = getScrollY();
        postDelayed(this.p, this.q);
    }

    private void e() {
        List<String> list = this.f846f;
        if (list == null || list.size() == 0) {
            return;
        }
        this.f844d.removeAllViews();
        this.n = (this.m * 2) + 1;
        for (int size = this.f846f.size() - 1; size >= 0; size--) {
            this.f844d.addView(b(this.f846f.get(size)));
        }
        a(0);
    }

    private TextView b(String str) {
        TextView textView = new TextView(this.f843c);
        textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        textView.setSingleLine(true);
        textView.setTextSize(2, 16.0f);
        textView.setText(str);
        textView.setGravity(17);
        textView.getPaint().setFakeBoldText(true);
        int iA = a(this.f843c, 8.0f);
        int iA2 = a(this.f843c, 6.0f);
        textView.setPadding(iA, iA2, iA, iA2);
        if (this.f845e == 0) {
            this.f845e = a(textView);
            this.f844d.setLayoutParams(new FrameLayout.LayoutParams(-2, this.f845e * this.n));
            setLayoutParams(new LinearLayout.LayoutParams(-2, this.f845e * this.n));
        }
        return textView;
    }

    private void a(int i) {
        int i2 = this.f845e;
        if (i2 == 0) {
            return;
        }
        int i3 = this.m;
        int i4 = (i / i2) + i3;
        int i5 = i % i2;
        int i6 = i / i2;
        if (i5 == 0) {
            i4 = i6 + i3;
        } else if (i5 > i2 / 2) {
            i4 = i6 + i3 + 1;
        }
        int childCount = this.f844d.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            TextView textView = (TextView) this.f844d.getChildAt(i7);
            if (textView == null) {
                return;
            }
            if (i4 == i7) {
                textView.setTextColor(Color.parseColor("#0288ce"));
            } else {
                textView.setTextColor(Color.parseColor("#bbbbbb"));
            }
        }
    }

    public void a(String[] strArr) {
        if (this.f846f == null) {
            this.f846f = new ArrayList();
        }
        this.f846f.clear();
        for (String str : strArr) {
            this.f846f.add(str);
        }
        for (int i = 0; i < this.m; i++) {
            this.f846f.add(0, "");
            this.f846f.add("");
        }
        e();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.j = i;
    }

    public void b() {
        Bitmap bitmap = this.i;
        if (bitmap != null && !bitmap.isRecycled()) {
            er.b(this.i);
            this.i = null;
        }
        if (this.r != null) {
            this.r = null;
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.f848h == 0) {
            try {
                WindowManager windowManager = (WindowManager) this.f843c.getSystemService("window");
                if (windowManager != null) {
                    this.f848h = windowManager.getDefaultDisplay().getWidth();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        super.setBackgroundDrawable(new Drawable() { // from class: com.amap.api.mapcore.util.fh.2
            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return 0;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int i) {
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter colorFilter) {
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                try {
                    a(canvas);
                    b(canvas);
                    c(canvas);
                } catch (Throwable unused) {
                }
            }

            private void a(Canvas canvas) {
                canvas.drawColor(fh.this.j);
            }

            private void b(Canvas canvas) {
                Paint paint = new Paint();
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                rect.left = 0;
                rect.top = 0;
                rect.right = fh.this.i.getWidth() + 0;
                rect.bottom = fh.this.i.getHeight() + 0;
                rect2.left = 0;
                rect2.top = fh.this.f()[0];
                rect2.right = fh.this.f848h + 0;
                rect2.bottom = fh.this.f()[1];
                canvas.drawBitmap(fh.this.i, rect, rect2, paint);
            }

            private void c(Canvas canvas) {
                Paint paint = new Paint();
                Rect clipBounds = canvas.getClipBounds();
                paint.setColor(fh.this.k);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(fh.this.l);
                canvas.drawRect(clipBounds, paint);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] f() {
        int i = this.f845e;
        int i2 = this.m;
        return new int[]{i * i2, i * (i2 + 1)};
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f848h = i;
        try {
            setBackgroundDrawable(null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        a(i2);
        if (i2 > i4) {
            this.f847g = 1;
        } else {
            this.f847g = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a aVar = this.r;
        if (aVar != null) {
            try {
                aVar.a(c());
            } catch (Throwable unused) {
            }
        }
    }

    public void a(String str) {
        List<String> list = this.f846f;
        if (list == null || list.size() == 0) {
            return;
        }
        int iIndexOf = this.f846f.indexOf(str);
        int size = this.f846f.size();
        final int i = ((size - r1) - 1) - iIndexOf;
        this.f842b = this.m + i;
        post(new Runnable() { // from class: com.amap.api.mapcore.util.fh.3
            @Override // java.lang.Runnable
            public void run() {
                fh fhVar = fh.this;
                fhVar.smoothScrollTo(0, i * fhVar.f845e);
            }
        });
    }

    public int c() {
        List<String> list = this.f846f;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return Math.min(this.f846f.size() - (this.m * 2), Math.max(0, ((this.f846f.size() - 1) - this.f842b) - this.m));
    }

    @Override // android.widget.ScrollView
    public void fling(int i) {
        super.fling(i / 3);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a(a aVar) {
        this.r = aVar;
    }

    public static int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int a(View view) {
        b(view);
        return view.getMeasuredHeight();
    }

    public static void b(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    public void a(boolean z) {
        setVisibility(z ? 0 : 8);
    }

    public boolean d() {
        return getVisibility() == 0;
    }
}