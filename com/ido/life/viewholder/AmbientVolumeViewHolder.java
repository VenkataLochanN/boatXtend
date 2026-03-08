package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.HookClickListenerHelper;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.AmbientVolumeProgressView;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.enums.AmbientVolumeExposureEnum;
import com.ido.life.module.home.IHomeView;

/* JADX INFO: loaded from: classes3.dex */
public class AmbientVolumeViewHolder extends BaseHomeItemViewHolder {

    @BindView(R.id.ambient_volume)
    AmbientVolumeProgressView mAmbientVolumeProgressView;

    @BindView(R.id.anim_view)
    HomeIconAnimView mAnimView;

    @BindView(R.id.img_empty)
    ImageView mImgEmpty;

    @BindView(R.id.lay_bottom)
    LinearLayout mLayBottom;

    @BindView(R.id.lay_content)
    LinearLayout mLayContent;

    @BindView(R.id.lay_empty)
    LinearLayout mLayEmpty;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tv_volume)
    TextView mTvVolume;

    @BindView(R.id.tv_volume_unit)
    TextView mTvVolumeUnit;

    public AmbientVolumeViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
        HookClickListenerHelper.hookOnClick(this.itemView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return HealthVolumeData.class.getSimpleName();
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        this.itemView.setTag(17);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_5), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_5), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
        }
        HealthVolumeData ambientNoiseData = this.mHomeView.getAmbientNoiseData();
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.ambient_volume));
        this.mAnimView.setAnimatorIcon(R.mipmap.home_volume_anim);
        this.mAnimView.setVisibility(8);
        if (ambientNoiseData == null) {
            printAndSaveLog("显示环境音量无数据UI。");
            this.mTvDate.setVisibility(8);
            this.mLayEmpty.setVisibility(0);
            this.mAmbientVolumeProgressView.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setTag(-1L);
            this.mFirstRefresh = false;
            return;
        }
        this.mTvDate.setVisibility(0);
        this.mLayEmpty.setVisibility(8);
        this.mAmbientVolumeProgressView.setVisibility(0);
        this.mLayBottom.setVisibility(0);
        long jLongValue = this.mAnimView.getTag() instanceof Long ? ((Long) this.mAnimView.getTag()).longValue() : -1L;
        if (jLongValue != -1 && jLongValue != ambientNoiseData.getTimestamp()) {
            printAndSaveLog("显示环境音量有数据UI，同时启动数据发生改变动画。");
            this.mAnimView.setVisibility(0);
            this.mAnimView.startAnimator();
        } else {
            printAndSaveLog("显示环境音量有数据UI，不启动刷剧发生改变动画。");
        }
        this.mAnimView.setTag(Long.valueOf(ambientNoiseData.getTimestamp()));
        this.mTvDate.setText(this.mHomeView.getDateShowByTimeStamp(ambientNoiseData.getTimestamp()));
        this.mTvVolume.setText(String.valueOf(ambientNoiseData.getLatestValue()));
        this.mTvVolumeUnit.setText(LanguageUtil.getLanguageText(R.string.public_volume_unit));
        this.mAmbientVolumeProgressView.setAnimatorDuration(500);
        int iMax = Math.max(Math.min(AmbientVolumeExposureEnum.HIGH.getMaxVolume(), ambientNoiseData.getLatestValue()), AmbientVolumeExposureEnum.LOW.getMinVolume());
        this.mAmbientVolumeProgressView.setCurrProgress(Math.max(0, Math.round(((iMax - 30) + 10) / 10.0f)));
        if (iMax > AmbientVolumeExposureEnum.NORMAL.getMaxVolume()) {
            this.mAmbientVolumeProgressView.setProgressColor(this.itemView.getResources().getColor(R.color.color_FFD127));
        } else {
            this.mAmbientVolumeProgressView.setProgressColor(this.itemView.getResources().getColor(R.color.color_16CE90));
        }
        this.mAmbientVolumeProgressView.invalidateWithAnimator(true);
        this.mFirstRefresh = false;
    }
}