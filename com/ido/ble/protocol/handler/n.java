package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.FrequentContactsV3CmdParaWrapper;
import com.ido.ble.protocol.model.MessageNotifyStateCmdParaWrapper;
import com.ido.ble.protocol.model.MusicOperate;
import com.ido.ble.protocol.model.ScheduleReminderV3CmdParaWrapper;
import com.ido.ble.protocol.model.SmallQuickModule;
import com.ido.ble.protocol.model.SmallQuickModuleCmdParaWrapper;
import com.ido.ble.protocol.model.Sport100TypeSort;
import com.ido.ble.protocol.model.SportSubItemParaSort;

/* JADX INFO: loaded from: classes2.dex */
final class n {
    n() {
    }

    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 5044) {
            b(bArr);
            return;
        }
        if (i == 5039) {
            f(bArr);
            return;
        }
        if (i == 5038) {
            e(bArr);
            return;
        }
        if (i == 5037) {
            g(bArr);
            return;
        }
        if (i == 5040) {
            h(bArr);
            return;
        }
        if (i == 5042) {
            c(bArr);
        } else if (i == 5048) {
            a(bArr);
        } else if (i == 5049) {
            d(bArr);
        }
    }

    private static void a(byte[] bArr) {
        MusicOperate.MusicAndFolderInfo musicAndFolderInfo;
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleDeviceMusicAndFolderInfo JsonString] " + strD);
        try {
            musicAndFolderInfo = (MusicOperate.MusicAndFolderInfo) new Gson().fromJson(strD, MusicOperate.MusicAndFolderInfo.class);
        } catch (Exception e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleDeviceMusicAndFolderInfo ]" + e2.getMessage());
            musicAndFolderInfo = null;
        }
        if (musicAndFolderInfo == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleDeviceMusicAndFolderInfo ] response == null");
        } else {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.MUSIC_AND_FOLDER, musicAndFolderInfo);
        }
    }

    public static boolean a(int i) {
        return i == 5044 || i == 5039 || i == 5038 || i == 5037 || i == 5040 || i == 5042 || i == 5048 || i == 5049;
    }

    private static void b(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return V3FrequentContacts JsonString] " + strD);
        FrequentContactsV3CmdParaWrapper.Response response = (FrequentContactsV3CmdParaWrapper.Response) new Gson().fromJson(strD, FrequentContactsV3CmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return V3FrequentContacts JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operat;
        if (i == 1) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
            return;
        }
        if (i == 100) {
            OperateCallBack.onAddResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
            return;
        }
        if (i == 200) {
            OperateCallBack.onDeleteResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
        } else if (i == 400) {
            OperateCallBack.onModifyResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
        } else if (i == 2) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, response.items);
        }
    }

    private static void c(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleMessageNotifyState JsonString] " + strD);
        MessageNotifyStateCmdParaWrapper.Response response = (MessageNotifyStateCmdParaWrapper.Response) new Gson().fromJson(strD, MessageNotifyStateCmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleMessageNotifyState JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operat;
        if (i == 1) {
            OperateCallBack.onAddResult(OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE, Boolean.valueOf(z));
        } else if (i == 2) {
            OperateCallBack.onModifyResult(OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE, Boolean.valueOf(z));
        } else if (i == 3) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE, response);
        }
    }

    private static void d(byte[] bArr) {
        MusicOperate.OperateResponse operateResponse;
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleMusicOperateResult JsonString] " + strD);
        try {
            operateResponse = (MusicOperate.OperateResponse) new Gson().fromJson(strD, MusicOperate.OperateResponse.class);
        } catch (Exception e2) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleMusicOperateResult JsonString] " + e2.getMessage());
            operateResponse = null;
        }
        if (operateResponse == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleMusicOperateResult ] response == null");
            return;
        }
        boolean z = operateResponse.err_code == 0;
        int i = operateResponse.operate_type;
        if (i == 0) {
            OperateCallBack.onInvalid(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
            return;
        }
        if (i == 1) {
            OperateCallBack.onDeleteMusic(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
            return;
        }
        if (i == 2) {
            OperateCallBack.onAddMusic(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z), operateResponse.music_id);
            return;
        }
        if (i == 3) {
            OperateCallBack.onDeleteFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
            return;
        }
        if (i == 4) {
            OperateCallBack.onAddFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
            return;
        }
        if (i == 5) {
            OperateCallBack.onModifyFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 6) {
            OperateCallBack.onImportFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 7) {
            OperateCallBack.onDeleteFolderMusic(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        }
    }

    private static void e(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleScheduleReminder JsonString] " + strD);
        ScheduleReminderV3CmdParaWrapper.Response response = (ScheduleReminderV3CmdParaWrapper.Response) new Gson().fromJson(strD, ScheduleReminderV3CmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleScheduleReminder JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operate;
        if (i == 1) {
            OperateCallBack.onAddResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, Boolean.valueOf(z));
            return;
        }
        if (i == 2) {
            OperateCallBack.onDeleteResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, Boolean.valueOf(z));
        } else if (i == 4) {
            OperateCallBack.onModifyResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, Boolean.valueOf(z));
        } else if (i == 3) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, response.items);
        }
    }

    private static void f(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleSmallQuickModuleSort JsonString] " + strD);
        SmallQuickModuleCmdParaWrapper.Response response = (SmallQuickModuleCmdParaWrapper.Response) new Gson().fromJson(strD, SmallQuickModuleCmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleSmallQuickModuleSort JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operate;
        if (i == 2) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.SMALL_QUICK_MODULE_SORT, Boolean.valueOf(z));
        } else if (i == 1) {
            SmallQuickModule.QueryResponse queryResponse = new SmallQuickModule.QueryResponse();
            queryResponse.items.addAll(response.items);
            queryResponse.support_items.addAll(response.support_items);
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SMALL_QUICK_MODULE_SORT, queryResponse);
        }
    }

    private static void g(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleSport100TypeSort JsonString] " + strD);
        Sport100TypeSort sport100TypeSort = (Sport100TypeSort) new Gson().fromJson(strD, Sport100TypeSort.class);
        if (sport100TypeSort == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleSport100TypeSort JsonString] response == null");
            return;
        }
        boolean z = sport100TypeSort.err_code == 0;
        int i = sport100TypeSort.operate;
        if (i == 2) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.SPORT_100_TYPE_SORT, Boolean.valueOf(z));
        } else if (i == 1) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SPORT_100_TYPE_SORT, sport100TypeSort);
        }
    }

    private static void h(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleSportSubItemParaSort JsonString] " + strD);
        SportSubItemParaSort sportSubItemParaSort = (SportSubItemParaSort) new Gson().fromJson(strD, SportSubItemParaSort.class);
        if (sportSubItemParaSort == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return handleSportSubItemParaSort JsonString] response == null");
            return;
        }
        boolean z = sportSubItemParaSort.err_code == 0;
        int i = sportSubItemParaSort.operate;
        if (i == 2) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.SPORT_SUB_ITEM_PARA_SORT, Boolean.valueOf(z));
        } else if (i == 1) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SPORT_SUB_ITEM_PARA_SORT, sportSubItemParaSort);
        }
    }
}