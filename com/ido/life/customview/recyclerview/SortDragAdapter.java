package com.ido.life.customview.recyclerview;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.bean.SortBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SortDragAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemTouchHelperAdapter, OnItemMoveListener {
    private static final String TAG = "SortDragAdapter";
    private boolean isSortStatue;
    private final OnStartDragListener mDragStartListener;
    private OnCheckedLimitedListener onCheckedCountListener;
    private final ArrayList<SortBean> mItems = new ArrayList<>();
    private final ArrayList<SortBean> mSelectedList = new ArrayList<>();
    private final ArrayList<SortBean> mUnselectedList = new ArrayList<>();
    private final ArrayList<Integer> mFixList = new ArrayList<>();
    private int maxSelectedCount = Integer.MAX_VALUE;
    private int minSelectedCount = -1;

    public interface OnCheckedLimitedListener {
        void onCheckedCountMax();

        void onCheckedCountMin();
    }

    public SortDragAdapter(List<SortBean> list, OnStartDragListener onStartDragListener) {
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

    public void setFixData(List<Integer> list) {
        this.mFixList.clear();
        this.mFixList.addAll(list);
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
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, int i) {
        final SortBean sortBean = this.mItems.get(i);
        itemViewHolder.mTvShowStatus.setVisibility(8);
        if (sortBean == null) {
            itemViewHolder.layoutCard.setVisibility(8);
            itemViewHolder.mViewDivider.setVisibility(4);
            if (i == 0) {
                itemViewHolder.mTvShowStatus.setVisibility(0);
                if (this.mSelectedList.size() > 0) {
                    itemViewHolder.mTvShowStatus.setText(LanguageUtil.getLanguageText(R.string.home_card_title_show));
                    return;
                } else {
                    itemViewHolder.mTvShowStatus.setText(LanguageUtil.getLanguageText(R.string.home_card_title_dismiss));
                    return;
                }
            }
            if (i == this.mSelectedList.size() + 1) {
                itemViewHolder.mTvShowStatus.setVisibility(0);
                itemViewHolder.mTvShowStatus.setText(LanguageUtil.getLanguageText(R.string.home_card_title_dismiss));
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
            itemViewHolder.ivIcon.setImageResource(R.mipmap.icon_remove_disable);
        }
        if (this.mSelectedList.size() >= this.maxSelectedCount && !sortBean.isSelected()) {
            itemViewHolder.ivIcon.setVisibility(4);
        }
        itemViewHolder.ivDrag.setVisibility(sortBean.isSelected() ? 0 : 8);
        final boolean zIsSelected = sortBean.isSelected();
        itemViewHolder.layoutDrag.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.customview.recyclerview.-$$Lambda$SortDragAdapter$nosgY1wl6erm_Yd3w-oZbydzwXI
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$onBindViewHolder$0$SortDragAdapter(itemViewHolder, view, motionEvent);
            }
        });
        itemViewHolder.ivIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.recyclerview.-$$Lambda$SortDragAdapter$evUsxJlKiHfScL9E3u-GELNjcF0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$1$SortDragAdapter(zIsSelected, sortBean, view);
            }
        });
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$0$SortDragAdapter(ItemViewHolder itemViewHolder, View view, MotionEvent motionEvent) {
        OnStartDragListener onStartDragListener;
        if (motionEvent.getAction() != 0 || (onStartDragListener = this.mDragStartListener) == null) {
            return false;
        }
        onStartDragListener.onStartDrag(itemViewHolder);
        return false;
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$SortDragAdapter(boolean z, SortBean sortBean, View view) {
        if (z) {
            if (this.mFixList.size() > 0) {
                if (this.mFixList.toString().contains(sortBean.getType() + "")) {
                    return;
                }
            }
            if (this.mSelectedList.size() <= this.minSelectedCount) {
                OnCheckedLimitedListener onCheckedLimitedListener = this.onCheckedCountListener;
                if (onCheckedLimitedListener != null) {
                    onCheckedLimitedListener.onCheckedCountMin();
                    return;
                }
                return;
            }
        } else if (this.mSelectedList.size() >= this.maxSelectedCount) {
            OnCheckedLimitedListener onCheckedLimitedListener2 = this.onCheckedCountListener;
            if (onCheckedLimitedListener2 != null) {
                onCheckedLimitedListener2.onCheckedCountMax();
                return;
            }
            return;
        }
        sortBean.setSelected(!z);
        this.mItems.remove(sortBean);
        if (sortBean.isSelected()) {
            this.mItems.add(sortBean);
        } else {
            this.mItems.add(0, sortBean);
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
        if (i2 < 1) {
            i2 = 1;
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
        final LinearLayout layoutDrag;
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
            this.layoutDrag = (LinearLayout) view.findViewById(R.id.layout_drag);
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