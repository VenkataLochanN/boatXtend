package com.ido.life.module.device.view;

import com.ido.life.data.api.entity.MyDialListEntity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDialMarketActivityView extends BaseDialView {
    void onGetMyDialList(List<MyDialListEntity.DialInfo> list);

    void startLoading();
}