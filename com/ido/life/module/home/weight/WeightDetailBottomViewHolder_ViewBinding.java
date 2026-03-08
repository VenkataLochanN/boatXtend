package com.ido.life.module.home.weight;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class WeightDetailBottomViewHolder_ViewBinding implements Unbinder {
    private WeightDetailBottomViewHolder target;

    public WeightDetailBottomViewHolder_ViewBinding(WeightDetailBottomViewHolder weightDetailBottomViewHolder, View view) {
        this.target = weightDetailBottomViewHolder;
        weightDetailBottomViewHolder.mTvRecordWeight = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_record_weight, "field 'mTvRecordWeight'", TextView.class);
        weightDetailBottomViewHolder.mTvRecordWeightTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_record_weight_tip, "field 'mTvRecordWeightTip'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WeightDetailBottomViewHolder weightDetailBottomViewHolder = this.target;
        if (weightDetailBottomViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        weightDetailBottomViewHolder.mTvRecordWeight = null;
        weightDetailBottomViewHolder.mTvRecordWeightTip = null;
    }
}