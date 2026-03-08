package com.ido.life.module.user.country;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.adapter.OnClickItemListener;
import com.ido.common.adapter.SimpleRecyclerAdapter;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ResourceUtil;

/* JADX INFO: loaded from: classes3.dex */
public class CountryAdapter extends SimpleRecyclerAdapter<CountryInfo> {
    private static final String TAG = "CountryAdapter";
    protected int mCurPosition;
    private OnClickItemListener<CountryInfo> mOnClickItemListener;
    protected CountryInfo mSelCountryInfo;

    @Override // com.ido.common.adapter.SimpleRecyclerAdapter
    public int getItemLayout(int i) {
        return R.layout.item_country_layout;
    }

    public void setOnClickItemListener(OnClickItemListener<CountryInfo> onClickItemListener) {
        this.mOnClickItemListener = onClickItemListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        CountryInfo item = getItem(i);
        if (item != null) {
            return item.type;
        }
        return 48;
    }

    @Override // com.ido.common.adapter.SimpleRecyclerAdapter
    public View getItemLayoutView(int i, Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_country_layout, (ViewGroup) null);
        viewInflate.setMinimumWidth(context.getResources().getDisplayMetrics().widthPixels);
        return viewInflate;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SimpleRecyclerAdapter.ViewHolder viewHolder, int i) {
        onBindItemViewHolder(viewHolder, i, getItem(i));
    }

    private void onBindHeadViewHolder(SimpleRecyclerAdapter.ViewHolder viewHolder, int i, CountryInfo countryInfo) {
        CommonLogUtil.d("onBindHeadViewHolder:" + countryInfo.toString());
        View view = viewHolder.getView(R.id.country_divider);
        ((TextView) viewHolder.getView(R.id.country_head_tv)).setText(countryInfo.session);
        if (i == 0) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
    }

    protected void onBindItemViewHolder(SimpleRecyclerAdapter.ViewHolder viewHolder, final int i, final CountryInfo countryInfo) {
        viewHolder.getView(R.id.country_divider).setVisibility(0);
        ((TextView) viewHolder.getView(R.id.country_item_name_tv)).setText(countryInfo.countryName);
        if (countryInfo.isChoose) {
            viewHolder.itemView.setBackgroundColor(ResourceUtil.getColor(R.color.color_66979797));
        } else {
            viewHolder.itemView.setBackgroundColor(ResourceUtil.getColor(R.color.color_2E2E2E));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.country.-$$Lambda$CountryAdapter$fVTbKuQVy3BC5jrb2zeqZ-poTJ4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindItemViewHolder$0$CountryAdapter(countryInfo, i, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindItemViewHolder$0$CountryAdapter(CountryInfo countryInfo, int i, View view) {
        CountryInfo item;
        CountryInfo countryInfo2 = this.mSelCountryInfo;
        if (countryInfo2 != null) {
            countryInfo2.isChoose = false;
        }
        countryInfo.isChoose = true;
        if (i != 0 && (item = getItem(this.mCurPosition)) != null && item.type == 0) {
            if (TextUtils.equals(item.countryName, countryInfo.countryName)) {
                if (!item.isChoose) {
                    item.isChoose = true;
                }
            } else if (item.isChoose) {
                item.isChoose = false;
            }
        }
        this.mSelCountryInfo = countryInfo;
        this.mCurPosition = i;
        OnClickItemListener<CountryInfo> onClickItemListener = this.mOnClickItemListener;
        if (onClickItemListener != null) {
            onClickItemListener.onClick(view, countryInfo);
        }
        notifyDataSetChanged();
    }

    public CountryInfo getChooseCountryInfo() {
        return this.mSelCountryInfo;
    }
}