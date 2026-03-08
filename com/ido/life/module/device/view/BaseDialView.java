package com.ido.life.module.device.view;

import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.life.base.IBaseView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface BaseDialView extends IBaseView {
    void onGetCurrentDial(String str);

    void onGetInstalledDialList(List<String> list);

    void onGetInstalledDialListV3(List<DialPlateParam.PlateFileInfo> list);
}