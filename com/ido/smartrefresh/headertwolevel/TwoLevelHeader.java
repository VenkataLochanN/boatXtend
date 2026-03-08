package com.ido.smartrefresh.headertwolevel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.view.NestedScrollingParent2;
import com.ido.smartrefresh.R;
import com.ido.smartrefresh.headerclassics.ClassicsHeader;
import com.ido.smartrefresh.headertwolevel.listener.OnTwoLevelListener;
import com.ido.smartrefresh.layoutkernel.api.RefreshComponent;
import com.ido.smartrefresh.layoutkernel.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshKernel;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import com.ido.smartrefresh.layoutkernel.simple.SimpleComponent;

/* JADX INFO: loaded from: classes3.dex */
public class TwoLevelHeader extends SimpleComponent implements RefreshHeader, NestedScrollingParent2 {
    protected float mBottomPullUpToCloseRate;
    protected boolean mEnablePullToCloseTwoLevel;
    protected boolean mEnableRefresh;
    protected boolean mEnableTwoLevel;
    protected int mFloorDuration;
    protected float mFloorRate;
    protected int mHeaderHeight;
    protected float mMaxRate;
    private int mNestedScrollAxes;
    protected float mPercent;
    protected RefreshComponent mRefreshHeader;
    protected RefreshKernel mRefreshKernel;
    protected float mRefreshRate;
    protected int mSpinner;
    protected OnTwoLevelListener mTwoLevelListener;

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view, int i) {
    }

    public TwoLevelHeader(Context context) {
        this(context, null);
    }

    public TwoLevelHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mPercent = 0.0f;
        this.mMaxRate = 2.5f;
        this.mFloorRate = 1.9f;
        this.mRefreshRate = 1.0f;
        this.mBottomPullUpToCloseRate = 0.16666667f;
        this.mEnableRefresh = true;
        this.mEnableTwoLevel = true;
        this.mEnablePullToCloseTwoLevel = true;
        this.mFloorDuration = 1000;
        this.mNestedScrollAxes = 0;
        this.mSpinnerStyle = SpinnerStyle.FixedBehind;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TwoLevelHeader);
        this.mMaxRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.TwoLevelHeader_srlMaxRate, this.mMaxRate);
        this.mFloorRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.TwoLevelHeader_srlFloorRate, this.mFloorRate);
        this.mRefreshRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.TwoLevelHeader_srlRefreshRate, this.mRefreshRate);
        this.mFloorDuration = typedArrayObtainStyledAttributes.getInt(R.styleable.TwoLevelHeader_srlFloorDuration, this.mFloorDuration);
        this.mEnableRefresh = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TwoLevelHeader_srlEnableRefresh2, this.mEnableRefresh);
        this.mEnableTwoLevel = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TwoLevelHeader_srlEnableTwoLevel, this.mEnableTwoLevel);
        this.mBottomPullUpToCloseRate = typedArrayObtainStyledAttributes.getFloat(R.styleable.TwoLevelHeader_srlBottomPullUpToCloseRate, this.mBottomPullUpToCloseRate);
        this.mEnablePullToCloseTwoLevel = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TwoLevelHeader_srlEnablePullToCloseTwoLevel, this.mEnablePullToCloseTwoLevel);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            View childAt = getChildAt(i);
            if (childAt instanceof RefreshHeader) {
                this.mRefreshHeader = (RefreshHeader) childAt;
                this.mWrappedInternal = (RefreshComponent) childAt;
                bringChildToFront(childAt);
                break;
            }
            i++;
        }
        if (this.mRefreshHeader == null) {
            setRefreshHeader(new ClassicsHeader(getContext()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSpinnerStyle = SpinnerStyle.MatchLayout;
        if (this.mRefreshHeader == null) {
            setRefreshHeader(new ClassicsHeader(getContext()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mSpinnerStyle = SpinnerStyle.FixedBehind;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null) {
            if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                refreshComponent.getView().measure(i, i2);
                super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i), refreshComponent.getView().getMeasuredHeight());
                return;
            }
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, i2);
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent
    public boolean equals(Object obj) {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        return (refreshComponent != null && refreshComponent.equals(obj)) || super.equals(obj);
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent == null) {
            return;
        }
        if (((i2 + i) * 1.0f) / i != this.mMaxRate && this.mHeaderHeight == 0) {
            this.mHeaderHeight = i;
            this.mRefreshHeader = null;
            refreshKernel.getRefreshLayout().setHeaderMaxDragRate(this.mMaxRate);
            this.mRefreshHeader = refreshComponent;
        }
        if (this.mRefreshKernel == null && refreshComponent.getSpinnerStyle() == SpinnerStyle.Translate && !isInEditMode()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) refreshComponent.getView().getLayoutParams();
            marginLayoutParams.topMargin -= i;
            refreshComponent.getView().setLayoutParams(marginLayoutParams);
        }
        this.mHeaderHeight = i;
        this.mRefreshKernel = refreshKernel;
        refreshKernel.requestFloorDuration(this.mFloorDuration);
        refreshKernel.requestFloorBottomPullUpToCloseRate(this.mBottomPullUpToCloseRate);
        refreshKernel.requestNeedTouchEventFor(this, !this.mEnablePullToCloseTwoLevel);
        refreshComponent.onInitialized(refreshKernel, i, i2);
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (refreshComponent != null) {
            if (refreshState2 == RefreshState.ReleaseToRefresh && !this.mEnableRefresh) {
                refreshState2 = RefreshState.PullDownToRefresh;
            }
            refreshComponent.onStateChanged(refreshLayout, refreshState, refreshState2);
            int i = AnonymousClass1.$SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[refreshState2.ordinal()];
            boolean z = true;
            if (i == 1) {
                if (refreshComponent.getView() != this) {
                    refreshComponent.getView().animate().alpha(0.0f).setDuration(this.mFloorDuration / 2);
                }
                RefreshKernel refreshKernel = this.mRefreshKernel;
                if (refreshKernel != null) {
                    OnTwoLevelListener onTwoLevelListener = this.mTwoLevelListener;
                    if (onTwoLevelListener != null && !onTwoLevelListener.onTwoLevel(refreshLayout)) {
                        z = false;
                    }
                    refreshKernel.startTwoLevel(z);
                    return;
                }
                return;
            }
            if (i != 2) {
                if (i == 3) {
                    if (refreshComponent.getView() != this) {
                        refreshComponent.getView().animate().alpha(1.0f).setDuration(this.mFloorDuration / 2);
                    }
                } else if (i == 4 && refreshComponent.getView().getAlpha() == 0.0f && refreshComponent.getView() != this) {
                    refreshComponent.getView().setAlpha(1.0f);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.smartrefresh.headertwolevel.TwoLevelHeader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState = new int[RefreshState.values().length];

        static {
            try {
                $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[RefreshState.TwoLevelReleased.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[RefreshState.TwoLevel.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[RefreshState.TwoLevelFinish.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[RefreshState.PullDownToRefresh.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onMoving(boolean z, float f2, int i, int i2, int i3) {
        moveSpinner(i);
        RefreshComponent refreshComponent = this.mRefreshHeader;
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshComponent != null) {
            refreshComponent.onMoving(z, f2, i, i2, i3);
        }
        if (z) {
            float f3 = this.mPercent;
            float f4 = this.mFloorRate;
            if (f3 < f4 && f2 >= f4 && this.mEnableTwoLevel) {
                refreshKernel.setState(RefreshState.ReleaseToTwoLevel);
            } else if (this.mPercent >= this.mFloorRate && f2 < this.mRefreshRate) {
                refreshKernel.setState(RefreshState.PullDownToRefresh);
            } else {
                float f5 = this.mPercent;
                float f6 = this.mFloorRate;
                if (f5 >= f6 && f2 < f6 && this.mEnableRefresh) {
                    refreshKernel.setState(RefreshState.ReleaseToRefresh);
                } else if (!this.mEnableRefresh && refreshKernel.getRefreshLayout().getState() != RefreshState.ReleaseToTwoLevel) {
                    refreshKernel.setState(RefreshState.PullDownToRefresh);
                }
            }
            this.mPercent = f2;
        }
    }

    protected void moveSpinner(int i) {
        RefreshComponent refreshComponent = this.mRefreshHeader;
        if (this.mSpinner == i || refreshComponent == null) {
            return;
        }
        this.mSpinner = i;
        SpinnerStyle spinnerStyle = refreshComponent.getSpinnerStyle();
        if (spinnerStyle == SpinnerStyle.Translate) {
            refreshComponent.getView().setTranslationY(i);
        } else if (spinnerStyle.scale) {
            View view = refreshComponent.getView();
            view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getTop() + Math.max(0, i));
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mNestedScrollAxes = i;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mNestedScrollAxes;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        this.mNestedScrollAxes = i;
    }

    public TwoLevelHeader setRefreshHeader(RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, 0, 0);
    }

    public TwoLevelHeader setRefreshHeader(RefreshHeader refreshHeader, int i, int i2) {
        if (refreshHeader != null) {
            if (i == 0) {
                i = -1;
            }
            if (i2 == 0) {
                i2 = -2;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i2);
            ViewGroup.LayoutParams layoutParams2 = refreshHeader.getView().getLayoutParams();
            if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                layoutParams = (RelativeLayout.LayoutParams) layoutParams2;
            }
            RefreshComponent refreshComponent = this.mRefreshHeader;
            if (refreshComponent != null) {
                removeView(refreshComponent.getView());
            }
            if (refreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                addView(refreshHeader.getView(), 0, layoutParams);
            } else {
                addView(refreshHeader.getView(), getChildCount(), layoutParams);
            }
            this.mRefreshHeader = refreshHeader;
            this.mWrappedInternal = refreshHeader;
        }
        return this;
    }

    public TwoLevelHeader setMaxRate(float f2) {
        if (this.mMaxRate != f2) {
            this.mMaxRate = f2;
            RefreshKernel refreshKernel = this.mRefreshKernel;
            if (refreshKernel != null) {
                this.mHeaderHeight = 0;
                refreshKernel.getRefreshLayout().setHeaderMaxDragRate(this.mMaxRate);
            }
        }
        return this;
    }

    public TwoLevelHeader setEnablePullToCloseTwoLevel(boolean z) {
        RefreshKernel refreshKernel = this.mRefreshKernel;
        this.mEnablePullToCloseTwoLevel = z;
        if (refreshKernel != null) {
            refreshKernel.requestNeedTouchEventFor(this, !z);
        }
        return this;
    }

    public TwoLevelHeader setEnableRefresh(boolean z) {
        this.mEnableRefresh = z;
        return this;
    }

    public TwoLevelHeader setFloorRate(float f2) {
        this.mFloorRate = f2;
        return this;
    }

    public TwoLevelHeader setRefreshRate(float f2) {
        this.mRefreshRate = f2;
        return this;
    }

    public TwoLevelHeader setEnableTwoLevel(boolean z) {
        this.mEnableTwoLevel = z;
        return this;
    }

    public TwoLevelHeader setFloorDuration(int i) {
        this.mFloorDuration = i;
        return this;
    }

    public TwoLevelHeader setBottomPullUpToCloseRate(float f2) {
        this.mBottomPullUpToCloseRate = f2;
        return this;
    }

    public TwoLevelHeader setOnTwoLevelListener(OnTwoLevelListener onTwoLevelListener) {
        this.mTwoLevelListener = onTwoLevelListener;
        return this;
    }

    public TwoLevelHeader finishTwoLevel() {
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            refreshKernel.finishTwoLevel();
        }
        return this;
    }

    public TwoLevelHeader openTwoLevel(boolean z) {
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            OnTwoLevelListener onTwoLevelListener = this.mTwoLevelListener;
            refreshKernel.startTwoLevel(!z || onTwoLevelListener == null || onTwoLevelListener.onTwoLevel(refreshKernel.getRefreshLayout()));
        }
        return this;
    }
}