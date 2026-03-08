package com.ido.common.utils;

import android.view.View;
import android.widget.RadioGroup;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes2.dex */
public class SetUpUtil {
    public static void setUpWithRadioGroupAndViewPager(final RadioGroup radioGroup, final ViewPager viewPager) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.common.utils.-$$Lambda$SetUpUtil$O_-NwDrIRdHg9bj238gD_Uc3tzc
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                viewPager.setCurrentItem(radioGroup2.indexOfChild(radioGroup2.findViewById(i)));
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.ido.common.utils.SetUpUtil.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f2, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                View childAt = radioGroup.getChildAt(i);
                if (childAt != null) {
                    radioGroup.check(childAt.getId());
                }
            }
        });
    }
}