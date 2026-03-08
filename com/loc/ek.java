package com.loc;

import com.ido.life.util.DateUtil;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: compiled from: DateUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ek {
    private static long a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long a(long j, long j2, int i) {
        if (i > 0) {
            try {
                if (Math.abs(j - j2) > ((long) i) * 31536000000L) {
                    long jA = a(j2) + (j - a(j));
                    long jAbs = Math.abs(jA - j2);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date(jA));
                    int i2 = calendar.get(11);
                    if (i2 == 23 && jAbs >= 82800000) {
                        jA -= DateUtil.DAY;
                    }
                    return (i2 != 0 || jAbs < 82800000) ? jA : jA + DateUtil.DAY;
                }
            } catch (Throwable unused) {
            }
        }
        return j;
    }
}