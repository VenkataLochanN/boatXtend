package com.ido.life.module.user.feedback;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.bumptech.glide.Glide;
import com.ido.common.IdoApp;
import com.ido.common.utils.ResourceUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedbackPicAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0018H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Lcom/ido/life/module/user/feedback/FeedbackPicAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/module/user/feedback/FeedbackViewHolder;", "mList", "", "", "mClickListenter", "Landroid/view/View$OnClickListener;", "mLayoutInflater", "Landroid/view/LayoutInflater;", "(Ljava/util/List;Landroid/view/View$OnClickListener;Landroid/view/LayoutInflater;)V", "getMClickListenter", "()Landroid/view/View$OnClickListener;", "setMClickListenter", "(Landroid/view/View$OnClickListener;)V", "getMLayoutInflater", "()Landroid/view/LayoutInflater;", "setMLayoutInflater", "(Landroid/view/LayoutInflater;)V", "getMList", "()Ljava/util/List;", "setMList", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FeedbackPicAdapter extends RecyclerView.Adapter<FeedbackViewHolder> {
    private View.OnClickListener mClickListenter;
    private LayoutInflater mLayoutInflater;
    private List<String> mList;

    public final View.OnClickListener getMClickListenter() {
        return this.mClickListenter;
    }

    public final List<String> getMList() {
        return this.mList;
    }

    public final void setMClickListenter(View.OnClickListener onClickListener) {
        Intrinsics.checkParameterIsNotNull(onClickListener, "<set-?>");
        this.mClickListenter = onClickListener;
    }

    public final void setMList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mList = list;
    }

    public FeedbackPicAdapter(List<String> mList, View.OnClickListener mClickListenter, LayoutInflater mLayoutInflater) {
        Intrinsics.checkParameterIsNotNull(mList, "mList");
        Intrinsics.checkParameterIsNotNull(mClickListenter, "mClickListenter");
        Intrinsics.checkParameterIsNotNull(mLayoutInflater, "mLayoutInflater");
        this.mList = mList;
        this.mClickListenter = mClickListenter;
        this.mLayoutInflater = mLayoutInflater;
    }

    public final LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }

    public final void setMLayoutInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "<set-?>");
        this.mLayoutInflater = layoutInflater;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        ImageView mImg = holder.getMImg();
        Intrinsics.checkExpressionValueIsNotNull(mImg, "holder.mImg");
        Drawable drawable = (Drawable) null;
        mImg.setBackground(drawable);
        holder.getMImg().setImageDrawable(null);
        String str = this.mList.get(position);
        holder.getMLayItem().setOnClickListener(null);
        holder.getMImgDelete().setOnClickListener(null);
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            ImageView mImgDelete = holder.getMImgDelete();
            Intrinsics.checkExpressionValueIsNotNull(mImgDelete, "holder.mImgDelete");
            mImgDelete.setVisibility(8);
            ImageView mImg2 = holder.getMImg();
            Intrinsics.checkExpressionValueIsNotNull(mImg2, "holder.mImg");
            mImg2.setVisibility(0);
            ImageView mImg3 = holder.getMImg();
            Intrinsics.checkExpressionValueIsNotNull(mImg3, "holder.mImg");
            mImg3.setBackground(ResourceUtil.getDrawable(R.mipmap.ic_fd_add));
            ConstraintLayout mLayItem = holder.getMLayItem();
            Intrinsics.checkExpressionValueIsNotNull(mLayItem, "holder.mLayItem");
            mLayItem.setTag(Integer.valueOf(position));
            holder.getMLayItem().setOnClickListener(this.mClickListenter);
            return;
        }
        ImageView mImg4 = holder.getMImg();
        Intrinsics.checkExpressionValueIsNotNull(mImg4, "holder.mImg");
        mImg4.setVisibility(0);
        ImageView mImgDelete2 = holder.getMImgDelete();
        Intrinsics.checkExpressionValueIsNotNull(mImgDelete2, "holder.mImgDelete");
        mImgDelete2.setVisibility(0);
        ImageView mImg5 = holder.getMImg();
        Intrinsics.checkExpressionValueIsNotNull(mImg5, "holder.mImg");
        mImg5.setBackground(drawable);
        Context appContext = IdoApp.getAppContext();
        Intrinsics.checkExpressionValueIsNotNull(appContext, "IdoApp.getAppContext()");
        Glide.with(appContext.getApplicationContext()).load(this.mList.get(position)).into(holder.getMImg());
        ImageView mImgDelete3 = holder.getMImgDelete();
        Intrinsics.checkExpressionValueIsNotNull(mImgDelete3, "holder.mImgDelete");
        mImgDelete3.setTag(Integer.valueOf(position));
        holder.getMImgDelete().setOnClickListener(this.mClickListenter);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = this.mLayoutInflater.inflate(R.layout.item_feedback_pic, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "mLayoutInflater.inflate(…t.item_feedback_pic,null)");
        return new FeedbackViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}