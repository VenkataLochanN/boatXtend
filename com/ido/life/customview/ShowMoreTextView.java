package com.ido.life.customview;

import android.content.Context;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.boat.Xtend.two.R;
import com.ido.life.customview.ShowMoreSpan;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ShowMoreTextView extends AppCompatTextView {
    private ClickableSpan mPressedSpan;
    private int maxShowLines;
    private ShowMoreSpan.OnAllSpanClickListener onAllSpanClickListener;
    private boolean result;

    public ShowMoreTextView(Context context) {
        super(context);
        this.maxShowLines = 2;
        this.mPressedSpan = null;
        this.result = false;
    }

    public ShowMoreTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxShowLines = 2;
        this.mPressedSpan = null;
        this.result = false;
    }

    public void setMyText(CharSequence charSequence) {
        super.setText(charSequence);
        post(new Runnable() { // from class: com.ido.life.customview.ShowMoreTextView.1
            @Override // java.lang.Runnable
            public void run() {
                ShowMoreTextView.this.addEllipsisAndAllAtEnd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEllipsisAndAllAtEnd() {
        int i = this.maxShowLines;
        if (i > 0 && i < getLineCount()) {
            try {
                float theTextNeedWidth = getTheTextNeedWidth(getPaint(), "..." + getContext().getString(R.string.device_more));
                if (getLayout().getLineRight(this.maxShowLines - 1) + theTextNeedWidth >= getLayout().getWidth()) {
                    setText(getText().subSequence(0, getLayout().getLineEnd(this.maxShowLines - 1) - 4));
                    if (getLayout().getLineRight(this.maxShowLines - 1) + theTextNeedWidth >= getLayout().getWidth()) {
                        setText(getText().subSequence(0, getLayout().getLineEnd(this.maxShowLines - 1) - 4));
                    }
                } else {
                    setText(getText().subSequence(0, getLayout().getLineEnd(this.maxShowLines - 1)));
                }
                if (getText().toString().endsWith(IOUtils.LINE_SEPARATOR_UNIX) && getText().length() >= 1) {
                    setText(getText().subSequence(0, getText().length() - 1));
                }
                append("...");
                SpannableString spannableString = new SpannableString(getContext().getString(R.string.device_more));
                spannableString.setSpan(new ShowMoreSpan(getContext(), this.onAllSpanClickListener), 0, spannableString.length(), 18);
                append(spannableString);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        setText(getText());
    }

    public void setOnAllSpanClickListener(ShowMoreSpan.OnAllSpanClickListener onAllSpanClickListener) {
        this.onAllSpanClickListener = onAllSpanClickListener;
    }

    public int getMaxShowLines() {
        return this.maxShowLines;
    }

    public void setMaxShowLines(int i) {
        this.maxShowLines = i;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Spannable spannableNewSpannable = Spannable.Factory.getInstance().newSpannable(getText());
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mPressedSpan = getPressedSpan(this, spannableNewSpannable, motionEvent);
            ClickableSpan clickableSpan = this.mPressedSpan;
            if (clickableSpan != null) {
                if (clickableSpan instanceof ShowMoreSpan) {
                    ((ShowMoreSpan) clickableSpan).setPressed(true);
                }
                Selection.setSelection(spannableNewSpannable, spannableNewSpannable.getSpanStart(this.mPressedSpan), spannableNewSpannable.getSpanEnd(this.mPressedSpan));
                this.result = true;
            } else {
                this.result = false;
            }
        } else if (action == 1) {
            ClickableSpan clickableSpan2 = this.mPressedSpan;
            if (clickableSpan2 != null) {
                if (clickableSpan2 instanceof ShowMoreSpan) {
                    ((ShowMoreSpan) clickableSpan2).setPressed(false);
                }
                this.mPressedSpan.onClick(this);
            }
            this.mPressedSpan = null;
            Selection.removeSelection(spannableNewSpannable);
        } else if (action == 2) {
            ClickableSpan pressedSpan = getPressedSpan(this, spannableNewSpannable, motionEvent);
            ClickableSpan clickableSpan3 = this.mPressedSpan;
            if (clickableSpan3 != null && clickableSpan3 != pressedSpan) {
                if (clickableSpan3 instanceof ShowMoreSpan) {
                    ((ShowMoreSpan) clickableSpan3).setPressed(false);
                }
                this.mPressedSpan = null;
                Selection.removeSelection(spannableNewSpannable);
            }
        }
        return this.result;
    }

    private ClickableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int totalPaddingLeft = (x - textView.getTotalPaddingLeft()) + textView.getScrollX();
        int totalPaddingTop = (y - textView.getTotalPaddingTop()) + textView.getScrollY();
        Layout layout = getLayout();
        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(totalPaddingTop), totalPaddingLeft);
        ShowMoreSpan[] showMoreSpanArr = (ShowMoreSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ShowMoreSpan.class);
        if (showMoreSpanArr == null || showMoreSpanArr.length <= 0) {
            return null;
        }
        return showMoreSpanArr[0];
    }

    private int getTheTextNeedWidth(Paint paint, String str) {
        float[] fArr = new float[str.length()];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (float f2 : fArr) {
            i = (int) (i + f2);
        }
        return i;
    }
}