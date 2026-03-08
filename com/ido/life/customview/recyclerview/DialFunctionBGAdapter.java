package com.ido.life.customview.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.customview.DialRoundRelativelayout;
import com.ido.life.customview.viewgroup.DialDefinedFunctionView;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class DialFunctionBGAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Drawable> bgDrawables;
    private Context context;
    private int currentIndex;
    private ArrayList<Drawable> imageNames = new ArrayList<>();
    DialDefinedFunctionEntity.Imagegroupsize imageSize;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(int i);

        void selectView(int i);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout dialStyleCheckRl;
        DialRoundRelativelayout dialStyleRl;
        TextView dialStyleTv;
        LinearLayout dialStyleTvLl;

        public ViewHolder(View view) {
            super(view);
            if (view.getTag() == null) {
                Log.e("ViewHolder", "ViewHolder111111111111111");
                this.dialStyleTv = (TextView) view.findViewById(R.id.dial_style_tv);
                this.dialStyleRl = (DialRoundRelativelayout) view.findViewById(R.id.dial_style_rl);
                this.dialStyleTvLl = (LinearLayout) view.findViewById(R.id.dial_style_tv_ll);
                this.dialStyleCheckRl = (RelativeLayout) view.findViewById(R.id.dial_style_check_rl);
                view.setTag(this);
                return;
            }
            view.getTag();
        }
    }

    public DialFunctionBGAdapter(int i, ArrayList<Drawable> arrayList, DialDefinedFunctionEntity.Imagegroupsize imagegroupsize, Activity activity, OnItemClickListener onItemClickListener) {
        this.currentIndex = 0;
        this.currentIndex = i;
        this.imageSize = imagegroupsize;
        this.bgDrawables = arrayList;
        this.context = activity;
        this.listener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_style_dial, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (this.imageSize != null) {
            ViewGroup.LayoutParams layoutParams = viewHolder.dialStyleRl.getLayoutParams();
            layoutParams.width = (int) ((layoutParams.height / this.imageSize.getHeight().intValue()) * this.imageSize.getWidth().intValue());
            viewHolder.dialStyleRl.setLayoutParams(layoutParams);
        }
        viewHolder.dialStyleRl.addView(DialDefinedFunctionView.addView(this.context, this.bgDrawables.get(i)));
        if (this.currentIndex == i && this.bgDrawables.size() > 1) {
            viewHolder.dialStyleCheckRl.setBackgroundResource(R.drawable.dial_style_checks_select);
            viewHolder.dialStyleTvLl.setBackgroundResource(R.drawable.dial_style_check_bg);
            viewHolder.dialStyleTv.setVisibility(8);
        } else {
            viewHolder.dialStyleCheckRl.setBackground(null);
            viewHolder.dialStyleTvLl.setBackgroundResource(0);
            viewHolder.dialStyleTv.setVisibility(8);
        }
        viewHolder.dialStyleRl.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.recyclerview.DialFunctionBGAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialFunctionBGAdapter.this.currentIndex = i;
                DialFunctionBGAdapter.this.listener.onClick(i);
                DialFunctionBGAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.bgDrawables.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}