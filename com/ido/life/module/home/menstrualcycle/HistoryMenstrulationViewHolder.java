package com.ido.life.module.home.menstrualcycle;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.customview.MenstrulationProgressView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HistoryMenstrulationViewHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0010\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/HistoryMenstrulationViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mProgres", "Lcom/ido/life/customview/MenstrulationProgressView;", "kotlin.jvm.PlatformType", "getMProgres", "()Lcom/ido/life/customview/MenstrulationProgressView;", "mTvDateAreaDesc", "Landroid/widget/TextView;", "getMTvDateAreaDesc", "()Landroid/widget/TextView;", "mTvDateDesc", "getMTvDateDesc", "mTvYear", "getMTvYear", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HistoryMenstrulationViewHolder extends RecyclerView.ViewHolder {
    private final MenstrulationProgressView mProgres;
    private final TextView mTvDateAreaDesc;
    private final TextView mTvDateDesc;
    private final TextView mTvYear;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HistoryMenstrulationViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        this.mTvDateAreaDesc = (TextView) itemView.findViewById(R.id.tv_menstrulation_history_value_one);
        this.mTvDateDesc = (TextView) itemView.findViewById(R.id.tv_mensulation_history_desc_one);
        this.mProgres = (MenstrulationProgressView) itemView.findViewById(R.id.progress_menstrulation_one);
        this.mTvYear = (TextView) itemView.findViewById(R.id.tv_year);
        Resources resources = itemView.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "itemView.resources");
        itemView.setMinimumWidth(resources.getDisplayMetrics().widthPixels - (itemView.getResources().getDimensionPixelSize(R.dimen.sw_dp_16) * 4));
    }

    public final TextView getMTvDateAreaDesc() {
        return this.mTvDateAreaDesc;
    }

    public final TextView getMTvDateDesc() {
        return this.mTvDateDesc;
    }

    public final MenstrulationProgressView getMProgres() {
        return this.mProgres;
    }

    public final TextView getMTvYear() {
        return this.mTvYear;
    }
}