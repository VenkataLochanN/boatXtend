package com.ido.life.module.device.contract;

import com.ido.ble.protocol.model.FrequentContactsV3;
import com.ido.life.module.device.base.IBaseOperateView;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IContractView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00032\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010"}, d2 = {"Lcom/ido/life/module/device/contract/IContractView;", "Lcom/ido/life/module/device/base/IBaseOperateView;", "onSetFrequentFail", "", "setChoiceContract", "count", "", "setDeviceContracts", "frequentContactsV3", "", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "setNoContractLayout", "visible", "", "showDeleteLayout", "showEditLayout", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IContractView extends IBaseOperateView {
    void onSetFrequentFail();

    void setChoiceContract(int count);

    void setDeviceContracts(List<? extends FrequentContactsV3> frequentContactsV3);

    void setNoContractLayout(boolean visible);

    void showDeleteLayout();

    void showEditLayout();
}