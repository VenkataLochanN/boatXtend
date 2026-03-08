package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeSleepViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeSleepViewHolder target;

    public HomeSleepViewHolder_ViewBinding(HomeSleepViewHolder homeSleepViewHolder, View view) {
        super(homeSleepViewHolder, view);
        this.target = homeSleepViewHolder;
        homeSleepViewHolder.mLayOut = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_out, "field 'mLayOut'", LinearLayout.class);
        homeSleepViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        homeSleepViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        homeSleepViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        homeSleepViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        homeSleepViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        homeSleepViewHolder.mTvHour = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hour, "field 'mTvHour'", TextView.class);
        homeSleepViewHolder.mTvHourUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hour_unit, "field 'mTvHourUnit'", TextView.class);
        homeSleepViewHolder.mTvMinute = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_minute, "field 'mTvMinute'", TextView.class);
        homeSleepViewHolder.mTvMinuteUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_minute_unit, "field 'mTvMinuteUnit'", TextView.class);
        homeSleepViewHolder.mLaySleep = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.lay_sleep, "field 'mLaySleep'", ConstraintLayout.class);
        homeSleepViewHolder.mImgAlive = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_alive, "field 'mImgAlive'", ImageView.class);
        homeSleepViewHolder.mImgFast = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_fast, "field 'mImgFast'", ImageView.class);
        homeSleepViewHolder.mImgSleep = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_sleep, "field 'mImgSleep'", ImageView.class);
        homeSleepViewHolder.mImgDeepSleep = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_deep_sleep, "field 'mImgDeepSleep'", ImageView.class);
        homeSleepViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeSleepViewHolder homeSleepViewHolder = this.target;
        if (homeSleepViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeSleepViewHolder.mLayOut = null;
        homeSleepViewHolder.mTvTitle = null;
        homeSleepViewHolder.mTvDate = null;
        homeSleepViewHolder.mImgEmpty = null;
        homeSleepViewHolder.mLayBottom = null;
        homeSleepViewHolder.mLayContent = null;
        homeSleepViewHolder.mTvHour = null;
        homeSleepViewHolder.mTvHourUnit = null;
        homeSleepViewHolder.mTvMinute = null;
        homeSleepViewHolder.mTvMinuteUnit = null;
        homeSleepViewHolder.mLaySleep = null;
        homeSleepViewHolder.mImgAlive = null;
        homeSleepViewHolder.mImgFast = null;
        homeSleepViewHolder.mImgSleep = null;
        homeSleepViewHolder.mImgDeepSleep = null;
        homeSleepViewHolder.mAnimView = null;
        super.unbind();
    }
}