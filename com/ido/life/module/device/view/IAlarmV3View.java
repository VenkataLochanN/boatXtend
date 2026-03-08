package com.ido.life.module.device.view;

import com.ido.alexa.bean.AlexaV3Alarm;
import com.ido.alexa.bean.AlexaV3AlarmItem;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.life.base.IBaseView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IAlarmV3View extends IBaseView {
    void onGetAlarmList(List<AlarmV3> list);

    void onGetAlexaAlarm(List<AlexaV3AlarmItem> list);

    void onSetAlarmFailed();

    void onSetAlarmSuccess();

    void onSetAlexaAlarmFailed();

    void onSetAlexaAlarmSuccess(AlexaV3Alarm alexaV3Alarm);
}