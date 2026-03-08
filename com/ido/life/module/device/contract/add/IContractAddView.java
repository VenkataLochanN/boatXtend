package com.ido.life.module.device.contract.add;

import com.ido.life.module.device.base.IBaseOperateView;
import com.ido.life.module.device.contract.PhoneDto;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IContractAddView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/ido/life/module/device/contract/add/IContractAddView;", "Lcom/ido/life/module/device/base/IBaseOperateView;", "onAddFailed", "", "onAddSuccess", "onFinish", "setAllContract", "count", "", "setChoiceContract", "setPhoneContracts", "phoneDtos", "", "Lcom/ido/life/module/device/contract/PhoneDto;", "showMessage", "message", "", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IContractAddView extends IBaseOperateView {
    void onAddFailed();

    void onAddSuccess();

    void onFinish();

    void setAllContract(int count);

    void setChoiceContract(int count);

    void setPhoneContracts(List<PhoneDto> phoneDtos);

    void showMessage(String message);
}