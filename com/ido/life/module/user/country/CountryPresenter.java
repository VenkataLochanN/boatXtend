package com.ido.life.module.user.country;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.module.user.country.CountryContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CountryPresenter implements CountryContract.Presenter {
    private static final String TAG = "CountryPresenter";
    protected CountryAdapter mCountryAdapter;
    private List<CountryInfo> mCountryList;
    private CountryInfo mDefaultCountryInfo;
    private CountryContract.View mView;
    private List<CountryInfo> stringToCN = CountryManager.getStringToCN();

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    public CountryPresenter(CountryContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.country.CountryContract.Presenter
    public CountryInfo initDefaultCountry(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<CountryInfo> it = this.stringToCN.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CountryInfo next = it.next();
            if (TextUtils.equals(str, next.countryName)) {
                next.isChoose = true;
                this.mDefaultCountryInfo = next.m30clone();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initDefaultCountry:  mDefaultCountryInfo = countryInfo.clone(); " + next.toString());
                break;
            }
        }
        CountryInfo countryInfo = this.mDefaultCountryInfo;
        if (countryInfo != null) {
            countryInfo.type = 0;
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initDefaultCountry: mDefaultCountryInfo" + this.mDefaultCountryInfo.toString());
        }
        return this.mDefaultCountryInfo;
    }

    @Override // com.ido.life.module.user.country.CountryContract.Presenter
    public int initCountryAdapter() {
        this.mCountryAdapter.removeAll();
        if (this.mDefaultCountryInfo == null) {
            this.mCountryAdapter.addAll(this.mCountryList);
            this.mView.showCountryList(this.mCountryAdapter);
            return -1;
        }
        int i = 0;
        while (true) {
            if (i >= this.mCountryList.size()) {
                i = -1;
                break;
            }
            CountryInfo countryInfo = this.mCountryList.get(i);
            if (countryInfo != null && TextUtils.equals(countryInfo.countryCode, this.mDefaultCountryInfo.countryCode)) {
                countryInfo.isChoose = true;
                CountryAdapter countryAdapter = this.mCountryAdapter;
                countryAdapter.mCurPosition = i;
                countryAdapter.mSelCountryInfo = countryInfo;
                break;
            }
            i++;
        }
        this.mCountryAdapter.addAll(this.mCountryList);
        this.mView.showCountryList(this.mCountryAdapter);
        ChooseCountryInfo();
        return i;
    }

    @Override // com.ido.life.module.user.country.CountryContract.Presenter
    public void ChooseCountryInfo() {
        this.mView.confirmChooseCountryInfo(this.mCountryAdapter.getChooseCountryInfo());
    }

    @Override // com.ido.life.module.user.country.CountryContract.Presenter
    public void doSearch(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mView.showSideBar(false);
            ArrayList arrayList = new ArrayList();
            this.mCountryAdapter.removeAll();
            for (CountryInfo countryInfo : this.mCountryList) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "doSearch: " + countryInfo.toString());
                if (countryInfo.countryName.toUpperCase().contains(str.toUpperCase())) {
                    arrayList.add(countryInfo);
                }
            }
            CountryInfo countryInfo2 = this.mDefaultCountryInfo;
            if (countryInfo2 != null && countryInfo2.countryName.toUpperCase().contains(str.toUpperCase())) {
                this.mCountryAdapter.addItem(this.mDefaultCountryInfo);
            }
            this.mCountryAdapter.addAll(arrayList);
            if (arrayList.size() == 0) {
                this.mView.setShowTip(false);
            } else {
                this.mView.setShowTip(true);
            }
            this.mView.showCountryList(this.mCountryAdapter);
            return;
        }
        this.mView.showSideBar(false);
        this.mCountryAdapter.removeAll();
        CountryInfo countryInfo3 = this.mDefaultCountryInfo;
        if (countryInfo3 != null) {
            this.mCountryAdapter.addItem(countryInfo3);
        }
        this.mCountryAdapter.addAll(this.mCountryList);
        this.mView.setShowTip(true);
        this.mView.showCountryList(this.mCountryAdapter);
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
        this.mCountryAdapter = new CountryAdapter();
        this.mCountryList = CountryManager.makeResultCountry();
    }
}