package com.ido.life.module.device.view;

import com.ido.life.data.api.entity.TopDialPlateEntity;
import com.ido.life.module.bind.IBindView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceInfoView extends IBindView {
    void onDeleteDeviceFailed();

    void onDeleteDeviceSuccess();

    void onGetBattery(int i);

    void onRequestDialSuccess(List<TopDialPlateEntity.DialPlate> list);

    void onRequestedNewOtaInfo();
}