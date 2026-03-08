package com.ido.smartrefresh.headerfalsify.falsify;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.ido.smartrefresh.R;
import com.ido.smartrefresh.layoutkernel.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshKernel;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.simple.SimpleComponent;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FalsifyAbstract extends SimpleComponent implements RefreshHeader {
    protected RefreshKernel mRefreshKernel;

    protected FalsifyAbstract(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int iDp2px = SmartUtil.dp2px(5.0f);
            Context context = getContext();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(-858993460);
            paint.setStrokeWidth(SmartUtil.dp2px(1.0f));
            float f2 = iDp2px;
            paint.setPathEffect(new DashPathEffect(new float[]{f2, f2, f2, f2}, 1.0f));
            canvas.drawRect(f2, f2, getWidth() - iDp2px, getBottom() - iDp2px, paint);
            TextView textView = new TextView(context);
            textView.setText(context.getString(R.string.srl_component_falsify, getClass().getSimpleName(), Float.valueOf(SmartUtil.px2dp(getHeight()))));
            textView.setTextColor(-858993460);
            textView.setGravity(17);
            textView.measure(View.MeasureSpec.makeMeasureSpec(getWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(getHeight(), BasicMeasure.EXACTLY));
            textView.layout(0, 0, getWidth(), getHeight());
            textView.draw(canvas);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onReleased(RefreshLayout refreshLayout, int i, int i2) {
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            refreshKernel.setState(RefreshState.None);
            this.mRefreshKernel.setState(RefreshState.RefreshFinish);
        }
    }
}