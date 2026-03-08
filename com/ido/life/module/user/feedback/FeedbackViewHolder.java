package com.ido.life.module.user.feedback;

import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedbackViewHolder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\"\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\"\u0010\u000f\u001a\n \u0007*\u0004\u0018\u00010\u00100\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/ido/life/module/user/feedback/FeedbackViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mImg", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getMImg", "()Landroid/widget/ImageView;", "setMImg", "(Landroid/widget/ImageView;)V", "mImgDelete", "getMImgDelete", "setMImgDelete", "mLayItem", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getMLayItem", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setMLayItem", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FeedbackViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImg;
    private ImageView mImgDelete;
    private ConstraintLayout mLayItem;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeedbackViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        this.mImg = (ImageView) itemView.findViewById(R.id.feedback_iv_pic);
        this.mImgDelete = (ImageView) itemView.findViewById(R.id.feedback_iv_delete);
        this.mLayItem = (ConstraintLayout) itemView.findViewById(R.id.lay_item);
    }

    public final ImageView getMImg() {
        return this.mImg;
    }

    public final void setMImg(ImageView imageView) {
        this.mImg = imageView;
    }

    public final ImageView getMImgDelete() {
        return this.mImgDelete;
    }

    public final void setMImgDelete(ImageView imageView) {
        this.mImgDelete = imageView;
    }

    public final ConstraintLayout getMLayItem() {
        return this.mLayItem;
    }

    public final void setMLayItem(ConstraintLayout constraintLayout) {
        this.mLayItem = constraintLayout;
    }
}