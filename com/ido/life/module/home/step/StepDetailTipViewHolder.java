package com.ido.life.module.home.step;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StepDetailTipViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0017\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/ido/life/module/home/step/StepDetailTipViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailComoTipViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mLayTipContent", "Landroid/widget/LinearLayout;", "getMLayTipContent", "()Landroid/widget/LinearLayout;", "setMLayTipContent", "(Landroid/widget/LinearLayout;)V", "mTvTipAvg", "Landroid/widget/TextView;", "getMTvTipAvg", "()Landroid/widget/TextView;", "setMTvTipAvg", "(Landroid/widget/TextView;)V", "mTvTipDate", "getMTvTipDate", "setMTvTipDate", "mTvTipState", "getMTvTipState", "setMTvTipState", "mTvTipTitleAvg", "getMTvTipTitleAvg", "setMTvTipTitleAvg", "getContentView", "refreshLanguage", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class StepDetailTipViewHolder extends BaseDetailComoTipViewHolder {
    private LinearLayout mLayTipContent;
    private TextView mTvTipAvg;
    private TextView mTvTipDate;
    private TextView mTvTipState;
    private TextView mTvTipTitleAvg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StepDetailTipViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_content)");
        this.mLayTipContent = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.tv_title_avg);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.tv_title_avg)");
        this.mTvTipTitleAvg = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.tv_avg_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.tv_avg_value)");
        this.mTvTipAvg = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.tv_state)");
        this.mTvTipState = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_date)");
        this.mTvTipDate = (TextView) viewFindViewById5;
        setDefaultValue();
        refreshLanguage();
    }

    public final LinearLayout getMLayTipContent() {
        return this.mLayTipContent;
    }

    public final void setMLayTipContent(LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "<set-?>");
        this.mLayTipContent = linearLayout;
    }

    public final TextView getMTvTipTitleAvg() {
        return this.mTvTipTitleAvg;
    }

    public final void setMTvTipTitleAvg(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTipTitleAvg = textView;
    }

    public final TextView getMTvTipAvg() {
        return this.mTvTipAvg;
    }

    public final void setMTvTipAvg(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTipAvg = textView;
    }

    public final TextView getMTvTipState() {
        return this.mTvTipState;
    }

    public final void setMTvTipState(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTipState = textView;
    }

    public final TextView getMTvTipDate() {
        return this.mTvTipDate;
    }

    public final void setMTvTipDate(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
        this.mTvTipDate = textView;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTipViewHolder
    /* JADX INFO: renamed from: getContentView */
    public View getMLayContent() {
        return this.mLayTipContent;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder, com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTipState.setText(LanguageUtil.getLanguageText(R.string.public_sport_step));
    }
}