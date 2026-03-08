package com.ido.life.enums;

import android.graphics.Color;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public enum PressureEnum {
    HIGH(80, 99, Color.parseColor("#FC8B30"), R.string.home_pressure_high),
    MEDIUM(60, 79, Color.parseColor("#FFC327"), R.string.home_pressure_middle),
    NORMAL(30, 59, Color.parseColor("#3DB6F1"), R.string.home_pressure_normal),
    RELAX(1, 29, Color.parseColor("#67DAEC"), R.string.home_pressure_relax),
    MINZERO(0, 0, Color.parseColor("#67DAEC"), -1);

    public final int Color;
    public final int DesResId;
    public final int Max;
    public final int Min;

    PressureEnum(int i, int i2, int i3, int i4) {
        this.Min = i;
        this.Max = i2;
        this.Color = i3;
        this.DesResId = i4;
    }

    public static PressureEnum getPressureEnumByValue(int i) {
        PressureEnum pressureEnum = RELAX;
        if (i <= pressureEnum.Max) {
            return pressureEnum;
        }
        PressureEnum pressureEnum2 = NORMAL;
        if (i <= pressureEnum2.Max) {
            return pressureEnum2;
        }
        PressureEnum pressureEnum3 = MEDIUM;
        return i <= pressureEnum3.Max ? pressureEnum3 : HIGH;
    }

    public static int getPressureColor(int i) {
        PressureEnum pressureEnum = MINZERO;
        if (i <= pressureEnum.Max) {
            return pressureEnum.Color;
        }
        PressureEnum pressureEnum2 = RELAX;
        if (i <= pressureEnum2.Max) {
            return pressureEnum2.Color;
        }
        PressureEnum pressureEnum3 = NORMAL;
        if (i <= pressureEnum3.Max) {
            return pressureEnum3.Color;
        }
        PressureEnum pressureEnum4 = MEDIUM;
        if (i <= pressureEnum4.Max) {
            return pressureEnum4.Color;
        }
        return HIGH.Color;
    }
}