package com.ido.life.module.home.weight;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class WeightDetailBottomViewHolder extends BaseDetailViewHolder {

    @BindView(R.id.tv_record_weight)
    TextView mTvRecordWeight;

    @BindView(R.id.tv_record_weight_tip)
    TextView mTvRecordWeightTip;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
    }

    public WeightDetailBottomViewHolder(View view) {
        super(view);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvRecordWeight.setText(LanguageUtil.getLanguageText(R.string.record_weight));
    }

    public void updateVisiblity(int i) {
        if (getItemView() != null) {
            getItemView().setVisibility(i);
        }
    }

    public TextView getTvRecordWeight() {
        return this.mTvRecordWeight;
    }

    public TextView getTvRecordWeightTip() {
        return this.mTvRecordWeightTip;
    }
}