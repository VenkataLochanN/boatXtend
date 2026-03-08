package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MoreActivity_ViewBinding implements Unbinder {
    private MoreActivity target;
    private View view7f0a024e;
    private View view7f0a0261;
    private View view7f0a028c;
    private View view7f0a0290;

    public MoreActivity_ViewBinding(MoreActivity moreActivity) {
        this(moreActivity, moreActivity.getWindow().getDecorView());
    }

    public MoreActivity_ViewBinding(final MoreActivity moreActivity, View view) {
        this.target = moreActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_dnd_mode, "field 'mItemDndMode' and method 'onViewClicked'");
        moreActivity.mItemDndMode = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_dnd_mode, "field 'mItemDndMode'", CustomItemLabelView.class);
        this.view7f0a0261 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MoreActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                moreActivity.onViewClicked(view2);
            }
        });
        moreActivity.mItemFindPhone = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_find_phone, "field 'mItemFindPhone'", CustomItemLabelView.class);
        moreActivity.mItemMusicControl = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_music_control, "field 'mItemMusicControl'", CustomItemLabelView.class);
        moreActivity.mItemWristScreen = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_wrist_screen, "field 'mItemWristScreen'", CustomItemLabelView.class);
        moreActivity.mItemWeatherPush = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_weather_push, "field 'mItemWeatherPush'", CustomItemLabelView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_msg_reply, "field 'mItemMsgReply' and method 'onViewClicked'");
        moreActivity.mItemMsgReply = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_msg_reply, "field 'mItemMsgReply'", CustomItemLabelView.class);
        this.view7f0a028c = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MoreActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                moreActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_night_light, "field 'mItemNightLight' and method 'onViewClicked'");
        moreActivity.mItemNightLight = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_night_light, "field 'mItemNightLight'", CustomItemLabelView.class);
        this.view7f0a0290 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MoreActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                moreActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_amazon_alexa, "method 'onViewClicked'");
        this.view7f0a024e = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MoreActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                moreActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MoreActivity moreActivity = this.target;
        if (moreActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        moreActivity.mItemDndMode = null;
        moreActivity.mItemFindPhone = null;
        moreActivity.mItemMusicControl = null;
        moreActivity.mItemWristScreen = null;
        moreActivity.mItemWeatherPush = null;
        moreActivity.mItemMsgReply = null;
        moreActivity.mItemNightLight = null;
        this.view7f0a0261.setOnClickListener(null);
        this.view7f0a0261 = null;
        this.view7f0a028c.setOnClickListener(null);
        this.view7f0a028c = null;
        this.view7f0a0290.setOnClickListener(null);
        this.view7f0a0290 = null;
        this.view7f0a024e.setOnClickListener(null);
        this.view7f0a024e = null;
    }
}