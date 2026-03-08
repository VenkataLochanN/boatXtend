package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MusicControlActivity_ViewBinding implements Unbinder {
    private MusicControlActivity target;

    public MusicControlActivity_ViewBinding(MusicControlActivity musicControlActivity) {
        this(musicControlActivity, musicControlActivity.getWindow().getDecorView());
    }

    public MusicControlActivity_ViewBinding(MusicControlActivity musicControlActivity, View view) {
        this.target = musicControlActivity;
        musicControlActivity.mTvTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_tip, "field 'mTvTip'", RegularTextView.class);
        musicControlActivity.mItemMusicControl = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_music_control, "field 'mItemMusicControl'", CustomItemLabelView.class);
        musicControlActivity.mItemMusicNameControl = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_music_show_name, "field 'mItemMusicNameControl'", CustomItemLabelView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MusicControlActivity musicControlActivity = this.target;
        if (musicControlActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        musicControlActivity.mTvTip = null;
        musicControlActivity.mItemMusicControl = null;
        musicControlActivity.mItemMusicNameControl = null;
    }
}