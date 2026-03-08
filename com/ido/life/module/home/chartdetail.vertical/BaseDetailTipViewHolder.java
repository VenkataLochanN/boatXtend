package com.ido.life.module.home.chartdetail.vertical;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDetailTipViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailTipViewHolder;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "getContentView", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDetailTipViewHolder extends BaseDetailViewHolder {
    public abstract View getContentView();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDetailTipViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
    }
}