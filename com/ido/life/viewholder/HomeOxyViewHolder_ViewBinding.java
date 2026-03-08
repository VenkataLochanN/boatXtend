package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.OxyProgressView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeOxyViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeOxyViewHolder target;

    public HomeOxyViewHolder_ViewBinding(HomeOxyViewHolder homeOxyViewHolder, View view) {
        super(homeOxyViewHolder, view);
        this.target = homeOxyViewHolder;
        homeOxyViewHolder.mLayOut = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_out, "field 'mLayOut'", LinearLayout.class);
        homeOxyViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        homeOxyViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        homeOxyViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        homeOxyViewHolder.mOxyProgress = (OxyProgressView) Utils.findRequiredViewAsType(view, R.id.progress_oxy, "field 'mOxyProgress'", OxyProgressView.class);
        homeOxyViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        homeOxyViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        homeOxyViewHolder.mTvOxy = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oxy, "field 'mTvOxy'", TextView.class);
        homeOxyViewHolder.mTvOxyUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oxy_unit, "field 'mTvOxyUnit'", TextView.class);
        homeOxyViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeOxyViewHolder homeOxyViewHolder = this.target;
        if (homeOxyViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeOxyViewHolder.mLayOut = null;
        homeOxyViewHolder.mTvTitle = null;
        homeOxyViewHolder.mTvDate = null;
        homeOxyViewHolder.mImgEmpty = null;
        homeOxyViewHolder.mOxyProgress = null;
        homeOxyViewHolder.mLayBottom = null;
        homeOxyViewHolder.mLayContent = null;
        homeOxyViewHolder.mTvOxy = null;
        homeOxyViewHolder.mTvOxyUnit = null;
        homeOxyViewHolder.mAnimView = null;
        super.unbind();
    }
}