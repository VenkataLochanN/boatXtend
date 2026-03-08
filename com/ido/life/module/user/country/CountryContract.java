package com.ido.life.module.user.country;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public interface CountryContract {

    public interface Presenter extends BasePresenter {
        void ChooseCountryInfo();

        void doSearch(String str);

        int initCountryAdapter();

        CountryInfo initDefaultCountry(String str);
    }

    public interface View extends BaseView<Presenter> {
        void confirmChooseCountryInfo(CountryInfo countryInfo);

        void hideInputEdit();

        void hideLoading();

        void setShowTip(boolean z);

        void showCountryList(CountryAdapter countryAdapter);

        void showLoading();

        void showSessionPositionTop(int i);

        void showSideBar(boolean z);

        void showSideBarContent(String[] strArr);
    }
}