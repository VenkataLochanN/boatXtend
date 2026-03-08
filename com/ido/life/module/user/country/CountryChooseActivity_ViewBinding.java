package com.ido.life.module.user.country;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.SideBar;

/* JADX INFO: loaded from: classes3.dex */
public class CountryChooseActivity_ViewBinding implements Unbinder {
    private CountryChooseActivity target;
    private View view7f0a02f7;

    public CountryChooseActivity_ViewBinding(CountryChooseActivity countryChooseActivity) {
        this(countryChooseActivity, countryChooseActivity.getWindow().getDecorView());
    }

    public CountryChooseActivity_ViewBinding(final CountryChooseActivity countryChooseActivity, View view) {
        this.target = countryChooseActivity;
        countryChooseActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.country_recyclerview, "field 'mRecyclerView'", RecyclerView.class);
        countryChooseActivity.mCountrySidebar = (SideBar) Utils.findRequiredViewAsType(view, R.id.country_sidebar, "field 'mCountrySidebar'", SideBar.class);
        countryChooseActivity.mEtSerchKey = (EditText) Utils.findRequiredViewAsType(view, R.id.et_search_key, "field 'mEtSerchKey'", EditText.class);
        countryChooseActivity.mTvRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_remind, "field 'mTvRemind'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_country_tip, "field 'mIvCountryTip' and method 'toTipActivity'");
        countryChooseActivity.mIvCountryTip = (ImageView) Utils.castView(viewFindRequiredView, R.id.iv_country_tip, "field 'mIvCountryTip'", ImageView.class);
        this.view7f0a02f7 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.country.CountryChooseActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                countryChooseActivity.toTipActivity(view2);
            }
        });
        countryChooseActivity.mTvNoFindCountry = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_not_find_country, "field 'mTvNoFindCountry'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CountryChooseActivity countryChooseActivity = this.target;
        if (countryChooseActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        countryChooseActivity.mRecyclerView = null;
        countryChooseActivity.mCountrySidebar = null;
        countryChooseActivity.mEtSerchKey = null;
        countryChooseActivity.mTvRemind = null;
        countryChooseActivity.mIvCountryTip = null;
        countryChooseActivity.mTvNoFindCountry = null;
        this.view7f0a02f7.setOnClickListener(null);
        this.view7f0a02f7 = null;
    }
}