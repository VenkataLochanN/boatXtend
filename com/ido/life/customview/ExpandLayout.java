package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Html;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ido.life.R;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ExpandLayout extends RelativeLayout implements View.OnClickListener {
    private static final int STYLE_DEFAULT = 0;
    private static final int STYLE_ICON = 1;
    private static final int STYLE_TEXT = 2;
    private static final String TAG = ExpandLayout.class.getSimpleName();
    private String htmlColor;
    private String htmlText;
    private int mCollapseIconResId;
    private String mCollapseLessStr;
    private int mContentTextColor;
    private int mContentTextSize;
    private Context mContext;
    private CharSequence mEllipsizeStr;
    private int mExpandIconResId;
    private int mExpandIconWidth;
    private String mExpandMoreStr;
    private int mExpandStyle;
    private int mExpandTextColor;
    private int mExpandTextSize;
    private ImageView mIconExpand;
    private boolean mIsExpand;
    private LinearLayout mLayoutExpandMore;
    private float mLineSpacingExtra;
    private float mLineSpacingMultiplier;
    private int mMaxLines;
    private int mMeasuredWidth;
    private OnExpandStateChangeListener mOnExpandStateChangeListener;
    private CharSequence mOriginContentStr;
    private View mRootView;
    private int mSpaceMargin;
    private TextView mTvContent;
    private TextView mTvExpand;
    private TextView mTvExpandHelper;

    public interface OnExpandStateChangeListener {
        void onCollapse();

        void onExpand();
    }

    public ExpandLayout(Context context) {
        this(context, null);
    }

    public ExpandLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxLines = 2;
        this.mIsExpand = false;
        this.mExpandStyle = 0;
        this.mExpandIconWidth = 15;
        this.mSpaceMargin = 20;
        this.mLineSpacingExtra = 0.0f;
        this.mLineSpacingMultiplier = 1.0f;
        this.mContext = context;
        initAttributeSet(context, attributeSet);
        initView();
    }

    private void initAttributeSet(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExpandLayout);
        if (typedArrayObtainStyledAttributes != null) {
            this.mMaxLines = typedArrayObtainStyledAttributes.getInt(12, 3);
            this.mExpandIconResId = typedArrayObtainStyledAttributes.getResourceId(4, 0);
            this.mCollapseIconResId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            this.mExpandMoreStr = typedArrayObtainStyledAttributes.getString(6);
            this.mCollapseLessStr = typedArrayObtainStyledAttributes.getString(1);
            this.mContentTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, sp2px(context, 14.0f));
            this.mContentTextColor = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.mExpandTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, sp2px(context, 14.0f));
            this.mExpandTextColor = typedArrayObtainStyledAttributes.getColor(8, 0);
            this.mExpandStyle = typedArrayObtainStyledAttributes.getInt(7, 0);
            this.mExpandIconWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, dp2px(context, 15.0f));
            this.mSpaceMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, dp2px(context, 20.0f));
            this.mLineSpacingExtra = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, 0);
            this.mLineSpacingMultiplier = typedArrayObtainStyledAttributes.getFloat(11, 1.0f);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.mMaxLines < 1) {
            this.mMaxLines = 1;
        }
    }

    private void initView() {
        this.mRootView = inflate(this.mContext, com.boat.Xtend.two.R.layout.layout_expand, this);
        this.mTvContent = (TextView) findViewById(com.boat.Xtend.two.R.id.expand_content_tv);
        this.mLayoutExpandMore = (LinearLayout) findViewById(com.boat.Xtend.two.R.id.expand_ll);
        this.mIconExpand = (ImageView) findViewById(com.boat.Xtend.two.R.id.expand_iv);
        this.mTvExpand = (TextView) findViewById(com.boat.Xtend.two.R.id.expand_tv);
        this.mTvExpandHelper = (TextView) findViewById(com.boat.Xtend.two.R.id.expand_helper_tv);
        this.mTvExpand.setText(this.mExpandMoreStr);
        this.mTvContent.setTextSize(0, this.mContentTextSize);
        this.mTvExpandHelper.setTextSize(0, this.mContentTextSize);
        this.mTvExpand.setTextSize(0, this.mExpandTextSize);
        this.mTvContent.setLineSpacing(this.mLineSpacingExtra, this.mLineSpacingMultiplier);
        this.mTvExpandHelper.setLineSpacing(this.mLineSpacingExtra, this.mLineSpacingMultiplier);
        this.mTvExpand.setLineSpacing(this.mLineSpacingExtra, this.mLineSpacingMultiplier);
        setExpandMoreIcon(this.mExpandIconResId);
        setContentTextColor(this.mContentTextColor);
        setExpandTextColor(this.mExpandTextColor);
        int i = this.mExpandStyle;
        if (i == 1) {
            this.mIconExpand.setVisibility(0);
            this.mTvExpand.setVisibility(8);
        } else if (i == 2) {
            this.mIconExpand.setVisibility(8);
            this.mTvExpand.setVisibility(0);
        } else {
            this.mIconExpand.setVisibility(0);
            this.mTvExpand.setVisibility(0);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Log.d(TAG, "onMeasure,measureWidth = " + getMeasuredWidth());
        if (this.mMeasuredWidth > 0 || getMeasuredWidth() <= 0) {
            return;
        }
        this.mMeasuredWidth = getMeasuredWidth();
        measureEllipsizeText(this.mMeasuredWidth);
    }

    public void setContent(String str) {
        setContent(str, null);
    }

    public void setContent(CharSequence charSequence, String str, String str2) {
        if (TextUtils.isEmpty(charSequence) || this.mRootView == null) {
            return;
        }
        this.htmlText = str;
        this.htmlColor = str2;
        setContent(charSequence, null);
    }

    public void setContent(CharSequence charSequence, OnExpandStateChangeListener onExpandStateChangeListener) {
        if (TextUtils.isEmpty(charSequence) || this.mRootView == null) {
            return;
        }
        this.mOriginContentStr = charSequence;
        this.mOnExpandStateChangeListener = onExpandStateChangeListener;
        this.mTvContent.setMaxLines(this.mMaxLines);
        this.mTvContent.setText(this.mOriginContentStr);
        if (this.mMeasuredWidth <= 0) {
            Log.d(TAG, "宽度尚未获取到，第一次加载");
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.customview.ExpandLayout.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= 16) {
                        ExpandLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        ExpandLayout.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    ExpandLayout expandLayout = ExpandLayout.this;
                    expandLayout.mMeasuredWidth = expandLayout.getMeasuredWidth();
                    Log.d(ExpandLayout.TAG, "onGlobalLayout,控件宽度 = " + ExpandLayout.this.mMeasuredWidth);
                    ExpandLayout expandLayout2 = ExpandLayout.this;
                    expandLayout2.measureEllipsizeText(expandLayout2.mMeasuredWidth);
                }
            });
        } else {
            Log.d(TAG, "宽度已获取到，非第一次加载");
            measureEllipsizeText(this.mMeasuredWidth);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void measureEllipsizeText(int i) {
        if (TextUtils.isEmpty(this.mOriginContentStr)) {
            return;
        }
        handleMeasureEllipsizeText(i);
    }

    private void handleMeasureEllipsizeText(int i) {
        StaticLayout staticLayout = new StaticLayout(this.mOriginContentStr, this.mTvContent.getPaint(), i, Layout.Alignment.ALIGN_NORMAL, this.mLineSpacingMultiplier, this.mLineSpacingExtra, false);
        if (staticLayout.getLineCount() <= this.mMaxLines) {
            this.mEllipsizeStr = this.mOriginContentStr;
            this.mLayoutExpandMore.setVisibility(8);
            this.mTvContent.setMaxLines(Integer.MAX_VALUE);
            this.mTvContent.setText(this.mOriginContentStr);
            return;
        }
        this.mRootView.setOnClickListener(this);
        this.mLayoutExpandMore.setVisibility(0);
        handleEllipsizeString(staticLayout, i);
        handleLastLine(staticLayout, i);
        if (this.mIsExpand) {
            expand();
        } else {
            collapse();
        }
    }

    private void handleEllipsizeString(StaticLayout staticLayout, int i) {
        if (staticLayout == null) {
            return;
        }
        TextPaint paint = this.mTvContent.getPaint();
        int lineStart = staticLayout.getLineStart(this.mMaxLines - 1);
        int lineEnd = staticLayout.getLineEnd(this.mMaxLines - 1);
        Log.d(TAG, "startPos = " + lineStart);
        Log.d(TAG, "endPos = " + lineEnd);
        if (lineStart < 0) {
            lineStart = 0;
        }
        if (lineEnd > this.mOriginContentStr.length()) {
            lineEnd = this.mOriginContentStr.length();
        }
        if (lineStart > lineEnd) {
            lineStart = lineEnd;
        }
        String strSubstring = this.mOriginContentStr.toString().substring(lineStart, lineEnd);
        float fMeasureText = strSubstring != null ? paint.measureText(strSubstring) : 0.0f;
        Log.d(TAG, "第" + this.mMaxLines + "行 = " + strSubstring);
        Log.d(TAG, "第" + this.mMaxLines + "行 文本长度 = " + fMeasureText);
        float fMeasureText2 = ((float) this.mSpaceMargin) + paint.measureText("...") + getExpandLayoutReservedWidth();
        Log.d(TAG, "需要预留的长度 = " + fMeasureText2);
        float f2 = fMeasureText2 + fMeasureText;
        float f3 = (float) i;
        if (f2 > f3) {
            float f4 = f2 - f3;
            if (fMeasureText != 0.0f) {
                lineEnd -= (int) (((f4 / fMeasureText) * 1.0f) * (lineEnd - lineStart));
            }
        }
        Log.d(TAG, "correctEndPos = " + lineEnd);
        this.mEllipsizeStr = removeEndLineBreak(lineEnd <= 0 ? "" : this.mOriginContentStr.toString().substring(0, lineEnd)) + "...";
        if (TextUtils.isEmpty(this.htmlText)) {
            return;
        }
        this.mEllipsizeStr = Html.fromHtml("<font color='" + this.htmlColor + "'>" + this.htmlText + "</font>" + ((Object) this.mEllipsizeStr.subSequence(this.htmlText.length(), this.mEllipsizeStr.length())));
    }

    private void handleLastLine(StaticLayout staticLayout, int i) {
        int lineCount;
        if (staticLayout != null && (lineCount = staticLayout.getLineCount()) >= 1) {
            int i2 = lineCount - 1;
            int lineStart = staticLayout.getLineStart(i2);
            int lineEnd = staticLayout.getLineEnd(i2);
            Log.d(TAG, "最后一行 startPos = " + lineStart);
            Log.d(TAG, "最后一行 endPos = " + lineEnd);
            if (lineStart < 0) {
                lineStart = 0;
            }
            if (lineEnd > this.mOriginContentStr.length()) {
                lineEnd = this.mOriginContentStr.length();
            }
            if (lineStart > lineEnd) {
                lineStart = lineEnd;
            }
            String strSubstring = this.mOriginContentStr.toString().substring(lineStart, lineEnd);
            Log.d(TAG, "最后一行 内容 = " + strSubstring);
            float fMeasureText = strSubstring != null ? this.mTvContent.getPaint().measureText(strSubstring) : 0.0f;
            Log.d(TAG, "最后一行 文本长度 = " + fMeasureText);
            if (fMeasureText + getExpandLayoutReservedWidth() > i) {
                this.mOriginContentStr.toString().concat(IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
    }

    private float getExpandLayoutReservedWidth() {
        int i = this.mExpandStyle;
        int i2 = (i == 0 || i == 1) ? this.mExpandIconWidth : 0;
        int i3 = this.mExpandStyle;
        return i2 + ((i3 == 0 || i3 == 2) ? this.mTvExpand.getPaint().measureText(this.mExpandMoreStr) : 0.0f);
    }

    private String removeEndLineBreak(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        String string = charSequence.toString();
        return string.endsWith(IOUtils.LINE_SEPARATOR_UNIX) ? string.substring(0, string.length() - 1) : string;
    }

    public void setContentTextColor(int i) {
        if (i != 0) {
            this.mContentTextColor = i;
            this.mTvContent.setTextColor(i);
        }
    }

    public void setExpandTextColor(int i) {
        if (i != 0) {
            this.mExpandTextColor = i;
            this.mTvExpand.setTextColor(i);
        }
    }

    public void setExpandMoreIcon(int i) {
        if (i != 0) {
            this.mExpandIconResId = i;
            if (this.mIsExpand) {
                return;
            }
            this.mIconExpand.setImageResource(i);
        }
    }

    public void setCollapseLessIcon(int i) {
        if (i != 0) {
            this.mCollapseIconResId = i;
            if (this.mIsExpand) {
                this.mIconExpand.setImageResource(i);
            }
        }
    }

    public void expand() {
        setIsExpand(true);
        this.mTvContent.setMaxLines(Integer.MAX_VALUE);
        this.mTvContent.setText(this.mOriginContentStr);
        this.mTvExpand.setText(this.mCollapseLessStr);
        int i = this.mCollapseIconResId;
        if (i != 0) {
            this.mIconExpand.setImageResource(i);
        }
    }

    public void collapse() {
        setIsExpand(false);
        this.mTvContent.setMaxLines(Integer.MAX_VALUE);
        this.mTvContent.setText(this.mEllipsizeStr);
        this.mTvExpand.setText(this.mExpandMoreStr);
        int i = this.mExpandIconResId;
        if (i != 0) {
            this.mIconExpand.setImageResource(i);
        }
    }

    public int getLineCount() {
        TextView textView = this.mTvContent;
        if (textView == null) {
            return 0;
        }
        return textView.getLineCount();
    }

    public void setShrinkLines(int i) {
        this.mMaxLines = i;
    }

    public void setIsExpand(boolean z) {
        this.mIsExpand = z;
    }

    public boolean isExpand() {
        return this.mIsExpand;
    }

    private int dp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public int sp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.mIsExpand) {
            expand();
            OnExpandStateChangeListener onExpandStateChangeListener = this.mOnExpandStateChangeListener;
            if (onExpandStateChangeListener != null) {
                onExpandStateChangeListener.onExpand();
                return;
            }
            return;
        }
        collapse();
        OnExpandStateChangeListener onExpandStateChangeListener2 = this.mOnExpandStateChangeListener;
        if (onExpandStateChangeListener2 != null) {
            onExpandStateChangeListener2.onCollapse();
        }
    }
}