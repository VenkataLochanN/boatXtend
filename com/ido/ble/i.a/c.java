package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.AllPhoneContacts;
import com.ido.ble.protocol.model.WeatherInfo;
import com.ido.ble.protocol.model.WeatherInfoV3;
import com.ido.ble.protocol.model.WeatherSunTime;

/* JADX INFO: loaded from: classes2.dex */
class c {
    c() {
    }

    public static void a(AllPhoneContacts allPhoneContacts) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(allPhoneContacts)), com.veryfit.multi.nativeprotocol.b.c4);
    }

    public static void a(WeatherInfo weatherInfo) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(weatherInfo)), 153);
    }

    public static void a(WeatherInfoV3 weatherInfoV3) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(weatherInfoV3)), com.veryfit.multi.nativeprotocol.b.O3);
    }

    public static void a(WeatherSunTime weatherSunTime) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(weatherSunTime)), com.veryfit.multi.nativeprotocol.b.l4);
    }
}