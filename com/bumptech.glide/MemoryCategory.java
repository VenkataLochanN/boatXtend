package com.bumptech.glide;

/* JADX INFO: loaded from: classes.dex */
public enum MemoryCategory {
    LOW(0.5f),
    NORMAL(1.0f),
    HIGH(1.5f);

    private float multiplier;

    MemoryCategory(float f2) {
        this.multiplier = f2;
    }

    public float getMultiplier() {
        return this.multiplier;
    }
}