package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.FrequentContactsV3CmdParaWrapper;
import com.ido.ble.protocol.model.MessageNotifyStateCmdParaWrapper;
import com.ido.ble.protocol.model.MusicOperate;
import com.ido.ble.protocol.model.ScheduleReminderV3CmdParaWrapper;
import com.ido.ble.protocol.model.SmallQuickModuleCmdParaWrapper;
import com.ido.ble.protocol.model.Sport100TypeSort;
import com.ido.ble.protocol.model.SportSubItemParaSort;

/* JADX INFO: loaded from: classes2.dex */
class j {
    j() {
    }

    public static void a() {
        u.d(com.veryfit.multi.nativeprotocol.b.R3);
    }

    static void a(FrequentContactsV3CmdParaWrapper.Add add) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(add)), com.veryfit.multi.nativeprotocol.b.N3);
    }

    static void a(FrequentContactsV3CmdParaWrapper.Delete delete) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(delete)), com.veryfit.multi.nativeprotocol.b.N3);
    }

    static void a(FrequentContactsV3CmdParaWrapper.Modify modify) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(modify)), com.veryfit.multi.nativeprotocol.b.N3);
    }

    static void a(FrequentContactsV3CmdParaWrapper.Query query) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(query)), com.veryfit.multi.nativeprotocol.b.N3);
    }

    static void a(FrequentContactsV3CmdParaWrapper.Set set) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(set)), com.veryfit.multi.nativeprotocol.b.N3);
    }

    static void a(MessageNotifyStateCmdParaWrapper.Add add) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(add)), com.veryfit.multi.nativeprotocol.b.M3);
    }

    static void a(MessageNotifyStateCmdParaWrapper.Modify modify) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(modify)), com.veryfit.multi.nativeprotocol.b.M3);
    }

    static void a(MessageNotifyStateCmdParaWrapper.Query query) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(query)), com.veryfit.multi.nativeprotocol.b.M3);
    }

    public static void a(MusicOperate musicOperate) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(musicOperate)), com.veryfit.multi.nativeprotocol.b.S3);
    }

    static void a(ScheduleReminderV3CmdParaWrapper.Add add) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(add)), com.veryfit.multi.nativeprotocol.b.J3);
    }

    static void a(ScheduleReminderV3CmdParaWrapper.Delete delete) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(delete)), com.veryfit.multi.nativeprotocol.b.J3);
    }

    static void a(ScheduleReminderV3CmdParaWrapper.Modify modify) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(modify)), com.veryfit.multi.nativeprotocol.b.J3);
    }

    static void a(ScheduleReminderV3CmdParaWrapper.Query query) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(query)), com.veryfit.multi.nativeprotocol.b.J3);
    }

    public static void a(SmallQuickModuleCmdParaWrapper.Query query) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(query)), com.veryfit.multi.nativeprotocol.b.K3);
    }

    public static void a(SmallQuickModuleCmdParaWrapper.Set set) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(set)), com.veryfit.multi.nativeprotocol.b.K3);
    }

    public static void a(Sport100TypeSort sport100TypeSort) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sport100TypeSort)), com.veryfit.multi.nativeprotocol.b.I3);
    }

    public static void a(SportSubItemParaSort sportSubItemParaSort) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sportSubItemParaSort)), com.veryfit.multi.nativeprotocol.b.L3);
    }

    public static void b(Sport100TypeSort sport100TypeSort) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sport100TypeSort)), com.veryfit.multi.nativeprotocol.b.I3);
    }

    public static void b(SportSubItemParaSort sportSubItemParaSort) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sportSubItemParaSort)), com.veryfit.multi.nativeprotocol.b.L3);
    }
}