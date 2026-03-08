package com.ido.life.module.user;

import com.ido.life.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
public enum CodeType {
    REG(Constants.CHECK_CODE_REG),
    FORGOT("FORGOT"),
    DESTORY("DESTORY"),
    VERIFY("VERIFY");

    String type;

    CodeType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public static CodeType get(String str) {
        if (REG.getType() == str) {
            return REG;
        }
        if (FORGOT.getType() == str) {
            return FORGOT;
        }
        if (DESTORY.getType() == str) {
            return DESTORY;
        }
        return VERIFY;
    }
}