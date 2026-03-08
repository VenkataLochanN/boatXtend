package com.ido.life.viewholder;

import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.FitnessCircleView;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.GreenDaoUtil;

/* JADX INFO: loaded from: classes3.dex */
public class HomeFitnessViewHolder extends BaseHomeItemViewHolder {

    @BindView(R.id.fitness_circle_view)
    public FitnessCircleView mCycleView;

    @BindView(R.id.lay_calorie)
    public LinearLayout mLayCalorie;

    @BindView(R.id.lay_exercise)
    public LinearLayout mLayExercise;

    @BindView(R.id.lay_fitness)
    public ConstraintLayout mLayFitness;

    @BindView(R.id.lay_walk)
    public LinearLayout mLayWalk;

    @BindView(R.id.tv_calorie)
    public TextView mTvCalorie;

    @BindView(R.id.tv_calorie_target)
    public TextView mTvCalorieTarget;

    @BindView(R.id.tv_exercise)
    public TextView mTvExercies;

    @BindView(R.id.tv_exercise_target)
    public TextView mTvExerciseTarget;

    @BindView(R.id.tv_walk)
    public TextView mTvWalk;

    @BindView(R.id.tv_walk_target)
    public TextView mTvWalkTarget;

    public HomeFitnessViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    public void checkAnimator() {
        if (this.mHomeView != null && this.mHomeView.hasLogin() && !this.mHasData) {
            int iQueryLatestDataLoadState = GreenDaoUtil.queryLatestDataLoadState(getUserId(), CalorieDayData.class.getSimpleName());
            int iQueryLatestDataLoadState2 = GreenDaoUtil.queryLatestDataLoadState(getUserId(), ActiveTimeDayData.class.getSimpleName());
            int iQueryLatestDataLoadState3 = GreenDaoUtil.queryLatestDataLoadState(getUserId(), WalkDayData.class.getSimpleName());
            if ((iQueryLatestDataLoadState != 4 && iQueryLatestDataLoadState != 3) || ((iQueryLatestDataLoadState2 != 4 && iQueryLatestDataLoadState2 != 3) || (iQueryLatestDataLoadState3 != 4 && iQueryLatestDataLoadState3 != 3))) {
                startLoadingAnimator();
                this.mHasData = false;
            } else {
                this.mHasData = true;
                stopLoadingAnimator();
            }
        } else {
            this.mHasData = true;
            stopLoadingAnimator();
        }
        setClickListener(null);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        if (!this.mHomeView.hasLogin() || this.mHasData) {
            this.itemView.setOnClickListener(this.mHomeView);
        } else {
            this.itemView.setOnClickListener(getDefaultClickListener());
        }
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        CommonLogUtil.printAndSave("开始绑定数据三环数据");
        this.itemView.setTag(16);
        Pair<Pair<Boolean, Integer>, Integer> todayActiveTime = this.mHomeView.getTodayActiveTime();
        Pair<Pair<Boolean, Integer>, Integer> todayWalk = this.mHomeView.getTodayWalk();
        Pair<Pair<Boolean, Integer>, Integer> todayActive = this.mHomeView.getTodayActive();
        printAndSaveLog("开始绑定数据三环数据: exercisePair = " + todayActiveTime + ", walkPair = " + todayWalk + ", caloriePair = " + todayActive);
        this.mCycleView.setOutTopProgress(((Integer) ((Pair) todayActiveTime.first).second).intValue());
        this.mCycleView.setOutTopMaxProgress(((Integer) todayActiveTime.second).intValue());
        this.mCycleView.setOutBottomProgress(((Integer) ((Pair) todayWalk.first).second).intValue());
        this.mCycleView.setOutBottomMaxProgress(((Integer) todayWalk.second).intValue());
        this.mCycleView.setInnerProgress(((Integer) ((Pair) todayActive.first).second).intValue());
        this.mCycleView.setInnerMaxProgress(((Integer) todayActive.second).intValue());
        this.mCycleView.refreshView(true);
        this.mTvCalorie.setText(String.valueOf(((Pair) todayActive.first).second));
        this.mTvCalorieTarget.setText("/" + todayActive.second + LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        this.mTvExercies.setText(String.valueOf(((Pair) todayActiveTime.first).second));
        this.mTvExerciseTarget.setText("/" + todayActiveTime.second + LanguageUtil.getLanguageText(R.string.public_time_minute));
        this.mTvWalk.setText(String.valueOf(((Pair) todayWalk.first).second));
        this.mTvWalkTarget.setText("/" + todayWalk.second + LanguageUtil.getLanguageText(R.string.public_time_hour));
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return WeightItemBean.class.getSimpleName();
    }
}