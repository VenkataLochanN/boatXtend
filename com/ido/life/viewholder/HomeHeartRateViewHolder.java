package com.ido.life.viewholder;

import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.charter.HeartRateCubicChartBar;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.DateUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HomeHeartRateViewHolder extends BaseHomeItemViewHolder {

    @BindView(R.id.anim_view)
    HomeIconAnimView mAnimView;

    @BindView(R.id.chat_heart)
    HeartRateCubicChartBar mHeartRateChart;

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

    @BindView(R.id.tv_heart_rate)
    TextView mTvHeartRate;

    @BindView(R.id.tv_heart_rate_unit)
    TextView mTvHeartRateUnit;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public HomeHeartRateViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        CommonLogUtil.printAndSave("开始绑定心率数据");
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.home_card_heartbeat_title));
        this.itemView.setTag(8);
        this.mLayOut.setEnabled(this.mHomeView.hasHeartRate());
        this.mTvDate.setTag(null);
        this.mAnimView.setAnimatorIcon(R.mipmap.home_heart_rate_anim_icon);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        }
        Pair<ServerHeartRateDayData, List<BaseCharBean>> heartRateData = this.mHomeView.getHeartRateData();
        if (heartRateData == null || heartRateData.first == null || heartRateData.second == null || ((List) heartRateData.second).size() == 0) {
            this.mHasData = false;
            this.mTvDate.setVisibility(8);
            this.mLayContent.setGravity(80);
            this.mImgEmpty.setVisibility(0);
            this.mHeartRateChart.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setVisibility(8);
        } else {
            this.mHasData = true;
            if (!this.mFirstRefresh && !((ServerHeartRateDayData) heartRateData.first).getUploadSuccess()) {
                Object tag = this.mAnimView.getTag();
                if (tag == null || ((Long) tag).longValue() != ((ServerHeartRateDayData) heartRateData.first).getTimestamp()) {
                    this.mAnimView.stopAnimator();
                    this.mAnimView.setVisibility(0);
                    this.mAnimView.startAnimator();
                } else {
                    this.mAnimView.setVisibility(8);
                }
            } else {
                this.mAnimView.setVisibility(8);
            }
            this.mTvDate.setVisibility(0);
            this.mLayContent.setGravity(17);
            this.mImgEmpty.setVisibility(8);
            this.mHeartRateChart.setVisibility(0);
            this.mLayBottom.setVisibility(0);
            this.mTvHeartRate.setText(String.valueOf(((ServerHeartRateDayData) heartRateData.first).getLatestValue()));
            this.mTvHeartRateUnit.setText(LanguageUtil.getLanguageText(R.string.device_heart_rate_unit));
            this.mHeartRateChart.setXMinValue(1);
            this.mHeartRateChart.setXMaxValue(144);
            this.mHeartRateChart.setYMinValue(20.0f);
            this.mHeartRateChart.setYMaxValue(220.0f);
            this.mHeartRateChart.setList((List) heartRateData.second);
            this.mTvDate.setText(this.mHomeView.getDateShowByTimeStamp(((ServerHeartRateDayData) heartRateData.first).getTimestamp()));
            this.mTvDate.setTag(Long.valueOf(((ServerHeartRateDayData) heartRateData.first).getTimestamp()));
            this.mAnimView.setTag(Long.valueOf(((ServerHeartRateDayData) heartRateData.first).getTimestamp()));
            if (DateUtil.isToday(((ServerHeartRateDayData) heartRateData.first).getTimestamp())) {
                this.mHomeView.startUpdateTime();
            }
            this.mHeartRateChart.refreshChart(this.mShowAnimator);
        }
        this.mFirstRefresh = false;
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return ServerHeartRateDayData.class.getSimpleName();
    }
}