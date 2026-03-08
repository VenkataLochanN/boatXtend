package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.charter.HomeSportRecordChat;

/* JADX INFO: loaded from: classes3.dex */
public class HomeSportRecordViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeSportRecordViewHolder target;

    public HomeSportRecordViewHolder_ViewBinding(HomeSportRecordViewHolder homeSportRecordViewHolder, View view) {
        super(homeSportRecordViewHolder, view);
        this.target = homeSportRecordViewHolder;
        homeSportRecordViewHolder.mLayOut = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_out, "field 'mLayOut'", LinearLayout.class);
        homeSportRecordViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        homeSportRecordViewHolder.mLayTop = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_top, "field 'mLayTop'", LinearLayout.class);
        homeSportRecordViewHolder.mTvDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_desc, "field 'mTvDesc'", TextView.class);
        homeSportRecordViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        homeSportRecordViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        homeSportRecordViewHolder.mRecordChat = (HomeSportRecordChat) Utils.findRequiredViewAsType(view, R.id.chat_sport, "field 'mRecordChat'", HomeSportRecordChat.class);
        homeSportRecordViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        homeSportRecordViewHolder.mTvValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_value, "field 'mTvValue'", TextView.class);
        homeSportRecordViewHolder.mTvUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_unit, "field 'mTvUnit'", TextView.class);
        homeSportRecordViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
        homeSportRecordViewHolder.mLaySport = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_sport, "field 'mLaySport'", LinearLayout.class);
        homeSportRecordViewHolder.mTvName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_name, "field 'mTvName'", TextView.class);
        homeSportRecordViewHolder.mImgSport = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_sport, "field 'mImgSport'", ImageView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeSportRecordViewHolder homeSportRecordViewHolder = this.target;
        if (homeSportRecordViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeSportRecordViewHolder.mLayOut = null;
        homeSportRecordViewHolder.mTvTitle = null;
        homeSportRecordViewHolder.mLayTop = null;
        homeSportRecordViewHolder.mTvDesc = null;
        homeSportRecordViewHolder.mImgEmpty = null;
        homeSportRecordViewHolder.mLayContent = null;
        homeSportRecordViewHolder.mRecordChat = null;
        homeSportRecordViewHolder.mLayBottom = null;
        homeSportRecordViewHolder.mTvValue = null;
        homeSportRecordViewHolder.mTvUnit = null;
        homeSportRecordViewHolder.mAnimView = null;
        homeSportRecordViewHolder.mLaySport = null;
        homeSportRecordViewHolder.mTvName = null;
        homeSportRecordViewHolder.mImgSport = null;
        super.unbind();
    }
}