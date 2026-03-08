package com.ido.smartrefresh.layoutkernel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.content.ContextCompat;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import com.ido.common.log.CommonLogUtil;
import com.ido.smartrefresh.R;
import com.ido.smartrefresh.layoutkernel.api.RefreshComponent;
import com.ido.smartrefresh.layoutkernel.api.RefreshContent;
import com.ido.smartrefresh.layoutkernel.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshKernel;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.DimensionStatus;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshFooterCreator;
import com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshHeaderCreator;
import com.ido.smartrefresh.layoutkernel.listener.DefaultRefreshInitializer;
import com.ido.smartrefresh.layoutkernel.listener.OnLoadMoreListener;
import com.ido.smartrefresh.layoutkernel.listener.OnMultiListener;
import com.ido.smartrefresh.layoutkernel.listener.OnRefreshListener;
import com.ido.smartrefresh.layoutkernel.listener.OnRefreshLoadMoreListener;
import com.ido.smartrefresh.layoutkernel.listener.ScrollBoundaryDecider;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;
import com.ido.smartrefresh.layoutkernel.wrapper.RefreshContentWrapper;

/* JADX INFO: loaded from: classes3.dex */
public class SmartRefreshLayout extends ViewGroup implements RefreshLayout, NestedScrollingParent {
    protected Runnable animationRunnable;
    protected boolean mAttachedToWindow;
    protected int mCurrentVelocity;
    protected boolean mDisableContentWhenLoading;
    protected boolean mDisableContentWhenRefresh;
    protected char mDragDirection;
    protected float mDragRate;
    protected boolean mEnableAutoLoadMore;
    protected boolean mEnableClipFooterWhenFixedBehind;
    protected boolean mEnableClipHeaderWhenFixedBehind;
    protected boolean mEnableDisallowIntercept;
    protected boolean mEnableFooterFollowWhenNoMoreData;
    protected boolean mEnableFooterTranslationContent;
    protected boolean mEnableHeaderTranslationContent;
    protected boolean mEnableLoadMore;
    protected boolean mEnableLoadMoreWhenContentNotFull;
    protected boolean mEnableNestedScrolling;
    protected boolean mEnableOverScrollBounce;
    protected boolean mEnableOverScrollDrag;
    protected boolean mEnablePreviewInEditMode;
    protected boolean mEnablePureScrollMode;
    protected boolean mEnableRefresh;
    protected boolean mEnableScrollContentWhenLoaded;
    protected boolean mEnableScrollContentWhenRefreshed;
    protected MotionEvent mFalsifyEvent;
    protected int mFixedFooterViewId;
    protected int mFixedHeaderViewId;
    protected int mFloorDuration;
    protected int mFooterBackgroundColor;
    protected int mFooterHeight;
    protected DimensionStatus mFooterHeightStatus;
    protected int mFooterInsetStart;
    protected boolean mFooterLocked;
    protected float mFooterMaxDragRate;
    protected boolean mFooterNeedTouchEventWhenLoading;
    protected boolean mFooterNoMoreData;
    protected boolean mFooterNoMoreDataEffective;
    protected int mFooterTranslationViewId;
    protected float mFooterTriggerRate;
    protected Handler mHandler;
    protected int mHeaderBackgroundColor;
    protected int mHeaderHeight;
    protected DimensionStatus mHeaderHeightStatus;
    protected int mHeaderInsetStart;
    protected float mHeaderMaxDragRate;
    protected boolean mHeaderNeedTouchEventWhenRefreshing;
    protected int mHeaderTranslationViewId;
    protected float mHeaderTriggerRate;
    protected boolean mIsBeingDragged;
    protected RefreshKernel mKernel;
    protected long mLastOpenTime;
    protected int mLastSpinner;
    protected float mLastTouchX;
    protected float mLastTouchY;
    protected OnLoadMoreListener mLoadMoreListener;
    protected boolean mManualFooterTranslationContent;
    protected boolean mManualHeaderTranslationContent;
    protected boolean mManualLoadMore;
    protected int mMaximumVelocity;
    protected int mMinimumVelocity;
    protected NestedScrollingChildHelper mNestedChild;
    protected boolean mNestedInProgress;
    protected NestedScrollingParentHelper mNestedParent;
    protected OnMultiListener mOnMultiListener;
    protected Paint mPaint;
    protected int[] mParentOffsetInWindow;
    protected int[] mPrimaryColors;
    protected int mReboundDuration;
    protected Interpolator mReboundInterpolator;
    protected RefreshContent mRefreshContent;
    protected RefreshComponent mRefreshFooter;
    protected RefreshComponent mRefreshHeader;
    protected OnRefreshListener mRefreshListener;
    protected int mScreenHeightPixels;
    protected ScrollBoundaryDecider mScrollBoundaryDecider;
    protected Scroller mScroller;
    protected int mSpinner;
    protected RefreshState mState;
    protected boolean mSuperDispatchTouchEvent;
    protected int mTotalUnconsumed;
    protected int mTouchSlop;
    protected int mTouchSpinner;
    protected float mTouchX;
    protected float mTouchY;
    protected float mTwoLevelBottomPullUpToCloseRate;
    protected VelocityTracker mVelocityTracker;
    protected boolean mVerticalPermit;
    protected RefreshState mViceState;
    protected ValueAnimator reboundAnimator;
    protected static final String TAG = SmartRefreshLayout.class.getSimpleName();
    protected static DefaultRefreshFooterCreator sFooterCreator = null;
    protected static DefaultRefreshHeaderCreator sHeaderCreator = null;
    protected static DefaultRefreshInitializer sRefreshInitializer = null;
    protected static ViewGroup.MarginLayoutParams sDefaultMarginLP = new ViewGroup.MarginLayoutParams(-1, -1);

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public ViewGroup getLayout() {
        return this;
    }

    public SmartRefreshLayout(Context context) {
        this(context, null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFloorDuration = 300;
        this.mReboundDuration = 300;
        this.mDragRate = 0.5f;
        this.mDragDirection = 'n';
        this.mFixedHeaderViewId = -1;
        this.mFixedFooterViewId = -1;
        this.mHeaderTranslationViewId = -1;
        this.mFooterTranslationViewId = -1;
        this.mEnableRefresh = true;
        this.mEnableLoadMore = false;
        this.mEnableClipHeaderWhenFixedBehind = true;
        this.mEnableClipFooterWhenFixedBehind = true;
        this.mEnableHeaderTranslationContent = true;
        this.mEnableFooterTranslationContent = true;
        this.mEnableFooterFollowWhenNoMoreData = false;
        this.mEnablePreviewInEditMode = true;
        this.mEnableOverScrollBounce = true;
        this.mEnableOverScrollDrag = false;
        this.mEnableAutoLoadMore = true;
        this.mEnablePureScrollMode = false;
        this.mEnableScrollContentWhenLoaded = true;
        this.mEnableScrollContentWhenRefreshed = true;
        this.mEnableLoadMoreWhenContentNotFull = true;
        this.mEnableNestedScrolling = true;
        this.mDisableContentWhenRefresh = false;
        this.mDisableContentWhenLoading = false;
        this.mFooterNoMoreData = false;
        this.mFooterNoMoreDataEffective = false;
        this.mManualLoadMore = false;
        this.mManualHeaderTranslationContent = false;
        this.mManualFooterTranslationContent = false;
        this.mParentOffsetInWindow = new int[2];
        this.mNestedChild = new NestedScrollingChildHelper(this);
        this.mNestedParent = new NestedScrollingParentHelper(this);
        this.mHeaderHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mFooterHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mHeaderMaxDragRate = 2.5f;
        this.mFooterMaxDragRate = 2.5f;
        this.mHeaderTriggerRate = 1.0f;
        this.mFooterTriggerRate = 1.0f;
        this.mTwoLevelBottomPullUpToCloseRate = 0.16666667f;
        this.mKernel = new RefreshKernelImpl();
        this.mState = RefreshState.None;
        this.mViceState = RefreshState.None;
        this.mLastOpenTime = 0L;
        this.mHeaderBackgroundColor = 0;
        this.mFooterBackgroundColor = 0;
        this.mFooterLocked = false;
        this.mVerticalPermit = false;
        this.mFalsifyEvent = null;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mScreenHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
        this.mReboundInterpolator = new SmartUtil(SmartUtil.INTERPOLATOR_VISCOUS_FLUID);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mFooterHeight = SmartUtil.dp2px(60.0f);
        this.mHeaderHeight = SmartUtil.dp2px(100.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartRefreshLayout);
        if (!typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_android_clipToPadding)) {
            super.setClipToPadding(false);
        }
        if (!typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_android_clipChildren)) {
            super.setClipChildren(false);
        }
        DefaultRefreshInitializer defaultRefreshInitializer = sRefreshInitializer;
        if (defaultRefreshInitializer != null) {
            defaultRefreshInitializer.initialize(context, this);
        }
        this.mDragRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlDragRate, this.mDragRate);
        this.mHeaderMaxDragRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlHeaderMaxDragRate, this.mHeaderMaxDragRate);
        this.mFooterMaxDragRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlFooterMaxDragRate, this.mFooterMaxDragRate);
        this.mHeaderTriggerRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlHeaderTriggerRate, this.mHeaderTriggerRate);
        this.mFooterTriggerRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlFooterTriggerRate, this.mFooterTriggerRate);
        this.mEnableRefresh = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableRefresh, this.mEnableRefresh);
        this.mReboundDuration = typedArrayObtainStyledAttributes.getInt(R.styleable.SmartRefreshLayout_srlReboundDuration, this.mReboundDuration);
        this.mEnableLoadMore = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMore, this.mEnableLoadMore);
        this.mHeaderHeight = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderHeight, this.mHeaderHeight);
        this.mFooterHeight = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterHeight, this.mFooterHeight);
        this.mHeaderInsetStart = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderInsetStart, this.mHeaderInsetStart);
        this.mFooterInsetStart = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterInsetStart, this.mFooterInsetStart);
        this.mDisableContentWhenRefresh = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenRefresh, this.mDisableContentWhenRefresh);
        this.mDisableContentWhenLoading = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenLoading, this.mDisableContentWhenLoading);
        this.mEnableHeaderTranslationContent = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent, this.mEnableHeaderTranslationContent);
        this.mEnableFooterTranslationContent = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent, this.mEnableFooterTranslationContent);
        this.mEnablePreviewInEditMode = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePreviewInEditMode, this.mEnablePreviewInEditMode);
        this.mEnableAutoLoadMore = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableAutoLoadMore, this.mEnableAutoLoadMore);
        this.mEnableOverScrollBounce = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollBounce, this.mEnableOverScrollBounce);
        this.mEnablePureScrollMode = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePureScrollMode, this.mEnablePureScrollMode);
        this.mEnableScrollContentWhenLoaded = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenLoaded, this.mEnableScrollContentWhenLoaded);
        this.mEnableScrollContentWhenRefreshed = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenRefreshed, this.mEnableScrollContentWhenRefreshed);
        this.mEnableLoadMoreWhenContentNotFull = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMoreWhenContentNotFull, this.mEnableLoadMoreWhenContentNotFull);
        this.mEnableFooterFollowWhenNoMoreData = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenLoadFinished, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableFooterFollowWhenNoMoreData = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenNoMoreData, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableClipHeaderWhenFixedBehind = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipHeaderWhenFixedBehind, this.mEnableClipHeaderWhenFixedBehind);
        this.mEnableClipFooterWhenFixedBehind = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipFooterWhenFixedBehind, this.mEnableClipFooterWhenFixedBehind);
        this.mEnableOverScrollDrag = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag, this.mEnableOverScrollDrag);
        this.mFixedHeaderViewId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFixedHeaderViewId, this.mFixedHeaderViewId);
        this.mFixedFooterViewId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFixedFooterViewId, this.mFixedFooterViewId);
        this.mHeaderTranslationViewId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlHeaderTranslationViewId, this.mHeaderTranslationViewId);
        this.mFooterTranslationViewId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFooterTranslationViewId, this.mFooterTranslationViewId);
        this.mEnableNestedScrolling = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableNestedScrolling, this.mEnableNestedScrolling);
        this.mNestedChild.setNestedScrollingEnabled(this.mEnableNestedScrolling);
        this.mManualLoadMore = this.mManualLoadMore || typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableLoadMore);
        this.mManualHeaderTranslationContent = this.mManualHeaderTranslationContent || typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent);
        this.mManualFooterTranslationContent = this.mManualFooterTranslationContent || typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent);
        this.mHeaderHeightStatus = typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlHeaderHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mHeaderHeightStatus;
        this.mFooterHeightStatus = typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlFooterHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mFooterHeightStatus;
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_srlAccentColor, 0);
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_srlPrimaryColor, 0);
        if (color2 != 0) {
            if (color != 0) {
                this.mPrimaryColors = new int[]{color2, color};
            } else {
                this.mPrimaryColors = new int[]{color2};
            }
        } else if (color != 0) {
            this.mPrimaryColors = new int[]{0, color};
        }
        if (this.mEnablePureScrollMode && !this.mManualLoadMore && !this.mEnableLoadMore) {
            this.mEnableLoadMore = true;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onFinishInflate() {
        /*
            r11 = this;
            super.onFinishInflate()
            int r0 = super.getChildCount()
            r1 = 3
            if (r0 > r1) goto L9e
            r2 = -1
            r3 = 0
            r5 = r2
            r4 = r3
            r6 = r4
        Lf:
            r7 = 2
            r8 = 1
            if (r4 >= r0) goto L33
            android.view.View r9 = super.getChildAt(r4)
            boolean r10 = com.ido.smartrefresh.layoutkernel.util.SmartUtil.isContentView(r9)
            if (r10 == 0) goto L24
            if (r6 < r7) goto L21
            if (r4 != r8) goto L24
        L21:
            r5 = r4
            r6 = r7
            goto L30
        L24:
            boolean r7 = r9 instanceof com.ido.smartrefresh.layoutkernel.api.RefreshComponent
            if (r7 != 0) goto L30
            if (r6 >= r8) goto L30
            if (r4 <= 0) goto L2d
            goto L2e
        L2d:
            r8 = r3
        L2e:
            r5 = r4
            r6 = r8
        L30:
            int r4 = r4 + 1
            goto Lf
        L33:
            if (r5 < 0) goto L4d
            com.ido.smartrefresh.layoutkernel.wrapper.RefreshContentWrapper r4 = new com.ido.smartrefresh.layoutkernel.wrapper.RefreshContentWrapper
            android.view.View r6 = super.getChildAt(r5)
            r4.<init>(r6)
            r11.mRefreshContent = r4
            if (r5 != r8) goto L48
            if (r0 != r1) goto L45
            goto L46
        L45:
            r7 = r2
        L46:
            r1 = r3
            goto L4f
        L48:
            if (r0 != r7) goto L4d
            r1 = r2
            r7 = r8
            goto L4f
        L4d:
            r1 = r2
            r7 = r1
        L4f:
            r4 = r3
        L50:
            if (r4 >= r0) goto L9d
            android.view.View r5 = super.getChildAt(r4)
            if (r4 == r1) goto L8b
            if (r4 == r7) goto L65
            if (r1 != r2) goto L65
            com.ido.smartrefresh.layoutkernel.api.RefreshComponent r6 = r11.mRefreshHeader
            if (r6 != 0) goto L65
            boolean r6 = r5 instanceof com.ido.smartrefresh.layoutkernel.api.RefreshHeader
            if (r6 == 0) goto L65
            goto L8b
        L65:
            if (r4 == r7) goto L6d
            if (r7 != r2) goto L9a
            boolean r6 = r5 instanceof com.ido.smartrefresh.layoutkernel.api.RefreshFooter
            if (r6 == 0) goto L9a
        L6d:
            boolean r6 = r11.mEnableLoadMore
            if (r6 != 0) goto L78
            boolean r6 = r11.mManualLoadMore
            if (r6 != 0) goto L76
            goto L78
        L76:
            r6 = r3
            goto L79
        L78:
            r6 = r8
        L79:
            r11.mEnableLoadMore = r6
            boolean r6 = r5 instanceof com.ido.smartrefresh.layoutkernel.api.RefreshFooter
            if (r6 == 0) goto L82
            com.ido.smartrefresh.layoutkernel.api.RefreshFooter r5 = (com.ido.smartrefresh.layoutkernel.api.RefreshFooter) r5
            goto L88
        L82:
            com.ido.smartrefresh.layoutkernel.wrapper.RefreshFooterWrapper r6 = new com.ido.smartrefresh.layoutkernel.wrapper.RefreshFooterWrapper
            r6.<init>(r5)
            r5 = r6
        L88:
            r11.mRefreshFooter = r5
            goto L9a
        L8b:
            boolean r6 = r5 instanceof com.ido.smartrefresh.layoutkernel.api.RefreshHeader
            if (r6 == 0) goto L92
            com.ido.smartrefresh.layoutkernel.api.RefreshHeader r5 = (com.ido.smartrefresh.layoutkernel.api.RefreshHeader) r5
            goto L98
        L92:
            com.ido.smartrefresh.layoutkernel.wrapper.RefreshHeaderWrapper r6 = new com.ido.smartrefresh.layoutkernel.wrapper.RefreshHeaderWrapper
            r6.<init>(r5)
            r5 = r6
        L98:
            r11.mRefreshHeader = r5
        L9a:
            int r4 = r4 + 1
            goto L50
        L9d:
            return
        L9e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "最多只支持3个子View，Most only support three sub view"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.onFinishInflate():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        RefreshComponent refreshComponent;
        DefaultRefreshHeaderCreator defaultRefreshHeaderCreator;
        super.onAttachedToWindow();
        boolean z = true;
        this.mAttachedToWindow = true;
        if (!isInEditMode()) {
            if (this.mRefreshHeader == null && (defaultRefreshHeaderCreator = sHeaderCreator) != null) {
                RefreshHeader refreshHeaderCreateRefreshHeader = defaultRefreshHeaderCreator.createRefreshHeader(getContext(), this);
                if (refreshHeaderCreateRefreshHeader == null) {
                    throw new RuntimeException("DefaultRefreshHeaderCreator can not return null");
                }
                setRefreshHeader(refreshHeaderCreateRefreshHeader);
            }
            if (this.mRefreshFooter == null) {
                DefaultRefreshFooterCreator defaultRefreshFooterCreator = sFooterCreator;
                if (defaultRefreshFooterCreator != null) {
                    RefreshFooter refreshFooterCreateRefreshFooter = defaultRefreshFooterCreator.createRefreshFooter(getContext(), this);
                    if (refreshFooterCreateRefreshFooter == null) {
                        throw new RuntimeException("DefaultRefreshFooterCreator can not return null");
                    }
                    setRefreshFooter(refreshFooterCreateRefreshFooter);
                }
            } else {
                if (!this.mEnableLoadMore && this.mManualLoadMore) {
                    z = false;
                }
                this.mEnableLoadMore = z;
            }
            if (this.mRefreshContent == null) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    RefreshComponent refreshComponent2 = this.mRefreshHeader;
                    if ((refreshComponent2 == null || childAt != refreshComponent2.getView()) && ((refreshComponent = this.mRefreshFooter) == null || childAt != refreshComponent.getView())) {
                        this.mRefreshContent = new RefreshContentWrapper(childAt);
                    }
                }
            }
            if (this.mRefreshContent == null) {
                int iDp2px = SmartUtil.dp2px(20.0f);
                TextView textView = new TextView(getContext());
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText(R.string.srl_content_empty);
                super.addView(textView, 0, new LayoutParams(-1, -1));
                this.mRefreshContent = new RefreshContentWrapper(textView);
                this.mRefreshContent.getView().setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
            }
            View viewFindViewById = findViewById(this.mFixedHeaderViewId);
            View viewFindViewById2 = findViewById(this.mFixedFooterViewId);
            this.mRefreshContent.setScrollBoundaryDecider(this.mScrollBoundaryDecider);
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.setUpComponent(this.mKernel, viewFindViewById, viewFindViewById2);
            if (this.mSpinner != 0) {
                notifyStateChanged(RefreshState.None);
                RefreshContent refreshContent = this.mRefreshContent;
                this.mSpinner = 0;
                refreshContent.moveSpinner(0, this.mHeaderTranslationViewId, this.mFooterTranslationViewId);
            }
        }
        int[] iArr = this.mPrimaryColors;
        if (iArr != null) {
            RefreshComponent refreshComponent3 = this.mRefreshHeader;
            if (refreshComponent3 != null) {
                refreshComponent3.setPrimaryColors(iArr);
            }
            RefreshComponent refreshComponent4 = this.mRefreshFooter;
            if (refreshComponent4 != null) {
                refreshComponent4.setPrimaryColors(this.mPrimaryColors);
            }
        }
        RefreshContent refreshContent2 = this.mRefreshContent;
        if (refreshContent2 != null) {
            super.bringChildToFront(refreshContent2.getView());
        }
        RefreshComponent refreshComponent5 = this.mRefreshHeader;
        if (refreshComponent5 != null && refreshComponent5.getSpinnerStyle().front) {
            super.bringChildToFront(this.mRefreshHeader.getView());
        }
        RefreshComponent refreshComponent6 = this.mRefreshFooter;
        if (refreshComponent6 == null || !refreshComponent6.getSpinnerStyle().front) {
            return;
        }
        super.bringChildToFront(this.mRefreshFooter.getView());
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z = isInEditMode() && this.mEnablePreviewInEditMode;
        int childCount = super.getChildCount();
        int measuredWidth = 0;
        int measuredHeight = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = super.getChildAt(i7);
            if (childAt.getVisibility() != 8 && !"GONE".equals(childAt.getTag(R.id.srl_tag))) {
                RefreshComponent refreshComponent = this.mRefreshHeader;
                if (refreshComponent != null && refreshComponent.getView() == childAt) {
                    View view = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, layoutParams.width);
                    int iMax = this.mHeaderHeight;
                    if (this.mHeaderHeightStatus.ordinal < DimensionStatus.XmlLayoutUnNotify.ordinal) {
                        if (layoutParams.height > 0) {
                            int i8 = layoutParams.height + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                            if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.mHeaderHeight = layoutParams.height + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                                this.mHeaderHeightStatus = DimensionStatus.XmlExactUnNotify;
                            }
                            iMax = i8;
                        } else if (layoutParams.height == -2 && (this.mRefreshHeader.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mHeaderHeightStatus.notified)) {
                            int iMax2 = Math.max((View.MeasureSpec.getSize(i2) - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, 0);
                            view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(iMax2, Integer.MIN_VALUE));
                            int measuredHeight2 = view.getMeasuredHeight();
                            if (measuredHeight2 > 0) {
                                if (measuredHeight2 != iMax2 && this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                    this.mHeaderHeight = measuredHeight2 + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
                                    this.mHeaderHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                }
                                iMax = -1;
                            }
                        }
                    }
                    if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                        iMax = View.MeasureSpec.getSize(i2);
                        i6 = -1;
                        i5 = 0;
                    } else {
                        if (!this.mRefreshHeader.getSpinnerStyle().scale || z) {
                            i5 = 0;
                        } else {
                            i5 = 0;
                            iMax = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableRefresh) ? this.mSpinner : 0);
                        }
                        i6 = -1;
                    }
                    if (iMax != i6) {
                        view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((iMax - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, i5), BasicMeasure.EXACTLY));
                    }
                    if (!this.mHeaderHeightStatus.notified) {
                        float f2 = this.mHeaderMaxDragRate;
                        if (f2 < 10.0f) {
                            f2 *= this.mHeaderHeight;
                        }
                        this.mHeaderHeightStatus = this.mHeaderHeightStatus.notified();
                        this.mRefreshHeader.onInitialized(this.mKernel, this.mHeaderHeight, (int) f2);
                    }
                    if (z && isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
                        measuredWidth += view.getMeasuredWidth();
                        measuredHeight += view.getMeasuredHeight();
                    }
                }
                RefreshComponent refreshComponent2 = this.mRefreshFooter;
                if (refreshComponent2 == null || refreshComponent2.getView() != childAt) {
                    i3 = 0;
                } else {
                    View view2 = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : sDefaultMarginLP;
                    int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin, layoutParams2.width);
                    int iMax3 = this.mFooterHeight;
                    if (this.mFooterHeightStatus.ordinal < DimensionStatus.XmlLayoutUnNotify.ordinal) {
                        if (layoutParams2.height > 0) {
                            iMax3 = layoutParams2.height + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                            if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.mFooterHeight = layoutParams2.height + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                                this.mFooterHeightStatus = DimensionStatus.XmlExactUnNotify;
                            }
                        } else if (layoutParams2.height == -2 && (this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mFooterHeightStatus.notified)) {
                            int iMax4 = Math.max((View.MeasureSpec.getSize(i2) - marginLayoutParams2.bottomMargin) - marginLayoutParams2.topMargin, 0);
                            view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(iMax4, Integer.MIN_VALUE));
                            int measuredHeight3 = view2.getMeasuredHeight();
                            if (measuredHeight3 > 0) {
                                if (measuredHeight3 != iMax4 && this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                    this.mFooterHeight = measuredHeight3 + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                                    this.mFooterHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                }
                                iMax3 = -1;
                            }
                        }
                    }
                    if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                        iMax3 = View.MeasureSpec.getSize(i2);
                        i4 = -1;
                        i3 = 0;
                    } else {
                        if (!this.mRefreshFooter.getSpinnerStyle().scale || z) {
                            i3 = 0;
                        } else {
                            i3 = 0;
                            iMax3 = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0);
                        }
                        i4 = -1;
                    }
                    if (iMax3 != i4) {
                        view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((iMax3 - marginLayoutParams2.bottomMargin) - marginLayoutParams2.topMargin, i3), BasicMeasure.EXACTLY));
                    }
                    if (!this.mFooterHeightStatus.notified) {
                        float f3 = this.mFooterMaxDragRate;
                        if (f3 < 10.0f) {
                            f3 *= this.mFooterHeight;
                        }
                        this.mFooterHeightStatus = this.mFooterHeightStatus.notified();
                        this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) f3);
                    }
                    if (z && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        measuredWidth += view2.getMeasuredWidth();
                        measuredHeight += view2.getMeasuredHeight();
                    }
                }
                RefreshContent refreshContent = this.mRefreshContent;
                if (refreshContent != null && refreshContent.getView() == childAt) {
                    View view3 = this.mRefreshContent.getView();
                    ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : sDefaultMarginLP;
                    view3.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams3.leftMargin + marginLayoutParams3.rightMargin, layoutParams3.width), ViewGroup.getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams3.topMargin + marginLayoutParams3.bottomMargin + ((!z || ((this.mRefreshHeader == null || !isEnableRefreshOrLoadMore(this.mEnableRefresh) || !isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader)) ? i3 : 1) == 0) ? 0 : this.mHeaderHeight) + ((!z || ((this.mRefreshFooter == null || !isEnableRefreshOrLoadMore(this.mEnableLoadMore) || !isEnableTranslationContent(this.mEnableFooterTranslationContent, this.mRefreshFooter)) ? i3 : 1) == 0) ? 0 : this.mFooterHeight), layoutParams3.height));
                    measuredWidth += view3.getMeasuredWidth() + marginLayoutParams3.leftMargin + marginLayoutParams3.rightMargin;
                    measuredHeight += view3.getMeasuredHeight() + marginLayoutParams3.topMargin + marginLayoutParams3.bottomMargin;
                }
            }
        }
        super.setMeasuredDimension(View.resolveSize(Math.max(measuredWidth + getPaddingLeft() + getPaddingRight(), super.getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(measuredHeight + getPaddingTop() + getPaddingBottom(), super.getSuggestedMinimumHeight()), i2));
        this.mLastTouchX = getMeasuredWidth() / 2.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int iMax;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = super.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = super.getChildAt(i5);
            if (childAt.getVisibility() != 8 && !"GONE".equals(childAt.getTag(R.id.srl_tag))) {
                RefreshContent refreshContent = this.mRefreshContent;
                if (refreshContent != null && refreshContent.getView() == childAt) {
                    boolean z2 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh) && this.mRefreshHeader != null;
                    View view = this.mRefreshContent.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    int i6 = marginLayoutParams.leftMargin + paddingLeft;
                    int i7 = marginLayoutParams.topMargin + paddingTop;
                    int measuredWidth = view.getMeasuredWidth() + i6;
                    int measuredHeight = view.getMeasuredHeight() + i7;
                    if (z2 && isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader)) {
                        int i8 = this.mHeaderHeight;
                        i7 += i8;
                        measuredHeight += i8;
                    }
                    view.layout(i6, i7, measuredWidth, measuredHeight);
                }
                RefreshComponent refreshComponent = this.mRefreshHeader;
                if (refreshComponent != null && refreshComponent.getView() == childAt) {
                    boolean z3 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh);
                    View view2 = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : sDefaultMarginLP;
                    int i9 = marginLayoutParams2.leftMargin;
                    int i10 = marginLayoutParams2.topMargin + this.mHeaderInsetStart;
                    int measuredWidth2 = view2.getMeasuredWidth() + i9;
                    int measuredHeight2 = view2.getMeasuredHeight() + i10;
                    if (!z3 && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        int i11 = this.mHeaderHeight;
                        i10 -= i11;
                        measuredHeight2 -= i11;
                    }
                    view2.layout(i9, i10, measuredWidth2, measuredHeight2);
                }
                RefreshComponent refreshComponent2 = this.mRefreshFooter;
                if (refreshComponent2 != null && refreshComponent2.getView() == childAt) {
                    boolean z4 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableLoadMore);
                    View view3 = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : sDefaultMarginLP;
                    SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                    int i12 = marginLayoutParams3.leftMargin;
                    int measuredHeight3 = (marginLayoutParams3.topMargin + getMeasuredHeight()) - this.mFooterInsetStart;
                    if (this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && this.mEnableFooterFollowWhenNoMoreData && this.mRefreshContent != null && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        View view4 = this.mRefreshContent.getView();
                        ViewGroup.LayoutParams layoutParams4 = view4.getLayoutParams();
                        measuredHeight3 = view4.getMeasuredHeight() + paddingTop + paddingTop + (layoutParams4 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams4).topMargin : 0);
                    }
                    if (spinnerStyle == SpinnerStyle.MatchLayout) {
                        measuredHeight3 = marginLayoutParams3.topMargin - this.mFooterInsetStart;
                    } else {
                        if (z4 || spinnerStyle == SpinnerStyle.FixedFront || spinnerStyle == SpinnerStyle.FixedBehind) {
                            iMax = this.mFooterHeight;
                        } else if (spinnerStyle.scale && this.mSpinner < 0) {
                            iMax = Math.max(isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0, 0);
                        }
                        measuredHeight3 -= iMax;
                    }
                    view3.layout(i12, measuredHeight3, view3.getMeasuredWidth() + i12, view3.getMeasuredHeight() + measuredHeight3);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mManualLoadMore = true;
        this.animationRunnable = null;
        ValueAnimator valueAnimator = this.reboundAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.reboundAnimator.removeAllUpdateListeners();
            this.reboundAnimator.setDuration(0L);
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
        if (this.mRefreshHeader != null && this.mState == RefreshState.Refreshing) {
            this.mRefreshHeader.onFinish(this, false);
        }
        if (this.mRefreshFooter != null && this.mState == RefreshState.Loading) {
            this.mRefreshFooter.onFinish(this, false);
        }
        if (this.mSpinner != 0) {
            this.mKernel.moveSpinner(0, true);
        }
        if (this.mState != RefreshState.None) {
            notifyStateChanged(RefreshState.None);
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mFooterLocked = false;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        Paint paint;
        Paint paint2;
        RefreshContent refreshContent = this.mRefreshContent;
        View view2 = refreshContent != null ? refreshContent.getView() : null;
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null && refreshComponent.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableRefresh) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int iMax = Math.max(view2.getTop() + view2.getPaddingTop() + this.mSpinner, view.getTop());
                int i = this.mHeaderBackgroundColor;
                if (i != 0 && (paint2 = this.mPaint) != null) {
                    paint2.setColor(i);
                    if (this.mRefreshHeader.getSpinnerStyle().scale) {
                        iMax = view.getBottom();
                    } else if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        iMax = view.getBottom() + this.mSpinner;
                    }
                    canvas.drawRect(0.0f, view.getTop(), getWidth(), iMax, this.mPaint);
                }
                if ((this.mEnableClipHeaderWhenFixedBehind && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) || this.mRefreshHeader.getSpinnerStyle().scale) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), iMax);
                    boolean zDrawChild = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return zDrawChild;
                }
            }
        }
        RefreshComponent refreshComponent2 = this.mRefreshFooter;
        if (refreshComponent2 != null && refreshComponent2.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableLoadMore) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int iMin = Math.min((view2.getBottom() - view2.getPaddingBottom()) + this.mSpinner, view.getBottom());
                int i2 = this.mFooterBackgroundColor;
                if (i2 != 0 && (paint = this.mPaint) != null) {
                    paint.setColor(i2);
                    if (this.mRefreshFooter.getSpinnerStyle().scale) {
                        iMin = view.getTop();
                    } else if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate) {
                        iMin = view.getTop() + this.mSpinner;
                    }
                    canvas.drawRect(0.0f, iMin, getWidth(), view.getBottom(), this.mPaint);
                }
                if ((this.mEnableClipFooterWhenFixedBehind && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) || this.mRefreshFooter.getSpinnerStyle().scale) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), iMin, view.getRight(), view.getBottom());
                    boolean zDrawChild2 = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return zDrawChild2;
                }
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.mScroller.getCurrY();
        if (this.mScroller.computeScrollOffset()) {
            int finalY = this.mScroller.getFinalY();
            if ((finalY < 0 && ((this.mEnableRefresh || this.mEnableOverScrollDrag) && this.mRefreshContent.canRefresh())) || (finalY > 0 && ((this.mEnableLoadMore || this.mEnableOverScrollDrag) && this.mRefreshContent.canLoadMore()))) {
                if (this.mVerticalPermit) {
                    animSpinnerBounce(finalY > 0 ? -this.mScroller.getCurrVelocity() : this.mScroller.getCurrVelocity());
                }
                this.mScroller.forceFinished(true);
            } else {
                this.mVerticalPermit = true;
                invalidate();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:214:0x02bc  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r23) {
        /*
            Method dump skipped, instruction units count: 886
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        View scrollableView = this.mRefreshContent.getScrollableView();
        if ((Build.VERSION.SDK_INT >= 21 || !(scrollableView instanceof AbsListView)) && ViewCompat.isNestedScrollingEnabled(scrollableView)) {
            this.mEnableDisallowIntercept = z;
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    protected boolean startFlingIfNeed(float f2) {
        if (f2 == 0.0f) {
            f2 = this.mCurrentVelocity;
        }
        if (Build.VERSION.SDK_INT > 27 && this.mRefreshContent != null) {
            getScaleY();
            View view = this.mRefreshContent.getView();
            if (getScaleY() == -1.0f && view.getScaleY() == -1.0f) {
                f2 = -f2;
            }
        }
        if (Math.abs(f2) > this.mMinimumVelocity) {
            if (this.mSpinner * f2 < 0.0f) {
                if (this.mState == RefreshState.Refreshing || this.mState == RefreshState.Loading || (this.mSpinner < 0 && this.mFooterNoMoreData)) {
                    this.animationRunnable = new FlingRunnable(f2).start();
                    return true;
                }
                if (this.mState.isReleaseToOpening) {
                    return true;
                }
            }
            if ((f2 < 0.0f && ((this.mEnableOverScrollBounce && (this.mEnableLoadMore || this.mEnableOverScrollDrag)) || ((this.mState == RefreshState.Loading && this.mSpinner >= 0) || (this.mEnableAutoLoadMore && isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) || (f2 > 0.0f && ((this.mEnableOverScrollBounce && this.mEnableRefresh) || this.mEnableOverScrollDrag || (this.mState == RefreshState.Refreshing && this.mSpinner <= 0)))) {
                this.mVerticalPermit = false;
                this.mScroller.fling(0, 0, 0, (int) (-f2), 0, 0, -2147483647, Integer.MAX_VALUE);
                this.mScroller.computeScrollOffset();
                invalidate();
            }
        }
        return false;
    }

    protected boolean interceptAnimatorByAction(int i) {
        if (i == 0) {
            if (this.reboundAnimator != null) {
                if (this.mState.isFinishing || this.mState == RefreshState.TwoLevelReleased || this.mState == RefreshState.RefreshReleased || this.mState == RefreshState.LoadReleased) {
                    return true;
                }
                if (this.mState == RefreshState.PullDownCanceled) {
                    this.mKernel.setState(RefreshState.PullDownToRefresh);
                } else if (this.mState == RefreshState.PullUpCanceled) {
                    this.mKernel.setState(RefreshState.PullUpToLoad);
                }
                this.reboundAnimator.setDuration(0L);
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
        }
        return this.reboundAnimator != null;
    }

    protected void notifyStateChanged(RefreshState refreshState) {
        RefreshState refreshState2 = this.mState;
        if (refreshState2 != refreshState) {
            this.mState = refreshState;
            this.mViceState = refreshState;
            RefreshComponent refreshComponent = this.mRefreshHeader;
            RefreshComponent refreshComponent2 = this.mRefreshFooter;
            OnMultiListener onMultiListener = this.mOnMultiListener;
            if (refreshComponent != null) {
                refreshComponent.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshComponent2 != null) {
                refreshComponent2.onStateChanged(this, refreshState2, refreshState);
            }
            if (onMultiListener != null) {
                onMultiListener.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshState == RefreshState.LoadFinish) {
                this.mFooterLocked = false;
                return;
            }
            return;
        }
        if (this.mViceState != refreshState2) {
            this.mViceState = refreshState2;
        }
    }

    protected void setStateDirectLoading(boolean z) {
        if (this.mState != RefreshState.Loading) {
            this.mLastOpenTime = System.currentTimeMillis();
            this.mFooterLocked = true;
            notifyStateChanged(RefreshState.Loading);
            OnLoadMoreListener onLoadMoreListener = this.mLoadMoreListener;
            if (onLoadMoreListener != null) {
                if (z) {
                    onLoadMoreListener.onLoadMore(this);
                }
            } else if (this.mOnMultiListener == null) {
                finishLoadMore(2000);
            }
            if (this.mRefreshFooter != null) {
                float f2 = this.mFooterMaxDragRate;
                if (f2 < 10.0f) {
                    f2 *= this.mFooterHeight;
                }
                this.mRefreshFooter.onStartAnimator(this, this.mFooterHeight, (int) f2);
            }
            OnMultiListener onMultiListener = this.mOnMultiListener;
            if (onMultiListener == null || !(this.mRefreshFooter instanceof RefreshFooter)) {
                return;
            }
            if (z) {
                onMultiListener.onLoadMore(this);
            }
            float f3 = this.mFooterMaxDragRate;
            if (f3 < 10.0f) {
                f3 *= this.mFooterHeight;
            }
            this.mOnMultiListener.onFooterStartAnimator((RefreshFooter) this.mRefreshFooter, this.mFooterHeight, (int) f3);
        }
    }

    protected void setStateLoading(final boolean z) {
        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (animator == null || animator.getDuration() != 0) {
                    SmartRefreshLayout.this.setStateDirectLoading(z);
                }
            }
        };
        notifyStateChanged(RefreshState.LoadReleased);
        ValueAnimator valueAnimatorAnimSpinner = this.mKernel.animSpinner(-this.mFooterHeight);
        if (valueAnimatorAnimSpinner != null) {
            valueAnimatorAnimSpinner.addListener(animatorListenerAdapter);
        }
        if (this.mRefreshFooter != null) {
            float f2 = this.mFooterMaxDragRate;
            if (f2 < 10.0f) {
                f2 *= this.mFooterHeight;
            }
            this.mRefreshFooter.onReleased(this, this.mFooterHeight, (int) f2);
        }
        if (this.mOnMultiListener != null && (this.mRefreshFooter instanceof RefreshFooter)) {
            float f3 = this.mFooterMaxDragRate;
            if (f3 < 10.0f) {
                f3 *= this.mFooterHeight;
            }
            this.mOnMultiListener.onFooterReleased((RefreshFooter) this.mRefreshFooter, this.mFooterHeight, (int) f3);
        }
        if (valueAnimatorAnimSpinner == null) {
            animatorListenerAdapter.onAnimationEnd(null);
        }
    }

    protected void setStateRefreshing(final boolean z) {
        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.2
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (animator == null || animator.getDuration() != 0) {
                    SmartRefreshLayout.this.mLastOpenTime = System.currentTimeMillis();
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.Refreshing);
                    if (SmartRefreshLayout.this.mRefreshListener != null) {
                        if (z) {
                            SmartRefreshLayout.this.mRefreshListener.onRefresh(SmartRefreshLayout.this);
                        }
                    } else if (SmartRefreshLayout.this.mOnMultiListener == null) {
                        SmartRefreshLayout.this.finishRefresh(3000);
                    }
                    if (SmartRefreshLayout.this.mRefreshHeader != null) {
                        float f2 = SmartRefreshLayout.this.mHeaderMaxDragRate < 10.0f ? SmartRefreshLayout.this.mHeaderHeight * SmartRefreshLayout.this.mHeaderMaxDragRate : SmartRefreshLayout.this.mHeaderMaxDragRate;
                        RefreshComponent refreshComponent = SmartRefreshLayout.this.mRefreshHeader;
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        refreshComponent.onStartAnimator(smartRefreshLayout, smartRefreshLayout.mHeaderHeight, (int) f2);
                    }
                    if (SmartRefreshLayout.this.mOnMultiListener == null || !(SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                        return;
                    }
                    if (z) {
                        SmartRefreshLayout.this.mOnMultiListener.onRefresh(SmartRefreshLayout.this);
                    }
                    SmartRefreshLayout.this.mOnMultiListener.onHeaderStartAnimator((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, SmartRefreshLayout.this.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate < 10.0f ? SmartRefreshLayout.this.mHeaderHeight * SmartRefreshLayout.this.mHeaderMaxDragRate : SmartRefreshLayout.this.mHeaderMaxDragRate));
                }
            }
        };
        notifyStateChanged(RefreshState.RefreshReleased);
        ValueAnimator valueAnimatorAnimSpinner = this.mKernel.animSpinner(this.mHeaderHeight);
        if (valueAnimatorAnimSpinner != null) {
            valueAnimatorAnimSpinner.addListener(animatorListenerAdapter);
        }
        if (this.mRefreshHeader != null) {
            float f2 = this.mHeaderMaxDragRate;
            if (f2 < 10.0f) {
                f2 *= this.mHeaderHeight;
            }
            this.mRefreshHeader.onReleased(this, this.mHeaderHeight, (int) f2);
        }
        if (this.mOnMultiListener != null && (this.mRefreshHeader instanceof RefreshHeader)) {
            float f3 = this.mHeaderMaxDragRate;
            if (f3 < 10.0f) {
                f3 *= this.mHeaderHeight;
            }
            this.mOnMultiListener.onHeaderReleased((RefreshHeader) this.mRefreshHeader, this.mHeaderHeight, (int) f3);
        }
        if (valueAnimatorAnimSpinner == null) {
            animatorListenerAdapter.onAnimationEnd(null);
        }
    }

    protected void setViceState(RefreshState refreshState) {
        if (this.mState.isDragging && this.mState.isHeader != refreshState.isHeader) {
            notifyStateChanged(RefreshState.None);
        }
        if (this.mViceState != refreshState) {
            this.mViceState = refreshState;
        }
    }

    protected boolean isEnableTranslationContent(boolean z, RefreshComponent refreshComponent) {
        return z || this.mEnablePureScrollMode || refreshComponent == null || refreshComponent.getSpinnerStyle() == SpinnerStyle.FixedBehind;
    }

    protected boolean isEnableRefreshOrLoadMore(boolean z) {
        return z && !this.mEnablePureScrollMode;
    }

    protected class FlingRunnable implements Runnable {
        int mOffset;
        float mVelocity;
        int mFrame = 0;
        int mFrameDelay = 10;
        float mDamping = 0.98f;
        long mStartTime = 0;
        long mLastTime = AnimationUtils.currentAnimationTimeMillis();

        FlingRunnable(float f2) {
            this.mVelocity = f2;
            this.mOffset = SmartRefreshLayout.this.mSpinner;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0058  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00d4 A[EDGE_INSN: B:52:0x00d4->B:50:0x00d4 BREAK  A[LOOP:0: B:34:0x0080->B:49:0x00d0], SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Runnable start() {
            /*
                Method dump skipped, instruction units count: 229
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.FlingRunnable.start():java.lang.Runnable");
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SmartRefreshLayout.this.animationRunnable != this || SmartRefreshLayout.this.mState.isFinishing) {
                return;
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            long j = jCurrentAnimationTimeMillis - this.mLastTime;
            this.mVelocity = (float) (((double) this.mVelocity) * Math.pow(this.mDamping, (jCurrentAnimationTimeMillis - this.mStartTime) / (1000.0f / this.mFrameDelay)));
            float f2 = this.mVelocity * ((j * 1.0f) / 1000.0f);
            if (Math.abs(f2) > 1.0f) {
                this.mLastTime = jCurrentAnimationTimeMillis;
                this.mOffset = (int) (this.mOffset + f2);
                if (SmartRefreshLayout.this.mSpinner * this.mOffset > 0) {
                    SmartRefreshLayout.this.mKernel.moveSpinner(this.mOffset, true);
                    SmartRefreshLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
                    return;
                }
                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                smartRefreshLayout.animationRunnable = null;
                smartRefreshLayout.mKernel.moveSpinner(0, true);
                SmartUtil.fling(SmartRefreshLayout.this.mRefreshContent.getScrollableView(), (int) (-this.mVelocity));
                if (!SmartRefreshLayout.this.mFooterLocked || f2 <= 0.0f) {
                    return;
                }
                SmartRefreshLayout.this.mFooterLocked = false;
                return;
            }
            SmartRefreshLayout.this.animationRunnable = null;
        }
    }

    protected class BounceRunnable implements Runnable {
        int mSmoothDistance;
        float mVelocity;
        int mFrame = 0;
        int mFrameDelay = 10;
        float mOffset = 0.0f;
        long mLastTime = AnimationUtils.currentAnimationTimeMillis();

        BounceRunnable(float f2, int i) {
            this.mVelocity = f2;
            this.mSmoothDistance = i;
            SmartRefreshLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
            if (f2 > 0.0f) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
            } else {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SmartRefreshLayout.this.animationRunnable != this || SmartRefreshLayout.this.mState.isFinishing) {
                return;
            }
            if (Math.abs(SmartRefreshLayout.this.mSpinner) >= Math.abs(this.mSmoothDistance)) {
                if (this.mSmoothDistance != 0) {
                    double d2 = this.mVelocity;
                    this.mFrame = this.mFrame + 1;
                    this.mVelocity = (float) (d2 * Math.pow(0.44999998807907104d, r4 * 2));
                } else {
                    double d3 = this.mVelocity;
                    this.mFrame = this.mFrame + 1;
                    this.mVelocity = (float) (d3 * Math.pow(0.8500000238418579d, r4 * 2));
                }
            } else {
                double d4 = this.mVelocity;
                this.mFrame = this.mFrame + 1;
                this.mVelocity = (float) (d4 * Math.pow(0.949999988079071d, r4 * 2));
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float f2 = this.mVelocity * (((jCurrentAnimationTimeMillis - this.mLastTime) * 1.0f) / 1000.0f);
            if (Math.abs(f2) >= 1.0f) {
                this.mLastTime = jCurrentAnimationTimeMillis;
                this.mOffset += f2;
                SmartRefreshLayout.this.moveSpinnerInfinitely(this.mOffset);
                SmartRefreshLayout.this.mHandler.postDelayed(this, this.mFrameDelay);
                return;
            }
            if (SmartRefreshLayout.this.mViceState.isDragging && SmartRefreshLayout.this.mViceState.isHeader) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownCanceled);
            } else if (SmartRefreshLayout.this.mViceState.isDragging && SmartRefreshLayout.this.mViceState.isFooter) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpCanceled);
            }
            SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
            smartRefreshLayout.animationRunnable = null;
            if (Math.abs(smartRefreshLayout.mSpinner) >= Math.abs(this.mSmoothDistance)) {
                int iMin = Math.min(Math.max((int) SmartUtil.px2dp(Math.abs(SmartRefreshLayout.this.mSpinner - this.mSmoothDistance)), 30), 100) * 10;
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.animSpinner(this.mSmoothDistance, 0, smartRefreshLayout2.mReboundInterpolator, iMin);
            }
        }
    }

    protected ValueAnimator animSpinner(int i, int i2, Interpolator interpolator, int i3) {
        if (this.mSpinner == i) {
            return null;
        }
        ValueAnimator valueAnimator = this.reboundAnimator;
        if (valueAnimator != null) {
            valueAnimator.setDuration(0L);
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
        this.animationRunnable = null;
        this.reboundAnimator = ValueAnimator.ofInt(this.mSpinner, i);
        this.reboundAnimator.setDuration(i3);
        this.reboundAnimator.setInterpolator(interpolator);
        this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (animator == null || animator.getDuration() != 0) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.reboundAnimator = null;
                    if (smartRefreshLayout.mSpinner == 0 && SmartRefreshLayout.this.mState != RefreshState.None && !SmartRefreshLayout.this.mState.isOpening && !SmartRefreshLayout.this.mState.isDragging) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                    } else if (SmartRefreshLayout.this.mState != SmartRefreshLayout.this.mViceState) {
                        SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                        smartRefreshLayout2.setViceState(smartRefreshLayout2.mState);
                    }
                }
            }
        });
        this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator2.getAnimatedValue()).intValue(), false);
            }
        });
        this.reboundAnimator.setStartDelay(i2);
        this.reboundAnimator.start();
        return this.reboundAnimator;
    }

    protected void animSpinnerBounce(float f2) {
        if (this.reboundAnimator == null) {
            if (f2 > 0.0f && (this.mState == RefreshState.Refreshing || this.mState == RefreshState.TwoLevel)) {
                this.animationRunnable = new BounceRunnable(f2, this.mHeaderHeight);
                return;
            }
            if (f2 < 0.0f && (this.mState == RefreshState.Loading || ((this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) || (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && this.mState != RefreshState.Refreshing)))) {
                this.animationRunnable = new BounceRunnable(f2, -this.mFooterHeight);
            } else if (this.mSpinner == 0 && this.mEnableOverScrollBounce) {
                this.animationRunnable = new BounceRunnable(f2, 0);
            }
        }
    }

    protected void overSpinner() {
        if (this.mState == RefreshState.TwoLevel) {
            if (this.mCurrentVelocity > -1000 && this.mSpinner > getHeight() / 2) {
                ValueAnimator valueAnimatorAnimSpinner = this.mKernel.animSpinner(getHeight());
                if (valueAnimatorAnimSpinner != null) {
                    valueAnimatorAnimSpinner.setDuration(this.mFloorDuration);
                    return;
                }
                return;
            }
            if (this.mIsBeingDragged) {
                this.mKernel.finishTwoLevel();
                return;
            }
            return;
        }
        if (this.mState == RefreshState.Loading || (this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && this.mSpinner < 0 && isEnableRefreshOrLoadMore(this.mEnableLoadMore))) {
            int i = this.mSpinner;
            int i2 = this.mFooterHeight;
            if (i < (-i2)) {
                this.mKernel.animSpinner(-i2);
                return;
            } else {
                if (i > 0) {
                    this.mKernel.animSpinner(0);
                    return;
                }
                return;
            }
        }
        if (this.mState == RefreshState.Refreshing) {
            int i3 = this.mSpinner;
            int i4 = this.mHeaderHeight;
            if (i3 > i4) {
                this.mKernel.animSpinner(i4);
                return;
            } else {
                if (i3 < 0) {
                    this.mKernel.animSpinner(0);
                    return;
                }
                return;
            }
        }
        if (this.mState == RefreshState.PullDownToRefresh) {
            this.mKernel.setState(RefreshState.PullDownCanceled);
            return;
        }
        if (this.mState == RefreshState.PullUpToLoad) {
            this.mKernel.setState(RefreshState.PullUpCanceled);
            return;
        }
        if (this.mState == RefreshState.ReleaseToRefresh) {
            this.mKernel.setState(RefreshState.Refreshing);
            return;
        }
        if (this.mState == RefreshState.ReleaseToLoad) {
            this.mKernel.setState(RefreshState.Loading);
            return;
        }
        if (this.mState == RefreshState.ReleaseToTwoLevel) {
            this.mKernel.setState(RefreshState.TwoLevelReleased);
            return;
        }
        if (this.mState == RefreshState.RefreshReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(this.mHeaderHeight);
            }
        } else if (this.mState == RefreshState.LoadReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(-this.mFooterHeight);
            }
        } else {
            if (this.mState == RefreshState.LoadFinish || this.mSpinner == 0) {
                return;
            }
            this.mKernel.animSpinner(0);
        }
    }

    protected void moveSpinnerInfinitely(float f2) {
        float f3 = (!this.mNestedInProgress || this.mEnableLoadMoreWhenContentNotFull || f2 >= 0.0f || this.mRefreshContent.canLoadMore()) ? f2 : 0.0f;
        if (f3 > this.mScreenHeightPixels * 5 && getTag() == null && getTag(R.id.srl_tag) == null) {
            float f4 = this.mLastTouchY;
            int i = this.mScreenHeightPixels;
            if (f4 < i / 6.0f && this.mLastTouchX < i / 16.0f) {
                Toast.makeText(getContext(), "你这么死拉，臣妾做不到啊！", 0).show();
                setTag(R.id.srl_tag, "你这么死拉，臣妾做不到啊！");
            }
        }
        if (this.mState == RefreshState.TwoLevel && f3 > 0.0f) {
            this.mKernel.moveSpinner(Math.min((int) f3, getMeasuredHeight()), true);
        } else if (this.mState == RefreshState.Refreshing && f3 >= 0.0f) {
            int i2 = this.mHeaderHeight;
            if (f3 < i2) {
                this.mKernel.moveSpinner((int) f3, true);
            } else {
                float f5 = this.mHeaderMaxDragRate;
                if (f5 < 10.0f) {
                    f5 *= i2;
                }
                double d2 = f5 - this.mHeaderHeight;
                int iMax = Math.max((this.mScreenHeightPixels * 4) / 3, getHeight());
                int i3 = this.mHeaderHeight;
                double d3 = iMax - i3;
                double dMax = Math.max(0.0f, (f3 - i3) * this.mDragRate);
                double d4 = -dMax;
                if (d3 == 0.0d) {
                    d3 = 1.0d;
                }
                this.mKernel.moveSpinner(((int) Math.min(d2 * (1.0d - Math.pow(100.0d, d4 / d3)), dMax)) + this.mHeaderHeight, true);
            }
        } else if (f3 < 0.0f && (this.mState == RefreshState.Loading || ((this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mFooterNoMoreDataEffective && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) || (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) {
            int i4 = this.mFooterHeight;
            if (f3 > (-i4)) {
                this.mKernel.moveSpinner((int) f3, true);
            } else {
                float f6 = this.mFooterMaxDragRate;
                if (f6 < 10.0f) {
                    f6 *= i4;
                }
                double d5 = f6 - this.mFooterHeight;
                int iMax2 = Math.max((this.mScreenHeightPixels * 4) / 3, getHeight());
                int i5 = this.mFooterHeight;
                double d6 = iMax2 - i5;
                double d7 = -Math.min(0.0f, (i5 + f3) * this.mDragRate);
                double d8 = -d7;
                if (d6 == 0.0d) {
                    d6 = 1.0d;
                }
                this.mKernel.moveSpinner(((int) (-Math.min(d5 * (1.0d - Math.pow(100.0d, d8 / d6)), d7))) - this.mFooterHeight, true);
            }
        } else if (f3 >= 0.0f) {
            float f7 = this.mHeaderMaxDragRate;
            double d9 = f7 < 10.0f ? this.mHeaderHeight * f7 : f7;
            double dMax2 = Math.max(this.mScreenHeightPixels / 2, getHeight());
            double dMax3 = Math.max(0.0f, this.mDragRate * f3);
            double d10 = -dMax3;
            if (dMax2 == 0.0d) {
                dMax2 = 1.0d;
            }
            this.mKernel.moveSpinner((int) Math.min(d9 * (1.0d - Math.pow(100.0d, d10 / dMax2)), dMax3), true);
        } else {
            float f8 = this.mFooterMaxDragRate;
            double d11 = f8 < 10.0f ? this.mFooterHeight * f8 : f8;
            double dMax4 = Math.max(this.mScreenHeightPixels / 2, getHeight());
            double d12 = -Math.min(0.0f, this.mDragRate * f3);
            double d13 = -d12;
            if (dMax4 == 0.0d) {
                dMax4 = 1.0d;
            }
            this.mKernel.moveSpinner((int) (-Math.min(d11 * (1.0d - Math.pow(100.0d, d13 / dMax4)), d12)), true);
        }
        if (!this.mEnableAutoLoadMore || this.mFooterNoMoreData || !isEnableRefreshOrLoadMore(this.mEnableLoadMore) || f3 >= 0.0f || this.mState == RefreshState.Refreshing || this.mState == RefreshState.Loading || this.mState == RefreshState.LoadFinish) {
            return;
        }
        if (this.mDisableContentWhenLoading) {
            this.animationRunnable = null;
            this.mKernel.animSpinner(-this.mFooterHeight);
        }
        setStateDirectLoading(false);
        this.mHandler.postDelayed(new Runnable() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.5
            @Override // java.lang.Runnable
            public void run() {
                if (SmartRefreshLayout.this.mLoadMoreListener != null) {
                    SmartRefreshLayout.this.mLoadMoreListener.onLoadMore(SmartRefreshLayout.this);
                } else if (SmartRefreshLayout.this.mOnMultiListener == null) {
                    SmartRefreshLayout.this.finishLoadMore(2000);
                }
                OnMultiListener onMultiListener = SmartRefreshLayout.this.mOnMultiListener;
                if (onMultiListener != null) {
                    onMultiListener.onLoadMore(SmartRefreshLayout.this);
                }
            }
        }, this.mReboundDuration);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int backgroundColor;
        public SpinnerStyle spinnerStyle;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.backgroundColor = 0;
            this.spinnerStyle = null;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartRefreshLayout_Layout);
            this.backgroundColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_Layout_layout_srlBackgroundColor, this.backgroundColor);
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle)) {
                this.spinnerStyle = SpinnerStyle.values[typedArrayObtainStyledAttributes.getInt(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle, SpinnerStyle.Translate.ordinal)];
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.backgroundColor = 0;
            this.spinnerStyle = null;
        }
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mNestedParent.getNestedScrollAxes();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (isEnabled() && isNestedScrollingEnabled() && (i & 2) != 0) && (this.mEnableOverScrollDrag || this.mEnableRefresh || this.mEnableLoadMore);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mNestedParent.onNestedScrollAccepted(view, view2, i);
        this.mNestedChild.startNestedScroll(i & 2);
        this.mTotalUnconsumed = this.mSpinner;
        this.mNestedInProgress = true;
        interceptAnimatorByAction(0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        int i3;
        int i4 = this.mTotalUnconsumed;
        if (i2 * i4 > 0) {
            if (Math.abs(i2) > Math.abs(this.mTotalUnconsumed)) {
                i3 = this.mTotalUnconsumed;
                this.mTotalUnconsumed = 0;
            } else {
                this.mTotalUnconsumed -= i2;
                i3 = i2;
            }
            moveSpinnerInfinitely(this.mTotalUnconsumed);
        } else if (i2 <= 0 || !this.mFooterLocked) {
            i3 = 0;
        } else {
            this.mTotalUnconsumed = i4 - i2;
            moveSpinnerInfinitely(this.mTotalUnconsumed);
            i3 = i2;
        }
        this.mNestedChild.dispatchNestedPreScroll(i, i2 - i3, iArr, null);
        iArr[1] = iArr[1] + i3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        ScrollBoundaryDecider scrollBoundaryDecider;
        ViewParent parent;
        ScrollBoundaryDecider scrollBoundaryDecider2;
        boolean zDispatchNestedScroll = this.mNestedChild.dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        int i5 = i4 + this.mParentOffsetInWindow[1];
        if ((i5 < 0 && ((this.mEnableRefresh || this.mEnableOverScrollDrag) && (this.mTotalUnconsumed != 0 || (scrollBoundaryDecider2 = this.mScrollBoundaryDecider) == null || scrollBoundaryDecider2.canRefresh(this.mRefreshContent.getView())))) || (i5 > 0 && ((this.mEnableLoadMore || this.mEnableOverScrollDrag) && (this.mTotalUnconsumed != 0 || (scrollBoundaryDecider = this.mScrollBoundaryDecider) == null || scrollBoundaryDecider.canLoadMore(this.mRefreshContent.getView()))))) {
            if (this.mViceState == RefreshState.None || this.mViceState.isOpening) {
                this.mKernel.setState(i5 > 0 ? RefreshState.PullUpToLoad : RefreshState.PullDownToRefresh);
                if (!zDispatchNestedScroll && (parent = getParent()) != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            }
            int i6 = this.mTotalUnconsumed - i5;
            this.mTotalUnconsumed = i6;
            moveSpinnerInfinitely(i6);
        }
        if (!this.mFooterLocked || i2 >= 0) {
            return;
        }
        this.mFooterLocked = false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return (this.mFooterLocked && f3 > 0.0f) || startFlingIfNeed(-f3) || this.mNestedChild.dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return this.mNestedChild.dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
        this.mNestedParent.onStopNestedScroll(view);
        this.mNestedInProgress = false;
        this.mTotalUnconsumed = 0;
        overSpinner();
        this.mNestedChild.stopNestedScroll();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.mEnableNestedScrolling = z;
        this.mNestedChild.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.mEnableNestedScrolling && (this.mEnableOverScrollDrag || this.mEnableRefresh || this.mEnableLoadMore);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setHeaderHeight(float f2) {
        return setHeaderHeightPx(SmartUtil.dp2px(f2));
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setHeaderHeightPx(int i) {
        printAndSave("setHeaderHeightPx height=" + i);
        if (i != this.mHeaderHeight && this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mHeaderHeight = i;
            if (this.mRefreshHeader != null && this.mAttachedToWindow && this.mHeaderHeightStatus.notified) {
                SpinnerStyle spinnerStyle = this.mRefreshHeader.getSpinnerStyle();
                if (spinnerStyle != SpinnerStyle.MatchLayout && !spinnerStyle.scale) {
                    View view = this.mRefreshHeader.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(Math.max((this.mHeaderHeight - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, 0), BasicMeasure.EXACTLY));
                    int i2 = marginLayoutParams.leftMargin;
                    int i3 = (marginLayoutParams.topMargin + this.mHeaderInsetStart) - (spinnerStyle == SpinnerStyle.Translate ? this.mHeaderHeight : 0);
                    view.layout(i2, i3, view.getMeasuredWidth() + i2, view.getMeasuredHeight() + i3);
                }
                float f2 = this.mHeaderMaxDragRate;
                if (f2 < 10.0f) {
                    f2 *= this.mHeaderHeight;
                }
                this.mHeaderHeightStatus = DimensionStatus.CodeExact;
                this.mRefreshHeader.onInitialized(this.mKernel, this.mHeaderHeight, (int) f2);
            } else {
                this.mHeaderHeightStatus = DimensionStatus.CodeExactUnNotify;
            }
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFooterHeight(float f2) {
        return setFooterHeightPx(SmartUtil.dp2px(f2));
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFooterHeightPx(int i) {
        if (i != this.mFooterHeight && this.mFooterHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mFooterHeight = i;
            if (this.mRefreshFooter != null && this.mAttachedToWindow && this.mFooterHeightStatus.notified) {
                SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                if (spinnerStyle != SpinnerStyle.MatchLayout && !spinnerStyle.scale) {
                    View view = this.mRefreshFooter.getView();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : sDefaultMarginLP;
                    view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(Math.max((this.mFooterHeight - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin, 0), BasicMeasure.EXACTLY));
                    int i2 = marginLayoutParams.leftMargin;
                    int measuredHeight = ((marginLayoutParams.topMargin + getMeasuredHeight()) - this.mFooterInsetStart) - (spinnerStyle != SpinnerStyle.Translate ? this.mFooterHeight : 0);
                    view.layout(i2, measuredHeight, view.getMeasuredWidth() + i2, view.getMeasuredHeight() + measuredHeight);
                }
                float f2 = this.mFooterMaxDragRate;
                if (f2 < 10.0f) {
                    f2 *= this.mFooterHeight;
                }
                this.mFooterHeightStatus = DimensionStatus.CodeExact;
                this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) f2);
            } else {
                this.mFooterHeightStatus = DimensionStatus.CodeExactUnNotify;
            }
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setHeaderInsetStart(float f2) {
        this.mHeaderInsetStart = SmartUtil.dp2px(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setHeaderInsetStartPx(int i) {
        this.mHeaderInsetStart = i;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFooterInsetStart(float f2) {
        this.mFooterInsetStart = SmartUtil.dp2px(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFooterInsetStartPx(int i) {
        this.mFooterInsetStart = i;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setDragRate(float f2) {
        this.mDragRate = f2;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setHeaderMaxDragRate(float f2) {
        this.mHeaderMaxDragRate = f2;
        if (this.mRefreshHeader != null && this.mAttachedToWindow) {
            float f3 = this.mHeaderMaxDragRate;
            if (f3 < 10.0f) {
                f3 *= this.mHeaderHeight;
            }
            this.mRefreshHeader.onInitialized(this.mKernel, this.mHeaderHeight, (int) f3);
        } else {
            this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFooterMaxDragRate(float f2) {
        this.mFooterMaxDragRate = f2;
        if (this.mRefreshFooter != null && this.mAttachedToWindow) {
            float f3 = this.mFooterMaxDragRate;
            if (f3 < 10.0f) {
                f3 *= this.mFooterHeight;
            }
            this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) f3);
        } else {
            this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setHeaderTriggerRate(float f2) {
        this.mHeaderTriggerRate = f2;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFooterTriggerRate(float f2) {
        this.mFooterTriggerRate = f2;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setReboundInterpolator(Interpolator interpolator) {
        this.mReboundInterpolator = interpolator;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setReboundDuration(int i) {
        this.mReboundDuration = i;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableLoadMore(boolean z) {
        this.mManualLoadMore = true;
        this.mEnableLoadMore = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableRefresh(boolean z) {
        printAndSave("setEnableRefresh enabled=" + z);
        this.mEnableRefresh = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableHeaderTranslationContent(boolean z) {
        this.mEnableHeaderTranslationContent = z;
        this.mManualHeaderTranslationContent = true;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableFooterTranslationContent(boolean z) {
        this.mEnableFooterTranslationContent = z;
        this.mManualFooterTranslationContent = true;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableAutoLoadMore(boolean z) {
        this.mEnableAutoLoadMore = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableOverScrollBounce(boolean z) {
        this.mEnableOverScrollBounce = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnablePureScrollMode(boolean z) {
        this.mEnablePureScrollMode = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableScrollContentWhenLoaded(boolean z) {
        this.mEnableScrollContentWhenLoaded = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableScrollContentWhenRefreshed(boolean z) {
        this.mEnableScrollContentWhenRefreshed = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mEnableLoadMoreWhenContentNotFull = z;
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            refreshContent.setEnableLoadMoreWhenContentNotFull(z);
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableOverScrollDrag(boolean z) {
        this.mEnableOverScrollDrag = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableFooterFollowWhenNoMoreData(boolean z) {
        this.mEnableFooterFollowWhenNoMoreData = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableClipHeaderWhenFixedBehind(boolean z) {
        this.mEnableClipHeaderWhenFixedBehind = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableClipFooterWhenFixedBehind(boolean z) {
        this.mEnableClipFooterWhenFixedBehind = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setEnableNestedScroll(boolean z) {
        setNestedScrollingEnabled(z);
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFixedHeaderViewId(int i) {
        this.mFixedHeaderViewId = i;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFixedFooterViewId(int i) {
        this.mFixedFooterViewId = i;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setHeaderTranslationViewId(int i) {
        this.mHeaderTranslationViewId = i;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setFooterTranslationViewId(int i) {
        this.mFooterTranslationViewId = i;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setDisableContentWhenRefresh(boolean z) {
        this.mDisableContentWhenRefresh = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setDisableContentWhenLoading(boolean z) {
        this.mDisableContentWhenLoading = z;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setRefreshHeader(RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, 0, 0);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setRefreshHeader(RefreshHeader refreshHeader, int i, int i2) {
        RefreshComponent refreshComponent;
        printAndSave("setRefreshHeader header=" + refreshHeader + ",width=" + i + ",height=" + i2);
        RefreshComponent refreshComponent2 = this.mRefreshHeader;
        if (refreshComponent2 != null) {
            super.removeView(refreshComponent2.getView());
        }
        this.mRefreshHeader = refreshHeader;
        this.mHeaderBackgroundColor = 0;
        this.mHeaderNeedTouchEventWhenRefreshing = false;
        this.mHeaderHeightStatus = DimensionStatus.DefaultUnNotify;
        if (i == 0) {
            i = -1;
        }
        if (i2 == 0) {
            i2 = -2;
        }
        LayoutParams layoutParams = new LayoutParams(i, i2);
        ViewGroup.LayoutParams layoutParams2 = refreshHeader.getView().getLayoutParams();
        if (layoutParams2 instanceof LayoutParams) {
            layoutParams = (LayoutParams) layoutParams2;
        }
        if (this.mRefreshHeader.getSpinnerStyle().front) {
            super.addView(this.mRefreshHeader.getView(), getChildCount(), layoutParams);
        } else {
            super.addView(this.mRefreshHeader.getView(), 0, layoutParams);
        }
        int[] iArr = this.mPrimaryColors;
        if (iArr != null && (refreshComponent = this.mRefreshHeader) != null) {
            refreshComponent.setPrimaryColors(iArr);
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setRefreshFooter(RefreshFooter refreshFooter) {
        return setRefreshFooter(refreshFooter, 0, 0);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setRefreshFooter(RefreshFooter refreshFooter, int i, int i2) {
        RefreshComponent refreshComponent;
        RefreshComponent refreshComponent2 = this.mRefreshFooter;
        if (refreshComponent2 != null) {
            super.removeView(refreshComponent2.getView());
        }
        this.mRefreshFooter = refreshFooter;
        this.mFooterLocked = false;
        this.mFooterBackgroundColor = 0;
        this.mFooterNoMoreDataEffective = false;
        this.mFooterNeedTouchEventWhenLoading = false;
        this.mFooterHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mEnableLoadMore = !this.mManualLoadMore || this.mEnableLoadMore;
        if (i == 0) {
            i = -1;
        }
        if (i2 == 0) {
            i2 = -2;
        }
        LayoutParams layoutParams = new LayoutParams(i, i2);
        ViewGroup.LayoutParams layoutParams2 = refreshFooter.getView().getLayoutParams();
        if (layoutParams2 instanceof LayoutParams) {
            layoutParams = (LayoutParams) layoutParams2;
        }
        if (this.mRefreshFooter.getSpinnerStyle().front) {
            super.addView(this.mRefreshFooter.getView(), getChildCount(), layoutParams);
        } else {
            super.addView(this.mRefreshFooter.getView(), 0, layoutParams);
        }
        int[] iArr = this.mPrimaryColors;
        if (iArr != null && (refreshComponent = this.mRefreshFooter) != null) {
            refreshComponent.setPrimaryColors(iArr);
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setRefreshContent(View view) {
        return setRefreshContent(view, 0, 0);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setRefreshContent(View view, int i, int i2) {
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            super.removeView(refreshContent.getView());
        }
        if (i == 0) {
            i = -1;
        }
        if (i2 == 0) {
            i2 = -1;
        }
        LayoutParams layoutParams = new LayoutParams(i, i2);
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof LayoutParams) {
            layoutParams = (LayoutParams) layoutParams2;
        }
        super.addView(view, getChildCount(), layoutParams);
        this.mRefreshContent = new RefreshContentWrapper(view);
        if (this.mAttachedToWindow) {
            View viewFindViewById = findViewById(this.mFixedHeaderViewId);
            View viewFindViewById2 = findViewById(this.mFixedFooterViewId);
            this.mRefreshContent.setScrollBoundaryDecider(this.mScrollBoundaryDecider);
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.setUpComponent(this.mKernel, viewFindViewById, viewFindViewById2);
        }
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null && refreshComponent.getSpinnerStyle().front) {
            super.bringChildToFront(this.mRefreshHeader.getView());
        }
        RefreshComponent refreshComponent2 = this.mRefreshFooter;
        if (refreshComponent2 != null && refreshComponent2.getSpinnerStyle().front) {
            super.bringChildToFront(this.mRefreshFooter.getView());
        }
        return this;
    }

    public RefreshFooter getRefreshFooter() {
        RefreshComponent refreshComponent = this.mRefreshFooter;
        if (refreshComponent instanceof RefreshFooter) {
            return (RefreshFooter) refreshComponent;
        }
        return null;
    }

    public RefreshHeader getRefreshHeader() {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent instanceof RefreshHeader) {
            return (RefreshHeader) refreshComponent;
        }
        return null;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshState getState() {
        return this.mState;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || !(this.mManualLoadMore || onLoadMoreListener == null);
        return this;
    }

    public RefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.mRefreshListener = onRefreshLoadMoreListener;
        this.mLoadMoreListener = onRefreshLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || !(this.mManualLoadMore || onRefreshLoadMoreListener == null);
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setOnMultiListener(OnMultiListener onMultiListener) {
        this.mOnMultiListener = onMultiListener;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setPrimaryColors(int... iArr) {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null) {
            refreshComponent.setPrimaryColors(iArr);
        }
        RefreshComponent refreshComponent2 = this.mRefreshFooter;
        if (refreshComponent2 != null) {
            refreshComponent2.setPrimaryColors(iArr);
        }
        this.mPrimaryColors = iArr;
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setPrimaryColorsId(int... iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = ContextCompat.getColor(getContext(), iArr[i]);
        }
        setPrimaryColors(iArr2);
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        this.mScrollBoundaryDecider = scrollBoundaryDecider;
        RefreshContent refreshContent = this.mRefreshContent;
        if (refreshContent != null) {
            refreshContent.setScrollBoundaryDecider(scrollBoundaryDecider);
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout setNoMoreData(boolean z) {
        if (this.mState == RefreshState.Refreshing && z) {
            finishRefreshWithNoMoreData();
        } else if (this.mState == RefreshState.Loading && z) {
            finishLoadMoreWithNoMoreData();
        } else if (this.mFooterNoMoreData != z) {
            this.mFooterNoMoreData = z;
            RefreshComponent refreshComponent = this.mRefreshFooter;
            if (refreshComponent instanceof RefreshFooter) {
                if (((RefreshFooter) refreshComponent).setNoMoreData(z)) {
                    this.mFooterNoMoreDataEffective = true;
                    if (this.mFooterNoMoreData && this.mEnableFooterFollowWhenNoMoreData && this.mSpinner > 0 && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && isEnableTranslationContent(this.mEnableRefresh, this.mRefreshHeader)) {
                        this.mRefreshFooter.getView().setTranslationY(this.mSpinner);
                    }
                } else {
                    this.mFooterNoMoreDataEffective = false;
                    new RuntimeException("Footer:" + this.mRefreshFooter + " NoMoreData is not supported.(不支持NoMoreData，请使用[ClassicsFooter]或者[自定义Footer并实现setNoMoreData方法且返回true])").printStackTrace();
                }
            }
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout resetNoMoreData() {
        return setNoMoreData(false);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishRefresh() {
        return finishRefresh(true);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishLoadMore() {
        return finishLoadMore(true);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishRefresh(int i) {
        return finishRefresh(i, true, Boolean.FALSE);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishRefresh(boolean z) {
        if (z) {
            return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16, true, Boolean.FALSE);
        }
        return finishRefresh(0, false, null);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishRefresh(int i, final boolean z, final Boolean bool) {
        printAndSave("finishRefresh delayed=" + i + ",success=" + z + ",noMoreData=" + bool);
        final int i2 = i >> 16;
        int i3 = (i << 16) >> 16;
        Runnable runnable = new Runnable() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.6
            int count = 0;

            @Override // java.lang.Runnable
            public void run() {
                if (this.count == 0) {
                    if (SmartRefreshLayout.this.mState == RefreshState.None && SmartRefreshLayout.this.mViceState == RefreshState.Refreshing) {
                        SmartRefreshLayout.this.mViceState = RefreshState.None;
                    } else if (SmartRefreshLayout.this.reboundAnimator != null && SmartRefreshLayout.this.mState.isHeader && (SmartRefreshLayout.this.mState.isDragging || SmartRefreshLayout.this.mState == RefreshState.RefreshReleased)) {
                        SmartRefreshLayout.this.reboundAnimator.setDuration(0L);
                        SmartRefreshLayout.this.reboundAnimator.cancel();
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        smartRefreshLayout.reboundAnimator = null;
                        if (smartRefreshLayout.mKernel.animSpinner(0) == null) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                        } else {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownCanceled);
                        }
                    } else if (SmartRefreshLayout.this.mState == RefreshState.Refreshing && SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshContent != null) {
                        this.count++;
                        SmartRefreshLayout.this.mHandler.postDelayed(this, i2);
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                        if (bool == Boolean.FALSE) {
                            SmartRefreshLayout.this.setNoMoreData(false);
                        }
                    }
                    if (bool == Boolean.TRUE) {
                        SmartRefreshLayout.this.setNoMoreData(true);
                        return;
                    }
                    return;
                }
                int iOnFinish = SmartRefreshLayout.this.mRefreshHeader.onFinish(SmartRefreshLayout.this, z);
                if (SmartRefreshLayout.this.mOnMultiListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                    SmartRefreshLayout.this.mOnMultiListener.onHeaderFinish((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, z);
                }
                if (iOnFinish < Integer.MAX_VALUE) {
                    if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (SmartRefreshLayout.this.mIsBeingDragged) {
                            SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                            smartRefreshLayout2.mTouchY = smartRefreshLayout2.mLastTouchY;
                            SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                            smartRefreshLayout3.mTouchSpinner = 0;
                            smartRefreshLayout3.mIsBeingDragged = false;
                            SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(jCurrentTimeMillis, jCurrentTimeMillis, 0, smartRefreshLayout3.mLastTouchX, (SmartRefreshLayout.this.mLastTouchY + SmartRefreshLayout.this.mSpinner) - (SmartRefreshLayout.this.mTouchSlop * 2), 0));
                            SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                            SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(jCurrentTimeMillis, jCurrentTimeMillis, 2, smartRefreshLayout4.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + SmartRefreshLayout.this.mSpinner, 0));
                        }
                        if (SmartRefreshLayout.this.mNestedInProgress) {
                            SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                            smartRefreshLayout5.mTotalUnconsumed = 0;
                            SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(jCurrentTimeMillis, jCurrentTimeMillis, 1, smartRefreshLayout5.mLastTouchX, SmartRefreshLayout.this.mLastTouchY, 0));
                            SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                            smartRefreshLayout6.mNestedInProgress = false;
                            smartRefreshLayout6.mTouchSpinner = 0;
                        }
                    }
                    if (SmartRefreshLayout.this.mSpinner > 0) {
                        SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                        ValueAnimator valueAnimatorAnimSpinner = smartRefreshLayout7.animSpinner(0, iOnFinish, smartRefreshLayout7.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                        ValueAnimator.AnimatorUpdateListener animatorUpdateListenerScrollContentWhenFinished = SmartRefreshLayout.this.mEnableScrollContentWhenRefreshed ? SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner) : null;
                        if (valueAnimatorAnimSpinner == null || animatorUpdateListenerScrollContentWhenFinished == null) {
                            return;
                        }
                        valueAnimatorAnimSpinner.addUpdateListener(animatorUpdateListenerScrollContentWhenFinished);
                        return;
                    }
                    if (SmartRefreshLayout.this.mSpinner < 0) {
                        SmartRefreshLayout smartRefreshLayout8 = SmartRefreshLayout.this;
                        smartRefreshLayout8.animSpinner(0, iOnFinish, smartRefreshLayout8.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                    } else {
                        SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                    }
                }
            }
        };
        if (i3 > 0) {
            this.mHandler.postDelayed(runnable, i3);
        } else {
            runnable.run();
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishRefreshWithNoMoreData() {
        return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16, true, Boolean.TRUE);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishLoadMore(int i) {
        return finishLoadMore(i, true, false);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishLoadMore(boolean z) {
        return finishLoadMore(z ? Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16 : 0, z, false);
    }

    /* JADX INFO: renamed from: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout$7, reason: invalid class name */
    class AnonymousClass7 implements Runnable {
        int count = 0;
        final /* synthetic */ int val$more;
        final /* synthetic */ boolean val$noMoreData;
        final /* synthetic */ boolean val$success;

        AnonymousClass7(int i, boolean z, boolean z2) {
            this.val$more = i;
            this.val$noMoreData = z;
            this.val$success = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.count == 0) {
                if (SmartRefreshLayout.this.mState == RefreshState.None && SmartRefreshLayout.this.mViceState == RefreshState.Loading) {
                    SmartRefreshLayout.this.mViceState = RefreshState.None;
                } else if (SmartRefreshLayout.this.reboundAnimator != null && ((SmartRefreshLayout.this.mState.isDragging || SmartRefreshLayout.this.mState == RefreshState.LoadReleased) && SmartRefreshLayout.this.mState.isFooter)) {
                    SmartRefreshLayout.this.reboundAnimator.setDuration(0L);
                    SmartRefreshLayout.this.reboundAnimator.cancel();
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.reboundAnimator = null;
                    if (smartRefreshLayout.mKernel.animSpinner(0) == null) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                    } else {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpCanceled);
                    }
                } else if (SmartRefreshLayout.this.mState == RefreshState.Loading && SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshContent != null) {
                    this.count++;
                    SmartRefreshLayout.this.mHandler.postDelayed(this, this.val$more);
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                    return;
                }
                if (this.val$noMoreData) {
                    SmartRefreshLayout.this.setNoMoreData(true);
                    return;
                }
                return;
            }
            int iOnFinish = SmartRefreshLayout.this.mRefreshFooter.onFinish(SmartRefreshLayout.this, this.val$success);
            if (SmartRefreshLayout.this.mOnMultiListener != null && (SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter)) {
                SmartRefreshLayout.this.mOnMultiListener.onFooterFinish((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, this.val$success);
            }
            if (iOnFinish < Integer.MAX_VALUE) {
                final int iMax = SmartRefreshLayout.this.mSpinner - (this.val$noMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData && SmartRefreshLayout.this.mSpinner < 0 && SmartRefreshLayout.this.mRefreshContent.canLoadMore() ? Math.max(SmartRefreshLayout.this.mSpinner, -SmartRefreshLayout.this.mFooterHeight) : 0);
                if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (SmartRefreshLayout.this.mIsBeingDragged) {
                        SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                        smartRefreshLayout2.mTouchY = smartRefreshLayout2.mLastTouchY;
                        SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                        smartRefreshLayout3.mTouchSpinner = smartRefreshLayout3.mSpinner - iMax;
                        SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                        smartRefreshLayout4.mIsBeingDragged = false;
                        int i = smartRefreshLayout4.mEnableFooterTranslationContent ? iMax : 0;
                        SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                        float f2 = i;
                        SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(jCurrentTimeMillis, jCurrentTimeMillis, 0, smartRefreshLayout5.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f2 + (SmartRefreshLayout.this.mTouchSlop * 2), 0));
                        SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                        SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(jCurrentTimeMillis, jCurrentTimeMillis, 2, smartRefreshLayout6.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f2, 0));
                    }
                    if (SmartRefreshLayout.this.mNestedInProgress) {
                        SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                        smartRefreshLayout7.mTotalUnconsumed = 0;
                        SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(jCurrentTimeMillis, jCurrentTimeMillis, 1, smartRefreshLayout7.mLastTouchX, SmartRefreshLayout.this.mLastTouchY, 0));
                        SmartRefreshLayout smartRefreshLayout8 = SmartRefreshLayout.this;
                        smartRefreshLayout8.mNestedInProgress = false;
                        smartRefreshLayout8.mTouchSpinner = 0;
                    }
                }
                SmartRefreshLayout.this.mHandler.postDelayed(new Runnable() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ValueAnimator.AnimatorUpdateListener animatorUpdateListenerScrollContentWhenFinished;
                        ValueAnimator valueAnimatorAnimSpinner;
                        if (!SmartRefreshLayout.this.mEnableScrollContentWhenLoaded || iMax >= 0) {
                            animatorUpdateListenerScrollContentWhenFinished = null;
                        } else {
                            animatorUpdateListenerScrollContentWhenFinished = SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner);
                            if (animatorUpdateListenerScrollContentWhenFinished != null) {
                                animatorUpdateListenerScrollContentWhenFinished.onAnimationUpdate(ValueAnimator.ofInt(0, 0));
                            }
                        }
                        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.7.1.1
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(Animator animator) {
                                if (animator == null || animator.getDuration() != 0) {
                                    SmartRefreshLayout.this.mFooterLocked = false;
                                    if (AnonymousClass7.this.val$noMoreData) {
                                        SmartRefreshLayout.this.setNoMoreData(true);
                                    }
                                    if (SmartRefreshLayout.this.mState == RefreshState.LoadFinish) {
                                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                    }
                                }
                            }
                        };
                        if (SmartRefreshLayout.this.mSpinner > 0) {
                            valueAnimatorAnimSpinner = SmartRefreshLayout.this.mKernel.animSpinner(0);
                        } else {
                            if (animatorUpdateListenerScrollContentWhenFinished != null || SmartRefreshLayout.this.mSpinner == 0) {
                                if (SmartRefreshLayout.this.reboundAnimator != null) {
                                    SmartRefreshLayout.this.reboundAnimator.setDuration(0L);
                                    SmartRefreshLayout.this.reboundAnimator.cancel();
                                    SmartRefreshLayout.this.reboundAnimator = null;
                                }
                                SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                                SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                            } else if (AnonymousClass7.this.val$noMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData) {
                                if (SmartRefreshLayout.this.mSpinner >= (-SmartRefreshLayout.this.mFooterHeight)) {
                                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                } else {
                                    valueAnimatorAnimSpinner = SmartRefreshLayout.this.mKernel.animSpinner(-SmartRefreshLayout.this.mFooterHeight);
                                }
                            } else {
                                valueAnimatorAnimSpinner = SmartRefreshLayout.this.mKernel.animSpinner(0);
                            }
                            valueAnimatorAnimSpinner = null;
                        }
                        if (valueAnimatorAnimSpinner != null) {
                            valueAnimatorAnimSpinner.addListener(animatorListenerAdapter);
                        } else {
                            animatorListenerAdapter.onAnimationEnd(null);
                        }
                    }
                }, SmartRefreshLayout.this.mSpinner < 0 ? iOnFinish : 0L);
            }
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishLoadMore(int i, boolean z, boolean z2) {
        int i2 = i >> 16;
        int i3 = (i << 16) >> 16;
        AnonymousClass7 anonymousClass7 = new AnonymousClass7(i2, z2, z);
        if (i3 > 0) {
            this.mHandler.postDelayed(anonymousClass7, i3);
        } else {
            anonymousClass7.run();
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout finishLoadMoreWithNoMoreData() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) << 16, true, true);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public RefreshLayout closeHeaderOrFooter() {
        printAndSave("closeHeaderOrFooter() mState=" + this.mState + ",mViceState=" + this.mViceState);
        if (this.mState == RefreshState.None && (this.mViceState == RefreshState.Refreshing || this.mViceState == RefreshState.Loading)) {
            this.mViceState = RefreshState.None;
        }
        if (this.mState == RefreshState.Refreshing) {
            finishRefresh();
        } else if (this.mState == RefreshState.Loading) {
            finishLoadMore();
        } else if (this.mKernel.animSpinner(0) == null) {
            notifyStateChanged(RefreshState.None);
        } else if (this.mState.isHeader) {
            notifyStateChanged(RefreshState.PullDownCanceled);
        } else {
            notifyStateChanged(RefreshState.PullUpCanceled);
        }
        return this;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoRefresh() {
        return autoRefresh(this.mAttachedToWindow ? 0 : 400, this.mReboundDuration, (this.mHeaderMaxDragRate + this.mHeaderTriggerRate) / 2.0f, false);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoRefresh(int i) {
        return autoRefresh(i, this.mReboundDuration, (this.mHeaderMaxDragRate + this.mHeaderTriggerRate) / 2.0f, false);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoRefreshAnimationOnly() {
        return autoRefresh(this.mAttachedToWindow ? 0 : 400, this.mReboundDuration, (this.mHeaderMaxDragRate + this.mHeaderTriggerRate) / 2.0f, true);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoRefresh(int i, final int i2, final float f2, final boolean z) {
        if (this.mState != RefreshState.None || !isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
            return false;
        }
        Runnable runnable = new Runnable() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.8
            @Override // java.lang.Runnable
            public void run() {
                if (SmartRefreshLayout.this.mViceState != RefreshState.Refreshing) {
                    return;
                }
                if (SmartRefreshLayout.this.reboundAnimator != null) {
                    SmartRefreshLayout.this.reboundAnimator.setDuration(0L);
                    SmartRefreshLayout.this.reboundAnimator.cancel();
                    SmartRefreshLayout.this.reboundAnimator = null;
                }
                SmartRefreshLayout.this.mLastTouchX = r0.getMeasuredWidth() / 2.0f;
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
                float f3 = SmartRefreshLayout.this.mHeaderHeight == 0 ? SmartRefreshLayout.this.mHeaderTriggerRate : SmartRefreshLayout.this.mHeaderHeight;
                float f4 = f2;
                if (f4 < 10.0f) {
                    f4 *= f3;
                }
                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                smartRefreshLayout.reboundAnimator = ValueAnimator.ofInt(smartRefreshLayout.mSpinner, (int) f4);
                SmartRefreshLayout.this.reboundAnimator.setDuration(i2);
                SmartRefreshLayout.this.reboundAnimator.setInterpolator(new SmartUtil(SmartUtil.INTERPOLATOR_VISCOUS_FLUID));
                SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.8.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        if (SmartRefreshLayout.this.reboundAnimator == null || SmartRefreshLayout.this.mRefreshHeader == null) {
                            return;
                        }
                        SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.8.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (animator == null || animator.getDuration() != 0) {
                            SmartRefreshLayout.this.reboundAnimator = null;
                            if (SmartRefreshLayout.this.mRefreshHeader != null) {
                                if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToRefresh) {
                                    SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToRefresh);
                                }
                                SmartRefreshLayout.this.setStateRefreshing(!z);
                                return;
                            }
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                        }
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        setViceState(RefreshState.Refreshing);
        if (i > 0) {
            this.mHandler.postDelayed(runnable, i);
            return true;
        }
        runnable.run();
        return true;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoLoadMore() {
        return autoLoadMore(0, this.mReboundDuration, (this.mFooterMaxDragRate + this.mFooterTriggerRate) / 2.0f, false);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoLoadMore(int i) {
        return autoLoadMore(i, this.mReboundDuration, (this.mFooterMaxDragRate + this.mFooterTriggerRate) / 2.0f, false);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoLoadMoreAnimationOnly() {
        return autoLoadMore(0, this.mReboundDuration, (this.mFooterMaxDragRate + this.mFooterTriggerRate) / 2.0f, true);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean autoLoadMore(int i, final int i2, final float f2, final boolean z) {
        if (this.mState != RefreshState.None || !isEnableRefreshOrLoadMore(this.mEnableLoadMore) || this.mFooterNoMoreData) {
            return false;
        }
        Runnable runnable = new Runnable() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.9
            @Override // java.lang.Runnable
            public void run() {
                if (SmartRefreshLayout.this.mViceState != RefreshState.Loading) {
                    return;
                }
                if (SmartRefreshLayout.this.reboundAnimator != null) {
                    SmartRefreshLayout.this.reboundAnimator.setDuration(0L);
                    SmartRefreshLayout.this.reboundAnimator.cancel();
                    SmartRefreshLayout.this.reboundAnimator = null;
                }
                SmartRefreshLayout.this.mLastTouchX = r0.getMeasuredWidth() / 2.0f;
                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
                float f3 = SmartRefreshLayout.this.mFooterHeight == 0 ? SmartRefreshLayout.this.mFooterTriggerRate : SmartRefreshLayout.this.mFooterHeight;
                float f4 = f2;
                if (f4 < 10.0f) {
                    f4 *= f3;
                }
                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                smartRefreshLayout.reboundAnimator = ValueAnimator.ofInt(smartRefreshLayout.mSpinner, -((int) f4));
                SmartRefreshLayout.this.reboundAnimator.setDuration(i2);
                SmartRefreshLayout.this.reboundAnimator.setInterpolator(new SmartUtil(SmartUtil.INTERPOLATOR_VISCOUS_FLUID));
                SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.9.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        if (SmartRefreshLayout.this.reboundAnimator == null || SmartRefreshLayout.this.mRefreshFooter == null) {
                            return;
                        }
                        SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.9.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (animator == null || animator.getDuration() != 0) {
                            SmartRefreshLayout.this.reboundAnimator = null;
                            if (SmartRefreshLayout.this.mRefreshFooter != null) {
                                if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToLoad) {
                                    SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToLoad);
                                }
                                SmartRefreshLayout.this.setStateLoading(!z);
                                return;
                            }
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.None);
                        }
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        setViceState(RefreshState.Loading);
        if (i > 0) {
            this.mHandler.postDelayed(runnable, i);
            return true;
        }
        runnable.run();
        return true;
    }

    public static void setDefaultRefreshHeaderCreator(DefaultRefreshHeaderCreator defaultRefreshHeaderCreator) {
        sHeaderCreator = defaultRefreshHeaderCreator;
    }

    public static void setDefaultRefreshFooterCreator(DefaultRefreshFooterCreator defaultRefreshFooterCreator) {
        sFooterCreator = defaultRefreshFooterCreator;
    }

    public static void setDefaultRefreshInitializer(DefaultRefreshInitializer defaultRefreshInitializer) {
        sRefreshInitializer = defaultRefreshInitializer;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean isRefreshing() {
        return this.mState == RefreshState.Refreshing;
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshLayout
    public boolean isLoading() {
        return this.mState == RefreshState.Loading;
    }

    public class RefreshKernelImpl implements RefreshKernel {
        public RefreshKernelImpl() {
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshLayout getRefreshLayout() {
            return SmartRefreshLayout.this;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshContent getRefreshContent() {
            return SmartRefreshLayout.this.mRefreshContent;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel setState(RefreshState refreshState) {
            switch (refreshState) {
                case None:
                    if (SmartRefreshLayout.this.mState != RefreshState.None && SmartRefreshLayout.this.mSpinner == 0) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                    } else if (SmartRefreshLayout.this.mSpinner != 0) {
                        animSpinner(0);
                    }
                    break;
                case PullDownToRefresh:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        if (smartRefreshLayout.isEnableRefreshOrLoadMore(smartRefreshLayout.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownToRefresh);
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullDownToRefresh);
                    break;
                case PullUpToLoad:
                    SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                    if (smartRefreshLayout2.isEnableRefreshOrLoadMore(smartRefreshLayout2.mEnableLoadMore) && !SmartRefreshLayout.this.mState.isOpening && !SmartRefreshLayout.this.mState.isFinishing && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || !SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpToLoad);
                    } else {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpToLoad);
                    }
                    break;
                case PullDownCanceled:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout3 = SmartRefreshLayout.this;
                        if (smartRefreshLayout3.isEnableRefreshOrLoadMore(smartRefreshLayout3.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownCanceled);
                            setState(RefreshState.None);
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.PullDownCanceled);
                    break;
                case PullUpCanceled:
                    SmartRefreshLayout smartRefreshLayout4 = SmartRefreshLayout.this;
                    if (smartRefreshLayout4.isEnableRefreshOrLoadMore(smartRefreshLayout4.mEnableLoadMore) && !SmartRefreshLayout.this.mState.isOpening && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || !SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpCanceled);
                        setState(RefreshState.None);
                    } else {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpCanceled);
                    }
                    break;
                case ReleaseToRefresh:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout5 = SmartRefreshLayout.this;
                        if (smartRefreshLayout5.isEnableRefreshOrLoadMore(smartRefreshLayout5.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToRefresh);
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToRefresh);
                    break;
                case ReleaseToLoad:
                    SmartRefreshLayout smartRefreshLayout6 = SmartRefreshLayout.this;
                    if (smartRefreshLayout6.isEnableRefreshOrLoadMore(smartRefreshLayout6.mEnableLoadMore) && !SmartRefreshLayout.this.mState.isOpening && !SmartRefreshLayout.this.mState.isFinishing && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || !SmartRefreshLayout.this.mFooterNoMoreDataEffective)) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToLoad);
                    } else {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToLoad);
                    }
                    break;
                case ReleaseToTwoLevel:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout7 = SmartRefreshLayout.this;
                        if (smartRefreshLayout7.isEnableRefreshOrLoadMore(smartRefreshLayout7.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToTwoLevel);
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToTwoLevel);
                    break;
                case RefreshReleased:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout8 = SmartRefreshLayout.this;
                        if (smartRefreshLayout8.isEnableRefreshOrLoadMore(smartRefreshLayout8.mEnableRefresh)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshReleased);
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.RefreshReleased);
                    break;
                case LoadReleased:
                    if (!SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout smartRefreshLayout9 = SmartRefreshLayout.this;
                        if (smartRefreshLayout9.isEnableRefreshOrLoadMore(smartRefreshLayout9.mEnableLoadMore)) {
                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadReleased);
                        }
                    }
                    SmartRefreshLayout.this.setViceState(RefreshState.LoadReleased);
                    break;
                case Refreshing:
                    SmartRefreshLayout.this.setStateRefreshing(true);
                    break;
                case Loading:
                    SmartRefreshLayout.this.setStateLoading(true);
                    break;
                default:
                    SmartRefreshLayout.this.notifyStateChanged(refreshState);
                    break;
            }
            return null;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel startTwoLevel(boolean z) {
            if (z) {
                AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.RefreshKernelImpl.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (animator == null || animator.getDuration() != 0) {
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevel);
                        }
                    }
                };
                ValueAnimator valueAnimatorAnimSpinner = animSpinner(SmartRefreshLayout.this.getMeasuredHeight());
                if (valueAnimatorAnimSpinner != null && valueAnimatorAnimSpinner == SmartRefreshLayout.this.reboundAnimator) {
                    valueAnimatorAnimSpinner.setDuration(SmartRefreshLayout.this.mFloorDuration);
                    valueAnimatorAnimSpinner.addListener(animatorListenerAdapter);
                } else {
                    animatorListenerAdapter.onAnimationEnd(null);
                }
            } else if (animSpinner(0) == null) {
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
            }
            return this;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel finishTwoLevel() {
            if (SmartRefreshLayout.this.mState == RefreshState.TwoLevel) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevelFinish);
                if (SmartRefreshLayout.this.mSpinner == 0) {
                    moveSpinner(0, false);
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                } else {
                    animSpinner(0).setDuration(SmartRefreshLayout.this.mFloorDuration);
                }
            }
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x012d  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0144  */
        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.ido.smartrefresh.layoutkernel.api.RefreshKernel moveSpinner(int r19, boolean r20) {
            /*
                Method dump skipped, instruction units count: 1181
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ido.smartrefresh.layoutkernel.SmartRefreshLayout.RefreshKernelImpl.moveSpinner(int, boolean):com.ido.smartrefresh.layoutkernel.api.RefreshKernel");
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public ValueAnimator animSpinner(int i) {
            SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
            return smartRefreshLayout.animSpinner(i, 0, smartRefreshLayout.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel requestDrawBackgroundFor(RefreshComponent refreshComponent, int i) {
            if (SmartRefreshLayout.this.mPaint == null && i != 0) {
                SmartRefreshLayout.this.mPaint = new Paint();
            }
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderBackgroundColor = i;
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterBackgroundColor = i;
            }
            return this;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel requestNeedTouchEventFor(RefreshComponent refreshComponent, boolean z) {
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderNeedTouchEventWhenRefreshing = z;
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterNeedTouchEventWhenLoading = z;
            }
            return this;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel requestDefaultTranslationContentFor(RefreshComponent refreshComponent, boolean z) {
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (!SmartRefreshLayout.this.mManualHeaderTranslationContent) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mManualHeaderTranslationContent = true;
                    smartRefreshLayout.mEnableHeaderTranslationContent = z;
                }
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter) && !SmartRefreshLayout.this.mManualFooterTranslationContent) {
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.mManualFooterTranslationContent = true;
                smartRefreshLayout2.mEnableFooterTranslationContent = z;
            }
            return this;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel requestRemeasureHeightFor(RefreshComponent refreshComponent) {
            if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (SmartRefreshLayout.this.mHeaderHeightStatus.notified) {
                    SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                    smartRefreshLayout.mHeaderHeightStatus = smartRefreshLayout.mHeaderHeightStatus.unNotify();
                }
            } else if (refreshComponent.equals(SmartRefreshLayout.this.mRefreshFooter) && SmartRefreshLayout.this.mFooterHeightStatus.notified) {
                SmartRefreshLayout smartRefreshLayout2 = SmartRefreshLayout.this;
                smartRefreshLayout2.mFooterHeightStatus = smartRefreshLayout2.mFooterHeightStatus.unNotify();
            }
            return this;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel requestFloorDuration(int i) {
            SmartRefreshLayout.this.mFloorDuration = i;
            return this;
        }

        @Override // com.ido.smartrefresh.layoutkernel.api.RefreshKernel
        public RefreshKernel requestFloorBottomPullUpToCloseRate(float f2) {
            SmartRefreshLayout.this.mTwoLevelBottomPullUpToCloseRate = f2;
            return this;
        }
    }

    protected void printAndSave(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(TAG, str);
    }
}