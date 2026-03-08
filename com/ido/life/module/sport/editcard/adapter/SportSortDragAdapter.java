package com.ido.life.module.sport.editcard.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.bean.SortBean;
import com.ido.life.customview.recyclerview.ItemTouchHelperAdapter;
import com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportSortDragAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemTouchHelperAdapter {
    private static final String TAG = "SortDragAdapter";
    private boolean isSortStatue;
    private final OnStartDragListener mDragStartListener;
    private boolean mIsCanDelete;
    private int mType;
    private OnCheckedLimitedListener onCheckedCountListener;
    private final ArrayList<SortBean> mItems = new ArrayList<>();
    private final ArrayList<SortBean> mSelectedList = new ArrayList<>();
    private final ArrayList<SortBean> mUnselectedList = new ArrayList<>();
    private int maxCheckedCount = Integer.MAX_VALUE;
    private int minCheckedCount = -1;
    private int mEffectCount = 3;

    public interface OnCheckedLimitedListener {
        void onCheckedCountMax();

        void onCheckedCountMin();
    }

    public SportSortDragAdapter(List<SortBean> list, OnStartDragListener onStartDragListener, int i) {
        this.mType = 0;
        this.mDragStartListener = onStartDragListener;
        this.mType = i;
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
        if (this.mSelectedList.size() > 0 && this.mUnselectedList.size() > 0) {
            this.mItems.add(null);
        }
        this.mItems.add(0, null);
        this.mItems.addAll(1, this.mSelectedList);
        this.mItems.addAll(this.mUnselectedList);
        this.mIsCanDelete = this.mSelectedList.size() > this.mEffectCount;
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
        this.maxCheckedCount = i;
        this.minCheckedCount = i2;
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
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drage, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, int i) {
        if (i == 0) {
            ArrayList<SortBean> arrayList = this.mSelectedList;
            if (arrayList != null && arrayList.size() > 0) {
                itemViewHolder.layoutCard.setVisibility(8);
                itemViewHolder.divider.setVisibility(8);
                itemViewHolder.mTvSportDismiss.setVisibility(8);
                itemViewHolder.mTvSportShow.setVisibility(0);
                setShowTvSportText(itemViewHolder.mTvSportShow);
                itemViewHolder.mViewDivider.setVisibility(8);
                return;
            }
            itemViewHolder.layoutCard.setVisibility(8);
            itemViewHolder.divider.setVisibility(8);
            itemViewHolder.mTvSportDismiss.setVisibility(0);
            itemViewHolder.mTvSportShow.setVisibility(8);
            itemViewHolder.mViewDivider.setVisibility(8);
            return;
        }
        final SortBean sortBean = this.mItems.get(i);
        if (sortBean == null) {
            itemViewHolder.layoutCard.setVisibility(8);
            itemViewHolder.divider.setVisibility(8);
            itemViewHolder.mTvSportShow.setVisibility(8);
            itemViewHolder.mTvSportDismiss.setVisibility(0);
            setDismissTvSportText(itemViewHolder.mTvSportDismiss);
            itemViewHolder.mViewDivider.setVisibility(8);
            return;
        }
        if (i == this.mSelectedList.size()) {
            itemViewHolder.mViewDivider.setVisibility(8);
        } else {
            itemViewHolder.mViewDivider.setVisibility(0);
        }
        itemViewHolder.layoutCard.setVisibility(0);
        itemViewHolder.divider.setVisibility(8);
        itemViewHolder.mTvSportDismiss.setVisibility(8);
        int i2 = this.mType;
        int i3 = R.mipmap.icon_remove;
        int i4 = R.mipmap.icon_add;
        if (i2 == 2) {
            if (this.mIsCanDelete) {
                ImageView imageView = itemViewHolder.ivIcon;
                if (!sortBean.isSelected()) {
                    i3 = R.mipmap.icon_add;
                }
                imageView.setImageResource(i3);
            } else {
                ImageView imageView2 = itemViewHolder.ivIcon;
                if (sortBean.isSelected()) {
                    i4 = R.mipmap.ic_sport_enable_card_delete;
                }
                imageView2.setImageResource(i4);
            }
        } else {
            ImageView imageView3 = itemViewHolder.ivIcon;
            if (!sortBean.isSelected()) {
                i3 = R.mipmap.icon_add;
            }
            imageView3.setImageResource(i3);
        }
        itemViewHolder.mtvName.setText(LanguageUtil.getLanguageText(sortBean.getNameId()));
        itemViewHolder.ivDrag.setVisibility(sortBean.isSelected() ? 0 : 8);
        final boolean zIsSelected = sortBean.isSelected();
        itemViewHolder.ivDrag.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.module.sport.editcard.adapter.-$$Lambda$SportSortDragAdapter$kwJDFVhJd4Ia9d0_-xgb-Y6G0tA
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$onBindViewHolder$0$SportSortDragAdapter(itemViewHolder, view, motionEvent);
            }
        });
        itemViewHolder.ivIcon.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.editcard.adapter.-$$Lambda$SportSortDragAdapter$LldxLhZXQH7m7iavbxC2QfAAB4A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$1$SportSortDragAdapter(zIsSelected, sortBean, view);
            }
        });
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$0$SportSortDragAdapter(ItemViewHolder itemViewHolder, View view, MotionEvent motionEvent) {
        OnStartDragListener onStartDragListener;
        if (motionEvent.getAction() != 0 || (onStartDragListener = this.mDragStartListener) == null) {
            return false;
        }
        onStartDragListener.onStartDrag(itemViewHolder);
        return false;
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$SportSortDragAdapter(boolean z, SortBean sortBean, View view) {
        if (this.onCheckedCountListener != null) {
            CommonLogUtil.d(TAG, "onBindViewHolder: " + this.mSelectedList.size());
            if (z) {
                if (this.mSelectedList.size() <= this.mEffectCount && this.mType == 2) {
                    return;
                }
                if (this.mSelectedList.size() <= this.minCheckedCount) {
                    this.onCheckedCountListener.onCheckedCountMin();
                    return;
                }
            } else if (this.mSelectedList.size() >= this.maxCheckedCount) {
                this.onCheckedCountListener.onCheckedCountMax();
                return;
            }
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

    private void setShowTvSportText(TextView textView) {
        int i = this.mType;
        if (i == 1) {
            textView.setText(LanguageUtil.getLanguageText(R.string.home_card_edit_title_show));
        } else {
            if (i != 2) {
                return;
            }
            textView.setText(LanguageUtil.getLanguageText(R.string.sport_card_title_show));
        }
    }

    private void setDismissTvSportText(TextView textView) {
        int i = this.mType;
        if (i == 1) {
            textView.setText(LanguageUtil.getLanguageText(R.string.home_card_edit_title_dismiss));
        } else {
            if (i != 2) {
                return;
            }
            textView.setText(LanguageUtil.getLanguageText(R.string.sport_card_title_dismiss));
        }
    }

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public void onItemDismiss(int i) {
        this.mItems.remove(i);
        notifyItemRemoved(i);
    }

    @Override // com.ido.life.customview.recyclerview.ItemTouchHelperAdapter
    public boolean onItemMove(int i, int i2) {
        if (i2 >= this.mSelectedList.size() - 1) {
            i2 = this.mSelectedList.size() - 1;
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

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        public final View divider;
        public final View ivDrag;
        public final ImageView ivIcon;
        public final View layoutCard;
        public final TextView mTvSportDismiss;
        public final TextView mTvSportShow;
        public final View mViewDivider;
        public final RegularTextView mtvName;

        public ItemViewHolder(View view) {
            super(view);
            this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            this.mtvName = (RegularTextView) view.findViewById(R.id.mtv_name);
            this.layoutCard = view.findViewById(R.id.layout_card);
            this.divider = view.findViewById(R.id.divider);
            this.ivDrag = view.findViewById(R.id.iv_drag);
            this.mTvSportDismiss = (TextView) view.findViewById(R.id.tv_sport_dismiss);
            this.mTvSportShow = (TextView) view.findViewById(R.id.tv_sport_show);
            this.mViewDivider = view.findViewById(R.id.view_divider);
        }

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemSelected() {
            this.itemView.setBackgroundColor(-3355444);
        }

        @Override // com.ido.life.customview.recyclerview.ItemTouchHelperViewHolder
        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
        }
    }
}