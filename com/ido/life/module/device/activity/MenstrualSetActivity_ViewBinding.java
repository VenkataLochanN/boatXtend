package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualSetActivity_ViewBinding implements Unbinder {
    private MenstrualSetActivity target;
    private View view7f0a027a;
    private View view7f0a0281;
    private View view7f0a0297;

    public MenstrualSetActivity_ViewBinding(MenstrualSetActivity menstrualSetActivity) {
        this(menstrualSetActivity, menstrualSetActivity.getWindow().getDecorView());
    }

    public MenstrualSetActivity_ViewBinding(final MenstrualSetActivity menstrualSetActivity, View view) {
        this.target = menstrualSetActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_menstrual_length, "field 'mItemMenstrualLength' and method 'onViewClicked'");
        menstrualSetActivity.mItemMenstrualLength = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_menstrual_length, "field 'mItemMenstrualLength'", CustomItemLabelView.class);
        this.view7f0a0281 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualSetActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualSetActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_period_length, "field 'mItemPeriodLength' and method 'onViewClicked'");
        menstrualSetActivity.mItemPeriodLength = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_period_length, "field 'mItemPeriodLength'", CustomItemLabelView.class);
        this.view7f0a0297 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualSetActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualSetActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_last_menstruation, "field 'mItemLastMenstruation' and method 'onViewClicked'");
        menstrualSetActivity.mItemLastMenstruation = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_last_menstruation, "field 'mItemLastMenstruation'", CustomItemLabelView.class);
        this.view7f0a027a = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualSetActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualSetActivity.onViewClicked(view2);
            }
        });
        menstrualSetActivity.mTvTips = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_tip, "field 'mTvTips'", RegularTextView.class);
        menstrualSetActivity.mRtvQMenstrualLength = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_q_menstrual_length, "field 'mRtvQMenstrualLength'", RegularTextView.class);
        menstrualSetActivity.mRtvQPeriodLength = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_q_period_length, "field 'mRtvQPeriodLength'", RegularTextView.class);
        menstrualSetActivity.mRtvQLastMenstruationDate = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_q_last_menstruation_date, "field 'mRtvQLastMenstruationDate'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MenstrualSetActivity menstrualSetActivity = this.target;
        if (menstrualSetActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        menstrualSetActivity.mItemMenstrualLength = null;
        menstrualSetActivity.mItemPeriodLength = null;
        menstrualSetActivity.mItemLastMenstruation = null;
        menstrualSetActivity.mTvTips = null;
        menstrualSetActivity.mRtvQMenstrualLength = null;
        menstrualSetActivity.mRtvQPeriodLength = null;
        menstrualSetActivity.mRtvQLastMenstruationDate = null;
        this.view7f0a0281.setOnClickListener(null);
        this.view7f0a0281 = null;
        this.view7f0a0297.setOnClickListener(null);
        this.view7f0a0297 = null;
        this.view7f0a027a.setOnClickListener(null);
        this.view7f0a027a = null;
    }
}