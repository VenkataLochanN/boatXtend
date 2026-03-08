package com.ido.life.customview.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.FileDialDefinedUtil;
import com.ido.common.utils.MemoryManagerUtil;
import com.ido.life.customview.DialRoundRelativelayout;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.viewgroup.DialDefinedView;
import com.ido.life.data.api.entity.DialDefinedEntityNew;
import com.ido.life.data.api.entity.DialStyleEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialStyleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private int currentIndex;
    private DialDefinedEntityNew.Colors dialColors;
    DialDefinedEntityNew.OutFile.Imagegroupsize imageSize;
    private int imageStyleIndex;
    private OnItemClickListener listener;
    private ArrayList<DialStyleEntity> styleDrawables;
    private ArrayList<Drawable> imageNames = new ArrayList<>();
    private boolean isselect = false;

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

    public DialStyleAdapter(int i, ArrayList<DialStyleEntity> arrayList, DialDefinedEntityNew.Colors colors, List<String> list, DialDefinedEntityNew.OutFile.Imagegroupsize imagegroupsize, Activity activity, OnItemClickListener onItemClickListener, int i2) {
        this.currentIndex = 0;
        this.currentIndex = i;
        this.dialColors = colors;
        this.imageSize = imagegroupsize;
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (TextUtils.equals(FileDialDefinedUtil.STYLE_BG, list.get(i3))) {
                this.imageStyleIndex = i3;
                this.imageNames.add(null);
            } else if (MemoryManagerUtil.getSurplusMemory(activity)) {
                this.imageNames.add(BitmapUtil.readBitmapFromFileDrawable(FileDialDefinedUtil.appFilePng(activity, i2) + File.separator + list.get(i3)));
            } else {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "内存不足，请清理后重试");
                NormalToast.showToast(activity.getString(R.string.insufficient_memory));
                activity.finish();
            }
        }
        this.styleDrawables = arrayList;
        this.context = activity;
        this.listener = onItemClickListener;
    }

    public void setCurrentIndex(int i, boolean z) {
        this.currentIndex = i;
        this.isselect = z;
        notifyDataSetChanged();
    }

    public void setCurrentIndex(DialDefinedEntityNew.Colors colors, boolean z) {
        this.dialColors = colors;
        this.isselect = z;
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
        if (this.dialColors.getStylemodul() != null) {
            viewHolder.dialStyleRl.removeAllViews();
            for (int i2 = 0; i2 < this.imageNames.size(); i2++) {
                if (this.imageStyleIndex == i2) {
                    for (int i3 = 0; i3 < this.styleDrawables.get(i).getStyleimg().size(); i3++) {
                        viewHolder.dialStyleRl.addView(DialDefinedView.addView(i3, this.context, this.styleDrawables.get(i).getStyleimg().get(i3), this.dialColors, true, true));
                    }
                } else if (this.imageNames.get(i2) != null) {
                    viewHolder.dialStyleRl.addView(DialDefinedView.addView(i2, this.context, this.imageNames.get(i2), this.dialColors, false, true));
                }
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
        viewHolder.dialStyleRl.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.customview.recyclerview.DialStyleAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialStyleAdapter.this.currentIndex = i;
                DialStyleAdapter.this.listener.onClick(i);
                DialStyleAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.styleDrawables.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}