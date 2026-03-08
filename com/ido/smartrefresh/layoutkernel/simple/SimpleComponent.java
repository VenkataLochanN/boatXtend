package com.ido.smartrefresh.layoutkernel.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.ido.smartrefresh.layoutkernel.SmartRefreshLayout;
import com.ido.smartrefresh.layoutkernel.api.RefreshComponent;
import com.ido.smartrefresh.layoutkernel.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshKernel;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SimpleComponent extends RelativeLayout implements RefreshComponent {
    protected SpinnerStyle mSpinnerStyle;
    protected RefreshComponent mWrappedInternal;
    protected View mWrappedView;

    /* JADX WARN: Multi-variable type inference failed */
    protected SimpleComponent(View view) {
        this(view, view instanceof RefreshComponent ? (RefreshComponent) view : null);
    }

    protected SimpleComponent(View view, RefreshComponent refreshComponent) {
        super(view.getContext(), null, 0);
        this.mWrappedView = view;
        this.mWrappedInternal = refreshComponent;
        if (this instanceof RefreshFooter) {
            RefreshComponent refreshComponent2 = this.mWrappedInternal;
            if ((refreshComponent2 instanceof RefreshHeader) && refreshComponent2.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                refreshComponent.getView().setScaleY(-1.0f);
                return;
            }
        }
        if (this instanceof RefreshHeader) {
            RefreshComponent refreshComponent3 = this.mWrappedInternal;
            if ((refreshComponent3 instanceof RefreshFooter) && refreshComponent3.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                refreshComponent.getView().setScaleY(-1.0f);
            }
        }
    }

    protected SimpleComponent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        return (obj instanceof RefreshComponent) && getView() == ((RefreshComponent) obj).getView();
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public View getView() {
        View view = this.mWrappedView;
        return view == null ? this : view;
    }

    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent == null || refreshComponent == this) {
            return 0;
        }
        return refreshComponent.onFinish(refreshLayout, z);
    }

    public void setPrimaryColors(int... iArr) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent == null || refreshComponent == this) {
            return;
        }
        refreshComponent.setPrimaryColors(iArr);
    }

    @Override // com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public SpinnerStyle getSpinnerStyle() {
        SpinnerStyle spinnerStyle = this.mSpinnerStyle;
        if (spinnerStyle != null) {
            return spinnerStyle;
        }
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent != null && refreshComponent != this) {
            return refreshComponent.getSpinnerStyle();
        }
        View view = this.mWrappedView;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof SmartRefreshLayout.LayoutParams) {
                this.mSpinnerStyle = ((SmartRefreshLayout.LayoutParams) layoutParams).spinnerStyle;
                SpinnerStyle spinnerStyle2 = this.mSpinnerStyle;
                if (spinnerStyle2 != null) {
                    return spinnerStyle2;
                }
            }
            if (layoutParams != null && (layoutParams.height == 0 || layoutParams.height == -1)) {
                for (SpinnerStyle spinnerStyle3 : SpinnerStyle.values) {
                    if (spinnerStyle3.scale) {
                        this.mSpinnerStyle = spinnerStyle3;
                        return spinnerStyle3;
                    }
                }
            }
        }
        SpinnerStyle spinnerStyle4 = SpinnerStyle.Translate;
        this.mSpinnerStyle = spinnerStyle4;
        return spinnerStyle4;
    }

    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent != null && refreshComponent != this) {
            refreshComponent.onInitialized(refreshKernel, i, i2);
            return;
        }
        View view = this.mWrappedView;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof SmartRefreshLayout.LayoutParams) {
                refreshKernel.requestDrawBackgroundFor(this, ((SmartRefreshLayout.LayoutParams) layoutParams).backgroundColor);
            }
        }
    }

    public boolean isSupportHorizontalDrag() {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        return (refreshComponent == null || refreshComponent == this || !refreshComponent.isSupportHorizontalDrag()) ? false : true;
    }

    public void onHorizontalDrag(float f2, int i, int i2) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent == null || refreshComponent == this) {
            return;
        }
        refreshComponent.onHorizontalDrag(f2, i, i2);
    }

    public void onMoving(boolean z, float f2, int i, int i2, int i3) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent == null || refreshComponent == this) {
            return;
        }
        refreshComponent.onMoving(z, f2, i, i2, i3);
    }

    public void onReleased(RefreshLayout refreshLayout, int i, int i2) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent == null || refreshComponent == this) {
            return;
        }
        refreshComponent.onReleased(refreshLayout, i, i2);
    }

    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent == null || refreshComponent == this) {
            return;
        }
        refreshComponent.onStartAnimator(refreshLayout, i, i2);
    }

    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        if (refreshComponent == null || refreshComponent == this) {
            return;
        }
        if ((this instanceof RefreshFooter) && (refreshComponent instanceof RefreshHeader)) {
            if (refreshState.isFooter) {
                refreshState = refreshState.toHeader();
            }
            if (refreshState2.isFooter) {
                refreshState2 = refreshState2.toHeader();
            }
        } else if ((this instanceof RefreshHeader) && (this.mWrappedInternal instanceof RefreshFooter)) {
            if (refreshState.isHeader) {
                refreshState = refreshState.toFooter();
            }
            if (refreshState2.isHeader) {
                refreshState2 = refreshState2.toFooter();
            }
        }
        RefreshComponent refreshComponent2 = this.mWrappedInternal;
        if (refreshComponent2 != null) {
            refreshComponent2.onStateChanged(refreshLayout, refreshState, refreshState2);
        }
    }

    public boolean setNoMoreData(boolean z) {
        RefreshComponent refreshComponent = this.mWrappedInternal;
        return (refreshComponent instanceof RefreshFooter) && ((RefreshFooter) refreshComponent).setNoMoreData(z);
    }
}