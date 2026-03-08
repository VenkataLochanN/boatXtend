package com.ido.life.module.user.country;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class CountryChooseTipActivity_ViewBinding implements Unbinder {
    private CountryChooseTipActivity target;

    public CountryChooseTipActivity_ViewBinding(CountryChooseTipActivity countryChooseTipActivity) {
        this(countryChooseTipActivity, countryChooseTipActivity.getWindow().getDecorView());
    }

    public CountryChooseTipActivity_ViewBinding(CountryChooseTipActivity countryChooseTipActivity, View view) {
        this.target = countryChooseTipActivity;
        countryChooseTipActivity.mTvDescription = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_choose_country_description, "field 'mTvDescription'", TextView.class);
        countryChooseTipActivity.mTvOne = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_choose_country_one, "field 'mTvOne'", TextView.class);
        countryChooseTipActivity.mTvTwo = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_choose_country_two, "field 'mTvTwo'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CountryChooseTipActivity countryChooseTipActivity = this.target;
        if (countryChooseTipActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        countryChooseTipActivity.mTvDescription = null;
        countryChooseTipActivity.mTvOne = null;
        countryChooseTipActivity.mTvTwo = null;
    }
}