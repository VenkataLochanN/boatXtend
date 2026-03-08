package com.ido.life.module.home.fitness;

import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.FitnessCircleView;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTipViewHolder;
import com.ido.life.util.RunTimeUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FitnessDetailTipViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010E\u001a\u00020\u0003H\u0016J\b\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u00020GH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0012\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\rR\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\rR\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\rR\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\rR\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\rR\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\u001a\u0010*\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\u001a\u0010-\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010$\"\u0004\b/\u0010&R\u001a\u00100\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010$\"\u0004\b2\u0010&R\u001a\u00103\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010$\"\u0004\b5\u0010&R\u001a\u00106\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\u001a\u00109\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010$\"\u0004\b;\u0010&R\u001a\u0010<\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010$\"\u0004\b>\u0010&R\u001a\u0010?\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010$\"\u0004\bA\u0010&R\u001a\u0010B\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010$\"\u0004\bD\u0010&¨\u0006I"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailTipViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailTipViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mFitnessCircle", "Lcom/ido/life/customview/FitnessCircleView;", "getMFitnessCircle", "()Lcom/ido/life/customview/FitnessCircleView;", "setMFitnessCircle", "(Lcom/ido/life/customview/FitnessCircleView;)V", "mLayChartContent", "getMLayChartContent", "()Landroid/view/View;", "setMLayChartContent", "mLayLineBottom", "Landroid/widget/LinearLayout;", "getMLayLineBottom", "()Landroid/widget/LinearLayout;", "mLayWalkValue", "getMLayWalkValue", "setMLayWalkValue", "(Landroid/widget/LinearLayout;)V", "mLineFive", "getMLineFive", "mLineFour", "getMLineFour", "mLineOne", "getMLineOne", "mLineThree", "getMLineThree", "mLineTwo", "getMLineTwo", "mTvActiveCalorieTitle", "Landroid/widget/TextView;", "getMTvActiveCalorieTitle", "()Landroid/widget/TextView;", "setMTvActiveCalorieTitle", "(Landroid/widget/TextView;)V", "mTvActiveCalorieUnit", "getMTvActiveCalorieUnit", "setMTvActiveCalorieUnit", "mTvActiveCalorieValue", "getMTvActiveCalorieValue", "setMTvActiveCalorieValue", "mTvActiveTimeTitle", "getMTvActiveTimeTitle", "setMTvActiveTimeTitle", "mTvActiveTimeUnit", "getMTvActiveTimeUnit", "setMTvActiveTimeUnit", "mTvActiveTimeValue", "getMTvActiveTimeValue", "setMTvActiveTimeValue", "mTvDate", "getMTvDate", "setMTvDate", "mTvWalkValueDefault", "getMTvWalkValueDefault", "setMTvWalkValueDefault", "mTvWalkingTitle", "getMTvWalkingTitle", "setMTvWalkingTitle", "mTvWalkingUnit", "getMTvWalkingUnit", "setMTvWalkingUnit", "mTvWalkingValue", "getMTvWalkingValue", "setMTvWalkingValue", "getContentView", "refreshLanguage", "", "setDefaultValue", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FitnessDetailTipViewHolder extends BaseDetailTipViewHolder {
    private FitnessCircleView mFitnessCircle;
    private View mLayChartContent;
    private final LinearLayout mLayLineBottom;
    private LinearLayout mLayWalkValue;
    private final View mLineFive;
    private final View mLineFour;
    private final View mLineOne;
    private final View mLineThree;
    private final View mLineTwo;
    private TextView mTvActiveCalorieTitle;
    private TextView mTvActiveCalorieUnit;
    private TextView mTvActiveCalorieValue;
    private TextView mTvActiveTimeTitle;
    private TextView mTvActiveTimeUnit;
    private TextView mTvActiveTimeValue;
    private TextView mTvDate;
    private TextView mTvWalkValueDefault;
    private TextView mTvWalkingTitle;
    private TextView mTvWalkingUnit;
    private TextView mTvWalkingValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessDetailTipViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.fitness_circle_view);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.fitness_circle_view)");
        this.mFitnessCircle = (FitnessCircleView) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.tv_active_calorie_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.….tv_active_calorie_title)");
        this.mTvActiveCalorieTitle = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.tv_active_calorie_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.….tv_active_calorie_value)");
        this.mTvActiveCalorieValue = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_active_calorie_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.tv_active_calorie_unit)");
        this.mTvActiveCalorieUnit = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_date)");
        this.mTvDate = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.tv_active_time_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.id.tv_active_time_title)");
        this.mTvActiveTimeTitle = (TextView) viewFindViewById6;
        View viewFindViewById7 = itemView.findViewById(R.id.tv_actime_time_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById7, "itemView.findViewById(R.id.tv_actime_time_value)");
        this.mTvActiveTimeValue = (TextView) viewFindViewById7;
        View viewFindViewById8 = itemView.findViewById(R.id.tv_active_time_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById8, "itemView.findViewById(R.id.tv_active_time_unit)");
        this.mTvActiveTimeUnit = (TextView) viewFindViewById8;
        View viewFindViewById9 = itemView.findViewById(R.id.tv_walking_title);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById9, "itemView.findViewById(R.id.tv_walking_title)");
        this.mTvWalkingTitle = (TextView) viewFindViewById9;
        View viewFindViewById10 = itemView.findViewById(R.id.tv_walking_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById10, "itemView.findViewById(R.id.tv_walking_value)");
        this.mTvWalkingValue = (TextView) viewFindViewById10;
        View viewFindViewById11 = itemView.findViewById(R.id.tv_walking_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById11, "itemView.findViewById(R.id.tv_walking_unit)");
        this.mTvWalkingUnit = (TextView) viewFindViewById11;
        View viewFindViewById12 = itemView.findViewById(R.id.tv_walking_value_default);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById12, "itemView.findViewById(R.…tv_walking_value_default)");
        this.mTvWalkValueDefault = (TextView) viewFindViewById12;
        View viewFindViewById13 = itemView.findViewById(R.id.lay_walk_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById13, "itemView.findViewById(R.id.lay_walk_value)");
        this.mLayWalkValue = (LinearLayout) viewFindViewById13;
        View viewFindViewById14 = itemView.findViewById(R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById14, "itemView.findViewById(R.id.lay_content)");
        this.mLayChartContent = viewFindViewById14;
        View viewFindViewById15 = itemView.findViewById(R.id.lay_tip_bottom);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById15, "itemView.findViewById(R.id.lay_tip_bottom)");
        this.mLayLineBottom = (LinearLayout) viewFindViewById15;
        View viewFindViewById16 = itemView.findViewById(R.id.line_one);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById16, "itemView.findViewById(R.id.line_one)");
        this.mLineOne = viewFindViewById16;
        View viewFindViewById17 = itemView.findViewById(R.id.line_two);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById17, "itemView.findViewById(R.id.line_two)");
        this.mLineTwo = viewFindViewById17;
        View viewFindViewById18 = itemView.findViewById(R.id.line_three);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById18, "itemView.findViewById(R.id.line_three)");
        this.mLineThree = viewFindViewById18;
        View viewFindViewById19 = itemView.findViewById(R.id.line_four);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById19, "itemView.findViewById(R.id.line_four)");
        this.mLineFour = viewFindViewById19;
        View viewFindViewById20 = itemView.findViewById(R.id.line_five);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById20, "itemView.findViewById(R.id.line_five)");
        this.mLineFive = viewFindViewById20;
        refreshLanguage();
        setDefaultValue();
        View view = this.mLayChartContent;
        Resources resources = itemView.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "itemView.resources");
        view.setMinimumWidth((resources.getDisplayMetrics().widthPixels * 4) / 5);
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

    public final TextView getMTvWalkValueDefault() {
        return this.mTvWalkValueDefault;
    }

    public final void setMTvWalkValueDefault(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvWalkValueDefault = textView;
    }

    public final LinearLayout getMLayWalkValue() {
        return this.mLayWalkValue;
    }

    public final void setMLayWalkValue(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayWalkValue = linearLayout;
    }

    public final View getMLayChartContent() {
        return this.mLayChartContent;
    }

    public final void setMLayChartContent(View view) {
        Intrinsics.checkParameterIsNotNull(view, "<set-?>");
        this.mLayChartContent = view;
    }

    public final LinearLayout getMLayLineBottom() {
        return this.mLayLineBottom;
    }

    public final View getMLineOne() {
        return this.mLineOne;
    }

    public final View getMLineTwo() {
        return this.mLineTwo;
    }

    public final View getMLineThree() {
        return this.mLineThree;
    }

    public final View getMLineFour() {
        return this.mLineFour;
    }

    public final View getMLineFive() {
        return this.mLineFive;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTipViewHolder
    /* JADX INFO: renamed from: getContentView, reason: from getter */
    public View getMLayChartContent() {
        return this.mLayChartContent;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvActiveCalorieTitle.setText(LanguageUtil.getLanguageText(R.string.activity));
        this.mTvActiveTimeTitle.setText(LanguageUtil.getLanguageText(R.string.exercise));
        this.mTvActiveTimeUnit.setText(LanguageUtil.getLanguageText(R.string.min_unit_short));
        this.mTvWalkingTitle.setText(LanguageUtil.getLanguageText(R.string.walking));
        this.mTvWalkingUnit.setText(LanguageUtil.getLanguageText(R.string.public_unit_hr));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvActiveCalorieValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvActiveCalorieUnit.setText(RunTimeUtil.getCalorieUnit());
        this.mTvDate.setText("");
        this.mTvActiveTimeValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvWalkingValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvWalkValueDefault.setText("--");
        this.mTvWalkValueDefault.setVisibility(0);
        this.mLayWalkValue.setVisibility(8);
    }
}