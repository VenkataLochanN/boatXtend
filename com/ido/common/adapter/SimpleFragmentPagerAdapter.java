package com.ido.common.adapter;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private String[] mTitleList;

    private boolean isNull(Object obj) {
        return obj == null;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public long getItemId(int i) {
        return i;
    }

    public SimpleFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.mFragmentList = new ArrayList();
        this.mFragmentList = list;
    }

    public SimpleFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> list, String[] strArr) {
        super(fragmentManager);
        this.mFragmentList = new ArrayList();
        this.mFragmentList = list;
        this.mTitleList = strArr;
    }

    public void setAll(List<Fragment> list) {
        this.mFragmentList = list;
        notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (isNull(this.mFragmentList)) {
            return null;
        }
        return this.mFragmentList.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        if (isNull(this.mFragmentList)) {
            return 0;
        }
        return this.mFragmentList.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        if (isNull(this.mFragmentList) || isNull(this.mTitleList)) {
            return null;
        }
        return this.mTitleList[i];
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        if (isNull(this.mFragmentList)) {
            return -1;
        }
        return this.mFragmentList.indexOf(obj);
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }
}