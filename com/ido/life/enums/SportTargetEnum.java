package com.ido.life.enums;

/* JADX INFO: loaded from: classes2.dex */
public enum SportTargetEnum {
    TARGET_STEP(1),
    TARGET_DISTANCE(2),
    TARGET_CATEGORY(3),
    TARGET_TIME(4);

    private int mTarget;

    SportTargetEnum(int i) {
        this.mTarget = i;
    }

    public int getTarget() {
        return this.mTarget;
    }
}