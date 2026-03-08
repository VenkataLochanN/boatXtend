package com.ido.life.viewholder;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.HomeMenstrualProgressView;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.IHomeView;
import com.ido.life.module.home.WholeLifeCycleInfo;

/* JADX INFO: loaded from: classes3.dex */
public class HomeLifeCycleViewHolder extends BaseHomeItemViewHolder {

    @BindView(R.id.anim_view)
    HomeIconAnimView mAnimView;

    @BindView(R.id.menstrual_progress)
    HomeMenstrualProgressView mCycleView;

    @BindView(R.id.img_empty)
    ImageView mImgEmpty;

    @BindView(R.id.lay_bottom)
    LinearLayout mLayBottom;

    @BindView(R.id.lay_content)
    LinearLayout mLayContent;

    @BindView(R.id.lay_out)
    LinearLayout mLayOut;

    @BindView(R.id.tv_left)
    TextView mTvLeft;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public HomeLifeCycleViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        CommonLogUtil.printAndSave("开始绑定生理周期数据");
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.mine_cycle_weight));
        this.itemView.setTag(12);
        this.mAnimView.setAnimatorIcon(R.mipmap.home_menstrual_anim_icon);
        if (showLeftSpace()) {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        } else {
            this.itemView.setPadding(this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_8), this.itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_12));
        }
        if (this.mHomeView.hasLifeCycle()) {
            this.mLayOut.setEnabled(true);
        } else {
            this.mLayOut.setEnabled(false);
        }
        WholeLifeCycleInfo menstrual = this.mHomeView.getMenstrual();
        if (menstrual == null) {
            this.mHasData = false;
            this.mLayContent.setGravity(80);
            this.mImgEmpty.setVisibility(0);
            this.mCycleView.setVisibility(8);
            this.mLayBottom.setVisibility(8);
            this.mAnimView.setVisibility(8);
        } else {
            this.mHasData = true;
            if (!this.mFirstRefresh && !menstrual.getUpload()) {
                Object tag = this.mAnimView.getTag();
                if (tag == null || ((tag instanceof String) && !TextUtils.equals((String) tag, menstrual.toString()))) {
                    this.mAnimView.stopAnimator();
                    this.mAnimView.setVisibility(0);
                    this.mAnimView.startAnimator();
                } else {
                    this.mAnimView.setVisibility(8);
                }
            } else {
                this.mAnimView.setVisibility(8);
            }
            this.mLayContent.setGravity(17);
            this.mImgEmpty.setVisibility(8);
            this.mCycleView.setVisibility(0);
            this.mLayBottom.setVisibility(0);
            int[] iArrCalculateMensState = HomeHelperKt.calculateMensState(menstrual.getMensesCycle(), menstrual.getMensesDays(), menstrual.getMensStartDate());
            int i = iArrCalculateMensState[1];
            int i2 = iArrCalculateMensState[0];
            String str = String.format(LanguageUtil.getLanguageText(i2 != 1 ? i2 != 2 ? i2 != 3 ? R.string.end_of_easy_pregnancy : R.string.start_of_menstruation : R.string.end_of_menstruation : R.string.menstrual_reminder), Integer.valueOf(i));
            int iIndexOf = str.indexOf(String.valueOf(i));
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            spannableStringBuilder.setSpan(new TextAppearanceSpan(IdoApp.getAppContext(), R.style.home_health_data_value_style), iIndexOf, String.valueOf(i).length() + iIndexOf, 17);
            this.mTvLeft.setText(spannableStringBuilder);
            this.mAnimView.setTag(menstrual.toString());
            this.mCycleView.setProgress(iArrCalculateMensState[2]);
            this.mCycleView.setCycleLength(menstrual.getMensesCycle());
            this.mCycleView.setMenstrualLength(menstrual.getMensesDays());
            if (menstrual.getMensesCycle() >= menstrual.getMensesDays() + 19) {
                this.mCycleView.setFertileLength(10);
            } else {
                this.mCycleView.setFertileLength(0);
            }
            this.mCycleView.invalidate();
        }
        this.mFirstRefresh = false;
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return LifeCycleItemBean.class.getSimpleName();
    }
}