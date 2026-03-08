package com.ido.life.module.device.contract;

import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.protocol.model.FrequentContactsV3;
import com.ido.life.module.device.base.BaseOperatePresenter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContractPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\nJ\u001c\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u001a\u0010\u0014\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\bH\u0014J\u0014\u0010\u0016\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/ido/life/module/device/contract/ContractPresenter;", "Lcom/ido/life/module/device/base/BaseOperatePresenter;", "Lcom/ido/life/module/device/contract/IContractView;", "()V", "mDeviceContracts", "", "Lcom/ido/ble/protocol/model/FrequentContactsV3;", "mIsDelete", "", "deleteFrequentContactsV3", "", "frequentContactsV3", "frequentContactsV3List", "getDeviceContracts", "getDeviceDeleteContracts", "onOperateQueryResult", "operateType", "Lcom/ido/ble/callback/OperateCallBack$OperateType;", "o", "", "onOperateSetResult", "b", "updateFrequentContacts", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ContractPresenter extends BaseOperatePresenter<IContractView> {
    private static final String TAG = "ContractPresenter";
    private List<FrequentContactsV3> mDeviceContracts;
    private boolean mIsDelete;

    public final void getDeviceDeleteContracts() {
    }

    public final void getDeviceContracts() {
        Log.d(TAG, "getDeviceContracts: ");
        BLEManager.queryFrequentContactsV3();
    }

    @Override // com.ido.life.module.device.base.BaseOperatePresenter
    protected void onOperateQueryResult(OperateCallBack.OperateType operateType, Object o) {
        super.onOperateQueryResult(operateType, o);
        Log.d(TAG, "onOperateQueryResult: " + operateType + o);
        if (operateType == OperateCallBack.OperateType.FREQUENT_CONTACTS) {
            if (o != null) {
                List<? extends FrequentContactsV3> list = (List) o;
                if (!list.isEmpty()) {
                    IContractView iContractView = (IContractView) getView();
                    if (iContractView != null) {
                        iContractView.setNoContractLayout(false);
                    }
                    IContractView iContractView2 = (IContractView) getView();
                    if (iContractView2 != null) {
                        iContractView2.setDeviceContracts(list);
                        return;
                    }
                    return;
                }
                IContractView iContractView3 = (IContractView) getView();
                if (iContractView3 != null) {
                    iContractView3.setNoContractLayout(true);
                    return;
                }
                return;
            }
            IContractView iContractView4 = (IContractView) getView();
            if (iContractView4 != null) {
                iContractView4.setNoContractLayout(true);
            }
        }
    }

    @Override // com.ido.life.module.device.base.BaseOperatePresenter
    protected void onOperateSetResult(OperateCallBack.OperateType operateType, boolean b2) {
        super.onOperateSetResult(operateType, b2);
        Log.d(TAG, "onOperateSetResult: " + operateType + ',' + b2);
        if (operateType == OperateCallBack.OperateType.FREQUENT_CONTACTS) {
            if (b2) {
                IContractView iContractView = (IContractView) getView();
                if (iContractView != null) {
                    iContractView.setDeviceContracts(this.mDeviceContracts);
                    return;
                }
                return;
            }
            ((IContractView) getView()).onSetFrequentFail();
        }
    }

    public final void updateFrequentContacts(List<FrequentContactsV3> frequentContactsV3List) {
        Intrinsics.checkParameterIsNotNull(frequentContactsV3List, "frequentContactsV3List");
        List<FrequentContactsV3> list = this.mDeviceContracts;
        if (list == null) {
            this.mDeviceContracts = new ArrayList();
        } else if (list != null) {
            list.clear();
        }
        List<FrequentContactsV3> list2 = this.mDeviceContracts;
        if (list2 != null) {
            list2.addAll(frequentContactsV3List);
        }
        BLEManager.setFrequentContactsV3(frequentContactsV3List);
        Log.d(TAG, "updateFrequentContacts: " + frequentContactsV3List.toString() + AppInfo.DELIM + String.valueOf(this.mDeviceContracts));
    }

    public final void deleteFrequentContactsV3(FrequentContactsV3 frequentContactsV3, List<FrequentContactsV3> frequentContactsV3List) {
        Intrinsics.checkParameterIsNotNull(frequentContactsV3, "frequentContactsV3");
        Intrinsics.checkParameterIsNotNull(frequentContactsV3List, "frequentContactsV3List");
        frequentContactsV3List.remove(frequentContactsV3);
        this.mDeviceContracts = new ArrayList();
        List<FrequentContactsV3> list = this.mDeviceContracts;
        if (list != null) {
            list.addAll(frequentContactsV3List);
        }
        BLEManager.setFrequentContactsV3(frequentContactsV3List);
        Log.d(TAG, "deleteFrequentContactsV3: " + frequentContactsV3List.toString() + AppInfo.DELIM + String.valueOf(this.mDeviceContracts));
    }
}