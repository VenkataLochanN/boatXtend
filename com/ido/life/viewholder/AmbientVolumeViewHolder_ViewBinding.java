package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.AmbientVolumeProgressView;
import com.ido.life.customview.HomeIconAnimView;

/* JADX INFO: loaded from: classes3.dex */
public class AmbientVolumeViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private AmbientVolumeViewHolder target;

    public AmbientVolumeViewHolder_ViewBinding(AmbientVolumeViewHolder ambientVolumeViewHolder, View view) {
        super(ambientVolumeViewHolder, view);
        this.target = ambientVolumeViewHolder;
        ambientVolumeViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        ambientVolumeViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
        ambientVolumeViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        ambientVolumeViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        ambientVolumeViewHolder.mLayEmpty = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_empty, "field 'mLayEmpty'", LinearLayout.class);
        ambientVolumeViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        ambientVolumeViewHolder.mAmbientVolumeProgressView = (AmbientVolumeProgressView) Utils.findRequiredViewAsType(view, R.id.ambient_volume, "field 'mAmbientVolumeProgressView'", AmbientVolumeProgressView.class);
        ambientVolumeViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        ambientVolumeViewHolder.mTvVolume = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_volume, "field 'mTvVolume'", TextView.class);
        ambientVolumeViewHolder.mTvVolumeUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_volume_unit, "field 'mTvVolumeUnit'", TextView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        AmbientVolumeViewHolder ambientVolumeViewHolder = this.target;
        if (ambientVolumeViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ambientVolumeViewHolder.mTvTitle = null;
        ambientVolumeViewHolder.mAnimView = null;
        ambientVolumeViewHolder.mTvDate = null;
        ambientVolumeViewHolder.mLayContent = null;
        ambientVolumeViewHolder.mLayEmpty = null;
        ambientVolumeViewHolder.mImgEmpty = null;
        ambientVolumeViewHolder.mAmbientVolumeProgressView = null;
        ambientVolumeViewHolder.mLayBottom = null;
        ambientVolumeViewHolder.mTvVolume = null;
        ambientVolumeViewHolder.mTvVolumeUnit = null;
        super.unbind();
    }
}