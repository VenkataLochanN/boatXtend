package com.ido.common.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class PassWordInputFilter implements InputFilter {
    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        String str = "";
        if (!valid(charSequence, i, i2)) {
            return "";
        }
        CharSequenceWrapper charSequenceWrapper = new CharSequenceWrapper(charSequence, i, i2);
        int iCharCount = 0;
        int iCodePointAt = "_".codePointAt(0);
        int iCodePointAt2 = "@".codePointAt(0);
        int iCodePointAt3 = "#".codePointAt(0);
        int iCodePointAt4 = "*".codePointAt(0);
        int i5 = 0;
        while (iCharCount < charSequenceWrapper.length()) {
            int iCodePointAt5 = Character.codePointAt(charSequenceWrapper, iCharCount);
            if (Character.isLetter(iCodePointAt5) || Character.isDigit(iCodePointAt5) || iCodePointAt == iCodePointAt5 || iCodePointAt2 == iCodePointAt5 || iCodePointAt3 == iCodePointAt5 || iCodePointAt4 == iCodePointAt5) {
                str = str + charSequenceWrapper.charAt(i5);
            }
            iCharCount += Character.charCount(iCodePointAt5);
            i5++;
        }
        return str;
    }

    private boolean valid(CharSequence charSequence, int i, int i2) {
        return !TextUtils.isEmpty(charSequence) && i >= 0 && i2 >= 0 && i < i2 && i < charSequence.length() && i2 <= charSequence.length();
    }

    private class CharSequenceWrapper implements CharSequence {
        private final int mEnd;
        private final int mLength;
        private final CharSequence mSource;
        private final int mStart;

        CharSequenceWrapper(CharSequence charSequence, int i, int i2) {
            this.mSource = charSequence;
            this.mStart = i;
            this.mEnd = i2;
            this.mLength = i2 - i;
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.mLength;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            if (i < 0 || i >= this.mLength) {
                throw new IndexOutOfBoundsException();
            }
            return this.mSource.charAt(this.mStart + i);
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            if (i < 0 || i2 < 0 || i2 > this.mLength || i > i2) {
                return "";
            }
            PassWordInputFilter passWordInputFilter = PassWordInputFilter.this;
            CharSequence charSequence = this.mSource;
            int i3 = this.mStart;
            return passWordInputFilter.new CharSequenceWrapper(charSequence, i + i3, i3 + i2);
        }

        @Override // java.lang.CharSequence
        public String toString() {
            return this.mSource.subSequence(this.mStart, this.mEnd).toString();
        }
    }
}