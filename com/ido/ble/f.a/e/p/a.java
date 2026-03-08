package com.ido.ble.f.a.e.p;

import com.google.android.material.timepicker.TimeModel;
import com.ido.ble.data.manage.database.DaoSession;
import com.ido.ble.f.a.b;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private String a(int i, int i2, int i3, boolean z) {
        StringBuilder sb;
        String str;
        if (z) {
            sb = new StringBuilder();
            sb.append(String.format("%04d", Integer.valueOf(i)));
            sb.append("-");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)));
            sb.append("-");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3)));
            sb.append(" ");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 0));
            sb.append(":");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 0));
            sb.append(":");
            str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 0);
        } else {
            sb = new StringBuilder();
            sb.append(String.format("%04d", Integer.valueOf(i)));
            sb.append("-");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)));
            sb.append("-");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3)));
            sb.append(" ");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 23));
            sb.append(":");
            sb.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 59));
            sb.append(":");
            str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, 59);
        }
        sb.append(str);
        return sb.toString();
    }

    protected DaoSession a() {
        return b.d().b();
    }

    protected Date a(int i, int i2) {
        int iAbs = Math.abs(i);
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(7) - i2;
        if (i3 == 0) {
            i3 = 7;
        }
        calendar.add(5, ((-i3) + 1) - (iAbs * 7));
        calendar.add(5, i3 + i2);
        return a(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    protected Date a(int i, int i2, int i3) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a(i, i2, i3, false));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    protected Date b(int i, int i2) {
        int iAbs = Math.abs(i);
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(7) - i2;
        if (i3 == 0) {
            i3 = 7;
        }
        calendar.add(5, ((-i3) + 1) - (iAbs * 7));
        return b(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    protected Date b(int i, int i2, int i3) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a(i, i2, i3, true));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}