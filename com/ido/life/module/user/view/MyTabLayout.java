package com.ido.life.module.user.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.google.android.material.tabs.TabLayout;
import com.ido.common.utils.ResourceUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MyTabLayout extends TabLayout {
    private List<String> titles;

    public MyTabLayout(Context context) {
        super(context);
        this.titles = new ArrayList();
        init();
    }

    public MyTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.titles = new ArrayList();
        init();
    }

    public MyTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.titles = new ArrayList();
        init();
    }

    private void init() {
        this.titles = new ArrayList();
        addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.ido.life.module.user.view.MyTabLayout.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == null || tab.getCustomView() == null) {
                    return;
                }
                ((TextView) tab.getCustomView().findViewById(R.id.tab_text)).setTextColor(ResourceUtil.getColor(R.color.white));
                ((ImageView) tab.getCustomView().findViewById(R.id.tab_indicator)).setBackgroundResource(R.color.color_E51C23);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == null || tab.getCustomView() == null) {
                    return;
                }
                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
                textView.setTextColor(MyTabLayout.this.getResources().getColor(android.R.color.holo_red_light));
                textView.getPaint().setFakeBoldText(false);
                textView.setTextColor(ResourceUtil.getColor(R.color.white));
                ((ImageView) tab.getCustomView().findViewById(R.id.tab_indicator)).setBackgroundResource(0);
            }
        });
    }

    public void setTitle(List<String> list) {
        List<String> list2 = this.titles;
        if (list2 != null && list2.size() > 0) {
            this.titles.clear();
            removeAllTabs();
        }
        this.titles.addAll(list);
        for (String str : this.titles) {
            TabLayout.Tab tabNewTab = newTab();
            tabNewTab.setCustomView(R.layout.tablayout_item);
            if (tabNewTab.getCustomView() != null) {
                ((TextView) tabNewTab.getCustomView().findViewById(R.id.tab_text)).setText(str);
            }
            addTab(tabNewTab);
        }
    }
}