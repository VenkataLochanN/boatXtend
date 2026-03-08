package com.ido.life.module.user.userdata.weight;

import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.RulerView;

/* JADX INFO: loaded from: classes3.dex */
public class WeightFragment_ViewBinding implements Unbinder {
    private WeightFragment target;
    private View view7f0a02f1;
    private View view7f0a0311;

    public WeightFragment_ViewBinding(final WeightFragment weightFragment, View view) {
        this.target = weightFragment;
        weightFragment.rulerWeight = (RulerView) Utils.findRequiredViewAsType(view, R.id.ruler_weight, "field 'rulerWeight'", RulerView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_forward, "field 'mIvForward' and method 'toUpdateWeight'");
        weightFragment.mIvForward = (ImageButton) Utils.castView(viewFindRequiredView, R.id.iv_forward, "field 'mIvForward'", ImageButton.class);
        this.view7f0a0311 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.weight.WeightFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                weightFragment.toUpdateWeight(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_back_forward, "method 'backForward'");
        this.view7f0a02f1 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.weight.WeightFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                weightFragment.backForward(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WeightFragment weightFragment = this.target;
        if (weightFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        weightFragment.rulerWeight = null;
        weightFragment.mIvForward = null;
        this.view7f0a0311.setOnClickListener(null);
        this.view7f0a0311 = null;
        this.view7f0a02f1.setOnClickListener(null);
        this.view7f0a02f1 = null;
    }
}