package com.ido.life.module.device.view;

import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.module.bind.IBindView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceView extends IBindView {
    void onDeleteDeviceFailed();

    void onDeleteDeviceSuccess(String str);

    void onGetDeviceList(List<DeviceListEntity.DeviceInfo> list, boolean z);
}