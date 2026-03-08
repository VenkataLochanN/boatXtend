package com.ido.life.module.user.userinfo;

import android.text.TextUtils;
import com.ido.life.base.TextLengthWatcher;

/* JADX INFO: loaded from: classes3.dex */
public class UserInfoTextLengthWatcher extends TextLengthWatcher {
    public UserInfoTextLengthWatcher(int i, TextLengthWatcher.OnTextChangedListener onTextChangedListener) {
        super(i, onTextChangedListener);
    }

    @Override // com.ido.life.base.TextLengthWatcher, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        String string = charSequence.toString();
        if (TextUtils.isEmpty(string)) {
            if (this.mListener != null) {
                this.mListener.onAfterTextChanged(string);
            }
        } else {
            String strTrim = string.trim();
            String strDisposeMsgContent = disposeMsgContent(strTrim);
            if (!strTrim.contentEquals(strDisposeMsgContent) || this.mListener == null) {
                return;
            }
            this.mListener.onAfterTextChanged(strDisposeMsgContent);
        }
    }
}