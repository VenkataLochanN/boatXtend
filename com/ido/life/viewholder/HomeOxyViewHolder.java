package com.ido.life.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.OxyProgressView;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.DateUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HomeOxyViewHolder extends BaseHomeItemViewHolder {

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

    @BindView(R.id.progress_oxy)
    OxyProgressView mOxyProgress;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    @BindView(R.id.tv_oxy)
    TextView mTvOxy;

    @BindView(R.id.tv_oxy_unit)
    TextView mTvOxyUnit;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public HomeOxyViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        CommonLogUtil.printAndSave("开始绑定血氧饱和度数据");
        ServerBloodOxyDayData nearOxyData = this.mHomeView.getNearOxyData();
        this.mLayOut.setEnabled(nearOxyData != null);
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.home_oxygen_title));
        this.itemView.setTag(10);
        this.mAnimView.setAnimatorIcon(R.mipmap.home_oxy_anim_icon);
        this.mTvDate.setTag(null);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        }
        if (nearOxyData == null || nearOxyData.getAvgValue() == 0) {
            this.mHasData = false;
            this.mTvDate.setVisibility(8);
            this.mLayContent.setGravity(80);
            this.mImgEmpty.setVisibility(0);
            this.mOxyProgress.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setVisibility(8);
        } else {
            this.mHasData = true;
            if (!this.mFirstRefresh && !nearOxyData.getUploaded()) {
                Object tag = this.mAnimView.getTag();
                if (tag == null || ((Long) tag).longValue() != nearOxyData.getTimestamp()) {
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
            this.mOxyProgress.setVisibility(0);
            this.mLayBottom.setVisibility(0);
            this.mTvDate.setText(this.mHomeView.getDateShowByTimeStamp(nearOxyData.getTimestamp()));
            this.mTvDate.setTag(Long.valueOf(nearOxyData.getTimestamp()));
            this.mAnimView.setTag(Long.valueOf(nearOxyData.getTimestamp()));
            if (DateUtil.isToday(nearOxyData.getTimestamp())) {
                this.mHomeView.startUpdateTime();
            }
            int latestValue = nearOxyData.getLatestValue();
            if (latestValue <= 0 && !TextUtils.isEmpty(nearOxyData.getItems())) {
                List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(nearOxyData.getItems(), int[][].class);
                if (listAnalysisJsonArrayToList != null && listAnalysisJsonArrayToList.size() > 0) {
                    for (int size = listAnalysisJsonArrayToList.size() - 1; size > -1 && (latestValue = ((int[]) listAnalysisJsonArrayToList.get(size))[1]) <= 0; size--) {
                    }
                }
                nearOxyData.setLatestValue(latestValue);
                nearOxyData.setUploaded(false);
                try {
                    nearOxyData.update();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.mTvOxy.setText(String.valueOf(latestValue));
            this.mTvOxyUnit.setText("%");
            this.mOxyProgress.setProgress(nearOxyData.getLatestValue());
        }
        this.mFirstRefresh = false;
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return ServerBloodOxyDayData.class.getSimpleName();
    }
}