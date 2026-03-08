package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.HomeMenstrualProgressView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeLifeCycleViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeLifeCycleViewHolder target;

    public HomeLifeCycleViewHolder_ViewBinding(HomeLifeCycleViewHolder homeLifeCycleViewHolder, View view) {
        super(homeLifeCycleViewHolder, view);
        this.target = homeLifeCycleViewHolder;
        homeLifeCycleViewHolder.mLayOut = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_out, "field 'mLayOut'", LinearLayout.class);
        homeLifeCycleViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        homeLifeCycleViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        homeLifeCycleViewHolder.mCycleView = (HomeMenstrualProgressView) Utils.findRequiredViewAsType(view, R.id.menstrual_progress, "field 'mCycleView'", HomeMenstrualProgressView.class);
        homeLifeCycleViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        homeLifeCycleViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        homeLifeCycleViewHolder.mTvLeft = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left, "field 'mTvLeft'", TextView.class);
        homeLifeCycleViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeLifeCycleViewHolder homeLifeCycleViewHolder = this.target;
        if (homeLifeCycleViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeLifeCycleViewHolder.mLayOut = null;
        homeLifeCycleViewHolder.mTvTitle = null;
        homeLifeCycleViewHolder.mImgEmpty = null;
        homeLifeCycleViewHolder.mCycleView = null;
        homeLifeCycleViewHolder.mLayBottom = null;
        homeLifeCycleViewHolder.mLayContent = null;
        homeLifeCycleViewHolder.mTvLeft = null;
        homeLifeCycleViewHolder.mAnimView = null;
        super.unbind();
    }
}