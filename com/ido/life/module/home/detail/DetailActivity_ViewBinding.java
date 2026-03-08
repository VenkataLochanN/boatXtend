package com.ido.life.module.home.detail;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.RegularRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class DetailActivity_ViewBinding implements Unbinder {
    private DetailActivity target;

    public DetailActivity_ViewBinding(DetailActivity detailActivity) {
        this(detailActivity, detailActivity.getWindow().getDecorView());
    }

    public DetailActivity_ViewBinding(DetailActivity detailActivity, View view) {
        this.target = detailActivity;
        detailActivity.mRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group, "field 'mRadioGroup'", RadioGroup.class);
        detailActivity.mVsSleep = (ViewStub) Utils.findRequiredViewAsType(view, R.id.vs_sleep, "field 'mVsSleep'", ViewStub.class);
        detailActivity.mIvPrevious = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_previous, "field 'mIvPrevious'", ImageView.class);
        detailActivity.mIvNext = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_next, "field 'mIvNext'", ImageView.class);
        detailActivity.mRadioDay = (RegularRadioButton) Utils.findRequiredViewAsType(view, R.id.rrb_day, "field 'mRadioDay'", RegularRadioButton.class);
        detailActivity.mRadioWeek = (RegularRadioButton) Utils.findRequiredViewAsType(view, R.id.rrb_week, "field 'mRadioWeek'", RegularRadioButton.class);
        detailActivity.mRadioMonth = (RegularRadioButton) Utils.findRequiredViewAsType(view, R.id.rrb_month, "field 'mRadioMonth'", RegularRadioButton.class);
        detailActivity.mRadioYear = (RegularRadioButton) Utils.findRequiredViewAsType(view, R.id.rrb_year, "field 'mRadioYear'", RegularRadioButton.class);
        detailActivity.mLayoutTotalData = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_total_data, "field 'mLayoutTotalData'", LinearLayout.class);
        detailActivity.mViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
        detailActivity.mDivider1 = Utils.findRequiredView(view, R.id.divider_1, "field 'mDivider1'");
        detailActivity.mDivider2 = Utils.findRequiredView(view, R.id.divider_2, "field 'mDivider2'");
        detailActivity.mDivider3 = Utils.findRequiredView(view, R.id.divider_3, "field 'mDivider3'");
        detailActivity.mMtvTotalData = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_total_data, "field 'mMtvTotalData'", MediumTextView.class);
        detailActivity.mMtvTotalData2 = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_total_data2, "field 'mMtvTotalData2'", MediumTextView.class);
        detailActivity.mRtvTotalUnit = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_total_unit, "field 'mRtvTotalUnit'", RegularTextView.class);
        detailActivity.mRtvTotalUnit2 = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_total_unit2, "field 'mRtvTotalUnit2'", RegularTextView.class);
        detailActivity.mRtvTotalTitle1 = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_total_title_1, "field 'mRtvTotalTitle1'", RegularTextView.class);
        detailActivity.mRtvTotalTitle2 = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_total_title_2, "field 'mRtvTotalTitle2'", RegularTextView.class);
        detailActivity.mRtvDate = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_date, "field 'mRtvDate'", RegularTextView.class);
        detailActivity.mMtvAvgData = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_avg_data, "field 'mMtvAvgData'", MediumTextView.class);
        detailActivity.mRtvAvgUnit = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_avg_unit, "field 'mRtvAvgUnit'", RegularTextView.class);
        detailActivity.mLayTotalOne = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_total_1, "field 'mLayTotalOne'", LinearLayout.class);
        detailActivity.mLayoutTotalTwo = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_total_2, "field 'mLayoutTotalTwo'", LinearLayout.class);
        detailActivity.mLayLoading = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_loading, "field 'mLayLoading'", LinearLayout.class);
        detailActivity.mImgLoading = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_data_loading, "field 'mImgLoading'", ImageView.class);
        detailActivity.mTvDataLoadState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_data_loading_state, "field 'mTvDataLoadState'", TextView.class);
        detailActivity.mImgLoadFailed = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_data_load_failed, "field 'mImgLoadFailed'", ImageView.class);
        detailActivity.mViewBottom = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.view_bottom, "field 'mViewBottom'", RelativeLayout.class);
        detailActivity.mRbGroundTime1 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_ground_time_1, "field 'mRbGroundTime1'", RadioButton.class);
        detailActivity.mRbGroundTime2 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_ground_time_2, "field 'mRbGroundTime2'", RadioButton.class);
        detailActivity.mRbGroundTime3 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_ground_time_3, "field 'mRbGroundTime3'", RadioButton.class);
        detailActivity.mRadioGroupGround = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_ground, "field 'mRadioGroupGround'", RadioGroup.class);
        detailActivity.mRbTopTime1 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_top_time_1, "field 'mRbTopTime1'", RadioButton.class);
        detailActivity.mRbTopTime2 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_top_time_2, "field 'mRbTopTime2'", RadioButton.class);
        detailActivity.mRbTopTime3 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_top_time_3, "field 'mRbTopTime3'", RadioButton.class);
        detailActivity.mRadioGroupTop = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group_top, "field 'mRadioGroupTop'", RadioGroup.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DetailActivity detailActivity = this.target;
        if (detailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        detailActivity.mRadioGroup = null;
        detailActivity.mVsSleep = null;
        detailActivity.mIvPrevious = null;
        detailActivity.mIvNext = null;
        detailActivity.mRadioDay = null;
        detailActivity.mRadioWeek = null;
        detailActivity.mRadioMonth = null;
        detailActivity.mRadioYear = null;
        detailActivity.mLayoutTotalData = null;
        detailActivity.mViewPager = null;
        detailActivity.mDivider1 = null;
        detailActivity.mDivider2 = null;
        detailActivity.mDivider3 = null;
        detailActivity.mMtvTotalData = null;
        detailActivity.mMtvTotalData2 = null;
        detailActivity.mRtvTotalUnit = null;
        detailActivity.mRtvTotalUnit2 = null;
        detailActivity.mRtvTotalTitle1 = null;
        detailActivity.mRtvTotalTitle2 = null;
        detailActivity.mRtvDate = null;
        detailActivity.mMtvAvgData = null;
        detailActivity.mRtvAvgUnit = null;
        detailActivity.mLayTotalOne = null;
        detailActivity.mLayoutTotalTwo = null;
        detailActivity.mLayLoading = null;
        detailActivity.mImgLoading = null;
        detailActivity.mTvDataLoadState = null;
        detailActivity.mImgLoadFailed = null;
        detailActivity.mViewBottom = null;
        detailActivity.mRbGroundTime1 = null;
        detailActivity.mRbGroundTime2 = null;
        detailActivity.mRbGroundTime3 = null;
        detailActivity.mRadioGroupGround = null;
        detailActivity.mRbTopTime1 = null;
        detailActivity.mRbTopTime2 = null;
        detailActivity.mRbTopTime3 = null;
        detailActivity.mRadioGroupTop = null;
    }
}