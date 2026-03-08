package com.ido.common.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentPagerStateAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    public FragmentPagerStateAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.mFragmentList = list;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.mFragmentList;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.mFragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}