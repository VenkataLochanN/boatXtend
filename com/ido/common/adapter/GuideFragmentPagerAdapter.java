package com.ido.common.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GuideFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> pagers;
    private String[] titles;

    private boolean isNull(Object obj) {
        return obj == null;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public long getItemId(int i) {
        return i;
    }

    public GuideFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.pagers = new ArrayList();
        this.pagers.clear();
        this.pagers.addAll(list);
    }

    public GuideFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> list, String[] strArr) {
        super(fragmentManager);
        this.pagers = new ArrayList();
        this.pagers.clear();
        this.pagers.addAll(list);
        this.titles = strArr;
    }

    public void setAll(List<Fragment> list) {
        this.pagers.clear();
        this.pagers.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (isNull(this.pagers)) {
            return null;
        }
        return this.pagers.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        if (isNull(this.pagers)) {
            return 0;
        }
        return this.pagers.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        if (isNull(this.pagers) || isNull(this.titles)) {
            return null;
        }
        return this.titles[i];
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        if (isNull(this.pagers)) {
            return -1;
        }
        return this.pagers.indexOf(obj);
    }
}