package com.ido.life.module.home.fitness;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.RadiusProgressBar;
import com.ido.life.customview.cardview.CustomCardView;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.util.RunTimeUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FitnessDetailBottomViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b5\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010k\u001a\u00020l2\b\u0010m\u001a\u0004\u0018\u00010nJ\b\u0010o\u001a\u00020lH\u0016J\b\u0010p\u001a\u00020lH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0017\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR\u001a\u0010#\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR\u001a\u0010&\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\b\"\u0004\b(\u0010\nR\u001a\u0010)\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010,\"\u0004\b1\u0010.R\u001a\u00102\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010,\"\u0004\b4\u0010.R\u001a\u00105\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u00108\"\u0004\b=\u0010:R\u001a\u0010>\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u00108\"\u0004\b@\u0010:R\u001a\u0010A\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u00108\"\u0004\bC\u0010:R\u001a\u0010D\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u00108\"\u0004\bF\u0010:R\u001a\u0010G\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u00108\"\u0004\bI\u0010:R\u001a\u0010J\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u00108\"\u0004\bL\u0010:R\u001a\u0010M\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u00108\"\u0004\bO\u0010:R\u001a\u0010P\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u00108\"\u0004\bR\u0010:R\u001a\u0010S\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u00108\"\u0004\bU\u0010:R\u001a\u0010V\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u00108\"\u0004\bX\u0010:R\u001a\u0010Y\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u00108\"\u0004\b[\u0010:R\u001a\u0010\\\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u00108\"\u0004\b^\u0010:R\u001a\u0010_\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u00108\"\u0004\ba\u0010:R\u001a\u0010b\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u00108\"\u0004\bd\u0010:R\u001a\u0010e\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u00108\"\u0004\bg\u0010:R\u001a\u0010h\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u00108\"\u0004\bj\u0010:¨\u0006q"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailBottomViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mCardRecent", "Lcom/ido/life/customview/cardview/CustomCardView;", "getMCardRecent", "()Lcom/ido/life/customview/cardview/CustomCardView;", "setMCardRecent", "(Lcom/ido/life/customview/cardview/CustomCardView;)V", "mImgSituationActivityCompare", "Landroid/widget/ImageView;", "getMImgSituationActivityCompare", "()Landroid/widget/ImageView;", "setMImgSituationActivityCompare", "(Landroid/widget/ImageView;)V", "mImgSituationExerciseCompare", "getMImgSituationExerciseCompare", "setMImgSituationExerciseCompare", "mImgSituationWalkCompare", "getMImgSituationWalkCompare", "setMImgSituationWalkCompare", "mLayRecentSituation", "getMLayRecentSituation", "setMLayRecentSituation", "mLayRecentSituationActivity", "Landroid/widget/LinearLayout;", "getMLayRecentSituationActivity", "()Landroid/widget/LinearLayout;", "setMLayRecentSituationActivity", "(Landroid/widget/LinearLayout;)V", "mLayRecentSituationExercise", "getMLayRecentSituationExercise", "setMLayRecentSituationExercise", "mLayRecentSituationWalk", "getMLayRecentSituationWalk", "setMLayRecentSituationWalk", "mLayWhatFitness", "getMLayWhatFitness", "setMLayWhatFitness", "mProgressActivity", "Lcom/ido/life/customview/RadiusProgressBar;", "getMProgressActivity", "()Lcom/ido/life/customview/RadiusProgressBar;", "setMProgressActivity", "(Lcom/ido/life/customview/RadiusProgressBar;)V", "mProgressExercise", "getMProgressExercise", "setMProgressExercise", "mProgressWalk", "getMProgressWalk", "setMProgressWalk", "mTvFitnessDesc", "Landroid/widget/TextView;", "getMTvFitnessDesc", "()Landroid/widget/TextView;", "setMTvFitnessDesc", "(Landroid/widget/TextView;)V", "mTvFitnessTitle", "getMTvFitnessTitle", "setMTvFitnessTitle", "mTvRecentSituationDesc", "getMTvRecentSituationDesc", "setMTvRecentSituationDesc", "mTvRecentSituationScore", "getMTvRecentSituationScore", "setMTvRecentSituationScore", "mTvRecentSituationScoreUnit", "getMTvRecentSituationScoreUnit", "setMTvRecentSituationScoreUnit", "mTvRecentSituationTitle", "getMTvRecentSituationTitle", "setMTvRecentSituationTitle", "mTvSituationActivityTarget", "getMTvSituationActivityTarget", "setMTvSituationActivityTarget", "mTvSituationActivityTitle", "getMTvSituationActivityTitle", "setMTvSituationActivityTitle", "mTvSituationActivityValue", "getMTvSituationActivityValue", "setMTvSituationActivityValue", "mTvSituationExerciseTarget", "getMTvSituationExerciseTarget", "setMTvSituationExerciseTarget", "mTvSituationExerciseTitle", "getMTvSituationExerciseTitle", "setMTvSituationExerciseTitle", "mTvSituationExerciseValue", "getMTvSituationExerciseValue", "setMTvSituationExerciseValue", "mTvSituationWalkTarget", "getMTvSituationWalkTarget", "setMTvSituationWalkTarget", "mTvSituationWalkTitle", "getMTvSituationWalkTitle", "setMTvSituationWalkTitle", "mTvSituationWalkValue", "getMTvSituationWalkValue", "setMTvSituationWalkValue", "mTvStageDateDesc", "getMTvStageDateDesc", "setMTvStageDateDesc", "mTvTotalDesc", "getMTvTotalDesc", "setMTvTotalDesc", "initListener", "", "clickListener", "Landroid/view/View$OnClickListener;", "refreshLanguage", "setDefaultValue", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FitnessDetailBottomViewHolder extends BaseDetailViewHolder {
    private CustomCardView mCardRecent;
    private ImageView mImgSituationActivityCompare;
    private ImageView mImgSituationExerciseCompare;
    private ImageView mImgSituationWalkCompare;
    private CustomCardView mLayRecentSituation;
    private LinearLayout mLayRecentSituationActivity;
    private LinearLayout mLayRecentSituationExercise;
    private LinearLayout mLayRecentSituationWalk;
    private CustomCardView mLayWhatFitness;
    private RadiusProgressBar mProgressActivity;
    private RadiusProgressBar mProgressExercise;
    private RadiusProgressBar mProgressWalk;
    private TextView mTvFitnessDesc;
    private TextView mTvFitnessTitle;
    private TextView mTvRecentSituationDesc;
    private TextView mTvRecentSituationScore;
    private TextView mTvRecentSituationScoreUnit;
    private TextView mTvRecentSituationTitle;
    private TextView mTvSituationActivityTarget;
    private TextView mTvSituationActivityTitle;
    private TextView mTvSituationActivityValue;
    private TextView mTvSituationExerciseTarget;
    private TextView mTvSituationExerciseTitle;
    private TextView mTvSituationExerciseValue;
    private TextView mTvSituationWalkTarget;
    private TextView mTvSituationWalkTitle;
    private TextView mTvSituationWalkValue;
    private TextView mTvStageDateDesc;
    private TextView mTvTotalDesc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessDetailBottomViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.card_recent);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.card_recent)");
        this.mCardRecent = (CustomCardView) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.tv_fitness_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.tv_fitness_title)");
        this.mTvFitnessTitle = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.tv_fitness_desc);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.tv_fitness_desc)");
        this.mTvFitnessDesc = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_recent_situation_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.…v_recent_situation_title)");
        this.mTvRecentSituationTitle = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_recent_situation_score);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.…v_recent_situation_score)");
        this.mTvRecentSituationScore = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.tv_recent_situation_score_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.…ent_situation_score_unit)");
        this.mTvRecentSituationScoreUnit = (TextView) viewFindViewById6;
        View viewFindViewById7 = itemView.findViewById(R.id.tv_recent_situation_desc);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "itemView.findViewById(R.…tv_recent_situation_desc)");
        this.mTvRecentSituationDesc = (TextView) viewFindViewById7;
        View viewFindViewById8 = itemView.findViewById(R.id.lay_recent_situation);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "itemView.findViewById(R.id.lay_recent_situation)");
        this.mLayRecentSituation = (CustomCardView) viewFindViewById8;
        View viewFindViewById9 = itemView.findViewById(R.id.lay_recent_situation_activity);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "itemView.findViewById(R.…ecent_situation_activity)");
        this.mLayRecentSituationActivity = (LinearLayout) viewFindViewById9;
        View viewFindViewById10 = itemView.findViewById(R.id.tv_situation_activity_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById10, "itemView.findViewById(R.…situation_activity_title)");
        this.mTvSituationActivityTitle = (TextView) viewFindViewById10;
        View viewFindViewById11 = itemView.findViewById(R.id.tv_situation_activity_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById11, "itemView.findViewById(R.…situation_activity_value)");
        this.mTvSituationActivityValue = (TextView) viewFindViewById11;
        View viewFindViewById12 = itemView.findViewById(R.id.tv_situation_activity_target);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById12, "itemView.findViewById(R.…ituation_activity_target)");
        this.mTvSituationActivityTarget = (TextView) viewFindViewById12;
        View viewFindViewById13 = itemView.findViewById(R.id.progress_activity);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById13, "itemView.findViewById(R.id.progress_activity)");
        this.mProgressActivity = (RadiusProgressBar) viewFindViewById13;
        View viewFindViewById14 = itemView.findViewById(R.id.img_situation_activity_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById14, "itemView.findViewById(R.…tuation_activity_compare)");
        this.mImgSituationActivityCompare = (ImageView) viewFindViewById14;
        View viewFindViewById15 = itemView.findViewById(R.id.lay_recent_situation_exercise);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById15, "itemView.findViewById(R.…ecent_situation_exercise)");
        this.mLayRecentSituationExercise = (LinearLayout) viewFindViewById15;
        View viewFindViewById16 = itemView.findViewById(R.id.tv_situation_exercise_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById16, "itemView.findViewById(R.…situation_exercise_title)");
        this.mTvSituationExerciseTitle = (TextView) viewFindViewById16;
        View viewFindViewById17 = itemView.findViewById(R.id.tv_situation_exercise_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById17, "itemView.findViewById(R.…situation_exercise_value)");
        this.mTvSituationExerciseValue = (TextView) viewFindViewById17;
        View viewFindViewById18 = itemView.findViewById(R.id.tv_situation_exercise_target);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById18, "itemView.findViewById(R.…ituation_exercise_target)");
        this.mTvSituationExerciseTarget = (TextView) viewFindViewById18;
        View viewFindViewById19 = itemView.findViewById(R.id.progress_exercise);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById19, "itemView.findViewById(R.id.progress_exercise)");
        this.mProgressExercise = (RadiusProgressBar) viewFindViewById19;
        View viewFindViewById20 = itemView.findViewById(R.id.img_situation_exercise_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById20, "itemView.findViewById(R.…tuation_exercise_compare)");
        this.mImgSituationExerciseCompare = (ImageView) viewFindViewById20;
        View viewFindViewById21 = itemView.findViewById(R.id.lay_recent_situation_walk);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById21, "itemView.findViewById(R.…ay_recent_situation_walk)");
        this.mLayRecentSituationWalk = (LinearLayout) viewFindViewById21;
        View viewFindViewById22 = itemView.findViewById(R.id.tv_situation_walk_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById22, "itemView.findViewById(R.….tv_situation_walk_title)");
        this.mTvSituationWalkTitle = (TextView) viewFindViewById22;
        View viewFindViewById23 = itemView.findViewById(R.id.tv_situation_walk_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById23, "itemView.findViewById(R.….tv_situation_walk_value)");
        this.mTvSituationWalkValue = (TextView) viewFindViewById23;
        View viewFindViewById24 = itemView.findViewById(R.id.tv_situation_walk_target);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById24, "itemView.findViewById(R.…tv_situation_walk_target)");
        this.mTvSituationWalkTarget = (TextView) viewFindViewById24;
        View viewFindViewById25 = itemView.findViewById(R.id.progress_walk);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById25, "itemView.findViewById(R.id.progress_walk)");
        this.mProgressWalk = (RadiusProgressBar) viewFindViewById25;
        View viewFindViewById26 = itemView.findViewById(R.id.img_situation_walk_compare);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById26, "itemView.findViewById(R.…g_situation_walk_compare)");
        this.mImgSituationWalkCompare = (ImageView) viewFindViewById26;
        View viewFindViewById27 = itemView.findViewById(R.id.tv_total_desc);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById27, "itemView.findViewById(R.id.tv_total_desc)");
        this.mTvTotalDesc = (TextView) viewFindViewById27;
        View viewFindViewById28 = itemView.findViewById(R.id.lay_what_fitness);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById28, "itemView.findViewById(R.id.lay_what_fitness)");
        this.mLayWhatFitness = (CustomCardView) viewFindViewById28;
        View viewFindViewById29 = itemView.findViewById(R.id.tv_stage_date_desc);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById29, "itemView.findViewById(R.id.tv_stage_date_desc)");
        this.mTvStageDateDesc = (TextView) viewFindViewById29;
        setDefaultValue();
        refreshLanguage();
    }

    public final CustomCardView getMCardRecent() {
        return this.mCardRecent;
    }

    public final void setMCardRecent(CustomCardView customCardView) {
        Intrinsics.checkParameterIsNotNull(customCardView, "<set-?>");
        this.mCardRecent = customCardView;
    }

    public final TextView getMTvFitnessTitle() {
        return this.mTvFitnessTitle;
    }

    public final void setMTvFitnessTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvFitnessTitle = textView;
    }

    public final TextView getMTvFitnessDesc() {
        return this.mTvFitnessDesc;
    }

    public final void setMTvFitnessDesc(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvFitnessDesc = textView;
    }

    public final TextView getMTvRecentSituationTitle() {
        return this.mTvRecentSituationTitle;
    }

    public final void setMTvRecentSituationTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvRecentSituationTitle = textView;
    }

    public final TextView getMTvRecentSituationScore() {
        return this.mTvRecentSituationScore;
    }

    public final void setMTvRecentSituationScore(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvRecentSituationScore = textView;
    }

    public final TextView getMTvRecentSituationScoreUnit() {
        return this.mTvRecentSituationScoreUnit;
    }

    public final void setMTvRecentSituationScoreUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvRecentSituationScoreUnit = textView;
    }

    public final TextView getMTvRecentSituationDesc() {
        return this.mTvRecentSituationDesc;
    }

    public final void setMTvRecentSituationDesc(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvRecentSituationDesc = textView;
    }

    public final CustomCardView getMLayRecentSituation() {
        return this.mLayRecentSituation;
    }

    public final void setMLayRecentSituation(CustomCardView customCardView) {
        Intrinsics.checkParameterIsNotNull(customCardView, "<set-?>");
        this.mLayRecentSituation = customCardView;
    }

    public final LinearLayout getMLayRecentSituationActivity() {
        return this.mLayRecentSituationActivity;
    }

    public final void setMLayRecentSituationActivity(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayRecentSituationActivity = linearLayout;
    }

    public final TextView getMTvSituationActivityTitle() {
        return this.mTvSituationActivityTitle;
    }

    public final void setMTvSituationActivityTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationActivityTitle = textView;
    }

    public final TextView getMTvSituationActivityValue() {
        return this.mTvSituationActivityValue;
    }

    public final void setMTvSituationActivityValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationActivityValue = textView;
    }

    public final TextView getMTvSituationActivityTarget() {
        return this.mTvSituationActivityTarget;
    }

    public final void setMTvSituationActivityTarget(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationActivityTarget = textView;
    }

    public final RadiusProgressBar getMProgressActivity() {
        return this.mProgressActivity;
    }

    public final void setMProgressActivity(RadiusProgressBar radiusProgressBar) {
        Intrinsics.checkParameterIsNotNull(radiusProgressBar, "<set-?>");
        this.mProgressActivity = radiusProgressBar;
    }

    public final ImageView getMImgSituationActivityCompare() {
        return this.mImgSituationActivityCompare;
    }

    public final void setMImgSituationActivityCompare(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
        this.mImgSituationActivityCompare = imageView;
    }

    public final LinearLayout getMLayRecentSituationExercise() {
        return this.mLayRecentSituationExercise;
    }

    public final void setMLayRecentSituationExercise(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayRecentSituationExercise = linearLayout;
    }

    public final TextView getMTvSituationExerciseTitle() {
        return this.mTvSituationExerciseTitle;
    }

    public final void setMTvSituationExerciseTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationExerciseTitle = textView;
    }

    public final TextView getMTvSituationExerciseValue() {
        return this.mTvSituationExerciseValue;
    }

    public final void setMTvSituationExerciseValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationExerciseValue = textView;
    }

    public final TextView getMTvSituationExerciseTarget() {
        return this.mTvSituationExerciseTarget;
    }

    public final void setMTvSituationExerciseTarget(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationExerciseTarget = textView;
    }

    public final RadiusProgressBar getMProgressExercise() {
        return this.mProgressExercise;
    }

    public final void setMProgressExercise(RadiusProgressBar radiusProgressBar) {
        Intrinsics.checkParameterIsNotNull(radiusProgressBar, "<set-?>");
        this.mProgressExercise = radiusProgressBar;
    }

    public final ImageView getMImgSituationExerciseCompare() {
        return this.mImgSituationExerciseCompare;
    }

    public final void setMImgSituationExerciseCompare(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
        this.mImgSituationExerciseCompare = imageView;
    }

    public final LinearLayout getMLayRecentSituationWalk() {
        return this.mLayRecentSituationWalk;
    }

    public final void setMLayRecentSituationWalk(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayRecentSituationWalk = linearLayout;
    }

    public final TextView getMTvSituationWalkTitle() {
        return this.mTvSituationWalkTitle;
    }

    public final void setMTvSituationWalkTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationWalkTitle = textView;
    }

    public final TextView getMTvSituationWalkValue() {
        return this.mTvSituationWalkValue;
    }

    public final void setMTvSituationWalkValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationWalkValue = textView;
    }

    public final TextView getMTvSituationWalkTarget() {
        return this.mTvSituationWalkTarget;
    }

    public final void setMTvSituationWalkTarget(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvSituationWalkTarget = textView;
    }

    public final RadiusProgressBar getMProgressWalk() {
        return this.mProgressWalk;
    }

    public final void setMProgressWalk(RadiusProgressBar radiusProgressBar) {
        Intrinsics.checkParameterIsNotNull(radiusProgressBar, "<set-?>");
        this.mProgressWalk = radiusProgressBar;
    }

    public final ImageView getMImgSituationWalkCompare() {
        return this.mImgSituationWalkCompare;
    }

    public final void setMImgSituationWalkCompare(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "<set-?>");
        this.mImgSituationWalkCompare = imageView;
    }

    public final TextView getMTvTotalDesc() {
        return this.mTvTotalDesc;
    }

    public final void setMTvTotalDesc(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTotalDesc = textView;
    }

    public final CustomCardView getMLayWhatFitness() {
        return this.mLayWhatFitness;
    }

    public final void setMLayWhatFitness(CustomCardView customCardView) {
        Intrinsics.checkParameterIsNotNull(customCardView, "<set-?>");
        this.mLayWhatFitness = customCardView;
    }

    public final TextView getMTvStageDateDesc() {
        return this.mTvStageDateDesc;
    }

    public final void setMTvStageDateDesc(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvStageDateDesc = textView;
    }

    public final void initListener(View.OnClickListener clickListener) {
        this.mLayWhatFitness.setOnClickListener(clickListener);
        this.mLayRecentSituationActivity.setOnClickListener(clickListener);
        this.mLayRecentSituationExercise.setOnClickListener(clickListener);
        this.mLayRecentSituationWalk.setOnClickListener(clickListener);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvFitnessTitle.setText(LanguageUtil.getLanguageText(R.string.what_fitness));
        this.mTvRecentSituationTitle.setText(LanguageUtil.getLanguageText(R.string.recent_situation));
        this.mTvRecentSituationScoreUnit.setText(LanguageUtil.getLanguageText(R.string.fitness_recent_score_unit));
        this.mTvSituationActivityTitle.setText(LanguageUtil.getLanguageText(R.string.activity));
        this.mTvSituationExerciseTitle.setText(LanguageUtil.getLanguageText(R.string.exercise));
        this.mTvSituationWalkTitle.setText(LanguageUtil.getLanguageText(R.string.walking));
        this.mTvTotalDesc.setText(LanguageUtil.getLanguageText(R.string.fitness_detail_total_desc));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvRecentSituationScore.setText("--");
        String calorieUnit = RunTimeUtil.getCalorieUnit();
        this.mTvSituationActivityValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + calorieUnit + "/" + LanguageUtil.getLanguageText(R.string.fitness_day_unit));
        TextView textView = this.mTvSituationActivityTarget;
        StringBuilder sb = new StringBuilder();
        sb.append("500");
        sb.append(calorieUnit);
        textView.setText(sb.toString());
        this.mTvSituationExerciseValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.public_time_minute) + "/" + LanguageUtil.getLanguageText(R.string.fitness_day_unit));
        TextView textView2 = this.mTvSituationExerciseTarget;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("30");
        sb2.append(LanguageUtil.getLanguageText(R.string.public_time_minute));
        textView2.setText(sb2.toString());
        this.mTvSituationWalkValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + LanguageUtil.getLanguageText(R.string.public_unit_hr) + "/" + LanguageUtil.getLanguageText(R.string.fitness_day_unit));
        TextView textView3 = this.mTvSituationWalkTarget;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("12");
        sb3.append(LanguageUtil.getLanguageText(R.string.public_unit_hrs));
        textView3.setText(sb3.toString());
        this.mTvFitnessDesc.setText(LanguageUtil.getLanguageText(R.string.fitness_detail_what_fitness_desc));
        this.mImgSituationActivityCompare.setImageResource(R.mipmap.fitness_activity_calorie_flat);
        this.mImgSituationExerciseCompare.setImageResource(R.mipmap.fitness_activity_time_flat);
        this.mImgSituationWalkCompare.setImageResource(R.mipmap.fitness_walk_flat);
        this.mTvStageDateDesc.setText("");
    }
}