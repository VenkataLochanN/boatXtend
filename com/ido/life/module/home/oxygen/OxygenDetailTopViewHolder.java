package com.ido.life.module.home.oxygen;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OxygenDetailTopViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001eH\u0016J\b\u0010 \u001a\u00020\u001eH\u0016J\u0012\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001eH\u0016J\b\u0010%\u001a\u00020\u001eH\u0016J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020(H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0017\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R\u001a\u0010\u001a\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010¨\u0006)"}, d2 = {"Lcom/ido/life/module/home/oxygen/OxygenDetailTopViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailTopViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mLayAvg", "Landroid/widget/LinearLayout;", "getMLayAvg", "()Landroid/widget/LinearLayout;", "setMLayAvg", "(Landroid/widget/LinearLayout;)V", "mTvAvg", "Landroid/widget/TextView;", "getMTvAvg", "()Landroid/widget/TextView;", "setMTvAvg", "(Landroid/widget/TextView;)V", "mTvDate", "getMTvDate", "setMTvDate", "mTvState", "getMTvState", "setMTvState", "mTvTitleAvg", "getMTvTitleAvg", "setMTvTitleAvg", "mTvUnit", "getMTvUnit", "setMTvUnit", "hideRightLayout", "", "refreshLanguage", "setDefaultValue", "showLoadFailedView", "clickListener", "Landroid/view/View$OnClickListener;", "showLoadingView", "showRightLayout", "showSuccessView", "showRight", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class OxygenDetailTopViewHolder extends BaseDetailTopViewHolder {
    private LinearLayout mLayAvg;
    private TextView mTvAvg;
    private TextView mTvDate;
    private TextView mTvState;
    private TextView mTvTitleAvg;
    private TextView mTvUnit;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OxygenDetailTopViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.lay_avg);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_avg)");
        this.mLayAvg = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.tv_title_avg);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.tv_title_avg)");
        this.mTvTitleAvg = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.tv_avg);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.tv_avg)");
        this.mTvAvg = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_unit);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.tv_unit)");
        this.mTvUnit = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_state)");
        this.mTvState = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "itemView.findViewById(R.id.tv_date)");
        this.mTvDate = (TextView) viewFindViewById6;
        setDefaultValue();
        refreshLanguage();
    }

    public final LinearLayout getMLayAvg() {
        return this.mLayAvg;
    }

    public final void setMLayAvg(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayAvg = linearLayout;
    }

    public final TextView getMTvTitleAvg() {
        return this.mTvTitleAvg;
    }

    public final void setMTvTitleAvg(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTitleAvg = textView;
    }

    public final TextView getMTvAvg() {
        return this.mTvAvg;
    }

    public final void setMTvAvg(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvAvg = textView;
    }

    public final TextView getMTvUnit() {
        return this.mTvUnit;
    }

    public final void setMTvUnit(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvUnit = textView;
    }

    public final TextView getMTvState() {
        return this.mTvState;
    }

    public final void setMTvState(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvState = textView;
    }

    public final TextView getMTvDate() {
        return this.mTvDate;
    }

    public final void setMTvDate(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvDate = textView;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleAvg.setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
        this.mTvUnit.setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_unit));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvAvg.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvDate.setText("");
        this.mTvState.setText("");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showSuccessView(boolean showRight) {
        stopAnimator();
        this.mLayAvg.setVisibility(0);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(8);
        this.mLayLoading.setOnClickListener(null);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadFailedView(View.OnClickListener clickListener) {
        stopAnimator();
        this.mLayAvg.setVisibility(4);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(0);
        ImageView mImgLoading = this.mImgLoading;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoading, "mImgLoading");
        mImgLoading.setVisibility(8);
        TextView mTvLoadingState = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState, "mTvLoadingState");
        mTvLoadingState.setVisibility(0);
        ImageView mImgLoadFailed = this.mImgLoadFailed;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoadFailed, "mImgLoadFailed");
        mImgLoadFailed.setVisibility(0);
        this.mLayLoading.setOnClickListener(clickListener);
        TextView mTvLoadingState2 = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState2, "mTvLoadingState");
        mTvLoadingState2.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        this.mLayAvg.setVisibility(4);
        LinearLayout mLayLoading = this.mLayLoading;
        Intrinsics.checkExpressionValueIsNotNull(mLayLoading, "mLayLoading");
        mLayLoading.setVisibility(0);
        ImageView mImgLoading = this.mImgLoading;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoading, "mImgLoading");
        mImgLoading.setVisibility(0);
        TextView mTvLoadingState = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState, "mTvLoadingState");
        mTvLoadingState.setVisibility(0);
        ImageView mImgLoadFailed = this.mImgLoadFailed;
        Intrinsics.checkExpressionValueIsNotNull(mImgLoadFailed, "mImgLoadFailed");
        mImgLoadFailed.setVisibility(8);
        TextView mTvLoadingState2 = this.mTvLoadingState;
        Intrinsics.checkExpressionValueIsNotNull(mTvLoadingState2, "mTvLoadingState");
        mTvLoadingState2.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
        this.mLayLoading.setOnClickListener(null);
        startAnimator();
    }
}