package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CitySideBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u00015B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0014J\u0010\u0010-\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010\u0016J\u0010\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020\fH\u0002J\u000e\u00101\u001a\u00020*2\u0006\u00102\u001a\u00020\u001fJ\u001b\u00103\u001a\u00020*2\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e¢\u0006\u0002\u0010#R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010%\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/ido/life/customview/CitySideBar;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "isOnTouch", "", "()Z", "setOnTouch", "(Z)V", "mChoose", "getMChoose", "()I", "setMChoose", "(I)V", "mOnTouchSessionChangedListener", "Lcom/ido/life/customview/CitySideBar$OnTouchSessionChangedListener;", "mPaint", "Landroid/graphics/Paint;", "getMPaint", "()Landroid/graphics/Paint;", "setMPaint", "(Landroid/graphics/Paint;)V", "mSessions", "", "", "getMSessions", "()[Ljava/lang/String;", "setMSessions", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "sessionHeight", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "setOnTouchingSessionChangedListener", "onTouchSessionChangedListener", "setPaintColor", "isChoose", "setSelectedSession", "s", "setSideBarSessions", "sessions", "OnTouchSessionChangedListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CitySideBar extends View {
    private HashMap _$_findViewCache;
    private boolean isOnTouch;
    private int mChoose;
    private OnTouchSessionChangedListener mOnTouchSessionChangedListener;
    private Paint mPaint;
    private String[] mSessions;
    private final int sessionHeight;

    /* JADX INFO: compiled from: CitySideBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/customview/CitySideBar$OnTouchSessionChangedListener;", "", "onTouchSessionChanged", "", "session", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnTouchSessionChangedListener {
        void onTouchSessionChanged(String session);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public CitySideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.sessionHeight = DipPixelUtil.dip2px(10.0f);
        this.mSessions = new String[]{"#"};
        this.mChoose = -1;
        this.mPaint = new Paint();
    }

    public CitySideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.sessionHeight = DipPixelUtil.dip2px(10.0f);
        this.mSessions = new String[]{"#"};
        this.mChoose = -1;
        this.mPaint = new Paint();
    }

    public CitySideBar(Context context) {
        super(context);
        this.sessionHeight = DipPixelUtil.dip2px(10.0f);
        this.mSessions = new String[]{"#"};
        this.mChoose = -1;
        this.mPaint = new Paint();
    }

    public final String[] getMSessions() {
        return this.mSessions;
    }

    public final void setMSessions(String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "<set-?>");
        this.mSessions = strArr;
    }

    protected final int getMChoose() {
        return this.mChoose;
    }

    protected final void setMChoose(int i) {
        this.mChoose = i;
    }

    protected final Paint getMPaint() {
        return this.mPaint;
    }

    protected final void setMPaint(Paint paint) {
        Intrinsics.checkParameterIsNotNull(paint, "<set-?>");
        this.mPaint = paint;
    }

    /* JADX INFO: renamed from: isOnTouch, reason: from getter */
    public final boolean getIsOnTouch() {
        return this.isOnTouch;
    }

    public final void setOnTouch(boolean z) {
        this.isOnTouch = z;
    }

    public final void setSideBarSessions(String[] sessions) {
        if (sessions == null) {
            return;
        }
        this.mSessions = sessions;
        postInvalidate();
    }

    public final void setOnTouchingSessionChangedListener(OnTouchSessionChangedListener onTouchSessionChangedListener) {
        this.mOnTouchSessionChangedListener = onTouchSessionChangedListener;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        String[] strArr = this.mSessions;
        int length = (height - (strArr.length * this.sessionHeight)) / (strArr.length + 1);
        int length2 = strArr.length;
        for (int i = 0; i < length2; i++) {
            setPaintColor(false);
            this.mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setTextSize(ResourceUtil.getDimens(R.dimen.com_text_small));
            if (i == this.mChoose) {
                setPaintColor(true);
                this.mPaint.setFakeBoldText(true);
            }
            canvas.drawText(this.mSessions[i], (width / 2) - (this.mPaint.measureText(this.mSessions[i]) / 2), (length * i) + (this.sessionHeight * i) + length, this.mPaint);
            this.mPaint.reset();
        }
    }

    private final void setPaintColor(boolean isChoose) {
        if (isChoose) {
            this.mPaint.setTextSize(ResourceUtil.getDimens(R.dimen.com_text_mind));
        } else {
            this.mPaint.setColor(ResourceUtil.getColor(R.color.color_text_side_bar_55));
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int action = event.getAction();
        float y = event.getY();
        int i = this.mChoose;
        int height = getHeight();
        OnTouchSessionChangedListener onTouchSessionChangedListener = this.mOnTouchSessionChangedListener;
        String[] strArr = this.mSessions;
        int length = (int) ((strArr.length * y) / height);
        if (action == 1 || action == 3) {
            this.isOnTouch = false;
        } else if (i != length && length >= 0 && length < strArr.length) {
            this.isOnTouch = true;
            if (onTouchSessionChangedListener != null) {
                onTouchSessionChangedListener.onTouchSessionChanged(strArr[length]);
            }
            this.mChoose = length;
            invalidate();
        }
        return true;
    }

    public final void setSelectedSession(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        int length = this.mSessions.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual(this.mSessions[i], s)) {
                this.mChoose = i;
                invalidate();
                return;
            }
        }
    }
}