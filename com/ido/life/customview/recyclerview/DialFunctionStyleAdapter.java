package com.ido.life.customview.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.life.customview.DialRoundRelativelayout;
import com.ido.life.customview.viewgroup.DialDefinedFunctionView;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;
import com.ido.life.data.api.entity.DialStyleEntity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialFunctionStyleAdapter extends RecyclerView.Adapter<ViewHolder> {
    Drawable background;
    DialDefinedFunctionEntity.BackgroundInfo backgroundInfo;
    private Context context;
    private int currentIndex;
    private DialDefinedFunctionEntity.PaletteColor dialColors;
    DialDefinedFunctionEntity.Imagegroupsize imageSize;
    private OnItemClickListener listener;
    private int mDeviceId;
    private ArrayList<DialStyleEntity> styleDrawables;
    private boolean isselect = false;
    private int colorIndex = 0;

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

    public DialFunctionStyleAdapter(DialDefinedFunctionEntity.BackgroundInfo backgroundInfo, Drawable drawable, int i, ArrayList<DialStyleEntity> arrayList, DialDefinedFunctionEntity.PaletteColor paletteColor, DialDefinedFunctionEntity.Imagegroupsize imagegroupsize, Activity activity, OnItemClickListener onItemClickListener, int i2) {
        this.currentIndex = 0;
        this.currentIndex = i;
        this.dialColors = paletteColor;
        this.imageSize = imagegroupsize;
        this.mDeviceId = i2;
        this.styleDrawables = arrayList;
        this.context = activity;
        this.listener = onItemClickListener;
        this.background = drawable;
        this.backgroundInfo = backgroundInfo;
    }

    public void setCurrentIndex(int i, boolean z) {
        this.currentIndex = i;
        this.isselect = z;
        notifyDataSetChanged();
    }

    public void upDateSelectColor(DialDefinedFunctionEntity.PaletteColor paletteColor) {
        this.dialColors = paletteColor;
        notifyDataSetChanged();
    }

    public void upDateBG(Drawable drawable) {
        this.background = drawable;
        notifyDataSetChanged();
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
        DialDefinedFunctionEntity.BackgroundInfo backgroundInfo = this.backgroundInfo;
        if (backgroundInfo != null && !TextUtils.isEmpty(backgroundInfo.getBackgroundColor())) {
            viewHolder.dialStyleRl.addView(DialDefinedFunctionView.addViewFromColor(this.context, this.backgroundInfo.getBackgroundColor()));
        }
        if (this.background != null) {
            viewHolder.dialStyleRl.addView(DialDefinedFunctionView.addView(this.context, this.background));
        }
        DialStyleEntity dialStyleEntity = this.styleDrawables.get(i);
        for (int i2 = 0; i2 < dialStyleEntity.getStyleimg().size(); i2++) {
            if (dialStyleEntity.getCanChangeColor() == 1) {
                viewHolder.dialStyleRl.addView(DialDefinedFunctionView.addView(i2, this.context, this.styleDrawables.get(i).getStyleimg().get(i2), this.dialColors, true));
            } else {
                viewHolder.dialStyleRl.addView(DialDefinedFunctionView.addView(i2, this.context, this.styleDrawables.get(i).getStyleimg().get(i2), null, true));
            }
        }
        this.listener.selectView(this.currentIndex);
        viewHolder.dialStyleTv.setText(this.styleDrawables.get(i).getDescription());
        if (this.currentIndex == i) {
            viewHolder.dialStyleCheckRl.setBackgroundResource(R.drawable.dial_style_checks_select);
            viewHolder.dialStyleTvLl.setBackgroundResource(R.drawable.dial_style_check_bg);
            viewHolder.dialStyleTv.setTextColor(this.context.getResources().getColor(R.color.white, null));
        } else {
            viewHolder.dialStyleCheckRl.setBackground(null);
            viewHolder.dialStyleTvLl.setBackgroundResource(0);
            viewHolder.dialStyleTv.setTextColor(this.context.getResources().getColor(R.color.color_A9ABAC, null));
        }
        viewHolder.dialStyleRl.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.recyclerview.DialFunctionStyleAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialFunctionStyleAdapter.this.currentIndex = i;
                DialFunctionStyleAdapter.this.listener.onClick(i);
                DialFunctionStyleAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public View addView(float f2, int i, List<Integer> list, Drawable drawable) {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.style_image_swift, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.color_style_img);
        imageView.setImageDrawable(drawable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (list.get(2).intValue() * f2), (int) (list.get(3).intValue() * f2));
        layoutParams.leftMargin = (int) (list.get(0).intValue() * f2);
        layoutParams.topMargin = (int) (list.get(1).intValue() * f2);
        imageView.setLayoutParams(layoutParams);
        return viewInflate;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.styleDrawables.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}