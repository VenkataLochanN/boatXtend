package com.ido.life.module.home.rate.vertical;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RateDetailTipViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/ido/life/module/home/rate/vertical/RateDetailTipViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailComoTipViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mLayContent", "Landroid/widget/LinearLayout;", "getMLayContent", "()Landroid/widget/LinearLayout;", "mTvDate", "Landroid/widget/TextView;", "getMTvDate", "()Landroid/widget/TextView;", "mTvTitle", "getMTvTitle", "mTvUnit", "getMTvUnit", "mTvValue", "getMTvValue", "getContentView", "refreshLanguage", "", "setDefaultValue", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RateDetailTipViewHolder extends BaseDetailComoTipViewHolder {
    private final LinearLayout mLayContent;
    private final TextView mTvDate;
    private final TextView mTvTitle;
    private final TextView mTvUnit;
    private final TextView mTvValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RateDetailTipViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.lay_content);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_content)");
        this.mLayContent = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.tv_title_avg);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "itemView.findViewById(R.id.tv_title_avg)");
        this.mTvTitle = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.tv_avg_value);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "itemView.findViewById(R.id.tv_avg_value)");
        this.mTvValue = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.tv_state);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "itemView.findViewById(R.id.tv_state)");
        this.mTvUnit = (TextView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "itemView.findViewById(R.id.tv_date)");
        this.mTvDate = (TextView) viewFindViewById5;
        setDefaultValue();
        refreshLanguage();
    }

    public final LinearLayout getMLayContent() {
        return this.mLayContent;
    }

    public final TextView getMTvTitle() {
        return this.mTvTitle;
    }

    public final TextView getMTvValue() {
        return this.mTvValue;
    }

    public final TextView getMTvUnit() {
        return this.mTvUnit;
    }

    public final TextView getMTvDate() {
        return this.mTvDate;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTipViewHolder
    /* JADX INFO: renamed from: getContentView, reason: from getter */
    public LinearLayout getMLayContent() {
        return this.mLayContent;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder, com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitle.setText(LanguageUtil.getLanguageText(R.string.detail_average_daily));
        this.mTvUnit.setText(LanguageUtil.getLanguageText(R.string.device_heart_rate_unit));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailComoTipViewHolder, com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvValue.setText("--");
    }
}