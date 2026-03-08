package com.ido.life.viewholder;

import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.HookClickListenerHelper;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.RadiusProgressBar;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.RunTimeUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class OxyUptakeViewHolder extends BaseHomeItemViewHolder {

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

    @BindView(R.id.oxy_uptake_progress)
    RadiusProgressBar mOxyUptakeProgress;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    @BindView(R.id.tv_oxygen_uptake)
    TextView mTvOxygenUptake;

    @BindView(R.id.tv_oxygen_uptake_unit)
    TextView mTvOxygenUptakeUnit;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public OxyUptakeViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
        HookClickListenerHelper.hookOnClick(this.itemView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return SportHealth.class.getSimpleName();
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        List<RadiusProgressBar.DividerProperty> oxygenUpdatePropertyList;
        this.itemView.setTag(19);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_5), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_5), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_10));
        }
        Pair<Long, Integer> oxygenUptakeData = this.mHomeView.getOxygenUptakeData();
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.help_max_spo2_title));
        this.mAnimView.setAnimatorIcon(R.mipmap.home_oxygen_uptake);
        this.mAnimView.setVisibility(8);
        if (oxygenUptakeData == null) {
            printAndSaveLog("显示最大摄氧量无数据UI。");
            this.mTvDate.setVisibility(8);
            this.mLayEmpty.setVisibility(0);
            this.mOxyUptakeProgress.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setTag(-1L);
            this.mFirstRefresh = false;
            return;
        }
        this.mTvDate.setVisibility(0);
        this.mLayEmpty.setVisibility(8);
        this.mOxyUptakeProgress.setVisibility(0);
        this.mLayBottom.setVisibility(0);
        long jLongValue = this.mAnimView.getTag() instanceof Long ? ((Long) this.mAnimView.getTag()).longValue() : -1L;
        if (jLongValue != -1 && jLongValue != ((Long) oxygenUptakeData.first).longValue()) {
            printAndSaveLog("显示最大摄氧量有数据UI，同时启动数据发生改变动画。");
            this.mAnimView.setVisibility(0);
            this.mAnimView.startAnimator();
        } else {
            printAndSaveLog("显示最大摄氧量有数据UI，不启动刷剧发生改变动画。");
        }
        this.mAnimView.setTag(oxygenUptakeData.first);
        this.mTvDate.setText(this.mHomeView.getDateShowByTimeStamp(((Long) oxygenUptakeData.first).longValue()));
        this.mTvOxygenUptake.setText(String.valueOf(oxygenUptakeData.second));
        this.mTvOxygenUptakeUnit.setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_unit));
        this.mOxyUptakeProgress.getMDividerPropertyList();
        if (this.mOxyUptakeProgress.getMDividerPropertyList().isEmpty() && (oxygenUpdatePropertyList = HomeHelperKt.getOxygenUpdatePropertyList(RunTimeUtil.getInstance().getUserId())) != null && oxygenUpdatePropertyList.size() > 0) {
            this.mOxyUptakeProgress.setMDividerPropertyList(oxygenUpdatePropertyList);
        }
        this.mOxyUptakeProgress.setMCurrentProgress(((Integer) oxygenUptakeData.second).intValue());
        this.mOxyUptakeProgress.invalidate();
        this.mFirstRefresh = false;
    }
}