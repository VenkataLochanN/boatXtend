package com.ido.life.module.home.fitness;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.FitnessCircleView;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;
import com.ido.life.util.RunTimeUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FitnessDetailTopViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u000207H\u0016J\b\u00109\u001a\u000207H\u0016J\u0012\u0010:\u001a\u0002072\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010=\u001a\u000207H\u0016J\b\u0010>\u001a\u000207H\u0016J\u0010\u0010?\u001a\u0002072\u0006\u0010@\u001a\u00020AH\u0016J\u000e\u0010B\u001a\u0002072\u0006\u0010C\u001a\u00020AR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0004R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001a\u0010!\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001a\u0010$\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001a\u0010'\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001a\u0010*\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0018\"\u0004\b,\u0010\u001aR\u001a\u0010-\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0018\"\u0004\b/\u0010\u001aR\u001a\u00100\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0018\"\u0004\b2\u0010\u001aR\u001a\u00103\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0018\"\u0004\b5\u0010\u001a¨\u0006D"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailTopViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailTopViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mDateType", "", "getMDateType", "()I", "setMDateType", "(I)V", "mFitnessCircle", "Lcom/ido/life/customview/FitnessCircleView;", "getMFitnessCircle", "()Lcom/ido/life/customview/FitnessCircleView;", "setMFitnessCircle", "(Lcom/ido/life/customview/FitnessCircleView;)V", "mLayContent", "getMLayContent", "()Landroid/view/View;", "setMLayContent", "mTvActiveCalorieTitle", "Landroid/widget/TextView;", "getMTvActiveCalorieTitle", "()Landroid/widget/TextView;", "setMTvActiveCalorieTitle", "(Landroid/widget/TextView;)V", "mTvActiveCalorieUnit", "getMTvActiveCalorieUnit", "setMTvActiveCalorieUnit", "mTvActiveCalorieValue", "getMTvActiveCalorieValue", "setMTvActiveCalorieValue", "mTvActiveTimeTitle", "getMTvActiveTimeTitle", "setMTvActiveTimeTitle", "mTvActiveTimeUnit", "getMTvActiveTimeUnit", "setMTvActiveTimeUnit", "mTvActiveTimeValue", "getMTvActiveTimeValue", "setMTvActiveTimeValue", "mTvDate", "getMTvDate", "setMTvDate", "mTvWalkingTitle", "getMTvWalkingTitle", "setMTvWalkingTitle", "mTvWalkingUnit", "getMTvWalkingUnit", "setMTvWalkingUnit", "mTvWalkingValue", "getMTvWalkingValue", "setMTvWalkingValue", "hideRightLayout", "", "refreshLanguage", "setDefaultValue", "showLoadFailedView", "clickListener", "Landroid/view/View$OnClickListener;", "showLoadingView", "showRightLayout", "showSuccessView", "showRight", "", "updateTopTitle", "dayType", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FitnessDetailTopViewHolder extends BaseDetailTopViewHolder {
    private int mDateType;
    private FitnessCircleView mFitnessCircle;
    private View mLayContent;
    private TextView mTvActiveCalorieTitle;
    private TextView mTvActiveCalorieUnit;
    private TextView mTvActiveCalorieValue;
    private TextView mTvActiveTimeTitle;
    private TextView mTvActiveTimeUnit;
    private TextView mTvActiveTimeValue;
    private TextView mTvDate;
    private TextView mTvWalkingTitle;
    private TextView mTvWalkingUnit;
    private TextView mTvWalkingValue;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessDetailTopViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        this.mDateType = 1;
        View viewFindViewById = itemView.findViewById(R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_content)");
        this.mLayContent = viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.fitness_circle_view);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.fitness_circle_view)");
        this.mFitnessCircle = (FitnessCircleView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.tv_active_calorie_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.….tv_active_calorie_title)");
        this.mTvActiveCalorieTitle = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_active_calorie_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.….tv_active_calorie_value)");
        this.mTvActiveCalorieValue = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_active_calorie_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_active_calorie_unit)");
        this.mTvActiveCalorieUnit = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.id.tv_date)");
        this.mTvDate = (TextView) viewFindViewById6;
        View viewFindViewById7 = itemView.findViewById(R.id.tv_active_time_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "itemView.findViewById(R.id.tv_active_time_title)");
        this.mTvActiveTimeTitle = (TextView) viewFindViewById7;
        View viewFindViewById8 = itemView.findViewById(R.id.tv_actime_time_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "itemView.findViewById(R.id.tv_actime_time_value)");
        this.mTvActiveTimeValue = (TextView) viewFindViewById8;
        View viewFindViewById9 = itemView.findViewById(R.id.tv_active_time_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "itemView.findViewById(R.id.tv_active_time_unit)");
        this.mTvActiveTimeUnit = (TextView) viewFindViewById9;
        View viewFindViewById10 = itemView.findViewById(R.id.tv_walking_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById10, "itemView.findViewById(R.id.tv_walking_title)");
        this.mTvWalkingTitle = (TextView) viewFindViewById10;
        View viewFindViewById11 = itemView.findViewById(R.id.tv_walking_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById11, "itemView.findViewById(R.id.tv_walking_value)");
        this.mTvWalkingValue = (TextView) viewFindViewById11;
        View viewFindViewById12 = itemView.findViewById(R.id.tv_walking_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById12, "itemView.findViewById(R.id.tv_walking_unit)");
        this.mTvWalkingUnit = (TextView) viewFindViewById12;
    }

    public final int getMDateType() {
        return this.mDateType;
    }

    public final void setMDateType(int i) {
        this.mDateType = i;
    }

    public final View getMLayContent() {
        return this.mLayContent;
    }

    public final void setMLayContent(View view) {
        Intrinsics.checkParameterIsNotNull(view, "<set-?>");
        this.mLayContent = view;
    }

    public final FitnessCircleView getMFitnessCircle() {
        return this.mFitnessCircle;
    }

    public final void setMFitnessCircle(FitnessCircleView fitnessCircleView) {
        Intrinsics.checkParameterIsNotNull(fitnessCircleView, "<set-?>");
        this.mFitnessCircle = fitnessCircleView;
    }

    public final TextView getMTvActiveCalorieTitle() {
        return this.mTvActiveCalorieTitle;
    }

    public final void setMTvActiveCalorieTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvActiveCalorieTitle = textView;
    }

    public final TextView getMTvActiveCalorieValue() {
        return this.mTvActiveCalorieValue;
    }

    public final void setMTvActiveCalorieValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvActiveCalorieValue = textView;
    }

    public final TextView getMTvActiveCalorieUnit() {
        return this.mTvActiveCalorieUnit;
    }

    public final void setMTvActiveCalorieUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvActiveCalorieUnit = textView;
    }

    public final TextView getMTvDate() {
        return this.mTvDate;
    }

    public final void setMTvDate(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDate = textView;
    }

    public final TextView getMTvActiveTimeTitle() {
        return this.mTvActiveTimeTitle;
    }

    public final void setMTvActiveTimeTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvActiveTimeTitle = textView;
    }

    public final TextView getMTvActiveTimeValue() {
        return this.mTvActiveTimeValue;
    }

    public final void setMTvActiveTimeValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvActiveTimeValue = textView;
    }

    public final TextView getMTvActiveTimeUnit() {
        return this.mTvActiveTimeUnit;
    }

    public final void setMTvActiveTimeUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvActiveTimeUnit = textView;
    }

    public final TextView getMTvWalkingTitle() {
        return this.mTvWalkingTitle;
    }

    public final void setMTvWalkingTitle(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWalkingTitle = textView;
    }

    public final TextView getMTvWalkingValue() {
        return this.mTvWalkingValue;
    }

    public final void setMTvWalkingValue(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWalkingValue = textView;
    }

    public final TextView getMTvWalkingUnit() {
        return this.mTvWalkingUnit;
    }

    public final void setMTvWalkingUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWalkingUnit = textView;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvActiveCalorieUnit.setText(RunTimeUtil.getCalorieUnit());
        this.mTvActiveTimeUnit.setText(LanguageUtil.getLanguageText(R.string.public_time_minute));
        this.mTvWalkingUnit.setText(LanguageUtil.getLanguageText(R.string.public_unit_hr));
    }

    public final void updateTopTitle(boolean dayType) {
        if (dayType) {
            this.mTvActiveCalorieTitle.setText(LanguageUtil.getLanguageText(R.string.activity));
            this.mTvActiveTimeTitle.setText(LanguageUtil.getLanguageText(R.string.exercise));
            this.mTvWalkingTitle.setText(LanguageUtil.getLanguageText(R.string.walking));
        } else {
            this.mTvActiveCalorieTitle.setText(LanguageUtil.getLanguageText(R.string.activity_avg));
            this.mTvActiveTimeTitle.setText(LanguageUtil.getLanguageText(R.string.exercise_avg));
            this.mTvWalkingTitle.setText(LanguageUtil.getLanguageText(R.string.walking_avg));
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        if (this.mLayContent.getVisibility() == 0) {
            this.mTvActiveCalorieValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mTvActiveTimeValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mTvWalkingValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mTvDate.setText("");
            this.mFitnessCircle.setOutTopProgress(0);
            this.mFitnessCircle.setOutBottomProgress(0);
            this.mFitnessCircle.setInnerProgress(0);
            this.mFitnessCircle.setOutTopMaxProgress(100);
            this.mFitnessCircle.setOutBottomMaxProgress(100);
            this.mFitnessCircle.setInnerMaxProgress(100);
            this.mFitnessCircle.refreshView(false);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showSuccessView(boolean showRight) {
        stopAnimator();
        this.mLayContent.setVisibility(0);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(8);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadFailedView(View.OnClickListener clickListener) {
        stopAnimator();
        this.mLayContent.setVisibility(8);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(0);
        ImageView mImgLoading = this.mImgLoading;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoading, "mImgLoading");
        mImgLoading.setVisibility(8);
        ImageView mImgLoadFailed = this.mImgLoadFailed;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoadFailed, "mImgLoadFailed");
        mImgLoadFailed.setVisibility(0);
        TextView mTvLoadingState = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState, "mTvLoadingState");
        mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        stopAnimator();
        this.mLayContent.setVisibility(8);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(0);
        ImageView mImgLoading = this.mImgLoading;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoading, "mImgLoading");
        mImgLoading.setVisibility(0);
        ImageView mImgLoadFailed = this.mImgLoadFailed;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoadFailed, "mImgLoadFailed");
        mImgLoadFailed.setVisibility(8);
        TextView mTvLoadingState = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState, "mTvLoadingState");
        mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
        startAnimator();
    }
}