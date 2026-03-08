package com.ido.life.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoadingLayout.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\r\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0002¢\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010s\u001a\u0004\u0018\u00010F2\u0006\u0010t\u001a\u00020\u0007H\u0002J\u000e\u0010u\u001a\u00020\u00072\u0006\u0010v\u001a\u00020wJ\b\u0010x\u001a\u00020yH\u0002J \u0010z\u001a\u00020_2\u0006\u0010t\u001a\u00020\u00072\u0006\u0010{\u001a\u00020\u00072\u0006\u0010|\u001a\u00020\u0007H\u0002J\b\u0010}\u001a\u00020_H\u0002J\u0012\u0010~\u001a\u0004\u0018\u00010F2\u0006\u0010t\u001a\u00020\u0007H\u0002J\b\u0010\u007f\u001a\u00020_H\u0014J\u0013\u0010\u0080\u0001\u001a\u0004\u0018\u00010F2\u0006\u0010t\u001a\u00020\u0007H\u0002J\u0011\u0010\u0081\u0001\u001a\u00020_2\u0006\u0010t\u001a\u00020\u0007H\u0002J\u0012\u0010\u0082\u0001\u001a\u00020_2\u0007\u0010\u0083\u0001\u001a\u00020FH\u0002J\u0010\u0010\u0084\u0001\u001a\u00020_2\u0007\u0010\u0085\u0001\u001a\u00020\u0007J\u0012\u0010\u0086\u0001\u001a\u00020_2\u0007\u0010\u0087\u0001\u001a\u00020FH\u0002J\u0012\u0010\u0088\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0089\u0001\u001a\u00020\u0007J\u0011\u0010\u008a\u0001\u001a\u00020\u00002\b\b\u0001\u0010|\u001a\u00020\u0007J\u0011\u0010\u008b\u0001\u001a\u00020\u00002\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001J\u0011\u0010\u008e\u0001\u001a\u00020\u00002\b\b\u0001\u0010|\u001a\u00020\u0007J\u0011\u0010\u008f\u0001\u001a\u00020\u00002\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001J\u0012\u0010\u0090\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0089\u0001\u001a\u00020\u0007J\u0010\u0010\u0091\u0001\u001a\u00020\u00002\u0007\u0010\u0092\u0001\u001a\u00020OJ\u0010\u0010\u0093\u0001\u001a\u00020\u00002\u0007\u0010\u0092\u0001\u001a\u00020OJ\u0016\u0010\u0094\u0001\u001a\u00020\u00002\r\u0010\u0092\u0001\u001a\b\u0012\u0004\u0012\u00020_0^J\u0011\u0010\u0095\u0001\u001a\u00020\u00002\b\u0010\u0096\u0001\u001a\u00030\u008d\u0001J\u0011\u0010\u0097\u0001\u001a\u00020_2\u0006\u0010t\u001a\u00020\u0007H\u0002J\u0007\u0010\u0098\u0001\u001a\u00020_J\u0007\u0010\u0099\u0001\u001a\u00020_J\u001e\u0010\u009a\u0001\u001a\u00020_2\n\b\u0002\u0010\u009b\u0001\u001a\u00030\u008d\u00012\t\b\u0002\u0010\u009c\u0001\u001a\u00020\u0007J)\u0010\u009d\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u009b\u0001\u001a\u00030\u008d\u00012\t\b\u0002\u0010\u009e\u0001\u001a\u00020n2\t\b\u0002\u0010\u009c\u0001\u001a\u00020\u0007J\u0007\u0010\u009f\u0001\u001a\u00020_J$\u0010\u0096\u0001\u001a\u00020_2\u0006\u0010t\u001a\u00020\u00072\u0006\u0010{\u001a\u00020\u00072\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010*H\u0002J\u0012\u0010 \u0001\u001a\u00020\u00072\u0007\u0010¡\u0001\u001a\u00020\u0007H\u0002R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u001a\u0010\u0017\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\rR\u001a\u0010\u001a\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR\u001a\u0010\u001d\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR\u001a\u0010 \u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000b\"\u0004\b\"\u0010\rR\u001a\u0010#\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000b\"\u0004\b%\u0010\rR\u001a\u0010&\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u000b\"\u0004\b(\u0010\rR\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u000b\"\u0004\b1\u0010\rR\u001a\u00102\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u000b\"\u0004\b4\u0010\rR\u001a\u00105\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u000b\"\u0004\b7\u0010\rR\u001c\u00108\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010,\"\u0004\b:\u0010.R\u001a\u0010;\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u000b\"\u0004\b=\u0010\rR\u001a\u0010>\u001a\u00020?X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR&\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020F0EX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u000b\"\u0004\bM\u0010\rR\u001c\u0010N\u001a\u0004\u0018\u00010OX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001c\u0010T\u001a\u0004\u0018\u00010OX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010Q\"\u0004\bV\u0010SR\u001a\u0010W\u001a\u00020XX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\"\u0010]\u001a\n\u0012\u0004\u0012\u00020_\u0018\u00010^X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u001c\u0010d\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010,\"\u0004\bf\u0010.R\u001a\u0010g\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\u000b\"\u0004\bi\u0010\rR\u001a\u0010j\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\u000b\"\u0004\bl\u0010\rR\u001a\u0010m\u001a\u00020nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010p\"\u0004\bq\u0010r¨\u0006£\u0001"}, d2 = {"Lcom/ido/life/customview/LoadingLayout;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defaultLayout", "getDefaultLayout", "()I", "setDefaultLayout", "(I)V", "mButtonBackground", "Landroid/graphics/drawable/Drawable;", "getMButtonBackground", "()Landroid/graphics/drawable/Drawable;", "setMButtonBackground", "(Landroid/graphics/drawable/Drawable;)V", "mButtonTextColor", "getMButtonTextColor", "setMButtonTextColor", "mButtonTextSize", "getMButtonTextSize", "setMButtonTextSize", "mContentId", "getMContentId", "setMContentId", "mContentIndex", "getMContentIndex", "setMContentIndex", "mEmptyImage", "getMEmptyImage", "setMEmptyImage", "mEmptyImageVisibility", "getMEmptyImageVisibility", "setMEmptyImageVisibility", "mEmptyResId", "getMEmptyResId", "setMEmptyResId", "mEmptyText", "", "getMEmptyText", "()Ljava/lang/CharSequence;", "setMEmptyText", "(Ljava/lang/CharSequence;)V", "mErrorImage", "getMErrorImage", "setMErrorImage", "mErrorImageVisibility", "getMErrorImageVisibility", "setMErrorImageVisibility", "mErrorResId", "getMErrorResId", "setMErrorResId", "mErrorText", "getMErrorText", "setMErrorText", "mHeaderResId", "getMHeaderResId", "setMHeaderResId", "mInflater", "Landroid/view/LayoutInflater;", "getMInflater", "()Landroid/view/LayoutInflater;", "setMInflater", "(Landroid/view/LayoutInflater;)V", "mLayouts", "", "Landroid/view/View;", "getMLayouts", "()Ljava/util/Map;", "setMLayouts", "(Ljava/util/Map;)V", "mLoadingResId", "getMLoadingResId", "setMLoadingResId", "mOnEmptyInflateListener", "Lcom/ido/life/customview/LoadingLayout$OnInflateListener;", "getMOnEmptyInflateListener", "()Lcom/ido/life/customview/LoadingLayout$OnInflateListener;", "setMOnEmptyInflateListener", "(Lcom/ido/life/customview/LoadingLayout$OnInflateListener;)V", "mOnErrorInflateListener", "getMOnErrorInflateListener", "setMOnErrorInflateListener", "mRetryButtonClickListener", "Landroid/view/View$OnClickListener;", "getMRetryButtonClickListener", "()Landroid/view/View$OnClickListener;", "setMRetryButtonClickListener", "(Landroid/view/View$OnClickListener;)V", "mRetryListener", "Lkotlin/Function0;", "", "getMRetryListener", "()Lkotlin/jvm/functions/Function0;", "setMRetryListener", "(Lkotlin/jvm/functions/Function0;)V", "mRetryText", "getMRetryText", "setMRetryText", "mTextColor", "getMTextColor", "setMTextColor", "mTextSize", "getMTextSize", "setMTextSize", "showHeader", "", "getShowHeader", "()Z", "setShowHeader", "(Z)V", "addChild", "layoutId", "dp2px", "dp", "", "getContentLayout", "Landroid/widget/FrameLayout;", "image", "ctrlId", "resId", "initLayout", "layout", "onFinishInflate", "onlyLayout", "remove", "removeChild", "view", "retryBtnVisible", "visibility", "setContentView", "contentView", "setEmpty", "id", "setEmptyImage", "setEmptyText", "value", "", "setErrorImage", "setErrorText", "setLoading", "setOnEmptyInflateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnErrorInflateListener", "setRetryListener", "setRetryText", "text", "show", "showContent", "showCustomEmpty", "showEmpty", "msg", "imageVisible", "showError", "canRetry", "showLoading", "transVisibility", "myVisibility", "OnInflateListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class LoadingLayout extends LinearLayout {
    private HashMap _$_findViewCache;
    private int defaultLayout;
    private Drawable mButtonBackground;
    private int mButtonTextColor;
    private int mButtonTextSize;
    private int mContentId;
    private int mContentIndex;
    private int mEmptyImage;
    private int mEmptyImageVisibility;
    private int mEmptyResId;
    private CharSequence mEmptyText;
    private int mErrorImage;
    private int mErrorImageVisibility;
    private int mErrorResId;
    private CharSequence mErrorText;
    private int mHeaderResId;
    private LayoutInflater mInflater;
    private Map<Integer, View> mLayouts;
    private int mLoadingResId;
    private OnInflateListener mOnEmptyInflateListener;
    private OnInflateListener mOnErrorInflateListener;
    private View.OnClickListener mRetryButtonClickListener;
    private Function0<Unit> mRetryListener;
    private CharSequence mRetryText;
    private int mTextColor;
    private int mTextSize;
    private boolean showHeader;

    /* JADX INFO: compiled from: LoadingLayout.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/customview/LoadingLayout$OnInflateListener;", "", "onInflate", "", "inflated", "Landroid/view/View;", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnInflateListener {
        void onInflate(View inflated);
    }

    public LoadingLayout(Context context) {
        this(context, null, 0, 6, null);
    }

    public LoadingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    private final int transVisibility(int myVisibility) {
        if (myVisibility != 1) {
            return myVisibility != 2 ? 0 : 8;
        }
        return 4;
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

    public /* synthetic */ LoadingLayout(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? R.attr.styleLoadingLayout : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.showHeader = true;
        this.mRetryButtonClickListener = new View.OnClickListener() { // from class: com.ido.life.customview.LoadingLayout$mRetryButtonClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> mRetryListener = this.this$0.getMRetryListener();
                if (mRetryListener != null) {
                    mRetryListener.invoke();
                }
            }
        };
        this.mRetryListener = new Function0<Unit>() { // from class: com.ido.life.customview.LoadingLayout$mRetryListener$1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        };
        this.mHeaderResId = -1;
        this.mEmptyResId = -1;
        this.mLoadingResId = -1;
        this.mErrorResId = -1;
        this.mContentId = -1;
        this.mLayouts = new HashMap();
        this.mContentIndex = 1;
        setOrientation(1);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(context)");
        this.mInflater = layoutInflaterFrom;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.LoadingLayout, i, 2131886332);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…ingLayout_Style\n        )");
        this.showHeader = typedArrayObtainStyledAttributes.getBoolean(17, true);
        this.mEmptyImage = typedArrayObtainStyledAttributes.getResourceId(6, -1);
        this.mEmptyText = typedArrayObtainStyledAttributes.getString(8);
        this.mErrorImage = typedArrayObtainStyledAttributes.getResourceId(9, -1);
        this.mErrorText = typedArrayObtainStyledAttributes.getString(11);
        this.mRetryText = typedArrayObtainStyledAttributes.getString(14);
        this.mTextColor = typedArrayObtainStyledAttributes.getColor(15, -6710887);
        this.mTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, dp2px(16.0f));
        this.mButtonTextColor = typedArrayObtainStyledAttributes.getColor(4, -6710887);
        this.mButtonTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, dp2px(16.0f));
        this.mButtonBackground = typedArrayObtainStyledAttributes.getDrawable(3);
        this.mHeaderResId = typedArrayObtainStyledAttributes.getResourceId(12, R.layout.layout_common_title);
        this.mEmptyResId = typedArrayObtainStyledAttributes.getResourceId(7, R.layout._loading_layout_empty);
        this.mLoadingResId = typedArrayObtainStyledAttributes.getResourceId(13, R.layout._loading_layout_loading);
        this.mErrorResId = typedArrayObtainStyledAttributes.getResourceId(10, R.layout._loading_layout_error);
        this.defaultLayout = typedArrayObtainStyledAttributes.getInt(0, 2);
        this.mEmptyImageVisibility = typedArrayObtainStyledAttributes.getInt(1, 0);
        this.mErrorImageVisibility = typedArrayObtainStyledAttributes.getInt(2, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final int getDefaultLayout() {
        return this.defaultLayout;
    }

    public final void setDefaultLayout(int i) {
        this.defaultLayout = i;
    }

    public final boolean getShowHeader() {
        return this.showHeader;
    }

    public final void setShowHeader(boolean z) {
        this.showHeader = z;
    }

    public final int getMEmptyImageVisibility() {
        return this.mEmptyImageVisibility;
    }

    public final void setMEmptyImageVisibility(int i) {
        this.mEmptyImageVisibility = i;
    }

    public final int getMEmptyImage() {
        return this.mEmptyImage;
    }

    public final void setMEmptyImage(int i) {
        this.mEmptyImage = i;
    }

    public final CharSequence getMEmptyText() {
        return this.mEmptyText;
    }

    public final void setMEmptyText(CharSequence charSequence) {
        this.mEmptyText = charSequence;
    }

    public final int getMErrorImageVisibility() {
        return this.mErrorImageVisibility;
    }

    public final void setMErrorImageVisibility(int i) {
        this.mErrorImageVisibility = i;
    }

    public final int getMErrorImage() {
        return this.mErrorImage;
    }

    public final void setMErrorImage(int i) {
        this.mErrorImage = i;
    }

    public final CharSequence getMErrorText() {
        return this.mErrorText;
    }

    public final void setMErrorText(CharSequence charSequence) {
        this.mErrorText = charSequence;
    }

    public final CharSequence getMRetryText() {
        return this.mRetryText;
    }

    public final void setMRetryText(CharSequence charSequence) {
        this.mRetryText = charSequence;
    }

    public final View.OnClickListener getMRetryButtonClickListener() {
        return this.mRetryButtonClickListener;
    }

    public final void setMRetryButtonClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(onClickListener, "<set-?>");
        this.mRetryButtonClickListener = onClickListener;
    }

    public final Function0<Unit> getMRetryListener() {
        return this.mRetryListener;
    }

    public final void setMRetryListener(Function0<Unit> function0) {
        this.mRetryListener = function0;
    }

    public final OnInflateListener getMOnEmptyInflateListener() {
        return this.mOnEmptyInflateListener;
    }

    public final void setMOnEmptyInflateListener(OnInflateListener onInflateListener) {
        this.mOnEmptyInflateListener = onInflateListener;
    }

    public final OnInflateListener getMOnErrorInflateListener() {
        return this.mOnErrorInflateListener;
    }

    public final void setMOnErrorInflateListener(OnInflateListener onInflateListener) {
        this.mOnErrorInflateListener = onInflateListener;
    }

    public final int getMTextColor() {
        return this.mTextColor;
    }

    public final void setMTextColor(int i) {
        this.mTextColor = i;
    }

    public final int getMTextSize() {
        return this.mTextSize;
    }

    public final void setMTextSize(int i) {
        this.mTextSize = i;
    }

    public final int getMButtonTextColor() {
        return this.mButtonTextColor;
    }

    public final void setMButtonTextColor(int i) {
        this.mButtonTextColor = i;
    }

    public final int getMButtonTextSize() {
        return this.mButtonTextSize;
    }

    public final void setMButtonTextSize(int i) {
        this.mButtonTextSize = i;
    }

    public final Drawable getMButtonBackground() {
        return this.mButtonBackground;
    }

    public final void setMButtonBackground(Drawable drawable) {
        this.mButtonBackground = drawable;
    }

    public final int getMHeaderResId() {
        return this.mHeaderResId;
    }

    public final void setMHeaderResId(int i) {
        this.mHeaderResId = i;
    }

    public final int getMEmptyResId() {
        return this.mEmptyResId;
    }

    public final void setMEmptyResId(int i) {
        this.mEmptyResId = i;
    }

    public final int getMLoadingResId() {
        return this.mLoadingResId;
    }

    public final void setMLoadingResId(int i) {
        this.mLoadingResId = i;
    }

    public final int getMErrorResId() {
        return this.mErrorResId;
    }

    public final void setMErrorResId(int i) {
        this.mErrorResId = i;
    }

    public final int getMContentId() {
        return this.mContentId;
    }

    public final void setMContentId(int i) {
        this.mContentId = i;
    }

    public final Map<Integer, View> getMLayouts() {
        return this.mLayouts;
    }

    public final void setMLayouts(Map<Integer, View> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.mLayouts = map;
    }

    public final LayoutInflater getMInflater() {
        return this.mInflater;
    }

    public final void setMInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "<set-?>");
        this.mInflater = layoutInflater;
    }

    public final int getMContentIndex() {
        return this.mContentIndex;
    }

    public final void setMContentIndex(int i) {
        this.mContentIndex = i;
    }

    public final int dp2px(float dp) {
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) (resources.getDisplayMetrics().density * dp);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 0) {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setId(R.id.loading_layout_default_content);
            addView(frameLayout);
        }
        try {
            if (getChildCount() > 1) {
                removeViews(1, getChildCount() - 1);
            }
            View view = getChildAt(0);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            setContentView(view);
            initLayout();
            int i = this.defaultLayout;
            if (i == 1) {
                showLoading();
                return;
            }
            if (i == 2) {
                showContent();
            } else if (i == 3) {
                showError$default(this, null, false, transVisibility(this.mErrorImageVisibility), 3, null);
            } else {
                if (i != 4) {
                    return;
                }
                showEmpty$default(this, null, transVisibility(this.mEmptyImageVisibility), 1, null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private final void initLayout() {
        int i;
        int i2 = 0;
        if (this.showHeader && (i = this.mHeaderResId) > 0) {
            addView(this.mInflater.inflate(i, (ViewGroup) this, false), 0);
            i2 = 1;
        }
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.mContentIndex = i2;
        addView(frameLayout, i2);
        View view = this.mLayouts.get(Integer.valueOf(this.mContentId));
        if (view != null) {
            removeView(view);
            frameLayout.addView(view);
        }
    }

    private final void setContentView(View contentView) {
        this.mContentId = contentView.getId();
        this.mLayouts.put(Integer.valueOf(this.mContentId), contentView);
    }

    public final LoadingLayout setLoading(int id) {
        int i = this.mLoadingResId;
        if (i != id) {
            remove(i);
            this.mLoadingResId = id;
        }
        return this;
    }

    public final LoadingLayout setEmpty(int id) {
        int i = this.mEmptyResId;
        if (i != id) {
            remove(i);
            this.mEmptyResId = id;
        }
        return this;
    }

    public final LoadingLayout setOnEmptyInflateListener(OnInflateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mOnEmptyInflateListener = listener;
        if (this.mOnEmptyInflateListener != null && this.mLayouts.containsKey(Integer.valueOf(this.mEmptyResId))) {
            listener.onInflate(this.mLayouts.get(Integer.valueOf(this.mEmptyResId)));
        }
        return this;
    }

    public final LoadingLayout setOnErrorInflateListener(OnInflateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mOnErrorInflateListener = listener;
        if (this.mOnErrorInflateListener != null && this.mLayouts.containsKey(Integer.valueOf(this.mErrorResId))) {
            listener.onInflate(this.mLayouts.get(Integer.valueOf(this.mErrorResId)));
        }
        return this;
    }

    public final LoadingLayout setEmptyImage(int resId) {
        this.mEmptyImage = resId;
        image(this.mEmptyResId, R.id.empty_image, this.mEmptyImage);
        return this;
    }

    public final LoadingLayout setEmptyText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.mEmptyText = value;
        text(this.mEmptyResId, R.id.empty_text, this.mEmptyText);
        return this;
    }

    public final LoadingLayout setErrorImage(int resId) {
        this.mErrorImage = resId;
        image(this.mErrorResId, R.id.error_image, this.mErrorImage);
        return this;
    }

    public final LoadingLayout setErrorText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.mErrorText = value;
        text(this.mErrorResId, R.id.error_text, this.mErrorText);
        return this;
    }

    public final LoadingLayout setRetryText(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.mRetryText = text;
        text(this.mErrorResId, R.id.retry_button, this.mRetryText);
        return this;
    }

    public final LoadingLayout setRetryListener(Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mRetryListener = listener;
        return this;
    }

    public final void showLoading() {
        show(this.mLoadingResId);
    }

    public final void showCustomEmpty() {
        Iterator<View> it = this.mLayouts.values().iterator();
        while (it.hasNext()) {
            it.next().setVisibility(8);
        }
        View viewOnlyLayout = onlyLayout(this.mEmptyResId);
        if (viewOnlyLayout != null) {
            viewOnlyLayout.setVisibility(0);
        }
    }

    public static /* synthetic */ void showEmpty$default(LoadingLayout loadingLayout, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        loadingLayout.showEmpty(str, i);
    }

    public final void showEmpty(String msg, int imageVisible) {
        ImageView imageView;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        View viewLayout = layout(this.mEmptyResId);
        if (viewLayout != null && (imageView = (ImageView) viewLayout.findViewById(R.id.empty_image)) != null) {
            imageView.setVisibility(imageVisible);
        }
        String str = msg;
        if (!TextUtils.isEmpty(str)) {
            this.mEmptyText = str;
        }
        text(this.mEmptyResId, R.id.empty_text, this.mEmptyText);
        show(this.mEmptyResId);
    }

    public static /* synthetic */ LoadingLayout showError$default(LoadingLayout loadingLayout, String str, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        if ((i2 & 2) != 0) {
            z = true;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return loadingLayout.showError(str, z, i);
    }

    public final LoadingLayout showError(String msg, boolean canRetry, int imageVisible) {
        ImageView imageView;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        View viewLayout = layout(this.mErrorResId);
        if (viewLayout != null && (imageView = (ImageView) viewLayout.findViewById(R.id.error_image)) != null) {
            imageView.setVisibility(imageVisible);
        }
        String str = msg;
        if (!TextUtils.isEmpty(str)) {
            this.mErrorText = str;
        }
        int i = this.mErrorResId;
        CharSequence charSequence = this.mErrorText;
        if (charSequence == null) {
        }
        text(i, R.id.error_text, charSequence);
        if (!canRetry) {
            retryBtnVisible(8);
        } else {
            retryBtnVisible(0);
        }
        show(this.mErrorResId);
        return this;
    }

    public final void showContent() {
        show(this.mContentId);
    }

    public final void retryBtnVisible(int visibility) {
        View viewFindViewById;
        View viewLayout = layout(this.mErrorResId);
        if (viewLayout == null || (viewFindViewById = viewLayout.findViewById(R.id.retry_button)) == null) {
            return;
        }
        viewFindViewById.setVisibility(visibility);
    }

    private final void show(int layoutId) {
        Iterator<View> it = this.mLayouts.values().iterator();
        while (it.hasNext()) {
            it.next().setVisibility(8);
        }
        View viewLayout = layout(layoutId);
        if (viewLayout != null) {
            viewLayout.setVisibility(0);
        }
    }

    private final void remove(int layoutId) {
        View viewRemove;
        if (!this.mLayouts.containsKey(Integer.valueOf(layoutId)) || (viewRemove = this.mLayouts.remove(Integer.valueOf(layoutId))) == null) {
            return;
        }
        removeChild(viewRemove);
    }

    private final FrameLayout getContentLayout() {
        View childAt = getChildAt(this.mContentIndex);
        if (childAt != null) {
            return (FrameLayout) childAt;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout");
    }

    private final View onlyLayout(int layoutId) {
        if (this.mLayouts.containsKey(Integer.valueOf(layoutId))) {
            return this.mLayouts.get(Integer.valueOf(layoutId));
        }
        View viewAddChild = addChild(layoutId);
        if (viewAddChild != null) {
            this.mLayouts.put(Integer.valueOf(layoutId), viewAddChild);
        }
        return viewAddChild;
    }

    private final View layout(int layoutId) {
        if (this.mLayouts.containsKey(Integer.valueOf(layoutId))) {
            return this.mLayouts.get(Integer.valueOf(layoutId));
        }
        View viewAddChild = addChild(layoutId);
        if (viewAddChild == null) {
            return null;
        }
        this.mLayouts.put(Integer.valueOf(layoutId), viewAddChild);
        if (layoutId == this.mEmptyResId) {
            View viewFindViewById = viewAddChild.findViewById(R.id.empty_image);
            if (viewFindViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            ImageView imageView = (ImageView) viewFindViewById;
            if (imageView != null) {
                imageView.setImageResource(this.mEmptyImage);
            }
            View viewFindViewById2 = viewAddChild.findViewById(R.id.empty_text);
            if (viewFindViewById2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            TextView textView = (TextView) viewFindViewById2;
            if (textView != null) {
                textView.setText(this.mEmptyText);
                textView.setTextColor(this.mTextColor);
                textView.setTextSize(0, this.mTextSize);
            }
            OnInflateListener onInflateListener = this.mOnEmptyInflateListener;
            if (onInflateListener != null) {
                if (onInflateListener == null) {
                    Intrinsics.throwNpe();
                }
                onInflateListener.onInflate(viewAddChild);
            }
        } else if (layoutId == this.mErrorResId) {
            View viewFindViewById3 = viewAddChild.findViewById(R.id.error_image);
            if (viewFindViewById3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            ImageView imageView2 = (ImageView) viewFindViewById3;
            if (imageView2 != null) {
                imageView2.setImageResource(this.mErrorImage);
            }
            View viewFindViewById4 = viewAddChild.findViewById(R.id.error_text);
            if (viewFindViewById4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            TextView textView2 = (TextView) viewFindViewById4;
            if (textView2 != null) {
                textView2.setText(this.mErrorText);
                textView2.setTextColor(this.mTextColor);
                textView2.setTextSize(0, this.mTextSize);
            }
            View viewFindViewById5 = viewAddChild.findViewById(R.id.retry_button);
            if (viewFindViewById5 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            TextView textView3 = (TextView) viewFindViewById5;
            if (textView3 != null) {
                textView3.setText(this.mRetryText);
                textView3.setTextColor(this.mButtonTextColor);
                textView3.setTextSize(0, this.mButtonTextSize);
                textView3.setBackground(this.mButtonBackground);
                textView3.setOnClickListener(this.mRetryButtonClickListener);
            }
            OnInflateListener onInflateListener2 = this.mOnErrorInflateListener;
            if (onInflateListener2 != null) {
                if (onInflateListener2 == null) {
                    Intrinsics.throwNpe();
                }
                onInflateListener2.onInflate(viewAddChild);
            }
        }
        return viewAddChild;
    }

    private final View addChild(int layoutId) {
        View viewInflate = this.mInflater.inflate(layoutId, (ViewGroup) this, false);
        if (viewInflate != null) {
            viewInflate.setVisibility(8);
        }
        getContentLayout().addView(viewInflate);
        return viewInflate;
    }

    private final void removeChild(View view) {
        getContentLayout().removeView(view);
    }

    private final void text(int layoutId, int ctrlId, CharSequence value) {
        if (this.mLayouts.containsKey(Integer.valueOf(layoutId))) {
            View view = this.mLayouts.get(Integer.valueOf(layoutId));
            if (view == null) {
                Intrinsics.throwNpe();
            }
            View viewFindViewById = view.findViewById(ctrlId);
            if (viewFindViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            TextView textView = (TextView) viewFindViewById;
            if (textView != null) {
                textView.setText(value);
            }
        }
    }

    private final void image(int layoutId, int ctrlId, int resId) {
        if (this.mLayouts.containsKey(Integer.valueOf(layoutId))) {
            View view = this.mLayouts.get(Integer.valueOf(layoutId));
            if (view == null) {
                Intrinsics.throwNpe();
            }
            View viewFindViewById = view.findViewById(ctrlId);
            if (viewFindViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            ImageView imageView = (ImageView) viewFindViewById;
            if (imageView != null) {
                imageView.setImageResource(resId);
            }
        }
    }
}