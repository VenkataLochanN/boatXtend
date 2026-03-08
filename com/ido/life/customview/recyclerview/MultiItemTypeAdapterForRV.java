package com.ido.life.customview.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MultiItemTypeAdapterForRV<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;
    protected ItemViewDelegateManagerForRV mItemViewDelegateManager = new ItemViewDelegateManagerForRV();
    protected OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i);
    }

    protected boolean isEnabled(int i) {
        return true;
    }

    public void onViewHolderCreated(CommonRecyclerViewHolder commonRecyclerViewHolder, View view) {
    }

    public MultiItemTypeAdapterForRV(Context context, List<T> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return !useItemViewDelegateManager() ? super.getItemViewType(i) : this.mItemViewDelegateManager.getItemViewType(this.mDatas.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CommonRecyclerViewHolder commonRecyclerViewHolderCreateViewHolder = CommonRecyclerViewHolder.createViewHolder(this.mContext, viewGroup, this.mItemViewDelegateManager.getItemViewDelegate(i).getItemViewLayoutId());
        onViewHolderCreated(commonRecyclerViewHolderCreateViewHolder, commonRecyclerViewHolderCreateViewHolder.getConvertView());
        setListener(viewGroup, commonRecyclerViewHolderCreateViewHolder, i);
        return commonRecyclerViewHolderCreateViewHolder;
    }

    public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, T t) {
        this.mItemViewDelegateManager.convert(commonRecyclerViewHolder, t, commonRecyclerViewHolder.getAdapterPosition());
    }

    protected void setListener(ViewGroup viewGroup, final CommonRecyclerViewHolder commonRecyclerViewHolder, int i) {
        if (isEnabled(i)) {
            commonRecyclerViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MultiItemTypeAdapterForRV.this.mOnItemClickListener != null) {
                        MultiItemTypeAdapterForRV.this.mOnItemClickListener.onItemClick(view, commonRecyclerViewHolder, commonRecyclerViewHolder.getAdapterPosition());
                    }
                }
            });
            commonRecyclerViewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    if (MultiItemTypeAdapterForRV.this.mOnItemClickListener == null) {
                        return false;
                    }
                    return MultiItemTypeAdapterForRV.this.mOnItemClickListener.onItemLongClick(view, commonRecyclerViewHolder, commonRecyclerViewHolder.getAdapterPosition());
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CommonRecyclerViewHolder commonRecyclerViewHolder, int i) {
        convert(commonRecyclerViewHolder, this.mDatas.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<T> getDatas() {
        return this.mDatas;
    }

    public void addAll(List<T> list) {
        this.mDatas.clear();
        if (list != null) {
            this.mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    public MultiItemTypeAdapterForRV addItemViewDelegate(int i, ItemViewDelegateForRV<T> itemViewDelegateForRV) {
        this.mItemViewDelegateManager.addDelegate(i, itemViewDelegateForRV);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return this.mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}