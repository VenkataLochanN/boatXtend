package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.HomeWeightChatView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeWeightViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeWeightViewHolder target;

    public HomeWeightViewHolder_ViewBinding(HomeWeightViewHolder homeWeightViewHolder, View view) {
        super(homeWeightViewHolder, view);
        this.target = homeWeightViewHolder;
        homeWeightViewHolder.mLayOut = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_out, "field 'mLayOut'", LinearLayout.class);
        homeWeightViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        homeWeightViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        homeWeightViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        homeWeightViewHolder.mWeightChart = (HomeWeightChatView) Utils.findRequiredViewAsType(view, R.id.chat_weight, "field 'mWeightChart'", HomeWeightChatView.class);
        homeWeightViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        homeWeightViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        homeWeightViewHolder.mTvWeight = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_weight, "field 'mTvWeight'", TextView.class);
        homeWeightViewHolder.mTvWeightUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_weight_unit, "field 'mTvWeightUnit'", TextView.class);
        homeWeightViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeWeightViewHolder homeWeightViewHolder = this.target;
        if (homeWeightViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeWeightViewHolder.mLayOut = null;
        homeWeightViewHolder.mTvTitle = null;
        homeWeightViewHolder.mTvDate = null;
        homeWeightViewHolder.mImgEmpty = null;
        homeWeightViewHolder.mWeightChart = null;
        homeWeightViewHolder.mLayBottom = null;
        homeWeightViewHolder.mLayContent = null;
        homeWeightViewHolder.mTvWeight = null;
        homeWeightViewHolder.mTvWeightUnit = null;
        homeWeightViewHolder.mAnimView = null;
        super.unbind();
    }
}