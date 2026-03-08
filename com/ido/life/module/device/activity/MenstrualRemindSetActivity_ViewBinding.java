package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualRemindSetActivity_ViewBinding implements Unbinder {
    private MenstrualRemindSetActivity target;
    private View view7f0a0282;
    private View view7f0a0296;
    private View view7f0a02a5;
    private View view7f0a041d;
    private View view7f0a0422;

    public MenstrualRemindSetActivity_ViewBinding(MenstrualRemindSetActivity menstrualRemindSetActivity) {
        this(menstrualRemindSetActivity, menstrualRemindSetActivity.getWindow().getDecorView());
    }

    public MenstrualRemindSetActivity_ViewBinding(final MenstrualRemindSetActivity menstrualRemindSetActivity, View view) {
        this.target = menstrualRemindSetActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_menstrual_remind, "field 'mItemMenstrualRemind' and method 'onViewClicked'");
        menstrualRemindSetActivity.mItemMenstrualRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_menstrual_remind, "field 'mItemMenstrualRemind'", CustomItemLabelView.class);
        this.view7f0a0282 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualRemindSetActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualRemindSetActivity.onViewClicked(view2);
            }
        });
        menstrualRemindSetActivity.mTvMenstrualDate = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_menstrual_date, "field 'mTvMenstrualDate'", RegularTextView.class);
        menstrualRemindSetActivity.mTvMenstrualDetail = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_menstrual_detail, "field 'mTvMenstrualDetail'", RegularTextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_ovulation_remind, "field 'mItemOvulationRemind' and method 'onViewClicked'");
        menstrualRemindSetActivity.mItemOvulationRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_ovulation_remind, "field 'mItemOvulationRemind'", CustomItemLabelView.class);
        this.view7f0a0296 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualRemindSetActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualRemindSetActivity.onViewClicked(view2);
            }
        });
        menstrualRemindSetActivity.mTvOvulationDate = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_ovulation_date, "field 'mTvOvulationDate'", RegularTextView.class);
        menstrualRemindSetActivity.mTvOvulationDetail = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_ovulation_detail, "field 'mTvOvulationDetail'", RegularTextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_remind_time, "field 'mItemRemindTime' and method 'onViewClicked'");
        menstrualRemindSetActivity.mItemRemindTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_remind_time, "field 'mItemRemindTime'", CustomItemLabelView.class);
        this.view7f0a02a5 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualRemindSetActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualRemindSetActivity.onViewClicked(view2);
            }
        });
        menstrualRemindSetActivity.mRtvMenstruationRemindTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_menstruation_remind_tip, "field 'mRtvMenstruationRemindTip'", RegularTextView.class);
        menstrualRemindSetActivity.mRtvRemindTimeTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_remind_time_tip, "field 'mRtvRemindTimeTip'", RegularTextView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.layout_menstrual_remind, "method 'onViewClicked'");
        this.view7f0a041d = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualRemindSetActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualRemindSetActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.layout_ovulation_remind, "method 'onViewClicked'");
        this.view7f0a0422 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualRemindSetActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualRemindSetActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MenstrualRemindSetActivity menstrualRemindSetActivity = this.target;
        if (menstrualRemindSetActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        menstrualRemindSetActivity.mItemMenstrualRemind = null;
        menstrualRemindSetActivity.mTvMenstrualDate = null;
        menstrualRemindSetActivity.mTvMenstrualDetail = null;
        menstrualRemindSetActivity.mItemOvulationRemind = null;
        menstrualRemindSetActivity.mTvOvulationDate = null;
        menstrualRemindSetActivity.mTvOvulationDetail = null;
        menstrualRemindSetActivity.mItemRemindTime = null;
        menstrualRemindSetActivity.mRtvMenstruationRemindTip = null;
        menstrualRemindSetActivity.mRtvRemindTimeTip = null;
        this.view7f0a0282.setOnClickListener(null);
        this.view7f0a0282 = null;
        this.view7f0a0296.setOnClickListener(null);
        this.view7f0a0296 = null;
        this.view7f0a02a5.setOnClickListener(null);
        this.view7f0a02a5 = null;
        this.view7f0a041d.setOnClickListener(null);
        this.view7f0a041d = null;
        this.view7f0a0422.setOnClickListener(null);
        this.view7f0a0422 = null;
    }
}