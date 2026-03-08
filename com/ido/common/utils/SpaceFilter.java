package com.ido.common.utils;

import android.text.InputFilter;
import android.text.Spanned;

/* JADX INFO: loaded from: classes2.dex */
public class SpaceFilter implements InputFilter {
    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (charSequence.toString().contentEquals(" ")) {
            return "";
        }
        if (charSequence.length() <= 0) {
            return charSequence;
        }
        int length = charSequence.length();
        for (int i5 = 0; i5 < length; i5++) {
            if (charSequence.charAt(i5) == ' ') {
                return charSequence.subSequence(0, i5);
            }
        }
        return charSequence;
    }
}