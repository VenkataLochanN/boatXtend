package com.ido.life.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Adapter;
import android.widget.ListView;
import androidx.core.view.MotionEventCompat;
import com.google.android.material.timepicker.TimeModel;

/* JADX INFO: loaded from: classes3.dex */
public class ViewUtil {
    public static float calculateRectW(int i, int i2, float f2, float f3) {
        if (f3 >= 1.0f) {
            f3 = 1.0f;
        }
        if (f3 <= 0.0f) {
            f3 = 0.0f;
        }
        if (i2 == 0) {
            return 0.0f;
        }
        return i * ((f3 * f2) / i2);
    }

    public static int getColorBetweenAB(int i, int i2, float f2, int i3) {
        float f3 = i3;
        float f4 = (((((i2 & 16711680) >> 16) - r0) / f2) * f3) + ((16711680 & i) >> 16);
        int i4 = 65280 & i;
        return Color.rgb((int) f4, (int) ((((((i2 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) - i4) >> 8) / f2) * f3) + (i4 >> 8)), (int) (((((i2 & 255) - r3) / f2) * f3) + (i & 255)));
    }

    public static Bitmap blur(Bitmap bitmap, Context context) {
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, allocationCreateFromBitmap.getElement());
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.setRadius(25.0f);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap);
        allocationCreateFromBitmap.copyTo(bitmap);
        return bitmap;
    }

    public static float getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom - fontMetrics.top;
    }

    public static float getDrawBaselineY(Paint paint) {
        float f2 = paint.getFontMetricsInt().top;
        float f3 = paint.getFontMetricsInt().bottom;
        return ((f3 - f2) / 2.0f) - f3;
    }

    public static float getTextRectWidth(Paint paint, String str) {
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return paint.measureText(str);
    }

    public static float getTextRectHeight(Paint paint, String str) {
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return r0.height();
    }

    public static void startRotateAnimation(View view) {
        view.setVisibility(0);
        view.clearAnimation();
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(500L);
        rotateAnimation.setRepeatCount(-1);
        view.startAnimation(rotateAnimation);
    }

    public static void drawRect(Canvas canvas, float f2, float f3, float f4, int i, int i2, float f5, float f6, float f7, Paint paint) {
        float f8 = f3 + f7;
        canvas.drawRect(f2, f8, f2 + calculateRectW(i, i2, f5, f6), (f4 + f8) - f7, paint);
    }

    public static void drawText(Canvas canvas, int i, float f2, float f3, float f4, float f5, float f6, int i2, String str, String str2) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(i2);
        String str3 = (i / 60) + "";
        String str4 = (i % 60) + "";
        paint.setTextSize(f5);
        float textRectHeight = getTextRectHeight(paint, str3);
        canvas.drawText(str3, f2, f3, paint);
        float textRectWidth = f2 + f4 + getTextRectWidth(paint, str3);
        paint.setTextSize(f6);
        float textRectHeight2 = getTextRectHeight(paint, str);
        float f7 = f3 - ((textRectHeight > textRectHeight2 ? textRectHeight - textRectHeight2 : textRectHeight2 - textRectHeight) / 4.0f);
        canvas.drawText(str, textRectWidth, f7, paint);
        float textRectWidth2 = textRectWidth + f4 + getTextRectWidth(paint, str);
        paint.setTextSize(f5);
        canvas.drawText(str4, textRectWidth2, f3, paint);
        float textRectWidth3 = textRectWidth2 + f4 + getTextRectWidth(paint, str4);
        paint.setTextSize(f6);
        canvas.drawText(str2, textRectWidth3, f7, paint);
    }

    public static void drawText(Canvas canvas, int i, float f2, float f3, float f4, float f5, float f6, int i2, String str, String str2, Paint.Align align) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(align);
        paint.setColor(i2);
        String str3 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i / 60));
        String str4 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i % 60));
        paint.setTextSize(f5);
        float textRectHeight = getTextRectHeight(paint, str3);
        paint.setTextSize(f6);
        float textRectHeight2 = getTextRectHeight(paint, str);
        float f7 = textRectHeight > textRectHeight2 ? textRectHeight - textRectHeight2 : textRectHeight2 - textRectHeight;
        if (align == Paint.Align.LEFT) {
            paint.setTextSize(f5);
            canvas.drawText(str3, f2, f3, paint);
            float textRectWidth = f2 + f4 + getTextRectWidth(paint, str3);
            paint.setTextSize(f6);
            float f8 = f3 - (f7 / 4.0f);
            canvas.drawText(str, textRectWidth, f8, paint);
            float textRectWidth2 = textRectWidth + f4 + getTextRectWidth(paint, str);
            paint.setTextSize(f5);
            canvas.drawText(str4, textRectWidth2, f3, paint);
            float textRectWidth3 = textRectWidth2 + f4 + getTextRectWidth(paint, str4);
            paint.setTextSize(f6);
            canvas.drawText(str2, textRectWidth3, f8, paint);
            return;
        }
        if (align == Paint.Align.CENTER) {
            paint.setTextAlign(Paint.Align.RIGHT);
            float f9 = f4 / 2.0f;
            float f10 = f2 - f9;
            paint.setTextSize(f6);
            float f11 = f3 - (f7 / 4.0f);
            canvas.drawText(str, f10, f11, paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            float textRectWidth4 = (f10 - f4) - getTextRectWidth(paint, str);
            paint.setTextSize(f5);
            canvas.drawText(str3, textRectWidth4, f3, paint);
            paint.setTextAlign(Paint.Align.LEFT);
            float f12 = f2 + f9;
            paint.setTextSize(f5);
            canvas.drawText(str4, f12, f3, paint);
            paint.setTextAlign(Paint.Align.LEFT);
            float textRectWidth5 = f12 + f4 + getTextRectWidth(paint, str4);
            paint.setTextSize(f6);
            canvas.drawText(str2, textRectWidth5, f11, paint);
            return;
        }
        if (align == Paint.Align.RIGHT) {
            paint.setTextSize(f6);
            float f13 = f3 - (f7 / 4.0f);
            canvas.drawText(str2, f2, f13, paint);
            float textRectWidth6 = (f2 - f4) - getTextRectWidth(paint, str2);
            paint.setTextSize(f5);
            canvas.drawText(str4, textRectWidth6, f3, paint);
            float textRectWidth7 = (textRectWidth6 - f4) - getTextRectWidth(paint, str4);
            paint.setTextSize(f6);
            canvas.drawText(str, textRectWidth7, f13, paint);
            float textRectWidth8 = (textRectWidth7 - f4) - getTextRectWidth(paint, str);
            paint.setTextSize(f5);
            canvas.drawText(str3, textRectWidth8, f3, paint);
        }
    }

    public static float px2Dp(int i, Context context) {
        return i * context.getResources().getDisplayMetrics().density;
    }

    public static int getTotalHeightOfListView(ListView listView, Adapter adapter) {
        if (adapter == null) {
            return 0;
        }
        int measuredHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, listView);
            if (view.getMeasuredHeight() == 0) {
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            }
            measuredHeight += view.getMeasuredHeight();
        }
        return measuredHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
    }

    public static boolean getTotalHeightOfListView(ListView listView, Adapter adapter, int i) {
        if (adapter == null) {
            return false;
        }
        System.currentTimeMillis();
        int measuredHeight = 0;
        for (int i2 = 0; i2 < adapter.getCount(); i2++) {
            View view = adapter.getView(i2, null, listView);
            if (view.getMeasuredHeight() == 0) {
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight > i) {
                return true;
            }
        }
        return false;
    }

    public static void drawLine(Canvas canvas, float f2, float f3, float f4, float f5, Paint paint) {
        if (f4 == 0.0f) {
            return;
        }
        int i = (int) (f5 / f4);
        int i2 = 0;
        while (i2 < i) {
            i2++;
            float f6 = i2 * f4;
            float f7 = f6 + f2;
            float f8 = f6 + (f4 / 2.0f) + f2;
            canvas.drawLine(f7, f3, f8, f3, paint);
            if (f7 >= f5 || f8 >= f5) {
                return;
            }
        }
    }
}