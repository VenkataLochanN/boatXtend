package com.ido.life.module.user.sportrecord.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.customview.charter.SportRecordItemChat;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.customview.viewgroup.TopSportView;
import com.ido.life.module.user.sportrecord.holder.ItemVH;
import com.ido.life.module.user.sportrecord.model.SportChildItem;
import com.ido.life.module.user.sportrecord.model.SportGroupItem;
import com.ido.life.module.user.sportrecord.model.SportItem;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ExpandableRecyclerAdapter extends RecyclerView.Adapter<ItemVH> {
    public static final int CHILD = 3842;
    public static final int GROUP = 3841;
    private static final String TAG = "ExpandableRecyclerAdapter";
    private ArrayList<SportItem> items;
    private List<Integer> mGroupItemList = new ArrayList();
    private int mGroupPosition;
    private boolean mIsKm;
    private OnItemClickListener mOnClickListener;

    public interface OnItemClickListener {
        void onChildItemClick(int i, String str, int i2);
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.mOnClickListener = onItemClickListener;
    }

    public ExpandableRecyclerAdapter(ArrayList<SportItem> arrayList) {
        this.mIsKm = true;
        this.items = arrayList;
        this.mIsKm = RunTimeUtil.getInstance().getUnitSet() == 1;
    }

    public void addGroup(SportGroupItem sportGroupItem) {
        if (sportGroupItem != null) {
            this.items.add(sportGroupItem);
        }
    }

    public void addGroupList(List<SportGroupItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.items.addAll(list);
    }

    public void clearGroup() {
        this.items.clear();
        this.mGroupItemList.clear();
    }

    public void setGroupItemList(List<Integer> list) {
        this.mGroupItemList.addAll(list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 3841) {
            return new GroupVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sport_record_head, viewGroup, false));
        }
        if (i != 3842) {
            return null;
        }
        return new ChildVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sport_record_detail_new, viewGroup, false));
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0177 A[FALL_THROUGH] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onBindViewHolder(com.ido.life.module.user.sportrecord.holder.ItemVH r12, final int r13) {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.user.sportrecord.adapter.ExpandableRecyclerAdapter.onBindViewHolder(com.ido.life.module.user.sportrecord.holder.ItemVH, int):void");
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$ExpandableRecyclerAdapter(SportChildItem sportChildItem, View view) {
        OnItemClickListener onItemClickListener = this.mOnClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onChildItemClick(sportChildItem.getType(), sportChildItem.getDateTime(), sportChildItem.getIsLocus());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.items.get(i).type();
    }

    public void expand(int i) {
        for (int i2 = 0; i2 < this.items.size(); i2++) {
            SportItem sportItem = this.items.get(i2);
            if (sportItem.type() == 3841) {
                SportGroupItem sportGroupItem = (SportGroupItem) sportItem;
                if (sportGroupItem.id == i) {
                    this.items.addAll(i2 + 1, sportGroupItem.children);
                    sportGroupItem.isExpand = true;
                }
            }
        }
        notifyDataSetChanged();
    }

    public void close(int i) {
        int i2 = -1;
        for (int i3 = 0; i3 < this.items.size(); i3++) {
            SportItem sportItem = this.items.get(i3);
            if (sportItem.type() == 3841) {
                SportGroupItem sportGroupItem = (SportGroupItem) sportItem;
                if (sportGroupItem.isExpand && i != sportGroupItem.id) {
                    i2 = sportGroupItem.id;
                    sportGroupItem.isExpand = false;
                }
            }
        }
        Iterator<SportItem> it = this.items.iterator();
        while (it.hasNext()) {
            SportItem next = it.next();
            if (next.type() == 3842 && ((SportChildItem) next).group.id == i2) {
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void action(SportGroupItem sportGroupItem, int i) {
        sportGroupItem.isExpand = !sportGroupItem.isExpand;
        if (sportGroupItem.isExpand) {
            this.items.addAll(i + 1, sportGroupItem.children);
        } else {
            Iterator<SportItem> it = this.items.iterator();
            while (it.hasNext()) {
                SportItem next = it.next();
                if (next.type() == 3842 && ((SportChildItem) next).group.id == sportGroupItem.id) {
                    it.remove();
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ChildVH extends ItemVH {
        ImageView mIvSport;
        SportRecordItemChat mRecordChat;
        TextView mTvDistance;
        TextView mTvDistanceUnit;
        TextView mTvMonth;
        TextView mTvSpace;
        TextView mTvSportTime;
        TextView mTvSportTypeName;
        View mViewLine;

        @Override // com.ido.life.module.user.sportrecord.holder.ItemVH
        public int type() {
            return ExpandableRecyclerAdapter.CHILD;
        }

        public ChildVH(View view) {
            super(view);
            this.mTvDistance = (TextView) view.findViewById(R.id.tv_sport_distance);
            this.mIvSport = (ImageView) view.findViewById(R.id.iv_sport);
            this.mTvDistance = (TextView) view.findViewById(R.id.tv_sport_distance);
            this.mTvDistanceUnit = (TextView) view.findViewById(R.id.tv_sport_distance_unit);
            this.mTvSportTime = (TextView) view.findViewById(R.id.tv_sport_time);
            this.mTvSpace = (TextView) view.findViewById(R.id.tv_sport_speed);
            this.mViewLine = view.findViewById(R.id.view_line);
            this.mTvMonth = (TextView) view.findViewById(R.id.tv_sport_data);
            this.mRecordChat = (SportRecordItemChat) view.findViewById(R.id.chat_sport);
            this.mTvSportTypeName = (TextView) view.findViewById(R.id.tv_sport_type_name);
        }
    }

    public class GroupVH extends ItemVH {
        BottomSportView mBvTotalTime;
        ImageView mIvArrow;
        LinearLayout mLlItemHeader;
        TopSportView mTpAvgTime;
        TopSportView mTpTotalCategory;
        TopSportView mTpTotalCount;
        TextView mTvDate;

        @Override // com.ido.life.module.user.sportrecord.holder.ItemVH
        public int type() {
            return ExpandableRecyclerAdapter.GROUP;
        }

        public GroupVH(View view) {
            super(view);
            this.mTvDate = (TextView) view.findViewById(R.id.tv_date);
            this.mIvArrow = (ImageView) view.findViewById(R.id.iv_expand);
            this.mLlItemHeader = (LinearLayout) view.findViewById(R.id.ll_item_header);
            this.mBvTotalTime = (BottomSportView) view.findViewById(R.id.bv_sport_item_time);
            this.mTpAvgTime = (TopSportView) view.findViewById(R.id.bv_sport_average_week_time);
            this.mTpTotalCount = (TopSportView) view.findViewById(R.id.bv_sport_item_account);
            this.mTpTotalCategory = (TopSportView) view.findViewById(R.id.bv_sport_item_category);
        }
    }
}