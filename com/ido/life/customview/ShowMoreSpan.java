package com.ido.life.customview;

import android.R;
import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class ShowMoreSpan extends ClickableSpan {
    private OnAllSpanClickListener clickListener;
    private Context context;
    private boolean isPressed = false;

    public interface OnAllSpanClickListener {
        void onClick(View view);
    }

    public ShowMoreSpan(Context context, OnAllSpanClickListener onAllSpanClickListener) {
        this.context = context;
        this.clickListener = onAllSpanClickListener;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        OnAllSpanClickListener onAllSpanClickListener = this.clickListener;
        if (onAllSpanClickListener != null) {
            onAllSpanClickListener.onClick(view);
        }
    }

    public void setPressed(boolean z) {
        this.isPressed = z;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        if (this.isPressed) {
            textPaint.bgColor = this.context.getResources().getColor(R.color.darker_gray);
        } else {
            textPaint.bgColor = this.context.getResources().getColor(R.color.transparent);
        }
        textPaint.setColor(this.context.getResources().getColor(R.color.holo_blue_light));
        textPaint.setUnderlineText(false);
    }
}