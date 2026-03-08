package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;
import kotlin.Metadata;

/* JADX INFO: compiled from: INoiseMonitoringView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/ido/life/module/device/view/INoiseMonitoringView;", "Lcom/ido/life/base/IBaseView;", "onSetNoiseFailed", "", "onSetNoiseSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface INoiseMonitoringView extends IBaseView {
    void onSetNoiseFailed();

    void onSetNoiseSuccess();
}