package com.ido.life.base;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class TextLengthWatcher implements TextWatcher {
    public static final int LENGTH_MAX_DEFAULT = 24;
    private static final String REGULAR_CN = "[一-龥]";
    protected OnTextChangedListener mListener;
    private int maxLength;

    public interface OnTextChangedListener {
        void onAfterTextChanged(String str);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public TextLengthWatcher() {
        this.maxLength = 24;
    }

    public TextLengthWatcher(int i, OnTextChangedListener onTextChangedListener) {
        this.maxLength = 24;
        this.maxLength = i;
        this.mListener = onTextChangedListener;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        disposeMsgContent(editable);
    }

    protected void disposeMsgContent(Editable editable) {
        if (editable.length() > 0 && TextUtils.isEmpty(editable.toString().trim())) {
            OnTextChangedListener onTextChangedListener = this.mListener;
            if (onTextChangedListener != null) {
                onTextChangedListener.onAfterTextChanged("");
                return;
            }
            return;
        }
        disposeMsgContent(editable.toString().trim());
    }

    protected String disposeMsgContent(String str) {
        OnTextChangedListener onTextChangedListener;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strTrim = str.trim();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char c2 : strTrim.toCharArray()) {
            String strValueOf = String.valueOf(c2);
            i = Pattern.compile(REGULAR_CN).matcher(strValueOf).matches() ? i + 2 : i + 1;
            if (i > this.maxLength) {
                break;
            }
            sb.append(strValueOf);
        }
        if (!sb.toString().equals(strTrim) && (onTextChangedListener = this.mListener) != null) {
            onTextChangedListener.onAfterTextChanged(sb.toString());
        }
        return sb.toString();
    }
}