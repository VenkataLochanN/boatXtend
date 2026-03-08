package com.ido.life.enums;

import kotlin.Metadata;

/* JADX INFO: compiled from: AmbientVolumeExposureEnum.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/ido/life/enums/AmbientVolumeExposureEnum;", "", "minVolume", "", "maxVolume", "(Ljava/lang/String;III)V", "getMaxVolume", "()I", "setMaxVolume", "(I)V", "getMinVolume", "setMinVolume", "HIGH", "MEDIUM", "NORMAL", "LOW", "app_release"}, k = 1, mv = {1, 1, 16})
public enum AmbientVolumeExposureEnum {
    HIGH(90, 120),
    MEDIUM(80, 89),
    NORMAL(75, 79),
    LOW(0, 75);

    private int maxVolume;
    private int minVolume;

    AmbientVolumeExposureEnum(int i, int i2) {
        this.minVolume = i;
        this.maxVolume = i2;
    }

    public final int getMaxVolume() {
        return this.maxVolume;
    }

    public final int getMinVolume() {
        return this.minVolume;
    }

    public final void setMaxVolume(int i) {
        this.maxVolume = i;
    }

    public final void setMinVolume(int i) {
        this.minVolume = i;
    }
}