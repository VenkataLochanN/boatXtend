package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.DateUtil;

/* JADX INFO: loaded from: classes3.dex */
public class HomeSleepViewHolder extends BaseHomeItemViewHolder {

    @BindView(R.id.anim_view)
    HomeIconAnimView mAnimView;

    @BindView(R.id.img_alive)
    ImageView mImgAlive;

    @BindView(R.id.img_deep_sleep)
    ImageView mImgDeepSleep;

    @BindView(R.id.img_empty)
    ImageView mImgEmpty;

    @BindView(R.id.img_fast)
    ImageView mImgFast;

    @BindView(R.id.img_sleep)
    ImageView mImgSleep;

    @BindView(R.id.lay_bottom)
    LinearLayout mLayBottom;

    @BindView(R.id.lay_content)
    LinearLayout mLayContent;

    @BindView(R.id.lay_out)
    LinearLayout mLayOut;

    @BindView(R.id.lay_sleep)
    ConstraintLayout mLaySleep;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    @BindView(R.id.tv_hour)
    TextView mTvHour;

    @BindView(R.id.tv_hour_unit)
    TextView mTvHourUnit;

    @BindView(R.id.tv_minute)
    TextView mTvMinute;

    @BindView(R.id.tv_minute_unit)
    TextView mTvMinuteUnit;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public HomeSleepViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout.LayoutParams layoutParams2;
        LinearLayout.LayoutParams layoutParams3;
        LinearLayout.LayoutParams layoutParams4;
        CommonLogUtil.printAndSave("开始绑定睡眠数据");
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.home_card_sleep_title));
        this.itemView.setTag(7);
        this.mLayOut.setEnabled(this.mHomeView.hasSleepData());
        this.mTvDate.setTag(null);
        ServerSleepDayData sleepData = this.mHomeView.getSleepData();
        this.mAnimView.setAnimatorIcon(R.mipmap.home_sleep_anim_icon);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        }
        if (sleepData == null) {
            this.mHasData = false;
            this.mTvDate.setVisibility(8);
            this.mLayContent.setGravity(80);
            this.mImgEmpty.setVisibility(0);
            this.mLaySleep.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setVisibility(8);
        } else {
            this.mHasData = true;
            if (!this.mFirstRefresh && !sleepData.getUploaded()) {
                Object tag = this.mAnimView.getTag();
                if (tag == null || ((Long) tag).longValue() != sleepData.getTimestamp()) {
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
            this.mLaySleep.setVisibility(0);
            this.mLayBottom.setVisibility(0);
            this.mAnimView.setTag(Long.valueOf(sleepData.getTimestamp()));
            try {
                long time = DateUtil.string2Date(sleepData.getEndTime(), "yyyy-MM-dd HH:mm:ss").getTime();
                this.mTvDate.setText(this.mHomeView.getDateShowByTimeStamp(time));
                this.mTvDate.setTag(Long.valueOf(time));
                if (DateUtil.isToday(time)) {
                    this.mHomeView.startUpdateTime();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                this.mTvDate.setText("");
            }
            this.mTvHourUnit.setText(LanguageUtil.getLanguageText(R.string.public_time_hour));
            this.mTvMinuteUnit.setText(LanguageUtil.getLanguageText(R.string.public_time_minute));
            this.mTvHour.setText(String.valueOf(sleepData.getTotalSeconds() / 3600));
            this.mTvMinute.setText(String.valueOf((sleepData.getTotalSeconds() % 3600) / 60));
            if (this.mImgAlive.getLayoutParams() != null) {
                layoutParams = (LinearLayout.LayoutParams) this.mImgAlive.getLayoutParams();
            } else {
                layoutParams = new LinearLayout.LayoutParams(-2, -1);
                layoutParams.width = 0;
            }
            if (this.mImgFast.getLayoutParams() != null) {
                layoutParams2 = (LinearLayout.LayoutParams) this.mImgFast.getLayoutParams();
            } else {
                layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams2.width = 0;
            }
            if (this.mImgSleep.getLayoutParams() != null) {
                layoutParams3 = (LinearLayout.LayoutParams) this.mImgSleep.getLayoutParams();
            } else {
                layoutParams3 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams3.width = 0;
            }
            if (this.mImgDeepSleep.getLayoutParams() != null) {
                layoutParams4 = (LinearLayout.LayoutParams) this.mImgDeepSleep.getLayoutParams();
            } else {
                layoutParams4 = new LinearLayout.LayoutParams(-2, -1);
            }
            layoutParams.weight = sleepData.getAwakeRatio();
            layoutParams2.weight = sleepData.getEyeMovementRatio();
            layoutParams3.weight = sleepData.getLightlyRatio();
            layoutParams4.weight = sleepData.getDeeplyRatio();
            this.mImgAlive.setLayoutParams(layoutParams);
            this.mImgFast.setLayoutParams(layoutParams2);
            this.mImgSleep.setLayoutParams(layoutParams3);
            this.mImgDeepSleep.setLayoutParams(layoutParams4);
        }
        this.mFirstRefresh = false;
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return ServerSleepDayData.class.getSimpleName();
    }
}