package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.bean.WorldTimeCity;
import com.ido.life.customview.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorldTimeCityAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001#B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\u00020\u001c2\n\u0010\u001d\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001aH\u0016J\u001c\u0010\u001f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001aH\u0016R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006$"}, d2 = {"Lcom/ido/life/adapter/WorldTimeCityAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/ido/life/adapter/WorldTimeCityAdapter$ViewHolder;", "context", "Landroid/content/Context;", "cities", "", "Lcom/ido/life/bean/WorldTimeCity;", "mSelectedCities", "Ljava/util/ArrayList;", "(Landroid/content/Context;Ljava/util/List;Ljava/util/ArrayList;)V", "getCities", "()Ljava/util/List;", "getContext", "()Landroid/content/Context;", "getMSelectedCities", "()Ljava/util/ArrayList;", "setMSelectedCities", "(Ljava/util/ArrayList;)V", "onItemClickListener", "Lcom/ido/life/customview/OnItemClickListener;", "getOnItemClickListener", "()Lcom/ido/life/customview/OnItemClickListener;", "setOnItemClickListener", "(Lcom/ido/life/customview/OnItemClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WorldTimeCityAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<WorldTimeCity> cities;
    private final Context context;
    private ArrayList<WorldTimeCity> mSelectedCities;
    private OnItemClickListener onItemClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final List<WorldTimeCity> getCities() {
        return this.cities;
    }

    public final ArrayList<WorldTimeCity> getMSelectedCities() {
        return this.mSelectedCities;
    }

    public final void setMSelectedCities(ArrayList<WorldTimeCity> arrayList) {
        this.mSelectedCities = arrayList;
    }

    public WorldTimeCityAdapter(Context context, List<WorldTimeCity> list, ArrayList<WorldTimeCity> arrayList) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.cities = list;
        this.mSelectedCities = arrayList;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.item_world_time_city, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…time_city, parent, false)");
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        List<WorldTimeCity> list = this.cities;
        WorldTimeCity worldTimeCity = list != null ? list.get(position) : null;
        if (worldTimeCity != null) {
            TextView tvCityName = holder.getTvCityName();
            if (tvCityName != null) {
                tvCityName.setText(worldTimeCity.getName() + (char) 65288 + worldTimeCity.getCountry() + (char) 65289);
            }
            ArrayList<WorldTimeCity> arrayList = this.mSelectedCities;
            boolean z = (arrayList == null || arrayList.contains(worldTimeCity)) ? false : true;
            LinearLayout llLayout = holder.getLlLayout();
            if (llLayout != null) {
                llLayout.setEnabled(z);
            }
            TextView tvCityName2 = holder.getTvCityName();
            if (tvCityName2 != null) {
                tvCityName2.setEnabled(z);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WorldTimeCity> list = this.cities;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: compiled from: WorldTimeCityAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/ido/life/adapter/WorldTimeCityAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/ido/life/adapter/WorldTimeCityAdapter;Landroid/view/View;)V", "llLayout", "Landroid/widget/LinearLayout;", "getLlLayout", "()Landroid/widget/LinearLayout;", "setLlLayout", "(Landroid/widget/LinearLayout;)V", "tvCityName", "Landroid/widget/TextView;", "getTvCityName", "()Landroid/widget/TextView;", "setTvCityName", "(Landroid/widget/TextView;)V", "app_release"}, k = 1, mv = {1, 1, 16})
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llLayout;
        final /* synthetic */ WorldTimeCityAdapter this$0;
        private TextView tvCityName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(WorldTimeCityAdapter worldTimeCityAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.this$0 = worldTimeCityAdapter;
            this.llLayout = (LinearLayout) itemView.findViewById(R.id.llLayout);
            this.tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.adapter.WorldTimeCityAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnItemClickListener onItemClickListener = ViewHolder.this.this$0.getOnItemClickListener();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(ViewHolder.this.getAdapterPosition());
                    }
                }
            });
        }

        public final LinearLayout getLlLayout() {
            return this.llLayout;
        }

        public final void setLlLayout(LinearLayout linearLayout) {
            this.llLayout = linearLayout;
        }

        public final TextView getTvCityName() {
            return this.tvCityName;
        }

        public final void setTvCityName(TextView textView) {
            this.tvCityName = textView;
        }
    }
}