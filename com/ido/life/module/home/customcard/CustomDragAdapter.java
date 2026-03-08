package com.ido.life.module.home.customcard;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.bean.SortBean;
import com.ido.life.customview.recyclerview.ItemTouchHelperAdapter;
import com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder;
import com.ido.life.customview.recyclerview.OnItemMoveListener;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CustomDragAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemTouchHelperAdapter, OnItemMoveListener {
    private static final String TAG = "CustomDragAdapter";
    private boolean isSortStatue;
    private final OnStartDragListener mDragStartListener;
    private final ArrayList<SortBean> mItems = new ArrayList<>();
    private final ArrayList<SortBean> mSelectedList = new ArrayList<>();
    private final ArrayList<SortBean> mUnselectedList = new ArrayList<>();
    private int maxSelectedCount = Integer.MAX_VALUE;
    private int minSelectedCount = -1;
    private OnCheckedLimitedListener onCheckedCountListener;

    public interface OnCheckedLimitedListener {
        void onCheckedCountMax();

        void onCheckedCountMin();
    }

    public CustomDragAdapter(List<SortBean> list, OnStartDragListener onStartDragListener) {
        this.mDragStartListener = onStartDragListener;
        initDataList(list);
    }

    private void initDataList(List<SortBean> list) {
        refreshData(list);
        notifyDataSetChanged();
    }

    private void refreshData(List<SortBean> list) {
        this.mSelectedList.clear();
        this.mUnselectedList.clear();
        if (list != null) {
            for (SortBean sortBean : list) {
                if (sortBean != null) {
                    if (sortBean.isSelected()) {
                        this.mSelectedList.add(sortBean);
                    } else {
                        this.mUnselectedList.add(sortBean);
                    }
                }
            }
        }
        this.mItems.clear();
        if (this.mSelectedList.size() > 0) {
            this.mItems.add(null);
            this.mItems.addAll(this.mSelectedList);
        }
        if (this.mUnselectedList.size() > 0) {
            this.mItems.add(null);
            this.mItems.addAll(this.mUnselectedList);
        }
    }

    public boolean isSortStatue() {
        return this.isSortStatue;
    }

    public void setSortStatue(boolean z) {
        this.isSortStatue = z;
        notifyDataSetChanged();
    }

    public void setData(List<SortBean> list) {
        initDataList(list);
    }

    public void setLimitedCount(int i, int i2) {
        this.maxSelectedCount = i;
        this.minSelectedCount = i2;
    }

    public void setOnCheckedCountListener(OnCheckedLimitedListener onCheckedLimitedListener) {
        this.onCheckedCountListener = onCheckedLimitedListener;
    }

    public ArrayList<SortBean> getDatas() {
        return this.mItems;
    }

    public ArrayList<SortBean> getSelectedList() {
        return this.mSelectedList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drage_comm, viewGroup, false), this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        final SortBean sortBean = this.mItems.get(i);
        itemViewHolder.mTvShowStatus.setVisibility(8);
        if (sortBean == null) {
            itemViewHolder.layoutCard.setVisibility(8);
            itemViewHolder.mViewDivider.setVisibility(4);
            if (i == 0) {
                itemViewHolder.mTvShowStatus.setVisibility(0);
                if (this.mSelectedList.size() > 0) {
                    itemViewHolder.mTvShowStatus.setText(LanguageUtil.getLanguageText(R.string.home_card_edit_title_show));
                    return;
                } else {
                    itemViewHolder.mTvShowStatus.setText(LanguageUtil.getLanguageText(R.string.home_card_edit_title_dismiss));
                    return;
                }
            }
            if (i == this.mSelectedList.size() + 1) {
                itemViewHolder.mTvShowStatus.setVisibility(0);
                itemViewHolder.mTvShowStatus.setText(LanguageUtil.getLanguageText(R.string.home_card_edit_title_dismiss));
                return;
            }
            return;
        }
        if (i == this.mSelectedList.size()) {
            itemViewHolder.mViewDivider.setVisibility(4);
        } else {
            itemViewHolder.mViewDivider.setVisibility(0);
        }
        itemViewHolder.layoutCard.setVisibility(0);
        itemViewHolder.mtvName.setText(LanguageUtil.getLanguageText(sortBean.getNameId()));
        itemViewHolder.ivIcon.setVisibility(0);
        itemViewHolder.ivIcon.setImageResource(sortBean.isSelected() ? R.mipmap.icon_remove : R.mipmap.icon_add);
        if (this.mSelectedList.size() <= this.minSelectedCount && sortBean.isSelected()) {
            itemViewHolder.ivIcon.setVisibility(4);
        }
        if (this.mSelectedList.size() >= this.maxSelectedCount && !sortBean.isSelected()) {
            itemViewHolder.ivIcon.setVisibility(4);
        }
        itemViewHolder.ivDrag.setVisibility(sortBean.isSelected() ? 0 : 8);
        if (i == 1 && this.mSelectedList.size() > 0 && this.mSelectedList.get(0).getType() == 1) {
            itemViewHolder.ivDrag.setVisibility(4);
        }
        final boolean zIsSelected = sortBean.isSelected();
        itemViewHolder.ivDrag.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.module.home.customcard.-$$Lambda$CustomDragAdapter$koeBp8YrXndUooq9MCjxCgKU9jI
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$onBindViewHolder$0$CustomDragAdapter(i, itemViewHolder, view, motionEvent);
            }
        });
        itemViewHolder.ivIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.customcard.-$$Lambda$CustomDragAdapter$oXZErF6KqFW1RJIpZh3vxg6Q9aM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$1$CustomDragAdapter(zIsSelected, sortBean, view);
            }
        });
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$0$CustomDragAdapter(int i, ItemViewHolder itemViewHolder, View view, MotionEvent motionEvent) {
        OnStartDragListener onStartDragListener;
        if (motionEvent.getAction() == 0 && ((i != 1 || this.mSelectedList.get(0).getType() != 1) && (onStartDragListener = this.mDragStartListener) != null)) {
            onStartDragListener.onStartDrag(itemViewHolder);
        }
        return false;
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$CustomDragAdapter(boolean z, SortBean sortBean, View view) {
        if (this.onCheckedCountListener != null) {
            if (z) {
                if (this.mSelectedList.size() <= this.minSelectedCount) {
                    this.onCheckedCountListener.onCheckedCountMin();
                    return;
                }
            } else if (this.mSelectedList.size() >= this.maxSelectedCount) {
                this.onCheckedCountListener.onCheckedCountMax();
                return;
            }
        }
        sortBean.setSelected(!z);
        this.mItems.remove(sortBean);
        if (!sortBean.isSelected() || sortBean.getType() == 1) {
            this.mItems.add(0, sortBean);
        } else {
            this.mItems.add(sortBean);
        }
        initDataList(this.mItems);
    }

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public void onItemDismiss(int i) {
        this.mItems.remove(i);
        notifyItemRemoved(i);
    }

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public boolean onItemMove(int i, int i2) {
        if (i2 >= this.mSelectedList.size()) {
            i2 = this.mSelectedList.size();
        }
        ArrayList<SortBean> arrayList = this.mSelectedList;
        if (arrayList == null || arrayList.size() <= 2) {
            if (i2 < 1) {
                i2 = 1;
            }
        } else if (this.mSelectedList.get(0).getType() == 1 && i2 < 2) {
            i2 = 2;
        }
        Collections.swap(this.mItems, i, i2);
        notifyItemMoved(i, i2);
        refreshData(this.mItems);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    @Override // com.ido.life.customview.recyclerview.OnItemMoveListener
    public void onItemMoveCompleted() {
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        final View ivDrag;
        final ImageView ivIcon;
        final View layoutCard;
        final OnItemMoveListener mItemMoveListener;
        final TextView mTvShowStatus;
        final View mViewDivider;
        final RegularTextView mtvName;

        ItemViewHolder(View view, OnItemMoveListener onItemMoveListener) {
            super(view);
            this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            this.mtvName = (RegularTextView) view.findViewById(R.id.mtv_name);
            this.layoutCard = view.findViewById(R.id.layout_card);
            this.ivDrag = view.findViewById(R.id.iv_drag);
            this.mTvShowStatus = (TextView) view.findViewById(R.id.tv_show_status);
            this.mViewDivider = view.findViewById(R.id.view_divider);
            this.mItemMoveListener = onItemMoveListener;
        }

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemSelected() {
            this.itemView.setBackgroundResource(R.color.color_66979797);
            this.mViewDivider.setVisibility(4);
        }

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
            OnItemMoveListener onItemMoveListener = this.mItemMoveListener;
            if (onItemMoveListener != null) {
                onItemMoveListener.onItemMoveCompleted();
            }
        }
    }
}