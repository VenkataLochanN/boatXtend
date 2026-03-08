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
import com.ido.life.bean.BarChartPoint;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.charter.BarChartBar;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.enums.PressureEnum;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.DateUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HomePressureViewHolder extends BaseHomeItemViewHolder {

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

    @BindView(R.id.chat_presure)
    BarChartBar mPresureChart;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    @BindView(R.id.tv_pressure)
    TextView mTvPressure;

    @BindView(R.id.tv_pressure_state)
    TextView mTvPressureState;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public HomePressureViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        CommonLogUtil.printAndSave("开始绑定压力数据");
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.home_pressure_title));
        this.itemView.setTag(9);
        this.mAnimView.setAnimatorIcon(R.mipmap.home_pressure_anim_icon);
        this.mTvDate.setTag(null);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        }
        Pair<Pair<Long, Integer>, Pair<Boolean, List<BarChartPoint>>> healthPressure = this.mHomeView.getHealthPressure();
        if (healthPressure == null || healthPressure.first == null || healthPressure.second == null || ((Pair) healthPressure.second).second == null || ((List) ((Pair) healthPressure.second).second).size() == 0) {
            this.mHasData = false;
            this.mLayOut.setEnabled(false);
            this.mTvDate.setVisibility(8);
            this.mLayContent.setGravity(80);
            this.mImgEmpty.setVisibility(0);
            this.mPresureChart.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setVisibility(8);
        } else {
            boolean z = true;
            this.mHasData = true;
            this.mLayOut.setEnabled(true);
            if (!this.mFirstRefresh && ((Boolean) ((Pair) healthPressure.second).first).booleanValue()) {
                Object tag = this.mAnimView.getTag();
                if (tag != null && ((Long) tag).longValue() == ((Long) ((Pair) healthPressure.first).first).longValue()) {
                    z = false;
                }
                if (z) {
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
            this.mPresureChart.setVisibility(0);
            this.mLayBottom.setVisibility(0);
            this.mTvDate.setText(this.mHomeView.getDateShowByTimeStamp(((Long) ((Pair) healthPressure.first).first).longValue()));
            this.mTvDate.setTag(((Pair) healthPressure.first).first);
            this.mAnimView.setTag(((Pair) healthPressure.first).first);
            if (DateUtil.isToday(((Long) ((Pair) healthPressure.first).first).longValue())) {
                this.mHomeView.startUpdateTime();
            }
            int iIntValue = ((Integer) ((Pair) healthPressure.first).second).intValue();
            this.mTvPressure.setText(String.valueOf(iIntValue));
            PressureEnum pressureEnumByValue = PressureEnum.getPressureEnumByValue(iIntValue);
            if (pressureEnumByValue == PressureEnum.RELAX) {
                this.mTvPressureState.setText(LanguageUtil.getLanguageText(R.string.home_pressure_relax));
            } else if (pressureEnumByValue == PressureEnum.NORMAL) {
                this.mTvPressureState.setText(LanguageUtil.getLanguageText(R.string.home_pressure_normal));
            } else if (pressureEnumByValue == PressureEnum.MEDIUM) {
                this.mTvPressureState.setText(LanguageUtil.getLanguageText(R.string.home_pressure_middle));
            } else {
                this.mTvPressureState.setText(LanguageUtil.getLanguageText(R.string.home_pressure_high));
            }
            this.mPresureChart.setXMaxValue(15);
            this.mPresureChart.setXMinValue(0);
            this.mPresureChart.setYMinValue(PressureEnum.RELAX.Min);
            this.mPresureChart.setYMaxValue(PressureEnum.HIGH.Max);
            this.mPresureChart.setList((List) ((Pair) healthPressure.second).second);
            this.mPresureChart.refreshChart(this.mShowAnimator);
        }
        this.mFirstRefresh = false;
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return HealthPressure.class.getSimpleName();
    }
}