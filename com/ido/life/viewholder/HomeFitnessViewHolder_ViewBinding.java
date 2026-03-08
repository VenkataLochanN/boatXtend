package com.ido.life.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.FitnessCircleView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeFitnessViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeFitnessViewHolder target;

    public HomeFitnessViewHolder_ViewBinding(HomeFitnessViewHolder homeFitnessViewHolder, View view) {
        super(homeFitnessViewHolder, view);
        this.target = homeFitnessViewHolder;
        homeFitnessViewHolder.mLayFitness = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.lay_fitness, "field 'mLayFitness'", ConstraintLayout.class);
        homeFitnessViewHolder.mCycleView = (FitnessCircleView) Utils.findRequiredViewAsType(view, R.id.fitness_circle_view, "field 'mCycleView'", FitnessCircleView.class);
        homeFitnessViewHolder.mLayCalorie = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_calorie, "field 'mLayCalorie'", LinearLayout.class);
        homeFitnessViewHolder.mTvCalorie = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_calorie, "field 'mTvCalorie'", TextView.class);
        homeFitnessViewHolder.mTvCalorieTarget = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_calorie_target, "field 'mTvCalorieTarget'", TextView.class);
        homeFitnessViewHolder.mLayExercise = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_exercise, "field 'mLayExercise'", LinearLayout.class);
        homeFitnessViewHolder.mTvExercies = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_exercise, "field 'mTvExercies'", TextView.class);
        homeFitnessViewHolder.mTvExerciseTarget = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_exercise_target, "field 'mTvExerciseTarget'", TextView.class);
        homeFitnessViewHolder.mLayWalk = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_walk, "field 'mLayWalk'", LinearLayout.class);
        homeFitnessViewHolder.mTvWalk = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_walk, "field 'mTvWalk'", TextView.class);
        homeFitnessViewHolder.mTvWalkTarget = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_walk_target, "field 'mTvWalkTarget'", TextView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeFitnessViewHolder homeFitnessViewHolder = this.target;
        if (homeFitnessViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeFitnessViewHolder.mLayFitness = null;
        homeFitnessViewHolder.mCycleView = null;
        homeFitnessViewHolder.mLayCalorie = null;
        homeFitnessViewHolder.mTvCalorie = null;
        homeFitnessViewHolder.mTvCalorieTarget = null;
        homeFitnessViewHolder.mLayExercise = null;
        homeFitnessViewHolder.mTvExercies = null;
        homeFitnessViewHolder.mTvExerciseTarget = null;
        homeFitnessViewHolder.mLayWalk = null;
        homeFitnessViewHolder.mTvWalk = null;
        homeFitnessViewHolder.mTvWalkTarget = null;
        super.unbind();
    }
}