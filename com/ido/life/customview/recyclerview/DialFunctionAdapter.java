package com.ido.life.customview.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;
import com.ido.life.module.device.activity.DialDefinedFunctionActivity;
import com.ido.life.util.DeviceUtil;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialFunctionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private OnItemClickListener listener;
    private Context mContext;
    List<DialDefinedFunctionEntity.Function> mListFunction;
    private int selectPosition;

    public interface OnItemClickListener {
        void onClick(int i);

        void selectView(int i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View driver;
        ImageView function_select_iv;
        MediumTextView function_title_tv;
        RelativeLayout layout_card;
        TextView mtv_name;

        public ViewHolder(View view) {
            super(view);
            if (view.getTag() == null) {
                Log.e("ViewHolder", "ViewHolder111111111111111");
                this.mtv_name = (TextView) view.findViewById(R.id.mtv_name);
                this.function_select_iv = (ImageView) view.findViewById(R.id.function_select_tv);
                this.layout_card = (RelativeLayout) view.findViewById(R.id.layout_card);
                this.function_title_tv = (MediumTextView) view.findViewById(R.id.function_title_tv);
                this.driver = view.findViewById(R.id.view_divider);
                view.setTag(this);
                return;
            }
            view.getTag();
        }
    }

    public DialFunctionAdapter(Context context, List<DialDefinedFunctionEntity.Function> list, int i, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mListFunction = list;
        this.listener = onItemClickListener;
        this.selectPosition = i;
    }

    public void updateSelect(int i) {
        this.selectPosition = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dial_function, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final DialDefinedFunctionEntity.Function function = this.mListFunction.get(i);
        String stringFromName = DeviceUtil.getStringFromName(this.mContext, DialDefinedFunctionActivity.functionLaugeMap.get(function.getType()));
        if (function.getId() == -1) {
            viewHolder.function_title_tv.setVisibility(0);
            viewHolder.function_title_tv.setText(stringFromName);
            viewHolder.layout_card.setVisibility(8);
            viewHolder.driver.setVisibility(8);
        } else {
            viewHolder.function_title_tv.setVisibility(8);
            viewHolder.layout_card.setVisibility(0);
            viewHolder.mtv_name.setText(stringFromName);
            if (function.getId() == 100) {
                viewHolder.layout_card.setBackgroundResource(R.drawable.shape_function_f2f2f7_top_corner_bg);
                viewHolder.driver.setVisibility(0);
            } else if (function.getId() == 200) {
                viewHolder.layout_card.setBackgroundResource(R.drawable.shape_function_f2f2f7_bottom_corner_bg);
                viewHolder.driver.setVisibility(8);
            } else if (function.getId() == 300) {
                viewHolder.layout_card.setBackgroundResource(R.drawable.shape_function_f2f2f7_corner_bg);
                viewHolder.driver.setVisibility(8);
            } else {
                viewHolder.layout_card.setBackgroundResource(R.drawable.shape_function_f2f2f7_center_corner_bg);
                viewHolder.driver.setVisibility(0);
            }
        }
        if (i == this.selectPosition) {
            viewHolder.function_select_iv.setVisibility(0);
        } else {
            viewHolder.function_select_iv.setVisibility(8);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.recyclerview.DialFunctionAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (function.getId() == -1) {
                    return;
                }
                DialFunctionAdapter.this.listener.onClick(i);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mListFunction.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}