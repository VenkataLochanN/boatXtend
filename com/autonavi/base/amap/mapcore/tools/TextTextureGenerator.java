package com.autonavi.base.amap.mapcore.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.amap.api.mapcore.util.er;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class TextTextureGenerator {
    private static final int ALIGNCENTER = 51;
    private static final int ALIGNLEFT = 49;
    private static final int ALIGNRIGHT = 50;
    static final int AN_LABEL_MAXCHARINLINE = 7;
    static final int AN_LABEL_MULITYLINE_SPAN = 2;
    private int TEXT_FONTSIZE = -1;
    private int TEXT_FONTSIZE_TRUE = -1;
    private float base_line = 0.0f;
    private float start_x = 0.0f;
    private Paint text_paint = null;

    public static int GetNearstSize2N(int i) {
        int i2 = 1;
        while (i > i2) {
            i2 *= 2;
        }
        return i2;
    }

    public TextTextureGenerator() {
        createTextParam();
    }

    private void createTextParam() {
        this.TEXT_FONTSIZE_TRUE = this.TEXT_FONTSIZE - 2;
        this.text_paint = newPaint(null, this.TEXT_FONTSIZE_TRUE, 49);
        float f2 = (this.TEXT_FONTSIZE - this.TEXT_FONTSIZE_TRUE) / 2.0f;
        this.start_x = f2;
        float f3 = 7.3242188f;
        float f4 = -27.832031f;
        try {
            Paint.FontMetrics fontMetrics = this.text_paint.getFontMetrics();
            f3 = fontMetrics.descent;
            f4 = fontMetrics.ascent;
            float f5 = fontMetrics.top;
            float f6 = fontMetrics.bottom;
            float f7 = fontMetrics.leading;
        } catch (Exception unused) {
        }
        this.base_line = ((this.TEXT_FONTSIZE_TRUE - (f3 + f4)) / 2.0f) + f2 + 0.5f;
    }

    public byte[] getTextPixelBuffer(int i, int i2) {
        if (this.TEXT_FONTSIZE != i2) {
            this.TEXT_FONTSIZE = i2;
            createTextParam();
        }
        try {
            char c2 = (char) i;
            char[] cArr = {c2};
            float f2 = this.base_line;
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.TEXT_FONTSIZE, this.TEXT_FONTSIZE, Bitmap.Config.ALPHA_8);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            byte[] bArr = new byte[this.TEXT_FONTSIZE * this.TEXT_FONTSIZE];
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            float fMeasureText = this.text_paint.measureText(String.valueOf(c2));
            if (cArr[0] > 0 && cArr[0] < 256) {
                f2 -= 1.5f;
            }
            float f3 = f2;
            Paint.Align textAlign = this.text_paint.getTextAlign();
            float textSize = this.text_paint.getTextSize();
            float f4 = fMeasureText - this.TEXT_FONTSIZE_TRUE;
            if (textAlign != Paint.Align.CENTER && f4 >= 4.0f) {
                this.text_paint.setTextAlign(Paint.Align.CENTER);
                this.text_paint.setTextSize(this.TEXT_FONTSIZE_TRUE - f4);
                canvas.drawText(cArr, 0, 1, (this.TEXT_FONTSIZE_TRUE - f4) / 2.0f, f3, this.text_paint);
                this.text_paint.setTextAlign(textAlign);
                this.text_paint.setTextSize(textSize);
            } else {
                canvas.drawText(cArr, 0, 1, this.start_x, f3, this.text_paint);
            }
            bitmapCreateBitmap.copyPixelsToBuffer(byteBufferWrap);
            er.b(bitmapCreateBitmap);
            return bArr;
        } catch (Exception | OutOfMemoryError unused) {
            return null;
        }
    }

    public byte[] getCharsWidths(int[] iArr) {
        int length = iArr.length;
        byte[] bArr = new byte[length];
        float[] fArr = new float[1];
        for (int i = 0; i < length; i++) {
            fArr[0] = this.text_paint.measureText(((char) iArr[i]) + "");
            bArr[i] = (byte) ((int) (fArr[0] + ((float) (this.TEXT_FONTSIZE - this.TEXT_FONTSIZE_TRUE))));
        }
        return bArr;
    }

    private static Paint newPaint(String str, int i, int i2) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(-1);
        textPaint.setTextSize(i);
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        switch (i2) {
            case 49:
                textPaint.setTextAlign(Paint.Align.LEFT);
                return textPaint;
            case 50:
                textPaint.setTextAlign(Paint.Align.RIGHT);
                return textPaint;
            case 51:
                textPaint.setTextAlign(Paint.Align.CENTER);
                return textPaint;
            default:
                textPaint.setTextAlign(Paint.Align.LEFT);
                return textPaint;
        }
    }

    public static float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }
}