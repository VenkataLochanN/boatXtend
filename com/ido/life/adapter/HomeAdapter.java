package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.bean.MainData;
import com.ido.life.module.home.IHomeView;
import com.ido.life.viewholder.AmbientVolumeViewHolder;
import com.ido.life.viewholder.BaseHomeViewHolder;
import com.ido.life.viewholder.HomeDeviceStateHolder;
import com.ido.life.viewholder.HomeFitnessViewHolder;
import com.ido.life.viewholder.HomeFootViewHolder;
import com.ido.life.viewholder.HomeHeartRateViewHolder;
import com.ido.life.viewholder.HomeLifeCycleViewHolder;
import com.ido.life.viewholder.HomeOxyViewHolder;
import com.ido.life.viewholder.HomePressureViewHolder;
import com.ido.life.viewholder.HomeSleepViewHolder;
import com.ido.life.viewholder.HomeSportRecordViewHolder;
import com.ido.life.viewholder.HomeStepViewHolder;
import com.ido.life.viewholder.HomeWeightViewHolder;
import com.ido.life.viewholder.OxyUptakeViewHolder;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class HomeAdapter extends RecyclerView.Adapter<BaseHomeViewHolder> {
    private Context mContext;
    private IHomeView mHomeView;
    private LayoutInflater mInflater;
    private LinkedList<MainData> mList;
    private boolean mShowAnimator;

    public HomeAdapter(Context context, LinkedList<MainData> linkedList, IHomeView iHomeView) {
        this.mContext = context;
        this.mList = linkedList;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mHomeView = iHomeView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseHomeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new HomeDeviceStateHolder(this.mInflater.inflate(R.layout.item_device_state, (ViewGroup) null), this.mHomeView);
            case 1:
                return new HomeStepViewHolder(this.mInflater.inflate(R.layout.item_step_card, (ViewGroup) null), this.mHomeView);
            case 2:
                return new HomeFitnessViewHolder(this.mInflater.inflate(R.layout.item_home_fitness_layout, (ViewGroup) null), this.mHomeView);
            case 3:
                return new HomeSportRecordViewHolder(this.mInflater.inflate(R.layout.item_home_sport_record_card_layout, (ViewGroup) null), this.mHomeView);
            case 4:
                return new HomeSleepViewHolder(this.mInflater.inflate(R.layout.item_home_sleep_card_layout, (ViewGroup) null), this.mHomeView);
            case 5:
                return new HomeHeartRateViewHolder(this.mInflater.inflate(R.layout.item_home_heart_rate_card_layout, (ViewGroup) null), this.mHomeView);
            case 6:
                return new HomePressureViewHolder(this.mInflater.inflate(R.layout.item_home_pressure_card_layout, (ViewGroup) null), this.mHomeView);
            case 7:
                return new HomeOxyViewHolder(this.mInflater.inflate(R.layout.item_home_oxy_card_layout, (ViewGroup) null), this.mHomeView);
            case 8:
                return new HomeWeightViewHolder(this.mInflater.inflate(R.layout.item_home_weight_card_layout, (ViewGroup) null), this.mHomeView);
            case 9:
                return new HomeLifeCycleViewHolder(this.mInflater.inflate(R.layout.item_home_life_cycle_layout, (ViewGroup) null), this.mHomeView);
            case 10:
                return new HomeFootViewHolder(this.mInflater.inflate(R.layout.item_main_foot_view, (ViewGroup) null), this.mHomeView);
            case 11:
                return new AmbientVolumeViewHolder(this.mInflater.inflate(R.layout.item_home_ambient_volume_card_layout, (ViewGroup) null), this.mHomeView);
            case 12:
                return new OxyUptakeViewHolder(this.mInflater.inflate(R.layout.item_home_oxygen_uptake_card_layout, (ViewGroup) null), this.mHomeView);
            default:
                return null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseHomeViewHolder baseHomeViewHolder, int i) {
        if (this.mShowAnimator) {
            baseHomeViewHolder.setShowAnimator(true);
        }
        baseHomeViewHolder.bindData(this.mList.get(i));
        if (i == getItemCount() - 1) {
            this.mShowAnimator = false;
        }
        baseHomeViewHolder.setShowAnimator(false);
    }

    public void notifyDataSetChanged(boolean z) {
        this.mShowAnimator = z;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mList.get(i).getViewType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        LinkedList<MainData> linkedList = this.mList;
        if (linkedList == null) {
            return 0;
        }
        return linkedList.size();
    }
}