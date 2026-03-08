package com.ido.life.module.home.chartdetail.vertical;

import android.view.View;
import android.widget.LinearLayout;
import com.boat.Xtend.two.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDetailComoTipViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailComoTipViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailTipViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mLayTipBottom", "Landroid/widget/LinearLayout;", "getMLayTipBottom", "()Landroid/widget/LinearLayout;", "refreshLanguage", "", "setDefaultValue", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDetailComoTipViewHolder extends BaseDetailTipViewHolder {
    private final LinearLayout mLayTipBottom;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDetailComoTipViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.lay_tip_bottom);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.lay_tip_bottom)");
        this.mLayTipBottom = (LinearLayout) viewFindViewById;
    }

    public final LinearLayout getMLayTipBottom() {
        return this.mLayTipBottom;
    }
}