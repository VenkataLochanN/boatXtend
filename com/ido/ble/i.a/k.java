package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.SportModeSort;

/* JADX INFO: loaded from: classes2.dex */
class k {
    k() {
    }

    public static void a() {
        u.a(com.veryfit.multi.nativeprotocol.b.j5, 405, 0, 0);
    }

    static void a(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(calorieAndDistanceGoal)), 161);
    }

    static void a(Menstrual menstrual) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(menstrual)), 159);
    }

    static void a(MenstrualRemind menstrualRemind) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(menstrualRemind)), 160);
    }

    static void a(PressureParam pressureParam) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(pressureParam)), 163);
    }

    static void a(SPO2Param sPO2Param) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sPO2Param)), 162);
    }

    static void a(SportModeSort sportModeSort) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sportModeSort)), 164);
    }

    public static void b() {
        u.a(com.veryfit.multi.nativeprotocol.b.j5, 1, 0, 0);
    }

    public static void c() {
        u.a(com.veryfit.multi.nativeprotocol.b.j5, 2, 0, 0);
    }
}