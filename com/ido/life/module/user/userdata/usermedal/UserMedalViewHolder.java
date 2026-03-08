package com.ido.life.module.user.userdata.usermedal;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserMedalViewHolder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\"\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\r0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\n \u0007*\u0004\u0018\u00010\u00130\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\n \u0007*\u0004\u0018\u00010\u00130\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\"\u0010\u001b\u001a\n \u0007*\u0004\u0018\u00010\u001c0\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/ido/life/module/user/userdata/usermedal/UserMedalViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mImg", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getMImg", "()Landroid/widget/ImageView;", "setMImg", "(Landroid/widget/ImageView;)V", "mLayMedal", "Landroid/widget/LinearLayout;", "getMLayMedal", "()Landroid/widget/LinearLayout;", "setMLayMedal", "(Landroid/widget/LinearLayout;)V", "mTvMedalDesc", "Landroid/widget/TextView;", "getMTvMedalDesc", "()Landroid/widget/TextView;", "setMTvMedalDesc", "(Landroid/widget/TextView;)V", "mTvMedalTitle", "getMTvMedalTitle", "setMTvMedalTitle", "mTvTitle", "Lcom/ido/common/widget/textview/RegularTextView;", "getMTvTitle", "()Lcom/ido/common/widget/textview/RegularTextView;", "setMTvTitle", "(Lcom/ido/common/widget/textview/RegularTextView;)V", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMedalViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImg;
    private LinearLayout mLayMedal;
    private TextView mTvMedalDesc;
    private TextView mTvMedalTitle;
    private RegularTextView mTvTitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserMedalViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        this.mTvTitle = (RegularTextView) itemView.findViewById(R.id.tv_title);
        this.mLayMedal = (LinearLayout) itemView.findViewById(R.id.lay_medal);
        this.mImg = (ImageView) itemView.findViewById(R.id.img);
        this.mTvMedalTitle = (TextView) itemView.findViewById(R.id.tv_medal_title);
        this.mTvMedalDesc = (TextView) itemView.findViewById(R.id.tv_medal_desc);
    }

    public final RegularTextView getMTvTitle() {
        return this.mTvTitle;
    }

    public final void setMTvTitle(RegularTextView regularTextView) {
        this.mTvTitle = regularTextView;
    }

    public final LinearLayout getMLayMedal() {
        return this.mLayMedal;
    }

    public final void setMLayMedal(LinearLayout linearLayout) {
        this.mLayMedal = linearLayout;
    }

    public final ImageView getMImg() {
        return this.mImg;
    }

    public final void setMImg(ImageView imageView) {
        this.mImg = imageView;
    }

    public final TextView getMTvMedalTitle() {
        return this.mTvMedalTitle;
    }

    public final void setMTvMedalTitle(TextView textView) {
        this.mTvMedalTitle = textView;
    }

    public final TextView getMTvMedalDesc() {
        return this.mTvMedalDesc;
    }

    public final void setMTvMedalDesc(TextView textView) {
        this.mTvMedalDesc = textView;
    }
}