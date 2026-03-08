package com.ido.life.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public BaseFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        this(fragmentManager, 1, list);
    }

    public BaseFragmentPagerAdapter(FragmentManager fragmentManager, int i, List<Fragment> list) {
        super(fragmentManager, i);
        setFragmentList(list);
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
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

    public void setFragmentList(List<Fragment> list) {
        if (this.mFragmentList == null) {
            this.mFragmentList = new ArrayList();
        }
        this.mFragmentList.clear();
        this.mFragmentList.addAll(list);
    }
}