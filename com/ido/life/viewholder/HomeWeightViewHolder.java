package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.HomeWeightChatView;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HomeWeightViewHolder extends BaseHomeItemViewHolder {

    @BindView(R.id.anim_view)
    HomeIconAnimView mAnimView;

    @BindView(R.id.img_empty)
    ImageView mImgEmpty;

    @BindView(R.id.lay_bottom)
    LinearLayout mLayBottom;

    @BindView(R.id.lay_content)
    LinearLayout mLayContent;

    @BindView(R.id.lay_out)
    LinearLayout mLayOut;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tv_weight)
    TextView mTvWeight;

    @BindView(R.id.tv_weight_unit)
    TextView mTvWeightUnit;

    @BindView(R.id.chat_weight)
    HomeWeightChatView mWeightChart;

    public HomeWeightViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        CommonLogUtil.printAndSave("开始绑定体重数据");
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.mine_data_weight));
        this.itemView.setTag(11);
        this.mTvDate.setTag(null);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        }
        List<WeightItemBean> weightList = this.mHomeView.getWeightList();
        if (weightList == null || weightList.size() == 0) {
            this.mHasData = false;
            this.mTvDate.setVisibility(8);
            this.mLayContent.setGravity(80);
            this.mImgEmpty.setVisibility(0);
            this.mWeightChart.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setVisibility(8);
        } else {
            this.mHasData = true;
            this.mTvDate.setVisibility(0);
            this.mLayContent.setGravity(17);
            this.mImgEmpty.setVisibility(8);
            this.mWeightChart.setVisibility(0);
            this.mLayBottom.setVisibility(0);
            this.mAnimView.setVisibility(8);
            WeightItemBean weightItemBean = weightList.get(weightList.size() - 1);
            this.mTvDate.setText(this.mHomeView.getDateShowByTimeStamp(weightItemBean.getTimeStamp()));
            this.mTvDate.setTag(Long.valueOf(weightItemBean.getTimeStamp()));
            if (DateUtil.isToday(weightItemBean.getTimeStamp())) {
                this.mHomeView.startUpdateTime();
            }
            if (RunTimeUtil.getInstance().getUnitSet() == 1) {
                this.mTvWeight.setText(String.format("%.1f", Float.valueOf(weightItemBean.getTotalWeight())));
                this.mTvWeightUnit.setText(LanguageUtil.getLanguageText(R.string.weight_kg_unit_short));
            } else {
                this.mTvWeight.setText(String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(Math.round(UnitUtil.getKg2Pound(weightItemBean.getTotalWeight())))));
                this.mTvWeightUnit.setText(LanguageUtil.getLanguageText(R.string.weight_pound_unit_short));
            }
            ArrayList arrayList = new ArrayList();
            Iterator<WeightItemBean> it = weightList.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf((int) it.next().getTotalWeight()));
            }
            this.mWeightChart.setWeightList(arrayList);
            this.mWeightChart.invalidate();
            this.mAnimView.setTag(Long.valueOf(weightItemBean.getTimeStamp()));
        }
        this.mFirstRefresh = false;
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return WeightItemBean.class.getSimpleName();
    }
}